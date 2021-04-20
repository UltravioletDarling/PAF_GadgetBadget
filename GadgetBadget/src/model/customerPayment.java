package model;
import java.sql.*;

public class customerPayment {
	
	//database connectivity
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver"); 

	     //Provide the correct details: DBServer/DBName, username, password
	     con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "abc123"); 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	//insert payment details method
	public String insertPaymentd(String cardNo, String nameonCard, String expDate, String cvv)
	 {
		String output = "";
	 try
	 {
		 
	 Connection con = connect();
	 
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into customer_payment (`Card_Id`,`Card_No`,`Name_on_card`,`Exp_date`,`Cvv`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, cardNo);
	 preparedStmt.setString(3, nameonCard);
	 preparedStmt.setString(4, expDate);
	 preparedStmt.setString(5, cvv);
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while inserting the customer payment details.";
		 System.err.println(e.getMessage());
	 }
	 	return output;
	 } 
	
	//read  payment details method
	
	public String viewCusPayment()
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='2'><tr><th>Card ID</th><th>Card Number</th><th>Name on Card</th>" +
		 "<th>Expiration date</th>" +
		 "<th>Cvv</th>" +
		 "<th>Update</th><th>Remove</th></tr>";
	
		 String query = "select * from customer_payment";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 
		 while (rs.next())
		 {
			 
		 String cardId = Integer.toString(rs.getInt("Card_Id"));
		 String cardNo = rs.getString("Card_No");
		 String nameonCard = rs.getString("Name_on_card");
		 String expDate = rs.getString("Exp_date");
		 String cvv = rs.getString("Cvv");
		 
		 // Add into the html table
		 output += "<tr><td>" + cardId  + "</td>";
		 output += "<td>" + cardNo  + "</td>";
		 output += "<td>" + nameonCard + "</td>";
		 output += "<td>" + expDate + "</td>";
		 output += "<td>" + cvv  + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='customerPayment.jsp'>" 
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='cardId' type='hidden' value='" + cardId
		 + "'>" + "</form></td></tr>";
		 }
		 
		 con.close();
		 
		 // Complete the html table
		 output += "</table>";
		 }
		 	catch (Exception e)
		 {
		 output = "Error while reading the customer payment details.";
		 	System.err.println(e.getMessage());
		 }
		 return output;
	 }
	
	//Update customer payment details
	
	public String updateCusPayment (String cardId, String cardNo, String nameonCard, String expDate, String cvv) 
	{ 
	 String output = ""; 
	 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error accessing the database"; } 

	 String query = "UPDATE customer_payment SET Card_No=?,Name_on_card=?,Exp_date=?,Cvv=?  WHERE Card_Id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 

	 
	 preparedStmt.setString(1, cardNo); 
	 preparedStmt.setString(2, nameonCard); 
	 preparedStmt.setString(3, expDate); 
	 preparedStmt.setString(4, cvv); 
	 preparedStmt.setInt(5, Integer.parseInt(cardId)); 
	 

	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Successfully Updated"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Fail to update"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	//delete customer payment details
	
	public String deleteCusPayment(String Card_Id) 
	 { 
	 String output = ""; 
	 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error accessing the database"; } 

	 String query = "delete from customer_payment where Card_Id=?"; 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 

	 preparedStmt.setInt(1, Integer.parseInt(Card_Id)); 

	 preparedStmt.execute(); 
	 con.close(); 
	 
	 output = " Successfully Deleted"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Could not delete the project"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
