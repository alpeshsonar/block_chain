package com.supplychain;

import com.boot.mvc.model.Consignment;
import com.boot.mvc.service.BlockChainFactoryImpl;
import com.google.gson.GsonBuilder;

public class Run 
{
	public static void main(String[] args) 
	{
		Consignment consignment = new Consignment(1, 1, null);
		consignment.setTag("Consignment 1");
		
		try
		{
			BlockChainFactoryImpl factory = new BlockChainFactoryImpl();
			
			String hash = "0";
			hash = factory.tranfer(consignment, "Mumbai", "Delhi", hash);
			hash = factory.tranfer(consignment, "Delhi", "Pune", hash);
			hash = factory.tranfer(consignment, "Pune", "Chennai", hash);
		}
		catch(Exception e)
		{
			String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(e);
			System.out.println(blockchainJson);
		}
	}
}
