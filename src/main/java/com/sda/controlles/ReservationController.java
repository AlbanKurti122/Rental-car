package com.sda.controlles;

import com.sda.dto.ReservationDto;
import com.sda.entity.Reservation;
import com.sda.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @PostMapping("/dto/create")
    public Reservation create(@RequestBody ReservationDto dto){
         return reservationService.createReservation(dto);
    }
    @PostMapping("/carAvailable")
   public Boolean carAvailable(@RequestBody Reservation reservation, @RequestParam LocalDate dateForm, @RequestParam LocalDate dateTo) {
        return !(reservation.getDateFrom().isBefore(dateForm) &&
                reservation.getDateTo().isAfter(dateTo));
   }
   @PostMapping("/{id}/closeReservation")
   public Reservation closeReservation(@PathVariable Long id, @RequestBody String comment){
        return reservationService.closeReservation(id, comment);
   }
}