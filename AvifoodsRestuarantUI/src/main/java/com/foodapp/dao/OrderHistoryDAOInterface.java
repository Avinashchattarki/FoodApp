package com.foodapp.dao;

import com.foodapp.model.OrderHistoryDAO;
import com.foodapp.model.OrderItems;

import java.sql.SQLException;
import java.util.List;

public interface OrderHistoryDAOInterface {
    void addOrderHistory(OrderHistoryDAO orderHistory);
    OrderHistoryDAO getOrderHistoryById(int orderhistory);
    List<OrderHistoryDAO> getAllOrderHistories();
    void updateOrderHistory(OrderHistoryDAO orderHistory); // Update method
    void deleteOrderHistory(int orderhistory);   
    List<OrderItems> getOrdersByRestaurantId(int restaurantId) throws SQLException; // Corrected method signature
}