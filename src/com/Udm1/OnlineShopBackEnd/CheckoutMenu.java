package com.Udm1.OnlineShopBackEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckoutMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    private static final String ORDER_SUCCESS_MESSAGE = "Thanks a lot for your purchase. " +
            "Details about order delivery are sent to your email";
    private static final String ORDER_ERROR_MESSAGE = "Error creating order. Please, try again\n";

    private static CheckoutMenu instance;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        context.setMainMenu(this);
        Order createdOrder = new DefaultOrder();
        List<Product> orderedProducts = new ArrayList<Product>();
        Scanner sc = new Scanner(System.in);
        String userCreditCardInput = "";

        orderSubmission : while (true) {
            printMenuHeader();
            userCreditCardInput = sc.nextLine();
            if (userCreditCardInput.equalsIgnoreCase("exit")){
                break orderSubmission;
            }
            createdOrder.setCreditCardNumber(userCreditCardInput);
            orderedProducts = context
                    .getSessionCart()
                    .getProducts();
            createdOrder.setProducts(orderedProducts);
            createdOrder.setCustomerId(context
                    .getLoggedInUser()
                    .getId());
            if (createdOrder.getCreditCardNumber() == null) {
                System.out.println(ORDER_ERROR_MESSAGE);
            } else {
                orderManagementService.addOrder(createdOrder);
                System.out.println(ORDER_SUCCESS_MESSAGE);
                break orderSubmission;
            }
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("Enter your credit card number without spaces " +
                "and press enter if you confirm purchase");
    }

    public static CheckoutMenu getInstance() {
        if (instance == null) {
            instance = new CheckoutMenu();
        }
        return instance;
    }
}

