/*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */

package com.webfiche.checkpoint.inclear.service;

import java.io.*;
import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.*;
//import org.apache.struts.action.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;

/**
 * <strong>InclStopUtil</strong> is a utility class to provide methods to access
 * .and manipilate the incl_stop_pay database table.
 * 
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
@Service
public class InclStopUtil {
	private String		   className = "> InclStopUtil.";
	private String		   moduleName;
	String				   stopay_from_acct;
	String				   stopay_to_acct;
	String				   stopay_from_check;
	String				   stopay_to_check;
	String				   stopay_currency;
	String				   stopay_check_amount;
	String				   stopay_swift_ref;
	String				   stopay_issue_date;
	String				   stopay_status_sel;
	String				   stopay_log_date;
	String				   stopay_log_user;
	ArrayList<String>		paramArray;
	ArrayList<StoppayDetail> stopBeans = new ArrayList<StoppayDetail>();
	String				   param	 = "";
	int					  row_count = 0;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName +
				// userId+
				" - " + logMsg);
	}
	//
	public InclStopUtil() {
	}
	//
	//
	// The following Methods are specialised for each of the incl tables.
	// The first is a set of methods for specific purposes;
	// 1. Get the accounts number colums of every row in the incl_accounts table
	// for the Accounts Inquiry/Maintenance selection table.
	//
	public ArrayList<String> GetStopAccountList(Connection dbConn)
			throws Exception {
		moduleName = "> InclStopUtil.GetStopAccountList : ";
		String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> stop_list = new ArrayList<String>();
		stop_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT STOPAY_ACCOUNT_NUM from incl_stop_pay ORDER by STOPAY_ACCOUNT_NUM");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp = result.getString("STOPAY_ACCOUNT_NUM");
			stop_list.add(temp);
		}
		statement.close();
		result.close();
		return (stop_list);
	}
	//
	public ArrayList<String> GetStopLogAccountList(Connection dbConn)
			throws Exception {
		moduleName = "> InclStopUtil.GetStopLogAccountList : ";
		String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> stop_list = new ArrayList<String>();
		stop_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT SPLOG_ACCOUNT_NUM from incl_stop_pay_log ORDER by SPLOG_ACCOUNT_NUM");
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp = result.getString("SPLOG_ACCOUNT_NUM");
			stop_list.add(temp);
		}
		statement.close();
		result.close();
		return (stop_list);
	}
	//
	public ArrayList<String> GetStopLogDateList(Connection dbConn)
			throws Exception {
		moduleName = "> InclStopUtil.GetStopLogDateList : ";
		String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> sdate_list = new ArrayList<String>();
		sdate_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT SUBSTR(to_char(SPLOG_LAST_MODIFIED,'yyyymmdd hh24:mi:ss'),1,8) as SLM1, ");
		sql.append("SUBSTR(SPLOG_LAST_MODIFIED,1,9) as SLM from incl_stop_pay_log ORDER by SLM1 DESC");
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp = result.getString("SLM");
			sdate_list.add(temp);
		}
		statement.close();
		result.close();
		return (sdate_list);
	}
	//
	public ArrayList<String> GetStopLogUserList(Connection dbConn) throws Exception {
		moduleName = "> InclStopUtil.GetStopLogUserList : ";
		String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> suser_list = new ArrayList<String>();
		suser_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT SPLOG_MOD_USER from incl_stop_pay_log ORDER by SPLOG_MOD_USER");
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp = result.getString("SPLOG_MOD_USER");
			suser_list.add(temp);
		}
		statement.close();
		result.close();
		return (suser_list);
	}
	//
	public boolean StoppayExists(Connection dbConn, 
			String acct_num, String check_num, StoppayDetail stopDetail)
	// ActionErrors errors)
			throws Exception {
		moduleName = "> InclStopUtil.StoppayExists : ";
		// Called by the Add module with an account number
		// --------------------------------------------------
		if (acct_num.length() == 0) {
			stopDetail.setErrorVec("Account", "error.field.required");
			PrintLog("Account Number required  " + acct_num);
			return (true);
		}
		if (check_num.length() == 0) {
			stopDetail.setErrorVec("Check Number", "error.field.required");
			PrintLog("Check Number Required " + check_num);
			return (true);
		}
		param = "WHERE STOPAY_ACCOUNT_NUM='" + acct_num;
		param += "' AND STOPAY_CHECK_NUM='" + check_num + "'";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT STOPAY_ACCOUNT_NUM from incl_stop_pay ");
		sql.append(param);
		// PrintLog("Stoppay Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("STOPAY_ACCOUNT_NUM");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	// Called for Stoppayment Marching
	//
	public int GetStoppayRows(Connection dbConn,
	// ArrayList<String> stop_plist,
			StoppaySelector stopSelector, String acct_num, String check_num)
			throws Exception {
		moduleName = "> InclStopUtil.GetStoppayRows : ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param = "WHERE STOPAY_ACCOUNT_NUM='" + acct_num + "'";
		param += " AND STOPAY_CHECK_NUM='" + check_num + "'";
		// row_count = GetStoppayRows (dbConn, stop_plist, stopSelector);
		row_count = GetStoppayRows(dbConn, stopSelector);
		return (row_count);
	}
	//
	// Called for Modify and possibly update
	//
	public int GetStoppayRows(Connection dbConn,
			// ArrayList<String> stop_plist,
			StoppaySelector stopSelector, String acct_num, String check_num, String lock) throws Exception {
		moduleName = "> InclStopUtil.GetStoppayRows : ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param	= lock;
		param	= "WHERE STOPAY_ACCOUNT_NUM='" + acct_num + "'";
		param	+= " AND STOPAY_CHECK_NUM='" + check_num + "'";
		String accessFlag = stopSelector.getAccessFlag();
		String dbUsed = stopSelector.getDbUsed();
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
		row_count = GetStoppayRows(dbConn, stopSelector);
		return (row_count);
	}
	//
	public int GetStoppayRows(Connection dbConn,
	// ArrayList<String> stop_plist,
			StoppaySelector stopSelector) throws Exception {
		moduleName = "> InclStopUtil.GetStoppayRows : ";
		// DecimalFormat df = new DecimalFormat("###,###.00");
		String dbUsed = stopSelector.getDbUsed();
		String appl_date = stopSelector.getAppl_date();
		// PrintLog("DBUSED "+dbUsed+" ApplDate: "+appl_date);
		// ArrayList acct_rows = new ArrayList<String>();
		if (param.equals(""))
			// GetParams (stopSelector);
			param = stopSelector.GetParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT STOPAY_ACCOUNT_NUM, STOPAY_CHECK_NUM, ");
		sql.append("STOPAY_CURRENCY, STOPAY_CHECK_AMOUNT, ");
		sql.append("STOPAY_SWIFT_REF, ");
		sql.append("STOPAY_ISSUE_DATE, ");
		sql.append("STOPAY_PAYEE, ");
		sql.append("STOPAY_PAYEE_ADDR1, ");
		sql.append("STOPAY_PAYEE_ADDR2, ");
		sql.append("STOPAY_PAYEE_ADDR3, ");
		sql.append("STOPAY_PROC_DATE, ");
		sql.append("STOPAY_VALUE_DATE, STOPAY_EXPIRY_DATE, ");
		sql.append("STOPAY_STATUS, STOPAY_LAST_MODIFIED, ");
		sql.append("STOPAY_MOD_USER, STOPAY_MOD_FUNC ");
		sql.append("FROM incl_stop_pay ");
		sql.append(param);

		// PrintLog("SQL: "+dbUsed+" <> "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			// PrintLog("Getting Rows");
			StoppayDetail stop_detail = new StoppayDetail();
			// Here add the fields tp the StoppayDetail bean
			stop_detail.setDbUsed(dbUsed);
			stop_detail.setAppl_date(appl_date);
			// PrintLog("get col 0");
			stop_detail.setStopay_account_num(result.getString("STOPAY_ACCOUNT_NUM").trim());
			stop_detail.setStopay_check_num(result.getString("STOPAY_CHECK_NUM").trim());
			stop_detail.setStopay_currency(result.getString("STOPAY_CURRENCY").trim());
			stop_detail.setStopay_check_amount(result.getString("STOPAY_CHECK_AMOUNT"));
			stop_detail.setStopay_swift_ref(result.getString("STOPAY_SWIFT_REF"));
			stop_detail.setStopay_issue_date(result.getString("STOPAY_ISSUE_DATE").trim());
			stop_detail.setStopay_payee(result.getString("STOPAY_PAYEE"));
			stop_detail.setStopay_payee_addr1(result.getString("STOPAY_PAYEE_ADDR1"));
			stop_detail.setStopay_payee_addr2(result.getString("STOPAY_PAYEE_ADDR2"));
			stop_detail.setStopay_payee_addr3(result.getString("STOPAY_PAYEE_ADDR3"));
			stop_detail.setStopay_proc_date(result.getString("STOPAY_PROC_DATE"));
			stop_detail.setStopay_value_date(result.getString("STOPAY_VALUE_DATE").trim());
			stop_detail.setStopay_expiry_date(result.getString("STOPAY_EXPIRY_DATE"));
			stop_detail.setStopay_status(result.getString("STOPAY_STATUS"));
			stop_detail.setStopay_last_modified(result.getString("STOPAY_LAST_MODIFIED"));
			stop_detail.setStopay_mod_user(result.getString("STOPAY_MOD_USER").trim());
			stop_detail.setStopay_mod_func(result.getString("STOPAY_MOD_FUNC").trim());
			stop_detail.setStopay_modparam();
			stopBeans.add(stop_detail);
			row_count++;
		}
		stopSelector.setStoppayrows(stopBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetStoppayLogRows(Connection dbConn,
	// ArrayList<String> stop_plist,
			StoppaySelector stopSelector) throws Exception {
		moduleName = "> InclStopUtil.GetStoppayLogRows : ";
		String dbUsed = stopSelector.getDbUsed();
		// ArrayList<String> acct_rows = new ArrayList();
		if (param.equals(""))
			// GetLogParams (stopSelector);
			param = stopSelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT SPLOG_ACCOUNT_NUM, SPLOG_CHECK_NUM, ");
		sql.append("SPLOG_CURRENCY, SPLOG_CHECK_AMOUNT, ");
		sql.append("SPLOG_SWIFT_REF, ");
		sql.append("SPLOG_ISSUE_DATE, ");
		sql.append("SPLOG_PAYEE, ");
		sql.append("SPLOG_PAYEE_ADDR1, ");
		sql.append("SPLOG_PAYEE_ADDR2, ");
		sql.append("SPLOG_PAYEE_ADDR3, ");
		sql.append("SPLOG_PROC_DATE, ");
		sql.append("SPLOG_VALUE_DATE, ");
		sql.append("SPLOG_EXPIRY_DATE, SPLOG_STATUS, ");
		sql.append("SPLOG_LAST_MODIFIED, ");
		sql.append("SPLOG_MOD_USER, SPLOG_MOD_FUNC ");
		sql.append("FROM incl_stop_pay_log ");
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
			StoppayDetail stop_detail = new StoppayDetail();
			// Here add the fields tp the StoppayDetail bean
			stop_detail.setDbUsed(dbUsed);
			stop_detail.setStopay_account_num(result.getString("SPLOG_ACCOUNT_NUM"));
			stop_detail.setStopay_check_num(result.getString("SPLOG_CHECK_NUM"));
			stop_detail.setStopay_currency(result.getString("SPLOG_CURRENCY"));
			stop_detail.setStopay_check_amount(result.getString("SPLOG_CHECK_AMOUNT"));
			stop_detail.setStopay_swift_ref(result.getString("SPLOG_SWIFT_REF"));
			stop_detail.setStopay_issue_date(result.getString("SPLOG_ISSUE_DATE"));
			stop_detail.setStopay_payee(result.getString("SPLOG_PAYEE"));
			stop_detail.setStopay_payee_addr1(result.getString("SPLOG_PAYEE_ADDR1"));
			stop_detail.setStopay_payee_addr2(result.getString("SPLOG_PAYEE_ADDR2"));
			stop_detail.setStopay_payee_addr3(result.getString("SPLOG_PAYEE_ADDR3"));
			stop_detail.setStopay_proc_date(result.getString("SPLOG_PROC_DATE"));
			stop_detail.setStopay_value_date(result.getString("SPLOG_VALUE_DATE"));
			stop_detail.setStopay_expiry_date(result.getString("SPLOG_EXPIRY_DATE"));
			stop_detail.setStopay_status(result.getString("SPLOG_STATUS"));
			stop_detail.setStopay_last_modified(result.getString("SPLOG_LAST_MODIFIED"));
			stop_detail.setStopay_mod_user(result.getString("SPLOG_MOD_USER"));
			stop_detail.setStopay_mod_func(result.getString("SPLOG_MOD_FUNC"));
			stop_detail.setStopay_modparam();
			stopBeans.add(stop_detail);
			row_count++;
		}
		stopSelector.setStoppayrows(stopBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	public int InsertUpdateStop(Connection dbConn, StoppayDetail stopDetail,
	// ActionErrors errors,
			String user_name, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "> InclStopUtil.InsertUpdateStop: ";
		String dbUsed = stopDetail.getDbUsed();
		// PrintLog("In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		String mod_func = "";
		//
		stopDetail.clearNulls();
		String stopay_account_num = stopDetail.getStopay_account_num().trim();
		String stopay_check_num;
		try {
			stopay_check_num = Integer.parseInt(stopDetail.getStopay_check_num()) + "";
		} catch (Exception e) {
			stopay_check_num = stopDetail.getStopay_check_num();
		}
		// PrintLog("Stopay Insert/Update check num --->"+
		// stopDetail.getStopay_check_num()+"<");
		// PrintLog("Stopay Insert/Update check num ---> "+stopay_check_num);
		//
		String stopay_value_date = stopDetail.getStopay_value_date();
		// temp = stopDetail.getStopay_check_amount();
		// String stopay_check_amount = gUtil.strip_commas(temp);
		String stopay_check_amount	= stopDetail.getStopay_check_amount();
		String stopay_currency		= stopDetail.getStopay_currency();
		String stopay_swift_ref		= stopDetail.getStopay_swift_ref();
		String stopay_issue_date	= stopDetail.getStopay_issue_date();
		String stopay_payee			= stopDetail.getStopay_payee();
		stopay_payee				= stopay_payee.replaceAll("'", "''");
		String stopay_payee_addr1	= stopDetail.getStopay_payee_addr1();
		stopay_payee_addr1			= stopay_payee_addr1.replaceAll("'", "''");
		String stopay_payee_addr2	= stopDetail.getStopay_payee_addr2();
		stopay_payee_addr2			= stopay_payee_addr2.replaceAll("'", "''");
		String stopay_payee_addr3	= stopDetail.getStopay_payee_addr3();
		stopay_payee_addr3			= stopay_payee_addr3.replaceAll("'", "''");
		String stopay_proc_date		= stopDetail.getStopay_proc_date();
		String stopay_expiry_date	= stopDetail.getStopay_expiry_date();
		String stopay_status		= stopDetail.getStopay_status();
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			mod_func = "Add Stop";
			sql.setLength(0);
			sql.append("INSERT INTO incl_stop_pay VALUES (");
			sql.append("'" + stopay_account_num + "', ");
			sql.append("'" + stopay_check_num + "', ");
			sql.append("'" + stopay_currency + "', ");
			sql.append("'" + stopay_check_amount + "', ");
			sql.append("'" + stopay_swift_ref + "', ");
			sql.append("'" + stopay_issue_date + "', ");
			sql.append("'" + stopay_payee + "', ");
			sql.append("'" + stopay_payee_addr1 + "', ");
			sql.append("'" + stopay_payee_addr2 + "', ");
			sql.append("'" + stopay_payee_addr3 + "', ");
			sql.append("'" + stopay_proc_date + "', ");
			sql.append("'" + stopay_value_date + "', ");
			sql.append("'" + stopay_expiry_date + "', ");
			sql.append("'" + stopay_status + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL,");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate,");
			}
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog("Stoppay Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Stop pay ->" + e.toString());
				PrintLog("Stoppay Insert SQL ---> " + sql);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling back 1: " + ex);
				}
				statement.close();
				// result.close();
				return (0);
			}
		} else {
			mod_func = "Modify Stop";
			sql.setLength(0);
			sql.append("UPDATE incl_stop_pay SET ");
			sql.append("STOPAY_CURRENCY='" + stopay_currency + "', ");
			sql.append("STOPAY_CHECK_AMOUNT='" + stopay_check_amount + "', ");
			sql.append("STOPAY_SWIFT_REF='" + stopay_swift_ref + "', ");
			sql.append("STOPAY_ISSUE_DATE='" + stopay_issue_date + "', ");
			sql.append("STOPAY_PAYEE='" + stopay_payee + "', ");
			sql.append("STOPAY_PAYEE_ADDR1='" + stopay_payee_addr1 + "', ");
			sql.append("STOPAY_PAYEE_ADDR2='" + stopay_payee_addr2 + "', ");
			sql.append("STOPAY_PAYEE_ADDR3='" + stopay_payee_addr3 + "', ");
			sql.append("STOPAY_PROC_DATE='" + stopay_proc_date + "', ");
			sql.append("STOPAY_VALUE_DATE='" + stopay_value_date + "', ");
			sql.append("STOPAY_EXPIRY_DATE='" + stopay_expiry_date + "', ");
			sql.append("STOPAY_STATUS='" + stopay_status + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("STOPAY_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("STOPAY_LAST_MODIFIED=sysdate, ");
			}
			sql.append("STOPAY_MOD_USER='" + user_name + "', ");
			sql.append("STOPAY_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE STOPAY_ACCOUNT_NUM='" + stopay_account_num + "' ");
			sql.append("AND STOPAY_CHECK_NUM='" + stopay_check_num + "'");
			// PrintLog("Stop Pay Update SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating -->" + e.toString());
				PrintLog("Stop Pay Update SQL ---> " + sql);

				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling back 2: " + ex);
				}
				statement.close();
				// result.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO incl_stop_pay_log VALUES (");
		sql.append("'" + stopay_account_num + "', ");
		sql.append("'" + stopay_check_num + "', ");
		sql.append("'" + stopay_currency + "', ");
		sql.append("'" + stopay_check_amount + "', ");
		sql.append("'" + stopay_swift_ref + "', ");
		sql.append("'" + stopay_issue_date + "', ");
		sql.append("'" + stopay_payee + "', ");
		sql.append("'" + stopay_payee_addr1 + "', ");
		sql.append("'" + stopay_payee_addr2 + "', ");
		sql.append("'" + stopay_payee_addr3 + "', ");
		sql.append("'" + stopay_proc_date + "', ");
		sql.append("'" + stopay_value_date + "', ");
		sql.append("'" + stopay_expiry_date + "', ");
		sql.append("'" + stopay_status + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate,");
		}
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// /PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);

			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling back 3: " + ex);
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
	public int DeleteStoppay(Connection dbConn, StoppayDetail stopDetail,
			String user_name) throws Exception {
		moduleName			= "> DeleteStoppay: ";
		StringBuffer sql	= new StringBuffer();
		Statement statement = null;
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		String stopay_account_num	= stopDetail.getStopay_account_num().trim();
		String stopay_check_num		= Integer.parseInt(stopDetail.getStopay_check_num()) + "";
		dbConn.setAutoCommit(false);
		sql.append(user_name);
		sql.setLength(0);
		sql.append("INSERT INTO incl_stop_pay_log ");
		sql.append("SELECT STOPAY_ACCOUNT_NUM, STOPAY_CHECK_NUM, ");
		sql.append("STOPAY_CURRENCY, ");
		sql.append("STOPAY_CHECK_AMOUNT, ");
		sql.append("STOPAY_SWIFT_REF, ");
		sql.append("STOPAY_ISSUE_DATE, ");
		sql.append("STOPAY_PAYEE, ");
		sql.append("STOPAY_PAYEE_ADDR1, ");
		sql.append("STOPAY_PAYEE_ADDR2, ");
		sql.append("STOPAY_PAYEE_ADDR3, ");
		sql.append("STOPAY_PROC_DATE, ");
		sql.append("STOPAY_VALUE_DATE, STOPAY_EXPIRY_DATE, ");
		sql.append("STOPAY_STATUS, STOPAY_LAST_MODIFIED, ");
		sql.append("STOPAY_MOD_USER, STOPAY_MOD_FUNC ");
		sql.append("FROM incl_stop_pay ");
		sql.append("WHERE STOPAY_ACCOUNT_NUM='" + stopay_account_num + "' ");
		sql.append("AND STOPAY_CHECK_NUM='" + stopay_check_num + "'");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling back 3: " + ex);
			}
			statement.close();
			return (0);
		}
		sql.setLength(0);
		sql.append("DELETE from incl_stop_pay ");
		sql.append("WHERE STOPAY_ACCOUNT_NUM='" + stopay_account_num + "' ");
		sql.append("AND STOPAY_CHECK_NUM='" + stopay_check_num + "'");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error Deleting Stoppay ->" + e.toString());
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling Back Stoppay Delete: " + ex);
			}
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
	//
	public int ExtractStoppayRows(Connection dbConn,
			StoppaySelector stopSelector) throws Exception {
		//
		moduleName = "> InclStopUtil.ExtractStoppayRows: ";
		SysadControlUtil ctlUtil = new SysadControlUtil();
		ControlSelector ctlSelector = new ControlSelector();
		ControlDetail ctlDetail = new ControlDetail();
		int rowCount = stopSelector.getRowCount();
		StoppayDetail stopDetail[] = new StoppayDetail[rowCount];
		StoppayDetail sD = new StoppayDetail();
		String extractFileName = "";
		String fileTime = "";
		DateFormat newFmt2 = null;
		String outputPath = "";
		String outputStoppay = "";
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
		/*
		 * //try { // int ctlRow = ctlUtil.GetControlRow (dbConn, ctlDetail);
		 * //} catch (Throwable t) { //
		 * PrintLog("Error Getting Control ->"+t.toString()); //} //outputPath =
		 * ctlDetail.getInterface_f8();
		 */
		//
		newFmt2 = new SimpleDateFormat("yyyyMMddHHmmss");
		fileTime = newFmt2.format(java.util.Calendar.getInstance().getTime());
		extractFileName = outputPath + "ExtractStoppay_" + fileTime;
		// PrintLog("Extract File Name -> "+extractFileName);
		outExtract = new FileOutputStream(extractFileName + "_temp");
		psExtract = new PrintStream(outExtract);
		//
		stopDetail = stopSelector.getStoppayrows();
		outputStoppay = ("Account Number" + "\t" +
						 "Check Number" + "\t" +
						 "Amount" + "\t" + 
						 "Swift Refernce" + "\t" + 
						 "Issue Date" + "\t" +
						 "Payee" + "\t" + 
						 "Payee Address Line 1" + "\t" +
						 "Payee Address Line 2" + "\t" + 
						 "Payee Address Line 3" + "\t" +
						 "Value Date" + "\t" + 
						 "Status");
		newText = outputStoppay.getBytes();
		outExtract.write(newText, 0, outputStoppay.length());
		psExtract.println();
		outputStoppay = "";
		for (int idx = 0; idx <= stopDetail.length - 1; idx++) {
			sD = stopDetail[idx];
			// Here get the fields from the StoppayDetail bean
			String stopay_account_num = sD.getStopay_account_num();
			String stopay_check_num = sD.getStopay_check_num();
			// String stopay_currency = sD.getStopay_currency();
			String stopay_check_amount = sD.getStopay_check_amount();
			String stopay_swift_ref = sD.getStopay_swift_ref();
			String stopay_issue_date = sD.getStopay_issue_date();
			if (stopay_issue_date.equals(" ") || stopay_issue_date.equals("")) {
				 stopay_issue_date	= "        ";
			}
			String stopay_payee = sD.getStopay_payee();
			String stopay_payee_addr1 = sD.getStopay_payee_addr1();
			String stopay_payee_addr2 = sD.getStopay_payee_addr2();
			String stopay_payee_addr3 = sD.getStopay_payee_addr3();
			String stopay_value_date = sD.getStopay_value_date();
			if (stopay_value_date.equals(" ") || stopay_value_date.equals("")) {
				 stopay_value_date	= "        ";
			}
			String stopay_status = sD.getStopay_status();
			outputStoppay = (stopay_account_num + "\t" +
							 stopay_check_num + "\t" +
							 stopay_check_amount + "\t" + stopay_swift_ref + "\t" +
							 stopay_issue_date.substring(4, 6) + "/" +
							 stopay_issue_date.substring(6, 8)+ "/" +
							 stopay_issue_date.substring(0, 4)+ "\t" +
							 stopay_payee + "\t" + 
							 stopay_payee_addr1 + "\t" +
							 stopay_payee_addr2 + "\t" + 
							 stopay_payee_addr3 + "\t" +
							 stopay_value_date.substring(4, 6) + "/" +
							 stopay_value_date.substring(6, 8) + "/" +
							 stopay_value_date.substring(0, 4) + "\t" +
			// stopay_value_date + "\t" +
			stopay_status);
			newText = outputStoppay.getBytes();
			outExtract.write(newText, 0, outputStoppay.length());
			psExtract.println();
			outputStoppay = "";
		}
		outExtract.close();
		PrintLog("Will Rename file " + extractFileName + "_temp" + " to "
				+ extractFileName + ".xls");
		File extractFile = new File(extractFileName + "_temp");
		extractFile.renameTo(new File(extractFileName + ".xls"));
		return (row_count);
	}
}
