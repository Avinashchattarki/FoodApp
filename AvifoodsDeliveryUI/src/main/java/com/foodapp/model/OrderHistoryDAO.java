package com.foodapp.model;

public class OrderHistoryDAO {

		private int orderhistory;
		private String OrderId;
		private int userid;
		private double totalAmount;
		private String status;
		public int getOrderhistory() {
			return orderhistory;
		}
		public void setOrderhistory(int orderhistory) {
			this.orderhistory = orderhistory;
		}
		public String getOrderId() {
			return OrderId;
		}
		public void setOrderId(String orderId) {
			OrderId = orderId;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public OrderHistoryDAO(int orderhistory, String orderId, int userid, int totalAmount, String status) {
			super();
			this.orderhistory = orderhistory;
			OrderId = orderId;
			this.userid = userid;
			this.totalAmount = totalAmount;
			this.status = status;
		}
		public OrderHistoryDAO()
		{
			
		}
		@Override
		public String toString() {
			return "OrderHistoryDAO [orderhistory=" + orderhistory + ", OrderId=" + OrderId + ", userid=" + userid
					+ ", totalAmount=" + totalAmount + ", status=" + status + "]";
		}
		

}
