<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.text.*"%>

<%@page import="org.KrrCommon"%>
<%@page import="bean.PROJECT_MASTER"%>
<%@page import="bean.PROJECT_CommonData"%>
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

			PROJECT_CommonData commonData = (PROJECT_CommonData) request
					.getAttribute("commonData");
					long CPROJECTSRNO=commonData.getPROJECTSRNO();
					String CPROJECTNAME=commonData.getPROJECTNAME();
					String CMANAGER=commonData.getMANAGER();
					String CLOCATION=commonData.getLOCATION();
					String CEMAILID=commonData.getEMAILID();
					
					if(CPROJECTNAME==null) CPROJECTNAME="";
					if(CMANAGER==null) CMANAGER="";
					if(CLOCATION==null) CLOCATION="";
					if(CEMAILID==null) CEMAILID="";
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
	document.getElementById("gridForm").action='PROJECT_MASTER_CONTROLLER?Option=1';
	
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
	function addProject()
	{
	 document.getElementById("gridForm").action ='PROJECT_MASTER_CONTROLLER?Option=2';
	 document.getElementById("gridForm").submit();
	 return true;   
	}
	
//Edit button function   
	function editproject()
	{
	
	   if(getRadioVal() == null || getRadioVal() == "0")
	    {
		 alert('Please select type or code')
	    }
	     else
	     {
	    var selected_value = getRadioVal();
	    
	      document.forms[0].action='PROJECT_MASTER_CONTROLLER?Option=4&VP_SRNO='+selected_value;
		  document.forms[0].submit();
		 }
		return true;   
	}


//ClearSearch button function   
	function clearSearch()
	{
		document.getElementById("PROJECTNAME").selectedIndex ="";
		document.getElementById("LOCATION").selectedIndex = "";
		document.getElementById("MANAGER").selectedIndex = "";
		document.getElementById("CPROJECTSRNO").value = "";
		
	
	}
	
//Exit button function   
	function exitprojectGrid()
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
				value="<%=commonData.getUSERNAME()%>">
			<input type="hidden" name="DATE"
				value="<%=commonData.getLOGINDATE()%>">
			<input type="hidden" name="USERROLE"
				value="<%=commonData.getUSERROLE()%>">
			<input type="hidden" name="LOGINID"
				value="<%=commonData.getLOGINID()%>">

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
									<%=commonData.getUSERNAME()%>
									, Date:
									<%=commonData.getLOGINDATE()%></td>
							</tr>
							<tr>
								<td align="center">
									<h2>
										PROJECT DETAILS
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
												Code
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
											List<PROJECT_MASTER> projectmasterList = (List<PROJECT_MASTER>) request
													.getAttribute("projectmasterList");
											int endRecord = commonData.getPAGENO() * 10;
											int startRecord = endRecord - 10;
											int i = 1;
											for (PROJECT_MASTER projectmaster : projectmasterList) {
											
											long PROJECTSRNO=projectmaster.getPROJECTSRNO();
											String PROJECTNAME=projectmaster.getPROJECTNAME();
											String LOCATION=projectmaster.getLOCATION();
											String MANAGER=projectmaster.getMANAGER();
											String EMAILID=projectmaster.getEMAILID();
																						
											if(PROJECTNAME==null) PROJECTNAME="";
											if(LOCATION==null) LOCATION="";
											if(MANAGER==null) MANAGER="";
											if(EMAILID==null) EMAILID="";
											
												if (i > startRecord && i <= endRecord) {
										%>
										<tr id="<%=i%>">
											<%
												} else {
											%>
										
										<tr style="display: none;" id="<%=i%>">
											<%
												}
													if (commonData.getSelectedSerialNo() == projectmaster
															.getPROJECTSRNO()) {
											%>
											<td align="center">
												<input type="radio" name="radio" id="radio"
													value="<%=PROJECTSRNO%>" checked="checked" />
											</td>
											<%
												} else {
											%>
											<td align="center">
												<input type="radio" name="radio" id="radio"
													value="<%=PROJECTSRNO%>" />
											</td>
											<%
												}
											%>

											<td><%=PROJECTNAME%></td>
											<td><%=PROJECTSRNO%></td>											
											<td><%=LOCATION%></td>
											<td><%=MANAGER%></td>
											<td><%=EMAILID%></td>
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
											<%=commonData.getTotalNoOfRecords()%>
											| Total Number of Pages :
											<span id="totalNoOfPages"><%=commonData.getTotalNoOfPages()%>
											</span> | Enter Page No:
											<select name="pageNo" id="pageNo" onchange="pagination()">
												<%
													for (int j = 1; j <= commonData.getTotalNoOfPages(); j++) {
														if (j == commonData.getPAGENO()) {
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

												<td>
													Project
												</td>
												<td>
													<select id="PROJECTNAME" name="PROJECTNAME" style="width: 77%">
														<option value="" selected>
															- - Select - -
														</option>
														<%
															if (!(CPROJECTNAME == null || CPROJECTNAME == "")) {
														%>
														<option value="<%=CPROJECTNAME%>" selected><%=CPROJECTNAME%></option>
														<%
															}
														%>
														<%
															List<PROJECT_MASTER> projectlist = (List<PROJECT_MASTER>) request
																	.getAttribute("PROJECT");
															for (PROJECT_MASTER project : projectlist) {
														%><option value="<%=project.getPROJECTNAME()%>">
															<%=project.getPROJECTNAME()%></option>
														<%
															}
														%>
													</select>
													<span style="color: red;" id="err_PROJECTNAME"></span>
												</td>
																								<td>
													Location
												</td>
												<td>
													<select id="LOCATION" name="LOCATION" style="width: 77%">
														<option value="" selected>
															- - Select - -
														</option>
														<%
															if (!(CLOCATION == null || CLOCATION == "")) {
														%>
														<option value="<%=CLOCATION%>" selected><%=CLOCATION%></option>
														<%
															}
														%>
														<%
															List<PROJECT_MASTER> Locationlist = (List<PROJECT_MASTER>) request
																	.getAttribute("LOCATION");
															for (PROJECT_MASTER location : Locationlist) {
														%><option value="<%=location.getLOCATION()%>">
															<%=location.getLOCATION()%></option>
														<%
															}
														%>
													</select>
													<span style="color: red;" id="err_LOCATION"></span>
												</td>

											</tr>
												<tr>

												<td>
													Manager
												</td>
												<td>
													<select id="MANAGER" name="MANAGER" style="width: 77%">
														<option value="" selected>
															- - Select - -
														</option>
														<%
															if (!(CMANAGER == null || CMANAGER == "")) {
														%>
														<option value="<%=CMANAGER%>" selected><%=CMANAGER%></option>
														<%
															}
														%>
														<%
															List<PROJECT_MASTER> managerlist = (List<PROJECT_MASTER>) request
																	.getAttribute("MANAGER");
															for (PROJECT_MASTER manager : managerlist) {
														%><option value="<%=manager.getMANAGER()%>">
															<%=manager.getMANAGER()%></option>
														<%
															}
														%>
													</select>
													<span style="color: red;" id="err_MANAGER"></span>
												</td>
												<td>Code</td>
												<td><input type="text" id="CPROJECTSRNO" name="CPROJECTSRNO" value="<%=CPROJECTSRNO%>" style="width:77%"/>
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
										<button type="button" onclick="addProject()" class="button">
											ADD
										</button>
										<button type="button" onclick="editproject()"
											class="button">
											EDIT
										</button>
										
										<button type="button" onclick="exitprojectGrid()"
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