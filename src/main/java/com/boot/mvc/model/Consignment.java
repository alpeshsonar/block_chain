package com.boot.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Consignment {
	@JsonIgnore
	private int consignmentId;
	@JsonIgnore
	private int itemId;
	private String tag;
	public int getConsignmentId() {
		return consignmentId;
	}
	public void setConsignmentId(int consignmentId) {
		this.consignmentId = consignmentId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Consignment(int consignmentId, int itemId, String tag) {
		super();
		this.consignmentId = consignmentId;
		this.itemId = itemId;
		this.tag = tag;
	}
}
