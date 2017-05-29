package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import bean.MAJOR_MINOR_CODE;
import bean.MAJOR_MINOR_CommonData;

public class MAJOR_MINOR_DB 
{
	KrrCommon krrCommon = new KrrCommon();
	public String search_major_minor_code(Connection con,List<MAJOR_MINOR_CODE> MAJORMINORLIST,MAJOR_MINOR_CommonData majorMinorCommonData)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		String condition="";
		try
		{
			stmt = con.createStatement();
			sql="select * from MAJOR_MINOR_CODE";
			if(majorMinorCommonData.getMAJORCODE()!=0){
				condition = condition +" MAJORCODE = "+majorMinorCommonData.getMAJORCODE();
			}
			if(krrCommon.isValue(majorMinorCommonData.getMAJORCODEDESC())){
				condition = condition+" and upper(MAJORCODEDESC) LIKE "+krrCommon.AppendPercentileBothEnds(majorMinorCommonData.getMAJORCODEDESC());			
			}
			if(majorMinorCommonData.getMINORCODE()!=0){
				condition = condition +" and MINORCODE ="+majorMinorCommonData.getMINORCODE();
			}
			if(krrCommon.isValue(majorMinorCommonData.getMINORCODEDESC())){
				condition = condition +" and upper(MINORCODEDESC) LIKE "+krrCommon.AppendPercentileBothEnds(majorMinorCommonData.getMINORCODEDESC());
			}
			System.out.println("sql1"+sql);
			if(condition !=""){
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and")){
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+" ";	
			}	
			System.out.println("sql"+sql);
			sql=sql+" order by MAJORCODE ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				MAJOR_MINOR_CODE MAJORMINORCODE = new MAJOR_MINOR_CODE();
				errMsg=trans_major_minor_code(rs, MAJORMINORCODE);
				MAJORMINORLIST.add(MAJORMINORCODE);
				i++;
			}
		}
		catch(Exception e)
		{
			errMsg="Error in searching major minor code details "+ e.getMessage();
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

	public String insert_major_minor_code(Connection con,MAJOR_MINOR_CODE MAJORMINORCODE)
	{
		Statement stmt=null;
		String sql;
		long seqId=0;
		String l_ers="";
		try
		{
			seqId=krrCommon.Nextval_SQ("MAJOR_MINOR_CODE_SQ", con);
			MAJORMINORCODE.setMAJORMINORSRNO(seqId);
			stmt = con.createStatement();
			if(seqId>0)
			{
				sql = "insert into MAJOR_MINOR_CODE (";
				sql=sql+"MAJORMINORSRNO,";
				sql=sql+"MAJORCODE,";
				sql=sql+"MAJORCODEDESC,";
				sql=sql+"MINORCODE,";
				sql=sql+"MINORCODEDESC,";
				sql=sql+"CREATEDBY,";
				sql=sql+"CREATEDDATE,";
				sql=sql+"STATUS";
				sql=sql+") VALUES (";
				sql=sql+(MAJORMINORCODE.getMAJORMINORSRNO())+",";
				sql=sql+MAJORMINORCODE.getMAJORCODE()+",";
				sql=sql+krrCommon.AppendSinlequote(MAJORMINORCODE.getMAJORCODEDESC())+",";
				sql=sql+MAJORMINORCODE.getMINORCODE()+",";
				sql=sql+krrCommon.AppendSinlequote(MAJORMINORCODE.getMINORCODEDESC())+",";
				sql=sql+krrCommon.AppendSinlequote(MAJORMINORCODE.getCREATEDBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(MAJORMINORCODE.getCREATEDDATE())+", ";
				sql=sql+krrCommon.AppendSinlequote(MAJORMINORCODE.getSTATUS())+") ";
				System.out.println(sql);
				stmt.execute(sql);
			}
			else
			{
				l_ers="Major Minor Code serial number does not exist";
			}
		}
		catch(Exception e)
		{
			l_ers="error in insertion major minor code details process"+e.getMessage();
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
		return l_ers;
	}

//	public int get_max_minor_code(Connection con,long majorCode)
//	{
//	int maxMinorCode=0;
//	Statement stmt=null;
//	String sql;
//	ResultSet rs=null;
//	try{
//	stmt = con.createStatement();
//	sql= "select max(MINORCODE) as MINORCODE from MAJOR_MINOR_CODE where MAJORCODE = "+majorCode;
//	System.out.println(sql);
//	rs = stmt.executeQuery(sql);
//	if (rs.next())
//	{
//	maxMinorCode=rs.getInt("MINORCODE");
//	}
//	rs.close();
//	stmt.close();
//	}catch(Exception e)
//	{
//	e.printStackTrace();
//	}
//	finally
//	{
//	try
//	{
//	if(rs != null) rs.close();
//	if(stmt != null) stmt.close();
//	}
//	catch(Exception e2) 
//	{
//	e2.printStackTrace();
//	}
//	}
//	return maxMinorCode;
//	}

	public String trans_major_minor_code(ResultSet rs,MAJOR_MINOR_CODE majorBean)
	{
		String l_ers="";
		try
		{
			majorBean.setMAJORMINORSRNO(rs.getLong("MAJORMINORSRNO"));
			majorBean.setMAJORCODE(rs.getLong("MAJORCODE"));
			majorBean.setMINORCODE(rs.getLong("MINORCODE"));
			majorBean.setMAJORCODEDESC(krrCommon.CheckEmptyReturn(rs.getString("MAJORCODEDESC")));
			majorBean.setMINORCODEDESC(krrCommon.CheckEmptyReturn(rs.getString("MINORCODEDESC")));
			majorBean.setSTATUS(krrCommon.CheckEmptyReturn(rs.getString("STATUS")));
			majorBean.setCREATEDBY(krrCommon.CheckEmptyReturn(rs.getString("CREATEDBY")));
			majorBean.setCREATEDDATE(krrCommon.CheckEmptyReturn(rs.getString("CREATEDDATE")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in search results "+e.getMessage();
		}
		return l_ers;
	}

	public String ge_code_list(Connection con, List<MAJOR_MINOR_CODE> MAJORMINORCODELIST, long majorCode){
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from MAJOR_MINOR_CODE where MAJORCODE = "+ majorCode;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{	
				MAJOR_MINOR_CODE MAJORMINORCODE = new MAJOR_MINOR_CODE();
				errMsg=trans_major_minor_code(rs, MAJORMINORCODE);
				MAJORMINORCODELIST.add(MAJORMINORCODE);
			}
		}
		catch(Exception e)
		{
			errMsg="Error in major code list details process"+e.getMessage();	
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

	public String change_Major_Minor_Status(Connection con,long mmSrNo,String status) 
	{
		Statement stmt=null;
		String sql;
		int recordsUpdated=0;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "update MAJOR_MINOR_CODE set STATUS = "+krrCommon.AppendSinlequote(status)+" where MAJORMINORSRNO="+mmSrNo;
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

	public String get_status(Connection con,long MMSrNo)
	{
		Statement stmt=null;
		ResultSet rs=null;
		String sql;
		String MAJORMINORSTATUS="";
		try
		{
			stmt = con.createStatement();
			sql= "select STATUS FROM MAJOR_MINOR_CODE where MAJORMINORSRNO="+MMSrNo;
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) 
			{
				MAJORMINORSTATUS=rs.getString("STATUS");
			}
		}
		catch(Exception e)
		{
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
		return MAJORMINORSTATUS;
	}
	public boolean is_code_exits(Connection con,long code)
	{
		boolean codeExits = false;
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from MAJOR_CODE where MAJORCODE = "+code;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				codeExits = true;
			}
		}
		catch(Exception e)
		{
			l_ers="error in searching major minor details process"+e.getMessage();
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
	
	public boolean is_minorCode_exits(Connection con,long majorcode,long minorcode)
	{
		boolean codeExits = false;
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql= "select * from MAJOR_MINOR_CODE where MAJORCODE = "+majorcode+" and MINORCODE = "+minorcode;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				codeExits = true;
			}
		}
		catch(Exception e)
		{
			l_ers="error in searching minor code details "+e.getMessage();
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

	public String insert_major_code(Connection con,bean.MAJOR_CODE MAJORCODE)
	{
		System.out.println("in insert major code");
		Statement stmt=null;
		String sql;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql = "insert into MAJOR_CODE (";
			sql=sql+"MAJORCODE,";
			sql=sql+"MAJORCODEDESC,";
			sql=sql+"CREATEDBY,";
			sql=sql+"CREATEDDATE,";
			sql=sql+"STATUS";
			sql=sql+") VALUES (";
			sql=sql+MAJORCODE.getMAJORCODE()+",";
			sql=sql+krrCommon.AppendSinlequote(MAJORCODE.getMAJORCODEDESC())+",";
			sql=sql+krrCommon.AppendSinlequote(MAJORCODE.getCREATEDBY())+", ";
			sql=sql+krrCommon.AppendSinlequote(MAJORCODE.getCREATEDDATE())+", ";
			sql=sql+krrCommon.AppendSinlequote(MAJORCODE.getSTATUS())+") ";
			System.out.println(sql);
			stmt.execute(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in insertion major code details"+e.getMessage();
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

	public String get_major_code(Connection con, List<MAJOR_MINOR_CODE> MAJORMINORLIST)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		try
		{
			stmt = con.createStatement();
			sql= "select distinct MAJORCODE,MAJORCODEDESC from MAJOR_CODE order by MAJORCODEDESC";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{	
				MAJOR_MINOR_CODE majorBean = new MAJOR_MINOR_CODE();
				majorBean.setMAJORCODE(rs.getLong("MAJORCODE"));
				majorBean.setMAJORCODEDESC(rs.getString("MAJORCODEDESC"));
				MAJORMINORLIST.add(majorBean);
			}
		}
		catch(Exception e)
		{
			errMsg="Error in major code list details process"+e.getMessage();	
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

	public String  trans_MAJOR_CODE(java.sql.ResultSet rsReg, bean.MAJOR_CODE MAJOR_CODE)
	{
		String errMsg="";
		try
		{
			MAJOR_CODE.setMAJORCODE  				(rsReg.getLong("MAJORCODE"));
			MAJOR_CODE.setMAJORCODEDESC           	(rsReg.getString("MAJORCODEDESC"));
			MAJOR_CODE.setSTATUS               		(krrCommon.CheckEmptyReturn(rsReg.getString("STATUS")));
			MAJOR_CODE.setCREATEDBY            		(krrCommon.CheckEmptyReturn(rsReg.getString("CREATEDBY")));
			MAJOR_CODE.setCREATEDDATE          		(krrCommon.CheckEmptyReturn(rsReg.getString("CREATEDDATE")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errMsg="Error in Trans_MRN_HEADER "+e.getMessage();
		}
		return errMsg;
	}
}
