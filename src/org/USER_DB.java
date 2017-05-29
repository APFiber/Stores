package org;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.List;
import org.KrrCommon;
import bean.USER_MASTER;

public class USER_DB 
{
	KrrCommon krrCommon = new KrrCommon();
	org.Date_library Datalibrary  = new Date_library();
	public String search_user_master(Connection con,List<USER_MASTER> userData,bean.USER_COMMONDATA common_data)
	{   
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String errMsg="";
		String condition="";
		try
		{
			stmt = con.createStatement();
			sql="select * from USER_MASTER ";
			if(krrCommon.isValue(common_data.getUSERLOGINID())){
				condition = condition +" USERLOGINID = "+krrCommon.AppendSinlequote(common_data.getUSERLOGINID());
			}
			if(krrCommon.isValue(common_data.getUSERROLE1())){
				condition = condition+" and USERROLE LIKE "+krrCommon.AppendSinlequote(common_data.getUSERROLE1());			
			}

			if(krrCommon.isValue(common_data.getSTATUS())){
				condition = condition +" and STATUS LIKE "+krrCommon.AppendSinlequote(common_data.getSTATUS());
			}
			if(condition !=""){
				condition = condition.trim();
				if(condition.substring(0, 3).equalsIgnoreCase("and")){
					condition = condition.substring(3, condition.length());
				}
				sql = sql+" where "+ condition+ " ";	
			}
			sql=sql+" order by USERSRNO ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int i=1;
			while (rs.next()) 
			{
				USER_MASTER userBean = new USER_MASTER();
				errMsg=trans_user_master(rs, userBean);
				userData.add(userBean);
				i++;
			}
		}catch(Exception e)
		{

			errMsg="Error User Db "+ e.getMessage();
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

	public String update_user_master(Connection con,USER_MASTER user_details)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		try
		{	
			stmt = con.createStatement();
			sql= "update USER_MASTER set ";
			sql= sql+"USERNAME = " +krrCommon.AppendSinlequote(user_details.getUSERNAME())+",";
			sql= sql+"USERLOGINID = "+krrCommon.AppendSinlequote(user_details.getUSERLOGINID())+",";
			sql= sql+"USERPASSWORD = " +krrCommon.AppendSinlequote(user_details.getUSERPASSWORD())+",";
			sql= sql+"USERROLE = "+krrCommon.AppendSinlequote(user_details.getUSERROLE())+",";
			sql=sql+"STATUS = "+krrCommon.AppendSinlequote(user_details.getSTATUS())+" ";
			sql=sql+"where USERSRNO = " +user_details.getUSERSRNO();
			System.out.println(sql);
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{

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

	public String get_user_status(Connection con,long userSrNo)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String STATUS="";
		try{
			stmt = con.createStatement();
			sql= "select STATUS FROM USER_MASTER where USERSRNO="+userSrNo;
			rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) 
			{
				STATUS=rs.getString("STATUS");
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
				if(stmt != null) stmt.close();
			}
			catch(Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return STATUS;
	}


	public boolean is_user_exists(Connection con,long userSrNo)
	{
		boolean userExits = false;
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		try{
			stmt = con.createStatement();
			sql= "select * from USER_MASTER where USERSRNO = "+userSrNo;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				userExits = true;
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
		return userExits;
	}

	public boolean is_loginid_exists(Connection con,String userLoginId)
	{
		boolean Loginid = false;
		Statement stmt = null;
		String sql;
		ResultSet rs =null;
		try{
			stmt = con.createStatement();
			sql= "select * from USER_MASTER where USERLOGINID = "+krrCommon.AppendSinlequote(userLoginId);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Loginid = true;
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
		return Loginid;
	}


	public String insert_user_master(Connection con, USER_MASTER user)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		try{

			long seqId=krrCommon.Nextval_SQ("USER_MASTER_SQ", con);
			if (seqId>0)
			{
				user.setUSERSRNO(seqId);	
				sql = "insert into USER_MASTER (";
				sql=sql+"USERSRNO,";
				sql=sql+"USERLOGINID,";
				sql=sql+"USERNAME,";
				sql=sql+"USERPASSWORD,";
				sql=sql+"USERROLE, ";
				sql=sql+"CREATEDBY,";
				sql=sql+"CREATEDDATE,";
				sql=sql+"USERPWDRESETDATE,";
				sql=sql+"STATUS";
				sql=sql+") VALUES (";
				sql=sql+(user.getUSERSRNO())+", ";
				sql=sql+krrCommon.AppendSinlequote(user.getUSERLOGINID())+",";
				sql=sql+krrCommon.AppendSinlequote(user.getUSERNAME())+",";
				sql=sql+krrCommon.AppendSinlequote(user.getUSERPASSWORD())+",";
				sql=sql+krrCommon.AppendSinlequote(user.getUSERROLE())+", ";
				sql=sql+krrCommon.AppendSinlequote(user.getCREATEDBY())+", ";
				sql=sql+krrCommon.AppendSinlequote(user.getCREATEDDATE())+", ";
				sql=sql+krrCommon.AppendSinlequote(user.getUSERPWDRESETDATE())+", ";
				sql=sql+krrCommon.AppendSinlequote(user.getSTATUS())+") ";
				System.out.println(sql);
				stmt = con.createStatement();	
				stmt.execute(sql);
			}
			else
			{
				errMsg="USER_MASTER_SQ is missing ";
			}
		}
		catch(Exception e)
		{
			errMsg="Error in userdb "+e.getMessage();
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
	public String get_user(Connection con,USER_MASTER userBean)
	{
		Statement stmt=null;
		String sql;
		String errMsg="";
		ResultSet rs=null;
		try{
			stmt = con.createStatement();
			sql= "select * from USER_MASTER where USERSRNO = "+userBean.getUSERSRNO();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				errMsg=trans_user_master(rs, userBean);
			}
		}
		catch(Exception e)
		{
			errMsg="Error in user details  "+e.getMessage();
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

	public String trans_user_master(ResultSet rs,USER_MASTER userBean)
	{
		String l_ers="";
		try
		{
			userBean.setUSERSRNO(rs.getLong("USERSRNO"));
			userBean.setUSERNAME(krrCommon.CheckEmptyReturn(rs.getString("USERNAME")));
			userBean.setUSERLOGINID(krrCommon.CheckEmptyReturn(rs.getString("USERLOGINID")));
			userBean.setUSERPASSWORD(krrCommon.CheckEmptyReturn(rs.getString("USERPASSWORD")));
			userBean.setUSERROLE(krrCommon.CheckEmptyReturn(rs.getString("USERROLE")));
			userBean.setCREATEDBY(krrCommon.CheckEmptyReturn(rs.getString("CREATEDBY")));
			userBean.setCREATEDDATE(krrCommon.CheckEmptyReturn(rs.getString("CREATEDDATE")));
			userBean.setUSERPWDRESETDATE(krrCommon.CheckEmptyReturn(rs.getString("USERPWDRESETDATE")));
			userBean.setSTATUS(krrCommon.CheckEmptyReturn(rs.getString("STATUS")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l_ers="Error in user results "+e.getMessage();
		}
		return l_ers;
	}

	public String change_user_status(Connection con,long userSrNo,String status) 
	{
		Statement stmt=null;
		String sql;
		int recordsUpdated=0;
		String l_ers="";
		try{
			stmt = con.createStatement();
			sql= "update USER_MASTER set STATUS = "+krrCommon.AppendSinlequote(status)+" where USERSRNO="+userSrNo;
			System.out.println(sql);
			recordsUpdated=stmt.executeUpdate(sql);
			if(recordsUpdated==0)
			{
				l_ers="Records not updated";
			}
		}
		catch(Exception e)
		{
			l_ers="Error in user changing status";
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
//	public String change_deleted(Connection con,List<USER_MASTER> USER_MASTER)	
//
//	{
//		Statement stmt=null;
//		String sql;
//		String l_ers="";
//		ResultSet rs=null;
//		try
//		{
//			stmt=con.createStatement();
//			sql="select * from USER_MASTER where STATUS <> 'DELETED'";
//			rs=stmt.executeQuery(sql);
//
//			USER_MASTER USERMASTER = new USER_MASTER();
//			l_ers=trans_user_master(rs, USERMASTER);
//			USER_MASTER.add(USERMASTER);
//		}
//		catch(Exception e)
//		{
//			l_ers="Error in deleted changing status";
//		}
//		finally
//		{
//			try
//			{
//				if(stmt !=null)stmt.close();
//			}
//			catch(Exception e2)
//			{
//				e2.printStackTrace();
//			}
//		}
//		return l_ers;
//	}
	public String authenticate_user(Connection con , bean.USER_MASTER USERMASTER){

		String l_ers="";		
		String sql;
		Statement stmt=null;
		ResultSet rs=null;

		try
		{
			stmt = con.createStatement();
			sql="select * from USER_MASTER where USERLOGINID = "+krrCommon.AppendSinlequote(USERMASTER.getUSERLOGINID());
        	sql=sql+" and USERPASSWORD="+krrCommon.AppendSinlequote(USERMASTER.getUSERPASSWORD());
			System.out.println("aun=="+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				l_ers=trans_user_master(rs,USERMASTER);
			}
			else
			{
				l_ers = "User Details Not Found";
			}
		}catch(Exception e){
			l_ers = "Error in Login db "+ e.getMessage();
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
				e.printStackTrace();
			}
		}
		System.out.println("Aun stats ==>"+l_ers);

		return l_ers;
	}

	public String userloginid_reset(Connection con,USER_MASTER USERMASTER,String loginid)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql="update USER_MASTER set " ;
			sql=sql+"USERPASSWORD = " + " ' ' ,";
			sql=sql+"USERPWDRESETDATE = "+Datalibrary.display_Current_Date("yyyyMMdd");
			sql=sql+" where USERLOGINID ='"+loginid+"'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
//			if(rs.next())
//			{
//			l_ers =trans_user_master(rs, USERMASTER);
//			}
//			else
//			{
//			l_ers="loginid doesn't exists";
//			}
		}
		catch (Exception e) {
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
		return l_ers;

	}

	public String get_userDetails(Connection con,java.util.Vector UserLoginid)
	{
		Statement stmt=null;
		String sql="";
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt = con.createStatement();
			sql="select USERLOGINID from USER_MASTER order by USERSRNO  ";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);

			while(rs.next())
			{
				UserLoginid.add(rs.getString("USERLOGINID"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null) stmt.close();
				if(rs !=null)rs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return l_ers;

	}

	public String new_password(Connection con,String loginid,String password)
	{
		Statement stmt=null;
		String sql;
		ResultSet rs=null;
		String l_ers="";
		try
		{
			stmt=con.createStatement();
			sql="update USER_MASTER set USERPASSWORD = "+krrCommon.AppendSinlequote(password)+ " where USERLOGINID = "+krrCommon.AppendSinlequote(loginid); 
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
		}
		catch (Exception e) {
			l_ers="user db password error";
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
		return l_ers;

	}

	public boolean is_password_check(Connection con,String password)
	{
		boolean Password = false;
		Statement stmt = null;
		String sql;
		ResultSet rs = null;
		try{
			stmt = con.createStatement();
			sql= "select * from USER_MASTER where USERPASSWORD = "+krrCommon.AppendSinlequote(password);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Password = true;
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
		return Password;
	}
	public String authenticate_userMaster(Connection con , bean.USER_MASTER USERMASTER){

		String l_ers="";		
		String sql;
		Statement stmt=null;
		ResultSet rs=null;

		try
		{
			stmt = con.createStatement();
			sql="select * from USER_MASTER where USERLOGINID = "+krrCommon.AppendSinlequote(USERMASTER.getUSERLOGINID());
//        	sql=sql+" and USERPASSWORD="+krrCommon.AppendSinlequote(USERMASTER.getUSERPASSWORD());
			System.out.println("aun=="+sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
			{
				l_ers=trans_user_master(rs,USERMASTER);
			}
			else
			{
				l_ers = "User Details Not Found";
			}
		}catch(Exception e){
			l_ers = "Error in Login db "+ e.getMessage();
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
				e.printStackTrace();
			}
		}
		

		return l_ers;
	}
}


