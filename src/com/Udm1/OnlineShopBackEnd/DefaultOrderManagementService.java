package com.Udm1.OnlineShopBackEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderManagementService implements OrderManagementService, Serializable {

    private static DefaultOrderManagementService instance;

    private List<Order> orders;   // all orders including loaded orders
    private List<Order> recentOrders; // orders to be saved, made at current runtime

    {
        orders = new ArrayList<Order>();
        recentOrders = new ArrayList<Order>();
    }

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

//  Runtime orders must be added to both recent and total orders
    @Override
    public void addOrder(Order order) {
        if (order == null) {
            return;
        }
        recentOrders.add(order);
        orders.add(order);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> userOrders = new ArrayList<Order>();

        for (Order order : orders) {
            if (order != null
                    && order.getCustomerId() == userId) {
                userOrders.add(order);
            }
        }
        if (userOrders.size() == 0) {
            return null;
        }
        return userOrders;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> copyOfOrders = orders;
        return copyOfOrders;
    }

    @Override
    public List<Order> getRecentOrders() {
        List<Order> copyOfRecentOrders = recentOrders;
        return copyOfRecentOrders;
    }

    @Override
    public void removeRecentOrder(int orderIndex) {
        recentOrders.remove(orderIndex);
    }

    @Override
    public void addOrders(List<Order> loadedOrders) {
        orders.addAll(loadedOrders);
    }

    @Override
    public void setRecentOrders(List<Order> loadedOrders) {
        recentOrders = loadedOrders;
    }

    public void clearServiceState() {
        orders.clear();
    }
}

