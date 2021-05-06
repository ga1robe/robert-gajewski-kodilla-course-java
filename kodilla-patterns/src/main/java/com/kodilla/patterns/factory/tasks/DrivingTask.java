package com.kodilla.patterns.factory.tasks;

public class DrivingTask implements Task {
    private final String name;
    private final String destination;
    private final String timeOfMeeting;
    private final double lengthOfRoute;
    private boolean isExecuted = false;

    public DrivingTask(final String name, final String destination, final String timeOfMeeting, final double lengthOfRoute) {
        this.name = name;
        this.destination = destination;
        this.timeOfMeeting = timeOfMeeting;
        this.lengthOfRoute = lengthOfRoute;
    }

    public String getDestination() {
        return destination;
    }

    public double getLengthOfRoute() {
        return lengthOfRoute;
    }

    public String getTimeOfMeeting() {
        return timeOfMeeting;
    }

    @Override
    public void executeTask() {
        driving(destination, lengthOfRoute);
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

    private void driving(String destination, double lengthOfRoute) {
        System.out.println("task{Driving: " + "destination: " + destination + ", " + "time of meeting: " + timeOfMeeting + ", " + "length of the route: " + lengthOfRoute + "}");
    }
}
