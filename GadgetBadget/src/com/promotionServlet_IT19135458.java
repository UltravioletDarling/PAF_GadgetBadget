package com;


import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.promotion; 

@Path("/promotion")
public class promotionServlet_IT19135458 {

	promotion promotionObj = new promotion();
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 {     
	 return promotionObj.displaypromotion(); 
	 } 

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("name") String name, 
	 @FormParam("type") String type, 
	 @FormParam("discount")double discount,
	 @FormParam("start_date") String stdate, 
	 @FormParam("end_date") String eddate) 
	{ 
	 String output = promotionObj.insertpromotions(name, type, discount, stdate, eddate); 
	return output; 
	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatepromotion(String promotioninfo) 
	{ 

	 JsonObject promotionObject = new JsonParser().parse(promotioninfo).getAsJsonObject(); 
	 
	//Read the values 
	 String promotionID= promotionObject.get("promotionid").getAsString(); 
	 String name = promotionObject.get("name").getAsString(); 
	 String type = promotionObject.get("type").getAsString(); 
	 double discount = promotionObject.get("discount").getAsDouble();
	 String stdate = promotionObject.get("stdate").getAsString(); 
	 String eddate = promotionObject.get("eddate").getAsString(); 
	 String output = promotionObj.updateProject(promotionID, name, type, stdate, eddate); 
	return output;
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String promotioninfo) 
	{ 
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(promotioninfo, "", Parser.xmlParser()); 
	 
	//Read the value from the element
	 String promotionID = doc.select("promotionID").text(); 
	 String output = promotionObj.deletepromotions(promotionID); 
	return output; 
	}


}
