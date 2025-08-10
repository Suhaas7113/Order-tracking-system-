package com.project.ordertrackingsystem.DTO;

import java.time.LocalDate;

import com.project.ordertrackingsystem.Model.orderstatus;

public interface CustomOrderdto {
    int getOid();
    int getCid();
    String getName();
    LocalDate getOrderdate();
    orderstatus getOstatus();
    LocalDate getDeliverydate();
}
