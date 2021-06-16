package com.kodilla.patterns2.decorator.pizza;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaOrderTestSuite {
    @Test
    public void testThinCrustHamMushroomsTomatoSaucePizzaGetCost() {
        /* Given */
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new PizzaThinCrustDecorator(theOrder);
        theOrder = new PizzaCheeseDecorator(theOrder);
        theOrder = new PizzaHamDecorator(theOrder);
        theOrder = new PizzaMushroomsDecorator(theOrder);
        theOrder = new PizzaTomatoSauceDecorator(theOrder);
        /* When */
        BigDecimal calculatedCost = theOrder.getCost();
        /* Then */
        assertEquals(new BigDecimal(26), calculatedCost);
        System.out.println("Ordered pizza price: " + calculatedCost);
    }

    @Test
    public void testThinCrustHamMushroomsTomatoSaucePizzaGetDescription() {
        /* Given */
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new PizzaThinCrustDecorator(theOrder);
        theOrder = new PizzaCheeseDecorator(theOrder);
        theOrder = new PizzaHamDecorator(theOrder);
        theOrder = new PizzaMushroomsDecorator(theOrder);
        theOrder = new PizzaTomatoSauceDecorator(theOrder);
        /* When */
        String description = theOrder.getDescription();
        /* Then */
        assertEquals("Ordered pizza description:\n" +
                "  - thin-crust\n" +
                "  - cheese\n" +
                "  - ham\n" +
                "  - mushrooms\n" +
                "  - tomato sauce\n" ,
                description);
        System.out.println(description);
    }

    @Test
    public void testThickCrustDoubleCheeseHamMushroomsOnionBbqSaucePizzaGetCost() {
        /* Given */
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new PizzaThickCrustDecorator(theOrder);
        theOrder = new PizzaCheeseDecorator(theOrder);
        theOrder = new PizzaCheeseDecorator(theOrder);
        theOrder = new PizzaHamDecorator(theOrder);
        theOrder = new PizzaMushroomsDecorator(theOrder);
        theOrder = new PizzaOnionDecorator(theOrder);
        theOrder = new PizzaBbqSauceDecorator(theOrder);
        /* When */
        BigDecimal calculatedCost = theOrder.getCost();
        /* Then */
        assertEquals(new BigDecimal(35), calculatedCost);
        System.out.println("Ordered pizza price: " + calculatedCost);
    }

    @Test
    public void testThickCrustDoubleCheeseHamMushroomsOnionBbqSaucePizzaGetDescription() {
        /* Given */
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new PizzaThickCrustDecorator(theOrder);
        theOrder = new PizzaCheeseDecorator(theOrder);
        theOrder = new PizzaCheeseDecorator(theOrder);
        theOrder = new PizzaHamDecorator(theOrder);
        theOrder = new PizzaMushroomsDecorator(theOrder);
        theOrder = new PizzaOnionDecorator(theOrder);
        theOrder = new PizzaBbqSauceDecorator(theOrder);
        /* When */
        String description = theOrder.getDescription();
        /* Then */
        assertEquals("Ordered pizza description:\n" +
                "  - thick-crust\n" +
                "  - cheese\n" +
                "  - cheese\n" +
                "  - ham\n" +
                "  - mushrooms\n" +
                "  - onion\n" +
                "  - BBQ sauce\n" ,
                description);
        System.out.println(description);
    }
}
