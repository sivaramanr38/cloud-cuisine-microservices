package com.cloudcuisine.customerservice.model.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // Primary key

    private String firstName;       // Customer's first name
    private String lastName;        // Customer's last name

    @Column(unique = true, nullable = false)
    private String email;           // Unique email (used for login)

    // Relationships
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    private boolean active;         // Account status (active/inactive)

    private LocalDateTime createdAt;// When account was created
    private LocalDateTime updatedAt;// Last profile update

    private String passwordHash;    // Hashed password for authentication
    private String role;            // Role (e.g., ROLE_USER, ROLE_ADMIN)

    private String refreshToken;    // Optional: store refresh token
    private LocalDateTime lastLogin;// Track last login time

}
