package com.Udm1.OnlineShopBackEnd;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    private static final String USER_NOT_LOGGED_IN_MESSAGE = "Please, log in " +
            "or create new account to see list of your orders";
    private static final String USER_HAS_NO_ORDERS_MESSAGE = "Unfortunately, you don’t have any orders yet." +
            " Navigate back to main menu to place a new order";

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        List<Order> currentUserOrders = new ArrayList<Order>();

        context.setMainMenu(this);
        if (context.getLoggedInUser() == null) {
            System.out.println(USER_NOT_LOGGED_IN_MESSAGE);
            return;
        }
        currentUserOrders = orderManagementService
                .getOrdersByUserId(
                        context.getLoggedInUser()
                        .getId());
        if (currentUserOrders == null) {
            System.out.println(USER_HAS_NO_ORDERS_MESSAGE);
            return;
        }
        System.out.println(currentUserOrders);
    }

    @Override
    public void printMenuHeader() {
        System.out.println("Your orders: \n");
    }

}
