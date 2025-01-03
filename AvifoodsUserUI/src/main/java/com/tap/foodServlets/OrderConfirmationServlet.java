package com.tap.foodServlets;

import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.foodapp.model.Delivery;
import com.foodapp.model.Menu;
import com.foodapp.model.User;
import com.foodapp.model.OrderHistoryDAO;
import com.foodapp.model.OrderItems;
import com.foodapp.model.Restaurant;
import com.foodapp.daoimpl.DeliveryDAOImpl;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.daoimpl.OrderHistoryDAOImpl;
import com.foodapp.daoimpl.OrderItemsDAOImpl;
import com.foodapp.daoimpl.RestuarantDAOImp1;
import com.foodapp.util.DatabaseUtil;

@WebServlet("/OrderConfirmationServlet")
public class OrderConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getUserid();
        String restaurantIdStr = (String) session.getAttribute("restaurantId");
        int resturantId = Integer.parseInt(restaurantIdStr);
        System.out.print(resturantId);
        @SuppressWarnings("unchecked")
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            request.setAttribute("error", "Your cart is empty or restaurant not selected.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        }

        String paymentMethod = request.getParameter("paymentMethod");
        String paymentMode = paymentMethod.equalsIgnoreCase("UPI") ? "Online" : "Offline";
        double totalAmount = 0.0;

        try (Connection connection = DatabaseUtil.getConnection()) {
            MenuDAOImpl menuDAO = new MenuDAOImpl(connection);

            // Calculate total amount
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                Menu item = menuDAO.getMenuById(Integer.parseInt(entry.getKey()));
                if (item != null) {
                    totalAmount += item.getPrice() * entry.getValue();
                }
            }

            String orderId = generateOrderId();
            Date orderDate = new Date();

            // Insert order into database
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO orders (OrderId, userid, resturantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                statement.setString(1, orderId);
                statement.setInt(2, userId);
                statement.setInt(3, resturantId);
                statement.setTimestamp(4, new java.sql.Timestamp(orderDate.getTime()));
                statement.setDouble(5, totalAmount);
                statement.setString(6, "Pending");
                statement.setString(7, paymentMode);

                statement.executeUpdate();
            }
            
            
         // Add to order history
            OrderHistoryDAOImpl orderHistoryDAO = new OrderHistoryDAOImpl(connection);
            OrderHistoryDAO orderHistory = new OrderHistoryDAO();
            orderHistory.setOrderId(orderId);
            orderHistory.setUserid(userId);
            orderHistory.setTotalAmount(totalAmount);
            orderHistory.setStatus("Pending");

            orderHistoryDAO.addOrderHistory(orderHistory);

            
            
            
            
            OrderItemsDAOImpl orderItemsDAO = new OrderItemsDAOImpl(); 
            int quantity = 0;
            int total=0;
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                Menu item = menuDAO.getMenuById(Integer.parseInt(entry.getKey()));
                if (item != null) {
                    quantity = entry.getValue();
                    total += item.getPrice() * entry.getValue();
                    
                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrderId(orderId); 
                    orderItem.setMenuid(Integer.parseInt(entry.getKey())); 
                    orderItem.setQuantity(quantity);
                    orderItem.setItemtotal(total); 
                    total=0;

                    try {
                        orderItemsDAO.addOrder(orderItem); 
                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("error", "Error while adding order items. Please try again.");
                        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
                        return;
                    }

                    
                }
            }
            
            
            String address = request.getParameter("address");
            String street = request.getParameter("street");
            String phone = request.getParameter("phone");
            String pincode = request.getParameter("pincode"); 
            String deliveryInstructions = request.getParameter("delivery_instructions");
            
            
            RestuarantDAOImp1 res = new RestuarantDAOImp1();
            Restaurant rs=res.searchRestaurantById(resturantId);
            
            
            

            // Populate the Delivery object
            Delivery delivery = new Delivery();
            delivery.setOrderId(orderId);
            delivery.setAddress(address);
            delivery.setStreetName(street);
            delivery.setPincode(pincode);
            delivery.setPhone(phone);
            delivery.setDeliveryInstructions(deliveryInstructions);
            delivery.setPaymentMethod(paymentMode);
            delivery.setPrice(totalAmount);
            delivery.setResturantname(rs.getName());
            delivery.setAddressResturant(rs.getAddress());

            // Call DAO to insert data
            DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
            deliveryDAO.addDelivery(delivery);
    
            
            
            
            
            
         // Update session attributes
            session.setAttribute("orderId", orderId);
            session.setAttribute("totalAmount", totalAmount);
            session.setAttribute("paymentMode", paymentMode);
            session.removeAttribute("cart");
            session.setAttribute("cartCount", 0);

            response.sendRedirect("orderSuccess.jsp");

            

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing your order. Please try again.");
            request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
        }
    }

    private String generateOrderId() {
        return "ORD" + System.currentTimeMillis();
    }
}
