<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="bean.RTN_COMMONDATA"%>
<%@page import="bean.RETURN_NOTE"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Add MRN</title>
   <link rel="stylesheet" type="text/css" href="css/style.css">
   <link rel="stylesheet" type="text/css" href="calendar.css"></link>
	<script type="text/javascript" src="calendar.js"></script>
	<script type="text/javascript">
	
	//To save the MRN details
function rtnSave()
{ 
     var man =mandatoryRTNcheck();
     if(man)
     {
      document.forms[0].action='RTN_CONTROLLER?Option=3';
	  document.forms[0].submit();
     }
}
// to clear the fields in addMRN.jsp page
	function rtnResetErrors() 
	{
    document.getElementById("err_GPNO").innerHTML="";
    document.getElementById("err_ITEMCODE").innerHTML="";
    document.getElementById("err_RETURNDATE").innerHTML="";
	document.getElementById("err_QTYRETURNED").innerHTML="";
	document.getElementById("err_PURPOSEOFRETURN").innerHTML="";
	}
function mandatoryRTNcheck()
	{
	var status;
    status=true;
    rtnResetErrors();
	     if (mrnDtls.RTNOTE_DATE.value.length == 0) {
				document.getElementById("err_RETURNDATE").innerHTML = "Enter the Return Date ";
				status = false;
			} else {
				if (mrnDtls.RTNOTE_DATE.value.length != 10) {
					document.getElementById("err_RETURNDATE").innerHTML = "Date Should be DD-MM-YYYY";
					status = false;
				} else {
					var ll = position(mrnDtls.RTNOTE_DATE.value);
					var ll1 = position1(mrnDtls.RTNOTE_DATE.value);
					if (!(ll == "-" && ll1 == "-")) {
					 	document.getElementById("err_RETURNDATE").innerHTML = "Date Should be DD-MM-YYYY";
					} else {
						var bval = Comparedates(mrnDtls.RTNOTE_DATE.value,
								Getcurrentdate());
						if (bval == false) {
							document.getElementById("err_RETURNDATE").innerHTML = "Date Should not be Future date";
							status = false;
						} else {
							datecom = 1;
						}
					}
				}
			}
             if (mrnDtls.RTNOTE_GPNO.value.length==0) {
				document.getElementById("err_GPNO").innerHTML = "Enter the Gpno ";
				status = false;
				}
				
				if (mrnDtls.RTNOTE_ITEMCODE.value.length==0) {
				document.getElementById("err_ITEMCODE").innerHTML = "Enter the Itemcode ";
				status = false;
				}
       if(mrnDtls.RTNOTE_QTYRETURNED.value.length==0)
       {
       document.getElementById("err_QTYRETURNED").innerHTML="Enter the Qty Return value";
       status=false;
       }
       if(mrnDtls.RTNOTE_PURPOSEOFRETURN.value.length==0)
       {
       document.getElementById("err_PURPOSEOFRETURN").innerHTML="Enter the purpose";
       status=false;
       }
	     return status;
	 }
	 
	  // function checking for Retrun no field
	 
 // feild checking for Gp no in addMRN.jsp page
 function rtnGpNoCheck(GPNO)
{
	document.getElementById("err_GPNO").innerHTML = "";
	if (GPNO.value.length > 0) 
	{
		var regex1=/^[0-9/s]+$/;    //this is the pattern of regular expersion
		if (regex1.test(GPNO.value) == false)
		{
			document.getElementById("err_GPNO").innerHTML = "numeric values only accepted ";
			document.mrnDtls.RTNOTE_GPNO.value="";
		}
	}
	else  document.getElementById("err_GPNO").innerHTML = "Enter the Gp no";
}
  
 function rtnItemCodeCheck(ITEMCODE)
{
	document.getElementById("err_ITEMCODE").innerHTML = "";
	if (ITEMCODE.value.length > 0) 
	{
		var regex1=/^[0-9/s]+$/;    //this is the pattern of regular expersion
		if (regex1.test(ITEMCODE.value) == false)
		{
			document.getElementById("err_ITEMCODE").innerHTML = "numeric values only accepted ";
		}
	}
	else  document.getElementById("err_ITEMCODE").innerHTML = "Enter the Item code";
}
 
 // Return date feild check
 function rtnReturnDateCheck(RETURNDATE)

		{
     		document.getElementById("err_RETURNDATE").innerHTML="";
	 		if(mrnDtls.RETURNDATE.value.length=="")
        	{   
	        document.getElementById("err_RETURNDATE").innerHTML="Enter the Return date";
	        document.mrnDtls.RTNOTE_DATE.value="";   
        	}
		}
