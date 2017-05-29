<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.text.*"%>

<%@page import="org.KrrCommon"%>
<%@page import="bean.VENDOR_SUPPLIER_PROJECT"%>
<%@page import="bean.VENDOR_SUPPLIER"%>
<%@page import="bean.VSP_Commondata"%>
<%@page import="bean.VENDOR_SUPPLIER"%>
<%@page import="bean.PROJECT_MASTER"%>
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

			VSP_Commondata vsp_commondata = (VSP_Commondata) request
					.getAttribute("vsp_commondata");
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
	document.getElementById("gridForm").action='VENDOR_PROJECT_CONTROLLER?Option=1';
	
	document.getElementById("gridForm").submit();
	return true;
	}
	
//Enter page no field validation function 
	function pagination(e) 
	{
		var totalpages=<%=vsp_commondata.getTotalNoOfPages()%>;
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
    var val;
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
	function addVendorProject()
	{
	 document.getElementById("gridForm").action ='VENDOR_PROJECT_CONTROLLER?Option=2';
	 document.getElementById("gridForm").submit();
	 return true;   
	}
	
//Edit button function   
	function editVendorproject()
	{
	
	   if(getRadioVal() == null || getRadioVal() == "0")
	    {
		 alert('Please select type or code')
	    }
	     else
	     {
	    var selected_value = getRadioVal();
	    
	      document.forms[0].action='VENDOR_PROJECT_CONTROLLER?Option=2&VP_SRNO='+selected_value;
		  document.forms[0].submit();
		 }
		return true;   
	}


//ClearSearch button function   
	function clearSearch()
	{
	document.getElementById("NAME").selectedIndex = 0;
	document.getElementById("PROJECT").selectedIndex = 0;
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
			<input type="hidden" name="USERNAME"
				value="<%=vsp_commondata.getUSERNAME()%>">
			<input type="hidden" name="DATE"
				value="<%=vsp_commondata.getLOGINDATE()%>">
			<input type="hidden" name="USERROLE"
				value="<%=vsp_commondata.getUSERROLE()%>">
			<input type="hidden" name="LOGINID"
				value="<%=vsp_commondata.getLOGINID()%>">

			<table align="center" class="mainTable">
				<tr align="center">
					<td>
						<img class="headerImg" src="img/StoresHeader.jpg">
					</td>
				</tr>
				<tr>
					<td>
						<table border="0" cellspacing="0" cellpadding="0" align="center"
							class="childTable">
							<tr>
							<tr>
								<td valign="top" align="right">
									Welcome
									<%=vsp_commondata.getUSERNAME()%>
									, Date:
									<%=vsp_commondata.getLOGINDATE()%></td>
							</tr>
							<tr>
								<td align="center">
									<h2>
										VENDOR PROJECT DETAILS
									</h2>
								</td>
							</tr>
							<tr>
								<td>
									<table class="gridTable gridTableBg" align="center">
										<tr class=th>
											<th>
												Select
											</th>
											<th>
												Project
											</th>
											<th>
												Location
											</th>
											<th>
												Manager
											</th>
											<th>
												Mailid
											</th>
											
										</tr>
										<%
											List<PROJECT_MASTER> vendorprojectBeanList = (List<PROJECT_MASTER>) request
													.getAttribute("vendorprojectBeanList");
											int endRecord = vsp_commondata.getPAGENO() * 10;
											int startRecord = endRecord - 10;
											int i = 1;
											for (PROJECT_MASTER vendorproject : vendorprojectBeanList) {
												if (i > startRecord && i <= endRecord) {
										%>
										<tr id="<%=i%>">
											<%
												} else {
											%>
										
										<tr style="display: none;" id="<%=i%>">
											<%
												}
													if (vsp_commondata.getSelectedSerialNo() == vendorproject
															.getPROJECTSRNO()) {
											%>
											<td align="center">
												<input type="radio" name="radio" id="radio"
													value="<%=vendorproject.getPROJECTSRNO()%>" checked="checked" />
											</td>
											<%
												} else {
											%>
											<td align="center">
												<input type="radio" name="radio" id="radio"
													value="<%=vendorproject.getPROJECTSRNO()%>" />
											</td>
											<%
												}
											%>

											<td><%=vendorproject.getPROJECTNAME()%></td>
											<td><%=vendorproject.getLOCATION()%></td>
											<td><%=vendorproject.getMANAGER()%></td>
											<td><%=vendorproject.getEMAILID()%></td>
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
										<p align="center">
											<font color="red">Status : <%=message%></font>
										</p>
										<p align="center">
											Total Number of Records :
											<%=vsp_commondata.getTotalNoOfRecords()%>
											| Total Number of Pages :
											<span id="totalNoOfPages"><%=vsp_commondata.getTotalNoOfPages()%>
											</span> | Enter Page No:
											<select name="pageNo" id="pageNo" onchange="pagination()">
												<%
													for (int j = 1; j <= vsp_commondata.getTotalNoOfPages(); j++) {
														if (j == vsp_commondata.getPAGENO()) {
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

												
												<td>Code</td>
												<td><input type="text" id="CPROJECTSRNO" name="CPROJECTSRNO" value="<%=vsp_commondata.getPROJECTSRNO()%>" style="width:77%"/>
												<span style="color:red;" id="err_PROJECTSRNO"></span></td>
											</tr>



										</table>
										<div class="searchCriteriaButtons" align="center">
											<button type="button" onclick="search()" class="button">
												Search
											</button>
											<button type="button" onclick="clearSearch()" class="button">
												clearSearch
											</button>
										</div>
							<tr align="center">
								<td>
									<div class="operationalButtons">
										<button type="button" onclick="editVendorproject()" class="button">
											ADD
										</button>
										<button type="button" onclick="editVendorproject()"
											class="button">
											EDIT
										</button>
										
										<button type="button" onclick="exitVendorGrid()"
											class="button">
											EXIT
										</button>
									</div>
								</td>
							</tr>
							<tr align="center">
								<td>
									<img class="headerImg" src="img/footer.jpg">
								</td>
							</tr>
						</table>
				<tr>
					<td>
						&nbsp;
	</body>
</html>