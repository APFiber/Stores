import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



public class VENDOR_PROJECT_CONTROLLER extends HttpServlet {
	Connection con ;
	org.Global_Data Global_Data=new org.Global_Data();
	org.Initclass initclass=new org.Initclass();
	org.KrrCommon krrcommon=new org.KrrCommon();

	/**
	 * Constructor of the object.
	 */
	public VENDOR_PROJECT_CONTROLLER() {
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
	public void init() throws ServletException 
	{
		initclass.initurl(getServletContext().getRealPath("/properties/database.properties"), Global_Data);
	}

	public void service (javax.servlet.http.HttpServletRequest req ,
			javax.servlet.http.HttpServletResponse resp )
	{
		org.ActionErrors errors=new org.ActionErrors();
		int option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
		con = initclass.getConnection(Global_Data, errors);
		bean.VSP_Commondata VSP_Commondata= new bean.VSP_Commondata();
		String l_ers="";
		org.VendorSupplierProjectBusiness VendorSupplierProjectBusiness = new org.VendorSupplierProjectBusiness();
		bean.VENDOR_PROJECT VENDOR_SUPPLIER_PROJECT= new bean.VENDOR_PROJECT();
		bean.VENDOR_SUPPLIER VENDOR_SUPPLIER=new bean.VENDOR_SUPPLIER();
		bean.PROJECT_MASTER PROJECT_MASTER=new bean.PROJECT_MASTER();
		List<bean.PROJECT_MASTER> vendorsupplierprojectBeanList=new ArrayList<bean.PROJECT_MASTER>();
		List<bean.VENDOR_SUPPLIER> nameListinadd = new ArrayList<bean.VENDOR_SUPPLIER>();
		List<bean.VENDOR_PROJECT> projectListingrid = new ArrayList<bean.VENDOR_PROJECT>();
		try{
			l_ers=VendorSupplierProjectBusiness.Getreq_VSP_CommonData(req, VSP_Commondata);			
			if(VSP_Commondata.getPAGENO()==0) VSP_Commondata.setPAGENO(1);	
			System.out.println("sdjfjdsbgfdsjb"+req.getParameter("VP_SRNO"));
			String VPSRNO= req.getParameter("VP_SRNO");
			VENDOR_SUPPLIER_PROJECT.setVPSRNO(krrcommon.ConvertNum(VPSRNO));
			VSP_Commondata.setSelectedSerialNo(VENDOR_SUPPLIER_PROJECT.getVPSRNO());

			System.out.println("input option "+option);
			con = initclass.getConnection(Global_Data, errors);
			if(krrcommon.isValuenull(VSP_Commondata.getUSERROLE())|| krrcommon.isValuenull(VSP_Commondata.getUSERNAME())|| krrcommon.isValuenull(VSP_Commondata.getLOGINDATE()))
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
					l_ers=VendorSupplierProjectBusiness.search_VENDOR_SUPPLIER_PROJECT(con, vendorsupplierprojectBeanList, VSP_Commondata);
					if(krrcommon.isValuenull(l_ers))
					{
						VSP_Commondata.setPAGENO(1);
						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						VendorSupplierProjectBusiness.getProjectListgrid(con, projectListingrid);
						req.setAttribute("vendorprojectBeanList",vendorsupplierprojectBeanList);
						req.setAttribute("namelist", nameListinadd);
//						req.setAttribute("project", projectListingrid);
						req.setAttribute("vsp_commondata", VSP_Commondata);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/VendorsupplierProjectGrid.jsp").forward( req , resp ) ;
					}
					else
					{
//						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						VendorSupplierProjectBusiness.getProjectListgrid(con, projectListingrid);
						req.setAttribute("vendorprojectBeanList",vendorsupplierprojectBeanList);
//						req.setAttribute("namelist", nameListinadd);
//						req.setAttribute("project", projectListingrid);
						req.setAttribute("vsp_commondata", VSP_Commondata);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/VendorsupplierProjectGrid.jsp").forward( req , resp ) ;
					}
				}
				break;

				case 2:	
					//Add vendor project page display
				{
					System.out.println("case 2");
					VSP_Commondata.setPROJECTSRNO(krrcommon.ConvertNum(req.getParameter("VP_SRNO")));
					l_ers=VendorSupplierProjectBusiness.get_VENDOR_SUPPLIER_PROJECT(con, PROJECT_MASTER, krrcommon.ConvertNum(req.getParameter("VP_SRNO")));
					if(krrcommon.isValuenull(l_ers))
					{
						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						VendorSupplierProjectBusiness.getProjectListgrid(con, projectListingrid);
						req.setAttribute("projectmaster", PROJECT_MASTER);					
						req.setAttribute("vendorsupplier", VENDOR_SUPPLIER);					
						req.setAttribute("vendorproject", VENDOR_SUPPLIER_PROJECT);
						req.setAttribute("name", nameListinadd);
						req.setAttribute("commonData", VSP_Commondata);
						req.setAttribute("message", "");
						getServletContext().getRequestDispatcher("/Addvendorsupplierproject.jsp").forward(req, resp);
					}
					else
					{
//						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						VendorSupplierProjectBusiness.getProjectListgrid(con, projectListingrid);
						req.setAttribute("vendorprojectBeanList",vendorsupplierprojectBeanList);
//						req.setAttribute("namelist", nameListinadd);
//						req.setAttribute("project", projectListingrid);
						req.setAttribute("vsp_commondata", VSP_Commondata);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/VendorsupplierProjectGrid.jsp").forward( req , resp ) ;
					}
				}
				break;

				case 3:
					//save vendor project record
				{
					System.out.println("case 3");
					System.out.println("njndjdfnbkjnXCVXCVXCVbkndnfjd"+req.getParameter("ADDNAME"));
					System.out.println("njndjdfnbkjnbkndnfjd"+req.getParameter("PROJECTSRNO"));	
					System.out.println("njndjdfnbkjnbkndnfjd"+req.getParameter("PROJECTSRNO"));	
					VENDOR_SUPPLIER_PROJECT.setVSSRNO(krrcommon.ConvertNum(req.getParameter("ADDNAME")));
					VENDOR_SUPPLIER_PROJECT.setPROJECTSRNO(krrcommon.ConvertNum(req.getParameter("PROJECTSRNO")));
					l_ers=VendorSupplierProjectBusiness.insert_VENDOR_PROJECT(con, VENDOR_SUPPLIER_PROJECT,VSP_Commondata);

					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=VendorSupplierProjectBusiness.search_VENDOR_SUPPLIER_PROJECT(con, vendorsupplierprojectBeanList, VSP_Commondata);
//						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						VendorSupplierProjectBusiness.getProjectListgrid(con, projectListingrid);
						req.setAttribute("vendorprojectBeanList",vendorsupplierprojectBeanList);
//						req.setAttribute("namelist", nameListinadd);
//						req.setAttribute("project", projectListingrid);
						req.setAttribute("vsp_commondata", VSP_Commondata);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/VendorsupplierProjectGrid.jsp").forward( req , resp ) ;
					}
					else
					{
						System.out.println("in else"+l_ers);
						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
						req.setAttribute("vendorproject", VENDOR_SUPPLIER_PROJECT);
						req.setAttribute("name", nameListinadd);
						req.setAttribute("commonData", VSP_Commondata);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/Addvendorsupplierproject.jsp").forward(req, resp);
					}
				}
				break;
//
//				case 4:	
//					//edit vendor project page display
//				{
//					System.out.println("case 2");
//					l_ers=VendorSupplierProjectBusiness.edit_VENDOR_SUPPLIER_PROJECT(con, VENDOR_SUPPLIER_PROJECT);
//					if(krrcommon.isValuenull(l_ers))
//					{
//						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						req.setAttribute("vendorproject", VENDOR_SUPPLIER_PROJECT);
//						req.setAttribute("name", nameListinadd);
//						req.setAttribute("commonData", VSP_Commondata);
//						req.setAttribute("message", "");
//						getServletContext().getRequestDispatcher("/Addvendorsupplierproject.jsp").forward(req, resp);
//					}
//					else
//					{
//						l_ers=l_ers+VendorSupplierProjectBusiness.search_VENDOR_SUPPLIER_PROJECT(con, vendorsupplierprojectBeanList, VSP_Commondata);
//						VendorSupplierProjectBusiness.getNameList(con, nameListinadd);
//						VendorSupplierProjectBusiness.getProjectListgrid(con, projectListingrid);
//						req.setAttribute("vendorprojectBeanList",vendorsupplierprojectBeanList);
//						req.setAttribute("namelist", nameListinadd);
//						req.setAttribute("project", projectListingrid);
//						req.setAttribute("vsp_commondata", VSP_Commondata);
//						req.setAttribute("message", l_ers);
//						getServletContext().getRequestDispatcher("/VendorsupplierProjectGrid.jsp").forward( req , resp ) ;
//					}
//				}
//				break;
				}
			}
		}catch(Exception e)
		{e.printStackTrace();
		try{
			req.setAttribute("vendorprojectBeanList",vendorsupplierprojectBeanList);
			req.setAttribute("vsp_commondata", VSP_Commondata);
			req.setAttribute("message", l_ers);
			getServletContext().getRequestDispatcher("/VendorsupplierProjectGrid.jsp").forward( req , resp ) ;

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