// return qty check
 function rtnQtyReturnedCheck(QTYRETURNED)
{
	document.getElementById("err_QTYRETURNED").innerHTML = "";
	if (QTYRETURNED.value.length > 0) 
	{
		var regex1=/^[0-9/s]+$/;    //this is the pattern of regular expersion
		if (regex1.test(QTYRETURNED.value) == false)
		{
			document.getElementById("err_QTYRETURNED").innerHTML = "numeric values only accepted ";
			document.mrnDtls.RTNOTE_QTYRETURNED.value="";
		}
	}
	else  document.getElementById("err_QTYRETURNED").innerHTML = "Enter the quantity";
}


            //return note purpose check
 function rtnPurposeOfReturnCheck(PURPOSEOFRETURN)
{
	document.getElementById("err_PURPOSEOFRETURN").innerHTML = "";
	if (PURPOSEOFRETURN.value.length > 0) 
	{
		var regex1=/^[a-z A-Z/s ]+$/;      //this is the pattern of regular expersion
		if (regex1.test(PURPOSEOFRETURN.value) == false)
		{
			document.getElementById("err_PURPOSEOFRETURN").innerHTML = "alphabet can be accepted ";
			document.mrnDtls.PURPOSEOFRETURN.value="";
		}
	}
	else  document.getElementById("err_PURPOSEOFRETURN").innerHTML = "Enter the purpose of return";
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

// to go addrtn.jsp page to rtn.jsp
	function exitAddRTN()
{
   	document.forms[0].action='RTN_CONTROLLER?Option=1';
 	document.forms[0].submit();
} 
//Function for set the user to read only when it is in type of edit
 function editFormFucntion() 
{
	var option = getParameterByName("Option");
	if (option == 4)
	{
		document.getElementById('RTNOTE_NO').readOnly = true;
		document.getElementById('reset').type = "hidden";
	}
}

 function getGpnoDetails(RTNOTE_GPNO)
{
var RTNOTE_GPNO=document.getElementById("RTNOTE_GPNO").value;

document.forms[0].action='RTN_CONTROLLER?Option=7&GPNO='+RTNOTE_GPNO;
document.getElementById("mrnDtls").submit();
}
function getItemDetails(RTNOTE_ITEMCODE)
{
var RTNOTE_ITEMCODE=document.getElementById("RTNOTE_ITEMCODE").value;

document.forms[0].action='RTN_CONTROLLER?Option=7&ITEM_CODE='+RTNOTE_ITEMCODE;
document.getElementById("mrnDtls").submit();
}
		
	</script>
  </head>
  <body onload="editFormFucntion()">
  <%
   RTN_COMMONDATA common_data = (RTN_COMMONDATA)request.getAttribute("commonData");
  %>
    <form name="mrnDtls" id="mrnDtls" method="POST">
     <input type="hidden" name="USERNAME"  value="<%=common_data.getUSERNAME() %>" >
 	 <input type="hidden" name="DATE"  value="<%=common_data.getLOGINDATE() %>"> 
 	 <input type="hidden" name="USERROLE"  value="<%=common_data.getUSERROLE() %>">
 	 <input type="hidden" name="LOGINID" value="<%=common_data.getLOGINID() %>">
 	  <input type="hidden" id="ADDITEMCODE" name="ADDITEMCODE" value="<%=common_data.getITEMCODE()%>">
 	   <input type="hidden" id="ADDPURPOSEOFRETURN" name="ADDPURPOSEOFRETURN" value="<%=common_data.getPURPOSEOFRETURN()%>">
 	 <input type="hidden" id="ADDGPNO" name="ADDGPNO" value="<%=common_data.getGPNO()%>">
 	 <input type="hidden" id="ADDRETURNDATE" name="ADDRETURNDATE" value="<%=common_data.getRETURNDATE()%>">
 	 <input type="hidden" id="pageNo" name="pageNo" value="<%=common_data.getPageNo()%>">
    <%RETURN_NOTE rtn =(RETURN_NOTE)request.getAttribute("rtn");
    
    long RTNOTE_GPNO = rtn.getRTNOTE_GPNO();
    String RTNOTE_GPNOX; RTNOTE_GPNOX="";
    if(RTNOTE_GPNO>0)RTNOTE_GPNOX=""+RTNOTE_GPNO;
    
    long RTNOTE_ITEMCODE = rtn.getRTNOTE_ITEMCODE();
    String RTNOTE_ITEMCODEX; RTNOTE_ITEMCODEX="";
    if(RTNOTE_ITEMCODE>0)RTNOTE_ITEMCODEX=""+RTNOTE_ITEMCODE;
    
    String RTNOTE_GPDATE =rtn.getRTNOTE_GPDATE();
    if(RTNOTE_GPDATE == null) RTNOTE_GPDATE="";
    
    long RTNOTE_VENDORCODE =rtn.getRTNOTE_VENDORCODE();
    String RTNOTE_VENDORCODEX; RTNOTE_VENDORCODEX="";
    if(RTNOTE_VENDORCODE>0)RTNOTE_VENDORCODEX=""+RTNOTE_VENDORCODE;
    
    String RTNOTE_VENDORNAME =rtn.getRTNOTE_VENDORNAME();
    if(RTNOTE_VENDORNAME == null) RTNOTE_VENDORNAME="";
    
    String RTNOTE_ITEMDESCRIPTION =rtn.getRTNOTE_ITEMDESCRIPTION();
    if(RTNOTE_ITEMDESCRIPTION == null) RTNOTE_ITEMDESCRIPTION="";
    
    String RTNOTE_ITEMMAKE =rtn.getRTNOTE_ITEMMAKE();
    if(RTNOTE_ITEMMAKE ==null) RTNOTE_ITEMMAKE="";
    
    long RTNOTE_QTYRETURNED =rtn.getRTNOTE_QTYRETURNED();
    String RTNOTE_QTYRETURNEDX; RTNOTE_QTYRETURNEDX="";
    if(RTNOTE_QTYRETURNED>0)RTNOTE_QTYRETURNEDX=""+RTNOTE_QTYRETURNED;
    
    String  RTNOTE_DATE =rtn.getRTNOTE_DATE();
    if(RTNOTE_DATE == null) RTNOTE_DATE="";
    
    String RTNOTE_PURPOSEOFRETURN =rtn.getRTNOTE_PURPOSEOFRETURN();
    if(RTNOTE_PURPOSEOFRETURN == null) RTNOTE_PURPOSEOFRETURN="";
    %>
    
    <input type="hidden" id="RTNOTE_NO" name="RTNOTE_NO" value="<%=rtn.getRTNOTE_NO()%>">
        <table  class="mainTable" align="center">
	  	<tr align="center"><td><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
	  	<tr><td> <table  width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=common_data.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=common_data.getLOGINDATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
		    		<tr><td> <table align="center" cellpadding="0" cellspacing="0" class="pochildTable">
		    		<tr><td colspan="6"><h2 align="center" >ADD RETURN NOTE DETAILS</h2></td></tr>
		    		
				
	<tr style="background-color:FFE4B5">
	<td colspan="1">Gp No:</td>
	<td colspan="2"><input type="text" id="RTNOTE_GPNO" name="RTNOTE_GPNO" value="<%=RTNOTE_GPNOX%>" onchange="getGpnoDetails(this.value);" onblur="rtnGpNoCheck(this)"/>
		    	<font color="#ff0000">*</font>
		    	<br><span style="color:red;" id="err_GPNO"></span></td>
		    	
	
	<td colspan="1">Item Code:</td>
    <td colspan="2"><input type="text" id="RTNOTE_ITEMCODE" name="RTNOTE_ITEMCODE" value="<%=RTNOTE_ITEMCODEX%>" onchange="getItemDetails(this.value);" onblur="rtnItemCodeCheck(this)" />
		    	<font color="#ff0000">*</font>
		    	<br><span style="color:red;" id="err_ITEMCODE"></span>
 </td></tr>
	
	</table><tr><td>

	<table class="pochildTable" align="center"  style="background-color: #D3D3D3" >		
	<tr><td colspan="8" align="center">
	<tr>
	<td colspan="1">Gp date</td>
	<td colspan="2"><input type="text" name="RTNOTE_GPDATE" id="RTNOTE_GPDATE"  value="<%=RTNOTE_GPDATE%>" readonly="readonly" maxlength="10" />
	</td>
	
	<td colspan="1">Vendor Code:</td>
		    	<td colspan="2"><input type="text" name="RTNOTE_VENDORCODE" id="RTNOTE_VENDORCODE" readonly="readonly" value="<%=RTNOTE_VENDORCODEX%>" />
		    	<font color="#ff0000"></font>
		    	</td>
		    	</tr>
		    	
		    	<tr>
		    	<td colspan="1">Vendor Name:</td>
		    	<td colspan="2"><input type="text" name="RTNOTE_VENDORNAME" id="RTNOTE_VENDORNAME" readonly="readonly" value="<%=RTNOTE_VENDORNAME%>"/>
		    	<font color="#ff0000"></font>
		    	</td>
	
	<td colspan="1">Item Desc:</td>
		    	<td colspan="2"><input type="text" id="RTNOTE_ITEMDESCRIPTION" name="RTNOTE_ITEMDESCRIPTION" value="<%=RTNOTE_ITEMDESCRIPTION%>" readonly="readonly" />
		    	<font color="#ff0000"></font>
		    	</td>
	<tr>
	<td colspan="1">Item Make:</td>
		    	<td colspan="2"><input type="text" id="RTNOTE_ITEMMAKE" name="RTNOTE_ITEMMAKE" value="<%=RTNOTE_ITEMMAKE%>" readonly="readonly" />
		    	<font color="#ff0000"></font>
		    	</td>
		    	
	<td colspan="1">Qty Return</td>
    <td colspan="2"><input type="text" id="RTNOTE_QTYRETURNED" name="RTNOTE_QTYRETURNED" value="<%=RTNOTE_QTYRETURNEDX%>" onblur="rtnQtyReturnedCheck(this)"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_QTYRETURNED"></span></td>
	</tr>
	</table>
	
	<table class="pochildTable" align="center"  style="background-color:#c7fdf4">		
	<tr><td colspan="8" align="center">
	<tr>
       <td colspan="1">Return Date:</td>
	<td colspan="1"><input type="text" name="RTNOTE_DATE" id="RTNOTE_DATE"  value="<%=RTNOTE_DATE%>" maxlength="10" onblur="rtnReturnDateCheck(this)"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'RTNOTE_DATE');" value="RTNOTE_DATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_RETURNDATE"></span>
	</td></tr>
	
	<tr>
	<td colspan="1" >Purpose Of Return:</td>
	<td  colspan="1"><input type="text" name="RTNOTE_PURPOSEOFRETURN" id="RTNOTE_PURPOSEOFRETURN" value="<%=RTNOTE_PURPOSEOFRETURN%>"    onblur="rtnPurposeOfReturnCheck(this)"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_PURPOSEOFRETURN"> </span></td>
	</tr>
	
	<tr>	    	
	<td align="center" colspan="6"> 
	<p><font color="red"> <%=request.getAttribute("message") %></font></p>
	</td>
	</tr>
	
 <tr>
 <td align=center colspan="6">
  
	<button type="button" onclick="return rtnSave();" class="button"> SAVE</button>
	<button type="reset" class="button" onclick="rtnResetErrors();"> RESET</button>
	<button type="button" onclick="exitAddRTN();" class="button"> EXIT</button>
	</td></tr></table>
</td></tr></table>

	<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg">
	</td></tr>
	</table>
</form>
 </body>
 </html>