<%@page import="bean.MAJOR_MINOR_CODE"%>
<%@page import="java.util.List"%>
<%@page import="bean.MAJOR_MINOR_CommonData"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Major Minor</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<script type="text/javascript">
	function minorSave()
	{
		var mandatory=mandatoryMajorMinorcheck();
		if(mandatory)
		{
		  var majorcode=document.getElementById("ADD_MAJORCODE");
		  var majordesc=majorcode.options[majorcode.selectedIndex].text; 
	      document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=5&ADD_MAJOR_CODE_DESC='+majordesc;
		  document.forms[0].submit(); 
		}
	}
	
	function minorDescCheck(ADDMINORCODEDESC)
		{
			document.getElementById("err_minordesc_check").innerHTML = "";
			if (ADDMINORCODEDESC.value.length > 0) 
			{
				var regex1=/^[0-9a-zA-Z_\s]+$/;
				if (regex1.test(ADD_MINORCODEDESC.value) == false)
				{
				document.getElementById("err_minordesc_check").innerHTML = "Minor code number should be in alpha-numeric only";
				document.majorMinor.ADD_MAJOR_CODE_DESC.value="";
				}		
			}
			else
			{
			document.getElementById("err_minordesc_check").innerHTML = "Enter Minor code description";
			}
		}
	
