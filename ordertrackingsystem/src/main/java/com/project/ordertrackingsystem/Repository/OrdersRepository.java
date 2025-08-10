package com.project.ordertrackingsystem.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.ordertrackingsystem.dto.AllOrderDetailsdto;
import com.project.ordertrackingsystem.dto.CustomOrderdto;
import com.project.ordertrackingsystem.dto.Orderdto;
import com.project.ordertrackingsystem.model.Orders;
import com.project.ordertrackingsystem.model.orderstatus;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

    @Query("select o from Orders o where o.orderdate > :date")
    List<Orderdto> getOrdersAfterDate(@Param("date") LocalDate date);

    @Query("select o.oid as oid, c.cid as cid, c.name as name, o.orderdate as orderdate, o.ostatus as ostatus, o.deliverydate as deliverydate " +
        "from Orders o join o.cust c where c.cid = :id")
    List<CustomOrderdto> getOrdersbyCust(@Param("id") int id);

    @Query("select o.oid as oid, c.cid as cid, c.name as name, o.orderdate as orderdate, o.ostatus as ostatus, o.deliverydate as deliverydate " +
       "from Orders o join o.cust c where o.ostatus = :st")
    List<CustomOrderdto> getOrderbyStatus(@Param("st") orderstatus st);

    @Query("""
            select c.cid as cid, c.name as name, p.pid as pid, p.pname as pname, p.description as description, 
            o.oid as oid, o.orderdate as orderdate, o.ostatus as ostatus, o.deliverydate as deliverydate
            from Orderitems oi
            join oi.product p
            join oi.order o
            join o.cust c where o.oid = :id  """)
    List<AllOrderDetailsdto> getAllDetailsbyId(@Param("id") int id);

}
