package com.boot.mvc.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.mvc.model.User;
import com.boot.mvc.service.UserLoginService;

@Controller
@RequestMapping("/login")
public class UserLoginController 
{
	
	@Autowired
	private UserLoginService userLoginService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestParam Map<String, String> requestParams,  HttpServletResponse response)
	{
		String trackBean = "1";
		
		User user = userLoginService.doLogin(requestParams.get("user"), requestParams.get("pwd"));
		
		response.addCookie(new Cookie("user_id", user.getUserId()>0 ? user.getUserId()+"" : ""));
		response.addCookie(new Cookie("user_type_id", user.getTypeId()>0 ? user.getTypeId()+"" : ""));
		response.addCookie(new Cookie("user_name", user.getUserId()>0? user.getUserName()+"" : ""));
		response.addCookie(new Cookie("name", user.getUserId()>0? user.getName().replace(" ", "_") : ""));

		HttpHeaders headers = new HttpHeaders();
		
        headers.add("user_id", user.getUserId()>0 ? user.getUserId()+"" : "");
        headers.add("user_type_id", user.getTypeId()>0 ? user.getTypeId()+"" : "");
        headers.add("user_name", user.getUserId()>0? user.getUserName()+"" : "");
        headers.add("name", user.getUserId()>0 ? user.getName().replace(" ", "_") : "");

		return new ResponseEntity<String>(trackBean, headers, HttpStatus.OK);
	}
	

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestParam Map<String, String> requestParams,  HttpServletResponse response)
	{
		String status="<h6>Username already in used.</h6>";
		User user = userLoginService.create(requestParams.get("username"), requestParams.get("name"), requestParams.get("pwd"), 
				requestParams.get("location"), requestParams.get("type"));
		if(user != null)
			status ="200";
		return new ResponseEntity<String>(status, HttpStatus.OK);


	}
}
