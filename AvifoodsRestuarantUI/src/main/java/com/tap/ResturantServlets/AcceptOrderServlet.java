package com.tap.ResturantServlets;

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

import com.foodapp.dao.OrderItemsDAO;
import com.foodapp.daoimpl.OrderDAOimpl;
import com.foodapp.daoimpl.OrderItemsDAOImpl;
import com.foodapp.model.OrderItems;
import com.foodapp.util.DatabaseUtil;

@WebServlet("/acceptOrder")
public class AcceptOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAOimpl orderDao = new OrderDAOimpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        
        if (orderId != null && !orderId.isEmpty()) {
            orderDao.updateOrder(orderId);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            return;
        }

        HttpSession session = request.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (restaurantId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Restaurant ID not found in session");
            return;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            OrderItemsDAO orderItemsDAO = new OrderItemsDAOImpl();
            List<OrderItems> orderItems = orderItemsDAO.getOrdersByRestaurantId(restaurantId);

            if (orderItems != null && !orderItems.isEmpty()) {
                request.setAttribute("orderItems", orderItems);
            } else {
                request.setAttribute("error", "No orders available.");
            }

            // Forward to JSP
            request.getRequestDispatcher("/viewOrders.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }
}
