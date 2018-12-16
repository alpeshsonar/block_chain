package com.boot.mvc.model;

import java.util.List;

public class TagTrack {
	private Consignment consignment; 
	private Item item; 
	private List<Track> track;
	public Consignment getConsignment() {
		return consignment;
	}
	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<Track> getTrack() {
		return track;
	}
	public void setTrack(List<Track> track) {
		this.track = track;
	}
}
