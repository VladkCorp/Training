package com.Udm1.OnlineShopBackEnd;

import java.util.ArrayList;
import java.util.List;

//TODO RETEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class DefaultCart implements Cart {

    //TODO must get capacity calculation upgrade soon...
    private static final int DEFAULT_CART_CAPACITY = 20;

    public List<Product> productsInCart = new ArrayList<Product>();

    @Override
    public boolean isEmpty() {
        if (productsInCart == null
                || productsInCart.size() < 1) {
            return true;
        }
        for (Product product : productsInCart) {
            if (product != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addProduct(Product product) {
        if (product == null) {
            return;
        }
        productsInCart.add(product);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> copyOfProductsInCart = productsInCart;
        return copyOfProductsInCart;
    }

    @Override
    public void clear() {
        productsInCart.clear();
    }

}
