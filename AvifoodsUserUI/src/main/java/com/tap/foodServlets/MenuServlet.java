package com.tap.foodServlets;

import com.foodapp.dao.MenuDAO;




import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/viewMenu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FoodApplication", "root", "Avi@143563");
            menuDAO = new MenuDAOImpl(connection); 
        } catch (Exception e) {
            throw new ServletException("Database connection error", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
        String restaurantIdParam = request.getParameter("restaurantId");
        session.setAttribute("restaurantId", restaurantIdParam);
        
        if (restaurantIdParam != null) {
            int restaurantId = Integer.parseInt(restaurantIdParam);
            List<Menu> menuList = menuDAO.getMenuByRestaurantId(restaurantId);
            
            request.getSession().setAttribute("menu", menuList);
            

            request.setAttribute("menuList", menuList);
            request.getRequestDispatcher("menu.jsp").forward(request, response); 
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing restaurant ID");
        }
    }
}