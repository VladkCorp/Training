package com.Udm1.OnlineShopBackEnd;

import java.io.Serializable;
import java.util.List;

public interface OrderStoringService extends Serializable {

    void saveOrder(Order order);

    List<Order> loadOrders();

    void serializeOrders();

    void serializeLoggingOutUserOrders(int loggingOutUserCustomerId);

    void deserializeOrders();
}
