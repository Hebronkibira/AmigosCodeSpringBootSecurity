package com.hebronworks.etc;

public class Address {
    String street;
    String City;

    public Address(String street, String city) {
        this.street = street;
        City = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}
