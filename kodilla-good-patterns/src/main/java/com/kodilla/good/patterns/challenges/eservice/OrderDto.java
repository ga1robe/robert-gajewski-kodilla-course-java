package com.kodilla.good.patterns.challenges.eservice;

public class OrderDto {
    private User user;
    private Product product;
    private boolean isRealized;

    public OrderDto(final User user, final Product product, final boolean isRealized) {
        this.user = user;
        this.product = product;
        this.isRealized = isRealized;
    }

    public User getUser() {

        return user;
    }

    public Product getProduct() {

        return product;
    }

    public boolean isRealized() {

        return isRealized;
    }
}