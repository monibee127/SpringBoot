package com.payment_management.payment_management.model.dto;

import com.payment_management.payment_management.model.Customer;
import lombok.Data;

@Data
public class TotalDetailDto {
    private Customer customer;
    private double totalAmount;
    private double paidAmount;
}
