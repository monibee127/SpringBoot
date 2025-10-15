package com.payment_management.payment_management.model.dto;


import com.payment_management.payment_management.model.Bill;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class PaymentResponseDto {

    private int paymentId;
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private int billno;
}
