package com.foodapp.daoimpl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.DeliveryDAO;
import com.foodapp.model.Delivery;

public class DeliveryDAOImpl implements DeliveryDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Avi@143563";

    @Override
    public void addDelivery(Delivery delivery) {
        String sql = "INSERT INTO deliverytable (OrderId,Address, StreetName,Pincode, Phone, DeliveryInstructions, PaymentMethod, Price, resturantname , addressResturant) VALUES (?,?, ?, ?, ?, ?, ?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
        	ps.setString(1, delivery.getOrderId());
            ps.setString(2, delivery.getAddress());
            ps.setString(3, delivery.getStreetName());
            ps.setString(4, delivery.getPincode());
            ps.setString(5, delivery.getPhone());
            ps.setString(6, delivery.getDeliveryInstructions());
            ps.setString(7, delivery.getPaymentMethod());
            ps.setDouble(8, delivery.getPrice());
            ps.setString(9, delivery.getResturantname());
            ps.setString(10,delivery.getAddressResturant());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Delivery getDeliveryById(int orderId) {
        String sql = "SELECT * FROM deliverytable WHERE OrderId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Delivery delivery = new Delivery();
                delivery.setOrderId(rs.getString("OrderId"));
                delivery.setAddress(rs.getString("Address"));
                delivery.setStreetName(rs.getString("StreetName"));
                delivery.setPincode(rs.getString("Pincode"));
                delivery.setPhone(rs.getString("Phone"));
                delivery.setDeliveryInstructions(rs.getString("DeliveryInstructions"));
                delivery.setPaymentMethod(rs.getString("PaymentMethod"));
                delivery.setPrice(rs.getDouble("Price"));
                delivery.setResturantname(rs.getString("resturantname"));
                delivery.setAddressResturant(rs.getString("addressResturant"));
                return delivery;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        String sql = "SELECT * FROM deliverytable";
        List<Delivery> deliveries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Delivery delivery = new Delivery();
                delivery.setOrderId(rs.getString("OrderId"));
                delivery.setAddress(rs.getString("Address"));
                delivery.setStreetName(rs.getString("StreetName"));
                delivery.setPhone(rs.getString("Phone"));
                delivery.setDeliveryInstructions(rs.getString("DeliveryInstructions"));
                delivery.setPaymentMethod(rs.getString("PaymentMethod"));
                delivery.setPrice(rs.getDouble("Price"));
                delivery.setResturantname(rs.getString("resturantname"));
                delivery.setAddressResturant(rs.getString("addressResturant"));
                deliveries.add(delivery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        String sql = "UPDATE deliverytable SET Address = ?, StreetName = ?, Phone = ?, DeliveryInstructions = ?, PaymentMethod = ?, Price = ? WHERE OrderId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, delivery.getAddress());
            ps.setString(2, delivery.getStreetName());
            ps.setString(3, delivery.getPhone());
            ps.setString(4, delivery.getDeliveryInstructions());
            ps.setString(5, delivery.getPaymentMethod());
            ps.setDouble(6, delivery.getPrice());
            ps.setString(7, delivery.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDelivery(int orderId) {
        String sql = "DELETE FROM deliverytable WHERE OrderId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
