package com.Udm1.OnlineShopBackEnd;

public class ApplicationContext {

    private static ApplicationContext instance;

    private User loggedInUser;
    private Menu mainMenu;
    private Cart sessionCart;

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    private ApplicationContext() {
    }

    public void setLoggedInUser(User user) {
        if (this.sessionCart != null) {
            this.sessionCart.clear(); // we have to clear session cart when new user is logged in
        }
        logOutLoggedInUser(); //just in case
        this.loggedInUser = user;
    }

    public User getLoggedInUser() { return this.loggedInUser; }

    public void logOutLoggedInUser () {
        this.loggedInUser = null;
    }

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    }

    public Menu getMainMenu() {
        return this.mainMenu;
    }

    public void setSessionCart(Cart sessionCart) {
        if (sessionCart != null) {
            this.sessionCart = sessionCart;
        } else {
            System.out.println("Cannot process empty cart");
        }
    }

    public Cart getSessionCart() {

        if (this.sessionCart == null) {
            this.sessionCart = new DefaultCart();
        }
        return this.sessionCart;
    }

}
