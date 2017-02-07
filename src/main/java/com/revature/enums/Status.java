package com.revature.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
	PENDING(1,"Pending"), APPROVED(2,"Approved"),DECLINED(3,"Declined");
	
	private final int id;
	private final String status;
	
	private final static Map<Integer, Status> lookUpStatusById = mapIdToStatus();
	
	private final static Map<String, Status> lookUpStatusByName = mapNameToStatus();
	
	private Status(int id, String status){
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public static Status getStatusById(int id){
		return lookUpStatusById.get(id);
	}
	
	public static Status getStatusByName(String name){
		return lookUpStatusByName.get(name);
	}
	
	private static Map<Integer,Status> mapIdToStatus(){
		Map<Integer, Status> tempMap = new HashMap<>();
		for(Status ur : Status.values()){
			tempMap.put(ur.id, ur);
		}
		return tempMap;
	}
	
	private static Map<String ,Status> mapNameToStatus(){
		Map<String, Status> tempMap = new HashMap<>();
		for(Status ur : Status.values()){
			tempMap.put(ur.name(), ur);
		}
		return tempMap;
	}
}
