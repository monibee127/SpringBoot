package com.payment_management.payment_management.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BillDetailDto {

    private int billno;
    private double totalAmount;
    private double paidAmount;
    private double remainingAmount;
    private String status;
    private LocalDateTime createdAt;

    // Fields from Customer entity
    private int customerId;
    private String customerName;
    private String customerPhoneNumber;

    // A list of payment details
    private List<PaymentDetailDto> payments;
}
