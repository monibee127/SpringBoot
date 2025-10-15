package com.payment_management.payment_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Bill {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billno;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private double totalAmount;
    private double paidAmount;
    private double remainingAmount;
    private String status;
    @CreatedDate
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Payment> payments=new ArrayList<>();

}
