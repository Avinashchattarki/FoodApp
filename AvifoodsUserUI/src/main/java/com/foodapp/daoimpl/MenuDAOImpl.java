package com.foodapp.daoimpl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;



public class MenuDAOImpl implements MenuDAO {
    private Connection connection;

   
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

    
    // Main method with user options
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/foodapplication", "root", "Avi@143563")) {

            MenuDAOImpl menuDAO = new MenuDAOImpl(connection);
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n--- Menu Options ---");
                System.out.println("1. Add Menu");
                System.out.println("2. Update Menu");
                System.out.println("3. Delete Menu");
                System.out.println("4. Get Menu By ID");
                System.out.println("5. Get All Menus");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1: // Add Menu
                        System.out.println("Enter Menu Details:");
                        System.out.print("Menu ID: ");
                        int menuId = scanner.nextInt();
                        System.out.print("Restaurant ID: ");
                        int restaurantId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Price: ");
                        int price = scanner.nextInt();
                        System.out.print("Is Available (1 for Yes, 0 for No): ");
                        boolean isAvailable = scanner.nextInt() == 1;
                        scanner.nextLine(); // Consume newline
                        System.out.print("Image Path: ");
                        String imagePath = scanner.nextLine();

                        Menu menu = new Menu(menuId, restaurantId, name, description, price, isAvailable, imagePath);
                        menuDAO.addMenu(menu);
                        break;

                    case 2: // Update Menu
                        System.out.println("Enter Updated Menu Details:");
                        System.out.print("Menu ID: ");
                        menuId = scanner.nextInt();
                        System.out.print("Restaurant ID: ");
                        restaurantId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Name: ");
                        name = scanner.nextLine();
                        System.out.print("Description: ");
                        description = scanner.nextLine();
                        System.out.print("Price: ");
                        price = scanner.nextInt();
                        System.out.print("Is Available (1 for Yes, 0 for No): ");
                        isAvailable = scanner.nextInt() == 1;
                        scanner.nextLine(); // Consume newline
                        System.out.print("Image Path: ");
                        imagePath = scanner.nextLine();

                        menu = new Menu(menuId, restaurantId, name, description, price, isAvailable, imagePath);
                        menuDAO.updateMenu(menu);
                        break;

                    case 3: // Delete Menu
                        System.out.print("Enter Menu ID to delete: ");
                        menuId = scanner.nextInt();
                        menuDAO.deleteMenu(menuId);
                        break;

                    case 4: // Get Menu By ID
                        System.out.print("Enter Menu ID to retrieve: ");
                        menuId = scanner.nextInt();
                        Menu retrievedMenu = menuDAO.getMenuById(menuId);
                        if (retrievedMenu != null) {
                            System.out.println(retrievedMenu);
                        } else {
                            System.out.println("Menu not found.");
                        }
                        break;

                    case 5: // Get All Menus
                        List<Menu> menus = menuDAO.getAllMenus();
                        for (Menu m : menus) {
                            System.out.println(m);
                        }
                        break;

                    case 6: // Exit
                        exit = true;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            scanner.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

