package com.tap.foodServlets;

import com.foodapp.dao.UserDAO;



import com.foodapp.model.User;
import com.foodapp.util.DatabaseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

         
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get form parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Basic validation
        if (email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            
            request.setAttribute("error", "Email and password are required");
            response.sendRedirect("login-error.html");
            return;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);

            // Try to get user by email and password
            User user = userDAO.getUserByEmailAndPassword(email, password);

          
            

            if (user != null) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userid", user.getUserid());
                response.sendRedirect("RestaurantServlet");
            } else {
                // User not found or invalid credentials
                request.setAttribute("error", "Invalid email or password");
                response.sendRedirect("login-error.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            response.sendRedirect("login-error.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}