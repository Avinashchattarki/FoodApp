package com.foodapp.model;

public class DeliveryBoy {
    private int deliveryId;
    private String username;
    private String password;
    private String email;
    private String adhar;
    private String phone;
    private String address;

    // Constructors
    public DeliveryBoy() {
    }

    public DeliveryBoy(int deliveryId, String username, String password, String email, String adhar, String phone, String address) {
        this.deliveryId = deliveryId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.adhar = adhar;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DeliveryBoy{" +
                "deliveryId=" + deliveryId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", adhar='" + adhar + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

