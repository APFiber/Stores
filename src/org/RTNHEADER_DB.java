package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import bean.GP_DETAILS;
import bean.RETURN_NOTE;
import bean.RTN_COMMONDATA;
import bean.GP_HEADER;
import bean.STOCK_SUMMARY;

public class RTNHEADER_DB {
  KrrCommon krrcommon = new KrrCommon();
  Date_library date_library = new Date_library();
  public String serach_rtn_details(Connection con,List<RETURN_NOTE> RETURNNOTELIST,RTN_COMMONDATA commonData)
  {
 	  Statement stmt=null ;
 	  String sql;
 	  ResultSet rs=null;
 	  String errMsg="";
 	  String condition="";
 	  try
 	  {
 		  stmt=con.createStatement();
 		  sql="SELECT * FROM RETURN_NOTE where RTNOTE_STATUS <> 'DELETED'";
 		  if(commonData.getITEMCODE()!=0)
 		  {
 			  condition = condition +" RTNOTE_ITEMCODE  = "+commonData.getITEMCODE();
 		  }
 		  if(commonData.getGPNO()!=0){
 				condition = condition+" RTNOTE_GPNO = "+commonData.getGPNO();			
 			}
 		  if(krrcommon.isValue(commonData.getRETURNDATE()))
 		  {
 				condition = condition +" AND RTNOTE_DATE = "+krrcommon.AppendSinlequote(date_library.date_Conv(commonData.getRETURNDATE(),"dd-MM-yyyy","yyyyMMdd"));
 			}
 		  if(krrcommon.isValue(commonData.getPURPOSEOFRETURN()))
 		  {
 			  condition = condition +"  RTNOTE_PURPOSEOFRETURN = "+krrcommon.AppendSinlequote(commonData.getPURPOSEOFRETURN());
 			  
 		  }
 	
 	  if(condition !=""){
 			condition = condition.trim();
 			if(condition.substring(0, 3).equalsIgnoreCase("and")){
 				condition = condition.substring(3, condition.length());
 			}
 			sql = sql+" and "+ condition+" ";	
 		}	
 		sql=sql+" order by RTNOTE_NO ";
 		System.out.println(sql);
 		rs = stmt.executeQuery(sql);
 		int i=1;
 		while (rs.next()) 
 		{
 			RETURN_NOTE RETURNNOTE = new RETURN_NOTE();
 			errMsg=trans_rtn_details(rs, RETURNNOTE);
 			RETURNNOTE.setRTNOTE_DATE(date_library.date_Conv(RETURNNOTE.getRTNOTE_DATE(), "yyyyMMdd", "ddMMyyyy"));
 			RETURNNOTELIST.add(RETURNNOTE);
 			i++;
 		}
 	}catch(Exception e)
 	{
 		errMsg="Error searching rtn details "+ e.getMessage();
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
  public String trans_rtn_details(ResultSet rs,RETURN_NOTE rtnBean)
  {
	  String l_ers="";
	  try
	  {
		  rtnBean.setRTNOTE_NO(rs.getLong("RTNOTE_NO"));
		  rtnBean.setRTNOTE_DATE(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_DATE")));
		  rtnBean.setRTNOTE_GPNO(rs.getLong("RTNOTE_GPNO"));
		  rtnBean.setRTNOTE_GPDATE(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_GPDATE")));
		  rtnBean.setRTNOTE_VENDORCODE(rs.getLong("RTNOTE_VENDORCODE"));
		  rtnBean.setRTNOTE_VENDORNAME(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_VENDORNAME")));
          rtnBean.setRTNOTE_QTYRETURNED(rs.getLong("RTNOTE_QTYRETURNED"));
		  rtnBean.setRTNOTE_ITEMCODE(rs.getLong("RTNOTE_ITEMCODE"));
		  rtnBean.setRTNOTE_ITEMMAKE(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_ITEMMAKE")));
		  rtnBean.setRTNOTE_ITEMDESCRIPTION(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_ITEMDESCRIPTION")));
		  rtnBean.setRTNOTE_PURPOSEOFRETURN(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_PURPOSEOFRETURN")));
		  rtnBean.setRTNOTE_APPROVEDBY(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_APPROVEDBY")));
		  rtnBean.setRTNOTE_APPROVEDDATE(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_APPROVEDDATE")));
		  rtnBean.setRTNOTE_STATUS(rs.getString(krrcommon.CheckEmptyReturn("RTNOTE_STATUS")));
	  }
	  catch (Exception e) {
		e.printStackTrace();
		l_ers="Error in rtndeatils "+e.getMessage();
	}
	return l_ers;
	  
  }

public String insert_rtn(Connection con,RETURN_NOTE returnnote)
{
	Statement stmt=null;
	String sql;
	String errMsg="";
	
				try
						{		
						long SeqId=krrcommon.Nextval_SQ("RETURN_NOTE_SQ", con);
						if(SeqId>0)
						{
							returnnote.setRTNOTE_NO(SeqId);
							sql = "insert into RETURN_NOTE (";
							sql=sql+"RTNOTE_NO ,";
							sql=sql+"RTNOTE_DATE,";
							sql=sql+"RTNOTE_GPNO,";
							sql=sql+"RTNOTE_GPDATE,";
							sql=sql+"RTNOTE_VENDORCODE,";
						    sql=sql+"RTNOTE_VENDORNAME,";
							sql=sql+"RTNOTE_VENDORADDRESSLINE1,";
							sql=sql+"RTNOTE_VENDORADDRESSLINE2,";
							sql=sql+"RTNOTE_VENDORADDRESSLINE3,";
							sql=sql+"RTNOTE_VENDORADDRESSLINE4,";
							sql=sql+"RTNOTE_CITY,";
							sql=sql+"RTNOTE_DISTRICT,";
							sql=sql+"RTNOTE_STATE,";
							sql=sql+"RTNOTE_COUNTRY,";
							sql=sql+"RTNOTE_PIN,";
							sql=sql+"RTNOTE_VENDORPHONENO,";
							sql=sql+"RTNOTE_PROJECT,";
							sql=sql+"RTNOTE_LOCATION,";
     						sql=sql+"RTNOTE_QTYRETURNED,";
							sql=sql+"RTNOTE_ITEMCODE,"; 
					//		sql=sql+"RTNOTE_ITEMGROUP,";
							sql=sql+"RTNOTE_ITEMMAKE ,";
							sql=sql+"RTNOTE_ITEMDESCRIPTION,";
							sql=sql+"RTNOTE_PURPOSEOFRETURN,";
							sql=sql+"RTNOTE_APPROVEDBY,";
							sql=sql+"RTNOTE_APPROVEDDATE,";
							sql=sql+"RTNOTE_CREATEDBY,";
							sql=sql+"RTNOTE_CREATEDDATE,";
							sql=sql+"RTNOTE_STATUS ";
							
							sql=sql+") VALUES (";
							sql=sql+(returnnote.getRTNOTE_NO()) +",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_DATE())+",";
							
							sql=sql+(returnnote.getRTNOTE_GPNO())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_GPDATE())+",";
							sql=sql+(returnnote.getRTNOTE_VENDORCODE())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_VENDORNAME())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_VENDORADDRESSLINE1())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_VENDORADDRESSLINE2())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_VENDORADDRESSLINE3())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_VENDORADDRESSLINE4())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_CITY())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_DISTRICT())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_STATE())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_COUNTRY())+",";
							sql=sql+(returnnote.getRTNOTE_PIN())+",";
							sql=sql+(returnnote.getRTNOTE_VENDORPHONENO())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_PROJECT())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_LOCATION())+",";
							sql=sql+(returnnote.getRTNOTE_QTYRETURNED())+",";
							sql=sql+(returnnote.getRTNOTE_ITEMCODE())+",";
						//	sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_ITEMGROUP())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_ITEMMAKE())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_ITEMDESCRIPTION())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_PURPOSEOFRETURN())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_APPROVEDBY())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_APPROVEDDATE())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_CREATEDBY())+",";
							sql=sql+krrcommon.AppendSinlequote(returnnote.getRTNOTE_CREATEDDATE())+",";
							sql=sql+krrcommon.AppendSinlequote("ENTRY")+")";
							
							System.out.println(sql);
							stmt = con.createStatement();	
							stmt.execute(sql);
						}
	
