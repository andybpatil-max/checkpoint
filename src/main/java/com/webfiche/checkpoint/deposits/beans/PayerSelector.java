package com.webfiche.checkpoint.deposits.beans;

import java.io.*;
import java.util.*;

//import econtroller.deposits.beans.*;

public final class PayerSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID	= 1L;
	// -- Instance Variables
	private String className			= "> PayerSelector.";
	private String moduleName			= "";
	private String param				= "";
	// private int recIndex	= 0;
	private int rowCount				= 0;
	private int fromRow					= 0;
	private int toRow					= 0;
	private int showRows				= 0;
	private String actionFlag			= "";
	private String accessFlag			= "";
	private String dbUsed				= "";
	private String dbTable				= "";
	private String applDate				= "";
	private String bankId				= "";
	private String prodId				= "";
	private String defCurr				= "";
	private String userId;
	private String payerAcct			= "";
	private String payerAba				= "";
	//
	private String payerAcctFrom		= "";
	private String payerAcctTo			= "";
	private String payerAbaFrom			= "";
	private String payerAbaTo			= "";
	private String payerName			= "";
	private String payerCountry			= "";
	private String payerSource			= "";
	private String payerBank			= "";
	private String payerLogDate			= "";
	private String payerLogUser			= "";
	//
	private String payerAcctFrom_o		= "";
	private String payerAcctTo_o		= "";
	private String payerAbaFrom_o		= "";
	private String payerAbaTo_o			= "";
	private String payerName_o			= "";
	private String payerCountry_o		= "";
	private String payerSource_o		= "";
	private String payerBank_o			= "";
	private String payerLogDate_o		= "";
	private String payerLogUser_o		= "";
	//
	private Vector<Vector<String>> errorVec		= new Vector<Vector<String>>();
	private TreeMap<String, String> payersList	= new TreeMap<String, String>();
	private ArrayList<String> payerAcctList		= new ArrayList<String>();
	private ArrayList<String> payerAbaList		= new ArrayList<String>();
	private ArrayList<String> payerDateList		= new ArrayList<String>();
	private ArrayList<String> payerUserList		= new ArrayList<String>();
	private PayerDetail modifyRow				= new PayerDetail();
	private ArrayList<PayerDetail> payerRows	= new ArrayList<PayerDetail>();
	//
	// Here deifne variables for controlling the number of checks displayed.
	// Use the checksPerView constant defined in web.xml
	//
	private int rowStart		= 0;
	private int rowEnd			= 20;
	private int rowsDisplayed	= 20;
	private String rowsDispStr	= "";
	// ----------------------------------------------------------- Properties
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() + 
							className + moduleName + userId + " - " + logMsg);
	}
	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}
	//
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair	= new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public void clearPayerAbaList() {
		this.payerAbaList.clear();
	}
	//
	public void clearPayerAcctList() {
		this.payerAcctList.clear();
	}
	//
	public void clearPayerDateList() {
		this.payerDateList.clear();
	}
	//
	public void clearPayerUserList() {
		this.payerUserList.clear();
	}
	//
	public void clearPayersList() {
		this.payersList.clear();
	}
	//
	public void clearPayerRows() {
		this.payerRows.clear();
		this.rowCount	= 0;
	}
	//
	public int getRowCount() {
		return (this.rowCount);
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	//
	public int getFromRow() {
		return (this.fromRow);
	}
	public void setFromRow(int fromRow) {
		this.fromRow = fromRow;
	}
	//
	public int getToRow() {
		return (this.toRow);
	}
	public void setToRow(int toRow) {
		this.toRow = toRow;
	}
	//
	public int getShowRows() {
		return (this.showRows);
	}
	public void setShowRows(int showRows) {
		this.showRows = showRows;
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
	public void setAccessFlag(String accessFlag) {
		this.accessFlag = accessFlag;
	}
	//
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getDbTable() {
		return (this.dbTable);
	}
	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}
	//
	public String getApplDate() {
		return (this.applDate);
	}
	public void setAppl_date(String applDate) {
		this.applDate = applDate;
	}
	//
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	//
	public String getProdId() {
		return (this.prodId);
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	//
	public String getUserId() {
		return (this.userId);
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//
	public String getPayerAba() {
		return payerAba;
	}
	public void setPayerAba(String payerAba) {
		this.payerAba = payerAba;
	}
	//
	public String getPayerAcct() {
		return payerAcct;
	}
	public void setPayerAcct(String payerAcct) {
		this.payerAcct = payerAcct;
	}
	//
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public String getPayerAcctFrom() {
		return payerAcctFrom;
	}
	public void setPayerAcctFrom(String payerAcctFrom) {
		this.payerAcctFrom = payerAcctFrom;
	}
	//
	public String getPayerAcctTo() {
		return payerAcctTo;
	}
	public void setPayerAcctTo(String payerAcctTo) {
		this.payerAcctTo = payerAcctTo;
	}
	//
	public String getPayerAbaFrom() {
		return payerAbaFrom;
	}
	public void setPayerAbaFrom(String payerAbaFrom) {
		this.payerAbaFrom = payerAbaFrom;
	}
	//
	public String getPayerAbaTo() {
		return payerAbaTo;
	}
	public void setPayerAbaTo(String payerAbaTo) {
		this.payerAbaTo = payerAbaTo;
	}
	//
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	//
	public String getPayerCountry() {
		return payerCountry;
	}
	public void setPayerCountry(String payerCountry) {
		this.payerCountry = payerCountry;
	}
	//
	public String getPayerSource() {
		return payerSource;
	}
	public void setPayerSource(String payerSource) {
		this.payerSource = payerSource;
	}
	//
	public String getPayerBank() {
		return payerBank;
	}
	public void setPayerBank(String payerBank) {
		this.payerBank = payerBank;
	}
	//
	public String getPayerLogDate() {
		return payerLogDate;
	}
	public void setPayerLogDate(String payerLogDate) {
		this.payerLogDate = payerLogDate;
	}
	//
	public String getPayerLogUser() {
		return payerLogUser;
	}
	public void setPayerLogUser(String payerLogUser) {
		this.payerLogUser = payerLogUser;
	}
	//
	public String getPayerAcctFrom_o() {
		return payerAcctFrom_o;
	}
	public void setPayerAcctFrom_o(String payerAcctFrom_o) {
		this.payerAcctFrom_o = payerAcctFrom_o;
	}
	//
	public String getPayerAcctTo_o() {
		return payerAcctTo_o;
	}
	public void setPayerAcctTo_o(String payerAcctTo_o) {
		this.payerAcctTo_o = payerAcctTo_o;
	}
	//
	public String getPayerAbaFrom_o() {
		return payerAbaFrom_o;
	}
	public void setPayerAbaFrom_o(String payerAbaFrom_o) {
		this.payerAbaFrom_o = payerAbaFrom_o;
	}
	//
	public String getPayerAbaTo_o() {
		return payerAbaTo_o;
	}
	public void setPayerAbaTo_o(String payerAbaTo_o) {
		this.payerAbaTo_o = payerAbaTo_o;
	}
	//
	public String getPayerName_o() {
		return payerName_o;
	}
	public void setPayerName_o(String payerName_o) {
		this.payerName_o = payerName_o;
	}
	//
	public String getPayerCountry_o() {
		return payerCountry_o;
	}
	public void setPayerCountry_o(String payerCountry_o) {
		this.payerCountry_o = payerCountry_o;
	}
	//
	public String getPayerSource_o() {
		return payerSource_o;
	}
	public void setPayerSource_o(String payerSource_o) {
		this.payerSource_o = payerSource_o;
	}
	//
	public String getPayerBank_o() {
		return payerBank_o;
	}
	public void setPayerBank_o(String payerBank_o) {
		this.payerBank_o = payerBank_o;
	}
	//
	public String getPayerLogDate_o() {
		return payerLogDate_o;
	}
	public void setPayerLogDate_o(String payerLogDate_o) {
		this.payerLogDate_o = payerLogDate_o;
	}
	//
	public String getPayerLogUser_o() {
		return payerLogUser_o;
	}
	public void setPayerLogUser_o(String payerLogUser_o) {
		this.payerLogUser_o = payerLogUser_o;
	}
	//
	public ArrayList<String> getPayerAcctList() {
		return (this.payerAcctList);
	}
	public void setPayerAcctList(ArrayList<String> payerAcctList) {
		Iterator<String> accts = payerAcctList.iterator();
		clearPayerAcctList();
		int n = 0;
		while (accts.hasNext()) {
			this.payerAcctList.add(n, (String) accts.next());
			n++;
		}
		if (n == 0) {
			this.payerAcctList.add(" ");
		}
	}
	//
	public ArrayList<String> getPayerAbaList() {
		return (this.payerAbaList);
	}
	public void setPayerAbaList(ArrayList<String> payerAbaList) {
		Iterator<String> abas = payerAbaList.iterator();
		clearPayerAbaList();
		int n = 0;
		while (abas.hasNext()) {
			this.payerAbaList.add(n, (String) abas.next());
			n++;
		}
		if (n == 0) {
			this.payerAbaList.add(" ");
		}
	}
	//
	public ArrayList<String> getPayerDateList() {
		return (this.payerDateList);
	}
	public void setPayerDateList(ArrayList<String> payerDateList) {
		Iterator<String> dates = payerDateList.iterator();
		clearPayerDateList();
		int n = 0;
		while (dates.hasNext()) {
			this.payerDateList.add(n, (String) dates.next());
			//PrintLog("Date: "+dates.next()+"");
			n++;
		}
		if (n == 0) {
			this.payerDateList.add(" ");
		}
	}
	//
	public ArrayList<String> getPayerUserList() {
		return (this.payerUserList);
	}
	public void setPayerUserList(ArrayList<String> payerUserList) {
		Iterator<String> users = payerUserList.iterator();
		clearPayerUserList();
		int n = 0;
		while (users.hasNext()) {
			this.payerUserList.add(n, (String) users.next());
			n++;
		}
		if (n == 0) {
			this.payerUserList.add(" ");
		}
	}
	//
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	public int getRowStart() {
		return (this.rowStart);
	}
	//
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	public int getRowEnd() {
		return (this.rowEnd);
	}
	//
	public void setRowsDisplayed(int rowsDisplayed) {
		moduleName = "setRowsDisplayed: ";
		PrintLog("rowsDisplayed: " + rowsDisplayed);
		if (rowsDisplayed <= 500) {
			this.rowsDisplayed = rowsDisplayed;
		} else {
			this.rowsDisplayed = 500;
		}
		if (this.rowsDisplayed > this.rowCount) {
			this.rowsDisplayed = this.rowCount;
		}
	}
	public int getRowsDisplayed() {
		return (this.rowsDisplayed);
	}
	//
	public void setRowsDispStr(String rowsDispStr) {
		moduleName = "setRowsDispStr: ";
		//PrintLog("rowsDispStr: " + rowsDispStr);
		if (Integer.parseInt(rowsDispStr) <= 500) {
			this.rowsDispStr = rowsDispStr;
		} else {
			this.rowsDispStr = "500";
		}
		this.rowsDisplayed = Integer.parseInt(this.rowsDispStr);
		if (this.rowsDisplayed > this.rowCount) {
			this.rowsDisplayed = this.rowCount;
			this.rowsDispStr = this.rowCount + "";
		}
		//PrintLog("rowsDispStr: " + this.rowsDispStr);
	}
	public String getRowsDispStr() {
		return (this.rowsDisplayed + "");
	}
	//
	public void setRowSEC(int rowStart, int rowEnd, int rowCount) {
		this.rowStart		= rowStart;
		this.rowEnd			= rowEnd;
		this.rowsDisplayed	= rowCount;
	}
	public void InitRowSEC() {
		clearErrors();
		this.rowStart		= 0;
		this.rowEnd			= 20;
		this.rowsDisplayed	= 20;
	}
	//
	public String getPayersValue(String key) {
		return ((String) this.payersList.get(key));
	}
	public TreeMap<String, String> getPayersList() {
		return (this.payersList);
	}
	public void setPayersList(TreeMap<String, String> payersList) {
		this.payersList = payersList;
	}
	//
	public boolean payerExists(String payerAcct) {
		return (payersList.containsKey(payerAcct));
	}
	//
	public void SaveParams() {
		payerAcctFrom_o		= payerAcctFrom;
		payerAcctTo_o		= payerAcctTo;
		payerAbaFrom_o		= payerAbaFrom;
		payerAbaTo_o		= payerAbaTo;
		payerName_o			= payerName;
		payerCountry_o		= payerCountry;
		payerSource_o		= payerSource;
		payerBank_o			= payerBank;
		payerLogDate_o		= payerLogDate;
		payerLogUser_o		= payerLogUser;
	}
	public void RestoreParams() {
		payerAcctFrom		= payerAcctFrom_o;
		payerAcctTo			= payerAcctTo_o;
		payerAbaFrom		= payerAbaFrom_o;
		payerAbaTo			= payerAbaTo_o;
		payerName			= payerName_o;
		payerCountry		= payerCountry_o;
		payerSource			= payerSource_o;
		payerBank			= payerBank_o;
		payerLogDate		= payerLogDate_o;
		payerLogUser		= payerLogUser_o;
	}
	public void ShowParams() {
		moduleName = "ShowParams: ";
		PrintLog("payerAcctFrom\t\t" + payerAcctFrom);
		PrintLog("payerAcctTo\t\t" + payerAcctTo);
		PrintLog("payerAbaFrom\t\t" + payerAbaFrom);
		PrintLog("payerAbaTo\t\t" + payerAbaTo);
		PrintLog("payerName\t\t" + payerName);
		PrintLog("payerCountry\t\t" + payerCountry);
		PrintLog("payerSource\t\t" + payerSource);
		PrintLog("payerBank\t\t" + payerBank);
		PrintLog("payerLogDate\t\t" + payerLogDate);
		PrintLog("payerLogUser\t\t" + payerLogUser);
	}
	public void InitParams() {
		payerAcctFrom		= "ALL";
		payerAcctTo			= "NONE";
		payerAbaFrom		= "";
		payerAbaTo			= "";
		payerName			= "";
		payerCountry		= "";
		payerSource			= "";
		payerBank			= "";
		payerLogDate		= "";
		payerLogUser		= "";
	}
	//
	public PayerDetail[] getPayerRows() {
		// return (this.accountrows);
		// (accounts) {
		PayerDetail results[] = new PayerDetail[payerRows.size()];
		Iterator<PayerDetail> payers = payerRows.iterator();
		int n = 0;
		while (payers.hasNext()) {
			results[n++] = (PayerDetail) payers.next();
		}
		return (results);
	}
	//
	public PayerDetail getArow() {
		PayerDetail results	= new PayerDetail();
		Iterator<PayerDetail> accts	= payerRows.iterator();
		results	= (PayerDetail) accts.next();
		return (results);
	}
	//
	public void setPayerRows(ArrayList<PayerDetail> payerRows) {		
		Iterator<PayerDetail> rows = payerRows.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.payerRows.add(n, (PayerDetail) rows.next());
			n++;
		}
		rowCount = n;
		PrintLog("Num Rows="+n);
	}
	//
	public PayerDetail getArow(int recIndex) {
		PayerDetail results = new PayerDetail();
		// Iterator acct = checkrows.iterator();
		// this.recIndex = recIndex;
		results = (PayerDetail) payerRows.get(recIndex);
		return (results);
	}
	//
	public void setModifyRow(PayerDetail modifyRow) {
		this.modifyRow = modifyRow;
	}
	//
	public PayerDetail getModifyRow() {
		return (this.modifyRow);
	}
	//
	// Param Setters and Getters
	//
	public void clearNulls() {
		// account_num_from = (account_num_from == null) ? "" :
		payerAcctFrom		= (payerAcctFrom == null) ? "" : payerAcctFrom;
		payerAcctTo			= (payerAcctTo == null) ? "" : payerAcctTo;
		payerAbaFrom		= (payerAbaFrom == null) ? "" : payerAbaFrom;
		payerAbaTo			= (payerAbaTo == null) ? "" : payerAbaTo;
		payerName			= (payerName == null) ? "" : payerName;
		payerCountry		= (payerCountry == null) ? "" : payerCountry;
		payerSource			= (payerSource == null) ? "" : payerSource;
		payerBank			= (payerBank == null) ? "" : payerBank;
		payerLogDate		= (payerLogDate == null) ? "" : payerLogDate;
		payerLogUser		= (payerLogUser == null) ? "" : payerLogUser;
	}
	//
	public String GetParams() {
		moduleName = "GetParams: ";
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		param				= "";
		/*
		private String payerAcctFrom		= "";
		private String payerAcctTo			= "";
		private String payerAbaFrom			= "";
		private String payerAbaTo			= "";
		private String payerName			= "";
		private String payerCountry			= "";
		private String payerBank			= "";
		private String payerLogDate			= "";
		private String payerLogUser			= "";
		 */
		String fromAcct		= "";
		String toAcct		= "";
		String fromAba		= "";
		String toAba		= "";
		String name			= "";
		String country		= "";
		String source		= "";
		String bank			= "";
		String accParam		= "";
		String abaParam		= "";
		String nameParam	= "";
		String countryParam	= "";
		String sourceParam	= "";
		String bankParam	= "";
		//
		clearNulls();
		if (!payerAcctFrom.equals("ALL")) {
			fromAcct	= payerAcctFrom.trim();
		}
		if (!payerAcctTo.equals("NONE")) {
			toAcct		= payerAcctTo.trim();
		}
		if (!payerAbaFrom.equals("ALL")) {
			fromAba		= payerAbaFrom.trim();
		}
		if (!payerAbaTo.equals("NONE")) {
			toAba		= payerAbaTo.trim();
		}
		if (!payerName.equals("")) {
			name		= payerName.trim();
		}
		if (!payerCountry.equals("")) {
			country		= payerCountry.trim();
		}
		if (!payerSource.equals("")) {
			source		= payerSource.trim();
		}
		if (!payerBank.equals("")) {
			bank		= payerBank.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!fromAcct.equals("")) {
			accParam = "payeracct='" + fromAcct + "' ";
		}
		if (!toAcct.equals("")) {
			if (!accParam.equals("")) {
				accParam = " payeracct between '" + fromAcct + "' AND '"
						+ toAcct + "'";
			} else {
				accParam = "payerAcct='" + toAcct + "' ";
			}
		}
		//
		if (!fromAba.equals("")) {
			abaParam = "payeraba='" + fromAba + "' ";
		}
		if (!toAba.equals("")) {
			if (!abaParam.equals("")) {
				abaParam = " (payeraba between '" + fromAba + "' AND '"
						+ toAba + "') ";
			} else {
				abaParam = "(payerAba='" + toAba + "') ";
			}
		}
		//
		if (!name.equals("")) {
			nameParam = "(payername like '%" + name + "%' OR " +
					"payername like '%" + name.toLowerCase() + "%' OR " +
					"payername like '%" + name.toUpperCase() + "%') ";
		}
		if (!country.equals("")) {
			countryParam = "payercountry='" + country + "' ";
		}
		if (!source.equals("")) {
			sourceParam = "payerSource='" + source + "' ";
		}
		if (!bank.equals("")) {
			bankParam = "payerbank='" + bank + "' ";
		}
		//
		if (!accParam.equals("")) {
			param = accParam;
		}
		if (!param.equals("")) {
			if (!abaParam.equals(""))
				param += " AND " + abaParam;
		} else {
			param = abaParam;
		}
		if (!param.equals("")) {
			if (!nameParam.equals(""))
				param += " AND " + nameParam;
		} else {
			param = nameParam;
		}
		if (!param.equals("")) {
			if (!countryParam.equals(""))
				param += " AND " + countryParam;
		} else {
			param = countryParam;
		}
		if (!param.equals("")) {
			if (!sourceParam.equals(""))
				param += " AND " + sourceParam;
		} else {
			param = sourceParam;
		}
		if (!param.equals("")) {
			if (!bankParam.equals(""))
				param += " AND " + bankParam;
		} else {
			param = bankParam;
		}
		//
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY payeraba, payeracct";
		PrintLog(" Param->" + param);
		return (param);
	}
	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName			= "GetLogParams: ";
		param				= "";
		String dateParam	= "";
		String userParam	= "";
		//
		clearNulls();
		GetParams();
		param	= param.substring(0, param.indexOf("ORDER BY")-1) + " ";
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!payerLogDate.equals("ALL")) {
			dateParam = "modtime LIKE '%" + payerLogDate + "%' ";
		}
		if (!payerLogUser.equals("ALL")) {
			userParam = "moduser='" + payerLogUser + "' ";
		}
		//
		if (!param.equals("")) {
			if (!dateParam.equals("")) {
				param	+= " AND " + dateParam;
			}
		}
		if (!param.equals("")) {
			if (!userParam.equals("")) {
				param	+= " AND " + userParam;
			}
		}
		if (!param.equals("")) {
			param = " " + param;
		}
		param += " ORDER BY payeraba, payeracct, modtime DESC";
		//PrintLog(" Param->" + param);
		return (param);
	}
	//
	public void SetNextRows() {
		if (this.rowEnd < rowCount) {
			this.rowStart = this.rowEnd;
			this.rowEnd = this.rowEnd + this.rowsDisplayed;
			if (this.rowEnd > this.rowCount) {
				this.rowEnd = this.rowCount;
			}
		}
	}
	//
	public void SetPrevRows() {
		if (this.rowStart >= this.rowsDisplayed) {
			this.rowEnd = this.rowStart;
			this.rowStart = this.rowStart - this.rowsDisplayed;
			if (this.rowStart < 0) {
				this.rowStart = 0;
			}
		} else {
			if (this.rowStart < this.rowsDisplayed) {
				this.rowStart = 0;
				this.rowEnd = this.rowsDisplayed;
			}
		}
	}
	//
	protected void finalize() {
		//
	}
}
