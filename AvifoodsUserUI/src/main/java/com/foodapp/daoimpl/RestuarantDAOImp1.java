package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodapp.model.Restaurant;

public class RestuarantDAOImp1 {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/foodapplication";
    private static final String USER = "root";
    private static final String PASSWORD = "Avi@143563";

    // Establish connection to database
    private Connection connect() throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Method to add a restaurant to the database
    public void addRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO resturant (resturantid, name, cuisineType, deliveryTime, address, ratings, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, restaurant.getRestaurantId());
            stmt.setString(2, restaurant.getName());
            stmt.setString(3, restaurant.getCuisineType());
            stmt.setInt(4, restaurant.getDeliveryTime());
            stmt.setString(5, restaurant.getAddress());
            stmt.setDouble(6, restaurant.getRatings());
            stmt.setBoolean(7, restaurant.isActive());
            stmt.setString(8, restaurant.getImagePath());

            stmt.executeUpdate();
            System.out.println("Restaurant added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding restaurant: " + e.getMessage());
        }
    }

    // Method to display all restaurants from the database
    public void displayRestaurants() {
        String query = "SELECT * FROM resturant";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                System.out.println("No restaurants available.");
                return;
            }
            do {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("resturantid"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineType(rs.getString("cuisineType"));
                restaurant.setDeliveryTime(rs.getInt("deliveryTime"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setRatings(rs.getDouble("ratings"));
                restaurant.setActive(rs.getBoolean("isActive"));
                restaurant.setImagePath(rs.getString("imagePath"));

                System.out.println(restaurant);
            } while (rs.next());
        } catch (Exception e) {
            System.out.println("Error displaying restaurants: " + e.getMessage());
        }
    }

    // Method to search restaurant by ID from the database
    public Restaurant searchRestaurantById(int id) {
        String query = "SELECT * FROM resturant WHERE resturantid = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("resturantid"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineType(rs.getString("cuisineType"));
                restaurant.setDeliveryTime(rs.getInt("deliveryTime"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setRatings(rs.getDouble("ratings"));
                restaurant.setActive(rs.getBoolean("isActive"));
                restaurant.setImagePath(rs.getString("imagePath"));

                return restaurant;
            }
        } catch (Exception e) {
            System.out.println("Error searching for restaurant: " + e.getMessage());
        }
        return null;
    }
    
    
    public List<Restaurant> getAllRestaurants() throws Exception {
        String query = "SELECT * FROM resturant";
        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("resturantid"));
                restaurant.setName(rs.getString("name"));
                restaurant.setCuisineType(rs.getString("cuisineType"));
                restaurant.setDeliveryTime(rs.getInt("deliveryTime"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setRatings(rs.getDouble("ratings"));
                restaurant.setActive(rs.getBoolean("isActive"));
                restaurant.setImagePath(rs.getString("imagePath"));

                restaurantList.add(restaurant);
            }
        }
        return restaurantList;
    }

    // Method to delete a restaurant by ID from the database
    public void deleteRestaurantById(int id) {
        String query = "DELETE FROM resturant WHERE resturantid = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Restaurant deleted successfully!");
            } else {
                System.out.println("Restaurant not found!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting restaurant: " + e.getMessage());
        }
    }

    // Main method for menu-driven program
    public static void main(String[] args) {
        RestuarantDAOImp1 daoImplementation = new RestuarantDAOImp1(); // Using the DAOImplementation class
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Restaurant Management ====");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Display All Restaurants");
            System.out.println("3. Search Restaurant by ID");
            System.out.println("4. Delete Restaurant by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Restaurant restaurant = new Restaurant();
                    System.out.print("Enter Restaurant ID: ");
                    restaurant.setRestaurantId(scanner.nextInt());
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Name: ");
                    restaurant.setName(scanner.nextLine());

                    System.out.print("Enter Cuisine Type: ");
                    restaurant.setCuisineType(scanner.nextLine());

                    System.out.print("Enter Delivery Time (minutes): ");
                    restaurant.setDeliveryTime(scanner.nextInt());
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Address: ");
                    restaurant.setAddress(scanner.nextLine());

                    System.out.print("Enter Ratings (0.0 to 5.0): ");
                    restaurant.setRatings(scanner.nextDouble());

                    System.out.print("Is Active (true/false): ");
                    restaurant.setActive(scanner.nextBoolean());

                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Image Path: ");
                    restaurant.setImagePath(scanner.nextLine());

                    daoImplementation.addRestaurant(restaurant);
                    break;

                case 2:
                    daoImplementation.displayRestaurants();
                    break;

                case 3:
                    System.out.print("Enter Restaurant ID to search: ");
                    int searchId = scanner.nextInt();
                    Restaurant foundRestaurant = daoImplementation.searchRestaurantById(searchId);
                    if (foundRestaurant != null) {
                        System.out.println("Restaurant Found: " + foundRestaurant);
                    } else {
                        System.out.println("Restaurant not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Restaurant ID to delete: ");
                    int deleteId = scanner.nextInt();
                    daoImplementation.deleteRestaurantById(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
