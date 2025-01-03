package com.foodapp.model;

public class Delivery {
    private String orderId;
    private String address;
    private String streetName;
    private String Pincode;
    private String phone;
    private String deliveryInstructions;
    private String paymentMethod;
    private double price;
    private String resturantname;
    private String addressResturant;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getPincode() {
		return Pincode;
	}
	public void setPincode(String pincode) {
		Pincode = pincode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}
	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getResturantname() {
		return resturantname;
	}
	public void setResturantname(String resturantname) {
		this.resturantname = resturantname;
	}
	public String getAddressResturant() {
		return addressResturant;
	}
	public void setAddressResturant(String addressResturant) {
		this.addressResturant = addressResturant;
	}
	public Delivery(String orderId, String address, String streetName, String Pincode, String phone,
			String deliveryInstructions, String paymentMethod, double price, String resturantname,
			String addressResturant) {
		super();
		this.orderId = orderId;
		this.address = address;
		this.streetName = streetName;
		this.Pincode = Pincode;
		this.phone = phone;
		this.deliveryInstructions = deliveryInstructions;
		this.paymentMethod = paymentMethod;
		this.price = price;
		this.resturantname = resturantname;
		this.addressResturant = addressResturant;
	}
	@Override
	public String toString() {
		return "Delivery [orderId=" + orderId + ", address=" + address + ", streetName=" + streetName + ", Pincode="
				+ Pincode + ", phone=" + phone + ", deliveryInstructions=" + deliveryInstructions + ", paymentMethod="
				+ paymentMethod + ", price=" + price + ", resturantname=" + resturantname + ", addressResturant="
				+ addressResturant + "]";
	}
	
	public Delivery()
	{
		
	}
    

}