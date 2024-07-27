package com.sda.service.impl;

import com.sda.entity.Branch;
import com.sda.entity.Rental;
import com.sda.repository.BranchRepository;
import com.sda.repository.RentalRepository;
import com.sda.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Override
    public Branch createBranch(Long rentalId, Branch branch){
       Rental rental = rentalRepository.findById(rentalId)
               .orElseThrow(() -> new IllegalArgumentException
                       ("Rental with id " + rentalId + " not found."));
       branch.setRental(rental);
       return branchRepository.save(branch);
    }
    @Override
    public void deleteBranch(Long id){
        branchRepository.deleteById(id);
    }
}