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

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            MenuDAO menuDAO = new MenuDAOImpl(connection);
            Menu menu = menuDAO.getMenuById(menuId);
            request.setAttribute("menu", menu);
            request.getRequestDispatcher("editMenu.jsp").forward(request, response); // Forward to an edit form JSP
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        boolean isAvailable = request.getParameter("isAvailable").equals("1");
        String imagePath = request.getParameter("imagePath");

        HttpSession session = request.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");


        Menu menu = new Menu(menuId, restaurantId, name, description, price, isAvailable, imagePath);

        try (Connection connection = DatabaseUtil.getConnection()) {
            MenuDAO menuDAO = new MenuDAOImpl(connection);
            menuDAO.updateMenu(menu);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("MenuManagementServlet"); // Redirect to the menu management page
    }
}