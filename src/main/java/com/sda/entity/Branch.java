package com.sda.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "branches")
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String city;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Car>cars;
}