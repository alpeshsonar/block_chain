package com.boot.mvc.repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.boot.mvc.model.Item;
import com.boot.mvc.model.Location;
import com.boot.mvc.model.UserType;

@Repository
public class AppConfigDao 
{
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfigDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void loadLocation(Map<Integer, Location> LOCATION)
	{
		String sql = "SELECT loc_id, location FROM locations";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		String location;
		Integer id;
		LOCATION.clear();

		while (rowSet.next())
		{
			location = rowSet.getString("location");
			id = rowSet.getInt("loc_id");
			LOCATION.put(id, new Location(id, location));
		}
	}
	

	public void loadUserType(Map<Integer, UserType> USER_TYPE)
	{
		String sql = "SELECT type_id, type FROM user_type";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		String location;
		int id;
		USER_TYPE.clear();

		while (rowSet.next())
		{
			location = rowSet.getString("type");
			id = rowSet.getInt("type_id");
			USER_TYPE.put(id, new UserType(id, location));
		}
	}
	public void loadItems(Map<Integer, Item> ITEMS)
	{
		String sql = "SELECT id, name, characteristics, features, image_location, org_id FROM item";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		ITEMS.clear();

		while (rowSet.next())
		{
			ITEMS.put(rowSet.getInt("id"), new Item(rowSet.getInt("id"), rowSet.getString("name"), rowSet.getString("characteristics"), rowSet.getString("features"), rowSet.getString("image_location"), rowSet.getInt("org_id")));
		}
	}
}
