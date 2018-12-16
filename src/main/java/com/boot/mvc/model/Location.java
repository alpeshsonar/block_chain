package com.boot.mvc.model;

public class Location {
	private int locId; 
	private String location;
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Location(int locId, String location) {
		super();
		this.locId = locId;
		this.location = location;
	}
}

