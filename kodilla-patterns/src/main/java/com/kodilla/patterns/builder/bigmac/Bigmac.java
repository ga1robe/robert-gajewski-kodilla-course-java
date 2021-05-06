package com.kodilla.patterns.builder.bigmac;

import java.util.HashSet;
import java.util.Set;

public class Bigmac {

    private final String bun;
    private final int burgers;
    private final String sauce;

    public enum Ingredients {
        salad,
        onion,
        bacon,
        cucumber,
        chilli_peppers,
        mushrooms,
        shrimp,
        cheese
    }

    private final Set<Ingredients> ingredients;

    private Bigmac(String bun, int burgers, String sauce, Set<Ingredients> ingredients) {
        if (bun.equals("hamburger bun") || bun.equals("roll") || bun.equals("with sesame") || bun.equals("without sesame"))
            this.bun = bun;
        else
            throw new IllegalStateException("Choose your bun between 'hamburger bun', 'roll' 'with sesame' or 'without sesame'");
        if (sauce.equals("standard") || sauce.equals("1000") || sauce.equals("wysp") || sauce.equals("barbecue") || sauce.isBlank())
            this.sauce = sauce;
        else
            throw new IllegalStateException("Choose your sauce between 'standard', '1000' 'wysp' or 'barbecue'");
        this.burgers = burgers;
        this.ingredients = new HashSet<>(ingredients);
    }

    public String getBun() {
        return bun;
    }

    public int getBurgers() {
        return burgers;
    }

    public String getSauce() {
        return sauce;
    }

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {

        return "Your BigMac: " +
                "bun '" + bun + '\'' +
                ", with: " + burgers + " burgers, "
                + sauce + " sauce and ingredients: "
                + ingredients;
    }
    public static class BigmacBuilder {
        private String bun;
        private int burgers;
        private String sauce;
        private Set<Ingredients> ingredients = new HashSet<>();

        public BigmacBuilder bun(String bun) {
            this.bun = bun;
            return this;
        }

        public BigmacBuilder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public BigmacBuilder burgers(int burgers) {
            this.burgers = burgers;
            return this;
        }

        public BigmacBuilder ingredient(Ingredients ingredient) {
            ingredients.add(ingredient);
            return this;
        }

        public Bigmac build() {
            return new Bigmac(bun, burgers, sauce, ingredients);
        }
    }
}
