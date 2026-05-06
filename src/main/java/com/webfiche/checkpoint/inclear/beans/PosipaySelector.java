/*
 * ====================================================================
 */

package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

public final class PosipaySelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -- Instance Variables
	private int rowCount				= 0;
	private String row_Count			= "0";
	private String className			= "> PosipaySelector.";
	private String moduleName			= "";
	private String param				= "";
	private int fromRow					= 0;
	private int toRow					= 0;
	private int showRows				= 0;
	//
	private String userId;
	private String actionFlag			= "";
	private String accessFlag			= "";
	private String dbUsed				= "";
	private String appl_date			= "";
	private String bankId				= "";
	private String defCurr				= "";
	//
	private String pp_from_acct			= "";
	private String pp_to_acct			= "";
	private String pp_currency			= "";
	private String pp_from_check		= "";
	private String pp_to_check			= "";
	private String pp_check_amount		= "";
	private String pp_swift_ref			= "";
	private String pp_issue_date		= "";
	private String pp_status			= "";
	private String pp_log_date			= "";
	private String pp_log_user			= "";
	//
	private String pp_from_acct_o		= "";
	private String pp_to_acct_o			= "";
	private String pp_currency_o		= "";
	private String pp_from_check_o		= "";
	private String pp_to_check_o		= "";
	private String pp_check_amount_o	= "";
	private String pp_swift_ref_o		= "";
	private String pp_issue_date_o		= "";
	private String pp_status_o			= "";
	private String pp_log_date_o		= "";
	private String pp_log_user_o		= "";
	//
	private String imageURL				= "";
	private String imageDir				= "";
	//
	private ArrayList<String> acctList	= new ArrayList<String>();
	private ArrayList<String> dateList	= new ArrayList<String>();
	private ArrayList<String> userList	= new ArrayList<String>();
	//
	private ArrayList<PosipayDetail> posipayrows	= new ArrayList<PosipayDetail>();
	private Vector<Vector<String>> errorVec			= new Vector<Vector<String>>();
	//
	// Here deifne variables for controlling the number of checks displayed.
	// Use the checksPerView constant defined in web.xml
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
		posipayrows.clear();
		clearErrors();
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
	public String getPp_from_acct() {
		return (this.pp_from_acct);
	}
	public void setPp_from_acct(String pp_from_acct) {
		this.pp_from_acct = pp_from_acct;
	}
	//
	public String getPp_to_acct() {
		return (this.pp_to_acct);
	}
	public void setPp_to_acct(String pp_to_acct) {
		this.pp_to_acct = pp_to_acct;
	}
	//
	public String getPp_currency() {
		return (this.pp_currency);
	}
	public void setPp_currency(String pp_currency) {
		this.pp_currency = pp_currency;
	}
	//
	public String getPp_from_check() {
		return (this.pp_from_check);
	}
	public void setPp_from_check(String pp_from_check) {
		this.pp_from_check = pp_from_check;
	}
	//
	public String getPp_to_check() {
		return (this.pp_to_check);
	}
	public void setPp_to_check(String pp_to_check) {
		this.pp_to_check = pp_to_check;
	}
	//
	public String getPp_check_amount() {
		return (this.pp_check_amount);
	}
	public void setPp_check_amount(String pp_check_amount) {
		moduleName = "setPp_check_amount: ";
		this.pp_check_amount = pp_check_amount;
		// PrintLog(this.pp_check_amount);
	}
	//
	public String getPp_swift_ref() {
		return (this.pp_swift_ref);
	}
	public void setPp_swift_ref(String pp_swift_ref) {
		this.pp_swift_ref = pp_swift_ref;
	}
	//
	public String getPp_issue_date() {
		return (this.pp_issue_date);
	}
	public void setPp_issue_date(String pp_issue_date) {
		this.pp_issue_date = pp_issue_date;
	}
	//
	public String getPp_status() {
		return (this.pp_status);
	}
	public void setPp_status(String pp_status) {
		this.pp_status = pp_status;
	}
	//
	public String getPp_log_date() {
		return (this.pp_log_date);
	}
	public void setPp_log_date(String pp_log_date) {
		this.pp_log_date = pp_log_date;
	}
	//
	public String getPp_log_user() {
		return (this.pp_log_user);
	}
	public void setPp_log_user(String pp_log_user) {
		this.pp_log_user = pp_log_user;
	}
	//
	public ArrayList<String> getAcctList() {
		return (this.acctList);
	}
	public void setAcctList(ArrayList<String> acctList) {
		clearAcctList();
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
	public PosipayDetail[] getPosipayrows() {
		PosipayDetail results[] = new PosipayDetail[posipayrows.size()];
		Iterator<PosipayDetail> ppays = posipayrows.iterator();
		int n = 0;
		while (ppays.hasNext()) {
			results[n++] = (PosipayDetail) ppays.next();
		}
		return (results);
	}
	//
	public PosipayDetail getArow() {
		PosipayDetail results = new PosipayDetail();
		Iterator<PosipayDetail> ppays = posipayrows.iterator();
		results = (PosipayDetail) ppays.next();
		return (results);
	}
	//
	public void setPosipayrows(ArrayList<?> posipay_row) {
		Iterator<?> rows = posipay_row.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.posipayrows.add((PosipayDetail) rows.next());
			n++;
		}
		rowCount = n;
	}
	//
	public void SaveParams() {
		pp_from_acct_o = pp_from_acct;
		pp_to_acct_o = pp_to_acct;
		pp_currency_o = pp_currency;
		pp_from_check_o = pp_from_check;
		pp_to_check_o = pp_to_check;
		pp_check_amount_o = pp_check_amount;
		pp_swift_ref_o = pp_swift_ref;
		pp_issue_date_o = pp_issue_date;
		pp_status_o = pp_status;
		pp_log_date_o = pp_log_date;
		pp_log_user_o = pp_log_user;
	}
	//
	public void RestoreParams() {
		pp_from_acct = pp_from_acct_o;
		pp_to_acct = pp_to_acct_o;
		pp_currency = pp_currency_o;
		pp_from_check = pp_from_check_o;
		pp_to_check = pp_to_check_o;
		pp_check_amount = pp_check_amount_o;
		pp_swift_ref = pp_swift_ref_o;
		pp_issue_date = pp_issue_date_o;
		pp_status = pp_status_o;
		pp_log_date = pp_log_date_o;
		pp_log_user = pp_log_user_o;
	}
	//
	public void ShowParams() {
		moduleName = "ShowParams: ";
		PrintLog("pp_from_acct\t\t" + pp_from_acct);
		PrintLog("pp_to_acct\t\t" + pp_to_acct);
		PrintLog("pp_currency\t\t" + pp_currency);
		PrintLog("pp_from_check\t\t" + pp_from_check);
		PrintLog("pp_to_check\t\t" + pp_to_check);
		PrintLog("pp_check_amount\t\t" + pp_check_amount);
		PrintLog("pp_swift_ref\t\t" + pp_swift_ref);
		PrintLog("pp_issue_date\t\t" + pp_issue_date);
		PrintLog("pp_status\t\t" + pp_status);
		PrintLog("pp_log_date\t\t" + pp_log_date);
		PrintLog("pp_log_user\t\t" + pp_log_user);
	}
	//
	public void clearNulls() {
		pp_currency = (pp_currency == null) ? "" : pp_currency;
		pp_from_check = (pp_from_check == null) ? "" : pp_from_check;
		pp_to_check = (pp_to_check == null) ? "" : pp_to_check;
		pp_check_amount = (pp_check_amount == null) ? "" : pp_check_amount;
		pp_swift_ref = (pp_swift_ref == null) ? "" : pp_swift_ref;
		pp_issue_date = (pp_issue_date == null) ? "" : pp_issue_date;
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
	public String GetParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName = "> InclPosiUtil.GetParams: ";
		param = "";
		//
		clearNulls();
		//
		// System.out.println (java.util.Calendar.getInstance().getTime()+
		// module_name+"Amount->" + pp_check_amount);

		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String from_acct = "";
		String to_acct = "";
		String currency_sel = "";
		String from_check = "";
		String to_check = "";
		String posi_amt = "";
		String sw_ref = "";
		String iss_date = "";
		String posi_status = "";
		//
		String acc_param = "";
		String curr_param = "";
		String check_param = "";
		String swref_param = "";
		String amt_param = "";
		String issdate_param = "";
		String status_param = "";
		//
		if (!pp_from_acct.equals("ALL")) {
			from_acct = pp_from_acct.trim();
		}
		if (!pp_to_acct.equals("NONE")) {
			to_acct = pp_to_acct.trim();
		}
		if (!pp_currency.equals("")) {
			currency_sel = pp_currency.trim();
		}
		if (!pp_from_check.equals("")) {
			from_check = pp_from_check.trim();
		}
		if (!pp_to_check.equals("")) {
			to_check = pp_to_check.trim();
		}
		if (!pp_check_amount.equals("")) {
			posi_amt = pp_check_amount.trim();
		}
		if (!pp_swift_ref.equals("")) {
			sw_ref = pp_swift_ref.trim();
		}
		if (!pp_issue_date.equals("")) {
			iss_date = pp_issue_date.trim();
		}
		if (!pp_status.equals("ALL")) {
			posi_status = pp_status.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param = "pospay_account_num='" + from_acct + "' ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " pospay_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = "pospay_account_num='" + to_acct + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = "pospay_currency='" + currency_sel + "' ";
		}
		if (!from_check.equals("")) {
			check_param = "pospay_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " pospay_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = "pospay_check_num='" + to_check + "' ";
			}
		}
		if (!sw_ref.equals("")) {
			swref_param = "pospay_swift_ref='" + sw_ref + "' ";
		}
		if (!iss_date.equals("")) {
			issdate_param = "pospay_issue_date='" + iss_date + "' ";
		}
		if (!posi_amt.equals("") && !posi_amt.equals("0")) {
			amt_param = "(pospay_check_amount='" + posi_amt + "') ";
			// amt_param = "((pospay_check_amount>'" + posi_amt+
			// "') or (pospay_check_amount='" + posi_amt + "')) ";
		}
		if (!posi_status.equals("")) {
			status_param = "pospay_status='" + posi_status + "' ";
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
		param += " ORDER BY pospay_account_num, pospay_check_num";
		// System.out.println (java.util.Calendar.getInstance().getTime()+
		// module_name+"Param->" + param);
		return (param);
	}
	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		String module_name = "> InclPosiUtil.GetLogParams: ";
		param = "";
		//
		clearNulls();
		//
		String from_acct = "";
		String to_acct = "";
		String currency_sel = "";
		String from_check = "";
		String to_check = "";
		String posi_amt = "0";
		String sw_ref = "";
		String iss_date = "";
		String posi_status = "";
		String log_date = "";
		String log_user = "";

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
		if (!pp_from_acct.equals("ALL")) {
			from_acct = pp_from_acct.trim();
		}
		if (!pp_to_acct.equals("NONE")) {
			to_acct = pp_to_acct.trim();
		}
		if (!pp_currency.equals("")) {
			currency_sel = pp_currency.trim();
		}
		if (!pp_from_check.equals("")) {
			from_check = pp_from_check.trim();
		}
		if (!pp_to_check.equals("")) {
			to_check = pp_to_check.trim();
		}
		if (!pp_check_amount.equals("")) {
			posi_amt = pp_check_amount.trim();
		}
		if (!pp_swift_ref.equals("")) {
			sw_ref = pp_swift_ref.trim();
		}
		if (!pp_issue_date.equals("")) {
			iss_date = pp_issue_date.trim();
		}
		if (!pp_status.equals("ALL")) {
			posi_status = pp_status.trim();
		}
		if (!pp_log_date.equals("ALL")) {
			log_date = pp_log_date.trim();
		}
		if (!pp_log_user.equals("ALL")) {
			log_user = pp_log_user.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param = "pplog_account_num='" + from_acct + "' ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " pplog_account_num between '" + from_acct
						+ "' AND '" + to_acct + "'";
			} else {
				acc_param = "pplog_account_num='" + to_acct + "' ";
			}
		}
		if (!currency_sel.equals("")) {
			curr_param = "pplog_currency='" + currency_sel + "' ";
		}
		if (!from_check.equals("")) {
			check_param = "pplog_check_num='" + from_check + "' ";
		}
		if (!to_check.equals("")) {
			if (!check_param.equals("")) {
				check_param = " pplog_check_num between '" + from_check
						+ "' AND '" + to_check + "'";
			} else {
				check_param = "pplog_check_num='" + to_check + "' ";
			}
		}
		if (!sw_ref.equals("")) {
			swref_param = "pplog_swift_ref='" + sw_ref + "' ";
		}
		if (!iss_date.equals("")) {
			issdate_param = "pplog_issue_date='" + iss_date + "' ";
		}
		if (!posi_amt.equals("") && !posi_amt.equals("0")) {
			amt_param = "(pplog_check_amount='" + posi_amt + "') ";
			// amt_param = ("((pplog_check_amount>'" + posi_amt
			// + "') or (pplog_check_amount='" + posi_amt + "')) ";)
		}
		if (!posi_status.equals("")) {
			status_param = "pplog_status='" + posi_status + "' ";
		}
		if (!log_date.equals("")) {
			log_date_param = " SUBSTR(pplog_last_modified,1,9) LIKE'"
					+ log_date + "%' ";
		}
		if (!log_user.equals("")) {
			log_user_param = " pplog_mod_user='" + log_user + "' ";
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
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ module_name + "Param->" + param);
		param += " ORDER BY pplog_account_num, pplog_check_num, pplog_last_modified DESC";
		return (param);
	}
}
