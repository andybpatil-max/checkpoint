/*
 * ====================================================================
 */

package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class CalendarSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -- Instance Variables
	private String className = "> CalendarSelector.";
	private String moduleName = "";
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	private String bankId = "";
	private String param = "";
	private String userId = "";
	private String h_from_year = "";
	private String h_to_year = "";
	private String h_from_curr = "";
	private String h_to_curr = "";
	private String h_from_year_o = "";
	private String h_to_year_o = "";
	private String h_from_curr_o = "";
	private String h_to_curr_o = "";
	//
	private ArrayList<CalendarDetail> calendarrows = new ArrayList<CalendarDetail>();
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
	//
	// private String hd_last_modified = "";
	// private String hd_mod_user = "";
	// private String hd_mod_func = "";

	// private HashMap<?, ?> hd_modparam = new HashMap<Object, Object>();
	private Vector<String> yearList = new Vector<String>();
	private Vector<String> currList = new Vector<String>();

	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + userId + " - " + logMsg);
	}

	//
	public void clearLists() {
		yearList.clear();
		currList.clear();
	}

	//
	public void clearRows() {
		calendarrows.clear();
	}

	//
	public void clearErrors() {
		errorVec.clear();
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

	//
	public String getBankId() {
		return (this.bankId);
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	//
	public String getUserId() {
		return (this.userId);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//
	public void clearCalendarRows() {
		calendarrows.clear();
	}

	// ----------------------------------------------------------- Properties
	public String getActionFlag() {
		return (this.actionFlag);
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}

	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
	}

	public String getDbUsed() {
		return (this.dbUsed);
	}

	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}

	//
	public void saveParams() {
		h_from_year_o = h_from_year;
		h_to_year_o = h_to_year;
		h_from_curr_o = h_from_curr;
		h_to_curr_o = h_to_curr;
	}

	//
	public void restoreParams() {
		h_from_year = h_from_year_o;
		h_to_year = h_to_year_o;
		h_from_curr = h_from_curr_o;
		h_to_curr = h_to_curr_o;
	}

	public String getH_from_year() {
		return (this.h_from_year);
	}

	public void setH_from_year(String h_from_year) {
		// PrintLog(": Set from year "+h_from_year);
		this.h_from_year = h_from_year;
	}

	public String getH_to_year() {
		return (this.h_to_year);
	}

	public void setH_to_year(String h_to_year) {
		this.h_to_year = h_to_year;
	}

	public String getH_from_curr() {
		return (this.h_from_curr);
	}

	public void setH_from_curr(String h_from_curr) {
		this.h_from_curr = h_from_curr;
	}

	public String getH_to_curr() {
		return (this.h_to_curr);
	}

	public void setH_to_curr(String h_to_curr) {
		this.h_to_curr = h_to_curr;
	}

	//
	public Vector<String> getYearList() {
		return this.yearList;
	}

	public void setYearList(String year) {
		// PrintLog(": Set Year "+year);
		this.yearList.add(year);
	}

	//
	public Vector<String> getCurrList() {
		return this.currList;
	}

	public void setCurrList(String curr) {
		// PrintLog(": Set Curr "+curr);
		this.currList.add(curr);
	}

	//
	public CalendarDetail[] getCalendarrows() {
		CalendarDetail results[] = new CalendarDetail[calendarrows.size()];
		Iterator<CalendarDetail> hdays = calendarrows.iterator();
		int n = 0;
		while (hdays.hasNext()) {
			results[n++] = (CalendarDetail) hdays.next();
		}
		return (results);
	}

	public CalendarDetail getArow() {
		CalendarDetail results = new CalendarDetail();
		Iterator<CalendarDetail> hdays = calendarrows.iterator();
		results = (CalendarDetail) hdays.next();
		return (results);
	}

	public void setCalendarrows(ArrayList<?> calendar_row) {
		Iterator<?> rows = calendar_row.iterator();
		while (rows.hasNext()) {
			this.calendarrows.add((CalendarDetail) rows.next());
		}
	}

	//
	public String GetParams() {
		moduleName = "GetParams: ";
		param = "";
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String year_param = "";
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!h_from_year.equals("ALL")) {
			year_param = "holidays_year_curr='" + h_from_year + "' ";
		}
		if (!param.equals("")) {
			if (!year_param.equals(""))
				param += " AND " + year_param;
		} else {
			param = year_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY HOLIDAYS_YEAR_CURR DESC";
		PrintLog("GetParams Param->" + param);
		return (param);
	}

	//
	public String GetLogParams() {
		moduleName = "GetLogParams: ";
		param = "";
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_year = "";
		String to_year = "";
		String from_curr = "";
		String to_curr = "";

		String year_param = "";
		String curr_param = "";

		if (!h_from_year.equals("ALL")) {
			from_year = h_from_year.trim();
		}
		if (!h_to_year.equals("NONE")) {
			to_year = h_to_year.trim();
		}
		if (!h_from_curr.equals("ALL")) {
			from_curr = h_from_curr.trim();
		}
		if (!h_to_curr.equals("NONE")) {
			to_curr = h_to_curr.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_year.equals("")) {
			year_param = "hdlog_year='" + from_year + "' ";
		}
		if (!to_year.equals("")) {
			if (!year_param.equals("")) {
				year_param = " hdlog_year between '" + from_year + "' AND '"
						+ to_year + "'";
			} else {
				year_param = "hdlog_year='" + to_year + "' ";
			}
		}
		if (!from_curr.equals("")) {
			curr_param = "hdlog_curr='" + from_curr + "' ";
		}
		if (!to_curr.equals("")) {
			if (!curr_param.equals("")) {
				curr_param = " hdlog_currency between '" + from_curr
						+ "' AND '" + to_curr + "'";
			} else {
				curr_param = "hdlog_currency='" + to_curr + "' ";
			}
		}
		if (!param.equals("")) {
			if (!year_param.equals(""))
				param += " AND " + year_param;
		} else {
			param = year_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY hdlog_year, hdlog_curr, hdlog_last_modified DESC";
		return (param);
	}
}
