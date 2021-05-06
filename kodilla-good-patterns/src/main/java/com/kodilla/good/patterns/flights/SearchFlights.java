package com.kodilla.good.patterns.flights;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchFlights {
    private List<Flight> flights;

    public SearchFlights(List<Flight> flightsList) {

        this.flights = flightsList;
    }

    public List<Flight> getByDepature(String depature) {
        return flights.stream()
                .filter(c -> depature.equals(c.getDepature()))
                .collect(Collectors.toList());
    }

    public List<Flight> getByArrival(String arrival) {
        return flights.stream()
                .filter(c -> arrival.equals(c.getArrival()))
                .collect(Collectors.toList());
    }

    public List<Flight> advanced(String depature, String arrival, String breakInTrip) {
        List<Flight> secondListOfflight = flights.stream()
                .filter(c -> breakInTrip.equals(c.getDepature()))
                .filter(c -> arrival.equals(c.getArrival()))
                .collect(Collectors.toList());
        List<Flight> firstListOfflight = flights.stream()
                .filter(c -> depature.equals(c.getDepature()))
                .filter(c -> breakInTrip.equals(c.getArrival()))
                .collect(Collectors.toList());
        return Stream.concat(firstListOfflight.stream(), secondListOfflight.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}