<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.foodapp.model.Menu" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.*" %>
<%@ page import="jakarta.servlet.http.*" %>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="com.foodapp.daoimpl.MenuDAOImpl"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background-color: #f4f4f4;
            color: #333;
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
            max-width: 1200px;
            margin: auto;
            padding: 20px;
            margin-top: 80px;
        }

        .order-summary {
            margin-bottom: 40px;
        }

        .order-items {
            list-style-type: none;
            padding: 0;
            margin-bottom: 20px;
        }

        .order-items li {
            margin: 5px 0;
            padding: 10px;
            background: #f9f9f9;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            font-weight: 500;
        }

        .address-section, .payment-section {
            width: 100%;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            background-color: white;
            transition: transform 0.3s;
            margin-bottom: 20px;
        }

        .address-section:hover, .payment-section:hover {
            transform: translateY(-5px);
        }

        h1, h2 {
            font-weight: 600;
            color: #FF6B6B;
            margin-bottom: 15px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
            color: #555;
        }

        .form-group input, .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s;
        }

        .form-group input:focus, .form-group textarea:focus {
            border-color: #FF6B6B;
            outline: none;
        }

        .order-btn {
            background: linear-gradient(45deg, #FF6B6B, #FF8E8E);
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 500;
            transition: all 0.3s ease;
            display: block;
            margin-top: 20px;
            text-align: center;
            width: 100%;
            font-size: 16px;
        }

        .order-btn:hover {
            background: linear-gradient(45deg, #FF8E8E, #FF6B6B);
            transform: scale(1.05);
        }

        .payment-option {
            margin-bottom: 15px;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: background-color 0.3s, border-color 0.3s;
            position: relative;
            cursor: pointer;
            background-color: #f9f9f9;
        }

        .payment-option:hover {
            background-color: #f0f0f0;
            border-color: #FF6B6B;
        }

        .payment-option.selected {
            background-color: #FF6B6B;
            color: white;
            border-color: #FF6B6B;
        }

        .upi-section {
            margin-top: 10px;
            text-align: center;
        }

        .upi-qr {
            width: 200px;
            height: 200px;
            margin-top: 10px;
            border-radius: 10px;
        }

        .upi-id {
            font-weight: bold;
            color: #333;
        }

        .total-amount {
            font-weight: bold;
            font-size: 18px;
            margin-top: 10px;
            color: #FF6B6B;
            background-color: #ffe6e6;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
        }

        /* Modal Styles */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
            z-index: 1001;
        }

        .modal-content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }

        .modal-content h3 {
            margin-bottom: 15px;
        }

        .modal-content .total-amount {
            margin-bottom: 15px;
        }

        .modal-btn {
            background-color: #FF6B6B;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .modal-btn:hover {
            background-color: #FF8E8E;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="nav-content">
            <a href="#" class="logo">AviFoods</a>
            <div class="nav-links">
                <a href="logout">Logout</a>
            </div>
        </div>
    </div>

    <div class="container">
        <!-- Display Order Items on Top -->
        <div class="order-summary">
            <h1>Order Summary</h1>
            <ul class="order-items">
                <% 
                    HttpSession s = request.getSession();
                    Connection connection = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/foodapplication", "root", "Avi@143563");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    MenuDAOImpl Menu = new MenuDAOImpl(connection);
                    Map<String, Integer> cart = (Map<String, Integer>) s.getAttribute("cart");
                    double totalAmount = 0.0;
                    if (cart != null && !cart.isEmpty()) {
                        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                            String menuId = entry.getKey();
                            int quantity = entry.getValue();
                            int id = Integer.parseInt(menuId);
                            Menu menuItem = Menu.getMenuById(id); 
                            double itemPrice = menuItem.getPrice();
                            String itemName = menuItem.getName();
                            totalAmount += itemPrice * quantity;
                %>
                    <li><%= itemName %> - <%= quantity %> = ₹<%= itemPrice * quantity %></li>
                <% 
                        }
                    } else {
                %>
                    <li>No items in the cart</li>
                <% 
                    }
                %>
            </ul>
            <div class="total-amount">
                Total: ₹<%= totalAmount %>
            </div>
        </div>

        <!-- Address Section -->
        <div class="address-section">
            <h1>Confirm Your Order</h1>
            <form action="OrderConfirmationServlet" method="post">
                <div class="form-group">
                    <label for="address">Enter Delivery Address</label>
                    <textarea name="address" id="address" rows="4" required></textarea>
                </div>
                <div class="form-group">
                    <label for="street">Street Name</label>
                    <input type="text" name="street" id="street" required />
                </div>
                <div class="form-group">
                    <label for="phone">Enter Your Phone Number</label>
                    <input type="text" name="phone" id="phone" required />
                </div>
                <div class="form-group">
                    <label for="pincode">Enter Pincode</label>
                    <input type="text" name="pincode" id="pincode" required />
                </div>
                <div class="form-group">
                    <label for="delivery-instructions">Delivery Instructions</label>
                    <textarea name="delivery_instructions" id="delivery-instructions" rows="4"></textarea>
                </div>
                
                <!-- Payment Section -->
                <div class="payment-section">
                    <h2>Choose Payment Method</h2>
                    <div class="payment-option" onclick="selectPaymentMethod('Cash On Delivery')">
                        <input type="radio" name="paymentMethod" value="COD" id="cod"> Cash On Delivery
                    </div>
                    <div class="payment-option" onclick="selectPaymentMethod('UPI')">
                        <input type="radio" name="paymentMethod" value="UPI" id="upi"> UPI Payment
                    </div>
                    
                    <!-- UPI Modal -->
                    <div class="modal" id="upiModal">
                        <div class="modal-content">
                            <h3>Complete Your Payment</h3>
                            <div class="total-amount">
                                Total: ₹<%= totalAmount %>
                            </div>
                            <div class="upi-section">
                                <img src="payment.jpg" alt="QR Code" class="upi-qr">
                                <div class="upi-id">8861177014@ybl</div>
                            </div>
                            <button type="button" class="modal-btn" onclick="closeUPIModal()">Close</button>
                        </div>
                    </div>
                    
                    <button type="submit" class="order-btn">Confirm Order</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function selectPaymentMethod(paymentMethod) {
            const upiModal = document.getElementById('upiModal');
            if (paymentMethod === 'UPI') {
                upiModal.style.display = 'flex';
            } else {
                upiModal.style.display = 'none';
            }
        }

        function closeUPIModal() {
            document.getElementById('upiModal').style.display = 'none';
        }
    </script>
</body>
</html>
