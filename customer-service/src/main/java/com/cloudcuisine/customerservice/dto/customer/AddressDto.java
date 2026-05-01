package com.cloudcuisine.customerservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private boolean isDefault;
    private String type;       // HOME / WORK
}
