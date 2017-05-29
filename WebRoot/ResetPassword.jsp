<%@page import="bean.USER_COMMONDATA"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>Reset Password</title>
   <link rel="stylesheet" type="text/css" href="css/style.css">
   <script type="text/javascript">
  
  function submitCheck()
  {
 		var USERLOGINID=document.getElementById('USERLOGINID').value;
  		document.forms[0].action='USER_CONTROLLER?Option=11&USERLOGINID='+USERLOGINID;
  		document.forms[0].submit();
  }
  
  function resetExit()
  {
	document.forms[0].action='TERA_STORES_PURCHASE?Option=3';
 	document.forms[0].submit();
  }
  
  function clearResetError()
 {
    document.getElementById("err_USERLOGINID").innerHTML="";
 }
 
   function mandatoryLogincheck()
   {
   var status;
   status=true;
   clearResetError();
   
   if(resetdtls.USERLOGINID.value.length==0)
   {
   document.getElementById("err_USERLOGINID").innerHTML="";
   status=false;
   }
   }
   // loginid reset password
   
   function loginIdCheck(USERLOGIN)
   {
   document.getElementById("err_USERLOGINID").innerHTML ="";
   if(USERLOGIN.value.length > 0)
   {
   var regex1=/^[a-zA-z]+$/;
   if(regex1.test(USERLOGIN.value) == false)
   {
   document.getElementById("err_USERLOGINID").innerHTML="alphabet can only accepted";
   }
  }
   else
   document.getElementById("err_USERLOGINID").innerHTML="Enter the user id";
   }

  </script>
  </head>
  
  <body>
     <% String USERLOGINID="";
     if(USERLOGINID == null)USERLOGINID="";
    %>
   <%
  USER_COMMONDATA common_data = (USER_COMMONDATA)request.getAttribute("commonData"); 
  %>
   <table class="mainTable">
  <tr><td align="center" valign="top"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
  <tr><td class="center"> 
  <form action ="loginForm" name="changeDtls" method="post">
  <input type="hidden" name="USERNAME" value="<%=common_data.getUSERNAME()%>">
  <input type="hidden" name="DATE" value="<%=common_data.getLOGINDATE() %>">
  <input type="hidden" name="USERROLE" value="<%=common_data.getUSERROLE() %>">
  <input type="hidden" name="LOGINID" value="<%=common_data.getLOGINID()%>">
 
  <tr><td><table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td ><table align="center">
  <tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=common_data.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=common_data.getLOGINDATE() %></font></b></td>
		    	 	</tr>
 
  </table></td></tr>
  <tr align="center"><td><table class="childTable">
  <tbody>
 
   <%Vector<String> UserLoginid  = (Vector<String>)request.getAttribute("UserLoginid");%>
   
    <tr><td colspan="2" align="center"><h3>RESET PASSWORD</h3></td></tr><br>
    <tr><td><br></td></tr>
    <tr align="center"><td> User Loginid:</td>
    <td align="left"><select name="USERLOGINID" id="USERLOGINID"  style="width:65%">
    <option value="">--Select--</option>
    <%for(String userloginid : UserLoginid){
     %><option value ="<%=userloginid %>"><%=userloginid %>
     <%} %>
    </td></tr> 
     
   <tr><td colspan="2" align="center"> 
   <p><font color="red"><%=request.getAttribute("message") %></font></p></td></tr>
   <tr><td><br></td></tr>
   
  <tr><td colspan="2"  align="center"> <button type="button" onclick="submitCheck();" class="button">Reset</button>
  <button type="button" onclick=" resetExit();" name="exit" value="EXIT" class="button"> EXIT</button>
 </td></tr></tbody></table>
  </td></tr></table>

<tr><td><br></td></tr>
<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg" ></td></tr>
</table>
 </body>
</html>
