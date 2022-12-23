package com.Udm1.OnlineShopBackEnd;

import java.util.List;

public class CustomerListMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;
    private static final String NO_REGISTERED_USERS_MESSAGE = "No registered users at the moment. " +
            "Be the first to register!";

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        List<User> users;
        String customerList = "";

        context.setMainMenu(this);
        users = userManagementService.getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) != null) {
                customerList += users.
                        get(i).toString() + " ";
            } else {
                break;
            }
        }
        if (customerList.length() <= 1) {
            System.out.println(NO_REGISTERED_USERS_MESSAGE);
        } else {
            printMenuHeader();
            System.out.println(customerList);
        }
    }

    @Override
    public void printMenuHeader() {
        System.out.println("Our customers: \n");
    }
}
