package com.sda.service.impl;

import com.sda.entity.Rental;
import com.sda.repository.RentalRepository;
import com.sda.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Override
    public Rental createRental(Rental rental){
        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateRental(Long id , Rental rentaldetails){
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental rental = optionalRental.get();
            rental.setName(rentaldetails.getName());
            rental.setInternetDomain(rentaldetails.getInternetDomain());
            rental.setContactAddress(rentaldetails.getContactAddress());
            rental.setOwner(rentaldetails.getOwner());
            rental.setLogotype(rentaldetails.getLogotype());
            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Rental not found with id: " + id);
        }
    }
    @Transactional
    @Override
    public void deleteRental(Long id){
       rentalRepository.deleteById(id);
    }
    @Override
    public Rental getRental(Long id){
        return  rentalRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Rental not found"));
    }
}
