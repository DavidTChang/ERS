package com.revature.pojo;

import com.revature.enums.UserRole;

public class User {
	private int userId;
	private String userName, password, firstName, lastName, email;

	private UserRole userRole;
	
	public User(UserRole userRole, String userName, String password, String firstName, String lastName, String email) {
		super();
		//this.userId = userId;
		this.password = password;
		this.userRole = userRole;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.email = email;
	}
	
	public User(int userId,String userName, String password, String firstName, String lastName,
			String email, UserRole userRole) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}
	
	
}
