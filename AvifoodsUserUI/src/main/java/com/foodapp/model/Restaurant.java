package com.foodapp.model;


public class Restaurant {
    private int restaurantId;
    private String name;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private double ratings;
    private boolean isActive;
    private String imagePath;

    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return  "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", address='" + address + '\'' +
                ", ratings=" + ratings +
                ", isActive=" + isActive +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
