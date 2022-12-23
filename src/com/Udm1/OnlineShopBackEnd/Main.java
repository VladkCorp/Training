package com.Udm1.OnlineShopBackEnd;

public class Main {

    public static final String EXIT_COMMAND = "exit";
    static OrderManagementService orderManagementService;
    static UserManagementService userManagementService;
    static OrderStoringService orderStoringService;
    static UserStoringService userStoringService;

    static {
        orderManagementService = DefaultOrderManagementService.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
        orderStoringService = DefaultOrderStoringService.getInstance(); // TODO remove after testing loadOrders() output
        userStoringService = DefaultUserStoringService.getInstance(); // TODO remove after testing loadUsers() output
    }

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        try {
            orderStoringService.deserializeOrders();
            userStoringService.deserializeUsers();
            System.out.println("ORDERS: loaded orders - " + orderManagementService.getOrders()); //TODO remove!
            System.out.println("USERS: loaded users - " + userManagementService.getUsers()); //TODO remove!
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mainMenu.start();
    }

}
