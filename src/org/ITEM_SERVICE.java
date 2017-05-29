package org;

import java.sql.Connection;
import java.util.List;
import org.KrrCommon;
import bean.ITEM_CommonData;
import bean.ITEM_DETAILS;
import bean.MAJOR_MINOR_CODE;
import bean.STOCK_SUMMARY;
import org.Date_library;

public class ITEM_SERVICE {
	KrrCommon krrCommon = new KrrCommon();
	Date_library Date_library=new Date_library();
	ITEM_DB ITEMDB=new ITEM_DB();
	MAJOR_MINOR_DB MAJORMINORDB=new MAJOR_MINOR_DB();
	ITEM_DETAILS item=new ITEM_DETAILS();

	public String get_item_details(Connection con,List<ITEM_DETAILS> itemBeanList,ITEM_CommonData ITEMCommonData)
	{
		String l_ers="";
		try
		{
			l_ers=ITEMDB.get_item_details(con, itemBeanList,ITEMCommonData);
			//Total No of records
			ITEMCommonData.setTotal_NoOf_Records(itemBeanList.size());
			//getting values more than multiple of ten
			//adding remaining empty bean
			int excessValues = itemBeanList.size()%10;
			if(excessValues!=0 || itemBeanList.size()==0)
			{
				while(excessValues <= 10)
				{
					itemBeanList.add(new ITEM_DETAILS());
					excessValues++;
				}
			}
			//Total No of pages
			ITEMCommonData.setTotal_NoOf_Pages(itemBeanList.size()/10);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			l_ers = "Error in Item Search "+e.getMessage();
		}
		return l_ers;
	}

