package com.cloudcuisine.customerservice.service;

import com.cloudcuisine.customerservice.dto.customer.CustomerDto;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

}
