package com.sda.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.sda.entity.Car;
import com.sda.static_data.StatusCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.sda.repository.CarRepository;
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllAvailable() {
        // Arrange
        Car car1 = new Car();
        car1.setStatus(StatusCar.AVAILABLE.AVAILABLE);
        Car car2 = new Car();
        car2.setStatus(StatusCar.AVAILABLE);
        List<Car> availableCars = Arrays.asList(car1, car2);

        when(carRepository.findAllByStatus(StatusCar.AVAILABLE)).thenReturn(availableCars);

        // Act
        List<Car> result = carService.findAllAvailable();

        // Assert
        assertEquals(2, result.size());
        assertEquals(availableCars, result);
    }

    @Test
    void testFindAllAvailableEmptyList() {
        // Arrange
        List<Car> emptyList = Arrays.asList();
        when(carRepository.findAllByStatus(StatusCar.AVAILABLE)).thenReturn(emptyList);

        // Act
        List<Car> result = carService.findAllAvailable();

        // Assert
        assertEquals(0, result.size());
    }
}
