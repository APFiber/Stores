package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import bean.PO_CommonData;
import bean.PO_DETAILS;
import bean.PO_HEADER;
import bean.PO_TC;
import bean.VENDOR_SUPPLIER;

public class PO_DB {

	KrrCommon krrCommon = new KrrCommon();
	Date_library Datelibrary=new Date_library();

	public String get_PO_HEADER(Connection con,List<PO_HEADER> POHEADERLIST,PO_CommonData POCommonData)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		String condition="";
		try
		{
			stmt = con.createStatement();
			sql="select * from PO_HEADER";
			if(krrCommon.isValue(POCommonData.getPO_NO())){
				condition = condition +" PONO LIKE"+krrCommon.AppendSinlequote(POCommonData.getPO_NO());
			}

			if(krrCommon.isValue(POCommonData.getPO_SUPPLIER_NAME()))
			{
				condition = condition +" and POSUPPLIERNAME LIKE "+krrCommon.AppendSinlequote(POCommonData.getPO_SUPPLIER_NAME());
			}
			if(condition !=""){
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and")){
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+" ";	
			}	
			sql=sql+" order by PONO ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				PO_HEADER PO_HEADER_BEAN = new PO_HEADER();
				errMsg=trans_PO_HEADER(rs, PO_HEADER_BEAN);
				PO_HEADER_BEAN.setPODATE(Datelibrary.date_Conv(PO_HEADER_BEAN.getPODATE(),"yyyyMMdd","dd-MM-yyyy"));
				POHEADERLIST.add(PO_HEADER_BEAN);
				i++;
			}
		}catch(Exception e)
		{
			errMsg="Error PO Db "+ e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String  trans_PO_HEADER(java.sql.ResultSet rsReg, PO_HEADER POHEADER)
	{
		String errMsg="";
		try
		{
			POHEADER.setPOSRNO            	   	(rsReg.getInt("POSRNO"));
			POHEADER.setPONO                   	(rsReg.getString("PONO"));
			POHEADER.setPODATE                 	(rsReg.getString("PODATE"));
			POHEADER.setPOSUPPLIERCODE          (rsReg.getLong("POSUPPLIERCODE"));
			POHEADER.setPOSUPPLIERNAME        	(rsReg.getString("POSUPPLIERNAME"));
			POHEADER.setPOSUPPLIERADDRESSLINE1	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERADDRESSLINE1")));
			POHEADER.setPOSUPPLIERADDRESSLINE2 	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERADDRESSLINE2")));
			POHEADER.setPOSUPPLIERADDRESSLINE3 	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERADDRESSLINE3")));
			POHEADER.setPOSUPPLIERADDRESSLINE4 	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERADDRESSLINE4")));
			POHEADER.setPOSUPPLIERCITY         	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERCITY")));
			POHEADER.setPOSUPPLIERDISTRICT     	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERDISTRICT")));
			POHEADER.setPOSUPPLIERSTATE        	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERSTATE")));
			POHEADER.setPOSUPPLIERCOUNTRY      	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERCOUNTRY")));
			POHEADER.setPOSUPPLIERPIN          	(krrCommon.ConvertNum(rsReg.getString("POSUPPLIERPIN")));
			POHEADER.setPOSUPPLIERCONTACTPERSON	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUPPLIERCONTACTPERSON")));
			POHEADER.setPOSUPPLIERCONTACTNO    	(krrCommon.ConvertNum(rsReg.getString("POSUPPLIERCONTACTNO")));
			POHEADER.setPOSUBJECT              	(krrCommon.CheckEmptyReturn(rsReg.getString("POSUBJECT")));
			POHEADER.setPOVATRATE              	(rsReg.getDouble("POVATRATE"));
			POHEADER.setPOCSTRATE              	(rsReg.getDouble("POCSTRATE"));
			POHEADER.setPOEXCISEDUTYRATE       	(rsReg.getDouble("POEXCISEDUTYRATE"));
			POHEADER.setPOLABOURCHARGES       	(rsReg.getDouble("POLABOURCHARGES"));
			POHEADER.setPOFREIGHTAMT           	(rsReg.getDouble("POFREIGHTAMT"));
			POHEADER.setPOSERVICETAX			(rsReg.getDouble("POSERVICETAX"));
			POHEADER.setTOTALPOCOST				(rsReg.getDouble("TOTALPOCOST"));
			POHEADER.setPOSTATUS               	(krrCommon.CheckEmptyReturn(rsReg.getString("POSTATUS")));
			POHEADER.setPOREMARKS              	(krrCommon.CheckEmptyReturn(rsReg.getString("POREMARKS")));
			POHEADER.setPOAUTHORIZEDBY          (krrCommon.CheckEmptyReturn(rsReg.getString("POAUTHORIZEDBY")));
			POHEADER.setPOAUTHORIZEDDATE        (krrCommon.CheckEmptyReturn(rsReg.getString("POAUTHORIZEDDATE")));
			POHEADER.setPOEDITEDBY				(krrCommon.CheckEmptyReturn(rsReg.getString("POEDITEDBY")));
			POHEADER.setPOEDITEDDATE			(krrCommon.CheckEmptyReturn(rsReg.getString("POEDITEDDATE")));
			POHEADER.setCREATEDBY				(krrCommon.CheckEmptyReturn(rsReg.getString("CREATEDBY")));
			POHEADER.setCREATEDDATE				(krrCommon.CheckEmptyReturn(rsReg.getString("CREATEDDATE")));
			POHEADER.setQUOTATIONNO             (rsReg.getLong("QUOTATIONNO"));
			POHEADER.setQUOTATIONDATE			(krrCommon.CheckEmptyReturn(rsReg.getString("QUOTATIONDATE")));
			POHEADER.setDOCREFNO				(krrCommon.CheckEmptyReturn(rsReg.getString("DOCREFNO")));
			POHEADER.setDOCREFDATE				(krrCommon.CheckEmptyReturn(rsReg.getString("DOCREFDATE")));
			POHEADER.setRAISEDBY				(krrCommon.CheckEmptyReturn(rsReg.getString("RAISEDBY")));
			POHEADER.setAUTHBY					(krrCommon.CheckEmptyReturn(rsReg.getString("AUTHBY")));
			POHEADER.setAUTHDATE				(krrCommon.CheckEmptyReturn(rsReg.getString("AUTHDATE")));
			POHEADER.setACKNOWNO				(krrCommon.CheckEmptyReturn(rsReg.getString("ACKNOWNO")));
			POHEADER.setACKNOWDATE				(krrCommon.CheckEmptyReturn(rsReg.getString("ACKNOWDATE")));
			POHEADER.setPAYADVNO           		(rsReg.getLong("PAYADVNO"));
			POHEADER.setPAYADVDATE				(krrCommon.CheckEmptyReturn(rsReg.getString("PAYADVDATE")));
			POHEADER.setCHEQUENO           		(rsReg.getLong("CHEQUENO"));
			POHEADER.setCHEQUEDATE				(krrCommon.CheckEmptyReturn(rsReg.getString("CHEQUEDATE")));
			POHEADER.setBANKCODE				(krrCommon.CheckEmptyReturn(rsReg.getString("BANKCODE")));
			POHEADER.setBANKNAME				(krrCommon.CheckEmptyReturn(rsReg.getString("BANKNAME")));
			POHEADER.setADVAMOUNT				(rsReg.getDouble("ADVAMOUNT"));
			POHEADER.setBALAMOUNT				(rsReg.getDouble("BALAMOUNT"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errMsg="Error in Trans_PO_HEADER "+e.getMessage();
		}
		return errMsg;
	}

//	TYPE
	public String get_suppliername_PO_HEADER(Connection con,List<PO_HEADER> POHEADERLIST){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct POSUPPLIERNAME from PO_HEADER order by POSUPPLIERNAME";
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				PO_HEADER Supplier = new PO_HEADER();
				Supplier.setPOSUPPLIERNAME(rs.getString("POSUPPLIERNAME"));
				POHEADERLIST.add(Supplier);
			}
		}catch(Exception e)
		{
			l_ers="error in getting po supplier name details"+e.getMessage();
			e.printStackTrace();
		}finally
		{
			try
			{ 
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}

		return l_ers;
	}

	public String change_po_status(Connection con,long poSrNo,String status,PO_CommonData PO_CommonData)
	{
		Statement stmt=null;
		String errMsg="";
		String sql;
		try
		{
			stmt = con.createStatement();
			sql= "update PO_HEADER set POSTATUS = "+krrCommon.AppendSinlequote(status)+",";
			sql=sql+"POAUTHORIZEDBY="+krrCommon.AppendSinlequote(PO_CommonData.getLOGIN_ID())+",";
			sql=sql+"POAUTHORIZEDDATE = "+krrCommon.AppendSinlequote(Datelibrary.display_Current_Date("yyyyMMdd")); 
			sql=sql+" where POSRNO="+poSrNo;
			stmt.executeQuery(sql);
			System.out.println(sql);
		}catch(Exception e)
		{
			errMsg="Error in change PO status Details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	//TODO check, get from vendor_supplier DB
	public String get_supp_details_VENDOR_SUPPLIER(Connection con,VENDOR_SUPPLIER VENDORDETAILS,String code){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "select * from VENDOR_SUPPLIER where VSSRNO =" +code;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				errMsg=Trans_VENDOR_SUPPLIER_DETAILS(rs, VENDORDETAILS);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting PO supplier details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	//TODO Already written in vendor_suppllier
	public String  Trans_VENDOR_SUPPLIER_DETAILS(java.sql.ResultSet rsReg, bean.VENDOR_SUPPLIER VENDORDETAILS)
	{
		String errMsg="";
		try
		{
			VENDORDETAILS.setNAME(rsReg.getString("NAME"));
			VENDORDETAILS.setVSSRNO(rsReg.getLong("VSSRNO"));
			VENDORDETAILS.setADDRESSLINE1(rsReg.getString("ADDRESSLINE1"));
			VENDORDETAILS.setADDRESSLINE2(rsReg.getString("ADDRESSLINE2"));
			VENDORDETAILS.setADDRESSLINE3(rsReg.getString("ADDRESSLINE3"));
			VENDORDETAILS.setADDRESSLINE4(rsReg.getString("ADDRESSLINE4"));
			VENDORDETAILS.setCITY(rsReg.getString("CITY"));
			VENDORDETAILS.setSTATE(rsReg.getString("STATE"));
			VENDORDETAILS.setDISTRICT(rsReg.getString("DISTRICT"));
			VENDORDETAILS.setCOUNTRY(rsReg.getString("COUNTRY"));
			VENDORDETAILS.setPIN(rsReg.getLong("PIN"));
		}
		catch(Exception e)
		{
			errMsg="Error in Trans_Supplier_DETAILS "+e.getMessage();
		}
		return errMsg;
	}

	public String insert_PO_HEADER(Connection con, PO_HEADER POHEADER)   
	{
		String errMsg="";
		Statement stmt=null;
		String sql="";
		try{
			stmt=con.createStatement();
			long seqId=krrCommon.Nextval_SQ("PO_HEADER_SQ", con);
			POHEADER.setPOSRNO(seqId);
			if(seqId>0)
			{
				sql="INSERT INTO PO_HEADER(";
				sql=sql+"POSRNO,";
				sql=sql+"PONO, ";
				sql=sql+"PODATE, ";
				sql=sql+"POSUPPLIERCODE, ";
				sql=sql+"POSUPPLIERNAME, ";
				sql=sql+"POSUPPLIERADDRESSLINE1,";
				sql=sql+"POSUPPLIERADDRESSLINE2,";
				sql=sql+"POSUPPLIERADDRESSLINE3,";
				sql=sql+"POSUPPLIERADDRESSLINE4,";
				sql=sql+"POSUPPLIERCITY,";
				sql=sql+"POSUPPLIERDISTRICT,";
				sql=sql+"POSUPPLIERSTATE,";
				sql=sql+"POSUPPLIERCOUNTRY,";
				sql=sql+"POSUPPLIERPIN,";
				sql=sql+"POSUPPLIERCONTACTPERSON,";
				sql=sql+"POSUPPLIERCONTACTNO,";
				sql=sql+"POSUBJECT,";
				sql=sql+"POVATRATE,";
				sql=sql+"POCSTRATE,";
				sql=sql+"POEXCISEDUTYRATE,";
				sql=sql+"POLABOURCHARGES,";
				sql=sql+"POFREIGHTAMT,";
				sql=sql+"POSERVICETAX,";
				sql=sql+"TOTALPOCOST,";
				sql=sql+"POSTATUS,";
				sql=sql+"POREMARKS,";
				sql=sql+"POAUTHORIZEDBY,";
				sql=sql+"POAUTHORIZEDDATE,";
				sql=sql+"POEDITEDBY,";
				sql=sql+"POEDITEDDATE,";
				sql=sql+"CREATEDBY,";
				sql=sql+"CREATEDDATE,";
				sql=sql+"QUOTATIONNO,";
				sql=sql+"QUOTATIONDATE,";
				sql=sql+"DOCREFNO,";
				sql=sql+"DOCREFDATE,";
				sql=sql+"RAISEDBY,";
				sql=sql+"AUTHBY,";
				sql=sql+"AUTHDATE,";
				sql=sql+"ACKNOWNO,";
				sql=sql+"ACKNOWDATE,";
				sql=sql+"PAYADVNO,";
				sql=sql+"PAYADVDATE,";
				sql=sql+"CHEQUENO,";
				sql=sql+"CHEQUEDATE,";
				sql=sql+"BANKCODE,";
				sql=sql+"BANKNAME,";
				sql=sql+"ADVAMOUNT,";
				sql=sql+"BALAMOUNT";
				sql=sql+") VALUES (";
				sql=sql+POHEADER.getPOSRNO()+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPONO())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPODATE())+",";
				sql=sql+POHEADER.getPOSUPPLIERCODE()+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERNAME())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE1())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE2())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE3())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE4())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERCITY())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERDISTRICT())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERSTATE())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERCOUNTRY())+",";
				sql=sql+POHEADER.getPOSUPPLIERPIN()+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERCONTACTPERSON())+",";
				sql=sql+POHEADER.getPOSUPPLIERCONTACTNO()+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSUBJECT())+", ";
				sql=sql+POHEADER.getPOVATRATE()+", ";
				sql=sql+POHEADER.getPOCSTRATE()+", ";
				sql=sql+POHEADER.getPOEXCISEDUTYRATE()+", ";
				sql=sql+POHEADER.getPOLABOURCHARGES()+", ";
				sql=sql+POHEADER.getPOFREIGHTAMT()+", ";
				sql=sql+POHEADER.getPOSERVICETAX()+", ";
				sql=sql+POHEADER.getTOTALPOCOST()+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOSTATUS())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOREMARKS())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOAUTHORIZEDBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOAUTHORIZEDDATE())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOEDITEDBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPOEDITEDDATE())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getCREATEDBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getCREATEDDATE())+",";
				sql=sql+POHEADER.getQUOTATIONNO()+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getQUOTATIONDATE())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getDOCREFNO())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getDOCREFDATE())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getRAISEDBY())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getAUTHBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getAUTHDATE())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getACKNOWNO())+", ";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getACKNOWDATE())+",";
				sql=sql+POHEADER.getPAYADVNO()+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getPAYADVDATE())+",";
				sql=sql+POHEADER.getCHEQUENO()+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getCHEQUEDATE())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getBANKCODE())+",";
				sql=sql+krrCommon.AppendSinlequote(POHEADER.getBANKNAME())+",";
				sql=sql+POHEADER.getADVAMOUNT()+",";
				sql=sql+POHEADER.getBALAMOUNT()+")";
				System.out.println(sql);	
				stmt.execute(sql);
			}
			else
			{
				errMsg="PO Serial Number Does not exixts";
			}
		}
		catch(Exception e)
		{
			errMsg="error in insertion PO Header details"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String get_PO_DETAILS_list(Connection con,String PONO,List<PO_DETAILS> PODETAILS){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "select * from PO_DETAILS where PONUMBER="+krrCommon.AppendSinlequote(PONO);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				PO_DETAILS PO_DETAILS=new PO_DETAILS();
				Trans_PO_DETAILS(rs, PO_DETAILS);
				PODETAILS.add(PO_DETAILS);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting PO item details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String  Trans_PO_DETAILS(java.sql.ResultSet rsReg, bean.PO_DETAILS PODETAILS)
	{
		String errMsg="";
		try
		{
			PODETAILS.setPODETAILSSRNO		  	(rsReg.getLong("PODETAILSSRNO"));
			PODETAILS.setPONUMBER            	(rsReg.getString("PONUMBER"));
			PODETAILS.setPOITEMCODE         	(rsReg.getLong("POITEMCODE"));
			PODETAILS.setPOITEMDESCRIPTION		(rsReg.getString("POITEMDESCRIPTION"));
			PODETAILS.setUOM    				(rsReg.getString("UOM"));
			PODETAILS.setCATALOGNO    			(rsReg.getString("CATALOGNO"));
			PODETAILS.setMNFGCODE    			(rsReg.getString("MNFGCODE"));
			PODETAILS.setPOITEMUNITCOST			(rsReg.getDouble("POITEMUNITCOST"));
			PODETAILS.setQTYORDER   			(rsReg.getDouble("QTYORDER"));
			PODETAILS.setORDQTYTOTCOST         	(rsReg.getDouble("ORDQTYTOTCOST"));
			PODETAILS.setPOITEMQTYRECEIVED      (rsReg.getLong("POITEMQTYRECEIVED"));
			PODETAILS.setPOITEMBONUS          	(rsReg.getLong("POITEMBONUS"));
			PODETAILS.setPOITEMCREATEDBY 		(rsReg.getString("POITEMCREATEDBY"));
			PODETAILS.setPOITEMCREATEDDATE		(rsReg.getString("POITEMCREATEDDATE"));
			PODETAILS.setEXPDELIVERYDT     		(rsReg.getString("EXPDELIVERYDT"));
			PODETAILS.setSTATUS   				(rsReg.getString("STATUS"));
			PODETAILS.setORDQTYTOTCOST			(rsReg.getDouble("ORDQTYTOTCOST"));
		}
		catch(Exception e)
		{
			errMsg="Error in Trans_PO_DETAILS "+e.getMessage();
		}
		return errMsg;
	}

	//TODO get from VENDOR_SUPPLIER DB
	public String get_Supp_name_VENDOR_SUPPLIER(Connection con,List<VENDOR_SUPPLIER> VENDORDETAILSLIST){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "select NAME,VSSRNO from VENDOR_SUPPLIER where type='SUPPLIER' and status='ACTIVE'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				VENDOR_SUPPLIER VENDOR_SUPPLIER=new VENDOR_SUPPLIER();
				VENDOR_SUPPLIER.setNAME(rs.getString("NAME"));
				VENDOR_SUPPLIER.setVSSRNO(rs.getLong("VSSRNO"));
				VENDORDETAILSLIST.add(VENDOR_SUPPLIER);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting PO supplier name details"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String insert_PO_DETAILS(Connection con, PO_DETAILS PODETAILS)   
	{
		String errMsg="";
		Statement stmt=null;
		String sql="";
		try{
			stmt=con.createStatement();
			long seqId=krrCommon.Nextval_SQ("PO_DETAILS_SQ", con);
			PODETAILS.setPODETAILSSRNO(seqId);
			if(seqId>0)
			{
				sql="INSERT INTO PO_DETAILS(";
				sql=sql+"PODETAILSSRNO,";
				sql=sql+"PONUMBER,";   
				sql=sql+"POITEMCODE ,"; 
				sql=sql+"POITEMDESCRIPTION,"; 
				sql=sql+"UOM,";    
				sql=sql+"CATALOGNO ,";  
				sql=sql+"MNFGCODE ,";        
				sql=sql+"POITEMUNITCOST ,";     
				sql=sql+"QTYORDER,";         
				sql=sql+"ORDQTYTOTCOST,";  
				sql=sql+"POITEMQTYRECEIVED,";
				sql=sql+"POITEMBONUS ,";         
				sql=sql+"POITEMCREATEDBY,";   
				sql=sql+"POITEMCREATEDDATE,";
				sql=sql+"EXPDELIVERYDT,";
				sql=sql+"STATUS";
				sql=sql+") VALUES (";
				sql=sql+PODETAILS.getPODETAILSSRNO()+",";
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getPONUMBER())+",";    
				sql=sql+PODETAILS.getPOITEMCODE ()+",";    
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getPOITEMDESCRIPTION())+",";
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getUOM())+",";
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getCATALOGNO())+",";
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getMNFGCODE())+",";
				sql=sql+PODETAILS.getPOITEMUNITCOST()+",";    
				sql=sql+PODETAILS.getQTYORDER()+",";  
				sql=sql+PODETAILS.getORDQTYTOTCOST()+",";        
				sql=sql+PODETAILS.getPOITEMQTYRECEIVED()+",";     
				sql=sql+PODETAILS.getPOITEMBONUS()+",";         
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getPOITEMCREATEDBY())+",";  
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getPOITEMCREATEDDATE())+",";  
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getEXPDELIVERYDT())+",";  
				sql=sql+krrCommon.AppendSinlequote(PODETAILS.getSTATUS())+" ) ";
				System.out.println(sql);	
				stmt.execute(sql);
			}
			else
			{
				errMsg="PO Details Serail number does nor exists";
			}
		}
		catch(Exception e)
		{
			errMsg="error in inserting item details process "+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String insertPO_TC(Connection con, bean.PO_TC POTC)   
	{
		String errMsg="";
		Statement stmt=null;
		String sql="";
		try{
			stmt=con.createStatement();
			long seqId=krrCommon.Nextval_SQ("PO_TC_SQ", con);
			POTC.setPOTCSRNO(seqId);	
			if(seqId>0)
			{
				sql="INSERT INTO PO_TC(";
				sql=sql+"POTCSRNO ,";
				sql=sql+"PONO,";           
				sql=sql+"CONDITION  ,";  
				sql=sql+"DESCRIPTION  ";
				sql=sql+") VALUES (";
				sql=sql+POTC.getPOTCSRNO()+",";
				sql=sql+krrCommon.AppendSinlequote(POTC.getPONO())+",";    
				sql=sql+krrCommon.AppendSinlequote(POTC.getCONDITION())+",";
				sql=sql+krrCommon.AppendSinlequote(POTC.getDESCRIPTION ())+" ) ";
				System.out.println(sql);	
				stmt.execute(sql);
			}
			else
			{
				errMsg="POTC serial number does not exist";
			}
		}
		catch(Exception e)
		{
			errMsg="error in inserting POTC"+e.getMessage();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String getPO_TC(Connection con,String PONO,List<PO_TC> POTCLIST){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from PO_TC where PONO = "+krrCommon.AppendSinlequote(PONO);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				PO_TC PO_TC=new PO_TC();
				errMsg=Trans_PO_TC(rs, PO_TC);
				POTCLIST.add(PO_TC);
			}
		}
		catch(Exception e)
		{
			errMsg="Error in get po TC details process";
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String  Trans_PO_TC(java.sql.ResultSet rsReg, bean.PO_TC POTC)
	{
		String errMsg="";
		try
		{
			POTC.setPOTCSRNO	   (rsReg.getLong("POTCSRNO"));
			POTC.setPONO          (rsReg.getString("PONO"));					
			POTC.setCONDITION     (rsReg.getString("CONDITION"));
			POTC.setDESCRIPTION   (rsReg.getString("DESCRIPTION"));
		}
		catch(Exception e)
		{
			errMsg="Error in Trans_PO_TC "+e.getMessage();
		}
		return errMsg;
	}

	public boolean is_PO_exits(Connection con,String PONO)
	{
		boolean poExits = false;
		Statement stmt =null ;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from PO_HEADER where PONO = "+krrCommon.AppendSinlequote(PONO);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				poExits = true;
			}
		}
		catch(Exception e)
		{
			l_ers="error in checking PO code"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return poExits;
	}

	public String get_PO_status(Connection con,long POSrNo)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String POSTATUS="";
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select POSTATUS FROM PO_HEADER where POSRNO="+POSrNo;
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) 
			{
				POSTATUS=rs.getString("POSTATUS");
			}
		}
		catch(Exception e)
		{
			l_ers="Error in getting PO status"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return POSTATUS;
	}
	
	

	public String getPOHEADEROnPOSrNo(Connection con,PO_HEADER POHEADER){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from PO_HEADER where POSRNO = "+POHEADER.getPOSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				errMsg=trans_PO_HEADER(rs, POHEADER);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting po header details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}
	
	public String getPOHEADEROnPONO(Connection con,PO_HEADER POHEADER){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "select * from PO_HEADER where PONO = "+krrCommon.AppendSinlequote(POHEADER.getPONO());
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				errMsg=trans_PO_HEADER(rs, POHEADER);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting po header details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String getPOTCOnPOTCSrNo(Connection con,bean.PO_TC POTC){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "select * from PO_TC where POTCSRNO = "+POTC.getPOTCSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				errMsg=Trans_PO_TC(rs,POTC);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting po terms and conditions details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String getPODETAILSOnPOITEMSRNO(Connection con,bean.PO_DETAILS PODETAILS){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "select * from PO_DETAILS where PODETAILSSRNO = "+PODETAILS.getPODETAILSSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				errMsg=Trans_PO_DETAILS(rs, PODETAILS);
			}
		}catch(Exception e)
		{
			errMsg="Error in getting po item details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String deletePO_ITEM_DETAILS(Connection con,bean.PO_DETAILS PODETAILS){
		Statement stmt=null;
		String sql;
		String errMsg="";
		try{

			stmt = con.createStatement();
			sql= "delete PO_DETAILS where PODETAILSSRNO = "+PODETAILS.getPODETAILSSRNO();
			System.out.println(sql);
			stmt.executeQuery(sql);
		}catch(Exception e)
		{
			errMsg="Error in deleting po item details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String getTotalPOItemCost(Connection con,PO_HEADER POHEADER){
		Statement stmt=null;
		String sql;
		String errMsg="";
		ResultSet rs =null;
		try{
			stmt = con.createStatement();
			sql= "select sum(ORDQTYTOTCOST) as totalPoCost from PO_DETAILS where PONUMBER = "+krrCommon.AppendSinlequote(POHEADER.getPONO());
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				POHEADER.setTOTALPOCOST(rs.getDouble("totalPoCost"));
			}
		}catch(Exception e)
		{
			errMsg="Error in gettion PO Item total cost"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}
	
	public String deletePO_TC_DETAILS(Connection con,bean.PO_TC POTC){
		Statement stmt=null;
		String sql;
		String errMsg="";
		try{
			stmt = con.createStatement();
			sql= "delete PO_TC where POTCSRNO = "+POTC.getPOTCSRNO();
			System.out.println(sql);
			stmt.executeQuery(sql);
		}catch(Exception e)
		{
			errMsg="Error in deleting po terms and conditions details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String update_PO_HEADER(Connection con,PO_HEADER POHEADER)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		int recordsUpdated=0;
		try{
			stmt = con.createStatement();
			sql= "update PO_HEADER set PONO = "+krrCommon.AppendSinlequote(POHEADER.getPONO())+",";
			sql=sql+"PODATE = "+krrCommon.AppendSinlequote(POHEADER.getPODATE())+",";
			sql=sql+"POSUPPLIERCODE = "+POHEADER.getPOSUPPLIERCODE()+",";
			sql=sql+"POSUPPLIERNAME = " +krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERNAME())+",";
			sql=sql+"POSUPPLIERADDRESSLINE1 = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE1())+",";
			sql=sql+"POSUPPLIERADDRESSLINE2 = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE2())+",";
			sql=sql+"POSUPPLIERADDRESSLINE3 = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE3())+",";
			sql=sql+"POSUPPLIERADDRESSLINE4 = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERADDRESSLINE4())+",";
			sql=sql+"POSUPPLIERCITY = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERCITY())+",";
			sql=sql+"POSUPPLIERDISTRICT = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERDISTRICT())+",";
			sql=sql+"POSUPPLIERSTATE = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERSTATE())+",";
			sql=sql+"POSUPPLIERCOUNTRY = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERCOUNTRY())+",";
			sql=sql+"POSUPPLIERPIN = "+POHEADER.getPOSUPPLIERPIN()+",";
			sql=sql+"POSUPPLIERCONTACTPERSON = "+krrCommon.AppendSinlequote(POHEADER.getPOSUPPLIERCONTACTPERSON())+",";
			sql=sql+"POSUPPLIERCONTACTNO = "+POHEADER.getPOSUPPLIERCONTACTNO()+",";
			sql=sql+"POSUBJECT = "+krrCommon.AppendSinlequote(POHEADER.getPOSUBJECT())+",";
			sql=sql+"POVATRATE = "+POHEADER.getPOVATRATE()+",";
			sql=sql+"POCSTRATE = "+POHEADER.getPOCSTRATE()+",";
			sql=sql+"POEXCISEDUTYRATE = "+POHEADER.getPOEXCISEDUTYRATE()+",";
			sql=sql+"POLABOURCHARGES = "+POHEADER.getPOLABOURCHARGES()+",";
			sql=sql+"POFREIGHTAMT = "+POHEADER.getPOFREIGHTAMT()+",";
			sql=sql+"POSERVICETAX = "+POHEADER.getPOSERVICETAX()+",";
			sql=sql+"TOTALPOCOST = "+POHEADER.getTOTALPOCOST()+",";
			sql=sql+"POREMARKS = "+krrCommon.AppendSinlequote(POHEADER.getPOREMARKS())+",";
			sql=sql+"POEDITEDBY = "+krrCommon.AppendSinlequote(POHEADER.getPOEDITEDBY())+",";
			sql=sql+"POEDITEDDATE = "+krrCommon.AppendSinlequote(POHEADER.getPOEDITEDDATE())+",";
			sql=sql+"QUOTATIONNO = "+POHEADER.getQUOTATIONNO()+",";
			sql=sql+"QUOTATIONDATE = "+krrCommon.AppendSinlequote(POHEADER.getQUOTATIONDATE())+",";
			sql=sql+"DOCREFNO = "+krrCommon.AppendSinlequote(POHEADER.getDOCREFNO())+",";
			sql=sql+"DOCREFDATE = "+krrCommon.AppendSinlequote(POHEADER.getDOCREFDATE())+",";
			sql=sql+"RAISEDBY = "+krrCommon.AppendSinlequote(POHEADER.getRAISEDBY())+",";
			sql=sql+"AUTHBY = "+krrCommon.AppendSinlequote(POHEADER.getAUTHBY())+",";
			sql=sql+"AUTHDATE = "+krrCommon.AppendSinlequote(POHEADER.getAUTHDATE())+",";
			sql=sql+"ACKNOWNO = "+krrCommon.AppendSinlequote(POHEADER.getACKNOWNO())+",";
			sql=sql+"ACKNOWDATE = "+krrCommon.AppendSinlequote(POHEADER.getACKNOWDATE())+",";
			sql=sql+"PAYADVNO = "+POHEADER.getPAYADVNO()+",";
			sql=sql+"PAYADVDATE = "+krrCommon.AppendSinlequote(POHEADER.getPAYADVDATE())+",";
			sql=sql+"CHEQUENO = "+POHEADER.getCHEQUENO()+",";
			sql=sql+"CHEQUEDATE = "+krrCommon.AppendSinlequote(POHEADER.getCHEQUEDATE())+",";
			sql=sql+"BANKCODE = "+krrCommon.AppendSinlequote(POHEADER.getBANKCODE())+",";
			sql=sql+"BANKNAME = "+krrCommon.AppendSinlequote(POHEADER.getBANKNAME())+",";
			sql=sql+"ADVAMOUNT = "+POHEADER.getADVAMOUNT()+",";
			sql=sql+"BALAMOUNT = " +POHEADER.getBALAMOUNT();
			sql=sql+" where POSRNO = " +POHEADER.getPOSRNO();
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			System.out.println(sql);
			if(recordsUpdated==0)
			{
				errMsg="Records not updated";
			}

		}catch(Exception e)
		{
			errMsg="Error in update PO Header Details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String update_PO_TC(Connection con,PO_TC POTC)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		int recordsUpdated=0;
		try{
			stmt = con.createStatement();
			sql= "update PO_TC set PONO = "+krrCommon.AppendSinlequote(POTC.getPONO())+",";
			sql=sql+"CONDITION = "+krrCommon.AppendSinlequote(POTC.getCONDITION())+",";
			sql= sql+"DESCRIPTION = " +krrCommon.AppendSinlequote(POTC.getDESCRIPTION());
			sql=sql+"where POTCSRNO = " +POTC.getPOTCSRNO();
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			if(recordsUpdated==0)
			{
				errMsg="Records not updated";
			}

		}catch(Exception e)
		{
			errMsg="Error in update PO TC Details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public String update_PO_DETAILS(Connection con,PO_DETAILS PODETAILS)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		int recordsUpdated=0;
		try{
			stmt = con.createStatement();
			sql= "update PO_DETAILS set PONUMBER = "+krrCommon.AppendSinlequote(PODETAILS.getPONUMBER())+",";
			sql=sql+"POITEMCODE = "+(PODETAILS.getPOITEMCODE())+",";
			sql=sql+"POITEMDESCRIPTION = "+krrCommon.AppendSinlequote(PODETAILS.getPOITEMDESCRIPTION())+",";
			sql=sql+"UOM = "+krrCommon.AppendSinlequote(PODETAILS.getUOM())+",";
			sql=sql+"CATALOGNO = "+krrCommon.AppendSinlequote(PODETAILS.getCATALOGNO())+",";
			sql=sql+"MNFGCODE = "+krrCommon.AppendSinlequote(PODETAILS.getMNFGCODE())+",";
			sql=sql+"POITEMUNITCOST = "+(PODETAILS.getPOITEMUNITCOST())+",";
			sql=sql+"QTYORDER = "+(PODETAILS.getQTYORDER())+",";
			sql=sql+"ORDQTYTOTCOST = "+(PODETAILS.getORDQTYTOTCOST())+",";
			sql=sql+"EXPDELIVERYDT = "+PODETAILS.getEXPDELIVERYDT()+",";
			sql=sql+"POITEMBONUS = "+(PODETAILS.getPOITEMBONUS());
			sql=sql+"where PODETAILSSRNO = " +PODETAILS.getPODETAILSSRNO();
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			if(recordsUpdated==0)
			{
				errMsg="Records not updated";
			}
		}catch(Exception e)
		{
			errMsg="Error in update PO item Details process"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	public boolean is_POTCCondition_exist(Connection con,PO_TC POTC)
	{
		boolean codeExits = false;
		Statement stmt = null;
		String sql;
		ResultSet rs = null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select CONDITION from PO_TC where PONO = "+krrCommon.AppendSinlequote(POTC.getPONO())+"and CONDITION="+krrCommon.AppendSinlequote(POTC.getCONDITION());
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				codeExits = true;
			}
		}
		catch(Exception e)
		{
			l_ers="error in checking POTC condition"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs !=null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return codeExits;
	}
	
	public boolean is_PO_ITEMexits(Connection con,String PONO)
	{
		boolean codeExits = false;
		Statement stmt = null;
		String sql;
		ResultSet rs = null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from PO_DETAILS where PONUMBER = "+krrCommon.AppendSinlequote(PONO);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				codeExits = true;
			}
		}catch(Exception e)
		{
			l_ers="error in checking PO code"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return codeExits;
	}
	
	public boolean is_PO_TCexits(Connection con,String PONO)
	{
		boolean codeExits = false;
		Statement stmt = null;
		String sql;
		ResultSet rs = null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from PO_TC where PONO = "+krrCommon.AppendSinlequote(PONO);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				codeExits = true;
			}
		}
		catch(Exception e)
		{
			l_ers="error in checking PO code"+e.getMessage();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return codeExits;
	}
}
