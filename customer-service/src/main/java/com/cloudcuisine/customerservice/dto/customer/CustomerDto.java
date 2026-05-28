package com.cloudcuisine.customerservice.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "First name can not be blank")
    @Size(min = 2, max = 50, message = "First Name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name can not be blank")
    @Size(min = 1, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Email should be valid")
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
