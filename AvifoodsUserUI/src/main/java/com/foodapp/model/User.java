package com.foodapp.model;
		
public class User {
	        private int userid;
		    private String username;
		    private String password;
		    private String email;
		    private String address;

		    // Constructor
		    public User(int userid,String username, String password, String email, String address) {
		        this.userid=userid;
		        this.username = username;
		        this.password = password;
		        this.email = email;
		        this.address = address;
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

		    public String getAddress() {
		        return address;
		    }

		    public void setAddress(String address) {
		        this.address = address;
		    }
		    @Override
		    public String toString() {
		        return  "userid='" + userid + '\''
		        		+", username='" + username + '\'' +
		                ", email='" + email + '\'' +
		                ", address='" + address + '\'' +
		                '}';
		    }

			public int getUserid() {
				return userid;
			}

			public void setUserid(int userid) {
				this.userid = userid;
			}
		}
