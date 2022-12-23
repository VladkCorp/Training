package com.Udm1.OnlineShopBackEnd;

import java.io.Serializable;

public interface Product extends Serializable {

    int getId();

    String getCategoryName();

    double getPrice();

    void setPrice(double price);

    String getProductName();

}

