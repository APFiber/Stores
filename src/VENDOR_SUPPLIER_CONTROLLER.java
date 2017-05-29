import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class VENDOR_SUPPLIER_CONTROLLER extends HttpServlet {
	Connection con ;
	org.Global_Data Global_Data=new org.Global_Data();
	org.Initclass initclass=new org.Initclass();
	org.KrrCommon krrcommon=new org.KrrCommon();

	/**
	 * Constructor of the object.
	 */
	public VENDOR_SUPPLIER_CONTROLLER() {
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
		initclass.initurl(getServletContext().getRealPath("/properties/database.properties"), Global_Data);
	}
	public void service (javax.servlet.http.HttpServletRequest req ,
			javax.servlet.http.HttpServletResponse resp )
	{
		org.ActionErrors errors=new org.ActionErrors();
		int option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
		con = initclass.getConnection(Global_Data, errors);
		bean.VENDOR_CommonData VENDOR_CommonData = new bean.VENDOR_CommonData();
		String l_ers="";

		List<bean.VENDOR_SUPPLIER> VENDOR_DETAILSList=new ArrayList<bean.VENDOR_SUPPLIER>();
		try{
			List<bean.VENDOR_SUPPLIER> TypeList=new ArrayList<bean.VENDOR_SUPPLIER>();
			List<bean.VENDOR_SUPPLIER> CityList=new ArrayList<bean.VENDOR_SUPPLIER>();
			List<bean.VENDOR_SUPPLIER> DistrictList=new ArrayList<bean.VENDOR_SUPPLIER>();
			List<bean.VENDOR_SUPPLIER> StateList=new ArrayList<bean.VENDOR_SUPPLIER>();
			bean.VENDOR_SUPPLIER VENDOR_SUPPLIER= new bean.VENDOR_SUPPLIER();
			
			org.VendorBusinessLayer VendorBusinessLayer=new org.VendorBusinessLayer();
			l_ers=VendorBusinessLayer.Getreq_VENDOR_CommonData(req, VENDOR_CommonData);
			
			if(VENDOR_CommonData.getPAGENO()==0) VENDOR_CommonData.setPAGENO(1);
			String VSSRNO= req.getParameter("VSCODE");
			VENDOR_SUPPLIER.setVSSRNO(krrcommon.ConvertNum(VSSRNO));
			VENDOR_CommonData.setSelectedSerialNo(VENDOR_SUPPLIER.getVSSRNO());

			System.out.println("input option "+option);
			con = initclass.getConnection(Global_Data, errors);
			if(krrcommon.isValuenull(VENDOR_CommonData.getUSERROLE())|| krrcommon.isValuenull(VENDOR_CommonData.getUSERNAME())|| krrcommon.isValuenull(VENDOR_CommonData.getLOGINDATE()))
			{
				l_ers="Vendor/Supplier Details is accessed after login only";
				errors.addError(new org.ActionError("Error","",l_ers));
			}
			if(errors.getErrors().size()==0){
				switch(option) 
				{
				case 1:
					//Grid display
				{
					System.out.println("case 1");
					l_ers=VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
					if(krrcommon.isValuenull(l_ers))
					{
						
						VENDOR_CommonData.setPAGENO(1);
						VendorBusinessLayer.getTypeList(con, TypeList);
						VendorBusinessLayer.getCityList(con, CityList);
						VendorBusinessLayer.getDistrictList(con, DistrictList);
						VendorBusinessLayer.getStateList(con, StateList);

						req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
						req.setAttribute("type",TypeList);
						req.setAttribute("city",CityList);
						req.setAttribute("district",DistrictList);
						req.setAttribute("state",StateList);

						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req , resp ) ;
					}
					else
					{

						req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
						req.setAttribute("type",TypeList);
						req.setAttribute("city",CityList);
						req.setAttribute("district",DistrictList);
						req.setAttribute("state",StateList);
						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 2:	
					//Add vendor page display
				{
					System.out.println("case 2");
					System.out.println("In case 2 "+req.getParameter("LOGINID"));
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("VENDOR_SUPPLIER", VENDOR_SUPPLIER);
						req.setAttribute("message", "");
						req.setAttribute("commonData", VENDOR_CommonData);
						getServletContext().getRequestDispatcher("/vendorDetails.jsp").forward(req, resp);
					}
					else
					{
						req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 3:
					//save vendor/supplier record
				{
					System.out.println("case 3");
					System.out.println("In case 3 "+req.getParameter("LOGINID"));
					l_ers=VendorBusinessLayer.Getreq_VENDOR_Data(req, VENDOR_SUPPLIER);
					l_ers=VendorBusinessLayer.insert_update_VENDOR_SUPPLIER_details(con, VENDOR_SUPPLIER,VENDOR_CommonData);
					System.out.println(l_ers);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
						if(krrcommon.isValuenull(l_ers))
						{
							VendorBusinessLayer.getTypeList(con, TypeList);
							VendorBusinessLayer.getCityList(con, CityList);
							VendorBusinessLayer.getDistrictList(con, DistrictList);
							VendorBusinessLayer.getStateList(con, StateList);

							req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
							req.setAttribute("type",TypeList);
							req.setAttribute("city",CityList);
							req.setAttribute("district",DistrictList);
							req.setAttribute("state",StateList);
							req.setAttribute("commonData", VENDOR_CommonData);
							req.setAttribute("message", "Record saved successfully");
							getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req,resp) ;	
						}
					}
					else
					{
						System.out.println("in else"+l_ers);
						req.setAttribute("vendor",VENDOR_SUPPLIER);
						req.setAttribute("message", l_ers);
						req.setAttribute("commonData", VENDOR_CommonData);
						getServletContext().getRequestDispatcher("/vendorDetails.jsp").forward(req, resp);
					}
				}
				break;
				case 4:
					//Edit vendor/supplier page display
				{	
					System.out.println("    case 4  ");
					l_ers=VendorBusinessLayer.edit_VENDOR_SUPPLIER_details(con,VENDOR_SUPPLIER);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("VENDOR_SUPPLIER", VENDOR_SUPPLIER);
						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message", "");
						getServletContext().getRequestDispatcher("/vendorDetails.jsp").forward( req , resp ) ;
					}
					else
					{
						l_ers=l_ers+VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
						req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
						VendorBusinessLayer.getTypeList(con, TypeList);
						VendorBusinessLayer.getCityList(con, CityList);
						VendorBusinessLayer.getDistrictList(con, DistrictList);
						VendorBusinessLayer.getStateList(con, StateList);

						req.setAttribute("type",TypeList);
						req.setAttribute("city",CityList);
						req.setAttribute("district",DistrictList);
						req.setAttribute("state",StateList);
						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req , resp ) ;
					}
				}
				break; 
				case 5:
					//authorize vendor/supplier status
				{
					System.out.println("case 5");	
					
					l_ers=VendorBusinessLayer.authorize_VENDOR_SUPPLIER_details(con, VENDOR_SUPPLIER.getVSSRNO());
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
							VendorBusinessLayer.getTypeList(con, TypeList);
							VendorBusinessLayer.getCityList(con, CityList);
							VendorBusinessLayer.getDistrictList(con, DistrictList);
							VendorBusinessLayer.getStateList(con, StateList);

							req.setAttribute("type",TypeList);
							req.setAttribute("city",CityList);
							req.setAttribute("district",DistrictList);
							req.setAttribute("state",StateList);
							req.setAttribute("commonData", VENDOR_CommonData);
							req.setAttribute("message", "Record authorized successfully");
							getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req,resp) ;	
						}
					}
					else
					{
						l_ers=l_ers+VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
						req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
						req.setAttribute("type",TypeList);
						req.setAttribute("city",CityList);
						req.setAttribute("district",DistrictList);
						req.setAttribute("state",StateList);
						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message",l_ers);
						getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward(req, resp);
					}
				}
				break;
				case 6:
					//delete vendor/supplier
				{
					System.out.println("case 6");
					l_ers=VendorBusinessLayer.delete_VENDOR_SUPPLIER_details(con, VENDOR_SUPPLIER.getVSSRNO());
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
							VendorBusinessLayer.getTypeList(con, TypeList);
							VendorBusinessLayer.getCityList(con, CityList);
							VendorBusinessLayer.getDistrictList(con, DistrictList);
							VendorBusinessLayer.getStateList(con, StateList);

							req.setAttribute("type",TypeList);
							req.setAttribute("city",CityList);
							req.setAttribute("district",DistrictList);
							req.setAttribute("state",StateList);
							req.setAttribute("commonData", VENDOR_CommonData);
							req.setAttribute("message", "Record deleted successfully");
							getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req,resp) ;	
						}
					}
					else
					{
						l_ers=l_ers+VendorBusinessLayer.Search_VENDOR_SUPPLIER_details(con, VENDOR_DETAILSList, VENDOR_CommonData);
						req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
						req.setAttribute("type",TypeList);
						req.setAttribute("city",CityList);
						req.setAttribute("district",DistrictList);
						req.setAttribute("state",StateList);
						req.setAttribute("commonData", VENDOR_CommonData);
						req.setAttribute("message",l_ers);
						getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward(req, resp);
					}
				}
				break;

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
			e.printStackTrace();

			try{
				l_ers="error in vendor/Supplier controller";
				req.setAttribute("vendorBeanList",VENDOR_DETAILSList);
				req.setAttribute("commonData", VENDOR_CommonData);
				req.setAttribute("message", l_ers);
				getServletContext().getRequestDispatcher("/vendorGrid.jsp").forward( req , resp ) ;
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
