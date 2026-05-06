package com.webfiche.checkpoint.util;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;
import java.io.*;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import com.webfiche.checkpoint.sysadmin.beans.*;

//import com.webfiche.checkpoint.sysadmin.service.*;

public final class ValiDate {
	public static boolean DateAllNumeric(String dateString) {
		Pattern pattern	= Pattern.compile("\\d{8}");
		Matcher matcher	= pattern.matcher(dateString);
		if (matcher.matches()) {
			System.out.println(java.util.Calendar.getInstance().getTime()+
					"> ValiDateDate.DateAllNumeric: Date True "+dateString);
			return true;
		} else {
			System.out.println(java.util.Calendar.getInstance().getTime()+
					"> ValiDateDate.DateAllNumeric: Date : "+dateString);
			return false;
		}
	}
	//
	public static boolean CheckDate(int month, int day, int year) {
		// String moduleName = "> ValiDate.CheckDate: ";
		java.util.GregorianCalendar gcDate = new java.util.GregorianCalendar();
		gcDate.clear();
		gcDate.setLenient(false);
		gcDate.set(year, month - 1, day);
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"> "+month+"/"+day+"/"+year+"<");
		try {
			gcDate.get(Calendar.DATE);
		} catch (Exception e) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+moduleName+e.toString());
			return false;
		}
		return true;
	}
	//
	public static boolean IsWeekEnd(int month, int day, int year) {
		// String moduleName = "> ValiDate.IsWeekEnd: ";
		java.util.GregorianCalendar gcDate = new java.util.GregorianCalendar();
		gcDate.clear();
		gcDate.setLenient(false);
		gcDate.set(year, month - 1, day);
		int day_of_week = gcDate.get(Calendar.DAY_OF_WEEK);
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> Week Day "+day_of_week+"> "+month+"/"+day+"/"+year+"<");
		// sun=1, Mon=2, Tue=3, Wed=4, Thu=5, Fri=6, Sat=7
		//
		if ((day_of_week < 2) || (day_of_week > 6)) {
			return true;
		}
		return false;
	}
	//
	public static int DayOfWeek(int month, int day, int year) {
		//String moduleName = "> ValiDate.DayOfWeek: ";
		java.util.GregorianCalendar gcDate = new java.util.GregorianCalendar();
		gcDate.clear();
		gcDate.setLenient(false);
		gcDate.set(year, month - 1, day);
		int day_of_week = gcDate.get(Calendar.DAY_OF_WEEK);
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> Week Day "+day_of_week+"> "+month+"/"+day+"/"+year+"<");
		// sun=1, Mon=2, Tue=3, Wed=4, Thu=5, Fri=6, Sat=7
		//
		return day_of_week;
	}
	//
	public static String getTodays_date() {
		// String moduleName = "> ValiDate.getTodays_date: ";
		java.util.GregorianCalendar now = new java.util.GregorianCalendar();
		int date_iy = now.get(Calendar.YEAR);
		String date_y = date_iy + "";
		int date_im = now.get(Calendar.MONTH) + 1;
		String date_m = date_im + "";
		int date_id = now.get(Calendar.DATE);
		String date_d = date_id + "";
		if (date_m.length() == 1)
			date_m = '0' + date_m;
		if (date_d.length() == 1)
			date_d = '0' + date_d;
		String date_today = date_y + date_m + date_d;
		return (date_today);
	}
	//
	public static String getMonth(int month) {
		String[] months = { "", "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		// String moduleName = "> ValiDate.getMonth: ";
		return (months[month]);
	}
	//
	public static String addDays(int addays) {
		// Pass the number of days + or - to add or subtract. The routine adds
		// or subtracts the days passed to todays date and returns a week day in
		// yyyymmdd form.
		//
		//String moduleName = "> ValiDate.CheckDate: ";
		// String ret_date = "";
		//int dayCounter	= 0;
		DateFormat format				= DateFormat.getInstance();
		format							= new SimpleDateFormat("yyyyMMdd");
		java.util.GregorianCalendar now	= new java.util.GregorianCalendar();
		now.setLenient(false);
		/**/
		System.out.println(java.util.Calendar.getInstance().getTime()+
				"> ValiDate.addDays: "+addays+" "+
				format.format(now.getTime()));
		//while (dayCounter < addays) {
			//now.add(Calendar.DAY_OF_YEAR, 1);
			now.add(Calendar.DAY_OF_YEAR, addays);
			System.out.println(java.util.Calendar.getInstance().getTime()+
					"> ValiDate.addDays: "+addays+" "+
					format.format(now.getTime()));
			int day_of_week	= now.get(Calendar.DAY_OF_WEEK);
			while ((day_of_week < 2) || (day_of_week > 6)) {
				now.add(Calendar.DAY_OF_YEAR, 1);
				day_of_week	= now.get(Calendar.DAY_OF_WEEK);
			}
			//dayCounter++;
			//}
		System.out.println(java.util.Calendar.getInstance().getTime()+
				"> ValiDate.addDays: "+addays+" "+
				format.format(now.getTime()));
		return (format.format(now.getTime()));
	}
	//
	public static String addDays(String date_yyyymmdd, int addays) {
		// Pass a date and the number of days + or - to add or subtract. The
		// routine adds or subtracts the days to the date passed and returns
		// a week day in yyyymmdd form.
		//
		// String moduleName = "> ValiDate.addDays: ";
		int dayCounter	= 0;
		int date_yyyy	= Integer.parseInt(date_yyyymmdd.substring(0, 4));
		int date_mm		= Integer.parseInt(date_yyyymmdd.substring(4, 6));
		int date_dd		= Integer.parseInt(date_yyyymmdd.substring(6, 8));
		// String ret_date = "";
		DateFormat format	= DateFormat.getInstance();
		//
		format			= new SimpleDateFormat("yyyyMMdd");
		java.util.GregorianCalendar now	= new java.util.GregorianCalendar();
		now.setLenient(false);
		now.set(date_yyyy, date_mm - 1, date_dd);
		while (dayCounter < addays) {
			now.add(Calendar.DAY_OF_YEAR, 1);
			int day_of_week	= now.get(Calendar.DAY_OF_WEEK);
			while ((day_of_week < 2) || (day_of_week > 6)) {
				now.add(Calendar.DAY_OF_YEAR, 1);
				day_of_week	= now.get(Calendar.DAY_OF_WEEK);
			}
			dayCounter++;
		}
		return (format.format(now.getTime()));
	}
	//
	public static String subtractDays(String date_yyyymmdd, int subtractDays) {
		// Pass a date and the number of days + or - to add or subtract. The
		// routine adds or subtracts the days to the date passed and returns
		// a week day in yyyymmdd form.
		//
		// String moduleName = "> ValiDate.addDays: ";
		//int dayCounter	= 0;
		int date_yyyy	= Integer.parseInt(date_yyyymmdd.substring(0, 4));
		int date_mm		= Integer.parseInt(date_yyyymmdd.substring(4, 6));
		int date_dd		= Integer.parseInt(date_yyyymmdd.substring(6, 8));
		// String ret_date = "";
		DateFormat format	= DateFormat.getInstance();
		//
		format			= new SimpleDateFormat("yyyyMMdd");
		java.util.GregorianCalendar now	= new java.util.GregorianCalendar();
		now.setLenient(false);
		now.set(date_yyyy, date_mm - 1, date_dd);
		now.add(Calendar.DAY_OF_YEAR, (subtractDays*-1));
		int day_of_week	= now.get(Calendar.DAY_OF_WEEK);
		while ((day_of_week < 2) || (day_of_week > 6)) {
			now.add(Calendar.DAY_OF_YEAR, -1);
			day_of_week	= now.get(Calendar.DAY_OF_WEEK);
		}
		System.out.println("Date is: "+format.format(now.getTime()));
		return (format.format(now.getTime()));
	}
	//
	public static int diffDates(String date0_yyyymmdd, String date1_yyyymmdd) {
		// Pass a date and the number of days + or - to add or subtract. The
		// routine adds or subtracts the days to the date passed and returns
		//  a date in yyyymmdd form.
		//
		//String moduleName = "> ValiDate.diffDates: ";
		/*
		System.out.println(java.util.Calendar.getInstance().getTime() +
							moduleName + "date0:" + date0_yyyymmdd + " date1:" +
							date1_yyyymmdd);
		*/
		int yy_0 = Integer.parseInt(date0_yyyymmdd.substring(0, 4));
		int mm_0 = Integer.parseInt(date0_yyyymmdd.substring(4, 6));
		int dd_0 = Integer.parseInt(date0_yyyymmdd.substring(6, 8));
		int yy_1 = Integer.parseInt(date1_yyyymmdd.substring(0, 4));
		int mm_1 = Integer.parseInt(date1_yyyymmdd.substring(4, 6));
		int dd_1 = Integer.parseInt(date1_yyyymmdd.substring(6, 8));
		//
		// Set date 0
		//
		java.util.GregorianCalendar d_0 = new java.util.GregorianCalendar();
		d_0.setLenient(false);
		d_0.set(yy_0, mm_0 - 1, dd_0);
		//
		// Set date 1
		//
		java.util.GregorianCalendar d_1 = new java.util.GregorianCalendar();
		d_1.setLenient(false);
		d_1.set(yy_1, mm_1 - 1, dd_1);
		//
		// Get the difference divided by milliseconds in a day
		//
		int days = (int) ((d_1.getTime().getTime() - d_0.getTime().getTime()) / (24 * 60 * 60 * 1000));
		if (days < 0)
			days = days * (-1);
		// System.out.println("Date diff is: "+days);
		return (days);
	}
	//
	public static String getPrevBusinessDay(int month, int day, int year,
			ArrayList<String> holidays) {
		// String moduleName = "> ValiDate.getPrevBusinessDay: ";
		int ip_mm		= 0;
		int ip_dd		= 0;
		int ip_yy		= 0;
		String sp_mm	= "";
		String sp_dd	= "";
		String sp_yy	= "";
		String sp_date	= "";
		// String temp	= "";
		boolean maybeHoliday;
		boolean match_found;
		java.util.GregorianCalendar now = new java.util.GregorianCalendar();
		now.clear();
		now.setLenient(false);
		now.set(year, month - 1, day);
		maybeHoliday	= true;
		while (maybeHoliday == true) {
			if (now.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				now.add(Calendar.DAY_OF_MONTH, -3);
			} else if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				now.add(Calendar.DAY_OF_MONTH, -2);
			} else {
				now.add(Calendar.DAY_OF_MONTH, -1);
			}
			ip_yy	= now.get(Calendar.YEAR);
			sp_yy	= ip_yy + "";
			ip_mm	= now.get(Calendar.MONTH) + 1;
			sp_mm	= ip_mm + "";
			if (sp_mm.length() == 1) {
				sp_mm	= '0' + sp_mm;
			}
			ip_dd	= now.get(Calendar.DATE);
			sp_dd	= ip_dd + "";
			if (sp_dd.length() == 1) {
				sp_dd	= '0' + sp_dd;
			}
			sp_date	= sp_yy + sp_mm + sp_dd;
			Iterator<String> h_days = holidays.iterator();
			match_found	= false;
			while (h_days.hasNext()) {
				if (sp_date.equals((String) h_days.next())) {
					match_found	= true;
					break;
				}
			}
			if (match_found == false) {
				maybeHoliday	= false;
			}
		}
		return (sp_date);
	}
	//
	public static String getNextBusinessDay(int month, int day, int year,
			ArrayList<String> holidays) {
		//String moduleName = "> ValiDate.getNextBusinessDay: ";
		int in_mm		= 0;
		int in_dd		= 0;
		int in_yy		= 0;
		int idx1		= 0;
		// int idx2		= 0;
		String sn_mm	= "";
		String sn_dd	= "";
		String sn_yy	= "";
		String sn_date	= "";
		String temp		= "";
		boolean maybeHoliday;
		boolean match_found;
		java.util.GregorianCalendar now = new java.util.GregorianCalendar();
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"Input Date " + year + "/" + month + "/" + day);
		now.clear();
		now.setLenient(false);
		now.set(year, month - 1, day);
		in_yy	= now.get(Calendar.YEAR);
		in_yy	= in_yy + 1;
		temp	= in_yy + "0101";
		//
		// This takes into account the new year day of the next year
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"Temp " + temp);
		//
		holidays.add(temp);
		// Iterator<String> h_days = holidays.iterator();
		match_found	= false;
		// while (h_days.hasNext()) {
		/*
		System.out.println(java.util.Calendar.getInstance().getTime() +
							moduleName + "Holidays array has " + holidays.size() +
							" elements");
		*/
		// }
		maybeHoliday	= true;
		while (maybeHoliday == true) {
			if (now.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// moduleName+"Found Friday");
				now.add(Calendar.DAY_OF_MONTH, 3);
			} else if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// moduleName+"Found Saturday");
				now.add(Calendar.DAY_OF_MONTH, 2);
			} else {
				now.add(Calendar.DAY_OF_MONTH, 1);
			}
			in_yy	= now.get(Calendar.YEAR);
			sn_yy	= in_yy + "";
			in_mm	= now.get(Calendar.MONTH) + 1;
			sn_mm	= in_mm + "";
			if (sn_mm.length() == 1) {
				sn_mm	= '0' + sn_mm;
			}
			in_dd	= now.get(Calendar.DATE);
			sn_dd	= in_dd + "";
			if (sn_dd.length() == 1) {
				sn_dd	= '0' + sn_dd;
			}
			sn_date	= sn_yy + sn_mm + sn_dd;
			/*
			System.out.println(java.util.Calendar.getInstance().getTime() +
								moduleName + "sn_date " + ">" + sn_date + "<");
			*/
			// Iterator h_days = holidays.iterator();
			match_found = false;
			// while (h_days.hasNext()) {
			// temp	= (String) h_days.next();
			for (idx1 = 0; idx1 < holidays.size(); idx1++) {
				temp	= holidays.get(idx1);
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// "> ValiDate Dates 2"+">"+ temp +"<");
				if (sn_date.equals(temp)) {
					match_found	= true;
					break;
				}
			}
			if (match_found == false) {
				maybeHoliday	= false;
			}
		}
		return (sn_date);
	}
	//
	public static String getPriorDates(int month, int day, int year, int months) {
		// This method does not care about the weekends. It is primarily
		// used to get a 3 month 6 month or a year prior date for use in
		// purging the log files.
		// String moduleName = "> ValiDate.getPriorDates: ";
		int ip_yy; // i=int, p=prior
		int ip_mm;
		int ip_dd;
		String sp_yy; // s=string, p=prior
		String sp_mm;
		String sp_dd;
		String prior_date;
		int myMonth	= month;
		int myYear	= year;
		myMonth		-= months;
		java.util.GregorianCalendar priorDate = new java.util.GregorianCalendar();
		priorDate.clear();
		priorDate.setLenient(true);
		priorDate.set(myYear, myMonth - 1, day);
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> ValiDate Dates "+"> "+month+"/"+day+"/"+year+"<");
		try {
			priorDate.get(Calendar.DATE);
		} catch (Exception e) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+e.toString());
			return ("");
		}
		ip_yy	= priorDate.get(Calendar.YEAR);
		sp_yy	= ip_yy + "";
		ip_mm	= priorDate.get(Calendar.MONTH) + 1;
		sp_mm	= ip_mm + "";
		if (sp_mm.length() == 1) {
			sp_mm	= '0' + sp_mm;
		}
		ip_dd	= priorDate.get(Calendar.DATE);
		sp_dd	= ip_dd + "";
		if (sp_dd.length() == 1) {
			sp_dd	= '0' + sp_dd;
		}
		prior_date	 = sp_yy + sp_mm + sp_dd;
		return (prior_date);
	}
	//
	public static String getFutureDates(int month, int day, int year, int months) {
		// This method does care about the weekends. It is primarily
		// used to get a future dates by months setting StopPay expriry date
		// purging the log files. Can be enhanced for other purposes.
		// String moduleName = "> ValiDate.getFutureDates: ";
		int if_yy; // i=int, f=future
		int if_mm;
		int if_dd;
		int myMonth	= month;
		int myYear	= year;
		String sf_yy; // s=string, f=future
		String sf_mm;
		String sf_dd;
		ArrayList<String> dummy = new ArrayList<String>();
		String future_date;
		dummy.add(" ");
		myMonth	+= months;
		java.util.GregorianCalendar futureDate = new java.util.GregorianCalendar();
		futureDate.clear();
		futureDate.setLenient(true);
		futureDate.set(myYear, myMonth - 1, day);
		System.out.println(java.util.Calendar.getInstance().getTime()+
					"> ValiDate Dates "+"> "+month+"/"+day+"/"+year+"<");
		try {
			futureDate.get(Calendar.DATE);
		} catch (Exception e) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+e.toString());
			return ("");
		}
		if_yy	= futureDate.get(Calendar.YEAR);
		sf_yy	= if_yy + "";
		if_mm	= futureDate.get(Calendar.MONTH) + 1;
		sf_mm	= if_mm + "";
		if (sf_mm.length() == 1) {
			sf_mm	= '0' + sf_mm;
		}
		if_dd	= futureDate.get(Calendar.DATE);
		sf_dd	= if_dd + "";
		if (sf_dd.length() == 1) {
			sf_dd	= '0' + sf_dd;
		}
		if (IsWeekEnd(if_mm, if_dd, if_yy) == true) {
			future_date	= getNextBusinessDay(if_mm, if_dd, if_yy, dummy);
		} else {
			future_date	= sf_yy + sf_mm + sf_dd;
		}
		System.out.println(java.util.Calendar.getInstance().getTime()+
							"Future Date: "+future_date);
		return (future_date);
	}
	//
	public static void getHolidays(Connection dbConn, ControlDetail ctlDetail)
			throws Exception {
		//
		//int rowCount	= 0;
		//String moduleName	= "> ValiDate.getHolidays: ";
		String oneHoliday		= "";
		String temp				= "";
		String holi_year_curr	= "";
		//
		ResultSet result;
		Statement statement;
		StringBuffer sql	= new StringBuffer();
		temp				= ctlDetail.getNextBizDate();
		//holi_year_curr	= temp.substring(0,4) + ctlDetail.getDef_currency();
		holi_year_curr		= temp.substring(0, 4) + ctlDetail.getDefCurr();
		sql.setLength(0);
		sql.append("SELECT HOLIDAYS_YEAR_CURR, HOLIDAYS_DATES, ");
		sql.append("HOLIDAYS_LAST_MODIFIED, ");
		sql.append("HOLIDAYS_MOD_USER, HOLIDAYS_MOD_FUNC ");
		sql.append("FROM holidays ");
		sql.append("WHERE HOLIDAYS_YEAR_CURR='" + holi_year_curr + "' ");
		statement	= dbConn.createStatement();
		result		= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("HOLIDAYS_DATES");
		}
		statement.close();
		result.close();
		while (temp.length() > 0) {
			oneHoliday	= holi_year_curr.substring(0, 4) + temp.substring(0, 4);
			ctlDetail.setHolidays(oneHoliday);
			temp		= temp.substring(4, temp.length());
		}
		//
	}
	//
    public static TreeMap<String, TreeMap<String, String[]>> buildCal1(String rootDir) {
		/* 
		 * Based on todays date calculate the dates and day-of-week 15 years past from current year.
		 * If today=20131207 then gate dates from 19980101 thru 20131207
		 */
		TreeMap<String, TreeMap<String, String[]>> dateMap	= new TreeMap<String, TreeMap<String, String[]>>();
		//String moduleName	= "> ValiDate.buildCal: ";
		File dirEntry		= null;
		String temp			= "";
		String yyyyMMcur	= "";
		String yyyyMMpre	= "";
		String day			= "";
		String month		= "";
		String searchDir	= "";
		DateFormat format	= DateFormat.getInstance();;
		format				= new SimpleDateFormat("yyyyMMdd");
		java.util.GregorianCalendar today	= new java.util.GregorianCalendar();
		String dateToday	= format.format(today.getTime());
		int date_yyyy		= Integer.parseInt(dateToday.substring(0,4))-15;
		int date_mm			= 1;
		int date_dd			= 1;
		//System.out.println(moduleName+date_yyyymmdd);
		java.util.GregorianCalendar now		= new java.util.GregorianCalendar();
		//String[] dow		= new String[2];
		now.setLenient(false);
		now.set(date_yyyy, date_mm-1, date_dd);
		//System.out.println(moduleName+format.format(now.getTime()));
		int day_of_week		= now.get(Calendar.DAY_OF_WEEK);
		while (!format.format(now.getTime()).equals(dateToday)) {
    	    //System.out.println(moduleName+"date: "+format.format(now.getTime())+" day_of_week inloop "+day_of_week);
    	    TreeMap<String, String[]> dateMap1	= new TreeMap<String,String[]>();
    	    String[] dow	= new String[3];
    	    day_of_week		= now.get(Calendar.DAY_OF_WEEK);
    	    temp			= format.format(now.getTime());
    	    yyyyMMcur		= temp.substring(0,6);
    	    yyyyMMpre		= temp.substring(0,6);
    	    while (yyyyMMcur.equals(yyyyMMpre)) {
    	    	day_of_week	= now.get(Calendar.DAY_OF_WEEK);
    	    	day			= temp.substring(6,8);
				month		= temp.substring(4,6);
    	    	dow[0]		= month;
    	    	dow[1]		= day_of_week + "";
    	    	searchDir	= rootDir+temp.substring(0,4)+"/"+temp.substring(0,6)+"/"+temp+"/nyaaaa01";
    	    	dirEntry	= new File(searchDir);
    	    	dow[2]		= "";
    	    	if (dirEntry.exists()) {
    	    		dow[2]	= searchDir;
    	    	}
    	    	dateMap1.put(day, dow);
    	    	now.add(Calendar.DAY_OF_YEAR, 1);
    	    	temp		= format.format(now.getTime());
    	    	yyyyMMpre	= yyyyMMcur;
    	    	yyyyMMcur	= temp.substring(0,6);
    	    	if (temp.equals(dateToday)) 
    	    		break;
    	    	dow		= new String[3];
    	    }
    	    //System.out.println("Year Mon: "+yyyyMMc);
    	    dateMap.put(yyyyMMpre, dateMap1);
    	}
    	String[] dow= new String[3];
    	TreeMap<String, String[]> dateMap1	= new TreeMap<String,String[]>();
    	day_of_week	= now.get(Calendar.DAY_OF_WEEK);
    	temp		= format.format(now.getTime());
    	yyyyMMcur	= temp.substring(0,6);
		day			= temp.substring(6,8);
		month			= temp.substring(4,6);
		dow[0]		= month;
		dow[1]		= day_of_week + "";
		searchDir	= rootDir+temp.substring(0,4)+"/"+temp.substring(0,6)+"/"+temp+"/nyaaaa01";
		dirEntry	= new File(searchDir);
		dow[2]		= "";
		if (dirEntry.exists()) {
			dow[2]	= searchDir;
		}
    	dateMap1.put(day, dow);
    	dateMap.put(yyyyMMcur, dateMap1);
    	//System.out.println(moduleName+format.format(now.getTime()));
    	return dateMap;
    }
	//
	public static TreeMap<String, String[]> buildCal(String rootDir) {
		/*
		 * Based on todays date calculate the dates and day-of-week 15 years past from current year.
		 * If today=20131207 then gate dates from 19980101 thru 20131207
		 * Key = YYYYMMDD
		 * String array of Values 
		 * 		[0]	= day
		 * 		[1]	= Month
		 * 		[2]	= String of Integer Day (removes leading zero)
		 * 		[3]	= Day of Week
		 * 		[4]	= Folder Spec
		 * 		[5]	= File Name (for Access by file)
		 */
		TreeMap<String, String[]> dateMap	= new TreeMap<String,String[]>();
		//String moduleName	= "> ValiDate.buildCal: ";
		File dirEntry		= null;
		String folderBr		= rootDir.substring(rootDir.length()-3,rootDir.length()-1);
		//System.out.println(moduleName+"folder branch:"+folderBr);
		String temp			= "";
		String day			= "";
		int dayInt			= 0;
		String month		= "";
		String searchDir	= "";
		DateFormat format	= DateFormat.getInstance();;
		format				= new SimpleDateFormat("yyyyMMdd");
		java.util.GregorianCalendar today	= new java.util.GregorianCalendar();
		today.set(Calendar.DATE, today.getActualMaximum(Calendar.DATE));
		String dateToday	= format.format(today.getTime());
		int date_yyyy		= Integer.parseInt(dateToday.substring(0,4)) - 15;
		int date_mm			= 1;
		int date_dd			= 1;
		//System.out.println(moduleName+date_yyyymmdd);
		java.util.GregorianCalendar now		= new java.util.GregorianCalendar();
		now.setLenient(false);
		now.set(date_yyyy, date_mm-1, date_dd);
		/*
		System.out.println(java.util.Calendar.getInstance().getTime()+
							moduleName+format.format(now.getTime()));
		*/
		int day_of_week		= now.get(Calendar.DAY_OF_WEEK);
		//System.out.println(moduleName+"day_of_week "+day_of_week);
		while (!format.format(now.getTime()).equals(dateToday)) {
		    //System.out.println(moduleName+"date: "+format.format(now.getTime())+" day_of_week inloop "+day_of_week);
		    String[] dow	= new String[6];
		    day_of_week		= now.get(Calendar.DAY_OF_WEEK);
		    temp			= format.format(now.getTime());
		    day				= temp.substring(6,8);
		    dayInt			= Integer.parseInt(day);
		    month			= temp.substring(4,6);
		    dow[0]			= day;
		    dow[1]			= month;
		    dow[2]			= dayInt + "";
		    dow[3]			= day_of_week + "";
		    searchDir		= rootDir+temp.substring(0,4)+"/"+temp.substring(0,6)+"/"+temp+
		    						"/" + folderBr + "aaaa01/";
		    dirEntry		= new File(searchDir);
		    dow[4]			= "";
			//System.out.println(moduleName+"Search Dir:"+searchDir);
		    if (dirEntry.exists()) {
				//System.out.println(moduleName+"Dir Exists: "+searchDir);
		    	dow[4]		= searchDir;
		    }
		    dow[5]			= "";
		    dateMap.put(temp, dow);
		    now.add(Calendar.DAY_OF_YEAR, 1);
		}
		/*
		 * Add today
		 */
	    String[] dow	= new String[6];
	    day_of_week		= now.get(Calendar.DAY_OF_WEEK);
	    temp			= format.format(now.getTime());
	    day				= temp.substring(6,8);
	    dayInt			= Integer.parseInt(day);
	    month			= temp.substring(4,6);
	    dow[0]			= day;
	    dow[1]			= month;
	    dow[2]			= dayInt + "";
	    dow[3]			= day_of_week + "";
	    searchDir		= rootDir+temp.substring(0,4)+"/"+temp.substring(0,6)+"/"+temp+
	    					"/" + folderBr + "aaaa01/";
	    dirEntry		= new File(searchDir);
	    dow[4]			= "";
	    if (dirEntry.exists()) {
	    	dow[4]	= searchDir;
	    }
	    dow[5]			= "";
	    dateMap.put(temp, dow);
	    /*
	    System.out.println(java.util.Calendar.getInstance().getTime()+
	    				   moduleName+"dateMap size: "+dateMap.size());
		*/
		return dateMap;
	}
}
