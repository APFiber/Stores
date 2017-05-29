<%@page import="bean.PO_HEADER"%>
<%@page import="bean.VENDOR_SUPPLIER"%>
<%@page import="bean.PO_CommonData"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="calendar.css"></link>

<title>Add PO Header Details</title>
	<%PO_HEADER po_header =(PO_HEADER)request.getAttribute("po_header");
	String ADD_PONO=po_header.getPONO();  				   				
 	 		if(ADD_PONO==null) ADD_PONO="";
 	String PODATE=po_header.getPODATE();  				   				
 	 		if(PODATE==null) PODATE="";
 	String POSUPPLIERNAME=po_header.getPOSUPPLIERNAME();
 			if(POSUPPLIERNAME==null) POSUPPLIERNAME="";
 	String POSUPPLIERADDRESSLINE1=po_header.getPOSUPPLIERADDRESSLINE1();
 			if(POSUPPLIERADDRESSLINE1==null) POSUPPLIERADDRESSLINE1="";
 	String POSUPPLIERADDRESSLINE2=po_header.getPOSUPPLIERADDRESSLINE2();
 			if(POSUPPLIERADDRESSLINE2==null) POSUPPLIERADDRESSLINE2="";
 	String POSUPPLIERADDRESSLINE3=po_header.getPOSUPPLIERADDRESSLINE3();
 			if(POSUPPLIERADDRESSLINE3==null) POSUPPLIERADDRESSLINE3="";
 	String POSUPPLIERADDRESSLINE4=po_header.getPOSUPPLIERADDRESSLINE1();
 			if(POSUPPLIERADDRESSLINE4==null) POSUPPLIERADDRESSLINE4="";
 	String POSUPPLIERCITY=po_header.getPOSUPPLIERCITY();
 			if(POSUPPLIERCITY==null) POSUPPLIERCITY="";
 	String POSUPPLIERDISTRICT=po_header.getPOSUPPLIERDISTRICT();
 			if(POSUPPLIERDISTRICT==null) POSUPPLIERDISTRICT="";
 	String POSUPPLIERSTATE=po_header.getPOSUPPLIERSTATE();
 			if(POSUPPLIERSTATE==null) POSUPPLIERSTATE="";
 	String POSUPPLIERCOUNTRY=po_header.getPOSUPPLIERCOUNTRY();
 			if(POSUPPLIERCOUNTRY==null) POSUPPLIERCOUNTRY="";
 	long POSUPPLIERPIN=po_header.getPOSUPPLIERPIN();				   				
 	 	String POSUPPLIERPINX;  	POSUPPLIERPINX="";
 	 		if (POSUPPLIERPIN>0)POSUPPLIERPINX=""+POSUPPLIERPIN;
 	String POSUPPLIERCONTACTPERSON=po_header.getPOSUPPLIERCONTACTPERSON();
 			if(POSUPPLIERCONTACTPERSON==null) POSUPPLIERCONTACTPERSON="";
 	long POSUPPLIERCONTACTNO=po_header.getPOSUPPLIERCONTACTNO();				   				
 	 	String POSUPPLIERCONTACTNOX;  	POSUPPLIERCONTACTNOX="";
 	 		if (POSUPPLIERCONTACTNO>0)POSUPPLIERCONTACTNOX=""+POSUPPLIERCONTACTNO;
 	String POSUBJECT=po_header.getPOSUBJECT();
 			if(POSUBJECT==null) POSUBJECT="";
 	double POVATRATE=po_header.getPOVATRATE();				   				
 	 	String POVATRATEX;  	POVATRATEX="";
 	 		if (POVATRATE>0)POVATRATEX=""+POVATRATE;
 	double POCSTRATE=po_header.getPOCSTRATE();				   				
 	 	String POCSTRATEX;  	POCSTRATEX="";
 	 		if (POCSTRATE>0)POCSTRATEX=""+POCSTRATE;
 	double POEXCISEDUTYRATE=po_header.getPOEXCISEDUTYRATE();				   				
 	 	String POEXCISEDUTYRATEX;  	POEXCISEDUTYRATEX="";
 	 		if (POEXCISEDUTYRATE>0)POEXCISEDUTYRATEX=""+POEXCISEDUTYRATE;
 	double POLABOURCHARGES=po_header.getPOLABOURCHARGES();				   				
 	 	String POLABOURCHARGESX;  	POLABOURCHARGESX="";
 	 		if (POLABOURCHARGES>0)POLABOURCHARGESX=""+POLABOURCHARGES;
 	double POFREIGHTAMT=po_header.getPOFREIGHTAMT();				   				
 	 	String POFREIGHTAMTX;  	POFREIGHTAMTX="";
 	 		if (POFREIGHTAMT>0)POFREIGHTAMTX=""+POFREIGHTAMT;
 	double POSERVICETAX=po_header.getPOSERVICETAX();				   				
 	 	String POSERVICETAXX;  	POSERVICETAXX="";
 	 		if (POSERVICETAX>0)POSERVICETAXX=""+POSERVICETAX;
 	long QUOTATIONNO=po_header.getQUOTATIONNO();				   				
 	 	String QUOTATIONNOX;  	QUOTATIONNOX="";
 	 		if (QUOTATIONNO>0)QUOTATIONNOX=""+QUOTATIONNO;
 	String QUOTATIONDATE=po_header.getQUOTATIONDATE();
 			if(QUOTATIONDATE==null) QUOTATIONDATE="";
 	String DOCREFNO=po_header.getDOCREFNO();
 			if(DOCREFNO==null) DOCREFNO="";
 	String DOCREFDATE=po_header.getDOCREFDATE();
 			if(DOCREFDATE==null) DOCREFDATE="";
 	String ACKNOWNO=po_header.getACKNOWNO();
 			if(ACKNOWNO==null) ACKNOWNO="";
 	String ACKNOWDATE=po_header.getACKNOWDATE();
 			if(ACKNOWDATE==null) ACKNOWDATE="";
 	long PAYADVNO=po_header.getPAYADVNO();				   				
 	 	String PAYADVNOX;  	PAYADVNOX="";
 	 		if (PAYADVNO>0)PAYADVNOX=""+PAYADVNO;
 	String PAYADVDATE=po_header.getPAYADVDATE();
 			if(PAYADVDATE==null) PAYADVDATE="";
 	long CHEQUENO=po_header.getCHEQUENO();				   				
 	 	String CHEQUENOX;  	CHEQUENOX="";
 	 		if (CHEQUENO>0)CHEQUENOX=""+CHEQUENO;
 	String CHEQUEDATE=po_header.getCHEQUEDATE();
 			if(CHEQUEDATE==null) CHEQUEDATE="";
 	String BANKCODE=po_header.getBANKCODE();
 			if(BANKCODE==null) BANKCODE="";
 	String BANKNAME=po_header.getBANKNAME();
 			if(BANKNAME==null) BANKNAME="";
 	double ADVAMOUNT=po_header.getADVAMOUNT();				   				
 	 	String ADVAMOUNTX;  	ADVAMOUNTX="";
 	 		if (ADVAMOUNT>0)ADVAMOUNTX=""+ADVAMOUNT;
 	double BALAMOUNT=po_header.getBALAMOUNT();				   				
 	 	String BALAMOUNTX;  	BALAMOUNTX="";
 	 		if (BALAMOUNT>0)BALAMOUNTX=""+BALAMOUNT;
 	 String POREMARKS=po_header.getPOREMARKS();
 			if(POREMARKS==null) POREMARKS="";
 	double TOTALPOCOST=po_header.getTOTALPOCOST();				   				
 	 	String TOTALPOCOSTX;  	TOTALPOCOSTX="";
 	 		if (TOTALPOCOST>0)TOTALPOCOSTX=""+TOTALPOCOST;
	%> 
	
