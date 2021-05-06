package com.kodilla.spring.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BoardTestSuite {
    @Test
    void testBeansExist() {
        /**
         * Given
         */
        ApplicationContext contex = new AnnotationConfigApplicationContext("com.kodilla.spring.portfolio");
        /**
         * When
         */
        boolean boardExists = contex.containsBean("getBoard");
        boolean toDoListExists = contex.containsBean("toDoList");
        boolean inProgressListExists = contex.containsBean("inProgressList");
        boolean doneListExists = contex.containsBean("doneList");
        /**
         * Then
         */
        System.out.println("Bean getBoard was found in the container: " + boardExists);
        System.out.println("Bean taskToDoList was found in the container: " + toDoListExists);
        System.out.println("Bean taskInProgressList was found in the container: " + inProgressListExists);
        System.out.println("Bean taskDoneList was found in the container: " + doneListExists);

        assertTrue(boardExists);
        assertTrue(toDoListExists);
        assertTrue(inProgressListExists);
        assertTrue(doneListExists);
    }

    @Test
    void testTaskAdd() {
        /**
         * Given
         */
        ApplicationContext context = new AnnotationConfigApplicationContext("com.kodilla.spring.portfolio");
        Board board = (Board)context.getBean("getBoard");
        /**
         * When
         */
        board.getToDoList().add("Task To Do list");
        board.getInProgressList().add("Task in progress list");
        board.getDoneList().add("Task done list");
        /**
         * Then
         */
        assertEquals("Task To Do list", board.getToDoList().getTasks().get(0));
        assertEquals("Task in progress list", board.getInProgressList().getTasks().get(0));
        assertEquals("Task done list", board.getDoneList().getTasks().get(0));
    }
}
