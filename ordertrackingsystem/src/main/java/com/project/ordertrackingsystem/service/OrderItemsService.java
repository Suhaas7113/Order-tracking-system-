package com.project.ordertrackingsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.Repository.OrderItemsRepository;
import com.project.ordertrackingsystem.dto.CustomOrderitemsdto;
import com.project.ordertrackingsystem.dto.OrderItemsdto;

@Service
public class OrderItemsService {
    private final OrderItemsRepository oi;

    public OrderItemsService(OrderItemsRepository oi){
        this.oi = oi;
    }

    //6
    public List<OrderItemsdto> getOrderItems(int id){
        return oi.getOrderItems(id);
    }

    public List<CustomOrderitemsdto> getOrderItemsbyProduct(String prod){
        return oi.getOrderItemsbyProduct(prod);
    }
}
