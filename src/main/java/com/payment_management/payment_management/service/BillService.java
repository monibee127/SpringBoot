package com.payment_management.payment_management.service;

import com.payment_management.payment_management.model.Bill;
import com.payment_management.payment_management.model.Customer;
import com.payment_management.payment_management.model.dto.BillDetailDto;
import com.payment_management.payment_management.model.dto.BillSummaryDto;
import com.payment_management.payment_management.model.dto.PaymentDetailDto;
import com.payment_management.payment_management.repository.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private BillRepo repo;

    public BillDetailDto findBillDetailsById(int id){

        Bill bill=repo.findById(id).orElseThrow(()->new RuntimeException("Bill not found with ID"));

        BillDetailDto dto=new BillDetailDto();
        dto.setBillno(bill.getBillno());
        dto.setTotalAmount(bill.getTotalAmount());
        dto.setPaidAmount(bill.getPaidAmount());
        dto.setRemainingAmount(bill.getRemainingAmount());
        dto.setStatus(bill.getStatus());
        dto.setCreatedAt(bill.getCreatedAt());

        // Map fields from the related Customer entity
        Customer customer = bill.getCustomer();
        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setCustomerPhoneNumber(customer.getPhoneNumber());

        // Map the list of Payment entities to a list of PaymentDetailDTOs
        List<PaymentDetailDto> paymentDTOs = bill.getPayments().stream().map(payment -> {
            PaymentDetailDto paymentDto = new PaymentDetailDto();
            paymentDto.setPaymentId(payment.getPaymentId());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setPaymentMethod(payment.getPaymentMethod());
            paymentDto.setPaymentDate(payment.getPaymentDate());
            return paymentDto;
        }).collect(Collectors.toList());

        dto.setPayments(paymentDTOs);

        return dto;
    }

    public void createBill(Bill bill){
        bill.setRemainingAmount(bill.getTotalAmount()-bill.getPaidAmount());
        if (bill.getRemainingAmount()==0) {
            bill.setStatus("PAID");
        }
        else if(bill.getRemainingAmount()>0 && bill.getRemainingAmount()<bill.getTotalAmount()){
            bill.setStatus("PARTIALLY_PAID");
        }
        else if(bill.getRemainingAmount()==bill.getTotalAmount()){
            bill.setStatus("NOT PAID");
        }
        repo.save(bill);
    }

    public List<Bill> readBills(){
        return  repo.findAll();
    }



    public void DeleteBill(int id){
        repo.deleteById(id);
    }

    public List<Bill> findByCustomerId(int id){
        return repo.findByCustomerId(id);
    }



    }

