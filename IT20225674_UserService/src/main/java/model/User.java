package model;

import java.sql.*;

public class User
{	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogride", "root", "#Group20");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	public String readUser()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Name</th>" +
			"<th>User NIC</th>" +
			"<th>User Address</th>" +
			"<th>User Phone</th> " +
			"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String UserID = Integer.toString(rs.getInt("UserID"));
				String UserName = rs.getString("UserName");
				String UserNIC = rs.getString("UserNIC");
				String UserAddress = rs.getString("UserAddress");
				String UserPhone = rs.getString("UserPhone");
				
				// Add into the html table
				//output += "<tr><td><input id ='hidItemIDUpdate' name ='hidItemIDUpdate' type='hidden' value='" + UserID + " '>"	+ Name + "</td>";
				
				output += "<tr><td>" + UserName + "</td>";
				output += "<td>" + UserNIC + "</td>";
				output += "<td>" + UserAddress + "</td>";
				output += "<td>" + UserPhone + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' id ='" + UserID + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
				 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-UserID='"+ UserID + " '>" + "</td></tr>";  
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the User Users.";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String insertUser(String UserName, String UserNIC, String UserAddress, String UserPhone)
	{
		String output = "";
	
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
		
			
			// create a prepared statement
			String query = " insert into user "
						+ "(`UserID`,`UserName`,`UserNIC`,`UserAddress`,`UserPhone`)"
						+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, UserName);
			preparedStmt.setString(3, UserNIC);
			preparedStmt.setString(4, UserAddress);
			preparedStmt.setString(5, UserPhone);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Inserted successfully";
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
		}
		catch (Exception e)
		{
			//output = "Error while inserting the User User.";
			//System.err.println(e.getMessage());
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the User User.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String updateUser(int UserID, String UserName, String UserNIC, String UserAddress, String UserPhone)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating."; 
			}
			
			// create a prepared statement
			String query = "UPDATE user SET UserName=?,UserNIC=?,UserAddress=?,UserPhone=?"
			+ "WHERE UserID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, UserName);
			preparedStmt.setString(2, UserNIC);
			preparedStmt.setString(3, UserAddress);
			preparedStmt.setString(4, UserPhone);
			preparedStmt.setInt(5, UserID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Updated successfully";
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
		}
		catch (Exception e)
		{
			//output = "Error while updating the User User.";
			//System.err.println(e.getMessage());
			output = "{\"status\":\"error\", \"data\":\"Error while updating the User .\"}";
			System.err.println(e.getMessage()); 
		}
		
		return output;	
	}
	
	public String deleteUser(int UserID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			
			// create a prepared statement
			String query = "delete from user where UserID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			//preparedStmt.setInt(1, Integer.parseInt(UserID)); 
			preparedStmt.setInt(1, UserID); 
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Deleted successfully";
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
		}
		catch (Exception e)
		{
			//output = "Error while deleting the User User";
			//System.err.println(e.getMessage());
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the User .\"}"; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
		
	}
}
		

