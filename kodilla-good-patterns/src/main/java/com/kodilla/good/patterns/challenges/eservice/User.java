package com.kodilla.good.patterns.challenges.eservice;

import java.util.ArrayList;

public class User {
    private final String nickname;
    private final String firstName;
    private final String lastName;
    private ArrayList<OrderRequest> historyOfOrderRequests = new ArrayList<>();

    public User(final String nickname, final String firstName, final String lastName) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void addOrderToUserHistory(OrderRequest orderRequest) {
        historyOfOrderRequests.add(orderRequest);
    }
}

