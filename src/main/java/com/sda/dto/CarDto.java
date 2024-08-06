package com.sda.dto;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String colour;
    private Integer mileage;
    private Double amountPerDay;
    private Long branchId;
    private String image;
}
