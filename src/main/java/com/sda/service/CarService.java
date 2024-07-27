package com.sda.service;

import com.sda.dto.CarDto;
import com.sda.entity.Car;

import java.util.List;

public interface CarService {
    Car createCar(CarDto dto);

    Car bookCar(Long id);

    List<Car> findAllAvailable();
}