<script type="text/javascript" src="calendar.js"></script>
<script type="text/javascript">
		
//Function for saving the purchase order header details 
		function poSave()
		{
			var man=mandatory_po_check();
			if(man)
			{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=3';
	  		document.forms[0].submit();
	  		}
		}
		
//Function for to go to grid page
		function poExit()
		{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=1';
	  		document.forms[0].submit();
		}
		
//Function for displaying PO Terms and Conditions grid page
		function addPoTC()
		{
			var pono=document.getElementById('ADD_PONO').value; 
			if(pono==null || pono=="")
			{
				alert('enter PO number to add po item and terms');
			}
			else
			{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=19&ADDPONO='+pono;
	  		document.forms[0].submit();
	  		}
		}
		
//Function for displaying PO Item details grid page
		function addPoItem()
		{
			var po=document.getElementById('ADD_PONO').value;
			if(po==null || po=="")
			{
				alert('enter PO number to add po item details');
			}
			else
			{
      		document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=18&ADDPONO='+po;
	  		document.forms[0].submit();
	  		}
		}
	
//Function for get the supplier details using supplier code 
		function getAllSuppDetails(supplierCode)
		{	
			var supplierCode=document.getElementById("supplier_Code").value;
			document.forms[0].action='PURCHASE_ORDER_CONTROLLER?Option=10&SUPPCODE='+supplierCode;
			document.getElementById("add_po").submit();
		}
		
//Function for validating P.O number field
		function poCheck(pono)
		{
			document.getElementById("err_PONO").innerHTML = "";
			if (pono.value.length > 0) 
			{
				var regex1=/^[0-9A-Za-z\s]+$/;    //this is the pattern of regular expersion
				if (regex1.test(pono.value) == false)
				{
					document.getElementById("err_PONO").innerHTML = "PO number should be in numeric only";
					document.po.ADD_PONO.value="";
				}
			}
			else  document.getElementById("err_PONO").innerHTML = "PO Number field should not be empty";
		}
		
//Function for checking P.O date field
		function podate_check()
		{
     		document.getElementById("err_date_check").innerHTML="";
	 		if(po.PODATE.value.length==0)
        	{   
	       	 document.getElementById("err_date_check").innerHTML="Select P.O date";   
        	}
		}

//Function for checking Supplier name field
		function suppName_select()
		{
		     document.getElementById("err_Suppname").innerHTML="";
		     if(po.supplier_Code.value.length==0)
		     {
			    document.getElementById("err_Suppname").innerHTML="Select supplier name";
	         }
        }
     
//Contact person checking field
		function contactPersonCheck(cp) 
		{
			document.getElementById("err_cp").innerHTML = "";
			if (cp.value.length > 0) 
			{
				var reg = /^[A-Za-z\s]+$/;
				if (reg.test(cp.value) == false) 
				{
					document.getElementById("err_cp").innerHTML = "Enter contact person name in alphabets";
					document.POSUPPLIERCONTACTPERSON.value="";
				}
			}
			else document.getElementById("err_cp").innerHTML = "Enter contact person name";
		}
		
//Function for validating Phone number field
		function phonenumber(inputtxt)
	 	{
		document.getElementById("err_phoneno").innerHTML = "";
		if (inputtxt.value.length > 0) 
		{
			var reg = /^[0-9]+$/;
			if (reg.test(inputtxt.value) == false) {
				document.getElementById("err_phoneno").innerHTML = "Enter valid Mobile Number";
				document.POSUPPLIERCONTACTNO.value="";
			}
		}
			else  document.getElementById("err_phoneno").innerHTML = "Enter phone number";
		}
		
