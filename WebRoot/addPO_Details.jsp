<%@page import="bean.PO_DETAILS"%>
<%@page import="bean.PO_CommonData"%>
<%@page import="bean.MAJOR_MINOR_CODE"%>
<%@page import="org.KrrCommon"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="calendar.css"></link>
<title>P.O Item Details </title>
<%PO_CommonData commonData = (PO_CommonData) request.getAttribute("po_commondata");%>
<script type="text/javascript" src="calendar.js"></script>
<script language="javascript">

<%--Function for get the selected item from the item window jsp page--%>
	function getSelectedItem(itemCode)
	{
		document.getElementById("POITEMCODE").value=itemCode;
	}

<%--Function for get the total item amount--%>
	function getItemAmount(itemRate)
	{
		var qtyOrdered=document.getElementById("QTYORDER").value;
		var unitCost=document.getElementById("POITEMUNITCOST").value;
		var itemRate=qtyOrdered*unitCost;
		document.getElementById("ORDQTYTOTCOST").value=itemRate;
	}
	
	<%--Function for save the P.O Item details--%>
		function savePoItem()
		{
			var man=mandatoryPOITEMcheck();
			if(man)
			{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=5';
	  		document.forms[0].submit();
	  		}
		}
		
		<%--Function for to go to item window jsp page--%>
		function itemSearch()
		{
			var openWindowForm=document.getElementById('openWindowForm');
			openWindowForm.target='itemSearch';
			window.open("","itemSearch", "width=700,height=600");
			openWindowForm.action='ITEM_CONTROLLER?Option=7';
	  		openWindowForm.submit();
	  		return false;
		}

<%--Function for to go to P.O Item details grid page--%>
		function exitPoItem()
		{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=18';
	  		document.forms[0].submit();
		}
		
	<%--Function for to clear the fields in this page--%>
	function clearPOItem()
	{
		document.getElementById("err_POITEMCODE").innerHTML="";
		document.getElementById("err_UOM").innerHTML="";
		document.getElementById("err_CATALOGNO").innerHTML="";
		document.getElementById("err_POITEMUNITCOST").innerHTML="";
		document.getElementById("err_MNFGCODE").innerHTML="";
		document.getElementById("err_QTYORDER").innerHTML="";
		document.getElementById("err_POITEMBONUS").innerHTML="";
		document.getElementById("err_EXPDELIVERYDT").innerHTML="";
	}
	
<%--Function for validating the item unit cost field--%>
	function poitem_unitcost_Check(price)
	{
		document.getElementById("err_POITEMUNITCOST").innerHTML = "";
		if (price.value.length > 0) 
		{
			var regex1=(/^\d{0,10}(?:\.\d{0,2}){0,2}$/);      //this is the pattern of regular expersion
			if (regex1.test(price.value) == false)
			{	
				document.getElementById("err_POITEMUNITCOST").innerHTML = "Item unit cost should be in numeric only";
				document.gridForm.POITEMUNITCOST.value="";
			}
		}
	}
	
	<%--Function for validating the P.O item bonus field--%>
	function poitem_bonus_Check(price)
	{
	document.getElementById("err_POITEMBONUS").innerHTML = "";
	if (price.value.length > 0) 
	{
		var regex1=/^[0-9]+$/;      //this is the pattern of regular expersion
		if (regex1.test(price.value) == false)
		{
			document.getElementById("err_POITEMBONUS").innerHTML = "Item Bonus should be in numeric only";
			document.gridForm.POITEMBONUS.value="";
		}
	}
	}
	
<%--function for checking the item code field   --%>
function itemCodeCheck(Item_code)
{
	document.getElementById("err_POITEMCODE").innerHTML = "";
	if (Item_code.value.length > 0) 
	{
		var regex1=/^[0-9]+$/;    //this is the pattern of regular expersion
		if (regex1.test(Item_code.value) == false)
		{
			document.getElementById("err_POITEMCODE").innerHTML = "Item code number should be in numeric only";
			document.gridForm.POITEMCODE.value="";
		}
	}
	else  document.getElementById("err_POITEMCODE").innerHTML = "Item code field should not be empty";
}

<%--Function for validating the Catlog number field--%>
function CatlogNoCheck(catlogno)
{
	document.getElementById("err_CATALOGNO").innerHTML = "";
	if (catlogno.value.length > 0) 
	{
		var regex1=/^[A-Za-z/s0-9]+$/;    //this is the pattern of regular expersion
		if (regex1.test(catlogno.value) == false)
		{
			document.getElementById("err_CATALOGNO").innerHTML = "Catlog number should be in alpha-numeric only";
			document.gridForm.CATALOGNO.value="";
		}
	}
}

