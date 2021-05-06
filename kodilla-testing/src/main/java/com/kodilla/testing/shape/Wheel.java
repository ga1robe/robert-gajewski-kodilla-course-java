package com.kodilla.testing.shape;

public class Wheel implements Shape {
    private String shapeName = "wheel";
    private double radius = 0d;
    public Wheel(String shapeName, double radius) {
        this.shapeName = shapeName;
        this.radius = radius;
    }
    @Override
    public String getShapeName() {
        return shapeName;
    }
    @Override
    public double getField() {
        return Math.PI*Math.pow(radius, 2d);
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
}
