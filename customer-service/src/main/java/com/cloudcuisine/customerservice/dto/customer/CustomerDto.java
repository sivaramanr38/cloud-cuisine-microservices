package com.cloudcuisine.customerservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<PhoneDto> phones;
    private List<AddressDto> addresses;

    private boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String passwordHash;    // Hashed password for authentication
    private String role;            // Role (e.g., ROLE_USER, ROLE_ADMIN)

    private String refreshToken;    // Optional: store refresh token
    private LocalDateTime lastLogin;// Track last login time

}
