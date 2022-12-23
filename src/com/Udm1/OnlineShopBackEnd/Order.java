package com.Udm1.OnlineShopBackEnd;

import java.util.List;

public interface Order {

    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String userInput);

    void setProducts(List<Product> products);

    void setCustomerId(int customerId);

    int getCustomerId();

    String getCreditCardNumber();

    List<Product> getProducts();

    String makeSeparatedProductsText();

    String makeCustomerIdText();

    void clearServiceState();
}