	public String get_poitem_details(Connection con,List<ITEM_DETAILS> itemBeanList,ITEM_CommonData ITEMCommonData)
	{
		String l_ers="";
		try
		{
			ITEMCommonData.setPage_No(1);
			l_ers=ITEMDB.get_item_details_active(con, itemBeanList,ITEMCommonData);
			//Total No of records
			ITEMCommonData.setTotal_NoOf_Records(itemBeanList.size());
			int excessValues = itemBeanList.size()%10;
			if(excessValues!=0 || itemBeanList.size()==0)
			{
				while(excessValues <= 10)
				{
					itemBeanList.add(new ITEM_DETAILS());
					excessValues++;
				}
			}
			//Total No of pages
			ITEMCommonData.setTotal_NoOf_Pages(itemBeanList.size()/10);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	public String insert_update_item_details(Connection con,ITEM_DETAILS ITEMDETAILS,ITEM_CommonData ITEMCommonData)
	{
		String l_ers="";
		STOCK_SUMMARY STOCKSUMMARY=new STOCK_SUMMARY();
		try 
		{
			if(ITEMDETAILS.getITEMCODE()!=0)
			{
				
				ITEMDETAILS.setITEMCODE(ITEMDETAILS.getITEMCODE());
				long code=ITEMDETAILS.getITEMCODE();
				l_ers=ITEMDB.is_itemCode_exits(con, ITEMDETAILS);
				if(ITEMDETAILS.getITEMCODE()==code)
				{
					l_ers=ITEMDB.update_item_details(con,ITEMDETAILS);
					STOCKSUMMARY.setITEMCODE(ITEMDETAILS.getITEMCODE());
					STOCKSUMMARY.setOPENINGBALANCE(ITEMDETAILS.getOPENINGBALANCE());
					STOCKSUMMARY.setCLOSINGBALANCE(ITEMDETAILS.getOPENINGBALANCE());
					STOCKSUMMARY.setSUMMARYDATE(Date_library.display_Current_Date("yyyyMMdd"));
					l_ers=l_ers+ITEMDB.update_stock_summary(con, STOCKSUMMARY);

				}
				else
				{
					l_ers="Item code already exists with same Item Make, Model and Group";
				}
			}
			else
			{
				if(ITEMDB.is_item_exits(con,ITEMDETAILS))
				{
					l_ers="Item code already exists with same Item Make, Model and Group";	
				}
				else
				{
					ITEMDETAILS.setITEMCREATEDBY(ITEMCommonData.getLOGINID());
					ITEMDETAILS.setITEMCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
					ITEMDETAILS.setITEMSTATUS("INACTIVE");

					STOCKSUMMARY.setSUMMARYDATE(Date_library.display_Current_Date("yyyyMMdd"));
					STOCKSUMMARY.setITEMDESCRIPTION(ITEMDETAILS.getITEMDESCRIPTION());
					STOCKSUMMARY.setOPENINGBALANCE(ITEMDETAILS.getOPENINGBALANCE());
					STOCKSUMMARY.setRECEIPTS(0);
					STOCKSUMMARY.setISSUES(0);
					STOCKSUMMARY.setCLOSINGBALANCE(ITEMDETAILS.getOPENINGBALANCE());
					STOCKSUMMARY.setCREATEDBY(ITEMCommonData.getLOGINID());
					STOCKSUMMARY.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
					l_ers=ITEMDB.insert_item_details(con, ITEMDETAILS,STOCKSUMMARY);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String authorize_item_details(Connection con,long itemSrNo)
	{
		String l_ers="";
		try
		{
			String ITEMSTATUS=ITEMDB.get_item_status(con, itemSrNo);
			if(ITEMSTATUS.equalsIgnoreCase("INACTIVE"))
			{
				try 
				{	
					l_ers=ITEMDB.change_item_status(con, itemSrNo,"ACTIVE");
				} catch (Exception e) {
					l_ers="error in authorize item details "+e.getMessage();
					e.printStackTrace();
				}
			}
			else
			{
				l_ers="Only Inactive records can be authorized";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String delete_item_details(Connection con,long itemSrNo) 
	{
		String l_ers="";
		String ITEMSTATUS=ITEMDB.get_item_status(con, itemSrNo);
		try
		{
			if(!ITEMSTATUS.equalsIgnoreCase("DELETED"))
			{
				try 
				{
					l_ers=ITEMDB.change_item_status(con, itemSrNo,"DELETED");
				} 
				catch (Exception e) 
				{
					l_ers="Error in deleting item details "+e.getMessage();
					e.printStackTrace();
				}
			}
			else
			{
				l_ers="Selected Item already in DELETED status";
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	public String read_item_details(Connection con,ITEM_DETAILS ITEMDETAILS)
	{
		String l_ers="";
		String ITEMSTATUS=ITEMDB.get_item_status(con, ITEMDETAILS.getITEMCODE());
		STOCK_SUMMARY STOCKSUMMARY= new STOCK_SUMMARY();
		try
		{
			if(ITEMSTATUS.equalsIgnoreCase("INACTIVE"))
			{
				l_ers=ITEMDB.read_item_details(con, ITEMDETAILS);
				STOCKSUMMARY.setITEMCODE(ITEMDETAILS.getITEMCODE());
				l_ers=l_ers+ITEMDB.get_StockSummary_details(con, STOCKSUMMARY);
				ITEMDETAILS.setOPENINGBALANCE(STOCKSUMMARY.getOPENINGBALANCE());
			}
			else
			{
				l_ers="Active or Deleted Items can not be edited";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String read_item_detailsACTIVE(Connection con,ITEM_DETAILS ITEMDETAILS)
	{
		String l_ers="";
		String ITEMSTATUS=ITEMDB.get_item_status(con, ITEMDETAILS.getITEMCODE());
		try
		{
			if(ITEMSTATUS.equalsIgnoreCase("ACTIVE"))
			{
				if(ITEMDB.is_code_exits(con, ITEMDETAILS.getITEMCODE()))
				{
					l_ers="Opening balnaces for this Item code has been already entered";
				}
				else
				{
					l_ers=ITEMDB.read_item_details(con, ITEMDETAILS);
				}
			}
			else
			{
				l_ers="Only Active Items can add opening balance";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}


	public String get_code_list(Connection con,List<MAJOR_MINOR_CODE> MAJORMINORCODELIST, long majorCode)
	{
		String l_ers="";
		try
		{
			l_ers=MAJORMINORDB.ge_code_list(con, MAJORMINORCODELIST, majorCode);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}	

	public String insert_Stock_Summary(Connection con,ITEM_DETAILS ITEMDETAILS,STOCK_SUMMARY STOCKSUMMARY)
	{
		String l_ers="";
		try
		{
			STOCKSUMMARY.setITEMCODE(ITEMDETAILS.getITEMCODE());
			STOCKSUMMARY.setITEMDESCRIPTION(ITEMDETAILS.getITEMDESCRIPTION());
			STOCKSUMMARY.setSUMMARYDATE(Date_library.display_Current_Date("yyyyMMdd"));
			STOCKSUMMARY.setISSUES(0);
			STOCKSUMMARY.setRECEIPTS(0);
			STOCKSUMMARY.setOPENINGBALANCE(ITEMDETAILS.getOPENINGBALANCE());
			STOCKSUMMARY.setCLOSINGBALANCE(ITEMDETAILS.getOPENINGBALANCE());
			STOCKSUMMARY.setCREATEDBY(ITEMDETAILS.getITEMCREATEDBY());
			STOCKSUMMARY.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
			l_ers=ITEMDB.insert_STOCK_SUMMARY(con, STOCKSUMMARY);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}	
}
