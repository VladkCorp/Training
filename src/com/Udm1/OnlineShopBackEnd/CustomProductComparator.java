package com.Udm1.OnlineShopBackEnd;

import java.util.Comparator;

public class CustomProductComparator implements Comparator<Product> {

    public int compare(Product product1, Product product2) {
        int categoryComparisonResult;
        int priceComparisonResult;
        int nameComparisonResult;

        categoryComparisonResult = product1
                .getCategoryName()
                .compareTo(product2
                        .getCategoryName());
        if (categoryComparisonResult != 0) {
            return categoryComparisonResult;
        }
        priceComparisonResult = Double.compare(product1
                        .getPrice(),
                        product2
                                .getPrice()); //reverse variables to sort properly?
        if (priceComparisonResult != 0) {
            return priceComparisonResult;
        }
        nameComparisonResult = product1.getProductName()
                        .compareTo(
                product2.getProductName());
        if (nameComparisonResult != 0) {
            return nameComparisonResult;
        }
        return 0;
    }


}
