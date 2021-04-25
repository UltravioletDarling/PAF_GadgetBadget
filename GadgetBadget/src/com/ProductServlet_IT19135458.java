package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Product_IT19135458;

@Path("/product")
public class ProductServlet_IT19135458 {

	Product_IT19135458 productObj = new Product_IT19135458();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return productObj.displayProduct();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCreator(@FormParam("name") String name, @FormParam("price") String price,
			@FormParam("availability") String availability, @FormParam("details") String details)

	{
		String output = productObj.insertProduct(name, price, availability, details);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCreator(String productdetails) {

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
	public String deleteCreator(String productdetails) {

		Document doc = Jsoup.parse(productdetails, "", Parser.xmlParser());

		String productid = doc.select("productid").text();
		String output = productObj.deleteProduct(productid);
		return output;
	}

}
