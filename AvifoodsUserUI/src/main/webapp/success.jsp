<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #4CAF50, #8BC34A);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            display: flex;
            flex-direction: column; /* Stack items vertically */
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 700px;
            text-align: center;
            padding: 40px;
            animation: fadeIn 1s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h1 {
            font-size: 2.5em;
            color: #4CAF50;
            margin-bottom: 20px;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        p {
            font-size: 1.2em;
            color: #333;
            margin-bottom: 30px;
        }

        .success-icon {
            font-size: 4em;
            color: #4CAF50;
            margin-bottom: 20px;
        }

        button {
            width: 100%;
            padding: 15px;
            background: linear-gradient(45deg, #4CAF50, #81C784);
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
            background: linear-gradient(45deg, #81C784, #4CAF50);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(76, 175, 80, 0.3);
        }
    </style>
</head>
<body>
    <div class="container">
        <div>
            <div class="success-icon">&#x2714;</div> <!-- Checkmark symbol for success -->
            <h1>Order Successful</h1>
            <p>Thank you for choosing us! Your order has been confirmed.</p>
            <button onclick="window.location.href='RestaurantServlet'">Back to Home</button>
        </div>
    </div>
</body>
</html>