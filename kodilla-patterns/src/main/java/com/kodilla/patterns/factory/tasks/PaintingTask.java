package com.kodilla.patterns.factory.tasks;

public class PaintingTask implements Task {
    private final String name;
    private final String thingToPaint;
    private final String color;
    private boolean isExecuted = false;

    public PaintingTask(final String name, final String color, final String thingToPaint) {
        this.name = name;
        this.color = color;
        this.thingToPaint = thingToPaint;
    }

    public String getThingToPaint() {
        return thingToPaint;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void executeTask() {
        painting(thingToPaint, color);
        isExecuted = true;
    }

    @Override
    public String getTaskName() {
        return name;
    }

    @Override
    public boolean isTaskExecuted() {
        return isExecuted;
    }

    private void painting(String thingToPaint, String color) {
        System.out.println("task{Painting: " + "thing to paint: " + thingToPaint + ", " + "on color: " + color + "}");
    }
}
