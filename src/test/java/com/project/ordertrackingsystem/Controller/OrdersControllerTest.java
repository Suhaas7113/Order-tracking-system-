package com.project.ordertrackingsystem.Controller;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.project.ordertrackingsystem.DTO.AllOrderDetailsdto;
import com.project.ordertrackingsystem.DTO.CustomOrderdto;
import com.project.ordertrackingsystem.DTO.InsertionbyCustomeriddto;
import com.project.ordertrackingsystem.DTO.Orderdto;
import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.Model.Orders;
import com.project.ordertrackingsystem.Model.orderstatus;
import com.project.ordertrackingsystem.Service.InsertionbyCustomeridService;
import com.project.ordertrackingsystem.Service.OrdersService;



@ExtendWith(MockitoExtension.class)
public class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @Mock
    private InsertionbyCustomeridService insertionService;

    @InjectMocks
    private OrdersController ordersController;

    @Test
    void testGetOrdersByCustomerId_Success() {
        CustomOrderdto order = new CustomOrderdto() {
            public int getOid() { return 101; }
            public int getCid() { return 1; }
            public String getName() { return "Suhaas"; }
            public LocalDate getOrderdate() { return LocalDate.now(); }
            public orderstatus getOstatus() { return orderstatus.C; }
            public LocalDate getDeliverydate() { return LocalDate.now().plusDays(3); }
        };

        when(ordersService.getOrdersbyCust(1)).thenReturn(List.of(order));

        ResponseEntity<List<CustomOrderdto>> response = ordersController.getorders(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Suhaas", response.getBody().get(0).getName());
    }

    @Test
    void testGetOrdersByCustomerId_NotFound() {
        when(ordersService.getOrdersbyCust(99)).thenReturn(List.of());

        assertThrows(RequestNotFoundException.class, () -> {
            ordersController.getorders(99);
        });
    }

    @Test
    void testGetOrdersAfterDate_Success() {
        Orderdto dto = new Orderdto() {
            public int getOid() { return 301; }
            public LocalDate getOrderdate() { return LocalDate.of(2024, 6, 1); }
            public orderstatus getOstatus() { return orderstatus.N; }
            public LocalDate getDeliverydate() { return LocalDate.of(2024, 6, 5); }
            public com.project.ordertrackingsystem.Model.Customer getCust() { return null; }
        };

        LocalDate date = LocalDate.of(2024, 5, 1);
        when(ordersService.getOrdersAfterDate(date)).thenReturn(List.of(dto));

        ResponseEntity<List<Orderdto>> response = ordersController.getOrdersAfterDate(date);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderstatus.N, response.getBody().get(0).getOstatus());
    }

    @Test
    void testGetOrderByStatus_Success() {
        orderstatus status = orderstatus.N;
        CustomOrderdto dto = new CustomOrderdto() {
            public int getOid() { return 100; }
            public int getCid() { return 2; }
            public String getName() { return "Ganesh"; }
            public LocalDate getOrderdate() { return LocalDate.now(); }
            public orderstatus getOstatus() { return status; }
            public LocalDate getDeliverydate() { return LocalDate.now().plusDays(3); }
        };

        when(ordersService.getOrderbyStatus(status)).thenReturn(List.of(dto));

        ResponseEntity<List<CustomOrderdto>> response = ordersController.getorderbystatus(status);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ganesh", response.getBody().get(0).getName());
    }

    @Test
    void testGetAllDetailsbyId_Success() {
        AllOrderDetailsdto dto = new AllOrderDetailsdto() {
            public int getCid() { return 1; }
            public String getName() { return "Suhaas"; }
            public int getPid() { return 101; }
            public String getPname() { return "Laptop"; }
            public String getDescription() { return "Dell XPS"; }
            public int getOid() { return 501; }
            public LocalDate getOrderdate() { return LocalDate.now(); }
            public orderstatus getOstatus() { return orderstatus.C; }
            public LocalDate getDeliverydate() { return LocalDate.now().plusDays(2); }
        };

        when(ordersService.getAllDetailsbyId(501)).thenReturn(List.of(dto));

        ResponseEntity<List<AllOrderDetailsdto>> response = ordersController.getAllDetailsbyId(501);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Laptop", response.getBody().get(0).getPname());
    }

    @Test
    void testCreateOrder_Success() {
        InsertionbyCustomeriddto dto = new InsertionbyCustomeriddto();
        Orders order = new Orders();
        order.setOid(999);

        when(insertionService.createOrder(dto)).thenReturn(order);

        ResponseEntity<Orders> response = ordersController.createOrder(dto);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(999, response.getBody().getOid());
    }
}

