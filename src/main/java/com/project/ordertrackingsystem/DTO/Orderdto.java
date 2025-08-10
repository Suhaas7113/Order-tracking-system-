package com.project.ordertrackingsystem.DTO;

import java.time.LocalDate;

import com.project.ordertrackingsystem.Model.Customer;
import com.project.ordertrackingsystem.Model.orderstatus;

public interface Orderdto {
    int getOid();
    LocalDate getOrderdate();
    orderstatus getOstatus();
    LocalDate getDeliverydate();
    Customer getCust();    
}
