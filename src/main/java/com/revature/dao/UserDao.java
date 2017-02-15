package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.UserRole;
import com.revature.pojo.User;

import oracle.jdbc.internal.OracleTypes;

public class UserDao implements IUserDao{
	private final static String URL = "jdbc:oracle:thin:@java1701.cylnhxmf2kbj.us-west-2.rds.amazonaws.com:1521";
	private final static String USERNAME = "java1701";
	private final static String PASSWORD = "p4ssw0rd";
	
	static{
	    try {
	        Class.forName ("oracle.jdbc.OracleDriver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public User getUser(String username) {
		User user = null;
		try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);){
			String sqlStoredProc = "{call get_user_by_username(?,?)}";
			CallableStatement cs = connection.prepareCall(sqlStoredProc);
			
			cs.setString(1, username);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.executeQuery();
			
			ResultSet rs = (ResultSet) cs.getObject(2);
			
			List<User> userList = new ArrayList<User>();
			while(rs.next()){
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), UserRole.getRoleById(rs.getInt(7))));
			}
			
			if(userList.isEmpty()){
				return null;
			}
			
			user = userList.get(0);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> getUserByRole(int roleId) {
		String sql = "{call get_users_by_role(?,?)}";
		try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);){
			List<User> userList = new ArrayList<User>();
			CallableStatement cs = connection.prepareCall(sql);
			cs.setInt(1, roleId);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.executeQuery();
			
			ResultSet rs = (ResultSet) cs.getObject(2);
			
			while(rs.next()){
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), UserRole.getRoleById(rs.getInt(7))));
			}
			
			if(!userList.isEmpty()){
				return userList;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);){
			String updateSql = "{call upate_user_profile(?,?,?,?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(updateSql);
			
			cs.setInt(1, user.getUserId());
			cs.setString(2, user.getUserName());
			cs.setString(3, user.getPassword());
			cs.setString(4, user.getFirstName());
			cs.setString(5, user.getLastName());
			cs.setString(6, user.getEmail());
			cs.setInt(7, user.getUserRole().getUserId());
			
			cs.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(int id) {
		String sql = "{call get_user_by_id(?,?)}";
		
		try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);){
			CallableStatement cs = connection.prepareCall(sql);
			
			cs.setInt(1, id);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			
			cs.executeQuery();
			
			ResultSet rs = (ResultSet) cs.getObject(2);
			
			List<User> uList = new ArrayList<>();
			
			while(rs.next()){
				uList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), UserRole.getRoleById(rs.getInt(7))));
			}
			
			if(!uList.isEmpty()){
				return uList.get(0);
			}
			
		}catch(Exception e){
			
		}
		
		return null;
	}
	
	
	
}
