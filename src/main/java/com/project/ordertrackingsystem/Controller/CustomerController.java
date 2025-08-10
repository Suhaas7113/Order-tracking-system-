package com.project.ordertrackingsystem.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ordertrackingsystem.DTO.Customerdto;
import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.Model.Customer;
import com.project.ordertrackingsystem.Service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService cs;

    public CustomerController(CustomerService cs) {
        this.cs = cs;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Customerdto>> getCustomer(){
        List<Customerdto> customers = cs.getcustomer();
        if(customers.isEmpty()){
            throw new RequestNotFoundException("No customer deatils found... ");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(customers);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = cs.getCustomerbyId(id);
        return ResponseEntity.ok(customer);
    }
}
