package com.sda.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfBooking;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Double amount;
    @ManyToOne
    @JoinColumn(name ="customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "loan_branch_id")
    private Branch loanBranch;
    @ManyToOne
    @JoinColumn(name = "return_branch_id")
    private Branch returnbranch;
}