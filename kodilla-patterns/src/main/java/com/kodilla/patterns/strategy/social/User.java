package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.BuyPredictor;
import com.kodilla.patterns.strategy.social.publisher.FacebookPublisher;

public class User {
    private final String username;
    protected SocialPublisher socialPublisher;

    public User(final String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String sharePost() {
        return socialPublisher.share();
    }

    public void setSharePost(SocialPublisher theSocialPublisher) {
        socialPublisher = theSocialPublisher;
    }
}
