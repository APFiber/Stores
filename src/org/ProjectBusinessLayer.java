package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.VENDOR_SUPPLIER;
import bean.VENDOR_SUPPLIER_PROJECT;

public class ProjectBusinessLayer 
{
	bean.PROJECT_MASTER PROJECT_MASTER=new bean.PROJECT_MASTER();
	VENDOR_SUPPLIER_PROJECT_DB VENDOR_SUPPLIER_PROJECT_DB=new VENDOR_SUPPLIER_PROJECT_DB();
	KrrCommon KrrCommon=new KrrCommon();
	Date_library Date_library=new Date_library();
	public  String Search_PROJECT_MASTER_details(Connection con,List<bean.PROJECT_MASTER> PROJECT_MASTERList, bean.PROJECT_CommonData PROJECT_CommonData){

		String l_ers="";
		try{


			l_ers=VENDOR_SUPPLIER_PROJECT_DB.Search_PROJECT_MASTER_details(con,PROJECT_MASTERList, PROJECT_CommonData);
			PROJECT_CommonData.setTotalNoOfRecords(PROJECT_MASTERList.size());
			int excessValues = PROJECT_MASTERList.size()%10;
			if(excessValues != 0 || PROJECT_MASTERList.size() == 0)
			{
			while(excessValues <= 10){
				PROJECT_MASTERList.add(new bean.PROJECT_MASTER());
				excessValues++;
			}
			}
			//Total No of pages
			PROJECT_CommonData.setTotalNoOfPages( PROJECT_MASTERList.size()/10);

		}
		catch (Exception e) {
			e.printStackTrace();
			l_ers = "error in Project service "+e.getMessage();
		}
		return l_ers;
	}