//Function for validating quotation Number field  
		function quotationNOCheck(inputtxt)
	 	{
		document.getElementById("err_quoteNo").innerHTML = "";
		if (inputtxt.value.length > 0) 
		{
			var reg = /^[0-9]+$/;
			if (reg.test(inputtxt.value) == false) {
				document.getElementById("err_quoteNo").innerHTML = "Enter valid Quotation Number";
				document.QUOTATIONNO.value="";
			}
		}
		}
		
		//Subject checking field
		function SubjectCheck(subject) 
		{
			document.getElementById("err_sub").innerHTML = "";
			if (subject.value.length > 0)
			 {
				var reg = /^[A-Za-z\s0-9]+$/;
				if (reg.test(subject.value) == false)
				 {
					document.getElementById("err_sub").innerHTML = "Enter Subject in allowable characters";
					document.POSUBJECT.value="";
				}
			}
			else  document.getElementById("err_sub").innerHTML = "Enter Subject";
		}
		
		
//Function for validating V.A.T field
function vatCheck(vat)
{
		document.getElementById("err_vat").innerHTML = "";
		var cst =document.getElementById("POCSTRATE").value;
		if (vat.value.length > 0) 
		{
			if(cst.length > 0)
			{
				document.getElementById("err_cst").innerHTML = "Either VAT or CST must be entered";
				po.POVATRATE.value="";
				po.POCSTRATE.value="";
			}
			else
			{
				document.getElementById("err_cst").innerHTML = "";
				var regex1=/^((0|[1-9]\d?)(\.\d{1,2})?|100(\.00?)?)$/;//this is the pattern of regular expersion
				if (regex1.test(vat.value) == false)
				{
					document.getElementById("err_vat").innerHTML = "VAT% should be in numeric and valid only";
					po.POVATRATE.value="";
				}
			}
		}
		
}


		//Function for validating C.S.T field
		function cstCheck(cst)
		{ 
				document.getElementById("err_cst").innerHTML = "";
				var vat =document.getElementById("POVATRATE").value;
				if (cst.value.length > 0) 
				{
				if(vat.length > 0)
				{
					document.getElementById("err_vat").innerHTML = "Either VAT or CST must be entered";
					po.POVATRATE.value="";
					po.POCSTRATE.value="";
				}
				else
				{
					document.getElementById("err_vat").innerHTML = "";
					var regex1=/^((0|[1-9]\d?)(\.\d{1,2})?|100(\.00?)?)$/;     //this is the pattern of regular expersion
					if (regex1.test(cst.value) == false)
					{
						document.getElementById("err_cst").innerHTML = "CST% should be in numeric only";
						po.POCSTRATE.value="";
					}
				}
				}
				
		}
		
		//Function for validating excise duty field
		function excisedutyCheck(exciseduty)
		{        
			document.getElementById("err_exciseduty").innerHTML = "";
			if (exciseduty.value.length > 0) {
				var regex1=/^((0|[1-9]\d?)(\.\d{1,2})?|100(\.00?)?)$/;     //this is the pattern of regular expersion
				if (regex1.test(exciseduty.value) == false)
				{
                  document.getElementById("err_exciseduty").innerHTML = "Excise Duty% should be in numeric only";
                  po.POEXCISEDUTYRATE.value="";
				}
			}
			else document.getElementById("err_exciseduty").innerHTML = "Enter Excise Duty%";
		}
		
//Function for validating labour charges field
function labourchargesCheck(labourcharges)
{    
	document.getElementById("err_labourcharges").innerHTML = "";
	if (labourcharges.value.length > 0) 
	{
		var regex1=(/^\d{0,10}(?:\.\d{0,2}){0,2}$/);     //this is the pattern of regular expersion
		if (regex1.test(labourcharges.value) == false)
		{
            document.getElementById("err_labourcharges").innerHTML = "Labour Charges should be in numeric only";
            po.POLABOURCHARGES.value="";
		}
	}
	else document.getElementById("err_labourcharges").innerHTML = "Enter Labour Charges"; 
}


		//Function for validating freight amount field
		function freightamountCheck(freightamount)
		{     
			document.getElementById("err_freightamount").innerHTML = "";
			if (freightamount.value.length > 0)
			 {
				var regex1=(/^\d{0,10}(?:\.\d{0,2}){0,2}$/);      //this is the pattern of regular expersion
				if (regex1.test(freightamount.value) == false)
				{
                   document.getElementById("err_freightamount").innerHTML = "Freight Amount should be in numeric only";
                   po.POFREIGHTAMT.value="";
				}
			}
			else document.getElementById("err_freightamount").innerHTML = "Enter Freight Amount";
		}
		
		//Service Tax amount checking field
		function serviceTaxCheck(servicetax)
		{     
			document.getElementById("err_servicetax").innerHTML = "";
			if (servicetax.value.length > 0)
			 {
				var regex1=/^((0|[1-9]\d?)(\.\d{1,2})?|100(\.00?)?)$/;      //this is the pattern of regular expersion
				if (regex1.test(servicetax.value) == false)
				{
                   document.getElementById("err_servicetax").innerHTML = "Service Tax Amount should be in numeric only";
                   po.POSERVICETAX.value="";
				}
			}
		}
		
		//Function for validating remarks field
		function RemarksCheck(remarks) 
		{
			document.getElementById("err_remarks_check").innerHTML = "";
			if (remarks.value.length > 0) 
			{
				var reg = /^[A-Za-z\s0-9]+$/;
				if (reg.test(remarks.value) == false) 
				{
					document.getElementById("err_remarks_check").innerHTML = "Enter remarks in alphanumeric";
					po.PO_REMARKS.value="";
				}
			}
		}
		
		//Function for validating Document reference number field
		function DocRefNoCheck(docrefno) 
		{
			document.getElementById("err_DOCREFNO").innerHTML = "";
			if (docrefno.value.length > 0) 
			{
				var reg = /^[A-Za-z\s0-9]+$/;
				if (reg.test(docrefno.value) == false) 
				{
					document.getElementById("err_DOCREFNO").innerHTML = "Enter Dcoument reference No in alphanumeric";
					po.DOCREFNO.value="";
				}
			}
		}
		
		//Function for validating Acknowledgement number field
		function AckNoCheck(ackno) 
		{
			document.getElementById("err_ACKNOWNO").innerHTML = "";
			if (ackno.value.length > 0) 
			{
				var reg = /^[A-Za-z\s0-9]+$/;
				if (reg.test(ackno.value) == false) 
				{
					document.getElementById("err_ACKNOWNO").innerHTML = "Enter Acknowledge number in alphanumeric";
					po.ACKNOWNO.value="";
				}
			}
		}
		
		//Function for validating Payment advance number field
		function payAdvNoCheck(payadvno) 
		{
			document.getElementById("err_PAYADVNO").innerHTML = "";
			if (payadvno.value.length > 0) 
			{
				var reg = /^[0-9]+$/;
				if (reg.test(payadvno.value) == false) 
				{
					document.getElementById("err_PAYADVNO").innerHTML = "Enter Payment Advance number in alphanumeric";
					po.PAYADVNO.value="";
				}
			}
		}
		
		//Function for validating cheque number field
		function chequeNoCheck(chequeno) 
		{
			document.getElementById("err_CHEQUENO").innerHTML = "";
			if (chequeno.value.length > 0) 
			{
				var reg = /^[0-9]+$/;
				if (reg.test(chequeno.value) == false) 
				{
					document.getElementById("err_CHEQUENO").innerHTML = "Enter Cheque number in alphanumeric";
					po.CHEQUENO.value="";
				}
			}
		}
		
		//Function for validating bank code field
		function BankCodeCheck(bankcode) 
		{
			document.getElementById("err_BANKCODE").innerHTML = "";
			if (bankcode.value.length > 0) 
			{
				var reg = /^[A-Za-z\s0-9]+$/;
				if (reg.test(bankcode.value) == false) 
				{
					document.getElementById("err_BANKCODE").innerHTML = "Enter Bank Code in alphanumeric";
					po.BANKCODE.value="";
				}
			}
		}
	
