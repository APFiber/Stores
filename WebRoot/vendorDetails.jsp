<%@page import="bean.VENDOR_SUPPLIER"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="java.text.*"  %>
<%@page import="bean.VENDOR_CommonData;"%>

<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>vendorDetails</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
 	
    
	 <link rel="stylesheet" type="text/css" href="css/style.css">
    
	<script type="text/javascript" src="validation.js"></script>
	<script type="text/javascript">
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
	
	function VendorResetErrors() 
	   {
	  
	    document.getElementById("err_TYPE").innerHTML = "";
		document.getElementById("err_NAME").innerHTML = "";
		document.getElementById("err_ADDRESS1").innerHTML = "";
		document.getElementById("err_ADDRESS2").innerHTML = "";
		document.getElementById("err_ADDRESS3").innerHTML = "";
		document.getElementById("err_ADDRESS4").innerHTML = "";
		document.getElementById("err_CITY").innerHTML = "";
		document.getElementById("err_DISTRICT").innerHTML = "";
		document.getElementById("err_STATE").innerHTML = "";
		document.getElementById("err_COUNTRY").innerHTML = "";
		document.getElementById("err_PIN").innerHTML="";
		document.getElementById("err_PHONENO").innerHTML="";
	}
	
	//Checking for mandatory fields in vendor details JSP page
		function mandatorycheck()
	{
	
		var status;
		status = true;
		VendorResetErrors();
	 if(vendorDetails.ADDTYPE.value == "--Select--")
       {   
	       document.getElementById("err_TYPE").innerHTML="Select Type from list ";
	       status=false;
       }	
	
	
	
	if(vendorDetails.ADDNAME.value.length == 0){
		 document.getElementById("err_NAME").innerHTML="Enter Name";
	       status=false;
	}
	
	if(vendorDetails.ADDADDRESS1.value.length == 0){
		 document.getElementById("err_ADDRESS1").innerHTML="Enter Address line1";
	       status=false;
	}
	
	if(vendorDetails.ADDCITY.value.length == 0){
		 document.getElementById("err_CITY").innerHTML="Enter City";
	       status=false;
	}
	
	if(vendorDetails.ADDDISTRICT.value.length == 0){
		 document.getElementById("err_DISTRICT").innerHTML="Enter District";
	       status=false;
	}
	
	if(vendorDetails.ADDSTATE.value.length == 0){
		 document.getElementById("err_STATE").innerHTML="Enter State";
	       status=false;
	}
	
	if(vendorDetails.ADDCOUNTRY.value.length == 0){
		 document.getElementById("err_COUNTRY").innerHTML="Enter Country";
	       status=false;
	}
	
	if(vendorDetails.ADDPHONENO.value.length == 0){
		 document.getElementById("err_PHONENO").innerHTML="Enter Phone number";
	       status=false;
	}
		return status;
	}
	
		
//Type field validation function
	function type_select(type)
    {
       document.getElementById("err_TYPE").innerHTML="";
    if(type.value == "--Select--")
        {
       document.getElementById("err_TYPE").innerHTML="Must select Type";
	    }
	}


	
// code field validation function
	function codeCheck(code) 
	{
		document.getElementById("err_CODE").innerHTML = "";
		if (code.value.length > 0) 
		{
			var regex1 = /^[0-9]+$/; //this is the pattern of regular expersion
			if (regex1.test(code.value) == false) 
			{
				document.getElementById("err_CODE").innerHTML = " code number should be in numeric only";
			}
		}
		else document.getElementById("err_CODE").innerHTML = "Enter  code";
	}
	
//Name field validation function
	function NameCheck(namecheck) 
	{
		document.getElementById("err_NAME").innerHTML = "";
		if (namecheck.value.length > 0) 
		{
			var letters = /^[a-z A-Z \s]+$/;
			if (letters.test(namecheck.value) == false) 
			{
				document.getElementById("err_NAME").innerHTML = "Enter name in alphabets";
			}
		}
		else document.getElementById("err_NAME").innerHTML="Enter name";
	}
	
