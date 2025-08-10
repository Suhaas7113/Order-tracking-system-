package com.project.ordertrackingsystem.Controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.project.ordertrackingsystem.DTO.Customerdto;
import com.project.ordertrackingsystem.Model.Customer;
import com.project.ordertrackingsystem.Service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService cs;

    @InjectMocks
    private CustomerController cc;

    @Test
    void testGetAllCustomers() {
        Customerdto cu = new Customerdto() {
            @Override
            public int getId() {
                return 9;
            }
            @Override
            public String getName() {
                return "Ram Suhaas";
            }
            @Override
            public String getEmail() {
                return "suhaas@example.com";
            }
            @Override
            public Long getMobileNumber() {
                return 6303168672L;
            }
        };


        Mockito.when(cs.getcustomer()).thenReturn(List.of(cu));

        ResponseEntity<List<Customerdto>> response = cc.getCustomer();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size()); 
        assertEquals("Ram Suhaas", response.getBody().get(0).getName());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setCid(9);
        customer.setName("Ram Suhaas");

        Mockito.when(cs.getCustomerbyId(9)).thenReturn(customer);

        ResponseEntity<Customer> response = cc.getCustomerById(9);

        assertNotNull(response.getBody());
        assertEquals("Ram Suhaas", response.getBody().getName());
    }
}