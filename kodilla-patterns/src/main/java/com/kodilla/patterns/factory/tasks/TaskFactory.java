package com.kodilla.patterns.factory.tasks;


public final class TaskFactory {
    public static final String SHOPPING = "SHOPPING";
    public static final String PAINTING = "PAINTING";
    public static final String DRIVING = "DRIVING";

    public final Task makeTask(final String taskClass) {
        switch (taskClass) {
            case SHOPPING:
                return new ShoppingTask("buying paint", "paint", 2.0, 69.0);
            case PAINTING:
                return new PaintingTask("painting the wall", "purple", "the wall");
            case DRIVING:
                return new DrivingTask("Meeting with the staff", "the company", "10:00", 12.5);
            default:
                return null;
        }
    }
}
