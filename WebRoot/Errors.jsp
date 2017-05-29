<html>
<head>
<title>Error Page</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
	function exit()
	{
		document.getElementById("exitForm").action = "/TeraStoresPurchase";
		document.getElementById("exitForm").submit();
	}
	
</script>
</head>
<body>
<form name="exitForm" id="exitForm">
</form>

<table class="mainTable" >
<tr><td valign="top" style="height: 60px">&nbsp;<img class="headerImg" src="img/StoresHeader.jpg"></td></tr>
<tr><td valign="top" align="center" style="height: 60px">Errors Page</td></tr>
<tr><td valign="top" align="center" style="height: 60px">
<table>
<tr><td >S No</td><td>Error Description</td></tr>
<%org.ActionErrors errors = (org.ActionErrors)request.getAttribute("errors");   
	java.util.Vector v = errors.getErrors();
	 for ( int i = 0 ; i < v.size () ; i++ )
	 {
	    org.ActionError error = (org.ActionError)v.get(i) ;
	   %>
                <tr><td align=center><%out.println(i+1);%></td>      
                            <td><%out.println(error.getErrorDesc());%></td>	</tr>
                         <%
				 }
					%>
</table>
</td></tr>
<tr><td align="center"><button onclick="exit()">Exit</button></td></tr>
<tr><td valign="bottom"><img class="headerImg" src="img/footer.jpg"></td></tr>
</table>


</body>
</html>