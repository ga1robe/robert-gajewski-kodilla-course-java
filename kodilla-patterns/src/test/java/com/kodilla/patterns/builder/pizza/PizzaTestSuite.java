package com.kodilla.patterns.builder.pizza;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaTestSuite {

    @Test
    void testPizzaNew() {
        /**
         * Given
         */
//        Pizza pizza = new Pizza("Thin", "Spicy", "Mushrooms", "Onion", "Ham");
        Pizza pizza = new Pizza.PizzaBuilder()
                .bottom("Thin")
                .sauce("Spicy")
                .ingredient("Onion")
                .ingredient("Ham")
                .ingredient("Mushrooms")
                .build();
        System.out.println(pizza);
        /**
         * When
         */
        int howManyIngredients = pizza.getIngredients().size();
        /**
         * Then
         */
        assertEquals(3, howManyIngredients);
    }
}
