package com.foodapp.daoimpl;

import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class UserDAOImpl {
    public static void main(String[] args) {
        // Database credentials
        String dbURL = "jdbc:mysql://localhost:3306/foodapplication";
        String dbUsername = "root";
        String dbPassword = "Avi@143563";

        try (Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
            System.out.println("Database connected successfully!");

            UserDAO userDAO = new UserDAO(connection);
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                try {
                    System.out.println("\nChoose an operation:");
                    System.out.println("1. Create a new user");
                    System.out.println("2. Retrieve a user by ID");
                    System.out.println("3. Update a user");
                    System.out.println("4. Retrieve all users");
                    System.out.println("5. Delete a user");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    switch (choice) {
                        case 1:
                            try {
                                System.out.println("\nEnter user details:");
                   
                                System.out.print("Username: ");
                                String username = scanner.nextLine();
                                
                                System.out.print("Password: ");
                                String password = scanner.nextLine();
                                
                                System.out.print("Email: ");
                                String email = scanner.nextLine();
                                
                                System.out.print("Address: ");
                                String address = scanner.nextLine();

                                userDAO.createUser( username, password, email, address);
                                System.out.println("User created successfully!");
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. User ID must be a number.");
                                scanner.nextLine(); // Clear invalid input
                            } catch (SQLException e) {
                                System.err.println("Error creating user: " + e.getMessage());
                            }
                            break;

                        case 2:
                            try {
                                System.out.print("Enter user ID to retrieve: ");
                                int retrieveId = scanner.nextInt();
                                scanner.nextLine(); // Clear buffer

                                User user = userDAO.getUserById(retrieveId);
                                if (user != null) {
                                    System.out.println("Retrieved User: " + user);
                                } else {
                                    System.out.println("User not found.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. User ID must be a number.");
                                scanner.nextLine(); // Clear invalid input
                            } catch (SQLException e) {
                                System.err.println("Error retrieving user: " + e.getMessage());
                            }
                            break;

                        case 3:
                            try {
                                System.out.println("\nEnter updated user details:");
                                System.out.print("User ID: ");
                                int updateId = scanner.nextInt();
                                scanner.nextLine();
                                
                                System.out.print("Username: ");
                                String updateUsername = scanner.nextLine();
                                
                                System.out.print("Password: ");
                                String updatePassword = scanner.nextLine();
                                
                                System.out.print("Email: ");
                                String updateEmail = scanner.nextLine();
                                
                                System.out.print("Address: ");
                                String updateAddress = scanner.nextLine();

                                userDAO.updateUser(updateId, updateUsername, updatePassword, updateEmail, updateAddress);
                                System.out.println("User updated successfully!");
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. User ID must be a number.");
                                scanner.nextLine(); // Clear invalid input
                            } catch (SQLException e) {
                                System.err.println("Error updating user: " + e.getMessage());
                            }
                            break;

                        case 4:
                            try {
                                List<User> allUsers = userDAO.getAllUsers();
                                System.out.println("\nAll Users:");
                                for (User u : allUsers) {
                                    System.out.println(u);
                                }
                            } catch (SQLException e) {
                                System.err.println("Error retrieving users: " + e.getMessage());
                            }
                            break;

                        case 5:
                            try {
                                System.out.print("Enter user ID to delete: ");
                                int deleteId = scanner.nextInt();
                                scanner.nextLine(); // Clear buffer

                                userDAO.deleteUser(deleteId);
                                System.out.println("User deleted successfully!");
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. User ID must be a number.");
                                scanner.nextLine(); // Clear invalid input
                            } catch (SQLException e) {
                                System.err.println("Error deleting user: " + e.getMessage());
                            }
                            break;

                        case 6:
                            running = false;
                            System.out.println("Exiting the application. Goodbye!");
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}