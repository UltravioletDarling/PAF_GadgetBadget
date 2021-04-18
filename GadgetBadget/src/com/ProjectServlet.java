package com;
import model.Project; 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
import com.google.gson.*; 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/project") 
public class ProjectServlet {
	
	Project projectObj = new Project();
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 {     //test
 return projectObj.displayProject(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertItem(@FormParam("name") String name, 
 @FormParam("category") String category, 
 @FormParam("estcost") String estcost, 
 @FormParam("esttime") String esttime) 
{ 
 String output = projectObj.insertProject(name, category, estcost, esttime); 
return output; 
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateProject(String projectinfo) 
{ 

 JsonObject projectObject = new JsonParser().parse(projectinfo).getAsJsonObject(); 
 
 String projectid = projectObject.get("projectid").getAsString(); 
 String name = projectObject.get("name").getAsString(); 
 String category = projectObject.get("category").getAsString(); 
 String estcost = projectObject.get("estcost").getAsString(); 
 String esttime = projectObject.get("esttime").getAsString(); 
 String output = projectObj.updateProject(projectid, name, category, estcost, esttime); 
return output;
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteItem(String projectinfo) 
{ 

 Document doc = Jsoup.parse(projectinfo, "", Parser.xmlParser()); 
 
 String projectid = doc.select("projectid").text(); 
 String output = projectObj.deleteProject(projectid); 
return output; 
}


}
