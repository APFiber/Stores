<%@page import="bean.ITEM_DETAILS"%>
<%@page import="bean.MAJOR_MINOR_CODE"%>
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
      document.forms[0].action='ITEM_CONTROLLER?Option=3';
	  document.forms[0].submit();
	  }
}

//To clear the fields in addItem.jsp page
function itemResetErrors()
{
	document.getElementById("err_make_check").innerHTML="";
	document.getElementById("err_desc_check").innerHTML="";
	document.getElementById("err_model_check").innerHTML="";
	document.getElementById("err_group").innerHTML="";
	document.getElementById("err_units_check").innerHTML="";
	document.getElementById("err_spl").innerHTML="";
	document.getElementById("err_imp_item").innerHTML="";
	document.getElementById("err_OPENINGBALANCE").innerHTML="";
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
	   
       if(itemDtls.ADD_ITEMGROUP.value.length==0)
        {   
	       document.getElementById("err_group").innerHTML="Select item group from list ";
	       status=false;
        }
       if(itemDtls.ADD_ITEMMAKE.value.length==0)
       {   
	       document.getElementById("err_make_check").innerHTML="Select item make from list";
	       status=false;
       }
       if(itemDtls.ITEMUNITS.value.length==0)
       {   
	       document.getElementById("err_units_check").innerHTML="Select item units from list";
	       status=false;
       }
	return status;
}

//Function for Make field checking
function itemMake_Select()
{   
    document.getElementById("err_make_check").innerHTML = "";
    if(itemDtls.ADD_ITEMMAKE.value.length==0)
    {   
	      document.getElementById("err_make_check").innerHTML="select Item make from list";
	}
}

//Function for item group checking field
function itemGroup_Select()
{
   document.getElementById("err_group").innerHTML="";
   if(itemDtls.ADD_ITEMGROUP.value.length==0)
   {   
	    document.getElementById("err_group").innerHTML="select Item Group from list";
   }
}

//Function for item units field checking 
function itemUnits_Select()
{
     document.getElementById("err_units_check").innerHTML="";
	 if(itemDtls.ITEMUNITS.value.length==0)
     {   
	     document.getElementById("err_units_check").innerHTML="Select item units from list";  
     }
}

//Function for item sale units field checking 
function itemSaleUnits_Select()
{
	 document.getElementById("err_sale_check").innerHTML="";
	 if(itemDtls.ITEMSALEUNITS.value.length==0)
     {   
       document.getElementById("err_sale_check").innerHTML="Select units from list";  
     }
}

//Function for item package units field checking  
function itemPackageUnits_Select()
{
	document.getElementById("err_package_check").innerHTML = "";
	if(itemDtls.ITEMPACKAGEUNITS.value.length==0)
    {   
	   document.getElementById("err_package_check").innerHTML="select package units from list";
	}
}

//Function fot item Description field checking
function itemDescription_Check(desccheck)
{
	document.getElementById("err_desc_check").innerHTML = "";
	if (itemDtls.ADD_ITEMDESCRIPTION.value.length == 0) 
	{
		document.getElementById("err_desc_check").innerHTML = "Description field should not be empty";
	}
}

//Function fot item Model field checking
function itemModel_Check(modelcheck)
{
	document.getElementById("err_model_check").innerHTML = "";
	if (inputtxt.value.length > 0) 
	{
		var reg = /^[0-9A-Za-z/s]+$/;
		if (reg.test(inputtxt.value) == false) {
			document.getElementById("err_model_check").innerHTML = "Item model should accept alpha numeric only";
			document.ITEMMODEL.value="";
		}
	}
}


