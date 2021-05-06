package com.kodilla.patterns.builder.bigmac;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BigmacTestSuite {

    @Test
    public void testBicmacNew() {
        /**
         * Given
         */
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .bun("roll")
                .sauce("barbecue")
                .ingredient(Bigmac.Ingredients.mushrooms)
                .ingredient(Bigmac.Ingredients.onion)
                .burgers(3)
                .ingredient(Bigmac.Ingredients.cucumber)
                .ingredient(Bigmac.Ingredients.salad)
                .ingredient(Bigmac.Ingredients.bacon)
                .ingredient(Bigmac.Ingredients.shrimp)
                .ingredient(Bigmac.Ingredients.mushrooms)
                .ingredient(Bigmac.Ingredients.chilli_peppers)
                .ingredient(Bigmac.Ingredients.cheese)
                .build();
        /**
         * When
         */
        int howManyIngredients = bigmac.getIngredients().size();
        String kindOfSauce = bigmac.getSauce();

        //Then
        /**
         * Then
         */
        assertEquals(8, howManyIngredients);
        assertEquals("barbecue", kindOfSauce);
        System.out.println("Number of ingredients: " + howManyIngredients + ": " + bigmac.getIngredients());
        System.out.println("Sauce: " + bigmac.getSauce());
    }

    @Test
    public void testWithoutIngredients() {
        /**
         * Given
         */
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .bun("without sesame")
                .sauce("standard")
                .burgers(5)
                .build();
        /**
         * When
         */
        boolean areNotIngredients = bigmac.getIngredients().isEmpty();
        /**
         * Then
         */
        assertTrue(areNotIngredients);
        System.out.println("No ingredients? (" + bigmac.getIngredients() + "): " + areNotIngredients);
    }
}
