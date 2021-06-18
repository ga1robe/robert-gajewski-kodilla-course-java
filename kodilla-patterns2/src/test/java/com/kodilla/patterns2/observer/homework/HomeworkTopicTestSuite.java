package com.kodilla.patterns2.observer.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkTopicTestSuite {
    @Test
    public void testUpdate(){
        /*
        * Given
        * */
        Student firststudent = new Student("first", "student");
        Student secondstudent = new Student("second","student");
        Student thirdstudent = new Student("next", "student");

        Mentor firstmentor = new Mentor("first mentor");
        Mentor secondmentor = new Mentor("next mentor");

        Task task1 = new Task("task");
        Task task2 = new Task("task1");
        Task task3 = new Task  ("task2");
        /* When */
        firststudent.registeryObserver(secondmentor);
        secondstudent.registeryObserver(firstmentor);
        secondstudent.registeryObserver(secondmentor);
        thirdstudent.registeryObserver(secondmentor);
        secondstudent.putToQueue(task2);
        secondstudent.putToQueue(task3);
        firststudent.putToQueue(task1);
        firststudent.putToQueue(task2);
        thirdstudent.putToQueue(task1);
        /* Then */
        assertEquals(5,secondmentor.updateCount());
        assertEquals(2, firstmentor.updateCount());
    }
}
