package com.payment_management.payment_management.repository;

import com.payment_management.payment_management.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Integer> {
    List<Payment> findByBillBillno(int billId);

    @Query("SELECT p FROM Payment p WHERE p.bill.customer.id = :customerId ORDER BY p.paymentDate")
    List<Payment> findByCustomerId(int customerId);
}
