package com.project.ordertrackingsystem.dto;

import java.util.List;

public class InsertionbyCustomeriddto {
    private int customerId;

        
    // @NotEmpty(message = "Product List cannot be empty")
    private List<ProductQuantity> products;

    public static class ProductQuantity {
        private int productId;
        private int quantity;

        public int getProductId() {
            return productId;
        }
        public void setProductId(int productId) {
            this.productId = productId;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantity> products) {
        this.products = products;
    }
    
}

