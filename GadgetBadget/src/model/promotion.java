package model;

import java.sql.*;


public class promotion {
	
	
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
	public String insertpromotions(String name, String type, double discount, String stdate, String eddate)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into promotions (`promotionID`,`name`,`type`,`discount`,`stdate`,`eddate`)"
	 + " values (?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, type);
	 preparedStmt.setDouble(4, discount);
	 preparedStmt.setString(5, stdate);
	 preparedStmt.setString(6, eddate);
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the promotions.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String readpromotions()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Promotion Name</th><th>Promotion Type </th>" +
	 "<th>Promotion Percentage </th>" +
	 "<th>Promotion start date</th>" +
	 "<th>Promotion end date</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from promotionsg";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String promotionID = Integer.toString(rs.getInt("researchID"));
	 String promotionName = rs.getString("researchName");
	 String promotiontype = rs.getString("promotiontype");
	 String promotiondiscount = Double.toString(rs.getDouble("promotiondiscount"));
	 String promotionstdate = rs.getString("promotionstdate");
	 String promotioneddate = rs.getString("promotioneddate");
	 // Add into the html table
	 output += "<tr><td>" + promotionName  + "</td>";
	 output += "<td>" +promotiontype + "</td>";
	 output += "<td>" + promotiondiscount + "</td>";
	 output += "<td>" + promotionstdate + "</td>";
	 output += "<td>" + promotioneddate  + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='promotion.jsp'>" 
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + promotionID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the promotions.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateProject(String promotionID, String name, String type, String stdate, String eddate){
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
		 preparedStmt.setString(2, type);
		 String discount = null;
		preparedStmt.setDouble(3, Double.parseDouble(discount));
		 preparedStmt.setString(4, stdate);
		 preparedStmt.setString(5, eddate);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the promotions.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	public String deletepromotions(String promotionID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from research where  promotionID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(promotionID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Promotion.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String displaypromotion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
