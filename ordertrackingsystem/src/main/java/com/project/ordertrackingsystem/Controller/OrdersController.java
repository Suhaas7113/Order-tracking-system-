package com.project.ordertrackingsystem.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.dto.AllOrderDetailsdto;
import com.project.ordertrackingsystem.dto.CustomOrderdto;
import com.project.ordertrackingsystem.dto.InsertionbyCustomeriddto;
import com.project.ordertrackingsystem.dto.Orderdto;
import com.project.ordertrackingsystem.model.Orders;
import com.project.ordertrackingsystem.model.orderstatus;
import com.project.ordertrackingsystem.service.InsertionbyCustomeridService;
import com.project.ordertrackingsystem.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService os;

    @Autowired
    private InsertionbyCustomeridService ics;

    @GetMapping("/bycustid/{id}")
    public ResponseEntity<List<CustomOrderdto>> getorders(@PathVariable int id){
        List<CustomOrderdto> order = os.getOrdersbyCust(id);
        if (order.isEmpty()) {
            throw new RequestNotFoundException("No orders found for customer ID: " + id);
        }
        return ResponseEntity.ok(order);
    }
    

    @GetMapping("afterdate/d")
    public ResponseEntity<List<Orderdto>> getOrdersAfterDate(@RequestParam("d") LocalDate d){
        List<Orderdto> order = os.getOrdersAfterDate(d);
        if(order.isEmpty()){
            throw new RequestNotFoundException("Orders not found after date : "+d);
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/bystatus/")
    public ResponseEntity<List<CustomOrderdto>> getorderbystatus(@RequestParam("st") orderstatus st){
        List<CustomOrderdto> order = os.getOrderbyStatus(st);
        if(order.isEmpty()){
            throw new RequestNotFoundException("Orders not found of given status : "+st);
        }
        return ResponseEntity.ok(order);
    }    
    
    @GetMapping("/byid/{id}")
    public ResponseEntity<List<AllOrderDetailsdto>> getAllDetailsbyId(@PathVariable int id){
        List<AllOrderDetailsdto> order = os.getAllDetailsbyId(id);
        if(order.isEmpty()){
            throw new RequestNotFoundException("Orders not found of given id : "+id);
        }
        return ResponseEntity.ok(order);
   }

    @GetMapping("/orderdetails")
    public ResponseEntity<List<AllOrderDetailsdto>> getOrderDetailsbyCustId(@RequestParam("orderid") int orderid){
        List<AllOrderDetailsdto> alldetails = os.getAllDetailsbyId(orderid);
            if(alldetails.isEmpty()){
                throw new RequestNotFoundException("Order not found of order id : "+orderid);
            }
            return ResponseEntity.ok(alldetails);
    }

    @PostMapping("/addOrders")
    public ResponseEntity<Orders> createOrder(@RequestBody InsertionbyCustomeriddto createOrder) {
        Orders order = ics.createOrder(createOrder);
        return ResponseEntity.ok(order);
    }
}
