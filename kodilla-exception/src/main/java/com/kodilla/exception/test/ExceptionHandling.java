package com.kodilla.exception.test;

import java.time.LocalDateTime;

public class ExceptionHandling {
    public static void main(String[] args) throws Exception {
        SecondChallenge secondChallenge = new SecondChallenge();
        try {
//            secondChallenge.probablyIWillThrowException(2.0, 1.5);
            secondChallenge.probablyIWillThrowException(0.9, 1.5);
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            if (e.getMessage() != null) System.out.println("Message of exception: " + e.getMessage());
        } finally {
            LocalDateTime ldt = LocalDateTime.now();
            System.out.println(ldt + "\t"+"Attempted of method \"probablyIWillThrowException\" in class \"SecondChallenge\", will throw Exception.");
        }
        System.out.println("");
    }
}
