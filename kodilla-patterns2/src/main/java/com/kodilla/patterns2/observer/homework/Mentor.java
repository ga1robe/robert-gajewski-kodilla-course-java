package com.kodilla.patterns2.observer.homework;

import com.kodilla.patterns2.observer.homework.Observer;

public class Mentor implements Observer {
    private final String username;
    private int updateCount;

    public Mentor(String username) {
        this.username = username;
    }

    @Override
    public void updateTask(Student student) {
        System.out.println(username + ": New Task in Queue Of " + student.getFirstName() + " "
                +student.getLastName()+ ":\t"+
                ", total: " + student.getTaskQueue().size() + " tasks");
        updateCount++;

    }

    @Override
    public void pollTask(Student student) {
        student.getTaskQueue().poll();
    }

    @Override
    public void showTask(Student student) {
        student.getTaskQueue().peek();
    }
}
