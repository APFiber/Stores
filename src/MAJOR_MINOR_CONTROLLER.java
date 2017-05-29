import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class MAJOR_MINOR_CONTROLLER extends HttpServlet {
	org.KrrCommon krrcommon = new org.KrrCommon();
	org.Date_library date_library = new org.Date_library();
	org.Initclass initclass=new org.Initclass();
	org.Global_Data Global_Data=new org.Global_Data();
	Connection con ;

	/**
	 * Constructor of the object.
	 */
	public MAJOR_MINOR_CONTROLLER() {
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
		// Put your code here
		initclass.initurl(getServletContext().getRealPath("/properties/database.properties"), Global_Data);
	}
	public void service (javax.servlet.http.HttpServletRequest req ,
			javax.servlet.http.HttpServletResponse resp )
	{
		org.ActionErrors errors=new org.ActionErrors();
		org.MAJOR_MINOR_SERVICE MAJORMINORSERVICE=new org.MAJOR_MINOR_SERVICE();
		bean.MAJOR_MINOR_CommonData commonData = new bean.MAJOR_MINOR_CommonData();
		String l_ers="";
		try
		{
			int Option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
			List<bean.MAJOR_MINOR_CODE> MAJORMINORLIST=new ArrayList<bean.MAJOR_MINOR_CODE>();
			bean.MAJOR_MINOR_CODE MAJORMINORCODE=new bean.MAJOR_MINOR_CODE();
			bean.MAJOR_CODE MAJORCODE=new bean.MAJOR_CODE();
			// common data
			commonData.setUSERNAME(req.getParameter("USERNAME"));
			commonData.setLOGINDATE(req.getParameter("DATE"));
			commonData.setUSERROLE(req.getParameter("USERROLE"));
			commonData.setLOGINID(req.getParameter("LOGINID"));
			
			commonData.setMAJORCODE(krrcommon.ConvertNum(req.getParameter("MAJORCODE")));
			commonData.setMAJORCODEDESC(krrcommon.CheckEmptyReturn(req.getParameter("MAJORCODEDESC")));
			commonData.setMINORCODE(krrcommon.ConvertNum(req.getParameter("MINORCODE")));
			commonData.setMINORCODEDESC(krrcommon.CheckEmptyReturn(req.getParameter("MINORCODEDESC")));
			//pagination
			commonData.setPage_No((int)krrcommon.ConvertNum(req.getParameter("pageNo")));
			if(commonData.getPage_No()==0) commonData.setPage_No(1);
			//radio button value
			String MMSRNO=req.getParameter("MMSRNO");
			MAJORMINORCODE.setMAJORMINORSRNO(krrcommon.ConvertNum(MMSRNO));
			
//			commonData.setSelected_Serial_No(MAJORMINORCODE.getMAJORMINORSRNO());
			con = initclass.getConnection(Global_Data, errors);

			if(krrcommon.isValuenull(commonData.getUSERROLE())|| krrcommon.isValuenull(commonData.getUSERNAME())|| krrcommon.isValuenull(commonData.getLOGINDATE()))
			{
				l_ers="Major Minor Details is accessed after login only";
				errors.addError(new org.ActionError("Error","",l_ers));
			}
			if(errors.getErrors().size()==0)
			{
				switch(Option) 
				{
				case 1://major minor code grid page
				{
					commonData.setPage_No(1);
					l_ers=MAJORMINORSERVICE.search_major_minor_code(con, MAJORMINORLIST,commonData );
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("majorBeanList",MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
					}
					else
					{
						commonData.setTotal_NoOf_Records(0);
						commonData.setTotal_NoOf_Pages(0);
						req.setAttribute("majorBeanList",new ArrayList<bean.MAJOR_MINOR_CODE>(Collections.nCopies(10, new bean.MAJOR_MINOR_CODE())));
						req.setAttribute("commonData", commonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 2://display addMajor.jsp page
				{
					req.setAttribute("message", l_ers);
					req.setAttribute("majorMinorCode", MAJORCODE);
					req.setAttribute("commonData", commonData);
					getServletContext().getRequestDispatcher("/addMajorCode.jsp").forward(req, resp);
				}
				break;
				case 3://save major code details
				{
					MAJORCODE.setMAJORCODE(krrcommon.ConvertNum(req.getParameter("ADD_MAJORCODE")));
					MAJORCODE.setMAJORCODEDESC(req.getParameter("ADD_MAJORCODEDESC"));
					l_ers=MAJORMINORSERVICE.insert_major_code(con, MAJORCODE, commonData);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=MAJORMINORSERVICE.search_major_minor_code(con, MAJORMINORLIST,commonData );
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message", "Record saved successfully");	
						}
						else
						{
							req.setAttribute("message", l_ers);	
						}
						req.setAttribute("majorBeanList",MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
					}
					else
					{
						req.setAttribute("commonData", commonData);
						req.setAttribute("majorMinorCode", MAJORCODE);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/addMajorCode.jsp").forward(req, resp);
					}
				}
				break;
				case 4://display addminorCode.jsp page
				{
					l_ers=MAJORMINORSERVICE.get_major_code(con, MAJORMINORLIST);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message", l_ers);	
						req.setAttribute("commonData", commonData);
						req.setAttribute("MAJORCODEDESC", MAJORMINORLIST);
						req.setAttribute("majorMinorCode", MAJORMINORCODE);
						getServletContext().getRequestDispatcher("/addMinor.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+MAJORMINORSERVICE.search_major_minor_code(con, MAJORMINORLIST,commonData );
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message", " ");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("majorBeanList",MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 5://save minor code details
				{
					MAJORMINORCODE.setMAJORCODE(krrcommon.ConvertNum(req.getParameter("ADD_MAJORCODE")));
					MAJORMINORCODE.setMINORCODEDESC(req.getParameter("ADD_MINORCODEDESC"));
					MAJORMINORCODE.setMINORCODE(krrcommon.ConvertNum(req.getParameter("ADD_MINORCODE")));
					MAJORMINORCODE.setMAJORCODEDESC(req.getParameter("ADD_MAJOR_CODE_DESC"));
					l_ers=MAJORMINORSERVICE.insert_major_minor_code(con, MAJORMINORCODE,commonData);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+MAJORMINORSERVICE.search_major_minor_code(con, MAJORMINORLIST,commonData );
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message", "Record saved successfully");	
						}
						else
						{
							req.setAttribute("message",l_ers);
						}
						req.setAttribute("majorBeanList",MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
					}
					else
					{
						l_ers=l_ers+MAJORMINORSERVICE.get_major_code(con, MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message",l_ers);
						req.setAttribute("majorMinorCode", MAJORMINORCODE);
						req.setAttribute("MAJORCODEDESC", MAJORMINORLIST);
						getServletContext().getRequestDispatcher("/addMinor.jsp").forward(req, resp);
					}
				}
				break;
				case 6:
				{
					l_ers=MAJORMINORSERVICE.activate_deactivate_mm_code(con,MAJORMINORCODE.getMAJORMINORSRNO());
					String status=l_ers+MAJORMINORSERVICE.get_MM_Status(con, MAJORMINORCODE.getMAJORMINORSRNO());
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+MAJORMINORSERVICE.search_major_minor_code(con, MAJORMINORLIST,commonData );
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message", "Record successfully changed to "+status+" status");	
						}
						else
						{
							req.setAttribute("message",l_ers);
						}
						req.setAttribute("majorBeanList",MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
					}	
					else
					{
						l_ers=l_ers+MAJORMINORSERVICE.search_major_minor_code(con, MAJORMINORLIST,commonData );
						req.setAttribute("majorBeanList",MAJORMINORLIST);
						req.setAttribute("commonData", commonData);
						req.setAttribute("message",l_ers);
						getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
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
				l_ers="Error in major minor controller "+e.getMessage();
				commonData.setTotal_NoOf_Records(0);
				commonData.setTotal_NoOf_Pages(0);
				req.setAttribute("majorBeanList",new ArrayList<bean.MAJOR_MINOR_CODE>(Collections.nCopies(10, new bean.MAJOR_MINOR_CODE())));
				req.setAttribute("commonData", commonData);
				req.setAttribute("message", l_ers);
				getServletContext().getRequestDispatcher("/major_minor_grid.jsp").forward( req , resp ) ;
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