//Address Line1 validation function
	function AddressCheck(addresscheck)
	{
		document.getElementById("err_ADDRESS1").innerHTML = "";
		if (addresscheck.value.length > 0) 
		{
			var regex = /[A-Za-z0-9-[\]\s:,.\/]/
			if (regex.test(addresscheck.value) == false) 
			{
				document.getElementById("err_ADDRESS1").innerHTML = "Enter Address line1 in allowable characters";
		    }
		}
		else document.getElementById("err_ADDRESS1").innerHTML = "Enter address line1";
	} 
	
//Address Line2 validation function
	function AddressCheck2(addresscheck2) 
	{
		document.getElementById("err_ADDRESS2").innerHTML = "";
		if (addresscheck2.value.length > 0) 
		{
			var regex3 = /[A-Za-z0-9-[\]\s:,.\/]/
			if (regex3.test(addresscheck2.value) == false) 
			{
				document.getElementById("err_ADDRESS2").innerHTML = "Enter Address line2 in allowable characters";
			}
		}
	}
//Address Line3 validation function
	function AddressCheck3(addresscheck3) 
	{
		document.getElementById("err_ADDRESS3").innerHTML = "";
		if (addresscheck3.value.length > 0) 
		{
			var regex = /[A-Za-z0-9-[\]\s:,.\/]/
			if (regex.test(addresscheck3.value) == false) 
			{
				document.getElementById("err_ADDRESS3").innerHTML = "Enter Address line3 in allowable characters";
			}
		}
	}
//Address Line4 validation function
	function AddressCheck4(addresscheck4) 
	{
		document.getElementById("err_ADDRESS4").innerHTML = "";
		if (addresscheck4.value.length > 0) 
		{
			var regex =  /[A-Za-z0-9-[\]\s:,.\/]/
			if (regex.test(addresscheck4.value) == false) 
			{
				document.getElementById("err_ADDRESS4").innerHTML = "Enter Address line4 in allowable characters";
			}
		}
	}
	
//City name validation function
	function CityCheck(citycheck)
	 {
		document.getElementById("err_CITY").innerHTML = "";
		if (citycheck.value.length > 0) 
		{
			var regex = /^[a-zA-Z\s]+$/;
			if (regex.test(citycheck.value) == false) 
			{
				document.getElementById("err_CITY").innerHTML = "Enter city name in alphabets";
			}			
		}
		else document.getElementById("err_CITY").innerHTML = "Enter City name";
	}
	
//District name validation function
	function DistrictCheck(districtcheck) 
	{
		document.getElementById("err_DISTRICT").innerHTML = "";
		if (districtcheck.value.length > 0) 
		{
			var letters = /^[a-z A-Z \s]+$/;
			if (letters.test(districtcheck.value) == false) 
			{
				document.getElementById("err_DISTRICT").innerHTML = "Enter district name in alphabets";
			}
		}
		else document.getElementById("err_DISTRICT").innerHTML = "Enter district name";
	}
	
//State name validation function
	function StateCheck(statecheck) 
	{
		document.getElementById("err_STATE").innerHTML = "";
		if (statecheck.value.length > 0) 
		{
			var regex = /^[a-z . A-Z \s]+$/;
			if (regex.test(statecheck.value) == false) 
			{
				document.getElementById("err_STATE").innerHTML = "Enter state name in alphabets";
			}
		}
		else document.getElementById("err_STATE").innerHTML = "Enter state name";
	}
	
//Country name validation function
	function CountryCheck(countrycheck) 
	{
		document.getElementById("err_COUNTRY").innerHTML = "";
		if (countrycheck.value.length > 0) 
		{
			var regex = /^[a-z.A-Z\s]+$/;
			if (regex.test(countrycheck.value) == false) 
			{
				document.getElementById("err_COUNTRY").innerHTML = "Enter country name in alphabets";
			}
		}
		else document.getElementById("err_COUNTRY").innerHTML = "Enter country name";
	}
	
//PIN code validation function
	function pincheck(pin) 
	{
		document.getElementById("err_PIN").innerHTML = "";
		if (pin.value.length > 0) 
		{
			var reg = /^[0-9]+$/;
			if (reg.test(pin.value) == false) {
				document.getElementById("err_PIN").innerHTML = "Pin code should be numeric only";
			}
		}
	}
