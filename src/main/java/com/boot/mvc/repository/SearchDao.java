package com.boot.mvc.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.boot.mvc.model.Consignment;
import com.boot.mvc.model.Item;
import com.boot.mvc.model.Track;
import com.boot.mvc.model.User;

@Repository
public class SearchDao 
{
	
	private static final Logger logger = LoggerFactory.getLogger(SearchDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Track> track(String tag)
	{
		String sql = "SELECT t.track_id, t.consignment_id, t.depart_date, t.arrival_date, t.depart_loc_id, t.arrival_loc_id, t.hash, t.previous_hash, c.item_id, t.requested_by, t.requested_to FROM track t, consignment c where t.consignment_id = c.consignment_id and c.tag = '"+tag+"' order by t.track_id asc" ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		List<Track> tracks = new ArrayList<>();
		while (rowSet.next())
		{	
			tracks.add(new Track(rowSet.getInt("track_id"), rowSet.getInt("consignment_id"), rowSet.getDate("depart_date"), rowSet.getDate("arrival_date"), rowSet.getInt("depart_loc_id"), rowSet.getInt("arrival_loc_id"),rowSet.getInt("requested_by"),rowSet.getInt("requested_to"), rowSet.getString("hash"), rowSet.getString("previous_hash"), getUser(rowSet.getInt("requested_by")), getUser(rowSet.getInt("requested_to"))));	
		}
		return tracks;
	}
	public Consignment getConsignment(int consignmentId)
	{
		String sql = "SELECT consignment_id, item_id, tag FROM consignment where consignment_id = " + consignmentId ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		Consignment consignment = null;
		while (rowSet.next())
		{	
			consignment = new Consignment(rowSet.getInt("consignment_id"), rowSet.getInt("item_id"), rowSet.getString("tag"));	
		}
		return consignment;
	}
	
	
	public Item getItem(int itemId)
	{
		String sql = "SELECT id, name, characteristics, features, image_location, org_id FROM item where id = " + itemId ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		Item item = null;
		while (rowSet.next())
		{	
			item = new Item(rowSet.getInt("id"), rowSet.getString("name"), rowSet.getString("characteristics"), rowSet.getString("features"), rowSet.getString("image_location"), rowSet.getInt("org_id"));	
		}
		return item;
	}
	
	public User getUser(int userId)
	{
		String sql = "select user_id, user_name, password, loc_id, type_id, full_name from users where user_id = " + userId ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		User user = null;
		while (rowSet.next())
		{	
			user = new User(rowSet.getInt("user_id"), rowSet.getString("user_name"), rowSet.getString("password"), rowSet.getInt("loc_id"), rowSet.getInt("type_id"), rowSet.getString("full_name"));	
		}
		return user;
	}
	
}
