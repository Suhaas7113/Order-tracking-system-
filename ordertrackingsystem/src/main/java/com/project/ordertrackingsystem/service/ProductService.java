package com.project.ordertrackingsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.Repository.ProductRepository;
import com.project.ordertrackingsystem.dto.Productdto;

@Service
public class ProductService {
    private final ProductRepository pr;

    public ProductService(ProductRepository pr){
        this.pr = pr;
    }

    public Page<Productdto> getproduct(Pageable pg){
        return pr.getProduct(pg);
    }

    public List<Productdto> getProductbyName(String name){
        return pr.getProductbyName(name);
    }
    
}
