package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class SettingsMenu implements Menu {

    private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
            + "2. Change Email";
    private static final String WRONG_KEY_ERROR_MESSAGE = "Only 1, 2 is allowed. Try one more time";
    private static final String USER_NOT_LOGGED_IN_ERROR_MESSAGE = "Please, log in " +
            "or create new account to change your account settings";
    private static final int MENU_CODE = 100;

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String input;
        int key;

        if (context.getLoggedInUser() == null) {
            System.out.println(USER_NOT_LOGGED_IN_ERROR_MESSAGE);
            return;
        }
        context.setMainMenu(this);

        SettingsSelection : while(true) {

            printMenuHeader();
            input = sc.next();
            key = manageInput(input);
            switch (key) {
                case MENU_CODE:
                    return;
                case 1:
                    ChangePasswordMenu changePasswordMenu = new ChangePasswordMenu();
                    changePasswordMenu.start();
                    break SettingsSelection;
                case 2:
                    ChangeEmailMenu changeEmailMenu = new ChangeEmailMenu();
                    changeEmailMenu.start();
                    break SettingsSelection;
                default:
                    System.out.println(WRONG_KEY_ERROR_MESSAGE);
            }
        }
    }

    public int manageInput (String input) {

        if (input.equalsIgnoreCase("menu")) {
            return MENU_CODE;
        }
        try {
            return Integer.parseInt(input);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println(SETTINGS);
    }

}

