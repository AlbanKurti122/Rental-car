package com.sda.service.impl;

import com.sda.dto.RefundDto;
import com.sda.entity.Refund;
import com.sda.entity.Reservation;
import com.sda.repository.RefundRepository;
import com.sda.repository.ReservationRepository;
import com.sda.service.EmployeeService;
import com.sda.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
     private RefundRepository refundRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmployeeService employeeService;
    @Override
    public Refund create(RefundDto refundDto){
        Reservation reservation = reservationRepository.findById(refundDto.getReservationId()).orElseThrow(
                () -> new RuntimeException("Reservation not found"));
        Refund refund = new Refund();
        Long days = ChronoUnit.DAYS.between(reservation.getDateTo(),refundDto.getDateOfReturn());
        if (days != 0) {
            refund.setSurcharge(days * reservation.getCar().getAmountPerDay());
        } else {
            refund.setSurcharge(0);
        }
        refund.setComments(refundDto.getComments());
        refund.setReservation(reservation);
        refund.setDateOfReturn(refundDto.getDateOfReturn());
        refund.setEmployee(employeeService.findEmployeeLoggedIn());
        return refundRepository.save(refund);
    }
}
