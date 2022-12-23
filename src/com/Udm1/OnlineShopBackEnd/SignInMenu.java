package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class SignInMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        context.setMainMenu(this);
        printMenuHeader();
    }

    // can be separated to methods if input is made differently
    @Override
    public void printMenuHeader() {
        String email;
        String password;
        String signInResult;
        Scanner sc = new Scanner(System.in);

        System.out.println("Email : ");
        email = sc.next();
        System.out.println("Password : ");
        password = sc.next();
        signInResult = userManagementService
                .checkSignInPassword(email, password);

        System.out.println(signInResult);
        if (signInResult.contains("Welcome")) {
            context.setLoggedInUser(userManagementService
                    .getUserByEmail(email));
        }
    }

}
