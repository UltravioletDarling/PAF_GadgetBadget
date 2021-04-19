package com;

import model.progress;
import model.research;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Progress")


public class progressServices {
	
	progress PObj = new progress();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	public String readProgress()
	 {
		 return PObj.readProgress(); 
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProgress(@FormParam("researchName") String researchName, 
	 @FormParam("progress") String progress) 
	
	{ 
	 String output = PObj.insertProgress(researchName,progress ); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProgress(String progressData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject PObject = new JsonParser().parse(progressData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String progressID = PObject.get("progressID").getAsString(); 
	 String researchName = PObject.get("researchName").getAsString(); 
	 String progress  = PObject.get("progress").getAsString(); 
	 String output = PObj.updateProgress(progressID,researchName ,progress); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProgress(String progressData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(progressData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String progressID = doc.select("progressID").text(); 
	 String output = PObj.deleteProgress(progressID); 
	return output; 
	}


}
