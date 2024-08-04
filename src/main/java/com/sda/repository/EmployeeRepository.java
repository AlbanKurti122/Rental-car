package com.sda.repository;

import com.sda.entity.Customer;
import com.sda.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select c from Employee c where c.branch.id = :branchId")
    List<Employee> findEmployeesByBranch(Long branchId);
    Optional<Employee> findByUsername(String username);
}