//Function for validating bank name field	
function BankNameCheck(bankname) 
{
	document.getElementById("err_BANKNAME").innerHTML = "";
	if (bankname.value.length > 0) 
	{
		var reg = /^[A-Za-z\s0-9]+$/;
		if (reg.test(bankname.value) == false) 
		{
			document.getElementById("err_BANKNAME").innerHTML = "Enter Bank Name in alphanumeric";
			po.BANKNAME.value="";
		}
	}
}

//Function for validating advance amount field
function advAmtCheck(advamt)
{    
	document.getElementById("err_ADVAMOUNT").innerHTML = "";
	if (advamt.value.length > 0) 
	{
		var regex1=(/^\d{0,10}(?:\.\d{0,2}){0,2}$/);//this is the pattern of regular expersion
		if (regex1.test(advamt.value) == false)
		{
            document.getElementById("err_ADVAMOUNT").innerHTML = "Advance amount should be in numeric only";
            po.ADVAMOUNT.value="";
		}
	}
}
	
//Function for validating balance amount field	
function balAmtCheck(balamt)
{    
	document.getElementById("err_BALAMOUNT").innerHTML = "";
	if (balamt.value.length > 0) 
	{
		var regex1=(/^\d{0,10}(?:\.\d{0,2}){0,2}$/);//this is the pattern of regular expersion
		if (regex1.test(balamt.value) == false)
		{
            document.getElementById("err_BALAMOUNT").innerHTML = "Balance amount should be in numeric only";
            po.BALAMOUNT.value="";
		}
	}
}

	//clears all fields in purchase order
	function POResetErrors()
	{
		document.getElementById("err_PONO").innerHTML="";
		document.getElementById("err_Suppname").innerHTML="";
		document.getElementById("err_date_check").innerHTML="";
		document.getElementById("err_cp").innerHTML="";
		document.getElementById("err_phoneno").innerHTML="";
		document.getElementById("err_sub").innerHTML="";
		document.getElementById("err_vat").innerHTML = "";
		document.getElementById("err_cst").innerHTML = "";
		document.getElementById("err_exciseduty").innerHTML = "";
		document.getElementById("err_labourcharges").innerHTML = "";
		document.getElementById("err_freightamount").innerHTML = "";
		document.getElementById("err_servicetax").innerHTML = "";
		document.getElementById("err_remarks_check").innerHTML="";
		document.getElementById("err_quoteNo").innerHTML="";
		document.getElementById("err_DOCREFNO").innerHTML="";
		document.getElementById("err_ACKNOWNO").innerHTML="";
		document.getElementById("err_PAYADVNO").innerHTML="";
		document.getElementById("err_BANKCODE").innerHTML="";
		document.getElementById("err_CHEQUENO").innerHTML="";
		document.getElementById("err_ADVAMOUNT").innerHTML="";
		document.getElementById("err_BALAMOUNT").innerHTML="";
		document.getElementById("err_QUOTATIONDATE").innerHTML="";
		document.getElementById("err_DOCREFDATE").innerHTML="";
		document.getElementById("err_ACKNOWDATE").innerHTML="";
		document.getElementById("err_PAYADVDATE").innerHTML="";
		document.getElementById("err_CHEQUEDATE").innerHTML="";
	}
	
//Function for validating quotation date field
	function quoteDateCheck()
	{
		document.getElementById("err_QUOTATIONDATE").innerHTML = "";
		if (po.QUOTATIONDATE.value.length > 0)
		{
		if (po.QUOTATIONDATE.value.length != 10)
		{
			document.getElementById("err_QUOTATIONDATE").innerHTML = "Date Should be DD-MM-YYYY";
		} 
		else 
		{
			var ll = position(po.QUOTATIONDATE.value);
			var ll1 = position1(po.QUOTATIONDATE.value);
			if (!(ll == "-" && ll1 == "-"))
			{
				document.getElementById("err_QUOTATIONDATE").innerHTML = "Date Should be DD-MM-YYYY";
			}
			else
			{
				var bval = Comparedates(po.QUOTATIONDATE.value,Getcurrentdate());
				if (bval == false) 
				{
					document.getElementById("err_QUOTATIONDATE").innerHTML = "Date Should not be Future date";
				} 
			}
		}
		}
	}
	
