package com.boot.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.mvc.service.OutboxService;

@Controller
@RequestMapping("/outbox-controller")
public class OutboxController 
{
	
	@Autowired
	private OutboxService outboxService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> outbox(@RequestParam Map<String, String> requestParams)
	{
		String trackBean = outboxService.request(requestParams);
		return new ResponseEntity<String>(trackBean, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> onLoad(@RequestParam Map<String, String> requestParams)
	{
		String trackBean = outboxService.load(requestParams);
		return new ResponseEntity<String>(trackBean, HttpStatus.OK);
	}
	
	@RequestMapping(value="/suppliers", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, String>> getSuppliers(@RequestParam Map<String, String> requestParams)
	{
		Map<Integer, String> trackBean = outboxService.getSupplier(Integer.parseInt(requestParams.get("user_id")));
		return new ResponseEntity<Map<Integer, String>>(trackBean, HttpStatus.OK);
	}
}
