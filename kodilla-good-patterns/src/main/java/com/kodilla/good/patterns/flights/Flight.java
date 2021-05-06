package com.kodilla.good.patterns.flights;

import java.util.*;
import java.util.stream.Collectors;

public class Flight {
    private String depature;
    private String arrival;

    public Flight(String depature, String arrival) {
        this.depature = depature;
        this.arrival = arrival;
    }

    public String getDepature() {

        return depature;
    }

    public String getArrival() {

        return arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(depature, flight.depature) && Objects.equals(arrival, flight.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depature, arrival);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "Departure=" + depature + ", " +
                "Arrival=" + arrival +
                '}';
    }
}