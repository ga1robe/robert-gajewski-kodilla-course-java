package com.kodilla.good.patterns.food2door;

import java.util.ArrayList;
import java.util.List;

public class DistributionService implements Service {
    private List<Order> orders = new ArrayList<>();
    private int orderNumber;

    public void addNewOrder(Purchaser purchaser, Product product) {
        orderNumber++;
        Order order = new Order(orderNumber, purchaser, product, false);
        orders.add(order);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void setOrderAsCompleted(Order order) {
        for (Order ord : orders) {
            if (ord.equals(order)) {
                ord.setCompleted(true);
            }
        }
    }

    public List<Order> getActiveOrders() {
        List<Order> orders = new ArrayList<>();
        this.orders.stream()
                .filter(order -> !order.isCompleted())
                .forEach(order -> orders.add(order));
        return orders;
    }
}
