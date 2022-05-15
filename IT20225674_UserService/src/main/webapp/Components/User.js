$(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------

	
	 $("#alertSuccess").text("");
 	 $("#alertSuccess").hide();
 	 $("#alertError").text("");
 	 $("#alertError").hide();
 	 
 	 
   	// Form validation-------------------
  	
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 
         return;
    }
 
	// If valid------------------------
	
	
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";

	 $.ajax(
 	 {
 		url : "UserAPI",
 		type : type,
 		data : $("#formUser").serialize(),
 		dataType : "text",
	    complete : function(response, status)
        {
   
      			onItemSaveComplete(response.responseText, status);
	    }
	    
     });
     
});
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 
	 	 if (resultSet.status.trim() == "success")
		 {
 				$("#alertSuccess").text("Successfully saved.");
		    	$("#alertSuccess").show();
 				$("#divUserGrid").html(resultSet.data);
 			
 	 	  } else if (resultSet.status.trim() == "error")
 	 	  {
 	 
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
		  }
		  
    } else if (status == "error")
    {
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 			
 	} else
 	{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
    } 

 	$("#hidUserIDSave").val("");
	 $("#formUser")[0].reset();
}
$(document).on("click", ".btnUpdate", function(event)
{
		var id = event.target.id;
		$("#hidUserIDSave").val(id.substring(0, id.length-1));
 		$("#UserName").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#UserNIC").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#UserAddress").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#UserPhone").val($(this).closest("tr").find('td:eq(3)').text());
});
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 	{
 		url : "UserAPI",
 		type : "DELETE",
	    data : "UserID=" + $(this).data("UserID"),
 		dataType : "text",
 		complete : function(response, status)
		{
			 onItemDeleteComplete(response.responseText, status);
 		}
	 });
});



function onItemDeleteComplete(response, status)
{
	if (status == "success")
    {
 			var resultSet = JSON.parse(response);
 			
		   if (resultSet.status.trim() == "success")
 		   {
 		   
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				
			    $("#divUserGrid").html(resultSet.data);
			    
			    setTimeout( (function(){alert(43)}, 1000));
 			} else if (resultSet.status.trim() == "error")
 			{
				 $("#alertError").text(resultSet.data);
 				 $("#alertError").show();
		    }
 	} else if (status == "error")
    {
		 $("#alertError").text("Error while deleting.");
 		 $("#alertError").show();
    } else
    {
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}
function validateItemForm()
{
	// User NAME
	if ($("#UserName").val().trim() == "")
 	{
		 return "Insert  User Name.";
    }
    
    
	// User TYPE
	if ($("#UserNIC").val().trim() == "")
    {
		 return "Insert  User NIC.";
 	} 
 	

	// UNIT COST-------------------------------
	if ($("#UserAddress").val().trim() == "")
    {
 		return "Insert  User Address.";
 	}
 	
 	
	
	// MAX UNIT------------------------
	if ($("#UserPhone").val().trim() == "")
   {
		 return "Insert User Phone.";
   }
   
  
   return true;
}
