<%@page import="bean.USER_MASTER"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">

	function passwordCheck()
	{
	 var man = mandatoryLogincheck();
     if(man)
     {
		 	document.getElementById("loginForm").action ='TERA_STORES_PURCHASE?Option=5';
			document.getElementById("loginForm").submit();
	 }
	}
	
	function exitCheck()
	{
	document.getElementById("loginForm").action='TERA_STORES_PURCHASE?Option=2';
	document.getElementById("loginForm").submit();
	}
	
	function PasswordIdCheck(PASSWORD)
	{
	document.getElementById("err_PASSWORD").innerHTML = "";
	if (PASSWORD.value.length > 0) 
	{
		var regex1=/^[a-zA-Z0-9]+$/;    //this is the pattern of regular expersion
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
		var regex1=/^[a-zA-Z0-9]+$/;    //this is the pattern of regular expersion
		if (regex1.test(RESETPASSWORD.value) == false)
		{
			document.getElementById("err_RESETPASSWORD").innerHTML = "";
		}
	}
	else  document.getElementById("err_RESETPASSWORD").innerHTML = "Enter the reset password";
	}
	
	function ResetErrors()
	{
	document.getElementById("err_PASSWORD").innerHTML="";
	document.getElementById("err_RESETPASSWORD").innerHTML="";
	}
	
	
	function mandatoryLogincheck()
	{
	var status;
    status=true;
	ResetErrors();
	   if(loginForm.USERPASSWORD.value.length==0)
        {   
	       document.getElementById("err_PASSWORD").innerHTML="Enter new password";
	       status=false;
        }
       if(loginForm.RESETPASSWORD.value.length==0)
        {   
	       document.getElementById("err_RESETPASSWORD").innerHTML="Enter reset Password";
	       status=false;
        }
        if(loginForm.USERPASSWORD.value !=loginForm.RESETPASSWORD.value)
        {
        document.getElementById("err_RESETPASSWORD").innerHTML="Password mismatch";
        status=false;
        }
      return status;
     }
</script>
</head>
<body>
 <%
 //	String userpassword=(String)request.getAttribute("USERPASSWORD");
//	String resetpassword=(String)request.getAttribute("RESETPASSWORD");
   USER_MASTER user = (USER_MASTER)request.getAttribute("user");
   
   String USERLOGINID = user.getUSERLOGINID();
   if(USERLOGINID == null)USERLOGINID ="";
   
 	String userpassword="";
	String resetpassword="";
	if (userpassword==null)userpassword="";
 	if (resetpassword==null)resetpassword="";
 
%>
<tr><td align="center">
   <form id="loginForm" method="post" name="loginForm">
   <table align="center"  class="mainTable" >
   <tr><td align="center" valign="top"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
   <tr><td class="center">
   <form name ="loginForm" id="loginForm" method="post">
   <input type="hidden" name="USERNAME" value="<%=request.getAttribute("USERNAME")%>">
   <input type="hidden" name="DATE" value="<%=request.getAttribute("DATE")  %>">
   <input type="hidden" name="USERROLE" value="<%=request.getAttribute("USERROLE") %>">
   <input type="hidden" name="LOGINID" value="<%=request.getAttribute("LOGINID")%>">
 
  <tr><td><table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td valign="top"><table align="right">
  <tr align="center">
  <td align="left"><b><font size="4" color="blue">Name of the User:</font></b></td>
  <td align="left"><b><font size="3" color="black"><%=request.getAttribute("USERNAME")%></font></b></td> 
  <td align="left"><b><font size="4" color="blue">Date:</font></b></td>
  <td align="left"><b><font size="3" color="black"><%=request.getAttribute("DATE")%></font></b></td>
  </tr>
 </table></td></tr>
 <tr><td><br></td></tr>
	<table class="childTable" align="center">
		<tbody>
		<tr><td colspan="2" align="center"><h3>CREATE PASSWORD</h3></td></tr>
		<tr><td> <br></td></tr>
		
		<tr><td>User id</td><td><input type="text" id="USERLOGINID" name="USERLOGINID" readonly="readonly"  maxlength="10"  value="<%=USERLOGINID%>">
		<font color="#ff0000"></font>
		</td></tr>
		
 		<tr><td>New Password</td><td><input type="password" id="USERPASSWORD" name="USERPASSWORD" value="<%=userpassword%>"  maxlength="10"  onblur="PasswordIdCheck(this)">
		<font color="#ff0000"></font>
		<br><span style="color:red;" id="err_PASSWORD"></span>
		</td></tr>
		
		<tr><td>conform Password</td><td><input type="password" id="RESETPASSWORD" name="RESETPASSWORD" value="<%=resetpassword%>"  maxlength="10" onblur="ResetPasswordCheck(this);">
		<font color="#ff0000"></font>
		<br><span style="color:red;" id="err_RESETPASSWORD"></span>
		</td></tr>
		
	   <tr><td colspan="2" align="center"> 
	   <p><font color="red"><%=request.getAttribute("message") %></font></p>
	  </td></tr>
	  
		<tr align="center"><td colspan="2"><button type="button" onclick="return passwordCheck()" class="button">Submit</button>
		<button type="button" class="button" onclick="exitCheck()">Exit</button></td><td></td></tr>
	    </tbody></table>

<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg"></td></tr>
</table>
</td></tr>
</table>
</form>
</body>
</html>