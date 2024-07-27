package com.sda.service;

import com.sda.entity.Rental;

public interface RentalService {
    Rental createRental(Rental rental);
    Rental updateRental(Long id, Rental rentalDetalis);
    void deleteRental(Long id);
    Rental getRental(Long id);
}
