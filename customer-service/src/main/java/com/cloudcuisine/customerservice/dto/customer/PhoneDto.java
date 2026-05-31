package com.cloudcuisine.customerservice.dto.customer;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PhoneDto {
    private Long id;

    @NotBlank(message = "Phone must no be blank")
    private String type;     // MOBILE / HOME / WORK

    @Pattern(regexp = "//d{10}", message = "Phone must be of 10 digits")
    private String number;

    public PhoneDto(Long id, String type, String number) {
        this.id = id;
        this.type = type;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
