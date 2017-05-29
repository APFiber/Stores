<%@page import="bean.VENDOR_SUPPLIER"%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.text.*"%>

<%@page import="org.KrrCommon"%>
<%@page import="bean.VENDOR_CommonData;"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
    <base href="<%=basePath%>">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<%
		KrrCommon krrCommon = new KrrCommon();
		VENDOR_CommonData commonData = (VENDOR_CommonData) request
				.getAttribute("commonData");
				long CVSSRNO=commonData.getVSSRNO();
				String CTYPE=commonData.getTYPE();
				String CNAME=commonData.getNAME();
				String CCITY=commonData.getCITY();
				String CDISTRICT=commonData.getDISTRICT();
				String CSTATE=commonData.getSTATE();
				long CPIN=commonData.getPIN();
				long CPHONENO=commonData.getPHONENO();
				String CSTATUS=commonData.getSTATUS();
	%>
	<script type="text/javascript">
	function get(name){
   		if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
      	return decodeURIComponent(name[1]);
	}

//search button validation function
	function search(){
	    
		var option = document.getElementById("pageNo").value;
	if(option == "")
	{
		document.getElementById("pageNo").value="1";
	}
	document.getElementById("gridForm").action='VENDOR_SUPPLIER_CONTROLLER?Option=1';
	
	document.getElementById("gridForm").submit();
	return true;
	}
	
