<%@page import="bean.MAJOR_MINOR_CODE"%>
<%@page import="bean.MAJOR_MINOR_CommonData"%>
<%@page import="java.util.List"%>
<%@page import="org.KrrCommon"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>Major Minor Grid</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	 <%
	 MAJOR_MINOR_CommonData commonData = (MAJOR_MINOR_CommonData)request.getAttribute("commonData");
	 	long MAJORCODE=commonData.getMAJORCODE(); 				   				
 	 	String MAJORCODEX;  	MAJORCODEX="";
 	 		if (MAJORCODE>0)MAJORCODEX=""+MAJORCODE;
 	 		
 	 	String MAJORCODEDESC=commonData.getMAJORCODEDESC();  				   				
 	 		if(MAJORCODEDESC==null) MAJORCODEDESC="";
 	 
 	    long MINORCODE=commonData.getMINORCODE(); 				   				
 	 	String MINORCODEX;  	MINORCODEX="";
 	 		if (MINORCODE>0)MINORCODEX=""+MINORCODE;		
	 	
	 	String MINORCODEDESC=commonData.getMINORCODEDESC();   				   				
 	 		if(MINORCODEDESC==null) MINORCODEDESC="";
	  %>
	<script type="text/javascript">
	
function pagination() 
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

function exitMajorMinorGrid()
{
	document.getElementById("majorGridForm").action = "TERA_STORES_PURCHASE?Option=3";
	document.getElementById("majorGridForm").submit();
} 

