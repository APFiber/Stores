package org;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_library
{
	//=================================format_Validate===================//
	/*
	 * 			Function Name :format_Validate
	 * 			Input Parameters:
	 * 							1. format_string of the text
	 * 			Return Value	:
	 * 							1.Format number
	 * 			process logic:
	 * 							Format        return value
	 * 							ddMMyyyy		1
	 * 							yyMMdd			2
	 * 							ddMMyy			3
	 * 							dd-MMM-yyyy		4
	 * 							dd/MM/yyyy		5
	 * 							dd-MMM-yy		6
	 * 							yyyyMMdd		7
	 * 			Modified based on iteration 1: Added the following formats
	 * 							dd-MM-yyyy		8
	 * 							yyyy-MM-dd		9
	 * 							dd-MM-yy		10
	 * 			Errors			:Format number zero for invalid format
	 */
	public int format_Validate(String format_string)
	{
		String format=format_string;
		try
		{
			if(format.equals("ddMMyyyy")) return 1;
			if(format.equals("yyMMdd")) return 2;
			if(format.equals("ddMMyy")) return 3;
			if(format.equals("dd-MMM-yyyy")) return 4;
			if(format.equals("dd/MM/yyyy")) return 5;
			if(format.equals("dd-MMM-yy")) return 6;
			if(format.equals("yyyyMMdd")) return 7;
			if(format.equals("dd-MM-yyyy")) return 8;
			if(format.equals("yyyy-MM-dd")) return 9;
			if(format.equals("dd-MM-yy")) return 10;
		}
		catch(Exception e)
		{

		}
		return 0;
	}
	//================================month_Validate=====================//
	/*
	 * 			Function Name :month_Validate
	 * 			Input Parameters:
	 * 							1. month_string of the text
	 * 			Return Value	:
	 * 							1.Month number
	 * 			process logic:
	 * 							Format        return value
	 * 							jan				01
	 * 							feb				02
	 * 							mar				03
	 * 							apr				04
	 * 							may				05
	 * 							jun				06
	 * 							jul				07
	 * 							aug				08
	 * 							sep				09
	 * 							oct				10
	 * 							nov				11
	 * 							dec				12
	 * 			Errors			:Month number zero for invalid month
	 */
	public String month_Validate(String month_string)
	{
		String month=  month_string;
		try
		{
			if(month.equalsIgnoreCase("JAN"))return "01";
			if( month.equalsIgnoreCase("FEB"))return "02";
			if(month.equalsIgnoreCase("MAR"))return "03";
			if(month.equalsIgnoreCase("APR"))return "04";
			if(month.equalsIgnoreCase("MAY"))return "05";
			if(month.equalsIgnoreCase("JUN"))return "06";
			if(month.equalsIgnoreCase("JUL"))return "07";
			if(month.equalsIgnoreCase("AUG"))return "08";
			if(month.equalsIgnoreCase("SEP"))return "09";
			if(month.equalsIgnoreCase("OCT"))return "10";
			if(month.equalsIgnoreCase("NOV"))return "11";
			if(month.equalsIgnoreCase("DEC"))return "12";
		}
		catch(Exception e)
		{
		}

		return "0";
	}
	//===============================get_Current_Date====================//
	/*
	 * 			Function Name :get_Current_Date
	 * 			Input Parameters:Nill
	 * 			Return Value	:
	 * 							1.Current date
	 * 			process logic:
	 * 							Get the system date
	 * 			Errors			:Nil
	 */
	public Date get_Current_Date()
	{
		Date p_date = null;
		try
		{
			p_date=new Date();
		}
		catch(Exception e)
		{
		}
		return p_date;
	}
	//==================================display_Date========================//
	/*
	 * 			Function Name 	:display_Date
	 * 			Input Parameters:
	 * 							1.p_date
	 * 							2.p_format of the text
	 * 			Return Value	:
	 * 							1.Date in given format string
	 * 			process logic:
	 * 							Date is formatted in a given format string
	 * 			Errors			:Nil
	 */
	public  String display_Date(Date p_date,String p_format)
	{
		DateFormat formatter;
		String l_ers="";
		try
		{
			formatter = new SimpleDateFormat(p_format);
			l_ers=(formatter.format(p_date));
		}
		catch(Exception e)
		{
		}
		return l_ers;
	}
	//===================================display_Current_Date===========================//
	/*
	 * 			Function Name :display_Current_Date
	 * 			Input Parameters:
	 * 							1.Format of the text
	 * 			Return Value	:
	 * 							1.Errors in string
	 * 			process logic:
	 * 							Displays current date according to the given input format
	 * 							Validates on format
	 * 			Errors			:
	 * 							1.#Invalid date format
	 * 							2.#Date format should not be empty
	 */
	public String display_Current_Date(String p_format)
	{
		String l_ers="";
		Date c_date=null;
		try
		{
			int for_num=format_Validate(p_format);
			if(isValue(p_format))
			{
				if(for_num!=0)
				{
					c_date=get_Current_Date();
					l_ers=display_Date(c_date,p_format);
				}
				else
					l_ers="#Invalid date format " +p_format;
			}
			else
			{
				l_ers="#Date format should not be empty ";
			}
		}
		catch(Exception e)
		{
		}
		return l_ers;
	}
	//=============================string_Date=======================================//
	/*
	 * 			Function Name :string_Date
	 * 			Input Parameters:
	 * 							1.p_date of the text
	 * 							2.format1 in string
	 * 			Return Value	:
	 * 							1.returns in date
	 * 			process logic:
	 * 							Converts string to date by using parse
	 * 			Errors			:Nil
	 */
	public  Date string_Date(String p_date,String format1)
	{
		DateFormat formatter;
		Date datex=null;
		try
		{
			formatter = new SimpleDateFormat(format1);
			datex = (Date) formatter.parse(p_date);
		}
		catch (Exception e)
		{
		}
		return datex;
	}
	//==================================isValuenull=============================//
	/*
	 * 			Function Name :isValuenull
	 * 			Input Parameters:
	 * 							1.Value of the text
	 * 			Return Value	:
	 * 							returns true or false
	 * 			process logic:
	 * 							Checks the value for null
	 * 							if value is null it returns true else it returns false
	 * 			Errors			:Nil
	 */
	public  boolean isValuenull (String value )
	{
		if ( ( value == null || value.trim().equals("") ) )
			return true ;
		else
			return false ;
	}
	//=========================================isValue=========================//
	/*
	 * 			Function Name :isValue
	 * 			Input Parameters:
	 * 							1.Value of the text
	 * 			Return Value	:
	 * 							returns true or false
	 * 			process logic	:
	 * 							Checks the value for null
	 * 							if value is not null it returns true else it returns false
	 * 			Errors			:Nil
	 */
	public  boolean isValue ( String value )
	{
		if ( !( value == null || value.trim().equals("") ) )
			return true ;
		else
			return false ;
	}
	//======================================validate_DaysMonth=============================//
	/*
	 * 			Function Name 	:validate_DaysMonth
	 * 			Input Parameters:
	 * 							1.date of the text
	 * 							2.format of the text
	 * 			Return Value	:
	 * 							returns the error or empty string
	 * 			process logic	:
	 * 							takes the input format in allowed formats and date in text
	 * 							by using switch case format numbers will be equal to the case numbers
	 * 							takes the substring for day, month and year for allowed formats
	 * 							if date contains invalid string it prints an error
	 * 							if days greater than maximum days in a month then it returns an error
	 *   		Modified based on iteration 1: Added the following formats in switch case
	 *  									dd-MM-yyyy, yyyy-MM-dd and dd-MM-yy
	 * 			Errors			:
	 * 							1.#Invalid date
	 * 							2.#Invalid month: " +date.substring(3, 6)+" month should be in word and from jan-dec
	 * 							3.#Invalid date: "+date+", date should be in numeric
	 * 							4.#we are expecting 'dd-MMM-yyyy' format but you are giving this +date;
	 * 							5.#we are expecting 'dd-MMM-yy' format but you are giving this +date;
	 * 							6.#we are expecting 'dd/MM/yyyy' format but you are giving this +date;
	 *     		Modified based on iteration 1: changed the following errors
	 *     						1."#Date"  +date+" is incompatible with format1"
	 *     						2.#Invalid day of the month
	 *     						3.#Invalid date: "+date+", date should be in numeric
	 *     						4.#Invalid month month should be in word and from jan-dec
	 */
	public String validate_DaysMonth(String date,String format)
	{
		String l_ers="";
		String day="";
		String month="";
		String year ="";
		try
		{
			int for_num=format_Validate(format);
			switch (for_num)
			{
			case 1://ddMMyyyy
			{
				year=(date.substring(4,8));
				month=(date.substring(2, 4));
				day=(date.substring(0, 2));
			}
			break;
			case 2://yyMMdd
			{
				year=20+(date.substring(0, 2));
				month=(date.substring(2, 4));
				day=(date.substring(4, 6));
			}
			break;
			case 3://ddMMyy
			{
				day=(date.substring(0, 2));
				month=(date.substring(2, 4));
				year=20+(date.substring(4, 6));
			}
			break;
			case 4://dd-MMM-yyyy
			{
				if(("-".equals(date.substring(2, 3)))&&("-".equals(date.substring(6,7))))
				{
					month=month_Validate(date.substring(3, 6));
					day=(date.substring(0, 2));
					year=(date.substring(7,11));
					if(convertNum(month)==0)	l_ers="#Invalid month:"+date.substring(3, 6)+ ", month should be in word and from jan-dec";
					else l_ers="";
				}
				else l_ers="#Date " +date+" is incompatible with format1 " +format;
			}
			break;
			case 5://dd/MM/yyyy
			{
				if(("/".equals(date.substring(2, 3)))&&("/".equals(date.substring(5,6))))
				{
					day=(date.substring(0, 2));
					month=(date.substring(3,5));
					year=(date.substring(6,10));
				}
				else l_ers="#Date " +date+" is incompatible with format1 " +format;
			}
			break;
			case 6://dd-MMM-yy
			{
				if(("-".equals(date.substring(2, 3))&&("-".equals(date.substring(6,7)))))
				{
					month=month_Validate(date.substring(3, 6));
					day=(date.substring(0, 2));
					year=20+(date.substring(7,9));
					if(convertNum(month)==0)	l_ers="#Invalid month:"+date.substring(3, 6)+ ", month should be in word and from jan-dec";
					else l_ers="";
				}
				else l_ers="#Date " +date+"is incompatible with format1 " +format;
			}
			break;
			case 7://yyyyMMdd
			{
				year=(date.substring(0,4));
				month=(date.substring(4, 6));
				day=(date.substring(6,8));
			}
			break;
			case 8://dd-MM-yyyy
			{
				if(("-".equalsIgnoreCase(date.substring(2, 3)))&&("-".equalsIgnoreCase(date.substring(5,6))))
				{
					day=(date.substring(0, 2));
					month=(date.substring(3,5));
					year=(date.substring(6,10));
				}
				else l_ers="#Date " +date+"is incompatible with format1 " +format;
			}
			break;
			case 9://yyyy-MM-dd
			{
				if(("-".equals(date.substring(4, 5)))&&("-".equals(date.substring(7,8))))
				{
					day=(date.substring(8,10));
					month=(date.substring(5,7));
					year=(date.substring(0,4));
				}
				else	l_ers="#Date " +date+"is incompatible with format1 " +format;
			}
			break;
			case 10: //dd-MM-yy
			{
				if(("-".equals(date.substring(2,3)))&&("-".equals(date.substring(5,6))))
				{
					day=(date.substring(0,2));
					month=(date.substring(3,5));
					year=20+(date.substring(6,8));
				}
				else	l_ers="#Date " +date+"is incompatible with format1 " +format;
			}
			break;
			}
			if(isValuenull(l_ers))
			{
				if(convertNum(month) != 0 && convertNum(year)!=0 && convertNum(day)!=0)
				{
					l_ers="";
					if(isValuenull(l_ers))
					{
						date=month+year;
						l_ers=get_Maxdays(date);
						int i = Integer.parseInt(day);
						if( i> Integer.parseInt(l_ers))
						{
							l_ers="#Invalid day of the month " +i;
						}
						else l_ers="";
					}
				}
				else l_ers="#Invalid date: "+date+", date should be in numeric ";
			}
		}
		catch(Exception e)
		{
		}
		return l_ers;
	}
//	====================================get_Maxdays=============================//
	/*
	 * 			Function Name 	:get_Maxdays
	 * 			Input Parameters:
	 * 							1.Date in string
	 * 			Return Value	:
	 * 							returns max number of days for given date or error
	 * 			process logic	:
	 * 							takes the substring for month and year.
	 * 							if date contains any string then it returns an error
	 * 							if months greater than 12 then it returns an error
	 * 							by using simpledateformat object parse the given string date to date object
	 * 							by using getactualmaximum gets the maximum days in a month
	 * 							converts the maxdays to string
	 * 			Errors			:
	 * 							1.#Invalid month of the year
	 * 							2.#Invalid date:"+date+" date should be in numeric
	 * 							3.#Invalid date:"+date+", is incompatible with format MMyyyy
	 * 							4.#Date should not be empty
	 */
	public String get_Maxdays(String date)
	{
		SimpleDateFormat sdf;
		Date date1;
		int maxdays=0;
		String month ="";
		String year="";
		String l_ers="";
		try
		{
			if(isValue(date))
			{
				if(date.trim().length()==6)
				{
					month=date.substring(0,2);
					year=date.substring(2,6);
					if(convertNum(month)!=0 && convertNum(year)!=0)
					{
						if(convertNum(month) <= 12)
						{
							sdf=new SimpleDateFormat("MMyyyy");
							date1 = (Date)sdf.parse(month+year);
							Calendar calendar=Calendar.getInstance();
							calendar.setTime(date1);
							maxdays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
							l_ers=Integer.toString(maxdays);
						}
						else
						{
							l_ers="#Invalid month of the year " +month;
						}
					}
					else
					{
						l_ers="#Invalid date:"+date+" date should be in numeric";
					}
				}
				else
				{
					l_ers="#Invalid date:"+date+",is incompatible with  format MMyyyy";
				}
			}
			else
			{
				l_ers="#Date should not be empty";
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
//	=====================================convertNum================================//
	/*
	 * 			Function Name 	:convertNum
	 * 			Input Parameters:
	 * 							1.p_string of the text
	 * 			Return Value	:
	 * 							returns 0 or long or double
	 * 			process logic	:
	 * 							Checks the p_string value for only numbers otherwise it returns an error
	 * 							if there is no error then, p_string converts in to long or double
	 * 			Errors			:Returns zero if there is an error
	 */
	public  long convertNum(String p_string)
	{
		long l_dnum;
		double ld_dnum;
		l_dnum=0;
		try
		{
			if(p_string.matches("[0-9]+"))
			{
				if (org.apache.commons.validator.GenericValidator.isLong(p_string))
				{
					l_dnum=(Long.parseLong(p_string));
				}
				else
					if (org.apache.commons.validator.GenericValidator.isDouble(p_string))
					{
						ld_dnum=(Double.parseDouble(p_string));
						l_dnum=(long)ld_dnum;
					}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return l_dnum;
	}
	/*
	 * 			Function Name :date_Conv
	 * 			Input Parameters:
	 * 							1.date1 in string
	 * 							2.format1 of the text
	 * 							3.format2 of the text
	 * 			Return Value	:
	 * 							1.Returns error or converted date
	 * 			process logic	:
	 * 							if date value is null it returns an error
	 * 							validates on date, format1 and format2
	 * 							if date1 length and format1 length are not equal then returns an error
	 * 							validates on year, month and day field and returns an error.
	 * 							calls string to date function and date to string function.
	 * 			Errors			:
	 * 							1.Invalid date input
	 * 							2.Invalid date--> The length should be equals to specified format
	 * 							3.Invalid input format1
	 *                			4.Invalid input format2
	 * 			Modified based on iteration 1: changed the following errors
	 * 							1.#Date should not be empty
	 * 							2."#Date:" +date1+" is incompatible with format1"
	 *  						3.Invalid format1
	 *                			4.Invalid format2
	 */
//	===========================Date conversion=================================//

	public String date_Conv(String date1, String format1,String format2)
	{
		String l_ers="";
		int f1=format_Validate(format1);
		int f2=format_Validate(format2);
		try
		{
			if(f1!=0)
			{
				if(f2!=0)
				{
					if(isValue(date1))
					{
						if(date1.trim().length()==format1.length())
						{
							l_ers=validate_DaysMonth(date1, format1);//validate month and days
							if(l_ers.length()<=8)
							{
								Date Kdate=string_Date(date1,format1);//converts string to date
								l_ers=display_Date(Kdate, format2);//converts date to string
							}
						}
						else
						{
							l_ers= "#Date:" +date1+" is incompatible with format1 " +format1;
						}
					}
					else
					{
						l_ers="#Date should not be empty";
					}
				}
				else
				{
					l_ers="#Invalid format2:" +format2;
				}
			}
			else
			{
				l_ers="#Invalid format1:" +format1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
//	=========================Date difference============================//
	/*
	 * 			Function Name :get_Date_Difference
	 * 			Input Parameters:
	 * 							1.from date of the text
	 * 							2.to date of the text
	 * 							3.Format1 of the text
	 * 							4.Format2 of the text
	 * 							5.Mode of the text
	 * 			Return Value	:
	 * 							1.error/ date difference in days or months or years
	 * 			process logic:
	 * 							If from date and to date are empty then it returns an error
	 * 							if the length of date and formats are not equal then it returns an error
	 * 							validates on day and month field in from and to date
	 * 							convert the given from and to date by using string to date function
	 * 							if from date greater than to date then it returns an error
	 * 							Use the calendar instance to calculate date difference
	 * 							according to mode selection(d or m or y) it prints difference between from and to date
	 * 							if mode not equals to d or m or y then returns an error
	 * 			Errors			:
	 * 							1.#from_date field should not be empty
	 * 							2.#to_date field should not be empty
	 * 							5.#From date should not be Greater Than To Date
	 * 							6.#Invalid date--> The length should be equals to specified format
	 * 							7.#Mode should be in 'd' or 'm' or 'y'
	 * 			Modified based on iteration 1: changed the following errors
	 * 							1.#Date1 should not be greater than date2
	 * 							2.#Mode should be 'd' or 'm' or 'y'
	 * 							3."#Date2 " +to+" incompatible with format "
	 * 							4."#Date1 " +from+" incompatible with format "
	 * 							5.#Date2 should not be empty
	 * 							6.#Date1 should not be empty
	 * 							7.#Invalid format1
	 * 							8.#Invalid format2
	 */
	public String get_Date_Difference(String from,String to, String format1,String format2,String mode)
	{
		String l_ers="";
		int year = 0;
		int year1=0;
		int month = 0;
		int days = 0;
		int tempDifference;
		int f1=format_Validate(format1);
		int f2=format_Validate(format2);
		try
		{
			if(f1!=0)
			{
				if(f2!=0)
				{
					if(isValue(from))
					{
						if(isValue(to))
						{
							if(from.length()==format1.length())
							{
								if(to.length()==format2.length())
								{
									from=date_Conv(from, format1, "yyyyMMdd");
									if(!(from.substring(0, 1).equalsIgnoreCase("#")))
									{
										to=date_Conv(to,format2,"yyyyMMdd");
										if(!(to.substring(0, 1).equalsIgnoreCase("#")))
										{
											/*SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
									Date date1=(Date)sdf.parse(from);
									Date date2=(Date)sdf.parse(to);*/
											Calendar fromDate=Calendar.getInstance();
											Calendar toDate=Calendar.getInstance();
											Date date1=string_Date(from, "yyyyMMdd");
											Date date2=string_Date(to, "yyyyMMdd");
											if (date1.compareTo(date2) < 0)
											{
												fromDate.setTime(date1);
												toDate.setTime(date2);
												//MONTH CALCULATION
												year1 = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR));
												month = year1*12 + toDate.get(Calendar.MONTH) - fromDate.get(Calendar.MONTH);
												//YEAR CALCULATION
												if(month>12)
												{
													year=month/12;
												}
												// DAY CALCULATION
												while (fromDate.get(Calendar.YEAR) != toDate.get(Calendar.YEAR))
												{
													tempDifference = 365 * (toDate.get(Calendar.YEAR) - fromDate.get(Calendar.YEAR));
													days += tempDifference+1;
													fromDate.add(Calendar.DAY_OF_YEAR, tempDifference);
												}
												if (fromDate.get(Calendar.DAY_OF_YEAR) != toDate.get(Calendar.DAY_OF_YEAR))
												{
													tempDifference = toDate.get(Calendar.DAY_OF_YEAR) - fromDate.get(Calendar.DAY_OF_YEAR);
													fromDate.add(Calendar.DAY_OF_YEAR, tempDifference);
													days += tempDifference+1;
												}
												//MODE CALCULATION
												if(mode.equalsIgnoreCase("d"))
												{
													l_ers="Difference between two given dates in days " +days+" days";
												}
												else if(mode.equalsIgnoreCase("m"))
												{
													l_ers="Differnce between two given dates in months " +month+" months";
												}
												else if(mode.equalsIgnoreCase("y"))
												{
													l_ers="Difference between two given dates in years " +year+" year";
												}
												else
												{
													l_ers="#Mode should be 'd' or 'm' or 'y'";
												}
											}
											else
											{
												l_ers="#Date1 should not be greater than date2";
											}
										}
										else
										{
											l_ers=to;
										}
									}
									else
									{
										l_ers=from;
									}
								}
								else
								{
									l_ers="#Date2 " +to+" incompatible with format "+format2;
								}
							}
							else
							{
								l_ers="#Date1 " +from+" incompatible with format "+format1;
							}
						}
						else
						{
							l_ers="#Date2 should not be empty ";
						}
					}
					else
					{
						l_ers="#Date1 should not be empty ";
					}
				}
				else
				{
					l_ers="#Invalid format2:" +format2;
				}
			}
			else
			{
				l_ers="#Invalid format1:" +format1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
//	=======================err_Print==============================//
	/*
	 * 			Function Name 	:err_Print
	 * 			Input Parameters:
	 * 							1.plan of the text
	 * 							2.l_ers of the text
	 * 			Return Value	:
	 * 							Nil
	 * 			process logic	:
	 * 							if l_ers String matches '#' with first number it prints test is failed
	 * 							if l_ers String does not matches with '#' it prints test is success.
	 * 			Errors			:
	 * 							Nil
	 */
	public String err_Print(String l_ers1)
	{
		String l_ers="";
		if(l_ers1.substring(0,1).equalsIgnoreCase("#"))
		{
			l_ers=" Test is failed ";
		}
		else
		{
			l_ers=" Test is success ";
		}
		return l_ers;
	}
//	==================================date_Add_Subtract===================================//
	/*
	 * 			Function Name 	:date_Add_Subtract
	 * 			Input Parameters:
	 * 							1.p_date of the text
	 * 							2.Format of the text
	 * 							3.Mode of the text
	 * 							4.add_number of the number
	 * 			Return Value	:
	 * 							error or date after adding or subtract days or months or years
	 * 			process logic	:
	 * 							if p_date value is null then it returns an error.
	 * 							if the date format is not in allowed formats then returns error.
	 * 							if the p_date length and format length are not equal then it returns an error.
	 * 							Validate the date for days, months and years for allowed format.
	 * 							Parse the day, month and year to integer using substring according to format.
	 * 							If mode is equal to ‘d’ then add number of days to day by using calendar instance.
	 * 							If mode is equal to ‘m’ then add number of months to month by using calendar instance.
	 * 							If mode is equal to ‘y’ then add number of years to year by using calendar instance.
	 *  		Modified based on iteration 1: Added the following formats in switch case
	 *  										dd-MM-yyyy, yyyy-MM-dd and dd-MM-yy
	 * 			Errors			:
	 * 							1.#Mode should be 'd' or 'm' or 'y'
	 * 							2."#Date " +p_date+" is incompatible with format " +format;
	 * 							3.#Invalid format:
	 * 							4.#Invalid date input
	 * 			Modified based on iteration1: changed the following errors
	 * 							1.#Date should not be empty
	 *
	 */
	public String date_Add_Subtract(String p_date,String format,String mode,int add_number)
	{
		String l_ers="";
		int year=0;
		int month=0;
		int day=0;
		String month1="";
		Calendar cal = Calendar.getInstance();
		int for_num=format_Validate(format);
		if(isValue(p_date))
		{
			if(for_num!=0)
			{
				SimpleDateFormat sdf=new SimpleDateFormat(format);
				if(p_date.length()==format.length())
				{
					l_ers=validate_DaysMonth(p_date,format);
					if(isValuenull(l_ers))
					{
						switch(for_num)
						{
						case 1://ddMMyyyy
							year=Integer.parseInt(p_date.substring(4,8));
							month=Integer.parseInt(p_date.substring(2,4))-1;
							day=Integer.parseInt(p_date.substring(0,2));
							break;
						case 2://yyMMdd
							year=Integer.parseInt(p_date.substring(0,2));
							month=Integer.parseInt(p_date.substring(2,4))-1;
							day=Integer.parseInt(p_date.substring(4,6));
							break;
						case 3://ddMMyy
							day=Integer.parseInt(p_date.substring(0, 2));
							month=Integer.parseInt(p_date.substring(2, 4))-1;
							year=Integer.parseInt(p_date.substring(4, 6));
							break;
						case 4://dd-MMM-yyyy
							month1=month_Validate(p_date.substring(3, 6));
							month=Integer.parseInt(month1)-1;
							day=Integer.parseInt(p_date.substring(0, 2));
							year=Integer.parseInt(p_date.substring(7,11));
							break;
						case 5://dd/MM/yyyy
							day=Integer.parseInt(p_date.substring(0, 2));
							month=Integer.parseInt(p_date.substring(3,5))-1;
							year=Integer.parseInt(p_date.substring(6,10));
							break;
						case 6://dd-MMM-yy
							month1=month_Validate(p_date.substring(3, 6));
							month=Integer.parseInt(month1)-1;
							day=Integer.parseInt(p_date.substring(0, 2));
							year=Integer.parseInt(p_date.substring(7,9));
							break;
						case 7://yyyyyMMdd
							year=Integer.parseInt(p_date.substring(0,4));
							month=Integer.parseInt(p_date.substring(4, 6))-1;
							day=Integer.parseInt(p_date.substring(6,8));
							break;
						case 8://dd-MM-yyyy
							year=Integer.parseInt(p_date.substring(6,10));
							month=Integer.parseInt(p_date.substring(3,5))-1;
							day=Integer.parseInt(p_date.substring(0,2));
							break;
						case 9://yyyy-MM-dd
							year=Integer.parseInt(p_date.substring(0,4));
							month=Integer.parseInt(p_date.substring(5,7))-1;
							day=Integer.parseInt(p_date.substring(8,10));
							break;
						case 10://dd-MM-yy
							year=Integer.parseInt(p_date.substring(6,8));
							month=Integer.parseInt(p_date.substring(3,5))-1;
							day=Integer.parseInt(p_date.substring(0,2));
							break;
						}
						if(mode.equalsIgnoreCase("d"))
						{
							cal.set(Calendar.DATE, day);
							cal.set(Calendar.MONTH,month);
							cal.set(Calendar.YEAR, year);
							cal.add(Calendar.DATE, add_number);
							l_ers = sdf.format(cal.getTime());
						}
						else if(mode.equalsIgnoreCase("m"))
						{
							cal.set(Calendar.DATE, day);
							cal.set(Calendar.MONTH,month);
							cal.set(Calendar.YEAR, year);
							cal.add(Calendar.MONTH, add_number);
							l_ers = sdf.format(cal.getTime());
						}
						else if(mode.equalsIgnoreCase("y"))
						{
							cal.set(Calendar.DATE, day);
							cal.set(Calendar.MONTH, month);
							cal.set(Calendar.YEAR,year);
							cal.add(Calendar.YEAR, add_number);
							l_ers = sdf.format(cal.getTime());
						}
						else
						{
							l_ers="#Mode should be 'd' or 'm' or 'y'";
						}
					}
				}
				else
				{
					l_ers="#Date " +p_date+" is incompatible with format " +format;
				}
			}
			else
			{
				l_ers="#Invalid format: " +format;
			}
		}
		else
		{
			l_ers="#Date should not be empty ";
		}
		return l_ers;
	}
//	==================================Get time from String====================//
	/*
	 * 			Function Name 	:str_To_Time
	 * 			Input Parameters:
	 * 							1.time of the text
	 * 			Return Value	:
	 * 							error or given time
	 * 			process logic	:
	 * 							take the substring for hours, minutes and seconds
	 * 							by using simpledateformat take the time in hh:mm:ss format
	 * 							if time contains string or operator then it returns an error
	 * 							if hours greater than 23 then it returns an error
	 * 							if minutes greater than 60 then it returns an error
	 * 							if seconds greater than 60 then it returns an error
	 * 							by using parse convert the given String time to time
	 * 			Errors			:
	 * 							#Invalid seconds ,seconds range from 0 to 59
	 * 							#Invalid minutes ,minutes range from 0 to 59
	 * 							#Invalid hours ,hours range from 0 to 23
	 * 							#Time should be in numeric only
	 * 							#Time should not be empty
	 * 							#Invalid time"+time+",incompatible in HH:mm:ss format"
	 */
	public String str_To_Time(String time)
	{
		String l_ers="";
		long hours;
		long minutes;
		long seconds;
		Date date=null;
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		try
		{
			if(isValue(time))
			{
				if(time.length()==8)
				{
					hours=convertNum(time.substring(0,2));
					minutes=convertNum(time.substring(3,5));
					seconds=convertNum(time.substring(6,8));
					if((time.substring(2,3).equalsIgnoreCase(":")) &&  (time.substring(5,6).equalsIgnoreCase(":")))
					{
						if(hours!=0 && minutes!=0 && seconds!=0)
						{
							if(hours<=23 )
							{
								if(minutes<=59)
								{
									if(seconds<=59)
									{
										date = formatter.parse(time);
										l_ers=formatter.format(date);
									}
									else
									{
										l_ers="#Invalid seconds " +seconds+ ",seconds range from 0 to 59";
									}
								}
								else
								{
									l_ers="#Invalid minutes " +minutes+ ",minutes range from 0 to 59";
								}
							}
							else
							{
								l_ers="#Invalid hours " +hours+ ",hours range from 0 to 23";
							}
						}
						else{
							l_ers="#Time "+time+" should be numeric only";
						}
					}
					else
					{
						l_ers="#time "+time+",incompatible in HH:mm:ss format";
					}
				}
				else
				{
					l_ers="#Time " +time+ " incompatible with HH:mm:ss fotmat";
				}
			}
			else
			{
				l_ers="#Time should not be empty";
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
//	============================get_current_Time===============================//
	/*
	 * 			Function Name 	:current_Time
	 * 			Input Parameters:
	 * 							1.Nil
	 * 			Return Value	:
	 * 							current time
	 * 			process logic	:
	 * 							gets the current time using simpledateformat
	 * 			Errors			:
	 * 							Nil
	 */
	public String current_Time()
	{
		String l_ers = "";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		l_ers = "current time: " +sdf.format(cal.getTime());
		return l_ers;
	}
//	===================================TIME DIFFERENCE=================================//
	/*
	 * 			Function Name 	:time_Diff
	 * 			Input Parameters:
	 * 							1.time1 of the text
	 * 							2.time2 of the text
	 * 			Return Value	:
	 * 							error or time difference in minutes,hours and seconds
	 * 			process logic	:
	 * 							if time1 and time2 values are null then it returns an error
	 * 							if time1 and time2 contains alphabets then it returns an error
	 * 							if time1 and time2 length not equals to eight then it returns an error
	 * 							time1 and time2 validates for hours, minutes and seconds
	 * 							parse the time1 and time2 using simpledateformat object
	 * 							calculate time difference for hours, minutes and seconds
	 * 			Errors			:
	 * 							#Invalid seconds " +seconds1+ ",seconds range from 0 to 59
	 * 							#Invalid minutes " +minutes1+ ",minutes range from 0 to 59
	 * 							#Invalid hours " +hours1+ ",hours range from 0 to 23
	 * 							#Invalid seconds " +seconds+ ",seconds range from 0 to 59
	 * 							#Invalid minutes " +minutes ",minutes range from 0 to 59
	 * 							#Invalid hours " +hours+ ",hours range from 0 to 23
	 * 							#Invalid time length "+time2+ " is incompatible with hh:mm:ss format
	 * 							#Invalid time length "+time1+ "  is incompatible with hh:mm:ss format
	 * 							#invalid time " +time2+",the time should be in numeric only
	 * 							#invalid time " +time1+",the time should be in numeric only
	 * 							#Time1 should not be empty
	 *							#Time2 should not be empty
	 */
	public String time_Diff(String time1, String time2)
	{
		long hours,hours1;
		long minutes,minutes1;
		long seconds,seconds1;
		long difference;
		Date date1;
		Date date2;
		String l_ers="";
		try
		{
			if(isValue(time1))
			{
				if(isValue(time2))
				{
					if(time1.length()==8 )
					{
						if(time2.length()==8)
						{
							if((time1.substring(2,3).equalsIgnoreCase(":")) &&  (time1.substring(5,6).equalsIgnoreCase(":")))
							{
								if((time2.substring(2,3).equalsIgnoreCase(":")) &&  (time2.substring(5,6).equalsIgnoreCase(":")))
								{
									if(time1.matches("[0-9 :]+"))
									{
										if(time2.matches("[0-9 :]+"))
										{
											hours=convertNum(time1.substring(0,2));
											hours1=convertNum(time2.substring(0,2));
											minutes=convertNum(time1.substring(3,5));
											minutes1=convertNum(time2.substring(3,5));
											seconds=convertNum(time1.substring(6,8));
											seconds1=convertNum(time2.substring(6,8));
											if(hours <= 23)
											{
												if(minutes<=59)
												{
													if(seconds<=59)
													{
														if(hours1<=23 )
														{
															if(minutes1<=59)
															{
																if(seconds1<=59)
																{
																	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
																	date1 = (Date)sdf.parse(time1);
																	date2=(Date)sdf.parse(time2);
																	difference = date2.getTime() - date1.getTime();
																	seconds= difference / 1000 %60;
																	minutes=difference / (60*1000) % 60;
																	hours=difference / (60*60*1000) % 24;
																	l_ers=+hours+":"+minutes+":"+seconds;
																}
																else
																{
																	l_ers="#Invalid seconds " +seconds1+ ",seconds range from 0 to 59";
																}
															}
															else
															{
																l_ers="#Invalid minutes " +minutes1+ ",minutes range from 0 to 59";
															}
														}
														else
														{
															l_ers="#Invalid hours " +hours1+ ",hours range from 0 to 23";
														}
													}
													else
													{
														l_ers="#Invalid seconds " +seconds+ ",seconds range from 0 to 59";
													}
												}
												else
												{
													l_ers="#Invalid minutes " +minutes+ ",minutes range from 0 to 59";
												}
											}
											else
											{
												l_ers="#Invalid hours " +hours+ ",hours range from 0 to 23";
											}
										}
										else
										{
											l_ers="#Time2 "  +time2+ " should be numeric only";
										}
									}
									else
									{
										l_ers="#Time1 "  +time1+ " should be numeric only";
									}
								}
								else
								{
									l_ers="#Time2 "  +time2+ " is incompatible with format HH:mm:ss";
								}
							}
							else
							{
								l_ers="#Time1 "  +time1+ " is incompatible with format HH:mm:ss";
							}
						}
						else
						{
							l_ers="#Time2 "  +time2+ " is incompatible with format HH:mm:ss";
						}
					}
					else
					{
						l_ers="#Time1 "  +time1+ " is incompatible with format HH:mm:ss";
					}
				}
				else
				{
					l_ers="#Time2 should not be empty";
				}
			}
			else
			{
				l_ers="#Time1 should not be empty";
			}
		}
		catch (ParseException e)
		{
		}
		return l_ers;
	}
	public String pass_fail(String actual,String expected)
	{
		String l_ers="";
		if (actual.trim().equalsIgnoreCase(expected.trim()))
		{
			l_ers="pass";
		}
		else
		{
			l_ers="fail";
		}
		return l_ers;
	}
	
	public static void main(String args[])
	{
		Date_library db_obj=new Date_library();
		System.out.println(db_obj.current_Time());
	}
	
}

