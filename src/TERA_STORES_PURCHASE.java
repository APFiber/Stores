import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



public class TERA_STORES_PURCHASE extends HttpServlet {

	org.KrrCommon KrrCommon = new org.KrrCommon();
	org.Date_library date_library = new org.Date_library();
	org.Initclass initclass=new org.Initclass();
	org.Global_Data Global_Data=new org.Global_Data();
	org.USER_DB USER_DB = new org.USER_DB();
	org.USER_SERVICES USER_SERVICES = new org.USER_SERVICES();
	bean.USER_COMMONDATA common_data = new bean.USER_COMMONDATA();

	Connection con ;
	/**
	 * Constructor of the object.
	 */
	public TERA_STORES_PURCHASE() {
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
			javax.servlet.http.HttpServletResponse res )
	{
		org.ActionErrors errors=new org.ActionErrors();
		String l_ers="";
		String USERLOGINID ;
		String USERPASSWORD ;
		String USERROLE ;
		String DATE ;
		try
		{
			USERLOGINID = req.getParameter("USERLOGINID");
			USERPASSWORD = req.getParameter("USERPASSWORD");
			System.out.println("login id "+ USERLOGINID+" password "+ USERPASSWORD);
			int Option = (int)KrrCommon.ConvertNum(req.getParameter("Option"));
			bean.USER_MASTER USER_MASTER = new bean.USER_MASTER();
			con = initclass.getConnection(Global_Data, errors);
			if(errors.getErrors().size()==0)
			{
				switch (Option) 
				{
				case 1:
				{
					//check action errors size
					System.out.println("Case 1");			
					String displayCurrentDate = date_library.display_Current_Date("dd/MM/yyyy");

					USER_MASTER.setUSERLOGINID(USERLOGINID);
					USER_MASTER.setUSERPASSWORD(USERPASSWORD);
					if(KrrCommon.isValue(USER_MASTER.getUSERLOGINID()) && KrrCommon.isValue(USER_MASTER.getUSERPASSWORD()))
					{
						l_ers =USER_DB.authenticate_user(con, USER_MASTER);
						if(KrrCommon.isValuenull(l_ers)){

							req.setAttribute("USERNAME", USER_MASTER.getUSERNAME());
							req.setAttribute("USERROLE", USER_MASTER.getUSERROLE());
							req.setAttribute("DATE", displayCurrentDate);
							req.setAttribute("LOGINID", USER_MASTER.getUSERLOGINID());

							if(USER_MASTER.getUSERROLE().equalsIgnoreCase("ADMIN"))
								getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
							if(USER_MASTER.getUSERROLE().equalsIgnoreCase("SUPERVISOR"))
								getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
							if(USER_MASTER.getUSERROLE().equalsIgnoreCase("USER"))
								getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
						}
						else
						{

							req.setAttribute("message", "User details not found");
							getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
						}
					}

					if(KrrCommon.isValue(USER_MASTER.getUSERLOGINID()) && KrrCommon.isValuenull(USER_MASTER.getUSERPASSWORD()))
					{
						l_ers = USER_DB.authenticate_userMaster(con, USER_MASTER);

						System.out.println("error Msg ="+l_ers);
						USERROLE = req.getParameter("USERROLE");
						String loginid =req.getParameter("USERLOGINID");
						String password =req.getParameter("USERPASSWORD");
						if(KrrCommon.isValue(l_ers))
						{
							l_ers=USER_SERVICES.password_check(con, password);
						}

						else
						{
							l_ers=USER_SERVICES.new_password(con, loginid, password);

							req.setAttribute("LOGINID", USER_MASTER.getUSERLOGINID());
							req.setAttribute("USERNAME", USER_MASTER.getUSERNAME());
							req.setAttribute("USERROLE",USERROLE);
							req.setAttribute("DATE", displayCurrentDate);

							req.setAttribute("message", l_ers);
							req.setAttribute("user", USER_MASTER);
							getServletContext().getRequestDispatcher("/NewPassword.jsp").forward(req, res);					
						}
					}

				}
				break;
				case 2:
				{
//					req.setAttribute("USERLOGINID", USERLOGINID);
//					req.setAttribute("USERPASSWORD", USERPASSWORD);
					req.setAttribute("message", "You have successfully logged out!");
					getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				}
				break;
				case 3: 
				{
					System.out.println("Case 3");

					USERROLE = req.getParameter("USERROLE");
					DATE = req.getParameter("DATE");
					req.setAttribute("LOGINID", req.getParameter("LOGINID"));
					req.setAttribute("USERNAME", req.getParameter("USERNAME"));
					req.setAttribute("USERROLE",USERROLE);
					req.setAttribute("DATE", DATE);
					if(KrrCommon.isValuenull(l_ers))
					{
						if(USERROLE.equalsIgnoreCase("ADMIN"))
							getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
						if(USERROLE.equalsIgnoreCase("SUPERVISOR"))
							getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
						if(USERROLE.equalsIgnoreCase("USER"))
							getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
					}

				}
				break;
				case 4:
				{
					System.out.println("Case 4");

					String displayCurrentDate = date_library.display_Current_Date("dd/MM/yyyy");
					USERROLE = req.getParameter("USERROLE");
					USER_MASTER.setUSERLOGINID(req.getParameter("USERLOGINID"));
					USER_MASTER.setUSERPASSWORD(req.getParameter("USERPASSWORD"));

					req.setAttribute("LOGINID", USER_MASTER.getUSERLOGINID());
					req.setAttribute("USERNAME", USER_MASTER.getUSERNAME());
					req.setAttribute("USERROLE",USERROLE);
					req.setAttribute("DATE", displayCurrentDate);

					if(KrrCommon.isValuenull(l_ers))
					{
						req.setAttribute("message", l_ers);
						req.setAttribute("user", USER_MASTER);
						getServletContext().getRequestDispatcher("/NewPassword.jsp").forward(req, res);
					}
					else
					{
						req.setAttribute("message", l_ers);
						req.setAttribute("user", USER_MASTER);
						getServletContext().getRequestDispatcher("/NewPassword.jsp").forward(req, res);
					}
				}

				break;
				case 5:// new password
				{
					System.out.println("case 5");
					USERROLE = req.getParameter("USERROLE");
					String displayCurrentDate = date_library.display_Current_Date("dd/MM/yyyy");

					USER_MASTER.setUSERLOGINID(req.getParameter("USERLOGINID"));
					USER_MASTER.setUSERPASSWORD(req.getParameter("USERPASSWORD"));

					req.setAttribute("LOGINID", USER_MASTER.getUSERLOGINID());
					req.setAttribute("USERNAME", USER_MASTER.getUSERNAME());
					req.setAttribute("USERROLE",USERROLE);
					req.setAttribute("DATE", displayCurrentDate);

					String loginid =req.getParameter("USERLOGINID");
					String password =req.getParameter("USERPASSWORD");

					l_ers=USER_SERVICES.new_password(con, loginid, password);


					if(KrrCommon.isValuenull(l_ers))
					{

						req.setAttribute("message", "password save succesfully");
					}
					else
					{
						req.setAttribute("message", l_ers);

					}
					req.setAttribute("user",USER_MASTER);
					getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				}

				break;
//				case 6:
//				{
//				System.out.println("in case 6");
//				USER_MASTER.setUSERLOGINID(USERLOGINID);
//				USER_MASTER.setUSERPASSWORD(USERPASSWORD);

//				String displayCurrentDate = date_library.display_Current_Date("dd/MM/yyyy");
//				String password =req.getParameter("USERPASSWORD");

//				l_ers = USER_DB.authenticate_user(con, USER_MASTER);

//				if(KrrCommon.isValuenull(l_ers)){
//				req.setAttribute("USERNAME", USER_MASTER.getUSERNAME());
//				req.setAttribute("USERROLE", USER_MASTER.getUSERROLE());
//				req.setAttribute("DATE", displayCurrentDate);
//				req.setAttribute("LOGINID", USER_MASTER.getUSERLOGINID());
//				if(USER_MASTER.getUSERROLE().equalsIgnoreCase("ADMIN"))
//				getServletContext().getRequestDispatcher("/menu.jsp").forward(req, res);
//				}
//				else
//				{
//				l_ers=l_ers+USER_SERVICES.password_check(con, password);
//				req.setAttribute("message", l_ers);
//				req.setAttribute("user", USER_MASTER);
//				req.setAttribute("commonData", common_data);
//				getServletContext().getRequestDispatcher("/NewPassword.jsp").forward(req, res);

//				}
//				}
//				break;
				default:
					break;
				}
			}//if
			System.out.println("error size"+errors.getErrors().size());
			if(errors.getErrors().size()>0){
				req.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/Errors.jsp").forward(req, res);
			}
		}
		catch ( java.lang.Exception e )
		{
			e.printStackTrace () ;
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