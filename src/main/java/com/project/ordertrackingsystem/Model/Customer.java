package com.project.ordertrackingsystem.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String name;
    private String email;
    private long mobile;

    @OneToMany(mappedBy="cust",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Orders> custorders;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public List<Orders> getCustorders() {
        return custorders;
    }

    public void setCustorders(List<Orders> custorders) {
        this.custorders = custorders;
    }

    public Customer(int cid, String name, String email, long mobile) {
        this.cid = cid;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public Customer() {
    }
    
}
