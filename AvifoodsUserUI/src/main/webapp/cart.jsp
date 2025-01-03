<%@ page import="java.util.Map" %>
<%@ page import="com.foodapp.model.Menu" %>
<%@ page import="com.foodapp.daoimpl.MenuDAOImpl" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.foodapp.util.DatabaseUtil" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
         * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        .navbar {
            background: linear-gradient(135deg, #FF6B6B, #FF8E8E);
            padding: 15px 30px;
            position: fixed;
            width: 100%;
            top: 0;
            z-index: 1000;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .logo {
            color: white;
            font-size: 24px;
            font-weight: 600;
            text-decoration: none;
        }

        h1 {
            text-align: center;
            margin: 100px auto 20px;
            font-size: 36px;
            color: #FF6B6B;
            font-weight: bold;
            text-transform: uppercase;
            padding: 10px;
            background-color: #f8f9fa;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border: 2px solid #FF6B6B;
            width: fit-content;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: left;
        }

        th, td {
            padding: 15px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #FF8E8E;
            color: white;
        }

        td {
            background-color: #f9f9f9;
        }

        .total-row {
            font-weight: bold;
            background-color: #FFEBE8;
        }

        .empty-cart {
            text-align: center;
            margin: 150px auto;
            font-size: 18px;
            color: #777;
        }

        .btn-container {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin: 20px 0;
        }

        .btn {
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            transition: background 0.3s ease;
        }

        .clear-btn {
            background: #FF6B6B;
        }

        .clear-btn:hover {
            background: #e05b5b;
        }

        .order-btn {
            background: #4CAF50;
        }

        .order-btn:hover {
            background: #45a049;
        }

        .quantity-btn {
            padding: 4px 10px;
            background-color: #FF6B6B;
            border: none;
            color: white;
            cursor: pointer;
            font-size: 18px;
            transition: background 0.3s ease;
            width: 25px;
            height: 25px;
            display: inline-flex;
            justify-content: center;
            align-items: center;
        }

        .quantity-btn:hover {
            background-color: #e05b5b;
        }

        .quantity-btn:focus {
            outline: none;
        }

        .quantity-btn:disabled {
            background-color: #ddd;
            cursor: not-allowed;
        }
        
    </style>
</head>
<body>
    <div class="navbar">
        <div class="logo">AviFoods</div>
    </div>

    <h1>Your Cart</h1>
    <%
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart != null && !cart.isEmpty()) {
            double total = 0;
    %>
    <form action="CartServlet" method="post">
        <table>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <%
                Connection connection = null;
                try {
                    connection = DatabaseUtil.getConnection();
                    MenuDAOImpl menuDAO = new MenuDAOImpl(connection);

                    // Iterate through the cart items and fetch menu details
                    for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                        String menuId = entry.getKey();
                        int id = Integer.parseInt(menuId);
                        int quantity = entry.getValue();

                        // Fetching the menu item by ID
                        Menu item = menuDAO.getMenuById(id);
                        if (item != null) {
                            total += item.getPrice() * quantity;
            %>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td>
                    <!-- Decrease and Increase buttons for quantity -->
                    <form action="CartServlet" method="post" style="display: inline;">
                        <input type="hidden" name="menuId" value="<%= menuId %>">
                        <button type="submit" name="action" value="remove" class="quantity-btn">-</button>
                    </form>
                    <%= quantity %>
                    <form action="CartServlet" method="post" style="display: inline;">
                        <input type="hidden" name="menuId" value="<%= menuId %>">
                        <button type="submit" name="action" value="add" class="quantity-btn">+</button>
                    </form>
                </td>
                <td><%= item.getPrice() * quantity %> rs.</td>
            </tr>
            <%
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            %>
            <tr class="total-row">
                <td colspan="3">Total</td>
                <td><%= total %> rs.</td>
            </tr>
        </table>
        <div class="btn-container">
            <button type="submit" class="btn clear-btn" name="action" value="clear">Clear Cart</button>
            <button type="submit" class="btn clear-btn" name="action" value="addmore">Add More items</button>
            <button type="submit" class="btn order-btn" name="action" value="order">Order Now</button>
        </div>
    </form>
    <%
        } else {
    %>
    <p class="empty-cart">
    Your cart is empty. 
    <a href="<%= request.getSession().getAttribute("restaurantId") != null ? "viewMenu?restaurantId=" + request.getSession().getAttribute("restaurantId") : "viewMenu?restaurantId=1" %>" 
       style="color: #FF6B6B; text-decoration: none;">
        Browse Menu
    </a>
</p>
    
    <%
        }
    %>
</body>
</html>
