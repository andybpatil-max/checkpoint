package com.webfiche.checkpoint.deposits.beans;

import java.io.*;
import java.util.*;

public final class DepsSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -- Instance Variables
	private String className		= "> DepsSelector.";
	private String moduleName		= "";
	private String chexSource		= "";
	private int detail_count		= 0;
	private int recIndex			= 0;
	private int depsOsn				= 0;
	private String row_Count		= "0";
	private String detailAmount		= "";
	private String actionFlag		= "";
	private String accessFlag		= "";
	private String imageLookup		= "";
	private String dbUsed			= "";
	private String dbTable			= "";
	private String applDate			= "";
	private String applDate_f		= "";
	private String bankId			= "";
	private String defCurr			= "";
	private String allow_eod		= "N";
	private String eod_status		= "";
	private String bod_status		= "";
	private String userId			= "";
	private String modFunc			= "";
	private String ch_from_period	= "";
	private String ch_to_period		= "";
	private String ch_from_date		= "";
	private String ch_to_date		= "";
	private String ch_from_account	= "";
	private String ch_to_account	= "";
	private String ch_payee			= "";
	private String ch_fromPayee		= "";
	private String ch_toPayee		= "";
	private String ch_currency		= "";
	private String ch_from_check	= "";
	private String ch_to_check		= "";
	private String ch_from_amount	= "";
	private String ch_to_amount		= "";
	private String ch_check_status	= "";
	private String ch_log_date		= "";
	private String ch_log_user		= "";
	private String ch_source		= "";
	//
	private String ch_from_period_o	= "";
	private String ch_to_period_o	= "";
	private String ch_from_date_o	= "";
	private String ch_to_date_o		= "";
	private String ch_from_account_o= "";
	private String ch_to_account_o	= "";
	private String ch_payee_o		= "";
	private String ch_fromPayee_o	= "";
	private String ch_toPayee_o		= "";
	private String ch_currency_o	= "";
	private String ch_from_check_o	= "";
	private String ch_to_check_o	= "";
	private String ch_from_amount_o	= "";
	private String ch_to_amount_o	= "";
	private String ch_check_status_o= "";
	private String ch_log_date_o	= "";
	private String ch_log_user_o	= "";
	private String ch_source_o		= "";
	private String summ_total_amount= "";
	private String summ_total_checks= "";
	private DepsDetail modifyRow 	= new DepsDetail();
	private ArrayList <DepsDetail>checkrows 	= new ArrayList<DepsDetail>();
	private ArrayList <DepsSummary>summaryrows 	= new ArrayList<DepsSummary>();
	private ArrayList <String>hist_tables 	= new ArrayList<String>();
	private String imageURL			= "";
	private String imageDir			= "";
	private String pdfDir			= "";
	private String monthStart		= "";
	private String monthEnd			= "";
	private String weekStart		= "";
	private String weekEnd			= "";
	private String dailyDate		= "";
	private String param			= "";
	private Vector <Vector <String>> errorVec	= new Vector<Vector <String>>();
	private ArrayList <String>payerAcctList	= new ArrayList<String>();
	private ArrayList <String>payeeAcctList	= new ArrayList<String>();
	private ArrayList <String>payeeList	= new ArrayList<String>();
	private ArrayList <String>histList	= new ArrayList<String>();
	private ArrayList <String>dateList	= new ArrayList<String> ();
	private ArrayList <String>userList	= new ArrayList<String> ();
	private TreeMap<String, String> rejReasons		= new TreeMap<String, String>();
	private TreeMap<String, String> chexStatus		= new TreeMap<String, String>();
	private TreeMap<String, String> payerNamesList	= new TreeMap<String, String>();
	private TreeMap<String, String> payerCountryList= new TreeMap<String, String>();
	private TreeMap<String, String> payeeNamesList	= new TreeMap<String, String>();
	//
	//
	// Here deifne variables for controlling the number of checks displayed.
	// Use the checksPerView constant defined in web.xml
	//
	private int rowStart			= 0;
	private int rowEnd				= 20;
	private int rowsDisplayed		= 20;
	private String rowsDispStr		= "";
	//
	//	Param work variables 
	private String from_period		= "";
	private String to_period		= "";
	private String from_acct		= "";
	private String to_acct			= "";
	private String currency_sel		= "";
	private String payee_sel		= "";
	private String fromPayee_sel	= "";
	private String toPayee_sel		= "";
	private String from_check		= "";
	private String to_check			= "";
	private String from_amt			= "";
	private String to_amt			= "";
	private String chex_status		= "";
	private String from_date		= "";
	private String to_date			= "";
	private String log_date			= "";
	private String log_user			= "";
	private String chSource			= "";

	private String acc_param		= "";
	private String curr_param		= "";
	private String payee_param		= "";
	private String cred_param		= "";
	private String check_param		= "";
	private String amt_param		= "";
	private String status_param		= "";
	private String date_param		= "";
	private String log_date_param	= "";
	private String log_user_param	= "";
	private String sourceParam		= "";
	//
	ArrayList<String> hist_range	= new ArrayList<String>();
	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()+
						className+moduleName+logMsg);
	}
	//
	public void clearRows() {
		this.checkrows.clear();
		this.summaryrows.clear();
		this.detail_count	= 0;
	}
	public void clearSummary() {
		this.summaryrows.clear();
	}
	public void clearPayerAcctList() {
		this.payerAcctList.clear();
	}
	public void clearPayeeAcctList() {
		this.payeeAcctList.clear();
	}
	public void clearPayeeList() {
		this.payeeList.clear();
	}
	//
	public void clearDateList() {
		this.dateList.clear();
	}
	public void clearHistList() {
		this.histList.clear();
	}
	public void clearUserList() {
		this.userList.clear();
	}
	//
	public String getChexSource() {
		return (this.chexSource);
	}
	public void setChexSource(String chexSource) {
		this.chexSource = chexSource;
	}
	//
	public int getDetail_count() {
		return (this.detail_count);
	}
	public void setDetail_count(int detail_count) {
		this.detail_count	= detail_count;
		//PrintLog("RowEnd: "+this.rowEnd);
		if (this.rowEnd > detail_count) {
			this.rowEnd	= detail_count;
		}
	}
	//
	public int getRecIndex() {
		return (this.recIndex);
	}
	public void setRecIndex(int recIndex) {
		this.recIndex	= recIndex;
	}
	//
	public int getDepsOsn() {
		return (this.depsOsn);
	}
	public void setDepsOsn(int depsOsn) {
		this.depsOsn	= depsOsn;
	}
	//
	public String getRow_Count() {
		return (this.row_Count);
	}
	public void setRow_Count(String row_Count) {
		this.row_Count	= row_Count;
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
		this.accessFlag	= accessFlag;
	}
	//
	public String getImageLookup() {
		return (this.imageLookup);
	}
	public void setImageLookup(String imageLookup) {
		this.imageLookup	= imageLookup;
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
	public String getApplDate_f() {
		return (this.applDate_f);
	}
	//
	public String getApplDate() {
		return (this.applDate);
	}
	//date="112304";
	//new_date= "20"+date.substring(4,6)+date.substring(2,4)+date.substring(0,2);
	public void setApplDate (String applDate) {
		//PrintLog("ApplDate: "+applDate);
		this.applDate = applDate;
		if (!applDate.equals("")) {
			this.applDate_f	= (applDate.substring(4,6) + "/" +
									applDate.substring(6) + "/" +
									applDate.substring(0,4));
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
	public void setAllow_eod (String allow_eod) {
		this.allow_eod = allow_eod;
	}
	//
	public String getEod_status() {
		return (this.eod_status);
	}
	public void setEod_status (String eod_status) {
		this.eod_status = eod_status;
	}
	//
	public String getBod_status() {
		return (this.bod_status);
	}
	public void setBod_status (String bod_status) {
		this.bod_status = bod_status;
	}
	//
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getModFunc() {
		return modFunc;
	}
	public void setModFunc(String modFunc) {
		this.modFunc = modFunc;
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
	public void setCh_to_account (String ch_to_account) {
		this.ch_to_account	= ch_to_account;
	}
	//
	public String getCh_payee() {
		return (this.ch_payee);
	}
	public void setCh_payee (String ch_payee) {
		this.ch_payee	= ch_payee;
	}
	//
	public String getCh_fromPayee() {
		return (this.ch_fromPayee);
	}
	public void setCh_fromPayee (String ch_fromPayee) {
		this.ch_fromPayee	= ch_fromPayee;
	}
	//
	public String getCh_toPayee() {
		return (this.ch_toPayee);
	}
	public void setCh_toPayee (String ch_toPayee) {
		this.ch_toPayee	= ch_toPayee;
	}
	//
	public String getCh_currency() {
		return (this.ch_currency);
	}
	public void setCh_currency (String ch_currency) {
		this.ch_currency	= ch_currency;
	}
	//
	public String getCh_from_check() {
		return (this.ch_from_check);
	}
	public void setCh_from_check (String ch_from_check) {
		this.ch_from_check = ch_from_check;
	}
	//
	public String getCh_to_check() {
		return (this.ch_to_check);
	}
	public void setCh_to_check (String ch_to_check) {
		this.ch_to_check	= ch_to_check;
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
	public String getCh_source() {
		return (this.ch_source);
	}
	public void setCh_source(String ch_source) {
		this.ch_source = ch_source;
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
		this.detailAmount	= detailAmount;
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
		this.monthStart	= monthStart;
	}
	// End of month for eMail, Fax and Mail
	public String getMonthEnd() {
		return (this.monthEnd);
	}
	public void setMonthEnd(String monthEnd) {
		this.monthEnd	= monthEnd;
	}
	// Start of Week for eMail, Fax and Mail
	public String getWeekStart() {
		return (this.weekStart);
	}
	public void setWeekStart(String weekStart) {
		this.weekStart	= weekStart;
	}
	// End of month for eMail, Fax and Mail
	public String getWeekEnd() {
		return (this.weekEnd);
	}
	public void setWeekEnd(String weekEnd) {
		this.weekEnd	= weekEnd;
	}
	// Daily Date for eMail, Fax and Mail
	public String getDailyDate() {
		return (this.dailyDate);
	}
	public void setDailyDate(String dailyDate) {
		this.dailyDate	= dailyDate;
	}
	//
	public String getParam() {
		return (this.param);
	}
	public void setParam(String param) {
		this.param	= param;
	}
	//
	public void clearErrors () {
		errorVec.clear();
	}
	//
	public Vector <Vector <String>> getErrorVec() {
		return (this.errorVec);
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector <String>vecPair	= new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public void setPayerAcctList(ArrayList<String> acctList) {
		moduleName	= "setPayerAcctList: ";
		clearPayerAcctList();
		for (int i=0; i<acctList.size(); i++) {
			//PrintLog("Adding: "+(String)acctList.get(i));
			this.payerAcctList.add((String)acctList.get(i));
		}
	}
	public ArrayList <String>getPayerAcctList() {
		return (this.payerAcctList);
	}
	//
	public void setPayeeAcctList(ArrayList<String> acctList) {
		moduleName	= "setPayeeAcctList: ";
		clearPayeeAcctList();
		for (int i=0; i<acctList.size(); i++) {
			//PrintLog("Adding: "+(String)acctList.get(i));
			this.payeeAcctList.add((String)acctList.get(i));
		}
	}
	public ArrayList <String>getPayeeAcctList() {
		return (this.payeeAcctList);
	}
	//
	//
	public void setPayeeList(ArrayList<String> payeeList) {
		clearPayeeList();
		for (int i=0; i<payeeList.size(); i++) {
			this.payeeList.add((String)payeeList.get(i));
		}
	}
	public ArrayList <String>getPayeeList() {
		return (this.payeeList);
	}
	//
	public void setHistList(ArrayList<?> histList) {
		clearHistList();
		for (int i=0; i<histList.size(); i++) {
			this.histList.add((String)histList.get(i));
		}
	}
	public ArrayList <String>getHistList() {
		return (this.histList);
	}
	//
	public void setDateList(ArrayList<?> dateList) {
		clearDateList();
		for (int i=0; i<dateList.size(); i++) {
			this.dateList.add((String)dateList.get(i));
		}
	}
	public ArrayList<String> getDateList() {
		return (this.dateList);
	}
	//
	public void setUserList(ArrayList<?> userList) {
		clearUserList();
		for (int i=0; i<userList.size(); i++) {
			this.userList.add((String)userList.get(i));
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
	public TreeMap<String, String> getChexStatus() {
		return chexStatus;
	}
	public void setChexStatus(TreeMap<String, String> chexStatus) {
		this.chexStatus = chexStatus;
	}
	public String getChexStatus (String statusCode) {
		//PrintLog("StatusCode: "+statusCode);
		return (this.chexStatus.get(statusCode));
	}
	public void ShowStatus () {
		Set<String> keys = this.chexStatus.keySet();
		for (String key: keys) {
			System.out.println("Status Code "+key+" Desc: "+chexStatus.get(key));
		}
	}
	//
	public TreeMap<String, String> getPayerNamesList() {
		return payerNamesList;
	}
	public void setPayerNamesList(TreeMap<String, String> payerNamesList) {
		this.payerNamesList = payerNamesList;
	}
	public String getPayerName (String payersKey) {
		//PrintLog("PayersKey: "+payersKey);
		return (this.payerNamesList.get(payersKey));
	}
	public boolean payerExists (String payersKey) {
		//PrintLog("PayersKey: "+payersKey);
		PrintLog(this.payerNamesList.get(payersKey));
		return (this.payerNamesList.containsKey(payersKey));
	}
	public void addPayerNamesList (String payerKey, String payerName) {
		payerNamesList.put(payerKey, payerName);
	}
	//
	public TreeMap<String, String> getPayerCountryList() {
		return payerCountryList;
	}
	public void setPayerCountryList(TreeMap<String, String> payerCountryList) {
		this.payerCountryList = payerCountryList;
	}
	public String getPayerCountry (String payersKey) {
		//PrintLog("PayersKey: "+payersKey);
		return (this.payerCountryList.get(payersKey));
	}
	public boolean countryExists (String payerKey) {
		//PrintLog("PayersKey: "+payersKey);
		return (this.payerCountryList.containsKey(payerKey));
	}
	public void addPayerCountryList (String payerKey, String payerCountry) {
		payerCountryList.put(payerKey, payerCountry);
	}
	//
	public TreeMap<String, String> getPayeeNamesList() {
		return payeeNamesList;
	}
	public void setPayeeNamesList(TreeMap<String, String> payeeNamesList) {
		this.payeeNamesList = payeeNamesList;
	}
	public String getPayeeName (String payeeKey) {
		moduleName	= "getPayeeName> ";
		PrintLog("PayeeKey: "+payeeKey+" Payee Name: "+this.payeeNamesList.get(payeeKey));
		return (this.payeeNamesList.get(payeeKey));
	}
	public boolean payeeExists (String payeeKey) {
		moduleName	= "getPayeeName> ";
		PrintLog("PayeeKey: "+payeeKey);
		return (this.payeeNamesList.containsKey(payeeKey));
	}
	//
	public void setRowStart (int rowStart) {
		this.rowStart	= rowStart;
	}
	public int getRowStart() {
		return (this.rowStart);
	}
	//
	public void setRowEnd (int rowEnd) {
		this.rowEnd	= rowEnd;
	}
	public int getRowEnd() {
		return (this.rowEnd);
	}
	//
	public void setRowsDisplayed (int rowsDisplayed) {
		moduleName	= "setRowsDisplayed: ";
		//PrintLog("rowsDisplayed: "+rowsDisplayed);
		if (rowsDisplayed <=500) {
			this.rowsDisplayed	= rowsDisplayed;
		} else {
			this.rowsDisplayed	= 500;
		}
		if (this.rowsDisplayed >= this.detail_count) {
			this.rowsDisplayed = this.detail_count;
		}
	}
	public int getRowsDisplayed() {
		return (this.rowsDisplayed);
	}
	//
	public void setRowsDispStr (String rowsDispStr) {
		moduleName	= "setRowsDispStr: ";
		//PrintLog("rowsDispStr: "+rowsDispStr);
		if (Integer.parseInt(rowsDispStr) <=500) {
			this.rowsDispStr	= rowsDispStr;
		} else {
			this.rowsDispStr	= "500";
		}
		this.rowsDisplayed	= Integer.parseInt(this.rowsDispStr);
		if (this.rowsDisplayed >= this.detail_count) {
			this.rowsDisplayed	= this.detail_count;
			this.rowsDispStr	= this.detail_count + "";
		}
		//PrintLog("rowsDispStr: "+this.rowsDispStr);
	}
	public String getRowsDispStr() {
		return (this.rowsDisplayed+"");
	}
	//
	public void setRowSEC (int rowStart, int rowEnd, int rowCount) {
		this.rowStart		= rowStart;
		this.rowEnd			= rowEnd;
		this.rowsDisplayed	= rowCount;
	}
	//
	public void InitRowSEC () {
		clearErrors();
		this.rowStart		= 0;
		PrintLog("DetailCount: "+this.detail_count);
		if ((this.detail_count == 0) || (this.detail_count > 20)) {
			this.rowEnd			= 20;
			this.rowsDisplayed	= 20;
		} else {
			this.rowEnd			= this.detail_count;
			this.rowsDisplayed	= this.detail_count;
		}
	}
	//
	public void SaveParams () {
		clearNulls();
		ch_from_period_o	= ch_from_period;
		ch_to_period_o		= ch_to_period;
		ch_from_date_o		= ch_from_date;
		ch_to_date_o		= ch_to_date;
		ch_from_account_o	= ch_from_account;
		ch_to_account_o		= ch_to_account;
		ch_payee_o			= ch_payee;
		ch_fromPayee_o		= ch_fromPayee;
		ch_toPayee_o		= ch_toPayee;
		ch_currency_o		= ch_currency;
		ch_from_check_o		= ch_from_check;
		ch_to_check_o		= ch_to_check;
		ch_from_amount_o	= ch_from_amount;
		ch_to_amount_o		= ch_to_amount;
		ch_check_status_o	= ch_check_status;
		ch_log_date_o		= ch_log_date;
		ch_log_user_o		= ch_log_user;
		ch_source_o			= ch_source;
	}
	public void RestoreParams () {
		ch_from_period	= ch_from_period_o;
		ch_to_period	= ch_to_period_o;
		ch_from_date	= ch_from_date_o;
		ch_to_date		= ch_to_date_o;
		ch_from_account	= ch_from_account_o;
		ch_to_account	= ch_to_account_o;
		ch_payee		= ch_payee_o;
		ch_fromPayee	= ch_fromPayee_o;
		ch_toPayee		= ch_toPayee_o;
		ch_currency		= ch_currency_o;
		ch_from_check	= ch_from_check_o;
		ch_to_check		= ch_to_check_o;
		ch_from_amount	= ch_from_amount_o;
		ch_to_amount	= ch_to_amount_o;
		ch_check_status	= ch_check_status_o;
		ch_log_date		= ch_log_date_o;
		ch_log_user		= ch_log_user_o;
		ch_source		= ch_source_o;
	}
	//
	public void ShowParams () {
		moduleName	= "ShowParams: ";
		PrintLog("ch_from_period	= " + ch_from_period);
		PrintLog("ch_to_period		= " + ch_to_period);
		PrintLog("ch_from_date		= " + ch_from_date);
		PrintLog("ch_to_date		= " + ch_to_date);
		PrintLog("ch_from_account	= " + ch_from_account);
		PrintLog("ch_to_account		= " + ch_to_account);
		PrintLog("ch_payee			= " + ch_payee);
		PrintLog("ch_fromPayee		= " + ch_fromPayee);
		PrintLog("ch_toPayee		= " + ch_toPayee);
		PrintLog("ch_currency		= " + ch_currency);
		PrintLog("ch_from_check		= " + ch_from_check);
		PrintLog("ch_to_check		= " + ch_to_check);
		PrintLog("ch_from_amount	= " + ch_from_amount);
		PrintLog("ch_to_amount		= " + ch_to_amount);
		PrintLog("ch_check_status	= " + ch_check_status);
		PrintLog("ch_log_date		= " + ch_log_date);
		PrintLog("ch_log_user		= " + ch_log_user);
		PrintLog("ch_source			= " + ch_source);
	}
	//
	public void InitParams () {
		ch_from_period	= "";
		ch_to_period	= "";
		ch_from_date	= "";
		ch_to_date		= "";
		ch_from_account	= "";
		ch_to_account	= "";
		ch_payee		= "";
		ch_fromPayee	= "";
		ch_toPayee		= "";
		ch_currency		= "";
		ch_from_check	= "";
		ch_to_check		= "";
		ch_from_amount	= "";
		ch_to_amount	= "";
		ch_check_status	= "";
		ch_log_date		= "";
		ch_log_user		= "";
		ch_source		= "";
		InitRowSEC();
	}
	 //
	public void clearNulls () {
		ch_from_period	= (ch_from_period == null) ? "" : ch_from_period;
		ch_to_period	= (ch_to_period == null) ? "" : ch_to_period;
		ch_from_date	= (ch_from_date == null) ? "" : ch_from_date;
		ch_to_date		= (ch_to_date == null) ? "" : ch_to_date;
		ch_from_account	= (ch_from_account == null) ? "" : ch_from_account;
		ch_to_account	= (ch_to_account == null) ? "" : ch_to_account;
		ch_payee		= (ch_payee == null) ? "" : ch_payee;
		ch_fromPayee	= (ch_fromPayee == null) ? "" : ch_fromPayee;
		ch_toPayee		= (ch_toPayee == null) ? "" : ch_toPayee;
		ch_currency		= (ch_currency == null) ? "" : ch_currency;
		ch_from_check	= (ch_from_check == null) ? "" : ch_from_check;
		ch_to_check		= (ch_to_check == null) ? "" : ch_to_check;
		ch_from_amount	= (ch_from_amount == null) ? "" : ch_from_amount;
		ch_to_amount	= (ch_to_amount == null) ? "" : ch_to_amount;
		ch_check_status	= (ch_check_status == null) ? "" : ch_check_status;
		ch_log_date		= (ch_log_date == null) ? "" : ch_log_date;
		ch_log_user		= (ch_log_user == null) ? "" : ch_log_user;
		ch_source		= (ch_source == null) ? "" : ch_source;
	}
   //
	public DepsDetail[] getCheckrows() {
		moduleName	= "getCheckrows: ";
		DepsDetail results[] = new DepsDetail [checkrows.size()];
		Iterator<DepsDetail> chex = checkrows.iterator();
		int n = 0;
		while (chex.hasNext()) {
			results[n++] = (DepsDetail) chex.next();
		}
		//PrintLog("Detail count "+n);
		return (results);
	}
	//
	public ArrayList <DepsDetail>getChexRowsArray() {
		return (this.checkrows);
	}
	//
	public DepsDetail getArow() {
		DepsDetail results = new DepsDetail ();
		Iterator<DepsDetail> chex = checkrows.iterator();
		results = (DepsDetail) chex.next();
		return(results);
	}
	//
	public DepsDetail getArow(int recIndex) {
		DepsDetail results = new DepsDetail ();
		//Iterator chex = checkrows.iterator();
		this.recIndex	= recIndex;
		results = (DepsDetail) checkrows.get(recIndex);
		return(results);
	}
	//
	public void setCheckrows (ArrayList<?> check_row) {
		Iterator<?> rows = check_row.iterator();
		int n	= 0;
		while (rows.hasNext()) {
			this.checkrows.add(n, (DepsDetail)rows.next());
			n++;
		}
		detail_count	= n;
	}
	//
	public void setModifyRow (DepsDetail modifyRow) {
		this.modifyRow	= modifyRow;
	}
	//
	public DepsDetail getModifyRow () {
		return(this.modifyRow);
	}
	//
	public DepsSummary[] getSummaryrows() {
		DepsSummary results[] = new DepsSummary [summaryrows.size()];
		Iterator<DepsSummary> summ = summaryrows.iterator();
		int n = 0;
		while (summ.hasNext()) {
			results[n++] = (DepsSummary) summ.next();
		}
		return (results);
	}
	//
	public void setSummaryrows (ArrayList<?> summary_row) {
		Iterator<?> rows = summary_row.iterator();
		while (rows.hasNext()) {
			this.summaryrows.add((DepsSummary)rows.next());
		}
	}
	//
	public ArrayList<String> getHist_tables() {
		return (this.hist_tables);
	}
	//
	public void setHist_tables (String hist_table_row) {
		this.hist_tables.add(hist_table_row);
	}
	//
	private void SetParamVars () {
		moduleName	= "SetParamVars: ";
		initTempParams();
		clearNulls();
		if (!ch_from_account.equals("ALL")) {
			from_acct	= ch_from_account.trim();
		}
		if (!ch_to_account.equals("NONE")) {
			to_acct	= ch_to_account.trim();
		}
		//PrintLog("Ch creditor: "+ch_creditor);
		if (!ch_payee.equals("ALL")) {
			payee_sel	= ch_payee.trim();
		}
		if (!ch_fromPayee.equals("")) {
			fromPayee_sel	= ch_fromPayee.trim();
		}
		if (!ch_toPayee.equals("")) {
			toPayee_sel	= ch_toPayee.trim();
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
			to_amt	= ch_to_amount.trim();
		}
		if (!ch_check_status.equals("ALL")) {
			chex_status	= ch_check_status.trim();
		}
		if (!ch_log_date.equals("ALL")) {
			log_date	= ch_log_date.trim();
		}
		if (!ch_log_user.equals("ALL")) {
			log_user	= this.ch_log_user.trim();
		}
		//PrintLog("ch_source: " + ch_source);
		if (!ch_source.equals("ALL")) {
			chSource	= this.ch_source.trim();
		}
	}
	//
	public void initTempParams () {
		from_period		= "";
		to_period		= "";
		from_acct		= "";
		to_acct			= "";
		currency_sel	= "";
		payee_sel		= "";
		fromPayee_sel	= "";
		toPayee_sel		= "";
		from_check		= "";
		to_check		= "";
		from_amt		= "";
		to_amt			= "";
		chex_status		= "";
		from_date		= "";
		to_date			= "";
		log_date		= "";
		log_user		= "";
		chSource		= "";
		//
		acc_param		= "";
		curr_param		= "";
		payee_param		= "";
		cred_param		= "";
		check_param		= "";
		amt_param		= "";
		status_param	= "";
		date_param		= "";
		log_date_param	= "";
		log_user_param	= "";
		sourceParam		= "";
	}
	//
	public String GetParams () {
		moduleName	= "GetParams: ";
		param		= "";
		SetParamVars();
		moduleName	= "GetParams: ";
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param	= "chex_account_num='" + from_acct + "' ";
			//"(chex_account_num='"+from_acct+"' OR chex_account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param	= " chex_account_num between '" + from_acct + 
								"' AND '" + to_acct + "'";
			} else {
				acc_param	= " chex_account_num='" + to_acct + "' ";
			}
		}
		if (!payee_sel.equals("")) {
			cred_param	= " chex_payee='" + payee_sel + "' ";
		}
		if (!fromPayee_sel.equals("")) {
			payee_param	= " chex_payee='" + fromPayee_sel + "' ";
		}
		if (!toPayee_sel.equals("")) {
			if (!cred_param.equals("")) {
				cred_param	= " chex_payee between '" + fromPayee_sel + 
								"' AND '" + toPayee_sel + "'";
			} else {
				cred_param	= " chex_payee='" + toPayee_sel + "' ";
			}
		}
		if (!from_check.equals("")) {
			check_param	= " chex_check_num='" + from_check + "' ";
			//"(chex_check_num='"+from_check+"' "+" OR chex_check_num>'"+from_check+"') ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param	= " chex_check_num between '" + from_check + 
								"' AND '" + to_check + "'";
			} else {
				check_param	= " chex_check_num='" + to_check + "' ";
			}
		}
		if (!from_amt.equals("")) {
			amt_param	= " chex_check_amount='" + from_amt + "' ";
			//"(chex_check_num='"+from_check+"' "+" OR chex_check_num>'"+from_check+"') ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param	= " chex_check_amount between '" + from_amt + 
								"' AND '" + to_amt + "'";
			} else {
				amt_param	= " chex_check_amount='" + to_amt + "' ";
			}
		}
		if (!chex_status.equals("")) {
			status_param	= " chex_check_status='" + chex_status + "' ";
		}
		chSource	= this.chexSource;
		//PrintLog("chSource: "+chSource);
		if (!chSource.equals("")) {
			sourceParam	= " chex_source='" + chSource + "' ";
		}
		//
		//param	= " chex_source='" + this.chexSource + "' ";
		if (!acc_param.equals("")) {
			param	= acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals("")) {
				param	+= " AND " + curr_param;
			} 
		} else {
			param	= curr_param;
		}
		if (!param.equals("")) {
			if (!payee_param.equals("")) {
				param	+= " AND " + payee_param;
			} 
		} else {
			param	= payee_param;
		}
		if (!param.equals("")) {
			if (!cred_param.equals("")) {
				param	+= " AND " + cred_param;
			}
		} else {
			param	= cred_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals("")) {
				param	+= " AND " + check_param;
			}
		} else {
			param	= check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals("")) {
				param	+= " AND " + amt_param;
			} 
		} else {
			param	= amt_param;
		}
		if (!param.equals("")) {
			if (!status_param.equals("")) {
				param	+= " AND " + status_param;
			} 
		} else {
			param	= status_param;
		}
		if (!param.equals("")) {
			if (!sourceParam.equals("")) {
				param	+= " AND " + sourceParam;
			}
		} else {
			param	= sourceParam;
		}
		if (!param.equals("")) {
			param	= " WHERE " + param;
		}
		param	+= " ORDER BY chex_account_num, chex_check_num";
		PrintLog(" Param  "+param);
		return (param);
	}
	//
	public String GetLogHistParams () {
		moduleName	= "GetLogHistParams: ";
		param		= "";
		hist_range.clear();
		SetParamVars();
		//
		from_period	= ch_from_period.trim();
		if (!ch_to_period.equals("NONE")) {
			to_period	= ch_to_period.trim();
		}
		if (ch_from_date == null) {
			ch_from_date	= "";
		}
		if (!ch_from_date.equals("")) {
			from_date	= ch_from_date.trim();
		}
		if (ch_to_date == null) {
			ch_to_date	= "";
		}
		if (!ch_to_date.equals("")) {
			to_date	= ch_to_date.trim();
		}
		//
		PrintLog("From Period: "+from_period);
		PrintLog("From Date: "+from_date);
		moduleName	= "GetLogHistParams: ";
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		hist_range.add(from_period);
		if (!to_period.equals("")) {
			hist_range.add(to_period);
		}
		if (from_date.equals("") && to_date.equals("")) {
			// Case 3.
			//from_period_param	= from_period;
		} else if (!from_date.equals("") && !to_date.equals("")) {
			date_param	= " chex_proc_date between '"+ from_date +
					"' AND '" + to_date + "' ";
		} else if (!from_date.equals("")) {
			date_param	= " chex_proc_date='"+ from_date + "' ";
		} else if (!to_date.equals("")) {
			date_param	= " chex_proc_date='"+ to_date + "' ";
		}
		PrintLog("Date Param  "+date_param);
		if (!from_acct.equals("")) {
			acc_param	= "chex_account_num='" + from_acct + "' ";
			//acc_param	= "chex_orig_account_num='" + from_acct + "' ";
			//"(chex_account_num='"+from_acct+"' OR chex_account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param	= " chex_account_num between '" + from_acct + 
								"' AND '" + to_acct + "'";
				//acc_param	= " chex_orig_account_num between '" + 
				//				from_acct + "' AND '" + to_acct + "'";
			} else {
				acc_param	= " chex_account_num='" + to_acct + "' ";
				//acc_param	= " chex_orig_account_num='" + to_acct + "' ";
			}
		}
		PrintLog("Acc Param  "+acc_param);
		if (!ch_currency.equals("")) {
			curr_param	= " chex_check_currency='" + ch_currency + "' ";
		}
		PrintLog("Curr Param  "+curr_param);
		if (!from_check.equals("")) {
			check_param	= " chex_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param	= " chex_check_num between '" + from_check + 
							  "' AND '" + to_check + "'";
			} else {
				check_param	= " chex_check_num='" + to_check + "' ";
			}
		}
		PrintLog("Check Param  "+check_param);
		if (!from_amt.equals("")) {
			amt_param	= " chex_check_amount='" + from_amt + "' ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param	= " chex_check_amount between '" + from_amt + 
							  "' AND '" + to_amt + "'";
			} else {
				amt_param	= " chex_check_amount='" + to_amt + "' ";
			}
		}
		PrintLog("Amt Param  "+amt_param);
		//
		// Assemble the where clause for sql
		//
		if (!acc_param.equals("")) {
			param	= acc_param;
		}
		PrintLog("Acc Param  "+param);
		if (!param.equals("")) {	
			if (!curr_param.equals(""))
				param	+= " AND " + curr_param;
		} else {
			param	= curr_param;
		}
		PrintLog("Curr Param  "+param);
		if (!param.equals("")) {
			if (!check_param.equals(""))
				param	+= " AND " + check_param;
		} else {
			param	= check_param;
		}
		PrintLog("Check Param  "+param);
		if (!param.equals("")) {
			if (!amt_param.equals(""))
				param	+= " AND " + amt_param;
		} else {
			param	= amt_param;
		}
		PrintLog("Amount Param  "+param);
		if (!param.equals("")) {
			if (!date_param.equals(""))
				param	+= " AND " + date_param;
		} else {
			param	= date_param;
		}
		PrintLog("Date Param  "+param);
		if (!param.equals("")) {
			param	= " WHERE " + param;
		}
		PrintLog("final Param  "+param);
		param	+= " ORDER BY chex_account_num, chex_check_num, modtime DESC";
		PrintLog(" Param  "+param);
		return (param);
	}
	//
	public String GetLogParams () {
		moduleName	= "GetLogParams: ";
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		param		= "";
		SetParamVars();
		moduleName	= "GetLogParams: ";
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param	= "chex_account_num='" + from_acct + "' ";
			//"(chex_account_num='"+from_acct+"' OR chex_account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param	= " chex_account_num between '" + from_acct + "' AND '" + to_acct + "'";
			} else {
				acc_param	= " chex_account_num='" + to_acct + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param	= " chex_check_currency='" + currency_sel + "' ";
		}
		if (!from_check.equals("")) {
			check_param	= " chex_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param	= " chex_check_num between '" + from_check + "' AND '" + to_check + "'";
			} else {
				check_param	= " chex_check_num='" + to_check + "' ";
			}
		}
		if (!from_amt.equals("")) {
			amt_param	= " chex_check_amount='" + from_amt + "' ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param	= " chex_check_amount between '" + from_amt + "' AND '" + to_amt + "'";
			} else {
				amt_param	= " chex_check_amount='" + to_amt + "' ";
			}
		}
		if (!chex_status.equals("")) {
			status_param	= " chex_check_status='" + chex_status + "' ";
		}
		if (!log_date.equals("")) {
			log_date_param	= " modtime LIKE '" + log_date + "%' ";
		}
		if (!log_user.equals("")) {
			log_user_param	= " moduser '" + log_user + "' ";
		}
		//
		//
		chSource	= this.chexSource;
		//PrintLog("ChexSource1: "+this.chexSource);
		if (!this.chexSource.equals("")) {
			param	= " CHEX_SOURCE='" + this.chexSource + "' ";
			//PrintLog("param1: "+param);
		}
		if (!param.equals("")) {
			if (!acc_param.equals("")) {
				param	+= acc_param;
			}
		} else {
			param	= acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals("")) {
				param	+= " AND " + curr_param;
			}
		} else {
			param	= curr_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals("")) {
				param	+= " AND " + check_param;
			}
		} else {
			param	= check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals("")) {
				param	+= " AND " + amt_param;
			}
		} else {
			param	= amt_param;
		}
		if (!param.equals("")) {
			if (!status_param.equals("")) {
				param	+= " AND " + status_param;
			}
		} else {
			param	= status_param;
		}
		if (!param.equals("")) {
			if (!log_date_param.equals("")) {
				param	+= " AND " + log_date_param;
			}
		} else {
			param	= log_date_param;
		}
		if (!param.equals("")) {
			if (!log_user_param.equals("")) {
				param	+= " AND " + log_user_param;
			}
		} else {
			param	= log_user_param;
		}
		if (!param.equals("")) {
			param	= " WHERE " + param;
			//PrintLog("param2: "+param);
		}
		param	+= " ORDER BY chex_account_num, chex_check_num, modtime DESC";
		PrintLog("Param: "+param);
		return (param);
	}
	//
	public String GetHistParams () {
		moduleName	= "GetHistParams: ";
		param		= "";
		hist_range.clear();
		SetParamVars();
		moduleName	= "GetHistParams: ";
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------

		from_period	= ch_from_period.trim();
		if (!ch_to_period.equals("NONE")) {
			to_period	= ch_to_period.trim();
		}
		if (!ch_from_date.equals("")) {
			from_date	= ch_from_date.trim();
		}
		if (!ch_to_date.equals("")) {
			to_date	= ch_to_date.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		// Case 1. Both Period/s and Date/s is/are chosen
		// Case 2. Either Period/s or Date/s chosen
		// Case 3. Neither Period/s nor Date/s is/are chosen
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
			//from_period_param	= from_period;
		} else if (!from_date.equals("") && !to_date.equals("")) {
			date_param	= " chex_proc_date between '"+ from_date +
					"' AND '" + to_date + "' ";
		} else if (!from_date.equals("")) {
			date_param	= " chex_proc_date='"+ from_date + "' ";
		} else if (!to_date.equals("")) {
			date_param	= " chex_proc_date='"+ to_date + "' ";
		}
		//
		// Payer ACCOUNT PARAM
		//
		if (!from_acct.equals("")) {
			acc_param	= "chex_account_num='" + from_acct + "' ";
			//"(chex_account_num='"+from_acct+"' OR chex_account_num>'"
			//+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param	= " chex_account_num between '" + from_acct +
						"' AND '" + to_acct + "'";
			} else {
				acc_param	= " chex_account_num='" + to_acct + "' ";
			}
		}
		//
		// Payee ACCOUNT PARAM
		//
		if (!fromPayee_sel.equals("")) {
			cred_param	= " chex_payeeacct='" + fromPayee_sel + "' ";
		}
		if (!toPayee_sel.equals("")) {
			if (!cred_param.equals("")) {
				cred_param	= " chex_payeeacct between '" + fromPayee_sel + 
								"' AND '" + toPayee_sel + "'";
			} else {
				cred_param	= " chex_payeeacct='" + toPayee_sel + "' ";
			}
		}
		//
		// CURRENCY PARAM
		//
		if (!currency_sel.equals("")) {
			curr_param	= " chex_check_currency='" + currency_sel + "' ";
		}
		//
		// CHECK NUMBER PARAM
		//
		if (!from_check.equals("")) {
			check_param	= " chex_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param	= " chex_check_num between '" + from_check +
						"' AND '" + to_check + "'";
			} else {
				check_param	= " chex_check_num='" + to_check + "' ";
			}
		}
		//
		// AMOUNT PARAM
		//
		if (!from_amt.equals("")) {
			amt_param	= " chex_check_amount='" + from_amt + "' ";
		}
		if (!to_amt.equals("")) {
			if (!amt_param.equals("")) {
				amt_param	= " chex_check_amount between '" + from_amt +
						"' AND '" + to_amt + "'";
			} else {
				amt_param	= " chex_check_amount='" + to_amt + "' ";
			}
		}
		//
		// STATUS PARAM
		//
		if (!chex_status.equals("")) {
			status_param	= " chex_check_status='" + chex_status + "' ";
		}
		//
		// SOURCE PARAM
		//
		//chSource	= this.chexSource;
		chSource	= this.ch_source;
		//PrintLog("chSource: "+chSource);
		if (!chSource.equals("ALL")) {
			sourceParam	= " chex_source='" + chSource + "' ";
		}
		//
		if (!acc_param.equals("")) {
			param	= acc_param;
		}
		if (!param.equals("")) {
			if (!curr_param.equals("")) {
				param	+= " AND " + curr_param;
			}
		} else {
			param	= curr_param;
		}
		if (!param.equals("")) {
			if (!cred_param.equals("")) {
				param	+= " AND " + cred_param;
			}
		} else {
			param	= cred_param;
		}
		if (!param.equals("")) {
			if (!check_param.equals("")) {
				param	+= " AND " + check_param;
			}
		} else {
			param	= check_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals("")) {
				param	+= " AND " + amt_param;
			}
		} else {
			param	= amt_param;
		}
		if (!param.equals("")) {
			if (!date_param.equals("")) {
				param	+= " AND " + date_param;
			}
		} else {
			param	= date_param;
		}
		if (!param.equals("")) {
			if (!status_param.equals("")) {
				param	+= " AND " + status_param;
			}
		} else {
			param	= status_param;
		}
		if (!param.equals("")) {
			if (!sourceParam.equals("")) {
				param	+= " AND " + sourceParam;
			} 
		} else {
				param	= sourceParam;
		}
		//if (!freqParam.equals("")) {
		//	param	+= freqParam;
		//}
		if (!param.equals("")) {
			param	= " WHERE " + param;
		}
		param	+= " ORDER BY chex_account_num, chex_check_num, chex_proc_date";
		PrintLog("Params: "+param);
		return (param);
	}
	//
	protected void finalize() {
		//System.out.println(java.util.Calendar.getInstance().getTime()+
		//		   "> ChexSelector: MemoryBlock finalized: "+this );
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
			this.rowEnd		= this.rowStart;
			this.rowStart	= this.rowStart - this.rowsDisplayed;
			if (this.rowStart < 0) {
				this.rowStart	= 0;
			}
		} else {
			if (this.rowStart < this.rowsDisplayed) {
				this.rowStart	= 0;
				this.rowEnd	= this.rowsDisplayed;
			}
		}
	}
}
