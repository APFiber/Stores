<%@page import="bean.USER_MASTER"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="bean.USER_COMMONDATA"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Add User</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script type="text/javascript">
	//To save the user details
	
   function userSave()
  {
      var man=mandatoryUsercheck();
      if(man)
      {
      document.forms[0].action='USER_CONTROLLER?Option=3';
	  document.forms[0].submit();
	}
}
// to clear the fields in User.jsp page
	function userResetErrors()
  {
    document.getElementById("err_USERNAME").innerHTML="";
    document.getElementById("err_USERLOGINID").innerHTML="";
	document.getElementById("err_PASSWORD").innerHTML="";
	document.getElementById("err_ROLE").innerHTML="";
	document.getElementById("err_STATUS").innerHTML="";
	}

// to go User.jsp page to userGrd.jsp
	function exitAddUser()
  {
   	document.forms[0].action='USER_CONTROLLER?Option=1';
 	document.forms[0].submit();
  } 
// to check mandatorty fields check in User.page

	function mandatoryUsercheck()
	{
	var status;
    status=true;
    userResetErrors();
	     if(userDtls.USER_NAME.value.length==0){
	          document.getElementById("err_USERNAME").innerHTML="Enter the user Name";  
	           status=false;
	     }
	     
	     if(userDtls.USERLOGINID.value.length==0){
	         document.getElementById("err_USERLOGINID").innerHTML="Enter the  user Loginid";
	         status=false;
	       }
	     
	     if(userDtls.USER_PASSWORD.value.length==0){
	         document.getElementById("err_PASSWORD").innerHTML="Enter the password";
	         status=false;
	     }
	     
	     if(userDtls.USER_ROLE1.value=="")
       {   
	       document.getElementById("err_ROLE").innerHTML="Select user role from the list ";
	       status=false;
       }
       if(userDtls.USERSTATUS.value=="")
       {
       document.getElementById("err_STATUS").innerHTML="Select status from the list";
       status=false;
       }
	     return status;
	 }
	  // function checking for firstname field
	  function  userUserNameCheck(NAME)
  {
	document.getElementById("err_USERNAME").innerHTML = "";
	if (NAME.value.length > 0) 
	{
		var regex1=/^[A-Za-z/s ]+$/;    //this is the pattern of regular expersion
		if (regex1.test(NAME.value) == false)
		{
			document.getElementById("err_USERNAME").innerHTML = "First name should be alphabet only";
			document.userDtls.USER_NAME.value="";
		}
	}
	else  document.getElementById("err_USERNAME").innerHTML = "Enter the first name";
}
 
 // loginid feild check
   function userLoginIdCheck(LOGINID)
 {

	document.getElementById("err_USERLOGINID").innerHTML = "";
	if (LOGINID.value.length > 0) 
{
		var regex1=/^[A-Za-z/s ]+$/;    //this is the pattern of regular expersion
		if (regex1.test(LOGINID.value) == false)
		{
			document.getElementById("err_USERLOGINID").innerHTML = "Loginid should be alphabet only";
			document.userDtls.USERLOGINID.value="";
		}
	}
	else  document.getElementById("err_USERLOGINID").innerHTML = "Enter the login Id";
}
                  //feild checking for password
   function userPasswordCheck(PASSWORD)
  {
	document.getElementById("err_PASSWORD").innerHTML = "";
	if (PASSWORD.value.length > 0) 
	{
		var regex1=/^[a-z A-Z0-9/s ]+$/;    //this is the pattern of regular expersion
		if (regex1.test(PASSWORD.value) == false)
		{
			document.getElementById("err_PASSWORD").innerHTML = "Special characters not allowed ";
		}
	}
	else  document.getElementById("err_PASSWORD").innerHTML = "Enter the password";
 }
            //checking the role from  User.jsp page
  function userRoleCheck(ROLE)
 {
	 document.getElementById("err_ROLE").innerHTML="";
	 if(ROLE.value == "--Select--")
     {   
       document.getElementById("err_ROLE").innerHTML="Select from the list";  
     }
 }
  
   function userStatusCheck(STATUS)
 {
	 document.getElementById("err_STATUS").innerHTML="";
	 if(STATUS.value == "--Select--")
     {   
       document.getElementById("err_STATUS").innerHTML="Select from the list";  
     }
 }
	
   function getParameterByName(name, url) 
  {
	if (!url)
	url = window.location.href;
	url = url.toLowerCase(); // This is just to avoid case sensitiveness  
	name = name.replace(/[\[\]]/g, "\\$&").toLowerCase();// This is just to avoid case sensitiveness for query parameter name
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
 }

