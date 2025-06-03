package com.project.ordertrackingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.Repository.CustomerRepository;
import com.project.ordertrackingsystem.dto.Customerdto;

@Service
public class CustomerService {
    private final CustomerRepository cr;

    public CustomerService(CustomerRepository cr){
        this.cr = cr;
    }

    public List<Customerdto> getcustomer(){
        return cr.getcustomer();
    }

}
