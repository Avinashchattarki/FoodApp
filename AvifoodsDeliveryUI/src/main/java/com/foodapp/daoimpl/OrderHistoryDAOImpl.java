package com.foodapp.daoimpl;

import com.foodapp.dao.OrderHistoryDAOInterface;

import com.foodapp.model.OrderHistoryDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAOImpl implements OrderHistoryDAOInterface {

    private Connection connection;

    public OrderHistoryDAOImpl(Connection connection) {
        this.connection = connection;
    }
    

    @Override
    public void addOrderHistory(OrderHistoryDAO orderHistory) {
        String query = "INSERT INTO orderhistory (OrderId, userid, totalAmount, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, orderHistory.getOrderId());
            preparedStatement.setInt(2, orderHistory.getUserid());
            preparedStatement.setDouble(3, orderHistory.getTotalAmount());
            preparedStatement.setString(4, orderHistory.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Order history added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistoryDAO getOrderHistoryById(int orderhistory) {
        String query = "SELECT * FROM orderhistory WHERE orderhistory = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderhistory);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new OrderHistoryDAO(
                        rs.getInt("orderhistory"),
                        rs.getString("OrderId"),
                        rs.getInt("userid"),
                        rs.getInt("totalAmount"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderHistoryDAO> getAllOrderHistories() {
        String query = "SELECT * FROM orderhistory";
        List<OrderHistoryDAO> orderHistoryList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                OrderHistoryDAO orderHistory = new OrderHistoryDAO(
                        rs.getInt("orderhistory"),
                        rs.getString("OrderId"),
                        rs.getInt("userid"),
                        rs.getInt("totalAmount"),
                        rs.getString("status")
                );
                orderHistoryList.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public void updateOrderHistory(String orderId) {
    	String status = "Delieverd";
        String query = "UPDATE orderhistory SET status = ? WHERE OrderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(2, orderId);
            preparedStatement.setString(1, status);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order history updated successfully!");
            } else {
                System.out.println("Order history not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int orderhistory) {
        String query = "DELETE FROM orderhistory WHERE orderhistory = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderhistory);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Order history deleted successfully!");
            } else {
                System.out.println("Order history not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	@Override
	public void updateOrderHistory(OrderHistoryDAO orderHistory) {
		// TODO Auto-generated method stub
		
	}
}