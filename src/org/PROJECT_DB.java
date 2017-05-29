package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.KrrCommon;

import bean.PROJECT_MASTER;
import bean.VENDOR_SUPPLIER;

public class PROJECT_DB 
{

	KrrCommon krrCommon=new KrrCommon();

	public String Search_VENDOR_SUPPLIER_details(Connection con,List<VENDOR_SUPPLIER> VENDORDETAILSList, bean.VENDOR_CommonData VENDORCommonData)
	{
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> vendorBeanList = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		String condition="";
		ResultSet rs=null;
		String l_ers="";
		try
		{

			stmt = con.createStatement();
			sql="select * from VENDOR_SUPPLIER";
			if(krrCommon.isValue(VENDORCommonData.getTYPE())){
				condition = condition +" TYPE ="+krrCommon.AppendSinlequote((VENDORCommonData.getTYPE()));
			}
			if(VENDORCommonData.getVSSRNO()!=0){
				condition = condition+" and VSSRNO ="+VENDORCommonData.getVSSRNO();			
			}
			if(krrCommon.isValue(VENDORCommonData.getNAME())){
				condition = condition +" and upper(NAME) LIKE "+krrCommon.AppendPercentileBothEnds(VENDORCommonData.getNAME());

			}

			if(krrCommon.isValue(VENDORCommonData.getCITY())){
				condition = condition +" and CITY ="+krrCommon.AppendPercentileBothEnds(VENDORCommonData.getCITY());

			}
			if(krrCommon.isValue(VENDORCommonData.getDISTRICT())){
				condition = condition +" and DISTRICT ="+krrCommon.AppendPercentileBothEnds(VENDORCommonData.getDISTRICT());

			}
			if(krrCommon.isValue(VENDORCommonData.getSTATE())){
				condition = condition +" and STATE ="+krrCommon.AppendPercentileBothEnds(VENDORCommonData.getSTATE());

			}
			if(VENDORCommonData.getPIN()!=0){
				condition = condition +" and PIN ="+VENDORCommonData.getPIN();

			}
			if(VENDORCommonData.getPHONENO()!=0){
				condition = condition +" and PHONENO ="+VENDORCommonData.getPHONENO();

			}
			if(krrCommon.isValue(VENDORCommonData.getSTATUS())){
				condition = condition +" and STATUS ="+krrCommon.AppendSinlequote(VENDORCommonData.getSTATUS());

			}
			if(condition !=""){
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and")){
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+"";	
			}
			sql=sql+" order by VSSRNO ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				bean.PROJECT_MASTER PROJECT_MASTER = new bean.PROJECT_MASTER();
				l_ers = tans_Project_Details(rs, PROJECT_MASTER);

//				VENDORDETAILSList.add(PROJECT_MASTER);
				i++;
			}

		}catch(Exception e)
		{
			l_ers="Error vendor/supplier db"+e.getMessage();
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



	//Save or Insert
	public String insert_PROJECT_MASTER(Connection con, bean.PROJECT_MASTER PROJECT_MASTER)   
	{
		String l_ers="";
		Statement stmt=null;
		String sql="";
		try{		
			stmt=con.createStatement();
			long seqId=krrCommon.Nextval_SQ("PROJECT_MASTER_SQ", con);
			System.out.println("in db VSSRNO"+seqId);
			PROJECT_MASTER.setPROJECTSRNO(seqId);
			sql="INSERT INTO PROJECT_MASTER(";
			sql=sql+"PROJECTSRNO, ";
			sql=sql+"PROJECTNAME, ";
			sql=sql+"LOCATION, ";
			sql=sql+"MANAGER, ";
			sql=sql+"EMAILID, ";
			sql=sql+"CREATEDBY, ";
			sql=sql+"CREATEDDATE, ";
			sql=sql+"STATUS ";
			sql=sql+") VALUES (";
			sql=sql+PROJECT_MASTER.getPROJECTSRNO()+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getPROJECTNAME())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getLOCATION())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getMANAGER())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getEMAILID())+", ";			
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getCREATEDBY())+", ";
			sql=sql+PROJECT_MASTER.getCREATEDDATE()+") ";
			System.out.println(sql);	
			stmt.executeUpdate(sql);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in inserting project details "+e.getMessage();
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

	//Search

	public String tans_Project_Details(java.sql.ResultSet rs,bean.PROJECT_MASTER PROJECT_MASTER)
	{
		String l_ers="";
		try
		{
			PROJECT_MASTER.setPROJECTSRNO(rs.getLong("PROJECTSRNO"));
			PROJECT_MASTER.setPROJECTNAME(krrCommon.CheckEmptyReturn(rs.getString("PROJECTNAME")));
			PROJECT_MASTER.setLOCATION(krrCommon.CheckEmptyReturn(rs.getString("LOCATION")));
			PROJECT_MASTER.setMANAGER(krrCommon.CheckEmptyReturn(rs.getString("MANAGER")));
			PROJECT_MASTER.setEMAILID(krrCommon.CheckEmptyReturn(rs.getString("EMAILID")));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error to get project details"+e.getMessage();
		}
		return l_ers;
	}
	public String edit_PROJECT_MASTER_Details(Connection con,PROJECT_MASTER PROJECT_MASTER){
		//	com.terasoft.stores.bean.VENDOR_DETAILS vendorBean = new com.terasoft.stores.bean.VENDOR_DETAILS();
		Statement stmt=null ;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from PROJECT_MASTER where PROJECTSRNO = "+PROJECT_MASTER.getPROJECTSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				l_ers=tans_Project_Details(rs, PROJECT_MASTER);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			l_ers="error in getting project details to edit "+e.getMessage();
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


	public boolean is_Code_Exits(Connection con,String code)
	{
		boolean codeExits = false;
		Statement stmt = null;
		String sql;
		ResultSet rs = null;
		try{
			stmt = con.createStatement();
			sql= "select * from VENDOR_SUPPLIER where VSSRNO = "+code;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				codeExits = true;
			}
		}catch(Exception e)
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
		return codeExits;
	}
	public String update_VENDOR_SUPPLIER(Connection con,VENDOR_SUPPLIER VENDOR_DETAILS)
	{
		Statement stmt = null ;
		String sql="";
		String l_ers="";

		try{

			stmt = con.createStatement();
			sql= "UPDATE VENDOR_SUPPLIER SET ";
			sql= sql+"TYPE="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getTYPE())+",";
			sql=sql+"NAME="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getNAME())+",";
			sql=sql+"ADDRESSLINE1="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE1())+",";
			sql=sql+"ADDRESSLINE2="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE2())+",";
			sql=sql+"ADDRESSLINE3="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE3())+",";
			sql=sql+"ADDRESSLINE4="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE4())+",";
			sql=sql+"CITY="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getCITY())+",";
			sql=sql+"DISTRICT="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getDISTRICT())+",";
			sql=sql+"STATE="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getSTATE())+",";
			sql=sql+"COUNTRY="+krrCommon.AppendSinlequote(VENDOR_DETAILS.getCOUNTRY())+",";
			sql=sql+"PIN="+VENDOR_DETAILS.getPIN()+",";
			sql=sql+"PHONENO="+VENDOR_DETAILS.getPHONENO()+ " where VSSRNO = "+VENDOR_DETAILS.getVSSRNO();
			System.out.println("aasdfghjkl        "+sql);
			stmt = con.createStatement() ;
			stmt.executeUpdate(sql);


		}catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error to update Vendor/supplier Details"+e.getMessage();
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


