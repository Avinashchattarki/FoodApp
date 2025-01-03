package com.foodapp.daoimpl;

import com.foodapp.dao.OrderDAO;

import com.foodapp.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Order> searchOrdersByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM orders WHERE userid = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                    rs.getString("OrderId"),
                    rs.getInt("userid"),
                    rs.getInt("resturantid"),
                    rs.getString("orderdate"),
                    rs.getString("totalamount"),
                    rs.getString("status"),
                    rs.getString("paymentmode")
                ));
            }
        }

        return orders;
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        String query = "UPDATE orders SET userid = ?, resturantid = ?, totalamount = ?, status = ?, paymentmode = ? WHERE OrderId = ?";
        try (Connection conn = connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, order.getUserid());
            stmt.setInt(2, order.getResturantid());
            stmt.setString(3, order.getTotalamount());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getPaymentmode());
            stmt.setString(6, order.getOrderId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order updated successfully!");
            } else {
                System.out.println("Order not found with ID: " + order.getOrderId());
            }
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