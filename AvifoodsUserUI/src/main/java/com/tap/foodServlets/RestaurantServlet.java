package com.tap.foodServlets;


import com.foodapp.daoimpl.RestuarantDAOImp1;

import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // DAO instance
            RestuarantDAOImp1 restaurantDAO = new RestuarantDAOImp1();

            // Fetch all restaurant data
            List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();;

            // Set restaurants as request attribute
            request.setAttribute("restaurantList", restaurantList);

            // Forward to JSP
            request.getRequestDispatcher("restaurants.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html"); // Redirect to error page on failure
        }
    }
}

