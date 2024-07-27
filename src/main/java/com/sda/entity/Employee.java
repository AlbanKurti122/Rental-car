package com.sda.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String name;
    private String position;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
