<%@ page import="com.foodapp.model.Order" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Orders</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            padding: 20px;
            margin: 0; 
        }

        .navbar {
            background-color: #FF6B6B; 
            padding: 10px 20px; 
            text-align: left;
            margin-top:-20px;
            width:100%;
            margin-left : -20px;
        }

        .navbar h2 {
            color: white;
            margin: 0; 
            padding-left: 80px;
        }

        .button {
            background-color: #FF8E8E; 
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }

        .button:hover {
            background-color: #FF6B6B; 
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #f4f4f4;
        }

        table tr:hover {
            background-color: #f9f9f9;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            text-align: center; 
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>AviFoods</h2>
    </div>
    <div class="container">
        <h1>Your Orders</h1>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            if (orders != null && !orders.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Status</th>
                        <th>Payment Mode</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Order order : orders) {
                    %>
                    <tr>
                        <td><%= order.getOrderId() %></td>
                        <td><%= order.getOrderdate() %></td>
                        <td><%= order.getTotalamount() %></td>
                        <td><%= order.getStatus() %></td>
                        <td><%= order.getPaymentmode() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <p>No orders found.</p>
        <%
            }
        %>
        <a href="RestaurantServlet" class="button">Go to Home</a> 
    </div>
</body>
</html>