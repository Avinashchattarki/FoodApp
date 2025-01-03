package com.foodapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.model.User;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    public void createUser(String username, String password, String email, String address) throws SQLException {
        String query = "INSERT INTO user (username, password, email, address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.executeUpdate();
        }
    }

    // Retrieve a user by 
    public User getUserById(int userid) throws SQLException {
        String query = "SELECT * FROM user WHERE userid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userid);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                    	rs.getInt("useid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("address")
                    );
                }
            }
        }
        return null; // User not found
    }

    // Retrieve all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                	rs.getInt("userid"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("address")
                ));
            }
        }
        return users;
    }

    
 
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { 
                return new User(
                	rs.getInt("userid"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("address")
                );
            }
            return null;
          }
    }
    // Update a user's information
    public void updateUser(int userid, String username, String password, String email, String address) throws SQLException {
        String query = "UPDATE user SET username = ?, password = ?, email = ?, address = ? WHERE userid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setInt(5, userid);
            pstmt.executeUpdate();
        }
    }

    public void deleteUser(int userid) throws SQLException {
        String query = "DELETE FROM user WHERE userid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userid);
            pstmt.executeUpdate();
        }
    }
}

