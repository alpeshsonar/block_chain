package com.boot.mvc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.mvc.model.ApplicationCache;
import com.boot.mvc.model.ItemRequest;
import com.boot.mvc.repository.OutboxDao;
import com.boot.mvc.repository.SearchDao;

@Service
public class OutboxService {

	
	@Autowired
	OutboxDao outboxDao;
	
	@Autowired
	SearchDao searchDao;
	
	@Transactional
	public String request(Map<String, String> requestParams){		
		outboxDao.insertItemRequest(requestParams.get("User"),requestParams.get("Items"),requestParams.get("Supplier"),requestParams.get("Quntity"));
		List<ItemRequest> list = outboxDao.request(requestParams.get("User"));
		
		String html = " <table class='table table-sm'> "+
		" 	<thead class='table-info'  style='background-color: #47a3da;'> "+
		" 		<tr style='color: #fff;'> "+
		" 			<th>Item</th> "+
		" 			<th>Supplier</th> "+
		" 			<th>Quantity</th> "+
		" 			<th>Date</th> "+
		" 		</tr> "+
		" 	</thead> "+
		" 	<tbody> ";


		String post =  	"</tbody> "+
						" </table> ";
		boolean data = false;
		for(ItemRequest itemRequest:list)
		{
			html+=	"<tr>"+
					"<td>"+ApplicationCache.ITEMS.get(itemRequest.getItem()).getName()+"</td>"+
					"<td>"+ itemRequest.getSupplier() +"</td>"+
					"<td>"+ itemRequest.getQuantity() +"</td>"+
					"<td>"+ dateFormator("dd/MM/yyyy hh:mm:ss", itemRequest.getRequestedDate()) +"</td>"+
					"</tr>";
			data = true;
		}
		
		return data?(html+post): "<div></div>";
	}
	
	public String load(Map<String, String> requestParams){		
		List<ItemRequest> list = outboxDao.request(requestParams.get("User"));
		String html = " <table class='table table-sm'> "+
		" 	<thead class='table-info'  style='background-color: #47a3da;'> "+
		" 		<tr style='color: #fff;'> "+
		" 			<th>Item</th> "+
		" 			<th>Supplier</th> "+
		" 			<th>Quantity</th> "+
		" 			<th>Date</th> "+
		" 		</tr> "+
		" 	</thead> "+
		" 	<tbody> ";


		String post =  	"</tbody> "+
						" </table> ";
		boolean data = false;
		for(ItemRequest itemRequest:list)
		{
			html+=	"<tr>"+
					"<td>"+ApplicationCache.ITEMS.get(itemRequest.getItem()).getName()+"</td>"+
					"<td>"+ itemRequest.getSupplier() +"</td>"+
					"<td>"+ itemRequest.getQuantity() +"</td>"+
					"<td>"+ dateFormator("dd/MM/yyyy hh:mm:ss", itemRequest.getRequestedDate()) +"</td>"+
					"</tr>";
			data = true;
		}
		
		return data?(html+post): "<div></div>";
	}
	
	public Map<Integer, String> getSupplier(int userId)
	{
		Map<Integer, String> suppliers = outboxDao.getSuppliers(searchDao.getUser(userId));
		return suppliers;
	}
	
	public String dateFormator(String pattern, Date dateInput){
		
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dateInput);
		return date;
	}
}
