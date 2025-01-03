package com.tap.ResturantServlets;

import com.foodapp.dao.MenuDAO;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;
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

@WebServlet("/AddMenuServlet")
public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        boolean isAvailable = request.getParameter("isAvailable").equals("1");
        String imagePath = request.getParameter("imagePath");
        HttpSession session = request.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        

        Menu menu = new Menu(0, restaurantId, name, description, price, isAvailable, imagePath); // menuId is auto-generated

        try (Connection connection = DatabaseUtil.getConnection()) {
            MenuDAO menuDAO = new MenuDAOImpl(connection);
            menuDAO.addMenu(menu);
        } catch (SQLException e) { 
            e.printStackTrace();
        }

        response.sendRedirect("MenuManagementServlet");
    }
}