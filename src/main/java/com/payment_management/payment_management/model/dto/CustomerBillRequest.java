package com.payment_management.payment_management.model.dto;

import lombok.Data;

@Data
public class CustomerBillRequest {

    private String customerName;
    private String phoneNumber;

    // Data for the Bill entity
    private int billno;
    private double totalAmount;
    private double paidAmount;
    private String initialPaymentMethod;
}
