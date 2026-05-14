package com.cloudcuisine.customerservice.service;

import com.cloudcuisine.customerservice.dto.customer.AddressDto;
import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.dto.customer.PhoneDto;
import com.cloudcuisine.customerservice.mapper.CustomerMapper;
import com.cloudcuisine.customerservice.model.customer.Address;
import com.cloudcuisine.customerservice.model.customer.Customer;
import com.cloudcuisine.customerservice.model.customer.Phone;
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
    void shouldReturnCustomersWithPhonesAndAddresses() {
        // Arrange: build Customer entity with phones and addresses
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Alice");
        customer.setLastName("Smith");
        customer.setEmail("alice@example.com");

        Phone phone = new Phone();
        phone.setId(1L);
        phone.setType("mobile");
        phone.setNumber("1234567890");
        phone.setCustomer(customer);

        Address address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setPostalCode("12345");
        address.setCountry("USA");
        address.setType("HOME");
        address.setCustomer(customer);

        customer.setPhones(List.of(phone));
        customer.setAddresses(List.of(address));

        when(customerRepository.findAll()).thenReturn(List.of(customer));

        // Act
        List<CustomerDto> result = customerService.getCustomers();

        // Assert
        assertEquals(1, result.size());
        CustomerDto dto = result.get(0);
        assertEquals("Alice", dto.getFirstName());
        assertEquals("1234567890", dto.getPhones().get(0).getNumber());
        assertEquals("Springfield", dto.getAddresses().get(0).getCity());
        verify(customerRepository).findAll();
    }

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
