package com.cloudcuisine.customerservice.service.impl;

import com.cloudcuisine.customerservice.dto.customer.CustomerDto;
import com.cloudcuisine.customerservice.mapper.CustomerMapper;
import com.cloudcuisine.customerservice.model.customer.Customer;
import com.cloudcuisine.customerservice.repository.CustomerRepository;
import com.cloudcuisine.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        return customers.stream()
                        .map(CustomerMapper::mapToDto)
                        .toList();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer saveCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToDto(saveCustomer);
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.getReferenceById(id);
        return CustomerMapper.mapToDto(customer);
    }

}
