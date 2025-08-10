package com.project.ordertrackingsystem.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ordertrackingsystem.DTO.Productdto;
import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService ps;
    public ProductController(ProductService ps){
        this.ps = ps;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Productdto>> getProductbyName(@PathVariable String name){
        List<Productdto> product = ps.getProductbyName(name);
        if(product.isEmpty()){
            throw new RequestNotFoundException("No products found by name : "+name);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/pagenation/")
    public ResponseEntity<Page<Productdto>> getproduct(@RequestParam int page, @RequestParam int size){
        Pageable pg = PageRequest.of(page,size);
        Page<Productdto> product = ps.getproduct(pg);
        if(product.isEmpty()){
            throw new RequestNotFoundException("No products found on page : "+page+ "with size "+size);
        }
        return ResponseEntity.ok(product);
    }
}
