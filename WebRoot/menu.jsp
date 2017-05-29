<html>
<head>
<title>Login Page</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
	function logout(){
		document.getElementById("logoutForm").action = "TERA_STORES_PURCHASE?Option=2";
		document.getElementById("logoutForm").submit();
	}
	function itemLink(){
		document.getElementById("itemForm").action = "ITEM_CONTROLLER?Option=1";
		document.getElementById("itemForm").submit();
	}
	function majorMinorLink(){
		document.getElementById("itemForm").action = "MAJOR_MINOR_CONTROLLER?Option=1";
		document.getElementById("itemForm").submit();
	}
	function userLink(){
		document.getElementById("itemForm").action = "USER_CONTROLLER?Option=1";
		document.getElementById("itemForm").submit();
	}
	function POLink(){
		document.getElementById("itemForm").action = "PurchaseOrderController?Option=1";
		document.getElementById("itemForm").submit();	
	}
	function MRNLink(){
		document.getElementById("itemForm").action = "MRN_CONTROLLER?Option=1";
		document.getElementById("itemForm").submit();	
	}
	function vendorSupplierLink(){
		document.getElementById("itemForm").action = "VendorSupplierController?Option=1";
		document.getElementById("itemForm").submit();	
	}
	
	function resetLink(){
		document.getElementById("itemForm").action = "USER_CONTROLLER?Option=7";
		document.getElementById("itemForm").submit();
	}
	
	function changePasswordLink(){
		document.getElementById("itemForm").action = "USER_CONTROLLER?Option=8";
		document.getElementById("itemForm").submit();
	}
	function RTNLink(){
		document.getElementById("itemForm").action = "RTN_CONTROLLER?Option=1";
		document.getElementById("itemForm").submit();
	}
</script>
</head>
<body>

<form name="logoutForm" id="logoutForm"  method="POST">
</form>
<form name="itemForm" id="itemForm" method="POST" >
	<input type="hidden" name="USERNAME" id="USERNAME" value="<%=request.getAttribute("USERNAME")%>">
	<input type="hidden" name="DATE" id="DATE" value="<%=request.getAttribute("DATE") %>">
	<input type="hidden" name="USERROLE" id="USERROLE" value="<%=request.getAttribute("USERROLE") %>">
	<input type="hidden" name="LOGINID" id="LOGINID" value="<%=request.getAttribute("LOGINID")%>">
</form>

<table class="mainTable" style='background-image: url("img/stock.png");background-repeat: no-repeat; background-position: center center'>
<tr><td valign="top" style="height: 60px"><img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
<tr><td valign="top"  style="height: 60px">

<ul class="main-navigation">
   <li class="dropdown"><a href="#" class="dropbtn">Master</a>
    <ul class="dropdown-content">
      <li> <a onclick="majorMinorLink()">Major Minor</a></li>
      <li class="dropdown-menu"><a href="#">User<span class="menu-arrow"><i class="fa-chevron-right"></i></span></a>
        <ul class="dropdown" >
          <li class="dropdown-submenu"><a onclick="userLink()">User maintenance</a></li>
          <li><a onclick="resetLink()">Reset Password</a></li>
          <li><a onclick="changePasswordLink()">Change Password</a></li>
        </ul>
      </li>
      <li><a onclick="itemLink()">Item</a></li>
        <li><a onclick="vendorSupplierLink()">Supplier & Vendor</a></li>
    </ul>
  </li>
  
  <li class="dropdown"><a href="#" class="dropbtn">Transactions</a>
    <ul class="dropdown-content">
      <li> <a onclick="POLink()">Purchase order</a></li>
      <li><a onclick="">Gate pass</a></li>
      <li><a onclick="MRNLink()">MRN</a></li>
        <li><a onclick="RTNLink()">Return Note</a></li>
    </ul>
  </li>
    
   <li class="dropdown"><a href="#contact" class="dropbtn">Reports</a></li>
  <li class="dropdown"><a onclick="logout()" class="dropbtn">Logout</a></li>
  
  
</ul>
</td></tr>
<tr><td valign="top" align="right">Welcome: <%=request.getAttribute("USERNAME") %> , Date: <%=request.getAttribute("DATE") %></td></tr>
<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg"></td></tr>
</table>
</body>
</html>