package models;
 import play.db.jpa.Model;
 import javax.persistence.*;
 import java.util.*;


import java.util.List;



 @Entity
 public class AuctionItem extends Model {

 	public String title;
 	public Date startDate;
 	public Date endDate;
 	public Float deliveryCoat;
 	public Float startBid;
 	public Float buyNowPrice;
 	public Boolean buyNowEnabled;

 	@Column (length=4096)
 	public String description;
 	public Integer viewCount=0;

 	public Float getCurrentTopBid(){
 	return startBid;
    }
    public static List<AuctionItem> getMostPopular(int maxItems){
    	return find("endDate > ? order by viewCount DESC", new Date()).fetch(maxItems);
    }
    public static List<AuctionItem> getEndingSoon(int maxItems){
    	return find("endDate > ? order by endDate ASC", new Date()).fetch(maxItems);
    }
     
     public static List<AuctionItem> search(String search){
     	String likeSearch = "%"+search+"%";
     	return find("title like? OR description like ? AND endDate > ? order by " + "endDate ASC", 
     		likeSearch, likeSearch, new Date()).fetch();
     }
}