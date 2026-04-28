package com.cloudcuisine.customerservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean isActive;
    private LocalDateTime registeredAt;

    // Nested DTOs
    private List<PhoneDto> phones;
    private List<AddressDto> addresses;
}
