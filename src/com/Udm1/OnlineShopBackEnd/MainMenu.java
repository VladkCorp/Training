package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class MainMenu implements Menu {
    SignUpMenu signUpMenu = new SignUpMenu();
    SignInMenu signInMenu = new SignInMenu();
    SignOutMenu signOutMenu = new SignOutMenu();
    ProductCatalogMenu productCatalogMenu = new ProductCatalogMenu();
    MyOrdersMenu myOrdersMenu = new MyOrdersMenu();
    SettingsMenu settingsMenu = new SettingsMenu();
    CustomerListMenu customerListMenu = new CustomerListMenu();
    CheckoutMenu checkoutMenu = new CheckoutMenu();

    public static final String MENU_COMMAND = "menu";
    public static final String EXIT_COMMAND = "exit";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER =
            "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign In"
            + System.lineSeparator() + "3. Product Catalog"
                    + System.lineSeparator() + "4. My Orders" + System.lineSeparator() +
                    "5. Settings" + System.lineSeparator() +
            "6. Customer List\n";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER =
            "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List\n";

    private ApplicationContext context;
    private OrderStoringService orderStoringService;
    private UserStoringService userStoringService;

    {
        context = ApplicationContext.getInstance();
        orderStoringService = DefaultOrderStoringService.getInstance();
        userStoringService = DefaultUserStoringService.getInstance();
    }

    @Override
    public void start() {
        String userInput;

        context.setMainMenu(this);
        printMenuHeader();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    signUpMenu.start();
                    break;
                case "2":
                    if (context.getLoggedInUser() != null) {
                        signOutMenu.start();
                    } else {
                        signInMenu.start();
                    }
                    break;
                case "3":
                    productCatalogMenu.start();
                    break;
                case "4":
                    myOrdersMenu.start();
                    break;
                case "5":
                    settingsMenu.start();
                    break;
                case "6":
                    customerListMenu.start();
                    break;
                case EXIT_COMMAND:
                    //TODO
                    try {
                        orderStoringService.serializeOrders();
                        userStoringService.serializeUsers();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Only 1, 2, 3, 4, 5 and 'exit' is allowed. Try one more time. " );
            }
            printMenuHeader();
        }
    }

    @Override
    public void printMenuHeader() {

        if (context.getLoggedInUser() != null) {
        System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
        } else {
        System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
        }
    }

}
