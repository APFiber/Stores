<%@page import="bean.USER_COMMONDATA"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>change password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    
	<script type="text/javascript">
	
	function maintanceLink()
	{
		document.getElementById("itemForm").action = "USER_CONTROLLER?Option=1";
		document.getElementById("itemForm").submit();
	}
	
	function resetLink()
	{
		document.getElementById("itemForm").action = "USER_CONTROLLER?Option=7";
		document.getElementById("itemForm").submit();
	}
	
	function changePasswordLink()
	{
		document.getElementById("itemForm").action ="USER_CONTROLLER?Option=8";
		document.getElementById("itemForm").submit();
	}
	
	function exitCheck()
   {
	  document.getElementById("itemForm").action = "TERA_STORES_PURCHASE?Option=3";
	  document.getElementById("itemForm").submit();
	
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
  <form name="itemForm" id="itemForm" method="POST" >
  <input type="hidden" name="USERNAME" value="<%=common_data.getUSERNAME()%>">
  <input type="hidden" name="DATE" value="<%=common_data.getLOGINDATE() %>">
  <input type="hidden" name="USERROLE" value="<%=common_data.getUSERROLE() %>">
  <input type="hidden" name="LOGINID" value="<%=common_data.getLOGINID()%>">
 
  <tr><td><table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td ><table align="center">
  <tr align="right">
  <td align="left"><b><font size="4" color="blue">Name of the User:</font></b></td>
  <td align="left"><b><font size="3" color="black"><%=common_data.getUSERNAME()%></font></b></td> 
  <td align="left"><b><font size="4" color="blue">Date:</font></b></td>
  <td align="left"><b><font size="3" color="black"><%=common_data.getLOGINDATE()%></font></b></td>
  </tr>
  </table></td></tr>
  <tr align="center"><td><table class="childTable">
  
 <tr><td><h3 align="center">USER MASTER</h3></td></tr>
 
     <tr><td align="center"><a onclick="maintanceLink()"><u>USER MAINTANCE</u></a></td></tr>
     <tr><td><br></td></tr>
  
	 <tr><td align="center"><a onclick="resetLink()"><u>RESET PASSWORD</u></a></td></tr>
	 <tr><td><br></td></tr>
	 
	 <tr><td align="center"><a onclick="changePasswordLink()"><u>CHANGE PASSWORD</u></a></td> </tr>
	 <tr><td><br></td></tr>
	 </table>
	 
	 <tr><td><br></td></tr>
	 <tr><td colspan="2" align="center"> 
   <p><font color="red"><%=request.getAttribute("message") %></font></p></td></tr>
	 
	<tr>
	<td align="center" colspan="2">
	<button type="button" onclick="exitCheck();" name="exit" value="EXIT" class="button"> EXIT</button></td></tr>
 
        </table></td></tr>
		<tr><td><br></td></tr>
	
	<tr valign="bottom"><td ><img class="headerImg" src="img/footer.jpg"></td></tr>	
</table>
</body>
</html>
