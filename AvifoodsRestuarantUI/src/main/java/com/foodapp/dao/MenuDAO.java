package com.foodapp.dao;

import java.util.List;


import com.foodapp.model.Menu;

//DAO Interface
public interface MenuDAO {
 // Add a new menu item
 void addMenu(Menu menu);

 // Update an existing menu item
 void updateMenu(Menu menu);

 // Delete a menu item by its ID
 void deleteMenu(int menuId);

 // Retrieve a menu item by its ID
 public  Menu getMenuById(int menuId);

 // Retrieve all menu items
 List<Menu> getAllMenus();

 List<Menu> getMenuByRestaurantId(int restaurantId); // Add this line
}