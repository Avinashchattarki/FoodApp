<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Avi foods Resturant Management</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }
        .modal.show {
            display: flex;
        }
        .modal-content {
            background: white;
            padding: 20px;
            border-radius: 5px;
            width: 80%;
            max-width: 500px;
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar">
            <div class="nav-bar">
                <h1 class="logo">Avi foods Resturant Management</h1>
                <ul class="nav-links">
                    <li><a href="MenuManagementServlet">menu</a></li>
                    <li><a href="ViewOrdersServlet">orders</a></li>
                    <li><a href="logout">Logout</a></li>
                </ul>
            </div>
        </nav>
    </header>

    <div class="container">
        <div class="button-group">
            <a href="#" class="order-btn add-btn" onclick="openAddMenuForm()">Add Menu Item</a>
            <a href="MenuManagementServlet" class="order-btn display-btn">Display All Items</a>
        </div>

        <h2 style="text-align: center; margin:45px;">Menu Items</h2>
        <div class="menu-grid">
            <% List<Menu> menuList = (List<Menu>) request.getAttribute("menuList"); %>
            <% if (menuList != null && !menuList.isEmpty()) { %>
                <% for (Menu menu : menuList) { %>
                <div class="menu-item">
				    <img src="<%= menu.getImagePath() %>" alt="<%= menu.getName() %>" class="menu-image">
				    <h3><%= menu.getName() %></h3>
				    <p><%= menu.getDescription() %></p>
				    <p>Price: $<%= menu.getPrice() %></p>
				    <p>Available: <%= menu.isAvailable() ? "Yes" : "No" %></p>
				    
				    <!-- Edit Button -->
				    <button class="edit-btn" 
				            onclick="editMenu(<%= menu.getMenuId() %>, 
				                              '<%= menu.getName() %>', 
				                              '<%= menu.getDescription() %>', 
				                              <%= menu.getPrice() %>, 
				                              <%= menu.isAvailable() ? 1 : 0 %>, 
				                              '<%= menu.getImagePath() %>')">
				        Edit
				    </button>
				    
				    <!-- Delete Button -->
				    <form action="DeleteMenuServlet" method="POST" style="display: inline;">
				        <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
				        <button type="submit" class="delete-btn" 
				                onclick="return confirm('Are you sure you want to delete this item?');">
				            Delete
				        </button>
				    </form>
				</div>
                <% } %>
            <% } else { %>
            <div class="menu-item">
                <p>No menu items found.</p>
            </div>
            <% } %>
        </div>
    </div>


    <div id="addMenuForm" class="modal">
        <div class="modal-content">
            <h2 id="formTitle">Add Menu Item</h2>
            <form id="menuForm" action="AddMenuServlet" method="post" onsubmit="return validateForm()">
                <input type="hidden" name="menuId" id="menuId">

                <label for="name">Name:</label>
                <input type="text" name="name" id="name" required>

                <label for="description">Description:</label>
                <textarea name="description" id="description" required></textarea>

                <label for="price">Price:</label>
                <input type="number" name="price" id="price" required>

                <label for="isAvailable">Is Available:</label>
                <select name="isAvailable" id="isAvailable">
                    <option value="1">Yes</option>
                    <option value="0">No</option>
                </select>

                <label for="imagePath">Image Path:</label>
                <input type="text" name="imagePath" id="imagePath" required>

                <button type="submit" name="action" value="add" class="submit-btn">Save Changes</button>
                <button type="button" class="cancel-btn" onclick="closeAddMenuForm()">Cancel</button>
            </form>
        </div>
    </div>

    <script>

    function openAddMenuForm() {
        document.getElementById('formTitle').innerText = "Add Menu Item";
        document.getElementById('menuForm').reset();  
        document.getElementById('menuId').value = ""; 
        document.getElementById('menuForm').action = "AddMenuServlet"; 
        document.getElementById('addMenuForm').classList.add('show');
    }


    function editMenu(menuId, name, description, price, isAvailable, imagePath) {
        document.getElementById('formTitle').innerText = "Edit Menu Item";
        document.getElementById('menuId').value = menuId;
        document.getElementById('name').value = name;
        document.getElementById('description').value = description;
        document.getElementById('price').value = price;
        document.getElementById('isAvailable').value = isAvailable;
        document.getElementById('imagePath').value = imagePath;
        document.getElementById('menuForm').action = "EditServlet"; // Set form action for edit
        document.getElementById('addMenuForm').classList.add('show');
    }


    function closeAddMenuForm() {
        document.getElementById('addMenuForm').classList.remove('show');
    }


    function validateForm() {
        const name = document.getElementById('name').value.trim();
        const description = document.getElementById('description').value.trim();
        const price = document.getElementById('price').value;
        const imagePath = document.getElementById('imagePath').value.trim();

        if (!name || !description || !price || !imagePath) {
            alert("All fields are required.");
            return false;
        }

        if (price <= 0) {
            alert("Price must be greater than zero.");
            return false;
        }

        return true;
    }
    </script>
</body>
</html>
