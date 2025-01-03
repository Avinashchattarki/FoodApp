package com.foodapp.dao;

import java.util.List;




import com.foodapp.model.Menu;


public interface MenuDAO {
 void addMenu(Menu menu);

 void updateMenu(Menu menu);

 void deleteMenu(int menuId);

 public  Menu getMenuById(int menuId);

 List<Menu> getAllMenus();

 List<Menu> getMenuByRestaurantId(int restaurantId); 
}