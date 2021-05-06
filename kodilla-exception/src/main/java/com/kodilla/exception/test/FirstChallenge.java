package com.kodilla.exception.test;

import java.time.LocalDateTime;

public class FirstChallenge {

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0){
            throw new ArithmeticException();
        }
        return a / b;
    }
    /**
     * This  main can throw an AritmeticException!!!
     */
    public static void main(String[] args) {

        FirstChallenge firstChallenge = new FirstChallenge();

        try {
            double result = firstChallenge.divide(3, 0);
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(e.toString() + "\t" + "\"Divided by zero operation cannot possible\"");
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            if (e.getMessage() != null) System.out.println(e.getMessage());
        } finally {
            LocalDateTime ldt = LocalDateTime.now();
            System.out.println(ldt + "\t"+"Attempted to divide by zero, handled by ArithmeticException");
        }
    }
}
