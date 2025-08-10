package com.project.ordertrackingsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ordertrackingsystem.DTO.Customerdto;
import com.project.ordertrackingsystem.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c.cid as cid, c.name as name, c.email as email, c.mobile as mobileNumber from Customer c")
    List<Customerdto> getcustomer();

}
