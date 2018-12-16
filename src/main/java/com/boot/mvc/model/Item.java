package com.boot.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Item {
	@JsonIgnore
	private int id; 
	private String name; 
	private String characteristics; 
	private String features; 
	private String imageLocation; 
	@JsonIgnore
	private int orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public Item(int id, String name, String characteristics, String features, String imageLocation, int orgId) {
		super();
		this.id = id;
		this.name = name;
		this.characteristics = characteristics;
		this.features = features;
		this.imageLocation = imageLocation;
		this.orgId = orgId;
	}
}
