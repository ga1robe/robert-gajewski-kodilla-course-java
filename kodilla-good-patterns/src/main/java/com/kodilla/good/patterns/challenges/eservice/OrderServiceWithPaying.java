package com.kodilla.good.patterns.challenges.eservice;

import java.time.LocalDateTime;

public class OrderServiceWithPaying implements OrderService {
    @Override
    public boolean createOrder(User user, Product product, LocalDateTime orderTime){
        System.out.println(
                "INFO ORDER SERVICE\nproduct: " + product.getProductName()
                        + "\ncustomer: " + user.getNickname()
                        + "\ndate of order: " + orderTime + "\nchosen payment method\n");
        return true;
    }
}