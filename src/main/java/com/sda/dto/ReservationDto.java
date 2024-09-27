package com.sda.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long carId;
    private Long customerId;
    private Long loanBranchId;
    private Long returnBranchId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String comments;
}
