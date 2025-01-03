package com.tap.foodServlets;

import com.foodapp.daoimpl.OrderDAOimpl;

import com.foodapp.model.Order;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TrackOrdersServlet")
public class TrackOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); 
        User user = (User) session.getAttribute("user");
        
        
        if (user == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }
        
        int userId = user.getUserid();
        OrderDAOimpl orderDAO = new OrderDAOimpl();
        List<Order> orders = new ArrayList<>(); 
        
        try {
            orders = orderDAO.searchOrdersByUserId(userId); 
        } catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("errorMessage", "Unable to fetch orders. Please try again later.");
        }
        
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
}
