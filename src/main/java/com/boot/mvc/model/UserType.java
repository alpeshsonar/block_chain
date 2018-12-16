package com.boot.mvc.model;

public class UserType {
	private int typeId; 
	private String type;
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
}
