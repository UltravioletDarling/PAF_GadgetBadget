package com;

//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.progress_IT19216256;

@Path("/Progress")

public class progressServices_IT19216256 {

	progress_IT19216256 PObj = new progress_IT19216256();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProgress() // Read Progress Details
	{
		return PObj.readProgress();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProgress(@FormParam("researchName") String researchName, // Insert Progress Details
			@FormParam("progress") String progress)

	{
		String output = PObj.insertProgress(researchName, progress);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProgress(String progressData) // Update Progress Details
	{
		// Convert the input string to a JSON object
		JsonObject PObject = new JsonParser().parse(progressData).getAsJsonObject();
		// Read the values from the JSON object
		String progressID = PObject.get("progressID").getAsString();
		String researchName = PObject.get("researchName").getAsString();
		String progress = PObject.get("progress").getAsString();
		String output = PObj.updateProgress(progressID, researchName, progress);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProgress(String progressData) // Delete Progress Details
	{
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(progressData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String progressID = doc.select("progressID").text();
		String output = PObj.deleteProgress(progressID);
		return output;
	}

}
