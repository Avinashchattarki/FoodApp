package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Delivery;

public interface DeliveryDAO {
    void addDelivery(Delivery delivery);
    Delivery getDeliveryById(int orderId);
    List<Delivery> getAllDeliveries();
    void updateDelivery(Delivery delivery);
    void deleteDelivery(String orderId);
}
