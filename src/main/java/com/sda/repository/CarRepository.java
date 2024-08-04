package com.sda.repository;

import com.sda.entity.Car;
import com.sda.static_data.StatusCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car , Long> {
    List<Car> findAllByStatus(StatusCar status);
    @Query("select c from Car c where c.branch.id = :branchId and c.status = :status")
    List<Car> findByStatusInBranch(Long branchId, StatusCar status);
}