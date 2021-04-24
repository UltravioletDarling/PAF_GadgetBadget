package com;
import model.Customer_IT19806532; 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
import com.google.gson.*; 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/customer") 
public class CustomerServlet_IT19806532 {
	
	Customer_IT19806532 customerObj = new Customer_IT19806532();

@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 {    
 return customerObj.displayCustomer(); 
 } 


@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertCustomer(@FormParam("cusname") String cusname, 
 @FormParam("email") String email, 
 @FormParam("address") String address,
 @FormParam("paymethod") String paymethod)

{ 
 String output = customerObj.insertCustomer(cusname, email, address, paymethod); 
return output; 
}



@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCustomer(String customerdetails) 
{ 

 JsonObject customerObject = new JsonParser().parse(customerdetails).getAsJsonObject(); 
 
 String cusid = customerObject.get("cusid").getAsString(); 
 String cusname = customerObject.get("cusname").getAsString(); 
 String email = customerObject.get("email").getAsString(); 
 String address = customerObject.get("address").getAsString(); 
 String paymethod = customerObject.get("paymethod").getAsString(); 
 String output = customerObj.updateCustomer(cusid, cusname, email, address, paymethod); 
return output;
} 
	

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCreator(String customerdetails) 
{ 

 Document doc = Jsoup.parse(customerdetails, "", Parser.xmlParser()); 
 
 String cusid = doc.select("creatorid").text(); 
 String output = customerObj.deleteCustomer(cusid); 
return output; 
}	
	
	
	
	
}
