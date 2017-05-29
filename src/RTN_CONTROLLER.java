   
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.Date_library;
import org.Global_Data;
import org.KrrCommon;
import org.RTNHEADER_SERVICE;
import bean.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class RTN_CONTROLLER extends HttpServlet {

	Connection con ;
	Global_Data Global_Data=new Global_Data();
	org.Initclass initclass=new org.Initclass();
	KrrCommon krrcommon=new KrrCommon();
	Date_library date_library =new Date_library(); 
	/**
	 * Constructor of the object.
	 */
	public RTN_CONTROLLER() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Returns inform ation about the servlet, such as 
	 * author, version, and copyright. 
	 *t 
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		initclass.initurl(getServletContext().getRealPath("/properties/database.properties"), Global_Data);
	}
	

	public void service (javax.servlet.http.HttpServletRequest req ,
			javax.servlet.http.HttpServletResponse resp)
	{
		org.ActionErrors errors=new org.ActionErrors();
		RTN_COMMONDATA common_data = new RTN_COMMONDATA();
		org.RTNHEADER_SERVICE RTNHEADER_SERVICE = new RTNHEADER_SERVICE();
		String l_ers="";
		try{
			int Option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
			RETURN_NOTE RETURN_NOTE = new RETURN_NOTE();
			STOCK_SUMMARY STOCKSUMMARY = new STOCK_SUMMARY();
			List<bean.RETURN_NOTE> rtnBeanList = new ArrayList<RETURN_NOTE>();
			bean.GP_HEADER GPHEADER = new GP_HEADER();
			bean.GP_DETAILS GPDETAILS = new GP_DETAILS();
			common_data.setUSERNAME(req.getParameter("USERNAME"));
			common_data.setLOGINDATE(req.getParameter("DATE"));
			common_data.setUSERROLE(req.getParameter("USERROLE"));
			common_data.setLOGINID(req.getParameter("LOGINID"));
		    common_data.setITEMCODE(krrcommon.ConvertNum(req.getParameter("ADDITEMCODE")));
			common_data.setGPNO(krrcommon.ConvertNum(req.getParameter("ADDGPNO")));
			common_data.setRETURNDATE(krrcommon.CheckEmptyReturn(req.getParameter("ADDRETURNDATE")));
			common_data.setPURPOSEOFRETURN(krrcommon.CheckEmptyReturn(req.getParameter("ADDPURPOSEOFRETURN")));
		    RETURN_NOTE.setRTNOTE_NO(krrcommon.ConvertNum(req.getParameter("RTNOTE_NO")));
			common_data.setPageNo((int)krrcommon.ConvertNum(req.getParameter("pageNo")));
			if(common_data.getPageNo()==0) common_data.setPageNo(1);
			System.out.println("name"+req.getParameter("USERNAME")+"date"+req.getParameter("DATE")+"role"+req.getParameter("USERROLE")+" pageNo "+req.getParameter("pageNo"));
			con = initclass.getConnection(Global_Data, errors);
			if(krrcommon.isValuenull(common_data.getUSERROLE())|| krrcommon.isValuenull(common_data.getUSERNAME())|| krrcommon.isValuenull(common_data.getLOGINDATE()))
			{
				l_ers="RTN Details is accessed after login only";
				errors.addError(new org.ActionError("Error","",l_ers));
			}
		
			if(errors.getErrors().size()==0){
				switch(Option) 
				{
				//Grid Display
				case 1:
				{
					System.out.println(" case 1");
					common_data.setPageNo(1);
					
				   l_ers=l_ers+RTNHEADER_SERVICE.search_rtn_details(con, rtnBeanList, common_data);
				    if(krrcommon.isValuenull(l_ers))
				    {
				    	req.setAttribute("rtnBeanList", rtnBeanList);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
				    }
				    else
				    {
				    	common_data.setTotalNoOfPages(0);
				    	common_data.setTotalNoOfRecords(0);  
				    	req.setAttribute("rtnBeanList",new ArrayList<bean.RETURN_NOTE>(Collections.nCopies(10, new bean.RETURN_NOTE())));
		 		    	req.setAttribute("commonData", common_data);
				    	req.setAttribute("message", req.getAttribute("message"));
				    	getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;

				    }
				}
				break;
				case 2:
				{
					System.out.println("case 2");
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("rtn", RETURN_NOTE);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", "");
						getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
					}
					else
					{
					common_data.setTotalNoOfPages(0);
					common_data.setTotalNoOfRecords(0);
					req.setAttribute("rtnBeanList", new ArrayList<bean.RETURN_NOTE>(Collections.nCopies(10, new bean.RETURN_NOTE())));
					req.setAttribute("commonData", common_data);
					req.setAttribute("message", l_ers);
					getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 3:
				{
					System.out.println("in case 3");
					RETURN_NOTE.setRTNOTE_NO(krrcommon.ConvertNum(req.getParameter("RTNOTE_NO")));
					RETURN_NOTE.setRTNOTE_GPNO(krrcommon.ConvertNum(req.getParameter("RTNOTE_GPNO")));
					RETURN_NOTE.setRTNOTE_ITEMCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_ITEMCODE")));
					RETURN_NOTE.setRTNOTE_GPDATE((req.getParameter("RTNOTE_GPDATE")));
					RETURN_NOTE.setRTNOTE_VENDORCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_VENDORCODE")));
					RETURN_NOTE.setRTNOTE_VENDORNAME(req.getParameter("RTNOTE_VENDORNAME"));
					RETURN_NOTE.setRTNOTE_ITEMDESCRIPTION((req.getParameter("RTNOTE_ITEMDESCRIPTION")));
					RETURN_NOTE.setRTNOTE_ITEMMAKE(req.getParameter("RTNOTE_ITEMMAKE"));
					RETURN_NOTE.setRTNOTE_DATE((req.getParameter("RTNOTE_DATE")));
					RETURN_NOTE.setRTNOTE_QTYRETURNED(krrcommon.ConvertNum(req.getParameter("RTNOTE_QTYRETURNED")));
					RETURN_NOTE.setRTNOTE_PURPOSEOFRETURN((req.getParameter("RTNOTE_PURPOSEOFRETURN")));
					GPHEADER.setGPNO(krrcommon.ConvertNum(req.getParameter("RTNOTE_GPNO")));
					l_ers=RTNHEADER_SERVICE.getGp_header(con,GPHEADER);
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE1(GPHEADER.getVENDOR_ADDRESSLINE1());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE2(GPHEADER.getVENDOR_ADDRESSLINE2());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE3(GPHEADER.getVENDOR_ADDRESSLINE3());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE4(GPHEADER.getVENDOR_ADDRESSLINE3());
					RETURN_NOTE.setRTNOTE_CITY(GPHEADER.getVENDOR_CITY());
					RETURN_NOTE.setRTNOTE_DISTRICT(GPHEADER.getVENDOR_DISTRICT());
					RETURN_NOTE.setRTNOTE_STATE(GPHEADER.getVENDOR_STATE());
					RETURN_NOTE.setRTNOTE_COUNTRY(GPHEADER.getVENDOR_COUNTRY());
					RETURN_NOTE.setRTNOTE_PIN(GPHEADER.getVENDOR_PIN());
					RETURN_NOTE.setRTNOTE_VENDORPHONENO(GPHEADER.getVENDOR_PHONENO());
					RETURN_NOTE.setRTNOTE_PROJECT(GPHEADER.getISSUEDPROJECT());
					RETURN_NOTE.setRTNOTE_LOCATION(GPHEADER.getISSUEDLOCATION());
					
					l_ers=l_ers+RTNHEADER_SERVICE.insert_update_rtn_details(con, RETURN_NOTE, common_data);
				   
					
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+RTNHEADER_SERVICE.search_rtn_details(con, rtnBeanList, common_data);
						l_ers=l_ers+RTNHEADER_SERVICE.sumqty(con, RETURN_NOTE, GPDETAILS);
						if(krrcommon.isValuenull(l_ers))
						{
                        req.setAttribute("rtn", RETURN_NOTE);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", "Record save successfully");
						getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
					   }
					}
					else
					{
						    req.setAttribute("rtn", RETURN_NOTE);
							req.setAttribute("commonData", common_data);
							req.setAttribute("message", l_ers);
							getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
					}
				   }
				break;
				case 4:// Remove
				{
				    System.out.println("in case 4");
				    l_ers=RTNHEADER_SERVICE.remove_rtn(con, RETURN_NOTE,common_data);
				    l_ers=l_ers+RTNHEADER_SERVICE.search_rtn_details(con, rtnBeanList, common_data);
				    if(krrcommon.isValuenull(l_ers))
				      {
				    	req.setAttribute("rtnBeanList", rtnBeanList);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", "Remove record successfully");
						getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
						}
						else
						{
							req.setAttribute("rtnBeanList", rtnBeanList);
							req.setAttribute("commonData", common_data);
							req.setAttribute("message", l_ers);
							getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
							}
					   }
					break;
				case 5:// Approved
				{
					System.out.println("in case 5");
					
					l_ers=l_ers+RTNHEADER_SERVICE.approved_rtn_details(con, RETURN_NOTE,common_data,STOCKSUMMARY);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+RTNHEADER_SERVICE.search_rtn_details(con, rtnBeanList, common_data);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("rtnBeanList", rtnBeanList);
							req.setAttribute("commonData", common_data);
							req.setAttribute("message", "Approved record successfully");
							getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
						}
					}
					else
					{
						req.setAttribute("rtnBeanList", rtnBeanList);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message",l_ers);
						getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 6:// edit
				{
					System.out.println("in case 6");
					l_ers=RTNHEADER_SERVICE.edit_rtn(con, RETURN_NOTE);
					RETURN_NOTE.setRTNOTE_DATE(date_library.date_Conv(RETURN_NOTE.getRTNOTE_DATE(), "yyyyMMdd", "dd-MM-yyyy"));
					l_ers=l_ers+RTNHEADER_SERVICE.search_rtn_details(con, rtnBeanList, common_data);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("commonData", common_data);
						req.setAttribute("rtn", RETURN_NOTE);
						req.setAttribute("message", "");
					    getServletContext().getRequestDispatcher("/addRTN.jsp").forward(req, resp);
					}
					else
					{
						req.setAttribute("commonData", common_data);
						req.setAttribute("rtnBeanList", rtnBeanList);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward(req, resp);
					}
				}
				break;
				
				case 7:
				{
					System.out.println("in case 7");
					GPHEADER.setGPNO(krrcommon.ConvertNum(req.getParameter("RTNOTE_GPNO")));
					GPDETAILS.setGPNO(krrcommon.ConvertNum(req.getParameter("RTNOTE_GPNO")));
					GPDETAILS.setITEMCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_ITEMCODE")));

					if(krrcommon.isValue(req.getParameter("RTNOTE_GPNO")))
					{
					l_ers=l_ers+RTNHEADER_SERVICE.getGp_header(con,GPHEADER);
					}
					if(krrcommon.ConvertNum(req.getParameter("RTNOTE_ITEMCODE"))!=0)
					{
						l_ers=l_ers+RTNHEADER_SERVICE.getGp_details(con, GPDETAILS);
					}
					RETURN_NOTE.setRTNOTE_GPDATE(req.getParameter("RTNOTE_GPDATE"));
					RETURN_NOTE.setRTNOTE_VENDORNAME(req.getParameter("RTNOTE_VENDORNAME"));
					RETURN_NOTE.setRTNOTE_VENDORCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_VENDORCODE")));
					RETURN_NOTE.setRTNOTE_ITEMCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_ITEMCODE")));
					RETURN_NOTE.setRTNOTE_ITEMDESCRIPTION(req.getParameter("RTNOTE_ITEMDESCRIPTION"));
					RETURN_NOTE.setRTNOTE_DATE(req.getParameter("RTNOTE_DATE"));
					RETURN_NOTE.setRTNOTE_QTYRETURNED(krrcommon.ConvertNum(req.getParameter("RTNOTE_QTYRETURNED")));
					RETURN_NOTE.setRTNOTE_PURPOSEOFRETURN(req.getParameter("RTNOTE_PURPOSEOFRETURN"));
					if(krrcommon.isValuenull(l_ers))
					{
					RETURN_NOTE.setRTNOTE_ITEMCODE(GPDETAILS.getITEMCODE());
					RETURN_NOTE.setRTNOTE_ITEMDESCRIPTION(GPDETAILS.getITEMDESCRIPTION());
					RETURN_NOTE.setRTNOTE_ITEMMAKE(GPDETAILS.getITEMMAKE());
					req.setAttribute("rtn", RETURN_NOTE);
					req.setAttribute("commonData", common_data);
					req.setAttribute("message", "");
					RETURN_NOTE.setRTNOTE_GPNO(GPHEADER.getGPNO());
					RETURN_NOTE.setRTNOTE_GPDATE(GPHEADER.getGPDATE());
					RETURN_NOTE.setRTNOTE_VENDORCODE(GPHEADER.getVENDOR_CODE());
					RETURN_NOTE.setRTNOTE_VENDORNAME(GPHEADER.getVENDOR_NAME());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE1(GPHEADER.getVENDOR_ADDRESSLINE1());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE2(GPHEADER.getVENDOR_ADDRESSLINE2());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE3(GPHEADER.getVENDOR_ADDRESSLINE3());
					RETURN_NOTE.setRTNOTE_VENDORADDRESSLINE4(GPHEADER.getVENDOR_ADDRESSLINE4());
					RETURN_NOTE.setRTNOTE_CITY(GPHEADER.getVENDOR_CITY());
					RETURN_NOTE.setRTNOTE_DISTRICT(GPHEADER.getVENDOR_DISTRICT());
					RETURN_NOTE.setRTNOTE_STATE(GPHEADER.getVENDOR_STATE());
					RETURN_NOTE.setRTNOTE_COUNTRY(GPHEADER.getVENDOR_COUNTRY());
					RETURN_NOTE.setRTNOTE_PIN(GPHEADER.getVENDOR_PIN());
					RETURN_NOTE.setRTNOTE_VENDORPHONENO(GPHEADER.getVENDOR_PHONENO());
					RETURN_NOTE.setRTNOTE_PROJECT(GPHEADER.getISSUEDPROJECT());
					RETURN_NOTE.setRTNOTE_LOCATION(GPHEADER.getISSUEDLOCATION());
					getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
					}
					else
					{
						RETURN_NOTE.setRTNOTE_ITEMCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_ITEMCODE")));
						RETURN_NOTE.setRTNOTE_ITEMDESCRIPTION(req.getParameter("RTNOTE_ITEMDESCRIPTION"));
						RETURN_NOTE.setRTNOTE_ITEMMAKE(req.getParameter("RTNOTE_ITEMMAKE"));
						RETURN_NOTE.setRTNOTE_GPNO(krrcommon.ConvertNum(req.getParameter("RTNOTE_GPNO")));
						RETURN_NOTE.setRTNOTE_GPDATE(req.getParameter("RTNOTE_GPDATE"));
						RETURN_NOTE.setRTNOTE_VENDORCODE(krrcommon.ConvertNum(req.getParameter("RTNOTE_VENDORCODE")));
						RETURN_NOTE.setRTNOTE_VENDORNAME(req.getParameter("RTNOTE_VENDORNAME"));
						req.setAttribute("rtn", RETURN_NOTE);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
					}
			  } 
				break;
//				case 8:
//				{
//					System.out.println("in case 8");
//					GPDETAILS.setGP_NO(krrcommon.ConvertNum(req.getParameter("ADDGPNO")));
//					GPDETAILS.setITEM_CODE(krrcommon.ConvertNum(req.getParameter("MRNITEMCODE")));
//					l_ers=rtnheader_service.getGp_details(con, GPDETAILS);
//					return_note.setRTNOTE_GPNO(krrcommon.ConvertNum(req.getParameter("ADDGPNO")));
//					return_note.setRTNOTE_GPDATE(req.getParameter("ADDGPDATE"));
//					return_note.setRTNOTE_VENDORNAME(req.getParameter("VENDORNAME"));
//					return_note.setRTNOTE_VENDORCODE(krrcommon.ConvertNum(req.getParameter("ADDFROMVENDOR")));
//					return_note.setRTNOTE_ITEMCODE(krrcommon.ConvertNum(req.getParameter("MRNITEMCODE")));
//					return_note.setRTNOTE_DATE(req.getParameter("ADDRETURNDATE"));
//					return_note.setRTNOTE_QTYRETURNED(krrcommon.ConvertNum(req.getParameter("QTYRETURNED")));
//					return_note.setRTNOTE_PURPOSEOFRETURN(req.getParameter("PURPOSEOFRETURN"));
//					if(krrcommon.isValuenull(l_ers))
//					{
//						return_note.setRTNOTE_ITEMCODE(GPDETAILS.getITEM_CODE());
//						return_note.setRTNOTE_ITEMDESCRIPTION(GPDETAILS.getITEM_DESCRIPTION());
//						return_note.setRTNOTE_ITEMMAKE(GPDETAILS.getITEM_MAKE());
//						req.setAttribute("rtn", return_note);
//						req.setAttribute("commonData", common_data);
//						req.setAttribute("message", "");
//						getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
//					}
//					else
//					{
//						return_note.setRTNOTE_ITEMCODE(krrcommon.ConvertNum(req.getParameter("MRNITEMCODE")));
//						return_note.setRTNOTE_ITEMDESCRIPTION(req.getParameter("ITEMDESC"));
//						return_note.setRTNOTE_ITEMMAKE(req.getParameter("ITEMMAKE"));
//						req.setAttribute("rtn", return_note);
//						req.setAttribute("commonData", common_data);
//						req.setAttribute("message", l_ers);
//						getServletContext().getRequestDispatcher("/addRTN.jsp").forward( req , resp ) ;
//					}
//				}
//				break;
			default:
					break;
				}
			}//if

			if(errors.getErrors().size()>0){
				req.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/Errors.jsp").forward(req, resp);
			}
		}
		catch(java.lang.Exception e)
		{
			l_ers="Error in user controller "+e.getMessage();
			try
			{
				e.printStackTrace();
				req.setAttribute("rtnBeanList",new ArrayList<bean.RETURN_NOTE>(Collections.nCopies(10, new bean.RETURN_NOTE())));
				req.setAttribute("commonData", common_data);
				req.setAttribute("message", l_ers);
				getServletContext().getRequestDispatcher("/RTNJSP.jsp").forward( req , resp ) ;
			}
			catch(java.lang.Exception e1)
			{}
		}
		finally
		{
			try
			{
				if (!(con==null )) con.close();
			}
			catch ( java.lang.Exception  e )
			{
				e.printStackTrace();
			}
		}//finally
	}	

}