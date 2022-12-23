package com.Udm1.OnlineShopBackEnd;

import java.util.Collections;
import java.util.List;

public class ComparisonDemo {

    public static void main(String[] args) {
        ProductManagementService productManagementService = DefaultProductManagementService.getInstance();
        List<Product> products = productManagementService.getProducts();
        System.out.println("Unsorted" + products + "\n");
        Collections.sort(products, new CustomProductComparator());
        System.out.println("Collections:       " + products+ "\n");

        products.sort(new CustomProductComparator());
        System.out.println("List.sort():       " + products+ "\n");
    }
}
