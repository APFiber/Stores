<%@page import="bean.ITEM_DETAILS"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="bean.ITEM_CommonData"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Add item</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript">

//To save the item details	
function itemSave()
{
      var man=mandatoryItemcheck();
      if(man)
      {
      document.forms[0].action='ITEM_CONTROLLER?Option=9';
	  document.forms[0].submit();
	  }
}

//To clear the fields in addItem.jsp page
function itemResetErrors()
{
	document.getElementById("err_openingBalance_check").innerHTML="";
}

//To go to addItem.jsp page to the itemGrid.jsp page	
function exitAddItem()
{
   	document.forms[0].action='ITEM_CONTROLLER?Option=1';
 	document.forms[0].submit();
} 

//To check the mandatory errors in addItem.jsp page	   	
function mandatoryItemcheck()
{
	var status;
    status=true;
    itemResetErrors();	
       if(itemDtls.OPENINGBALANCE.value.length==0)
       {   
	       document.getElementById("err_openingBalance_check").innerHTML="Enter Opening balance number";
	       status=false;
       }
	return status;
}


//Function fot item Model field checking
function itemModel_Check(modelcheck)
{
	document.getElementById("err_model_check").innerHTML = "";
	if (itemDtls.ITEMMODEL.value.length == 0) 
	{
		document.getElementById("err_model_check").innerHTML = "Item Model field should not be empty";
	}
}

//Function for item imported field checking 
function itemImported_Select(imp)
{
  	document.getElementById("err_imported").innerHTML="";
	if(imp.value.length==0)
    {   
	   document.getElementById("err_imported").innerHTML="Select yes or no from list";  
    }
}

	</script>
 </head>
  <body onload="editFormFucntion()">
  <%
  	ITEM_CommonData commonData = (ITEM_CommonData)request.getAttribute("commonData");
  %>
     <form action="item" method="post" name="itemDtls">
     <input type="hidden" name="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  	 <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGINDATE() %>">
  	 <input type="hidden" name="USERROLE" value="<%=commonData.getUSERROLE() %>">
  	 <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID()%>">
  	 <input type="hidden" id="ITEMCODE" name="ITEMCODE" value="<%=commonData.getITEMCODE()%>" />
  	 <input type="hidden" id="ITEMGROUP" name="ITEMGROUP" value="<%=commonData.getITEMGROUP()%>" />
  	 <input type="hidden" id="ITEMMAKE" name="ITEMMAKE" value="<%=commonData.getITEMMAKE()%>">
  	 <input type="hidden" id="ITEMDESCRIPTION" name="ITEMDESCRIPTION" value="<%=commonData.getITEMDESCRIPTION()%>">
  	 <input type="hidden" id="ITEMSTATUS" name="ITEMSTATUS" value="<%=commonData.getITEMSTATUS() %>">
  	 <input type="hidden" id="pageNo" name="pageNo" value="<%=commonData.getPage_No()%>">
  	 
    <%ITEM_DETAILS item =(ITEM_DETAILS)request.getAttribute("item");
 	 		String ADD_ITEMGROUP=item.getITEMGROUP(); 				   				
 	 			if(ADD_ITEMGROUP==null) ADD_ITEMGROUP="";
 	 		String ADD_ITEMMAKE=item.getITEMMAKE(); 				   				
 	 			if(ADD_ITEMMAKE==null) ADD_ITEMMAKE="";
 	 		String ITEMMODEL=item.getITEMMODEL(); 				   				
 	 			if(ITEMMODEL==null) ITEMMODEL="";
 	 		String ADD_ITEMDESCRIPTION=item.getITEMDESCRIPTION();
 	 			if(ADD_ITEMDESCRIPTION==null) ADD_ITEMDESCRIPTION="";
 	 		 double OPENINGBALANCE=item.getOPENINGBALANCE();				   				
 	 			String OPENINGBALANCEX;  	OPENINGBALANCEX="";
 	 			if (OPENINGBALANCE>0)OPENINGBALANCEX=""+OPENINGBALANCE;
    %> 
        <input type="hidden" name="ITEMCODESRNO" value="<%=item.getITEMCODE()%>">
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
				<tr><td colspan="2"><h2 align="center">OPENING BALANCE DETAILS </h2></td></tr>

	
	<tr>
	<td class="formLable">Item Group:</td>
	<td><input type="text" name="ADD_ITEMGROUP" id="ADD_ITEMGROUP" style="width:65%;background-color: #e6e6e6" value="<%=ADD_ITEMGROUP%>" maxlength="30" readonly="readonly"/>
	<br><span style="color:red;" id="err_model_check"> </span></td>
	</tr>
	
	<tr>
	<td class="formLable">Item Make:</td>
	<td><input type="text" name="ADD_ITEMMAKE" id="ADD_ITEMMAKE" style="width:65%;background-color: #e6e6e6" value="<%=ADD_ITEMMAKE%>" maxlength="30" readonly="readonly"/>
	<br><span style="color:red;" id="err_model_check"> </span></td>
	</tr>
	
	<tr>
	<td class="formLable">Item Model:</td>
	<td><input type="text" name="ITEMMODEL" id="ITEMMODEL" style="width:65%;background-color: #e6e6e6" value="<%=ITEMMODEL%>" maxlength="30" readonly="readonly"/>
	<br><span style="color:red;" id="err_model_check"> </span></td>
	</tr>
	
	<tr>
	<td class="formLable">Item Description:</td>
	<td><input type="text" name="ADD_ITEMDESCRIPTION" id="ADD_ITEMDESCRIPTION" style="width:65%;background-color: #e6e6e6" value="<%=ADD_ITEMDESCRIPTION%>" maxlength="50" readonly="readonly"/>
	<br><span style="color:red;" id="err_desc_check" > </span></td>
	</tr>
	
	
	<tr>
	<td class="formLable">Opening Balance:</td>
	<td><input type="text" name="OPENINGBALANCE" id="OPENINGBALANCE" style="width:65%" value="<%=OPENINGBALANCEX%>" maxlength="50" onblur=""/>
	<br><span style="color:red;" id="err_openingBalance_check" > </span></td>
	</tr>
	
	<tr>
	<td colspan="2" align="center" style="color: red"> 
	<p><%=request.getAttribute("message") %></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="2">
	 <button type="button" onclick="return itemSave()" class="button"> SAVE</button>
	 <button type="reset" class="button" onclick="itemResetErrors();"> RESET</button>	
	 <button type="button" onclick="exitAddItem()" name="exit" value="EXIT" class="button"> EXIT</button>
	</td>
	</tr>
	</table></td></tr>
		</table></td></tr>
	<tr align="center"><td><jsp:include page="footer.jsp"></jsp:include><br></td></tr>
</table>
</form>
</body>
</html>
