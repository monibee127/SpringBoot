package com.payment_management.payment_management.service;

import com.payment_management.payment_management.model.Bill;
import com.payment_management.payment_management.model.Customer;
import com.payment_management.payment_management.model.Payment;
import com.payment_management.payment_management.model.dto.PaymentResponseDto;
import com.payment_management.payment_management.repository.BillRepo;
import com.payment_management.payment_management.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private BillRepo billRepo;

    public PaymentResponseDto addPayment(Payment payment){

        Bill bill=billRepo.findById(payment.getBill().getBillno())
                .orElseThrow(()->new RuntimeException("Bill not found"));

        bill.setPaidAmount(bill.getPaidAmount()+ payment.getAmount());
        bill.setRemainingAmount(bill.getRemainingAmount()- payment.getAmount());

        if (bill.getRemainingAmount()<=0){
            bill.setStatus("PAID");
        }else{
            bill.setStatus("PARTIALLY_PAID");
        }
        payment.setBill(bill);
        Payment savedPayment=paymentRepo.save(payment);
        billRepo.save(bill);

        PaymentResponseDto dto=new PaymentResponseDto();

        dto.setPaymentId(savedPayment.getPaymentId());
        dto.setAmount(savedPayment.getAmount());
        dto.setPaymentMethod(savedPayment.getPaymentMethod());
        dto.setPaymentDate(savedPayment.getPaymentDate());
        dto.setBillno(savedPayment.getBill().getBillno());

        return dto;


    }

    // Fetch all payments for a bill
    public List<Payment> getPaymentsByBill(int billId) {
        return paymentRepo.findByBillBillno(billId);
    }

    // Optional: fetch payments by customer
    public List<Payment> getPaymentsByCustomer(int customerId) {
        return paymentRepo.findByCustomerId(customerId);
    }
}
