package com.project.ordertrackingsystem.Controller;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.project.ordertrackingsystem.DTO.CustomOrderitemsdto;
import com.project.ordertrackingsystem.DTO.OrderItemsdto;
import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.Service.OrderItemsService;

@ExtendWith(MockitoExtension.class)
public class OrderItemsControllerTest {

    @Mock
    private OrderItemsService ois;

    @InjectMocks
    private OrderItemsController controller;

    @Test
    void testGetOrderItemsByProduct() {
        CustomOrderitemsdto mockDto = new CustomOrderitemsdto() {
            public String getPname() { return "Laptop"; }
            public String getName() { return "Suhaas"; }
            public int getQuantity() { return 2; }
            public double getPrice() { return 59999.0; }
            public LocalDate getOrderdate() { return LocalDate.of(2025, 6, 1); }
        };

        when(ois.getOrderItemsbyProduct("Laptop")).thenReturn(List.of(mockDto));

        ResponseEntity<List<CustomOrderitemsdto>> response = controller.getOrderitemsbyProduct("Laptop");

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        assertEquals("Suhaas", response.getBody().get(0).getName());
        assertEquals("Laptop", response.getBody().get(0).getPname());
    }

    @Test
    void testGetOrderItemsByProduct_EmptyList() {
        when(ois.getOrderItemsbyProduct("Phone")).thenReturn(List.of());

        RequestNotFoundException ex = assertThrows(RequestNotFoundException.class, () ->
            controller.getOrderitemsbyProduct("Phone")
        );

        assertEquals("No order items found for the given product : Phone", ex.getMessage());
    }

    @Test
    void testGetOrderItemsByOrderId() {
        OrderItemsdto mockDto = new OrderItemsdto() {
            public int getOid() { return 101; }
            public String getPname() { return "Keyboard"; }
            public String getDescription() { return "Mechanical keyboard"; }
            public int getQuantity() { return 1; }
            public double getPrice() { return 1999.0; }
        };

        when(ois.getOrderItems(101)).thenReturn(List.of(mockDto));

        ResponseEntity<List<OrderItemsdto>> response = controller.getOrderItems(101);

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        assertEquals("Keyboard", response.getBody().get(0).getPname());
    }

    // ✅ Test 4: Throw exception if no order item found for order id
    @Test
    void testGetOrderItemsByOrderId_EmptyList() {
        when(ois.getOrderItems(999)).thenReturn(List.of());

        RequestNotFoundException ex = assertThrows(RequestNotFoundException.class, () ->
            controller.getOrderItems(999)
        );

        assertEquals("No order items found for the given id : 999", ex.getMessage());
    }
}

