package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.KrrCommon;
import bean.ITEM_CommonData;
import bean.ITEM_DETAILS;
import bean.STOCK_SUMMARY;

public class ITEM_DB 
{
	KrrCommon krrCommon = new KrrCommon();
	Date_library Date_library=new Date_library();
	public String get_item_details(Connection con,List<ITEM_DETAILS> ITEMDETAILSLIST,ITEM_CommonData commonData)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		String condition="";
		try
		{
			stmt = con.createStatement();
			sql="select * from ITEM_DETAILS";
			if(commonData.getITEMCODE()!=0){
				condition = condition +" ITEMCODE = "+commonData.getITEMCODE();
			}
			if(krrCommon.isValue(commonData.getITEMGROUP())){
				condition = condition+" and upper(ITEMGROUP) LIKE "+krrCommon.AppendPercentileBothEnds(commonData.getITEMGROUP());			
			}
			if(krrCommon.isValue(commonData.getITEMMAKE())){
				condition = condition +" and upper(ITEMMAKE) LIKE "+krrCommon.AppendPercentileBothEnds(commonData.getITEMMAKE());
			}
			if(krrCommon.isValue(commonData.getITEMSTATUS())){
				condition = condition +" and ITEMSTATUS = "+krrCommon.AppendSinlequote(commonData.getITEMSTATUS());
			}
			if(krrCommon.isValue(commonData.getITEMDESCRIPTION()))
			{
				condition = condition +" and upper (ITEMDESCRIPTION) LIKE "+krrCommon.AppendPercentileBothEnds(commonData.getITEMDESCRIPTION());
			}
			if(condition !="")
			{
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and"))
				{
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+" ";	
			}	
			sql=sql+" order by ITEMCODE ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				ITEM_DETAILS ITEMDETAILS = new ITEM_DETAILS();
				errMsg=trans_item_details(rs, ITEMDETAILS);
				ITEMDETAILSLIST.add(ITEMDETAILS);
				i++;
			}
		}catch(Exception e)
		{
			errMsg="Error searching item details "+ e.getMessage();
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


	public String update_item_details(Connection con,ITEM_DETAILS ITEMDETAILS)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		int recordsUpdated=0;
		try
		{
			stmt = con.createStatement();
			sql= "update ITEM_DETAILS set ITEMGROUP = " +krrCommon.AppendSinlequote(ITEMDETAILS.getITEMGROUP())+",";
			sql= sql+"ITEMMAKE = " +krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMAKE())+",";
			sql= sql+"ITEMMODEL = " +krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMODEL())+",";
			sql= sql+"ITEMDESCRIPTION = " +krrCommon.AppendSinlequote(ITEMDETAILS.getITEMDESCRIPTION())+",";
			sql= sql+"ITEMUNITS = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMUNITS())+",";
			sql=sql+"ITEMSPECIALITEM = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMSPECIALITEM())+",";
			sql=sql+"ITEMIMPORTEDITEM = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMIMPORTEDITEM());
			sql=sql+"where ITEMCODE = " +ITEMDETAILS.getITEMCODE();
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			if(recordsUpdated==0)
			{
				errMsg="Records not updated";
			}

		}
		catch(Exception e)
		{
			errMsg="Error in update item Details process"+e.getMessage();
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
	
	public String update_stock_summary(Connection con,STOCK_SUMMARY STOCKSUMMARY)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		int recordsUpdated=0;
		try
		{
			stmt = con.createStatement();
			sql= "update STOCK_SUMMARY set OPENINGBALANCE = " +STOCKSUMMARY.getOPENINGBALANCE()+",";
			sql=sql+"CLOSINGBALANCE = " +STOCKSUMMARY.getCLOSINGBALANCE()+",";
			sql=sql+"SUMMARYDATE = " +krrCommon.AppendSinlequote(STOCKSUMMARY.getSUMMARYDATE());
			sql=sql+" where ITEMCODE = " +STOCKSUMMARY.getITEMCODE();
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			if(recordsUpdated==0)
			{
				errMsg="Records not updated";
			}
		}
		catch(Exception e)
		{
			errMsg="Error in update stock summary Details process"+e.getMessage();
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

	public String get_item_status(Connection con,long itemSrNo)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String ITEMSTATUS="";
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select ITEMSTATUS FROM ITEM_DETAILS where ITEMCODE="+itemSrNo;
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) 
			{
				ITEMSTATUS=rs.getString("ITEMSTATUS");
			}
		}
		catch(Exception e)
		{
			l_ers="Error in getting item status"+e.getMessage();
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
		return ITEMSTATUS;
	}
	
	public String change_item_status(Connection con,long itemSrNo,String status) 
	{
		Statement stmt=null;
		String sql;
		int recordsUpdated=0;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "update ITEM_DETAILS set ITEMSTATUS = "+krrCommon.AppendSinlequote(status)+" where ITEMCODE="+itemSrNo;
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			if(recordsUpdated==0)
			{
				l_ers="Records not updated";
			}
		}
		catch(Exception e)
		{
			l_ers="Error in item changing status"+e.getMessage();
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
		return l_ers;
	}


	public boolean is_item_exits(Connection con,ITEM_DETAILS ITEMDETAILS)
	{
		boolean itemExits = false;
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from ITEM_DETAILS where ITEMGROUP = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMGROUP())+"";
			sql= sql+" and ITEMMAKE = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMAKE())+" and ITEMMODEL = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMODEL());
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				itemExits = true;
				System.out.println("in if is item exists");
			}
		}
		catch(Exception e)
		{
			l_ers="error in getting item details"+e.getMessage();
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
		return itemExits;
	}
	
	public String is_itemCode_exits(Connection con,ITEM_DETAILS ITEMDETAILS)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select ITEMCODE from ITEM_DETAILS where ITEMGROUP = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMGROUP())+"";
			sql= sql+" and ITEMMAKE = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMAKE())+" and ITEMMODEL = "+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMODEL());
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				ITEMDETAILS.setITEMCODE(rs.getLong("ITEMCODE"));
			}
			else
			{
				l_ers="Item does not exists for this group";
			}
		}
		catch(Exception e)
		{
			l_ers="error in getting item details"+e.getMessage();
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
		return l_ers;
	}

	public boolean is_code_exits(Connection con,long code)
	{
		boolean codeExits = false;
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from STOCK_SUMMARY where ITEMCODE = "+code;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				codeExits = true;
			}
		}
		catch(Exception e)
		{
			l_ers="error in checking item code"+e.getMessage();
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

//	public boolean is_item_Active(Connection con,long code)
//	{
//	boolean codeExits = false;
//	Statement stmt;
//	String sql;
//	ResultSet rs;
//	String l_ers="";
//	try{
//	stmt = con.createStatement();
//	sql= "select * from ITEM_DETAILS where ITEM_CODE_SRNO = "+code +" and ITEM_STATUS='ACTIVE'";
//	System.out.println(sql);
//	rs = stmt.executeQuery(sql);
//	if (rs.next()) {
//	codeExits = true;
//	}
//	System.out.println(codeExits);
//	}catch(Exception e)
//	{
//	l_ers="error in checking item code"+e.getMessage();
//	e.printStackTrace();
//	}

//	return codeExits;
//	}
	

	public String insert_item_details(Connection con, ITEM_DETAILS ITEMDETAILS,STOCK_SUMMARY STOCKSUMMARY)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		try
		{
			stmt = con.createStatement();	
			long seqId=krrCommon.Nextval_SQ("ITEM_DETAILS_SQ", con);
			ITEMDETAILS.setITEMCODE(seqId);	
			if(seqId>0)
			{
				sql = "insert into ITEM_DETAILS (";
				sql=sql+"ITEMCODE,";
				sql=sql+"ITEMGROUP,";
				sql=sql+"ITEMMAKE,";
				sql=sql+"ITEMMODEL,";
				sql=sql+"ITEMDESCRIPTION,";
				sql=sql+"ITEMUNITS,";
				sql=sql+"ITEMSPECIALITEM,";
				sql=sql+"ITEMIMPORTEDITEM,";
				sql=sql+"ITEMSTATUS,";
				sql=sql+"ITEMCREATEDBY,";
				sql=sql+"ITEMCREATEDDATE";
				sql=sql+") VALUES (";
				sql=sql+ITEMDETAILS.getITEMCODE()+",";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMGROUP())+",";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMAKE())+",";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMMODEL())+",";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMDESCRIPTION())+",";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMUNITS())+", ";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMSPECIALITEM())+", ";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMIMPORTEDITEM())+", ";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMSTATUS())+", ";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMCREATEDBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(ITEMDETAILS.getITEMCREATEDDATE())+") ";
				System.out.println(sql);
				stmt.execute(sql);
				STOCKSUMMARY.setITEMCODE(ITEMDETAILS.getITEMCODE());
				errMsg=insert_STOCK_SUMMARY(con, STOCKSUMMARY);
			}
			else
			{
				errMsg="ITEM serial number does not exits";
			}
		}
		catch(Exception e)
		{
			errMsg="Error in inserting item details"+e.getMessage();
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

	public String  read_item_details(Connection con,ITEM_DETAILS ITEMDETAILS) 
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from ITEM_DETAILS where ITEMCODE = "+ITEMDETAILS.getITEMCODE();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				trans_item_details(rs, ITEMDETAILS);
			}
		}
		catch (Exception e) {
			l_ers="error in geting item details"+e.getMessage();
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
		return l_ers;
	}
	
	public String  get_StockSummary_details(Connection con,STOCK_SUMMARY STOCKSUMMARY) 
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from STOCK_SUMMARY where ITEMCODE = "+STOCKSUMMARY.getITEMCODE();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				trans_Stock_Summary(rs, STOCKSUMMARY);
			}
		}
		catch (Exception e) {
			l_ers="error in getting stock summary details"+e.getMessage();
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
		return l_ers;
	}

	public String trans_item_details(ResultSet rs,ITEM_DETAILS ITEMDETAILS)
	{
		String l_ers="";
		try
		{
			ITEMDETAILS.setITEMGROUP(rs.getString("ITEMGROUP"));
			ITEMDETAILS.setITEMMAKE(rs.getString("ITEMMAKE"));
			ITEMDETAILS.setITEMMODEL(rs.getString("ITEMMODEL"));
			ITEMDETAILS.setITEMUNITS(rs.getString("ITEMUNITS"));
			ITEMDETAILS.setITEMDESCRIPTION(rs.getString("ITEMDESCRIPTION"));
			ITEMDETAILS.setITEMSPECIALITEM(rs.getString("ITEMSPECIALITEM"));
			ITEMDETAILS.setITEMIMPORTEDITEM(rs.getString("ITEMIMPORTEDITEM"));
			ITEMDETAILS.setITEMCREATEDBY(rs.getString("ITEMCREATEDBY"));
			ITEMDETAILS.setITEMCREATEDDATE(rs.getString("ITEMCREATEDDATE"));
			ITEMDETAILS.setITEMCODE(rs.getLong("ITEMCODE"));
			ITEMDETAILS.setITEMSTATUS(rs.getString("ITEMSTATUS"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in get item status results "+e.getMessage();
		}
		return l_ers;
	}
	
	public String trans_Stock_Summary(ResultSet rs,STOCK_SUMMARY STOCKSUMMARY)
	{
		String l_ers="";
		try
		{
			STOCKSUMMARY.setITEMCODE(rs.getLong("ITEMCODE"));
			STOCKSUMMARY.setSUMMARYDATE(rs.getString("SUMMARYDATE"));
			STOCKSUMMARY.setITEMDESCRIPTION(rs.getString("ITEMDESCRIPTION"));
			STOCKSUMMARY.setOPENINGBALANCE(rs.getDouble("OPENINGBALANCE"));
			STOCKSUMMARY.setRECEIPTS(rs.getLong("RECEIPTS"));
			STOCKSUMMARY.setISSUES(rs.getLong("ISSUES"));
			STOCKSUMMARY.setCLOSINGBALANCE(rs.getLong("CLOSINGBALANCE"));
			STOCKSUMMARY.setCREATEDDATE(rs.getString("CREATEDDATE"));
			STOCKSUMMARY.setCREATEDBY(rs.getString("CREATEDBY"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in get stock Summary details "+e.getMessage();
		}
		return l_ers;
	}

	public String get_item_details_active(Connection con,List<ITEM_DETAILS> ITEMDETAILSLIST,ITEM_CommonData commonData)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		String condition="";
		try
		{
			stmt = con.createStatement();
			sql="select * from ITEM_DETAILS";
			if(commonData.getITEMCODE()!=0)
			{
				condition = condition +" ITEMCODE = "+commonData.getITEMCODE();
			}
			if(krrCommon.isValue(commonData.getITEMGROUP()))
			{
				condition = condition+" and upper(ITEMGROUP) LIKE "+krrCommon.AppendPercentileBothEnds(commonData.getITEMGROUP());			
			}
			if(krrCommon.isValue(commonData.getITEMMAKE()))
			{
				condition = condition +" and upper(ITEMMAKE) LIKE "+krrCommon.AppendPercentileBothEnds(commonData.getITEMMAKE());
			}
			commonData.setITEMSTATUS("ACTIVE");
			if(krrCommon.isValue(commonData.getITEMSTATUS()))
			{
				condition = condition +" and ITEMSTATUS = "+krrCommon.AppendSinlequote(commonData.getITEMSTATUS());
			}
			if(krrCommon.isValue(commonData.getITEMDESCRIPTION()))
			{
				condition = condition +" and upper (ITEMDESCRIPTION) LIKE "+krrCommon.AppendPercentileBothEnds(commonData.getITEMDESCRIPTION());
			}
			if(condition !="")
			{
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and"))
				{
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+"  ";	
			}	
			sql=sql+" order by ITEMCODE";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				ITEM_DETAILS ITEMDETAILS = new ITEM_DETAILS();
				errMsg=trans_item_details(rs, ITEMDETAILS);
				ITEMDETAILSLIST.add(ITEMDETAILS);
				i++;
			}
		}catch(Exception e)
		{
			errMsg="Error searching item details "+ e.getMessage();
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

	public String get_item_details_PO(Connection con,bean.ITEM_DETAILS ITEMDETAILS)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select ITEMMAKE,ITEMDESCRIPTION FROM ITEM_DETAILS where ITEMCODE= "+ITEMDETAILS.getITEMCODE()+" and ITEMSTATUS='ACTIVE'";
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) 
			{
				ITEMDETAILS.setITEMMAKE(rs.getString("ITEMMAKE"));
				ITEMDETAILS.setITEMDESCRIPTION(rs.getString("ITEMDESCRIPTION"));
			}
			else
			{
				l_ers="item code should be exists and it should be in active";
			}
		}
		catch(Exception e)
		{
			l_ers="Error in getting item details"+e.getMessage();
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
		return l_ers;
	}
	
	
	
	public String insert_STOCK_SUMMARY(Connection con, bean.STOCK_SUMMARY STOCKSUMMARY)   
	{
		String errMsg="";
		Statement stmt=null;
		String sql="";
		try{
			stmt=con.createStatement();
			long seqId=krrCommon.Nextval_SQ("STOCK_SUMMARY_SQ", con);
			STOCKSUMMARY.setSTOCKMASTERSRNO(seqId);
			if(seqId>0)
			{
				sql="INSERT INTO STOCK_SUMMARY(";
				sql=sql+"STOCKMASTERSRNO,";
				sql=sql+"ITEMCODE, ";
				sql=sql+"SUMMARYDATE, ";
				sql=sql+"ITEMDESCRIPTION,";
				sql=sql+"OPENINGBALANCE, ";
				sql=sql+"RECEIPTS, ";
				sql=sql+"ISSUES, ";
				sql=sql+"CLOSINGBALANCE, ";
				sql=sql+"CREATEDBY, ";
				sql=sql+"CREATEDDATE";
				sql=sql+") VALUES (";
				sql=sql+STOCKSUMMARY.getSTOCKMASTERSRNO()+",";
				sql=sql+STOCKSUMMARY.getITEMCODE()+",";
				sql=sql+krrCommon.AppendSinlequote(STOCKSUMMARY.getSUMMARYDATE())+",";
				sql=sql+krrCommon.AppendSinlequote(STOCKSUMMARY.getITEMDESCRIPTION())+",";
				sql=sql+STOCKSUMMARY.getOPENINGBALANCE()+",";
				sql=sql+STOCKSUMMARY.getRECEIPTS()+",";
				sql=sql+STOCKSUMMARY.getISSUES()+",";
				sql=sql+STOCKSUMMARY.getCLOSINGBALANCE()+",";
				sql=sql+krrCommon.AppendSinlequote(STOCKSUMMARY.getCREATEDBY())+",";
				sql=sql+krrCommon.AppendSinlequote(STOCKSUMMARY.getCREATEDDATE())+" ) ";
				System.out.println(sql);	
				stmt.execute(sql);
			}
			else
			{
				errMsg="Stock summary serial number does not exists";
			}
		}
		catch(Exception e)
		{
			errMsg="error in insertion Stock Summary details"+e.getMessage();
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
}
