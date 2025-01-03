package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.DeliveryBoy;

public interface DeliveryBoyDAO {
    void addDeliveryBoy(DeliveryBoy deliveryBoy);

    List<DeliveryBoy> getAllDeliveryBoys();

    DeliveryBoy getDeliveryBoyById(int deliveryId);

    void updateDeliveryBoy(DeliveryBoy deliveryBoy);

    void deleteDeliveryBoy(int deliveryId);
    
    public DeliveryBoy getDeliveryBoyByUsername(String username);
}
