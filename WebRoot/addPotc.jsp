<%@page import="bean.PO_TC"%>
<%@page import="java.util.List"%>
<%@page import="bean.PO_CommonData"%>
<%@page import="bean.MAJOR_MINOR_CODE" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>PO Terms and Conditions</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<script type="text/javascript">
	
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


<%--Function for changing condition field to read only--%>
function editFormFucntion() 
{
	var option = getParameterByName("Option");
	if (option == 14 )
	{
		document.getElementById('CONDITION').readOnly = true;
	}
}

<%--Function for to go P.O terms and conditions grid page--%>
		function exitPoTC()
		{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=19';
	  		document.forms[0].submit();
		}
		
<%--Function for get the radio value which is selected--%>
function getRadioVal() 
{
    var val=0;
    // get list of radio buttons with specified name
    var radios = document.getElementsByName('radio');
    // loop through list of radio buttons
    for (var i=0, len=radios.length; i<len; i++) 
    {
        if ( radios[i].checked ) 
        { // radio checked?
            val = radios[i].value; // if so, hold its value in val
            break; // and break out of for loop
        }
    }
    return val; // return value of checked radio or undefined if none checked
}
		
<%--Function for reset the fields in this page--%>
		function resetTC()
		{
		 document.getElementById("err_CONDITION").innerHTML="";
		 document.getElementById("err_DESCRIPTION").innerHTML="";
		}
		
<%--Function for save the P.O Terms and conditions--%>
		function savePoTC()
		{
			var man=mandatoryPOTC();
			if(man)
			{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=7';
	  		document.forms[0].submit();
	  		}
		}
		
	<%--Function for validating the condition field in this page--%>	
		function condition_select()
		{
   			document.getElementById("err_CONDITION").innerHTML="";
   			if(POTC.CONDITION.value.length==0)
        	{   
	       	document.getElementById("err_CONDITION").innerHTML="Select condition from list";
	    	}
		}
		
		<%--Function for validating the description field--%>
		function descriptionCheck(DESCRIPTION)
		{
			document.getElementById("err_DESCRIPTION").innerHTML = "";
			if (DESCRIPTION.value.length > 0) 
			{
				var regex1=/^[0-9a-zA-Z_\s]+$/;
				if (regex1.test(DESCRIPTION.value) == false)
				{
				document.getElementById("err_DESCRIPTION").innerHTML = "Description should be in alpha-numeric only";
				document.POTC.DESCRIPTION.value="";
				}		
			}
			else
			{
			document.getElementById("err_DESCRIPTION").innerHTML = "Enter description";
			}
		}
		
<%--Checking for mandatory fields in add terms and conditions jsp page--%>
		function mandatoryPOTC()
		{
			var status;
   			status=true;
    		resetTC();
	   		if(POTC.CONDITION.value.length==0)
        	{   
	       		document.getElementById("err_CONDITION").innerHTML="Select condition from list";
	       		status=false;
        	}
       		if(POTC.DESCRIPTION.value.length==0)
        	{   
	       		document.getElementById("err_DESCRIPTION").innerHTML="Enter Description";
	       		status=false;
       		}
			return status;
		}
	</script>
 </head>
  <body onload="editFormFucntion()">
   <%
   	PO_CommonData po_commondata = (PO_CommonData)request.getAttribute("po_commondata");
   %>
    <form action="PURCHASE_ORDER_CONTROLLER" method="post" name="POTC">
     <input type="hidden" name="USERNAME" value="<%=po_commondata.getUSER_NAME() %>" >
  	 <input type="hidden" name="DATE" value="<%=po_commondata.getLOGIN_DATE() %>">
  	 <input type="hidden" name="USERROLE" value="<%=po_commondata.getUSER_ROLE() %>">
  	 <input type="hidden" name="LOGINID" value="<%=po_commondata.getLOGIN_ID() %>">
  	  <input type="hidden" name="pageNo" value="<%=po_commondata.getPAGE_NO()%>">
  	 <input type="hidden" name="PONO" value="<%=po_commondata.getPO_NO()%>">
  	 <input type="hidden" name="POSUPPLIERNAME" value="<%=po_commondata.getPO_SUPPLIER_NAME()%>">
 	 
   <table class="mainTable" style="height: 650px">
	  	<tr><td><jsp:include page="header1.jsp"></jsp:include></td></tr>
	  	<tr><td> <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
	  		<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=po_commondata.getUSER_NAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=po_commondata.getLOGIN_DATE() %></font></b></td>
		    	 	</tr>
		    	 	</table></td></tr>
     		  <tr><td> <table class="childTable" height="200" align="center">
			 <br> <tr><td colspan="2"><h2>ADD Terms and Conditions</h2><tr><td>
     		<%PO_TC po_tc = (PO_TC)request.getAttribute("po_tc"); 
     		String CONDITION =po_tc.getCONDITION();
     			if(CONDITION==null) CONDITION="";
     		String DESCRIPTION=po_tc.getDESCRIPTION();
     			if(DESCRIPTION==null) DESCRIPTION="";
     		%>
	<input type="hidden" name="POTCSRNO" value="<%=po_tc.getPOTCSRNO()%>">
 	<tr>
	<td class="formLable">PO Number</td>
	<td><input type="text" name="ADDPONO" id="ADDPONO" style="width:65%;background-color:#e6e6e6" value="<%=request.getParameter("ADDPONO")%>" maxlength="50" readonly="readonly"/>
	<br></td>
	</tr>
							
	<tr>
	<td class="formLable">Condition:</td>
		<td>
		<select name="CONDITION"  id="CONDITION" style="width:65%" onblur="condition_select();">
   			<%
						if(CONDITION==null||CONDITION=="")
						{
						%>
							<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=CONDITION%>" selected><%=CONDITION%></option>
						<%
	 						}
	  					%>
			     <%  List<MAJOR_MINOR_CODE> conditionList =  (List<MAJOR_MINOR_CODE>)request.getAttribute("CONDITION"); 
   				    for(MAJOR_MINOR_CODE majorMinorCode : conditionList){
   					%><option value="<%=majorMinorCode.getMINORCODEDESC()%>"> <%=majorMinorCode.getMINORCODEDESC()%></option> <%
   					}
   				%>
    		</select>
    		<font color="#ff0000">*</font><br>
    		<span style="color:red;" id="err_CONDITION"></span></td>
	</tr>
	
	<tr>
	<td class="formLable">Description</td>
	<td><textarea name="DESCRIPTION" id="DESCRIPTION" style="width: 65%" maxlength="100" onblur="descriptionCheck(this);"><%=DESCRIPTION%></textarea>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_DESCRIPTION"></span></td>
	</tr>
 	
 	<tr>
	<td colspan="2" align="center"> 
	<p><%=request.getAttribute("message") %></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="2">
	 <button type="button" onclick="return savePoTC();" class="button"> SAVE </button>&nbsp;&nbsp;&nbsp;&nbsp;
	 <button type="reset" class="button" onclick="resetTC()"> CLEAR </button>&nbsp;&nbsp;&nbsp;&nbsp;
	 <button type="button" onclick="exitPoTC()" name="exit" value="EXIT" class="button">EXIT</button>
	</td>
	</tr>
 </table></td></tr>
 </table>
 <tr><td><jsp:include page="footer.jsp"></jsp:include>
 </td></tr></table>
 </form>
 </body>
</html>
