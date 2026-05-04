package com.cloudcuisine.customerservice.service;

import com.cloudcuisine.customerservice.dto.customer.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getCustomers();

    CustomerDto createCustomer(CustomerDto customerDto);

}
