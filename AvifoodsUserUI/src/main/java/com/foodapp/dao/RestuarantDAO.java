package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Restaurant;

public interface RestuarantDAO {
    void addRestaurant(Restaurant restaurant);
    void displayRestaurants();
    Restaurant searchRestaurantById(int id);
    void deleteRestaurantById(int id);
    public List<Restaurant> getAllRestaurants();
    }

