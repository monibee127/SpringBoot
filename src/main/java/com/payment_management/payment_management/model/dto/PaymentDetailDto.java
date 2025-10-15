package com.payment_management.payment_management.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDetailDto {
    private int paymentId; //
    private double amount; //
    private String paymentMethod; //
    private LocalDateTime paymentDate; //

}
