package com.Udm1.OnlineShopBackEnd;

import java.util.List;

public interface OrderManagementService {

    void addOrder(Order order);

    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrders();

    List<Order> getRecentOrders();

    void removeRecentOrder(int orderIndex);

    void clearServiceState();

    void addOrders(List<Order> loadedOrders);

    void setRecentOrders(List<Order> loadedOrders);
}

