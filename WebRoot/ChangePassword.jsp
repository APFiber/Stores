<%@page import="bean.USER_COMMONDATA"%>
<%@page import="bean.USER_MASTER"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>change password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script type="text/javascript">
	function exitCheck()
	{
	document.forms[0].action='TERA_STORES_PURCHASE?Option=3';
 	document.forms[0].submit();
	}
	
	function passwordCheck()
	{
	var man = mandantoryUserCheck();
	if(man)
	{
		 	document.forms[0].action ='USER_CONTROLLER?Option=9';
			document.forms[0].submit();
	  }
	}
	
	function clearResetError()
	{
	document.getElementById("err_USERLOGINID").innerHTML="";
	document.getElementById("err_USERPASSWORD").innerHTML="";
	document.getElementById("err_PASSWORD").innerHTML="";
	document.getElementById("err_RESETPASSWORD").innerHTML="";
	}
	
	function mandantoryUserCheck()
	{
	var status;
	status=true;
	clearResetError();
	if(loginForm.USERLOGINID.value.length==0)
        {   
	       document.getElementById("err_USERLOGINID").innerHTML="Enter Login ID";
	       status=false;
        }
       if(loginForm.USERPASSWORD.value.length==0)
        {   
	       document.getElementById("err_USERPASSWORD").innerHTML="Enter old Password";
	       status=false;
        }
	
	if(loginForm.NEWPASSWORD.value.length==0)
        {   
	       document.getElementById("err_PASSWORD").innerHTML="Enter new password";
	       status=false;
        }
       if(loginForm.CONFORMPASSWORD.value.length==0)
        {   
	       document.getElementById("err_RESETPASSWORD").innerHTML="Enter conform Password";
	       status=false;
        }
        if(loginForm.NEWPASSWORD.value != loginForm.CONFORMPASSWORD.value)
        {
        document.getElementById("err_RESETPASSWORD").innerHTML ="password mismatch"
        status=false;
        }
        return status;
    }
	
	function loginIdCheck(USERLOGIN)
   {
   document.getElementById("err_USERLOGINID").innerHTML ="";
   if(USERLOGIN.value.length > 0)
   {
   var regex1=/^[a-zA-z ]+$/;
   if(regex1.test(USERLOGIN.value) == false)
   {
   document.getElementById("err_USERLOGINID").innerHTML="alphabet can only accepted";
   }
  }
   else
   document.getElementById("err_USERLOGINID").innerHTML="Enter the user id";
  
   }

   function UserPasswordCheck(USERPASSWORD)
	{
	document.getElementById("err_USERPASSWORD").innerHTML = "";
	if (USERPASSWORD.value.length > 0) 
	{
		var regex1=/^[a-zA-Z0-9 ]+$/;    //this is the pattern of regular expersion
		if (regex1.test(USERPASSWORD.value) == false)
		{
			document.getElementById("err_USERPASSWORD").innerHTML = "Password should accepts alpha numeric only ";
			document.loginForm.USERPASSWORD.value="";
		}
	}
	else  document.getElementById("err_USERPASSWORD").innerHTML = "Enter the password";
	}
   
   function PasswordIdCheck(PASSWORD)
	{
	document.getElementById("err_PASSWORD").innerHTML = "";
	if (PASSWORD.value.length > 0) 
	{
		var regex1=/^[a-zA-Z0-9 ]+$/;    //this is the pattern of regular expersion
		if (regex1.test(PASSWORD.value) == false)
		{
			document.getElementById("err_PASSWORD").innerHTML = "Password should accepts alpha numeric only ";
		}
	}
	else  document.getElementById("err_PASSWORD").innerHTML = "Enter the password";
	}
	
	
	function ResetPasswordCheck(RESETPASSWORD)
	{
	document.getElementById("err_RESETPASSWORD").innerHTML = "";
	if (RESETPASSWORD.value.length > 0) 
	{
		var regex1=/^[a-zA-Z0-9 ]+$/;     //this is the pattern of regular expersion
		if (regex1.test(RESETPASSWORD.value) == false)
		{
			document.getElementById("err_RESETPASSWORD").innerHTML = "Password should accepts alpha numeric only";
		}
	}
	else  document.getElementById("err_RESETPASSWORD").innerHTML = "Enter the reset password";
	}
	</script>

  </head>
  
  <body>
  <%
  USER_COMMONDATA common_data = (USER_COMMONDATA)request.getAttribute("commonData"); 
  %> 
   <table class="mainTable">
  <tr><td align="center" valign="top"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
  <tr><td class="center">
  <form action ="loginForm" name="loginForm" method="post">
  <input type="hidden" name="USERNAME" value="<%=common_data.getUSERNAME()%>">
  <input type="hidden" name="DATE" value="<%=common_data.getLOGINDATE() %>">
  <input type="hidden" name="USERROLE" value="<%=common_data.getUSERROLE() %>">
  <input type="hidden" name="LOGINID" value="<%=common_data.getLOGINID()%>">
 
  <tr><td><table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td ><table align="center">
  <tr align="center">
  <td align="left"><b><font size="4" color="blue">Name of the User:</font></b></td>
  <td align="left"><b><font size="3" color="black"><%=common_data.getUSERNAME()%></font></b></td> 
  <td align="left"><b><font size="4" color="blue">Date:</font></b></td>
  <td align="left"><b><font size="3" color="black"><%=common_data.getLOGINDATE()%></font></b></td>
  </tr>
  </table></td></tr>
  <tr align="center"><td><table class="childTable">
  <tbody>
  <% USER_MASTER user =(USER_MASTER)request.getAttribute("user");
  
  String USERLOGINID = user.getUSERLOGINID();
  if(USERLOGINID == null)USERLOGINID ="";
  String USERPASSWORD = user.getUSERPASSWORD();
  if(USERPASSWORD == null)USERPASSWORD="";
  
  String newpassword="";
	String conformpassword="";
	if (newpassword==null)newpassword="";
 	if (conformpassword==null)conformpassword="";
   %>
  <tr align="center"><td colspan="2" ><b>Change Password</b></td></tr>
   <tr><td><br></td></tr>
  <tr><td align="center"> User Id:</td>
  <td ><input type="text" name="USERLOGINID" id="USERLOGINID" value="<%=USERLOGINID %>" maxlength="10"  onblur="loginIdCheck(this);" >
  <font color="#ff0000"></font>
  <br><span style="color:red;" id="err_USERLOGINID"></span></td></tr>
  
  <tr><td align="center">Old Passsword:</td>
  <td><input type="password" name="USERPASSWORD" id="USERPASSWORD" value=""  maxlength="10" onblur="UserPasswordCheck(this);">
  <font color="#ff0000"></font>
  <br><span style="color:red;" id="err_USERPASSWORD"></span> </td></tr>
  
  <tr><td align="center">New Password:</td>
  <td><input type="password" name="NEWPASSWORD" id="NEWPASSWORD" value="<%=newpassword%>"  maxlength="10" onblur="PasswordIdCheck(this);">
  <font color="#ff0000"></font>
  <br><span style="color:red;" id="err_PASSWORD"></span> </td></tr>

  <tr><td align="center">Reset New Password:</td>
  <td><input type="password" name="CONFORMPASSWORD" id="CONFORMPASSWORD" value="<%=conformpassword%>"  maxlength="10" onblur="ResetPasswordCheck(this);">
  <font color="#ff0000"></font>
  <br><span style="color:red;" id="err_RESETPASSWORD"></span></td></tr>
  
<tr><td colspan="2" align="center"> 
	<p><font color="red"><%=request.getAttribute("message") %></font></p>
	</td>
	</tr>
  
   
 <tr><td colspan="2"  align="center">
 <button type="button" onclick="passwordCheck()" class="button">Submit</button>
 <button type="button" onclick="exitCheck()" class="button">Exit</button>
 </td></tr></tbody></table>
</td></tr></table>
<tr><td><br></td></tr>
<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg" ></td></tr>

</table>
  </body>
</html>
