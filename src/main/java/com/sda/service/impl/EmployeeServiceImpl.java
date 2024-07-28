package com.sda.service.impl;

import com.sda.entity.Employee;
import com.sda.repository.BranchRepository;
import com.sda.repository.EmployeeRepository;
import com.sda.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Override
    public void createEmployee(Employee employee){
        employeeRepository.save(employee);
    }
}
