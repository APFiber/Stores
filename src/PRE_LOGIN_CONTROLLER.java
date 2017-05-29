import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PRE_LOGIN_CONTROLLER extends HttpServlet {
	org.KrrCommon KrrCommon = new org.KrrCommon();
	org.Initclass initclass=new org.Initclass();
	org.Global_Data Global_Data=new org.Global_Data();
	org.USER_DB USER_DB = new org.USER_DB();
	Connection con ;

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
		try
		{
			con = initclass.getConnection(Global_Data, errors);
			if(errors.getErrors().size()==0)
			{				
				bean.USER_MASTER USER_MASTER = new bean.USER_MASTER();
				USER_MASTER.setUSERLOGINID("admin");
				USER_MASTER.setUSERPASSWORD("admin");
				l_ers = USER_DB.authenticate_user(con, USER_MASTER);
				//if admin user doestnt exits create a record
				if(KrrCommon.isValue(l_ers))
				{
					USER_MASTER.setUSERROLE("ADMIN");
					USER_MASTER.setSTATUS("ACTIVE");
					USER_MASTER.setUSERNAME("SuperUser");
					USER_MASTER.setCREATEDBY("System");
					USER_MASTER.setCREATEDDATE(KrrCommon.curdateyyyymmdd());
					USER_MASTER.setUSERPWDRESETDATE("");
					USER_DB.insert_user_master(con, USER_MASTER);
				}
				getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
			}//if
//			System.out.println("error size"+errors.getErrors().size());
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
