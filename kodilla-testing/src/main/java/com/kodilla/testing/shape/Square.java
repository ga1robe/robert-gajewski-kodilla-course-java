package com.kodilla.testing.shape;

public class Square implements Shape {
    private String shapeName = "square";
    private double side = 0d;
    public Square(String shapeName, double side) {
        this.shapeName = shapeName;
        this.side = side;
    }
    @Override
    public String getShapeName() {
        return shapeName;
    }
    @Override
    public double getField() {
        return Math.pow(side, 2d);
    }
    public void setSide(double side) {
        this.side = side;
    }
}