<%--Function for validating the manufacturer field--%>
function MnfgCodeCheck(mngfcode)
{
	document.getElementById("err_MNFGCODE").innerHTML = "";
	if (mngfcode.value.length > 0) 
	{
		var regex1=/^[A-Za-z/s0-9]+$/;    //this is the pattern of regular expersion
		if (regex1.test(mngfcode.value) == false)
		{
			document.getElementById("err_MNFGCODE").innerHTML = "Manufacturer code should be in alpha-numeric only";
			document.gridForm.MNFGCODE.value="";
		}
	}
}

<%--Function for validating the Quantity order field--%>
function qtyOrderCheck(qtyOrder)
{
	document.getElementById("err_QTYORDER").innerHTML = "";
	if (qtyOrder.value.length > 0) 
	{
		var regex1=/^[0-9]+$/;    //this is the pattern of regular expersion
		if (regex1.test(qtyOrder.value) == false)
		{
			document.getElementById("err_QTYORDER").innerHTML = "quantity ordere should be in numeric only";
			document.gridForm.QTYORDER.value="";
		}
	}
}

<%--Function for validating mandatory field--%>
function mandatoryPOITEMcheck()
{
	var status;
    status=true;
    clearPOItem();
	   if(gridForm.POITEMCODE.value==0)
        {   
	       document.getElementById("err_POITEMCODE").innerHTML="select item code from item search button";
	       status=false;
        }   
        if(gridForm.QTYORDER.value==0)
        {   
	       document.getElementById("err_QTYORDER").innerHTML="Enter quantity ordered";
	       status=false;
        }    
        if(gridForm.POITEMUNITCOST.value==0)
        {   
	       document.getElementById("err_POITEMUNITCOST").innerHTML="Enter unit cost amount";
	       status=false;
        }    
	return status;
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

<%--Function for validating the UOM field--%>
function UOM_select()
{
	document.getElementById("err_UOM").innerHTML="";
	if(gridForm.UOM.value.length==0)
	{
		 document.getElementById("err_UOM").innerHTML="Select U.O.M from list";
	}
}

<%--Function for validating the Expected delivery date field--%>
	function ExpDeliveryDateCheck()
	{
		document.getElementById("err_EXPDELIVERYDT").innerHTML = "";
		if (gridForm.EXPDELIVERYDT.value.length > 0)
		{
			if (gridForm.EXPDELIVERYDT.value.length != 10)
			{
				document.getElementById("err_EXPDELIVERYDT").innerHTML = "Date Should be DD-MM-YYYY";
			} 
			else 
			{
				var ll = position(gridForm.EXPDELIVERYDT.value);
				var ll1 = position1(gridForm.EXPDELIVERYDT.value);
				if (!(ll == "-" && ll1 == "-"))
				{
					document.getElementById("err_EXPDELIVERYDT").innerHTML = "Date Should be DD-MM-YYYY";
				}
				else
				{
					var bval = Comparedates(gridForm.EXPDELIVERYDT.value,Getcurrentdate());
					if (bval == false) 
					{
						document.getElementById("err_EXPDELIVERYDT").innerHTML = "Date Should not be Future date";
					} 
				}
			}
		}
	}
	
<%--Function for changing the Item search button to hidden--%>
function editFormFucntion() 
{
	var option = getParameterByName("Option");
	if (option == 13 )
	{
		document.getElementById('POITEMCODE').readOnly = true;
		document.getElementById('itembutton').style.visibility = "hidden";
	}
}

<%--Function for get the item details using item code--%>
function getAllItemDetails()
{	
	var option = getParameterByName("Option");
	if (option != 13)
	{
		var itemCodeValue=document.getElementById('POITEMCODE').value;
		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=20&POITEMCODE='+itemCodeValue;
  		document.forms[0].submit();
  	}
}

</script>
<title>Add PO Item</title>
</head>
<body onload="editFormFucntion()">
	<form  method="post" name="gridForm" id="gridForm">
	<input type="hidden" name="USERNAME" id="USERNAME" value="<%=commonData.getUSER_NAME()%>" >
  	<input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGIN_DATE() %>">
  	<input type="hidden" name="USERROLE" id="USERROLE" value="<%=commonData.getUSER_ROLE()%>">
  	<input type="hidden" name="LOGINID" id="LOGINID" value="<%=commonData.getLOGIN_ID()%>">
  	<input type="hidden" name="pageNo" value="<%=commonData.getPAGE_NO()%>">
  	<input type="hidden" name="PONO" value="<%=commonData.getPO_NO()%>">
  	<input type="hidden" name="POSUPPLIERNAME" value="<%=commonData.getPO_SUPPLIER_NAME()%>">
	<table  class="mainTable" style="height: 650px" align="center">
	<tr align="center"><td align="center"><jsp:include page="header1.jsp"></jsp:include></td></tr>
	<tr><td> <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    		<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getUSER_NAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getLOGIN_DATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
	
	<tr><td> <br><table class="childTable" height="200" align="center">
				<tr><td colspan="6"><h2 align="left">PO ITEM</h2></td></tr>
		<%PO_DETAILS po_details =(PO_DETAILS)request.getAttribute("po_details");
			long POITEMCODE=po_details.getPOITEMCODE();
			String POITEMCODEX;  	POITEMCODEX="";
 	 			if (POITEMCODE>0)POITEMCODEX=""+POITEMCODE;
			String POITEMDESCRIPTION=po_details.getPOITEMDESCRIPTION();
				if(POITEMDESCRIPTION==null) POITEMDESCRIPTION="";
			String UOM=po_details.getUOM();
				if(UOM==null) UOM="";
			String CATALOGNO=po_details.getCATALOGNO();
				if(CATALOGNO==null) CATALOGNO="";
			String MNFGCODE=po_details.getMNFGCODE();
				if(MNFGCODE==null) MNFGCODE="";
			double POITEMUNITCOST=po_details.getPOITEMUNITCOST();
			String POITEMUNITCOSTX;  	POITEMUNITCOSTX="";
 	 			if (POITEMUNITCOST>0)POITEMUNITCOSTX=""+POITEMUNITCOST;
 	 		double QTYORDER=po_details.getQTYORDER();
			String QTYORDERX;  	QTYORDERX="";
 	 			if (QTYORDER>0)QTYORDERX=""+QTYORDER;
 	 		double ORDQTYTOTCOST=po_details.getORDQTYTOTCOST();
			String ORDQTYTOTCOSTX;  	ORDQTYTOTCOSTX="";
 	 			if (ORDQTYTOTCOST>0)ORDQTYTOTCOSTX=""+ORDQTYTOTCOST;
 	 		long POITEMBONUS=po_details.getPOITEMBONUS();
 	 		String POITEMBONUSX; POITEMBONUSX="";
 	 			if(POITEMBONUS>0) POITEMBONUSX=""+POITEMBONUS;
 	 		String EXPDELIVERYDT=po_details.getEXPDELIVERYDT();
 	 			if(EXPDELIVERYDT==null) EXPDELIVERYDT="";
		%> 
		<input type="hidden" name="POITEMSRNO" value="<%=po_details.getPODETAILSSRNO()%>">
		
		<tr>
			<td colspan="1">PO Number:</td>
			<td colspan="5"><input type="text" name="ADDPONO" id="ADDPONO"  value="<%= request.getAttribute("ADDPONO")%>" maxlength="25" onblur="" readonly="readonly" style="width: 65%;background-color:#e6e6e6"/>
			</td></tr>
		
		<tr>
	    <td colspan="1">Item Code:</td>
        <td colspan="2"><input type="text" name="POITEMCODE" id="POITEMCODE" value="<%=POITEMCODEX%>" maxlength="30"  style="width: 65%" onblur="getAllItemDetails();"/>
   	    <button id="itembutton" onclick="itemSearch(); return false;">Item Search</button>
   	    <br><span style="color:red;" id="err_POITEMCODE"></span>
   	    </td>
   	    </tr>
   	    
   	    
   	    <tr>
	    <td colspan="1">Item Description:</td>
        <td colspan="2"><input type="text" name="POITEMDESCRIPTION" id="POITEMDESCRIPTION" value="<%=POITEMDESCRIPTION%>" maxlength="50" readonly="readonly" style="background-color:#e6e6e6;width: 65%"/>
   	    </td>
   	    </tr>
   	    
   	    <tr>
	<td class="formLable">U.O.M:</td>
	<td>
	<select name="UOM"  id="UOM" style="width:65%" onblur="UOM_select();">
   			<%
						if(UOM==null||UOM=="")
						{
						%>
							<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=UOM%>" selected><%=UOM%></option>
						<%
	 						}
	  					%>
			     <%  List<MAJOR_MINOR_CODE> uomList =  (List<MAJOR_MINOR_CODE>)request.getAttribute("UOMLIST"); 
   				    for(MAJOR_MINOR_CODE majorMinorCode : uomList){
   					%><option value="<%=majorMinorCode.getMINORCODEDESC()%>"> <%=majorMinorCode.getMINORCODEDESC()%></option> <%
   					}
   				%>
    		</select> <font color="#ff0000">*</font>
			<br><span style="color:red;" id="err_UOM"/></span>
			</td>
			</tr>
		
		<tr>
		<td colspan="1">CATALOG NO:</td>
		<td colspan="2"><input type="text" name="CATALOGNO" id="CATALOGNO" value="<%=CATALOGNO%>" maxlength="60" style="width: 65%" onblur="CatlogNoCheck(this);">
		<br><span style="color:red;" id="err_CATALOGNO"></span></td>
		</tr>
		
		<tr>
		<td colspan="1">Manufacture CODE:</td>
		<td colspan="2"><input type="text" name="MNFGCODE" id="MNFGCODE" value="<%=MNFGCODE%>" maxlength="10" style="width: 65%" onblur="MnfgCodeCheck(this);"  >
		<br><span style="color:red;" id="err_MNFGCODE"></span></td>
		</tr>	
		
   	    <tr>
		<td colspan="1">Unit Cost:(in Rupees)</td>
		<td colspan="2"><input type="text" name="POITEMUNITCOST" id="POITEMUNITCOST" value="<%=POITEMUNITCOSTX%>" maxlength="13"  style="width: 65%" onblur="poitem_unitcost_Check(this);" >
		<br><span style="color:red;" id="err_POITEMUNITCOST"></span></td>
		</tr>
		
   	    <tr>
		<td colspan="1">Quantity Ordered:</td>
		<td colspan="2"><input type="text" name="QTYORDER" id="QTYORDER" value="<%=QTYORDERX%>" maxlength="10" style="width: 65%" onblur="qtyOrderCheck(this);"  >
		<br><span style="color:red;" id="err_QTYORDER"></span></td>
		</tr>
		
		<tr>
	    <td colspan="1">Item Bonus:</td>
        <td colspan="2"><input type="text" name="POITEMBONUS" id="POITEMBONUS" value="<%=POITEMBONUSX%>" maxlength="10" style="width: 65%" onblur="poitem_bonus_Check(this);"/>
   	    <br><span style="color:red;" id="err_POITEMBONUS"></span></td>
   	    </tr>
   	    
   	    <tr>
   	    <td colspan="1">Expected Delivery Date:</td>
		<td colspan="2" class="formLable">
		<input type="text" name="EXPDELIVERYDT" id="EXPDELIVERYDT" style="width: 65%" value="<%=EXPDELIVERYDT%>" maxlength="10" onblur="ExpDeliveryDateCheck(this);"/>
		<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'EXPDELIVERYDT');" value="EXPDELIVERYDT"> 
		<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
		<jsp:include page="Calender.jsp"></jsp:include>
		<br><span style="color:red;" id="err_EXPDELIVERYDT"></span></td>
		</tr>
   	    
   	    <tr>
		<td colspan="1">Total cost:</td>
		<td colspan="2"><input type="text" name="ORDQTYTOTCOST" id="ORDQTYTOTCOST"  value="<%=ORDQTYTOTCOSTX%>" style="background-color:#e6e6e6;width: 65%" maxlength="17" onclick="getItemAmount(this.value);" readonly="readonly">
		<br><span style="color:red;" id=""></span></td>
		</tr>
		
		 <%KrrCommon krrCommon = new KrrCommon();
  			String message=(String)request.getAttribute("message");
  			if(krrCommon.isValuenull(message)){
  			message ="";
  		}%>	
  		
	<tr>
	<td colspan="6" align="center"> 
	<p> <%=message%></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="6">
	 <button type="button" onclick="return savePoItem();" class="button"> SAVE</button>
	 <button type="reset" class="button" onclick="clearPOItem();"> RESET</button>	
	<button type="button" onclick="exitPoItem();" name="exit" value="EXIT" class="button"> EXIT</button>
	</td>
	</tr>
	</table></td></tr>
	<tr align="center"><td><jsp:include page="footer.jsp"></jsp:include></td></tr></table></td></tr>
</table>
	</form>
	<form  method="post" name="openWindowForm" id="openWindowForm">
	<input type="hidden" name="USERNAME" id="USERNAME" value="<%=commonData.getUSER_NAME()%>" >
  	 <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGIN_DATE()%>">
  	 <input type="hidden" name="USERROLE" id="USERROLE" value="<%=commonData.getUSER_ROLE()%>">
  	 <input type="hidden" name="LOGINID" id="LOGINID" value="<%=commonData.getLOGIN_ID()%>">
  	 </form>
</body>
</html>