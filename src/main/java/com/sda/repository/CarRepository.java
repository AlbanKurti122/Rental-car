package com.sda.repository;

import com.sda.entity.Car;
import com.sda.static_data.StatusCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car , Long> {
    List<Car> findAllByStatus(StatusCar status);
}
