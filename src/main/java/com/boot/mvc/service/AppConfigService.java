package com.boot.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.mvc.model.ApplicationCache;
import com.boot.mvc.repository.AppConfigDao;

@Service
public class AppConfigService {

	@Autowired
	AppConfigDao appConfigDao;
	
	public void execute(){
		
		System.out.println(" init :: start ");
		appConfigDao.loadLocation(ApplicationCache.LOCATION);
		System.out.println("         LOCATION :: " + ApplicationCache.LOCATION.size());
	
		appConfigDao.loadUserType(ApplicationCache.USER_TYPE);
		System.out.println("         USER_TYPE :: " + ApplicationCache.USER_TYPE.size());
		
		appConfigDao.loadItems(ApplicationCache.ITEMS);
		System.out.println("         ITEMS :: " + ApplicationCache.ITEMS.size());
		/*
		appConfigDao.loadAdProviders(ApplicationCache.ADS_PROVIDER_CONFIG, ApplicationCache.ADS_PROVIDER_WITH_DOM);
		System.out.println("         ADS_PROVIDER_CONFIG :: " + ApplicationCache.ADS_PROVIDER_CONFIG.size());
		System.out.println("         ADS_PROVIDER_WITH_DOM :: " + ApplicationCache.ADS_PROVIDER_WITH_DOM.size());
		
		appConfigDao.loadRules(ApplicationCache.RULES);
		System.out.println("         RULES :: " + ApplicationCache.RULES.size());
		
		System.out.println(" init :: end ");*/
	}
}
