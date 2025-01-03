package com.tap.ResturantServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foodapp.util.DatabaseUtil;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantId = request.getParameter("restaurantId");
        String password = request.getParameter("password");
       

        // Validate credentials
        if (validateCredentials(restaurantId, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("restaurantId", Integer.parseInt(restaurantId));

            response.sendRedirect("MenuManagementServlet");
        } else {
            // Invalid credentials, show error message
            request.setAttribute("errorMessage", "Invalid Restaurant ID or Password.");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    private boolean validateCredentials(String restaurantId, String password) {
        String query = "SELECT * FROM users WHERE restaurantId = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(restaurantId));
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a matching record is found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}