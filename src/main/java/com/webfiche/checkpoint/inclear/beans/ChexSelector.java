/*
 * ====================================================================
 */

package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

public final class ChexSelector implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID  = 1L;
	// -- Instance Variables
	private String	className		= "> ChexSelector.";
	private String	moduleName		= "";
	private String	param			= "";
	private int		detail_count	= 0;
	private int		recIndex		= 0;
	private String	row_Count		= "0";
	private String	detailAmount	= "";
	private String	actionFlag		= "";
	private String	accessFlag		= "";
	private String	imageLookup		= "";
	private String	dbUsed			= "";
	private String	dbTable			= "incl_chex";
	private String	appl_date		= "";
	private String	appl_date_f		= "";
	private String	bankId			= "";
	private String	defCurr			= "";
	private String	allow_eod		= "N";
	private String	eod_status		= "";
	private String	bod_status		= "";
	private String	cod_status		= "";
	private String	ch_from_period	= "";
	private String	ch_to_period	= "";
	private String	ch_from_date	= "";
	private String	ch_to_date		= "";
	private String	ch_from_account = "";
	private String	ch_to_account	= "";
	private String	ch_currency		= "";
	private String	ch_from_check	= "";
	private String	ch_to_check		= "";
	private String	ch_from_amount	= "";
	private String	ch_to_amount	= "";
	private String	ch_check_status = "";
	private String	ch_retstat		= "";
	private String	ch_log_date		= "";
	private String	ch_log_user		= "";
	private String	ch_from_period_o= "";
	private String	ch_to_period_o	= "";
	private String	ch_from_date_o	= "";
	private String	ch_to_date_o	= "";
	private String	ch_from_account_o	= "";
	private String	ch_to_account_o	= "";
	private String	ch_currency_o	= "";
	private String	ch_from_check_o = "";
	private String	ch_to_check_o	= "";
	private String	ch_from_amount_o= "";
	private String	ch_to_amount_o	= "";
	private String	ch_check_status_o	= "";
	private String	ch_retstat_o	= "";
	private String	ch_log_date_o	= "";
	private String	ch_log_user_o	= "";
	private String	summ_total_amount	= "";
	private String	summ_total_checks	= "";
	private ChexDetail modifyRow		= new ChexDetail();
	private ArrayList<ChexDetail>  checkrows	= new ArrayList<ChexDetail>();
	private ArrayList<ChexSummary> summaryrows	= new ArrayList<ChexSummary>();
	private ArrayList<String>	  hist_tables	= new ArrayList<String>();
	private String	imageURL		= "";
	private String	imageDir		= "";
	private String	pdfDir			= "";
	private String	monthStart		= "";
	private String	monthEnd		= "";
	private String	weekStart		= "";
	private String	weekEnd			= "";
	private String	dailyDate		= "";
	private Vector<Vector<String>> errorVec		= new Vector<Vector<String>>();
	private ArrayList<String>	  acctList		= new ArrayList<String>();
	private ArrayList<String>	  dateList		= new ArrayList<String>();
	private ArrayList<String>	  histList		= new ArrayList<String>();
	private ArrayList<String>	  userList		= new ArrayList<String>();
	private TreeMap<String, String> rejReasons	= new TreeMap<String, String>();
	private TreeMap<String, String> retReasons	= new TreeMap<String, String>();
	private TreeMap<String, String> chexStatus	= new TreeMap<String, String>();
	private TreeMap<String, Long>	highCheckNum= new TreeMap <String, Long>(); 
	private TreeMap<String, Integer>monthAverage= new TreeMap <String, Integer>(); 
	//
	// Here deifne variables for controlling the number of checks displayed.
	// Use the checksPerView constant defined in web.xml
	//
	private int		rowStart		= 0;
	private int		rowEnd			= 20;
	private int		rowsDisplayed	= 20;
	private String	rowsDispStr	 	= "";

	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
	}
	//
	public void clearParam() {
		this.param	= "";
	}
	public void clearRows() {
		checkrows.clear();
		summaryrows.clear();
		this.detail_count	= 0;
	}
	public void clearSummary() {
		summaryrows.clear();
	}
	public void clearAcctList() {
		acctList.clear();
	}
	public void clearDateList() {
		dateList.clear();
	}
	public void clearHistList() {
		histList.clear();
	}
	public void clearUserList() {
		userList.clear();
	}
	//
	public int getDetail_count() {
		return (this.detail_count);
	}
	public void setDetail_count(int detail_count) {
		this.detail_count = detail_count;
		//setRowsDisplayed(this.detail_count);
	}
	//
	public int getRecIndex() {
		return (this.recIndex);
	}
	public void setRecIndex(int recIndex) {
		this.recIndex = recIndex;
	}
	//
	public String getRow_Count() {
		return (this.row_Count);
	}
	public void setRow_Count(String row_Count) {
		this.row_Count = row_Count;
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
	public String getImageLookup() {
		return (this.imageLookup);
	}
	public void setImageLookup(String imageLookup) {
		this.imageLookup = imageLookup;
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
	public String getAppl_date_f() {
		return (this.appl_date_f);
	}
	//
	public String getAppl_date() {
		return (this.appl_date);
	}
	// date="112304";
	// new_date=
	// "20"+date.substring(4,6)+date.substring(2,4)+date.substring(0,2);
	public void setAppl_date(String appl_date) {
		//System.out.println(java.util.Calendar.getInstance().getTime() +
		//					"> ChexSelector: Appl_date " + appl_date);
		this.appl_date = appl_date;
		if (!appl_date.equals("")) {
			this.appl_date_f = (appl_date.substring(4, 6) + "/" +
								appl_date.substring(6) + "/" + appl_date.substring(0, 4));
		}
	}
	//
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	//
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public String getAllow_eod() {
		return (this.allow_eod);
	}
	public void setAllow_eod(String allow_eod) {
		this.allow_eod = allow_eod;
	}
	//
	public String getEod_status() {
		return (this.eod_status);
	}
	public void setEod_status(String eod_status) {
		this.eod_status = eod_status;
	}
	//
	public String getBod_status() {
		return (this.bod_status);
	}
	public void setBod_status(String bod_status) {
		this.bod_status = bod_status;
	}
	//
	public String getCod_status() {
		return (this.cod_status);
	}
	public void setCod_status(String cod_status) {
		this.cod_status = cod_status;
	}
	//
	public String getCh_from_period() {
		return (this.ch_from_period);
	}
	public void setCh_from_period(String ch_from_period) {
		this.ch_from_period = ch_from_period;
	}
	//
	public String getCh_to_period() {
		return ((this.ch_to_period == null) ? "" : this.ch_to_period);
	}
	public void setCh_to_period(String ch_to_period) {
		this.ch_to_period = ch_to_period;
	}
	//
	public String getCh_from_date() {
		return (this.ch_from_date);
	}
	public void setCh_from_date(String ch_from_date) {
		this.ch_from_date = ch_from_date;
	}
	//
	public String getCh_to_date() {
		return (this.ch_to_date);
	}
	public void setCh_to_date(String ch_to_date) {
		this.ch_to_date = ch_to_date;
	}
	//
	public String getCh_from_account() {
		return (this.ch_from_account);
	}
	public void setCh_from_account(String ch_from_account) {
		this.ch_from_account = ch_from_account;
	}
	//
	public String getCh_to_account() {
		return (this.ch_to_account);
	}
	public void setCh_to_account(String ch_to_account) {
		this.ch_to_account = ch_to_account;
	}
	//
	public String getCh_currency() {
		return (this.ch_currency);
	}
	public void setCh_currency(String ch_currency) {
		this.ch_currency = ch_currency;
	}
	//
	public String getCh_from_check() {
		return (this.ch_from_check);
	}
	public void setCh_from_check(String ch_from_check) {
		this.ch_from_check = ch_from_check;
	}
	//
	public String getCh_to_check() {
		return (this.ch_to_check);
	}
	public void setCh_to_check(String ch_to_check) {
		this.ch_to_check = ch_to_check;
	}
	//
	public String getCh_from_amount() {
		return (this.ch_from_amount);
	}
	public void setCh_from_amount(String ch_from_amount) {
		this.ch_from_amount = ch_from_amount;
	}
	//
	public String getCh_to_amount() {
		return (this.ch_to_amount);
	}
	public void setCh_to_amount(String ch_to_amount) {
		this.ch_to_amount = ch_to_amount;
	}
	//
	public String getCh_check_status() {
		return (this.ch_check_status);
	}
	public void setCh_check_status(String ch_check_status) {
		this.ch_check_status = ch_check_status;
	}
	//
	public String getCh_retstat() {
		return (this.ch_retstat);
	}
	public void setCh_retstat(String ch_retstat) {
		this.ch_retstat = ch_retstat;
	}
	//
	public String getCh_log_date() {
		return (this.ch_log_date);
	}
	public void setCh_log_date(String ch_log_date) {
		this.ch_log_date = ch_log_date;
	}
	//
	public String getCh_log_user() {
		return (this.ch_log_user);
	}
	public void setCh_log_user(String ch_log_user) {
		this.ch_log_user = ch_log_user;
	}
	//
	public String getSumm_total_amount() {
		return (this.summ_total_amount);
	}
	public void setSumm_total_amount(String summ_total_amount) {
		this.summ_total_amount = summ_total_amount;
	}
	//
	public String getSumm_total_checks() {
		return (this.summ_total_checks);
	}
	public void setSumm_total_checks(String summ_total_checks) {
		this.summ_total_checks = summ_total_checks;
	}
	//
	public String getDetailAmount() {
		return (this.detailAmount);
	}
	public void setDetailAmount(String detailAmount) {
		this.detailAmount = detailAmount;
	}
	// For use with the JSP
	public String getImageURL() {
		return (this.imageURL);
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	// For use to lookup image files on the disk
	public String getImageDir() {
		return (this.imageDir);
	}
	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}
	// Directory where PDFs will be generated
	public String getPdfDir() {
		return (this.pdfDir);
	}
	public void setPdfDir(String pdfDir) {
		this.pdfDir = pdfDir;
	}
	// Start of month for eMail, Fax and Mail
	public String getMonthStart() {
		return (this.monthStart);
	}
	public void setMonthStart(String monthStart) {
		this.monthStart = monthStart;
	}
	// End of month for eMail, Fax and Mail
	public String getMonthEnd() {
		return (this.monthEnd);
	}
	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}
	// Start of Week for eMail, Fax and Mail
	public String getWeekStart() {
		return (this.weekStart);
	}
	public void setWeekStart(String weekStart) {
		this.weekStart = weekStart;
	}
	// End of month for eMail, Fax and Mail
	public String getWeekEnd() {
		return (this.weekEnd);
	}
	public void setWeekEnd(String weekEnd) {
		this.weekEnd = weekEnd;
	}
	// Daily Date for eMail, Fax and Mail
	public String getDailyDate() {
		return (this.dailyDate);
	}
	public void setDailyDate(String dailyDate) {
		this.dailyDate = dailyDate;
	}
	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public void setAcctList(ArrayList<?> acctList) {
		clearAcctList();
		for (int i = 0; i < acctList.size(); i++) {
			this.acctList.add((String) acctList.get(i));
		}
	}
	public ArrayList<String> getAcctList() {
		return (this.acctList);
	}
	//
	public void setDateList(ArrayList<?> dateList) {
		clearDateList();
		for (int i = 0; i < dateList.size(); i++) {
			this.dateList.add((String) dateList.get(i));
		}
	}
	public ArrayList<String> getDateList() {
		return (this.dateList);
	}
	//
	public void setHistList(ArrayList<?> histList) {
		clearHistList();
		for (int i = 0; i < histList.size(); i++) {
			this.histList.add((String) histList.get(i));
		}
	}
	public ArrayList<String> getHistList() {
		return (this.histList);
	}
	//
	public void setUserList(ArrayList<?> userList) {
		clearUserList();
		for (int i = 0; i < userList.size(); i++) {
			this.userList.add((String) userList.get(i));
		}
	}
	public ArrayList<String> getUserList() {
		return (this.userList);
	}
	//
	public TreeMap<String, String> getRejReasons() {
		return rejReasons;
	}
	public void setRejReasons(TreeMap<String, String> rejReasons) {
		this.rejReasons = rejReasons;
	}
	public String getRejReason (String rejCode) {
		return (this.rejReasons.get(rejCode));
	}
	//
	public TreeMap<String, String> getRetReasons() {
		return retReasons;
	}
	public void setRetReasons(TreeMap<String, String> retReasons) {
		this.retReasons = retReasons;
	}
	public String getRetReason (String retCode) {
		return (this.retReasons.get(retCode));
	}
	//
	public TreeMap<String, String> getChexStatus() {
		return chexStatus;
	}
	public void setChexStatus(TreeMap<String, String> chexStatus) {
		this.chexStatus = chexStatus;
	}
	public String getChexStatus (String statusCode) {
		return (this.chexStatus.get(statusCode));
	}
	//
	public TreeMap<String, Long> getHighCheckNum() {
		return highCheckNum;
	}
	public void setHighCheckNum(TreeMap<String, Long> highCheckNum) {
		this.highCheckNum = highCheckNum;
	}
	//
	public TreeMap<String, Integer> getMonthAverage() {
		return monthAverage;
	}
	public void setMonthAverage(TreeMap<String, Integer> monthAverage) {
		this.monthAverage = monthAverage;
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
		moduleName	= "setRowsDisplayed: ";
		//PrintLog("rowsDisplayed: " + rowsDisplayed);
		if (rowsDisplayed <= 500) {
			this.rowsDisplayed	= rowsDisplayed;
		} else {
			this.rowsDisplayed	= 500;
		}
		if (this.rowsDisplayed > this.detail_count) {
			this.rowsDisplayed	= this.detail_count;
		}
		setRowsDispStr(""+this.rowsDisplayed);
	}
	public int getRowsDisplayed() {
		return (this.rowsDisplayed);
	}
	//
	public void setRowsDispStr(String rowsDispStr) {
		moduleName	= "setRowsDispStr: ";
		//PrintLog("rowsDispStr: " + rowsDispStr);
		if (Integer.parseInt(rowsDispStr) <= 500) {
			this.rowsDispStr	= rowsDispStr;
		} else {
			this.rowsDispStr	= "500";
		}
		this.rowsDisplayed = Integer.parseInt(this.rowsDispStr);
		if (this.rowsDisplayed > this.detail_count) {
			this.rowsDisplayed	= this.detail_count;
			this.rowsDispStr	= this.detail_count + "";
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
	//
	public void InitRowSEC() {
		clearErrors();
		this.rowStart		= 0;
		this.rowEnd			= 20;
		this.rowsDisplayed	= 20;
	}
	//
	public void SaveParams() {
		ch_from_period_o	= ch_from_period;
		ch_to_period_o		= ch_to_period;
		ch_from_date_o		= ch_from_date;
		ch_to_date_o		= ch_to_date;
		ch_from_account_o	= ch_from_account;
		ch_to_account_o		= ch_to_account;
		ch_currency_o		= ch_currency;
		ch_from_check_o		= ch_from_check;
		ch_to_check_o		= ch_to_check;
		ch_from_amount_o	= ch_from_amount;
		ch_to_amount_o		= ch_to_amount;
		ch_check_status_o	= ch_check_status;
		ch_retstat_o		= ch_retstat;
		ch_log_date_o		= ch_log_date;
		ch_log_user_o		= ch_log_user;
	}
	//
	public void RestoreParams() {
		ch_from_period		= ch_from_period_o;
		ch_to_period		= ch_to_period_o;
		ch_from_date		= ch_from_date_o;
		ch_to_date			= ch_to_date_o;
		ch_from_account		= ch_from_account_o;
		ch_to_account		= ch_to_account_o;
		ch_currency			= ch_currency_o;
		ch_from_check		= ch_from_check_o;
		ch_to_check			= ch_to_check_o;
		ch_from_amount		= ch_from_amount_o;
		ch_to_amount		= ch_to_amount_o;
		ch_check_status		= ch_check_status_o;
		ch_retstat			= ch_retstat_o;
		ch_log_date			= ch_log_date_o;
		ch_log_user			= ch_log_user_o;
	}
	//
	public void ShowParams() {
		PrintLog("ch_from_period	= " + ch_from_period);
		PrintLog("ch_to_period		= " + ch_to_period);
		PrintLog("ch_from_date		= " + ch_from_date);
		PrintLog("ch_to_date		= " + ch_to_date);
		PrintLog("ch_from_account	= " + ch_from_account);
		PrintLog("ch_to_account		= " + ch_to_account);
		PrintLog("ch_currency		= " + ch_currency);
		PrintLog("ch_from_check		= " + ch_from_check);
		PrintLog("ch_to_check		= " + ch_to_check);
		PrintLog("ch_from_amount	= " + ch_from_amount);
		PrintLog("ch_to_amount		= " + ch_to_amount);
		PrintLog("ch_check_status	= " + ch_check_status);
		PrintLog("ch_retstat		= " + ch_retstat);
		PrintLog("ch_log_date		= " + ch_log_date);
		PrintLog("ch_log_user		= " + ch_log_user);
	}
	public void InitParams() {
		ch_from_period	= "";
		ch_to_period	= "";
		ch_from_date	= "";
		ch_to_date		= "";
		ch_from_account	= "";
		ch_to_account	= "";
		ch_currency		= "";
		ch_from_check	= "";
		ch_to_check		= "";
		ch_from_amount	= "";
		ch_to_amount	= "";
		ch_check_status	= "";
		ch_retstat		= "";
		ch_log_date		= "";
		ch_log_user		= "";
	}
	//
	public void clearNulls() {
		ch_from_period	= (ch_from_period == null) ? "" : ch_from_period;
		ch_to_period	= (ch_to_period == null) ? "" : ch_to_period;
		ch_from_date	= (ch_from_date == null) ? "" : ch_from_date;
		ch_to_date		= (ch_to_date == null) ? "" : ch_to_date;
		ch_from_account = (ch_from_account == null) ? "" : ch_from_account;
		ch_to_account	= (ch_to_account == null) ? "" : ch_to_account;
		ch_currency		= (ch_currency == null) ? "" : ch_currency;
		ch_from_check	= (ch_from_check == null) ? "" : ch_from_check;
		ch_to_check		= (ch_to_check == null) ? "" : ch_to_check;
		ch_from_amount	= (ch_from_amount == null) ? "" : ch_from_amount;
		ch_to_amount	= (ch_to_amount == null) ? "" : ch_to_amount;
		ch_check_status	= (ch_check_status == null) ? "" : ch_check_status;
		ch_retstat		= (ch_retstat == null) ? "" : ch_retstat;
		ch_log_date		= (ch_log_date == null) ? "" : ch_log_date;
		ch_log_user		= (ch_log_user == null) ? "" : ch_log_user;
	}
	//
	public ChexDetail[] getCheckrows() {
		moduleName = "getCheckrows: ";
		ChexDetail results[] = new ChexDetail[checkrows.size()];
		Iterator<ChexDetail> chex = checkrows.iterator();
		int n = 0;
		while (chex.hasNext()) {
			results[n++] = (ChexDetail) chex.next();
		}
		// PrintLog("Detail count "+n);
		return (results);
	}
	//
	public ArrayList<ChexDetail> getChexRowsArray() {
		return (this.checkrows);
	}
	//
	public ChexDetail getArow() {
		ChexDetail results = new ChexDetail();
		Iterator<ChexDetail> chex = checkrows.iterator();
		results = (ChexDetail) chex.next();
		return (results);
	}
	//
	public ChexDetail getArow(int recIndex) {
		ChexDetail results = new ChexDetail();
		// Iterator chex = checkrows.iterator();
		this.recIndex = recIndex;
		results = (ChexDetail) checkrows.get(recIndex);
		return (results);
	}
	//
	public void setCheckrows(ArrayList<ChexDetail> check_row) {
		checkrows.clear();
		Iterator<ChexDetail> rows = check_row.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.checkrows.add(n, (ChexDetail) rows.next());
			n++;
		}
		//PrintLog("Checks Count: "+n);
		detail_count = n;
	}
	//
	public ChexSummary[] getSummaryrows() {
		ChexSummary results[] = new ChexSummary[summaryrows.size()];
		Iterator<ChexSummary> summ = summaryrows.iterator();
		int n = 0;
		while (summ.hasNext()) {
			results[n++] = (ChexSummary) summ.next();
		}
		return (results);
	}
	//
	public void setSummaryrows(ArrayList<?> summary_row) {
		Iterator<?> rows = summary_row.iterator();
		while (rows.hasNext()) {
			this.summaryrows.add((ChexSummary) rows.next());
		}
	}
	//
	public void setModifyRow(ChexDetail modifyRow) {
		this.modifyRow = modifyRow;
	}
	//
	public ChexDetail getModifyRow() {
		return (this.modifyRow);
	}
	//
	public ArrayList<String> getHist_tables() {
		return (this.hist_tables);
	}
	//
	public void setHist_tables(String hist_table_row) {
		this.hist_tables.add(hist_table_row);
	}
	//
	public String GetParams() {
		moduleName	= "GetParams: ";
		param		= "";
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_acct	= "";
		String to_acct 		= "";
		String currency_sel	= "";
		String from_check	= "";
		String to_check		= "";
		String from_amt		= "";
		String to_amt		= "";
		String chex_status	= "";
		String chex_retstat	= "";
		//
		String acc_param	= "";
		String curr_param	= "";
		String check_param	= "";
		String amt_param	= "";
		String status_param	= "";
		String retstat_param= "";
		//
		clearNulls();
		if (!ch_from_account.equals("ALL")) {
			from_acct	= ch_from_account.trim();
		}
		if (!ch_to_account.equals("NONE")) {
			to_acct		= ch_to_account.trim();
		}
		if (!ch_currency.equals("")) {
			currency_sel	= ch_currency.trim();
		}
		if (!ch_from_check.equals("")) {
			from_check	= ch_from_check.trim();
		}
		if (!ch_to_check.equals("")) {
			to_check	= ch_to_check.trim();
		}
		if (!ch_from_amount.equals("")) {
			from_amt	= ch_from_amount.trim();
		}
		if (!ch_to_amount.equals("")) {
			to_amt		= ch_to_amount.trim();
		}
		if (!ch_check_status.equals("ALL")) {
			chex_status	= ch_check_status.trim();
		}
		if (!ch_retstat.equals("")) {
			chex_retstat		= ch_retstat.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param	= "chex_account_num='" + from_acct + "' ";
			// "(chex_account_num='"+from_acct+"' OR chex_account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " chex_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = " chex_account_num='" + to_acct + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = " chex_check_currency='" + currency_sel + "' ";
		}
		if (!from_check.equals("")) {
			check_param = " chex_check_num='" + from_check + "' ";
			// "(chex_check_num='"+from_check+"' "+" OR chex_check_num>'"+from_check+"') ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " chex_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = " chex_check_num='" + to_check + "' ";
			}
		}
		if (!from_amt.equals("")) {
			amt_param = " chex_check_amount='" + from_amt + "' ";
			// "(chex_check_num='"+from_check+"' "+" OR chex_check_num>'"+from_check+"') ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param = " chex_check_amount between '" + from_amt
						+ "' AND '" + to_amt + "'";
			} else {
				amt_param = " chex_check_amount='" + to_amt + "' ";
			}
		}
		if (!chex_status.equals("")) {
			status_param = " chex_check_status='" + chex_status + "' ";
		}
		if (!chex_retstat.equals("")) {
			retstat_param = " chex_return_status<>' ' ";
		}
		//
		//
		if (!acc_param.equals("")) {
			param = acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals(""))
				param += " AND " + curr_param;
		} else {
			param = curr_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals(""))
				param += " AND " + check_param;
		} else {
			param = check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals(""))
				param += " AND " + amt_param;
		} else {
			param = amt_param;
		}
		if (!param.equals("")) {
			if (!status_param.equals(""))
				param += " AND " + status_param;
		} else {
			param = status_param;
		}
		if (!param.equals("")) {
			if (!retstat_param.equals(""))
				param += " AND " + retstat_param;
		} else {
			param = retstat_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY chex_account_num, chex_check_num";
		return (param);
	}
	//
	public String GetLogHistParams() {
		moduleName = "GetLogHistParams: ";
		param = "";
		ArrayList<String> hist_range = new ArrayList<String>();
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_period = "";
		String to_period = "";
		String from_date = "";
		String to_date = "";
		String from_acct = "";
		String to_acct = "";
		String currency_sel = "";
		String from_check = "";
		String to_check = "";
		String from_amt = "";
		String to_amt = "";
		//
		String acc_param = "";
		String curr_param = "";
		String check_param = "";
		String amt_param = "";
		String date_param = "";
		//
		clearNulls();
		from_period = ch_from_period.trim();
		if (!ch_to_period.equals("NONE")) {
			to_period = ch_to_period.trim();
		}
		if (!ch_from_date.equals("")) {
			from_date = ch_from_date.trim();
		}
		if (!ch_to_date.equals("")) {
			to_date = ch_to_date.trim();
		}
		//
		if (!ch_from_account.equals("")) {
			from_acct = ch_from_account.trim();
		}
		//
		if (!ch_to_account.equals("NONE")) {
			to_acct = ch_to_account.trim();
		}
		if (!ch_currency.equals("")) {
			currency_sel = ch_currency.trim();
		}
		if (!ch_from_check.equals("")) {
			from_check = ch_from_check.trim();
		}
		if (!ch_to_check.equals("")) {
			to_check = ch_to_check.trim();
		}
		if (!ch_from_amount.equals("")) {
			from_amt = ch_from_amount.trim();
		}
		if (!ch_to_amount.equals("")) {
			to_amt = ch_to_amount.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		hist_range.add(from_period);
		if (!to_period.equals("")) {
			hist_range.add(to_period);
		}
		if (from_date.equals("") && to_date.equals("")) {
			// Case 3.
			// from_period_param = from_period;
		} else if (!from_date.equals("") && !to_date.equals("")) {
			date_param = " chlog_proc_date between '" + from_date + "' AND '"
					+ to_date + "' ";
		} else if (!from_date.equals("")) {
			date_param = " chlog_proc_date='" + from_date + "' ";
		} else if (!to_date.equals("")) {
			date_param = " chlog_proc_date='" + to_date + "' ";
		}
		if (!from_acct.equals("")) {
			acc_param = "chlog_account_num='" + from_acct + "' ";
			// acc_param = "chlog_orig_account_num='" + from_acct + "' ";
			// "(chlog_account_num='"+from_acct+"' OR chlog_account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " chlog_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
				// acc_param = " chlog_orig_account_num between '" + from_acct +
				// "' AND '" + to_acct + "'";
			} else {
				acc_param = " chlog_account_num='" + to_acct + "' ";
				// acc_param = " chlog_orig_account_num='" + to_acct + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = " chlog_check_currency='" + currency_sel + "' ";
		}
		if (!from_check.equals("")) {
			check_param = " chlog_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " chlog_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = " chlog_check_num='" + to_check + "' ";
			}
		}
		if (!from_amt.equals("")) {
			amt_param = " chlog_check_amount='" + from_amt + "' ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param = " chlog_check_amount between '" + from_amt
						+ "' AND '" + to_amt + "'";
			} else {
				amt_param = " chlog_check_amount='" + to_amt + "' ";
			}
		}
		//
		//
		if (!acc_param.equals("")) {
			param = acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals(""))
				param += " AND " + curr_param;
		} else {
			param = curr_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals(""))
				param += " AND " + check_param;
		} else {
			param = check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals(""))
				param += " AND " + amt_param;
		} else {
			param = amt_param;
		}
		if (!param.equals("")) {
			if (!date_param.equals(""))
				param += " AND " + date_param;
		} else {
			param = date_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY chlog_account_num, chlog_check_num, chlog_last_modified DESC";
		//PrintLog(" Param  " + param);
		return (param);
	}
	//
	public String GetLogParams() {
		moduleName			= "GetLogParams: ";
		param				= "";
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_acct	= "";
		String to_acct		= "";
		String currency_sel	= "";
		String from_check	= "";
		String to_check		= "";
		String from_amt		= "";
		String to_amt		= "";
		String chex_status	= "";
		String log_date		= "";
		String log_user		= "";
		//
		String acc_param	= "";
		String curr_param	= "";
		String check_param	= "";
		String amt_param	= "";
		String status_param	= "";
		String log_date_param	= "";
		String log_user_param	= "";
		//
		clearNulls();
		if (!ch_from_account.equals("ALL")) {
			from_acct = ch_from_account.trim();
		}
		if (!ch_to_account.equals("NONE")) {
			to_acct = ch_to_account.trim();
		}
		if (!ch_currency.equals("")) {
			currency_sel = ch_currency.trim();
		}
		if (!ch_from_check.equals("")) {
			from_check = ch_from_check.trim();
		}
		if (!ch_to_check.equals("")) {
			to_check = ch_to_check.trim();
		}
		if (!ch_from_amount.equals("")) {
			from_amt = ch_from_amount.trim();
		}
		if (!ch_to_amount.equals("")) {
			to_amt = ch_to_amount.trim();
		}
		if (!ch_check_status.equals("ALL")) {
			chex_status = ch_check_status.trim();
		}
		if (!ch_log_date.equals("ALL")) {
			log_date = ch_log_date.trim();
		}
		if (!ch_log_user.equals("ALL")) {
			log_user = ch_log_user.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param = "chlog_orig_account_num='" + from_acct + "' ";
			// "(chlog_account_num='"+from_acct+"' OR chlog_account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " chlog_orig_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = " chlog_orig_account_num='" + to_acct + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = " chlog_check_currency='" + currency_sel + "' ";
		}
		if (!from_check.equals("")) {
			check_param = " chlog_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " chlog_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = " chlog_check_num='" + to_check + "' ";
			}
		}
		if (!from_amt.equals("")) {
			amt_param = " chlog_check_amount='" + from_amt + "' ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param = " chlog_check_amount between '" + from_amt
						+ "' AND '" + to_amt + "'";
			} else {
				amt_param = " chlog_check_amount='" + to_amt + "' ";
			}
		}
		if (!chex_status.equals("")) {
			status_param = " chlog_check_status='" + chex_status + "' ";
		}
		if (!log_date.equals("")) {
			log_date_param = " chlog_last_modified LIKE '%" + log_date + "%' ";
		}
		if (!log_user.equals("")) {
			log_user_param = " chlog_mod_user '" + log_user + "' ";
		}
		//
		//
		if (!acc_param.equals("")) {
			param = acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals(""))
				param += " AND " + curr_param;
		} else {
			param = curr_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals(""))
				param += " AND " + check_param;
		} else {
			param = check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals(""))
				param += " AND " + amt_param;
		} else {
			param = amt_param;
		}
		if (!param.equals("")) {
			if (!status_param.equals(""))
				param += " AND " + status_param;
		} else {
			param = status_param;
		}
		if (!param.equals("")) {
			if (!log_date_param.equals(""))
				param += " AND " + log_date_param;
		} else {
			param = log_date_param;
		}
		if (!param.equals("")) {
			if (!log_user_param.equals(""))
				param += " AND " + log_user_param;
		} else {
			param = log_user_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY chlog_account_num, chlog_check_num, chlog_last_modified DESC";
		return (param);
	}
	//
	public String GetHistParams() {
		moduleName = "GetHistParams: ";
		param = "";
		ArrayList<String> hist_range = new ArrayList<String>();
		// PrintLog("In Get Params");
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_period = "";
		String to_period = "";
		String from_date = "";
		String to_date = "";
		String from_acct = "";
		String to_acct = "";
		String currency_sel = "";
		String from_check = "";
		String to_check = "";
		String from_amt = "";
		String to_amt = "";
		String retstat = "";
		//
		String date_param = "";
		String acc_param = "";
		String curr_param = "";
		String check_param = "";
		String amt_param = "";
		String retstat_param = "";
		// String freqParam = "";
		//
		clearNulls();
		from_period = ch_from_period.trim();
		//PrintLog("From Period: " + from_period);
		if (!ch_to_period.equals("NONE")) {
			to_period = ch_to_period.trim();
		}
		if (!ch_from_date.equals("")) {
			from_date = ch_from_date.trim();
		}
		if (!ch_to_date.equals("")) {
			to_date = ch_to_date.trim();
		}
		if (!ch_from_account.equals("")) {
			from_acct = ch_from_account.trim();
		}
		if (!ch_to_account.equals("")) {
			to_acct = ch_to_account.trim();
		}
		if (ch_currency == null) {
			ch_currency = "";
		}
		if (!ch_currency.equals("")) {
			currency_sel = ch_currency.trim();
		}
		if (!ch_from_check.equals("")) {
			from_check = ch_from_check.trim();
		}
		if (!ch_to_check.equals("")) {
			to_check = ch_to_check.trim();
		}
		if (!ch_from_amount.equals("")) {
			from_amt = ch_from_amount.trim();
		}
		if (!ch_to_amount.equals("")) {
			to_amt = ch_to_amount.trim();
		}
		//
		//PrintLog("ch_retstat: " + ch_retstat);
		if (!ch_retstat.equals("N")) {
			retstat = ch_retstat.trim();
		}
		//
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		// Case 1. Both Period/s and Date/s is/are chosen
		// Case 2. Either Period/s or Date/s chosen
		// Case 3. Neither Period/s nor Date/s is/are chosen
		//
		//
		hist_range.add(from_period);
		if (!to_period.equals("")) {
			hist_range.add(to_period);
		}
		//
		// DATE PARAM
		//
		if (from_date.equals("") && to_date.equals("")) {
			// Case 3.
			// from_period_param = from_period;
		} else if (!from_date.equals("") && !to_date.equals("")) {
			date_param = " chist_proc_date between '" + from_date + "' AND '"
					+ to_date + "' ";
		} else if (!from_date.equals("")) {
			date_param = " chist_proc_date='" + from_date + "' ";
		} else if (!to_date.equals("")) {
			date_param = " chist_proc_date='" + to_date + "' ";
		}
		//
		// ACCOUNT PARAM
		//
		if (!from_acct.equals("")) {
			acc_param = "chist_account_num='" + from_acct + "' ";
			// "(chlog_account_num='"+from_acct+"' OR chlog_account_num>'"
			// +from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " chist_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = " chist_account_num='" + to_acct + "' ";
			}
		}
		//
		// CURRENCY PARAM
		//
		if (!currency_sel.equals("")) {
			curr_param = " chist_check_currency='" + currency_sel + "' ";
		}
		//
		// CHECK NUMBER PARAM
		//
		if (!from_check.equals("")) {
			check_param = " chist_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " chist_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = " chist_check_num='" + to_check + "' ";
			}
		}
		//
		// AMOUNT PARAM
		//
		if (!from_amt.equals("")) {
			amt_param = " chist_check_amount='" + from_amt + "' ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param = " chist_check_amount between '" + from_amt
						+ "' AND '" + to_amt + "'";
			} else {
				amt_param = " chist_check_amount='" + to_amt + "' ";
			}
		}
		//
		// STATUS PARAM
		//
		if (!retstat.equals("")) {
			retstat_param = " chist_return_status<>' ' ";
		}
		//
		// FREQUENCY PARAM
		//
		// if (chex_stmt_freq.equals("M")) {
		// freqParam = (" AND (chist_stmt_emailfreqm='Y' OR "+
		// "chist_stmt_mailfreqm='Y' OR "+
		// "chist_stmt_faxfreqm='Y') ");
		// } else if (chex_stmt_freq.equals("W")) {
		// freqParam = (" AND (chist_stmt_emailfreqw='Y' OR "+
		// "chist_stmt_mailfreqw='Y' OR "+
		// "chist_stmt_faxfreqw='Y') ");
		// } else if (chex_stmt_freq.equals("D")) {
		// freqParam = (" AND (chist_stmt_emailfreqd='Y' OR "+
		// "chist_stmt_mailfreqd='Y' OR "+
		// "chist_stmt_faxfreqd='Y') ");
		// }
		//
		// Here assemble field params into a single param
		//
		if (!acc_param.equals("")) {
			param = acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals("")) {
				param += " AND " + curr_param;
			}
		} else {
			param = curr_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals("")) {
				param += " AND " + check_param;
			}
		} else {
			param = check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals("")) {
				param += " AND " + amt_param;
			}
		} else {
			param = amt_param;
		}
		if (!param.equals("")) {
			if (!date_param.equals("")) {
				param += " AND " + date_param;
			}
		} else {
			param = date_param;
		}
		if (!param.equals("")) {
			if (!retstat_param.equals(""))
				param += " AND " + retstat_param;
		} else {
			param = retstat_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY chist_account_num, chist_check_num, chist_proc_date";
		//PrintLog("Params: " + param);
		// moduleName = calledFrom;
		return (param);
	}
	//
	public void SetNextRows() {
		if (this.rowEnd < detail_count) {
			this.rowStart	= this.rowEnd;
			this.rowEnd		= this.rowEnd + this.rowsDisplayed;
			if (this.rowEnd > this.detail_count) {
				this.rowEnd	= this.detail_count;
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
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> ChexSelector: MemoryBlock finalized: "+this );
	}
}
