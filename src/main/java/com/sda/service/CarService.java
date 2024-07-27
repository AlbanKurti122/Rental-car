package com.sda.service;

import com.sda.dto.CarDto;
import com.sda.entity.Car;

public interface CarService {
    Car createCar(CarDto dto);
}
