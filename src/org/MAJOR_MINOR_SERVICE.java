package org;

import java.sql.Connection;
import java.util.List;
import org.Date_library;
import org.KrrCommon;
import bean.MAJOR_CODE;
import bean.MAJOR_MINOR_CODE;
import bean.MAJOR_MINOR_CommonData;

public class MAJOR_MINOR_SERVICE {

	KrrCommon krrCommon = new KrrCommon();
	Date_library Date_library=new Date_library();
	MAJOR_MINOR_DB MAJOR_MINOR_DB=new MAJOR_MINOR_DB();
	public String search_major_minor_code(Connection con,List<MAJOR_MINOR_CODE> MAJORMINORLIST, MAJOR_MINOR_CommonData majorMinorCommonData)
	{
		String l_ers="";
		try
		{
			l_ers=MAJOR_MINOR_DB.search_major_minor_code(con, MAJORMINORLIST,majorMinorCommonData);
			majorMinorCommonData.setTotal_NoOf_Records(MAJORMINORLIST.size());
			int excessValues = MAJORMINORLIST.size()%10;
			if(excessValues!=0 || MAJORMINORLIST.size()==0)
			{
				while(excessValues <= 10)
				{
					MAJORMINORLIST.add(new MAJOR_MINOR_CODE());
					excessValues++;
				}
			}
			majorMinorCommonData.setTotal_NoOf_Pages(MAJORMINORLIST.size()/10);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			l_ers = "error in Item service "+e.getMessage();
		}
		return l_ers;
	}

	public String insert_major_code(Connection con,MAJOR_CODE MAJORCODE, MAJOR_MINOR_CommonData major_minor_CommonData)
	{
		String l_ers="";
		try
		{
			if(MAJOR_MINOR_DB.is_code_exits(con,MAJORCODE.getMAJORCODE()))
			{
				l_ers="Major code already exists";	
			}
			else
			{
				MAJORCODE.setCREATEDBY(major_minor_CommonData.getLOGINID());
//				MAJORCODE.setCREATEDDATE(Date_library.date_Conv(major_minor_CommonData.getLOGINDATE(), "dd/MM/yyyy", "yyyyMMdd"));
				MAJORCODE.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
				MAJORCODE.setSTATUS("INACTIVE");
				l_ers=MAJOR_MINOR_DB.insert_major_code(con, MAJORCODE);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_major_code(Connection con, List<MAJOR_MINOR_CODE> MAJORMINORLIST)
	{
		String l_ers="";
		try
		{
			l_ers=MAJOR_MINOR_DB.get_major_code(con, MAJORMINORLIST);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String insert_major_minor_code(Connection con, MAJOR_MINOR_CODE MAJORMINORCODE,MAJOR_MINOR_CommonData major_minor_CommonData)
	{
		String l_ers="";
		try
		{
			if(MAJOR_MINOR_DB.is_minorCode_exits(con, MAJORMINORCODE.getMAJORCODE(), MAJORMINORCODE.getMINORCODE()))
			{
				l_ers="Minor code already exists";	
			}
			else
			{
				MAJORMINORCODE.setCREATEDBY(major_minor_CommonData.getLOGINID());
//				MAJORMINORCODE.setCREATEDDATE(Date_library.date_Conv(major_minor_CommonData.getLOGINDATE(), "dd/MM/yyyy", "yyyyMMdd"));
				MAJORMINORCODE.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
				MAJORMINORCODE.setSTATUS("ACTIVE");
				l_ers=MAJOR_MINOR_DB.insert_major_minor_code(con, MAJORMINORCODE);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String activate_deactivate_mm_code(Connection con,long MMSrNo)
	{
		String l_ers="";
		String status=MAJOR_MINOR_DB.get_status(con, MMSrNo);
		try
		{
			if(status.equalsIgnoreCase("ACTIVE"))
			{
				l_ers=MAJOR_MINOR_DB.change_Major_Minor_Status(con,MMSrNo, "INACTIVE" );
			}
			else
			{
				l_ers=MAJOR_MINOR_DB.change_Major_Minor_Status(con,MMSrNo,"ACTIVE" );
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_MM_Status(Connection con,long MMSRNO)
	{
		String l_ers="";
		try
		{
			l_ers=MAJOR_MINOR_DB.get_status(con, MMSRNO);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}
}
