package com.kodilla.exception.test;

import java.util.*;
import java.util.stream.Collectors;

public class FlightLigistics {
    private List<Flight> flightList = new ArrayList<>();
    private HashMap<String,Boolean> availableAirport = new HashMap<>();

    public void addFlightToList(Flight flight) {

        flightList.add(flight);
    }
    public boolean removeFlightFromList(Flight flight) {

        return flightList.remove(flight);
    }
    public void putAirportToMap(String airport, boolean isAvalable) {

        availableAirport.put(airport,isAvalable);
    }
    public HashMap<String,Boolean> getAvailableAirportMap() {

        return availableAirport;
    }
    public boolean findFlight(Flight flight) throws RouteNotFoundException {

        HashMap<String,Boolean> availableAirportMap = getAvailableAirportMap();
        Set<String> setOfNameOfDepartureAirport = availableAirportMap.entrySet().stream().filter((airport) -> airport.getKey().equals(flight.getDepartureAirport())).map((airport) -> airport.getKey()).collect(Collectors.toSet());
        Set<String> setOfNameOfArrivalAirport = availableAirportMap.entrySet().stream().filter((airport) -> airport.getKey().equals(flight.getArrivalAirport())).map((airport) -> airport.getKey()).collect(Collectors.toSet());

        Set<Boolean> setOfAvailableDepartureAirport = availableAirportMap.entrySet().stream().filter((airport) -> airport.getKey().equals(flight.getDepartureAirport())).map((airport) -> airport.getValue()).collect(Collectors.toSet());
        Set<Boolean> setOfAvailableArrivalAirport = availableAirportMap.entrySet().stream().filter((airport) -> airport.getKey().equals(flight.getArrivalAirport())).map((airport) -> airport.getValue()).collect(Collectors.toSet());

        String nameOfDepartureAirport = setOfNameOfDepartureAirport.toString().replace("[","\"").replace("]","\"");
        String nameOfArrivalAirport = setOfNameOfArrivalAirport.toString().replace("[","\"").replace("]","\"");

        Boolean isAvailableDepartureAirport = Boolean.FALSE;
        Boolean isAvailableArrivalAirport = Boolean.FALSE;

        if (setOfAvailableDepartureAirport.size() == 1) isAvailableDepartureAirport = setOfAvailableDepartureAirport.iterator().next();
        if (setOfAvailableArrivalAirport.size() == 1) isAvailableArrivalAirport = setOfAvailableArrivalAirport.iterator().next();

        if (isAvailableDepartureAirport == Boolean.FALSE && isAvailableArrivalAirport == Boolean.FALSE) {
            throw new RouteNotFoundException("Both Airports " + nameOfDepartureAirport + " and " + nameOfArrivalAirport + " are not available!");
        } else if (isAvailableDepartureAirport == Boolean.FALSE) {
            throw new RouteNotFoundException("Departure Airport " + nameOfDepartureAirport + " is not available!");
        } else if (isAvailableArrivalAirport == Boolean.FALSE) {
            throw new RouteNotFoundException("Arrival Airport " + nameOfArrivalAirport + " is not available!");
        }

        return isAvailableDepartureAirport && isAvailableArrivalAirport;
    }
}