function openBalance(inputtxt)
{
	document.getElementById("err_OPENINGBALANCE").innerHTML = "";
	if (inputtxt.value.length > 0) 
	{
		var reg = /^[0-9]+$/;
		if (reg.test(inputtxt.value) == false) {
			document.getElementById("err_OPENINGBALANCE").innerHTML = "Opening balances should be in numeric only";
			document.OPENINGBALANCE.value="";
		}
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

function getParameterByName(name, url) 
{
	if (!url)
	url = window.location.href;
	url = url.toLowerCase(); // This is just to avoid case sensitiveness  
	name = name.replace(/[\[\]]/g, "\\$&").toLowerCase();// This is just to avoid case sensitiveness for query parameter name
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}

//Function for set the item code to read only when it is in type of edit
function editFormFucntion() 
{
	var option = getParameterByName("Option");
	if (option == 4)
	{
		document.getElementById('ADD_ITEM_CODE').readOnly = true;
		document.getElementById('reset').type = "hidden";
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
 	 		String ITEMUNITS=item.getITEMUNITS();
 	 			if(ITEMUNITS==null) ITEMUNITS="";
 	 		 String ITEMSPECIALITEM=item.getITEMSPECIALITEM();
 	 		 	if(ITEMSPECIALITEM==null) ITEMSPECIALITEM="";
 	 		 String ITEMIMPORTEDITEM=item.getITEMIMPORTEDITEM();
 	 		 	if(ITEMIMPORTEDITEM==null) ITEMIMPORTEDITEM="";
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
				<tr><td colspan="2"><h2 align="center">ADD ITEM DETAILS </h2></td></tr>


	<tr>
	<td class="formLable">Item Group:</td>
	<td>
	<select name="ADD_ITEMGROUP"  id="ADD_ITEMGROUP" style="width:65%" onblur="itemGroup_Select();">
   			<%
						if(ADD_ITEMGROUP==null||ADD_ITEMGROUP=="")
						{
						%>
							<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=ADD_ITEMGROUP%>" selected><%=ADD_ITEMGROUP%></option>
						<%
	 						}
	  					%>
			     <%  List<MAJOR_MINOR_CODE> groupList =  (List<MAJOR_MINOR_CODE>)request.getAttribute("ITEMGROUP"); 
   				    for(MAJOR_MINOR_CODE majorMinorCode : groupList){
					%><option value="<%=majorMinorCode.getMINORCODEDESC()%>"> <%=majorMinorCode.getMINORCODEDESC()%></option> <%
   					}
   				%>
    		</select><font color="#ff0000"> *</font>
        		<br><span style="color:red;" id="err_group" > </span>		
	</td>
	</tr>
	
	<tr>
	<td class="formLable">Item Make:</td>
	<td>
	<select name="ADD_ITEMMAKE"  id="ADD_ITEMMAKE" style="width:65%" onblur="itemMake_Select();">
   			<%
						if(ADD_ITEMMAKE==null||ADD_ITEMMAKE=="")
						{
						%>
							<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=ADD_ITEMMAKE%>" selected><%=ADD_ITEMMAKE%></option>
						<%
	 						}
	  					%>
			     <%  List<MAJOR_MINOR_CODE> makeList =  (List<MAJOR_MINOR_CODE>)request.getAttribute("ITEMMAKE"); 
   				    for(MAJOR_MINOR_CODE majorMinorCode : makeList){
   					%><option value="<%=majorMinorCode.getMINORCODEDESC()%>"> <%=majorMinorCode.getMINORCODEDESC()%></option> <%
   					}
   				%>
    		</select> <font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_make_check"/></span>
	</td>
	</tr>
	
	<tr>
	<td class="formLable">Item Model:</td>
	<td><input type="text" name="ITEMMODEL" id="ITEMMODEL" style="width:65%" value="<%=ITEMMODEL%>" maxlength="30" onblur="itemModel_Check(this);"/>
	<br><span style="color:red;" id="err_model_check"> </span></td>
	</tr>
	
	<tr>
	<td class="formLable">Item Description:</td>
	<td><input type="text" name="ADD_ITEMDESCRIPTION" id="ADD_ITEMDESCRIPTION" style="width:65%" value="<%=ADD_ITEMDESCRIPTION%>" maxlength="50" onblur="itemDescription_Check(this);"/>
	<br><span style="color:red;" id="err_desc_check" > </span></td>
	</tr>
	
	<tr>
	<td class="formLable">Item Units:</td>
	<td>
	<select name="ITEMUNITS" id="ITEMUNITS" class="input_select" style="width:65%" onblur="itemUnits_Select();">
   			<%
								if(ITEMUNITS==null||ITEMUNITS=="")
								{
						%>
							<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=ITEMUNITS%>" selected><%=ITEMUNITS%></option>
						<%
	 						}
	  					%>
   				<%List<MAJOR_MINOR_CODE> unitCodeList = (List<MAJOR_MINOR_CODE>)request.getAttribute("ITEMUNITS"); 
   					for(MAJOR_MINOR_CODE code : unitCodeList ){
   						%>
   						   <option value="<%=code.getMINORCODEDESC()%>" ><%=code.getMINORCODEDESC()%></option>
   						<%
   					}
   				%>
   			</select> <font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_units_check" > </span>
	</td>
	</tr>
	
	
	<tr>
	<td class="formLable">Special item :</td>
	<td>
	<select name="ITEMSPECIALITEM" id="ITEMSPECIALITEM" class="input_select" style="width:65%">
   			<%
						if(ITEMSPECIALITEM==null||ITEMSPECIALITEM=="")
						{
						%>
							<option value="" selected>- - Select - -</option>
						<%
	 						}else{
	  					%>
							<option value="<%=ITEMSPECIALITEM%>" selected><%=ITEMSPECIALITEM%></option>
						<%
	 						}
	  					%>
	  					<option value="Y">YES</option>
						<option value="N">NO</option>
   			</select> 
   			
	<br><span style="color:red;" id="err_spl" > </span>
	</td>
	</tr>
	
	<tr>
	<td class="formLable">Imported item :</td>
	<td>
	<select name="ITEMIMPORTEDITEM" id="ITEMIMPORTEDITEM" class="input_select" style="width:65%">
   			<%
						if(ITEMIMPORTEDITEM==null||ITEMIMPORTEDITEM=="")
						{
						%>
							<option value="" selected>- - Select - -</option>
						<%
	 						}else{
	  					%>
							<option value="<%=ITEMIMPORTEDITEM%>" selected><%=ITEMIMPORTEDITEM%></option>
						<%
	 						}
	  					%>
	  					<option value="Y">YES</option>
						<option value="N">NO</option>
   			</select> 
	<br><span style="color:red;" id="err_imp_item" > </span>
	</td>
	</tr>
	<tr>
	</tr>
	
	<tr>
	<td class="formLable">Opening Balances:</td>
	<td><input type="text" name="OPENINGBALANCE" id="OPENINGBALANCE" style="width:65%" value="<%=OPENINGBALANCEX%>" maxlength="10" onblur="openBalance(this);"/>
	<br><span style="color:red;" id="err_OPENINGBALANCE"> </span></td>
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
