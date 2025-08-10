package com.project.ordertrackingsystem.Controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.project.ordertrackingsystem.DTO.Productdto;
import com.project.ordertrackingsystem.Exception.RequestNotFoundException;
import com.project.ordertrackingsystem.Service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testGetProductByName_Success() {
        Productdto product = new Productdto() {
            public int getPid() { return 101; }
            public String getPname() { return "Mouse"; }
            public String getDescription() { return "Wireless mouse"; }
            public double getPrice() { return 799.0; }
        };

        when(productService.getProductbyName("Mouse")).thenReturn(List.of(product));

        ResponseEntity<List<Productdto>> response = productController.getProductbyName("Mouse");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Mouse", response.getBody().get(0).getPname());
        assertEquals(799.0, response.getBody().get(0).getPrice());
    }

    @Test
    void testGetProductByName_NotFound() {
        when(productService.getProductbyName("InvalidName")).thenReturn(List.of());

        assertThrows(RequestNotFoundException.class, () -> {
            productController.getProductbyName("InvalidName");
        });
    }

    @Test
    void testGetProductPagination_Success() {
        Productdto product = new Productdto() {
            public int getPid() { return 201; }
            public String getPname() { return "Keyboard"; }
            public String getDescription() { return "Mechanical Keyboard"; }
            public double getPrice() { return 1599.0; }
        };

        Pageable pageable = PageRequest.of(0, 1);
        Page<Productdto> mockPage = new PageImpl<>(List.of(product));

        when(productService.getproduct(pageable)).thenReturn(mockPage);

        ResponseEntity<Page<Productdto>> response = productController.getproduct(0, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Keyboard", response.getBody().getContent().get(0).getPname());
        assertEquals(1599.0, response.getBody().getContent().get(0).getPrice());
    }

    @Test
    void testGetProductPagination_NotFound() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Productdto> emptyPage = Page.empty();

        when(productService.getproduct(pageable)).thenReturn(emptyPage);

        assertThrows(RequestNotFoundException.class, () -> {
            productController.getproduct(0, 1);
        });
    }
}
