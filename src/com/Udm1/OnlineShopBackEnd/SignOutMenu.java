package com.Udm1.OnlineShopBackEnd;

public class SignOutMenu implements Menu {

    private ApplicationContext context;
    private OrderManagementService orderManagementService;
    private OrderStoringService orderStoringService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
        orderStoringService = DefaultOrderStoringService.getInstance();
    }

    @Override
    public void start() {
        context.setMainMenu(this);
        orderStoringService.serializeLoggingOutUserOrders(context
                .getLoggedInUser()
                .getId());
        context.logOutLoggedInUser();
        System.out.println("Have a nice day! Looking forward to welcoming back!\n");
    }

    @Override
    public void printMenuHeader() {
        System.out.println("Signing out...");
    }

}

