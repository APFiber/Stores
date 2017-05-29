package org;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.VENDOR_SUPPLIER;

public class VendorBusinessLayer 
{
	bean.VENDOR_SUPPLIER VENDOR_DETAILS=new VENDOR_SUPPLIER();
	VENDOR_DB VENDORDB= new VENDOR_DB();
	KrrCommon KrrCommon=new KrrCommon();
	Date_library Date_library=new Date_library();
	public  String Search_VENDOR_SUPPLIER_details(Connection con,List<VENDOR_SUPPLIER> VENDOR_DETAILSList, bean.VENDOR_CommonData VENDORCommomData){

		String l_ers="";
		try{


			l_ers=VENDORDB.Search_VENDOR_SUPPLIER_details(con,VENDOR_DETAILSList, VENDORCommomData);
			VENDORCommomData.setTotalNoOfRecords(VENDOR_DETAILSList.size());
			int excessValues = VENDOR_DETAILSList.size()%10;
			if(excessValues != 0 || VENDOR_DETAILSList.size() == 0)
			{
			while(excessValues <= 10){
				VENDOR_DETAILSList.add(new VENDOR_SUPPLIER());
				excessValues++;
			}
			}
			//Total No of pages
			VENDORCommomData.setTotalNoOfPages( VENDOR_DETAILSList.size()/10);

		}
		catch (Exception e) {
			e.printStackTrace();
			l_ers = "error in Vendor/Supplier service "+e.getMessage();
		}
		return l_ers;
	}
	//Type drop down
	public String getTypeList(Connection con,List<VENDOR_SUPPLIER> TypeList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDORDB.getType(con,TypeList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	//City drop down
	public String getCityList(Connection con,List<VENDOR_SUPPLIER> CityList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDORDB.getCity(con, CityList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	//District drop down
	public String getDistrictList(Connection con,List<VENDOR_SUPPLIER> DistrictList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDORDB.getDistrict(con, DistrictList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	
	//City drop down
	public String getStateList(Connection con,List<VENDOR_SUPPLIER> StateList)
	{
		String l_ers="";
		try
		{
			l_ers=VENDORDB.getState(con, StateList);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	public String Getreq_VENDOR_CommonData(HttpServletRequest req, bean.VENDOR_CommonData VENDOR_CommonData)
	 {
		 String errMsg="";
		 try
		 {
			 VENDOR_CommonData.setUSERNAME(req.getParameter("USERNAME"));
			 VENDOR_CommonData.setLOGINDATE(req.getParameter("DATE"));
			 VENDOR_CommonData.setUSERROLE(req.getParameter("USERROLE"));
			 VENDOR_CommonData.setLOGINID(req.getParameter("LOGINID"));
			 VENDOR_CommonData.setTYPE(KrrCommon.CheckEmptyReturn(req.getParameter("TYPE")));
			 VENDOR_CommonData.setVSSRNO(KrrCommon.ConvertNum(req.getParameter("VSSRNO")));
			 VENDOR_CommonData.setNAME(KrrCommon.CheckEmptyReturn(req.getParameter("NAME")));
			 VENDOR_CommonData.setCITY(KrrCommon.CheckEmptyReturn(req.getParameter("CITY")));
			 VENDOR_CommonData.setDISTRICT(KrrCommon.CheckEmptyReturn(req.getParameter("DISTRICT")));
			 VENDOR_CommonData.setSTATE(KrrCommon.CheckEmptyReturn(req.getParameter("STATE")));
			 VENDOR_CommonData.setPIN(KrrCommon.ConvertNum(req.getParameter("PIN")));
			 VENDOR_CommonData.setPROJECT(KrrCommon.CheckEmptyReturn(req.getParameter("PROJECT")));
			 VENDOR_CommonData.setMANAGER(KrrCommon.CheckEmptyReturn(req.getParameter("MANAGER")));
			 VENDOR_CommonData.setPHONENO(KrrCommon.ConvertNum(req.getParameter("PHONENO")));
			 VENDOR_CommonData.setSTATUS(KrrCommon.CheckEmptyReturn(req.getParameter("STATUS")));
			 VENDOR_CommonData.setPAGENO((int)KrrCommon.ConvertNum(req.getParameter("pageNo")));			 

		 }
		 catch (Exception e)
		 {
			 errMsg="Error in Getreq_VENDOR_CommonData "+e.getMessage();
		 }
		 return errMsg;
	 }
	
	public String Getreq_VENDOR_Data(HttpServletRequest req, bean.VENDOR_SUPPLIER VENDOR_SUPPLIER)
	 {
		 String errMsg="";
		 try
		 {
			 VENDOR_SUPPLIER.setTYPE(req.getParameter("ADDTYPE"));
			 VENDOR_SUPPLIER.setNAME(req.getParameter("ADDNAME"));
			 VENDOR_SUPPLIER.setADDRESSLINE1(req.getParameter("ADDADDRESS1"));
			 VENDOR_SUPPLIER.setADDRESSLINE2(req.getParameter("ADDADDRESS2"));
			 VENDOR_SUPPLIER.setADDRESSLINE3(req.getParameter("ADDADDRESS3"));
			 VENDOR_SUPPLIER.setADDRESSLINE4(req.getParameter("ADDADDRESS4"));
			 VENDOR_SUPPLIER.setCITY(req.getParameter("ADDCITY"));
			 VENDOR_SUPPLIER.setDISTRICT(req.getParameter("ADDDISTRICT"));
			 VENDOR_SUPPLIER.setSTATE(req.getParameter("ADDSTATE"));
			 VENDOR_SUPPLIER.setCOUNTRY(req.getParameter("ADDCOUNTRY"));
			 VENDOR_SUPPLIER.setPIN(KrrCommon.ConvertNum(req.getParameter("ADDPIN")));
			 VENDOR_SUPPLIER.setPHONENO(KrrCommon.ConvertNum(req.getParameter("ADDPHONENO")));		 

		 }
		 catch (Exception e)
		 {
			 errMsg="Error in Getreq_VENDOR_CommonData "+e.getMessage();
		 }
		 return errMsg;
	 }
	
	//Save
	public String insert_update_VENDOR_SUPPLIER_details(Connection con,VENDOR_SUPPLIER VENDORDETAILS,bean.VENDOR_CommonData VENDORCommonData)
	{
		String l_ers="";
		try
		{
			System.out.println("code to update "+VENDORDETAILS.getVSSRNO());
			if(VENDORDETAILS.getVSSRNO()!=0){
				
				l_ers=VENDORDB.update_VENDOR_SUPPLIER(con, VENDORDETAILS);
			}
			else
			{
					System.out.println("insert in service class");
					VENDORDETAILS.setCREATEDBY(VENDORCommonData.getLOGINID());
					VENDORDETAILS.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
					VENDORDETAILS.setSTATUS("INACTIVE");
					l_ers=VENDORDB.insert_VENDOR_SUPPLIER(con, VENDORDETAILS);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;	
	}

	//Edit or Modify
	public  String edit_VENDOR_SUPPLIER_details(Connection con,VENDOR_SUPPLIER VENDOR_SUPPLIER)
	{
		String l_ers="";
		String VENDORSTATUS=VENDORDB.get_VENDOR_SUPPLIER_Status(con, VENDOR_SUPPLIER.getVSSRNO());
		try
		{
			if(VENDORSTATUS.equalsIgnoreCase("INACTIVE"))
			{
				l_ers=VENDORDB.edit_VENDOR_SUPPLIER_Details(con,VENDOR_SUPPLIER);
			}
			else
			{
				l_ers="Active or Deleted items can not be edited";
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
	
	//Authorize
	public String authorize_VENDOR_SUPPLIER_details(Connection con,long vscode)
	{
		String l_ers="";
		String STATUS=VENDORDB.get_VENDOR_SUPPLIER_Status(con, vscode);
		try{
					
		if(STATUS.equalsIgnoreCase("INACTIVE"))
		{
			try
			{
				l_ers=VENDORDB.change_VENDOR_SUPPLIER_status(con, vscode, "ACTIVE");
				
			}catch(Exception e){
				l_ers="error in authorize vendor details "+e.getMessage();
				e.printStackTrace();
			}
		}
		else
		{
			l_ers="Only Inactive records can be authorized";
		}
		}catch(Exception e){
			l_ers="error in authorizing vendor details "+e.getMessage();
			e.printStackTrace();
		}
		return l_ers;
	}
	//Delete 
	public String delete_VENDOR_SUPPLIER_details(Connection con,long vssrno)
	{
		String l_ers="";
		String STATUS=VENDORDB.get_VENDOR_SUPPLIER_Status(con, vssrno);
		if(!STATUS.equalsIgnoreCase("DELETED"))
		{
			try
			{
				l_ers=VENDORDB.change_VENDOR_SUPPLIER_status(con, vssrno, "DELETED");
			}catch(Exception e){
				l_ers="error in deleting vendor details "+e.getMessage();
				e.printStackTrace();
			}
		}
		else
		{
			l_ers="Selcted item already in DELETED status";
		}
		return l_ers;
	}

}
