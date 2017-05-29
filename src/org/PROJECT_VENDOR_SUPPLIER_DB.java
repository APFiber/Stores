package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import bean.PROJECT_MASTER;
import bean.VENDOR_SUPPLIER;
import bean.VENDOR_SUPPLIER_PROJECT;;

public class PROJECT_VENDOR_SUPPLIER_DB {

	KrrCommon krrCommon=new KrrCommon();


	public String search_VENDOR_SUPPLIER_PROJECT(Connection con,List<PROJECT_MASTER> vendor_supplier_projectList,bean.VSP_Commondata VSP_Commondata){
		String errMsg="";
		Statement stmt=null;
		ResultSet rs = null;
		String sql="";
		String condition="";
		try
		{

			stmt = con.createStatement();

			sql="select * from PROJECT_MASTER ";
			if(VSP_Commondata.getPROJECTSRNO() != 0){
				condition = condition +" PROJECTSRNO ="+VSP_Commondata.getPROJECTSRNO();
			}
//			if(krrCommon.isValue(VSP_Commondata.getPROJECTNAME())){
//				condition = condition+" and PROJECTNAME ="+krrCommon.AppendSinlequote(VSP_Commondata.getPROJECTNAME());			
//			}
//			if(krrCommon.isValue(VSP_Commondata.getMANAGER())){
//				condition = condition+" and MANAGER ="+krrCommon.AppendSinlequote(VSP_Commondata.getMANAGER());			
//			}
//			if(krrCommon.isValue(VSP_Commondata.getLOCATION())){
//				condition = condition+" and LOCATION ="+krrCommon.AppendSinlequote(VSP_Commondata.getLOCATION());			
//			}
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
				vendor_supplier_projectList.add(PROJECT_MASTER);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return errMsg;
	}

	//Save or Insert
	public String insert_VENDOR_SUPPLIER_PROJECT(Connection con, VENDOR_SUPPLIER_PROJECT VENDORSUPPLIERPROJECT)   
	{
		String errMsg="";
		Statement stmt=null;
		ResultSet rs=null;
		String sql="";
		try{


			long seqId=krrCommon.Nextval_SQ("VP_SRNO", con);
			System.out.println("in db VS_SRNO"+seqId);
			VENDORSUPPLIERPROJECT.setVP_SRNO(seqId);
			sql="INSERT INTO VENDOR_PROJECT(";
			sql=sql+"VP_SRNO, ";
			sql=sql+"VP_PROJECT, ";
			sql=sql+"VP_LOCATION, ";
			sql=sql+"VP_MANAGER, ";
			sql=sql+"VP_MAILID, ";
			sql=sql+"CREATEDBY, ";
			sql=sql+"CREATEDDATE, ";
			sql=sql+"VP_VENDOR_CODE, ";
			sql=sql+"VP_VENDOR_NAME, ";
			sql=sql+"VP_VENDOR_ADDRESSLINE1, ";
			sql=sql+"VP_VENDOR_ADDRESSLINE2, ";
			sql=sql+"VP_VENDOR_ADDRESSLINE3, ";
			sql=sql+"VP_VENDOR_ADDRESSLINE4, ";
			sql=sql+"VP_VENDOR_CITY, ";
			sql=sql+"VP_VENDOR_DISTRICT, ";
			sql=sql+"VP_VENDOR_STATE, ";
			sql=sql+"VP_VENDOR_COUNTRY, ";
			sql=sql+"VP_VENDOR_PIN, ";
			sql=sql+"VP_VENDOR_PHONENO ";
			
			sql=sql+")select ";
			sql=sql+VENDORSUPPLIERPROJECT.getVP_SRNO()+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_PROJECT())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_LOCATION())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_MANAGER())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_MAILID())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getCREATEDBY())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getCREATEDDATE())+", ";
			sql=sql+VENDORSUPPLIERPROJECT.getVP_VENDOR_CODE()+", ";
			sql=sql+"VS_NAME, VS_ADDRESSLINE1, VS_ADDRESSLINE2, VS_ADDRESSLINE3, VS_ADDRESSLINE4, ";
			sql=sql+"VS_CITY, VS_DISTRICT, VS_STATE, VS_COUNTRY, VS_PIN, VS_PHONENO from VENDOR_SUPPLIER ";
			sql=sql+"where VS_CODE="+VENDORSUPPLIERPROJECT.getVP_VENDOR_CODE();
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

	public String update_VENDOR_SUPPLIER_PROJECT(Connection con,VENDOR_SUPPLIER_PROJECT VENDORSUPPLIERPROJECT)
	{
		java.sql.Statement stmt = null ;
		String sql="";
		String errMsg="";

		try{

			stmt = con.createStatement();
			sql= "UPDATE VENDOR_PROJECT SET ";
			sql=sql+"VP_PROJECT="+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_PROJECT())+",";
			sql=sql+"VP_LOCATION="+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_LOCATION())+",";
			sql=sql+"VP_MANAGER="+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_MANAGER())+",";
			sql=sql+"VP_MAILID="+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getVP_MAILID());
			sql=sql+" where VP_SRNO = "+VENDORSUPPLIERPROJECT.getVP_SRNO();
			System.out.println("aasdfghjkl        "+sql);
			stmt = con.createStatement() ;
			stmt.executeUpdate(sql);


		}catch(Exception e)
		{
			errMsg="Error in update Vendor project Details process ";
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

	public String getname_VENDOR_SUPPLIER_PROJECT(Connection con,List<VENDOR_SUPPLIER> codebean){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select VSSRNO,NAME from VENDOR_SUPPLIER where TYPE='VENDOR' and STATUS='ACTIVE'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				VENDOR_SUPPLIER Name = new VENDOR_SUPPLIER();
				Name.setNAME(rs.getString("NAME"));
				Name.setVSSRNO(rs.getLong("VSSRNO"));
				codebean.add(Name);
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


	public String getproject_VENDOR_SUPPLIER_PROJECT(Connection con,List<VENDOR_SUPPLIER_PROJECT> codebean){
		//List<com.terasoft.stores.bean.VENDOR_DETAILS> codebean = new ArrayList<com.terasoft.stores.bean.VENDOR_DETAILS>();
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select distinct VP_PROJECT from VENDOR_PROJECT ";

			rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				VENDOR_SUPPLIER_PROJECT project = new VENDOR_SUPPLIER_PROJECT();
				project.setVP_PROJECT(rs.getString("VP_PROJECT"));
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

	public String edit_VENDOR_SUPPLIER_PROJECT_Details(Connection con,VENDOR_SUPPLIER_PROJECT VENDORSUPPLIERPROJECT){
		//	com.terasoft.stores.bean.VENDOR_DETAILS vendorBean = new com.terasoft.stores.bean.VENDOR_DETAILS();
		Statement stmt=null ;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select VENDOR_PROJECT.VP_SRNO,VENDOR_PROJECT.VP_PROJECT,VENDOR_PROJECT.VP_LOCATION,VENDOR_PROJECT.VP_MANAGER,VENDOR_PROJECT.VP_MAILID,VENDOR_PROJECT.VP_VENDOR_NAME from VENDOR_PROJECT " +
			" where VP_SRNO = "+VENDORSUPPLIERPROJECT.getVP_SRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				VENDORSUPPLIERPROJECT.setVP_SRNO(rs.getLong("VP_SRNO"));
				VENDORSUPPLIERPROJECT.setVP_PROJECT(rs.getString("VP_PROJECT"));
				VENDORSUPPLIERPROJECT.setVP_LOCATION(rs.getString("VP_LOCATION"));
				VENDORSUPPLIERPROJECT.setVP_MANAGER(rs.getString("VP_MANAGER"));
				VENDORSUPPLIERPROJECT.setVP_MAILID(rs.getString("VP_MAILID"));
				VENDORSUPPLIERPROJECT.setVP_VENDOR_NAME(rs.getString("VP_VENDOR_NAME"));
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
		Statement stmt;
		String sql;
		ResultSet rs;
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

		return vendorExits;
	}

public String getProjectdetails(Connection con,bean.PROJECT_MASTER PROJECT_MASTER,long srno)
{
	String l_ers="";
	String sql="";
	Statement stmt=null;
	ResultSet rs=null;

	try
	{
		sql="SELECT * FROM PROJECT_MASTER WHERE PROJECTSRNO="+srno;
		stmt=con.createStatement();
		rs=stmt.executeQuery(sql);
		System.out.println("sql"+sql);
		if(rs.next())
		{
			l_ers=tans_Project_Details(rs, PROJECT_MASTER);
						
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		l_ers="Project details not found for Id  "+srno+"  >> "+e.getMessage();
	}
	finally
	{
		try
		{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
		}
		catch(Exception e)
		{
//			e.printStackTrace();
		}
	}
	return l_ers;
}
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

public String insert_VENDOR_PROJECT(Connection con, bean.VENDOR_PROJECT VENDORSUPPLIERPROJECT)   
{
	String l_ers="";
	Statement stmt=null;
	String sql="";
	try{		
		stmt=con.createStatement();
		long seqId=krrCommon.Nextval_SQ("VENDOR_PROJECT_SQ", con);
		System.out.println("in db VSSRNO"+seqId);
		VENDORSUPPLIERPROJECT.setVPSRNO(seqId);
		sql="INSERT INTO VENDOR_PROJECT(";
		sql=sql+"VPSRNO, ";
		sql=sql+"VSSRNO, ";
		sql=sql+"PROJECTSRNO, ";
		sql=sql+"CREATEDBY, ";
		sql=sql+"CREATEDDATE ";
		sql=sql+") VALUES (";
		sql=sql+VENDORSUPPLIERPROJECT.getVPSRNO()+", ";
		sql=sql+VENDORSUPPLIERPROJECT.getVSSRNO()+", ";
		sql=sql+VENDORSUPPLIERPROJECT.getPROJECTSRNO()+", ";
		sql=sql+krrCommon.AppendSinlequote(VENDORSUPPLIERPROJECT.getCREATEDBY())+", ";
		sql=sql+VENDORSUPPLIERPROJECT.getCREATEDDATE()+") ";
		System.out.println(sql);	
		stmt.executeUpdate(sql);

	}
	catch(Exception e)
	{
		e.printStackTrace();
		l_ers="Error in inserting vendor project details "+e.getMessage();
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

}
