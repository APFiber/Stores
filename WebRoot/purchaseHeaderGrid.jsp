<%@page import="bean.PO_CommonData"%>
<%@ page language="java" import="java.util.List" pageEncoding="ISO-8859-1"%>
<%@page import="bean.PO_HEADER"%>
<%@page import="org.KrrCommon"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>PO Grid</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<% KrrCommon krrCommon = new KrrCommon();
			PO_CommonData commonData = (PO_CommonData) request.getAttribute("po_commondata"); 
			String PONO1=commonData.getPO_NO(); 				   				
 	 			if(PONO1==null) PONO1="";
 	 		String POSUPPLIERNAME=commonData.getPO_SUPPLIER_NAME();
 	 			if(POSUPPLIERNAME==null) POSUPPLIERNAME="";
		%>
		<script type="text/javascript">

<%--function to get radio value--%>
function getRadioVal() 
{
    var val;
    var radios = document.getElementsByName('POSRNO1');
    for (var i=0, len=radios.length; i<len; i++) 
    {
        if ( radios[i].checked ) 
        { 
            val = radios[i].value; 
            break; 
        }
    }
    return val; 
}
	
//function for to populate the data from the from the data base
function editPOHEADER()
{
	if(getRadioVal() == null || getRadioVal() == "0")
	{
		alert('Please select Purchase Order');
	}
	else
	{
		var selected_value= getRadioVal();
	    document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=12&POSRNO='+selected_value;
		document.getElementById("gridForm").submit();
		return true; 
	} 
}

<%--function to change PO Status to completed--%>
function completePurchaseOrder()
{
	if(getRadioVal() == null || getRadioVal() == "0")
	{
		alert('Please select Purchase Order Number');
	}
	else
	{
		var selected_value= getRadioVal();
	    document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=11&POSRNO='+selected_value;
		document.getElementById("gridForm").submit();
		return true; 
	} 
}

<%--Enter page no field validation function --%>
	function pagination(e) 
	{
		var totalpages=<%=commonData.getTotal_NoOf_Pages()%>;
   		var totalRows = document.getElementsByTagName("table")[0].getElementsByTagName("tr").length;
   		for(var j=1; j<=totalRows; j++){
   		if(document.getElementById(j) !=null){
   			document.getElementById(j).style.display = "none";
   			}
   		}
		var pageNo= document.getElementById("pageNo").value;
		var lastRecord=pageNo*10;
		var firstRecord =lastRecord-9;
		for (var i = firstRecord; i <= lastRecord; i++) {
			document.getElementById(i).style.display = "";
		};
	}
	
<%--function to search an item--%>
	function search()
	{
	document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=1';
	 document.getElementById("gridForm").submit();
	}

<%--function to clear the search fields	--%>
	function clearSearch()
	{
	document.getElementById("PONO").value="";
	document.getElementById("POSUPPLIERNAME").selectedIndex = 0;
	}	

<%--function to authorize purchase order	--%>
	function authorizePurchaseOrder()
	{
	if(getRadioVal() == null || getRadioVal() == "0")
	     {
			alert('Please select PO Number');
	     }
	     else
	     {
	      	var selected_value = getRadioVal();
			document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=8&POSRNO='+selected_value;
	 		document.getElementById("gridForm").submit();
	 }
	 return true;
	}

<%--function to delete puchase order	--%>
	function deletePurchaseOrder()
	{
	if(getRadioVal() == null || getRadioVal() == "0")
	     {
			alert('Please select PO Number');
	     }
	     else
	     {
	      	var selected_value = getRadioVal();
			document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=9&POSRNO='+selected_value;
	 		document.getElementById("gridForm").submit();
	 }
	 return true;
	}
	
	<%--function to generate PDF --%>
	function pdfPurchaseOrder()
	{
	if(getRadioVal() == null || getRadioVal() == "0")
	     {
			alert('Please select PO Number');
	     }
	     else
	     {
	      	var selected_value = getRadioVal();
			document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=17&POSRNO='+selected_value;
	 		document.getElementById("gridForm").submit();
	 }
	 return true;
	}
	
<%--function to exit from grid page to menu page--%>
	function exitPurchaseGrid()
	{
		document.getElementById("gridForm").action ='TERA_STORES_PURCHASE?Option=3';
	 	document.getElementById("gridForm").submit();
	}
	
