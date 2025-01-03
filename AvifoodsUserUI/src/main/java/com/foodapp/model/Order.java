package com.foodapp.model;

public class Order {
    private String OrderId;
    private int userid;
    private int resturantid;
    private String orderdate;
    private String totalamount;
    private String status;
    private String paymentmode;

    // Constructor
    public Order(String OrderId, int userid, int resturantid, String orderdate, 
                String totalamount, String status, String paymentmode) {
        this.OrderId = OrderId;
        this.userid = userid;
        this.resturantid = resturantid;
        this.orderdate = orderdate;
        this.totalamount = totalamount;
        this.status = status;
        this.paymentmode = paymentmode;
    }

    // Getters and Setters
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getResturantid() {
        return resturantid;
    }

    public void setResturantid(int resturantid) {
        this.resturantid = resturantid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId=" + OrderId +
                ", userid=" + userid +
                ", resturantid=" + resturantid +
                ", orderdate='" + orderdate + '\'' +
                ", totalamount='" + totalamount + '\'' +
                ", status='" + status + '\'' +
                ", paymentmode='" + paymentmode + '\'' +
                '}';
    }
} 