//Function for validating document reference date field
	function DocRefDateCheck()
	{
		document.getElementById("err_DOCREFDATE").innerHTML = "";
		if (po.DOCREFDATE.value.length > 0)
		{
		if (po.DOCREFDATE.value.length != 10)
		{
			document.getElementById("err_DOCREFDATE").innerHTML = "Date Should be DD-MM-YYYY";
		} 
		else 
		{
			var ll = position(po.DOCREFDATE.value);
			var ll1 = position1(po.DOCREFDATE.value);
			if (!(ll == "-" && ll1 == "-"))
			{
				document.getElementById("err_DOCREFDATE").innerHTML = "Date Should be DD-MM-YYYY";
			}
			else
			{
				var bval = Comparedates(po.DOCREFDATE.value,Getcurrentdate());
				if (bval == false) 
				{
					document.getElementById("err_DOCREFDATE").innerHTML = "Date Should not be Future date";
				} 
			}
		}
		}
	}
	
//Function for validating Acknowledgement date field
	function AcknoDateCheck()
	{
		document.getElementById("err_ACKNOWDATE").innerHTML = "";
		if (po.ACKNOWDATE.value.length > 0)
		{
		if (po.ACKNOWDATE.value.length != 10)
		{
			document.getElementById("err_ACKNOWDATE").innerHTML = "Date Should be DD-MM-YYYY";
		} 
		else 
		{
			var ll = position(po.ACKNOWDATE.value);
			var ll1 = position1(po.ACKNOWDATE.value);
			if (!(ll == "-" && ll1 == "-"))
			{
				document.getElementById("err_ACKNOWDATE").innerHTML = "Date Should be DD-MM-YYYY";
			}
			else
			{
				var bval = Comparedates(po.ACKNOWDATE.value,Getcurrentdate());
				if (bval == false) 
				{
					document.getElementById("err_ACKNOWDATE").innerHTML = "Date Should not be Future date";
				} 
			}
		}
		}
	}
	
//Function for validating payment advance field
	function PayAdvDateCheck()
	{
		document.getElementById("err_PAYADVDATE").innerHTML = "";
		if (po.PAYADVDATE.value.length > 0)
		{
		if (po.PAYADVDATE.value.length != 10)
		{
			document.getElementById("err_PAYADVDATE").innerHTML = "Date Should be DD-MM-YYYY";
		} 
		else 
		{
			var ll = position(po.PAYADVDATE.value);
			var ll1 = position1(po.PAYADVDATE.value);
			if (!(ll == "-" && ll1 == "-"))
			{
				document.getElementById("err_PAYADVDATE").innerHTML = "Date Should be DD-MM-YYYY";
			}
			else
			{
				var bval = Comparedates(po.PAYADVDATE.value,Getcurrentdate());
				if (bval == false) 
				{
					document.getElementById("err_PAYADVDATE").innerHTML = "Date Should not be Future date";
				} 
			}
		}
		}
	}

//Function for validating cheque date field	
	function ChequeDateCheck()
	{
		document.getElementById("err_CHEQUEDATE").innerHTML = "";
		if (po.CHEQUEDATE.value.length > 0)
		{
		if (po.CHEQUEDATE.value.length != 10)
		{
			document.getElementById("err_CHEQUEDATE").innerHTML = "Date Should be DD-MM-YYYY";
		} 
		else 
		{
			var ll = position(po.CHEQUEDATE.value);
			var ll1 = position1(po.CHEQUEDATE.value);
			if (!(ll == "-" && ll1 == "-"))
			{
				document.getElementById("err_CHEQUEDATE").innerHTML = "Date Should be DD-MM-YYYY";
			}
			else
			{
				var bval = Comparedates(po.CHEQUEDATE.value,Getcurrentdate());
				if (bval == false) 
				{
					document.getElementById("err_CHEQUEDATE").innerHTML = "Date Should not be Future date";
				} 
			}
		}
		}
	}
	
//Function for validating mandatory field
	function mandatory_po_check()
	{
	var status;
    status=true;
    POResetErrors();	
	   if(po.ADD_PONO.value.length==0)
        {   
	       document.getElementById("err_PONO").innerHTML="Enter purchase order number";
	       status=false;
        }
	
	    //P.O raised date
		if (po.PODATE.value.length == 0) {
				document.getElementById("err_date_check").innerHTML = "Select P.O raised date ";
				status = false;
			} else {
				if (po.PODATE.value.length != 10) {
					document.getElementById("err_date_check").innerHTML = "Date Should be DD-MM-YYYY";
					status = false;
				} else {
					var ll = position(po.PODATE.value);
					var ll1 = position1(po.PODATE.value);
					if (!(ll == "-" && ll1 == "-")) {
						document.getElementById("err_date_check").innerHTML = "Date Should be DD-MM-YYYY";
					} else {
						var bval = Comparedates(po.PODATE.value,
								Getcurrentdate());
						if (bval == false) {
							document.getElementById("err_date_check").innerHTML = "Date Should not be Future date";
							status = false;
						} else {
							datecom = 1;
						}
					}
				}
			}
			if (po.supplier_Code.value.length == 0) {
				document.getElementById("err_Suppname").innerHTML = "Select supplier name from list";
				status = false;
			}
			if (po.POSUBJECT.value.length == 0) {
				document.getElementById("err_sub").innerHTML = "Enter Subject";
				status = false;
			}
			if (po.POCSTRATE.value.length == 0  ) 
			{
				if (po.POVATRATE.value.length == 0) 
				{
					document.getElementById("err_cst").innerHTML = "Enter CST%";
					status = false;
				}
			}
			if (po.POVATRATE.value.length == 0 ) 
			{
				if (po.POCSTRATE.value.length == 0) 
				{
					document.getElementById("err_vat").innerHTML = "Enter VAT%";
					status = false;
				}
			}
			if (po.POSUPPLIERCONTACTNO.value.length == 0) {
				document.getElementById("err_phoneno").innerHTML = "Enter Contact Number";
				status = false;
			}
			if (po.POSUPPLIERCONTACTPERSON.value.length == 0) {
				document.getElementById("err_cp").innerHTML = "Enter Contact Person name";
				status = false;
			}
			
			if (po.POEXCISEDUTYRATE.value.length == 0) {
				document.getElementById("err_exciseduty").innerHTML = "Enter excise duty";
				status = false;
			}
			if (po.POLABOURCHARGES.value.length == 0) {
				document.getElementById("err_labourcharges").innerHTML = "Enter labour charges";
				status = false;
			}
			if (po.POFREIGHTAMT.value.length == 0) {
				document.getElementById("err_freightamount").innerHTML = "Enter freight amount";
				status = false;
			}
			return status;
		}
		
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
		
