package com.kodilla.patterns.factory.tasks;

public class ShoppingTask implements Task {
    private final String name;
    private final String thingToBuy;
    private final double quantity;
    private final double price;
    private boolean isTaskExecuted = false;

    public ShoppingTask(final String name, final String thingToBuy, final double quantity, final double price) {
        this.name = name;
        this.thingToBuy = thingToBuy;
        this.quantity = quantity;
        this.price = price;
    }

    public String getThingToBuy() {
        return thingToBuy;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void executeTask() {
        shopping(thingToBuy, quantity);
        isTaskExecuted = true;
    }

    @Override
    public String getTaskName() {
        return name;
    }

    @Override
    public boolean isTaskExecuted() {
        return isTaskExecuted;
    }

    private void shopping(String thingToBuy, double quantity) {
        System.out.println("task{Shopping: " + "thing to buy: " + thingToBuy + ", " + "quantity: " + quantity + ", " + "price: " + price + "}");
    }
}
