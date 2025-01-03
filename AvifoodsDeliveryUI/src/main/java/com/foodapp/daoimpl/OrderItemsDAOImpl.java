package com.foodapp.daoimpl;


import com.foodapp.dao.OrderItemsDAO;

import com.foodapp.model.OrderItems;

import java.sql.*;

public class OrderItemsDAOImpl implements OrderItemsDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Avi@143563";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    @Override
    public void addOrder(OrderItems orderItem) throws SQLException {
        String query = "INSERT INTO orderitems (orderitemid, OrderId, menuid, quantity, itemtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, orderItem.getOrderitemid());
            stmt.setString(2, orderItem.getOrderId());
            stmt.setInt(3, orderItem.getMenuid());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.setDouble(5, orderItem.getItemtotal());

            stmt.executeUpdate();
            System.out.println("Order Item added successfully!");
        }
    }

    @Override
    public void displayOrders() throws SQLException {
        String query = "SELECT * FROM orderitems";
        try (Connection conn = connect(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (!rs.next()) {
                System.out.println("No order items available.");
                return;
            }
            do {
                OrderItems orderItem = new OrderItems(
                    rs.getInt("orderitemid"),
                    rs.getString("OrderId"),
                    rs.getInt("menuid"),
                    rs.getInt("quantity"),
                    rs.getInt("itemtotal")
                );
                System.out.println(orderItem);
            } while (rs.next());
        }
    }

    @Override
    public OrderItems searchOrderById(int id) throws SQLException {
        String query = "SELECT * FROM orderitems WHERE orderitemid = ?";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new OrderItems(
                    rs.getInt("orderitemid"),
                    rs.getString("OrderId"),
                    rs.getInt("menuid"),
                    rs.getInt("quantity"),
                    rs.getInt("itemtotal")
                );
            }
        }
        return null;
    }

    @Override
    public void updateOrder(OrderItems orderItem) throws SQLException {
        String query = "UPDATE orderitems SET OrderId = ?, menuid = ?, quantity = ?, itemtotal = ? WHERE orderitemid = ?";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getMenuid());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getItemtotal());
            stmt.setInt(5, orderItem.getOrderitemid());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order Item updated successfully!");
            } else {
                System.out.println("Order Item not found with ID: " + orderItem.getOrderitemid());
            }
        }
    }

    @Override
    public void deleteOrderById(int id) throws SQLException {
        String query = "DELETE FROM orderitems WHERE orderitemid = ?";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Order Item deleted successfully!");
            } else {
                System.out.println("Order Item not found!");
            }
        }
    }
}
