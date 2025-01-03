package com.tap.foodServlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.foodapp.daoimpl.DeliveryBoyDAOImpl;
import com.foodapp.model.DeliveryBoy;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DeliveryBoyDAOImpl dao = new DeliveryBoyDAOImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String adhar = request.getParameter("adhar");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Create a DeliveryBoy object and save to the database
        DeliveryBoy deliveryBoy = new DeliveryBoy(0, username, password, email, adhar, phone, address);
        dao.addDeliveryBoy(deliveryBoy);

        // Redirect to Login page after successful registration
        response.sendRedirect("login.jsp");
    }
}
