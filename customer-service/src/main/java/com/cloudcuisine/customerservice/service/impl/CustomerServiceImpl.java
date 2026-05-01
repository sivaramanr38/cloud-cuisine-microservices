package com.cloudcuisine.customerservice.service.impl;

import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.mapper.CustomerMapper;
import com.cloudcuisine.customerservice.model.customer.Customer;
import com.cloudcuisine.customerservice.repository.CustomerRepository;
import com.cloudcuisine.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer saveCustomer = customerRepository.save(customer);
        return CustomerMapper.customerToDto(saveCustomer);
    }

}
