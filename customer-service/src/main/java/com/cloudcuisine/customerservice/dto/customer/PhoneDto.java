package com.cloudcuisine.customerservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private Long id;
    private String phoneNumber;
    private String type;       // MOBILE / HOME / WORK
    private boolean isPrimary;
}