//Function for set the user to read only when it is in type of edit
   function editFormFucntion() 
 {
	var option = getParameterByName("Option");
	if (option == 4)
	{
		document.getElementById('USER_LOGINID').readOnly = true;
		document.getElementById('reset').type = "hidden";
	}
  }
	</script>
 </head>
  <body onload="editFormFucntion()">
  <%
  USER_COMMONDATA common_data = (USER_COMMONDATA)request.getAttribute("commonData"); 
  %>
     <form action="UserController" method="post" name="userDtls">
     <input type="hidden" name="USERNAME"  value="<%=common_data.getUSERNAME() %>" >
 	 <input type="hidden" name="DATE"  value="<%=common_data.getLOGINDATE() %>"> 
 	 <input type="hidden" name="USERROLE"  value="<%=common_data.getUSERROLE() %>">
 	 <input type="hidden" name="LOGINID" value="<%=common_data.getLOGINID()%>">
  	 <input type="hidden" id="ADD_USER_LOGINID" name="ADD_USER_LOGINID" value="<%=common_data.getUSERLOGINID()%>">
     <input type="hidden" id="ADD_USER_ROLE1" name="ADD_USER_ROLE1" value="<%=common_data.getUSERROLE1()%>">
     <input type="hidden" id="ADD_STATUS" name="ADD_STATUS" value="<%=common_data.getSTATUS()%>">
     <input type="hidden" id="pageNo" name="pageNo" value="<%=common_data.getPageNo()%>">
     
    <%USER_MASTER user =(USER_MASTER)request.getAttribute("user");
      
    String USER_NAME = user.getUSERNAME();
    if(USER_NAME==null) USER_NAME="";
    
    String USERLOGINID = user.getUSERLOGINID();
    if(USERLOGINID==null) USERLOGINID="";
    
    String USER_PASSWORD = user.getUSERPASSWORD();
    if(USER_PASSWORD==null) USER_PASSWORD="";
    
    String USER_ROLE1 = user.getUSERROLE();
    if(USER_ROLE1==null) USER_ROLE1="";
    
    String USERSTATUS = user.getSTATUS();
    if(USERSTATUS==null) USERSTATUS=""; 
    %> 
    
      <input type="hidden" name="USER_SRNO" value="<%=user.getUSERSRNO()%>">
        <table  class="mainTable" >
	  	<tr align="center" ><td valign="top"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
	  	<tr><td > <table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=common_data.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=common_data.getLOGINDATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
		    		<tr><td> <table class="childTable" height="400"  width="300" align="center">
				<tr><td><h2 align="left">USER DETAILS</h2></td></tr>
				
	<tr><td class="formLable">User name:</td>
	<td><input type="text" name="USER_NAME" id="USER_NAME" style="width:65%" maxlength="15" value="<%=USER_NAME%>"  onblur="userUserNameCheck(this);">
	<font color="#ff0000">*</font><br>
	<span style="color:red;" id="err_USERNAME"></span>
	</td></tr>
	
	<tr><td class="formLable">User loginid:</td>
	<td><input type="text" name="USERLOGINID" id="USERLOGINID" style="width:65%" maxlength="10" value="<%=USERLOGINID%>" maxlength="10" onblur="userLoginIdCheck(this);">
	<font color="#ff0000">*</font><br>
	<span style="color:red;" id="err_USERLOGINID"></span>
	</td></tr>
	
	<tr><td class="formLable">User Password:</td>
	<td><input type="password" name="USER_PASSWORD" id="USER_PASSWORD" style="width:65%" maxlength="10" value="<%=USER_PASSWORD%>" maxlength="10" onblur="userPasswordCheck(this);">
	<font color="#ff0000">*</font><br>
	<span style="color:red;" id="err_PASSWORD"></span>
	</td></tr>
	
    <tr><td class="formLable">Role:</td>
	<td><select  id="USER_ROLE1" name="USER_ROLE1"   style="width:65%" onblur="userRoleCheck(this);">
		<option value="" selected>- - Select - - </option>
	    <%
	    if(!(user.getUSERROLE()==null||user.getUSERROLE() ==""))
		{
		%>
		<option value="<%=user.getUSERROLE()%>" selected><%=user.getUSERROLE()%></option>
		<%
	 	}
	  	%>
	  	<option value="USER" >USER</option>
		<option value="ADMIN" >ADMIN</option>
		<option value="SUPERVISOR" >SUPERVISOR</option>
		</select>
	<font color="#ff0000">*</font><br>
	<span style="color:red;" id="err_ROLE"></span>
	</td></tr>
	
    <tr><td class="formLable">Status</td>
    <td><select id="USERSTATUS" name="USERSTATUS" style="width:65%"  onblur="userStatusCheck(this);">
    <option value="" selected>- - Select - -</option>
  		 <%
	    if(!(user.getSTATUS()==null||user.getSTATUS() ==""))
		{
		%>
		<option value="<%=user.getSTATUS()%>" selected><%=user.getSTATUS()%></option>
		<%
	 	}
	  	%>
    <option value="ACTIVE">ACTIVE</option>
    <option value="INACTIVE">INACTIVE</option>
    </select>
    <font color="#ff0000"></font><br>
    <span style="color: red;" id="err_STATUS"></span>
   </td></tr>

	<tr><td colspan="2" align="center"> 
	<p><%=request.getAttribute("message") %></p></td></tr>
	
	<tr><td align="center" colspan="2">
	<button type="button" onclick="userSave();" class="button"> SAVE</button>
	<button type="reset" class="button" onclick="userResetErrors();"> RESET</button>	
	<button type="button" onclick="exitAddUser();" name="exit" value="EXIT" class="button"> EXIT</button>
	</td></tr>
	
	</table></td></tr>
	<tr><td><br></td></tr>
<tr><td align="center" valign="bottom"><img class="headerImg" src="img/footer.jpg"></td></tr>
</table></td></tr>
</table>
</body>
</html>
