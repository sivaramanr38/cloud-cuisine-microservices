package com.cloudcuisine.customerservice.service;

import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(long id) throws CustomerNotFoundException;
}
