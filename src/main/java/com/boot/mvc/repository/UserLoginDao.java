package com.boot.mvc.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.boot.mvc.model.User;

@Repository
public class UserLoginDao {

	private static final Logger logger = LoggerFactory.getLogger(UserLoginDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public User doLogin(String userName, String password) {
		String sql = "select user_id, user_name, password, loc_id, type_id, full_name from users where user_name = '" + userName+"' and password = '"+password+"'" ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		User user = new User();
		while (rowSet.next())
		{	
			user = new User(rowSet.getInt("user_id"), rowSet.getString("user_name"), rowSet.getString("password"), rowSet.getInt("loc_id"), rowSet.getInt("type_id"), rowSet.getString("full_name"));	
		}
		return user;
	}

	public void create(String username, String name, String pwd, String location, String type) 
	{
		String sql = "INSERT INTO users (user_name, password, loc_id, type_id, full_name) VALUES (?, ?, ?, ?, ?)";			
		jdbcTemplate.update(sql, new Object[] { username, pwd, location, type, name});
	}
}
