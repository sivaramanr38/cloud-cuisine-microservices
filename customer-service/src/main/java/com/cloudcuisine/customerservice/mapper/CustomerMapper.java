package com.cloudcuisine.customerservice.mapper;

import com.cloudcuisine.customerservice.dto.customer.AddressDto;
import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.dto.customer.PhoneDto;
import com.cloudcuisine.customerservice.model.customer.Address;
import com.cloudcuisine.customerservice.model.customer.Customer;
import com.cloudcuisine.customerservice.model.customer.Phone;

import java.util.List;

public class CustomerMapper {

    public static CustomerDto mapToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhones(
                customer.getPhones().stream()
                        .map(phone -> new PhoneDto(phone.getId(), phone.getType(), phone.getNumber()))
                        .toList()
        );
        customerDto.setAddresses(
                customer.getAddresses().stream()
                        .map(address -> new AddressDto(address.getId(), address.getStreet(),
                                address.getCity(), address.getPostalCode(),
                                address.getCountry(), address.getType()))
                        .toList()
        );

        customerDto.setActive(customer.isActive());
        customerDto.setCreatedAt(customer.getCreatedAt());
        customerDto.setUpdatedAt(customer.getUpdatedAt());
        customerDto.setRole(customer.getRole());
        customerDto.setLastLogin(customer.getLastLogin());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setActive(customerDto.isActive());
        customer.setCreatedAt(customerDto.getCreatedAt());
        customer.setUpdatedAt(customerDto.getUpdatedAt());
        customer.setRole(customerDto.getRole());
        customer.setLastLogin(customerDto.getLastLogin());

        // Map nested DTOs to entities
        if (customerDto.getPhones() != null) {
            List<Phone> phones = customerDto.getPhones().stream()
                    .map(phoneDto -> {
                        Phone phone = new Phone();
                        phone.setId(phoneDto.getId());
                        phone.setType(phoneDto.getType());
                        phone.setNumber(phoneDto.getNumber());
                        phone.setCustomer(customer); // set back-reference
                        return phone;
                    })
                    .toList();
            customer.setPhones(phones);
        }

        if (customerDto.getAddresses() != null) {
            List<Address> addresses = customerDto.getAddresses().stream()
                    .map(addressDto -> {
                        Address address = new Address();
                        address.setId(addressDto.getId());
                        address.setStreet(addressDto.getStreet());
                        address.setCity(addressDto.getCity());
                        address.setPostalCode(addressDto.getPostalCode());
                        address.setCountry(addressDto.getCountry());
                        address.setCustomer(customer); // set back-reference
                        address.setType(addressDto.getType());
                        return address;
                    })
                    .toList();
            customer.setAddresses(addresses);
        }
        return customer;
    }

}
