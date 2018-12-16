package com.boot.mvc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.mvc.model.ApplicationCache;
import com.boot.mvc.model.Consignment;
import com.boot.mvc.model.Item;
import com.boot.mvc.model.ItemRequest;
import com.boot.mvc.repository.InboxDao;
import com.boot.mvc.repository.SearchDao;

@Service
public class StockService {

	
	@Autowired
	InboxDao inboxDao;
	
	@Autowired
	SearchDao trackDao;

	public String load(String user){	
		Map<Integer, List<Consignment>> consignments = inboxDao.consignment(user);
		
		
		String html = " <table class='table table-sm'> "+
		" 	<thead class='table-info'  style='background-color: #47a3da;'> "+
		" 		<tr style='color: #fff;'> "+
		" 			<th>Item</th> "+
		" 			<th>Tag</th> "+
		" 		</tr> "+
		" 	</thead> "+
		" 	<tbody> ";


		String post =  	"</tbody> "+
						" </table> ";
		boolean data = false;
		
		for (Entry<Integer, List<Consignment>> entry : consignments.entrySet()) 
		{ 
			for(Consignment consignment : entry.getValue())
			{
				
				html+=	"<tr>"+
						"<td>"+ApplicationCache.ITEMS.get(entry.getKey()).getName()+"</td>"+
						"<td>"+ consignment.getTag() +"</td>"+
						"</tr>";
				data = true;
			}
		}
		return data?(html+post): "<div>Out of stock, Please request from supplier chain.</div>";
	}
	
	public String dateFormator(String pattern, Date dateInput){
		
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dateInput);
		return date;
	}
}
