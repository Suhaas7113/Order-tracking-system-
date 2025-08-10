package com.project.ordertrackingsystem.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.dto.CustomOrderitemsdto;
import com.project.ordertrackingsystem.dto.OrderItemsdto;
import com.project.ordertrackingsystem.service.OrderItemsService;

@RestController
@RequestMapping("/orderitems")
public class OrderItemsController {

    private final OrderItemsService ois;

    public OrderItemsController(OrderItemsService ois){
        this.ois = ois;
    }

    @GetMapping("/prod/{prod}")
    public ResponseEntity<List<CustomOrderitemsdto>> getOrderitemsbyProduct(@PathVariable String prod){
        List<CustomOrderitemsdto> orderitems = ois.getOrderItemsbyProduct(prod);
        if(orderitems.isEmpty()){
            throw new RequestNotFoundException("No order items found for the given product : "+prod);
        }
        return ResponseEntity.ok(orderitems);
    }

    @GetMapping("/orderid/{id}")
    public ResponseEntity<List<OrderItemsdto>> getOrderItems(@PathVariable int id){
        List<OrderItemsdto> orderitems = ois.getOrderItems(id);
        if(orderitems.isEmpty()){
            throw new RequestNotFoundException("No order items found for the given id : "+id);
        }
        return ResponseEntity.ok(orderitems);
    }

}
