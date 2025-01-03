package com.foodapp.daoimpl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;

public class MenuDAOImpl implements MenuDAO {
    private Connection connection;

    // Constructor to initialize the database connection
    public MenuDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addMenu(Menu menu) {
        String query = "INSERT INTO menu (menuid, resturantId, name, description, price, isAvalaible, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menu.getMenuId());
            statement.setInt(2, menu.getRestaurantId());
            statement.setString(3, menu.getName());
            statement.setString(4, menu.getDescription());
            statement.setInt(5, menu.getPrice());
            statement.setBoolean(6, menu.isAvailable());
            statement.setString(7, menu.getImagePath());
            statement.executeUpdate();
            System.out.println("Menu added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public void updateMenu(Menu menu) {
        String query = "UPDATE menu SET resturantId = ?, name = ?, description = ?, price = ?, isAvalaible = ?, imagePath = ? WHERE menuId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menu.getRestaurantId());
            statement.setString(2, menu.getName());
            statement.setString(3, menu.getDescription());
            statement.setInt(4, menu.getPrice());
            statement.setBoolean(5, menu.isAvailable());
            statement.setString(6, menu.getImagePath());
            statement.setInt(7, menu.getMenuId());
            statement.executeUpdate();
            System.out.println("Menu updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String query = "DELETE FROM menu WHERE menuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menuId);
            statement.executeUpdate();
            System.out.println("Menu deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE resturantId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Menu menu = new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getInt("resturantId"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isAvalaible"),
                        resultSet.getString("imagePath")
                );
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public  Menu getMenuById(int menuId) {
        String query = "SELECT * FROM menu WHERE menuid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menuId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getInt("resturantId"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isAvalaible"),
                        resultSet.getString("imagePath")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Menu menu = new Menu(
                        resultSet.getInt("menuId"),
                        resultSet.getInt("resturantId"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("isAvalaible"),
                        resultSet.getString("imagePath")
                );
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}

