<%@page language="java" import="java.util.List" pageEncoding="ISO-8859-1"%>
<%@page import="bean.RTN_COMMONDATA"%>
<%@page import="org.KrrCommon"%>
<%@page import="bean.RETURN_NOTE"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>MaterialReturnGrid</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"></link>
		<link rel="stylesheet" type="text/css" href="calendar.css"></link>
		<%
		RTN_COMMONDATA common_data = (RTN_COMMONDATA) request.getAttribute("commonData");	
		
		long ADDITEMCODE=common_data.getITEMCODE();  				   				
 	    String ADDITEMCODEX; ADDITEMCODEX="";
 	    if(ADDITEMCODE>0)ADDITEMCODEX=""+ADDITEMCODE;
 	    
 	 	long ADDGPNO=common_data.getGPNO();  				   				
 	 	String ADDGPNOX; ADDGPNOX="";
 	 	if(ADDGPNO>0)ADDGPNOX=""+ADDGPNO;
 	 	
 	 	String ADDRETURNDATE=common_data.getRETURNDATE();  				   				
 	 		if(ADDRETURNDATE==null) ADDRETURNDATE="";
 	 		
 	 	String ADDPURPOSEOFRETURN=common_data.getPURPOSEOFRETURN();  				   				
 	 		if(ADDPURPOSEOFRETURN==null) ADDPURPOSEOFRETURN="";
 	 	
		%>
		<script type="text/javascript" src="calendar.js"></script>
		<script type="text/javascript">
	function pagination(e) 
	{
		var totalpages=<%=common_data.getTotalNoOfPages()%>;
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

	//Function for go to MRN grid page to index page
  function exitRTNGrid()
 {
	document.getElementById("gridForm").action = "TERA_STORES_PURCHASE?Option=3";
	document.getElementById("gridForm").submit();

} 
// reset records in MRN grid page
 function materialClearSearch()
{
	document.getElementById("ADDITEMCODE").value="";
	document.getElementById("ADDGPNO").value="";
	document.getElementById("ADDRETURNDATE").value="";
	document.getElementById("ADDPURPOSEOFRETURN").value="";
}

//Function for get the radio value which is selected
 function getRadioVal() 
{
    var val =0;
    // get list of radio buttons with specified name
     var radios = document.getElementsByName('RTNOTENO');
    // loop through list of radio buttons
    for (var i=0, len=radios.length; i<len; i++) 
    {
        if ( radios[i].checked ) 
        { // radio checked?

            val = radios[i].value; 
            // if so, hold its value in val
            break; // and break out of for loop
        }
    }
    return val; // return value of checked radio or "0" if none checked
}

//function for  to go to the addMRN.jsp page
 function AddRTN()
{
     document.getElementById("gridForm").action ='RTN_CONTROLLER?Option=2';
	 document.getElementById("gridForm").submit();
	 return true;  
}
// Remove the return grid page
 function RemoveRTN()
{
   if(getRadioVal()==null ||getRadioVal()=="0")
 {
      alert('please select Returnno')
  }
    else
  {
        var selected_value=getRadioVal();
        document.getElementById('gridForm').action='RTN_CONTROLLER?Option=4&RTNOTE_NO='+selected_value;
        document.getElementById('gridForm').submit();
     }
 }
// function  approved the grid page
 function ApprovedRTN()
{
	if(getRadioVal()==null ||getRadioVal()=="0")
	{
		alert('Please select Returnno')
	}
	else
	{
		var selected_value= getRadioVal(); 
	    document.getElementById("gridForm").action ='RTN_CONTROLLER?Option=5&RTNOTE_NO='+selected_value;
		document.getElementById("gridForm").submit();
		return true;  
	} 
} 
function EditRTN()
{
if(getRadioVal()==null ||getRadioVal()=="0")
{
alert('please select Returnno')
}
else
{
var selected_value=getRadioVal();
document.getElementById("gridForm").action ='RTN_CONTROLLER?Option=6&RTNOTE_NO='+selected_value;
document.getElementById("gridForm").submit();
return true;
    }
 }


//function for to populate the data from the from the data base

 function get(name)
{
	if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
   	return decodeURIComponent(name[1]);
}
//function for mrn search fields

 function  searchReturn()
{ 
	
		var option = document.getElementById("pageNo").value;
		if(option == "")
		{
			document.getElementById("pageNo").value="1";
		}
		document.getElementById("gridForm").action='RTN_CONTROLLER?Option=1';	
		document.getElementById("gridForm").submit();
		return true;
}

   </script>
	</head>
	
	<body>

	
		<%
			KrrCommon krrCommon = new KrrCommon();
		%>
 <form name="gridForm" id="gridForm" method="POST">
 <input type="hidden" name="USERNAME" id="USERNAME" value="<%=common_data.getUSERNAME()%>">
 <input type="hidden" name="DATE" id="DATE" value="<%=common_data.getLOGINDATE()%>">
 <input type="hidden" name="USERROLE" id="USERROLE" value="<%=common_data.getUSERROLE()%>">
 <input type="hidden" name="LOGINID" id="LOGINID" value="<%=common_data.getLOGINID()%>">

 <table align="center" class="childTable">
 <tr align="center"><td><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
 <tr><td align="center"><table  width="100%"  border="0" cellspacing="0" cellpadding="0" >
 <tr>
 <tr><td valign="top" align="right">Welcome:<%=common_data.getUSERNAME()%>, Date:<%=common_data.getLOGINDATE()%></td>
 </tr>
 <tr><td align="center"><h2>RETURN NOTE DETAILS</h2></td></tr>
 <tr><td><table class="gridTable gridTableBg" align="center" width="750" height="55"><tr class=th>
 <th>Select</th>
 <th>Return No</th>
 <th>Return Date</th>
 <th>Gp No</th>
 <th>Gp Date</th>    
 <th>Vendor Code</th>
 <th>Vendor name</th>
 <th>QtyReturned</th>
 <th>ItemCode</th>
 <th>ItemDesc</th>
 <th>PurposeOfReturn</th>
 <th>Status</th>
 </tr>
 <%List<RETURN_NOTE > rtnBeanList = (List<RETURN_NOTE>) request.getAttribute("rtnBeanList");
 int endRecord = common_data.getPageNo() * 10;
 int startRecord = endRecord - 10;
 int i = 1;
 for (RETURN_NOTE rtn : rtnBeanList) {
 long RTNOTENO = rtn.getRTNOTE_NO();
    
    String RETURN_DATE = rtn.getRTNOTE_DATE();
    if(RETURN_DATE == null)RETURN_DATE=""; 
    
    long RETURN_GPNO = rtn.getRTNOTE_GPNO();
    
    String RETURN_GPDATE = rtn.getRTNOTE_GPDATE();
    if(RETURN_GPDATE == null)RETURN_GPDATE="";
    
    long RETURN_VENDORCODE = rtn.getRTNOTE_VENDORCODE();
    
    String RETURN_VENDORNAME = rtn.getRTNOTE_VENDORNAME();
    if(RETURN_VENDORNAME ==null)RETURN_VENDORNAME="";
    
    long RETURN_ITEMCODE = rtn.getRTNOTE_ITEMCODE();

    String RETURN_ITEMDESC = rtn.getRTNOTE_ITEMDESCRIPTION();
    if(RETURN_ITEMDESC == null)RETURN_ITEMDESC="";
    
    long RTNOTE_QTYRETURNED=rtn.getRTNOTE_QTYRETURNED();
    
    String RETURN_PURPOSEOFRETURN = rtn.getRTNOTE_PURPOSEOFRETURN();
    if(RETURN_PURPOSEOFRETURN == null)RETURN_PURPOSEOFRETURN="";
    
    String RETURN_STATUS = rtn.getRTNOTE_STATUS();
    if(RETURN_STATUS == null)RETURN_STATUS="";
     
 if (i > startRecord && i <= endRecord) {
 %> 
 <tr id="<%=i%>">
 
 <%} else {%>
 <tr style="display: none;" id="<%=i%>">
 <%}%>
 <%if(RTNOTENO > 0)
 {
  %>
<td align="center"><input type="radio" name="RTNOTENO" id="RTNOTENO" value="<%=rtn.getRTNOTE_NO()%>" />
 <td><%=RTNOTENO%></td>
 <td><%=RETURN_DATE%></td>
 <td><%=RETURN_GPNO%></td>
 <td><%=RETURN_GPDATE%></td>
 <td><%=RETURN_VENDORCODE%></td>
 <td><%=RETURN_VENDORNAME%></td>
 <td><%=RTNOTE_QTYRETURNED%></td>
 <td><%=RETURN_ITEMCODE%></td>
 <td><%=RETURN_ITEMDESC%></td>
 <td><%=RETURN_PURPOSEOFRETURN%></td>
 <td><%=RETURN_STATUS%></td>
 </tr>
 <%}
 else
 {
  %>
  <td> &nbsp </td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <td></td>
  <%
  } %>
  
 <%i++;
 }//for
 %>
 </table>
 <div class="pagination">
 <%String message = (String) request.getAttribute("message");
 if (krrCommon.isValuenull(message)) {
 message = "";
 }
 %>
 <p align="center">
 <font color="red">Status : <%=message%></font>
 </p>
 Total Number of Records :<%=common_data.getTotalNoOfRecords()%>|| Total Number of Pages :<span id="totalNoOfPages"><%=common_data.getTotalNoOfPages()%> </span> | Select Page No: 
 <select name="pageNo" id="pageNo" onchange="pagination()"> 
 <% 
 for (int j = 1; j <= common_data.getTotalNoOfPages(); j++) { 
 if (j == common_data.getPageNo()) { 
 %> 
 <option value="<%=j%>" selected><%=j%></option> 
 <% 
 } else { 
 %> 
 <option value="<%=j%>"><%=j%></option> 
 <%}}%>
 </select><p align="center"> 
 </p>
 </div>

  <div  class="searchCriteriaDiv">
 	<span class="searchCriteriaLabel">Search Criteria</span>
	<table style="width:100%" align="center">
		
		<tr>
		<td>Item Code:</td>
		<td>
		<input type="text" id="ADDITEMCODE" name="ADDITEMCODE" value="<%=ADDITEMCODEX%>" style="width: 55%" >
    <br><span style="color: red;" id="err_ITEMCODE"></span></td>
    
       <td>Gp No:</td>
		<td><input type="text" id="ADDGPNO" name="ADDGPNO" value="<%=ADDGPNOX%>" style="width:55%"/>
		<br><span style="color:red;" id="err_GPNO"></span></td>
    <tr>
		<td>Return Date:</td>
		<td><input type="text" name="ADDRETURNDATE" id="ADDRETURNDATE" style="width:55%"   maxlength="10" value="<%=ADDRETURNDATE%>"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'ADDRETURNDATE');" value="ADDRETURNDATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_RETURNDATE"></span></td>
	
		<td>Purpose of Return:</td>
		<td><input type="text" id="ADDPURPOSEOFRETURN" name="ADDPURPOSEOFRETURN" style="width:55%" value="<%=ADDPURPOSEOFRETURN%>" />
		   <br><span style="color:red;" id="err_PURPOSEOFRETURN"></span></td>
	    </tr>
	</table>
	<div class ="searchCriteriaButtons" align="center">
	<button type="button" onclick="searchReturn()" class="button">Search</button>
	<button type="button" onclick="materialClearSearch();" class="button">Clear Search</button>
</div></div>
	
  <tr align="center"><td><div class="operationalButtons">
	<button type="button" onclick="AddRTN();" class="button"> ADD</button>
	<button type="button" onclick="EditRTN();" class="button">EDIT</button>
	<button type="button" onclick="RemoveRTN();" class="button"> REMOVE</button>
	<button type="button" onclick="ApprovedRTN();" class="button"> APPROVED</button>
	<button type="button" onclick="exitRTNGrid();" class="button"> EXIT</button>
</div></td></tr>
		<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg"></td></tr>
	</table>
  </td></tr>
 </table>
 </form>
 </body>
 </html>