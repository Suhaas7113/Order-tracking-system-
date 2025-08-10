package com.project.ordertrackingsystem.DTO;

import java.time.LocalDate;

import com.project.ordertrackingsystem.Model.orderstatus;

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
