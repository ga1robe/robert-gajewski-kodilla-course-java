package com.kodilla.good.patterns.challenges.eservice;

public class OrderProcessor {
    private InfoService informationService;
    private OrderService orderService;
    private OrderRepository orderRepository;

    public OrderProcessor(final OrderService orderService,
                          final InfoService informationService,
                          final OrderRepository orderRepository) {
        this.informationService = informationService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    public OrderDto process(final OrderRequest orderRequest) {
        boolean isRealized = orderService.createOrder(orderRequest.getUser(), orderRequest.getProduct(),
                orderRequest.getOrderTime());

        if (isRealized) {
            informationService.informationUserAboutOrder(orderRequest.getUser());
            orderRepository.addOrderToRepository(orderRequest);
            orderRequest.getUser().addOrderToUserHistory(orderRequest);
            return new OrderDto(orderRequest.getUser(), orderRequest.getProduct(), true);
        } else {
            return new OrderDto(orderRequest.getUser(), orderRequest.getProduct(), false);
        }
    }
}