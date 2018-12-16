package com.boot.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.mvc.model.User;
import com.boot.mvc.repository.UserLoginDao;

@Service
public class UserLoginService {

	@Autowired
	private UserLoginDao userLoginDao;

	public User doLogin(String userName,String password)
	{
		return userLoginDao.doLogin(userName, password);
	}

	@Transactional
	public User create(String username, String name, String pwd, String location, String type) 
	{
		try
		{
			userLoginDao.create(username, name, pwd, location, type);
			return userLoginDao.doLogin(username, pwd);

		}catch(Exception e)
		{
			
		}
		return null;
	}
}
