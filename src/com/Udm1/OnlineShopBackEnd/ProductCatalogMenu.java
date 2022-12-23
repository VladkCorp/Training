package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String PRODUCT_ADDED_MESSAGE = " has been added to your cart. " +
            " \nIf you want to add a new product - enter the product id." +
            " \nIf you want to proceed with checkout - enter word ‘checkout’ to console\n";
    private static final String ENTER_PRODUCT_AGAIN_MESSAGE = "Enter product id to add it to the cart " +
            "or ‘menu’ if you want to navigate back to the main menu";
    private static final String EMPTY_CART_ERROR_MESSAGE = "Your cart is empty. Please, " +
            "add product to cart first and then proceed with checkout";
    private static final String WRONG_ID_MESSAGE = "There's no product under this id. Please enter other id";
    private static final String NOT_NUMBER_ENTERED_ERROR_MESSAGE = "Please enter a number";
    private static final String CHECKOUT_COMMAND = "checkout";
    private static final String EXIT_COMMAND = "exit";
    private static final String CAPSLOCKED_EXIT_COMMAND = "EXIT";
    private static final String MENU_COMMAND = "menu";
    private ApplicationContext context;
    private ProductManagementService productManagementService;
    private CheckoutMenu checkoutMenu;
    private Cart currentCart;
    int headPrintedTimes = 0;

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
        checkoutMenu = CheckoutMenu.getInstance();
    }

    //todo maybe fix bad design? Allow user to add products to cart and login later
    //so that it's less annoying
    @Override
    public void start() {
        if (context.getLoggedInUser() == null) {
            System.out.println("You are not logged in." +
                    " Please, sign in or create new account\n");
        } else {
            currentCart = new DefaultCart();

            context.setMainMenu(this);
            printMenuHeader();
            manageInput();
        }
    }

    private void manageInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        cartFillingProcess : while (true) {
            switch (userInput) {
                case MENU_COMMAND, EXIT_COMMAND, CAPSLOCKED_EXIT_COMMAND:
                    break cartFillingProcess;
                case CHECKOUT_COMMAND:
                    if (currentCart.isEmpty()) {
                        System.out.println(EMPTY_CART_ERROR_MESSAGE);
                        this.start(); //catalog restart
                    } else {
                        context.setSessionCart(currentCart);
                        checkoutMenu.start();
                        break cartFillingProcess;
                    }
                    break;
                default:
                    Product productToGet = handleProductInput(userInput);
                    if (productToGet != null) {
                        currentCart.addProduct(productToGet);
                        System.out.println(productToGet + PRODUCT_ADDED_MESSAGE);
                    }
            }
            printMenuHeader();
            userInput = sc.next();
        }
    }

    public Product handleProductInput(String userInput) {
        Product product;

        try {
            product = productManagementService
                    .getProductById(Integer
                            .parseInt(userInput));
            if (product != null) {
                return product;
            } else {
                System.out.println(WRONG_ID_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(NOT_NUMBER_ENTERED_ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void printMenuHeader() {
        System.out.println("Choose product: \n" + productManagementService.getProducts());
        if (headPrintedTimes < 1) {
            System.out.println(ENTER_PRODUCT_AGAIN_MESSAGE);
        }
        headPrintedTimes++;
    }

}

