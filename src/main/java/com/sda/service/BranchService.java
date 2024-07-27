package com.sda.service;

import com.sda.entity.Branch;

public interface BranchService {
    Branch createBranch(Long rentalId, Branch branch);
    void deleteBranch(Long id);
}
