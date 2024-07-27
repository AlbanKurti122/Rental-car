package com.sda.controlles;

import com.sda.entity.Branch;
import com.sda.repository.BranchRepository;
import com.sda.service.BranchService;
import com.sda.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class BranchController {
    @Autowired
    private BranchService branchService;
    @PostMapping("/{rentalId}/branches")
    public ResponseEntity<Branch>createBranch(@PathVariable Long rentalId , @RequestBody Branch branch){
        return ResponseEntity.ok(branchService.createBranch(rentalId, branch));
    }
    @DeleteMapping("/branch/{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long branchId){
        branchService.deleteBranch(branchId);
        return ResponseEntity.noContent().build();
    }
}