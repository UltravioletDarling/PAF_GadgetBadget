package model;
import java.sql.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {

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
	
public String insertProduct (String name, String price, String availability, String details) 

{ 
String output = ""; 
try

{ 
Connection con = connect(); 
if (con == null) 
{return "Connection Error"; } 

String query = " insert into product(`productid`,`name`,`price`,`availability`,`details`)"
+ " values (?, ?, ?, ?, ?)"; 
PreparedStatement preparedStmt = con.prepareStatement(query); 

preparedStmt.setInt(1, 0); 
preparedStmt.setString(2, name); 
preparedStmt.setDouble(3, Double.parseDouble(price)); 
preparedStmt.setString(4, availability); 
preparedStmt.setString(5, details); 



preparedStmt.execute(); 
con.close(); 
output = "Product Added"; 
} 
catch (Exception e) 
{ 
output = "Could not add product"; 
System.err.println(e.getMessage()); 
} 
return output; 
} 


public String displayProduct()

{ 
String output = ""; 
try

{ 
Connection con = connect(); 

if (con == null) 
{return "Error connecting to the database to retrive product information"; } 

output = "<table border='2'><tr><th>Name</th><th>Price</th>" +
"<th>Availability</th>" + 
"<th>Details</th>" +
"<th>Update</th><th>Delete</th></tr>"; 

String query = "select * from product"; 
Statement stmt = con.createStatement(); 
ResultSet rs = stmt.executeQuery(query); 

while (rs.next()) 
	 
{ 
	 
String productid = Integer.toString(rs.getInt("productid")); 
String name = rs.getString("name"); 
String price = Double.toString(rs.getDouble("price")); 
String availability = rs.getString("availability"); 
String details = rs.getString("details"); 



output += "<tr><td>" + name + "</td>"; 
output += "<td>" + price + "</td>"; 
output += "<td>" + availability + "</td>"; 
output += "<td>" + details + "</td>"; 

output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
+ "<input name='itemID' type='hidden' value='" + productid 
+ "'>" + "</form></td></tr>"; 

} 
con.close(); 

output += "</table>"; 

} 
catch (Exception e) 
{ 
output = "Error accessing the database to retrive product details"; 
System.err.println(e.getMessage()); 
} 
return output; 
} 

public String updateProduct (String productid, String name, String price, String availability, String details) 
{ 
String output = ""; 

try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error accessing the database for updating product details"; } 

String query = "UPDATE product SET name=?,price=?,availability=?,details=?  WHERE productid=?"; 
PreparedStatement preparedStmt = con.prepareStatement(query); 

preparedStmt.setString(1, name); 
preparedStmt.setDouble(2, Double.parseDouble(price)); 
preparedStmt.setString(3, availability); 
preparedStmt.setString(4, details); 

preparedStmt.setInt(7, Integer.parseInt(productid));

preparedStmt.execute(); 
con.close(); 
output = "Updated successfully"; 
} 
catch (Exception e) 
{ 
output = "Could not update product details"; 
System.err.println(e.getMessage()); 
} 
return output; 
} 


public String deleteProduct(String productid) 
{ 
String output = ""; 
try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error accessing the database for deleting the product"; } 

String query = "delete from product where productid=?"; 

PreparedStatement preparedStmt = con.prepareStatement(query); 

preparedStmt.setInt(1, Integer.parseInt(productid)); 

preparedStmt.execute(); 
con.close(); 

output = "Deleted successfully"; 
} 
catch (Exception e) 
{ 
output = "Could not delete the product"; 
System.err.println(e.getMessage()); 
} 
return output; 
} 
	
}
