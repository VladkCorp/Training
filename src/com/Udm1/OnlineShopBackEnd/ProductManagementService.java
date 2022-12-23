package com.Udm1.OnlineShopBackEnd;

import java.util.List;

public interface ProductManagementService {

    List<Product> getProducts();

    Product getProductById(int productIdToAddToCart);

    void clearServiceState();
}