<%--function to display addPOHeader.jsp page--%>	
	function addpoHeaderPurchase()
	{
		document.getElementById("gridForm").action ='PURCHASE_ORDER_CONTROLLER?Option=2';
		document.getElementById("gridForm").submit();
	}
   </script>
	</head>
	<body>
	<form name="gridForm" id="gridForm" method="POST">
	<input type="hidden" name="USERNAME" id="USERNAME" value="<%=commonData.getUSER_NAME()%>">
	<input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGIN_DATE()%>">
	<input type="hidden" name="USERROLE" id="USERROLE" value="<%=commonData.getUSER_ROLE()%>">
    <input type="hidden" name="LOGINID" id="LOGINID" value="<%=commonData.getLOGIN_ID()%>">
	<table align="center" class="childTable">
		<tr align="center">
		<td><img class="headerImg" src="img/StoresHeader.jpg"></td>
		</tr>
		<tr>
		<td align="center">
		<table border="0" cellspacing="0" cellpadding="0" class="childTable">
		<tr>
			<tr>
				<td valign="top" align="right">	Welcome : <%=commonData.getUSER_NAME()%>, Date:<%=commonData.getLOGIN_DATE()%>
				</td>
			</tr>
			<tr> <td align="center"><h2>Purchase Order Details</h2>	</td></tr>
			<tr>
			    <td>
					<table class="gridTable gridTableBg" align="center" width="650"	height="56">
						<tr class=th>
							<th>Select</th>
							<th>PoNo</th>
							<th>PoDate</th>
							<th>Supplier</th>
							<th>Status</th>
						</tr>
			<% List<PO_HEADER> POBeanList = (List<PO_HEADER>) request.getAttribute("poheaderList");
				int endRecord=commonData.getPAGE_NO()*10;
				int startRecord=endRecord-10;
				int i = 1;
				for (PO_HEADER PO : POBeanList){
				 String PO_PONO=PO.getPONO(); 				   				
				 	 if(PO_PONO==null) PO_PONO="";
				 String PO_PODATE=PO.getPODATE(); 				   				
				 	 if(PO_PODATE==null) PO_PODATE="";
				 String PO_POSUPPLIERNAME=PO.getPOSUPPLIERNAME(); 				   				
				 	if(PO_POSUPPLIERNAME==null) PO_POSUPPLIERNAME="";
				 String PO_POSTATUS=PO.getPOSTATUS();				   				
				 	if(PO_POSTATUS==null) PO_POSTATUS="";
				 if (i > startRecord && i <= endRecord) {%>
				<tr id="<%=i%>">
					<%} else {%>
				<tr style="display : none;" id="<%=i%>">
				<%}%>
				<%if(PO.getPOSRNO() > 0) 
 	 			{
 	 			%>
				<td align="center"> <input type="radio" name="POSRNO1" id="POSRNO1" value="<%=PO.getPOSRNO()%>"/></td>
					<td><%=PO_PONO%></td>
					<td><%=PO_PODATE%></td>
					<td><%=PO_POSUPPLIERNAME%></td>
					<td><%=PO_POSTATUS%></td>
				</tr>
				<% 
 	 			}
 	 			else 
 	 			{
 	 			%>
 					<td> &nbsp </td>
 	 				<td></td>
 	 				<td></td>
 	 				<td></td>
 	 				<td></td>
 	 			<%
 	 			} 
 	 			%>
					<%	i++; }//for %>
		</table>
			<div class="pagination">
			<%
				String message = (String) request.getAttribute("message");
					if (krrCommon.isValuenull(message)) {
						message = "";
					}
			%>
		<p align="center">
		<font color="red">Status : <%=message%></font>
		</p>
	<p align="center"> Total Number of Records : <%=commonData.getTotal_NoOf_Records()%> | Total Number of Pages :<span id="totalNoOfPages"><%=commonData.getTotal_NoOf_Pages()%>
	</span> | Select Page No:
			<select name="pageNo" id="pageNo" onchange="pagination()">
				<% for (int j = 1; j <= commonData.getTotal_NoOf_Pages(); j++) {
					  if (j==commonData.getPAGE_NO()) {
					%>
					<option value="<%=j%>" selected><%=j%></option>
					<%
						} else {
					%>
					<option value="<%=j%>"><%=j%></option>
					<%
					}
					}
					%>
	</select>
		</p>
		</div>

		<div class="searchCriteriaDiv">
		<span class="searchCriteriaLabel">Search Criteria</span>
		<table style="width: 100%" align="center">
		<tr>
			<td>PO Number</td>
			<td><input type="text" id="PONO" name="PONO" style="width: 65%" value="<%=PONO1%>">
			<td>Supplier</td>
			<td><select id="POSUPPLIERNAME" name="POSUPPLIERNAME" style="width: 77%">
				<option value="" selected>- - Select - -</option>
				<%
				if (!(POSUPPLIERNAME == null || POSUPPLIERNAME == "")) {
				%>
				<option value="<%=POSUPPLIERNAME%>" selected><%=POSUPPLIERNAME%></option>
				<%
					}
				%>
						<%List<PO_HEADER> supplierlist = (List<PO_HEADER>) request.getAttribute("supplierlist");
							for (PO_HEADER supplier : supplierlist) {
								%><option value="<%=supplier.getPOSUPPLIERNAME()%>">
								<%=supplier.getPOSUPPLIERNAME()%></option>
									<%
									}
									%>
				</td>
			</tr>
		</table>

	<div class="searchCriteriaButtons">
	<button type="button" onclick="search();" class="button">Search</button>
	<button type="button" onclick="clearSearch();" class="button">Clear Search</button>
	</div>
	</div>
	<tr align="center">
	<td>
	<div class="operationalButtons">
		<button type="button" onclick="addpoHeaderPurchase()" class="button">New Purchase Order</button>
		<button type="button" onclick="editPOHEADER();" class="button">EDIT	</button>
		<button type="button" onclick="completePurchaseOrder()" class="button">Complete PO</button>
		<button type="button" onclick="pdfPurchaseOrder()" class="button">Print PO</button>
		<% String USERROLE = (String) commonData.getUSER_ROLE();
				if (USERROLE.equalsIgnoreCase("ADMIN") || USERROLE.equalsIgnoreCase("SUPERVISOR"))
					//if(true)
					{%>
					<button type="button" onclick="authorizePurchaseOrder()" class="button">AUTHORIZE</button>
					<button type="button" onclick="deletePurchaseOrder()" class="button">DELETE</button>
					<%}%>
					<button type="button" onclick="exitPurchaseGrid();" class="button">EXIT</button>
					</div>
					</td>
				</tr>
					<tr align="center">
						<td>
					<img class="headerImg" src="img/footer.jpg">
				</td>
					</tr>
			</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>