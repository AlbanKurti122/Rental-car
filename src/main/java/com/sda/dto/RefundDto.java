package com.sda.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RefundDto {
    private String comments;
    private LocalDate dateOfReturn;
    private Long reservationId;
}
