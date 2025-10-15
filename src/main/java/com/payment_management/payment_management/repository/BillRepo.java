package com.payment_management.payment_management.repository;

import com.payment_management.payment_management.model.Bill;
import com.payment_management.payment_management.model.Customer;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill,Integer> {

    @Query("SELECT b FROM Bill b WHERE b.customer.id = :customerId ORDER BY b.createdAt")
    List<Bill> findByCustomerId(@Param("customerId") int customerId);
}
