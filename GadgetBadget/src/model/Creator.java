package model;
import java.sql.*; 

public class Creator {

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
	
public String insertCreator (String name, String city, String contactnum, String email, String fieldofinterest, String budget) 

{ 
String output = ""; 
try

{ 
Connection con = connect(); 
if (con == null) 
{return "Connection Error"; } 

 String query = " insert into creator(`creatorid`,`fullname`,`city`,`contactnum`,`email`,`fieldofinterest`,`currentbudget`)"
 + " values (?, ?, ?, ?, ?,?,?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 

 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, city); 
 preparedStmt.setInt(4, Integer.parseInt(contactnum)); 
 preparedStmt.setString(5, email); 
 preparedStmt.setString(6, fieldofinterest); 
 preparedStmt.setDouble(7, Double.parseDouble(budget)); 
 

 preparedStmt.execute(); 
 con.close(); 
 output = "Information Added"; 
 } 
 catch (Exception e) 
 { 
 output = "Could not insert"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String displayCreator()

{ 
 String output = ""; 
 try
 
 { 
 Connection con = connect(); 
 
 if (con == null) 
 {return "Connection Error"; } 

 output = "<table border='2'><tr><th>Name</th><th>City</th>" +
 "<th>Contact Number</th>" + 
 "<th>Email</th>" +
 "<th>Field Of Interest</th>" +
 "<th>Current Budget</th>" +
 "<th>Update</th><th>Delete</th></tr>"; 
 
 String query = "select * from creator"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 
 while (rs.next()) 
	 
 { 
	 
 String creatorid = Integer.toString(rs.getInt("creatorid")); 
 String name = rs.getString("fullname"); 
 String city = rs.getString("city"); 
 String contactnum = Integer.toString(rs.getInt("contactnum"));
 String email = rs.getString("email"); 
 String fieldofinterest = rs.getString("fieldofinterest"); 
 String budget = Double.toString(rs.getDouble("currentbudget")); 


 output += "<tr><td>" + name + "</td>"; 
 output += "<td>" + city + "</td>"; 
 output += "<td>" + contactnum + "</td>"; 
 output += "<td>" + email + "</td>"; 
 output += "<td>" + fieldofinterest + "</td>"; 
 output += "<td>" + budget + "</td>"; 

 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + creatorid 
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

public String updateCreator (String creatorid, String name, String city, String contactnum, String email, String fieldofinterst, String budget) 
{ 
 String output = ""; 
 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error accessing the database"; } 

 String query = "UPDATE creator SET fullname=?,city=?,contactnum=?,email=?,fieldofinterest=?,currentbudget=?  WHERE creatorid=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 

 preparedStmt.setString(1, name); 
 preparedStmt.setString(2, city); 
 preparedStmt.setInt(3, Integer.parseInt(contactnum));
 preparedStmt.setString(4, email); 
 preparedStmt.setString(5, fieldofinterst); 
 preparedStmt.setDouble(6, Double.parseDouble(budget)); 
 preparedStmt.setInt(7, Integer.parseInt(creatorid));
 
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


public String deleteCreator(String creatorid) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error accessing the database"; } 

 String query = "delete from creator where creatorid=?"; 
 
 PreparedStatement preparedStmt = con.prepareStatement(query); 

 preparedStmt.setInt(1, Integer.parseInt(creatorid)); 

 preparedStmt.execute(); 
 con.close(); 
 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Could not delete the user"; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
	
	
}
