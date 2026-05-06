/*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */

package com.webfiche.checkpoint.sysadmin.service;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.*;

/**
 * <strong>InclStopUtil</strong> is a utility class to provide methods to access
 * .and manipilate the incl_stop_pay database table.
 *
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
@Service
public class SysadCurrencyUtil {
	ArrayList<String> paramArray;
	ArrayList<CurrencyDetail> currencyBeans = new ArrayList<CurrencyDetail>();
	private String className = "> SysadCurrencyUtil.";
	private String moduleName = "";
	private String param = "";
	int rowCount = 0;

	//
	public SysadCurrencyUtil() {
	}

	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
	}

	//
	public void GetCurrencyList(Connection dbConn, CurrencySelector currSelector)
			throws Exception {
		moduleName = "GetCurrencyList: ";
		String tempCurrency = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		currSelector.clearCurrList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT CURR_CODE from currency"
				+ " ORDER by CURR_CODE");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			tempCurrency = result.getString("CURR_CODE");
			currSelector.setCurrList(tempCurrency);
		}
		statement.close();
		result.close();
	}

	//
	public void GetLogCurrencyList(Connection dbConn,
			CurrencySelector currSelector) throws Exception {
		moduleName = "GetCurrencyList: ";
		String tempCurrency = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		currSelector.clearCurrList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT CURRLOGCODE from currency_log"
				+ " ORDER by CURRLOGCODE");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			tempCurrency = result.getString("CURRLOGCODE");
			currSelector.setCurrList(tempCurrency);
		}
		statement.close();
		result.close();
	}

	//
	public boolean CurrencyExists(Connection dbConn, String currCode)
			throws Exception {
		//
		moduleName = "CurrencyExists: ";
		StringBuffer sql = new StringBuffer();
		//
		sql.append("SELECT CURR_CODE from currency ");
		sql.append("WHERE CURR_CODE='" + currCode + "' ");
		// PrintLog("Currency Exist SQL "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("CURR_CODE");
			statement.close();
			result.close();
			PrintLog("CURRENCY Exists returning true ");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}

	//
	public int GetCurrencyRows(Connection dbConn,
			CurrencySelector currencySelector, String currCode)
	// String holi_curr)
			throws Exception {
		// Called by the modify module with an account number
		// --------------------------------------------------
		moduleName = "GetCurrencyRows: ";
		param = "WHERE CURR_CODE='" + currCode + "'";
		rowCount = GetCurrencyRows(dbConn, currencySelector);
		return (rowCount);
	}

	//
	public int GetCurrencyRows(Connection dbConn,
			CurrencySelector currencySelector) throws Exception {
		moduleName = "GetCurrencyRows: ";
		if (param.equals(""))
			param = currencySelector.GetParams();
		StringBuffer sql = new StringBuffer();
		rowCount = 0;
		sql.append("SELECT CURR_CODE, CURR_COUNTRY, CURR_NAME, ");
		sql.append("CURR_NUM, CURR_MINOR_UNIT, CURR_LAST_MODIFIED, ");
		sql.append("CURR_MOD_USER, CURR_MOD_FUNC ");
		sql.append("FROM currency ");
		sql.append(param);
		// PrintLog(" SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			CurrencyDetail currencyDetail = new CurrencyDetail();
			currencyDetail.setCurrCode(result.getString("CURR_CODE"));
			currencyDetail.setCurrCountry(result.getString("CURR_COUNTRY"));
			currencyDetail.setCurrName(result.getString("CURR_NAME"));
			currencyDetail.setCurrNumber(result.getString("CURR_NUM"));
			currencyDetail
					.setCurrMinorUnit(result.getString("CURR_MINOR_UNIT"));
			currencyDetail.setCurrModTime(result
					.getString("CURR_LAST_MODIFIED"));
			currencyDetail.setCurrModUser(result.getString("CURR_MOD_USER"));
			currencyDetail.setCurrModFunc(result.getString("CURR_MOD_FUNC"));
			currencyDetail.setCurrModParam();
			currencyBeans.add(currencyDetail);
			rowCount++;
		}
		currencySelector.setCurrRows(currencyBeans);
		statement.close();
		result.close();
		return (rowCount);
	}

	//
	public int GetCurrencyLogRows(Connection dbConn,
			CurrencySelector currencySelector) throws Exception {
		moduleName = "GetCurrencyRows: ";
		if (param.equals(""))
			param = currencySelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		rowCount = 0;
		sql.append("SELECT CURR_LOG_CODE, CURR_LOG_COUNTRY, CURR_LOG_NAME, ");
		sql.append("CURR_LOG_NUMBER, CURR_LOG_MINOR_UNIT, CURR_LOG_LAST_MODIFIED, ");
		sql.append("CURR_LOG_MOD_USER, CURR_LOG_MOD_FUNC ");
		sql.append("FROM currency_log ");
		sql.append(param);
		// PrintLog(" SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			CurrencyDetail currencyDetail = new CurrencyDetail();
			currencyDetail.setCurrCode(result.getString("CURR_LOG_CODE"));
			currencyDetail.setCurrCountry(result.getString("CURR_LOG_COUNTRY"));
			currencyDetail.setCurrName(result.getString("CURR_LOG_NAME"));
			currencyDetail.setCurrNumber(result.getString("CURR_LOG_NUMBER"));
			currencyDetail.setCurrMinorUnit(result
					.getString("CURR_LOG_MINOR_UNIT"));
			currencyDetail.setCurrModTime(result
					.getString("CURR_LOG_LAST_MODIFIED"));
			currencyDetail
					.setCurrModUser(result.getString("CURR_LOG_MOD_USER"));
			currencyDetail
					.setCurrModFunc(result.getString("CURR_LOG_MOD_FUNC"));
			currencyDetail.setCurrModParam();
			currencyBeans.add(currencyDetail);
			rowCount++;
		}
		currencySelector.setCurrRows(currencyBeans);
		statement.close();
		result.close();
		return (rowCount);
	}

	//
	public int InsertUpdateCurrency(Connection dbConn,
			CurrencyDetail currencyDetail, String dbUsed, String user_name,
			int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "InsertUpdateCurrency: ";
		PrintLog(": In Insert/Update Currency Data");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "";
		//
		String currCode = currencyDetail.getCurrCode();
		String currCountry = currencyDetail.getCurrCountry();
		String currName = currencyDetail.getCurrName();
		String currNumber = currencyDetail.getCurrNumber();
		String currMinorUnit = currencyDetail.getCurrMinorUnit();
		if (ins_or_upd == 1) {
			mod_func = "Add Currency";
			sql.setLength(0);
			sql.append("INSERT INTO currency VALUES (");
			sql.append("'" + currCode + "', ");
			sql.append("'" + currCountry + "', ");
			sql.append("'" + currName + "', ");
			sql.append("'" + currNumber + "', ");
			sql.append("'" + currMinorUnit + "', ");
			// sql.append("NULL,");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			// sql.append("sysdate,");
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog("Currency Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog(": Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Adding Currency ->" + e.toString());
				PrintLog("Currency Insert SQL ---> " + sql);
				e.printStackTrace(System.out);
			}
		} else {
			mod_func = "Modify Currency";
			sql.setLength(0);
			sql.append("UPDATE currency SET ");
			sql.append("CURR_NODE='" + currCode + "', ");
			sql.append("CURR_COUNTRY='" + currCountry + "', ");
			sql.append("CURR_NAME='" + currName + "', ");
			sql.append("CURR_NUM='" + currNumber + "', ");
			sql.append("CURR_MINOR_UNIT='" + currMinorUnit + "', ");
			// sql.append("CURR_LAST_MODIFIED=NULL, ");
			if (dbUsed.equals("MySQL")) {
				sql.append("CURR_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("CURR_LAST_MODIFIED=sysdate, ");
			}
			sql.append("CURR_MOD_USER='" + user_name + "', ");
			sql.append("CURR_MOD_FUNC='" + mod_func + "' ");
			sql.append("CURR CODE='" + currCode + "' ");
			// PrintLog("Currency Update SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog(": Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Currency ->" + e.toString());
				PrintLog("Currency Update SQL ---> " + sql);
				e.printStackTrace(System.out);
			}
		}
		statement.close();
		sql.setLength(0);
		sql.append("INSERT INTO currency_log VALUES (");
		sql.append("'" + currCode + "', ");
		sql.append("'" + currCountry + "', ");
		sql.append("'" + currName + "', ");
		sql.append("'" + currNumber + "', ");
		sql.append("'" + currMinorUnit + "', ");
		// sql.append("NULL,");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("sysdate,");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		// PrintLog("Currency Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Currency Log ->" + e.toString());
			PrintLog("Currency Log Insert SQL ---> " + sql);
			e.printStackTrace(System.out);
		}
		statement.close();
		return (1);
	}

	public int DeleteCurrency(Connection dbConn, CurrencyDetail currencyDetail,
			String dbUsed, String user_name) // ,
			// int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "DeleteCurrency: ";
		// PrintLog("In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "Delete Currency";
		//
		String currCode = currencyDetail.getCurrCode();

		sql.setLength(0);
		sql.append("INSERT INTO currency_log ");
		sql.append("SELECT CURR_CODE, CURR_COUNTRY, CURR_NAME, ");
		sql.append("CURR_NUM, CURR_MINOR_UNIT, ");
		// sql.append("NULL, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("sysdate, ");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM currency ");
		sql.append("WHERE CURR_CODE='" + currCode + "' ");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Currency Log ->" + e.toString());
			PrintLog(": Currency Log Insert SQL ---> " + sql);
			e.printStackTrace(System.out);
		}
		statement.close();
		sql.setLength(0);
		sql.append("DELETE FROM currency ");
		sql.append("WHERE CURR_CODE='" + currCode + "' ");
		// PrintLog("Currency Delete SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting Currency " + e.toString());
			PrintLog("Currency Delete SQL ---> " + sql);
			e.printStackTrace(System.out);
		}
		statement.close();
		return (1);
	}
}
