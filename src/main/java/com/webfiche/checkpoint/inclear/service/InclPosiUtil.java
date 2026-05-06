/*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */

package com.webfiche.checkpoint.inclear.service;

import java.io.*;
import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
//import org.apache.struts.action.*;
import java.text.*;
/**
 * <strong>InclPosiUtil</strong> is a utility class to provide methods to access
 * .and manipilate the incl_posi_pay database table.
 * 
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
@Service
public class InclPosiUtil {
	String moduleName;
	String calledFrom;
	String className = "> InclPosiUtil.";
	/*
	 * String pospay_from_acct; String pospay_to_acct; String pospay_currency;
	 * String pospay_from_check; String pospay_to_check; String
	 * pospay_check_amount; String pospay_swift_ref; String pospay_issue_date;
	 * String pospay_status_sel; String pospay_log_date; String pospay_log_user;
	 */
	//
	ArrayList<PosipayDetail> posiBeans = new ArrayList<PosipayDetail>();
	String param = "";
	int row_count = 0;
	//
	public InclPosiUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + logMsg);
	}
	// The following Methods are specialised for each of the incl tables.
	// The first is a set of methods for specific purposes;
	// 1. Get the accounts number colums of every row in the incl_accounts table
	// for the Accounts Inquiry/Maintenance selection table.
	//
	public ArrayList<String> GetPosiAccountList(Connection dbConn)
			throws Exception {
		moduleName	= "> InclPosiUtil.GetPosiAccountList: ";
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> posi_list = new ArrayList<String>();
		posi_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT POSPAY_ACCOUNT_NUM from incl_posi_pay ORDER by POSPAY_ACCOUNT_NUM");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp	= result.getString("POSPAY_ACCOUNT_NUM");
			posi_list.add(temp);
		}
		statement.close();
		result.close();
		return (posi_list);
	}
	//
	public ArrayList<String> GetPosiLogAccountList(Connection dbConn)
			throws Exception {
		moduleName	= "GetPosiLogAccountList: ";
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> posi_list	= new ArrayList<String>();
		posi_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT PPLOG_ACCOUNT_NUM from incl_posi_pay_log ORDER by PPLOG_ACCOUNT_NUM");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp	= result.getString("PPLOG_ACCOUNT_NUM");
			// PrintLog("PPLOG_ACCOUNT_NUM "+temp);
			posi_list.add(temp);
		}
		statement.close();
		result.close();
		return (posi_list);
	}
	//
	public ArrayList<String> GetPosiLogDateList(Connection dbConn)
			throws Exception {
		moduleName	= "GetPosiLogDateList: ";
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> pdate_list	= new ArrayList<String>();
		pdate_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT SUBSTR(to_char(PPLOG_LAST_MODIFIED,'yyyymmdd hh24:mi:ss'),1,8) as PLM1, ");
		sql.append("SUBSTR(PPLOG_LAST_MODIFIED,1,9) as PLM from incl_posi_pay_log ORDER by PLM1 DESC");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("PLM");
			pdate_list.add(temp);
		}
		statement.close();
		result.close();
		return (pdate_list);
	}
	//
	public ArrayList<String> GetPosiLogUserList(Connection dbConn)
			throws Exception {
		moduleName	= "> InclPosiUtil.GetPosiLogUserList: ";
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> puser_list	= new ArrayList<String>();
		puser_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT PPLOG_MOD_USER from incl_posi_pay_log ORDER by PPLOG_MOD_USER");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("PPLOG_MOD_USER");
			puser_list.add(temp);
		}
		statement.close();
		result.close();
		return (puser_list);
	}
	//
	public boolean PosipayExists(Connection dbConn, String acct_num,
			String check_num, PosipayDetail posiDetail) throws Exception {
		// Called by the Add module with an account number
		// --------------------------------------------------
		moduleName = "> InclPosiUtil.PosipayExists: ";
		if (acct_num.length() == 0) {
			posiDetail.setErrorVec("Account", "error.field.required");
			PrintLog("Error Account number required  " + acct_num);
			return (true);
		}
		if (check_num.length() == 0) {
			posiDetail.setErrorVec("Check Number", "error.field.required");
			PrintLog("Error Check number required " + check_num);
			return (true);
		}
		param = "WHERE POSPAY_ACCOUNT_NUM='" + acct_num;
		param += "' AND POSPAY_CHECK_NUM='" + check_num + "'";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT POSPAY_ACCOUNT_NUM from incl_posi_pay ");
		sql.append(param);
		// PrintLog("Posipay Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("POSPAY_ACCOUNT_NUM");
			statement.close();
			result.close();
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	// public java.util.ArrayList<String> GetAccountRows (Connection dbConn,
	// Called by InclChexUtil to Match Posipay to the Check will be
	// updated but will not worry about lockin since it will rarely cause
	// a deadlock
	//
	public int GetPosipayRows(Connection dbConn,
			// ArrayList<String> posi_plist,
			PosipaySelector posiSelector, String acct_num, String check_num,
			String pospay_status) throws Exception {
		moduleName = "> InclPosiUtil.GetPosipayRows: ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param = "WHERE POSPAY_ACCOUNT_NUM='" + acct_num + "' ";
		param += "AND POSPAY_CHECK_NUM='" + check_num + "' ";
		param += "AND POSPAY_STATUS='" + pospay_status + "'";
		// ArrayList<String> acct_rows = new ArrayList<String>();
		// acct_rows = GetAccountRows (dbConn, acct_plist, posiSelector);
		row_count = GetPosipayRows(dbConn, posiSelector);
		return (row_count);
	}
	//
	// Called PosipayModifyAddAction should be locked.
	//
	public int GetPosipayRows(Connection dbConn,
	// ArrayList<String> posi_plist,
			PosipaySelector posiSelector, String acct_num, String check_num)
			throws Exception {
		moduleName = "> InclPosiUtil.GetPosipayRows: ";
		String dbUsed = posiSelector.getDbUsed();
		// Called by the modify module with an account number
		// --------------------------------------------------
		param = "WHERE POSPAY_ACCOUNT_NUM='" + acct_num + "'";
		param += " AND POSPAY_CHECK_NUM='" + check_num + "'";
		// ArrayList<String> acct_rows = new ArrayList<String>();
		// acct_rows = GetAccountRows (dbConn, acct_plist, posiSelector);
		String accessFlag = posiSelector.getAccessFlag();
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
		row_count = GetPosipayRows(dbConn, posiSelector);
		return (row_count);
	}

	//
	// public java.util.ArrayList<String> GetAccountRows (Connection dbConn,
	// Called by Inq and other GetPosipayRows -- If lcok needed will be
	// specified
	// in the param by the calling GetPOsipayRows.
	//
	public int GetPosipayRows(Connection dbConn, PosipaySelector posiSelector)
			throws Exception {
		moduleName = "> InclPosiUtil.GetPosipayRows: ";
		String dbUsed = posiSelector.getDbUsed();
		// ArrayList<String> acct_rows = new ArrayList<String>();
		if (param.equals(""))
			// GetParams (posiSelector);
			param = posiSelector.GetParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT POSPAY_ACCOUNT_NUM, POSPAY_CHECK_NUM, ");
		sql.append("POSPAY_VALUE_DATE, POSPAY_CURRENCY, ");
		sql.append("POSPAY_CHECK_AMOUNT, POSPAY_SWIFT_REF, ");
		sql.append("POSPAY_ISSUE_DATE, POSPAY_PAYEE, ");
		sql.append("POSPAY_PAYEE_ADDR1, ");
		sql.append("POSPAY_PAYEE_ADDR2, ");
		sql.append("POSPAY_PAYEE_ADDR3, ");
		sql.append("POSPAY_PROC_DATE, ");
		sql.append("POSPAY_EXPIRY_DATE, POSPAY_STATUS, ");
		sql.append("POSPAY_LAST_MODIFIED, ");
		sql.append("POSPAY_MOD_USER, POSPAY_MOD_FUNC ");
		sql.append("FROM incl_posi_pay ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			PosipayDetail posi_detail = new PosipayDetail();
			// Here add the fields tp the PosipayDetail bean
			posi_detail.setDbUsed(dbUsed);
			posi_detail.setPospay_account_num(result.getString("POSPAY_ACCOUNT_NUM"));
			posi_detail.setPospay_check_num(result.getString("POSPAY_CHECK_NUM"));
			posi_detail.setPospay_value_date(result.getString("POSPAY_VALUE_DATE"));
			posi_detail.setPospay_currency(result.getString("POSPAY_CURRENCY"));
			posi_detail.setPospay_check_amount(result.getString("POSPAY_CHECK_AMOUNT"));
			posi_detail.setPospay_swift_ref(result.getString("POSPAY_SWIFT_REF"));
			posi_detail.setPospay_issue_date(result.getString("POSPAY_ISSUE_DATE"));
			posi_detail.setPospay_payee(result.getString("POSPAY_PAYEE"));
			posi_detail.setPospay_payee_addr1(result.getString("POSPAY_PAYEE_ADDR1"));
			posi_detail.setPospay_payee_addr2(result.getString("POSPAY_PAYEE_ADDR2"));
			posi_detail.setPospay_payee_addr3(result.getString("POSPAY_PAYEE_ADDR3"));
			posi_detail.setPospay_proc_date(result.getString("POSPAY_PROC_DATE"));
			posi_detail.setPospay_expiry_date(result.getString("POSPAY_EXPIRY_DATE"));
			posi_detail.setPospay_status(result.getString("POSPAY_STATUS"));
			posi_detail.setPospay_last_modified(result.getString("POSPAY_LAST_MODIFIED"));
			posi_detail.setPospay_mod_user(result.getString("POSPAY_MOD_USER"));
			posi_detail.setPospay_mod_func(result.getString("POSPAY_MOD_FUNC"));
			posi_detail.setPospay_modparam();
			posi_detail.clearNulls();
			posiBeans.add(posi_detail);
			row_count++;
			// PrintLog("Getting Row: "+row_count);
		}
		posiSelector.setPosipayrows(posiBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	public int GetPosipayLogRows(Connection dbConn,
	// ArrayList<String> posi_plist,
			PosipaySelector posiSelector) throws Exception {
		moduleName = "> InclPosiUtil.GetPosipayLogRows: ";
		String dbUsed = posiSelector.getDbUsed();
		// ArrayList<String> acct_rows = new ArrayList<String>();
		if (param.equals("")) {
			// GetLogParams (posiSelector);
			param = posiSelector.GetLogParams();
		}
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT PPLOG_ACCOUNT_NUM, PPLOG_CHECK_NUM, ");
		sql.append("PPLOG_VALUE_DATE, PPLOG_CURRENCY, ");
		sql.append("PPLOG_CHECK_AMOUNT, ");
		sql.append("PPLOG_SWIFT_REF, ");
		sql.append("PPLOG_ISSUE_DATE, ");
		sql.append("PPLOG_PAYEE, ");
		sql.append("PPLOG_PAYEE_ADDR1, ");
		sql.append("PPLOG_PAYEE_ADDR2, ");
		sql.append("PPLOG_PAYEE_ADDR3, ");
		sql.append("PPLOG_PROC_DATE, ");
		sql.append("PPLOG_EXPIRY_DATE, PPLOG_STATUS, ");
		sql.append("PPLOG_LAST_MODIFIED, ");
		sql.append("PPLOG_MOD_USER, PPLOG_MOD_FUNC ");
		sql.append("FROM incl_posi_pay_log ");
		sql.append(param);

		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			PosipayDetail posi_detail = new PosipayDetail();
			// Here add the fields tp the PosipayDetail bean
			posi_detail.setDbUsed(dbUsed);
			// PrintLog("Getting 1");
			posi_detail.setPospay_account_num(result.getString("PPLOG_ACCOUNT_NUM"));
			// PrintLog("Getting 2");
			posi_detail.setPospay_check_num(result.getString("PPLOG_CHECK_NUM"));
			// PrintLog("Getting 3");
			posi_detail.setPospay_value_date(result.getString("PPLOG_VALUE_DATE"));
			// PrintLog("Getting 4");
			posi_detail.setPospay_currency(result.getString("PPLOG_CURRENCY"));
			// PrintLog("Getting 5");
			posi_detail.setPospay_check_amount(result.getString("PPLOG_CHECK_AMOUNT"));
			// PrintLog("Getting 6");
			posi_detail.setPospay_swift_ref(result.getString("PPLOG_SWIFT_REF"));
			// PrintLog("Getting 7");
			posi_detail.setPospay_issue_date(result.getString("PPLOG_ISSUE_DATE"));
			// PrintLog("Getting 8");
			posi_detail.setPospay_payee(result.getString("PPLOG_PAYEE"));
			// PrintLog("Getting 9");
			posi_detail.setPospay_payee_addr1(result.getString("PPLOG_PAYEE_ADDR1"));
			// PrintLog("Getting 10");
			posi_detail.setPospay_payee_addr2(result.getString("PPLOG_PAYEE_ADDR2"));
			// PrintLog("Getting 11");
			posi_detail.setPospay_payee_addr3(result.getString("PPLOG_PAYEE_ADDR3"));
			// PrintLog("Getting 12");
			posi_detail.setPospay_proc_date(result.getString("PPLOG_PROC_DATE"));
			// PrintLog("Getting 13");
			posi_detail.setPospay_expiry_date(result.getString("PPLOG_EXPIRY_DATE"));
			// PrintLog("Getting 14");
			posi_detail.setPospay_status(result.getString("PPLOG_STATUS"));
			// PrintLog("Getting 15");
			posi_detail.setPospay_last_modified(result.getString("PPLOG_LAST_MODIFIED"));
			// PrintLog("Getting 16");
			posi_detail.setPospay_mod_user(result.getString("PPLOG_MOD_USER"));
			// PrintLog("Getting 17");
			posi_detail.setPospay_mod_func(result.getString("PPLOG_MOD_FUNC"));
			posi_detail.setPospay_modparam();
			posiBeans.add(posi_detail);
			row_count++;
		}
		posiSelector.setPosipayrows(posiBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	//
	public int InsertUpdatePosi(Connection dbConn, PosipayDetail posiDetail,
	// ActionErrors errors,
			String user_name, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "> InclPosiUtil.InsertUpdatePosi: ";
		String dbUsed		= posiDetail.getDbUsed();
		// PrintLog("In Insert/Update Posipay DBUSED >"+dbUsed+"<");
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		String mod_func		= "";
		//
		posiDetail.clearNulls();
		String pospay_check_num		= "";
		String pospay_account_num	= posiDetail.getPospay_account_num().trim();
		try {
			pospay_check_num	= Integer.parseInt(posiDetail.getPospay_check_num()) + "";
		} catch (Exception e) {
			pospay_check_num	= posiDetail.getPospay_check_num();
		}
		// PrintLog("Posipay Insert/Update check num --->"+
		// posiDetail.getPospay_check_num()+"<");
		// PrintLog("Posipay Insert/Update check num ---> "+pospay_check_num());
		String pospay_value_date	= posiDetail.getPospay_value_date();
		String pospay_currency		= posiDetail.getPospay_currency();
		String pospay_check_amount	= posiDetail.getPospay_check_amount();
		String pospay_swift_ref		= posiDetail.getPospay_swift_ref();
		if (pospay_swift_ref == null) {
			pospay_swift_ref	= " ";
		}
		if (pospay_swift_ref.equals("")) {
			pospay_swift_ref	= " ";
		}
		String pospay_issue_date	= posiDetail.getPospay_issue_date();
		String pospay_payee			= posiDetail.getPospay_payee();
		pospay_payee				= pospay_payee.replaceAll("'", "''");
		String pospay_payee_addr1	= posiDetail.getPospay_payee_addr1();
		if (pospay_payee_addr1 == null) {
			pospay_payee_addr1	= " ";
		}
		if (pospay_payee_addr1.equals("")) {
			pospay_payee_addr1	= " ";
		}
		pospay_payee_addr1	= pospay_payee_addr1.replaceAll("'", "''");
		String pospay_payee_addr2	= posiDetail.getPospay_payee_addr2();
		if (pospay_payee_addr2 == null) {
			pospay_payee_addr2	= " ";
		}
		if (pospay_payee_addr2.equals("")) {
			pospay_payee_addr2	= " ";
		}
		pospay_payee_addr2	= pospay_payee_addr2.replaceAll("'", "''");
		String pospay_payee_addr3	= posiDetail.getPospay_payee_addr3();
		if (pospay_payee_addr3 == null) {
			pospay_payee_addr3	= " ";
		}
		if (pospay_payee_addr3.equals("")) {
			pospay_payee_addr3	= " ";
		}
		pospay_payee_addr3	= pospay_payee_addr3.replaceAll("'", "''");
		String pospay_proc_date		= posiDetail.getPospay_proc_date();
		String pospay_expiry_date	= posiDetail.getPospay_expiry_date();
		String pospay_status		= posiDetail.getPospay_status();
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			mod_func	= "Add Posi";
			sql.setLength(0);
			sql.append("INSERT INTO incl_posi_pay VALUES (");
			sql.append("'" + pospay_account_num + "', ");
			sql.append("'" + pospay_check_num + "', ");
			sql.append("'" + pospay_value_date + "', ");
			sql.append("'" + pospay_currency + "', ");
			sql.append("'" + pospay_check_amount + "', ");
			sql.append("'" + pospay_swift_ref + "', ");
			sql.append("'" + pospay_issue_date + "', ");
			sql.append("'" + pospay_payee + "', ");
			sql.append("'" + pospay_payee_addr1 + "', ");
			sql.append("'" + pospay_payee_addr2 + "', ");
			sql.append("'" + pospay_payee_addr3 + "', ");
			sql.append("'" + pospay_proc_date + "', ");
			sql.append("'" + pospay_expiry_date + "', ");
			sql.append("'" + pospay_status + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			//PrintLog("Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Posipay ->" + e.toString());
				PrintLog("SQL: " + sql);
				posiDetail.setErrorVec("Positive Payment Table", "error.inserting");
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling Insert: " + ex);
				}
				statement.close();
				// result.close();
				return (0);
			}
		} else {
			mod_func = "Modify Posi";
			sql.setLength(0);
			sql.append("UPDATE incl_posi_pay SET ");
			sql.append("POSPAY_VALUE_DATE='" + pospay_value_date + "', ");
			sql.append("POSPAY_CURRENCY='" + pospay_currency + "', ");
			sql.append("POSPAY_CHECK_AMOUNT='" + pospay_check_amount + "', ");
			sql.append("POSPAY_SWIFT_REF='" + pospay_swift_ref + "', ");
			sql.append("POSPAY_ISSUE_DATE='" + pospay_issue_date + "', ");
			sql.append("POSPAY_PAYEE='" + pospay_payee + "', ");
			sql.append("POSPAY_PAYEE_ADDR1='" + pospay_payee_addr1 + "', ");
			sql.append("POSPAY_PAYEE_ADDR2='" + pospay_payee_addr2 + "', ");
			sql.append("POSPAY_PAYEE_ADDR3='" + pospay_payee_addr3 + "', ");
			sql.append("POSPAY_PROC_DATE='" + pospay_proc_date + "', ");
			sql.append("POSPAY_EXPIRY_DATE='" + pospay_expiry_date + "', ");
			sql.append("POSPAY_STATUS='" + pospay_status + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("POSPAY_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("POSPAY_LAST_MODIFIED=sysdate, ");
			}
			sql.append("POSPAY_MOD_USER='" + user_name + "', ");
			sql.append("POSPAY_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE POSPAY_ACCOUNT_NUM='" + pospay_account_num + "' ");
			sql.append("AND POSPAY_CHECK_NUM='" + pospay_check_num + "'");
			// PrintLog("SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating ->" + e.toString());
				PrintLog("SQL: " + sql);
				posiDetail.setErrorVec("Positive Payment Table", "error.updating");
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling Back Modify: " + ex);
				}
				statement.close();
				// result.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO incl_posi_pay_log VALUES (");
		sql.append("'" + pospay_account_num + "', ");
		sql.append("'" + pospay_check_num + "', ");
		sql.append("'" + pospay_value_date + "', ");
		sql.append("'" + pospay_currency + "', ");
		sql.append("'" + pospay_check_amount + "', ");
		sql.append("'" + pospay_swift_ref + "', ");
		sql.append("'" + pospay_issue_date + "', ");
		sql.append("'" + pospay_payee + "', ");
		sql.append("'" + pospay_payee_addr1 + "', ");
		sql.append("'" + pospay_payee_addr2 + "', ");
		sql.append("'" + pospay_payee_addr3 + "', ");
		sql.append("'" + pospay_proc_date + "', ");
		sql.append("'" + pospay_expiry_date + "', ");
		sql.append("'" + pospay_status + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		//PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Posi Log ->"+ e.toString());
			PrintLog("SQL: " + sql);
			posiDetail.setErrorVec("Positive Payment Log", "error.inserting");
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling insert Log: " + ex);
			}
			statement.close();
			// result.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		// result.close();
		return (1);
	}

	//
	public int DeletePosipay(Connection dbConn, PosipayDetail posiDetail,
	// ActionErrors errors,
			String user_name) // ,
			throws Exception {
		moduleName			= "> InclPosiUtil.DeletePosipay: ";
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		String mod_func		= "Delete Posi";
		//
		posiDetail.clearNulls();
		String dbUsed		= posiDetail.getDbUsed().trim();
		String pospay_account_num	= posiDetail.getPospay_account_num().trim();
		String pospay_check_num		= "";
		// String pospay_check_num = posiDetail.getPospay_check_num();
		try {
			pospay_check_num = Integer.parseInt(posiDetail.getPospay_check_num()) + "";
		} catch (Exception e) {
			pospay_check_num = posiDetail.getPospay_check_num();
		}
		// String pospay_check_num =
		// Integer.parseInt(posiDetail.getPospay_check_num())+"";
		// PrintLog("Posipay Insert/Update check num ---> "+pospay_check_num);
		String pospay_value_date = posiDetail.getPospay_value_date();
		String pospay_currency = posiDetail.getPospay_currency();
		String pospay_check_amount = posiDetail.getPospay_check_amount();
		String pospay_swift_ref = posiDetail.getPospay_swift_ref();
		String pospay_issue_date = posiDetail.getPospay_issue_date();
		String pospay_payee = posiDetail.getPospay_payee();
		pospay_payee = pospay_payee.replaceAll("'", "''");
		String pospay_payee_addr1 = posiDetail.getPospay_payee_addr1();
		pospay_payee_addr1 = pospay_payee_addr1.replaceAll("'", "''");
		String pospay_payee_addr2 = posiDetail.getPospay_payee_addr2();
		pospay_payee_addr2 = pospay_payee_addr2.replaceAll("'", "''");
		String pospay_payee_addr3 = posiDetail.getPospay_payee_addr3();
		pospay_payee_addr3 = pospay_payee_addr3.replaceAll("'", "''");
		String pospay_proc_date = posiDetail.getPospay_proc_date();
		String pospay_expiry_date = posiDetail.getPospay_expiry_date();
		String pospay_status = posiDetail.getPospay_status();
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT INTO incl_posi_pay_log VALUES (");
		sql.append("'" + pospay_account_num + "', ");
		sql.append("'" + pospay_check_num + "', ");
		sql.append("'" + pospay_value_date + "', ");
		sql.append("'" + pospay_currency + "', ");
		sql.append("'" + pospay_check_amount + "', ");
		sql.append("'" + pospay_swift_ref + "', ");
		sql.append("'" + pospay_issue_date + "', ");
		sql.append("'" + pospay_payee + "', ");
		sql.append("'" + pospay_payee_addr1 + "', ");
		sql.append("'" + pospay_payee_addr2 + "', ");
		sql.append("'" + pospay_payee_addr3 + "', ");
		sql.append("'" + pospay_proc_date + "', ");
		sql.append("'" + pospay_expiry_date + "', ");
		sql.append("'" + pospay_status + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Posi Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
			posiDetail.setErrorVec("Positive Payment Log", "error.inserting");
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling back Insert log: " + ex);
			}
			statement.close();
			// result.close();
			return (0);
		}
		sql.setLength(0);
		sql.append("DELETE from incl_posi_pay ");
		sql.append("WHERE POSPAY_ACCOUNT_NUM='" + pospay_account_num + "' ");
		sql.append("AND POSPAY_CHECK_NUM='" + pospay_check_num + "'");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting Posipay ->" + e.toString());
			posiDetail.setErrorVec("Positive Payment", "error.deleting");
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling back delete: " + ex);
			}
			statement.close();
			// result.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}

	//
	public int ExtractPosipayRows(Connection dbConn,
			PosipaySelector posiSelector) throws Exception {
		//
		moduleName = "> InclPosiUtil.ExtractPosipayRows: ";
		SysadControlUtil ctlUtil = new SysadControlUtil();
		ControlSelector ctlSelector = new ControlSelector();
		ControlDetail ctlDetail = new ControlDetail();
		int rowCount = posiSelector.getRowCount();
		PosipayDetail posiDetail[] = new PosipayDetail[rowCount];
		PosipayDetail pD = new PosipayDetail();
		String extractFileName = "";
		String fileTime = "";
		DateFormat newFmt2 = null;
		String outputPath = "";
		String outputPosipay = "";
		//
		byte[] newText;
		FileOutputStream outExtract = null;
		PrintStream psExtract = null;
		//
		String prodId = "A";
		rowCount = ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
		ctlDetail = ctlSelector.getARow();
		outputPath = ctlDetail.getLocOutputDir();
		outputPath += "incl/";
		//
		/*
		 * //try { // int ctlRow = ctlUtil.GetControlRow (dbConn, ctlDetail);
		 * //} catch (Throwable t) { //
		 * PrintLog("Error Getting Control ->"+t.toString()); // return (0);
		 * //} //outputPath = ctlDetail.getInterface_f8();
		 */
		//
		newFmt2 = new SimpleDateFormat("yyyyMMddHHmmss");
		fileTime = newFmt2.format(java.util.Calendar.getInstance().getTime());
		extractFileName = outputPath + "ExtractPosipay_" + fileTime;
		// PrintLog("Extract File Name -> "+extractFileName);
		outExtract = new FileOutputStream(extractFileName + "_temp");
		psExtract = new PrintStream(outExtract);
		//
		posiDetail = posiSelector.getPosipayrows();
		outputPosipay = ("Account Number" + "\t" + "Check Number" +
						 "\t" +
						 "Value Date" +
						 "\t" +
						 // "Currency"+"\t"+
						 "Amount" + "\t" + "Swift Refernce" + "\t" + "Issue Date" + "\t" +
						 "Payee" + "\t" + "Payee Address Line 1" + "\t" +
						 "Payee Address Line 2" + "\t" + "Payee Address Line 3" + "\t" +
						 "Status");
		newText = outputPosipay.getBytes();
		outExtract.write(newText, 0, outputPosipay.length());
		psExtract.println();
		outputPosipay = "";
		for (int idx = 0; idx <= posiDetail.length - 1; idx++) {
			pD = posiDetail[idx];
			// Here get the fields from the PosipayDetail bean
			String pospay_account_num	= pD.getPospay_account_num();
			String pospay_check_num		= pD.getPospay_check_num();
			String pospay_value_date	= pD.getPospay_value_date();
			// String pospay_currency	= pD.getPospay_currency();
			String pospay_check_amount	= pD.getPospay_check_amount();
			String pospay_swift_ref		= pD.getPospay_swift_ref();
			String pospay_issue_date	= pD.getPospay_issue_date();
			String pospay_payee			= pD.getPospay_payee();
			String pospay_payee_addr1	= pD.getPospay_payee_addr1();
			String pospay_payee_addr2	= pD.getPospay_payee_addr2();
			String pospay_payee_addr3	= pD.getPospay_payee_addr3();
			String pospay_status		= pD.getPospay_status();
			outputPosipay	= (pospay_account_num + "\t" +
							   pospay_check_num + "\t" +
							   // pospay_value_date + "/" +
							   pospay_value_date.substring(4, 6) + "/" +
							   pospay_value_date.substring(6, 8) + "/" +
							   pospay_value_date.substring(0, 4) + "\t" +
							   // pospay_currency + "\t" +
							   pospay_check_amount + "\t" +
							   pospay_swift_ref + "\t" +
							   // pospay_issue_date + "/" +
							   pospay_issue_date.substring(4, 6) + "/" +
							   pospay_issue_date.substring(6, 8) + "/" +
							   pospay_issue_date.substring(0, 4) + "\t" +
							   pospay_payee + "\t" +
							   pospay_payee_addr1 + "\t" +
							   pospay_payee_addr2 + "\t" +
							   pospay_payee_addr3 + "\t" +
							   pospay_status);
			newText		= outputPosipay.getBytes();
			outExtract.write(newText, 0, outputPosipay.length());
			psExtract.println();
			outputPosipay	= "";
		}
		outExtract.close();
		PrintLog("Will Rename " + extractFileName + "_temp to " + extractFileName + ".xls");
		File extractFile	= new File(extractFileName + "_temp");
		extractFile.renameTo(new File(extractFileName + ".xls"));
		pD.setErrorVec("Positive Payment", "result.data.extracted");
		return (rowCount);
	}
}
