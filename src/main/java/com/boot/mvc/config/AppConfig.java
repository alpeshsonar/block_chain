package com.boot.mvc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boot.mvc.service.AppConfigService;

@Component
public class AppConfig {
	
	@Autowired
	AppConfigService appConfigService;
	
	@PostConstruct
	public void init() {
		// init code goes here
		appConfigService.execute();
	}
}
