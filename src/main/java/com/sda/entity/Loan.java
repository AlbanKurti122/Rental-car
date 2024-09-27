package com.sda.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfRental;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
