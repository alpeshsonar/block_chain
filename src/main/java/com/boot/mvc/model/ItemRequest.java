package com.boot.mvc.model;

import java.util.Date;

public class ItemRequest {
	private int requestId;
	private int item; 
	private String supplier; 
	private int quantity; 
	private Date requestedDate;
	private int requestedBy;
	public ItemRequest(){
		
	}
	public ItemRequest(int item, String supplier, int quantity, Date requestedDate, int requestId) {
		super();
		this.item = item;
		this.supplier = supplier;
		this.quantity = quantity;
		this.requestedDate = requestedDate;
		this.requestId = requestId;
	}
	
	public int getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	 
}
