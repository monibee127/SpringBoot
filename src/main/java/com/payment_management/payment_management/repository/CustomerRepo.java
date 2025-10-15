package com.payment_management.payment_management.repository;

import com.payment_management.payment_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Customer findByCustomerName(String name);

    Customer findByPhoneNumber(String phno);


}
