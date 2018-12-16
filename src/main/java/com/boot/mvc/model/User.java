package com.boot.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	@JsonIgnore
	private int userId; 
	private String userName; 
	@JsonIgnore
	private String password; 
	@JsonIgnore
	private int locId;
	@JsonIgnore
	private int typeId;
	private String name; 
	
	
	public User() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public User(int userId, String userName, String password, int locId, int typeId, String name) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.locId = locId;
		this.typeId = typeId;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
