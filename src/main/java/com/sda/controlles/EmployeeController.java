package com.sda.controlles;
import com.sda.entity.Employee;
import com.sda.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/{branchId}/employee")
    public Employee addEmployee ( @PathVariable Long branchId , @RequestBody Employee employee){
          return employeeService.addEmployee(branchId,employee);
    }
    @GetMapping("/{branchId}")
    public List<Employee> findEmployeeByBranch(@PathVariable Long branchId){
        return employeeService.findEmployeeByBranch(branchId);
    }
    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        return employeeService.login(username, password);
    }
    @PostMapping("/user/create")
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }
}