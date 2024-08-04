package com.sda.service;

import com.sda.dto.ReservationDto;
import com.sda.entity.Reservation;

import java.time.LocalDate;

public interface ReservationService {
    Reservation createReservation(ReservationDto dto);
    Boolean isCarAvailable(Reservation reservation, LocalDate fromDate, LocalDate toDate);
    Reservation closeReservation(Long id, String comments);
}
