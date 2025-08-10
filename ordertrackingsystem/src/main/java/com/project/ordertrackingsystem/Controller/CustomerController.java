package com.project.ordertrackingsystem.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.dto.Customerdto;
import com.project.ordertrackingsystem.service.CustomerService;

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
            return ResponseEntity.ok(customers);
        }
    }
}
