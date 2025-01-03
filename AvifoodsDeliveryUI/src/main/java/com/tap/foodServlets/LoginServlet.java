package com.tap.foodServlets;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.foodapp.dao.DeliveryBoyDAO;
import com.foodapp.daoimpl.DeliveryBoyDAOImpl;
import com.foodapp.model.DeliveryBoy;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryBoyDAO deliveryBoyDAO = new DeliveryBoyDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DeliveryBoy deliveryBoy = deliveryBoyDAO.getDeliveryBoyByUsername(username);
        System.out.print(deliveryBoy);

        if (deliveryBoy != null && deliveryBoy.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", deliveryBoy.getUsername());
            session.setAttribute("Phone", deliveryBoy.getPhone());
            
            response.sendRedirect("DashBoard");
        } else {
            // Redirect to login page with error
            request.setAttribute("error", "Invalid username or password!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
