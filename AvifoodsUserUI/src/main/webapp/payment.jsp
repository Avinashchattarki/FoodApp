<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #FF6B6B, #FFE66D);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            background: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 500px;
            animation: fadeIn 1s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h1 {
            text-align: center;
            color: #FF6B6B;
            font-size: 2.5em;
            margin-bottom: 30px;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
        }

        input, textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 1em;
            transition: all 0.3s ease;
        }

        input:focus, textarea:focus {
            border-color: #FF6B6B;
            box-shadow: 0 0 10px rgba(255, 107, 107, 0.2);
            outline: none;
        }

        .payment-option {
            padding: 20px;
            border: 1px solid #ddd;
            margin: 10px 0;
            text-align: center;
            cursor: pointer;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s, transform 0.3s;
            width: 100%;
        }

        .payment-option:hover {
            background-color: #f0f0f0;
            transform: translateY(-2px);
        }

        input[type="radio"] {
            display: none; /* Hide the default radio button */
        }

        input[type="radio"] + label {
            display: flex;
            align-items: center;
            cursor: pointer;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 10px;
            justify-content: center;
            margin: 10px 0;
            transition: background-color 0.3s, transform 0.3s;
        }

        input[type="radio"]:checked + label {
            background-color: #FF6B6B;
            color: white;
            border: 1px solid #FF6B6B;
            transform: scale(1.05);
        }

        button {
            width: 100%;
            padding: 15px;
            background: linear-gradient(45deg, #FF6B6B, #FF8E8E);
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 1.1em;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        button:hover {
            background: linear-gradient(45deg, #FF8E8E, #FF6B6B);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(255, 107, 107, 0.3);
        }

        .error-message {
            color: #FF6B6B; 
            text-align: center; 
            margin-top: 20px; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Confirm Your Order</h1>

        <form action="OrderConfirmationServlet" method="post">
            <!-- Address Confirmation Section -->
            <div class="form-group">
                <label for="street">Street Address:</label>
                <input type="text" name="street" id="street" placeholder="Enter your street address..." required>
            </div>

            <div class="form-group">
                <label for="city">City:</label>
                <input type="text" name="city" id="city" placeholder="Enter your city..." required>
            </div>

            <div class="form-group">
                <label for="state">State:</label>
                <input type="text" name="state" id="state" placeholder="Enter your state..." required>
            </div>

            <div class="form-group">
                <label for="postalCode">Postal Code:</label>
                <input type="text" name="postalCode" id="postalCode" placeholder="Enter your postal code..." required>
            </div>

            <div class="form-group">
                <label for="address">Additional Delivery Instructions:</label>
                <textarea name="address" id="address" rows="4" placeholder="Enter any additional instructions..." required></textarea>
            </div>

            <!-- Payment Options Section -->
            <h2>Select Payment Method</h2>
            <div class="form-group">
                <input type="radio" id="upiPayment" name="paymentMethod" value="UPI" required>
                <label for="upiPayment" class="payment-option">
                    <h3>Pay with UPI</h3>
                    <p>Scan the QR code or use UPI ID</p>
                </label>

                <input type="radio" id="codPayment" name="paymentMethod" value="COD" required>
                <label for="codPayment" class="payment-option">
                    <h3>Cash on Delivery (COD)</h3>
                    <p>Pay with cash when your order is delivered</p>
                </label>
            </div>

            <button type="submit">Confirm Order</button>
        </form>

        <% 
            // Display error message if set by the servlet
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <p class="error-message"><%= error %></p>
        <% } %>
    </div>
</body>
</html>