package com.tap.foodServlets;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.foodapp.dao.DeliveryDAO;
import com.foodapp.daoimpl.DeliveryDAOImpl;
import com.foodapp.model.Delivery;

@WebServlet("/DashBoard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryDAO deliveryDAO = new DeliveryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all deliveries
        List<Delivery> deliveries = deliveryDAO.getAllDeliveries();
        System.out.print(deliveries);
        
        // Set deliveries as a request attribute
        request.setAttribute("deliveries", deliveries);
        
        // Forward to the dashboard JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
