package com.project.ordertrackingsystem.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class orderItemsId implements Serializable{
    private int oid;
    private int pid;

    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public orderItemsId() {
    }
    public orderItemsId(int oid, int pid) {
        this.oid = oid;
        this.pid = pid;
    }
}
