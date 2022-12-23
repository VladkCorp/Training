 package com.Udm1.OnlineShopBackEnd;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultOrderStoringService implements OrderStoringService {

    private static final String ORDER_INFO_STORAGE_FILE_NAME = "Orders.csv";
    private static final int ORDER_CUSTOMER_ID_INDEX = 0;

    private static final int ORDER_PRODUCTS_INDEX = 1;
    private static DefaultOrderStoringService instance;

    private OrderManagementService orderManagementService;
    private ApplicationContext context;


    {
        orderManagementService = DefaultOrderManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void saveOrder(Order order) {
        try {
            Files.writeString(Paths.get(ORDER_INFO_STORAGE_FILE_NAME),
                    convertOrderToStorableString(order),
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertOrderToStorableString(Order order) {
        return "Order Contents: [" + order.makeSeparatedProductsText() + "], "
                + order.makeCustomerIdText() + System.lineSeparator();
    }

    // Only recent orders must be serialized
    @Override
    public void serializeOrders() {
        try {
            for (int i = 0; i < orderManagementService
                    .getRecentOrders().size(); i++) {
                    saveOrder(orderManagementService
                            .getRecentOrders().get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Order must be removed from recent orders after saving to prevent its duplicate saving
    @Override
    public void serializeLoggingOutUserOrders(int loggingOutUserCustomerId) {
        try {
            for (int i = 0; i < orderManagementService
                    .getRecentOrders().size(); i++) {
                if (orderManagementService
                        .getRecentOrders().get(i)
                        .getCustomerId() == loggingOutUserCustomerId) {
                    saveOrder(orderManagementService
                            .getRecentOrders().get(i));
                    orderManagementService.removeRecentOrder(i);
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    // TODO make it thread safe if registration and reading are done simultaneously
    public void updateIdIndex(int customerId) {
        if (DefaultUser.idIndex < customerId) {
            DefaultUser.idIndex = customerId + 1;
        }
    }

    // Returns last customer id from Orders.csv
    // Not the best way to read. List is redundant. todo upgrade reading
    // TODO remove
    private int readLastCustomerId() {
        List<Integer> savedCustomerIdList = null;
        // hmm

        try (var stream = Files
                .lines(Paths.get(ORDER_INFO_STORAGE_FILE_NAME))) {
            savedCustomerIdList = stream.filter(line -> (line.length() > 1))
                    .map(line -> getCustomerIdFromLine(line))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (savedCustomerIdList != null
            && savedCustomerIdList.size() > 0  // for thread safety
            && !savedCustomerIdList.isEmpty()) {
            return savedCustomerIdList.get(
                    savedCustomerIdList.size() - 1);
        } else {
            return 0;
        }
    }

    private int getCustomerIdFromLine(String line) throws IllegalArgumentException {
        int customerIdValue = 0;

        if (line.lastIndexOf('#') >= 1) {
            customerIdValue = convertOrderIndexToInt(line
                    .substring(line
                            .lastIndexOf('#') + 1));
        } else {
            throw new IllegalArgumentException();
        }
        return customerIdValue;
    }

    // TODO use last id of customer with idindex instead of reading operation
    //  DEPRECATED: loadLastCustomerId() must be used in loadOrders()!
    // It maintains customer id after closing the program
    @Override
    public List<Order> loadOrders() {
        try (var stream = Files
                .lines(Paths.get(ORDER_INFO_STORAGE_FILE_NAME))) {
            if (DefaultUser.idIndex <= 0) {
                return Collections.emptyList();
            }
            return stream.filter(Objects::nonNull)
                    .filter(line -> (line.length() > 1
                            && (line.lastIndexOf("[") != (line.lastIndexOf("]") - 1)) ))
                    .map(line -> {
                        String[] orderElements = line.split("[\\[\\]]");
                        int currentClientId = getCustomerIdFromLine(
                                orderElements[ORDER_CUSTOMER_ID_INDEX]);
                        updateIdIndex(currentClientId);
                        return new DefaultOrder(
                                currentClientId,
                                convertOrderProductsToProductList(orderElements[ORDER_PRODUCTS_INDEX]));
                    }).collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    // order element must contain customer index!
    private int convertOrderIndexToInt(String orderElement) {
        String customerIndex = "";

        for (int i = 0; i < orderElement.length(); i++) {
            if (Character.isDigit(orderElement
                    .charAt(i))) {
                customerIndex += orderElement.charAt(i);
                if (i+1 < orderElement.length()
                        && !Character.isDigit(orderElement
                        .charAt(i+1))) {
                    break;
                }
            }
        }
        if (customerIndex == null) {
            return 0;
        }
        return Integer.parseInt(customerIndex);
    }

    private List<Product> convertOrderProductsToProductList(String products) {
        String[] separatedProducts = products
                .split("&");
        List<Product> productList = new ArrayList<>();

        for (String product:
             separatedProducts) {
            String[] productValues = product.split(",");
            if (!productValues[0].isEmpty() && !productValues[1].isEmpty()
               && !productValues[2].isEmpty() && !productValues[3].isEmpty()) {
                productList.add(
                        createDefaultProduct(productValues));
            }
        }
        return productList;
    }

    // Must use particular string array as input. Particular array must contain product object toString()
    // output split by ","! Indexes are aligned with values.
    private Product createDefaultProduct(String[] productValues) {
        int productId;
        String productName;
        String categoryName;
        double price;

        productId = Integer.parseInt(extractProductValue(productValues[0]));
        productName = extractProductValue(productValues[1]);
        categoryName = extractProductValue(productValues[2]);
        price = Double.parseDouble(extractProductValue(productValues[3]));
        if (productId <= 0
                || productName == null || productName.equals("")
                || categoryName == null || categoryName.equals("")
                || price <= 0) {
            throw new IllegalArgumentException();
        } else {
            return new DefaultProduct(productId, productName, categoryName, price);
        }
    }

    // productValueLine example: " category name=Doors"
    private String extractProductValue(String productValueLine) {
        return productValueLine
                .substring(productValueLine
                .indexOf("=") + 1);
    }

    //TODO Customer list is not deserialized yet, but IdIndex for new users is deserialized
    @Override
    public void deserializeOrders() {
        orderManagementService
                .addOrders(loadOrders());
    }

    public static DefaultOrderStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderStoringService();
        }
        return instance;
    }

}
