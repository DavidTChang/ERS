package com.revature.enums;

import java.util.HashMap;
import java.util.Map;

public enum RType {
	
	WEAPONS(1,"Weapons"), FOOD(2,"Food"), TRANSPORTATION(3,"Transportation"),
	CLOTHES(4,"Clothes");
	
	private final String type;
	private final int typeId;
	
	private final static Map<Integer, RType> lookUpTypeById = mapIdToType();
	private final static Map<String, RType> lookUpTypeByName = mapNameToType();

	private RType( int typeId,String type) {
		this.type = type;
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public int getTypeId() {
		return typeId;
	}
	
	public static RType getTypeById(int id){
		return lookUpTypeById.get(id);
	}
	
	private static Map<Integer,RType> mapIdToType(){
		Map<Integer, RType> tempMap = new HashMap<>();
		for(RType ur : RType.values()){
			tempMap.put(ur.typeId, ur);
		}
		return tempMap;
	}
	
	private static Map<String, RType> mapNameToType(){
		Map<String, RType> tempMap = new HashMap<>();
		for(RType ur : RType.values()){
			tempMap.put(ur.type, ur);
		}
		return tempMap;
	}
	public static RType getTypeByName(String name) {
		// TODO Auto-generated method stub
		return lookUpTypeByName.get(name);
	}
}
