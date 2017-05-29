<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="java.text.*"  %>
<%@page import="bean.PROJECT_MASTER"%>
<%@page import="bean.PROJECT_CommonData"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>vendorDetails</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
 	
    
	 <link rel="stylesheet" type="text/css" href="css/style.css">
    
	<script type="text/javascript" src="validation.js"></script>
	<script type="text/javascript">
	function VendorResetErrors() 
	   {
	  
		document.getElementById("err_PROJECT").innerHTML = "";
		document.getElementById("err_LOCATION").innerHTML = "";
		document.getElementById("err_MANAGER").innerHTML = "";
		document.getElementById("err_MANAGERMAILID").innerHTML = "";
		
	}
	
	//Checking for mandatory fields in vendor details JSP page
	function mandatorycheck()
	{
	
		var status;
		status = true;
		VendorResetErrors();
	
	if(ADDPROJECTNAME.value.length == 0){
		 document.getElementById("err_PROJECT").innerHTML="Enter Project";
	       status=false;
	}
	
	if(ADDLOCATION.value.length == 0){
		 document.getElementById("err_LOCATION").innerHTML="Enter Location";
	       status=false;
	}
	
	if(ADDMANAGER.value.length == 0){
		 document.getElementById("err_MANAGER").innerHTML="Enter Manager";
	       status=false;
	}
	
		return status;
	}
	
		
//Type field validation function
	function Name_select(name)
    {
       document.getElementById("err_NAME_check").innerHTML="";
    if(name.value == "--Select--")
        {
       document.getElementById("err_NAME_check").innerHTML="Must select Vendor Name";
	    }
}
	
	

	
//Project field validation function
	function projectCheck(project) 
	{
		document.getElementById("err_PROJECT").innerHTML = "";
		if (project.value.length > 0) 
		{
			var letters = /^[a-z A-Z \s]+$/;
			if (letters.test(project.value) == false) 
			{
				document.getElementById("err_PROJECT").innerHTML = "Enter project name in alphabets";
			}
		}
		else document.getElementById("err_PROJECT").innerHTML="Enter project name";
	}
	

//Location validation function
	function locationCheck(location)
	 {
		document.getElementById("err_LOCATION").innerHTML = "";
		if (location.value.length > 0) 
		{
			var regex = /^[a-zA-Z\s]+$/;
			if (regex.test(location.value) == false) 
			{
				document.getElementById("err_LOCATION").innerHTML = "Enter Location in alphabets";
			}			
		}
		else document.getElementById("err_LOCATION").innerHTML = "Enter Location";
	}
	
//Manager validation function
	function managerCheck(manager) 
	{
		document.getElementById("err_MANAGER").innerHTML = "";
		if (manager.value.length > 0) 
		{
			var letters = /^[a-z A-Z \s]+$/;
			if (letters.test(manager.value) == false) 
			{
				document.getElementById("err_MANAGER").innerHTML = "Enter manager name in alphabets";
			}
		}
		else document.getElementById("err_MANAGER").innerHTML = "Enter manager name";
	}
	

//E-mail field validation function
	function emailCheck(email) 
	{
		document.getElementById("err_MANAGERMAILID").innerHTML = "";
		if (email.value.length > 0) 
		{
			var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if (reg.test(email.value) == false) 
			{
				document.getElementById("err_MANAGERMAILID").innerHTML = "Enter valid email id";
			}
		}
		else document.getElementById("err_MANAGERMAILID").innerHTML = "Enter Project Manager E-Mail ID";
	}
	

	
