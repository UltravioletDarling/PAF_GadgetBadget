//IT19223322
//K.A.S.J Ranasinghe

package com;
import model.customerPayment;

//for REST service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//for JSON
import com.google.gson.*;
//for XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



	
	@Path("/customer_payment") 
	public class customerPaymentServlet {
		
	customerPayment paymentObj = new customerPayment();
	
	//view
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCusPayment() 
	 {     //test
	 return paymentObj.viewCusPayment(); 
	 } 

	//insert
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCusPayment(@FormParam("Card_No") String cardNo, 
	 @FormParam("Name_on_card") String nameonCard, 
	 @FormParam("Exp_date") String expDate, 
	 @FormParam("Cvv") String cvv) 
	
	{ 
	 String output = paymentObj.insertPaymentd(cardNo, nameonCard, expDate, cvv); 
	return output; 
	}
	
	//update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustPayment(String cuspaymentInfo) 
	{ 

	 JsonObject paymentObject = new JsonParser().parse(cuspaymentInfo).getAsJsonObject(); 
	 
	 String cardID = paymentObject.get("Card_Id").getAsString();
	 String cardNo = paymentObject.get("Card_No").getAsString(); 
	 String nameonCard = paymentObject.get("Name_on_card").getAsString(); 
	 String expDate = paymentObject.get("Exp_date").getAsString(); 
	 String cvv = paymentObject.get("Cvv").getAsString(); 
	 
	 String output = paymentObj.updateCusPayment(cardID,cardNo, nameonCard, expDate, cvv); 
	return output;
	}

	
	//delete
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCusDetails(String cuspaymentinfo) 
	{ 

	 Document doc = Jsoup.parse(cuspaymentinfo, "", Parser.xmlParser()); 
	 
	 String Card_Id = doc.select("Card_Id").text(); 
	 String output = paymentObj.deleteCusPayment(Card_Id); 
	return output; 
	}

}