	//Project drop down
	public String getProjectListgrid(Connection con,List<bean.PROJECT_MASTER> PROJECT_MASTERList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDOR_SUPPLIER_PROJECT_DB.getproject_PROJECT_MASTER(con, PROJECT_MASTERList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	//Location drop down
	public String getLocationList(Connection con,List<bean.PROJECT_MASTER> LocationList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDOR_SUPPLIER_PROJECT_DB.getlocation_PROJECT_MASTER(con, LocationList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	//Manager drop down
	public String getManagerList(Connection con,List<bean.PROJECT_MASTER> ManagerList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDOR_SUPPLIER_PROJECT_DB.getmanager_PROJECT_MASTER(con, ManagerList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	
	//City drop down
	public String getStateList(Connection con,List<bean.PROJECT_MASTER> StateList)
	{
		String l_ers="";
		try
		{
//			l_ers=VENDOR_SUPPLIER_PROJECT_DB.getState(con, StateList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	public String Getreq_PROJECT_CommonData(HttpServletRequest req, bean.PROJECT_CommonData PROJECT_CommonData)
	 {
		 String errMsg="";
		 try
		 {
			 PROJECT_CommonData.setUSERNAME(req.getParameter("USERNAME"));
			 PROJECT_CommonData.setLOGINDATE(req.getParameter("DATE"));
			 PROJECT_CommonData.setUSERROLE(req.getParameter("USERROLE"));
			 PROJECT_CommonData.setLOGINID(req.getParameter("LOGINID"));
			 PROJECT_CommonData.setPROJECTSRNO(KrrCommon.ConvertNum(req.getParameter("PROJECTSRNO")));
			 PROJECT_CommonData.setPROJECTNAME(KrrCommon.CheckEmptyReturn(req.getParameter("PROJECTNAME")));
			 PROJECT_CommonData.setLOCATION(KrrCommon.CheckEmptyReturn(req.getParameter("LOCATION")));
			 System.out.println("dbsbgfdrjbg   "+PROJECT_CommonData.getLOCATION());
			 PROJECT_CommonData.setMANAGER(KrrCommon.CheckEmptyReturn(req.getParameter("MANAGER")));
			 PROJECT_CommonData.setEMAILID(KrrCommon.CheckEmptyReturn(req.getParameter("EMAILID")));
			 PROJECT_CommonData.setPAGENO((int)KrrCommon.ConvertNum(req.getParameter("pageNo")));
			 

		 }
		 catch (Exception e)
		 {
			 errMsg="Error in Getreq_PROJECT_CommonData "+e.getMessage();
		 }
		 return errMsg;
	 }
	
	public String Getreq_PROJECT_Data(HttpServletRequest req, bean.PROJECT_MASTER PROJECT_MASTER)
	 {
		 String errMsg="";
		 try
		 {
			 PROJECT_MASTER.setPROJECTSRNO(KrrCommon.ConvertNum(req.getParameter("ADDPROJECTSRNO")));
			 PROJECT_MASTER.setPROJECTNAME(req.getParameter("ADDPROJECTNAME"));
			 PROJECT_MASTER.setMANAGER(req.getParameter("ADDMANAGER"));
			 PROJECT_MASTER.setLOCATION(req.getParameter("ADDLOCATION"));
			 PROJECT_MASTER.setEMAILID(req.getParameter("ADDEMAILID"));
		 }
		 catch (Exception e)
		 {
			 errMsg="Error in Getreq_PROJECT_CommonData "+e.getMessage();
		 }
		 return errMsg;
	 }
	
	//Save
	public String insert_update_PROJECT_MASTER_details(Connection con,bean.PROJECT_MASTER PROJECT_MASTER,bean.PROJECT_CommonData PROJECT_CommonData)
	{
		String l_ers="";
		try
		{
			System.out.println("code to update "+PROJECT_MASTER.getPROJECTSRNO());
			if(PROJECT_MASTER.getPROJECTSRNO()!=0){
				
				l_ers=VENDOR_SUPPLIER_PROJECT_DB.update_PROJECT_MASTER(con, PROJECT_MASTER);
			}
			else
			{
					System.out.println("insert in service class");
					PROJECT_MASTER.setCREATEDBY(PROJECT_CommonData.getLOGINID());
					PROJECT_MASTER.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
//					PROJECT_MASTER.setSTATUS("INACTIVE");
					l_ers=VENDOR_SUPPLIER_PROJECT_DB.insert_PROJECT_MASTER(con, PROJECT_MASTER);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;	
	}

	//Edit or Modify
	public  String edit_PROJECT_MASTER_details(Connection con,bean.PROJECT_MASTER PROJECT_MASTER)
	{
		String l_ers="";
		try
		{
				l_ers=VENDOR_SUPPLIER_PROJECT_DB.edit_PROJECT_MASTER_Details(con,PROJECT_MASTER);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	
//	//Authorize
//	public String authorize_VENDOR_SUPPLIER_details(Connection con,long vscode)
//	{
//		String l_ers="";
//		String STATUS=VENDOR_SUPPLIER_PROJECT_DB.get_VENDOR_SUPPLIER_Status(con, vscode);
//		if(STATUS.equalsIgnoreCase("INACTIVE"))
//		{
//			try
//			{
//				l_ers=VENDOR_SUPPLIER_PROJECT_DB.change_VENDOR_SUPPLIER_status(con, vscode, "ACTIVE");
//				
//			}catch(Exception e){
//				l_ers="error in authorize vendor details "+e.getMessage();
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			l_ers="Only Inactive records can be authorized";
//		}
//		return l_ers;
//	}
	//Delete 
	public String delete_PROJECT_MASTER_details(Connection con,long vssrno)
	{
		String l_ers="";
//		String STATUS=VENDOR_SUPPLIER_PROJECT_DB.get_PROJECT_MASTER_Status(con, vssrno);
//		if(!STATUS.equalsIgnoreCase("DELETED"))
//		{
//			try
//			{
////				l_ers=VENDOR_SUPPLIER_PROJECT_DB.change_PROJECT_MASTER_status(con, vssrno, "DELETED");
//			}catch(Exception e){
//				l_ers="error in deleting vendor details "+e.getMessage();
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			l_ers="Selcted item already in DELETED status";
//		}
		return l_ers;
	}

}
