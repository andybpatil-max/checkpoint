package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class CurrencySelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance Variables
	private String className = "> CurrencySelector.";
	private String moduleName = "";
	private String param = "";
	private String userId = "";
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	private String currFrom = "";
	private String currTo = "";
	private Vector<String> currList = new Vector<String>();
	private ArrayList<CurrencyDetail> currRows = new ArrayList<CurrencyDetail>();
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();

	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
	}

	//
	public void clearCurrList() {
		currList.clear();
	}

	//
	public void clearCurrRows() {
		currRows.clear();
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
	public Vector<String> getCurrList() {
		return this.currList;
	}

	public void setCurrList(String curr) {
		this.currList.add(curr);
	}

	//
	public String getActionFlag() {
		return (this.actionFlag);
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	//
	public String getAccessFlag() {
		return (this.accessFlag);
	}

	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
	}

	//
	public String getDbUsed() {
		return (this.dbUsed);
	}

	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}

	//
	public String getCurrFrom() {
		return (this.currFrom);
	}

	public void setCurrFrom(String currFrom) {
		this.currFrom = currFrom;
	}

	//
	public String getCurrTo() {
		return (this.currTo);
	}

	public void setCurrTo(String currTo) {
		this.currTo = currTo;
	}

	//
	public String getUserId() {
		return (this.userId);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//
	public CurrencyDetail[] getCurrRows() {
		CurrencyDetail results[] = new CurrencyDetail[currRows.size()];
		Iterator<CurrencyDetail> currs = currRows.iterator();
		int n = 0;
		while (currs.hasNext()) {
			results[n++] = (CurrencyDetail) currs.next();
		}
		return (results);
	}

	//
	public CurrencyDetail getArow() {
		CurrencyDetail results = new CurrencyDetail();
		Iterator<CurrencyDetail> currs = currRows.iterator();
		results = (CurrencyDetail) currs.next();
		return (results);
	}

	//
	public void setCurrRows(ArrayList<?> currRow) {
		clearCurrRows();
		Iterator<?> cRows = currRow.iterator();
		while (cRows.hasNext()) {
			this.currRows.add((CurrencyDetail) cRows.next());
		}
	}

	//
	public String GetParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName = "> SysadCurrencyUtil.GetParams: ";
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		//
		String currFrom1	= "";
		String currTo1		= "";
		//
		String currParam	= "";
		String currTemp		= "";
		//
		param				= "";
		//PrintLog("currFrom " + currFrom);
		//PrintLog("currTo " + currTo);
		if (!currFrom.equals("ALL")) {
			currFrom1 = currFrom.trim();
		}
		if (!currTo.equals("NONE")) {
			currTo1 = currTo.trim();
		}
		//
		if ((!currFrom1.equals("")) && (!currTo1.equals(""))) {
			if (currFrom.compareTo(currTo) > 0) {
				currTemp = currFrom;
				currFrom = currTo;
				currTo = currTemp;
			}
		}
		/*
		 * PrintLog("currFrom "+currFrom); PrintLog("currTo "+currTo);
		 */
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!currFrom1.equals("")) {
			if (!currTo1.equals("")) {
				currParam = "CURR_CODE BETWEEN '" + currFrom1 + "' AND '"
						+ currTo1 + "' ";
			} else {
				currParam = "CURR_CODE='" + currFrom1 + "' ";
			}
		}
		PrintLog("currParams " + currParam);
		if (!currParam.equals("")) {
			param = " WHERE " + currParam;
		}
		param += " ORDER BY CURR_CODE";
		PrintLog("Params " + param);
		return (param);
	}

	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName = "> SysadCurrencyUtil.GetLogParams: ";
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String currFrom1	= "";
		String currTo1		= "";
		String currParam	= "";
		String currTemp		= "";
		param				= "";
		if (!currFrom.equals("ALL")) {
			currFrom1 = currFrom.trim();
		}
		if (!currTo.equals("NONE")) {
			currTo1 = currTo.trim();
		}
		if (currFrom1.compareTo(currTo1) > 0) {
			currTemp = currFrom1;
			currFrom1 = currTo1;
			currTo1 = currTemp;
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!currFrom.equals("")) {
			if (!currTo.equals("")) {
				currParam = "CURR_LOG_CODE BETWEEN '" + currFrom + "' AND '"
						+ currTo + "' ";
			} else {
				currParam = "CURRLOGCODE='" + currFrom + "' ";
			}
		}
		if (!currParam.equals("")) {
			param = " WHERE " + currParam;
		}
		param += " ORDER BY CURR_LOG_CODE";
		return (param);
	}
}
