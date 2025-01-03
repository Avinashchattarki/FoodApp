package com.tap.foodServlets;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String menuId = request.getParameter("menuId");

        // Retrieve the current cart map from session (if it exists)
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Get the current quantity of the menu item
        int currentQuantity = cart.getOrDefault(menuId, 0);

        // Update the cart based on the action
        if ("add".equals(action)) {
            cart.put(menuId, currentQuantity + 1);
        } else if ("remove".equals(action)) {
            if (currentQuantity > 1) {
                cart.put(menuId, currentQuantity - 1);
            } else {
                cart.remove(menuId);
            }
        } else if ("clear".equals(action)) {
            // Clear the cart
            cart.clear();
        } else if ("order".equals(action)) {
            // Redirect to the addressConfirmation.jsp page when "Order Now" is clicked
            response.sendRedirect("addressConfirmation.jsp");
            return;  // Exit the method to avoid further processing
        } else if ("addmore".equals(action)) {
            // Redirect to the view menu page based on the restaurantId in the session
            String restaurantId = (String) session.getAttribute("restaurantId");
            String redirectUrl = (restaurantId != null) ? "viewMenu?restaurantId=" + restaurantId : "viewMenu?restaurantId=1";
            response.sendRedirect(redirectUrl);
            return;  // Exit the method after redirection
        }

        // Update the cart count in the session
        session.setAttribute("cart", cart);

        int cartCount = cart.values().stream().mapToInt(Integer::intValue).sum();
        session.setAttribute("cartCount", cartCount);

        // Get the referer to redirect back to the previous page or menu.jsp
        String referer = request.getHeader("Referer");

        // Handle actions based on the referer header
        if ("clear".equals(action)) {
            // If the cart is cleared, stay on the same page
            if (referer != null) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect("menu.jsp");
            }
        } else {
            if (referer != null) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect("menu.jsp");
            }
        }
    }
}
