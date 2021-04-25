package com;
import model.Creator;
import model.Product; 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
import com.google.gson.*; 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/product") 
public class ProductServlet {
	
      Product productObj = new Product();
  
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 {    
 return productObj.displayProduct(); 
 } 


@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertCreator(@FormParam("name") String name, 
 @FormParam("price") String price, 
 @FormParam("availability") String availability, 
 @FormParam("details") String details)

{ 
 String output = productObj.insertProduct(name, price, availability, details); 
return output; 
}


@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCreator(String productdetails) 
{ 

 JsonObject productObject = new JsonParser().parse(productdetails).getAsJsonObject(); 
 
 String productid = productObject.get("productid").getAsString(); 
 String name = productObject.get("name").getAsString(); 
 String price = productObject.get("price").getAsString(); 
 String availability = productObject.get("availability").getAsString(); 
 String details = productObject.get("details").getAsString();  
 String output = productObj.updateProduct(productid, name, price, availability, details); 
return output;
} 


@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCreator(String productdetails) 
{ 

 Document doc = Jsoup.parse(productdetails, "", Parser.xmlParser()); 
 
 String productid = doc.select("productid").text(); 
 String output = productObj.deleteProduct(productid); 
return output; 
}

	

}
