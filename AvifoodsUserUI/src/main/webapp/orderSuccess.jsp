<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.User" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background: linear-gradient(135deg, #FF6B6B, #FF8E8E);
            padding: 15px 30px;
            position: fixed;
            width: 100%;
            top: 0;
            z-index: 1000;
            box-shadow: 0 2px 10px rgba(0,0,0,0.2);
        }
        .nav-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 1200px;
            margin: 0 auto;
        }
        .logo {
            color: white;
            font-size: 24px;
            font-weight: 600;
            text-decoration: none;
        }
        .nav-links {
            display: flex;
            gap: 30px;
        }
        .nav-links a {
            color: white;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        .nav-links a:hover {
            color: #FFE66D;
        }
        .container {
            max-width: 800px;
            margin: 100px auto; /* Adjusted for fixed navbar */
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #FF6B6B;
            margin-bottom: 20px;
        }
        .order-id {
            font-size: 20px;
            font-weight: bold;
            margin: 20px 0;
        }
        .home-button {
            background: linear-gradient(45deg, #FF6B6B, #FF8E8E);
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 500;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }
        .home-button:hover {
            background: linear-gradient(45deg, #FF8E8E, #FF6B6B);
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="nav-content">
            <a href="#" class="logo">AviFoods</a>
            <div class="nav-links">
                <a href="logout">Logout</a>
                <a href="home.jsp">Home</a> <!-- Link to home page -->
            </div>
        </div>
    </div>

    <div class="container">
        <h1>Order Placed Successfully!</h1>
        <p class="order-id">Your Order ID is: <%= session.getAttribute("orderId") %></p>
        <p>Thank you for your order! We will process it shortly.</p>
        <a href="RestaurantServlet" class="home-button">Order More items</a> <!-- Button to go back to home -->
    </div>
</body>
</html>