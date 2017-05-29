package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.KrrCommon;
import bean.VENDOR_SUPPLIER;

public class VENDOR_DB 
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
				condition = condition +" TYPE ="+krrCommon.AppendSinlequote(VENDORCommonData.getTYPE());
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
				bean.VENDOR_SUPPLIER vendorBean = new bean.VENDOR_SUPPLIER();
				l_ers = tans_Vendor_Details( rs, vendorBean);

				VENDORDETAILSList.add(vendorBean);
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
	public String insert_VENDOR_SUPPLIER(Connection con, VENDOR_SUPPLIER VENDOR_DETAILS)   
	{
		String l_ers="";
		Statement stmt=null;
		String sql="";
		try{		
			stmt=con.createStatement();
			long seqId=krrCommon.Nextval_SQ("VENDOR_SUPPLIER_SQ", con);
			System.out.println("in db VSSRNO"+seqId);
			VENDOR_DETAILS.setVSSRNO(seqId);
			sql="INSERT INTO VENDOR_SUPPLIER(";
			sql=sql+"TYPE, ";
			sql=sql+"VSSRNO, ";
			sql=sql+"NAME, ";
			sql=sql+"ADDRESSLINE1, ";
			sql=sql+"ADDRESSLINE2, ";
			sql=sql+"ADDRESSLINE3, ";
			sql=sql+"ADDRESSLINE4, ";
			sql=sql+"CITY, ";
			sql=sql+"DISTRICT, ";
			sql=sql+"STATE, ";
			sql=sql+"COUNTRY, ";
			sql=sql+"PIN, ";
			sql=sql+"PHONENO, ";
			sql=sql+"CREATEDBY, ";
			sql=sql+"CREATEDDATE, ";
			sql=sql+"STATUS ";
			sql=sql+") VALUES (";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getTYPE())+", ";
			sql=sql+VENDOR_DETAILS.getVSSRNO()+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getNAME())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE1())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE2())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE3())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getADDRESSLINE4())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getCITY())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getDISTRICT())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getSTATE())+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getCOUNTRY())+", ";
			sql=sql+VENDOR_DETAILS.getPIN()+", ";
			sql=sql+VENDOR_DETAILS.getPHONENO()+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getCREATEDBY())+", ";
			sql=sql+VENDOR_DETAILS.getCREATEDDATE()+", ";
			sql=sql+krrCommon.AppendSinlequote(VENDOR_DETAILS.getSTATUS())+") ";
			System.out.println(sql);	
			stmt.executeUpdate(sql);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in inserting vendor supplier details "+e.getMessage();
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

	public String tans_Vendor_Details(java.sql.ResultSet rs,VENDOR_SUPPLIER VENDOR_SUPPLIER)
	{
		String l_ers="";
		try
		{
			VENDOR_SUPPLIER.setTYPE(krrCommon.CheckEmptyReturn1(rs.getString("TYPE")));
			System.out.println("dfngndfn"+VENDOR_SUPPLIER.getTYPE());
			VENDOR_SUPPLIER.setVSSRNO(rs.getLong("VSSRNO"));
			VENDOR_SUPPLIER.setNAME(krrCommon.CheckEmptyReturn1(rs.getString("NAME")));
			VENDOR_SUPPLIER.setADDRESSLINE1(krrCommon.CheckEmptyReturn1(rs.getString("ADDRESSLINE1")));
			VENDOR_SUPPLIER.setADDRESSLINE2(krrCommon.CheckEmptyReturn1(rs.getString("ADDRESSLINE2")));
			VENDOR_SUPPLIER.setADDRESSLINE3(krrCommon.CheckEmptyReturn1(rs.getString("ADDRESSLINE3")));
			VENDOR_SUPPLIER.setADDRESSLINE4(krrCommon.CheckEmptyReturn1(rs.getString("ADDRESSLINE4")));
			VENDOR_SUPPLIER.setCITY(krrCommon.CheckEmptyReturn1(rs.getString("CITY")));
			VENDOR_SUPPLIER.setDISTRICT(krrCommon.CheckEmptyReturn1(rs.getString("DISTRICT")));
			VENDOR_SUPPLIER.setSTATE(krrCommon.CheckEmptyReturn1(rs.getString("STATE")));
			VENDOR_SUPPLIER.setCOUNTRY(krrCommon.CheckEmptyReturn1(rs.getString("COUNTRY")));
			VENDOR_SUPPLIER.setPIN(rs.getLong("PIN"));
			VENDOR_SUPPLIER.setPHONENO(rs.getLong("PHONENO"));
			VENDOR_SUPPLIER.setSTATUS(krrCommon.CheckEmptyReturn1(rs.getString("STATUS")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error to get vendor details"+e.getMessage();
		}
		return l_ers;
	}
	public String edit_VENDOR_SUPPLIER_Details(Connection con,VENDOR_SUPPLIER VENDOR_DETAILS){
		//	com.terasoft.stores.bean.VENDOR_DETAILS vendorBean = new com.terasoft.stores.bean.VENDOR_DETAILS();
		Statement stmt=null ;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "select * from VENDOR_SUPPLIER where VSSRNO = "+VENDOR_DETAILS.getVSSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				l_ers=tans_Vendor_Details(rs, VENDOR_DETAILS);
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
