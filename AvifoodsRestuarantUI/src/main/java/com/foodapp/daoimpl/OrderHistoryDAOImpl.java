package com.foodapp.daoimpl;

import com.foodapp.dao.OrderHistoryDAOInterface;

import com.foodapp.model.OrderHistoryDAO;
import com.foodapp.model.OrderItems;

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
    public void updateOrderHistory(OrderHistoryDAO orderHistory) {
        String query = "UPDATE orderhistory SET OrderId = ?, userid = ?, totalAmount = ?, status = ? WHERE orderhistory = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, orderHistory.getOrderId());
            preparedStatement.setInt(2, orderHistory.getUserid());
            preparedStatement.setDouble(3, orderHistory.getTotalAmount());
            preparedStatement.setString(4, orderHistory.getStatus());
            preparedStatement.setInt(5, orderHistory.getOrderhistory());
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
		    public List<OrderItems> getOrdersByRestaurantId(int restaurantId) throws SQLException {
		        List<OrderItems> orderItemsList = new ArrayList<>();
		        String query = "SELECT oi.orderitemid, oi.OrderId, oi.menuid, oi.quantity, oi.itemtotal " +
		                       "FROM orderitems oi " +
		                       "JOIN orderhistory oh ON oi.OrderId = oh.OrderId " + // Assuming there's an order history table
		                       "WHERE oh.userid = ?"; // Assuming userid links to restaurantId

		        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		            preparedStatement.setInt(1, restaurantId);
		            ResultSet rs = preparedStatement.executeQuery();

		            while (rs.next()) {
		                OrderItems orderItem = new OrderItems(
		                    rs.getInt("orderitemid"),
		                    rs.getString("OrderId"),
		                    rs.getInt("menuid"),
		                    rs.getInt("quantity"),
		                    rs.getInt("itemtotal")
		                );
		                orderItemsList.add(orderItem);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return orderItemsList;
		    }
		
	}

