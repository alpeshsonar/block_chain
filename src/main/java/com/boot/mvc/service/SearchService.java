
package com.boot.mvc.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.mvc.model.ApplicationCache;
import com.boot.mvc.model.Consignment;
import com.boot.mvc.model.Item;
import com.boot.mvc.model.TagTrack;
import com.boot.mvc.model.Track;
import com.boot.mvc.repository.SearchDao;

@Service
public class SearchService {

	@Autowired
	SearchDao trackDao;
	
	@Autowired
	BlockChainFactoryImpl blockChainFactoryImpl;
	
	public String track(String tag)
	{			
		TagTrack tagTrack = new TagTrack();
		List<Track> tracks =  trackDao.track(tag);
		Consignment consignment = null;
		Item item = null;
		boolean present = false;
		if(tracks!=null && !tracks.isEmpty())
		{
			consignment = trackDao.getConsignment(tracks.get(0).getConsignmentId());
			tagTrack.setConsignment(consignment);
			if(consignment!=null)
			{
				item = trackDao.getItem(consignment.getItemId());
				tagTrack.setItem(item);
				present = true;
			}
			tagTrack.setTrack(tracks);
		}
		String html_1 = "<br /><h5>Consignment not found with tag \""+tag+"\"</h5>";
		String ul = "";
		if(present)
		{
			
			 html_1 = " <ul class='cbp_tmtimeline'> "+
							" 	<li> "+
							" 		<div class='cbp_tmlabel' style='margin: 0 0 0 0;'> "+
							" 			<h3>"+item.getName()+"</h3> "+
							" 			<div class='search-row'> "+
							" 				<div class='search-column' style='width: 30%;'> "+
							" 					<img src='/images/"+item.getImageLocation()+"' style='height: 100%; width: 100%;'> "+
							" 				</div> "+
							" 				<div class='search-column' style='width: 70%;'> "+
							" 	             " + item.getFeatures() + " "+
							"                " + item.getCharacteristics() + " "+
							" 				</div> "+
							" 			</div> "+
							" 		</div> "+
							" 	</li> "+
							" </ul> ";

			 ul = "<ul class='cbp_tmtimeline'>";
			for(Track track : tracks)
			{
				
				if(blockChainFactoryImpl.validate(consignment, ApplicationCache.LOCATION.get(track.getDepartLocId()).getLocation(), track.getPreviousHash()))
				{
					track.setStatus("OK");
				}
				else
				{
					track.setStatus("Consignment is tempered");
				}
				
				ul+=" <li> "+
						" 	<time class='cbp_tmtime' datetime='"+ dateFormator("yyyy-MM-dd HH:ss", track.getArrivalDate())+"'> "+
						" 		 <span>"+dateFormator("dd/MM/yyyy", track.getArrivalDate()) +"</span> <span>"+dateFormator("HH:ss", track.getArrivalDate())+"</span> "+
						" 	</time> "+
						" 	<div class='cbp_tmicon cbp_tmicon-earth'></div> "+
						" 	<div class='cbp_tmlabel'"+  (track.getStatus().equals("OK")?"":"style='background: #ee6c6c;'")+">"+
						" 		<h6>Supplier : "+track.getRequestedByUser().getUserName() + " - "+ ApplicationCache.USER_TYPE.get(track.getRequestedByUser().getTypeId()).getType() +"</h6> "+
						" 		<h6>Requester: "+track.getRequestedToUser().getUserName() + " - "+ ApplicationCache.USER_TYPE.get(track.getRequestedToUser().getTypeId()).getType() +"</h6> "+
						" 		<h6>Consignment departed from "+ ApplicationCache.LOCATION.get(track.getDepartLocId()).getLocation()+" at "+dateFormator("yyyy-MM-dd HH:ss", track.getDepartDate())+"</h6> "+
						" 		<h6>Consignment arrived at "+ ApplicationCache.LOCATION.get(track.getArrivalLocId()).getLocation() +" at "+ dateFormator("yyyy-MM-dd HH:ss", track.getArrivalDate())+"</h6> "+
						" 		<h6>Status : "+ track.getStatus()+"</h6> "+
						" 		<h6>Hash : "+track.getHash() +"</h6> "+
						" 	</div> "+
						" </li> ";
			}		
			ul+="</ul>";
		}
		return html_1 + ul;
	}
	
	
	public String dateFormator(String pattern, Date dateInput){
		
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dateInput);
		return date;
	}
}
