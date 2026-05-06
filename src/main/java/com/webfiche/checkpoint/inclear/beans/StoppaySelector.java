package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

public final class StoppaySelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -- Instance Variables
	private int rowCount				= 0;
	private String row_Count			= "0";
	private String className			= "> StoppaySelector.";
	private String moduleName			= "";
	private int fromRow					= 0;
	private int toRow					= 0;
	private int showRows				= 0;
	private int detailCount				= 0;
	//
	private String param				= "";
	private String userId;
	private String actionFlag			= "";
	private String accessFlag			= "";
	private String dbUsed				= "";
	private String appl_date			= "";
	private String bankId				= "";
	private String defCurr				= "";
	//
	private String sp_from_acct			= "";
	private String sp_to_acct			= "";
	private String sp_from_check		= "";
	private String sp_to_check			= "";
	private String sp_currency			= "";
	private String sp_check_amount		= "";
	private String sp_swift_ref			= "";
	private String sp_issue_date		= "";
	private String sp_status			= "";
	private String sp_log_date			= "";
	private String sp_log_user			= "";
	//
	private String sp_from_acct_o		= "";
	private String sp_to_acct_o			= "";
	private String sp_from_check_o		= "";
	private String sp_to_check_o		= "";
	private String sp_currency_o		= "";
	private String sp_check_amount_o	= "";
	private String sp_swift_ref_o		= "";
	private String sp_issue_date_o		= "";
	private String sp_status_o			= "";
	private String sp_log_date_o		= "";
	private String sp_log_user_o		= "";
	//
	private String imageURL				= "";
	private String imageDir				= "";
	//
	private ArrayList<StoppayDetail> stoppayrows	= new ArrayList<StoppayDetail>();
	private ArrayList<String> acctList				= new ArrayList<String>();
	private ArrayList<String> dateList				= new ArrayList<String>();
	private ArrayList<String> userList				= new ArrayList<String>();
	private Vector<Vector<String>> errorVec			= new Vector<Vector<String>>();
	//
	private int rowStart		= 0;
	private int rowEnd			= 20;
	private int rowsDisplayed	= 20;
	private String rowsDispStr	= "";
	//
	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + userId + " - " + logMsg);
	}
	//
	public void clearRows() {
		stoppayrows.clear();
		this.rowCount	= 0;
	}
	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public void clearAcctList() {
		acctList.clear();
	}
	//
	public void clearDateList() {
		dateList.clear();
	}
	//
	public void clearUserList() {
		userList.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}
	//
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public int getRowCount() {
		return (this.rowCount);
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	//
	public String getRow_Count() {
		return (this.row_Count);
	}
	public void setRow_Count(String row_Count) {
		this.row_Count = row_Count;
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
	public int getDetailCount() {
		return (this.detailCount);
	}
	public void setDetailCount(int detailCount) {
		this.detailCount = detailCount;
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
	public String getAppl_date() {
		return (this.appl_date);
	}
	public void setAppl_date(String appl_date) {
		this.appl_date = appl_date;
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
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public String getSp_from_acct() {
		return (this.sp_from_acct);
	}
	public void setSp_from_acct(String sp_from_acct) {
		this.sp_from_acct = sp_from_acct;
	}
	//
	public String getSp_to_acct() {
		return (this.sp_to_acct);
	}
	public void setSp_to_acct(String sp_to_acct) {
		this.sp_to_acct = sp_to_acct;
	}
	//
	public String getSp_from_check() {
		return (this.sp_from_check);
	}
	public void setSp_from_check(String sp_from_check) {
		this.sp_from_check = sp_from_check;
	}
	//
	public String getSp_to_check() {
		return (this.sp_to_check);
	}
	public void setSp_to_check(String sp_to_check) {
		this.sp_to_check = sp_to_check;
	}
	//
	public String getSp_currency() {
		return (this.sp_currency);
	}
	public void setSp_currency(String sp_currency) {
		this.sp_currency = sp_currency;
	}
	//
	public String getSp_check_amount() {
		return (this.sp_check_amount);
	}
	public void setSp_check_amount(String sp_check_amount) {
		this.sp_check_amount = sp_check_amount;
	}
	//
	public String getSp_swift_ref() {
		return (this.sp_swift_ref);
	}
	public void setSp_swift_ref(String sp_swift_ref) {
		this.sp_swift_ref = sp_swift_ref;
	}
	//
	public String getSp_issue_date() {
		return (this.sp_issue_date);
	}
	public void setSp_issue_date(String sp_issue_date) {
		this.sp_issue_date = sp_issue_date;
	}
	//
	public String getSp_status() {
		return (this.sp_status);
	}
	public void setSp_status(String sp_status) {
		this.sp_status = sp_status;
	}
	//
	public String getSp_log_date() {
		return (this.sp_log_date);
	}
	public void setSp_log_date(String sp_log_date) {
		this.sp_log_date = sp_log_date;
	}
	//
	public String getSp_log_user() {
		return (this.sp_log_user);
	}
	public void setSp_log_user(String sp_log_user) {
		this.sp_log_user = sp_log_user;
	}
	//
	public ArrayList<String> getAcctList() {
		return (this.acctList);
	}
	public void setAcctList(ArrayList<String> acctList) {
		Iterator<String> accts = acctList.iterator();
		int n = 0;
		while (accts.hasNext()) {
			this.acctList.add(n, (String) accts.next());
			n++;
		}
		if (n == 0) {
			this.acctList.add(" ");
		}
	}
	//
	public ArrayList<String> getDateList() {
		return (this.dateList);
	}
	public void setDateList(ArrayList<String> dateList) {
		clearDateList();
		Iterator<String> dates = dateList.iterator();
		int n = 0;
		while (dates.hasNext()) {
			this.dateList.add(n, (String) dates.next());
			n++;
		}
		if (n == 0) {
			this.dateList.add(" ");
		}
	}
	//
	public ArrayList<String> getUserList() {
		return (this.userList);
	}
	public void setUserList(ArrayList<String> userList) {
		clearUserList();
		Iterator<String> users = userList.iterator();
		int n = 0;
		while (users.hasNext()) {
			this.userList.add(n, (String) users.next());
			n++;
		}
		if (n == 0) {
			this.userList.add(" ");
		}
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
	//
	public StoppayDetail[] getStoppayrows() {
		StoppayDetail results[] = new StoppayDetail[stoppayrows.size()];
		Iterator<StoppayDetail> spays = stoppayrows.iterator();
		int n = 0;
		while (spays.hasNext()) {
			results[n++] = (StoppayDetail) spays.next();
		}
		return (results);
	}
	//
	public StoppayDetail getArow() {
		StoppayDetail results = new StoppayDetail();
		Iterator<StoppayDetail> spays = stoppayrows.iterator();
		results = (StoppayDetail) spays.next();
		return (results);
	}
	//
	public void setStoppayrows(ArrayList<?> stoppay_row) {
		Iterator<?> rows = stoppay_row.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.stoppayrows.add((StoppayDetail) rows.next());
			n++;
		}
		rowCount = n;
	}
	//
	public void SaveParams() {
		sp_from_acct_o		= sp_from_acct;
		sp_to_acct_o		= sp_to_acct;
		sp_currency_o		= sp_currency;
		sp_from_check_o		= sp_from_check;
		sp_to_check_o		= sp_to_check;
		sp_check_amount_o	= sp_check_amount;
		sp_swift_ref_o		= sp_swift_ref;
		sp_issue_date_o		= sp_issue_date;
		sp_status_o			= sp_status;
		sp_log_date_o		= sp_log_date;
		sp_log_user_o		= sp_log_user;
	}
	//
	public void RestoreParams() {
		sp_from_acct		= sp_from_acct_o;
		sp_to_acct			= sp_to_acct_o;
		sp_currency			= sp_currency_o;
		sp_from_check		= sp_from_check_o;
		sp_to_check			= sp_to_check_o;
		sp_check_amount		= sp_check_amount_o;
		sp_swift_ref		= sp_swift_ref_o;
		sp_issue_date		= sp_issue_date_o;
		sp_status			= sp_status_o;
		sp_log_date			= sp_log_date_o;
		sp_log_user			= sp_log_user_o;
	}
	//
	public void ShowParams() {
		moduleName = "ShowParams: ";
		PrintLog("sp_from_acct\t\t" + sp_from_acct);
		PrintLog("sp_to_acct\t\t" + sp_to_acct);
		PrintLog("sp_currency\t\t" + sp_currency);
		PrintLog("sp_from_check\t\t" + sp_from_check);
		PrintLog("sp_to_check\t\t" + sp_to_check);
		PrintLog("sp_check_amount\t\t" + sp_check_amount);
		PrintLog("sp_swift_ref\t\t" + sp_swift_ref);
		PrintLog("sp_issue_date\t\t" + sp_issue_date);
		PrintLog("sp_status\t\t" + sp_status);
		PrintLog("sp_log_date\t\t" + sp_log_date);
		PrintLog("sp_log_user\t\t" + sp_log_user);
	}
	//
	public void clearNulls() {
		sp_from_acct = (sp_from_acct == null) ? "" : sp_from_acct;
		sp_to_acct = (sp_to_acct == null) ? "" : sp_to_acct;
		sp_currency = (sp_currency == null) ? "" : sp_currency;
		sp_from_check = (sp_from_check == null) ? "" : sp_from_check;
		sp_to_check = (sp_to_check == null) ? "" : sp_to_check;
		sp_check_amount = (sp_check_amount == null) ? "" : sp_check_amount;
		sp_swift_ref = (sp_swift_ref == null) ? "" : sp_swift_ref;
		sp_issue_date = (sp_issue_date == null) ? "" : sp_issue_date;
		sp_status = (sp_status == null) ? "" : sp_status;
		sp_log_date = (sp_log_date == null) ? "" : sp_log_date;
		sp_log_user = (sp_log_user == null) ? "" : sp_log_user;
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
		//PrintLog("rowsDisplayed: " + rowsDisplayed);
		if (rowsDisplayed <= 500) {
			this.rowsDisplayed = rowsDisplayed;
		} else {
			this.rowsDisplayed = 500;
		}
		if (this.rowsDisplayed > this.rowCount) {
			this.rowsDisplayed = this.rowCount;
		}
		setRowsDispStr(""+this.rowsDisplayed);
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
	//
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
		this.rowStart = 0;
		this.rowEnd = 20;
		this.rowsDisplayed = 20;
	}
	//
	public String GetParams() {
		// String module_name = "> InclStopUtil.GetParams: ";
		param = "";
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_acct		= "";
		String to_acct			= "";
		String from_check		= "";
		String to_check			= "";
		String currency_sel		= "";
		String stop_amt			= "";
		String sw_ref			= "";
		String iss_date			= "";
		String stop_status		= "";
		//
		String acc_param		= "";
		String curr_param		= "";
		String check_param		= "";
		String swref_param		= "";
		String amt_param		= "";
		String issdate_param	= "";
		String status_param		= "";
		//
		clearNulls();
		if (!sp_from_acct.equals("ALL")) {
			from_acct = sp_from_acct.trim();
		}
		if (!sp_to_acct.equals("NONE")) {
			to_acct = sp_to_acct.trim();
		}
		if (!sp_from_check.equals("")) {
			from_check = sp_from_check.trim();
		}
		if (!sp_to_check.equals("")) {
			to_check = sp_to_check.trim();
		}
		if (!sp_currency.equals("")) {
			currency_sel = sp_currency.trim();
		}
		if (!sp_check_amount.equals("")) {
			stop_amt = sp_check_amount.trim();
		}
		if (!sp_swift_ref.equals("")) {
			sw_ref = sp_swift_ref.trim();
		}
		if (!sp_issue_date.equals("")) {
			iss_date = sp_issue_date.trim();
		}
		if (!sp_status.equals("")) {
			stop_status = sp_status.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param = "stopay_account_num='" + from_acct + "' ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " stopay_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = "stopay_account_num='" + to_acct + "' ";
			}
		}
		if (!from_check.equals("")) {
			check_param = "stopay_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " stopay_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = "stopay_check_num='" + to_check + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = "stopay_currency='" + currency_sel + "' ";
		}
		if (!sw_ref.equals("")) {
			swref_param = "stopay_swift_ref='" + sw_ref + "' ";
		}
		if (!iss_date.equals("")) {
			issdate_param = "stopay_issue_date='" + iss_date + "' ";
		}
		if (!stop_amt.equals("") && !stop_amt.equals("0")) {
			amt_param = "(stopay_check_amount='" + stop_amt + "') ";
			// amt_param = "((stopay_check_amount>'" + stop_amt
			// + "') or (stopay_check_amount='" + stop_amt + "')) ";
		}
		if (!stop_status.equals("ALL")) {
			status_param = "stopay_status='" + stop_status + "' ";
		}
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
			if (!swref_param.equals(""))
				param += " AND " + swref_param;
		} else {
			param = swref_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals(""))
				param += " AND " + amt_param;
		} else {
			param = amt_param;
		}
		if (!param.equals("")) {
			if (!issdate_param.equals(""))
				param += " AND " + issdate_param;
		} else {
			param = issdate_param;
		}
		if (!param.equals("")) {
			if (!status_param.equals(""))
				param += " AND " + status_param;
		} else {
			param = status_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY stopay_account_num, stopay_check_num";
		// System.out.println (java.util.Calendar.getInstance().getTime()+
		// module_name+"GetParams Param->" + param);
		return (param);
	}
	//
	public String GetLogParams() {
		// String module_name = "> InclStopUtil.GetLogParams: ";
		param = "";
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_acct = "";
		String to_acct = "";
		String from_check = "";
		String to_check = "";
		String currency_sel = "";
		String stop_amt = "";
		String sw_ref = "";
		String iss_date = "";
		String stop_status = "";
		String log_date = "";
		String log_user = "";
		//
		String acc_param = "";
		String curr_param = "";
		String check_param = "";
		String swref_param = "";
		String amt_param = "";
		String issdate_param = "";
		String status_param = "";
		String log_date_param = "";
		String log_user_param = "";
		//
		clearNulls();
		if (!sp_from_acct.equals("ALL")) {
			from_acct = sp_from_acct.trim();
		}
		if (!sp_to_acct.equals("NONE")) {
			to_acct = sp_to_acct.trim();
		}
		if (!sp_from_check.equals("")) {
			from_check = sp_from_check.trim();
		}
		if (!sp_to_check.equals("")) {
			to_check = sp_to_check.trim();
		}
		if (!sp_currency.equals("")) {
			currency_sel = sp_currency.trim();
		}
		if (!sp_check_amount.equals("")) {
			stop_amt = sp_check_amount.trim();
		}
		if (!sp_swift_ref.equals("")) {
			sw_ref = sp_swift_ref.trim();
		}
		if (!sp_issue_date.equals("")) {
			iss_date = sp_issue_date.trim();
		}
		if (!sp_status.equals("")) {
			stop_status = sp_status.trim();
		}
		if (!sp_log_date.equals("ALL")) {
			log_date = sp_log_date.trim();
		}
		if (!sp_log_user.equals("ALL")) {
			log_user = sp_log_user.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param = "splog_account_num='" + from_acct + "' ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " splog_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = "splog_account_num='" + to_acct + "' ";
			}
		}
		if (!from_check.equals("")) {
			check_param = "splog_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " splog_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = "splog_check_num='" + to_check + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = "splog_currency='" + currency_sel + "' ";
		}
		if (!sw_ref.equals("")) {
			swref_param = "splog_swift_ref='" + sw_ref + "' ";
		}
		if (!iss_date.equals("")) {
			issdate_param = "splog_issue_date='" + iss_date + "' ";
		}
		if (!stop_amt.equals("") && !stop_amt.equals("0")) {
			amt_param = "(splog_check_amount='" + stop_amt + "') ";
			// amt_param = ("((splog_check_amount>'" + stop_amt
			// + "') or (splog_check_amount='" + stop_amt + "')) ");
		}
		if (!stop_status.equals("ALL")) {
			status_param = "splog_status='" + stop_status + "' ";
		}
		if (!log_date.equals("")) {
			log_date_param = "splog_last_modified LIKE'" + log_date + "%' ";
		}
		if (!log_user.equals("")) {
			log_user_param = "splog_mod_user='" + log_user + "' ";
		}
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
			if (!swref_param.equals(""))
				param += " AND " + swref_param;
		} else {
			param = swref_param;
		}
		if (!param.equals("")) {
			if (!amt_param.equals(""))
				param += " AND " + amt_param;
		} else {
			param = amt_param;
		}
		if (!param.equals("")) {
			if (!issdate_param.equals(""))
				param += " AND " + issdate_param;
		} else {
			param = issdate_param;
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
		param += " ORDER BY splog_account_num, splog_check_num, splog_last_modified DESC";
		return (param);
	}
}