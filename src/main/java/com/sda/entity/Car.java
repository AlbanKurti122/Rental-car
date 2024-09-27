package com.sda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sda.static_data.StatusCar;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String brand;
    private String model;
    private String bodyType;
    private Integer year;
    private String colour;
    private Integer mileage;
    @Enumerated(EnumType.STRING)
    private StatusCar status;
    private Double amountPerDay;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Reservation> reservations;
}