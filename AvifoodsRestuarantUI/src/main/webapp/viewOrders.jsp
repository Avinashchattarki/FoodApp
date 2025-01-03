<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.OrderItems" %>
<%@ page import="com.foodapp.daoimpl.OrderDAOimpl" %>
<%@ page import="com.foodapp.model.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Orders</title>
    <link rel="stylesheet" href="styles.css">
    <style>
    .accept-btn {
        background-color: #4CAF50;
        color: white;
        font-size: 16px;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        transition: background-color 0.3s ease, box-shadow 0.3s ease;
    }
    .accept-btn:hover {
        background-color: #45a049;
        box-shadow: 0 6px 8px rgba(0, 0, 0, 0.3);
    }
    .accept-btn:active {
        background-color: #3e8e41;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        transform: translateY(2px);
    }
</style>
    
</head>
<body>
    <header>
        <div class="nav-bar">
            <div class="logo">AviFoods</div>
        </div>
    </header>
    <div class="container">
        <h1>Order Items</h1>
        <table class="styled-table">
            <thead>
                <tr>
                    <th>Order Item ID</th>
                    <th>Order ID</th>
                    <th>Menu ID</th>
                    <th>Quantity</th>
                    <th>Item Total</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    OrderDAOimpl orderDao = new OrderDAOimpl();
                    List<OrderItems> orderItems = (List<OrderItems>) request.getAttribute("orderItems");

                    if (orderItems != null && !orderItems.isEmpty()) {
                        for (OrderItems item : orderItems) {
                            // Debug: Check if the order is being fetched properly
                            Order order = orderDao.searchOrderById(item.getOrderId());
                %>
                <tr>
                    <td><%= item.getOrderitemid() %></td>
                    <td><%= item.getOrderId() %></td>
                    <td><%= item.getMenuid() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>$<%= item.getItemtotal() %></td>
                    <td>
                        <% if (order != null && "Preparing Your Order".equals(order.getStatus())) { %>
                            <span class="accepted">Accepted</span>
                        <% } else { %>
                            <form action="acceptOrder" method="post">
                                <input type="hidden" name="orderId" value="<%= item.getOrderId() %>">
                                <button type="submit" class="accept-btn">Accept Order</button>
                            </form>
                        <% } %> 
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No order items found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
