<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">

	function loginCheck()
	{
		 var man=mandatoryLogincheck();
     	 if(man)
      	 {
		 	document.getElementById("loginForm").action = "TERA_STORES_PURCHASE?Option=1";
			document.getElementById("loginForm").submit();
		 }
	}
	
	function loginIDCheck(USERLOGIN)
	{
	document.getElementById("err_login_id").innerHTML = "";
	if (USERLOGIN.value.length > 0) 
	{
		var regex1=/^[a-zA-Z ]+$/;    //this is the pattern of regular expersion
		if (regex1.test(USERLOGIN.value) == false)
		{
			document.getElementById("err_login_id").innerHTML = "Login ID should accepts alphabets only";
		}
	}
	else  document.getElementById("err_login_id").innerHTML = "Enter Login ID";
	}
	
<%--	function password_Check(USERLOGIN)--%>
<%--	{--%>
<%--	document.getElementById("err_password").innerHTML = "";--%>
<%--	if (USERLOGIN.value.length > 0) --%>
<%--	{--%>
<%--		var regex1=/^[a-zA-Z0-9]+$/;    //this is the pattern of regular expersion--%>
<%--		if (regex1.test(USERLOGIN.value) == false)--%>
<%--		{--%>
<%--			document.getElementById("err_password").innerHTML = "Password should accepts alpha numeric only";--%>
<%--		}--%>
<%--	}--%>
<%--	else  document.getElementById("err_password").innerHTML = "Enter Password";--%>
<%--	}--%>
	
	function loginResetErrors()
	{
	document.getElementById("err_login_id").innerHTML="";
<%--	document.getElementById("err_password").innerHTML="";--%>
	document.getElementById("USERLOGINID").value="";
	document.getElementById("USERPASSWORD").value="";
   	document.getElementById("message").innerHTML="";
	}
	function loginResetErrors1()
	{
	document.getElementById("err_login_id").innerHTML="";
<%--	document.getElementById("err_password").innerHTML="";--%>
	}
	
	function mandatoryLogincheck()
	{
	
	var status;
    status=true;
	loginResetErrors1();
	   if( document.getElementById("USERLOGINID").value.length==0)
        {   
	       document.getElementById("err_login_id").innerHTML="Enter Login ID";
	       status=false;
        }
<%--       if(document.getElementById("USERPASSWORD").value.length==0)--%>
<%--        {   --%>
<%--	       document.getElementById("err_password").innerHTML="Enter Password";--%>
<%--	       status=false;--%>
<%--        }--%>
      return status;
     }
</script>
</head>
<body>
 <%
 //	String loginid=(String)request.getAttribute("USERLOGINID");
//	String loginpass=(String)request.getAttribute("USERPASSWORD");
 	String loginid="";
	String loginpass="";
	if (loginid==null)loginid="";
 	if (loginpass==null)loginpass="";
 
%>
<table class="mainTable">
<tr><td valign="top"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>

<tr><td align="center">
 	<form id="loginForm" method="post" name="loginForm">
 
	<table class="childTable">
		<tbody>
		<tr><td colspan="2" align="center">LOGIN</td></tr>
		<tr><td>Login ID</td><td><input type="text" id="USERLOGINID" name="USERLOGINID" value="<%=loginid%>" maxlength="50" onblur="loginIDCheck(this)">
		<font color="#ff0000">*</font>
		<br><span style="color:red;" id="err_login_id"></span>
		</td></tr>
		
		<tr><td>Password</td><td><input type="password" id="USERPASSWORD" name="USERPASSWORD" value="<%=loginpass%>" maxlength="10" onblur="password_Check(this);">
		<font color="#ff0000">*</font>
		<br><span style="color:red;" id="err_password"></span>
		</td></tr>
		
		<tr><td colspan="2" id="message" align="center"><font color="red"><%if(request.getAttribute("message")!= null){
		%>
		<%=request.getAttribute("message") %>
		<%
		} %></font></td></tr>
		<tr align="center"><td colspan="2"><button type="button" onclick="return loginCheck()" class="button"> Login</button>
			<button type="button" class="button" onclick="loginResetErrors()"> Clear</button></td><td></td></tr>
	</tbody></table>
	</form>
</td></tr>
<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg"></td></tr>
</table>
</body>
</html>