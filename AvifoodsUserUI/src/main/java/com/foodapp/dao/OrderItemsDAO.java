package com.foodapp.dao;

import com.foodapp.model.OrderItems;
import java.sql.SQLException;

public interface OrderItemsDAO {
    void addOrder(OrderItems orderItem) throws SQLException;
    void displayOrders() throws SQLException;
    OrderItems searchOrderById(int id) throws SQLException;
    void updateOrder(OrderItems orderItem) throws SQLException;
    void deleteOrderById(int id) throws SQLException;
}
