package com.kodilla.stream.world;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class World {
    private final List<Continent> listOfContinents = new ArrayList<>();
    public World() { }
    public List<Continent> getListOfContinents() { return listOfContinents; }
    public void addContinentToList(Continent continent){ listOfContinents.add(continent); }
    public BigDecimal getPeopleQuantity() {
        List<Country> listOfCountry =listOfContinents.stream().flatMap(continent -> continent.getListOfCountries().stream()).collect(Collectors.toList());
        return listOfCountry.stream().map(Country::getPeopleQuantity).reduce(BigDecimal.ZERO, (sum, current) -> sum = sum.add(current));
    }
    @Override
    public String toString() {
        return "list of Continents: " +
                listOfContinents.stream().map(continent -> continent.getNameOfContinent()).collect(Collectors.toList());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof World)) return false;
        World world = (World) o;
        return Objects.equals(getListOfContinents(), world.getListOfContinents());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getListOfContinents());
    }
}
