package com.project.ordertrackingsystem.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Orderitems{
    @EmbeddedId
    private orderItemsId oitemsid;

    @ManyToOne
    @MapsId("oid")
    @JoinColumn(name = "order_id")
    @JsonManagedReference
    private Orders order;

    @ManyToOne
    @MapsId("pid")
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    private int quantity;
    private double price;

    public orderItemsId getOitemsid() {
        return oitemsid;
    }
    public void setOitemsid(orderItemsId oitemsid) {
        this.oitemsid = oitemsid;
    }

    public Orders getOrder() {
        return order;
    }
    public void setOrder(Orders order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    
}
