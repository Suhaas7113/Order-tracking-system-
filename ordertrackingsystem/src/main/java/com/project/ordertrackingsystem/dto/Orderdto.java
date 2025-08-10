package com.project.ordertrackingsystem.dto;

import java.time.LocalDate;

import com.project.ordertrackingsystem.model.Customer;
import com.project.ordertrackingsystem.model.orderstatus;

public interface Orderdto {
    int getOid();
    LocalDate getOrderdate();
    orderstatus getOstatus();
    LocalDate getDeliverydate();
    Customer getCust();    
}
