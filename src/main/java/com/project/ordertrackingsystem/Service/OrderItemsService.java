package com.project.ordertrackingsystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.DTO.CustomOrderitemsdto;
import com.project.ordertrackingsystem.DTO.OrderItemsdto;
import com.project.ordertrackingsystem.Repository.OrderItemsRepository;

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