//	TYPE
	public String getType(Connection con,List<VENDOR_SUPPLIER> VENDOR_DETAILSList){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct TYPE from VENDOR_SUPPLIER order by TYPE";
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				VENDOR_SUPPLIER Type = new VENDOR_SUPPLIER();
				Type.setTYPE(rs.getString("TYPE"));

				VENDOR_DETAILSList.add(Type);
			}
		}catch(Exception e){
			e.printStackTrace();
			l_ers="error to get TYPE list in dropdown "+e.getMessage();
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

//	CITY
	public String getCity(Connection con,List<VENDOR_SUPPLIER> VENDOR_DETAILSList){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct CITY from VENDOR_SUPPLIER order by CITY";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				VENDOR_SUPPLIER city = new VENDOR_SUPPLIER();
				city.setCITY(rs.getString("CITY"));
				VENDOR_DETAILSList.add(city);
			}
		}catch(Exception e){
			e.printStackTrace();
			l_ers="Error to get City list in dropdown"+e.getMessage();
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

//	District
	public String getDistrict(Connection con,List<VENDOR_SUPPLIER> VENDOR_DETAILSList){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct DISTRICT from VENDOR_SUPPLIER order by DISTRICT";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				VENDOR_SUPPLIER district = new VENDOR_SUPPLIER();
				district.setDISTRICT(rs.getString("DISTRICT"));
				VENDOR_DETAILSList.add(district);
			}
		}catch(Exception e){
			e.printStackTrace();
			l_ers="Error to get District list in dropdown"+e.getMessage();
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

//	STATE
	public String getState(Connection con,List<VENDOR_SUPPLIER> VENDOR_DETAILSList){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct STATE from VENDOR_SUPPLIER order by STATE";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				VENDOR_SUPPLIER state = new VENDOR_SUPPLIER();
				state.setSTATE(rs.getString("STATE"));
				VENDOR_DETAILSList.add(state);
			}
		}catch(Exception e){
			e.printStackTrace();
			l_ers="Error to get State list in dropdown"+e.getMessage();
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

//	authorize or delete

	public String change_VENDOR_SUPPLIER_status(Connection con,long vscode,String status)
	{
		Statement stmt=null;
		String l_ers="";
		String sql;
		int recordsUpdated=0;
		try{
			System.out.println("in authorize item");
			stmt = con.createStatement();
			sql= "update VENDOR_SUPPLIER set STATUS= "+krrCommon.AppendSinlequote(status)+" where VSSRNO="+vscode;
			recordsUpdated=stmt.executeUpdate(sql);
			System.out.println(sql);
			if(recordsUpdated==0)
			{
				l_ers="Records not updated";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in authorizing vendor Details"+e.getMessage();
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

//	get vendor/supplier status

	public String get_VENDOR_SUPPLIER_Status(Connection con,long vscode)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String VENDORSTATUS="";
		try{
			stmt = con.createStatement();
			sql= "select STATUS FROM VENDOR_SUPPLIER where VSSRNO="+vscode;
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) 
			{
				VENDORSTATUS=rs.getString("STATUS");
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
		return VENDORSTATUS;
	}
}
