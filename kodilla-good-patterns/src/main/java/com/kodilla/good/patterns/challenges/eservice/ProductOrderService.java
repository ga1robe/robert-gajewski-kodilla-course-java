package com.kodilla.good.patterns.challenges.eservice;


public class ProductOrderService {
    public static void main(String[] args) {
        SampleOrder sampleOrder = new SampleOrder();
        OrderRequest orderRequest = sampleOrder.retrieve();

        OrderProcessor orderProcessor = new OrderProcessor(
                new OrderServiceWithPaying(),
                new InfoServiceSMS(),
                new OrderRepositoryProducts()
        );
        OrderDto orderDto = orderProcessor.process(orderRequest);

        if (orderDto.isRealized()) {
            System.out.println("SUMMARY: "
                    + orderDto.getProduct().getProductName()
                    + " was ordered by "
                    + orderDto.getUser().getNickname() + ".");
        } else {
            System.out.println("SUMMARY: The order couldn't be completed.");
        }
    }
}
