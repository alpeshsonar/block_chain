package com.boot.mvc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService{
	
	public boolean isValid(String authorization, String jsessionId) {

		// decrypt		
		System.out.println(" authorization "+authorization+" jsessionId "+jsessionId);
		long salt = 0;
		for(int i=0;i<jsessionId.length();i++)
		{
			salt+=(int)jsessionId.charAt(i);
		}
		
		String input="";
		authorization = authorization.replaceAll("[^0-9]", "X");
		
		System.out.println(authorization);
		for(int i=0;i<authorization.split("X").length;i++)
		{
			int index = Integer.parseInt(authorization.split("X")[i]);
			input += ((char)(index- salt));
		}
		System.out.println(" input "+input);
		//source=FX|version=1.0
		
		String[] keyValuePairs = input.split(",");              
		Map<String,String> map = new HashMap<>();               
		for(String pair : keyValuePairs)                        
		{
		    String[] entry = pair.split("=");                    
		    map.put(entry[0].trim(), entry[1].trim());          
		}
		
		System.out.println(" map "+map);
		
		if(map.get("source")==null || map.get("source").isEmpty() || !"1.0".equals(map.get("version")))
		{
			return false;
		}
		return true;
	}

}
