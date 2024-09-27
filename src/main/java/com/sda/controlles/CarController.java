package com.sda.controlles;

import com.sda.dto.CarDto;
import com.sda.entity.Car;
import com.sda.service.CarService;
import com.sda.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping("/car/createCar")
    public Car createCar(@RequestBody CarDto car){
        return carService.createCar(car);
    }
    @PutMapping("/car/bookCar")
    public Car bookCar(@RequestParam Long id){
        return carService.bookCar(id);
    }
    @GetMapping("/car/availableCar")
    public List<Car> availableCar(){
        return carService.findAllAvailable();
    }
    @GetMapping("/car/getAvailableCar")
    public List<Car> getAvailableCar(@RequestParam Long branchId){
        return carService.getAvailableCarInBranch(branchId);
    }
}