//Function for changes the PONO field to read only 
		function editFormFucntion() 
		{
			var option = getParameterByName("Option");
			if (option != 2)
			{
				document.getElementById('ADD_PONO').readOnly = true;
				document.getElementById('reset').type = "hidden";
			}
		}
		
</script>
</head>
<body onload="editFormFucntion()">
<%PO_CommonData commonData = (PO_CommonData) request.getAttribute("po_commondata");%>
	 <form action="PURCHASE_ORDER_CONTROLLER" method="post" name="po" id="add_po">
	 <input type="hidden" name="USERNAME" value="<%=commonData.getUSER_NAME()%>" >
	 <input type="hidden" name="pageNo" value="<%=commonData.getPAGE_NO()%>">
  	 <input type="hidden" name="DATE" id="DATE" value="<%=commonData.getLOGIN_DATE()%>">
  	 <input type="hidden" name="USERROLE" value="<%=commonData.getUSER_ROLE()%>">
  	 <input type="hidden" name="LOGINID" id="LOGINID" value="<%=commonData.getLOGIN_ID()%>">
  	 <input type="hidden" name="PONO" value="<%=commonData.getPO_NO()%>">
  	 <input type="hidden" name="POSUPPLIERNAME" value="<%=commonData.getPO_SUPPLIER_NAME()%>">
  	 <input type="hidden" name="POSRNO" value="<%=commonData.getSelected_Serial_No()%>">
  	 <input type="hidden" id="PO_SUPPLIER_CODE" name="PO_SUPPLIER_CODE" value="<%=po_header.getPOSUPPLIERCODE()%>">
	 <table  class="mainTable">
			<tr align="center"><td align="center"><jsp:include page="header1.jsp"></jsp:include></td></tr>
			<tr><td> <table width="100%" border="0" cellspacing="0" cellpadding="0" class="">
			<tr><td> <table align="center">
		    	 	<tr align="center">
		    	 	<td align="left"><b><font size="4" color="blue">Name of the User :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getUSER_NAME() %></font></b></td>
		    	 	<td align="left"><b><font size="4" color="blue">Date :</font></b></td>
		    	 	<td align="left"><b><font size="3" color="Black"><%=commonData.getLOGIN_DATE() %></font></b></td>
		    	 	</tr>
		    		</table></td></tr>
			<tr><td> <table class="pochildTable" align="center">
				<tr><td colspan="8"><h2 align="center">Purchase order</h2></td></tr>
	
	<tr>
	<td colspan="1">PO Number:</td>
	<td class="formLable" colspan="4">
	<input type="text" name="ADD_PONO" id="ADD_PONO"  value="<%=ADD_PONO%>" maxlength="30" onblur="poCheck(this);" style="width:90%" title="Purchase Order number"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_PONO"> </span></td>
	
	<td colspan="1">PO Date:</td>
	<td colspan="2" class="formLable"><input type="text" name="PODATE" id="PODATE"  value="<%=PODATE%>" maxlength="10" onblur="podate_check();"/>
	<font color="#ff0000">*</font>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'PODATE');" value="PODATE" onblur="podate_check();" > 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_date_check"></span></td>
	</tr>
	
	<tr>
			<td colspan="1">Supplier Name:
			<td colspan="7" style="width: 75%">
			<select name="supplier_Code" id="supplier_Code" onchange="getAllSuppDetails(this.value);" onblur="suppName_select()" style="width: 45%">
			<% if(POSUPPLIERNAME==null|| POSUPPLIERNAME=="") 
			{
			%>
			<option value="" selected>- - Select - - </option>
						<%
	 						}else{
	  					%>
							<option value="<%=POSUPPLIERNAME%>" selected><%=POSUPPLIERNAME%></option>
						<%
	 						}
	  					%>
			<%List<VENDOR_SUPPLIER> suppList =  (List<VENDOR_SUPPLIER>)request.getAttribute("suppList"); %>	
			<%
				for(VENDOR_SUPPLIER SUPPLIER_DETAILS : suppList){
   					%>
   					<option value="<%=SUPPLIER_DETAILS.getVSSRNO()%>"><%=SUPPLIER_DETAILS.getNAME()%></option> 
   					<%
   					}
   					%>
   					
			</select> <font color="#ff0000">*</font>
			<br><span style="color:red;" id="err_Suppname"></span>
			</td>
			</tr>
	</table><tr><td>
