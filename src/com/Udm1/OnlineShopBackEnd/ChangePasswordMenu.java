package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

    private static final String ENTER_NEW_PASSWORD = "Enter your new password: ";
    private static final String PASSWORD_CHANGED_MESSAGE = "Your password has been successfully changed";
    private static final String ILLEGAL_PASSWORD_ERROR_MESSAGE =
            "Password must be at least 7 characters long and contain at least 1 number\n";

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    //TODO logic upgrade to check existing password and so user can doublecheck new
    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String newPassword;

        context.setMainMenu(this);
        printMenuHeader();
        newPassword = sc.next();
        if (checkUserPasswordValidity(newPassword) == true) {
            changeUserPassword(newPassword);
            System.out.println(PASSWORD_CHANGED_MESSAGE);
        }
    }

    public boolean checkUserPasswordValidity(String password){

        if (password == null ||
                password.length() < 7
                || ( !password.contains("1")
                && !password.contains("2")
                && !password.contains("3")
                && !password.contains("4")
                && !password.contains("5")
                && !password.contains("6")
                && !password.contains("7")
                && !password.contains("8")
                && !password.contains("9")) ) {
            System.out.println(ILLEGAL_PASSWORD_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void changeUserPassword(String newPassword) {
        context
                .getLoggedInUser()
                .setPassword(newPassword);
    }

    @Override
    public void printMenuHeader() {
        System.out.println(ENTER_NEW_PASSWORD);
    }

}

