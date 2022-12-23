package com.Udm1.OnlineShopBackEnd;

import java.util.Scanner;

public class SignUpMenu implements Menu {
    private DefaultUser user;
    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    //Registers user
    @Override
    public void start() {
        context.setMainMenu(this);
        context.logOutLoggedInUser();
        String registrationResult = "";

        registration : while (true) {
            printMenuHeader();
            registrationResult = userManagementService
                    .registerUser(user);
            System.out.println(registrationResult);
            if (registrationResult.contains("New user successfully created\n")) {
                context.setLoggedInUser(user);
                break registration;
            }
        }
    }

//    Asks for and collects input
    @Override
    public void printMenuHeader() {
        Scanner scanner = new Scanner(System.in);
        user = new DefaultUser();

        System.out.println("Please, enter Your first name: ");
        user.setFirstName(scanner.next());
        System.out.println("Your last name: ");
        user.setLastName(scanner.next());
        System.out.println("create your password: ");
        user.setPassword(scanner.next());
        System.out.println("Please, enter your email: ");
        user.setEmail(scanner.next());
    }

}

