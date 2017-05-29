package org;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public  class KrrCommon {
	
	//for Search field if the value is nothing it adds '%' and if input has value it adds ' on both ends
	public String searchField(String input){
		String output;
		if(input ==""){
	 		output = "'%'";
	 	}
		else{
			output = "'%"+input+"%'";
		}
		return output;
	}
	public  boolean isValue( java.lang.String value )
	{
		if ( !( value == null || value.trim().equals("") ) )
			return true ;
		else
			return false ;
	}
	public  boolean isValuenull ( java.lang.String value )
	{
		if ( ( value == null || value.trim().equals("") ) )
			return true ;
		else
			return false ;
	}
	
//	public  String Checknullreturn(String p_string)
//	{
//
//		if (isValue(p_string))
//			return "'"+RemoveChar(p_string.trim(),'\'')+"'";
//		else
//			return "null";
//	}
	public  String AppendSinlequote(String p_string)
	{

		if (isValue(p_string))
			return "'"+RemoveChar(p_string.trim(),'\'')+"'";
		else
			return "null";
	}
	
	public String AppendPercentileBothEnds(String p_string){
		p_string = p_string.toUpperCase();
		if(p_string ==""){
	 		p_string = "'%'";
	 	}
		else{
			p_string = "'%"+p_string+"%'";
		}
		return p_string;
	}
	
	public  String CheckEmptyReturn(String p_string)
	{
		if (isValue(p_string))
			return p_string;
		else
			return "";
	}
	public  String CheckEmptyReturn1(String p_string)
	{

		if (isValue(p_string))
			return p_string;
		else
			return "&nbsp";
	}
	public    String RemoveChar(String P_string,char c)
	{
		String lstring="";
		if (isValuenull(P_string))
			return "";
		for (int i=0; i<P_string.length();i++)
		{
			if 	(P_string.charAt(i)!=c)
				lstring=lstring+P_string.charAt(i);
		}
		return lstring;
	}
	public long getGatePassSeqVal(Connection con)
	{
		String sql="";
		long seq_id=0;
		ResultSet seq_res=null;
		Statement seq_stmt=null;
		try
		{
			seq_stmt=con.createStatement();
			sql="select GATEPASS.NEXTVAL AS GatePassSeq from dual";
			System.out.println(sql);
			seq_res=seq_stmt.executeQuery(sql);
			if(seq_res.next())seq_id=seq_res.getLong(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return seq_id;
	}
	public  long ConvertNum(String p_string)
	{
		long l_dnum;
		if (org.apache.commons.validator.GenericValidator.isLong(p_string))
		{  
			l_dnum=(Long.parseLong(p_string));
		}
		else
			l_dnum=0;
		return l_dnum; 
	}
	
	
	public  double ConvertDouble(String p_string)
	{
		Double l_dnum;
		if (org.apache.commons.validator.GenericValidator.isDouble(p_string))
		{  
			l_dnum=(Double.parseDouble(p_string));
		}
		else
			l_dnum=0.0;
		return l_dnum; 
	}
	
	public long Nextval_SQ(String SQ_NAME,Connection con)
	{
		long SQ_NUMBER=0;
		Statement stmt=null;
		ResultSet rsset=null;
		try
		{
			String sql="select  "+SQ_NAME+".nextval from dual";
			stmt=con.createStatement();
			rsset=stmt.executeQuery(sql);
			if(rsset.next())
			{
				SQ_NUMBER=rsset.getInt(1);
			}
			System.out.println(sql);
		}
		catch(Exception e)
		{

		}
		finally
		{
			try
			{
				if(rsset!=null) rsset.close();
			}
			catch(Exception e)
			{
			}
			try
			{
				if(stmt!=null) stmt.close();
			}
			catch(Exception e)
			{
			}
		}
		return SQ_NUMBER;
	}
	
	public  int Convertint(String p_string)
	{
		int l_dnum;
		if (org.apache.commons.validator.GenericValidator.isLong(p_string))
		{  
			l_dnum=(Integer.parseInt(p_string));
		}
		else
			l_dnum=0;
		return l_dnum; 
	}
	public   String curdateyyyymmdd()
	{
		int i;
		String curdt="";
		java.util.Date dt = new java.util.Date () ;
		i=dt.getYear()+1900;
		if (i>9) curdt=""+i; else curdt="0"+i;
		i=dt.getMonth()+1;
		if (i>9) curdt=curdt+""+i; else curdt=curdt+"0"+i;
		i=dt.getDate();
		if (i>9) curdt=curdt+""+i; else curdt=curdt+"0"+i;
		return curdt;
	}

}//class
