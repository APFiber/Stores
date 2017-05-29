<%@page import="bean.ITEM_DETAILS"%>
<%@ page language="java" import="java.util.List" pageEncoding="ISO-8859-1"%>
<%@page import="bean.ITEM_CommonData"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Item Grid</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	 <%
	 	ITEM_CommonData commonData = (ITEM_CommonData)request.getAttribute("commonData");
		long ITEMCODE=commonData.getITEMCODE();  				   				
 	 	String ITEMCODEX;  	ITEMCODEX="";
 	 		if (ITEMCODE>0)ITEMCODEX=""+ITEMCODE;
 	 	
 	 	String ITEMGROUP=commonData.getITEMGROUP();  				   				
 	 		if(ITEMGROUP==null) ITEMGROUP="";
 	 	String ITEMMAKE=commonData.getITEMMAKE();  				   				
 	 		if(ITEMMAKE==null) ITEMMAKE="";
 	 	String ITEMDESCRIPTION=commonData.getITEMDESCRIPTION();  				   				
 	 		if(ITEMDESCRIPTION==null) ITEMDESCRIPTION="";
 	 	String ITEMSTATUS=commonData.getITEMSTATUS();
 	 		if(ITEMSTATUS==null) ITEMSTATUS="";
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

function closeWindow()
{
	 window.close();
} 

function chooseItem()
{
	if(getRadioVal() == null || getRadioVal() == "0" || getRadioVal() == "" )
	{
		alert('Please select item');
	}
	else
	{
		opener.getSelectedItem(getRadioVal());
		window.close();
	}
}

//Function for get the radio value which is selected
function getRadioVal() 
{
    var val=0;
    // get list of radio buttons with specified name
    var radios = document.getElementsByName('ITEMCODESRNO');
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

//reset search form 
function clearSearch()
{
	document.getElementById("ITEMCODE").value="";
	document.getElementById("ITEMGROUP").value="";
	document.getElementById("ITEMMAKE").value="";
	document.getElementById("ITEMDESCRIPTION").value="";
}
	
//function for item search fields
function itemSearch()
{
	var option = document.getElementById("pageNo").value;
	if(option == "")
	{
		document.getElementById("pageNo").value="1";
	}
	document.getElementById("gridForm").action='ITEM_CONTROLLER?Option=7';
	document.getElementById("gridForm").submit();
	return true;
}

function load()
{
	opener.location.reload(false);
}
	
</script>
  </head>
  <body onload="load()">
  <form name="gridForm" id="gridForm" method="POST">
  <input type="hidden" name="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGINDATE() %>">
  <input type="hidden" name="USERROLE" value="<%=commonData.getUSERROLE() %>">
  <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID() %>">
  
   <table>
	  	<tr><td> <table  border="0" cellspacing="0" cellpadding="0" align="center" class="childTable" style="height: 100; width: 100%">
		    	<tr><td align="center"><h2>ITEM DETAILS</h2></td></tr>
		    	<tr><td><table class="gridTable gridTableBg" align="center">
 	<tr>		
 		<th>Select</th>
 		<th>Item Code</th>
 		<th>Group</th>
 		<th>Make</th>
 		<th>Description</th>
 		<th>Units</th>
 		<th>Item status</th>
 	</tr>
 	<%List<ITEM_DETAILS> itemBeanList = (List<ITEM_DETAILS>)request.getAttribute("itemBeanList");
 	
 	int endRecord=commonData.getPage_No()*10;
 	int startRecord=endRecord-10;
 	int i=1;
 		for(ITEM_DETAILS item : itemBeanList){
 		long ITEM_ITEM_CODE=item.getITEMCODE(); 				   				
 	 		String ITEM_ITEMGROUP=item.getITEMGROUP(); 				   				
 	 			if(ITEM_ITEMGROUP==null) ITEM_ITEMGROUP="";
 	 		String ITEM_ITEMMAKE=item.getITEMMAKE(); 				   				
 	 			if(ITEM_ITEMMAKE==null) ITEM_ITEMMAKE="";
 	 		String ITEM_ITEMDESCRIPTION=item.getITEMDESCRIPTION();
 	 			if(ITEM_ITEMDESCRIPTION==null) ITEM_ITEMDESCRIPTION="";
 	 		String ITEM_ITEMUNITS=item.getITEMUNITS();
 	 			if(ITEM_ITEMUNITS==null) ITEM_ITEMUNITS="";
 	 		String ITEM_ITEMSTATUS=item.getITEMSTATUS();
 	 			if(ITEM_ITEMSTATUS==null) ITEM_ITEMSTATUS="";
 			 if(i>startRecord && i<=endRecord)
 			{
 			%>
 	 		<tr  id="<%=i %>">
 			<%
 			}
 			else
 			{
 			 %>
 	 		<tr style="display : none;" id="<%=i %>">
 	 		<% 
 	 		}
 	 		%>
 	 		<%if(ITEM_ITEM_CODE > 0) 
 	 		{
 	 		%>
 	 		<td align="center"><input type="radio" name="ITEMCODESRNO" id="ITEMCODESRNO" value="<%=item.getITEMCODE()%>"/></td>
 	 		<td><%=ITEM_ITEM_CODE%></td>
 	 		<td><%=ITEM_ITEMGROUP%></td>
 	 		<td><%=ITEM_ITEMMAKE%></td>
 	 		<td><%=ITEM_ITEMDESCRIPTION%></td>
 	 		<td><%=ITEM_ITEMUNITS%></td>
 	 		<td><%=ITEM_ITEMSTATUS%></td> 		
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
 <p align="center">Total Number of Records : <%=commonData.getTotal_NoOf_Records() %> | Total Number of Pages :<span id="totalNoOfPages"><%=commonData.getTotal_NoOf_Pages() %> </span> | Select Page No: 
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
	<table style="width:100%" align="center">
		
	<tr>
		<td>Item code</td>
		<td><input type="text" id="ITEMCODE" name="ITEMCODE" value="<%=ITEMCODEX%>" style="width:77%"/>
		<span style="color:red;" id="err_item_code"></span></td>
		
		<td>Item Group</td>
		<td><input type="text" id="ITEMGROUP" name="ITEMGROUP" value="<%=ITEMGROUP%>" style="width:77%"/>
		<span style="color:red;" id="err_group_check"></span></td>
		</tr>
		
		<tr>
		<td>Item Make</td><td>
		<input type="text" id="ITEMMAKE" name="ITEMMAKE" value="<%=ITEMMAKE%>" style="width:77%"/>
		<span style="color:red;" id="err_make_check"></span></td>

		<td>Item Description</td><td>
		<input type="text" id="ITEMDESCRIPTION" name="ITEMDESCRIPTION" value="<%=ITEMDESCRIPTION%>" style="width:77%"/>
		<span style="color:red;" id="err_itemDesc_check"></span></td>
		</tr>
		
	</table>
	<div class ="searchCriteriaButtons" align="center">
	<button type="button" onclick="itemSearch()" class="button">Search</button>
	<button type="button" onclick="clearSearch()"  class="button">Clear Search</button>
</div>
	
	</table>
	<tr><td>&nbsp;
  <tr align="center"><td> <button type="button" onclick="chooseItem()" class="button">Choose</button>
	<button type="button" onclick="closeWindow()"  class="button">Exit</button></td></tr>
   
</table>
 </form>
</body>
</html>