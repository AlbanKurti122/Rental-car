package com.sda.service;

import com.sda.dto.RefundDto;
import com.sda.entity.Refund;

public interface RefundService {
    Refund create(RefundDto refundDto);
}
