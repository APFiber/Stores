import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import bean.STOCK_SUMMARY;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ITEM_CONTROLLER extends HttpServlet {
	org.KrrCommon krrcommon = new org.KrrCommon();
	org.Date_library date_library = new org.Date_library();
	org.Initclass initclass=new org.Initclass();
	org.Global_Data Global_Data=new org.Global_Data();
	Connection con ;

	/**
	 * Constructor of the object.
	 */
	public ITEM_CONTROLLER() {
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
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
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
			javax.servlet.http.HttpServletResponse resp )
	{
		org.ActionErrors errors=new org.ActionErrors();
		org.ITEM_SERVICE ITEMSERVICE = new org.ITEM_SERVICE();
		bean.ITEM_CommonData commonData = new bean.ITEM_CommonData();
		String l_ers="";
		try
		{
			int Option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
			List<bean.ITEM_DETAILS> ITEMDETAILSLIST=new ArrayList<bean.ITEM_DETAILS>();			
			bean.ITEM_DETAILS ITEMDETAILS=new bean.ITEM_DETAILS();
			bean.STOCK_SUMMARY STOCKSUMMARY=new STOCK_SUMMARY();
			List<bean.MAJOR_MINOR_CODE> majorGroupList=new ArrayList<bean.MAJOR_MINOR_CODE>();
			List<bean.MAJOR_MINOR_CODE> majorMakeList=new ArrayList<bean.MAJOR_MINOR_CODE>();
			List<bean.MAJOR_MINOR_CODE> majorUnitsList=new ArrayList<bean.MAJOR_MINOR_CODE>();
//			List<bean.MAJOR_MINOR_CODE> majorSaleUnitsList=new ArrayList<bean.MAJOR_MINOR_CODE>();
//			List<bean.MAJOR_MINOR_CODE> majorPackageUnitsList=new ArrayList<bean.MAJOR_MINOR_CODE>();

			commonData.setUSERNAME(req.getParameter("USERNAME"));
			commonData.setLOGINDATE(req.getParameter("DATE"));
			commonData.setUSERROLE(req.getParameter("USERROLE"));
			commonData.setLOGINID(req.getParameter("LOGINID"));
			commonData.setITEMCODE(krrcommon.ConvertNum(req.getParameter("ITEMCODE")));
			commonData.setITEMGROUP(krrcommon.CheckEmptyReturn(req.getParameter("ITEMGROUP")));
			commonData.setITEMMAKE(krrcommon.CheckEmptyReturn(req.getParameter("ITEMMAKE")));
			commonData.setITEMSTATUS(krrcommon.CheckEmptyReturn(req.getParameter("ITEMSTATUS")));
			commonData.setITEMDESCRIPTION(krrcommon.CheckEmptyReturn(req.getParameter("ITEMDESCRIPTION")));

			commonData.setPage_No((int)krrcommon.ConvertNum(req.getParameter("pageNo")));
			if(commonData.getPage_No()==0) commonData.setPage_No(1);
			String ITEMCODE= req.getParameter("ITEMSRNO");
			ITEMDETAILS.setITEMCODE(krrcommon.ConvertNum(ITEMCODE));
//			commonData.setSelected_Serial_No(ITEMDETAILS.getITEMCODE());

			con = initclass.getConnection(Global_Data, errors);
			if(krrcommon.isValuenull(commonData.getUSERROLE())|| krrcommon.isValuenull(commonData.getUSERNAME())|| krrcommon.isValuenull(commonData.getLOGINDATE()))
			{
				l_ers="Item Details is accessed after login only";
				errors.addError(new org.ActionError("Error","",l_ers));
			}
			if(errors.getErrors().size()==0)
			{
				switch(Option) 
				{
				case 1://Grid display
				{
					System.out.println("case 1");
					l_ers=ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST,commonData);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
					else
					{
						commonData.setTotal_NoOf_Records(0);
						commonData.setTotal_NoOf_Pages(0);
						req.setAttribute("itemBeanList",new ArrayList<bean.ITEM_DETAILS>(Collections.nCopies(10, new bean.ITEM_DETAILS())));
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 2:	//Add item page display
				{
					System.out.println("case 2");
					l_ers=ITEMSERVICE.get_code_list(con, majorGroupList, 1);
					l_ers=l_ers+ITEMSERVICE.get_code_list(con, majorMakeList, 2);
					l_ers=l_ers+ITEMSERVICE.get_code_list(con, majorUnitsList, 3);
					if(krrcommon.isValuenull(l_ers))
					{					
						req.setAttribute("item", ITEMDETAILS);
						req.setAttribute("message", "");
						req.setAttribute("ITEMGROUP", majorGroupList);
						req.setAttribute("ITEMMAKE",majorMakeList);
						req.setAttribute("ITEMUNITS",majorUnitsList);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/addItem.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST,commonData);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message", "");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 3://save item record
				{
					System.out.println("case 3");
					ITEMDETAILS.setITEMCODE(krrcommon.ConvertNum(req.getParameter("ITEMCODESRNO")));
					ITEMDETAILS.setITEMGROUP(req.getParameter("ADD_ITEMGROUP"));
					ITEMDETAILS.setITEMMAKE(req.getParameter("ADD_ITEMMAKE"));
					ITEMDETAILS.setITEMMODEL(req.getParameter("ITEMMODEL"));
					ITEMDETAILS.setITEMDESCRIPTION(req.getParameter("ADD_ITEMDESCRIPTION"));
					ITEMDETAILS.setITEMUNITS(req.getParameter("ITEMUNITS"));
					ITEMDETAILS.setITEMSPECIALITEM(req.getParameter("ITEMSPECIALITEM"));
					ITEMDETAILS.setITEMIMPORTEDITEM(req.getParameter("ITEMIMPORTEDITEM"));
					ITEMDETAILS.setOPENINGBALANCE(krrcommon.ConvertDouble(req.getParameter("OPENINGBALANCE")));
					l_ers=ITEMSERVICE.insert_update_item_details(con, ITEMDETAILS, commonData);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST, commonData);
						if(krrcommon.isValuenull(l_ers))
						{	
							req.setAttribute("message", "Record saved successfully");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
					else
					{
						l_ers=l_ers+ITEMSERVICE.get_code_list(con, majorGroupList, 1);
						l_ers=l_ers+ITEMSERVICE.get_code_list(con, majorMakeList, 2);
						l_ers=l_ers+ITEMSERVICE.get_code_list(con, majorUnitsList, 3);
						req.setAttribute("item",ITEMDETAILS);
						req.setAttribute("ITEMGROUP",majorGroupList);
						req.setAttribute("ITEMMAKE",majorMakeList);
						req.setAttribute("ITEMUNITS",majorUnitsList);
						req.setAttribute("message", l_ers);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/addItem.jsp").forward(req, resp);
					}
				}
				break;
				case 4://Edit item page display
				{
					System.out.println("case 4");
					l_ers=ITEMSERVICE.read_item_details(con, ITEMDETAILS);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=ITEMSERVICE.get_code_list(con, majorGroupList,1);
						l_ers=l_ers+ITEMSERVICE.get_code_list(con,majorMakeList,2);
						l_ers=l_ers+ITEMSERVICE.get_code_list(con, majorUnitsList,3);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message", "");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("ITEMGROUP", majorGroupList);
						req.setAttribute("ITEMMAKE", majorMakeList);
						req.setAttribute("ITEMUNITS", majorUnitsList);
						req.setAttribute("commonData", commonData);
						req.setAttribute("item", ITEMDETAILS);
						getServletContext().getRequestDispatcher("/addItem.jsp").forward( req , resp ) ;
					}
					else
					{
						l_ers = l_ers+ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST,commonData);
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
				}
				break; 
				case 5://authorize item
				{
					System.out.println("case 5");	
					l_ers=ITEMSERVICE.authorize_item_details(con,ITEMDETAILS.getITEMCODE());
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST, commonData);
						if(krrcommon.isValuenull(l_ers))
						{	
							req.setAttribute("message", "Record authorized successfully");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
					else
					{
						l_ers = l_ers+ ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST,commonData);
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 6://delete item
				{
					System.out.println("case 6");
					l_ers=ITEMSERVICE.delete_item_details(con,ITEMDETAILS.getITEMCODE());
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST, commonData);
						if(krrcommon.isValuenull(l_ers))
						{	
							req.setAttribute("message", "Record deleted successfully");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;	
					}
					else
					{
						l_ers = l_ers+ITEMSERVICE.get_item_details(con, ITEMDETAILSLIST,commonData);
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 7://get active item details
				{
					l_ers=ITEMSERVICE.get_poitem_details(con, ITEMDETAILSLIST,commonData);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("itemBeanList",ITEMDETAILSLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/itemWindow.jsp").forward( req , resp ) ;
					}
					else
					{
						commonData.setTotal_NoOf_Records(0);
						commonData.setTotal_NoOf_Pages(0);
						req.setAttribute("itemBeanList",new ArrayList<bean.ITEM_DETAILS>(Collections.nCopies(10, new bean.ITEM_DETAILS())));
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/itemWindow.jsp").forward( req , resp ) ;
					}
				}
				break;
				default:
					break;
				}
			}//if
			if(errors.getErrors().size()>0)
			{
				req.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/Errors.jsp").forward(req, resp);
			}
		}
		catch ( java.lang.Exception e )
		{
			try
			{
				l_ers="Error in item controller "+e.getMessage();
				commonData.setTotal_NoOf_Records(0);
				commonData.setTotal_NoOf_Pages(0);
				req.setAttribute("itemBeanList",new ArrayList<bean.ITEM_DETAILS>(Collections.nCopies(10, new bean.ITEM_DETAILS())));
				req.setAttribute("commonData", commonData);
				req.setAttribute("message", l_ers);
				getServletContext().getRequestDispatcher("/itemGrid.jsp").forward( req , resp ) ;
			}
			catch ( java.lang.Exception e1 )
			{
				e.printStackTrace();
			}
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
		}
	}
}
