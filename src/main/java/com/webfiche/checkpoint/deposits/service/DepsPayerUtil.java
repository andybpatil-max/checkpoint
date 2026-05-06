package com.webfiche.checkpoint.deposits.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;

//import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.deposits.beans.*;

@Service
public class DepsPayerUtil {
	private String moduleName;
	private String className	= "> DepsPayerUtil.";
	private String param = "";
	private ArrayList<PayerDetail> payerBeans	= new ArrayList<PayerDetail>();
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public DepsPayerUtil() {
	}
	//
	public TreeMap<String, String> GetPayerNamesList(Connection dbConn, PayerSelector payerSelector)
			throws Exception {
		moduleName			= "GetPayerNamesList: ";
		// PrintLog("DBUSED "+dbUsed);
		String payerAba		= "";
		String payerAcct	= "";
		String payerName	= "";
		//String payerSource	= payerSelector.getPayerSource();
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> payerList	= new TreeMap<String, String>();
		payerList.clear();
		// acctSelector.clearAllAcctList();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT PAYERABA, PAYERACCT, PAYERNAME from payers ");
		sql.append("ORDER by PAYERNAME");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			payerAba	= result.getString("PAYERABA");
			payerAcct	= result.getString("PAYERACCT");
			payerName	= result.getString("PAYERNAME");
			//payerCountry= result.getString("PAYERCOUNTRY");
			payerList.put(payerAba+payerAcct, payerName);
			//PrintLog("Key: "+payerAba+payerAcct+" Value: "+payerName);
		}
		statement.close();
		result.close();
		return (payerList);
	}
	//
	public TreeMap<String, String> GetPayerCountryList(Connection dbConn, PayerSelector payerSelector)
			throws Exception {
		moduleName			= "GetPayersCountryList: ";
		// PrintLog("DBUSED "+dbUsed);
		String payerAba		= "";
		String payerAcct	= "";
		String payerCountry	= "";
		//String payerSource	= payerSelector.getPayerSource();
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> countryList	= new TreeMap<String, String>();
		countryList.clear();
		// acctSelector.clearAllAcctList();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT PAYERABA, PAYERACCT, PAYERCOUNTRY from payers ");
		sql.append("ORDER by PAYERABA, PAYERACCT, PAYERCOUNTRY");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			payerAba	= result.getString("PAYERABA");
			payerAcct	= result.getString("PAYERACCT");
			payerCountry= result.getString("PAYERCOUNTRY");
			countryList.put(payerAba+payerAcct, payerCountry);
		}
		statement.close();
		result.close();
		return (countryList);
	}
	//
	public TreeMap<String, String> GetPayerList(Connection dbConn, AcctentrySelector acctentrySelector)
			throws Exception {
		moduleName			= "GetPayersList: ";
		// PrintLog("DBUSED "+dbUsed);
		String payerAba		= "";
		//String payerAcct	= "";
		String payerSource	= "";
		//String payerSource	= payerSelector.getPayerSource();
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> payerList	= new TreeMap<String, String>();
		payerList.clear();
		// acctSelector.clearAllAcctList();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT PAYERABA, PAYERSOURCE from payers ");
		//sql.append("WHERE PAYERSOURCE='NEXTDAY' ");
		sql.append("GROUP BY PAYERABA, PAYERSOURCE ");
		sql.append("ORDER BY PAYERABA ");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			payerAba	= result.getString("PAYERABA");
			payerSource	= result.getString("PAYERSOURCE");
			if (payerSource.equals("NEXTDAY")) {
				//
				//PrintLog("ABA: " + payerAba + " payer Source: " + payerSource);
			} else {
				payerSource	= "NORMAL";
			}
			payerList.put(payerAba, payerSource);
			//PrintLog("ABA: " + payerAba + " payer Source: " + payerSource);
		}
		statement.close();
		result.close();
		/*
		Set<String> keys = payerList.keySet();
		for(String key: keys){
			if (payerList.get(key).equals("NEXTDAY")) {
				PrintLog("Value of "+key+" is: "+payerList.get(key));
			}
			if (key.equals("026008044")) {
				PrintLog("Value of "+key+" is: "+payerList.get(key));
			}
		}
		*/
		//PrintLog("Payer List Size: "+payerList.size());
		return (payerList);
	}
	//
	public String GetPayerName(Connection dbConn, String payerAba, String payerAcct)
			throws Exception {
		moduleName			= "GetPayerNamesList: ";
		// PrintLog("DBUSED "+dbUsed);
		//String payerAba		= payerSelector.getPayerAba();
		//String payerAcct	= payerSelector.getPayerAcct();
		String payerName	= " ";
		//String payerSource	= payerSelector.getPayerSource();
		if (dbConn == null)
			PrintLog("dbConn is Null");
		// acctSelector.clearAllAcctList();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT PAYERABA, PAYERACCT, PAYERNAME from payers ");
		sql.append("WHERE PAYERABA='"+payerAba+"' and "+"PAYERACCT='"+payerAcct+"' "+"ORDER by PAYERNAME");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			payerName	= result.getString("PAYERNAME");
			//payerCountry= result.getString("PAYERCOUNTRY");
			//PrintLog("Key: "+payerAba+payerAcct+" Value: "+payerName);
		}
		statement.close();
		result.close();
		return (payerName);
	}
	//
	public String GetPayerCountry (Connection dbConn, String payerAba, String payerAcct)
			throws Exception {
		moduleName			= "GetPayersCountryList: ";
		// PrintLog("DBUSED "+dbUsed);
		String payerCountry	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT PAYERCOUNTRY from payers ");
		sql.append("WHERE PAYERABA='"+payerAba+"' and "+"PAYERACCT='"+payerAcct+"' "+"ORDER by PAYERCOUNTRY");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			payerCountry= result.getString("PAYERCOUNTRY");
		}
		statement.close();
		result.close();
		return (payerCountry);
	}
	//
	public ArrayList<String> GetPayerAbaList(Connection dbConn, PayerSelector payerSelector)
			 throws Exception {
		moduleName = "GetPayerAbaList: ";
		String payerSource	= payerSelector.getPayerSource();
		String dbTable		= payerSelector.getDbTable();
		ArrayList<String> payerAbas	= new ArrayList<String>();
		if (dbConn == null) {
			PrintLog("dbConn NULL");
		}
		//
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT PAYERABA from " + dbTable + " ");
		sql.append("WHERE payersource='"+payerSource+"' ");
		sql.append("ORDER BY PAYERABA");
		//
		//PrintLog("SQL ---> "+sql);
		// 
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(result.getString("PAYERACCT"));
			payerAbas.add(result.getString("PAYERABA"));
		}
		statement.close();
		result.close();
		//PrintLog("Number of ABAs "+payerAbas.size());
		return (payerAbas);
	}
	//
	public ArrayList<String> GetPayerAcctList(Connection dbConn, PayerSelector payerSelector)
			 throws Exception {
		moduleName = "GetPayerAcctList: ";
		String payerSource	= payerSelector.getPayerSource();
		String dbTable		= payerSelector.getDbTable();
		ArrayList<String> payerAccts	= new ArrayList<String>();
		if (dbConn == null) {
			PrintLog("dbConn NULL");
		}
		//
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PAYERACCT from " + dbTable + " ");
		sql.append("WHERE payersource='"+payerSource+"' ");
		sql.append("ORDER BY PAYERACCT");
		//
		//PrintLog("SQL ---> "+sql);
		// 
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(result.getString("PATERACCT"));
			payerAccts.add(result.getString("PAYERACCT"));
		}
		statement.close();
		result.close();
		//PrintLog("retVal.size() "+retVal.size());
		return (payerAccts);
	}
	//
	public boolean PayerAcctExists(Connection dbConn, PayerSelector payerSelector)
			throws Exception {
		moduleName	= "PayerAcctExists: ";
		String payerAba		= payerSelector.getPayerAba();
		String payerAcct	= payerSelector.getPayerAcct();
		String dbTable		= payerSelector.getDbTable();
		boolean acctExists	= false;
		if (dbConn == null) {
			PrintLog("dbConn NULL");
		}
		//
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PAYERACCT from " + dbTable + " ");
		sql.append("WHERE (PAYERABA='" + payerAba + "' AND ");
		sql.append("PAYERACCT='" + payerAcct + "') ");
		//
		//PrintLog("SQL ---> "+sql);
		// 
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(result.getString("PAYERACCT"));
			acctExists	= true;
		}
		statement.close();
		result.close();
		//PrintLog("retVal.size() "+retVal.size());
		return (acctExists);
	}
	//
	public int GetPayerRows(Connection dbConn, PayerSelector payerSelector,
							String payerAcct) throws Exception {
		moduleName							= "GetPayerRows: ";
		int row_count						= 0;
		String dbUsed						= payerSelector.getDbUsed();
		if (dbConn == null) {
			PrintLog(": dbConn is Null");
		}
		//PrintLog("Payer Account: " + depsAcct);
		if (payerAcct.equals("")) {
			if (param.equals("")) {
				param	= payerSelector.GetParams();
			}
		} else {
			param = " WHERE PAYERACCT='" + payerAcct + "' ";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("PAYERSOURCE, ");
		sql.append("PAYERABA, ");
		sql.append("PAYERACCT, ");
		sql.append("PAYERNAME, ");
		sql.append("PAYERCOUNTRY, ");
		sql.append("PAYERBANK, ");
		sql.append("MODTIME, ");
		sql.append("MODUSER, ");
		sql.append("MODFUNC ");
		sql.append("FROM payers ");
		sql.append(param);
		//PrintLog("Get Payer row: " + sql);
		payerSelector.clearPayerRows();
		payerBeans.clear();
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			PayerDetail payerDetail = new PayerDetail();
			payerDetail.setDbUsed(dbUsed);
			payerDetail.setPayersource(result.getString("PAYERSOURCE"));
			payerDetail.setPayeraba(result.getString("PAYERABA"));
			payerDetail.setPayeracct(result.getString("PAYERACCT"));
			payerDetail.setPayername(result.getString("PAYERNAME"));
			payerDetail.setPayercountry(result.getString("PAYERCOUNTRY"));
			payerDetail.setPayerbank(result.getString("PAYERBANK"));
			payerDetail.setModtime(result.getString("MODTIME"));
			payerDetail.setModuser(result.getString("MODUSER"));
			payerDetail.setModfunc(result.getString("MODFUNC"));
			payerBeans.add(payerDetail);
			row_count++;
		}
		payerSelector.setPayerRows(payerBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetPayerLogRows(Connection dbConn, PayerSelector payerSelector,
							String payerAcct) throws Exception {
		moduleName			= "GetPayerLogRows: ";
		int row_count		= 0;
		String dbUsed		= payerSelector.getDbUsed();
		if (dbConn == null) {
			PrintLog(": dbConn is Null");
		}
		//PrintLog("Payer Account: " + depsAcct);
		if (payerAcct.equals("")) {
			if (param.equals("")) {
				param	= payerSelector.GetLogParams();
			}
		} else {
			param = " WHERE PAYERACCT='" + payerAcct + "' ";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("PAYERSOURCE, ");
		sql.append("PAYERABA, ");
		sql.append("PAYERACCT, ");
		sql.append("PAYERNAME, ");
		sql.append("PAYERCOUNTRY, ");
		sql.append("PAYERBANK, ");
		sql.append("MODTIME, ");
		sql.append("MODUSER, ");
		sql.append("MODFUNC ");
		sql.append("FROM payerslog ");
		sql.append(param);
		//PrintLog("Get Payer log rows: " + sql);
		payerSelector.clearPayerRows();
		payerBeans.clear();
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			PayerDetail payerDetail = new PayerDetail();
			payerDetail.setDbUsed(dbUsed);
			payerDetail.setPayersource(result.getString("PAYERSOURCE"));
			payerDetail.setPayeraba(result.getString("PAYERABA"));
			payerDetail.setPayeracct(result.getString("PAYERACCT"));
			payerDetail.setPayername(result.getString("PAYERNAME"));
			payerDetail.setPayercountry(result.getString("PAYERCOUNTRY"));
			payerDetail.setPayerbank(result.getString("PAYERBANK"));
			payerDetail.setModtime(result.getString("MODTIME"));
			payerDetail.setModuser(result.getString("MODUSER"));
			payerDetail.setModfunc(result.getString("MODFUNC"));
			payerBeans.add(payerDetail);
			row_count++;
		}
		payerSelector.setPayerRows(payerBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int AddUpdatePayerAcct(Connection dbConn,
			PayerDetail payerDetail, String ourAba, String user_name, int ins_or_upd)
			throws Exception {
		// 1 for insert or 2 for update
		moduleName	= "AddUpdateDepsAcct: ";
		//
		String payerSource	= "";
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		String modFunc		= "";
		payerDetail.ClearNulls();
		String dbUsed		= payerDetail.getDbUsed();
		String payerAba		= payerDetail.getPayeraba();
		String tempAba		= "";
		tempAba		= Long.parseLong(payerAba)+"";
		payerSource	= payerDetail.getPayersource();
		if ((payerAba.equals(ourAba)) || (tempAba.length() < 6)) {
			payerSource	= "NEXTDAY";
		}
		payerAba			= String.format("%09d", Long.parseLong(payerAba));
		String payerAccount	= payerDetail.getPayeracct();
		String payerName	= payerDetail.getPayername();
		payerName			= payerName.replaceAll("'", "''");
		String payerCountry	= payerDetail.getPayercountry();
		String payerBank	= payerDetail.getPayerbank();
		if (payerName.equals("")) {
			payerName	= " ";
		}
		;
		dbConn.setAutoCommit(false);
		// here we Insert or Update the Check row and insert a log row
		if (ins_or_upd == 1) {
			modFunc	= "AddPayer";
			sql.setLength(0);
			sql.append("INSERT INTO payers VALUES (");
			sql.append("'" + payerSource + "', ");
			sql.append("'" + payerAba + "', ");
			sql.append("'" + payerAccount + "', ");
			sql.append("'" + payerName + "', ");
			sql.append("'" + payerCountry + "', ");
			sql.append("'" + payerBank + "', ");
			if (dbUsed.toUpperCase().equals("MYSQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.toUpperCase().equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + user_name + "', ");
			sql.append("'" + modFunc + "')");
			//PrintLog(": Account Insert SQL ---> "+sql);
			try {
				statement = dbConn.createStatement();
				// PrintLog(": Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog(": Error Inserting Lockbox Payer ->" + e.toString());
				PrintLog(": Lockbox Payer Insert SQL ---> " + sql);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling Insert Lockbox Payer: " + ex);
				}
				statement.close();
				return (0);
			}
		} else {
			modFunc = "ModifyPayer";
			sql.setLength(0);
			sql.append("update payers ");
			sql.append("set PAYERSOURCE='" + payerSource + "', ");
			sql.append("    PAYERABA='" + payerAba + "', ");
			sql.append("    PAYERACCT='" + payerAccount + "', ");
			sql.append("    PAYERNAME='" + payerName + "', ");
			sql.append("    PAYERCOUNTRY='" + payerCountry + "', ");
			sql.append("    PAYERBANK='" + payerBank + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    MODTIME=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    MODTIME=sysdate, ");
			}
			sql.append("    MODUSER='" + user_name + "', ");
			sql.append("    MODFUNC='" + modFunc + "' ");
			sql.append("    WHERE PAYERACCT='" + payerAccount + "' AND ");
			sql.append("    PAYERABA='" + payerAba + "' ");
			//PrintLog(": Payers Update SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog(": Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog(": Error Updating Payers ->" + e.toString());
				PrintLog(": Payers Update SQL ---> " + sql);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Error Rolling Modify Payer: " + ex);
				}
				statement.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		// PrintLog("account_num 2:"+account_num);
		sql.setLength(0);
		sql.append("INSERT INTO payerslog VALUES (");
		sql.append("'" + payerSource + "', ");
		sql.append("'" + payerAba + "', ");
		sql.append("'" + payerAccount + "', ");
		sql.append("'" + payerName + "', ");
		sql.append("'" + payerCountry + "', ");
		sql.append("'" + payerBank + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + user_name + "', ");
		sql.append("'" + modFunc + "')");
		// PrintLog(": Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog(": Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error inserting Lockbox Payers Log ->" + e.toString());
			// PrintLog": Payers Log Insert SQL ---> "+sql);
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling Insert Lockbox Payers Log: " + ex);
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
	public int DeletePayerAcct(Connection dbConn,
			PayerDetail payerDetail, String userId) throws Exception {
		moduleName			= "DeletePayerAcct: ";
		StringBuffer sql	= new StringBuffer();
		String param		= "";
		Statement statement	= null;
		String modFunc		= "Delete";
		//
		String dbUsed		= payerDetail.getDbUsed().trim();
		String payerAcct	= payerDetail.getPayeracct().trim();
		String payerAba		= payerDetail.getPayeraba().trim();
		//
		if (payerAcct.equals("")) {
			return 0;
		} else {
			if (payerAba.equals("")) {
					param	= ("WHERE PAYERACCT='" + payerAcct + "' ");
			} else {
					param	= ("WHERE PAYERACCT='" + payerAcct + "' " +
								" AND PAYERABA='" + payerAba + "' ");
			}
		}
		//
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT INTO payerslog ");
		sql.append("SELECT PAYERSOURCE, ");
		sql.append("PAYERABA, ");
		sql.append("PAYERACCT, ");
		sql.append("PAYERNAME, ");
		sql.append("PAYERCOUNTRY, ");
		sql.append("PAYERBANK, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + modFunc + "' ");
		sql.append("FROM payers ");
		sql.append(param);
		// PrintLog(": Log Insert SQL ---> "+sql);
		//
		try {
			statement	= dbConn.createStatement();
			// PrintLog(": Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Payer Delete sql: "+ sql);
			PrintLog("Error inserting payers Log ->" + e.toString());
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling Back Insert payers Log: " + ex);
			}
			statement.close();
			return (0);
		}
		sql.setLength(0);
		sql.append("DELETE from payers ");
		sql.append(param);
		PrintLog("DELETE payer SQL: " + sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog(": Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error Deleting Payer SQL ->" + sql);
			PrintLog(": Error Deleting Payer Cause ->" + e.toString());
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling back Insert Payer Log: " + ex);
			}
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return 1;
	}
	//
	public ArrayList<String> GetPayerLogAccountList(Connection dbConn) throws Exception {
		moduleName = "GetPayerLogAccountList: ";
		// PrintLog("DBUSED "+dbUsed);
		String acctNum = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> depAcctList = new ArrayList<String>();
		depAcctList.clear();
		// acctSelector.clearDepAcctList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT PAYERACCT from payerslog "
				+ "ORDER by PAYERACCT ");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum = result.getString("PAYERACCT");
			//PrintLog("Log Payer Account Num: " + acctNum);
			depAcctList.add(acctNum);
		}
		statement.close();
		result.close();
		return (depAcctList);
	}
	//
	public ArrayList<String> GetPayerLogDateList(Connection dbConn)
			throws Exception {
		moduleName		= "GetPayerLogDateList: ";
		// PrintLog(" DBUsed "+dbUsed);
		//PrintLog(" In Get Log Date ");
		String temp		= "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> dateList = new ArrayList<String>();
		dateList.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT SUBSTR(to_char(MODTIME,'yyyymmdd hh24:mi:ss'),1,8) as lmd, ");
		sql.append("SUBSTR(MODTIME,1,9) as LD from payerslog ORDER by LMD DESC");
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			temp = result.getString("LD");
			dateList.add(temp);
			//PrintLog("Date "+temp);
		}
		statement.close();
		result.close();
		return (dateList);
	}
	//
	public ArrayList<String> GetPayerLogUserList(Connection dbConn) throws Exception {
		moduleName = "GetPayerLogUserList: ";
		// PrintLog(" DBUsed "+dbUsed);
		//PrintLog(" In Get Log User ");
		String temp = "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> userList = new ArrayList<String>();
		userList.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT MODUSER from payerslog ORDER by MODUSER");
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			temp = result.getString("MODUSER");
			userList.add(temp);
			//PrintLog("User "+temp);
		}
		statement.close();
		result.close();
		return (userList);
	}
}