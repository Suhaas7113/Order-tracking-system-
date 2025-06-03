package com.project.ordertrackingsystem.dto;

import java.time.LocalDate;

import com.project.ordertrackingsystem.model.orderstatus;

public interface CustomOrderdto {
    int getOid();
    int getCid();
    String getName();
    LocalDate getOrderdate();
    orderstatus getOstatus();
    LocalDate getDeliverydate();
}
