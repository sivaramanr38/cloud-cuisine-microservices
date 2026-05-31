package com.cloudcuisine.customerservice.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddressDto {

    private Long id;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @Pattern(regexp = "\\d{6}", message = "Postal code must be 6 digits")
    private String postalCode;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "Type is required")
    private String type;       // HOME / WORK

    public AddressDto(Long id, String street, String city, String postalCode, String country, String type) {
        this.city = city;
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.country = country;
        this.type = type;
    }

    public AddressDto(long l, String s, String metropolis, String number, String usa, String work) {
    }

    public AddressDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
