package com.Udm1.OnlineShopBackEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrder implements Order, Serializable {

    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private String creditCardNumber;
    private List<Product> products = new ArrayList<Product>();
    private int customerId;

    public DefaultOrder() {
    }

    public DefaultOrder(int customerId, List<Product> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public DefaultOrder(int customerId) {
        this.customerId = customerId;
    }

    //TODO. Not a bank check!
    @Override
    public boolean isCreditCardNumberValid(String creditCardNumber) {
        if (creditCardNumber.length() == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER) {
            for (int i = 0; i < creditCardNumber.length() ; i++) {
                if (!Character.
                        isDigit(creditCardNumber
                                .charAt(i))
                        || Character
                        .isSpaceChar(creditCardNumber
                                .charAt(i)) ) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        if (isCreditCardNumberValid(creditCardNumber)) {
            this.creditCardNumber = creditCardNumber;
        } else {
            System.out.println("Invalid credit card number");
        }
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    @Override
    public int getCustomerId() {
        return this.customerId;
    }

    @Override
    public String getCreditCardNumber() {
        return creditCardNumber;
    }


    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void clearServiceState() {
        this.customerId = -1;
        this.creditCardNumber = null;
        this.products = null;
    }

    @Override
    public String toString() {
        return "Order customer ID=" + customerId + "{" +
                "products=" + products + "}";
    }

    @Override
    public String makeSeparatedProductsText() {
        String productsText = "";

        for (int i = 0; i < products.size(); i++) {
            productsText += products.get(i).toString();
            if (i < products.size() - 1) {
                productsText += " & ";
            }
        }
        return productsText;
    }

    @Override
    public String makeCustomerIdText() {
        char locationMarker = '#';

        return locationMarker + String.valueOf(customerId);
    }

}

