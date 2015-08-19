package ua.core.util.dates;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * Creation date: (2002/06/04 6:00:54 PM)
 */
public class DateUtils {

	public static String	CLASS_NAME	= DateUtils.class.getName();

	public static long		MILLISECONDS_PER_DAY = 86400000;
	
		
	/**
	 * 
	 * 
	 * Creation date: (2002/12/20 3:07:18 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return int
	 * @param date1 java.util.Date
	 * @param date2 java.util.Date
	 */
	public static int compareIgnoreTime (Date date1, Date date2) {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
	    Date				convDate1;		// Date 1 without the time component.
	    Date				convDate2;		// Date 2 without the time component.
	
		int					compareValue;    	
	
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		convDate1 = stripTime (date1);
		convDate2 = stripTime (date2);
	
		compareValue = convDate1.compareTo (convDate2);
	
		return compareValue;
	
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/09/18 6:16:56 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @param oDate java.util.Date
	 * @param lDays long
	 */
	public static void dayAdd(Date oDate, long lDays) {
	
		// Add the days...
	
		// 1 day in milliseconds = 1000 * 60 * 6 * 24 = 86400000
		
		oDate.setTime (oDate.getTime() + lDays * 86400000L);
		
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/09/18 4:11:37 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Date
	 * @param oDate java.util.Date
	 * @param iDay int
	 */
	public static void daySet (Date oDate, int iDay) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		Calendar	calendar	= null;
	
		long		lDateValue;
	
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		calendar = getCalendar();
		
		calendar.setTime	(oDate);
		calendar.set		(Calendar.DATE, iDay);
	
		lDateValue = calendar.getTime().getTime();
	
		oDate.setTime (lDateValue);
	
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/09/19 12:10:01 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Calendar
	 */
	private static GregorianCalendar getCalendar() {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		GregorianCalendar newCalendar	= null;
	
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		// Private method to return the calendar object
		// with the correct settings.
	
		newCalendar = new GregorianCalendar(2000,0,1,0,0);
		newCalendar.setLenient (false);
		
		return newCalendar;
	
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/09/18 11:17:37 AM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Date
	 * @param iYear int
	 * @param iMonth int
	 * @param iDay int
	 */
	public static Date getDate (int iYear, int iMonth, int iDay) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		Calendar	calendar	= null;
	
		Date		oDate		= null;
	
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		calendar = getCalendar();
		calendar.set (iYear, iMonth - 1, iDay);
	
		oDate = calendar.getTime();
		
		return oDate;
	
	}
	
	
	/**
	 * Returns the day (Monday / Tuesday...) 
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay (Date date) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		SimpleDateFormat	dateFormat		= null;
		
		String				isoDateString	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
	
		dateFormat		= new SimpleDateFormat ("E");
		isoDateString	= dateFormat.format (date);

		return	isoDateString;
	}

	
	
	public static long getDiffInDays (Date date1, Date date2) {
		
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		long	diffInDays;    	
	
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		diffInDays = (date1.getTime() - date2.getTime()) / MILLISECONDS_PER_DAY;
	
		return diffInDays;
	
	}
	
	/**
	 * Returns the current date in ISO format (YYYY-MM-DD)
	 * 
	 * @return
	 */
	public static String getISODate () {
		
		return getISODate (new Date());
	}
	
	
	/**
	 * Returns the date 
	 * 
	 * @param date
	 * @return
	 */
	public static String getISODate (Date date) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		SimpleDateFormat	dateFormat		= null;
		
		String				isoDateString	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
	
		dateFormat		= new SimpleDateFormat (IDateConst.DATE_FORMAT_ISO);
		isoDateString	= dateFormat.format (date);

		return	isoDateString;
	}
	
	
	/**
	 * Returns the current date in ISO format (YYYY-MM-DD)
	 * 
	 * @return
	 */
	public static String getISODotDate () {
		
		return getISODotDate (new Date());
	}
	
	
	/**
	 * Returns the date 
	 * 
	 * @param date
	 * @return
	 */
	public static String getISODotDate (Date date) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		SimpleDateFormat	dateFormat		= null;
		
		String				isoDateString	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
	
		dateFormat		= new SimpleDateFormat (IDateConst.DATE_FORMAT_ISO_DOT);
		isoDateString	= dateFormat.format (date);

		return	isoDateString;
	}
	
	
	/**
	 * Returns the time in HH:MM format.
	 * 
	 * @param date
	 * @return
	 */
	public static String getTime (Date date) {
		
		// ///////////////////////////////////////////////////////////////
		//   Declarations
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		SimpleDateFormat	dateFormat		= null;
		
		String				isoDateString	= null;


		// ///////////////////////////////////////////////////////////////
		//   Code
		// ///////////////////////////////////////////////////////////////

		/////////////////////////////////
	
		dateFormat		= new SimpleDateFormat ("HH:mm");
		isoDateString	= dateFormat.format (date);

		return	isoDateString;
	}

	
	/**
	 * 
	 * 
	 * Creation date: (2002/09/18 4:11:37 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Date
	 * @param oDate java.util.Date
	 * @param iMonth int
	 */
	public static void monthSet (Date oDate, int iMonth) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		Calendar	calendar	= null;
	
		long		lDateValue;
	
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		calendar = getCalendar();
		
		calendar.setTime	(oDate);
		calendar.set		(Calendar.MONTH, iMonth - 1);
	
		lDateValue = calendar.getTime().getTime();
	
		oDate.setTime (lDateValue);
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/12/20 1:16:08 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Date
	 * @param newDate java.util.Date
	 */
	public static Date stripTime (Date newDate) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		GregorianCalendar	calendar	= null;
		
		int					year;
		int					month;
		int					day;
	
	
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		calendar = getCalendar();
		calendar.setTime (newDate);
	
		year	= calendar.get (Calendar.YEAR);
		month	= calendar.get (Calendar.MONTH) + 1;
		day		= calendar.get (Calendar.DATE);
	
		return getDate (year, month, day);
	
	}
	
	
	/**
	 * 
	 * 
	 * Creation date: (2002/09/18 4:11:37 PM)
	 * 
	 * @param: <|>
	 * @return:
	 * 
	 * @return java.util.Date
	 * @param oDate java.util.Date
	 * @param iYear int
	 */
	public static void yearSet (Date date, int year) {
	
		/////////////////////////////////
		// Declarations:
		/////////////////////////////////
		
		Calendar	calendar	= null;
	
		long		dateValue;
	
		
		/////////////////////////////////
		// Code:
		/////////////////////////////////
	
		calendar = getCalendar();
		
		calendar.setTime	(date);
		calendar.set		(Calendar.YEAR, year);
	
		dateValue = calendar.getTime().getTime();
	
		date.setTime (dateValue);
	}
}