			else
			{
				errMsg="RETURN_NOTE_SQ is missing ";
			}
	}
			catch (Exception e) {
				errMsg="Error in rtn deatils "+e.getMessage();
				e.printStackTrace();
			}
			finally
			{ 
				try
				{
					if(stmt!=null)stmt.close();
				}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			}
	return errMsg;
	
}
public String edit_rtn_details(Connection con,RETURN_NOTE rtn_details)
{
	Statement stmt=null;
	String sql;
	String errMsg="";
	int recordsUpdated=0;
	try
	{
		stmt=con.createStatement();
		sql="update RETURN_NOTE set RTNOTE_DATE = "+krrcommon.AppendSinlequote(rtn_details.getRTNOTE_DATE())+",";
		sql=sql+"RTNOTE_GPNO = "+(rtn_details.getRTNOTE_GPNO())+",";
		sql=sql+"RTNOTE_GPDATE = "+krrcommon.AppendSinlequote(rtn_details.getRTNOTE_GPDATE())+",";
		sql=sql+"RTNOTE_VENDORCODE = "+(rtn_details.getRTNOTE_VENDORCODE())+",";
		sql=sql+"RTNOTE_VENDORNAME = "+krrcommon.AppendSinlequote(rtn_details.getRTNOTE_VENDORNAME())+",";
		sql=sql+"RTNOTE_QTYRETURNED = "+(rtn_details.getRTNOTE_QTYRETURNED())+",";
		sql=sql+"RTNOTE_ITEMCODE = "+(rtn_details.getRTNOTE_ITEMCODE())+",";
		sql=sql+"RTNOTE_ITEMMAKE = "+krrcommon.AppendSinlequote(rtn_details.getRTNOTE_ITEMMAKE())+",";
		sql=sql+"RTNOTE_ITEMDESCRIPTION = "+krrcommon.AppendSinlequote(rtn_details.getRTNOTE_ITEMDESCRIPTION())+",";
		sql=sql+"RTNOTE_PURPOSEOFRETURN = "+krrcommon.AppendSinlequote(rtn_details.getRTNOTE_PURPOSEOFRETURN())+"";
		sql=sql+"where RTNOTE_NO = "+(rtn_details.getRTNOTE_NO());
		System.out.println(sql);
		recordsUpdated=stmt.executeUpdate(sql);
		if(recordsUpdated==0)
		{
			errMsg="Record not updated";
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(stmt!=null)stmt.close();
		}
	
	catch (Exception e2) {
	e2.printStackTrace();
	}
	}
	return errMsg;
	
}
public boolean is_rtn_exists(Connection con,long returnNo)
{
	boolean rtnExists = false;
	Statement stmt = null;
	String sql;
	ResultSet rs = null;
	try{
		stmt = con.createStatement();
		sql= "select * from RETURN_NOTE where RTNOTE_NO = "+returnNo;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			rtnExists = true;
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
   finally
   {
	   try
	   {
		   if(stmt !=null)stmt.close();
		   if(rs !=null)rs.close();
	   }
	   catch (Exception e) {
		e.printStackTrace();
	}
   }
	return rtnExists;
}



public String get_mrn_status(Connection con,long return_No)
{
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	String STATUS="";
	String l_ers="";
	try
	{
	stmt=con.createStatement();
	sql="SELECT RTNOTE_STATUS FROM RETURN_NOTE WHERE RTNOTE_NO="+return_No;
	System.out.println(sql);
	rs=stmt.executeQuery(sql);
	if(rs.next())
	{
		STATUS=rs.getString("RTNOTE_STATUS");
	}
	}
	catch(Exception e)
	{
		l_ers="Error in getting status "+e.getMessage();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(stmt !=null) stmt.close();
			if(rs !=null) rs.close();
		}
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}
	}
	return STATUS;	
} 
public boolean gp_header_Exists(Connection con,long gpNo)
{
	boolean gpNoExists = false;
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	try
	{
		stmt=con.createStatement();
		sql="SELECT * FROM GP_HEADER WHERE GPNO  = "+gpNo;
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			gpNoExists = true;
		}
	}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs !=null) rs.close();
				if(stmt !=null) stmt.close();
			}
			catch (Exception e2)
			{ 
			    e2.printStackTrace();
			}
	}
	return gpNoExists;
}
public String getGpNo(Connection con,GP_HEADER GPHEADER){
	
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	String errMsg="";
	try{
		stmt = con.createStatement();
		sql= "SELECT * FROM GP_HEADER WHERE GPNO = "+GPHEADER.getGPNO()+" and status ='APPROVED' ";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		if (rs.next()) 
		{
			errMsg=Trans_gp_header(rs,GPHEADER);
		}
		else
		{
			errMsg=" Enter Approved gp no ";
		}
		
	}catch(Exception e){
		errMsg="Error in gp header "+e.getMessage();
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
	return errMsg;
}
public String getGpItem(Connection con,GP_DETAILS GPDETAILS){
	
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	String errMsg="";
	try{
		stmt = con.createStatement();
		sql= "SELECT * FROM GP_DETAILS WHERE ITEMCODE  = "+GPDETAILS.getITEMCODE()+" AND GPNO = "+GPDETAILS.getGPNO();
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		if (rs.next()) 
		{
			errMsg=Trans_gp_deatils(rs,GPDETAILS);
		}
		else 
		{
			errMsg="Item code doesn't exists";
		}
	}catch(Exception e){
		errMsg="Error in gp details "+e.getMessage();
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

	return errMsg;
}

public String Trans_gp_deatils(ResultSet rs,bean.GP_DETAILS gpdetails)
{
	String errMsg="";
	try
	{
		gpdetails.setGPNO(rs.getLong("GPNO"));
		gpdetails.setITEMCODE(rs.getLong("ITEMCODE"));
		gpdetails.setITEMMAKE(rs.getString("ITEMMAKE"));
		gpdetails.setITEMDESCRIPTION(rs.getString("ITEMDESCRIPTION"));
		gpdetails.setISSUEQTY(rs.getLong("ISSUEQTY"));
		gpdetails.setREMARKS(rs.getString("REMARKS"));
		gpdetails.setCREATEDBY(rs.getString("CREATEDBY"));
		gpdetails.setCREATEDDATE(rs.getLong("CREATEDDATE"));
	}
	catch (Exception e) {
		errMsg="Error in gp details "+e.getMessage();
		e.printStackTrace();
	}
	return errMsg;
	
}
public String Trans_gp_header(ResultSet rs,bean.GP_HEADER gpno)
{
	String errMsg="";
	try
	{
		gpno.setGPNO(rs.getLong("GPNO"));
		gpno.setGPDATE(rs.getString("GPDATE"));
		gpno.setGPTYPE(rs.getString("GPTYPE"));
		gpno.setGPDATE(date_library.date_Conv(gpno.getGPDATE(), "yyyyMMdd","ddMMyyyy"));
		gpno.setISSUEDLOCATION(rs.getString("ISSUEDLOCATION"));
		gpno.setVENDOR_CODE(rs.getLong("VENDOR_CODE"));
		gpno.setVENDOR_NAME(rs.getString("VENDOR_NAME"));
		gpno.setVENDOR_ADDRESSLINE1(rs.getString("VENDOR_ADDRESSLINE1"));
		gpno.setVENDOR_ADDRESSLINE2(rs.getString("VENDOR_ADDRESSLINE2"));
		gpno.setVENDOR_ADDRESSLINE3(rs.getString("VENDOR_ADDRESSLINE3"));
		gpno.setVENDOR_ADDRESSLINE4(rs.getString("VENDOR_ADDRESSLINE4"));
		gpno.setVENDOR_CITY(rs.getString("VENDOR_CITY"));
		gpno.setVENDOR_DISTRICT(rs.getString("VENDOR_DISTRICT"));
		gpno.setVENDOR_STATE(rs.getString("VENDOR_STATE"));
		gpno.setVENDOR_COUNTRY(rs.getString("VENDOR_COUNTRY"));
		gpno.setVENDOR_PIN(rs.getLong("VENDOR_PIN"));
		gpno.setVENDOR_PHONENO(rs.getLong("VENDOR_PHONENO"));
		gpno.setISSUEDPROJECT(rs.getString("ISSUEDPROJECT"));
		gpno.setISSUEDLOCATION(rs.getString("ISSUEDLOCATION"));
		gpno.setSTATUS(rs.getString("STATUS"));
		gpno.setCREATEDBY(rs.getString("CREATEDBY"));
		gpno.setCREATEDDATE(rs.getLong("CREATEDDATE"));
		
	}
	catch (Exception e) {
		errMsg="Error in trans details "+e.getMessage();
		e.printStackTrace();
	}
	return errMsg;
	
}

public String change_rtn_status(Connection con,RETURN_NOTE rtn,String status )
{
	Statement stmt=null;
	String sql;
	String errMsg="";
	int recordsUpdate=0;
	try
	{
		stmt=con.createStatement();
		sql="UPDATE RETURN_NOTE SET RTNOTE_STATUS = "+ krrcommon.AppendSinlequote(status)+",";
		sql=sql+"RTNOTE_APPROVEDBY = "+krrcommon.AppendSinlequote(rtn.getRTNOTE_APPROVEDBY())+",";
		sql=sql+"RTNOTE_APPROVEDDATE = "+krrcommon.AppendSinlequote(rtn.getRTNOTE_APPROVEDDATE());
		sql=sql+" WHERE  RTNOTE_NO= "+rtn.getRTNOTE_NO();
		System.out.println(sql);
		recordsUpdate=stmt.executeUpdate(sql);
		rtn.setRTNOTE_NO(rtn.getRTNOTE_NO());
		errMsg=get_rtn(con, rtn);
		STOCK_SUMMARY STOCKSUMMARY = new STOCK_SUMMARY();
		errMsg=errMsg+get_Stock_Summary_details(con, STOCKSUMMARY, rtn);
		if(krrcommon.isValue(errMsg))
		{
			con.rollback();
		}
		else
		{
			con.commit();
		}
		if(recordsUpdate==0)
		{
			errMsg="Record not update";
		}
	}
	catch (Exception e) {
	      
		errMsg="Error in rtn status details";
		e.printStackTrace();
	}
	finally
	{
	try
	{
		if(stmt!=null)stmt.close();
	}
	catch (Exception e2) {
		e2.printStackTrace();	
		
	}
	}
	return errMsg;
	
}
public String get_rtn(Connection con,RETURN_NOTE returnnote)
{
	Statement stmt=null;
	String sql;
	String errMsg="";
	ResultSet rs=null;
	try
	{
		stmt=con.createStatement();
		sql="SELECT * FROM RETURN_NOTE WHERE RTNOTE_NO = "+returnnote.getRTNOTE_NO();
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			errMsg=trans_rtn_details(rs, returnnote);
		}
	}
	catch (Exception e) {
		errMsg="Error in get rtn details "+e.getMessage();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	return errMsg;
	
}
public boolean is_qty_returned(Connection con,long qtyissued,long gpno,long itemcode)
  {
	boolean qtyExits = false;
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	try
	{
		stmt = con.createStatement();
		sql=" SELECT * FROM GP_DETAILS WHERE ISSUEQTY >= "+ qtyissued +" AND GPNO = " +gpno+" AND ITEMCODE ="+itemcode;
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			qtyExits = true;
		}
  }
	catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(rs !=null)rs.close();
			if(stmt !=null)stmt.close();
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	return qtyExits;
}
//public String sum_exits(Connection con,long gpno,long itemcode)
//{
//	
//	Statement stmt=null;
//	String sql;
//	ResultSet rs=null;
//	String errMsg="";
//	try
//	{
//		stmt = con.createStatement();
//		sql="SELECT SUM(ISSUEQTY) FROM GP_DETAILS WHERE GP_NO = "+ gpno +" AND ITEM_CODE  = "+itemcode;
//		System.out.println(sql);
//		rs=stmt.executeQuery(sql);
//		
//	}
//	catch (Exception e) {
//	    errMsg="Error in sum gp details "+e.getMessage();
//		e.printStackTrace();
//	}
//	finally 
//	{
//		try
//		{
//			if(rs!=null)rs.close();
//			if(stmt!=null)stmt.close();
//		}
//		catch (Exception e2) {
//			e2.printStackTrace();
//		}
//
//	}
//	return errMsg;
	
public String sumqty(Connection con,long gpno,long itemcode,RETURN_NOTE returnnote)
{
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	String errMsg="";
	long qty_returned=0;
	try
	{
		stmt=con.createStatement();
		sql="SELECT SUM(RTNOTE_QTYRETURNED) FROM RETURN_NOTE WHERE RTNOTE_GPNO = "+gpno +" AND RTNOTE_ITEMCODE = "+itemcode;
		System.out.println(sql);
		rs=stmt.executeQuery(sql);
		
		if(rs.next())
		{
			qty_returned=rs.getLong(1);
			returnnote.setRTNOTE_QTYRETURNED(qty_returned);
		}
	}
	catch (Exception e) {
		errMsg="Error in qty returned "+e.getMessage();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(rs!=null)rs.close();
			if(stmt!=null)rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
		return errMsg;
    }
public String issueqty(Connection con,long gpno,long itemcode,GP_DETAILS gpdetails)
{
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	String errMsg="";
	try
	{
		stmt=con.createStatement();
		sql="SELECT * FROM GP_DETAILS WHERE GPNO = "+ gpno +" AND ITEMCODE = "+itemcode;
		System.out.println(sql);
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			errMsg=Trans_gp_deatils(rs, gpdetails);
		}
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	finally
	{
		try
		{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	return errMsg;
	
}
public String insert_STOCK_SUMMARY(Connection con,STOCK_SUMMARY STOCKSUMMARY)
{
	Statement stmt=null;
	String errMsg="";
	String sql;
	try{
		stmt=con.createStatement();
	    long seqId=krrcommon.Nextval_SQ("STOCK_SUMMARY_SQ", con);
	    STOCKSUMMARY.setSTOCKMASTERSRNO(seqId);
	    if(seqId>0)
	    {
	    	sql="INSERT INTO STOCK_SUMMARY(";
	        sql=sql+"STOCKMASTERSRNO ,";
	        sql=sql+"ITEMCODE ,";
	        sql=sql+"SUMMARYDATE ,";
	        sql=sql+"ITEMDESCRIPTION ,";
	        sql=sql+"OPENINGBALANCE ,";
	        sql=sql+"RECEIPTS ,";
	        sql=sql+"ISSUES ,";
	        sql=sql+"CLOSEINGBALANCE ,";
	        sql=sql+"CREATEDBY ,";
	        sql=sql+"CREATEDDATE ";
	        sql=sql+")VALUES (";
	        sql=sql+STOCKSUMMARY.getSTOCKMASTERSRNO()+",";
	        sql=sql+STOCKSUMMARY.getITEMCODE()+",";
	        sql=sql+krrcommon.AppendSinlequote(STOCKSUMMARY.getSUMMARYDATE())+",";
	        sql=sql+krrcommon.AppendSinlequote(STOCKSUMMARY.getITEMDESCRIPTION())+",";
	        sql=sql+STOCKSUMMARY.getOPENINGBALANCE()+",";
	        sql=sql+STOCKSUMMARY.getRECEIPTS()+",";
	        sql=sql+STOCKSUMMARY.getISSUES()+",";
	        sql=sql+STOCKSUMMARY.getCLOSINGBALANCE()+",";
	        sql=sql+krrcommon.AppendSinlequote(STOCKSUMMARY.getCREATEDBY())+",";
	        sql=sql+krrcommon.AppendSinlequote(STOCKSUMMARY.getCREATEDDATE())+" ) ";
	        System.out.println(sql);
	        stmt.execute(sql);
	    }
	    else
	    {
	    	errMsg="Stock summary doesn't exists";
	    }
	}
	catch (Exception e) {
		errMsg="Error in stock summary "+e.getMessage();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(stmt !=null)stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	return errMsg;
}
public String Trans_stock_details(ResultSet rs,bean.STOCK_SUMMARY STOCKSUMMARY)
{
	String errMsg="";
	
		try
		{
			STOCKSUMMARY.setSTOCKMASTERSRNO(rs.getLong("STOCKMASTERSRNO"));
			STOCKSUMMARY.setITEMCODE(rs.getLong("ITEMCODE"));
			STOCKSUMMARY.setSUMMARYDATE(rs.getString("SUMMARYDATE"));
			STOCKSUMMARY.setITEMDESCRIPTION(rs.getString("ITEMDESCRIPTION"));
			STOCKSUMMARY.setOPENINGBALANCE(rs.getLong("OPENINGBALANCE"));
			STOCKSUMMARY.setRECEIPTS(rs.getLong("RECEIPTS"));
			STOCKSUMMARY.setISSUES(rs.getLong("ISSUES"));
			STOCKSUMMARY.setCLOSINGBALANCE(rs.getLong("CLOSEINGBALANCE"));
			STOCKSUMMARY.setCREATEDBY(rs.getString("CREATEDBY"));
			STOCKSUMMARY.setCREATEDDATE(rs.getString("CREATEDDATE"));
		}
		catch (Exception e) {
			errMsg="Error in trans stock details "+e.getMessage();
			e.printStackTrace();
		}
		return errMsg;
}
public String upadte_stock_summary(Connection con,bean.STOCK_SUMMARY STOCKSUMMARY)
{
	Statement stmt=null;
	String sql;
	String errMsg="";
	int recordsUpadted=0;
	try
	{
		stmt=con.createStatement();
		sql="update STOCK_SUMMARY set ITEMCODE = "+STOCKSUMMARY.getITEMCODE()+",";
		sql=sql+"ITEMDESCRIPTION = "+krrcommon.AppendSinlequote(STOCKSUMMARY.getITEMDESCRIPTION())+",";
		sql=sql+"SUMMARYDATE = "+date_library.display_Current_Date("yyyyMMdd")+",";
		sql=sql+"OPENINGBALANCE = "+STOCKSUMMARY.getOPENINGBALANCE()+",";
		sql=sql+"RECEIPTS = "+STOCKSUMMARY.getRECEIPTS()+",";
		sql=sql+"ISSUES = "+STOCKSUMMARY.getISSUES()+",";
		sql=sql+"CLOSEINGBALANCE = "+STOCKSUMMARY.getCLOSINGBALANCE()+",";
		sql=sql+"CREATEDBY = "+krrcommon.AppendSinlequote(STOCKSUMMARY.getCREATEDBY())+",";
		sql=sql+"CREATEDDATE = "+krrcommon.AppendSinlequote(STOCKSUMMARY.getCREATEDDATE());
		sql=sql+" where ITEMCODE = "+STOCKSUMMARY.getITEMCODE();
		System.out.println(sql);
		recordsUpadted=stmt.executeUpdate(sql);
		if(recordsUpadted==0)
		{
			errMsg="Record not updated in stock summary ";
		}
	}
	catch (Exception e) {
		errMsg="Error in update stock summary "+e.getMessage();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(stmt!=null)stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	return errMsg;
	
}
public String get_Stock_Summary_details(Connection con,STOCK_SUMMARY STOCKSUMMARY,RETURN_NOTE RETURNNOTE)
{
	Statement stmt=null;
	String sql;
	ResultSet rs=null;
	String l_ers="";
	double opening_balance=0;
	double  closing_balance=0;
	long receipts=0;
	try
	{
	stmt=con.createStatement();
	sql="SELECT * FROM STOCK_SUMMARY WHERE SUMMARYDATE = "+date_library.display_Current_Date("yyyyMMdd") +" AND ITEMCODE = "+RETURNNOTE.getRTNOTE_ITEMCODE();
	System.out.println(sql);
	rs=stmt.executeQuery(sql);
	STOCKSUMMARY.setITEMCODE(RETURNNOTE.getRTNOTE_ITEMCODE());
	STOCKSUMMARY.setITEMDESCRIPTION(RETURNNOTE.getRTNOTE_ITEMDESCRIPTION());
	STOCKSUMMARY.setCREATEDBY(RETURNNOTE.getRTNOTE_APPROVEDBY());
	STOCKSUMMARY.setCREATEDDATE(RETURNNOTE.getRTNOTE_APPROVEDDATE());
	STOCKSUMMARY.setSUMMARYDATE(date_library.display_Current_Date("yyyyMMdd"));
	STOCKSUMMARY.setISSUES(0);

	if(rs.next())
	{
		l_ers=Trans_stock_details(rs, STOCKSUMMARY);
		opening_balance=STOCKSUMMARY.getCLOSINGBALANCE();
		receipts=STOCKSUMMARY.getRECEIPTS()+RETURNNOTE.getRTNOTE_QTYRETURNED();
		closing_balance=STOCKSUMMARY.getCLOSINGBALANCE()+RETURNNOTE.getRTNOTE_QTYRETURNED();
		STOCKSUMMARY.setISSUES(STOCKSUMMARY.getISSUES());
		STOCKSUMMARY.setCLOSINGBALANCE(closing_balance);
		STOCKSUMMARY.setOPENINGBALANCE(opening_balance);
		STOCKSUMMARY.setRECEIPTS(receipts);
		l_ers=l_ers+upadte_stock_summary(con, STOCKSUMMARY);
	}
	else
	{
		sql="SELECT * FROM STOCK_SUMMARY WHERE ITEMCODE = "+RETURNNOTE.getRTNOTE_ITEMCODE();
		System.out.println(sql);
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			l_ers=l_ers+Trans_stock_details(rs, STOCKSUMMARY);
			opening_balance=STOCKSUMMARY.getCLOSINGBALANCE();
			receipts=RETURNNOTE.getRTNOTE_QTYRETURNED();
			closing_balance=RETURNNOTE.getRTNOTE_QTYRETURNED()+STOCKSUMMARY.getCLOSINGBALANCE();
			STOCKSUMMARY.setOPENINGBALANCE(opening_balance);
			STOCKSUMMARY.setRECEIPTS(receipts);
			STOCKSUMMARY.setCLOSINGBALANCE(closing_balance);
			l_ers=l_ers+insert_STOCK_SUMMARY(con, STOCKSUMMARY);
		}
		else
		{
			STOCKSUMMARY.setOPENINGBALANCE(0);
			STOCKSUMMARY.setRECEIPTS(RETURNNOTE.getRTNOTE_QTYRETURNED());
			STOCKSUMMARY.setCLOSINGBALANCE(RETURNNOTE.getRTNOTE_QTYRETURNED());
			l_ers=l_ers+insert_STOCK_SUMMARY(con, STOCKSUMMARY);
		}
	}
	}
	catch (Exception e) {
		l_ers="Error in return stock summary deatils "+e.getMessage();
		e.printStackTrace();
	}
	finally{
		try
		{
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
		}
		
catch (Exception e2) {
	e2.printStackTrace();
}		
	}
	return l_ers;
}
//public String update_rtn_returnqty(Connection con,bean.RETURN_NOTE RETURNNOTE)
//{
//	Statement stmt=null;
//	String sql;
//	String l_ers="";
//	int recordsUpdated=0;
//	try
//	{
//		stmt=con.createStatement();
//		sql="UPDATE RETURN_NOTE SET RTNOTE_QTYRETURNED = "+RETURNNOTE.getRTNOTE_QTYRETURNED();
//		sql=sql+" WHERE RTNOTE_GPNO = "+RETURNNOTE.getRTNOTE_GPNO() +" AND RTNOTE_ITEMCODE = "+RETURNNOTE.getRTNOTE_ITEMCODE();
//		System.out.println(sql);
//		recordsUpdated=stmt.executeUpdate(sql);
//		if(recordsUpdated==0)
//		{
//			l_ers="Record not update in return qty";
//		}
//	}
//	catch (Exception e) {
//		l_ers="Error in rtn returnqty "+e.getMessage();
//		e.printStackTrace();
//	}
//	finally{
//		try
//		{
//			if(stmt!=null)stmt.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	return l_ers;
//	
//}
}