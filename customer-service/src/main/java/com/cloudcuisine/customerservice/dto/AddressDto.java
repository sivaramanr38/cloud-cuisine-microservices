package com.cloudcuisine.customerservice.dto;

public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private boolean isDefault;
    private String type;       // HOME / WORK
}
