package com.tap.ResturantServlets;


import com.foodapp.dao.OrderItemsDAO;
import com.foodapp.daoimpl.OrderItemsDAOImpl;
import com.foodapp.model.OrderItems;
import com.foodapp.util.DatabaseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ViewOrdersServlet")
public class ViewOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Do not create a new session
        if (session == null || session.getAttribute("restaurantId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login page if session is invalid
            return;
        }

        int restaurantId = (Integer) session.getAttribute("restaurantId");

        try (Connection connection = DatabaseUtil.getConnection()) {
            OrderItemsDAO orderItemsDAO = new OrderItemsDAOImpl();
            List<OrderItems> orderItems = orderItemsDAO.getOrdersByRestaurantId(restaurantId);
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("viewOrders.jsp").forward(request, response); // Forward to JSP
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}