package com.kodilla.patterns2.observer.homework;

import com.kodilla.patterns2.observer.homework.Observer;

public interface Observable {
    void registeryObserver(Observer observer);
    void notifyObservers();
    void removeObserver(Observer observer);
    void putToQueue(Task task);
}
