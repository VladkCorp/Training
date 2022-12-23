package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

    private static final String ENTER_NEW_EMAIL = "Enter your new email: ";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE =
            "You have to input email to register. \n";
    private static final String IMPOSSIBLE_EMAIL_ERROR_MESSAGE =
            "Email you entered isn't correct. \n";
    private static final String EMAIL_CHANGED_MESSAGE = "Your email has been successfully changed";

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String email;

        context.setMainMenu(this);
        printMenuHeader();
        email = sc.next();
        if (checkUserEmailValidity(email) == true) {
            changeUserEmail(email);
            System.out.println(EMAIL_CHANGED_MESSAGE);
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println(ENTER_NEW_EMAIL);
    }

    public boolean checkUserEmailValidity(String email) {

        if (email == null) {
            System.out.println(EMPTY_EMAIL_ERROR_MESSAGE);
            return false;
        } else if (!email.contains("@")) {
            System.out.println(IMPOSSIBLE_EMAIL_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void changeUserEmail(String newEmail) {
        context
                .getLoggedInUser()
                .setEmail(newEmail);
    }

}
