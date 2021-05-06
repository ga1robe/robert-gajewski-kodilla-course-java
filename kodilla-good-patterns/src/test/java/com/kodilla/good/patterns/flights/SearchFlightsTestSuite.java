package com.kodilla.good.patterns.flights;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchFlightsTestSuite {
    @Test
    public void testSearchFlightsByDepature() {
        /**
         * given
         */
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

        /**
         * when
         */
        List<Flight> byDepatureList = searchFlights.getByDepature("Gdansk");

        /**
         * then
         */
        assertEquals(3, byDepatureList.size());
    }

    @Test
    public void testSearchFlightsByArrival() {
        /**
         * given
         */
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

        /**
         * when
         */
        List<Flight> byArrivalList = searchFlights.getByArrival("Wroclaw");

        /**
         * then
         */
        assertEquals(4, byArrivalList.size());
    }

    @Test
    public void testSearchFlightsAdvanced() {
        /**
         * given
         */
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

        /**
         * when
         */
        List<Flight> advancedList = searchFlights.advanced("Gdansk", "Wroclaw", "Krakow");

        /**
         * then
         */
        assertEquals(2, advancedList.size());
    }
}
