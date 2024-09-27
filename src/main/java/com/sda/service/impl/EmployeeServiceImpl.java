package com.sda.service.impl;
import com.sda.entity.Branch;
import com.sda.entity.Employee;
import com.sda.exception.GenericException;
import com.sda.repository.BranchRepository;
import com.sda.repository.EmployeeRepository;
import com.sda.service.EmployeeService;
import com.sda.static_data.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private BranchRepository branchRepository;
    @Override
    public Employee addEmployee(Long branchId, Employee employee){
        if (employeeRepository.findByUsername(employee.getUsername()).isPresent()){
            throw GenericException.existsBy("username", employee.getUsername());
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(()-> new RuntimeException("Branch not found"));
        employee.setRole(Role.ROLE_EMPLOYEE);
        employee.setBranch(branch);
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> findEmployeeByBranch(Long branchId){
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(()-> new RuntimeException("Branch not found"));
        return employeeRepository.findEmployeesByBranch(branchId);
    }

    @Override
    public Employee loadingByUsername(String username){
        return employeeRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public ResponseEntity<?> login(String username, String password){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(authentication.isAuthenticated());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public Employee create(Employee employee){
        if (employeeRepository.findByUsername(employee.getUsername()).isPresent()){
            throw GenericException.existsBy("username", employee.getUsername());
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setActive(true);
        employee.setRole(Role.ROLE_MANAGER);
        return employeeRepository.save(employee);
    }
    @Override
    public Employee findEmployeeLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return employeeRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
