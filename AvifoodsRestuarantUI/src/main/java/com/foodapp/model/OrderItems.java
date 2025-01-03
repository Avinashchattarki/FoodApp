package com.foodapp.model;

public class OrderItems {
    private int orderitemid;
    private String OrderId;
    private int menuid;
    private int quantity;
    private int itemtotal;
 
    // Constructor
    public OrderItems(int orderitemid, String OrderId, int menuid, int quantity, int itemtotal) {
        this.orderitemid = orderitemid;
        this.OrderId = OrderId;
        this.menuid = menuid;
        this.quantity = quantity;
        this.itemtotal = itemtotal;
    }
    
    public OrderItems()
    {
    	
    }

    // Getters and Setters
    public int getOrderitemid() {
        return orderitemid;
    }

    public void setOrderitemid(int orderitemid) {
        this.orderitemid = orderitemid;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        this.OrderId = orderId;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemtotal() {
        return itemtotal;
    }

    public void setItemtotal(int itemtotal) {
        this.itemtotal = itemtotal;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderitemid=" + orderitemid +
                ", OrderId=" + OrderId +
                ", menuid=" + menuid +
                ", quantity=" + quantity +
                ", itemtotal=" + itemtotal +
                '}';
    }
}