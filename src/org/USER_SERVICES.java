package org;
import java.sql.Connection;

import java.util.List;
import org.KrrCommon;
import bean.USER_MASTER;
import org.Date_library;
public class USER_SERVICES {
	KrrCommon krrCommon = new KrrCommon();
	Date_library date=new Date_library();
	org.USER_DB USERDB=new org.USER_DB();
	bean.USER_COMMONDATA common_data = new bean.USER_COMMONDATA();

	public String search_user_master(Connection con,List<USER_MASTER> userBeanList, bean.USER_COMMONDATA common_data)
	{
		String l_ers="";

		try{
			l_ers=USERDB.search_user_master(con, userBeanList,common_data);
			//Total No of records
			common_data.setTotalNoOfRecords(userBeanList.size());
			//getting values more than multiple of ten
			//adding remaining empty bean
			int excessValues = userBeanList.size()%10;
			System.out.println(excessValues);  
			if(excessValues!=0)
			{
				while(excessValues < 10){
					userBeanList.add(new USER_MASTER());
					excessValues++;
				}
			}
			//Total No of pages
			common_data.setTotalNoOfPages(userBeanList.size()/10);
			System.out.println(userBeanList.size());
			System.out.println(common_data.getTotalNoOfPages()); 
		}
		catch (Exception e) {
			e.printStackTrace(); 
			l_ers = "error in User service "+e.getMessage();
		}
		return l_ers;
	}

	public String insert_update_user_master(Connection con,USER_MASTER user,bean.USER_COMMONDATA commonData  )
	{
		String l_ers="";
		try {
			if(user.getUSERSRNO()!=0)
			{
				l_ers=USERDB.update_user_master(con, user);
			}
			else
			{
				if(USERDB.is_loginid_exists(con, user.getUSERLOGINID()))
				{
					l_ers="Loginid already exists";
				}

				else
				{
					user.setCREATEDBY(commonData.getLOGINID());
					user.setCREATEDDATE(date.date_Conv(commonData.getLOGINDATE(), "dd/MM/yyyy", "yyyyMMdd"));
					user.setSTATUS(user.getSTATUS());
					l_ers=USERDB.insert_user_master(con, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String authorize_user_master(Connection con,long userSrNo)
	{
		String l_ers="";
		String STATUS=USERDB.get_user_status(con, userSrNo);
		if(STATUS.equalsIgnoreCase("INACTIVE"))
		{
			try {
				l_ers=USERDB.change_user_status(con, userSrNo,"ACTIVE");

			} catch (Exception e) {
				l_ers="error in authorize user details "+e.getMessage();
				e.printStackTrace();
			}
		}
		else
		{
			try {
				l_ers=USERDB.change_user_status(con, userSrNo,"INACTIVE");

			} catch (Exception e) {
				l_ers="error in authorize user details "+e.getMessage();
				e.printStackTrace();
			}		}

		return l_ers;
	}

//	public String delete_user_master(Connection con,long userSrNo)
//	{
//		String l_ers="";
//		try{
//			String STATUS=USERDB.get_user_status(con, userSrNo);
//			if(!STATUS.equalsIgnoreCase("DELETED"))
//			{
//				try
//				{
//					l_ers=USERDB.change_user_status(con, userSrNo,"DELETED");
//
//				}	catch (Exception e) {
//					l_ers="error in deleting user details "+e.getMessage();
//					e.printStackTrace();
//				}
//			}
//			else
//			{
//				l_ers="Only Active and inactive can be deleted";
//			} 
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return l_ers;
//	}

	public String modify_user_master(Connection con,USER_MASTER usermaster)
	{
		String l_ers="";


		try{
			String STATUS=USERDB.get_user_status(con, usermaster.getUSERSRNO());
			if(STATUS.equalsIgnoreCase("INACTIVE"))
			{
				l_ers=USERDB.get_user(con, usermaster);

			}
			else
			{
				l_ers="Only inactive records can be modified";
			}
		}catch(Exception e)
		{

			e.printStackTrace();

		}
		return l_ers;
	}

//	public String delete_Status(Connection con,List<USER_MASTER> usermaster)
//	{
//		String l_ers="";
//		try{
//			l_ers=USERDB.change_deleted(con, usermaster);
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return l_ers;
//	}

	public String userloginid_reset(Connection con,USER_MASTER USERMASTER,String loginid)
	{
		String l_ers="";
		try
		{
			l_ers=	USERDB.userloginid_reset(con, USERMASTER,loginid);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;

	}
	public String get_userLoginid(Connection con,java.util.Vector UserLoginid )
	{
		String l_ers="";
		try
		{
			l_ers = USERDB.get_userDetails(con, UserLoginid);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;

	}
	public String new_password(Connection con,String loginid,String password)
	{
		String l_ers="";
		try
		{
			l_ers=USERDB.new_password(con, loginid, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String password_check(Connection con,String password)
	{
		boolean l_ers;
		try
		{
			l_ers=USERDB.is_password_check(con, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return password;

	}
}	 	 


