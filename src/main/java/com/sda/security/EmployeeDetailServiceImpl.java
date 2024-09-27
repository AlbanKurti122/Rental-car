package com.sda.security;

import com.sda.entity.Customer;
import com.sda.entity.Employee;
import com.sda.repository.CustomerRepository;
import com.sda.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetailServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found please create the user"));
        return new EmployeeDetailImpl(employee);
    }
}