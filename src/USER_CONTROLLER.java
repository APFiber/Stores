
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class USER_CONTROLLER extends HttpServlet {
	Connection con ;
	org.Global_Data Global_Data=new org.Global_Data();
	org.Initclass initclass=new org.Initclass();
	org.Date_library data_library=new org.Date_library();
	org.KrrCommon krrcommon=new org.KrrCommon();

	/**
	 * Constructor of the object.
	 */
	public USER_CONTROLLER() {
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
		org.USER_SERVICES USERSERVICES = new org.USER_SERVICES();
		org.USER_DB USER_DB = new org.USER_DB();
		bean.USER_COMMONDATA common_data = new bean.USER_COMMONDATA();
		String l_ers="";
		try
		{
			int Option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
			bean.USER_MASTER USER_MASTER = new bean.USER_MASTER();
			List<bean.USER_MASTER> userBeanList=new ArrayList<bean.USER_MASTER>();
			common_data.setUSERNAME(req.getParameter("USERNAME"));
			common_data.setLOGINDATE(req.getParameter("DATE"));
			common_data.setUSERROLE(req.getParameter("USERROLE"));
			common_data.setLOGINID(req.getParameter("LOGINID"));
			common_data.setUSERLOGINID(krrcommon.CheckEmptyReturn(req.getParameter("ADD_USER_LOGINID")));
			common_data.setUSERROLE1(krrcommon.CheckEmptyReturn(req.getParameter("ADD_USER_ROLE1")));
			common_data.setSTATUS(krrcommon.CheckEmptyReturn(req.getParameter("ADD_STATUS")));
			common_data.setPageNo((int)krrcommon.ConvertNum(req.getParameter("pageNo")));
			if(common_data.getPageNo()==0) common_data.setPageNo(1);
			USER_MASTER.setUSERSRNO(krrcommon.ConvertNum(req.getParameter("USER_SRNO")));
			common_data.setSelectedSerialNo(USER_MASTER.getUSERSRNO());

			System.out.println("name"+req.getParameter("USERNAME")+"date"+req.getParameter("DATE")+"role"+req.getParameter("USERROLE")+" pageNo "+req.getParameter("pageNo"));
			con = initclass.getConnection(Global_Data, errors);

			if(krrcommon.isValuenull(common_data.getUSERROLE())|| krrcommon.isValuenull(common_data.getUSERNAME())|| krrcommon.isValuenull(common_data.getLOGINDATE()))
			{
				l_ers="User Details is accessed after login only";
				errors.addError(new org.ActionError("Error","",l_ers));
			}
			if(errors.getErrors().size()==0){
				switch(Option) 
				{
				case 1:
					//Grid display
				{
					System.out.println("case 1");
					common_data.setPageNo(1);
					l_ers=USERSERVICES.search_user_master(con,userBeanList,common_data);					
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("userBeanList",userBeanList);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", req.getAttribute("message"));
						getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
					}
					else{
						common_data.setTotalNoOfRecords(0);
						common_data.setTotalNoOfPages(0);
						req.setAttribute("userBeanList",new ArrayList<bean.USER_MASTER>(Collections.nCopies(10, new bean.USER_MASTER())));
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 2:	//Add user page display
				{
					System.out.println("case 2");
					if(krrcommon.isValuenull(l_ers))
					{					
						req.setAttribute("user", USER_MASTER);
						req.setAttribute("message", "");
						req.setAttribute("commonData", common_data);
						getServletContext().getRequestDispatcher("/User.jsp").forward(req, resp);
					}
					else{
						common_data.setTotalNoOfRecords(0);
						common_data.setTotalNoOfPages(0);
						req.setAttribute("userBeanList",new ArrayList<bean.USER_MASTER>(Collections.nCopies(10, new bean.USER_MASTER())));
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 3:	//save user record
				{
					System.out.println("case 3");
					USER_MASTER.setUSERSRNO(krrcommon.ConvertNum(req.getParameter("USER_SRNO")));
					String userSrNo=req.getParameter("USER_SRNO");
					USER_MASTER.setUSERNAME(req.getParameter("USER_NAME"));
					USER_MASTER.setUSERLOGINID(req.getParameter("USERLOGINID"));
					USER_MASTER.setUSERPASSWORD(req.getParameter("USER_PASSWORD"));
					USER_MASTER.setUSERROLE(req.getParameter("USER_ROLE1"));
					USER_MASTER.setSTATUS(req.getParameter("USERSTATUS"));

					l_ers=USERSERVICES.insert_update_user_master(con, USER_MASTER,common_data);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+USERSERVICES.search_user_master(con, userBeanList, common_data);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("userBeanList",userBeanList);
							req.setAttribute("commonData", common_data);
							req.setAttribute("message", "Record saved successfully");
							getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
						}	
					}
					else
					{
						req.setAttribute("user",USER_MASTER);
						req.setAttribute("message", l_ers);
						req.setAttribute("commonData", common_data);
						getServletContext().getRequestDispatcher("/User.jsp").forward(req, resp);
					}
				}
				break;
				case 4:	//Edit user page display
				{
					System.out.println("case 4");
					l_ers=USERSERVICES.modify_user_master(con,USER_MASTER) ;
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", "");
						req.setAttribute("user", USER_MASTER);
						getServletContext().getRequestDispatcher("/User.jsp").forward( req , resp ) ;
					}
					else{
						l_ers = l_ers+USERSERVICES.search_user_master(con, userBeanList,common_data);
						req.setAttribute("userBeanList",userBeanList);
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
					}
				}
				break; 
				case 5:	//authorize user
				{
					System.out.println("case 5");	
					l_ers=USERSERVICES.authorize_user_master(con, USER_MASTER.getUSERSRNO());
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=USERSERVICES.search_user_master(con, userBeanList,common_data);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("userBeanList",userBeanList);
							req.setAttribute("commonData", common_data);
							req.setAttribute("message", "Status changed successfully");
							getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
						}	
					}
					else{

						l_ers = l_ers+ USERSERVICES.search_user_master(con, userBeanList,common_data);
						req.setAttribute("userBeanList",userBeanList);	
						req.setAttribute("commonData", common_data);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
					}	
				}
				break;
				
			
				case 7:// dispaly reset password jsp
				{
					System.out.println("case 7");
					Vector<String> UserLoginId = new Vector<String>();
					String USERROLE = req.getParameter("USERROLE");
					
					if(USERROLE.equalsIgnoreCase("ADMIN"))
					{
						l_ers=USERSERVICES.get_userLoginid(con, UserLoginId);
						if(krrcommon.isValuenull(l_ers))
						{
						req.setAttribute("UserLoginid", UserLoginId);
						req.setAttribute("message",l_ers);
						req.setAttribute("commonData", common_data);
						getServletContext().getRequestDispatcher("/ResetPassword.jsp").forward(req, resp);
					}
					}
						else
					{       
					        req.setAttribute("message", "Admin only can access the reset password");
							req.setAttribute("commonData", common_data);
							getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
					}
				}
				break;
				case 8://dispaly change password jsp
				{
					System.out.println("case 8");
					USER_MASTER.setUSERLOGINID(req.getParameter("USERLOGINID"));
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message",l_ers);
						req.setAttribute("user", USER_MASTER);
						req.setAttribute("commonData", common_data);
						getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(req, resp);

					}
				}
				break;
				case 9:// change password
				{
					System.out.println("in case 9");
					USER_MASTER.setUSERLOGINID(req.getParameter("USERLOGINID"));
					USER_MASTER.setUSERPASSWORD(req.getParameter("USERPASSWORD"));
					String loginid =req.getParameter("USERLOGINID");
					String password =req.getParameter("NEWPASSWORD");
					String oldpassword =req.getParameter("USERPASSWORD");
					l_ers =USER_DB.authenticate_userMaster(con, USER_MASTER);
					
					if(!(USER_MASTER.getUSERPASSWORD().equalsIgnoreCase(oldpassword)))
					{
						l_ers="Please enter old password correctly";
					}
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+USERSERVICES.new_password(con, loginid, password);
						if(krrcommon.isValuenull(l_ers))
						{
							req.setAttribute("message","password change successfully");
							req.setAttribute("user", USER_MASTER);
							req.setAttribute("commonData", common_data);
							getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(req, resp);
						}
					}
					else
					{
						req.setAttribute("message",l_ers);
						req.setAttribute("user", USER_MASTER);
						req.setAttribute("commonData", common_data);
						getServletContext().getRequestDispatcher("/ChangePassword.jsp").forward(req, resp);
					}
				}
				break;

//				case 10://dispaly user master jsp
//				{
//					System.out.println("case 10");
//					if(krrcommon.isValuenull(l_ers))
//					{
//						req.setAttribute("commonData", common_data);
//						req.setAttribute("message", l_ers);
//						getServletContext().getRequestDispatcher("/UserMaster.jsp").forward(req, resp);
//					}
//				}
//				break;
				case 11://reset password
				{
					System.out.println("in case 11");
//					USER_MASTER.setUSERLOGINID(req.getParameter("USERLOGINID"));
					String loginid=req.getParameter("USERLOGINID");

					String dispalyCurrentDate = data_library.display_Current_Date("dd/MM/yyyy");

					Vector<String> UserLoginId = new Vector<String>();
					l_ers=USERSERVICES.userloginid_reset(con, USER_MASTER,loginid);

					String USERROLE = req.getParameter("USERROLE");
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("LOGINID", req.getParameter("LOGINID"));
						req.setAttribute("USERNAME", req.getParameter("USERNAME"));
						req.setAttribute("USERROLE",USERROLE);
						req.setAttribute("DATE", dispalyCurrentDate);
						if(USERROLE.equalsIgnoreCase("ADMIN"))
							getServletContext().getRequestDispatcher("/menu.jsp").forward(req, resp);

					}

					else
					{
						req.setAttribute("message",l_ers);
						req.setAttribute("UserLoginid",UserLoginId);
						req.setAttribute("commonData", common_data);
						getServletContext().getRequestDispatcher("/ResetPassword.jsp").forward(req, resp);
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
			l_ers="Error in user controller "+e.getMessage();
			try
			{
				req.setAttribute("userBeanList",new ArrayList<bean.USER_MASTER>(Collections.nCopies(10, new bean.USER_MASTER())));
				req.setAttribute("commonData", common_data);
				req.setAttribute("message", l_ers);
				getServletContext().getRequestDispatcher("/userGrid.jsp").forward( req , resp ) ;
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
