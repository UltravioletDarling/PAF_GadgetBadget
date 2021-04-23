package model;
import java.sql.*;

public class progress_IT19216256 {
	
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
	
	//Insert Progress Details 
	public String insertProgress(String name, String pro)
	 {
	   String output = "";
	   try
	  {
	    Connection con = connect();
	    if (con == null)
	   {return "Error while connecting to the database for inserting."; }
	   // create a prepared statement
	   String query = " insert into progress(`progressID`,`researchName`,`progress`)"
	   + " values (?, ?, ?)";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	  // binding values
	   preparedStmt.setInt(1, 0);
	   preparedStmt.setString(2, name);
	   preparedStmt.setString(3, pro);
	 
	 
	  preparedStmt.execute();
	  con.close();
	  output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the progress.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	//Read Progress Details 
	public String readProgress()
	 {
	    String output = "";
	   try
	  {
	     Connection con = connect();
	     if (con == null)
	    {return "Error while connecting to the database for reading."; }
	    // Prepare the html table to be displayed
	    output = "<table border='1'><tr><th>Research Name</th><th>Progress</th>" + "<th>Update</th><th>Remove</th></tr>";

	   String query = "select * from progress "; 
	   Statement stmt = con.createStatement();
	   ResultSet rs = stmt.executeQuery(query);
	 
	  // iterate through the rows in the result set
	  while (rs.next())
	  {
	     String progressID = Integer.toString(rs.getInt("progressID"));
	     String researchName = rs.getString("researchName");
	     String progress = rs.getString("progress");


	 
	 // Add into the html table
	 output += "<tr><td>" + researchName  + "</td>";
	 output += "<td>" + progress + "</td>";
	 
	
	 
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='progress.jsp'>" 
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='progressID' type='hidden' value='" + progressID + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the progress.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	//Update Progress Details 
	public String updateProgress(String ID, String name, String pro){
		 String output = "";
		 try
		 {
		    Connection con = connect();
		    if (con == null)
		    {return "Error while connecting to the database for updating."; }
		   // create a prepared statement
		    String query = "UPDATE progress SET researchName=?,progress=? WHERE progressID=?";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		   // binding values
		   preparedStmt.setString(1, name);
		   preparedStmt.setString(2, pro);
		   preparedStmt.setInt(3, Integer.parseInt(ID));
		   // execute the statement
		   preparedStmt.execute();
		   con.close();
		   output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the progress.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	//Delete Progress Details 
	public String deleteProgress(String progressID)
	 {
	   String output = "";
	   try
	  {
	    Connection con = connect();
	    if (con == null)
	    {return "Error while connecting to the database for deleting."; }
	    // create a prepared statement
	    String query = "delete from progress where  progressID=?";
	    PreparedStatement preparedStmt = con.prepareStatement(query);
	   // binding values
	    preparedStmt.setInt(1, Integer.parseInt(progressID));
	  // execute the statement
	    preparedStmt.execute();
	    con.close();
	   output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the progress.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

}
