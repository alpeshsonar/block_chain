package com.boot.mvc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.mvc.model.ApplicationCache;
import com.boot.mvc.model.Consignment;
import com.boot.mvc.model.ItemRequest;
import com.boot.mvc.model.Track;
import com.boot.mvc.model.User;
import com.boot.mvc.repository.InboxDao;
import com.boot.mvc.repository.OutboxDao;
import com.boot.mvc.repository.SearchDao;

@Service
public class InboxService {

	
	@Autowired
	InboxDao inboxDao;
	
	@Autowired
	SearchDao trackDao;


	@Autowired
	BlockChainFactoryImpl blockChainFactoryImpl;
	
	@Transactional
	public String approve(String userId, String requestArray)
	{	
			requestArray =requestArray.replace("[", "").replace("]", "").replace("\"", "");
			if(requestArray.contains(":"))
			{
				for(String request : requestArray.split(","))
				{
					String requestId = request.split(":")[0];
					String consignmentId = request.split(":")[1];
					String itemId = request.split(":")[2];
		
				 	inboxDao.updateItemRequest(requestId);
				    // if create new if company
				 	if(consignmentId.startsWith("C"))
				 	{
				 		UUID uuid = UUID.randomUUID();
				 		String randomUUIDString = uuid.toString();
				 		consignmentId = String.valueOf(inboxDao.insertConsignment(itemId, randomUUIDString));
				 	}
				 	//get consigned object
				 	ItemRequest itemRequest = inboxDao.requestBy(requestId);

				 	Track track =  inboxDao.track(itemId, userId, consignmentId);
				 	Track insertTrack = null;
				 	User requstedByUser = inboxDao.getUser(itemRequest.getRequestedBy());
				 	User requstedToUser = inboxDao.getUser(Integer.parseInt(userId));
				 	Consignment consignment = trackDao.getConsignment(Integer.parseInt(consignmentId));
				 	if(track != null)
				 	{
				 		String hash = blockChainFactoryImpl.tranfer(consignment, ApplicationCache.LOCATION.get(requstedToUser.getLocId()).getLocation(), ApplicationCache.LOCATION.get(requstedByUser.getLocId()).getLocation(), track.getHash());
				 		insertTrack = new Track(0, Integer.parseInt(consignmentId), itemRequest.getRequestedDate(), new Date(), requstedToUser.getLocId(), requstedByUser.getLocId(), requstedByUser.getUserId(), requstedToUser.getUserId(), hash, track.getHash(), requstedToUser, requstedByUser);
				 		
				 	}
				 	else
				 	{
				 		String hash = blockChainFactoryImpl.tranfer(consignment, ApplicationCache.LOCATION.get(requstedToUser.getLocId()).getLocation(), ApplicationCache.LOCATION.get(requstedByUser.getLocId()).getLocation(), "0");
				 		insertTrack = new Track(0, Integer.parseInt(consignmentId), itemRequest.getRequestedDate(), new Date(), requstedToUser.getLocId(), requstedByUser.getLocId(), requstedByUser.getUserId(), requstedToUser.getUserId(), hash, "0", requstedToUser, requstedByUser);
				 	}
					//-- insert into track with info
				 	inboxDao.insertTrack(insertTrack);
				}
			}
		return load(userId);
	}
	public String load(String user){	
		Map<Integer, List<Consignment>> consignment = inboxDao.consignment(user);
		User userObj = trackDao.getUser(Integer.parseInt(user));
		boolean isOrg = false;
		
		if(userObj.getTypeId() == 1)
			isOrg = true;

		List<ItemRequest> list = inboxDao.request(user);
		String html = " <div class='card mb-5'> "+
				" 	<div class='card-block p-0'> "+
				" 		<table class='table table-bordered table-sm m-0'> "+
				" 			<thead class='table-info' style='background-color: #47a3da;'> "+
				" 				<tr style='color: #fff;'> "+
				" 					<th>#</th> "+
				" 					<th>Item</th> "+
				" 					<th>Requested By</th> "+
				" 					<th>Quantity</th> "+
				" 					<th>Requested On</th> ";
		
				html+=	isOrg ?"":" 					<th>Consignment Status/Tag</th> ";
				html+=		" 				</tr> "+
				" 			</thead> "+
				" 			<tbody> ";
		
				String post =	" 			</tbody> "+
				" 			<tfoot> "+
				" 				<tr> "+
				" 					<th></th> "+
				" 					<th colspan='4'> "+
				" 						<button class='btn btn-default' onclick='approve()'>Approve</button> "+
				" 						<button class='btn btn-default' onclick='approveAll()'>Approve All</button> "+
				" 					</th> "+
				" 				</tr> "+
				" 			</tfoot> "+
				" 		</table> "+
				" 	</div> "+
				" </div> ";

		for(ItemRequest itemRequest:list)
		{
			// 
			boolean isCheckbox = false;
			String cid = "C";
			String tag = "Item is not availlable in stock, please request it from supplier hierarchy.";
			if(!isOrg)
			{
				if(consignment.containsKey(itemRequest.getItem()))
				{
					if(!consignment.get(itemRequest.getItem()).isEmpty())
					{
						cid = consignment.get(itemRequest.getItem()).get(0).getConsignmentId()+"";
						tag = consignment.get(itemRequest.getItem()).get(0).getTag();
						consignment.get(itemRequest.getItem()).remove(0);
						isCheckbox = true;
					}
				}
			}
			
			html+=	"<tr>";
					if(isCheckbox || isOrg)
						html+=	"<td><label class='custom-control custom-checkbox'> <input type='checkbox' class='custom-control-input' value='"+itemRequest.getRequestId()+":"+cid+":"+itemRequest.getItem()+"'> <span class='custom-control-indicator'></span> </label></td>";
					else
						html+=	"<td></td>";
					html+="<td>"+ApplicationCache.ITEMS.get(itemRequest.getItem()).getName()+"</td>"+
					"<td>"+ itemRequest.getSupplier() +"</td>"+
					"<td>"+ itemRequest.getQuantity() +"</td>"+
					"<td>"+ dateFormator("dd/MM/yyyy hh:mm:ss", itemRequest.getRequestedDate()) +"</td>";
					html+=	isOrg ?"":"<td>"+tag+"</td>";
					html+=	"</tr>";
		}
		
		return html+post;
	}
	
	public String dateFormator(String pattern, Date dateInput){
		
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dateInput);
		return date;
	}
}
