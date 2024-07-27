package com.sda.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String colour;
    private Integer mileage;
    private String status;
    private Double amountPerDay;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}