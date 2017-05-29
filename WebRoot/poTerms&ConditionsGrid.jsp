<%@page import="bean.PO_CommonData"%>
<%@ page language="java" import="java.util.List" pageEncoding="ISO-8859-1"%>
<%@page import="bean.PO_HEADER"%>
<%@page import="bean.PO_TC"%>
<%@page import="org.KrrCommon"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>P.O Terms and Conditions Grid</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	 <%PO_CommonData commonData = (PO_CommonData) request.getAttribute("po_commondata");
	 KrrCommon krrCommon = new KrrCommon(); %>
	<script type="text/javascript">
<%--function for to go to add P.O details jsp page--%>
		function exitPOTC()
		{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=12';
	  		document.forms[0].submit();
		}
		
<%--Function for get the radio value which is selected--%>
		function getRadioVal() 
		{
   			 var val=0;
   			 // get list of radio buttons with specified name
   			 var radios = document.getElementsByName('POTCSRNO1');
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
		
		<%--function to edit the PO terms and conditions--%>
		function editPOTC()
		{
			if(getRadioVal() == null || getRadioVal() == "0")
			{
			alert('Please select PO Terms and condition');
			}
			else
			{
			var selected_value= getRadioVal();
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=14&POTCSRNO='+selected_value;
	  		document.forms[0].submit();
	  		}
		}
		
		<%--function to delete PO Terms and conditions in data base--%>
		function deletePOTC()
		{
			if(getRadioVal() == null || getRadioVal() == "0")
			{
			alert('Please select PO Terms and condition');
			}
			else
			{
			var selected_value= getRadioVal();
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=16&POTCSRNO='+selected_value;
	  		document.forms[0].submit();
	  		}
		}
		
		<%--function to display addPOTC page--%>
		function addPOTC()
		{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=6';
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
		
</script>
  </head>
  <body>
  <form name="gridForm" id="gridForm" method="POST">
   <%PO_HEADER po_header =(PO_HEADER)request.getAttribute("po_header");%>
  <input type="hidden" name="USERNAME" value="<%=commonData.getUSER_NAME() %>" >
  <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGIN_DATE() %>">
  <input type="hidden" name="USERROLE" value="<%=commonData.getUSER_ROLE() %>">
  <input type="hidden" name="LOGINID" value="<%=commonData.getLOGIN_ID() %>">
 <input type="hidden" name="PONO" value="<%=commonData.getPO_NO()%>">
  <input type="hidden" name="pageNo" value="<%=commonData.getPAGE_NO()%>">
 <input type="hidden" name="POSUPPLIERNAME" value="<%=commonData.getPO_SUPPLIER_NAME()%>">
  <input type="hidden" name="POSRNO" value="<%=po_header.getPOSRNO()%>">
   <table  align="center" class="childTable">
	  	<tr align="center" valign="top"><td><jsp:include page="header1.jsp"></jsp:include></td></tr>
	  	<tr><td> <table  border="0" cellspacing="0" cellpadding="0" align="center" style="height: 100">
		<tr><tr><td valign="top" align="right">Welcome: <%=commonData.getUSER_NAME() %> , Date: <%=commonData.getLOGIN_DATE() %></td></tr>
		    	
		    	
		    	<tr ><td>
		    		 <div class="pagination" >
		    		
		    		 <input type="hidden" name="ADDPONO" value="<%=po_header.getPONO()%>">
		    		<p>
		    		 PONO: <%=po_header.getPONO() %> | 
		    		 PO Raised Date: <%=po_header.getPODATE() %> | 
		    		 PO Supplier Name: <%=po_header.getPOSUPPLIERNAME() %>
		    		</p>
		    		</div></td></tr>
		    	<tr><td align="center"><h2>PO Terms and Conditions</h2></td></tr>
		    	<tr><td><table class="gridTable gridTableBg" align="center">
					<tr>
					<th>Select</th>
					<th>Condition</th>
					<th>Description</th>
					</tr>
					<%List<PO_TC> poTCList = (List<PO_TC>)request.getAttribute("POTCLIST");
 					for(PO_TC poTC : poTCList)
 					{
 						long POTCSRNO1=poTC.getPOTCSRNO();
 						String CONDITION=poTC.getCONDITION();
 							if(CONDITION==null) CONDITION="";
 						String DESCRIPTION=poTC.getDESCRIPTION();
 							if(DESCRIPTION==null) DESCRIPTION="";
 					%>
 	 				<tr>
 	 				<td align=center><input type=radio name="POTCSRNO1" id="POTCSRNO1" value="<%=POTCSRNO1%>"></td>
 	 				<td><%=CONDITION %></td>
 	 				<td><%=DESCRIPTION%></td>
 	 				</tr>
 					 <%		
 						}//for
 					%>
					</table></td></tr>
	<tr><td><div class="pagination">
  	<%
  String message=(String)request.getAttribute("message");
  if(krrCommon.isValuenull(message)){
  	message ="";
  }%>
  <p align="center">
	<font color="red">Status : <%=message%></font>
 </p>
  </div></td></tr>
  	
<tr align="center"><td><div class="operationalButtons">
	 <button type="button" onclick="addPOTC();" class="button"> ADD</button>
	 <button type="button" onclick="editPOTC();" class="button"> EDIT</button>
	 <button type="button" onclick="deletePOTC();" class="button"> DELETE</button>
	 <button type="button" onclick="exitPOTC();" name="exit" value="EXIT" class="button"> EXIT</button>
</div></td></tr>
	<tr align="center"><td><jsp:include page="footer.jsp"></jsp:include></td></tr>
</table></td></tr>
</table>
 </form>
</body>
</html>