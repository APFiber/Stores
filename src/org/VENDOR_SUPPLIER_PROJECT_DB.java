package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import bean.PROJECT_MASTER;
import bean.VENDOR_SUPPLIER;
import bean.VENDOR_SUPPLIER_PROJECT;;

public class VENDOR_SUPPLIER_PROJECT_DB {

	KrrCommon krrCommon=new KrrCommon();


	public String Search_PROJECT_MASTER_details(Connection con,List<PROJECT_MASTER> projectmasterList,bean.PROJECT_CommonData PROJECT_CommonData){
		String errMsg="";
		Statement stmt=null;
		ResultSet rs = null;
		String sql="";
		String condition="";
		try
		{

			stmt = con.createStatement();

			sql="select * from PROJECT_MASTER ";
			if(PROJECT_CommonData.getPROJECTSRNO() != 0){
				condition = condition +" PROJECTSRNO ="+PROJECT_CommonData.getPROJECTSRNO();
			}
			if(krrCommon.isValue(PROJECT_CommonData.getPROJECTNAME())){
				condition = condition+" and PROJECTNAME ="+krrCommon.AppendSinlequote(PROJECT_CommonData.getPROJECTNAME());			
			}
			if(krrCommon.isValue(PROJECT_CommonData.getMANAGER())){
				condition = condition+" and MANAGER ="+krrCommon.AppendSinlequote(PROJECT_CommonData.getMANAGER());			
			}
			if(krrCommon.isValue(PROJECT_CommonData.getLOCATION())){
				condition = condition+" and LOCATION ="+krrCommon.AppendSinlequote(PROJECT_CommonData.getLOCATION());			
			}
			if(condition !=""){
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and")){
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+"";	
			}
			sql=sql+" order by PROJECTSRNO ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				bean.PROJECT_MASTER PROJECT_MASTER = new bean.PROJECT_MASTER();
				PROJECT_MASTER.setPROJECTSRNO(rs.getLong("PROJECTSRNO"));
				PROJECT_MASTER.setPROJECTNAME(rs.getString("PROJECTNAME"));
				PROJECT_MASTER.setMANAGER(rs.getString("MANAGER"));
				PROJECT_MASTER.setLOCATION(rs.getString("LOCATION"));
				PROJECT_MASTER.setEMAILID(rs.getString("EMAILID"));
				projectmasterList.add(PROJECT_MASTER);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}

	//Save or Insert
	public String insert_PROJECT_MASTER(Connection con, bean.PROJECT_MASTER PROJECT_MASTER)   
	{
		String errMsg="";
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try{


			long seqId=krrCommon.Nextval_SQ("PROJECT_MASTER_SQ", con);
			System.out.println("in db VS_SRNO"+seqId);
			PROJECT_MASTER.setPROJECTSRNO(seqId);
			sql="INSERT INTO PROJECT_MASTER(";
			sql=sql+"PROJECTSRNO, ";
			sql=sql+"PROJECTNAME, ";
			sql=sql+"LOCATION, ";
			sql=sql+"MANAGER, ";
			sql=sql+"EMAILID, ";
			sql=sql+"CREATEDBY, ";
			sql=sql+"CREATEDDATE ";
			sql=sql+") VALUES (";
			sql=sql+PROJECT_MASTER.getPROJECTSRNO()+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getPROJECTNAME())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getLOCATION())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getMANAGER())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getEMAILID())+", ";
			sql=sql+krrCommon.AppendSinlequote(PROJECT_MASTER.getCREATEDBY())+", ";
			sql=sql+PROJECT_MASTER.getCREATEDDATE()+") ";
		
			System.out.println(sql);	
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
			
		}
		catch(Exception e)
		{
			errMsg=e.getMessage();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return errMsg;
	}
	public String edit_VENDOR_SUPPLIER_Details(Connection con,bean.VENDOR_SUPPLIER_PROJECT vendorproject){
		//	com.terasoft.stores.bean.VENDOR_DETAILS vendorBean = new com.terasoft.stores.bean.VENDOR_DETAILS();
		Statement stmt=null ;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
		//	sql="INSERT INTO VENDOR_PROJECT(VP_VENDOR_CODE,VP_VENDOR_NAME,VP_VENDOR_ADDRESSLINE1,VP_VENDOR_ADDRESSLINE2,VP_VENDOR_ADDRESSLINE3,";
		//	sql="VP_VENDOR_ADDRESSLINE4,VP_VENDOR_CITY,VP_VENDOR_DISTRICT,VP_VENDOR_STATE,VP_VENDOR_COUNTRY,VP_VENDOR_PIN,VP_VENDOR_PHONENO)";
			sql= "select  VS_CODE,VS_NAME,VS_ADDRESSLINE1,VS_ADDRESSLINE2,VS_ADDRESSLINE3,VS_ADDRESSLINE4,VS_CITY,VS_DISTRICT,VS_STATE,VS_COUNTRY,VS_PIN,VS_PHONENO from VENDOR_SUPPLIER where VS_CODE="+vendorproject.getVP_VENDOR_CODE();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				l_ers=tans_Vendor_Details(rs, vendorproject);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			l_ers="error in getting vendor details to edit "+e.getMessage();
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

	public String tans_Vendor_Details(java.sql.ResultSet rs,bean.VENDOR_SUPPLIER_PROJECT vendorproject)
	{
		String l_ers="";
		try
		{
			vendorproject.setVP_VENDOR_CODE(rs.getLong("VS_CODE"));
			vendorproject.setVP_VENDOR_NAME(krrCommon.CheckEmptyReturn(rs.getString("VS_NAME")));
			vendorproject.setVP_VENDOR_ADDRESSLINE1(krrCommon.CheckEmptyReturn(rs.getString("VS_ADDRESSLINE1")));
			vendorproject.setVP_VENDOR_ADDRESSLINE2(krrCommon.CheckEmptyReturn(rs.getString("VS_ADDRESSLINE2")));
			vendorproject.setVP_VENDOR_ADDRESSLINE3(krrCommon.CheckEmptyReturn(rs.getString("VS_ADDRESSLINE3")));
			vendorproject.setVP_VENDOR_ADDRESSLINE4(krrCommon.CheckEmptyReturn(rs.getString("VS_ADDRESSLINE4")));
			vendorproject.setVP_VENDOR_CITY(krrCommon.CheckEmptyReturn(rs.getString("VS_CITY")));
			vendorproject.setVP_VENDOR_DISTRICT(krrCommon.CheckEmptyReturn(rs.getString("VS_DISTRICT")));
			vendorproject.setVP_VENDOR_STATE(krrCommon.CheckEmptyReturn(rs.getString("VS_STATE")));
			vendorproject.setVP_VENDOR_COUNTRY(krrCommon.CheckEmptyReturn(rs.getString("VS_COUNTRY")));
			vendorproject.setVP_VENDOR_PIN(krrCommon.CheckEmptyReturn(rs.getString("VS_PIN")));
			vendorproject.setVP_VENDOR_PHONENO(krrCommon.CheckEmptyReturn(rs.getString("VS_PHONENO")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error to get vendor project"+e.getMessage();
		}
		return l_ers;
	}

	public String update_PROJECT_MASTER(Connection con,bean.PROJECT_MASTER PROJECT_MASTER)
	{
		java.sql.Statement stmt = null ;
		String sql="";
		String errMsg="";

		try{

			stmt = con.createStatement();
			sql= "UPDATE PROJECT_MASTER SET ";
			sql=sql+"PROJECTNAME="+krrCommon.AppendSinlequote(PROJECT_MASTER.getPROJECTNAME())+",";
			sql=sql+"LOCATION="+krrCommon.AppendSinlequote(PROJECT_MASTER.getLOCATION())+",";
			sql=sql+"MANAGER="+krrCommon.AppendSinlequote(PROJECT_MASTER.getMANAGER())+",";
			sql=sql+"EMAILID="+krrCommon.AppendSinlequote(PROJECT_MASTER.getEMAILID());
			sql=sql+" where PROJECTSRNO = "+PROJECT_MASTER.getPROJECTSRNO();
			System.out.println("sql  "+sql);
			stmt = con.createStatement() ;
			stmt.executeUpdate(sql);


		}catch(Exception e)
		{
			errMsg="Error in update project Details process ";
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

	
	public String getmanager_PROJECT_MASTER(Connection con,List<bean.PROJECT_MASTER> codebean){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct MANAGER from PROJECT_MASTER ";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				PROJECT_MASTER project = new PROJECT_MASTER();
				project.setMANAGER(rs.getString("MANAGER"));
				codebean.add(project);

			}


		}catch(Exception e){
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

	public String getlocation_PROJECT_MASTER(Connection con,List<bean.PROJECT_MASTER> codebean){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct LOCATION from PROJECT_MASTER ";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				PROJECT_MASTER project = new PROJECT_MASTER();
				project.setLOCATION(rs.getString("LOCATION"));
				codebean.add(project);

			}


		}catch(Exception e){
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
	
	public String getproject_PROJECT_MASTER(Connection con,List<bean.PROJECT_MASTER> codebean){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct PROJECTNAME from PROJECT_MASTER ";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				PROJECT_MASTER project = new PROJECT_MASTER();
				project.setPROJECTNAME(rs.getString("PROJECTNAME"));
				codebean.add(project);

			}


		}catch(Exception e){
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

	public String edit_PROJECT_MASTER_Details(Connection con,bean.PROJECT_MASTER PROJECT_MASTER){
		//	com.terasoft.stores.bean.VENDOR_DETAILS vendorBean = new com.terasoft.stores.bean.VENDOR_DETAILS();
		Statement stmt=null ;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from PROJECT_MASTER" +
			" where PROJECTSRNO = "+PROJECT_MASTER.getPROJECTSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				PROJECT_MASTER.setPROJECTSRNO(rs.getLong("PROJECTSRNO"));
				PROJECT_MASTER.setPROJECTNAME(rs.getString("PROJECTNAME"));
				PROJECT_MASTER.setLOCATION(rs.getString("LOCATION"));
				PROJECT_MASTER.setMANAGER(rs.getString("MANAGER"));
				PROJECT_MASTER.setEMAILID(rs.getString("EMAILID"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			l_ers="error in getting vendor project details "+e.getMessage();
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
	
//	vendor exist
	public boolean isVendorExits(Connection con,long vssrno)
	{
		boolean vendorExits = false;
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		try{
			stmt = con.createStatement();
			sql= "select * from VENDOR_PROJECT where VP_SRNO = "+vssrno;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				vendorExits = true;
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
		return vendorExits;
	}

}
