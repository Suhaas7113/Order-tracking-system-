package com.project.ordertrackingsystem.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ordertrackingsystem.DTO.InsertionbyCustomeriddto;
import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.Model.Customer;
import com.project.ordertrackingsystem.Model.Orderitems;
import com.project.ordertrackingsystem.Model.Orders;
import com.project.ordertrackingsystem.Model.Product;
import com.project.ordertrackingsystem.Model.orderItemsId;
import com.project.ordertrackingsystem.Model.orderstatus;
import com.project.ordertrackingsystem.Repository.CustomerRepository;
import com.project.ordertrackingsystem.Repository.OrdersRepository;
import com.project.ordertrackingsystem.Repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InsertionbyCustomeridService {

    @Autowired
    private CustomerRepository cr;

    @Autowired
    private OrdersRepository or;

    @Autowired
    private ProductRepository pr;

    public Orders createOrder(InsertionbyCustomeriddto createOrderDto) {

        Customer customer = cr.findById(createOrderDto.getCustomerId())
                .orElseThrow(() -> new RequestNotFoundException("Customer not found"));

        Orders order = new Orders();
        order.getOid();
        order.setOrderdate(LocalDate.now());
        order.setDeliverydate(LocalDate.now().plusDays(5));
        order.setOstatus(orderstatus.N);
        order.setCust(customer);

        Orders savedOrder = or.save(order); 

        List<Orderitems> orderItemsList = new ArrayList<>();

        for (InsertionbyCustomeriddto.ProductQuantity pq : createOrderDto.getProducts()) {

            Product product = pr.findById(pq.getProductId())
                    .orElseThrow(() -> new RequestNotFoundException("Product Id not found"));

            Orderitems oi = new Orderitems();
            oi.setOrder(savedOrder);  
            oi.setProduct(product);
            oi.setQuantity(pq.getQuantity());
            oi.setPrice((double) product.getPrice() * pq.getQuantity());
            oi.setOitemsid(new orderItemsId(product.getPid(),savedOrder.getOid()));

            orderItemsList.add(oi);
        }
        
        savedOrder.setOrderitemsList(orderItemsList);

        return savedOrder;
    }
}
