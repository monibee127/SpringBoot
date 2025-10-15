package com.payment_management.payment_management.service;

import com.payment_management.payment_management.model.Bill;
import com.payment_management.payment_management.model.Customer;
import com.payment_management.payment_management.model.Payment;
import com.payment_management.payment_management.model.dto.BillDetailDto;
import com.payment_management.payment_management.model.dto.BillSummaryDto;
import com.payment_management.payment_management.model.dto.CustomerBillRequest;
import com.payment_management.payment_management.model.dto.CustomerDetailDto;
import com.payment_management.payment_management.repository.BillRepo;
import com.payment_management.payment_management.repository.CustomerRepo;
import com.payment_management.payment_management.repository.PaymentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Transactional
    public Customer createCustomerAndBill(CustomerBillRequest request) {

        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customer.setPhoneNumber(request.getPhoneNumber());

        Bill bill = new Bill();
        bill.setBillno(request.getBillno());
        bill.setTotalAmount(request.getTotalAmount());
        bill.setPaidAmount(request.getPaidAmount());

        bill.setRemainingAmount(bill.getTotalAmount() - bill.getPaidAmount());
        if (bill.getRemainingAmount() <= 0) {
            bill.setStatus("PAID");
        } else if (bill.getPaidAmount() > 0) {
            bill.setStatus("PARTIALLY_PAID");
        } else {
            bill.setStatus("NOT PAID");
        }

        bill.setCustomer(customer);
        customer.getBills().add(bill);

        if (bill.getPaidAmount() > 0 && request.getInitialPaymentMethod() != null) {
            Payment initialPayment = new Payment();

            initialPayment.setAmount(bill.getPaidAmount());
            initialPayment.setPaymentMethod(request.getInitialPaymentMethod());
            initialPayment.setPaymentDate(LocalDateTime.now());
            initialPayment.setBill(bill);
            bill.getPayments().add(initialPayment);

        }
        return customerRepo.save(customer);

    }

    public void createCustomer(Customer customer){
        String phno=customer.getPhoneNumber();
        if(phno.length()==10) {
            customerRepo.save(customer);
        }
        else{
            System.out.println("Invalid PhoneNumber");
        }
    }


    public List<CustomerDetailDto> readCustomers(){

        List<Customer> customer= customerRepo.findAll();
        return customer.stream()
                .map(this::convertToCustomerDetailDto)
                .collect(Collectors.toList());
    }



    public CustomerDetailDto findById(int id){
        Customer customer=customerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found with given ID"));

        return convertToCustomerDetailDto(customer);


    }
    private CustomerDetailDto convertToCustomerDetailDto(Customer customer) {

        CustomerDetailDto dto=new CustomerDetailDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setCreatedAt(customer.getCreatedAt());

        List<BillSummaryDto>billsummaries=customer.getBills().stream().map(bill -> {
            BillSummaryDto billDto=new BillSummaryDto();
            billDto.setBillno(bill.getBillno());
            billDto.setTotalAmount(bill.getTotalAmount());
            billDto.setStatus(bill.getStatus());

            return billDto;
        }).collect(Collectors.toList());

        dto.setBills(billsummaries);

        return dto;
    }

    public void deleteCustomer(int id){
        customerRepo.deleteById(id);
    }

    public Customer searchByName(String name){
          return customerRepo.findByCustomerName(name);
    }

    public CustomerDetailDto searchByPhno(String phno){

        Customer customer= customerRepo.findByPhoneNumber(phno);
        return convertToCustomerDetailDto(customer);
    }



}
