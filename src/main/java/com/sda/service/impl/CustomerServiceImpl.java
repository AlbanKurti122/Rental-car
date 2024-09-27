package com.sda.service.impl;

import com.sda.entity.Customer;
import com.sda.repository.CustomerRepository;
import com.sda.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}