package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.DeliveryBoyDAO;
import com.foodapp.model.DeliveryBoy;

public class DeliveryBoyDAOImpl implements DeliveryBoyDAO {
    private final String url = "jdbc:mysql://localhost:3306/foodapplication"; 
    private final String user = "root"; 
    private final String password = "Avi@143563"; 

    private Connection connect() throws SQLException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void addDeliveryBoy(DeliveryBoy deliveryBoy) {
        String sql = "INSERT INTO delieveryboy (username, password, email, adhar, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deliveryBoy.getUsername());
            stmt.setString(2, deliveryBoy.getPassword());
            stmt.setString(3, deliveryBoy.getEmail());
            stmt.setString(4, deliveryBoy.getAdhar());
            stmt.setString(5, deliveryBoy.getPhone());
            stmt.setString(6, deliveryBoy.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DeliveryBoy getDeliveryBoyByUsername(String username) {
        DeliveryBoy deliveryBoy = null;
        String sql = "SELECT * FROM delieveryboy WHERE username = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                deliveryBoy = new DeliveryBoy();
                deliveryBoy.setDeliveryId(rs.getInt("delievery_id"));
                deliveryBoy.setUsername(rs.getString("username"));
                deliveryBoy.setPassword(rs.getString("password"));
                deliveryBoy.setEmail(rs.getString("email"));
                deliveryBoy.setAdhar(rs.getString("adhar"));
                deliveryBoy.setPhone(rs.getString("phone"));
                deliveryBoy.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryBoy;
    }

    @Override
    public List<DeliveryBoy> getAllDeliveryBoys() {
        List<DeliveryBoy> deliveryBoys = new ArrayList<>();
        String sql = "SELECT * FROM delieveryboy";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DeliveryBoy deliveryBoy = new DeliveryBoy();
                deliveryBoy.setDeliveryId(rs.getInt("delivery_id"));
                deliveryBoy.setUsername(rs.getString("username"));
                deliveryBoy.setPassword(rs.getString("password"));
                deliveryBoy.setEmail(rs.getString("email"));
                deliveryBoy.setAdhar(rs.getString("adhar"));
                deliveryBoy.setPhone(rs.getString("phone"));
                deliveryBoy.setAddress(rs.getString("address"));
                deliveryBoys.add(deliveryBoy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryBoys;
    }

    @Override
    public DeliveryBoy getDeliveryBoyById(int deliveryId) {
        DeliveryBoy deliveryBoy = null;
        String sql = "SELECT * FROM delieveryboy WHERE delivery_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deliveryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                deliveryBoy = new DeliveryBoy();
                deliveryBoy.setDeliveryId(rs.getInt("delivery_id"));
                deliveryBoy.setUsername(rs.getString("username"));
                deliveryBoy.setPassword(rs.getString("password"));
                deliveryBoy.setEmail(rs.getString("email"));
                deliveryBoy.setAdhar(rs.getString("adhar"));
                deliveryBoy.setPhone(rs.getString("phone"));
                deliveryBoy.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryBoy;
    }

    @Override
    public void updateDeliveryBoy(DeliveryBoy deliveryBoy) {
        String sql = "UPDATE delieveryboy SET username = ?, password = ?, email = ?, adhar = ?, phone = ?, address = ? WHERE delivery_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deliveryBoy.getUsername());
            stmt.setString(2, deliveryBoy.getPassword());
            stmt.setString(3, deliveryBoy.getEmail());
            stmt.setString(4, deliveryBoy.getAdhar());
            stmt.setString(5, deliveryBoy.getPhone());
            stmt.setString(6, deliveryBoy.getAddress());
            stmt.setInt(7, deliveryBoy.getDeliveryId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDeliveryBoy(int deliveryId) {
        String sql = "DELETE FROM delieveryboy WHERE delivery_id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, deliveryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
