package com.kodilla.patterns2.observer.homework;

public class Task {
    private final String taskname;
    private  Status status;

    public Task(String name) {
        this.taskname = name;
        this.status = Status.WAIT; /* default */
    }

    public String getTaskname() {
        return taskname;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
