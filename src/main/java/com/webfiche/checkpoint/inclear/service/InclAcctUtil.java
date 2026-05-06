package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.deposits.beans.AcctentrySelector;
//import com.webfiche.checkpoint.deposits.beans.PostingDetail;

@Service
public class InclAcctUtil {
	String moduleName;
	String className	= "> InclAcctUtil.";
	String param		= "";
	boolean modifyRow	= false;
	int row_count		= 0;
	ArrayList<AccountDetail> acctBeans	= new ArrayList<AccountDetail>();
	//
	//
	// account_extra1 used for Monthly average # of checks issued
	// account_extra6 used for balance available same day
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public InclAcctUtil() {
	}
	//
	public ArrayList<String> GetAccountList(Connection dbConn)
			throws Exception {
		moduleName	= "GetAccountList: ";
		// PrintLog("DBUSED "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> acctList	= new ArrayList<String>();
		acctList.clear();
		StringBuffer sql			= new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM from incl_accounts " +
				   "ORDER by ACCOUNT_NUM ");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("ACCOUNT_NUM");
			acctList.add(temp);
		}
		statement.close();
		result.close();
		return (acctList);
	}
	//
	public TreeMap<String, String> GetAccountMap(Connection dbConn)
			throws Exception {
		moduleName	= "GetAccountMap: ";
		// PrintLog("DBUSED "+dbUsed);
		String acctNum		= "";
		String clientName	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> allAcctList	= new TreeMap<String, String>();
		allAcctList.clear();
		// acctSelector.clearAllAcctList();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM, ACCOUNT_CLIENT_NAME from incl_accounts " +
				   "ORDER by ACCOUNT_NUM");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum		= result.getString("ACCOUNT_NUM");
			clientName	= result.getString("ACCOUNT_CLIENT_NAME");
			//PrintLog("Key: '"+acctNum+"'\tValue: '"+clientName+"'");
			allAcctList.put(acctNum, clientName);
		}
		statement.close();
		result.close();
		return (allAcctList);
	}
	//
	public TreeMap<String, Integer> GetMonthlyCount(Connection dbConn)
			throws Exception {
		moduleName	= "GetMonthlyCount: ";
		// PrintLog("DBUSED "+dbUsed);
		String acctNum		= "";
		String chexCountStr	= "";
		int	chexCount		= 0;
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, Integer> monthlyChexCount	= new TreeMap<String, Integer>();
		monthlyChexCount.clear();
		// acctSelector.clearAllAcctList();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT account_num, account_extra1 FROM incl_accounts " +
				   "ORDER by account_num");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum		= result.getString("ACCOUNT_NUM");
			chexCountStr= result.getString("ACCOUNT_EXTRA1");
			if (chexCountStr == null) {
				chexCountStr	= "0";
			}
			chexCount	= Integer.parseInt(chexCountStr);
			//PrintLog("Key: '"+acctNum+"'\tValue: '"+clientName+"'");
			monthlyChexCount.put(acctNum, chexCount);
		}
		statement.close();
		result.close();
		return (monthlyChexCount);
	}
	//
	public java.util.TreeMap<String, String> GetCreditorList(Connection dbConn)
			throws Exception {
		moduleName		= "GetCreditorList: ";
		// PrintLog("DBUSED "+dbUsed);
		String acctNum	= "";
		String acctName	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> creditorList	= new TreeMap<String, String>();
		creditorList.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM, ACCOUNT_CLIENT_NAME from incl_accounts " +
				   "where ACCOUNT_INT_EXT='C' ORDER by ACCOUNT_NUM");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum		= result.getString("ACCOUNT_NUM");
			acctName	= result.getString("ACCOUNT_CLIENT_NAME");
			creditorList.put(acctNum.trim(), acctName.trim());
		}
		statement.close();
		result.close();
		return (creditorList);
	}
	//
	public ArrayList<String> GetLogAccountList(Connection dbConn)
			throws Exception {
		moduleName = "GetLogAccountList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> acctList	= new ArrayList<String>();
		acctList.clear();
		StringBuffer sql			= new StringBuffer();
		sql.append("SELECT DISTINCT ACCTLOG_NUM from incl_accounts_log ORDER by ACCTLOG_NUM");
		Statement statement			= dbConn.createStatement();
		ResultSet result			= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp = result.getString("ACCTLOG_NUM");
			// PrintLog("AcctLogNum: "+temp);
			acctList.add(temp);
		}
		statement.close();
		result.close();
		return (acctList);
	}
	//
	public ArrayList<String> GetLogAccountDateList(Connection dbConn)
			throws Exception {
		moduleName		= "GetLogAccountDateList: ";
		// PrintLog(" DBUsed "+dbUsed);
		//PrintLog(" In Get Log Date ");
		String temp		= "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> ldate_list	= new ArrayList<String>();
		ldate_list.clear();
		StringBuffer sql				= new StringBuffer();
		sql.append("SELECT DISTINCT SUBSTR(to_char(ACCTLOG_LAST_MODIFIED,'yyyymmdd hh24:mi:ss'),1,8) as lmd, ");
		sql.append("SUBSTR(ACCTLOG_LAST_MODIFIED,1,9) as LD from incl_accounts_log ORDER by LMD DESC");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("LD");
			ldate_list.add(temp);
		}
		statement.close();
		result.close();
		return (ldate_list);
	}
	//
	public ArrayList<String> GetLogAccountUserList(Connection dbConn)
			throws Exception {
		moduleName	= "GetLogAccountUserList: ";
		// PrintLog(" DBUsed "+dbUsed);
		//PrintLog(" In Get Log User ");
		String temp	= "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> luser_list	= new ArrayList<String>();
		luser_list.clear();
		StringBuffer sql		= new StringBuffer();
		sql.append("SELECT DISTINCT ACCTLOG_MOD_USER from incl_accounts_log ORDER by ACCTLOG_MOD_USER");
		Statement statement		= dbConn.createStatement();
		ResultSet result		= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("ACCTLOG_MOD_USER");
			luser_list.add(temp);
		}
		statement.close();
		result.close();
		return (luser_list);
	}
	//
	public boolean AccountExists(Connection dbConn, String appl_date,
			String acct_num) throws Exception {
		moduleName = "AccountExists: ";
		if (dbConn == null) {
			PrintLog("dbConn NULL");
		}
		// PrintLog("DBUSED "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		// PrintLog(" DBUsed "+dbUsed+" Appl_date: "+appl_date);
		param = "AND (ACCOUNT_NUM='" + acct_num + "' OR ACCOUNT_NUM='" + acct_num + "USD' " +
				"OR ACCOUNT_GEN_SUSPENSE='" + acct_num + "') ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM from incl_accounts ");
		sql.append("WHERE (ACCOUNT_EFFECTIVE_DATE in (' ', '0') OR ");
		sql.append("ACCOUNT_EFFECTIVE_DATE<'" + appl_date + "') ");
		sql.append("AND (ACCOUNT_BLOCKED_DATE in (' ', '0')) ");
		sql.append(param);
		// Setup the SELECT statement.
		//PrintLog(" SQL ---> "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("ACCOUNT_NUM");
			statement.close();
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public boolean DepAccountExists(Connection dbConn, String appl_date,
			String acct_num) throws Exception {
		moduleName = "AccountExists: ";
		if (dbConn == null) {
			PrintLog("dbConn NULL");
		}
		// PrintLog("DBUSED "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		// PrintLog(" DBUsed "+dbUsed+" Appl_date: "+appl_date);
		param = "AND (ACCOUNT_NUM='" + acct_num + "' OR ACCOUNT_NUM='" + acct_num + "USD' " +
				"OR ACCOUNT_GEN_SUSPENSE='" + acct_num + "') ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM from incl_accounts ");
		sql.append("WHERE (ACCOUNT_EFFECTIVE_DATE in (' ', '0') OR ");
		sql.append("ACCOUNT_EFFECTIVE_DATE<'" + appl_date + "') ");
		sql.append("AND (ACCOUNT_CLOSED_DATE in (' ', '0')) ");
		sql.append(param);
		// Setup the SELECT statement.
		//PrintLog(" SQL ---> "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("ACCOUNT_NUM");
			statement.close();
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public String CheckAccountExists(Connection dbConn, String appl_date,
			String acct_num) throws Exception {
		moduleName = "CheckAccountExists: ";
		// PrintLog("DBUSED "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		// PrintLog(" DBUsed "+dbUsed+" Appl_date: "+appl_date);
		String temp = "";
		String temp1 = "";
		//param = "AND ACCOUNT_GEN_SUSPENSE='" + acct_num + "' ";
		param = "AND (ACCOUNT_NUM='" + acct_num + "' OR ACCOUNT_NUM='" + acct_num + "USD' " +
				"OR ACCOUNT_GEN_SUSPENSE='" + acct_num + "') ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM, ACCOUNT_CLIENT_NAME, ACCOUNT_CLOSED_DATE, ");
		sql.append("ACCOUNT_BLOCKED_DATE from incl_accounts ");
		sql.append("WHERE (ACCOUNT_EFFECTIVE_DATE in (' ', '0') OR ");
		sql.append("ACCOUNT_EFFECTIVE_DATE<'" + appl_date + "') ");
		sql.append("AND (ACCOUNT_CLOSED_DATE in (' ', '0')) ");
		sql.append(param);
		// Setup the SELECT statement.
		//PrintLog(" SQL ---> "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			temp = result.getString("ACCOUNT_NUM");
			temp += "|" + result.getString("ACCOUNT_CLIENT_NAME");
			temp1 = result.getString("ACCOUNT_CLOSED_DATE");
			if (temp1.equals("0")) {
				temp1 = result.getString("ACCOUNT_BLOCKED_DATE");
				if (temp1.equals("0")) {
					//
				} else {
					temp += "|" + "BLOCKED";
				}
			} else {
				temp += "|" + "CLOSED";
			}
		}
		statement.close();
		result.close();
		return (temp);
	}
	//
	public boolean AccountExists(Connection dbConn, String acct_num)
			throws Exception {
		moduleName = "AccountExists: ";
		// PrintLog("DBUSED "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		// PrintLog(" DBUsed "+dbUsed);
		//param = "WHERE ACCOUNT_NUM='" + acct_num + "'";
		param = " WHERE (ACCOUNT_NUM='" + acct_num + "' " +
				"OR ACCOUNT_NUM='" + acct_num + "USD' " +
				"OR ACCOUNT_GEN_SUSPENSE='" + acct_num + "') ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ACCOUNT_NUM from incl_accounts ");
		sql.append(param);
		//PrintLog(" SQL ---> "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("ACCOUNT_NUM");
			//PrintLog("Account Num: "+result.getString("ACCOUNT_NUM"));
			statement.close();
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	// To get an account given the SWIFT Address
	// used by PosiLoader (noupdate)
	public int GetAccountRows(Connection dbConn, String appl_date,
			String swift_tid, AccountSelector acct_selector) throws Exception {
		moduleName = "GetAccountRows1: ";
		// Called by the Posipay/Stoppay load module with an SWIFT TID
		// -----------------------------------------------------------
		param = "WHERE ACCOUNT_SW_ADDR LIKE '" + swift_tid + "%' AND ";
		param += "(ACCOUNT_EFFECTIVE_DATE in (' ', '0') OR ";
		param += "ACCOUNT_EFFECTIVE_DATE<'" + appl_date + "') ";
		param += "AND (ACCOUNT_CLOSED_DATE in (' ', '0')) ";
		row_count = GetAccountRows(dbConn, acct_selector);
		return (row_count);
	}
	//
	public int GetAccountRows(Connection dbConn, AccountSelector acctSelector,
			String acct_num) throws Exception {
		moduleName = "GetAccountRows2: ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param = "WHERE ACCOUNT_NUM='" + acct_num + "'";
		String accessFlag = acctSelector.getAccessFlag();
		String dbUsed = acctSelector.getDbUsed();
		if (dbUsed.equals("ORACLE")) {
			if (accessFlag != null) {
				if (accessFlag.equals("inq")) {
					;
				} else {
					param += " for UPDATE nowait ";
					dbConn.setAutoCommit(false);
				}
			} else {
				param += " for UPDATE nowait ";
				dbConn.setAutoCommit(false);
			}
		}
		modifyRow = true;
		row_count = GetAccountRows(dbConn, acctSelector);
		modifyRow = false;
		if (row_count > 0) {
			AccountDetail acctDetail = new AccountDetail();
			acctDetail = (AccountDetail) acctBeans.get(0);
			acctSelector.setModifyRow(acctDetail);
		}
		return (row_count);
	}
	//
	// This is called by any Load and ChexDetail modules
	public AccountDetail GetAccountRows(Connection dbConn, String acct_num)
			throws Exception {
		moduleName = "GetAccountRows2: ";
		AccountSelector acctSelector = new AccountSelector();
		param = "WHERE ACCOUNT_NUM='" + acct_num + "'";
		row_count = GetAccountRows(dbConn, acctSelector);
		if (row_count > 0) {
			return (acctSelector.getArow());
		} else {
			return (null);
		}
	}
	//
	// Called by Inq/Maint to display initial
	public int GetAccountRows(Connection dbConn, AccountSelector acctSelector)
			throws Exception {
		moduleName = "GetAccountRows3: ";
		acctBeans.clear();
		if (param.equals(""))
			// GetParams (acctSelector);
			param = acctSelector.GetParams();
		StringBuffer sql = new StringBuffer();
		String dbUsed = acctSelector.getDbUsed();
		// PrintLog(" DBUsed "+dbUsed);
		row_count = 0;
		sql.append("SELECT ACCOUNT_NUM, ACCOUNT_CURRENCY, ");
		sql.append("ACCOUNT_SW_ADDR, ");
		sql.append("ACCOUNT_ACC_REP, ");
		sql.append("ACCOUNT_ALT_ACC_REP, ");
		sql.append("ACCOUNT_CLIENT_NAME, ");
		sql.append("ACCOUNT_CLIENT_ADDR1, ");
		sql.append("ACCOUNT_CLIENT_ADDR2, ");
		sql.append("ACCOUNT_CLIENT_ADDR3, ");
		sql.append("ACCOUNT_CLIENT_ADDR4, ");
		/*
		 * sql.append("NVL(ACCOUNT_EFFECTIVE_DATE,0), ");
		 * sql.append("NVL(ACCOUNT_CLOSED_DATE,0), ");
		 * sql.append("NVL(ACCOUNT_BLOCKED_DATE,0), ");
		 */
		sql.append("ACCOUNT_EFFECTIVE_DATE, ");
		sql.append("ACCOUNT_CLOSED_DATE, ");
		sql.append("ACCOUNT_BLOCKED_DATE, ");
		sql.append("ACCOUNT_MAX_CHECK_AMOUNT, ACCOUNT_POSI_PAY_FLAG, ");
		sql.append("ACCOUNT_POSI_PAY_AMT_MIN, ACCOUNT_INT_EXT, ");
		sql.append("ACCOUNT_GEN_SUSPENSE, ACCOUNT_STOP_SUSPENSE, ");
		sql.append("ACCOUNT_EXCE_SUSPENSE, ");
		sql.append("ACCOUNT_STMT_EMAIL, ");
		sql.append("ACCOUNT_STMT_FAX, ");
		sql.append("ACCOUNT_STMT_MAIL1, ");
		sql.append("ACCOUNT_STMT_MAIL2, ");
		sql.append("ACCOUNT_STMT_MAIL3, ");
		sql.append("ACCOUNT_STMT_MAIL4, ");
		sql.append("ACCOUNT_STMT_EMAILFREQD, ");
		sql.append("ACCOUNT_STMT_EMAILFREQW, ");
		sql.append("ACCOUNT_STMT_EMAILFREQM, ");
		sql.append("ACCOUNT_STMT_FAXFREQD, ");
		sql.append("ACCOUNT_STMT_FAXFREQW, ");
		sql.append("ACCOUNT_STMT_FAXFREQM, ");
		sql.append("ACCOUNT_STMT_MAILFREQD, ");
		sql.append("ACCOUNT_STMT_MAILFREQW, ");
		sql.append("ACCOUNT_STMT_MAILFREQM, ");
		sql.append("ACCOUNT_STMT_EXTRA, ");
		sql.append("ACCOUNT_USER_COMMENTS, ");
		sql.append("ACCOUNT_DAYSCHECKVALID, ");
		sql.append("ACCOUNT_EXTRA1, ");
		sql.append("ACCOUNT_EXTRA2, ");
		sql.append("ACCOUNT_EXTRA3, ");
		sql.append("ACCOUNT_EXTRA4, ");
		sql.append("ACCOUNT_EXTRA5, ");
		sql.append("ACCOUNT_EXTRA6, ");
		sql.append("ACCOUNT_AVAILDAY1, ");
		sql.append("ACCOUNT_AVAILLOW1, ");
		sql.append("ACCOUNT_AVAILHIGH1, ");
		sql.append("ACCOUNT_AVAILDAY2, ");
		sql.append("ACCOUNT_AVAILLOW2, ");
		sql.append("ACCOUNT_AVAILHIGH2, ");
		sql.append("ACCOUNT_AVAILDAY3, ");
		sql.append("ACCOUNT_AVAILLOW3, ");
		sql.append("ACCOUNT_AVAILHIGH3, ");
		sql.append("ACCOUNT_AVAILDAY4, ");
		sql.append("ACCOUNT_AVAILLOW4, ");
		sql.append("ACCOUNT_AVAILHIGH4, ");
		sql.append("ACCOUNT_AVAILDAY5, ");
		sql.append("ACCOUNT_AVAILLOW5, ");
		sql.append("ACCOUNT_AVAILHIGH5, ");
		sql.append("ACCOUNT_LAST_MODIFIED, ");
		sql.append("ACCOUNT_MOD_USER, ");
		sql.append("ACCOUNT_MOD_FUNC ");
		sql.append("FROM incl_accounts ");
		sql.append(param);
		//
		//PrintLog(" SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog(": Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			AccountDetail acct_detail = new AccountDetail();
			// Here add the fields tp the AccountDetail bean
			acct_detail.setDbUsed(dbUsed);
			acct_detail.setAccount_num(result.getString("ACCOUNT_NUM"));
			// PrintLog("Account Num: "+acct_detail.getAccount_num());
			acct_detail.setAccount_currency(result.getString("ACCOUNT_CURRENCY"));
			// PrintLog("Currency: ");
			acct_detail.setAccount_sw_addr(result.getString("ACCOUNT_SW_ADDR"));
			// PrintLog("3: ");
			acct_detail.setAccount_acc_rep(result.getString("ACCOUNT_ACC_REP"));
			acct_detail.setAccount_alt_acc_rep(result.getString("ACCOUNT_ALT_ACC_REP"));
			acct_detail.setAccount_client_name(result.getString("ACCOUNT_CLIENT_NAME"));
			acct_detail.setAccount_client_addr1(result.getString("ACCOUNT_CLIENT_ADDR1"));
			acct_detail.setAccount_client_addr2(result.getString("ACCOUNT_CLIENT_ADDR2"));
			acct_detail.setAccount_client_addr3(result.getString("ACCOUNT_CLIENT_ADDR3"));
			acct_detail.setAccount_client_addr4(result.getString("ACCOUNT_CLIENT_ADDR4"));
			acct_detail.setAccount_effective_date(result.getString("ACCOUNT_EFFECTIVE_DATE"));
			acct_detail.setAccount_closed_date(result.getString("ACCOUNT_CLOSED_DATE"));
			acct_detail.setAccount_blocked_date(result.getString("ACCOUNT_BLOCKED_DATE"));
			acct_detail.setAccount_max_check_amount(result.getString("ACCOUNT_MAX_CHECK_AMOUNT"));
			acct_detail.setAccount_posi_pay_flag(result.getString("ACCOUNT_POSI_PAY_FLAG"));
			acct_detail.setAccount_posi_pay_amt_min(result.getString("ACCOUNT_POSI_PAY_AMT_MIN"));
			acct_detail.setAccount_int_ext(result.getString("ACCOUNT_INT_EXT"));
			acct_detail.setAccount_gen_suspense(result.getString("ACCOUNT_GEN_SUSPENSE"));
			acct_detail.setAccount_stop_suspense(result.getString("ACCOUNT_STOP_SUSPENSE"));
			acct_detail.setAccount_exce_suspense(result.getString("ACCOUNT_EXCE_SUSPENSE"));
			acct_detail.setAccount_stmt_email(result.getString("ACCOUNT_STMT_EMAIL"));
			acct_detail.setAccount_stmt_fax(result.getString("ACCOUNT_STMT_FAX"));
			acct_detail.setAccount_stmt_mail1(result.getString("ACCOUNT_STMT_MAIL1"));
			acct_detail.setAccount_stmt_mail2(result.getString("ACCOUNT_STMT_MAIL2"));
			acct_detail.setAccount_stmt_mail3(result.getString("ACCOUNT_STMT_MAIL3"));
			acct_detail.setAccount_stmt_mail4(result.getString("ACCOUNT_STMT_MAIL4"));
			acct_detail.setAccount_stmt_emailfreqD(result.getString("ACCOUNT_STMT_EMAILFREQD"));
			acct_detail.setAccount_stmt_emailfreqW(result.getString("ACCOUNT_STMT_EMAILFREQW"));
			acct_detail.setAccount_stmt_emailfreqM(result.getString("ACCOUNT_STMT_EMAILFREQM"));
			acct_detail.setAccount_stmt_faxfreqD(result.getString("ACCOUNT_STMT_FAXFREQD"));
			acct_detail.setAccount_stmt_faxfreqW(result.getString("ACCOUNT_STMT_FAXFREQW"));
			acct_detail.setAccount_stmt_faxfreqM(result.getString("ACCOUNT_STMT_FAXFREQM"));
			acct_detail.setAccount_stmt_mailfreqD(result.getString("ACCOUNT_STMT_MAILFREQD"));
			acct_detail.setAccount_stmt_mailfreqW(result.getString("ACCOUNT_STMT_MAILFREQW"));
			acct_detail.setAccount_stmt_mailfreqM(result.getString("ACCOUNT_STMT_MAILFREQM"));
			acct_detail.setAccount_stmt_extra(result.getString("ACCOUNT_STMT_EXTRA"));
			acct_detail.setAccount_user_comments(result.getString("ACCOUNT_USER_COMMENTS"));
			acct_detail.setAccount_dayscheckvalid(result.getString("ACCOUNT_DAYSCHECKVALID"));
			acct_detail.setAccount_extra1(result.getString("ACCOUNT_EXTRA1"));
			acct_detail.setAccount_extra2(result.getString("ACCOUNT_EXTRA2"));
			acct_detail.setAccount_extra3(result.getString("ACCOUNT_EXTRA3"));
			acct_detail.setAccount_extra4(result.getString("ACCOUNT_EXTRA4"));
			acct_detail.setAccount_extra5(result.getString("ACCOUNT_EXTRA5"));
			acct_detail.setAccount_extra6(result.getString("ACCOUNT_EXTRA6"));
			acct_detail.setAccount_availday1(result.getString("ACCOUNT_AVAILDAY1"));
			acct_detail.setAccount_availlow1(result.getString("ACCOUNT_AVAILLOW1"));
			acct_detail.setAccount_availhigh1(result.getString("ACCOUNT_AVAILHIGH1"));
			acct_detail.setAccount_availday2(result.getString("ACCOUNT_AVAILDAY2"));
			acct_detail.setAccount_availlow2(result.getString("ACCOUNT_AVAILLOW2"));
			acct_detail.setAccount_availhigh2(result.getString("ACCOUNT_AVAILHIGH2"));
			acct_detail.setAccount_availday3(result.getString("ACCOUNT_AVAILDAY3"));
			acct_detail.setAccount_availlow3(result.getString("ACCOUNT_AVAILLOW3"));
			acct_detail.setAccount_availhigh3(result.getString("ACCOUNT_AVAILHIGH3"));
			acct_detail.setAccount_availday4(result.getString("ACCOUNT_AVAILDAY4"));
			acct_detail.setAccount_availlow4(result.getString("ACCOUNT_AVAILLOW4"));
			acct_detail.setAccount_availhigh4(result.getString("ACCOUNT_AVAILHIGH4"));
			acct_detail.setAccount_availday5(result.getString("ACCOUNT_AVAILDAY5"));
			acct_detail.setAccount_availlow5(result.getString("ACCOUNT_AVAILLOW5"));
			acct_detail.setAccount_availhigh5(result.getString("ACCOUNT_AVAILHIGH5"));
			acct_detail.setAccount_last_modified(result.getString("ACCOUNT_LAST_MODIFIED"));
			acct_detail.setAccount_mod_user(result.getString("ACCOUNT_MOD_USER"));
			acct_detail.setAccount_mod_func(result.getString("ACCOUNT_MOD_FUNC"));
			acctBeans.add(acct_detail);
			row_count++;
		}
		if (modifyRow) {
		} else {
			//PrintLog("Setting Beans"+row_count);
			acctSelector.setAccountrows(acctBeans);
		}
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public void GetAccountRows(Connection dbConn, AcctentrySelector acctentrySelector) throws Exception {
		moduleName	= "GetAccountRows1: ";
		// Called by the Posting Action 
		moduleName = "GetAccountRows3: ";
		TreeMap<String, AccountDetail> acctList	= new TreeMap<String, AccountDetail>();
		String accountNum	= "";
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT ACCOUNT_NUM, ACCOUNT_CURRENCY, ");
		sql.append("ACCOUNT_SW_ADDR, ");
		sql.append("ACCOUNT_ACC_REP, ");
		sql.append("ACCOUNT_ALT_ACC_REP, ");
		sql.append("ACCOUNT_CLIENT_NAME, ");
		sql.append("ACCOUNT_CLIENT_ADDR1, ");
		sql.append("ACCOUNT_CLIENT_ADDR2, ");
		sql.append("ACCOUNT_CLIENT_ADDR3, ");
		sql.append("ACCOUNT_CLIENT_ADDR4, ");
		sql.append("ACCOUNT_EFFECTIVE_DATE, ");
		sql.append("ACCOUNT_CLOSED_DATE, ");
		sql.append("ACCOUNT_BLOCKED_DATE, ");
		sql.append("ACCOUNT_MAX_CHECK_AMOUNT, ACCOUNT_POSI_PAY_FLAG, ");
		sql.append("ACCOUNT_POSI_PAY_AMT_MIN, ACCOUNT_INT_EXT, ");
		sql.append("ACCOUNT_GEN_SUSPENSE, ACCOUNT_STOP_SUSPENSE, ");
		sql.append("ACCOUNT_EXCE_SUSPENSE, ");
		sql.append("ACCOUNT_STMT_EMAIL, ");
		sql.append("ACCOUNT_STMT_FAX, ");
		sql.append("ACCOUNT_STMT_MAIL1, ");
		sql.append("ACCOUNT_STMT_MAIL2, ");
		sql.append("ACCOUNT_STMT_MAIL3, ");
		sql.append("ACCOUNT_STMT_MAIL4, ");
		sql.append("ACCOUNT_STMT_EMAILFREQD, ");
		sql.append("ACCOUNT_STMT_EMAILFREQW, ");
		sql.append("ACCOUNT_STMT_EMAILFREQM, ");
		sql.append("ACCOUNT_STMT_FAXFREQD, ");
		sql.append("ACCOUNT_STMT_FAXFREQW, ");
		sql.append("ACCOUNT_STMT_FAXFREQM, ");
		sql.append("ACCOUNT_STMT_MAILFREQD, ");
		sql.append("ACCOUNT_STMT_MAILFREQW, ");
		sql.append("ACCOUNT_STMT_MAILFREQM, ");
		sql.append("ACCOUNT_STMT_EXTRA, ");
		sql.append("ACCOUNT_USER_COMMENTS, ");
		sql.append("ACCOUNT_DAYSCHECKVALID, ");
		sql.append("ACCOUNT_EXTRA1, ");
		sql.append("ACCOUNT_EXTRA2, ");
		sql.append("ACCOUNT_EXTRA3, ");
		sql.append("ACCOUNT_EXTRA4, ");
		sql.append("ACCOUNT_EXTRA5, ");
		sql.append("ACCOUNT_EXTRA6, ");
		sql.append("ACCOUNT_AVAILDAY1, ");
		sql.append("ACCOUNT_AVAILLOW1, ");
		sql.append("ACCOUNT_AVAILHIGH1, ");
		sql.append("ACCOUNT_AVAILDAY2, ");
		sql.append("ACCOUNT_AVAILLOW2, ");
		sql.append("ACCOUNT_AVAILHIGH2, ");
		sql.append("ACCOUNT_AVAILDAY3, ");
		sql.append("ACCOUNT_AVAILLOW3, ");
		sql.append("ACCOUNT_AVAILHIGH3, ");
		sql.append("ACCOUNT_AVAILDAY4, ");
		sql.append("ACCOUNT_AVAILLOW4, ");
		sql.append("ACCOUNT_AVAILHIGH4, ");
		sql.append("ACCOUNT_AVAILDAY5, ");
		sql.append("ACCOUNT_AVAILLOW5, ");
		sql.append("ACCOUNT_AVAILHIGH5, ");
		sql.append("ACCOUNT_LAST_MODIFIED, ");
		sql.append("ACCOUNT_MOD_USER, ");
		sql.append("ACCOUNT_MOD_FUNC ");
		sql.append("FROM incl_accounts ");
		sql.append(param);
		//
		//PrintLog(" SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog(": Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			AccountDetail acct_detail = new AccountDetail();
			// Here add the fields tp the AccountDetail bean
			//acct_detail.setDbUsed(dbUsed);
			accountNum	= result.getString("ACCOUNT_NUM");
			acct_detail.setAccount_num(accountNum);
			// PrintLog("Account Num: "+acct_detail.getAccount_num());
			acct_detail.setAccount_currency(result.getString("ACCOUNT_CURRENCY"));
			// PrintLog("Currency: ");
			acct_detail.setAccount_sw_addr(result.getString("ACCOUNT_SW_ADDR"));
			// PrintLog("3: ");
			acct_detail.setAccount_acc_rep(result.getString("ACCOUNT_ACC_REP"));
			acct_detail.setAccount_alt_acc_rep(result.getString("ACCOUNT_ALT_ACC_REP"));
			acct_detail.setAccount_client_name(result.getString("ACCOUNT_CLIENT_NAME"));
			acct_detail.setAccount_client_addr1(result.getString("ACCOUNT_CLIENT_ADDR1"));
			acct_detail.setAccount_client_addr2(result.getString("ACCOUNT_CLIENT_ADDR2"));
			acct_detail.setAccount_client_addr3(result.getString("ACCOUNT_CLIENT_ADDR3"));
			acct_detail.setAccount_client_addr4(result.getString("ACCOUNT_CLIENT_ADDR4"));
			acct_detail.setAccount_effective_date(result.getString("ACCOUNT_EFFECTIVE_DATE"));
			acct_detail.setAccount_closed_date(result.getString("ACCOUNT_CLOSED_DATE"));
			acct_detail.setAccount_blocked_date(result.getString("ACCOUNT_BLOCKED_DATE"));
			acct_detail.setAccount_max_check_amount(result.getString("ACCOUNT_MAX_CHECK_AMOUNT"));
			acct_detail.setAccount_posi_pay_flag(result.getString("ACCOUNT_POSI_PAY_FLAG"));
			acct_detail.setAccount_posi_pay_amt_min(result.getString("ACCOUNT_POSI_PAY_AMT_MIN"));
			acct_detail.setAccount_int_ext(result.getString("ACCOUNT_INT_EXT"));
			acct_detail.setAccount_gen_suspense(result.getString("ACCOUNT_GEN_SUSPENSE"));
			acct_detail.setAccount_stop_suspense(result.getString("ACCOUNT_STOP_SUSPENSE"));
			acct_detail.setAccount_exce_suspense(result.getString("ACCOUNT_EXCE_SUSPENSE"));
			acct_detail.setAccount_stmt_email(result.getString("ACCOUNT_STMT_EMAIL"));
			acct_detail.setAccount_stmt_fax(result.getString("ACCOUNT_STMT_FAX"));
			acct_detail.setAccount_stmt_mail1(result.getString("ACCOUNT_STMT_MAIL1"));
			acct_detail.setAccount_stmt_mail2(result.getString("ACCOUNT_STMT_MAIL2"));
			acct_detail.setAccount_stmt_mail3(result.getString("ACCOUNT_STMT_MAIL3"));
			acct_detail.setAccount_stmt_mail4(result.getString("ACCOUNT_STMT_MAIL4"));
			acct_detail.setAccount_stmt_emailfreqD(result.getString("ACCOUNT_STMT_EMAILFREQD"));
			acct_detail.setAccount_stmt_emailfreqW(result.getString("ACCOUNT_STMT_EMAILFREQW"));
			acct_detail.setAccount_stmt_emailfreqM(result.getString("ACCOUNT_STMT_EMAILFREQM"));
			acct_detail.setAccount_stmt_faxfreqD(result.getString("ACCOUNT_STMT_FAXFREQD"));
			acct_detail.setAccount_stmt_faxfreqW(result.getString("ACCOUNT_STMT_FAXFREQW"));
			acct_detail.setAccount_stmt_faxfreqM(result.getString("ACCOUNT_STMT_FAXFREQM"));
			acct_detail.setAccount_stmt_mailfreqD(result.getString("ACCOUNT_STMT_MAILFREQD"));
			acct_detail.setAccount_stmt_mailfreqW(result.getString("ACCOUNT_STMT_MAILFREQW"));
			acct_detail.setAccount_stmt_mailfreqM(result.getString("ACCOUNT_STMT_MAILFREQM"));
			acct_detail.setAccount_stmt_extra(result.getString("ACCOUNT_STMT_EXTRA"));
			acct_detail.setAccount_user_comments(result.getString("ACCOUNT_USER_COMMENTS"));
			acct_detail.setAccount_dayscheckvalid(result.getString("ACCOUNT_DAYSCHECKVALID"));
			acct_detail.setAccount_extra1(result.getString("ACCOUNT_EXTRA1"));
			acct_detail.setAccount_extra2(result.getString("ACCOUNT_EXTRA2"));
			acct_detail.setAccount_extra3(result.getString("ACCOUNT_EXTRA3"));
			acct_detail.setAccount_extra4(result.getString("ACCOUNT_EXTRA4"));
			acct_detail.setAccount_extra5(result.getString("ACCOUNT_EXTRA5"));
			acct_detail.setAccount_extra6(result.getString("ACCOUNT_EXTRA6"));
			//PrintLog("SameDayAcct: "+result.getString("ACCOUNT_EXTRA6"));
			acct_detail.setAccount_availday1(result.getString("ACCOUNT_AVAILDAY1"));
			acct_detail.setAccount_availlow1(result.getString("ACCOUNT_AVAILLOW1"));
			acct_detail.setAccount_availhigh1(result.getString("ACCOUNT_AVAILHIGH1"));
			acct_detail.setAccount_availday2(result.getString("ACCOUNT_AVAILDAY2"));
			acct_detail.setAccount_availlow2(result.getString("ACCOUNT_AVAILLOW2"));
			acct_detail.setAccount_availhigh2(result.getString("ACCOUNT_AVAILHIGH2"));
			acct_detail.setAccount_availday3(result.getString("ACCOUNT_AVAILDAY3"));
			acct_detail.setAccount_availlow3(result.getString("ACCOUNT_AVAILLOW3"));
			acct_detail.setAccount_availhigh3(result.getString("ACCOUNT_AVAILHIGH3"));
			acct_detail.setAccount_availday4(result.getString("ACCOUNT_AVAILDAY4"));
			acct_detail.setAccount_availlow4(result.getString("ACCOUNT_AVAILLOW4"));
			acct_detail.setAccount_availhigh4(result.getString("ACCOUNT_AVAILHIGH4"));
			acct_detail.setAccount_availday5(result.getString("ACCOUNT_AVAILDAY5"));
			acct_detail.setAccount_availlow5(result.getString("ACCOUNT_AVAILLOW5"));
			acct_detail.setAccount_availhigh5(result.getString("ACCOUNT_AVAILHIGH5"));
			acct_detail.setAccount_last_modified(result.getString("ACCOUNT_LAST_MODIFIED"));
			acct_detail.setAccount_mod_user(result.getString("ACCOUNT_MOD_USER"));
			acct_detail.setAccount_mod_func(result.getString("ACCOUNT_MOD_FUNC"));
			acctList.put(accountNum,acct_detail);
			//PrintLog("Tree Account: "+accountNum);
			row_count++;
		}
		PrintLog("AcctList has "+row_count+" rows");
		acctentrySelector.setAcctList(acctList);
		statement.close();
		result.close();
	}
	//
	public int GetLogAccountRows(Connection dbConn, AccountSelector acctSelector)
			throws Exception {
		moduleName = "GetLogAccountRows: ";
		ArrayList<AccountDetail> acctBeans = new ArrayList<AccountDetail>();
		// ArrayList<String> acct_rows = new ArrayList<String>();
		if (param.equals(""))
			// GetLogParams (acctSelector);
			param = acctSelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		String dbUsed = acctSelector.getDbUsed();
		// PrintLog(" DBUsed "+dbUsed);
		row_count = 0;
		sql.append("SELECT ACCTLOG_NUM, ACCTLOG_CURRENCY, ");
		sql.append("ACCTLOG_SW_ADDR, ");
		sql.append("ACCTLOG_ACC_REP, ACCTLOG_ALT_ACC_REP, ");
		sql.append("ACCTLOG_CLIENT_NAME, ");
		sql.append("ACCTLOG_CLIENT_ADDR1, ");
		sql.append("ACCTLOG_CLIENT_ADDR2, ");
		sql.append("ACCTLOG_CLIENT_ADDR3, ");
		sql.append("ACCTLOG_CLIENT_ADDR4, ");
		sql.append("ACCTLOG_EFFECTIVE_DATE, ");
		sql.append("ACCTLOG_CLOSED_DATE, ");
		sql.append("ACCTLOG_BLOCKED_DATE, ");
		sql.append("ACCTLOG_MAX_CHECK_AMOUNT, ACCTLOG_POSI_PAY_FLAG, ");
		sql.append("ACCTLOG_POSI_PAY_AMT_MIN, ACCTLOG_INT_EXT, ");
		sql.append("ACCTLOG_GEN_SUSPENSE, ACCTLOG_STOP_SUSPENSE, ");
		sql.append("ACCTLOG_EXCE_SUSPENSE, ");
		sql.append("ACCTLOG_STMT_EMAIL, ");
		sql.append("ACCTLOG_STMT_FAX, ");
		sql.append("ACCTLOG_STMT_MAIL1, ");
		sql.append("ACCTLOG_STMT_MAIL2, ");
		sql.append("ACCTLOG_STMT_MAIL3, ");
		sql.append("ACCTLOG_STMT_MAIL4, ");
		sql.append("ACCTLOG_STMT_EMAILFREQD, ");
		sql.append("ACCTLOG_STMT_EMAILFREQW, ");
		sql.append("ACCTLOG_STMT_EMAILFREQM, ");
		sql.append("ACCTLOG_STMT_FAXFREQD, ");
		sql.append("ACCTLOG_STMT_FAXFREQW, ");
		sql.append("ACCTLOG_STMT_FAXFREQM, ");
		sql.append("ACCTLOG_STMT_MAILFREQD, ");
		sql.append("ACCTLOG_STMT_MAILFREQW, ");
		sql.append("ACCTLOG_STMT_MAILFREQM, ");
		sql.append("ACCTLOG_STMT_EXTRA, ");
		sql.append("ACCTLOG_USER_COMMENTS, ");
		sql.append("ACCTLOG_DAYSCHECKVALID, ");
		sql.append("ACCTLOG_EXTRA1, ");
		sql.append("ACCTLOG_EXTRA2, ");
		sql.append("ACCTLOG_EXTRA3, ");
		sql.append("ACCTLOG_EXTRA4, ");
		sql.append("ACCTLOG_EXTRA5, ");
		sql.append("ACCTLOG_EXTRA6, ");
		sql.append("ACCTLOG_AVAILDAY1, ");
		sql.append("ACCTLOG_AVAILLOW1, ");
		sql.append("ACCTLOG_AVAILHIGH1, ");
		sql.append("ACCTLOG_AVAILDAY2, ");
		sql.append("ACCTLOG_AVAILLOW2, ");
		sql.append("ACCTLOG_AVAILHIGH2, ");
		sql.append("ACCTLOG_AVAILDAY3, ");
		sql.append("ACCTLOG_AVAILLOW3, ");
		sql.append("ACCTLOG_AVAILHIGH3, ");
		sql.append("ACCTLOG_AVAILDAY4, ");
		sql.append("ACCTLOG_AVAILLOW4, ");
		sql.append("ACCTLOG_AVAILHIGH4, ");
		sql.append("ACCTLOG_AVAILDAY5, ");
		sql.append("ACCTLOG_AVAILLOW5, ");
		sql.append("ACCTLOG_AVAILHIGH5, ");
		sql.append("ACCTLOG_LAST_MODIFIED, ");
		sql.append("ACCTLOG_MOD_USER, ACCTLOG_MOD_FUNC ");
		sql.append("FROM incl_accounts_log ");
		sql.append(param);
		// PrintLog(" SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog(" Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			AccountDetail acct_detail = new AccountDetail();
			// Here add the fields tp the AccountDetail bean
			acct_detail.setDbUsed(dbUsed);
			acct_detail.setAccount_num(result.getString("ACCTLOG_NUM"));
			acct_detail.setAccount_currency(result.getString("ACCTLOG_CURRENCY"));
			acct_detail.setAccount_sw_addr(result.getString("ACCTLOG_SW_ADDR"));
			acct_detail.setAccount_acc_rep(result.getString("ACCTLOG_ACC_REP"));
			acct_detail.setAccount_alt_acc_rep(result.getString("ACCTLOG_ALT_ACC_REP"));
			acct_detail.setAccount_client_name(result.getString("ACCTLOG_CLIENT_NAME"));
			acct_detail.setAccount_client_addr1(result.getString("ACCTLOG_CLIENT_ADDR1"));
			acct_detail.setAccount_client_addr2(result.getString("ACCTLOG_CLIENT_ADDR2"));
			acct_detail.setAccount_client_addr3(result.getString("ACCTLOG_CLIENT_ADDR3"));
			acct_detail.setAccount_client_addr4(result.getString("ACCTLOG_CLIENT_ADDR4"));
			acct_detail.setAccount_effective_date(result.getString("ACCTLOG_EFFECTIVE_DATE"));
			acct_detail.setAccount_closed_date(result.getString("ACCTLOG_CLOSED_DATE"));
			acct_detail.setAccount_blocked_date(result.getString("ACCTLOG_BLOCKED_DATE"));
			acct_detail.setAccount_max_check_amount(result.getString("ACCTLOG_MAX_CHECK_AMOUNT"));
			acct_detail.setAccount_posi_pay_flag(result.getString("ACCTLOG_POSI_PAY_FLAG"));
			acct_detail.setAccount_posi_pay_amt_min(result.getString("ACCTLOG_POSI_PAY_AMT_MIN"));
			acct_detail.setAccount_int_ext(result.getString("ACCTLOG_INT_EXT"));
			acct_detail.setAccount_gen_suspense(result.getString("ACCTLOG_GEN_SUSPENSE"));
			acct_detail.setAccount_stop_suspense(result.getString("ACCTLOG_STOP_SUSPENSE"));
			acct_detail.setAccount_exce_suspense(result.getString("ACCTLOG_EXCE_SUSPENSE"));
			acct_detail.setAccount_stmt_email(result.getString("ACCTLOG_STMT_EMAIL"));
			acct_detail.setAccount_stmt_fax(result.getString("ACCTLOG_STMT_FAX"));
			acct_detail.setAccount_stmt_mail1(result.getString("ACCTLOG_STMT_MAIL1"));
			acct_detail.setAccount_stmt_mail2(result.getString("ACCTLOG_STMT_MAIL2"));
			acct_detail.setAccount_stmt_mail3(result.getString("ACCTLOG_STMT_MAIL3"));
			acct_detail.setAccount_stmt_mail4(result.getString("ACCTLOG_STMT_MAIL4"));
			acct_detail.setAccount_stmt_emailfreqD(result.getString("ACCTLOG_STMT_EMAILFREQD"));
			acct_detail.setAccount_stmt_emailfreqW(result.getString("ACCTLOG_STMT_EMAILFREQW"));
			acct_detail.setAccount_stmt_emailfreqM(result.getString("ACCTLOG_STMT_EMAILFREQM"));
			acct_detail.setAccount_stmt_faxfreqD(result.getString("ACCTLOG_STMT_FAXFREQD"));
			acct_detail.setAccount_stmt_faxfreqW(result.getString("ACCTLOG_STMT_FAXFREQW"));
			acct_detail.setAccount_stmt_faxfreqM(result.getString("ACCTLOG_STMT_FAXFREQM"));
			acct_detail.setAccount_stmt_mailfreqD(result.getString("ACCTLOG_STMT_MAILFREQD"));
			acct_detail.setAccount_stmt_mailfreqW(result.getString("ACCTLOG_STMT_MAILFREQW"));
			acct_detail.setAccount_stmt_mailfreqM(result.getString("ACCTLOG_STMT_MAILFREQM"));
			acct_detail.setAccount_stmt_extra(result.getString("ACCTLOG_STMT_EXTRA"));
			acct_detail.setAccount_user_comments(result.getString("ACCTLOG_USER_COMMENTS"));
			acct_detail.setAccount_dayscheckvalid(result.getString("ACCTLOG_DAYSCHECKVALID"));
			acct_detail.setAccount_extra1(result.getString("ACCTLOG_EXTRA1"));
			acct_detail.setAccount_extra2(result.getString("ACCTLOG_EXTRA2"));
			acct_detail.setAccount_extra3(result.getString("ACCTLOG_EXTRA3"));
			acct_detail.setAccount_extra4(result.getString("ACCTLOG_EXTRA4"));
			acct_detail.setAccount_extra5(result.getString("ACCTLOG_EXTRA5"));
			acct_detail.setAccount_extra6(result.getString("ACCTLOG_EXTRA6"));
			acct_detail.setAccount_availday1(result.getString("ACCTLOG_AVAILDAY1"));
			acct_detail.setAccount_availlow1(result.getString("ACCTLOG_AVAILLOW1"));
			acct_detail.setAccount_availhigh1(result.getString("ACCTLOG_AVAILHIGH1"));
			acct_detail.setAccount_availday2(result.getString("ACCTLOG_AVAILDAY2"));
			acct_detail.setAccount_availlow2(result.getString("ACCTLOG_AVAILLOW2"));
			acct_detail.setAccount_availhigh2(result.getString("ACCTLOG_AVAILHIGH2"));
			acct_detail.setAccount_availday3(result.getString("ACCTLOG_AVAILDAY3"));
			acct_detail.setAccount_availlow3(result.getString("ACCTLOG_AVAILLOW3"));
			acct_detail.setAccount_availhigh3(result.getString("ACCTLOG_AVAILHIGH3"));
			acct_detail.setAccount_availday4(result.getString("ACCTLOG_AVAILDAY4"));
			acct_detail.setAccount_availlow4(result.getString("ACCTLOG_AVAILLOW4"));
			acct_detail.setAccount_availhigh4(result.getString("ACCTLOG_AVAILHIGH4"));
			acct_detail.setAccount_availday5(result.getString("ACCTLOG_AVAILDAY5"));
			acct_detail.setAccount_availlow5(result.getString("ACCTLOG_AVAILLOW5"));
			acct_detail.setAccount_availhigh5(result.getString("ACCTLOG_AVAILHIGH5"));
			acct_detail.setAccount_last_modified(result.getString("ACCTLOG_LAST_MODIFIED"));
			acct_detail.setAccount_mod_user(result.getString("ACCTLOG_MOD_USER"));
			acct_detail.setAccount_mod_func(result.getString("ACCTLOG_MOD_FUNC"));
			acctBeans.add(acct_detail);
			row_count++;
		}
		acctSelector.setAccountrows(acctBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int InsertUpdateAcct(Connection dbConn, AccountDetail acctDetail,
			String userId, int ins_or_upd)
	// 1 for insert or 2 for update
			throws Exception {
		moduleName = "InsertUpdateAcct: ";
		// Need the acct, posi and stop beans to validate for limit
		// stop and posi pay
		// PrintLog(": In Insert/Update Acct");
		acctDetail.clearNulls();
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		String mod_func		= "";
		// Here get all the fields and validate each aspect of the check.
		// 1. Check for the Limit
		// 2. Check for Stop payment
		// 3. If posipay required then check for existence of Posipay.
		//
		String dbUsed				= acctDetail.getDbUsed();
		// PrintLog(" DBUsed "+dbUsed);
		String account_num			= acctDetail.getAccount_num().trim();
		// PrintLog("account_num 1:"+account_num);
		String account_currency		= acctDetail.getAccount_currency();
		//PrintLog("account_currency: "+account_currency);
		String account_sw_addr		= acctDetail.getAccount_sw_addr();
		String account_acc_rep		= acctDetail.getAccount_acc_rep();
		String account_alt_acc_rep	= acctDetail.getAccount_alt_acc_rep();
		String account_client_name	= acctDetail.getAccount_client_name();
		account_client_name			= account_client_name.replaceAll("'", "''");
		String account_client_addr1	= acctDetail.getAccount_client_addr1();
		account_client_addr1		= account_client_addr1.replaceAll("'", "''");
		String account_client_addr2	= acctDetail.getAccount_client_addr2();
		account_client_addr2		= account_client_addr2.replaceAll("'", "''");
		String account_client_addr3	= acctDetail.getAccount_client_addr3();
		account_client_addr3		= account_client_addr3.replaceAll("'", "''");
		String account_client_addr4	= acctDetail.getAccount_client_addr4();
		account_client_addr4		= account_client_addr4.replaceAll("'", "''");
		String account_effective_date	= acctDetail.getAccount_effective_date();
		if (account_effective_date.equals(" ")) {
			account_effective_date	= "0";
		}
		String account_closed_date	= acctDetail.getAccount_closed_date();
		if (account_closed_date.equals(" ")) {
			account_closed_date		= "0";
		}
		String account_blocked_date	= acctDetail.getAccount_blocked_date();
		if (account_blocked_date.equals(" ")) {
			account_blocked_date	= "0";
		}
		String account_max_check_amount	= acctDetail.getAccount_max_check_amount();
		if (account_max_check_amount.equals(" ")) {
			account_max_check_amount = "0";
		}
		String account_posi_pay_flag	= acctDetail.getAccount_posi_pay_flag();
		String account_posi_pay_amt_min	= acctDetail.getAccount_posi_pay_amt_min();
		if (account_posi_pay_amt_min.equals(" ")) {
			account_posi_pay_amt_min	= "0";
		}
		String account_int_ext			= acctDetail.getAccount_int_ext();
		String account_gen_suspense		= acctDetail.getAccount_gen_suspense();
		String account_stop_suspense	= acctDetail.getAccount_stop_suspense();
		String account_exce_suspense	= " "; // acctDetail.getAccount_exce_suspense().trim();
		String account_stmt_email		= acctDetail.getAccount_stmt_email();
		String account_stmt_fax			= acctDetail.getAccount_stmt_fax();
		String account_stmt_mail1		= acctDetail.getAccount_stmt_mail1();
		String account_stmt_mail2		= acctDetail.getAccount_stmt_mail2();
		String account_stmt_mail3		= acctDetail.getAccount_stmt_mail3();
		String account_stmt_mail4		= acctDetail.getAccount_stmt_mail4();
		String account_stmt_emailfreqD	= acctDetail.getAccount_stmt_emailfreqD();
		String account_stmt_emailfreqW	= acctDetail.getAccount_stmt_emailfreqW();
		String account_stmt_emailfreqM	= acctDetail.getAccount_stmt_emailfreqM();
		String account_stmt_faxfreqD	= acctDetail.getAccount_stmt_faxfreqD();
		String account_stmt_faxfreqW	= acctDetail.getAccount_stmt_faxfreqW();
		String account_stmt_faxfreqM	= acctDetail.getAccount_stmt_faxfreqM();
		String account_stmt_mailfreqD	= acctDetail.getAccount_stmt_mailfreqD();
		String account_stmt_mailfreqW	= acctDetail.getAccount_stmt_mailfreqW();
		String account_stmt_mailfreqM	= acctDetail.getAccount_stmt_mailfreqM();
		String account_stmt_extra		= acctDetail.getAccount_stmt_extra();
		String account_user_comments	= acctDetail.getAccount_user_comments();
		String account_dayscheckvalid	= acctDetail.getAccount_dayscheckvalid();
		String account_extra1			= acctDetail.getAccount_extra1();
		/**/
		if (ins_or_upd == 1) {
			if (account_extra1.equals(" ")) {
				account_extra1	= "100";
			}
		}
		/**/
		String account_extra2			= acctDetail.getAccount_extra2();
		String account_extra3			= acctDetail.getAccount_extra3();
		String account_extra4			= acctDetail.getAccount_extra4();
		String account_extra5			= acctDetail.getAccount_extra5();
		String account_extra6			= acctDetail.getAccount_extra6();
		String account_availday1		= acctDetail.getAccount_availday1();
		String account_availlow1		= acctDetail.getAccount_availlow1();
		String account_availhigh1		= acctDetail.getAccount_availhigh1();
		String account_availday2		= acctDetail.getAccount_availday2();
		String account_availlow2		= acctDetail.getAccount_availlow2();
		String account_availhigh2		= acctDetail.getAccount_availhigh2();
		String account_availday3		= acctDetail.getAccount_availday3();
		String account_availlow3		= acctDetail.getAccount_availlow3();
		String account_availhigh3		= acctDetail.getAccount_availhigh3();
		String account_availday4		= acctDetail.getAccount_availday4();
		String account_availlow4		= acctDetail.getAccount_availlow4();
		String account_availhigh4		= acctDetail.getAccount_availhigh4();
		String account_availday5		= acctDetail.getAccount_availday5();
		String account_availlow5		= acctDetail.getAccount_availlow5();
		String account_availhigh5		= acctDetail.getAccount_availhigh5();
		//
		dbConn.setAutoCommit(false);
		// here we Insert or Update the Check row and insert a log row
		if (ins_or_upd == 1) {
			// account_gen_suspense = (account_num.substring(1,3)+
			// account_num.substring(4,10)+
			// account_num.substring(13));
			// acctDetail.setAccount_gen_suspense(account_gen_suspense);
			mod_func = "Add Acct";
			sql.setLength(0);
			sql.append("INSERT INTO incl_accounts VALUES (");
			sql.append("'" + account_num + "', ");
			sql.append("'" + account_currency + "', ");
			sql.append("'" + account_sw_addr + "', ");
			sql.append("'" + account_acc_rep + "', ");
			sql.append("'" + account_alt_acc_rep + "', ");
			sql.append("'" + account_client_name + "', ");
			sql.append("'" + account_client_addr1 + "', ");
			sql.append("'" + account_client_addr2 + "', ");
			sql.append("'" + account_client_addr3 + "', ");
			sql.append("'" + account_client_addr4 + "', ");
			sql.append("'" + account_effective_date + "', ");
			sql.append("'" + account_closed_date + "', ");
			sql.append("'" + account_blocked_date + "', ");
			sql.append("'" + account_max_check_amount + "', ");
			sql.append("'" + account_posi_pay_flag + "', ");
			sql.append("'" + account_posi_pay_amt_min + "', ");
			sql.append("'" + account_int_ext + "', ");
			sql.append("'" + account_gen_suspense + "', ");
			sql.append("'" + account_stop_suspense + "', ");
			sql.append("'" + account_exce_suspense + "', ");
			sql.append("'" + account_stmt_email + "', ");
			sql.append("'" + account_stmt_fax + "', ");
			sql.append("'" + account_stmt_mail1 + "', ");
			sql.append("'" + account_stmt_mail2 + "', ");
			sql.append("'" + account_stmt_mail3 + "', ");
			sql.append("'" + account_stmt_mail4 + "', ");
			sql.append("'" + account_stmt_emailfreqD + "', ");
			sql.append("'" + account_stmt_emailfreqW + "', ");
			sql.append("'" + account_stmt_emailfreqM + "', ");
			sql.append("'" + account_stmt_faxfreqD + "', ");
			sql.append("'" + account_stmt_faxfreqW + "', ");
			sql.append("'" + account_stmt_faxfreqM + "', ");
			sql.append("'" + account_stmt_mailfreqD + "', ");
			sql.append("'" + account_stmt_mailfreqW + "', ");
			sql.append("'" + account_stmt_mailfreqM + "', ");
			sql.append("'" + account_stmt_extra + "', ");
			sql.append("'" + account_user_comments + "', ");
			sql.append("'" + account_dayscheckvalid + "', ");
			// account_extra1 used for monthly average # of checks issued
			sql.append("'" + account_extra1 + "', ");
			sql.append("'" + account_extra2 + "', ");
			sql.append("'" + account_extra3 + "', ");
			sql.append("'" + account_extra4 + "', ");
			sql.append("'" + account_extra5 + "', ");
			// account_extra6 used for to Indicate same day availability of deposit amount
			sql.append("'" + account_extra6 + "', ");
			sql.append("'" + account_availday1 + "', ");
			sql.append("'" + account_availlow1 + "', ");
			sql.append("'" + account_availhigh1 + "', ");
			sql.append("'" + account_availday2 + "', ");
			sql.append("'" + account_availlow2 + "', ");
			sql.append("'" + account_availhigh2 + "', ");
			sql.append("'" + account_availday3 + "', ");
			sql.append("'" + account_availlow3 + "', ");
			sql.append("'" + account_availhigh3 + "', ");
			sql.append("'" + account_availday4 + "', ");
			sql.append("'" + account_availlow4 + "', ");
			sql.append("'" + account_availhigh4 + "', ");
			sql.append("'" + account_availday5 + "', ");
			sql.append("'" + account_availlow5 + "', ");
			sql.append("'" + account_availhigh5 + "', ");
			// Calendar rightNow = Calendar.getInstance() ;
			// java.sql.Date sqlDate = new java.sql.Date(
			// rightNow.getTimeInMillis()) ;
			// sql.append(sqlDate+", ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + userId + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog(": Account Insert SQL ---> "+sql);
			try {
				statement = dbConn.createStatement();
				// PrintLog(": Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog(": Error Inserting Account ->" + e.toString());
				PrintLog(": Account Insert SQL ---> " + sql);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling Insert Acct: " + ex);
				}
				statement.close();
				return (0);
			}
		} else {
			mod_func = "Modify Acct";
			sql.setLength(0);
			sql.append("update incl_accounts ");
			sql.append("set ");
			sql.append(" ACCOUNT_CURRENCY='" + account_currency + "', ");
			sql.append(" ACCOUNT_SW_ADDR='" + account_sw_addr + "', ");
			sql.append(" ACCOUNT_ACC_REP='" + account_acc_rep + "', ");
			sql.append(" ACCOUNT_ALT_ACC_REP='" + account_alt_acc_rep + "', ");
			sql.append(" ACCOUNT_CLIENT_NAME='" + account_client_name + "', ");
			sql.append(" ACCOUNT_EFFECTIVE_DATE='" + account_effective_date + "', ");
			sql.append(" ACCOUNT_CLOSED_DATE='" + account_closed_date + "', ");
			sql.append(" ACCOUNT_BLOCKED_DATE='" + account_blocked_date + "', ");
			sql.append(" ACCOUNT_MAX_CHECK_AMOUNT='" + account_max_check_amount + "', ");
			sql.append(" ACCOUNT_POSI_PAY_FLAG='" + account_posi_pay_flag + "', ");
			sql.append(" ACCOUNT_POSI_PAY_AMT_MIN='" + account_posi_pay_amt_min + "', ");
			sql.append(" ACCOUNT_INT_EXT='" + account_int_ext + "', ");
			sql.append(" ACCOUNT_GEN_SUSPENSE='" + account_gen_suspense + "', ");
			sql.append(" ACCOUNT_STOP_SUSPENSE='" + account_stop_suspense + "', ");
			sql.append(" ACCOUNT_EXCE_SUSPENSE='" + account_exce_suspense + "', ");
			sql.append(" ACCOUNT_STMT_EMAIL='" + account_stmt_email + "', ");
			sql.append(" ACCOUNT_STMT_FAX='" + account_stmt_fax + "', ");
			sql.append(" ACCOUNT_STMT_MAIL1='" + account_stmt_mail1 + "', ");
			sql.append(" ACCOUNT_STMT_MAIL2='" + account_stmt_mail2 + "', ");
			sql.append(" ACCOUNT_STMT_MAIL3='" + account_stmt_mail3 + "', ");
			sql.append(" ACCOUNT_STMT_MAIL4='" + account_stmt_mail4 + "', ");
			sql.append(" ACCOUNT_STMT_EMAILFREQD='" + account_stmt_emailfreqD + "', ");
			sql.append(" ACCOUNT_STMT_EMAILFREQW='" + account_stmt_emailfreqW + "', ");
			sql.append(" ACCOUNT_STMT_EMAILFREQM='" + account_stmt_emailfreqM + "', ");
			sql.append(" ACCOUNT_STMT_FAXFREQD='" + account_stmt_faxfreqD + "', ");
			sql.append(" ACCOUNT_STMT_FAXFREQW='" + account_stmt_faxfreqW + "', ");
			sql.append(" ACCOUNT_STMT_FAXFREQM='" + account_stmt_faxfreqM + "', ");
			sql.append(" ACCOUNT_STMT_MAILFREQD='" + account_stmt_mailfreqD + "', ");
			sql.append(" ACCOUNT_STMT_MAILFREQW='" + account_stmt_mailfreqW + "', ");
			sql.append(" ACCOUNT_STMT_MAILFREQM='" + account_stmt_mailfreqM + "', ");
			sql.append(" ACCOUNT_STMT_EXTRA='" + account_stmt_extra + "', ");
			sql.append(" ACCOUNT_USER_COMMENTS='" + account_user_comments + "', ");
			sql.append(" ACCOUNT_DAYSCHECKVALID='" + account_dayscheckvalid + "', ");
			sql.append(" ACCOUNT_EXTRA1='" + account_extra1 + "', ");
			sql.append(" ACCOUNT_EXTRA2='" + account_extra2 + "', ");
			sql.append(" ACCOUNT_EXTRA3='" + account_extra3 + "', ");
			sql.append(" ACCOUNT_EXTRA4='" + account_extra4 + "', ");
			sql.append(" ACCOUNT_EXTRA5='" + account_extra5 + "', ");
			sql.append(" ACCOUNT_EXTRA6='" + account_extra6 + "', ");
			sql.append(" ACCOUNT_AVAILDAY1='" + account_availday1 + "', ");
			sql.append(" ACCOUNT_AVAILLOW1='" + account_availlow1 + "', ");
			sql.append(" ACCOUNT_AVAILHIGH1='" + account_availhigh1 + "', ");
			sql.append(" ACCOUNT_AVAILDAY2='" + account_availday2 + "', ");
			sql.append(" ACCOUNT_AVAILLOW2='" + account_availlow2 + "', ");
			sql.append(" ACCOUNT_AVAILHIGH2='" + account_availhigh2 + "', ");
			sql.append(" ACCOUNT_AVAILDAY3='" + account_availday3 + "', ");
			sql.append(" ACCOUNT_AVAILLOW3='" + account_availlow3 + "', ");
			sql.append(" ACCOUNT_AVAILHIGH3='" + account_availhigh3 + "', ");
			sql.append(" ACCOUNT_AVAILDAY4='" + account_availday4 + "', ");
			sql.append(" ACCOUNT_AVAILLOW4='" + account_availlow4 + "', ");
			sql.append(" ACCOUNT_AVAILHIGH4='" + account_availhigh4 + "', ");
			sql.append(" ACCOUNT_AVAILDAY5='" + account_availday5 + "', ");
			sql.append(" ACCOUNT_AVAILLOW5='" + account_availlow5 + "', ");
			sql.append(" ACCOUNT_AVAILHIGH5='" + account_availhigh5 + "', ");
			// Calendar rightNow = Calendar.getInstance() ;
			// java.sql.Date sqlDate = new java.sql.Date(
			// rightNow.getTimeInMillis()) ;
			// sql.append("    ACCOUNT_LAST_MODIFIED="+sqlDate+", ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    ACCOUNT_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    ACCOUNT_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    ACCOUNT_MOD_USER='" + userId + "', ");
			sql.append("    ACCOUNT_MOD_FUNC='" + mod_func + "' ");
			sql.append("    WHERE ACCOUNT_NUM='" + account_num + "' ");
			// PrintLog(": Account Update SQL ---> "+sql);
			try {
				statement = dbConn.createStatement();
				// PrintLog(": Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog(": Error Updating Account ->" + e.toString());
				PrintLog(": Account Update SQL ---> " + sql);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling Modify Acct: " + ex);
				}
				statement.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		// PrintLog("account_num 2:"+account_num);
		sql.setLength(0);
		sql.append("INSERT INTO incl_accounts_log VALUES (");
		sql.append("'" + account_num + "', ");
		sql.append("'" + account_currency + "', ");
		sql.append("'" + account_sw_addr + "', ");
		sql.append("'" + account_acc_rep + "', ");
		sql.append("'" + account_alt_acc_rep + "', ");
		sql.append("'" + account_client_name + "', ");
		sql.append("'" + account_client_addr1 + "', ");
		sql.append("'" + account_client_addr2 + "', ");
		sql.append("'" + account_client_addr3 + "', ");
		sql.append("'" + account_client_addr4 + "', ");
		sql.append("'" + account_effective_date + "', ");
		sql.append("'" + account_closed_date + "', ");
		sql.append("'" + account_blocked_date + "', ");
		sql.append("'" + account_max_check_amount + "', ");
		sql.append("'" + account_posi_pay_flag + "', ");
		sql.append("'" + account_posi_pay_amt_min + "', ");
		sql.append("'" + account_int_ext + "', ");
		sql.append("'" + account_gen_suspense + "', ");
		sql.append("'" + account_stop_suspense + "', ");
		sql.append("'" + account_exce_suspense + "', ");
		sql.append("'" + account_stmt_email + "', ");
		sql.append("'" + account_stmt_fax + "', ");
		sql.append("'" + account_stmt_mail1 + "', ");
		sql.append("'" + account_stmt_mail2 + "', ");
		sql.append("'" + account_stmt_mail3 + "', ");
		sql.append("'" + account_stmt_mail4 + "', ");
		sql.append("'" + account_stmt_emailfreqD + "', ");
		sql.append("'" + account_stmt_emailfreqW + "', ");
		sql.append("'" + account_stmt_emailfreqM + "', ");
		sql.append("'" + account_stmt_faxfreqD + "', ");
		sql.append("'" + account_stmt_faxfreqW + "', ");
		sql.append("'" + account_stmt_faxfreqM + "', ");
		sql.append("'" + account_stmt_mailfreqD + "', ");
		sql.append("'" + account_stmt_mailfreqW + "', ");
		sql.append("'" + account_stmt_mailfreqM + "', ");
		sql.append("'" + account_stmt_extra + "', ");
		sql.append("'" + account_user_comments + "', ");
		sql.append("'" + account_dayscheckvalid + "', ");
		sql.append("'" + account_extra1 + "', ");
		sql.append("'" + account_extra2 + "', ");
		sql.append("'" + account_extra3 + "', ");
		sql.append("'" + account_extra4 + "', ");
		sql.append("'" + account_extra5 + "', ");
		sql.append("'" + account_extra6 + "', ");
		sql.append("'" + account_availday1 + "', ");
		sql.append("'" + account_availlow1 + "', ");
		sql.append("'" + account_availhigh1 + "', ");
		sql.append("'" + account_availday2 + "', ");
		sql.append("'" + account_availlow2 + "', ");
		sql.append("'" + account_availhigh2 + "', ");
		sql.append("'" + account_availday3 + "', ");
		sql.append("'" + account_availlow3 + "', ");
		sql.append("'" + account_availhigh3 + "', ");
		sql.append("'" + account_availday4 + "', ");
		sql.append("'" + account_availlow4 + "', ");
		sql.append("'" + account_availhigh4 + "', ");
		sql.append("'" + account_availday5 + "', ");
		sql.append("'" + account_availlow5 + "', ");
		sql.append("'" + account_availhigh5 + "', ");
		// Calendar rightNow = Calendar.getInstance() ;
		// java.sql.Date sqlDate = new java.sql.Date(
		// rightNow.getTimeInMillis()) ;
		// sql.append(sqlDate+", ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "')");
		// PrintLog(": Log Insert SQL ---> "+sql);
		try {
			statement = dbConn.createStatement();
			// PrintLog(": Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error inserting Account Log ->" + e.toString());
			// PrintLog(": Log Insert SQL ---> "+sql);
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling Insert Acct Log: " + ex);
			}
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		// result.close();
		return (1);
	}
	//
	public int DeleteAccount(Connection dbConn, AccountDetail acctDetail,
			String userId) throws Exception {
		moduleName = "DeleteAccount: ";
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		//
		String account_num = acctDetail.getAccount_num().trim();
		//
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT INTO incl_accounts_log ");
		sql.append("SELECT ACCOUNT_NUM, ACCOUNT_CURRENCY, ");
		sql.append("ACCOUNT_SW_ADDR, ACCOUNT_ACC_REP, ");
		sql.append("ACCOUNT_ALT_ACC_REP, ACCOUNT_CLIENT_NAME, ");
		sql.append("ACCOUNT_CLIENT_ADDR1, ");
		sql.append("ACCOUNT_CLIENT_ADDR2, ");
		sql.append("ACCOUNT_CLIENT_ADDR3, ");
		sql.append("ACCOUNT_CLIENT_ADDR4, ");
		sql.append("ACCOUNT_EFFECTIVE_DATE, ");
		sql.append("ACCOUNT_CLOSED_DATE, ");
		sql.append("ACCOUNT_BLOCKED_DATE, ");
		sql.append("ACCOUNT_MAX_CHECK_AMOUNT, ACCOUNT_POSI_PAY_FLAG, ");
		sql.append("ACCOUNT_POSI_PAY_AMT_MIN, ACCOUNT_INT_EXT, ");
		sql.append("ACCOUNT_GEN_SUSPENSE, ACCOUNT_STOP_SUSPENSE, ");
		sql.append("ACCOUNT_EXCE_SUSPENSE, ");
		sql.append("ACCOUNT_STMT_EMAIL, ");
		sql.append("ACCOUNT_STMT_FAX, ");
		sql.append("ACCOUNT_STMT_MAIL1, ");
		sql.append("ACCOUNT_STMT_MAIL2, ");
		sql.append("ACCOUNT_STMT_MAIL3, ");
		sql.append("ACCOUNT_STMT_MAIL4, ");
		sql.append("ACCOUNT_STMT_EMAILFREQD, ");
		sql.append("ACCOUNT_STMT_EMAILFREQW, ");
		sql.append("ACCOUNT_STMT_EMAILFREQM, ");
		sql.append("ACCOUNT_STMT_FAXFREQD, ");
		sql.append("ACCOUNT_STMT_FAXFREQW, ");
		sql.append("ACCOUNT_STMT_FAXFREQM, ");
		sql.append("ACCOUNT_STMT_MAILFREQD, ");
		sql.append("ACCOUNT_STMT_MAILFREQW, ");
		sql.append("ACCOUNT_STMT_MAILFREQM, ");
		sql.append("ACCOUNT_STMT_EXTRA, ");
		sql.append("ACCOUNT_USER_COMMENTS, ");
		sql.append("ACCOUNT_DAYSCHECKVALID, ");
		sql.append("ACCOUNT_EXTRA1, ");
		sql.append("ACCOUNT_EXTRA2, ");
		sql.append("ACCOUNT_EXTRA3, ");
		sql.append("ACCOUNT_EXTRA4, ");
		sql.append("ACCOUNT_EXTRA5, ");
		sql.append("ACCOUNT_EXTRA6, ");
		sql.append("ACCOUNT_AVAILDAY1, ");
		sql.append("ACCOUNT_AVAILLOW1, ");
		sql.append("ACCOUNT_AVAILHIGH1, ");
		sql.append("ACCOUNT_AVAILDAY2, ");
		sql.append("ACCOUNT_AVAILLOW2, ");
		sql.append("ACCOUNT_AVAILHIGH2, ");
		sql.append("ACCOUNT_AVAILDAY3, ");
		sql.append("ACCOUNT_AVAILLOW3, ");
		sql.append("ACCOUNT_AVAILHIGH3, ");
		sql.append("ACCOUNT_AVAILDAY4, ");
		sql.append("ACCOUNT_AVAILLOW4, ");
		sql.append("ACCOUNT_AVAILHIGH4, ");
		sql.append("ACCOUNT_AVAILDAY5, ");
		sql.append("ACCOUNT_AVAILLOW5, ");
		sql.append("ACCOUNT_AVAILHIGH5, ");
		sql.append("ACCOUNT_LAST_MODIFIED, '");
		sql.append(userId + "', 'Delete Acct' ");
		sql.append("FROM incl_accounts WHERE ACCOUNT_NUM='" + account_num + "'");
		//PrintLog(": Log Insert SQL ---> "+sql);
		try {
			statement = dbConn.createStatement();
			// PrintLog(": Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error inserting Account Log ->" + e.toString());
			acctDetail.setErrorVec("Account Log", "error.inserting");
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling Back Insert Acct Log: " + ex);
			}
			statement.close();
			return (0);
		}
		sql.setLength(0);
		sql.append("DELETE from incl_accounts ");
		sql.append("WHERE ACCOUNT_NUM='" + account_num + "'");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting Account SQL ->" + sql);
			PrintLog("Error Deleting Account Cause ->" + e.toString());
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling back Insert Log: " + ex);
			}
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
}
