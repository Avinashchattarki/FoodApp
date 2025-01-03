package com.tap.foodServlets;

import com.foodapp.dao.UserDAO;




import com.foodapp.util.DatabaseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;



public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 

            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if(!(confirmpassword.equals(password)))
        {
        	request.setAttribute("error", "All fields are required");
            response.sendRedirect("registration-error.html");
            return;	
        }
        // Basic validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            address == null || address.trim().isEmpty()) {
            
            request.setAttribute("error", "All fields are required");
            response.sendRedirect("LOGIN.html");
            return;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            UserDAO userDAO = new UserDAO(connection);

            // Create new user
            userDAO.createUser(username, password, email, address);//if this line executes without exception 
            response.sendRedirect("LOGIN.html");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            response.sendRedirect("registration-error.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to the registration page
        response.sendRedirect("LOGIN.html");
    }
}