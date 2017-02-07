package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface IUserDao {
	
	User getUser(String username);
	
	User getUser(int id);
	
	List<User> getUserByRole(int roleId);
	
	User createUser(User user);
	
	void updateUser(User user);
	
	boolean deleteUser(int userId);
	
}