//Function for to get the selected radio value
function getRadioValMM() 
{
    var val=0;
    // get list of radio buttons with specified name
    var radios = document.getElementsByName('MAJORMINORSRNO');
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

function majorClearSearch()
{
	document.getElementById("MAJORCODE").value="";
	document.getElementById("MAJORCODEDESC").value="";
	document.getElementById("MINORCODE").value="";
	document.getElementById("MINORCODEDESC").value = "";
}

//Function for to go to the major minor controller where option is 6
//change the status to delete 
function get(name)
{
	if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
   	return decodeURIComponent(name[1]);
}

//Function for to perform the search in major minor grid
function major_minor_Search()
{
	var option = document.getElementById("pageNo").value;
	if(option == "")
	{
		document.getElementById("pageNo").value="1";
	}
	document.getElementById("majorGridForm").action='MAJOR_MINOR_CONTROLLER?Option=1';
	
	document.getElementById("majorGridForm").submit();
	return true;
}

//Function for to go to the addMajor.jsp page 
function addMajor()
{
    document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=2';
	document.forms[0].submit();
	return true;   
}

//Function for to go the addMinor.jsp page
function addMinor()
{
     document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=4';
	 document.forms[0].submit();
	 return true;   
}

function deleteMajorMinor()
{
	if(getRadioValMM() == null|| getRadioValMM() == "0")
	{
		alert('Please select major code');
	}
	else
	{
		var selected_value= getRadioValMM();
	   	document.forms[0].action='MAJOR_MINOR_CONTROLLER?Option=6&MMSRNO='+selected_value;
	 	document.forms[0].submit();
		return true; 
	}  
} 
	</script>
  </head>
  <body>
  <%KrrCommon krrCommon = new KrrCommon();
   %>
  <form name="majorGridForm" id="majorGridForm" method="post">
  <input type="hidden" name="USERNAME" id="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGINDATE() %>">
  <input type="hidden" name="USERROLE" id="USERROLE" value="<%=commonData.getUSERROLE() %>">
   <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID()%>">
   <table align="center" class="mainTable" style="height: 450px">
	  	<tr align="center"><td><jsp:include page="header1.jsp"></jsp:include></td></tr>
	  		<tr><td> <table width="100%" border="0" cellspacing="0" cellpadding="0" class="childTable" align="center">
	  		<tr><tr><td valign="top" align="right">Welcome : <%=commonData.getUSERNAME() %> , Date: <%=commonData.getLOGINDATE() %></td></tr>
	  <tr><td> <h2> MAJOR MINOR DETAILS </h2></td></tr>
 <tr><td><table class="gridTable gridTableBg" align="center">
  	<tr  class=th>
 		<th>Select</th>
 		<th>Major Code</th>
 		<th>Major Code Description</th>
 		<th>Minor Code</th>
 		<th>Minor Code Description</th>
 		<th>Status</th>
 	</tr>
 	<%
 		List<MAJOR_MINOR_CODE> majorBeanList = (List<MAJOR_MINOR_CODE>)request.getAttribute("majorBeanList");
 		int endRecord=commonData.getPage_No()*10;
 		int startRecord=endRecord-10;
 		int i=1;
 		for(MAJOR_MINOR_CODE major : majorBeanList){
 			
 			long MAJORCODE_MAJORMINOR_CODE=major.getMAJORCODE(); 				   				
 	 		String MAJORCODE_MAJORMINOR_CODEX;
 	 		MAJORCODE_MAJORMINOR_CODEX="";
 	 		if (MAJORCODE_MAJORMINOR_CODE>0)MAJORCODE_MAJORMINOR_CODEX=""+MAJORCODE_MAJORMINOR_CODE;
 	 		String MAJORCODEDESC_MAJORMINOR_CODE=major.getMAJORCODEDESC();   				   				
 	 			if(MAJORCODEDESC_MAJORMINOR_CODE==null) MAJORCODEDESC_MAJORMINOR_CODE="";
 	 		
 	 		long MINORCODE_MAJORMINOR_CODE=major.getMINORCODE(); 				   				
  	 		String MINORCODE_MAJORMINOR_CODEX;
 	 		MINORCODE_MAJORMINOR_CODEX="";
  	 		if (MINORCODE_MAJORMINOR_CODE>0)MINORCODE_MAJORMINOR_CODEX=""+MINORCODE_MAJORMINOR_CODE;
 	 		
 	 		String MINORCODEDESC_MAJORMINOR_CODE=major.getMINORCODEDESC();   				   				
 	 			if(MINORCODEDESC_MAJORMINOR_CODE==null) MINORCODEDESC_MAJORMINOR_CODE="";
 			
 			String STATUS_MAJORMINOR_CODE=major.getSTATUS();  				   				
 	 			if(STATUS_MAJORMINOR_CODE==null) STATUS_MAJORMINOR_CODE="";
 			
 			if(i>startRecord && i<=endRecord)
 			{
 	%>
 	 		<tr  id="<%=i%>">
 			<%
 			}	else{ 
 		   %>
 	 		<tr style="display : none;" id="<%=i%>">
 	 		<%
 	 		}
 	%>
 	<%
 		if(MAJORCODE_MAJORMINOR_CODE > 0)
 		{
 	 %>
	 		<td align=center><input type=radio name="MAJORMINORSRNO" id="MAJORMINORSRNO" value="<%=major.getMAJORMINORSRNO()%>" /></td>
 	 		<td><%=MAJORCODE_MAJORMINOR_CODEX%></td>
 	 		<td><%=MAJORCODEDESC_MAJORMINOR_CODE%></td>
 	 		<td><%=MINORCODE_MAJORMINOR_CODEX%></td>
 	 		<td><%=MINORCODEDESC_MAJORMINOR_CODE%></td>
 	 		<td><%=STATUS_MAJORMINOR_CODE%></td>
 	 		</tr>
 	 	
 	 	<% 
 	 		}
  	 	
 	 	else 
 	 	{
 	 	%>
 	 		<td>&nbsp</td>
 	 		<td></td>
 	 		<td></td>
 	 		<td></td>
 	 		<td></td>
 	 		<td></td>
 	 	
 	 	<%
 	 	} 
 	 	%>
 			<%	
	 			i++;	 	
	 			}//for
 			%>
 </table>
  <div class="pagination">
   <%
  String message=(String)request.getAttribute("message");
  if(krrCommon.isValuenull(message)){
  	message ="";
  }%>
  <p align="center"><font color="red">Status : <%=message%></font></p>
  <p align="center">Total Number of Records : <%=commonData.getTotal_NoOf_Records() %> | Total Number of Pages :<span id="totalNoOfPages"><%=commonData.getTotal_NoOf_Pages() %> </span> | Select Page No
  <select  name="pageNo" id="pageNo"  onchange="pagination()">
 <%for(int j=1;j<=commonData.getTotal_NoOf_Pages();j++ ){
 if(j==commonData.getPage_No()){
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
	<table style="width:100%">
		<tr>
		<td>Major code</td>
		<td><input type="text" name="MAJORCODE" id="MAJORCODE" value="<%=MAJORCODEX%>"/>
		<span style="color:red;" id="err_majorcode_check"></span>
		</td>
		
		<td>Major code Description</td>
		<td><input type="text" name="MAJORCODEDESC" id="MAJORCODEDESC" value="<%=MAJORCODEDESC%>"/>
		<span style="color:red;" id="err_majorcodedesc_check"></span>
		</td>
		</tr>
		
		<tr>
		<td>Minor code</td>
		<td><input type="text" name="MINORCODE" id="MINORCODE" value="<%=MINORCODEX%>"/>
		<span style="color:red;" id="err_minorcode_check"></span>
		</td>
		
		<td>Minor code Description</td>
		<td><input type="text" name="MINORCODEDESC" id="MINORCODEDESC" value="<%=MINORCODEDESC%>"/>
		<span style="color:red;" id="err_minorcodedesc_check"></span>
		</td>
		</tr>
		
		
	</table>
	<div class ="searchCriteriaButtons">
	<button type="button" onclick="major_minor_Search()" class="button"> Search</button>
	<button type="button" onclick="majorClearSearch()" class="button">Clear Search</button>
</div>
</div>

<div class="operationalButtons">
	<button type="button" onclick="addMajor()" class="button">ADD MAJOR CODE</button>
 	<button type="button" onclick="addMinor()" class="button">ADD MINORCODE</button>
 	<button type="button" onclick="deleteMajorMinor()" class="button">ACTIVATE/DEACTIVATE</button>
	<button type="button" onclick="exitMajorMinorGrid()" class="button">EXIT</button>
</div></td></tr>
<tr align="center"><td><jsp:include page="footer.jsp"></jsp:include></td></tr>
 </table>
 </td></tr> </table>
 </form>
</body>
</html>
