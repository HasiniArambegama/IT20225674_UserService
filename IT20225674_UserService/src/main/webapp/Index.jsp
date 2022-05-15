
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.User"%>



<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>User Service Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>
<div class = "container">
<div class = "row">
<div class = "col">
	<h1>User Service Management</h1>
	
	<form id="formUser" name="formUser" method="post" action="Index.jsp">
	
		 Username:
		 <input id="UserName" name="UserName" type="text"
 						class="form-control form-control-sm">
 						
		<br> User NIC:
		<input id="UserNIC" name="UserNIC" type="text"
 						class="form-control form-control-sm">
 						
		<br> User Address:
		<input id="UserAddress" name="UserAddress" type="text"
 						class="form-control form-control-sm">
 										
		<br> User Phone:
		<input id="UserPhone" name="UserPhone" type="text"
						 class="form-control form-control-sm">
						 
		<br>
	<input id="btnSave" name="btnSave" type="button" value="Save"
	class="btn btn-primary">
						 
		<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success">
 	
 		
 	</div>
 	<div id = "alertError" class="alert-danger"></div>
	
	<br>
	<br>
	
	<div id ="divUserGrid">
	<%
	 User userObj = new User(); 
	 out.print(userObj.readUser()); 
	%>
	</div>

</body>
</html> 