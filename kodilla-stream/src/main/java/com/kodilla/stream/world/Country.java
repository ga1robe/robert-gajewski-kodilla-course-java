package com.kodilla.stream.world;

import java.math.BigDecimal;
import java.util.Objects;

public final class Country {
    private final String nameOfCountry;
    private final BigDecimal peopleQuantity;
    public Country(final String nameOfCountry, final BigDecimal peopleQuantity) {
        this.nameOfCountry = nameOfCountry;
        this.peopleQuantity = peopleQuantity;
    }
    public String getNameOfCountry() { return nameOfCountry; }
    public BigDecimal getPeopleQuantity() { return this.peopleQuantity; }
    @Override
    public String toString() {
        return "Country {" +
                "nameOfCountry='" + nameOfCountry + '\'' +
                ", peopleQuantity=" + peopleQuantity +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(getNameOfCountry(), country.getNameOfCountry());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getNameOfCountry());
    }
}
