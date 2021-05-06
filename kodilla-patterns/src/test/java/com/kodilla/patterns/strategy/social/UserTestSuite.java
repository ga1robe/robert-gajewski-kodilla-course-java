package com.kodilla.patterns.strategy.social;

import com.kodilla.patterns.strategy.social.publisher.FacebookPublisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTestSuite {
    @Test
    void testDefaultSharingStrategies() {
        /**
         * Given
         */
        User milenials = new Millenials("Millennials");
        User yGeneration = new YGeneration("Generation Y");
        User zGeneration = new ZGeneration("Generation Z");
        /**
         * When
         */
        String milenialsShare = milenials.sharePost();
        System.out.println(milenials.getUsername() + " prefer " + milenialsShare);
        String yGenerationShare = yGeneration.sharePost();
        System.out.println(yGeneration.getUsername() + " prefer " + yGenerationShare);
        String zGenerationShare = zGeneration.sharePost();
        System.out.println(zGeneration.getUsername() + " prefer " + zGenerationShare);
        /**
         * Then
         */
        assertEquals("[TWITTER]", milenialsShare);
        assertEquals("[FACEBOOK]", yGenerationShare);
        assertEquals("[SNAPCHAT]", zGenerationShare);
    }
    @Test
    void testIndividualSharingStrategy() {
        /**
         * Given
         */
        User milenials = new Millenials("Millennials");
        /**
         * When
         */
        String milenialsShare = milenials.sharePost();
        System.out.println(milenials.getUsername() + " prefer " + milenialsShare);
        milenials.setSharePost(new FacebookPublisher());
        milenialsShare = milenials.sharePost();
        System.out.println(milenials.getUsername() + " prefer " + milenialsShare);
        /**
         * Then
         */
        assertEquals("[FACEBOOK]", milenialsShare);
    }
}
