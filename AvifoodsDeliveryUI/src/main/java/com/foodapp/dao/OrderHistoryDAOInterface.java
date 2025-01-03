package com.foodapp.dao;

import com.foodapp.model.OrderHistoryDAO;

import java.util.List;

public interface OrderHistoryDAOInterface {
    void addOrderHistory(OrderHistoryDAO orderHistory);
    OrderHistoryDAO getOrderHistoryById(int orderhistory);
    List<OrderHistoryDAO> getAllOrderHistories();
    void updateOrderHistory(OrderHistoryDAO orderHistory); // Update method
    void deleteOrderHistory(int orderhistory);            // Delete method
    public void updateOrderHistory(String orderId);
}