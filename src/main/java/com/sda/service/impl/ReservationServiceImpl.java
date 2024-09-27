package com.sda.service.impl;
import com.sda.dto.RefundDto;
import com.sda.dto.ReservationDto;
import com.sda.entity.Car;
import com.sda.entity.Employee;
import com.sda.entity.Loan;
import com.sda.entity.Reservation;
import com.sda.repository.*;
import com.sda.service.RefundService;
import com.sda.service.ReservationService;
import com.sda.static_data.StatusCar;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;
    private final LoanRepository loanRepository;
    private final RefundService refundService;
    @Override
    public Reservation createReservation(ReservationDto dto) {
        Car car = carRepository.findById(dto.getCarId()).orElseThrow(
                () -> new RuntimeException("Car not found"));
        List<Reservation> existingReservations = car.getReservations();
        Boolean result = true;
        for (Reservation existingReservation : existingReservations){
            result = isCarAvailable(existingReservation, dto.getFromDate(), dto.getToDate());
            if (!result) break;
        }
        if (result) {
            Employee employee = employeeRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            Reservation reservation = new Reservation();
            reservation.setCar(car);
            reservation.setCustomer(customerRepository.findById(dto.getCustomerId()).orElseThrow(
                    () -> new RuntimeException("Customer not found")));
            reservation.setLoanBranch(branchRepository.findById(dto.getLoanBranchId()).orElseThrow(
                    () -> new RuntimeException("Branch not found")));
            reservation.setReturnbranch(branchRepository.findById(dto.getReturnBranchId()).orElseThrow(
                    () -> new RuntimeException("Branch not found")));
            reservation.setDateFrom(dto.getFromDate());
            reservation.setDateTo(dto.getToDate());
            reservation.setDateOfBooking(LocalDate.now());
            Long days = ChronoUnit.DAYS.between(dto.getFromDate(), dto.getToDate());
            reservation.setAmount(reservation.getCar().getAmountPerDay() * days);
            reservation = reservationRepository.save(reservation);
            Loan loan = new Loan();
            loan.setReservation(reservation);
            loan.setEmployee(employee);
            loan.setDateOfRental(LocalDate.now());
            loan.setComments(dto.getComments());
            loanRepository.save(loan);
            return reservation;
        } else {
            throw new RuntimeException("Car is not available");
        }
    }
    @Override
    public Boolean isCarAvailable(Reservation reservation, LocalDate fromDate, LocalDate toDate) {
        if (reservation.getDateTo().equals(fromDate) || reservation.getDateFrom().equals(fromDate)
        ||reservation.getDateTo().equals(toDate) || reservation.getDateFrom().equals(toDate))
            return false;
        if (fromDate.isAfter(reservation.getDateFrom()) && fromDate.isBefore(reservation.getDateTo()))
            return false;
        if (toDate.isAfter(reservation.getDateFrom()) && toDate.isBefore(reservation.getDateTo()))
            return false;
        return true;
    }
    @Override
    public Reservation closeReservation(Long id , String comments) {
        Reservation reservation = reservationRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Not Found"));
        Car car = reservation.getCar();
               car.setBranch(reservation.getReturnbranch());
               car.setStatus(StatusCar.AVAILABLE);
               carRepository.save(car);
        RefundDto refundDto = new RefundDto();
        refundDto.setReservationId(id);
        refundDto.setComments(comments);
        refundDto.setDateOfReturn(LocalDate.now());
        refundService.create(refundDto);
        return reservationRepository.save(reservation);
    }
}