/*
 * ====================================================================
 */

package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;
import com.webfiche.checkpoint.util.*;

public final class CalendarDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance Variables
	private String moduleName	= "> CalendarDetail.: ";
	private String actionFlag	= "";
	private String accessFlag	= "";
	private String dbUsed		= "";
	private String bankId		= "";
	private String hdYearCurr	= "";
	private String hdYear		= "";
	private String hdCurr		= "";
	private String holidate_00	= "";
	private String holidate_01	= "";
	private String holidate_02	= "";
	private String holidate_03	= "";
	private String holidate_04	= "";
	private String holidate_05	= "";
	private String holidate_06	= "";
	private String holidate_07	= "";
	private String holidate_08	= "";
	private String holidate_09	= "";
	private String holidate_10	= "";
	private String holidate_11	= "";
	private String holidate_12	= "";
	private String holidate_13	= "";
	private String holidate_14	= "";
	private String holidate_15	= "";
	private String holidate_16	= "";
	private String holidate_17	= "";
	private String holidate_18	= "";
	private String holidate_19	= "";
	private String dateArray[]	= { null, null, null, null, null, null, null,
									null, null, null, null, null, null, null, 
									null, null, null, null,	null, null };
	private String dateArray_o[]	= { null, null, null, null, null, null, null,
										null, null, null, null, null, null, null, 
										null, null, null, null,	null, null };
	private String hd_last_modified	= "";
	private String hd_mod_user		= "";
	private String hd_mod_func		= "";
	private HashMap<String, String> hd_modparam = new HashMap<String, String>();
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
	//
	// Methods
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							moduleName + logMsg);
	}
	public void clearErrors() {
		errorVec.clear();
	}
	public void clearDateArray() {
		for (int idx = 0; idx < 20; idx++) {
			this.dateArray[idx] = "";
		}
		hdYear		= "";
		hdCurr		= "";
		holidate_00	= "";
		holidate_01	= "";
		holidate_02	= "";
		holidate_03	= "";
		holidate_04	= "";
		holidate_05	= "";
		holidate_06	= "";
		holidate_07	= "";
		holidate_08	= "";
		holidate_09	= "";
		holidate_10	= "";
		holidate_11	= "";
		holidate_12	= "";
		holidate_13	= "";
		holidate_14	= "";
		holidate_15	= "";
		holidate_16	= "";
		holidate_17	= "";
		holidate_18	= "";
		holidate_19	= "";
	}
	public Vector<Vector<String>> getErrorVec() {
		return this.errorVec;
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	/*
	 * public void setErrorVec (String error) { this.errorVec.add(error); }
	 */
	// Get and set actioFlag
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String action_flag) {
		this.actionFlag = action_flag;
	}
	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
	}
	// Get and Set Database used
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	// Get and set year & curr
	public String getHdYearCurr() {
		return (this.hdYearCurr);
	}
	public void setHdYearCurr(String hd_YearCurr) {
		this.hdYearCurr = hd_YearCurr;
	}
	// Get and set year
	public String getHdYear() {
		return (this.hdYear);
	}
	public void setHdYear(String hd_Year) {
		this.hdYear = hd_Year;
	}
	// Get and set the Currency
	public String getHdCurr() {
		return (this.hdCurr);
	}
	public void setHdCurr(String hd_Curr) {
		this.hdCurr = hd_Curr.toUpperCase().trim();
	}
	//
	public String getHolidate_00() {
		return (this.dateArray[0]);
	}
	public void setHolidate_00(String h_date) {
		this.holidate_00 = h_date;
	}
	//
	public String getHolidate_01() {
		return (this.dateArray[1]);
	}
	public void setHolidate_01(String h_date) {
		this.holidate_01 = h_date;
	}
	//
	public String getHolidate_02() {
		return (this.dateArray[2]);
	}
	public void setHolidate_02(String h_date) {
		this.holidate_02 = h_date;
	}
	//
	public String getHolidate_03() {
		return (this.dateArray[3]);
	}
	public void setHolidate_03(String h_date) {
		this.holidate_03 = h_date;
	}
	//
	public String getHolidate_04() {
		return (this.dateArray[4]);
	}
	public void setHolidate_04(String h_date) {
		this.holidate_04 = h_date;
	}
	//
	public String getHolidate_05() {
		return (this.dateArray[5]);
	}
	public void setHolidate_05(String h_date) {
		this.holidate_05 = h_date;
	}
	//
	public String getHolidate_06() {
		return (this.dateArray[6]);
	}
	public void setHolidate_06(String h_date) {
		this.holidate_06 = h_date;
	}
	//
	public String getHolidate_07() {
		return (this.dateArray[7]);
	}
	public void setHolidate_07(String h_date) {
		this.holidate_07 = h_date;
	}
	//
	public String getHolidate_08() {
		return (this.dateArray[8]);
	}
	public void setHolidate_08(String h_date) {
		this.holidate_08 = h_date;
	}
	//
	public String getHolidate_09() {
		return (this.dateArray[9]);
	}
	public void setHolidate_09(String h_date) {
		this.holidate_09 = h_date;
	}
	//
	public String getHolidate_10() {
		return (this.dateArray[10]);
	}
	public void setHolidate_10(String h_date) {
		this.holidate_10 = h_date;
	}
	//
	public String getHolidate_11() {
		return (this.dateArray[11]);
	}
	public void setHolidate_11(String h_date) {
		this.holidate_11 = h_date;
	}
	//
	public String getHolidate_12() {
		return (this.dateArray[12]);
	}
	public void setHolidate_12(String h_date) {
		this.holidate_12 = h_date;
	}
	//
	public String getHolidate_13() {
		return (this.dateArray[13]);
	}
	public void setHolidate_13(String h_date) {
		this.holidate_13 = h_date;
	}
	//
	public String getHolidate_14() {
		return (this.dateArray[14]);
	}
	public void setHolidate_14(String h_date) {
		this.holidate_14 = h_date;
	}
	//
	public String getHolidate_15() {
		return (this.dateArray[15]);
	}
	public void setHolidate_15(String h_date) {
		this.holidate_15 = h_date;
	}
	//
	public String getHolidate_16() {
		return (this.dateArray[16]);
	}
	public void setHolidate_16(String h_date) {
		this.holidate_16 = h_date;
	}
	//
	public String getHolidate_17() {
		return (this.dateArray[17]);
	}
	public void setHolidate_17(String h_date) {
		this.holidate_17 = h_date;
	}
	//
	public String getHolidate_18() {
		return (this.dateArray[18]);
	}
	public void setHolidate_18(String h_date) {
		this.holidate_18 = h_date;
	}
	//
	public String getHolidate_19() {
		return (this.dateArray[19]);
	}
	public void setHolidate_19(String h_date) {
		this.holidate_19 = h_date;
	}
	public void updateDateArray() {
		moduleName = "> CalendarDetail.updateDateArray: ";
		this.dateArray[0] = this.holidate_00;
		// PrintLog(this.dateArray[0]);
		this.dateArray[1] = this.holidate_01;
		this.dateArray[2] = this.holidate_02;
		this.dateArray[3] = this.holidate_03;
		// PrintLog(his.dateArray[3]);
		this.dateArray[4] = this.holidate_04;
		this.dateArray[5] = this.holidate_05;
		this.dateArray[6] = this.holidate_06;
		this.dateArray[7] = this.holidate_07;
		// PrintLog(this.dateArray[7]);
		this.dateArray[8] = this.holidate_08;
		this.dateArray[9] = this.holidate_09;
		this.dateArray[10] = this.holidate_10;
		this.dateArray[11] = this.holidate_11;
		this.dateArray[12] = this.holidate_12;
		// PrintLog(this.dateArray[12]);
		this.dateArray[13] = this.holidate_13;
		this.dateArray[14] = this.holidate_14;
		this.dateArray[15] = this.holidate_15;
		this.dateArray[16] = this.holidate_16;
		// PrintLog(this.dateArray[16]);
		this.dateArray[17] = this.holidate_17;
		this.dateArray[18] = this.holidate_18;
		this.dateArray[19] = this.holidate_19;
		// for (int idx=0; idx < 20; idx++) {
		// if (this.dateArray[idx] != null) {
		// PrintLog(this.dateArray[idx]);
		// }
		// }
	}
	//
	public void updateDates() {
		moduleName = "> CalendarDetail.updateDates: ";
		this.holidate_00 = this.dateArray[0];
		// PrintLog("HOLIUTIL Dates "+this.holidate_00);
		this.holidate_01 = this.dateArray[1];
		this.holidate_02 = this.dateArray[2];
		this.holidate_03 = this.dateArray[3];
		this.holidate_04 = this.dateArray[4];
		this.holidate_05 = this.dateArray[5];
		this.holidate_06 = this.dateArray[6];
		this.holidate_07 = this.dateArray[7];
		this.holidate_08 = this.dateArray[8];
		this.holidate_09 = this.dateArray[9];
		this.holidate_10 = this.dateArray[10];
		this.holidate_11 = this.dateArray[11];
		this.holidate_12 = this.dateArray[12];
		this.holidate_13 = this.dateArray[13];
		this.holidate_14 = this.dateArray[14];
		this.holidate_15 = this.dateArray[15];
		this.holidate_16 = this.dateArray[16];
		this.holidate_17 = this.dateArray[17];
		this.holidate_18 = this.dateArray[18];
		this.holidate_19 = this.dateArray[19];
	}
	//
	public String getDateArray(int index) {
		if (index < 20) {
			return (this.dateArray[index]);
		} else {
			return (null);
		}
	}
	public void setDateArray(int index, String h_date) {
		if (index < 20) {
			this.dateArray[index] = h_date;
		}
	}
	//
	public String getDateArray_o(int index) {
		if (index < 20) {
			return (this.dateArray_o[index]);
		} else {
			return (null);
		}
	}
	public void setDateArray_o(int index, String h_date) {
		if (index < 20) {
			this.dateArray_o[index] = h_date;
		}
	}
	//
	public String[] getDateArray() {
		return (this.dateArray);
	}
	public void setDateArray(String[] date_array) {
		this.dateArray = date_array;
	}
	//
	public String[] getDateArray_o() {
		return (this.dateArray_o);
	}
	public void setDateArray_o(String[] date_array) {
		this.dateArray_o = date_array;
	}
	//
	public String getHd_last_modified() {
		return (this.hd_last_modified);
	}
	public void setHd_last_modified(String last_modified) {
		this.hd_last_modified = last_modified;
	}
	//
	public String getHd_mod_user() {
		return (this.hd_mod_user);
	}
	public void setHd_mod_user(String mod_user) {
		this.hd_mod_user = mod_user;
	}
	//
	public String getHd_mod_func() {
		return (this.hd_mod_func);
	}
	public void setHd_mod_func(String mod_func) {
		this.hd_mod_func = mod_func;
	}
	// Used to populate the date array from the DB row of 1 to 20 dates
	public void setHoliday_dates(String hd_dates) {
		moduleName = "> CalendarDetail.setHoliday_dates: ";
		// PrintLog("HOLIUTIL Dates "+hd_dates);
		int ctr = 0;
		String hdDates = hd_dates;
		String temp_date = "";
		while (hdDates.length() > 0) {
			temp_date = (hdDates.substring(0, 2) + "/" + hdDates
					.substring(2, 4));
			// temp_date = "/"+hdDates.substring(0,2)+
			// "/"+hdDates.substring(2,4);
			// dateArray[ctr] = hdYear+temp_date;
			dateArray[ctr] = temp_date + "/" + hdYear;
			// PrintLog("HOLIUTIL Dates "+dateArray[ctr]);
			hdDates = hdDates.substring(4, hdDates.length());
			ctr++;
		}
	}
	public String getHoliday_dates() {
		moduleName = "> CalendarDetail.getHoliday_dates: ";
		int ctr = 0;
		String temp_date = "";
		for (ctr = 0; ctr < dateArray.length; ctr++) {
			if (dateArray[ctr] != null) {
				if (!dateArray[ctr].equals("")) {
					temp_date += dateArray[ctr].substring(0, 2)
							+ dateArray[ctr].substring(3, 5);
					// PrintLog(temp_date);
				}
			}
		}
		return (temp_date);
	}
	public void sort_holidays() {
		moduleName = "> CalendarDetail.sort_holidays: ";
		int date_yyyy = 0;
		int date_mm = 0;
		int date_dd = 0;
		String[] dArray = { "", "", "" };
		// PrintLog("In Sort Dates ");
		TreeSet<String> holidays_sorted = new TreeSet<String>();
		int i = 0;
		// move all holidays to TreeSet. Here they are sorted and duplicates
		// eliminated
		for (i = 0; i < 20; i++) {
			// Only select holidays for the selected year eliminate others.
			if (this.dateArray[i] != null) {
				if (!this.dateArray[i].equals("")) {
					// Check if the date is entered in mm/dd/yy format
					if (this.dateArray[i].indexOf("/") > 0) {
						dArray = this.dateArray[i].split("/");
						for (int y = 0; y < 3; y++) {
							if (dArray[y].length() < 2)
								dArray[y] = "0" + dArray[y];
						}
						this.dateArray[i] = dArray[0] + "/" + dArray[1] + "/"
								+ dArray[2];
						// PrintLog("Dates present dArray.length "+dArray.length);
						if (dArray.length > 2) {
							// if
							// (this.dateArray[i].substring(6,10).equals(this.hdYear))
							// {
							// date_yyyy =
							// Integer.parseInt(this.dateArray[i].substring(6,10));
							// date_mm =
							// Integer.parseInt(this.dateArray[i].substring(0,2));
							// date_dd =
							// Integer.parseInt(this.dateArray[i].substring(3,5));
							date_mm = Integer.parseInt(dArray[0]);
							date_dd = Integer.parseInt(dArray[1]);
							date_yyyy = Integer.parseInt(dArray[2]);
							dArray[0] = "";
							dArray[1] = "";
							dArray[2] = "";
							// PrintLog("Dates present "+i+" >"+this.dateArray[i]+"<  "+this.hdYear);
							// Extra validation to make sure the user has not
							// entered
							// a date manually instead of the date picker.
							// Manual entry
							// is available only for removing a holiday from the
							// list.
							if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == true) {
								if (ValiDate.IsWeekEnd(date_mm, date_dd,
										date_yyyy) == false) {
									// PrintLog("Dates present "+i+" >"+this.dateArray[i]+"<  "+this.hdYear);
									holidays_sorted.add(this.dateArray[i]);
								}
							}
						}
					}
					dateArray[i] = "";
				}
			}
		}
		//
		// Move the sorted unique dates back to the dateArray
		i = 0;
		// PrintLog("Entering While ");
		Iterator<String> HD_iter = holidays_sorted.iterator();
		while (HD_iter.hasNext()) {
			this.dateArray[i] = (String) HD_iter.next();
			// PrintLog("Sort Dates "+this.dateArray[i]);
			// PrintLog("Sort Dates "+HD_iter.next());
			i++;
		}
	}
	public HashMap<String, String> getHd_modparam() {
		return (this.hd_modparam);
	}
	public void setHd_modparam() {
		this.hd_modparam.put("holidays_year", hdYear);
		this.hd_modparam.put("holidays_currency", hdCurr);
	}
	public void setHolidays_make_copy() {
		// moduleName = "> CalendarDetail.setHolidays_make_copy: ";
		// PrintLog("Saving "+this.dateArray.length+" Dates");
		for (int idx = 0; idx < 20; idx++) {
			this.dateArray_o[idx] = this.dateArray[idx];
		}
	}
	//
	public int CheckForChanges() {
		moduleName = "> CalendarDetail.CheckForChanges: ";
		int data_changed = 0;
		clearErrors();
		try {
			for (int idx = 0; idx < 20; idx++) {
				if (this.dateArray[idx] == null) {
					if (this.dateArray_o[idx] != null) {
						data_changed = 1;
					}
				} else {
					if (this.dateArray_o[idx] == null) {
						data_changed = 1;
					} else if (!this.dateArray[idx]
							.equals(this.dateArray_o[idx])) {
						data_changed = 1;
					}
				}
				PrintLog("Dates Old > " + dateArray_o[idx] + "<  NEW >"
						+ dateArray[idx] + "<");
			}
		} catch (Throwable t) {
			PrintLog("Exception " + t);
		}
		PrintLog("Data Changed " + data_changed);
		sort_holidays();
		if (data_changed == 0) {
			setErrorVec("holidays_year", "info.nomods");
		}
		return (data_changed);
	}
	public boolean NewHoliFieldsValid() {
		moduleName = "> CalendarDetail.NewHoliFieldsValid: ";
		PrintLog("IN New Holidays");
		// clearErrors();
		DateFormat format = DateFormat.getInstance();
		;
		// ----------------------------------------------------------------------------------
		// GregorianCalendar calendar = new GregorianCalendar(1973, 0, 18, 3,
		// 45, 50);
		// format = DateFormat.getInstance();
		// System.out.println("Default locale format gives: " +
		// format.format(calendar.getTime()));
		// ----------------------------------------------------------------------------------
		// calendar = new GregorianCalendar(1973, 0, 18, 3, 45, 50);
		// format = new SimpleDateFormat("E M d hh:mm:ss z yyyyMMdd");
		// System.out.println("Customized format gives: " +
		// format.format(calendar.getTime()));
		GregorianCalendar calendar = new GregorianCalendar();
		format = new SimpleDateFormat("yyyyMMdd");
		// int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		String todays_date = format.format(calendar.getTime());
		String thisYear = todays_date.substring(0, 4);
		// String thisDay = todays_date.substring(6,8);
		if (this.hdYear.compareTo(thisYear) < 0) {
			setErrorVec("Year", "error.field.invalid");
			return (false);
		}
		sort_holidays();
		PrintLog(this.dateArray.length + "");
		if (this.dateArray[0].equals("")) {
			setErrorVec("Holiday", "error.min1entry");
			return (false);
		}
		return (true);
	}
}
