package com.tap.ResturantServlets;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;
import com.foodapp.util.DatabaseUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/DeleteMenuServlet")
public class DeleteMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        try {
            // Get database connection
            Connection connection = DatabaseUtil.getConnection();
            menuDAO = new MenuDAOImpl(connection);
        } catch (SQLException e) {
            throw new ServletException("Database connection failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate session
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("restaurantId") == null) {
                response.sendRedirect("login.jsp"); // Redirect to login page if session is invalid
                return;
            }

            // Delete menu item
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            menuDAO.deleteMenu(menuId);

            // Reload updated menu list for the restaurant
            int restaurantId = (int) session.getAttribute("restaurantId");
            List<Menu> menuList = menuDAO.getMenuByRestaurantId(restaurantId);

            // Set the updated menu list in request scope
            request.setAttribute("menuList", menuList);
            request.setAttribute("successMessage", "Menu deleted successfully.");

            // Forward control back to the same JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("menuManagement.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid menu ID.");
            request.getRequestDispatcher("menuManagement.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Failed to delete menu.");
            request.getRequestDispatcher("menuManagement.jsp").forward(request, response);
        }
    }
}