function minorCodeCheck(minorcodecheck)
{
	document.getElementById("err_minorcode_check").innerHTML = "";
	if (minorcodecheck.value.length > 0) 
	{
		var regex1=/^[0-9]+$/;
		if (regex1.test(minorcodecheck.value) == false)
		{
			document.getElementById("err_minorcode_check").innerHTML = "Minor code number should be in numeric only";
			document.majorMinor.ADD_MINORCODE.value="";	
		}
	}
	else
	{
		document.getElementById("err_majorcode_check").innerHTML = "Enter Major code description";
	}
}
	
	function majorDesc_select()
	{
   		document.getElementById("err_majordesc_check").innerHTML="";
   		 if(majorMinor.ADD_MAJOR_CODE.value.length==0)
        {   
	       document.getElementById("err_majordesc_check").innerHTML="Select Major code description from list";
	    }
	}
	
	function exitMinor()
	{
	     	document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=1';
		 	document.forms[0].submit();
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
	 
		
	function minorResetErrors()
	{
		document.getElementById("err_majordesc_check").innerHTML="";
		document.getElementById("err_minordesc_check").innerHTML="";
		document.getElementById("err_minorcode_check").innerHTML="";
	}
	  
	//Checking for mandatory fields in add item JSP page
	function mandatoryMajorMinorcheck()
	{
	var status;
    status=true;
    minorResetErrors();	
	   if(majorMinor.ADD_MAJORCODE.value.length==0)
        {   
	       document.getElementById("err_majordesc_check").innerHTML="Select Major code description";
	       status=false;
        }
       if(majorMinor.ADD_MINORCODEDESC.value.length==0)
        {   
	       document.getElementById("err_minordesc_check").innerHTML="Enter Minor code description";
	       status=false;
        }
       if(majorMinor.ADD_MINORCODE.value.length==0)
        {   
	       document.getElementById("err_minorcode_check").innerHTML="Enter Minor code";
	       status=false;
        }
	return status;
	}
	</script>
 </head>
  <body>
   <%
   	MAJOR_MINOR_CommonData commonData = (MAJOR_MINOR_CommonData)request.getAttribute("commonData");
   %>
    <form action="majorMinorController" method="post" name="majorMinor">
     <input type="hidden" name="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  	 <input type="hidden" name="DATE" value="<%=commonData.getLOGINDATE() %>">
  	 <input type="hidden" name="USERROLE" value="<%=commonData.getUSERROLE() %>">
  	  <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID()%>">
  	 <input type="hidden" id="MAJORCODE" name="MAJORCODE" value="<%=commonData.getMAJORCODE()%>" />
  	 <input type="hidden" id="MAJORCODEDESC" name="MAJORCODEDESC" value="<%=commonData.getMAJORCODEDESC()%>" />
  	 <input type="hidden" id="MINORCODE" name="MINORCODE" value="<%=commonData.getMINORCODE()%>">
  	 <input type="hidden" id="MINORCODE" name="MINORCODE" value="<%=commonData.getMINORCODEDESC()%>">
 
   <table class="mainTable" style="height: 650px">
	  	<tr><td><jsp:include page="header1.jsp"></jsp:include></td></tr>
	  	<tr><td> <table border="0" cellspacing="0" cellpadding="0" width="100%">
	  		<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getLOGINDATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
     		  <tr><td> <table class="childTable" height="200" align="center">
			  <tr><td><h2>ADD MINOR DETAILS </h2><tr><td>
     		<%MAJOR_MINOR_CODE majorMinorCode = (MAJOR_MINOR_CODE)request.getAttribute("majorMinorCode"); 
     			long ADD_MINORCODE=majorMinorCode.getMINORCODE(); 				   				
 	 				String ADD_MINORCODEX;  	ADD_MINORCODEX="";
 	 			if (ADD_MINORCODE>0)ADD_MINORCODEX=""+ADD_MINORCODE;
     		String ADD_MAJORCODE=majorMinorCode.getMAJORCODEDESC();
     			if(ADD_MAJORCODE==null) ADD_MAJORCODE="";
     		String ADD_MINORCODEDESC=majorMinorCode.getMINORCODEDESC();				   				
 	 			if(ADD_MINORCODEDESC==null) ADD_MINORCODEDESC="";
     		%>
	<tr>
	<td class="formLable">Major Code  Description:</td>
		<td>
		<select name="ADD_MAJORCODE"  id="ADD_MAJORCODE" style="width:65%" onblur="majorDesc_select();">
   			<%
						if(majorMinorCode.getMAJORCODEDESC()==null||majorMinorCode.getMAJORCODEDESC()=="")
						{
						%>
							<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=majorMinorCode.getMAJORCODE()%>" selected><%=majorMinorCode.getMAJORCODEDESC()%></option>
						<%
	 						}
	  					%>
			     	<%  List<MAJOR_MINOR_CODE> majorCodeList =  (List<MAJOR_MINOR_CODE>)request.getAttribute("MAJORCODEDESC"); 
   				    for(MAJOR_MINOR_CODE majorMinorCode1 : majorCodeList){
					%><option value="<%=majorMinorCode1.getMAJORCODE()%>"> <%=majorMinorCode1.getMAJORCODEDESC()%></option> <%
   					}
   				%>
    		</select>
    		<font color="#ff0000">*</font>
		<br><span style="color:red;" id="err_majordesc_check"></span></td>
	</tr>
	
	<tr>
	<td class="formLable">Minor Code</td>
	<td><input type="text" name="ADD_MINORCODE" id="ADD_MINORCODE" style="width:65%" value="<%=ADD_MINORCODEX%>" maxlength="10" onblur="minorCodeCheck(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_minorcode_check"></span></td>
	</tr>
	
	<tr>
	<td class="formLable">Minor Code Description</td>
	<td><input type="text" name="ADD_MINORCODEDESC" id="ADD_MINORCODEDESC" style="width:65%" value="<%=ADD_MINORCODEDESC%>" maxlength="30" onblur="minorDescCheck(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_minordesc_check"></span></td>
	</tr>
 	
 	<tr>
	<td colspan="2" align="center"> 
	<p><%=request.getAttribute("message") %></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="2">
	 <button type="button" onclick="return minorSave();" class="button"> SAVE </button>&nbsp;&nbsp;&nbsp;&nbsp;
	 <button type="reset" class="button" onclick="minorResetErrors();"> CLEAR </button>&nbsp;&nbsp;&nbsp;&nbsp;
	 <button type="button" onclick="exitMinor()" name="exit" value="EXIT" class="button">EXIT</button>
	</td>
	</tr>
 </table></td></tr>
 </table>
 <tr><td><jsp:include page="footer.jsp"></jsp:include>
 </td></tr></table>
 </form>
 </body>
</html>
