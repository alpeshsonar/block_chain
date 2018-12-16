package com.boot.mvc.model;

import java.util.Date;

import com.boot.mvc.utilty.StringUtil;

public class Block {
	
	private Consignment consignment;
	private String source;
	private String destination;
	private long timeStamp;
	public String hash;
	public String previousHash;
	
	public Block(String previousHash, Consignment consignment,String source,String destination) {
		this.consignment = consignment;
		this.source = source;
		this.destination = destination;
		this.previousHash = previousHash;
		this.hash = calculateHash();
		this.timeStamp = new Date().getTime();
	}
	
	public String calculateHash() {
		String calculatedHash = StringUtil.applySha256(destination + consignment.getTag());
		return calculatedHash;
	}
}