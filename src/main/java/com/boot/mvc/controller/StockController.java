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
import com.boot.mvc.service.StockService;

@Controller
@RequestMapping("/stock-controller")
public class StockController 
{
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> stock(@RequestParam Map<String, String> requestParams)
	{
		String trackBean = stockService.load(requestParams.get("User"));
		return new ResponseEntity<String>(trackBean, HttpStatus.OK);
	}
}
