package model;
import java.sql.*; 

public class Project {
    //Create Data Base Connection
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 con = DriverManager.getConnection("jdbc:mysql://localhost/gadgetbadget", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
//Insert Details 	
public String insertProject (String name, String category, String estcost, String esttime) 

{ 
String output = ""; 
try

{ 
Connection con = connect(); 
if (con == null) 
{return "Connection Error"; } 

 String query = " insert into project(`projectid`,`name`,`category`,`estcoset`,`esttime`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 

 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, category); 
 preparedStmt.setDouble(4, Double.parseDouble(estcost)); 
 preparedStmt.setString(5, esttime); 

 preparedStmt.execute(); 
 con.close(); 
 output = "New Project Added"; 
 } 
 catch (Exception e) 
 { 
 output = "Could not create a new project"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 

//Display Details 
public String displayProject()

{ 
 String output = ""; 
 try
 
 { 
 Connection con = connect(); 
 
 if (con == null) 
 {return "Connection Error"; } 

 output = "<table border='2'><tr><th>Project Name</th><th>Category</th>" +
 "<th>Estimated cost</th>" + 
 "<th>Est.time to completion</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from project"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 
 while (rs.next()) 
	 
 { 
	 
 String projectid = Integer.toString(rs.getInt("projectid")); 
 String projectname = rs.getString("name"); 
 String category = rs.getString("category"); 
 String estcost = Double.toString(rs.getDouble("estcost")); 
 String esttime = rs.getString("esttime"); 

 output += "<tr><td>" + projectname + "</td>"; 
 output += "<td>" + category + "</td>"; 
 output += "<td>" + estcost + "</td>"; 
 output += "<td>" + esttime + "</td>"; 

 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + projectid 
 + "'>" + "</form></td></tr>"; 
 
 } 
 con.close(); 
 
 output += "</table>"; 
 
 } 
 catch (Exception e) 
 { 
 output = "Error accessing the database"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 

public String updateProject (String projectid, String name, String category, String estcost, String esttime) 
{ 
 String output = ""; 
 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error accessing the database"; } 

 String query = "UPDATE project SET projectid=?,name=?,category=?,estcost=?  WHERE projectid=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 

 preparedStmt.setString(1, name); 
 preparedStmt.setString(2, category); 
 preparedStmt.setDouble(3, Double.parseDouble(estcost)); 
 preparedStmt.setString(4, esttime); 
 preparedStmt.setInt(5, Integer.parseInt(projectid)); 

 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Could not update"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String deleteProject(String projectid) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error accessing the database"; } 

 String query = "delete from item where projectid=?"; 
 
 PreparedStatement preparedStmt = con.prepareStatement(query); 

 preparedStmt.setInt(1, Integer.parseInt(projectid)); 

 preparedStmt.execute(); 
 con.close(); 
 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Could not delete the project"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
	
	
}
