package com.foodapp.dao;

import java.sql.SQLException;

import com.foodapp.model.Order;

public interface OrderDAO {
    void addOrder(Order order)throws SQLException;
    void displayOrders()throws SQLException;
    public Order searchOrdersByOrderId(String OrderId);
    void updateOrder(Order order) throws SQLException;
    void deleteOrderById(int id)throws SQLException;
    public void updateOrder(String orderId,String number,String name);
    public void updateOrder1(String orderId);
}
