package com.boot.mvc.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "index";
	}
	
	@RequestMapping("/search")
	public String search(Map<String, Object> model) {
		return "search";
	}
	
	@RequestMapping("/inbox")
	public String inbox(Map<String, Object> model) {
		return "inbox";
	}
	
	@RequestMapping("/outbox")
	public String outbox(Map<String, Object> model) {
		return "outbox";
	}
	
	@RequestMapping("/history")
	public String history(Map<String, Object> model) {
		return "history";
	}
	
	@RequestMapping("/contact")
	public String contact(Map<String, Object> model) {
		return "contact";
	}
	
	@RequestMapping("/add-user")
	public String addUser(Map<String, Object> model) {
		return "add-user";
	}
	
	@RequestMapping("/mission")
	public String mission(Map<String, Object> model) {
		return "mission";
	}
}