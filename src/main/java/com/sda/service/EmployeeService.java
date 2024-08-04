package com.sda.service;

import com.sda.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Long branchId, Employee employee);
    List<Employee> findEmployeeByBranch(Long branchId);
    Employee loadingByUsername(String username);
    ResponseEntity<?> login(String username, String password);
    Employee create(Employee employee);

    Employee findEmployeeLoggedIn();
}
