package com.foodapp.dao;

import java.util.List;




import com.foodapp.model.Menu;

//DAO Interface
public interface MenuDAO {
 // Add a new menu item
 void addMenu(Menu menu);//create

 // Update an existing menu item
 void updateMenu(Menu menu);//updating

 // Delete a menu item by its ID
 void deleteMenu(int menuId);//delete

 // Retrieve a menu item by its ID
 public  Menu getMenuById(int menuId);//reading by id

 // Retrieve all menu items
 List<Menu> getAllMenus();//fetch

 List<Menu> getMenuByRestaurantId(int restaurantId); 
}