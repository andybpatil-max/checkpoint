package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

import com.webfiche.checkpoint.deposits.beans.*;

public final class AccountSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID	= 1L;
	// -- Instance Variables
	private String className					= "> AccountSelector.";
	private String moduleName					= "";
	//private String param						= "";
	// private int recIndex	= 0;
	private int rowCount						= 0;
	private int fromRow							= 0;
	private int toRow							= 0;
	private int showRows						= 0;
	private String actionFlag					= "";
	private String accessFlag					= "";
	private String dbUsed						= "";
	private String appl_date					= "";
	private String bankId						= "";
	private String prodId						= "";
	private String defCurr						= "";
	private String userId;
	private String createAcctMap				= "";
	private String account_num_from				= "";
	private String account_num_to				= "";
	private String account_crdb					= "";
	private String account_currency				= "";
	private String account_sw_addr_sel			= "";
	private String account_max_check_amt_sel	= "";
	private String account_posi_pay_flag_sel	= "";
	private String account_posi_pay_amt_min_sel	= "";
	private String account_log_date				= "";
	private String account_log_user				= "";
	//
	private String account_num_from_o			= "";
	private String account_num_to_o				= "";
	private String account_currency_o			= "";
	private String account_sw_addr_sel_o		= "";
	private String account_max_check_amt_sel_o	= "";
	private String account_posi_pay_flag_sel_o	= "";
	private String account_posi_pay_amt_min_sel_o	= "";
	private String account_log_date_o			= "";
	private String account_log_user_o			= "";
	//
	private Vector<Vector<String>> errorVec	= new Vector<Vector<String>>();
	//private TreeMap<String, String> allAcctList	= new TreeMap<String, String>();
	private TreeMap<String, String> acctCustList	= new TreeMap<String, String>();
	//private TreeMap<String, String> payersList	= new TreeMap<String, String>();
	private ArrayList<String> acctList		= new ArrayList<String>();
	private ArrayList<String> dateList		= new ArrayList<String>();
	private ArrayList<String> userList		= new ArrayList<String>();
	private AccountDetail modifyRow			= new AccountDetail();
	private ArrayList<AccountDetail> accountrows		= new ArrayList<AccountDetail>();
	private ArrayList<DepsAccountDetail> depsAcctRows	= new ArrayList<DepsAccountDetail>();
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
	public void clearAcctList() {
		this.acctList.clear();
	}
	//
	public void clearDateList() {
		this.dateList.clear();
	}
	//
	public void clearUserList() {
		this.userList.clear();
	}
	//
	public void clearAcctCustList() {
		this.acctCustList.clear();
	}
	//
	public void clearRows() {
		this.accountrows.clear();
		this.rowCount	= 0;
	}
	//
	public void clearDepsAcctRows() {
		this.depsAcctRows.clear();
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
	public String getCreateAcctMap() {
		return (this.createAcctMap);
	}
	public void setCreateAcctMap(String createAcctMap) {
		this.createAcctMap = createAcctMap;
	}
	//
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public ArrayList<String> getAcctList() {
		return (this.acctList);
	}
	public void setAcctList(ArrayList<String> acctList) {
		Iterator<String> accts = acctList.iterator();
		clearAcctList();
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
		Iterator<String> dates = dateList.iterator();
		clearDateList();
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
		Iterator<String> users = userList.iterator();
		clearUserList();
		int n = 0;
		while (users.hasNext()) {
			this.userList.add(n, (String) users.next());
			n++;
		}
		if (n == 0) {
			this.userList.add(" ");
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
		this.rowStart = rowStart;
		this.rowEnd = rowEnd;
		this.rowsDisplayed = rowCount;
	}
	public void InitRowSEC() {
		clearErrors();
		this.rowStart = 0;
		this.rowEnd = 20;
		this.rowsDisplayed = 20;
	}
	//
	public TreeMap<String, String> getAcctCustList() {
		return (this.acctCustList);
	}
	public void setAcctCustList(TreeMap<String, String> acctCustList) {
		this.acctCustList = acctCustList;
	}
	//
	public String getAccount_num_from() {
		return (this.account_num_from);
	}
	public void setAccount_num_from(String account_num_from) {
		this.account_num_from = account_num_from;
	}
	//
	public String getAccount_num_to() {
		return (this.account_num_to);
	}
	public void setAccount_num_to(String account_num_to) {
		this.account_num_to = account_num_to;
	}
	//
	public String getAccount_crdb() {
		return (this.account_crdb);
	}
	public void setAccount_crdb(String account_crdb) {
		this.account_crdb = account_crdb;
	}
	//
	public String getAccount_currency() {
		return (this.account_currency);
	}
	public void setAccount_currency(String account_currency) {
		this.account_currency = account_currency;
	}
	//
	public String getAccount_sw_addr_sel() {
		return (this.account_sw_addr_sel);
	}
	public void setAccount_sw_addr_sel(String account_sw_addr_sel) {
		this.account_sw_addr_sel = account_sw_addr_sel;
	}
	//
	public String getAccount_max_check_amt_sel() {
		return (this.account_max_check_amt_sel);
	}
	public void setAccount_max_check_amt_sel(String account_max_check_amt_sel) {
		this.account_max_check_amt_sel = account_max_check_amt_sel;
	}
	//
	public String getAccount_posi_pay_flag_sel() {
		return (this.account_posi_pay_flag_sel);
	}
	public void setAccount_posi_pay_flag_sel(String account_posi_pay_flag_sel) {
		this.account_posi_pay_flag_sel = account_posi_pay_flag_sel;
	}
	//
	public String getAccount_posi_pay_amt_min_sel() {
		return (this.account_posi_pay_amt_min_sel);
	}
	public void setAccount_posi_pay_amt_min_sel(
			String account_posi_pay_amt_min_sel) {
		this.account_posi_pay_amt_min_sel = account_posi_pay_amt_min_sel;
	}
	//
	public String getAccount_log_date() {
		return (this.account_log_date);
	}
	public void setAccount_log_date(String account_log_date) {
		this.account_log_date = account_log_date;
	}
	//
	public String getAccount_log_user() {
		return (this.account_log_user);
	}
	public void setAccount_log_user(String account_log_user) {
		this.account_log_user = account_log_user;
	}
	//
	public void SaveParams() {
		account_num_from_o				= account_num_from;
		account_num_to_o				= account_num_to;
		account_currency_o				= account_currency;
		account_sw_addr_sel_o			= account_sw_addr_sel;
		account_max_check_amt_sel_o		= account_max_check_amt_sel;
		account_posi_pay_flag_sel_o		= account_posi_pay_flag_sel;
		account_posi_pay_amt_min_sel_o	= account_posi_pay_amt_min_sel;
		account_log_date_o				= account_log_date;
		account_log_user_o				= account_log_user;
	}
	public void RestoreParams() {
		account_num_from				= account_num_from_o;
		account_num_to					= account_num_to_o;
		account_currency				= account_currency_o;
		account_sw_addr_sel				= account_sw_addr_sel_o;
		account_max_check_amt_sel		= account_max_check_amt_sel_o;
		account_posi_pay_flag_sel		= account_posi_pay_flag_sel_o;
		account_posi_pay_amt_min_sel	= account_posi_pay_amt_min_sel_o;
		account_log_date				= account_log_date_o;
		account_log_user				= account_log_user_o;
	}
	public void ShowParams() {
		moduleName = "ShowParams: ";
		PrintLog("account_num_from\t\t" + account_num_from);
		PrintLog("account_num_to\t\t" + account_num_to);
		PrintLog("account_currency\t\t" + account_currency);
		PrintLog("account_sw_addr_sel\t\t" + account_sw_addr_sel);
		PrintLog("account_max_check_amt_sel\t\t" + account_max_check_amt_sel);
		PrintLog("account_posi_pay_flag_sel\t\t" + account_posi_pay_flag_sel);
		PrintLog("account_posi_pay_amt_min_sel\t\t" + account_posi_pay_amt_min_sel);
		PrintLog("account_log_date\t\t" + account_log_date);
		PrintLog("account_log_user\t\t" + account_log_user);
	}
	public void InitParams() {
		account_num_from				= "ALL";
		account_num_to					= "NONE";
		account_currency				= "";
		account_sw_addr_sel				= "";
		account_max_check_amt_sel		= "";
		account_posi_pay_flag_sel		= "";
		account_posi_pay_amt_min_sel	= "";
		account_log_date				= "";
		account_log_user				= "";
	}
	public void ClearNulls() {
		account_num_from				= (account_num_from == null) ? "" : account_num_from;
		account_num_to					= (account_num_to == null) ? "" : account_num_to;
		account_currency				= (account_currency == null) ? "" : account_currency;
		account_sw_addr_sel				= (account_sw_addr_sel == null) ? "" : account_sw_addr_sel;
		account_max_check_amt_sel		= (account_max_check_amt_sel == null) ? "" : account_max_check_amt_sel;
		account_posi_pay_flag_sel		= (account_posi_pay_flag_sel == null) ? "" : account_posi_pay_flag_sel;
		account_posi_pay_amt_min_sel	= (account_posi_pay_amt_min_sel == null) ? "" : account_posi_pay_amt_min_sel;
		account_log_date				= (account_log_date == null) ? "" : account_log_date;
		account_log_user				= (account_log_user == null) ? "" : account_log_user;
	}
	//
	public AccountDetail[] getAccountrows() {
		// return (this.accountrows);
		// (accounts) {
		AccountDetail results[] = new AccountDetail[accountrows.size()];
		Iterator<AccountDetail> accts = accountrows.iterator();
		int n = 0;
		while (accts.hasNext()) {
			results[n++] = (AccountDetail) accts.next();
		}
		return (results);
		// }
	}
	//
	public AccountDetail getArow() {
		AccountDetail results	= new AccountDetail();
		Iterator<AccountDetail> accts	= accountrows.iterator();
		results	= (AccountDetail) accts.next();
		return (results);
	}
	//
	public void setAccountrows(ArrayList<AccountDetail> account_row) {		
		Iterator<AccountDetail> rows = account_row.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.accountrows.add(n, (AccountDetail) rows.next());
			n++;
		}
		rowCount = n;
		//PrintLog("Num Rows="+n);
	}
	//
	public AccountDetail getArow(int recIndex) {
		AccountDetail results = new AccountDetail();
		// Iterator acct = checkrows.iterator();
		// this.recIndex = recIndex;
		results = (AccountDetail) accountrows.get(recIndex);
		return (results);
	}
	//
	public void setModifyRow(AccountDetail modifyRow) {
		this.modifyRow = modifyRow;
	}
	//
	public AccountDetail getModifyRow() {
		return (this.modifyRow);
	}
	//
	public String GetParams() {
		moduleName = "GetParams: ";
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		String param			= "";
		String acc_crdb			= "";
		String acc_curr			= "";
		String sw_addr			= "";
		String max_amt			= "0";
		String posi_amt			= "0";
		String posi_flag		= "";
		String from_acct		= "";
		String to_acct			= "";
		String acc_param		= "";
		String accrdb_param		= "";
		String accurr_param		= "";
		String swaddr_param		= "";
		String maxamt_param		= "";
		String minamt_param		= "";
		String posiflag_param	= "";
		//
		ClearNulls();
		if (!account_num_from.equals("ALL")) {
			from_acct = account_num_from.trim();
		}
		if (!account_num_to.equals("NONE")) {
			to_acct = account_num_to.trim();
		}
		if (!account_crdb.equals("ALL")) {
			acc_crdb = account_crdb.trim();
		}
		if (!account_currency.equals("")) {
			acc_curr = account_currency.trim().toUpperCase();
		}
		if (!account_sw_addr_sel.equals("")) {
			sw_addr = account_sw_addr_sel.trim().toUpperCase();
		}
		if (!account_max_check_amt_sel.equals("")) {
			max_amt = account_max_check_amt_sel.trim();
		}
		if (!account_posi_pay_flag_sel.equals("ALL")) {
			posi_flag = account_posi_pay_flag_sel.trim();
		}
		if (!account_posi_pay_amt_min_sel.equals("")) {
			posi_amt = account_posi_pay_amt_min_sel.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param = "account_num='" + from_acct + "' ";
			// acc_param = "(account_num='"+from_acct+"' "+
			// " OR account_num>'"+from_acct+"') ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param = " account_num between '" + from_acct + "' AND '"
						+ to_acct + "'";
			} else {
				acc_param = "account_num='" + to_acct + "' ";
			}
		}
		if (!acc_crdb.equals("")) {
			accrdb_param = "account_int_ext='" + acc_crdb + "' ";
		}
		if (!acc_curr.equals("")) {
			accurr_param = "account_currency='" + acc_curr + "' ";
		}
		if (!sw_addr.equals("")) {
			swaddr_param = "account_sw_addr='" + sw_addr + "' ";
		}
		if (!max_amt.equals("") && !max_amt.equals("0")) {
			maxamt_param = "((account_max_check_amount>'" + max_amt
					+ "') or (account_max_check_amount='" + max_amt + "')) ";
		}
		if (!posi_flag.equals("")) {
			posiflag_param = "account_posi_pay_flag='" + posi_flag + "' ";
		}
		if (!posi_amt.equals("") && !posi_amt.equals("0")) {
			minamt_param = "((account_posi_pay_amt_min>'" + posi_amt
					+ "') or (account_posi_pay_amt_min='" + posi_amt + "')) ";
		}
		//
		if (!acc_param.equals("")) {
			param = acc_param;
		}
		if (!param.equals("")) {
			if (!accurr_param.equals(""))
				param += " AND " + accurr_param;
		} else {
			param = accurr_param;
		}
		if (!param.equals("")) {
			if (!accrdb_param.equals(""))
				param += " AND " + accrdb_param;
		} else {
			param = accrdb_param;
		}
		if (!param.equals("")) {
			if (!swaddr_param.equals(""))
				param += " AND " + swaddr_param;
		} else {
			param = swaddr_param;
		}
		if (!param.equals("")) {
			if (!maxamt_param.equals(""))
				param += " AND " + maxamt_param;
		} else {
			param = maxamt_param;
		}
		if (!param.equals("")) {
			if (!minamt_param.equals(""))
				param += " AND " + minamt_param;
		} else {
			param = minamt_param;
		}
		if (!param.equals("")) {
			if (!posiflag_param.equals(""))
				param += " AND " + posiflag_param;
		} else {
			param = posiflag_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY account_num";
		// PrintLog(" Param->" + param);
		return (param);
	}
	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName = "GetLogParams: ";
		String param			= "";
		String acc_param		= "";
		String acc_curr			= "";
		String sw_addr			= "";
		String max_amt			= "0";
		String posi_amt			= "0";
		String posi_flag		= "";
		String from_acct		= "";
		String to_acct			= "";
		String log_date			= "";
		String log_user			= "";
		String accurr_param		= "";
		String swaddr_param		= "";
		String maxamt_param		= "";
		String minamt_param		= "";
		String posiflag_param	= "";
		String log_date_param	= "";
		String log_user_param	= "";
		//
		ClearNulls();
		if (!account_num_from.equals("ALL")) {
			from_acct	= account_num_from.trim();
		}
		if (!account_num_to.equals("NONE")) {
			to_acct		= account_num_to.trim();
		}
		//
		if (!account_currency.equals("")) {
			acc_curr	= account_currency.trim().toUpperCase();
		}
		//
		if (!account_sw_addr_sel.equals("")) {
			sw_addr		= account_sw_addr_sel.trim().toUpperCase();
		}
		//
		if (!account_max_check_amt_sel.equals("")) {
			max_amt		= account_max_check_amt_sel.trim();
		}
		//
		if (!account_posi_pay_amt_min_sel.equals("")) {
			posi_amt	= account_posi_pay_amt_min_sel.trim();
		}
		if (!account_posi_pay_flag_sel.equals("ALL")) {
			posi_flag	= account_posi_pay_flag_sel.trim();
		}
		if (!account_log_date.equals("ALL")) {
			log_date	= account_log_date.trim();
		}
		if (!account_log_user.equals("ALL")) {
			log_user	= account_log_user.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!from_acct.equals("")) {
			acc_param	= "acctlog_num='" + from_acct + "' ";
		}
		if (!to_acct.equals("")) {
			if (!acc_param.equals("")) {
				acc_param	= " acctlog_num between '" + from_acct + "' AND '" +
								to_acct + "'";
			} else {
				acc_param	= "acctlog_num='" + to_acct + "' ";
			}
		}
		if (!acc_curr.equals("")) {
			accurr_param	= "acctlog_currency='" + acc_curr + "' ";
		}
		if (!sw_addr.equals("")) {
			swaddr_param	= "acctlog_sw_addr='" + sw_addr + "' ";
		}
		if (!max_amt.equals("") && !max_amt.equals("0")) {
			maxamt_param	= "((acctlog_max_check_amount>'" + max_amt +
							"') or (acctlog_max_check_amount='" + max_amt + "')) ";
		}
		if (!posi_flag.equals("")) {
			posiflag_param	= "acctlog_posi_pay_flag='" + posi_flag + "' ";
		}
		if (!posi_amt.equals("") && !posi_amt.equals("0")) {
			minamt_param	= "((acctlog_posi_pay_amt_min>'" + posi_amt +
								"') or (acctlog_posi_pay_amt_min='" + posi_amt + "')) ";
		}
		if (!log_date.equals("")) {
			log_date_param	= "acctlog_last_modified LIKE '%" + log_date + "%' ";
		}
		if (!log_user.equals("")) {
			log_user_param	= "acctlog_mod_user='" + log_user + "' ";
		}
		//
		if (!acc_param.equals("")) {
			param	= acc_param;
		}
		if (!param.equals("")) {
			if (!accurr_param.equals(""))
				param	+= " AND " + accurr_param;
		} else {
			param	= accurr_param;
		}
		if (!param.equals("")) {
			if (!swaddr_param.equals(""))
				param	+= " AND " + swaddr_param;
		} else {
			param	= swaddr_param;
		}
		if (!param.equals("")) {
			if (!maxamt_param.equals(""))
				param	+= " AND " + maxamt_param;
		} else {
			param	= maxamt_param;
		}
		if (!param.equals("")) {
			if (!minamt_param.equals(""))
				param	+= " AND " + minamt_param;
		} else {
			param	= minamt_param;
		}
		if (!param.equals("")) {
			if (!posiflag_param.equals(""))
				param	+= " AND " + posiflag_param;
		} else {
			param	= posiflag_param;
		}
		if (!param.equals("")) {
			if (!log_date_param.equals(""))
				param	+= " AND " + log_date_param;
		} else {
			param	= log_date_param;
		}
		if (!param.equals("")) {
			if (!log_user_param.equals(""))
				param	+= " AND " + log_user_param;
		} else {
			param	= log_user_param;
		}
		if (!param.equals("")) {
			param	= " WHERE " + param;
		}
		param	+= " ORDER BY acctlog_num, acctlog_last_modified DESC";
		PrintLog(" Param->" + param);
		return (param);
	}
	//
	public void SetNextRows() {
		if (this.rowEnd < rowCount) {
			this.rowStart	= this.rowEnd;
			this.rowEnd		= this.rowEnd + this.rowsDisplayed;
			if (this.rowEnd > this.rowCount) {
				this.rowEnd	= this.rowCount;
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
				this.rowEnd		= this.rowsDisplayed;
			}
		}
	}
	//
	protected void finalize() {
		//
	}
}
