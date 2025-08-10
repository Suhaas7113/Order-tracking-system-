package com.project.ordertrackingsystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.DTO.Customerdto;
import com.project.ordertrackingsystem.Model.Customer;
import com.project.ordertrackingsystem.Repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository cr;

    public CustomerService(CustomerRepository cr){
        this.cr = cr;
    }

    public List<Customerdto> getcustomer(){
        return cr.getcustomer();
    }

    public Customer getCustomerbyId(int id){
        return cr.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found of id : "+id));
    }

}
