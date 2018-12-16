package com.boot.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.mvc.model.TagTrack;
import com.boot.mvc.service.InboxService;
import com.boot.mvc.service.SearchService;

@Controller
@RequestMapping("/inbox-controller")
public class InboxController 
{
	
	@Autowired
	private InboxService inboxService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> activity(@RequestParam Map<String, String> requestParams)
	{
		String trackBean = inboxService.load(requestParams.get("User"));
		return new ResponseEntity<String>(trackBean, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> approve(@RequestParam Map<String, String> requestParams)
	{
		String trackBean = inboxService.approve(requestParams.get("User"), requestParams.get("Request"));
		return new ResponseEntity<String>(trackBean, HttpStatus.OK);
	}
}
