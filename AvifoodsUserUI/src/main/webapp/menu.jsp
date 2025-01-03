<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
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

        .nav-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            margin-left: 15px;
            font-weight: 500;
        }

        .container {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
            margin-top: 80px; 
        }

        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
        }

        .menu-item {
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            text-align: center;
            padding: 20px;
            transition: transform 0.3s ease;
        }

        .menu-item:hover {
            transform: scale(1.05);
        }

        .menu-item img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-bottom: 1px solid #eee;
        }

        .menu-item h3 {
            margin: 10px 0;
            font-size: 20px;
            color: #333;
        }

        .menu-item p {
            font-size: 14px;
            color: #666;
            margin: 10px 0;
        }

        .order-btn {
            background: linear-gradient(45deg, #FF6B6B, #FF8E8E);
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin-top: 20px;
            cursor: pointer;
            font-weight: 500;
            transition: all 0.3s ease;
            text-decoration: none;
            display: block;
        }

        .order-btn:hover {
            background: linear-gradient(45deg, #FF8E8E, #FF6B6B);
        }

        .cart-icon {
            position: relative;
            font-size: 24px;
            font-family: 'Font Awesome 5 Free';
            color: white;
        }

        .cart-icon::before {
            content: "\f07a"; 
            font-family: 'Font Awesome 5 Free';
            font-weight: 900;
        }

        #cart-count {
            background: #FF6B6B;
            color: white;
            font-size: 12px;
            font-weight: bold;
            border-radius: 50%;
            padding: 2px 6px;
            position: absolute;
            top: -5px;
            right: -10px;
            border: 2px solid white;
        }

        .add-remove-buttons {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 15px;
            margin-top: 15px;
        }

        .add-remove-buttons button {
            background-color: #FF6B6B;
            color: white;
            font-size: 16px;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .add-remove-buttons button:hover {
            background-color: #FF8E8E;
        }

        .add-remove-buttons button:active {
            background-color: #FF4B4B;
        }

        .add-remove-buttons button:focus {
            outline: none;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="nav-bar">
            <div class="logo">AviFoods</div>
            <div class="nav-links">
                <a href="LOGIN.html">Logout</a>
                <a href="cart.jsp" class="cart-icon">
                    <span id="cart-count"><%= session.getAttribute("cartCount") != null ? session.getAttribute("cartCount") : 0 %></span>
                </a>
            </div>
        </div>
    </div>

    <div class="container">
        <h1>Our Menu</h1>
        <div class="menu-grid">
            <%
                List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
                if (menuList != null) {
                    for (Menu menu : menuList) {
            %>
            <div class="menu-item">
                <img src="<%= menu.getImagePath() %>" alt="<%= menu.getName() %>">
                <h3><%= menu.getName() %></h3>
                <p><%= menu.getDescription() %></p>
                <p><strong>Price: <%= menu.getPrice() %> rs.</strong></p>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                    <div class="add-remove-buttons">
                        <button type="submit" name="action" value="add">+</button>
                        <span>Add to Cart</span>
                        <button type="submit" name="action" value="remove">-</button>
                    </div>
                </form>
            </div>
            <%
                    }
                } else {
            %>
            <p>No menu items available at the moment.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
