<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurants</title>
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

        .main-content {
            margin-top: 80px;
            padding: 20px;
            max-width: 1200px;
            margin-left: auto;
            margin-right: auto;
        }

        .slideshow-container {
            position: relative;
            max-width: 1200px;
            margin: 20px auto;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .slides {
            display: none;
            width: 100%;
        }

        .slides img {
            width: 100%;
            height: 400px;
            object-fit: cover;
        }

        .banner-text {
            position: absolute;
            bottom: 20px;
            left: 20px;
            color: white;
            font-size: 1.5em;
            background: rgba(0, 0, 0, 0.5);
            padding: 10px 20px;
            border-radius: 5px;
        }

        /* Search Bar */
        .search-container {
            margin: 20px auto;
            text-align: center;
        }

        .search-bar {
            padding: 10px;
            width: 80%;
            max-width: 600px;
            border: 2px solid #FF6B6B;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }

        .search-bar:focus {
            border-color: #FF8E8E;
            outline: none;
        }

        /* Main Content */
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 30px;
            padding: 20px 0;
        }

        .food-card {
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }

        .food-card:hover {
            transform: translateY(-5px);
        }

        .food-img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .food-info {
            padding: 20px;
        }

        .food-info h3 {
            color: #333;
            margin-bottom: 10px;
        }

        .food-info p {
            color: #666;
            font-size: 0.9em;
            margin-bottom: 15px;
        }

        .price {
            color: #FF6B6B;
            font-size: 1.2em;
            font-weight: 600;
            margin-bottom: 15px;
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

        @media (max-width: 768px) {
            .navbar {
                padding: 15px;
            }

            .menu-grid {
                grid-template-columns: 1fr;
            }
        }

        /* Footer Styles */
        .footer {
            background: linear-gradient(135deg, #FF6B6B, #FF8E8E);
            color: white;
            text-align: center;
            padding: 20px 0;
            position: relative;
            bottom: 0;
            width: 100%;
            margin-top: 20px;
        }

        .footer p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="nav-content">
            <a href="#" class="logo">AviFoods</a>
            <div class="nav-links">
                <a href="LOGIN.html">Logout</a>
                <a href="cart.jsp" class="cart-icon">
                    <span id="cart-count"><%= session.getAttribute("cartCount") != null ? session.getAttribute("cartCount") : 0 %></span>
                </a>
                <a href="TrackOrdersServlet">Track Your Orders</a>
            </div>
        </div>
    </div>

    <div class="main-content">
        <div class="slideshow-container">
            <div class="slides">
                <img src="https://sahibsbiryani.com/cdn/shop/files/Banner_Sahibs_100424_v102.png?v=1712744645&width=1600" alt="Biryani">
                <div class="banner-text">Welcome to AviFoods</div>
            </div>
            <div class="slides">
                <img src="https://img.freepik.com/premium-photo/pepperoni-pizza-black-background-hot-pepperoni-pizza-top-view-banner-generative-ai_446633-8397.jpg" alt="Pizza">
                <div class="banner-text">Delicious Pizzas Await!</div>
            </div>
            <div class="slides">
                <img src="https://images.pond5.com/chocolate-chip-cookies-rotation-4k-footage-083053206_iconl.jpeg" alt="Desserts">
                <div class="banner-text">Sweet Treats for Every Occasion</div>
            </div>
        </div>

        <!-- Search Bar -->
        <div class="search-container">
            <input type="text" id="searchInput" class="search-bar" placeholder="Search for restaurants..." onkeyup="filterRestaurants()">
        </div>

        <div class="menu-grid" id="restaurantGrid">
            <%
                List<Restaurant> restaurants = 
                    (List<Restaurant>) request.getAttribute("restaurantList");

                if (restaurants != null) {
                    for (com.foodapp.model.Restaurant restaurant : restaurants) {
            %>
                        <div class="food-card">
                            <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>" class="food-img">
                            <div class="food-info">
                                <h3 class="restaurant-name"><%= restaurant.getName() %></h3>
                                <p>Cuisine: <%= restaurant.getCuisineType() %></p>
                                <p>Delivery Time: <%= restaurant.getDeliveryTime() %> mins</p>
                                <p>Address: <%= restaurant.getAddress() %></p>
                                <p>Rating: <%= restaurant.getRatings() %> / 5.0</p>
                                <p class="price"><%= restaurant.isActive() ? "Open Now" : "Closed" %></p>
                                <a href="viewMenu?restaurantId=<%= restaurant.getRestaurantId() %>" class="order-btn">View Menu</a>
                                <form action="CartServlet" method="POST">
                                    <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
                                </form>
                            </div>
                        </div>
            <%
                    }
                } else {
            %>
                    <p>No restaurants available to display.</p>
            <%
                }
            %>
        </div>

        <div id="noResults" style="display: none; text-align: center; margin-top: 20px;">
            <p>No restaurants found matching your search.</p>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2023 AviFoods. All rights reserved.</p>
        <p>Follow us on social media for updates!</p>
    </div>

    <script>
        let slideIndex = 0;
        const slides = document.querySelectorAll('.slides');

        function showSlides() {
            slides.forEach((slide, index) => {
                slide.style.display = index === slideIndex ? 'block' : 'none';
            });
            slideIndex = (slideIndex + 1) % slides.length;
        }

        showSlides();
        setInterval(showSlides, 3000);

        function filterRestaurants() {
            const input = document.getElementById('searchInput');
            const filter = input.value.toLowerCase();
            const cards = document.querySelectorAll('.food-card');
            let hasResults = false;

            cards.forEach(card => {
                const restaurantName = card.querySelector('.restaurant-name').textContent.toLowerCase();
                if (restaurantName.includes(filter)) {
                    card.style.display = '';
                    hasResults = true;
                } else {
                    card.style.display = 'none';
                }
            });

            document.getElementById('noResults').style.display = hasResults ? 'none' : 'block';
        }
    </script>
</body>
</html>