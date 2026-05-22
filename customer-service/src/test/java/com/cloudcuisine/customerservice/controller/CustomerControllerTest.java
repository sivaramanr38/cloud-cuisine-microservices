package com.cloudcuisine.customerservice.controller;

import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.dto.customer.AddressDto;
import com.cloudcuisine.customerservice.dto.customer.PhoneDto;
import com.cloudcuisine.customerservice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Test
    void testGetCustomers() throws Exception {
        CustomerDto customer1 = new CustomerDto(
        );

        CustomerDto customer2 = new CustomerDto(
        );

        Mockito.when(customerService.getCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Alice")))
                .andExpect(jsonPath("$[1].firstName", is("Bob")))
                .andExpect(jsonPath("$[0].phones[0].number", is("1234567890")))
                .andExpect(jsonPath("$[1].addresses[0].city", is("CityB")));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerDto saved = new CustomerDto(
        );

        Mockito.when(customerService.createCustomer(Mockito.any(CustomerDto.class))).thenReturn(saved);

        String requestJson = """
                {
                  "firstName": "Charlie",
                  "lastName": "Brown",
                  "email": "charlie@example.com",
                  "phones": [
                    {"type": "mobile", "number": "1112223333"}
                  ],
                  "addresses": [
                    {"street": "Street 3", "city": "CityC", "postalCode": "33333", "country": "CountryC", "type": "home"}
                  ],
                  "active": true,
                  "role": "ROLE_USER"
                }
                """;

        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.firstName", is("Charlie")))
                .andExpect(jsonPath("$.phones[0].number", is("1112223333")))
                .andExpect(jsonPath("$.addresses[0].city", is("CityC")));
    }

    @Test
    void testGetConfig() throws Exception {
        mockMvc.perform(get("/api/customer/config"))
                .andExpect(status().isOk());
        // If you set spring.datasource.url in application-test.properties,
        // you can assert its exact value here.
    }
}
