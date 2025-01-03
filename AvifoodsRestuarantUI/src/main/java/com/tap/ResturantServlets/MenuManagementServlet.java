package com.tap.ResturantServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;
import com.foodapp.util.DatabaseUtil;

@WebServlet("/MenuManagementServlet")
public class MenuManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (restaurantId == null) {
            response.sendRedirect("login.html"); // Redirect to login if not logged in
            return;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {
            MenuDAO menuDAO = new MenuDAOImpl(connection);
            List<Menu> menuList = menuDAO.getMenuByRestaurantId(restaurantId);
            request.setAttribute("menuList", menuList);
            request.getRequestDispatcher("menuManagement.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (restaurantId == null) {
            response.sendRedirect("login.html"); // Redirect to login if not logged in
            return;
        }

        String action = request.getParameter("action");
        Menu menu = new Menu();
        menu.setRestaurantId(restaurantId);
        menu.setName(request.getParameter("name"));
        menu.setDescription(request.getParameter("description"));
        menu.setPrice(Integer.parseInt(request.getParameter("price")));
        menu.setAvailable("1".equals(request.getParameter("isAvailable")));
        menu.setImagePath(request.getParameter("imagePath"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            MenuDAO menuDAO = new MenuDAOImpl(connection);
            if ("add".equals(action)) {
                menu.setMenuId(Integer.parseInt(request.getParameter("menuId"))); // Assuming menuId is provided for adding
                menuDAO.addMenu(menu);
            } else if ("edit".equals(action)) {
                menu.setMenuId(Integer.parseInt(request.getParameter("menuId")));
                menuDAO.updateMenu(menu);
            }
            response.sendRedirect("MenuManagementServlet"); // Redirect to the same servlet to refresh the menu list
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}