package com.foodapp.daoimpl;

import com.foodapp.dao.OrderDAO;

import com.foodapp.model.Order;

import java.sql.*;

public class OrderDAOimpl implements OrderDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Avi@143563";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    @Override
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (OrderId, userid, resturantid, totalamount, status, paymentmode) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, order.getOrderId());
            stmt.setInt(2, order.getUserid());
            stmt.setInt(3, order.getResturantid());
            stmt.setString(4, order.getTotalamount());
            stmt.setString(5, order.getStatus());
            stmt.setString(6, order.getPaymentmode());

            stmt.executeUpdate();
            System.out.println("Order added successfully!");
        }
    }

    @Override
    public void displayOrders() throws SQLException {
        String query = "SELECT * FROM orders";
        try (Connection conn = connect(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (!rs.next()) {
                System.out.println("No orders available.");
                return;
            }
            do {
                Order order = new Order(
                    rs.getString("OrderId"),
                    rs.getInt("userid"),
                    rs.getInt("resturantid"),
                    rs.getString("orderdate"),
                    rs.getString("totalamount"),
                    rs.getString("status"),
                    rs.getString("paymentmode")
                );
                System.out.println(order);
            } while (rs.next());
        }
    }

    @Override
    public Order searchOrderById(String id) {
        String query = "SELECT * FROM orders WHERE OrderId = ?";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, id);  // Use id as string
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Adjust data types based on your actual database schema
                return new Order(
                    rs.getString("OrderId"),   
                    rs.getInt("userid"),        // Assuming userId is an integer
                    rs.getInt("resturantid"),  // Assuming restaurantId is an integer
                    rs.getString("orderdate"),  // Assuming orderDate is stored as a string
                    rs.getString("totalamount"),// Assuming totalAmount is stored as a string (for currency formatting)
                    rs.getString("status"),     // Assuming status is stored as a string
                    rs.getString("paymentmode") // Assuming paymentMode is stored as a string
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // It's better to log the exception properly or rethrow it
        }
        return null;  // Return null if no order is found
    }


    @Override
    public void updateOrder(String orderId) {
        String query = "UPDATE orders SET status = 'Preparing Your Order' WHERE OrderId = ?";
        
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Ensure OrderId is passed and not null
            if (orderId == null || orderId.isEmpty()) {
                System.out.println("Invalid Order ID provided.");
                return;
            }

            // Set the OrderId in the PreparedStatement
            stmt.setString(1, orderId);

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();
            
            // Check if any row was affected
            if (rowsAffected > 0) {
                System.out.println("Order updated successfully!");
            } else {
                System.out.println("Order not found with ID: " + orderId);
            }
            
        } catch (SQLException e) {
            System.err.println("Error while updating the order: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public void deleteOrderById(int id) throws SQLException {
        String query = "DELETE FROM orders WHERE OrderId = ?";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order deleted successfully!");
            } else {
                System.out.println("Order not found!");
            }
        }
    }
}