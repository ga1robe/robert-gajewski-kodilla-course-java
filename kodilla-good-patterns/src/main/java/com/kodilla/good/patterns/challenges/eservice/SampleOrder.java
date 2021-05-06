package com.kodilla.good.patterns.challenges.eservice;

import java.time.LocalDateTime;

public class SampleOrder {

    public OrderRequest retrieve() {

        User sampleUser = new User("anonymous", "Janusz", "Mazurek");
        Product sampleProduct = new Product("Think Pad X1 Headphone");
        LocalDateTime sampleOrderTime = LocalDateTime.of(2020, 10, 1, 12, 8, 23);

        return new OrderRequest(sampleUser, sampleProduct, sampleOrderTime);

    }

}