package com.kodilla.patterns2.observer.homework;

public interface Observer {
    void updateTask(Student student);
    void pollTask(Student student);
    void showTask(Student student);
}
