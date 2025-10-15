package com.payment_management.payment_management.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.payment_management.payment_management.model.Customer;
import com.payment_management.payment_management.model.dto.CustomerBillRequest;
import com.payment_management.payment_management.model.dto.CustomerDetailDto;
import com.payment_management.payment_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/add")
    public CustomerDetailDto addCustomer(@RequestBody CustomerBillRequest request){
        Customer customer=customerService.createCustomerAndBill(request);
        return customerService.findById(customer.getCustomerId());
    }


    @GetMapping("/all")
    public List<CustomerDetailDto> getAllCustomers() {
        return customerService.readCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDetailDto getCustomerById(@PathVariable int id) {
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully";
    }

    @GetMapping("/search/name")
    public Customer searchByName(@RequestParam String name) {
        return customerService.searchByName(name);
    }

    @GetMapping("/search/phno")
    public CustomerDetailDto searchByPhno(@RequestParam String phno) {
        return customerService.searchByPhno(phno);
    }

}