package com.kodilla.good.patterns.flights;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println();

        List<Flight> flightsList = new ArrayList<>();
        flightsList.add(new Flight("Warszawa", "Szczecin"));
        flightsList.add(new Flight("Szczecin","Wroclaw"));
        flightsList.add(new Flight("Szczecin","Warszawa"));
        flightsList.add(new Flight("Koszalin","Lodz"));
        flightsList.add(new Flight("Rzeszow","Gdansk"));
        flightsList.add(new Flight("Gdansk","Wroclaw"));
        flightsList.add(new Flight("Gdansk","Krakow"));
        flightsList.add(new Flight("Krakow","Wroclaw"));
        flightsList.add(new Flight("Gdansk","Wroclaw"));

        SearchFlights searchFlights = new SearchFlights(flightsList);

        List<Flight> byDepatureList = searchFlights.getByDepature("Gdansk");

        System.out.println("Departure from Gdansk");

        byDepatureList.stream().map(f -> f.toString()).forEach(System.out::println);

        List<Flight> byArrivalList = searchFlights.getByArrival("Wroclaw");

        System.out.println("\nArrival to Wroclaw");

        byArrivalList.stream().map(f -> f.toString()).forEach(System.out::println);

        List<Flight> advancedList = searchFlights.advanced("Gdansk", "Wroclaw", "Krakow");

        System.out.println("\nDeparture from Gdansk, Arrival to Wroclaw, with break in Krakow");

        advancedList.stream().map(f -> f.toString()).forEach(System.out::println);
    }
}
