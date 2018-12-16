package com.boot.mvc.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Track {
	
	private int trackId; 
	
	@JsonIgnore
	private int consignmentId; 
	private Date departDate; 
	private Date arrivalDate; 
	@JsonIgnore
	private int departLocId;
	@JsonIgnore
	private int arrivalLocId; 
	@JsonIgnore
	private int requestedBy;
	@JsonIgnore
	private int requestedTo; 
	private String hash;
	private String previousHash;
	private User requestedToUser; 
	private User requestedByUser; 
	private String status;
	
	
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public int getConsignmentId() {
		return consignmentId;
	}
	public void setConsignmentId(int consignmentId) {
		this.consignmentId = consignmentId;
	}
	public Date getDepartDate() {
		return departDate;
	}
	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public int getDepartLocId() {
		return departLocId;
	}
	public void setDepartLocId(int departLocId) {
		this.departLocId = departLocId;
	}
	public int getArrivalLocId() {
		return arrivalLocId;
	}
	public void setArrivalLocId(int arrivalLocId) {
		this.arrivalLocId = arrivalLocId;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}
	public int getRequestedTo() {
		return requestedTo;
	}
	public void setRequestedTo(int requestedTo) {
		this.requestedTo = requestedTo;
	}
	public User getRequestedToUser() {
		return requestedToUser;
	}
	public void setRequestedToUser(User requestedToUser) {
		this.requestedToUser = requestedToUser;
	}
	public User getRequestedByUser() {
		return requestedByUser;
	}
	public void setRequestedByUser(User requestedByUser) {
		this.requestedByUser = requestedByUser;
	}
	
	
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Track(int trackId, int consignmentId, Date departDate, Date arrivalDate, int departLocId, int arrivalLocId,
			int requestedBy, int requestedTo, String hash, String previousHash, User requestedToUser, User requestedByUser) {
		super();
		this.trackId = trackId;
		this.consignmentId = consignmentId;
		this.departDate = departDate;
		this.arrivalDate = arrivalDate;
		this.departLocId = departLocId;
		this.arrivalLocId = arrivalLocId;
		this.requestedBy = requestedBy;
		this.requestedTo = requestedTo;
		this.hash = hash;
		this.previousHash = previousHash;
		this.requestedToUser = requestedToUser;
		this.requestedByUser = requestedByUser;
	}
	
}
