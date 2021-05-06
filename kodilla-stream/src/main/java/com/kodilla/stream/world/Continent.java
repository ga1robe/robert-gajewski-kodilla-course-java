package com.kodilla.stream.world;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Continent {
    private final String nameOfContinent;
    private final List<Country> listOfCountries = new ArrayList<>();
    public Continent(String nameOfContinent) {
        this.nameOfContinent = nameOfContinent;
    }
    public String getNameOfContinent() { return nameOfContinent; }
    public List<Country> getListOfCountries() { return this.listOfCountries; }
    public void addCountryToList(Country country) { listOfCountries.add(country); }
}