//Enter page no field validation function 
	function pagination(e) 
	{
		var totalpages=<%=commonData.getTotalNoOfPages()%>;
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
		
//Add button function		
	function addVendor()
	{
	 document.getElementById("gridForm").action ='VENDOR_SUPPLIER_CONTROLLER?Option=2';
	 document.getElementById("gridForm").submit();
	 return true;   
	}
	
//Edit button function   
	function editVendor()
	{
	
	   if(getRadioVal() == null || getRadioVal() == "0")
	    {
		 alert('Please select type or code')
	    }
	     else
	     {
	    var selected_value = getRadioVal();
	    
	      document.forms[0].action='VENDOR_SUPPLIER_CONTROLLER?Option=4&VSCODE='+selected_value;
		  document.forms[0].submit();
		 }
		return true;   
	}

//Authorize button function   
	  function authorizeVendor()
	  {
	     if(getRadioVal() == "0")
	     {
			alert('Please select type or code')
	     }
	     else
	     {
	      var selected_value = getRadioVal();
	    
	      document.forms[0].action='VENDOR_SUPPLIER_CONTROLLER?Option=5&VSCODE='+selected_value;
		  document.forms[0].submit();
	     }
		return true;   
	 }
	  
//Delete button function 
	 function deleteVendor()
	 {
	    if(getRadioVal() == "0")
	    {
		alert('Please select type or code')
	    }
	     else
	     {
	      var selected_value = getRadioVal();
	 	  document.forms[0].action='VENDOR_SUPPLIER_CONTROLLER?Option=6&VSCODE='+selected_value;
		  document.forms[0].submit();
		  }
		return true;   
	  }
	
//ClearSearch button function   
	function clearSearch()
	{
	document.getElementById("TYPE").selectedIndex = 0;
	document.getElementById("CODE").value="";
	document.getElementById("NAME").value="";
	document.getElementById("CITY").selectedIndex = 0;
	document.getElementById("DISTRICT").selectedIndex = 0;
	document.getElementById("STATE").selectedIndex = 0;
	document.getElementById("PIN").value="";
	document.getElementById("PHONENO").value="";
	document.getElementById("STATUS").selectedIndex = 0;
	}
	
//Exit button function   
	function exitVendorGrid()
	{
		document.getElementById("gridForm").action = "TERA_STORES_PURCHASE?Option=3";
		document.getElementById("gridForm").submit();
	} 
	</script>
	</head>
<body>
<body>
  
  <form name="gridForm" id="gridForm" method="POST">
  <input type="hidden" name="USERNAME" value="<%=commonData.getUSERNAME()%>" >
  <input type="hidden" name="DATE" value="<%=commonData.getLOGINDATE()%>">
  <input type="hidden" name="USERROLE" value="<%=commonData.getUSERROLE()%>">
  <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID() %>">
  
   <table  align="center" class="mainTable">
	  	<tr align="center"><td><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
	  	<tr><td> <table  border="0" cellspacing="0" cellpadding="0" align="center" class="childTable">
		<tr><tr><td valign="top" align="right">Welcome <%=commonData.getUSERNAME()%> , Date: <%=commonData.getLOGINDATE()%></td></tr>
		    	<tr><td align="center"><h2>VENDOR DETAILS</h2></td></tr>
		    	<tr><td><table class="gridTable gridTableBg" align="center">
 	<tr  class=th>		
 		<th>Select</th>
 		<th>Type</th>
 		<th>Code</th>
 		<th>Name</th>
 		<th>City</th>
 		<th>District</th>
 		<th>State</th>
 		
 		<th>Phone no</th>
 		<th>Status</th>
 	</tr>
 	<%
 		List<VENDOR_SUPPLIER> vendorBeanList = (List<VENDOR_SUPPLIER>)request.getAttribute("vendorBeanList");
          	int endRecord=commonData.getPAGENO()*10;
 			int startRecord=endRecord-10;
 		int i = 1;
 		for (VENDOR_SUPPLIER vendor : vendorBeanList) {
 		long VSSRNO=vendor.getVSSRNO();
 		String TYPE=vendor.getTYPE();
 		String NAME=vendor.getNAME();
 	 	String CITY=vendor.getCITY();
 	 	String DISTRICT=vendor.getDISTRICT();
 	 	String STATE=vendor.getSTATE();
 	 	long PHONENO=vendor.getPHONENO();
 	 	String STATUS=vendor.getSTATUS();
 	 	
 	 	if(TYPE==null) TYPE="";
 		if(NAME==null) NAME="";
 	 	if(CITY==null) CITY="";
 	 	if(DISTRICT==null) DISTRICT="";
 	 	if(STATE==null) STATE="";
 	 	if(STATUS==null) STATUS="";
 	 	
 			if(i>startRecord && i<=endRecord){
 			%>
 	 		<tr  id="<%=i %>">
 			<%}else{ %>
 	 		<tr style="display : none;" id="<%=i %>">
 	 		<%} 
 	 		if(commonData.getSelectedSerialNo()!=0 && commonData.getSelectedSerialNo()==VSSRNO){
 	 		%>
 	 		<td align="center"><input type="radio" name="radio" id="radio" value="<%=VSSRNO%>" checked="checked"/></td>
 	 		<%
 	 		}else{%>
 	 		<td align="center"><input type="radio" name="radio" id="radio" value="<%=VSSRNO%>"/></td>
 	 		<%} %>
 	 		
 	 		<td><%=TYPE%></td>
 	 		<%if(VSSRNO == 0){ %>
 	 		<td></td>
 	 		<%}else{ %>
 	 		<td><%=vendor.getVSSRNO()%></td>
 	 		<%} %>
 	 		<td><%=NAME%></td>
 	 		<td><%=CITY%></td>
 	 		<td><%=DISTRICT%></td>
 	 		<td><%=STATE%></td> 
 	 		<td><%=PHONENO%></td> 
 	 		<td><%=STATUS%></td> 		
 	 		</tr>
 			<%
 				i++;
 				}//for
 			%>
 </table>
  <div class="pagination">
 
  <%
   	String message = (String) request.getAttribute("message");
   	if (krrCommon.isValuenull(message)) {
   		message = "";
   	}
   %>
 <p align="center"><font color="red">Status : <%=message%></font></p>
 <p align="center">Total Number of Records : <%=commonData.getTotalNoOfRecords()%> | Total Number of Pages :<span id="totalNoOfPages"><%=commonData.getTotalNoOfPages()%> </span> | Enter Page No:
 <select  name="pageNo" id="pageNo"  onchange="pagination()">
 <%for(int j=1;j<=commonData.getTotalNoOfPages();j++ ){
 if(j==commonData.getPAGENO()){
 %>
 <option value="<%=j %>" selected><%=j %></option><%
 }else{%>
 <option value="<%=j %>" ><%=j %></option><%
 }} %>
 </select>
 </p>
</div>

<div  class="searchCriteriaDiv">
 	<span class="searchCriteriaLabel">Search Criteria</span>
	<table style="width:100%" align="center">
		
		<tr>
		<td>Type</td>
		<td>
		<select   id="TYPE" name="TYPE"  style="width:77%">
		<option value="" selected>- - Select - - </option>
	    <%
	    	if (!(CTYPE == null || CTYPE == "")) {
	    %>
							<option value="<%=CTYPE%>" selected><%=CTYPE%></option>
						<%
							}
						%>
			     <%
			     	List<VENDOR_SUPPLIER> typelist = (List<VENDOR_SUPPLIER>) request.getAttribute("type");
			     	for (VENDOR_SUPPLIER type : typelist) {
			     %><option value="<%=type.getTYPE()%>"> <%=type.getTYPE()%></option> <%
 	}
 %>
		</select>
		<span style="color:red;" id="err_item_code"></span></td>
		
		<td>Code</td>
		<td><input type="text" id="CODE" name="CODE" value="<%=CVSSRNO%>" style="width:77%"/>
		<span style="color:red;" id="err_code_check"></span></td>
		</tr>
		
		<tr>
		<td>Name</td><td>
		<input type="text" id="NAME" name="NAME" value="<%=CNAME%>" style="width:77%"/>
		<span style="color:red;" id="err_name_check"></span></td>
		
		<td>City</td>
		<td>
		<select   id="CITY" name="CITY"  style="width:77%">
		<option value="" selected>- - Select - - </option>
	    <%
	    	if (!(CCITY == null || CCITY == "")) {
	    %>
							<option value="<%=CCITY%>" selected><%=CCITY%></option>
						<%
							}
						%>
			     <%
			     	List<VENDOR_SUPPLIER> citylist = (List<VENDOR_SUPPLIER>) request
			     			.getAttribute("city");
			     	for (VENDOR_SUPPLIER city : citylist) {
			     %><option value="<%=city.getCITY()%>"> <%=city.getCITY()%></option> <%
 	}
 %>
		</select>
		<span style="color:red;" id="err_city_check"></span></td>
		</tr>
		
		<tr>
		<td>District</td><td>
		<select id="DISTRICT" name="DISTRICT" value="<%=commonData.getDISTRICT()%>" style="width:77%"/>
		<option value="" selected>- - Select - - </option>
	    <%
	    	if (!(CDISTRICT == null || CDISTRICT == "")) {
	    %>
							<option value="<%=CDISTRICT%>" selected><%=CDISTRICT%></option>
						<%
							}
						%>
			     <%
			     	List<VENDOR_SUPPLIER> districtlist = (List<VENDOR_SUPPLIER>) request
			     			.getAttribute("district");
			     	for (VENDOR_SUPPLIER district : districtlist) {
			     %><option value="<%=district.getDISTRICT()%>"> <%=district.getDISTRICT()%></option> <%
 	}
 %>
 </select>
		<span style="color:red;" id="err_district_check"></span></td>
		
		<td>State</td><td>
		<select id="STATE" name="STATE" value="<%=commonData.getSTATE()%>" style="width:77%"/>
		<option value="" selected>- - Select - - </option>
	    <%
	    	if (!(CSTATE == null || CSTATE == "")) {
	    %>
							<option value="<%=CSTATE%>" selected><%=CSTATE%></option>
						<%
							}
						%>
			     <%
			     	List<VENDOR_SUPPLIER> statelist = (List<VENDOR_SUPPLIER>) request
			     			.getAttribute("state");
			     	for (VENDOR_SUPPLIER state : statelist) {
			     %><option value="<%=state.getSTATE()%>"> <%=state.getSTATE()%></option> <%
 	}
 %>
 </select>
		<span style="color:red;" id="err_state_check"></span></td>
		
		</tr>
		
		<tr>
		<td>Pin</td><td>
		<input type="text" id="PIN" name="PIN" value="<%=CPIN%>" style="width:77%"/>
		<span style="color:red;" id="err_pin_check"></span></td>
		
		<td>Phone no</td><td>
		<input type="text" id="PHONENO" name="PHONENO" value="<%=CPHONENO%>" style="width:77%"/>
		<span style="color:red;" id="err_phoneno_check"></span></td>
		</tr>
		<tr>
		<td>Status</td>
		<td>
		<select   id="STATUS" name="STATUS"  style="width:77%">
	    <option value="" selected>- - Select - - </option>
	    <%
	    	if (!(CSTATUS == null || CSTATUS == "")) {
	    %>
		<option value="<%=CSTATUS%>" selected><%=CSTATUS%></option>
		<%
			}
		%>
	  	<option value="ACTIVE" >ACTIVE</option>
		<option value="INACTIVE" >INACTIVE</option>
		<option value="DELETED" >DELETED</option>
		</select>
		
		<span style="color:red;" id="err_status_check"></span></td>
		</tr>
	</table>
	<div class ="searchCriteriaButtons" align="center">
	<button type="button" onclick="search()" class="button">Search</button>
	<button type="button" onclick="clearSearch()"  class="button">Clear Search</button>
</div>
	
<tr align="center"><td><div class="operationalButtons">
	<button type="button" onclick="addVendor()" class="button"> ADD</button>
	<button type="button" onclick="editVendor()" class="button"> EDIT</button>
	<%
		String USERROLE = (String) commonData.getUSERROLE();
		if (USERROLE.equalsIgnoreCase("ADMIN")
				|| USERROLE.equalsIgnoreCase("SUPERVISOR"))
		//if(true)
		{
	%>
	<button type="button" onclick="authorizeVendor()" class="button"> AUTHORIZE</button>
	<button type="button" onclick="deleteVendor()" class="button"> DELETE</button>
	<%
		}
	%>
	<button type="button" onclick="exitVendorGrid()" class="button"> EXIT</button>
</div></td></tr>
	<tr align="center"><td><img class="headerImg" src="img/footer.jpg"></td></tr>
	</table>
	<tr><td>&nbsp;
   
</table>
 </form>
</body>
</html>