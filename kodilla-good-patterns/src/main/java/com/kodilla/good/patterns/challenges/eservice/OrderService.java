package com.kodilla.good.patterns.challenges.eservice;

import java.time.LocalDateTime;

public interface OrderService {
    boolean createOrder(User user, Product product, LocalDateTime orderTime);
}
