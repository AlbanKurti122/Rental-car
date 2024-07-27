package com.sda.service.impl;

import com.sda.dto.CarDto;
import com.sda.entity.Branch;
import com.sda.entity.Car;
import com.sda.repository.BranchRepository;
import com.sda.repository.CarRepository;
import com.sda.service.CarService;
import com.sda.static_data.StatusCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Override
    public Car createCar(CarDto dto){
       Branch branch = branchRepository.findById(dto.getBranchId()).orElseThrow(
                ()-> new RuntimeException("Branch not found"));
        Car car = new Car();
        car.setColour(dto.getColour());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setBodyType(dto.getBodyType());
        car.setColour(dto.getColour());
        car.setAmountPerDay(dto.getAmountPerDay());
        car.setMileage(dto.getMileage());
        car.setBranch(branch);
        return carRepository.save(car);
    }
    @Override
    public Car bookCar(Long id){
        Car car = carRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Not Found"));
        car.setStatus(StatusCar.BOOKED);
        return carRepository.save(car);
    }
    @Override
    public List<Car> findAllAvailable(){
        return carRepository.findAllByStatus(StatusCar.AVAILABLE);
    }
}
