package controllers;

import play.*;
import play.mvc.*;


import java.util.*;
import java.util.List;

import models.*;

public class Application extends Controller {

      public static void show (Long id) {
        AuctionItem item= AuctionItem.findById(id);

        item.viewCount++;
        item.save();
        render(item);
      }
       
      public static void search(String search){
        List<AuctionItem> results= AuctionItem.search(search);
        render(results, search);
      }


    public static void doCreateItem( AuctionItem item, int days ){
       item.startDate= new Date ();
       item.endDate= new Date (System.currentTimeMillis() +
        (days * 24 * 60 * 60 * 1000));

      item.save();
      show(item.id); 
      
    }

    public static void createAuctionItem() {
      render();
    }
    public static void index() {
      List<AuctionItem> mostPopular=AuctionItem.getMostPopular(5);
      List<AuctionItem> endingSoon=AuctionItem.getEndingSoon(5);

        render(mostPopular, endingSoon);
    }
    public static void sayHello (String myName) {
      render(myName);
    }
    
//    public static void show(Long id) {
 //   	Article article = Article.findById(Id);
 //   	render (article);
  //  }

 //   public static void create(Article article) {
    	//first save the article to the database
   // 	article.save();
    	//now show the article
    //	show(article.id);
  //  }

//    public static String chop (String a, int size) {
 //   	return s.substring(size);
 //   }

}