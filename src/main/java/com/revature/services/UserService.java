package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.enums.UserRole;
import com.revature.pojo.User;

public class UserService {
	public User getUser(String userName, String password){
		UserDao uDao = new UserDao();
		/**
		 * Set some sort of message for error.
		 */
		User user = uDao.getUser(userName);
		if(user == null){
			//possibly some sort of error message here, can't find user? Redirect back to login. something.
			return null;
		}
		if(user.getPassword().equals(password)){ 
			return user; 
		}
		else{
			return null;
		}
	}
	
	public User getCurrentSessionUser(HttpServletRequest req){
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("currentuser");
		if(user ==null){
			//Something is very wrong.. TODO: Catch this
			System.out.println("ERROR: USER IS NULL");
		}
		return user;
	}
	
	public void updateUserProfile(HttpServletRequest req){
		User user = getCurrentSessionUser(req);
		
		User updateUser = new User(user.getUserId(),req.getParameter("e_username"),req.getParameter("e_password"),
				req.getParameter("e_firstname"),req.getParameter("e_lastname"),
				req.getParameter("e_email"),user.getUserRole());
				
		System.out.println(updateUser);
		UserDao userDao = new UserDao();
		userDao.updateUser(updateUser);
		updateUser = userDao.getUser(user.getUserName());
		
		req.getSession().setAttribute("currentuser", updateUser);
		
	}
	
	public User getUserById(int id){
		User user = new UserDao().getUser(id);
		return user;
	}
	
	public List<User> getAllEmployees(HttpServletRequest req){
		List<User> userList = new UserDao().getUserByRole(UserRole.EMPLOYEE.getUserId());
		/*
		 * GET OBJECT MAPPER AND CREATE THE RESPONSE WITH THE LIST OF USER LIST FOR THE AJAX REQUEST.
		 */
		
		return userList;
	}
}
