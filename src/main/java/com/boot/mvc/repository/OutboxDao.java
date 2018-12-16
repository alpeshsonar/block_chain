package com.boot.mvc.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.boot.mvc.model.ItemRequest;
import com.boot.mvc.model.User;

@Repository
public class OutboxDao 
{
	
	private static final Logger logger = LoggerFactory.getLogger(OutboxDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Map<Integer, String>  getSuppliers(User user)
	{
		String sql = "SELECT user_id, concat(user_name,' - ',t.type) as supplier FROM users u, user_type t where u.type_id in("+(user.getTypeId()-1)+", "+user.getTypeId()+") and u.user_id!="+user.getUserId()+" and t.type_id = u.type_id;" ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		Map<Integer, String> suppliers = new HashMap<>();
		while (rowSet.next())
		{				
			suppliers.put(rowSet.getInt("user_id"), rowSet.getString("supplier"));
		}
		return suppliers;
	}	
	
	public void insertItemRequest(String user,String items,String supplier,String quntity)
	{
		String sql = "INSERT INTO item_request (requested_by, requested_to, item_id, quantity) VALUES (?, ?, ?, ?)";			
		jdbcTemplate.update(sql, new Object[] { user,supplier,items, quntity});
	}
	
	
	public List<ItemRequest> request(String userId)
	{
		String sql = "SELECT r.request_id, r.item_id, concat(u.user_name,' - ',t.type) as supplier, r.quantity, r.requested_date  FROM item_request r, users u, user_type t where  r.requested_to = u.user_id and t.type_id = u.type_id and r.status= 'Pending'  and requested_by = " + userId ;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
		List<ItemRequest> itemRequest = new ArrayList<>();
		while (rowSet.next())
		{				
			itemRequest.add(new ItemRequest(rowSet.getInt("item_id"), rowSet.getString("supplier"),rowSet.getInt("quantity"),rowSet.getDate("requested_date"), rowSet.getInt("request_id")));
		}
		return itemRequest;
	}
}
