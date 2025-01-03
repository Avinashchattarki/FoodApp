<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.*" %>
<%@ page import="com.foodapp.daoimpl.*" %>

<%
    List<Delivery> deliveries = (List<Delivery>) request.getAttribute("deliveries");
    OrderDAOimpl odao = new OrderDAOimpl();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delivery Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
        }

        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #007bff;
            padding: 1rem 2rem;
            color: white;
        }

        .navbar h1 {
            margin: 0;
            font-size: 1.5rem;
        }

        .navbar .logout-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 0.5rem 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .navbar .logout-btn:hover {
            background-color: #c82333;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: 2rem auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            padding: 2rem;
        }

        h2 {
            text-align: center;
            margin-bottom: 2rem;
            color: #333;
            font-size: 2rem;
        }

        .delivery-list {
            display: flex;
            flex-wrap: wrap;
            gap: 1.5rem;
            justify-content: center;
        }

        .delivery-card {
            background-color: #ffffff;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 300px;
            padding: 1.5rem;
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
            transition: transform 0.2s;
        }

        .delivery-card:hover {
            transform: translateY(-5px);
        }

        .delivery-card p {
            margin: 0;
            font-size: 0.95rem;
            color: #555;
        }

        .delivery-card strong {
            color: #333;
        }

        .actions {
            display: flex;
            justify-content: space-between;
            margin-top: 1rem;
        }

        .btn {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 4px;
            font-size: 0.9rem;
            cursor: pointer;
            text-transform: uppercase;
            transition: background-color 0.3s;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-success {
            background-color: #28a745;
            color: white;
        }

        .btn-success:hover {
            background-color: #218838;
        }

        .no-data {
            text-align: center;
            color: #888;
            font-size: 1.2rem;
            margin-top: 2rem;
        }

        .btn-success-completed {
            background-color: #6c757d;
            color: white;
            cursor: not-allowed; 
        }

        button:disabled {
            background-color: #6c757d;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>Avifoods</h1>
        <button class="logout-btn" onclick="window.location.href='LogoutServlet'">Logout</button>
    </div>

    <div class="container">
        <h2>Delivery Dashboard</h2>

        <div class="delivery-list">
            <%
                if (deliveries != null && !deliveries.isEmpty()) {
                    for (Delivery delivery : deliveries) {
                        Order od = odao.searchOrdersByOrderId(delivery.getOrderId());
                        String Status = od != null ? od.getStatus() : "Not Found";
            %>
            <div class="delivery-card">
                <p><strong>Order ID:</strong> <%= delivery.getOrderId() %></p>
                <p><strong>Address:</strong> <%= delivery.getAddress() %></p>
                <p><strong>Street Name:</strong> <%= delivery.getStreetName() %></p>
                <p><strong>Pin Code:</strong> <%= delivery.getPincode() %></p>
                <p><strong>Phone:</strong> <%= delivery.getPhone() %></p>
                <p><strong>Delivery Instructions:</strong> <%= delivery.getDeliveryInstructions() %></p>
                <p><strong>Payment Method:</strong> <%= delivery.getPaymentMethod() %></p>
                <p><strong>Price:</strong> ₹<%= delivery.getPrice() %></p>
                <p><strong>Restaurant name: </strong><%= delivery.getResturantname() %></p>
                <p><strong>Restaurant Address: </strong><%= delivery.getAddressResturant() %></p>

                <div class="actions">
                    <% 
                        if (!Status.startsWith("Assigned")) {
                    %>
                    <form method="post" action="DeliveryStatusServlet" style="margin: 0;">
                        <input type="hidden" name="orderId" value="<%= delivery.getOrderId() %>">
                        <input type="hidden" name="status" value="accepted">
                        <button type="submit" class="btn btn-primary">Accept</button>
                    </form>
                    <% 
                        } else { 
                    %>
                    <button class="btn btn-primary" disabled>Accepted</button>
                    <% 
                        } 
                    %>

                    <form method="post" action="DeliveryStatusServlet" style="margin: 0;">
                        <input type="hidden" name="orderId" value="<%= delivery.getOrderId() %>">
                        <input type="hidden" name="status" value="delivered">
                        <button type="submit" class="btn btn-success <% if ("Delieverd".equals(Status)) { %>btn-success-completed<% } %>"
                                <% if ("Delieverd".equals(Status)) { %> disabled <% } %> >
                            <% if ("Delieverd".equals(Status)) { %> ✓ Delivered <% } else { %> Delivered <% } %>
                        </button>
                    </form>
                </div>
            </div>
            <% 
                    }
                } else {
            %>
            <p class="no-data">No deliveries found.</p>
            <% 
                }
            %>
        </div>
    </div>
    <script>
        function changeButtonAppearance(form) {
            var button = form.querySelector('button');
            button.innerHTML = "✓ Delivered";
            button.classList.remove('btn-success');
            button.classList.add('btn-success-completed');
            button.disabled = true;
        }
    </script>
</body>
</html>