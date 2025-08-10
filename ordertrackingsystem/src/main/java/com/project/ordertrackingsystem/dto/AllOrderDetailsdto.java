package com.project.ordertrackingsystem.dto;

import java.time.LocalDate;

import com.project.ordertrackingsystem.model.orderstatus;

public interface AllOrderDetailsdto {
    int getCid();
    String getName();
    int getPid();
    String getPname();
    String getDescription();
    int getOid();
    LocalDate getOrderdate();
    orderstatus getOstatus();
    LocalDate getDeliverydate();
}