<%--	<span class="searchCriteriaLabel">Supplier Details</span>--%>
	<table class="pochildTable" align="center" style="background-color: #c7fdf4">	
	<tr ><td colspan="8" align="center"><h4>Supplier Details</h4></td></tr>
	<tr>
	<td colspan="1">Address Line1:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERADDRESSLINE1" id="POSUPPLIERADDRESSLINE1"  value="<%=POSUPPLIERADDRESSLINE1%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td>
	
	<td colspan="1">Address Line2:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERADDRESSLINE2" id="POSUPPLIERADDRESSLINE2"  value="<%=POSUPPLIERADDRESSLINE2%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td></tr>   
	
	<tr>
	<td colspan="1">Address Line3:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERADDRESSLINE3" id="POSUPPLIERADDRESSLINE3"  value="<%=POSUPPLIERADDRESSLINE3%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td>
	
	<td colspan="1">Address Line4:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERADDRESSLINE4" id="POSUPPLIERADDRESSLINE4" value="<%=POSUPPLIERADDRESSLINE4%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td></tr>
	
	<tr>
	<td colspan="1">City</td>
	<td colspan="3"><input type="text" name="POSUPPLIERCITY" id="POSUPPLIERCITY"  value="<%=POSUPPLIERCITY%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td>
	
	<td colspan="1">District:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERDISTRICT" id="POSUPPLIERDISTRICT"  value="<%=POSUPPLIERDISTRICT%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td></tr>
	
	<tr>
	<td colspan="1">State</td>
	<td colspan="3"><input type="text" name="POSUPPLIERSTATE" id="POSUPPLIERSTATE"  value="<%=POSUPPLIERSTATE%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td>
	
	<td colspan="1">Country:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERCOUNTRY" id="POSUPPLIERCOUNTRY"  value="<%=POSUPPLIERCOUNTRY%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td></tr>
	
	<tr>
	<td colspan="1">PIN:</td>
	<td colspan="3"><input type="text" name="POSUPPLIERPIN" id="POSUPPLIERPIN"  value="<%=POSUPPLIERPINX%>" readonly="readonly" style="background-color:#e6e6e6;"/>
	</td></tr></table>
	
	<tr><td><table align="center" class="pochildTable">

	<tr>
	<td colspan="2">Contact person:</td>
	<td colspan="4"><input type="text" style="width: 85%" name="POSUPPLIERCONTACTPERSON" id="POSUPPLIERCONTACTPERSON"  value="<%=POSUPPLIERCONTACTPERSON%>" maxlength="25"  onblur="contactPersonCheck(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_cp" > </span></td>
	
	<td colspan="2">Contact Number:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="POSUPPLIERCONTACTNO" id="POSUPPLIERCONTACTNO"  value="<%=POSUPPLIERCONTACTNOX%>"  maxlength="12" onblur="phonenumber(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_phoneno"> </span></td>
	</tr>
	
	<tr>
	<td colspan="2">Subject:</td>
	<td colspan="10"><input type="text" name="POSUBJECT" id="POSUBJECT"  value="<%=POSUBJECT%>" maxlength="150" style="width:92%" onblur="SubjectCheck(this);"/>
	<font color="#ff0000">*</font>
	<br><span style="color:red;" id="err_sub"> </span></td>
	</tr>
	
	<tr>
	<td colspan="2">VAT Rate(%):</td>
	<td colspan="4"><input type="text" name="POVATRATE" id="POVATRATE"  value="<%=POVATRATEX%>"  maxlength="15" onblur="vatCheck(this);"/>
	<br><span style="color:red;" id="err_vat"> </span></td>
	
	<td colspan="2">CST Rate(%):</td>
	<td colspan="4"><input type="text" name="POCSTRATE" style="width: 75%" id="POCSTRATE"  value="<%=POCSTRATEX%>"  maxlength="12" onblur="cstCheck(this);"/>
	<br><span style="color:red;" id="err_cst" > </span>
	</td></tr>
	
	<tr>
	<td colspan="2">Excise Duty Rate(%):</td>
	<td colspan="4"><input type="text" name="POEXCISEDUTYRATE" id="POEXCISEDUTYRATE"  value="<%=POEXCISEDUTYRATEX%>" maxlength="12"  onblur="excisedutyCheck(this);"/>
	<br><span style="color:red;" id="err_exciseduty" > </span></td>
	
	<td colspan="2">Labour Charges(in RS):</td>
	<td colspan="4"><input type="text" style="width: 75%" name="POLABOURCHARGES" id="POLABOURCHARGES"  value="<%=POLABOURCHARGESX%>"  maxlength="13" onblur="labourchargesCheck(this);"/>
	<br><span style="color:red;" id="err_labourcharges"> </span></td>
	</tr>
	
	<tr>
	<td colspan="2">FREIGHT Amount(in RS):</td>
	<td colspan="4"><input type="text" name="POFREIGHTAMT" id="POFREIGHTAMT" value="<%=POFREIGHTAMTX%>" maxlength="13"  onblur="freightamountCheck(this);"/>
	<br><span style="color:red;" id="err_freightamount"> </span>
	</td>
	
	<td colspan="2">Service Tax(in %):</td>
	<td colspan="4"><input type="text" style="width: 75%" name="POSERVICETAX" id="POSERVICETAX" value="<%=POSERVICETAXX%>" maxlength="12" onblur="serviceTaxCheck(this);"/>
	<br><span style="color:red;" id="err_servicetax"> </span>
	</td>
	</tr>
	
	<tr>
	<td colspan="2">Quotation No :</td>
	<td colspan="4"><input type="text" name="QUOTATIONNO" id="QUOTATIONNO"  value="<%=QUOTATIONNOX%>"  maxlength="10" onblur="quotationNOCheck(this);"/>
	<br><span style="color:red;" id="err_quoteNo"> </span></td>
	
	<td colspan="2">Quotation Date:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="QUOTATIONDATE" id="QUOTATIONDATE"  value="<%=QUOTATIONDATE%>"  maxlength="10" onblur="quoteDateCheck(this);"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'QUOTATIONDATE');" value="QUOTATIONDATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_QUOTATIONDATE"> </span>
	</td>
	</tr>
	
	<tr>
	<td colspan="2">Document Reference No:</td>
	<td colspan="4"><input type="text" name="DOCREFNO" id="DOCREFNO"  value="<%=DOCREFNO%>"  maxlength="15" onblur="DocRefNoCheck(this);"/>
	<br><span style="color:red;" id="err_DOCREFNO"> </span></td>
	
	<td colspan="2">Document Ref Date:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="DOCREFDATE" id="DOCREFDATE"  value="<%=DOCREFDATE%>"  maxlength="10" onblur="docRefDateCheck(this);"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'DOCREFDATE');" value="DOCREFDATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_DOCREFDATE"> </span>
	</td>
	</tr>
	
	<tr>
	<td colspan="2">Acknowledgement No:</td>
	<td colspan="4"><input type="text" name="ACKNOWNO" id="ACKNOWNO"  value="<%=ACKNOWNO%>"  maxlength="15" onblur="AckNoCheck(this);"/>
	<br><span style="color:red;" id="err_ACKNOWNO"> </span></td>
	
	<td colspan="2">Acknowledgement Date:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="ACKNOWDATE" id="ACKNOWDATE"  value="<%=ACKNOWDATE%>"  maxlength="10" onblur="AcknoDateCheck(this);"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'ACKNOWDATE');" value="ACKNOWDATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_ACKNOWDATE"> </span>
	</td>
	</tr>
	
	<tr>
	<td colspan="2">Payment Advance No:</td>
	<td colspan="4"><input type="text" name="PAYADVNO" id="PAYADVNO"  value="<%=PAYADVNOX%>"  maxlength="10" onblur="payAdvNoCheck(this);"/>
	<br><span style="color:red;" id="err_PAYADVNO"> </span></td>
	
	<td colspan="2">Payment Advance Date:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="PAYADVDATE" id="PAYADVDATE"  value="<%=PAYADVDATE%>"  maxlength="10" onblur="PayAdvDateCheck(thsi);"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'PAYADVDATE');" value="PAYADVDATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_PAYADVDATE"> </span>
	</td>
	</tr>
	
	<tr>
	<td colspan="2">Cheque No:</td>
	<td colspan="4"><input type="text" name="CHEQUENO" id="CHEQUENO"  value="<%=CHEQUENOX%>"  maxlength="10" onblur="chequeNoCheck(this);"/>
	<br><span style="color:red;" id="err_CHEQUENO"> </span></td>
	
	<td colspan="2">Cheque Date:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="CHEQUEDATE" id="CHEQUEDATE"  value="<%=CHEQUEDATE%>"  maxlength="10" onblur="ChequeDateCheck(this);"/>
	<a href="#" onClick="setYears(1999, 2050); showCalender(this, 'CHEQUEDATE');" value="CHEQUEDATE"> 
	<img src="calender.png" width="29" height="16" style="width: 29px; height: 18px;"/> </a>
	<jsp:include page="Calender.jsp"></jsp:include>
	<br><span style="color:red;" id="err_CHEQUEDATE"> </span>
	</td>
	</tr>
	
	<tr>
	<td colspan="2">Bank Code:</td>
	<td colspan="4"><input type="text" name="BANKCODE" id="BANKCODE"  value="<%=BANKCODE%>" maxlength="10" onblur="BankCodeCheck(this);"/>
	<br><span style="color:red;" id="err_BANKCODE" > </span></td>
	
	<td colspan="2">Bank Name:</td>
	<td colspan="4"><input type="text" style="width: 75%" name="BANKNAME" id="BANKNAME"  value="<%=BANKNAME%>"  maxlength="50" onblur="BankNameCheck(this);"/>
	<br><span style="color:red;" id="err_BANKNAME"> </span></td>
	</tr>
	
	<tr>
	<td colspan="2">Advance Amount(in Rupess):</td>
	<td colspan="4"><input type="text" name="ADVAMOUNT" id="ADVAMOUNT"  value="<%=ADVAMOUNTX%>" maxlength="13" onblur="advAmtCheck(this);"/>
	<br><span style="color:red;" id="err_ADVAMOUNT" > </span></td>
	
	<td colspan="2">Balance Amount(in Rupees):</td>
	<td colspan="4"><input type="text" style="width: 75%" name="BALAMOUNT" id="BALAMOUNT"  value="<%=BALAMOUNTX%>"  maxlength="13" onblur="balAmtCheck(this);"/>
	<br><span style="color:red;" id="err_BALAMOUNT"> </span></td>
	</tr>
	
	<tr>
	<td colspan="2">Remarks:</td>
	<td colspan="10"><input type="text" name="POREMARKS" id="POREMARKS" value="<%=POREMARKS%>" style="width:92%" maxlength="300" onblur="RemarksCheck(this);"/>
	<span style="color:red;" id="err_remarks_check"> </span></td>
	</tr>
	
	<tr>
	<td colspan="2">Total PO COST</td>
	<td colspan="10"><input type="text" name="TOTALPOCOST" id="TOTALPOCOST" value="<%=TOTALPOCOSTX%>" style="width:65%;background-color:#e6e6e6;"  readonly="readonly"/>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="12"> 
	<p><%=request.getAttribute("message")%></p>
	</td>
	</tr>
	
	<tr>
	<td align="center" colspan="12">
	 <button type="button" onclick="return poSave();" class="button"> SAVE</button>
	 <button type="reset" class="button" onclick="POResetErrors();"> RESET</button>	
	 <button type="button" onclick="addPoItem();" class="button"> PO Items</button>
	 <button type="button" onclick="addPoTC();" class="button"> PO Terms and Conditions</button>
	 <button type="button" onclick="poExit();" name="exit" value="EXIT" class="button"> EXIT</button>
	</td>
	</tr>
	</table></td></tr>
	
	<tr align="center"><td><jsp:include page="footer.jsp"></jsp:include></td></tr>
</table>
</td></tr></table></form>
</body>
</html>