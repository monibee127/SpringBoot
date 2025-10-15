package com.payment_management.payment_management.model.dto;


import lombok.Data;

@Data
public class PaymentRequestDto {

    private int billId;
    private double amount;
    private String paymentMethod;
}
