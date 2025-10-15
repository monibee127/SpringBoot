package com.payment_management.payment_management.controller;

import com.payment_management.payment_management.model.Payment;
import com.payment_management.payment_management.model.dto.PaymentRequestDto;
import com.payment_management.payment_management.model.dto.PaymentResponseDto;
import com.payment_management.payment_management.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/add")
    public PaymentResponseDto addPayment(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

    @GetMapping("/bill/{billId}")
    public List<Payment> getPaymentsByBill(@PathVariable int billId) {
        return paymentService.getPaymentsByBill(billId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Payment> getPaymentsByCustomer(@PathVariable int customerId) {
        return paymentService.getPaymentsByCustomer(customerId);
    }
}
