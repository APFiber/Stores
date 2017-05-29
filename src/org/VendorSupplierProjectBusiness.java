package org;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import bean.PROJECT_MASTER;
import bean.VENDOR_SUPPLIER;
import bean.VENDOR_SUPPLIER_PROJECT;

public class VendorSupplierProjectBusiness {
	KrrCommon krrCommon = new KrrCommon();
	Date_library Date_library=new Date_library();

	org.PROJECT_VENDOR_SUPPLIER_DB PROJECT_VENDOR_SUPPLIER_DB = new PROJECT_VENDOR_SUPPLIER_DB();
	public  String search_VENDOR_SUPPLIER_PROJECT(Connection con,List<PROJECT_MASTER> VENDOR_SUPPLIER_PROJECTList, bean.VSP_Commondata VSP_Commondata){

		String l_ers="";
		try{

			l_ers=PROJECT_VENDOR_SUPPLIER_DB.search_VENDOR_SUPPLIER_PROJECT(con, VENDOR_SUPPLIER_PROJECTList,VSP_Commondata);
			VSP_Commondata.setTotalNoOfRecords(VENDOR_SUPPLIER_PROJECTList.size());
			int excessValues = VENDOR_SUPPLIER_PROJECTList.size()%10;
			while(excessValues <= 10){
				VENDOR_SUPPLIER_PROJECTList.add(new PROJECT_MASTER());
				excessValues++;
			}
			//Total No of pages
			VSP_Commondata.setTotalNoOfPages( VENDOR_SUPPLIER_PROJECTList.size()/10);

		}
		catch (Exception e) {
			e.printStackTrace();
			l_ers = "error in Item service "+e.getMessage();
		}
		return l_ers;
	}

	public String getNameList(Connection con,List<VENDOR_SUPPLIER> VENDORDETAILSList)
	{
		String l_ers="";
		try
		{
			l_ers=PROJECT_VENDOR_SUPPLIER_DB.getname_VENDOR_SUPPLIER_PROJECT(con, VENDORDETAILSList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}


	public String getProjectListgrid(Connection con,List<VENDOR_SUPPLIER_PROJECT> VENDORSUPPLIERPROJECTList)
	{
		String l_ers="";
		try
		{
			l_ers=PROJECT_VENDOR_SUPPLIER_DB.getproject_VENDOR_SUPPLIER_PROJECT(con, VENDORSUPPLIERPROJECTList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	public String insert_VENDOR_SUPPLIER_PROJECT(Connection con,bean.VENDOR_SUPPLIER_PROJECT VENDORSUPPLIERPROJECT,bean.VSP_Commondata vpcommondata)
	{
		String l_ers="";
		try
		{
			if(PROJECT_VENDOR_SUPPLIER_DB.isVendorExits(con, VENDORSUPPLIERPROJECT.getVP_SRNO()))
			{
				l_ers=PROJECT_VENDOR_SUPPLIER_DB.update_VENDOR_SUPPLIER_PROJECT(con, VENDORSUPPLIERPROJECT);
			}
			else
			{
				VENDORSUPPLIERPROJECT.setCREATEDBY(vpcommondata.getLOGINID());
				VENDORSUPPLIERPROJECT.setCREATEDDATE(Date_library.date_Conv(vpcommondata.getLOGINDATE(),"dd/MM/yyyy","yyyyMMdd"));
				l_ers=PROJECT_VENDOR_SUPPLIER_DB.insert_VENDOR_SUPPLIER_PROJECT(con,VENDORSUPPLIERPROJECT);
			//	PROJECT_VENDOR_SUPPLIER_DB.edit_VENDOR_SUPPLIER_Details(con, VENDORSUPPLIERPROJECT);
		//		PROJECT_VENDOR_SUPPLIER_DB.update_VENDOR_PROJECT_table(con, vendordetails);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;	
	}

	public String edit_VENDOR_SUPPLIER_PROJECT(Connection con,bean.VENDOR_SUPPLIER_PROJECT VENDORSUPPLIERPROJECT)
	{
		String l_ers="";
		try
		{
			
			l_ers=PROJECT_VENDOR_SUPPLIER_DB.edit_VENDOR_SUPPLIER_PROJECT_Details(con, VENDORSUPPLIERPROJECT);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	
	public String Getreq_VSP_CommonData(HttpServletRequest req, bean.VSP_Commondata VSP_Commondata)
	 {
		 String errMsg="";
		 try
		 {
			    VSP_Commondata.setUSERNAME(req.getParameter("USERNAME"));
				VSP_Commondata.setLOGINDATE(req.getParameter("DATE"));
				VSP_Commondata.setUSERROLE(req.getParameter("USERROLE"));
				VSP_Commondata.setLOGINID(req.getParameter("LOGINID"));
				VSP_Commondata.setPROJECTSRNO(krrCommon.ConvertNum(req.getParameter("PROJECTSRNO")));
				VSP_Commondata.setPROJECTNAME(req.getParameter("PROJECTNAME"));
				VSP_Commondata.setMANAGER(req.getParameter("MANAGER"));
				VSP_Commondata.setLOCATION(req.getParameter("LOCATION"));
				VSP_Commondata.setEMAILID(req.getParameter("EMAILID"));			
				VSP_Commondata.setVPSRNO(krrCommon.ConvertNum(req.getParameter("VPSRNO")));
				VSP_Commondata.setVSSRNO(krrCommon.ConvertNum(req.getParameter("VSSRNO")));
				VSP_Commondata.setPAGENO((int)krrCommon.ConvertNum(req.getParameter("pageNo")));
			 

		 }
		 catch (Exception e)
		 {
			 errMsg="Error in Getreq_VSP_CommonData "+e.getMessage();
		 }
		 return errMsg;
	 }
	public String get_VENDOR_SUPPLIER_PROJECT(Connection con,bean.PROJECT_MASTER PROJECT_MASTER,long srno)
	{
		String l_ers="";
		try
		{
			l_ers=PROJECT_VENDOR_SUPPLIER_DB.getProjectdetails(con, PROJECT_MASTER,srno);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	public String insert_VENDOR_PROJECT(Connection con,bean.VENDOR_PROJECT VENDORSUPPLIERPROJECT,bean.VSP_Commondata vpcommondata)
	{
		String l_ers="";
		try
		{
			if(PROJECT_VENDOR_SUPPLIER_DB.isVendorExits(con, VENDORSUPPLIERPROJECT.getVPSRNO()))
			{
//				l_ers=PROJECT_VENDOR_SUPPLIER_DB.update_VENDOR_SUPPLIER_PROJECT(con, VENDORSUPPLIERPROJECT);
			}
			else
			{
				VENDORSUPPLIERPROJECT.setCREATEDBY(vpcommondata.getLOGINID());
				VENDORSUPPLIERPROJECT.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
				l_ers=PROJECT_VENDOR_SUPPLIER_DB.insert_VENDOR_PROJECT(con,VENDORSUPPLIERPROJECT);
			//	PROJECT_VENDOR_SUPPLIER_DB.edit_VENDOR_SUPPLIER_Details(con, VENDORSUPPLIERPROJECT);
		//		PROJECT_VENDOR_SUPPLIER_DB.update_VENDOR_PROJECT_table(con, vendordetails);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;	
	}
}
