package com.payment_management.payment_management.model.dto;


import lombok.Data;

@Data
public class BillSummaryDto {
    private int billno;
    private double totalAmount;
    private String status;
}
