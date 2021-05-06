package com.kodilla.good.patterns.challenges.eservice;

import java.util.ArrayList;

public class OrderRepositoryProducts implements OrderRepository {
    private static ArrayList<OrderRequest> orderRequestRepository = new ArrayList<>();

    @Override
    public void addOrderToRepository(OrderRequest orderRequest) {
        orderRequestRepository.add(orderRequest);
        System.out.println("INFO SERVICE:\nthe order was added to the products category\n");
    }
}