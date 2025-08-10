package com.project.ordertrackingsystem.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.DTO.AllOrderDetailsdto;
import com.project.ordertrackingsystem.DTO.CustomOrderdto;
import com.project.ordertrackingsystem.DTO.Orderdto;
import com.project.ordertrackingsystem.Model.orderstatus;
import com.project.ordertrackingsystem.Repository.OrdersRepository;

@Service
public class OrdersService {
    private final OrdersRepository or;

    public OrdersService(OrdersRepository or){
        this.or = or;
    }

    public List<Orderdto> getOrdersAfterDate(LocalDate date){
        return or.getOrdersAfterDate(date);
    }

    public List<CustomOrderdto> getOrdersbyCust(int id){
        return or.getOrdersbyCust(id);
    }

    public List<CustomOrderdto> getOrderbyStatus(orderstatus st){
        return or.getOrderbyStatus(st);
    }

    public  List<AllOrderDetailsdto> getAllDetailsbyId(int id){
        return or.getAllDetailsbyId(id);
    }

}