//Project validation function
	function projectCheck(proname) 
	{
		document.getElementById("err_PROJECT").innerHTML = "";
		if (proname.value.length > 0) 
		{
			var regexpression = /^[A-Za-z0-9_,-\s]+$/;
			if (reg.test(proname.value) == false) 
			{
				document.getElementById("err_PROJECT").innerHTML = "Enter project name in alphabets";
			}
		}
		else document.getElementById("err_PROJECT").innerHTML = "Enter project";
	}
	
//Project manager validation function
	function ManagerCheck(promang) 
	{
		document.getElementById("err_MANAGER").innerHTML = "";
		if (promang.value.length > 0)
		 {
			var reg = /^[A-Za-z\s]+$/;
			if (reg.test(promang.value) == false) 
			{
				document.getElementById("err_MANAGER").innerHTML = "Enter manager name in alphabets";
			}
		}
		else document.getElementById("err_MANAGER").innerHTML = "Enter manager name";
	}
	
//E-mail field validation function
	function emailCheck(email) 
	{
		document.getElementById("err_MANAGERMAILID").innerHTML = "";
		if (email.value.length > 0) 
		{
			var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			if (reg.test(email.value) == false) 
			{
				document.getElementById("err_MANAGERMAILID").innerHTML = "Enter valid email id";
			}
		}
		else document.getElementById("err_MANAGERMAILID").innerHTML = "Enter Project Manager E-Mail ID";
	}
	
//Phone number field validation function
	function phonenumber(inputtxt)
	 {
		document.getElementById("err_PHONENO").innerHTML = "";
		if (inputtxt.value.length > 0) 
		{
			var reg = /^[0-9]+$/;
			if (reg.test(inputtxt.value) == false) {
				document.getElementById("err_PHONENO").innerHTML = "Enter valid Mobile Number";
			}
		}
		else  document.getElementById("err_PHONENO").innerHTML = "Enter phone number";
	}
	
	
//Save button funtion	
	  function saveVENDOR_SUPPLIER()
     {
	    var mandatory=mandatorycheck();
	      if(mandatory)
	      {
	      document.forms[0].action='VENDOR_SUPPLIER_CONTROLLER?Option=3';
		  document.forms[0].submit();
		  }
	 }
	 
	 
	 function editFormFucntion() 
    {
		var option = getParameterByName("option");
		  if (option == 4)
		  {
			document.getElementById('ADDCODE').readOnly = true;
			document.getElementById('reset').type = "hidden";
		  }
    }

