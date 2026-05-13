package com.cloudcuisine.customerservice.service;

import com.cloudcuisine.customerservice.dto.customer.AddressDto;
import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.dto.customer.PhoneDto;
import com.cloudcuisine.customerservice.mapper.CustomerMapper;
import com.cloudcuisine.customerservice.model.customer.Customer;
import com.cloudcuisine.customerservice.repository.CustomerRepository;
import com.cloudcuisine.customerservice.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void shouldCreateCustomerWithPhonesAndAddresses() {
        CustomerDto dto = new CustomerDto();
        dto.setId(1L);
        dto.setFirstName("Bob");
        dto.setLastName("Brown");
        dto.setEmail("bob@example.com");
        dto.setPhones(List.of(new PhoneDto(1L,"mobile", "9876543210")));
        dto.setAddresses(List.of(new AddressDto(1L,"456 Elm St", "Metropolis", "54321", "USA", "WORK")));

        Customer customer = CustomerMapper.mapToCustomer(dto);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto saved = customerService.createCustomer(dto);

        assertEquals("Bob", saved.getFirstName());
        assertEquals("bob@example.com", saved.getEmail());
        assertEquals(1, saved.getPhones().size());
        assertEquals("9876543210", saved.getPhones().get(0).getNumber());
        assertEquals("mobile", saved.getPhones().get(0).getType());
        assertEquals(1, saved.getAddresses().size());
        assertEquals("Metropolis", saved.getAddresses().get(0).getCity());
        assertEquals("WORK", saved.getAddresses().get(0).getType());
        verify(customerRepository).save(any(Customer.class));
    }
}
