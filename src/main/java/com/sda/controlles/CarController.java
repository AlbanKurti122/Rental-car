package com.sda.controlles;

import com.sda.dto.CarDto;
import com.sda.entity.Car;
import com.sda.service.CarService;
import com.sda.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping("/car/createCar")
    public Car createCar(@RequestBody CarDto car){
        return carService.createCar(car);
    }
}