//Exit button function   
    function exitVendorDetails(){
    document.forms[0].action='VENDOR_SUPPLIER_CONTROLLER?Option=1';
 	document.forms[0].submit();
    }
	</script>


	
  </head>
  <body class=bdy onload="editFormFucntion()">
 <%VENDOR_CommonData  commonData= (VENDOR_CommonData)request.getAttribute("commonData"); %>
 <form action="VendorSupplierController" method="post" name="vendorDetails">
     <input type="hidden" name="USERNAME" value="<%=commonData.getUSERNAME() %>" >
  	 <input type="hidden" name="DATE" value="<%=commonData.getLOGINDATE() %>">
  	 <input type="hidden" name="USERROLE" value="<%=commonData.getUSERROLE() %>">
  	  <input type="hidden" name="LOGINID" value="<%=commonData.getLOGINID()%>">
  	 <input type="hidden" id="TYPE" name="TYPE" value="<%=commonData.getTYPE()%>" />
  	 <input type="hidden" id="CODE" name="CODE" value="<%=commonData.getVSSRNO()%>" />
  	 <input type="hidden" id="NAME" name="NAME" value="<%=commonData.getNAME()%>" />
  	 <input type="hidden" id="CITY" name="CITY" value="<%=commonData.getCITY()%>" />
  	 <input type="hidden" id="DISTRICT" name="DISTRICT" value="<%=commonData.getDISTRICT()%>" />
  	 <input type="hidden" id="STATE" name="STATE" value="<%=commonData.getSTATE()%>" />
  	 <input type="hidden" id="PIN" name="PIN" value="<%=commonData.getPIN()%>" />
  	 <input type="hidden" id="PROJECT" name="PROJECT" value="<%=commonData.getPROJECT()%>" />
  	 <input type="hidden" id="MANAGER" name="MANAGER" value="<%=commonData.getMANAGER()%>" />
  	 <input type="hidden" id="PHONENO" name="PHONENO" value="<%=commonData.getPHONENO()%>" />
  	 <input type="hidden" id="STATUS" name="STATUS" value="<%=commonData.getSTATUS()%>" />
  	 <input type="hidden" id="pageNo" name="pageNo" value="<%=commonData.getPAGENO()%>">
    <%VENDOR_SUPPLIER VENDOR_SUPPLIER =(VENDOR_SUPPLIER)request.getAttribute("VENDOR_SUPPLIER");
    		String ADDTYPE=VENDOR_SUPPLIER.getTYPE();
			String ADDNAME=VENDOR_SUPPLIER.getNAME();
			String ADDDISTRICT=VENDOR_SUPPLIER.getDISTRICT();
			String ADDADDRESS1=VENDOR_SUPPLIER.getADDRESSLINE1();
			String ADDADDRESS2=VENDOR_SUPPLIER.getADDRESSLINE2();
			String ADDADDRESS3=VENDOR_SUPPLIER.getADDRESSLINE3();
			String ADDADDRESS4=VENDOR_SUPPLIER.getADDRESSLINE4();
			String ADDCITY=VENDOR_SUPPLIER.getCITY();
			String ADDDICTRICT=VENDOR_SUPPLIER.getDISTRICT();
			String ADDSTATE=VENDOR_SUPPLIER.getSTATE();
			String ADDCOUNTRY=VENDOR_SUPPLIER.getCOUNTRY();
			long ADDPIN=VENDOR_SUPPLIER.getPIN();
			long ADDPHONENO=VENDOR_SUPPLIER.getPHONENO();
			
			if(ADDTYPE==null) ADDTYPE="";
			if(ADDNAME==null) ADDNAME="";
			if(ADDDISTRICT==null) ADDDISTRICT="";
			if(ADDADDRESS1==null) ADDADDRESS1="";
			if(ADDADDRESS2==null) ADDADDRESS2="";
			if(ADDADDRESS3==null) ADDADDRESS3="";
			if(ADDADDRESS4==null) ADDADDRESS4="";
			if(ADDCITY==null) ADDCITY="";
			if(ADDDICTRICT==null) ADDDICTRICT="";
			if(ADDSTATE==null) ADDSTATE="";
			if(ADDCOUNTRY==null) ADDCOUNTRY="";
			
			
    %> 
        <input type="hidden" name="VSCODE" value="<%=VENDOR_SUPPLIER.getVSSRNO()%>">
        <table  class="mainTable">
	  	<tr align="center"><td align="center"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
	  	<tr><td> <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getUSERNAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getLOGINDATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
		    		<tr><td> <table class="childTable" height="200"  align="center">
				<tr><td><h2 align="left">ADD VENDOR/SUPPLIER</h2></td></tr>
	<tr>
		<td class="formLable">Type:</td>
				<td>
				<select   id="ADDTYPE" name="ADDTYPE"  style="width:57%" onblur="type_select(this)">
	<% if(ADDTYPE != ""){
		%><option><%=ADDTYPE %></option><%
	} else{%>
	<option>--Select--</option>
	<%}%>
 	<option>VENDOR</option>
		<option>SUPPLIER</option>
			 	</select>
			 	<font color="#ff0000">*</font>
				<br>
				<span style="color:red;" id="err_TYPE"></span>
				</td>
</tr>
	
