package com.tap.foodServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.foodapp.dao.DeliveryDAO;
import com.foodapp.daoimpl.DeliveryDAOImpl;
import com.foodapp.daoimpl.OrderDAOimpl;
import com.foodapp.daoimpl.OrderHistoryDAOImpl;
import com.foodapp.model.Delivery;
import com.foodapp.util.DatabaseUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet("/DeliveryStatusServlet")
public class DeliveryStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAOimpl orderDao = new OrderDAOimpl();
    private DeliveryDAO deliveryDAO = new DeliveryDAOImpl();
	private Connection con;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String action = request.getParameter("status");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        String Phone = (String) session.getAttribute("Phone");
        
        if (orderId != null && !orderId.isEmpty() && "accepted".equals(action)) {
            orderDao.updateOrder(orderId,name,Phone);
        }
        else if(action.equals("delivered"))
        {
        	orderDao.updateOrder1(orderId);
        	try {
				con = DatabaseUtil.getConnection();
			} catch (SQLException e) {

				e.printStackTrace();
			}
        	OrderHistoryDAOImpl odaoh = new OrderHistoryDAOImpl(con);
        	odaoh.updateOrderHistory(orderId);
        	
        	DeliveryDAOImpl dil = new DeliveryDAOImpl();
        	dil.deleteDelivery(orderId);
        	
        	
     
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order ID");
            return;
        }
        
        
        List<Delivery> deliveries = deliveryDAO.getAllDeliveries();
        System.out.print(deliveries);
        

        request.setAttribute("deliveries", deliveries);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
        
    }
}


