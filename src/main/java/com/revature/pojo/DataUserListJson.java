package com.revature.pojo;

import java.util.List;

public class DataUserListJson {
	private List<User> userList;

	public DataUserListJson(List<User> userList) {
		super();
		this.userList = userList;
	}
	
	public DataUserListJson(){}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	
}
