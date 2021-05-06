package com.kodilla.testing.shape;

public class Triangle implements Shape {
    private String shapeName = "triangle";
    private double base = 0d;
    private double height = 0d;
    public Triangle(String shapeName, double base, double height) {
        this.shapeName = shapeName;
        this.base = base;
        this.height = height;
    }
    @Override
    public String getShapeName() {
        return shapeName;
    }
    @Override
    public double getField() { return 0.5*base*height; }
    public void setBase(double base) {
        this.base = base;
    }
    public void setHeight(double height) {
        this.height = height;
    }
}
