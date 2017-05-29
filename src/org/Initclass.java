package org;

public class Initclass
{
	java.util.Properties dbproperties = null ;

	public  void initurl(String p_path,org.Global_Data Global_Data)
	{
		try
		{
			dbproperties = new java.util.Properties () ;
			dbproperties.load ( new java.io.FileInputStream (p_path )  ) ;
			Global_Data.setServicename_orcl(dbproperties.getProperty("servicename").trim());	//service name Ereturns
			Global_Data.setIpaddress_orcl(dbproperties.getProperty("ipaddress"));               //Ip address Ereturns
			Global_Data.setUsername_orcl(dbproperties.getProperty("username").trim());          //setting user name Ereturns
			Global_Data.setPassword_orcl(dbproperties.getProperty("password").trim());          //Password Ereturns
			Global_Data.setURL_orcl("jdbc:oracle:thin:@"+Global_Data.getIpaddress_orcl().trim()+":1521:"+	Global_Data.getServicename_orcl().trim());
//			Global_Data.setLogfileurl(dbproperties.getProperty("logfileurl").trim());
//			System.out.println("Initialization completed");
		}
		catch ( java.lang.Exception e )
		{
			e.printStackTrace () ;
		}
	}
	public  java.sql.Connection getConnection(org.Global_Data Global_Data,ActionErrors errors  ) //throws java.lang.Exception
	{
		java.sql.Connection con=null;
		try
		{
			java.sql.DriverManager.registerDriver ( new oracle.jdbc.driver.OracleDriver () ) ;
			con= java.sql.DriverManager.getConnection(Global_Data.getURL_orcl(),Global_Data.getUsername_orcl(),Global_Data.getPassword_orcl());
//			if(con!=null) System.out.println("Connection created.");
//			else System.out.println("Connection not created.");
		}
		catch ( java.lang.Exception e )
		{
			e.printStackTrace();
			errors.addError(new ActionError
					("","APP Level 0","Oracle Connection Error "+e.getMessage() ));
		}
		return con;
	}
}
