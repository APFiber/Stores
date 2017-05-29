<%@page import="bean.MAJOR_CODE"%>
<%@page import="bean.MAJOR_MINOR_CommonData"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Add Major Code</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<script type="text/javascript">
	//To save the item details
	
function majorSave()
{
		var man=mandatoryMajorMinorcheck();
		if(man)
		{
      		 document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=3';
	 		 document.forms[0].submit();
	 	}
}

//To clear the fields in addItem.jsp page
function majorResetErrors()
{
	document.getElementById("err_majordesc_check").innerHTML="";
	document.getElementById("err_majorcode_check").innerHTML="";
}

function majorReset()
{
	document.getElementById("ADD_MAJORCODEDESC").innerHTML="";
}

//To go to addItem.jsp page to the itemGrid.jsp page	
function exitAddMajorCode()
{
   	document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=1';
 	document.forms[0].submit();
} 

function mandatoryMajorMinorcheck()
{
	var status;
    status=true;
    majorResetErrors();	
	   if(majorMinor.ADD_MAJORCODEDESC.value.length==0)
        {   
	       document.getElementById("err_majordesc_check").innerHTML="Enter Major code description";
	       status=false;
        }
        if(majorMinor.ADD_MAJORCODE.value.length==0)
        {   
	       document.getElementById("err_majorcode_check").innerHTML="Enter Major code number";
	       status=false;
        }
	return status;
}


//Functions for to check the major code description field
function majorDescCheck(majordesccheck)
{
	document.getElementById("err_majordesc_check").innerHTML = "";
	if (majordesccheck.value.length > 0) 
	{
		var regex1=/^[0-9a-zA-Z_\s]+$/;
		if (regex1.test(majordesccheck.value) == false)
		{
			document.getElementById("err_majordesc_check").innerHTML = "Major code number should be in alpha-numeric only";
			document.majorMinor.ADD_MAJORCODEDESC.value="";	
		}
	}
	else
	{
		document.getElementById("err_majordesc_check").innerHTML = "Enter Major code description";
	}
}

//Functions for to check the major code description field
function majorCodeCheck(majorcodecheck)
{
	document.getElementById("err_majorcode_check").innerHTML = "";
	if (majorcodecheck.value.length > 0) 
	{
		var regex1=/^[0-9]+$/;
		if (regex1.test(majorcodecheck.value) == false)
		{
			document.getElementById("err_majorcode_check").innerHTML = "Major code number should be in numeric only";
			document.majorMinor.ADD_MAJORCODE.value="";	
		}
	}
	else
	{
		document.getElementById("err_majorcode_check").innerHTML = "Enter Major code description";
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


	</script>
 </head>
  <body>
  <%
  	MAJOR_MINOR_CommonData commonData = (MAJOR_MINOR_CommonData)request.getAttribute("commonData");
  %>
     <form action="majorMinorController" method="post" name="majorMinor">
     <input type="hidden" name="USERNAME" id="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  	 <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGINDATE() %>">
  	 <input type="hidden" name="USERROLE" id="USERROLE" value="<%=commonData.getUSERROLE() %>">
  	 <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID()%>">
  	 <input type="hidden" id="MAJORCODE" name="MAJORCODE" value="<%=commonData.getMAJORCODE()%>" />
  	 <input type="hidden" id="MAJORCODEDESC" name="MAJORCODEDESC" value="<%=commonData.getMAJORCODEDESC()%>" />
  	 <input type="hidden" id="MINORCODE" name="MINORCODE" value="<%=commonData.getMINORCODE()%>">
  	   	 <input type="hidden" id="MINORCODE" name="MINORCODE" value="<%=commonData.getMINORCODEDESC()%>">
  	 
    <%MAJOR_CODE majorMinorCode =(MAJOR_CODE)request.getAttribute("majorMinorCode");
//    long ADD_MAJORCODE=majorMinorCode.getMAJORCODE();
   
   	long ADD_MAJORCODE=majorMinorCode.getMAJORCODE(); 				   				
 	 	String ADD_MAJORCODEX;  	ADD_MAJORCODEX="";
 	 		if (ADD_MAJORCODE>0)ADD_MAJORCODEX=""+ADD_MAJORCODE;
   
    String ADD_MAJORCODEDESC=majorMinorCode.getMAJORCODEDESC();   				   				
 	 	if(ADD_MAJORCODEDESC==null) ADD_MAJORCODEDESC=""; 				   				
    %> 
        <input type="hidden" name="MMSRNO" value="<%=majorMinorCode.getMAJORCODE()%>">
        <table  class="mainTable" style="height: 650px">
	  	<tr align="center"><td align="center"><jsp:include page="header1.jsp"></jsp:include></td></tr>
	  	<tr><td> <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getLOGINDATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
		    		<tr><td> <table class="childTable" height="200" align="center">
				<tr><td colspan="2"><h2 align="left">ADD MAJOR CODE DETAILS</h2></td></tr>
	
	<tr>
	<td class="formLable">Major Code:</td>
	<td><input type="text" name="ADD_MAJORCODE" id="ADD_MAJORCODE" value="<%=ADD_MAJORCODEX%>" maxlength="10" onblur="majorCodeCheck(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_majorcode_check"></span></td>
	</tr>
	
	<tr>
	<td class="formLable">Major Code  Description:</td>
	<td><input type="text" name="ADD_MAJORCODEDESC" id="ADD_MAJORCODEDESC" value="<%=ADD_MAJORCODEDESC%>" maxlength="30" onblur="majorDescCheck(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_majordesc_check"></span></td>
	</tr>
	
	<tr>
	<td colspan="2" align="center"> 
	<p><%=request.getAttribute("message") %></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="2">
	 <button type="button" onclick="return majorSave();" class="button"> SAVE </button>&nbsp;&nbsp;&nbsp;&nbsp;
	 <button type="reset" class="button" onclick="majorResetErrors();">CLEAR </button>&nbsp;&nbsp;&nbsp;&nbsp;
	 <button type="button" onclick="exitAddMajorCode()" name="exit" value="EXIT" class="button">EXIT</button>
	</td>
	</tr>
	
	</table></td></tr>
		</table></td></tr>
	<tr align="center"><td><jsp:include page="footer.jsp"></jsp:include></td></tr>
</table>
</form>
</body>
</html>
