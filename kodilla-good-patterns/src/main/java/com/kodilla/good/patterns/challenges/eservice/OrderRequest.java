package com.kodilla.good.patterns.challenges.eservice;

import java.time.LocalDateTime;

public class OrderRequest {
    private User user;
    private Product product;
    private LocalDateTime orderTime;

    public OrderRequest(final User user, final Product product, final LocalDateTime orderTime) {
        this.user = user;
        this.product = product;
        this.orderTime = orderTime;
    }

    public User getUser() {

        return user;
    }

    public Product getProduct() {

        return product;
    }

    public LocalDateTime getOrderTime() {

        return orderTime;
    }
}