package com.project.ordertrackingsystem.dto;

import java.time.LocalDate;

public interface CustomOrderitemsdto {
    String getPname();
    String getName();
    int getQuantity();
    double getPrice();
    LocalDate getOrderdate();

}