<%--		<tr>--%>
<%--					<td class="formLable">Code:</td>--%>
<%--					<td><input type="text" name="ADDCODE" id="ADDCODE"  value="<%=vendor.getCODE()%>" onblur="codeCheck(this)">--%>
<%--					<font color="#ff0000">*</font><br>--%>
<%--					<span style="color:red;" id="err_CODE"></span>--%>
<%--					</td>--%>
<%--				</tr>--%>
				<tr>
					<td class="formLable">Name:</td>
					<td> <input type="text" name="ADDNAME" id="ADDNAME" maxlength="15" value="<%= ADDNAME%>" onblur="NameCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_NAME"></span></td>
				</tr>
				<tr>
					<td class="formLable">Address Line1:</td>
					<td><input type="text" name="ADDADDRESS1" id="ADDADDRESS1" maxlength="15" value="<%= ADDADDRESS1%>" onblur="AddressCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_ADDRESS1"></span></td>
				</tr>
				<tr>
					<td class="formLable">Address Line2:</td>
					<td><input type="text" name="ADDADDRESS2" id="ADDADDRESS2" maxlength="15" value="<%= ADDADDRESS2%>"  onblur="AddressCheck2(this)"><br>
					<span style="color:red;" id="err_ADDRESS2"></span></td>
				</tr>
				<tr>
					<td class="formLable">Address Line3:</td>
					<td><input type="text" name="ADDADDRESS3" id="ADDADDRESS3" maxlength="15" value="<%= ADDADDRESS3%>"  onblur="AddressCheck3(this)"><br>
					<span style="color:red;" id="err_ADDRESS3"></span></td>
				</tr>
				<tr>
					<td class="formLable">Address Line4:</td>
					<td><input type="text" name="ADDADDRESS4" id="ADDADDRESS4" maxlength="15" value="<%= ADDADDRESS4%>"  onblur="AddressCheck4(this)"><br>
					<span style="color:red;" id="err_ADDRESS4"></span></td>
				</tr>
				<tr>
					<td class="formLable">City:</td>
					<td><input type="text" name="ADDCITY" id="ADDCITY" maxlength="15" value="<%= ADDCITY%>" onblur="CityCheck(this)">
					<font color="#ff0000">*</font><br>
				<span style="color:red;" id="err_CITY"></span></td>
				</tr>
				<tr>
					<td class="formLable">District:</td>
					<td><input type="text" name="ADDDISTRICT" id="ADDDISTRICT" maxlength="15" value="<%= ADDDISTRICT%>" onblur="DistrictCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_DISTRICT"></span></td>
				</tr>
				<tr>
					<td class="formLable">State:</td>
					<td><input type="text" name="ADDSTATE" id="ADDSTATE" maxlength="15" value="<%= ADDSTATE%>"  onblur="StateCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_STATE"></span></td>
				</tr>
				<tr>
					<td class="formLable">Country:</td>
					<td><input type="text" name="ADDCOUNTRY" id="ADDCOUNTRY" maxlength="15" value="<%= ADDCOUNTRY%>" onblur="CountryCheck(this)">
					<font color="#ff0000">*</font><br>
					<span style="color:red;" id="err_COUNTRY"></span></td>
				</tr>
				<tr>
					<td class="formLable">Pincode/postal code:</td>
					<td><input type="text" name="ADDPIN" id="ADDPIN" maxlength="6" value="<%=ADDPIN%>"  onblur="pincheck(this)">
					<br><span style="color:red;" id="err_PIN" ></span>
					</td>
				</tr>
				
				<tr>
					<td class="formLable">Phone No:</td>
					<td><input type="text" name="ADDPHONENO" id="ADDPHONENO" maxlength="" value="<%=ADDPHONENO%>" onblur="phonenumber(this)"/> 
					<font color="red">*</font>
					<br><span style="color:red;" id="err_PHONENO" ></span>
					</td>
				</tr>
				<tr>
	
	<tr>
	<td colspan="2" align="center"> 
	<p><%=request.getAttribute("message") %></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="2">
	 <button type="button" onclick="saveVENDOR_SUPPLIER()" class="button"> SAVE</button>
	 <button type="reset" class="button" onclick="VendorResetErrors();"> RESET</button>	
	<button type="button" onclick="exitVendorDetails()" name="exit" value="EXIT" class="button"> EXIT</button>
	</td>
	</tr>
	</table></td></tr>
		</table></td></tr>
	<tr align="center"><td><img class="headerImg" src="img/footer.jpg"></td></tr>
</table>
</form>
  </body>
</html>
