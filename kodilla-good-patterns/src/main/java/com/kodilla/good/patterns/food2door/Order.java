package com.kodilla.good.patterns.food2door;

import java.util.Objects;

public class Order {
    private int orderNumber;
    private Purchaser purchaser;
    private Product product;
    private boolean completed;

    public Order(int orderNumber, Purchaser purchaser, Product product, boolean completed) {
        this.orderNumber = orderNumber;
        this.purchaser = purchaser;
        this.product = product;
        this.completed = completed;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getOrderNumber() == order.getOrderNumber() &&
                isCompleted() == order.isCompleted() &&
                Objects.equals(getPurchaser(), order.getPurchaser()) &&
                Objects.equals(getProduct(), order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNumber(), getPurchaser(), getProduct(), isCompleted());
    }
}
