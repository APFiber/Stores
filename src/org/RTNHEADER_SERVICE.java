package org;

import java.sql.Connection;
import java.util.List;
import bean.GP_DETAILS;
import bean.GP_HEADER;
import bean.RETURN_NOTE;
import bean.RTN_COMMONDATA;
import bean.STOCK_SUMMARY;

public class RTNHEADER_SERVICE {
    KrrCommon krrcommon = new KrrCommon();
    Date_library date = new Date_library();
    org.RTNHEADER_DB RTNHEADER_DB = new RTNHEADER_DB();
    bean.RTN_COMMONDATA common_data = new RTN_COMMONDATA();
   
    
   public String search_rtn_details(Connection con,List<RETURN_NOTE> rtnBeanList,RTN_COMMONDATA commonData)
   {
	   String l_ers="";
	   try
	   {
		   l_ers=RTNHEADER_DB.serach_rtn_details(con, rtnBeanList, commonData);
		 //  System.out.println("size= "+rtnBeanList.size());
		   //Total no records
		   commonData.setTotalNoOfRecords(rtnBeanList.size());
		 //getting values more than multiple of ten
			//adding remaining empty bean
		   int excessValues = rtnBeanList.size()%10;
		   if(excessValues!=0 )
		   {
			   while(excessValues < 10)
		   {
			   rtnBeanList.add(new RETURN_NOTE());
			   excessValues++;
			   }
		   }
	   commonData.setTotalNoOfPages(rtnBeanList.size()/10);
	   }
	   catch (Exception e) {
		e.printStackTrace();
		l_ers="Error in rtn services "+e.getMessage();
	}
	return l_ers;
	   
   }
   public String insert_update_rtn_details(Connection con,RETURN_NOTE rtn,bean.RTN_COMMONDATA commonData  )
	{
		String l_ers="";
		try {
			if(krrcommon.isValuenull(l_ers))
			{
				
			if(rtn.getRTNOTE_NO()!=0)
			{
				rtn.setRTNOTE_DATE(date.date_Conv(rtn.getRTNOTE_DATE(),"dd-MM-yyyy", "yyyyMMdd"));
				if(RTNHEADER_DB.is_qty_returned(con,rtn.getRTNOTE_QTYRETURNED(),rtn.getRTNOTE_GPNO(),rtn.getRTNOTE_ITEMCODE())==false)
				{
					l_ers="Qty returned should be less than in gp details issued qty";
				}
				if(krrcommon.isValuenull(l_ers))
					{
					l_ers=RTNHEADER_DB.edit_rtn_details(con, rtn);
					}
				
			}
			else
			{
				
				if(RTNHEADER_DB.is_rtn_exists(con, rtn.getRTNOTE_NO()))
				{
					l_ers="Loginid already exists";
				}

				else if(RTNHEADER_DB.is_qty_returned(con,rtn.getRTNOTE_QTYRETURNED(),rtn.getRTNOTE_GPNO(),rtn.getRTNOTE_ITEMCODE())==false)
				{
					l_ers="Qty returned should be less than in gp details issued qty";
				}
				
					else{
					rtn.setRTNOTE_CREATEDBY(commonData.getLOGINID());
					rtn.setRTNOTE_CREATEDDATE(date.date_Conv(commonData.getLOGINDATE(), "dd/MM/yyyy", "yyyyMMdd"));
					rtn.setRTNOTE_DATE(date.date_Conv(rtn.getRTNOTE_DATE(),"dd-MM-yyyy", "yyyyMMdd"));
					rtn.setRTNOTE_STATUS("ENTRY");
					l_ers=RTNHEADER_DB.insert_rtn(con, rtn);
				}
				
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}
   
    public String getGp_header(Connection con,GP_HEADER GPHEADER)
	{
		String l_ers="";
		try
		{
			l_ers=RTNHEADER_DB.getGpNo(con, GPHEADER);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
    
    public String getGp_details(Connection con,GP_DETAILS GPDETAILS)
    {
    	String l_ers="";
    	
    	try
    	{
    		l_ers=RTNHEADER_DB.getGpItem(con, GPDETAILS);
    	}
    	
    	catch (Exception e) {
    		
			e.printStackTrace();
		}
    
    	
    	return l_ers;
    }
    public String approved_rtn_details(Connection con,RETURN_NOTE rtn,RTN_COMMONDATA commonData,STOCK_SUMMARY STOCKSUMMARY)
    {
    	String l_ers="";
    	String STATUS=RTNHEADER_DB.get_mrn_status(con, rtn.getRTNOTE_NO());
    	if(STATUS.equalsIgnoreCase("ENTRY"))
    	{
    		try
    		{
    			rtn.setRTNOTE_APPROVEDBY(commonData.getLOGINID());
    			rtn.setRTNOTE_APPROVEDDATE(date.display_Current_Date("yyyyMMdd"));
    			STOCKSUMMARY.setCREATEDBY(rtn.getRTNOTE_APPROVEDBY());
    			STOCKSUMMARY.setCREATEDDATE(rtn.getRTNOTE_APPROVEDDATE());
    		    l_ers=RTNHEADER_DB.change_rtn_status(con,rtn,"APPROVED");
    	}
    	
    	catch (Exception e) {
    		
    		l_ers="Error in rtn approved details "+e.getMessage();
			e.printStackTrace();
		}
    	}
    	else
    	{
    		l_ers="Only Entry records can be approved";
    	}
		return l_ers;
    	
    }
    public String remove_rtn(Connection con,RETURN_NOTE rtn,RTN_COMMONDATA commonData)
    {
    	String l_ers="";
    	try
    	{
    		String RTNOTE_STATUS=RTNHEADER_DB.get_mrn_status(con, rtn.getRTNOTE_NO());
    		if(RTNOTE_STATUS.equalsIgnoreCase("ENTRY"))
    		{
  			rtn.setRTNOTE_APPROVEDBY(commonData.getLOGINID());
    			rtn.setRTNOTE_APPROVEDDATE(date.display_Current_Date("yyyyMMdd"));
    			l_ers=RTNHEADER_DB.change_rtn_status(con, rtn, "DELETED");
    		}
    		else
    		{
    			l_ers="Only Entry records can be deleted";
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
    	
    }
    public String edit_rtn(Connection con,RETURN_NOTE returnno)
    {
    	String l_ers="";
    	try
    	{
    		String RTNOTE_STATUS =RTNHEADER_DB.get_mrn_status(con, returnno.getRTNOTE_NO());
    		if(RTNOTE_STATUS.equalsIgnoreCase("ENTRY"))
    		{
    			l_ers=RTNHEADER_DB.get_rtn(con, returnno);
    		}
    		else
    		{
    			l_ers="Only Entry records can be modifed ";
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
    	
    }
//   public String sumqty(Connection con,RETURN_NOTE returnnote)
//    {
//    	String l_ers="";
//    	try
//    	{
//    		l_ers=rtnheader_db.sum_exits(con, returnnote.getRTNOTE_GPNO(), returnnote.getRTNOTE_ITEMCODE());
//    	}
//    	catch (Exception e) {
//         l_ers="dcfgvhjk "+e.getMessage();    		
//		   e.printStackTrace();
//		}
//		return l_ers; 
//    }
    public String sumqty(Connection con,RETURN_NOTE rtn,GP_DETAILS gpdetails)
    {
    	String l_ers="";
    	try
    	{
    		l_ers=l_ers+RTNHEADER_DB.sumqty(con, rtn.getRTNOTE_GPNO(), rtn.getRTNOTE_ITEMCODE(), rtn);
			l_ers=l_ers+RTNHEADER_DB.issueqty(con, rtn.getRTNOTE_GPNO(), rtn.getRTNOTE_ITEMCODE(), gpdetails);
    		if(rtn.getRTNOTE_QTYRETURNED() > gpdetails.getISSUEQTY())
    		{
    			l_ers="Qty returned should not be greater than issue qty";
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
    }
//    public String insert_update_stock_summary(Connection con,STOCK_SUMMARY STOCKSUMMARY,RETURN_NOTE RETURNNOTE)
//    {
//    	String l_ers="";
//    	try
//    	{
//    		if(STOCKSUMMARY.getSS_SR_NO()!=0)
//    		{
//    			l_ers=rtnheader_db.get_Stock_Summary_details(con, STOCKSUMMARY, RETURNNOTE);
//    			l_ers=l_ers+rtnheader_db.update_rtn_returnqty(con, RETURNNOTE);
//    		}
//    		
//    		else
//    		{
//    			STOCKSUMMARY.setCREATED_BY(RETURNNOTE.getRTNOTE_CREATEDBY());
//    			STOCKSUMMARY.setCREATED_DATE(date.date_Conv(RETURNNOTE.getRTNOTE_CREATEDDATE(), "dd/MM/yyyy", "yyyyMMdd"));
//    			l_ers=l_ers+rtnheader_db.insert_STOCK_SUMMARY(con, STOCKSUMMARY);
//    		}
//    	}
//    	catch (Exception e) {
//			e.printStackTrace();
//		}
//		return l_ers;
//    	
//    }
}