//Save button funtion	
	  function saveProject()
     {
	     var mandatory=mandatorycheck();
	      if(mandatory)
	      { 
	      
	      document.forms[0].action='PROJECT_MASTER_CONTROLLER?Option=3';
		  document.forms[0].submit();
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
	
	 
	 function editFormFucntion() 
    {
		var option = getParameterByName("Option");
		  if (option == 4)
		  {
			document.getElementById('ADDNAME').disabled = true;
			document.getElementById('reset').type = "hidden";
		  }
    }

//Exit button function   
    function exitprojectDetails(){
    document.forms[0].action='PROJECT_MASTER_CONTROLLER?Option=1';
 	document.forms[0].submit();
    }
	</script>


	
  </head>
  <body class=bdy onload="editFormFucntion()">
 <%PROJECT_CommonData commonData = (PROJECT_CommonData)request.getAttribute("commonData"); %>
 <form action="PROJECT_MASTER_CONTROLLER" method="post" name="vendorDetails">
     <input type="hidden" name="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  	 <input type="hidden" name="DATE" value="<%=commonData.getLOGINDATE() %>">
  	 <input type="hidden" name="USERROLE" value="<%=commonData.getUSERROLE() %>">
  	 <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID()%>">
  	   <input type="hidden" name="PROJECT" value="<%=commonData.getPROJECTNAME() %>">
  	 <input type="hidden" id="pageNo" name="pageNo" value="<%=commonData.getPAGENO()%>">
    <%PROJECT_MASTER PROJECT_MASTER =(PROJECT_MASTER)request.getAttribute("PROJECT_MASTER");
    
    long ADDPROJECTSRNO=PROJECT_MASTER.getPROJECTSRNO();
    String ADDPROJECTNAME=PROJECT_MASTER.getPROJECTNAME();
    String ADDLOCATION=PROJECT_MASTER.getLOCATION();
    String ADDMANAGER=PROJECT_MASTER.getMANAGER();
    String ADDEMAILID=PROJECT_MASTER.getEMAILID();
     
    if(ADDPROJECTNAME==null) ADDPROJECTNAME="";
    if(ADDLOCATION==null) ADDLOCATION="";
    if(ADDMANAGER==null) ADDMANAGER="";
    if(ADDEMAILID==null) ADDEMAILID="";
    %> 
        <input type="hidden" name="ADDPROJECTSRNO" value="<%=ADDPROJECTSRNO%>">
        <table  class="mainTable">
	  	<tr align="center"><td align="center"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
	  	<tr><td> <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getLOGINDATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
		    		<tr><td> <table class="childTable" height="200"  align="center">
				<tr><td><h2 align="left">ADD PROJECT</h2></td></tr>

				<tr>
					<td class="formLable">Project:<br></td>
					<td> <input type="text" name="ADDPROJECTNAME" id="ADDPROJECTNAME" maxlength="" value="<%= ADDPROJECTNAME%>" onblur="projectCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_PROJECT"></span></td>
				</tr>
				<tr>
					<td class="formLable">Location:</td>
					<td><input type="text" name="ADDLOCATION" id="ADDLOCATION" maxlength="" value="<%= ADDLOCATION%>" onblur="locationCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_LOCATION"></span></td>
				</tr>
				<tr>
					<td class="formLable">Manager:</td>
					<td><input type="text" name="ADDMANAGER" id="ADDMANAGER" maxlength="" value="<%= ADDMANAGER%>" onblur="nagerCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_MANAGER"></span></td>
				</tr>
				<tr>
					<td class="formLable">MailId:</td>
					<td><input type="text" name="ADDEMAILID" id="ADDEMAILID" maxlength="" value="<%= ADDEMAILID%>" onblur="emailCheck(this)" ><br>
					<span style="color:red;" id="err_MANAGERMAILID"></span></td>
				</tr>
				<tr></tr>
	<tr>			
	<td colspan="2" align="center"> 
	<p><%=request.getAttribute("message") %></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="2">
	 <button type="button" onclick="saveProject();" class="button"> SAVE</button>
	 <button type="reset" class="button" onclick="VendorResetErrors();"> RESET</button>	
	<button type="button" onclick="exitprojectDetails()" name="exit" value="EXIT" class="button"> EXIT</button>
	</td>
	</tr>
	</table></td></tr>
		</table></td></tr>
	<tr align="center"><td><img class="headerImg" src="img/footer.jpg"></td></tr>
</table>
</form>
  </body>
</html>
