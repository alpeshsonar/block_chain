package com.boot.mvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.boot.mvc.model.Consignment;
import com.boot.mvc.model.ItemRequest;
import com.boot.mvc.model.Track;
import com.boot.mvc.model.User;

@Repository
public class InboxDao {

	private static final Logger logger = LoggerFactory.getLogger(InboxDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<ItemRequest> request(String userId) {
		String sql = "SELECT r.request_id, r.item_id, concat(u.user_name,' - ',t.type) as supplier, r.quantity, r.requested_date FROM item_request r, users u, user_type t where r.status= 'Pending' and r.requested_by = u.user_id and t.type_id = u.type_id  and requested_to = "
				+ userId;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		List<ItemRequest> itemRequest = new ArrayList<>();
		while (rowSet.next()) {
			itemRequest.add(new ItemRequest(rowSet.getInt("item_id"), rowSet.getString("supplier"),
					rowSet.getInt("quantity"), rowSet.getDate("requested_date"), rowSet.getInt("request_id")));
		}
		return itemRequest;
	}

	public Map<Integer, List<Consignment>> consignment(String userId) {
		String query = " select c.item_id, c.tag, c.consignment_id from track tm, consignment c "
				+ " where tm.requested_by = " + userId + " and tm.consignment_id not in ( "
				+ " select t.consignment_id from track t, (SELECT t.consignment_id, t.hash FROM track t where t.requested_by = "
				+ userId + ") as ch  " + " where t.consignment_id = ch.consignment_id and t.previous_hash = ch.hash) "
				+ " and c.consignment_id = tm.consignment_id ";

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
		Map<Integer, List<Consignment>> consignements = new HashMap<>();
		while (rowSet.next()) {
			if (!consignements.containsKey(rowSet.getInt("item_id")))
				consignements.put(rowSet.getInt("item_id"), new ArrayList<Consignment>());

			consignements.get(rowSet.getInt("item_id")).add(new Consignment(rowSet.getInt("consignment_id"),
					rowSet.getInt("item_id"), rowSet.getString("tag")));
		}
		return consignements;
	}

	public int updateItemRequest(String request) {
		int rowCount = jdbcTemplate.update("UPDATE item_request SET status= 'Approved' WHERE  request_id=" + request);
		return rowCount;
	}


	public int insertConsignment(final String itemId, final String guid) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO consignment(item_id, tag) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, itemId);
				ps.setString(2, guid);
				return ps;
			}
		}, holder);

		int cid = holder.getKey().intValue();
		return cid;
	}
	
	
	public Track track(String itemId, String requested, String consignmentId)
	{
		String sql = "SELECT t.track_id, t.consignment_id, t.depart_date, t.arrival_date, t.depart_loc_id, t.arrival_loc_id, t.hash, t.previous_hash, c.item_id, t.requested_by, t.requested_to FROM track t, consignment c where t.consignment_id = c.consignment_id and t.requested_by ="+requested+" and t.consignment_id = "+consignmentId ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		List<Track> tracks = new ArrayList<>();
		while (rowSet.next())
		{	
			tracks.add(new Track(rowSet.getInt("track_id"), rowSet.getInt("consignment_id"), rowSet.getDate("depart_date"), rowSet.getDate("arrival_date"), rowSet.getInt("depart_loc_id"), rowSet.getInt("arrival_loc_id"),rowSet.getInt("requested_by"),rowSet.getInt("requested_to"), rowSet.getString("hash"), rowSet.getString("previous_hash"), getUser(rowSet.getInt("requested_by")), getUser(rowSet.getInt("requested_to"))));	
		}
		return tracks.isEmpty()? null : tracks.get(0);
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
	
	
	public ItemRequest requestBy(String requestId)
	{
		String sql = "SELECT r.requested_by, r.requested_date FROM item_request r where r.request_id = "+requestId ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		ItemRequest itemRequest = new ItemRequest();
		if (rowSet.next())
		{			
			itemRequest.setRequestedDate(rowSet.getDate("requested_date"));
			itemRequest.setRequestedBy(rowSet.getInt("requested_by"));
		}
		return itemRequest;
	}
	
	
	public void insertTrack(Track track)
	{
		String sql = "INSERT INTO track (consignment_id, depart_date, arrival_date, depart_loc_id, arrival_loc_id, hash, requested_by, requested_to, previous_hash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";			
		jdbcTemplate.update(sql, new Object[] { track.getConsignmentId(),dateFormator ("yyyy-MM-dd hh:mm:ss", track.getDepartDate()),track.getArrivalDate(), track.getDepartLocId(), track.getArrivalLocId(), track.getHash(), track.getRequestedBy(), track.getRequestedTo(), track.getPreviousHash()});
	}

public String dateFormator(String pattern, Date dateInput){
		
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dateInput);
		return date;
	}
}
