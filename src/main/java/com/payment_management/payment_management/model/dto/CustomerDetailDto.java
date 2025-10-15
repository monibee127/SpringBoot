package com.payment_management.payment_management.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDetailDto {

    private int customerId;
    private String customerName;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private List<BillSummaryDto> bills;
}
