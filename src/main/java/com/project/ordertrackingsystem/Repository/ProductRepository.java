package com.project.ordertrackingsystem.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ordertrackingsystem.DTO.Productdto;
import com.project.ordertrackingsystem.Model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p.pid as pid, p.pname as pname, p.description as description, p.price as price from Product p where p.pname = :name")
    List<Productdto> getProductbyName(@Param("name") String name);

    @Query("select p.pid as pid, p.pname as pname, p.description as description, p.price as price from Product p")
    Page<Productdto> getProduct(Pageable pg);
}


