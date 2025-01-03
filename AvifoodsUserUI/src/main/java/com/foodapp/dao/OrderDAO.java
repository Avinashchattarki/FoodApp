package com.foodapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.foodapp.model.Order;

public interface OrderDAO {
    void addOrder(Order order)throws SQLException;
    void displayOrders()throws SQLException;
    public List<Order> searchOrdersByUserId(int userId) throws SQLException;
    void updateOrder(Order order) throws SQLException;
    void deleteOrderById(int id)throws SQLException;
}
