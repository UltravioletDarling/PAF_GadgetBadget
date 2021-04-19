package model;

import java.sql.*;


public class research {
	
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver"); 

	    

	        //Provide the correct details: DBServer/DBName, username, password
	     con = DriverManager.getConnection("jdbc:mysql://localhost/gadgetbadget", "root", ""); 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertResearch(String name, String des, String price, String date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into research (`researchID`,`researchName`,`researchDescription`,`researchPrice`,`researchDate`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, des);
	 preparedStmt.setDouble(4, Double.parseDouble(price));
	 preparedStmt.setString(5, date);
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the Research.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String readResearch()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Research Name</th><th>Research Description</th>" +
	 "<th>Research Price</th>" +
	 "<th>Research Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from research";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String researchID = Integer.toString(rs.getInt("researchID"));
	 String researchName = rs.getString("researchName");
	 String researchDescription = rs.getString("researchDescription");
	 String researchPrice = Double.toString(rs.getDouble("researchPrice"));
	 String researchDate = rs.getString("researchDate");
	 // Add into the html table
	 output += "<tr><td>" + researchName  + "</td>";
	 output += "<td>" + researchDescription + "</td>";
	 output += "<td>" + researchPrice + "</td>";
	 output += "<td>" + researchDate  + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='research.jsp'>" 
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + researchID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the research.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateResearch(String ID, String name, String des, String price, String date){
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE research SET researchName=?,researchDescription=?,researchPrice=?,researchDate=? WHERE researchID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, des);
		 preparedStmt.setDouble(3, Double.parseDouble(price));
		 preparedStmt.setString(4, date);
		 preparedStmt.setInt(5, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the research.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	public String deleteResearch(String researchID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from research where  researchID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(researchID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the research.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

}
