package model;
import java.sql.*; 

public class Customer_IT19806532 {

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
	
public String insertCustomer (String cusname, String email, String address, String paymethod) 

{ 
String output = ""; 
try

{ 
Connection con = connect(); 
if (con == null) 
{return "Connection Error"; } 

String query = " insert into customer(`cusid`,`cusname`,`email`,`address`,`paymethod`)"
+ " values (?, ?, ?, ?, ?)"; 
PreparedStatement preparedStmt = con.prepareStatement(query); 

preparedStmt.setInt(1, 0); 
preparedStmt.setString(2, cusname); 
preparedStmt.setString(3, email); 
preparedStmt.setString(4, address); 
preparedStmt.setString(5, paymethod); 



preparedStmt.execute(); 
con.close(); 
output = "Added customer information"; 
} 
catch (Exception e) 
{ 
output = "Could not add customer details"; 
System.err.println(e.getMessage()); 
} 
return output; 
} 


public String displayCustomer()

{ 
String output = ""; 
try

{ 
Connection con = connect(); 

if (con == null) 
{return "Connection Error"; } 

output = "<table border='2'><tr><th>Name</th><th>Email</th>" +
"<th>Current Address</th>" + 
"<th>Default Payment Method</th>" +
"<th>Update</th><th>Delete</th></tr>"; 

String query = "select * from customer"; 
Statement stmt = con.createStatement(); 
ResultSet rs = stmt.executeQuery(query); 

while (rs.next()) 
	 
{ 
	 
String cusid = Integer.toString(rs.getInt("cusid")); 
String cusname = rs.getString("cusname"); 
String email = rs.getString("email"); 
String address = rs.getString("address"); 
String paymethod = rs.getString("paymethod"); 


output += "<tr><td>" + cusname + "</td>"; 
output += "<td>" + email + "</td>"; 
output += "<td>" + address + "</td>"; 
output += "<td>" + paymethod + "</td>"; 

output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
+ "<input name='itemID' type='hidden' value='" + cusid 
+ "'>" + "</form></td></tr>"; 

} 
con.close(); 

output += "</table>"; 

} 
catch (Exception e) 
{ 
output = "Error accessing the database for reading"; 
System.err.println(e.getMessage()); 
} 
return output; 
} 

public String updateCustomer (String cusid, String cusname, String email, String address, String paymethod) 
{ 
String output = ""; 

try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error accessing the database for updating"; } 

String query = "UPDATE customer SET cusname=?,email=?,address=?,paymethod=?  WHERE cusid=?"; 
PreparedStatement preparedStmt = con.prepareStatement(query); 

preparedStmt.setString(1, cusname); 
preparedStmt.setString(2, email); 
preparedStmt.setString(3, address); 
preparedStmt.setString(4, paymethod); 
preparedStmt.setInt(5, Integer.parseInt(cusid));

preparedStmt.execute(); 
con.close(); 
output = "Updated!"; 
} 
catch (Exception e) 
{ 
output = "Could not update :("; 
System.err.println(e.getMessage()); 
} 
return output; 
} 


public String deleteCustomer(String cusid) 
{ 
String output = ""; 
try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error accessing the database"; } 

String query = "delete from customer where cusid=?"; 

PreparedStatement preparedStmt = con.prepareStatement(query); 

preparedStmt.setInt(1, Integer.parseInt(cusid)); 

preparedStmt.execute(); 
con.close(); 

output = "Deleted!"; 
} 
catch (Exception e) 
{ 
output = "Could not delete :("; 
System.err.println(e.getMessage()); 
} 
return output; 
} 
	
	
	
	
}
