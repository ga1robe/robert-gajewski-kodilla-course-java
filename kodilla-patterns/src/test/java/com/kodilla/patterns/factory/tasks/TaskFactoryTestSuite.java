package com.kodilla.patterns.factory.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskFactoryTestSuite {
    @Test
    void testFactoryShopping() {
        /**
         * Given
         */
        TaskFactory factory = new TaskFactory();
        /**
         * When
         */
        Task shopping = factory.makeTask(TaskFactory.SHOPPING);
        shopping.executeTask();
        /**
         * Then
         */
        assertEquals("buying paint", shopping.getTaskName());
        assertTrue(shopping.isTaskExecuted());
    }
    @Test
    void testFactoryPainting() {
        /**
         * Given
         */
        TaskFactory factory = new TaskFactory();
        /**
         * When
         */
        Task painting = factory.makeTask(TaskFactory.PAINTING);
        painting.executeTask();
        /**
         * Then
         */
        assertEquals("painting the wall", painting.getTaskName());
        assertTrue(painting.isTaskExecuted());
    }
    @Test
    void testFactoryDriving() {
        /**
         * Given
         */
        TaskFactory factory = new TaskFactory();
        /**
         * When
         */
        Task driving = factory.makeTask(TaskFactory.DRIVING);
        driving.executeTask();
        /**
         * Then
         */
        assertEquals("Meeting with the staff", driving.getTaskName());
        assertTrue(driving.isTaskExecuted());
    }
}
