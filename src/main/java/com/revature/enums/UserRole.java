package com.revature.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
	MANAGER(1,"Manager"), EMPLOYEE(2,"Employee");
	
	final private int roleId;
	final private String roleName;
	
	private final static Map<Integer, UserRole> lookUpRoleById = mapIdToRole();
	
	private UserRole(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public int getUserId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	
	public static UserRole getRoleById(int id){
		return lookUpRoleById.get(id);
	}
	
	
	private static Map<Integer,UserRole> mapIdToRole(){
		Map<Integer, UserRole> tempMap = new HashMap<>();
		for(UserRole ur : UserRole.values()){
			tempMap.put(ur.roleId, ur);
		}
		return tempMap;
	}
	
}
