package com.kodilla.exception.test;

import java.time.LocalDateTime;

public class FlightRunner {
    public static void main(String[] args) {
        FlightLigistics flightLigistics = new FlightLigistics();

        flightLigistics.putAirportToMap("Lotnisko Turośń Kościelna", true);
        flightLigistics.putAirportToMap("Lotnisko Bielsko-Biała- Aleksandrowice", true);
        flightLigistics.putAirportToMap("Lotnisko Borne Sulinowo", true);
        flightLigistics.putAirportToMap("Lotnisko Brzeg- Skarbimierz", true);
        flightLigistics.putAirportToMap("Lotnisko Bydgoszcz- Biedaszkowo", true);
        flightLigistics.putAirportToMap("lądowisko PWSZ w Chełmie", true);
        flightLigistics.putAirportToMap("Lotnisko Chrcynno", true);
        flightLigistics.putAirportToMap("Lądowisko Częstochowa-Rudniki", false);
        flightLigistics.putAirportToMap("Lotnisko Mazury Residence", false);
        flightLigistics.putAirportToMap("Lotnisko Elbląg", false);
        flightLigistics.putAirportToMap("Lotnisko Gliwice- Trynek", false);

        Flight flight1 = new Flight("Lotnisko Turośń Kościelna", "Lotnisko Bielsko-Biała- Aleksandrowice");
        Flight flight2 = new Flight("lądowisko PWSZ w Chełmie", "Lądowisko Częstochowa-Rudniki");
        Flight flight3 = new Flight("Lotnisko Mazury Residence", "Lotnisko Elbląg");

        try {
            System.out.println("\nPossible findFlight " + flight1.toString() + ": " + flightLigistics.findFlight(flight1));
        } catch (RouteNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(LocalDateTime.now() + "\tTried to findFlight " + flight1.toString() + ".");
        }

        try {
            System.out.println("\nPossible findFlight " + flight2.toString() + ": " + flightLigistics.findFlight(flight2));
        } catch (RouteNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(LocalDateTime.now() + "\tTried to findFlight " + flight2.toString() + ".");
        }

        try {
            System.out.println("\nPossible findFlight " + flight3.toString() + ": " + flightLigistics.findFlight(flight3));
        } catch (RouteNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(LocalDateTime.now() + "\tTried to findFlight " + flight3.toString() + ".");
        }
    }
}
