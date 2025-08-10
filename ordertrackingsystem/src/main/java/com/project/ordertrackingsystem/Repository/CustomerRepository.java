package com.project.ordertrackingsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ordertrackingsystem.dto.Customerdto;
import com.project.ordertrackingsystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c.cid as id, c.name as name, c.email as email, c.mobile as mobileNumber FROM Customer c")
    List<Customerdto> getcustomer();
}
