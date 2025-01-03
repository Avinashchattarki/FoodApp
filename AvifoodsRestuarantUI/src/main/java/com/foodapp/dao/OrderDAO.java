package com.foodapp.dao;

import java.sql.SQLException;


import com.foodapp.model.Order;

public interface OrderDAO {
    void addOrder(Order order)throws SQLException;
    void displayOrders()throws SQLException;
    Order searchOrderById(String id);
    public void updateOrder(String OrderId);
    void deleteOrderById(int id)throws SQLException;
}
