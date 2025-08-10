package com.project.ordertrackingsystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.ordertrackingsystem.DTO.CustomOrderitemsdto;
import com.project.ordertrackingsystem.DTO.OrderItemsdto;
import com.project.ordertrackingsystem.Model.Orderitems;
import com.project.ordertrackingsystem.Model.orderItemsId;

@Repository
public interface OrderItemsRepository extends JpaRepository<Orderitems,orderItemsId>{
    //6
    @Query("select o.oid as oid, p.pname as pname, p.description as description, oi.quantity as quantity, oi.price as price from Orderitems oi join oi.product p join oi.order o where oi.order.oid = :id")
    List<OrderItemsdto> getOrderItems(@Param("id") int id);

    @Query("select p.pname as pname, c.name as name, oi.quantity as quantity, oi.price as price, o.orderdate as orderdate from Orderitems oi join oi.product p join oi.order o join o.cust c where UPPER(p.pname) = UPPER(:prod)")
    List<CustomOrderitemsdto> getOrderItemsbyProduct(String prod);

}
