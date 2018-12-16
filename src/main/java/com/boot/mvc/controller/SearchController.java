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
import com.boot.mvc.service.SearchService;

@Controller
@RequestMapping("/search-tag")
public class SearchController 
{
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> activity(@RequestParam Map<String, String> requestParams)
	{
		String trackBean = searchService.track(requestParams.get("tag"));
		return new ResponseEntity<String>(trackBean, HttpStatus.OK);
	}
}
