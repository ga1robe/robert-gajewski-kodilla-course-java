package com.kodilla.good.patterns.food2door;

public class Purchaser {
    private String name;
    private String street;
    private String postCode;
    private String city;

    public Purchaser(String name, String street, String postCode, String city) {
        this.name = name;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }
}
