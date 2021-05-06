package com.kodilla.exception.test;

public class RouteNotFoundException extends Exception {
    public RouteNotFoundException(final String message) {
//        System.out.println("Route Not Found Exception: " + message);
        super("Route Not Found Exception: " + message);
    }
}
