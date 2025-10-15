package com.payment_management.payment_management.controller;

import com.payment_management.payment_management.model.Bill;
import com.payment_management.payment_management.model.dto.BillDetailDto;
import com.payment_management.payment_management.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;


    @PostMapping("/add")
    public Bill addBill(@RequestBody Bill bill) {
        billService.createBill(bill);
        return bill;
    }

    @GetMapping("/all")
    public List<Bill> getAllBills() {
        return billService.readBills();
    }


    @GetMapping("/{id}")
    public BillDetailDto getBillById(@PathVariable int id) {
        return billService.findBillDetailsById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBill(@PathVariable int id) {
        billService.DeleteBill(id);
        return "Bill deleted successfully";
    }

    @GetMapping("/customer/{customerId}")
    public List<Bill> getBillsByCustomer(@PathVariable int customerId) {
        return billService.findByCustomerId(customerId);
    }
}