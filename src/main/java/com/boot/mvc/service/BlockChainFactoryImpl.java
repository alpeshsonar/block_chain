package com.boot.mvc.service;

import org.springframework.stereotype.Service;

import com.boot.mvc.model.Block;
import com.boot.mvc.model.Consignment;
import com.boot.mvc.utilty.StringUtil;
import com.google.gson.GsonBuilder;

@Service
public class BlockChainFactoryImpl
{
	public String tranfer(Consignment consignment, String source, String destination, String previousHash)
	{
		Block block = new Block(previousHash, consignment, source, destination);
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(block);
	    System.out.println(blockchainJson);
		return block.hash;
	}
	
	public boolean validate(Consignment consignment, String source, String previousHash)
	{
		String calPreviousHash = StringUtil.applySha256(source + consignment.getTag());		
		if(!previousHash.equals(calPreviousHash) && !previousHash.equals("0"))
			return false;
		return true;
	}
}
