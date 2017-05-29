<%@page import="bean.USER_MASTER"%>
<%@ page language="java" import="java.util.List" pageEncoding="ISO-8859-1"%>
<%@page import="bean.USER_COMMONDATA"%>
<%@page import="org.KrrCommon;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>User Grid</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
		<%
			USER_COMMONDATA common_data = (USER_COMMONDATA) request.getAttribute("commonData");
			
			String ADD_USER_LOGINID = common_data.getUSERLOGINID();
			if(ADD_USER_LOGINID == null)ADD_USER_LOGINID="";
		%>
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

	//Function for go to user grid page to index page
      function exitUserGrid()
    {
	document.getElementById("gridForm").action = "TERA_STORES_PURCHASE?Option=3";
	document.getElementById("gridForm").submit();
   } 
   
   // reset records in usergrid page
    function userClearSearch()
  {
	document.getElementById("ADD_USER_LOGINID").value="";
	document.getElementById("ADD_USER_ROLE1").selectedIndex = 0;
	document.getElementById("ADD_STATUS").selectedIndex = 0;
  }

//Function for get the radio value which is selected
   function getRadioVal() 
 {
    var val =0;
    // get list of radio buttons with specified name
     var radios = document.getElementsByName('USERSRNO');
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

//function for  to go to the user.jsp page
   function User()
  {
     document.getElementById("gridForm").action ='USER_CONTROLLER?Option=2';
	 document.getElementById("gridForm").submit();
	 return true;  
  }

//function for to populate the data from the from the data base
    function modifyUser()
  {
	if(getRadioVal()=="0")
	{
		alert('Please select user')
	}
	else
	{
		var selected_value= getRadioVal();
	    document.getElementById("gridForm").action ='USER_CONTROLLER?Option=4&USER_SRNO='+selected_value;
		document.getElementById("gridForm").submit();
		return true; 
	} 
 }
	
//function for authorize the user
    function authorizeUser()
  {
	if(getRadioVal() =="0")
	{
		alert('Please select user')
	}
	else
	{
		var selected_value= getRadioVal(); 
	    document.getElementById("gridForm").action ='USER_CONTROLLER?Option=5&USER_SRNO='+selected_value;
		document.getElementById("gridForm").submit();
		return true;  
	} 
 } 
	  
//function for delete the user

<%--   function deleteUser()--%>
<%--  {--%>
<%--	if(getRadioVal() =="0")--%>
<%--	{--%>
<%--		alert('Please select user')--%>
<%--	}--%>
<%--	else--%>
<%--	{--%>
<%--		var selected_value= getRadioVal(); --%>
<%--	    document.getElementById("gridForm").action ='USER_CONTROLLER?Option=6&USER_SRNO='+selected_value;--%>
<%--		document.getElementById("gridForm").submit();--%>
<%--		return true;  --%>
<%--	} --%>
<%-- } --%>
	   
function get(name)
{
	if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
   	return decodeURIComponent(name[1]);
}
//function for user search fields

function  userSearch()
{ 
	var option = document.getElementById("pageNo").value;
	
	if(option == "")
	{
		document.getElementById("pageNo").value="1";
	}
	document.getElementById("gridForm").action='USER_CONTROLLER?Option=1';	
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
<tr><td align="center"><table border="0" cellspacing="0" cellpadding="0" >
<tr>
<tr><td valign="top" align="right">Welcome:<%=common_data.getUSERNAME()%>, Date:<%=common_data.getLOGINDATE()%></td>
</tr>
<tr><td align="center"><h2>USER GRID</h2></td></tr>
<tr><td><table class="gridTable gridTableBg" align="center" width="750"height="55"><tr class=th>
<th>Select</th>
<th>User name</th>
<th>User loginid</th>
<th>User password</th>
<th>User Role</th>
<th>Status</th>
</tr>
<%List<USER_MASTER> userBeanList = (List<USER_MASTER>) request.getAttribute("userBeanList");
int endRecord = common_data.getPageNo() * 10;
int startRecord = endRecord - 10;
int i = 1;
for (USER_MASTER user : userBeanList) {
  long USERSRNO =user.getUSERSRNO();
String USER_NAME = user.getUSERNAME();
    if(USER_NAME==null) USER_NAME="";
    
    String USER_LOGINID = user.getUSERLOGINID();
    if(USER_LOGINID==null) USER_LOGINID="";
    
    String USER_PASSWORD = user.getUSERPASSWORD();
    if(USER_PASSWORD==null) USER_PASSWORD="";
    
    String USER_ROLE1 = user.getUSERROLE();
    if(USER_ROLE1==null) USER_ROLE1="";
    
    String USER_STATUS = user.getSTATUS();
    if(USER_STATUS==null) USER_STATUS=""; 
    
if (i > startRecord && i <= endRecord) {
%>
<tr id="<%=i%>">
<%} else {%>
<tr style="display: none;" id="<%=i%>">
<%}
%>
 	 		<%if(USERSRNO > 0) 
 	 		{
 	 		%>
<td align="center">
<input type="radio" name="USERSRNO" id="USERSRNO" value="<%=user.getUSERSRNO()%>"/>
</td>

<td><%=USER_NAME%><br></td>
<td><%=USER_LOGINID%><br></td>
<td><%=USER_PASSWORD%><br></td>
<td><%=USER_ROLE1%><br></td>
<td><%=USER_STATUS%><br></td>
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
 	 		<td></td>
 	 		
 	 	<%
 	 	} 
 	 	%>
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
<div class="searchCriteriaDiv">
<span class="searchCriteriaLabel">Search Criteria</span><table style="width: 100%" align="center">
<tr>
<td>Login id</td>
<td>
<input type="text" id="ADD_USER_LOGINID" name="ADD_USER_LOGINID" style="width: 77%" value="<%=ADD_USER_LOGINID%>">
<span style="color: red;" id="err_LOGINID"></span>

<td>User Role</td>
<td><select id="ADD_USER_ROLE1" name="ADD_USER_ROLE1" style="width: 77%">
<option value="" selected>- - Select - -</option>
<%
if (!(common_data.getUSERROLE1() == null || common_data.getUSERROLE1() == "")) {
%>
<option value="<%=common_data.getUSERROLE1()%>" selected><%=common_data.getUSERROLE1()%></option>
<%}%>
<option value="USER">USER</option>
<option value="ADMIN">ADMIN</option>
<option value="SUPERVISOR">SUPERVISOR</option>
</select><br>
<span style="color: red;" id="err_ROLE"></span></td></tr>

<tr>
<td>Status</td>
<td><select id="ADD_STATUS" name="ADD_STATUS" style="width: 77%">
<option value="" selected>- - Select - -</option>
<%
if (!(common_data.getSTATUS() == null || common_data.getSTATUS() == "")) {
%>
<option value="<%=common_data.getSTATUS()%>" selected><%=common_data.getSTATUS()%></option>
<%}%>
<option value="ACTIVE">ACTIVE</option>
<option value="INACTIVE">INACTIVE</option>

</select><br>
<span style="color: red;" id="err_STATUS"></span></td></tr>
</table>

<div class="searchCriteriaButtons">
<button type="button" onclick="userSearch();" class="button">Search</button>
<button type="button" onclick="userClearSearch();" class="button">Clear Search</button>
</div></div>

<tr align="center"><td>
<div class="operationalButtons"><button type="button" onclick="User();" class="button">ADD</button>
<button type="button" onclick="modifyUser();" class="button">EDIT</button>
<%String USERROLE = (String) common_data.getUSERROLE();
if (USERROLE.equalsIgnoreCase("ADMIN")|| USERROLE.equalsIgnoreCase("SUPERVISOR"))
//if(true)
{%>
<button type="button" onclick="authorizeUser();" class="button">ACTIVE/INACTIVE</button>

<%}%>
<button type="button" onclick="exitUserGrid();" class="button">EXIT</button></div></td></tr>

<tr align="center"><td>
<img class="headerImg" src="img/footer.jpg"></td></tr>
</table>

</td></tr></table>	
</form>
</body>
</html>