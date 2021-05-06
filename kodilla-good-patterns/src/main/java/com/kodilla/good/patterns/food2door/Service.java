package com.kodilla.good.patterns.food2door;

import java.util.List;

public interface Service {
    List<Order> getOrders();
    void setOrderAsCompleted(Order order);
}
