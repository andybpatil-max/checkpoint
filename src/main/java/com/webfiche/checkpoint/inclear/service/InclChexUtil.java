package com.webfiche.checkpoint.inclear.service;

import java.io.*;
import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.util.*;

@Service
public class InclChexUtil {
	String	moduleName;
	String	calledFrom;
	String	className			= "> InclChexUtil.";
	boolean	modifyRow			= false;
	boolean	retBool				= false;
	String	from_period			= "";
	String	to_period			= "";
	String	chex_from_period	= "";
	String	chex_to_period		= "";
	String	chex_from_check		= "";
	String	chex_to_check		= "";
	ArrayList<ChexDetail>	chexBeans	= new ArrayList<ChexDetail>();
	ArrayList<ChexSummary>	chexSBeans	= new ArrayList<ChexSummary>();
	String	param				= "";
	int		row_count			= 0;
	ArrayList<String>	hist_range	= new ArrayList<String>();
	private TreeMap <String, Long>		highCheckNum	= new TreeMap <String, Long>(); 
	private TreeMap <String, Integer>	monthAverage	= new TreeMap <String, Integer>(); 

	public InclChexUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	// The following Methods are specialised for each of the incl tables.
	// The first is a set of methods for specific purposes;
	// 1. Get the accounts number colums of every row in the incl_accounts table
	// for the Accounts Inquiry/Maintenance selection table.
	//
	public ArrayList<String> GetChexAccountList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetChexAccountList: ";
		// PrintLog("Detail: DBUsed "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> chex_list	= new ArrayList<String>();
		chex_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHEX_ACCOUNT_NUM from " + dbTable +
					" ORDER by CHEX_ACCOUNT_NUM");
		// Setup the SELECT statement.
		// PrintLog(" InclChexUtil: Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(" InclChexUtil: Execute In While");
			temp	= result.getString("CHEX_ACCOUNT_NUM");
			chex_list.add(temp);
		}
		statement.close();
		result.close();
		return (chex_list);
	}
	//
	public TreeMap<String, String> GetRejectReasons (Connection dbConn,
			String dbTable) throws Exception {
		moduleName			= "GetRejectReasons: ";
		// PrintLog("Detail: DBUsed "+dbUsed);
		String productId	= "";
		String rejCode		= "";
		String rejReason	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String>rejReasons	= new TreeMap<String, String>();
		rejReasons.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT PRODUCTID, REJECTCODE, REJECTDESC from " + dbTable +
					" ORDER by PRODUCTID, REJECTCODE");
		// Setup the SELECT statement.
		//PrintLog(" InclChexUtil: Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		//PrintLog(" InclChexUtil: Will Get reject reasons");
		while (result.next()) {
			//PrintLog(" InclChexUtil: Getting reject reasons");
			productId	= result.getString("PRODUCTID");
			rejCode		= result.getString("REJECTCODE");
			rejReason	= result.getString("REJECTDESC");
			rejReasons.put(productId+rejCode, rejReason);
			//PrintLog("RejCode: "+rejCode+" Reason: "+rejReason);
		}
		statement.close();
		result.close();
		return (rejReasons);
	}
	//
	public TreeMap<String, String> GetReturnReasons (Connection dbConn,
			String dbTable) throws Exception {
		moduleName			= "GetRejectReasons: ";
		// PrintLog("Detail: DBUsed "+dbUsed);
		//String productId	= "";
		String retCode		= "";
		String retReason	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String>retReasons	= new TreeMap<String, String>();
		retReasons.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		/*
			PRODUCTID									     NOT NULL VARCHAR2(2)
 			RETURNREASON									     NOT NULL VARCHAR2(2)
 			RETURNDESC		 */
		sql.append("SELECT PRODUCTID, RETURNCODE, RETURNREASON from " + dbTable +
					//" WHERE RETURNREASONACTIVE='Y' ORDER by PRODUCTID, RETURNREASON");
					" WHERE RETURNREASONACTIVE='Y' ORDER by RETURNCODE, RETURNREASON");
		// Setup the SELECT statement.
		//PrintLog(" InclChexUtil: Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		//PrintLog(" InclChexUtil: Will Get reject reasons");
		while (result.next()) {
			//PrintLog(" InclChexUtil: Getting reject reasons");
			//productId	= result.getString("PRODUCTID");
			retCode		= result.getString("RETURNCODE");
			retReason	= result.getString("RETURNREASON");
			//retReasons.put(productId+retCode, retReason);
			retReasons.put(retCode, retReason);
			//PrintLog("RetCode: "+retCode+" Reason: "+retReason);
		}
		statement.close();
		result.close();
		return (retReasons);
	}
	//
	public TreeMap<String, String> GetChexStatus (Connection dbConn,
			String dbTable) throws Exception {
		moduleName			= "GetChexStatus: ";
		// PrintLog("Detail: DBUsed "+dbUsed);
		String productId	= "";
		String statusCode	= "";
		String statusDesc	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String>statusCodes	= new TreeMap<String, String>();
		statusCodes.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT PRODUCTID, STATUSCODE, STATUSDESC from " + dbTable +
					" ORDER by PRODUCTID, STATUSCODE");
		// Setup the SELECT statement.
		//PrintLog(" InclChexUtil: Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		//PrintLog(" InclChexUtil: Will Get Status Codes");
		while (result.next()) {
			//PrintLog(" InclChexUtil: Getting Status");
			productId	= result.getString("PRODUCTID").trim();
			statusCode	= result.getString("STATUSCODE").trim();
			statusDesc	= result.getString("STATUSDESC").trim();
			statusCodes.put(productId+statusCode, statusDesc);
			//PrintLog("Status Code: "+productId+statusCode+" Status: "+statusDesc);
		}
		statement.close();
		result.close();
		return (statusCodes);
	}
	//
	public ArrayList<String> GetChexLogAccountList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetChexLogAccountList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> chex_list = new ArrayList<String>();
		chex_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHLOG_ORIG_ACCOUNT_NUM from " + dbTable +
					" ORDER by CHLOG_ORIG_ACCOUNT_NUM");
		// Setup the SELECT statement.
		// PrintLog(" InclChexUtil: Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(" InclChexUtil: Execute In While");
			temp	= result.getString("CHLOG_ORIG_ACCOUNT_NUM");
			chex_list.add(temp);
		}
		statement.close();
		result.close();
		return (chex_list);
	}
	//
	public ArrayList<String> GetChexLogDateList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetChexLogDateList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> cdate_list	= new ArrayList<String>();
		cdate_list.clear();
		StringBuffer sql				= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT SUBSTR(to_char(CHLOG_LAST_MODIFIED,'yyyymmdd hh24:mi:ss'),1,8) as CLM1, ");
		sql.append("SUBSTR(CHLOG_LAST_MODIFIED,1,9) as CLM from " + dbTable +
					" ORDER by CLM1 DESC");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("CLM");
			cdate_list.add(temp);
		}
		statement.close();
		result.close();
		return (cdate_list);
	}
	//
	public ArrayList<String> GetChexLogUserList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetChexLogUserList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> cuser_list	= new ArrayList<String>();
		cuser_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHLOG_MOD_USER from " + dbTable +
					" ORDER by CHLOG_MOD_USER");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("CHLOG_MOD_USER");
			cuser_list.add(temp);
		}
		statement.close();
		result.close();
		return (cuser_list);
	}
	//
	public ArrayList<String> GetChexHistList(Connection dbConn,
			String dbUsed, String dbTable) throws Exception {
		moduleName	= "GetChexHistList: ";
		PrintLog(" DBUsed " + dbUsed + " For Table: " + dbTable);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> hist_list = new ArrayList<String>();
		hist_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT HIST_PROC_MONTH from " + dbTable.toLowerCase() + " ");
		//sql.append(param);
		sql.append(" ORDER by HIST_PROC_MONTH DESC");
		//PrintLog("SQL: "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp = result.getString("HIST_PROC_MONTH");
			//PrintLog("History Table for "+ temp + " Found");
			hist_list.add(temp);
		}
		statement.close();
		result.close();
		// PrintLog("GetChexHistList size -->"+ hist_list.size());
		return (hist_list);
	}
	//
	public ArrayList<String> GetChexHistList(Connection dbConn,
			String dbUsed, String dbTable, String nParam) throws Exception {
		moduleName	= "GetChexHistList: ";
		PrintLog(" DBUsed " + dbUsed + " For Table: " + dbTable);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> hist_list = new ArrayList<String>();
		hist_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT HIST_PROC_MONTH from " + dbTable.toLowerCase() + " ");
		sql.append(nParam);
		sql.append(" ORDER by HIST_PROC_MONTH DESC");
		//PrintLog("SQL: "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp = result.getString("HIST_PROC_MONTH");
			//PrintLog("History Table for "+ temp + " Found");
			hist_list.add(temp);
		}
		statement.close();
		result.close();
		// PrintLog("GetChexHistList size -->"+ hist_list.size());
		return (hist_list);
	}
	//
	public TreeMap<String, Integer> ChexCount(Connection dbConn, String procYM) throws Exception {
		moduleName			= "ChexCount: ";
		TreeMap<String, Integer>checksCount	= new TreeMap <String, Integer>();
		checksCount.clear();
		String acctNum		= "";
		int	checkCount		= 0;
		int	checkCount1		= 0;
		// PrintLog(" DBUsed "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		//select chist_account_num, count(chist_account_num) + 
		//(select count(chex_account_num) from incl_chex 
		//where chist_account_num=chex_account_num) as chex_count
		//from incl_chex_m_201809 group by chist_account_num
		//
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		// sql.append("SELECT CHEX_ACCOUNT_NUM from incl_chex ");
		sql.append("SELECT chist_account_num, COUNT(chist_account_num) as chexCount " +
				   "FROM incl_chex_m_"+ procYM +" where chist_return_status=' ' " + 
				   "GROUP BY chist_account_num");
		// PrintLog("Chex Count SQL "+sql);
		// Setup the SELECT statement.
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum	= result.getString("CHIST_ACCOUNT_NUM");
			checkCount	= Integer.parseInt(result.getString("chexCount"));
			checksCount.put(acctNum, checkCount);
			PrintLog("1 Account Num: "+acctNum + " Chex Count: "+checkCount);
		}
		sql.setLength(0);
		//sql.append("SELECT CHEX_ACCOUNT_NUM from incl_chex ");
		sql.append("SELECT chex_account_num, COUNT(chex_account_num) as chexCount FROM incl_chex " + 
						   "WHERE chex_check_status<>'F'" + 
						   "GROUP BY chex_account_num");
		// PrintLog("Chex Count SQL "+sql);
		// Setup the SELECT statement.
		result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum	= result.getString("CHEX_ACCOUNT_NUM");
			checkCount1	= Integer.parseInt(result.getString("chexCount"));
			checkCount	= 0;
			if (checksCount.containsKey(acctNum)) {
				checkCount	= checksCount.get(acctNum);
			}
			checksCount.put(acctNum, checkCount+checkCount1);
			PrintLog("2 Account Num: "+acctNum + " Chex Count: "+checksCount.get(acctNum));
		}
		statement.close();
		result.close();
		return (checksCount);
	}
	//
	public TreeMap<String, Long> HighCheckNum(Connection dbConn, String procYM) throws Exception {
		moduleName			= "HighChexNumbers: ";
		TreeMap<String, Long>highCheckNum	= new TreeMap <String, Long>();
		highCheckNum.clear();
		String acctNum		= "";
		Long	hCheckNum	= 0L;
		//Long	hCheckNum1	= 0L;
		// PrintLog(" DBUsed "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		//select chist_account_num, count(chist_account_num) + 
		//(select count(chex_account_num) from incl_chex 
		//where chist_account_num=chex_account_num) as chex_count
		//from incl_chex_m_201809 group by chist_account_num
		//
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		// sql.append("SELECT CHEX_ACCOUNT_NUM from incl_chex ");
		sql.append("SELECT hist_account_num, nvl(max(hist_check_num_h),0) as highChNum " +
				   "FROM incl_chex_history where hist_proc_month > " + procYM + 
				   " GROUP BY hist_account_num");
		//PrintLog("Chex Count SQL "+sql);
		// Setup the SELECT statement.
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum		= result.getString("HIST_ACCOUNT_NUM");
			hCheckNum	= Long.parseLong(result.getString("highChNum"));
			highCheckNum.put(acctNum, hCheckNum);
			//PrintLog("Account Num: "+acctNum + "\tHigh Check Number: "+hCheckNum);
		}
		sql.setLength(0);
		/*
		//sql.append("SELECT CHEX_ACCOUNT_NUM from incl_chex ");
		sql.append("SELECT chex_account_num, nvl(max(hist_check_num_h),0) as highChNum" +
					" FROM incl_chex WHERE chex_check_status<>'F'" + 
					" GROUP BY chex_account_num");
		// PrintLog("Chex Count SQL "+sql);
		// Setup the SELECT statement.
		result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			acctNum		= result.getString("CHEX_ACCOUNT_NUM");
			hCheckNum1	= Long.parseLong(result.getString("highChNum"));
			checkCount	= 0;
			if (checksCount.containsKey(acctNum)) {
				checkCount	= checksCount.get(acctNum);
			}
			checksCount.put(acctNum, checkCount+checkCount1);
			PrintLog("2 Account Num: "+acctNum + " Chex Count: "+checksCount.get(acctNum));
		}
		*/
		statement.close();
		result.close();
		return (highCheckNum);
	}
	//
	public boolean ChexExists(Connection dbConn, String dbTable,
			String acct_num, String check_num, ChexDetail chexDetail)
			throws Exception {
		moduleName	= "ChexExists: ";
		// PrintLog(" DBUsed "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		if (acct_num.length() == 0) {
			chexDetail.setErrorVec("Account", "error.field.required");
			//PrintLog("Account NUmber " + acct_num);
			return (true);
		}
		if (check_num.length() == 0) {
			chexDetail.setErrorVec("Check Number", "error.field.required");
			//PrintLog("Check Number " + check_num);
			return (true);
		}
		param				= "WHERE CHEX_ACCOUNT_NUM='" + acct_num;
		param				+= "' AND CHEX_CHECK_NUM='" + check_num + "'";
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		// sql.append("SELECT CHEX_ACCOUNT_NUM from incl_chex ");
		sql.append("SELECT CHEX_ACCOUNT_NUM from " + dbTable + " ");
		sql.append(param);
		// PrintLog("Chex Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("CHEX_ACCOUNT_NUM");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public boolean ChexExistsInHistory(Connection dbConn,
					String acct_num, String check_num,
					ChexDetail chexDetail, String sevenMonths) throws Exception {
		moduleName	= "ChexExistsInHistory: ";
		//PrintLog("Checking in history for Account: "+acct_num+" Check Num:"+check_num);
		// PrintLog(" DBUsed "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		int hist_rows		= 0;
		String dbTable		= "INCL_CHEX_HISTORY";
		String histTable	= "";
		String sqlStr		= "";
		StringBuffer sql	= new StringBuffer();
		String procDate		= sevenMonths.substring(0,6);
		String dbUsed		= chexDetail.getDbUsed();
		String nParam		= "";
		ChexSelector chexSelector	= new ChexSelector();
		chexSelector.setDbUsed(dbUsed);
		if (acct_num.length() == 0) {
			chexDetail.setErrorVec("Account", "error.field.required");
			//PrintLog("Chex Exists in History " + acct_num);
			return (true);
		}
		if (check_num.length() == 0) {
			chexDetail.setErrorVec("Check Number", "error.field.required");
			//PrintLog("Chex Exists in History " + check_num);
			return (true);
		}
		ArrayList<String> hist_list	= new ArrayList<String>();
		nParam = " WHERE HIST_ACCOUNT_NUM='" + acct_num + "'" +
				" AND ('"+check_num+"' between HIST_CHECK_NUM_L and HIST_CHECK_NUM_H) " +
				" AND HIST_PROC_MONTH > "+ procDate;
		hist_list		= GetChexHistList(dbConn, dbUsed, dbTable, nParam);
				//hist_rows = GetHistRows(dbConn, acct_num, check_num,  chexSelector);
		Iterator<String> h_tables	= hist_list.iterator();
		param	= " WHERE CHIST_ACCOUNT_NUM='" + acct_num + "'";
		param	+= " AND CHIST_CHECK_NUM='"+check_num+"'";
		// Added Extra Check for return status
		param	+= " AND CHIST_RETURN_STATUS!='Y'";
		sqlStr	= "SELECT CHIST_PROC_DATE, CHIST_ACCOUNT_NUM, " +
					"CHIST_CHECK_NUM FROM incl_chex_m_";
		while (h_tables.hasNext()) {
			// PrintLog("Getting Rows");
			// ------------------- DO BOT REMOVE -------------------------
			// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
			// ---------------------------------------------------------------
			histTable	= h_tables.next().toString();
			PrintLog("Hist Table: >>" + histTable);
			sql.setLength(0);
			//PrintLog("SQL: "+sql+" Param: "+param);
			sql.append(sqlStr);
			sql.append(histTable.trim() + " ");
			sql.append(param);
			//PrintLog("SQL: "+sql);
			if (dbConn == null)
				PrintLog("------------------ NULL DBCONN -----------------");
			Statement statement	= dbConn.createStatement();
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				//PrintLog("checkReturned:> " + checkReturned);
				hist_rows++;
			}
			statement.close();
			result.close();
		}
		param	= "";
		if (hist_rows == 0) {
			return (false);
		} else {
			return (true);
		}
	}
	//
	public int GetChexStatusSummaryRows(Connection dbConn,
										ChexSelector chexSelector) throws Exception {
		moduleName		= "GetChexStatusSummaryRows: ";
		String check_status_desc	= "";
		String check_status			= "";
		String check_amount			= "";
		String check_count			= "";
		String completed_checks		= "0";
		String dbTable				= chexSelector.getDbTable();
		// double d = 0.45666;
		DecimalFormat df			= new DecimalFormat("###,###.00");
		// String s = df.format(d);
		// PrintLog(Double.parseDouble(s));
		double amount_total			= 0;
		String amount_total_s		= "";
		int check_total				= 0;
		StringBuffer sql			= new StringBuffer();
		int row_count				= 0;
		//
		sql.setLength(0);
		sql.append("SELECT SUM(CHEX_CHECK_AMOUNT), ");
		sql.append("CHEX_CHECK_STATUS, COUNT(*) FROM ");
		sql.append(dbTable + " GROUP BY CHEX_CHECK_STATUS ");
		sql.append("ORDER BY CHEX_CHECK_STATUS");

		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			ChexSummary chexSummary	= new ChexSummary();
			// Here add the fields tp the ChexDetail bean
			check_status			= result.getString("CHEX_CHECK_STATUS");
			check_amount			= result.getString("SUM(CHEX_CHECK_AMOUNT)");
			check_count				= result.getString("COUNT(*)");
			if (check_status.equals("A")) {
				check_status_desc	= "To be Authorized";
			} else if (check_status.equals("E")) {
				check_status_desc	= "Error - Invalid Account";
			} else if (check_status.equals("F")) {
				check_status_desc	= "Fraud Check Alert";
			} else if (check_status.equals("S")) {
				check_status_desc	= "Payment Stopped";
			} else if (check_status.equals("R")) {
				check_status_desc	= "Rejected";
			} else if (check_status.equals("Z")) {
				check_status_desc	= "Complete";
				completed_checks	= check_count;
			}
			chexSummary.setChex_check_status(check_status);
			chexSummary.setChex_status_description(check_status_desc);
			chexSummary.setChex_summ_amount(df.format(Double.parseDouble(check_amount)));
			chexSummary.setChex_check_count(check_count);
			amount_total	+= Double.parseDouble(check_amount);
			check_total		+= Integer.parseInt(check_count);
			chexSBeans.add(chexSummary);
			row_count++;
		}
		// double d = 0.45666;
		// d = Math.round(d * 100.0) / 100.0; // round to 2 decimal places
		// PrintLog(d); // print it.
		amount_total_s	= df.format(amount_total);
		// PrintLog(amount_total_s); // print it.
		if (check_total != 0) {
			if (Integer.parseInt(completed_checks) == check_total) {
				chexSelector.setAllow_eod("Y");
			}
		} else {
			chexSelector.setAllow_eod("Y");
		}
		chexSelector.setDetail_count(row_count);
		chexSelector.setRow_Count(row_count+"");
		chexSelector.setSumm_total_amount(amount_total_s);
		new String();
		chexSelector.setSumm_total_checks(String.valueOf(check_total));
		chexSelector.setSummaryrows(chexSBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public void GetChexImages(ChexSelector chexSelector, ChexDetail chexDetail,
								String proc_date, String unique_isn) throws Exception {
		moduleName				= "GetChexImages: ";
		String imageFieldDate	= "";
		String imageField		= "";
		String bankId			= chexDetail.getBankId();
		bankId					= chexSelector.getBankId();
		String[] fileNames		= {};
		//
		String imageURL			= chexSelector.getImageURL();
		//PrintLog("image URL: "+imageURL);
		String imageDir			= chexSelector.getImageDir();
		//PrintLog("image Dir: "+imageURL);
		//
		imageField				= chexDetail.getChex_image();
		//PrintLog("image field: "+imageField);
		imageFieldDate			= (proc_date.substring(0, 4) + "/" +
									proc_date.substring(4, 6) + "/" + 
									proc_date.substring(6, 8) + "/");
		//PrintLog("Finding "+imageDir+imageFieldDate+imageField.substring(6,39));
		// PrintLog("BankId "+bankId);
		//
		//PrintLog("Finding "+imageDir+imageFieldDate+imageField);
		if (bankId.equals("BNPMO")) {
			//
			// Here use account # and Check # to get image
			// 00000000011111111112222222222333333333344444444445555555555666666
			// 12345678901234567890123456789012345678901234567890123456789012345
			// Check_00000003064505140_000000000001147_000002004110000_back.jpg
			// Check_00000003064505140_000000000001147_000002004110000_front.jpg
			//
			//PrintLog("Finding "+imageDir+imageFieldDate+imageField);
			fileNames = FileUtil.GetFileNames(imageDir + imageFieldDate, unique_isn);
			// PrintLog("Filenames size "+fileNames.length);
			// if (fileNames.length > 0) {
			// //
			// } else {
			// PrintLog("BNPMO Finding "+imageDir+imageFieldDate+imageField.substring(6,39));
			// fileNames = fUtil.GetFileNames(imageDir+imageFieldDate,
			// imageField.substring(6,39));
			// }
		} else {
			File imgDir		= new File(imageDir + imageFieldDate);
			//PrintLog("Finding "+imageDir+imageFieldDate);
			if (imgDir.exists()) {
				fileNames	= FileUtil.GetFileNames(imageDir + imageFieldDate, unique_isn);
			}
			
		}
		// PrintLog("Filenames size "+fileNames.length);
		if (fileNames.length > 0) {
			//PrintLog("# of Images Files "+fileNames.length);
			PrintLog("Images Files "+fileNames[0]);
			PrintLog("Images Files "+fileNames[1]);
			imageField	= fileNames[0].substring(0, 55);
			chexDetail.setChex_image(imageField);
			//chexDetail.setChex_image_b(imageURL + "incl/" + imageFieldDate + imageField + "_back.jpg");
			//chexDetail.setChex_image_f(imageURL + "incl/" + imageFieldDate + imageField + "_front.jpg");
			chexDetail.setChex_image_b(imageURL + "incl/" + imageFieldDate + fileNames[0]);
			chexDetail.setChex_image_f(imageURL + "incl/" + imageFieldDate + fileNames[1]);
		} else {
			PrintLog("Images do not exist "+imageDir+imageFieldDate+imageField);
			imageField	= "Chex_imageUnavailable.jpg";
			chexDetail.setChex_image(imageField);
			chexDetail.setChex_image_b(imageURL + imageField);
			chexDetail.setChex_image_f(imageURL + imageField);
		}
	}
	//
	public int GetChexRowsByUser(Connection dbConn, ChexSelector chexSelector,
								String userId) throws Exception {
		// Called by Authorize
		moduleName			= "GetChexRowsByUser: ";
		//String proc_date	= "";
		//String unique_isn	= "";
		param				= "WHERE (CHEX_CHECK_STATUS='A' AND ";
		param				+= " CHEX_MOD_USER!='" + userId + "') ";
		param				+= " ORDER BY CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, ";
		param				+= " CHEX_UNIQUE_ISN";
		row_count			= GetChexRows(dbConn, chexSelector);
		/*
		if (row_count > 0) {
			ChexDetail chexDetail	= new ChexDetail();
			chexDetail				= chexSelector.getArow();
			proc_date				= chexDetail.getChex_proc_date();
			chexSelector.setAppl_date(proc_date);
			unique_isn				= chexDetail.getChex_unique_isn();
			// if (chexImage.equals(" ")) {
			//GetChexImages(chexSelector, chexDetail, proc_date, unique_isn);
			// }
		}
		*/
		chexSelector.setDetail_count(row_count);
		return (row_count);
	}

	public int GetChexRows(Connection dbConn, ChexSelector chexSelector,
			String check_status) throws Exception {
		moduleName	= "GetChexRows1: ";
		param		= "WHERE CHEX_CHECK_STATUS='" + check_status + "'";
		param		+= " ORDER BY CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, ";
		param		+= " CHEX_UNIQUE_ISN";
		row_count	= GetChexRows(dbConn, chexSelector);
		return (row_count);
	}

	public int GetChexRows(Connection dbConn, ChexSelector chexSelector,
			String acct_num, String check_num, String check_unique_isn)
			throws Exception {
		// called only by Modify
		moduleName			= "GetChexRows2: ";
		String proc_date	= "";
		String unique_isn	= "";
		param				= " WHERE CHEX_ACCOUNT_NUM='" + acct_num + "'";
		param				+= " AND CHEX_CHECK_NUM='" + check_num + "'";
		param				+= " AND CHEX_UNIQUE_ISN='" + check_unique_isn + "'";
		String accessFlag	= chexSelector.getAccessFlag();
		String dbUsed		= chexSelector.getDbUsed();
		if (dbUsed.equals("ORACLE")) {
			if (accessFlag != null) {
				if (accessFlag.equals("inq")) {
					;
				} else {
					param	+= " for UPDATE nowait ";
					dbConn.setAutoCommit(false);
				}
			} else {
				param	+= " for UPDATE nowait ";
				dbConn.setAutoCommit(false);
			}
		}
		modifyRow	= true;
		row_count	= GetChexRows(dbConn, chexSelector);
		modifyRow	= false;
		if (row_count > 0) {
			ChexDetail chexDetail	= new ChexDetail();
			// chexDetail = chexSelector.getArow ();
			chexDetail	= (ChexDetail) chexBeans.get(0);
			chexSelector.setModifyRow(chexDetail);
			proc_date	= chexDetail.getChex_proc_date();
			unique_isn	= chexDetail.getChex_unique_isn();
			// if (chexImage.equals(" ")) {
			GetChexImages(chexSelector, chexDetail, proc_date, unique_isn);
			// }
		}
		return (row_count);
	}

	public int GetChexRows(Connection dbConn, ChexSelector chexSelector)
		throws Exception {
		//
		moduleName				= "GetChexRows3: ";
		double amountTotal		= 0;
		String amountTotal_s	= "";
		String unique_isn		= "";
		String proc_date		= "";
		String appl_date		= chexSelector.getAppl_date();
		String dbUsed			= chexSelector.getDbUsed();
		String dbTable			= chexSelector.getDbTable();
		//String accessFlag		= chexSelector.getAccessFlag();
		//String actionFlag		= chexSelector.getActionFlag();
		//PrintLog("Action Flag: " + actionFlag + " Access Flag: " + accessFlag);
		String bankId			= chexSelector.getBankId();
		String rejCode			= "";
		String rejDesc			= "";
		String statusCode		= "";
		//
		DecimalFormat df		= new DecimalFormat("###,###,###.00");
		if (param.equals(""))
			// GetParams (chexSelector);
			param		= chexSelector.GetParams();
		StringBuffer sql		= new StringBuffer();
		row_count				= 0;
		chexBeans.clear();
		sql.setLength(0);
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, ");
		sql.append("CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, CHEX_CHECK_STATUS, ");
		sql.append("CHEX_AMOUNT_OD, CHEX_IMAGE, CHEX_LAST_MODIFIED, ");
		sql.append("CHEX_MOD_USER, CHEX_MOD_FUNC ");
		sql.append("FROM " + dbTable + " ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		try {
			Statement statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				ChexDetail chexDetail	= new ChexDetail();
				// Here add the fields tp the ChexDetail bean
				chexDetail.setDbUsed(dbUsed);
				chexDetail.setBankId(bankId);
				chexDetail.setAppl_date(appl_date);
				chexDetail.setChex_proc_date(result.getString("CHEX_PROC_DATE"));
				proc_date	= chexDetail.getChex_proc_date();
				chexDetail.setChex_orig_account_num(result.getString("CHEX_ORIG_ACCOUNT_NUM"));
				chexDetail.setChex_orig_check_num(result.getString("CHEX_ORIG_CHECK_NUM"));
				chexDetail.setChex_account_num(result.getString("CHEX_ACCOUNT_NUM"));
				chexDetail.setChex_check_num(result.getString("CHEX_CHECK_NUM"));
				chexDetail.setChex_routing_transit(result.getString("CHEX_ROUTING_TRANSIT"));
				chexDetail.setChex_check_amount(result.getString("CHEX_CHECK_AMOUNT"));
				//
				amountTotal	= amountTotal + Double.parseDouble(result.getString("CHEX_CHECK_AMOUNT"));
				//
				chexDetail.setChex_check_currency(result.getString("CHEX_CHECK_CURRENCY"));
				chexDetail.setChex_proc_control(result.getString("CHEX_PROC_CONTROL"));
				chexDetail.setChex_ext_proc_control(result.getString("CHEX_EXT_PROC_CONTROL"));
				chexDetail.setChex_image_locator(result.getString("CHEX_IMAGE_LOCATOR"));
				chexDetail.setChex_unique_isn(result.getString("CHEX_UNIQUE_ISN"));
				unique_isn	= chexDetail.getChex_unique_isn();
				chexDetail.setChex_addenda_rec_flag(result.getString("CHEX_ADDENDA_REC_FLAG"));
				chexDetail.setChex_orig_inst_rte(result.getString("CHEX_ORIG_INST_RTE"));
				chexDetail.setChex_isn(result.getString("CHEX_ISN"));
				chexDetail.setChex_budget_id(result.getString("CHEX_BUDGET_ID"));
				chexDetail.setChex_return_date(result.getString("CHEX_RETURN_DATE"));
				chexDetail.setChex_return_reason(result.getString("CHEX_RETURN_REASON"));
				chexDetail.setChex_return_status(result.getString("CHEX_RETURN_STATUS"));
				chexDetail.setChex_BOFD_aba(result.getString("CHEX_BOFD_ABA"));
				chexDetail.setChex_BOFD_date(result.getString("CHEX_BOFD_DATE"));
				chexDetail.setChex_extra_1(result.getString("CHEX_EXTRA_1"));
				chexDetail.setChex_extra_2(result.getString("CHEX_EXTRA_2"));
				chexDetail.setChex_issue_date(result.getString("CHEX_ISSUE_DATE"));
				chexDetail.setChex_payee(result.getString("CHEX_PAYEE"));
				chexDetail.setChex_payee_addr1(result.getString("CHEX_PAYEE_ADDR1"));
				chexDetail.setChex_payee_addr2(result.getString("CHEX_PAYEE_ADDR2"));
				chexDetail.setChex_payee_addr3(result.getString("CHEX_PAYEE_ADDR3"));
				chexDetail.setChex_comments(result.getString("CHEX_COMMENTS"));
				rejCode	= result.getString("CHEX_REASON_CODES");
				if (rejCode.length() > 1) {
					rejCode = rejCode.trim();
					//PrintLog("Reject Code: '"+rejCode+"'");
				}
				//chexDetail.setChex_reason_codes(result.getString("CHEX_REASON_CODES"));
				chexDetail.setChex_reason_codes(rejCode);
				rejDesc	= "";
				if (rejCode != null) {
					if (!rejCode.equals("") || !rejCode.equals(" ")) {
						for (int idx = 0; idx < rejCode.length(); idx++) {
							if (chexSelector.getRejReason("C"+rejCode.substring(idx, idx+1)) != null) {
								rejDesc	= rejDesc + 
								  chexSelector.getRejReason("C"+rejCode.substring(idx, idx+1)) + ", ";
							}
						}
						if (!rejDesc.equals("")) {
							chexDetail.setChex_reason_desc(rejDesc.substring(0,rejDesc.length()-2));
						}
					}
				}
				statusCode	= result.getString("CHEX_CHECK_STATUS");
				chexDetail.setChex_check_status(statusCode);
				chexDetail.setChex_status_desc(chexSelector.getChexStatus("C"+statusCode));
				chexDetail.setChex_amount_od(result.getString("CHEX_AMOUNT_OD"));
				chexDetail.setChex_image(result.getString("CHEX_IMAGE"));
				chexDetail.setChex_last_modified(result.getString("CHEX_LAST_MODIFIED"));
				chexDetail.setChex_mod_user(result.getString("CHEX_MOD_USER"));
				chexDetail.setChex_mod_func(result.getString("CHEX_MOD_FUNC"));
				chexDetail.setChex_modparam();
				// PrintLog("Acct Num:>"+acct_num+"< Check Num > "+check_num+
				// "< Unique ISN >"+unique_isn);
				GetChexImages(chexSelector, chexDetail, proc_date, unique_isn);
				chexBeans.add(chexDetail);
				row_count++;
			}
			amountTotal_s	= df.format(amountTotal);
			chexSelector.setDetailAmount(amountTotal_s);
			if (modifyRow) {
			} else {
				chexSelector.setCheckrows(chexBeans);
				chexSelector.setDetail_count(row_count);
			}
			statement.close();
			result.close();
			param = "";
			return (row_count);
		} catch (SQLException e) {
			PrintLog("Error Getting Chex" + e.toString());
			PrintLog("Get Chex " + sql);
			param = "";
			return (0);
		}
	}
	//
	// Called by ChexHistDetailAction for Inquiry of a specific check
	//
	public int GetHistRows(Connection dbConn, String proc_date,
			String acct_num, String check_num, String check_unique_isn,
			ChexSelector chexSelector) throws Exception {
		moduleName	= "GetHistRows1: ";
		// PrintLog(" In Get Hist Rows ");
		// PrintLog(" In Get Hist Rows Proc date "+proc_date);
		int hist_rows	= 0;
		ArrayList<String> hist_list	= new ArrayList<String>();
		chex_from_period			= proc_date.substring(0, 6);
		param						= " WHERE CHIST_ACCOUNT_NUM='" + acct_num + "'";
		param						+= " AND CHIST_CHECK_NUM='" + check_num + "'";
		param						+= " AND CHIST_UNIQUE_ISN='" + check_unique_isn + "'";
		hist_list.add(proc_date.substring(0, 6));
		from_period					= chex_from_period;
		hist_rows					= GetHistRows(dbConn, hist_list, chexSelector);
		if (hist_rows > 0) {
			ChexDetail chexDetail	= new ChexDetail();
			chexDetail				= chexSelector.getArow();
			GetChexImages(chexSelector, chexDetail, proc_date, check_unique_isn);
		}
		return (hist_rows);
	}
	//
	public int GetHistRows(Connection dbConn, 
			String acct_num, String check_num, ChexSelector chexSelector)
			throws Exception {
		moduleName		= "GetHistRows2: ";
		//PrintLog("In Get Hist Rows for Account "+acct_num+" check num: "+check_num);
		int hist_rows	= 0;
		ArrayList<String> hist_list	= new ArrayList<String>();
		String dbUsed				= chexSelector.getDbUsed();
		String dbTable				= "INCL_CHEX_HISTORY";
		param						= " WHERE CHIST_ACCOUNT_NUM='" + acct_num + "'";
		param						+= " AND CHIST_CHECK_NUM='" + check_num + "'";
		//
		// Set the Check from and to so that the GetHistRows will attempt to search
		// all the History table that has Check with the Number for the specified Account.
		//
		chex_from_check	= check_num;
		chex_to_check	= "";
		chexSelector.setCh_from_check(chex_from_check);
		chexSelector.setCh_to_check("");
		//param = " WHERE HIST_ACCOUNT_NUM='" + acct_num + "'";
		//param += " AND '"+check_num+"' between HIST_CHECK_NUM_L and HIST_CHECK_NUM_H";
		hist_list		= GetChexHistList(dbConn, dbUsed, dbTable);
		hist_rows		= GetHistRows(dbConn, hist_list, chexSelector);
		return (hist_rows);
	}
	//
	// Called by ChexGenReturn to generate Return Checks' file.
	//
	public int GetHistRows(Connection dbConn, String procDate,
			ArrayList<String> histList, ChexSelector chexSelector)
			throws Exception {
		moduleName		= "GetHistRows: for Returns";
		//PrintLog(" In Get Hist Rows Proc date " + procDate);
		ArrayList<String> histList1	= new ArrayList<String>();
		int hist_rows	= 0;
		String dbUsed	= chexSelector.getDbUsed();
		String dbTable	= "incl_chex_history";
		//param = (" WHERE CHIST_RETURN_DATE LIKE '" + procDate.substring(0, 4) +
		//		 "%'" + " AND CHIST_RETURN_STATUS='N'");
		param = (" WHERE CHIST_RETURN_STATUS='N'");
		//
		// Set the Check from and to so that the GetHistRows will attempt to search
		// all the History table that has Check with the Number for the specified Account.
		//
		// Fake it so that the GetHistRows will look into all tables
		//
		chex_from_check	= "1";
		chex_to_check	= "1";
		//histList1		= chexSelector.getHistList();
		histList1		= GetChexHistList(dbConn, dbUsed, dbTable);
		PrintLog("Hist List Size: "+histList1.size());
		hist_rows		= GetRetHistRows(dbConn, histList1, chexSelector);
		return (hist_rows);
	}
	//
	// Called by ChexGenPDF to generate PDFs.
	//
	public int GetHistRows(Connection dbConn, ArrayList<String> hist_list,
			ChexSelector chexSelector, ArrayList<String> histPlist) throws Exception {
		String stmtFreq	= histPlist.get(12);
		moduleName		= "GetHistRows: for PDFs: " + stmtFreq;
		//PrintLog(" In Get Hist Rows Proc date ");
		param			= chexSelector.GetHistParams();
		int hist_rows	= 0;
		if (stmtFreq.equals("D")) {
			param			= "where chist_proc_date='" + histPlist.get(10) + "' " + param; 
		} else if (stmtFreq.equals("W")) {
			param			= "where chist_proc_date between '" + 
							   histPlist.get(10) + "' AND '" + histPlist.get(11) + "' " + param;
		}
		hist_rows		= GetHistRows(dbConn, hist_list, chexSelector);
		return (hist_rows);
	}
	//
	// Generic Get History rows based on different select clauses
	//
	public int GetHistRows(Connection dbConn, ArrayList<String> hist_list,
			ChexSelector chexSelector) throws Exception {
		moduleName				= "GetHistRows3: ";
		calledFrom				= moduleName;
		double amountTotal		= 0;
		String amountTotal_s	= "";
		String unique_isn		= "";
		String proc_date		= "";
		String rejCode			= "";
		String rejDesc			= "";
		String statusCode		= "";
		String appl_date		= chexSelector.getAppl_date();
		DecimalFormat df		= new DecimalFormat("###,###,###.00");
		if (param.equals(""))
			param				= chexSelector.GetHistParams();
		from_period				= chexSelector.getCh_from_period();
		to_period				= chexSelector.getCh_to_period();
		chex_from_check			= chexSelector.getCh_from_check();
		chex_to_check			= chexSelector.getCh_to_check();
		StringBuffer sql		= new StringBuffer();
		String hist_table;
		ArrayList<String> hist_tables	= new ArrayList<String>();
		int hist_size = hist_list.size();
		//PrintLog("hist_size:>> " + hist_size);
		//PrintLog("Chex_from_check >"+chex_from_check+"< to check >"+chex_to_check+"<");
		//PrintLog("Chex_from_period >"+from_period+"< to period >"+to_period+"<");
		for (int idx = 0; idx < hist_size; idx++) {
			hist_table	= hist_list.get(idx);
			//PrintLog("hist_table: " + hist_table+" from period: "+from_period);
			if (chex_from_check.equals("") && chex_to_check.equals("")) {
				if (hist_table.compareTo(from_period) == 0) {
					//PrintLog("1.1 Hist Table: " + hist_table + " From Period: "	+ from_period);
					hist_tables.add(hist_table);
				} else if (!to_period.equals("NONE")
						&& hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					//PrintLog("1.2 Hist Table: " + hist_table + " to Period: " + to_period + " From Period: " + from_period);
					hist_tables.add(hist_table);
				} else if (!to_period.equals("NONE")
						&& hist_table.compareTo(to_period) == 0) {
					//PrintLog("1.3 Hist Table: " + hist_table + " From Period: " + from_period);
					hist_tables.add(hist_table);
				}
			} else {
				//PrintLog("2. Hist Table: " + hist_table + " From Period: " + from_period);
				if (hist_table.compareTo(from_period) == 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(to_period) == 0) {
					hist_tables.add(hist_table);
				}
				// hist_tables.add(hist_table);
			}
		}
		PrintLog("Tables:>> "+hist_tables.size());
		chexBeans.clear();
		row_count	= 0;
		chexSelector.clearRows();
		Iterator<String> h_tables = hist_tables.iterator();
		while (h_tables.hasNext()) {
			//PrintLog("Getting Rows");
			// ------------------- DO BOT REMOVE -------------------------
			// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
			// ---------------------------------------------------------------
			hist_table	= h_tables.next().toString();
			//PrintLog("Tables:>> " + hist_table);
			sql.setLength(0);
			// PrintLog("SQL: "+sql+" Param: "+param);
			sql.append("SELECT CHIST_PROC_DATE, CHIST_ORIG_ACCOUNT_NUM, ");
			sql.append("CHIST_ORIG_CHECK_NUM, CHIST_ACCOUNT_NUM, ");
			sql.append("CHIST_CHECK_NUM, CHIST_ROUTING_TRANSIT, ");
			sql.append("CHIST_CHECK_CURRENCY, CHIST_CHECK_AMOUNT, ");
			sql.append("CHIST_PROC_CONTROL, CHIST_EXT_PROC_CONTROL, ");
			sql.append("CHIST_IMAGE_LOCATOR, CHIST_UNIQUE_ISN, ");
			sql.append("CHIST_ADDENDA_REC_FLAG, CHIST_ORIG_INST_RTE, ");
			sql.append("CHIST_ISN, CHIST_BUDGET_ID, ");
			sql.append("CHIST_RETURN_DATE, ");
			sql.append("CHIST_RETURN_REASON, ");
			sql.append("CHIST_RETURN_STATUS, ");
			sql.append("CHIST_BOFD_ABA, ");
			sql.append("CHIST_BOFD_DATE, ");
			sql.append("CHIST_EXTRA_1, ");
			sql.append("CHIST_EXTRA_2, ");
			sql.append("CHIST_ISSUE_DATE, ");
			sql.append("CHIST_PAYEE, ");
			sql.append("CHIST_PAYEE_ADDR1, ");
			sql.append("CHIST_PAYEE_ADDR2, ");
			sql.append("CHIST_PAYEE_ADDR3, ");
			sql.append("CHIST_COMMENTS, ");
			sql.append("CHIST_REASON_CODES, ");
			sql.append("CHIST_CHECK_STATUS, CHIST_AMOUNT_OD, CHIST_IMAGE, ");
			sql.append("CHIST_LAST_MODIFIED, ");
			sql.append("CHIST_MOD_USER, CHIST_MOD_FUNC ");
			sql.append("FROM incl_chex_m_" + hist_table.trim() + " ");
			sql.append(param);
			//PrintLog("SQL: " + sql);
			if (dbConn == null)
				PrintLog("------------------ NULL DBCONN -----------------");
			Statement statement	= dbConn.createStatement();
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				ChexDetail chexDetail	= new ChexDetail();
				// Here add the fields tp the CHISTDetail
				chexDetail.setAppl_date(appl_date);
				chexDetail.setDbUsed(chexSelector.getDbUsed());
				chexDetail.setChex_proc_date(result.getString("CHIST_PROC_DATE"));
				proc_date = result.getString("CHIST_PROC_DATE");
				chexDetail.setChex_orig_account_num(result.getString("CHIST_ORIG_ACCOUNT_NUM"));
				chexDetail.setChex_orig_check_num(result.getString("CHIST_ORIG_CHECK_NUM"));
				chexDetail.setChex_account_num(result.getString("CHIST_ACCOUNT_NUM"));
				chexDetail.setChex_check_num(result.getString("CHIST_CHECK_NUM"));
				chexDetail.setChex_routing_transit(result.getString("CHIST_ROUTING_TRANSIT"));
				chexDetail.setChex_check_amount(result.getString("CHIST_CHECK_AMOUNT"));
				//
				amountTotal	= amountTotal + Double.parseDouble(result.getString("CHIST_CHECK_AMOUNT"));
				//
				chexDetail.setChex_check_currency(result.getString("CHIST_CHECK_CURRENCY"));
				chexDetail.setChex_proc_control(result.getString("CHIST_PROC_CONTROL"));
				chexDetail.setChex_ext_proc_control(result.getString("CHIST_EXT_PROC_CONTROL"));
				chexDetail.setChex_image_locator(result.getString("CHIST_IMAGE_LOCATOR"));
				chexDetail.setChex_unique_isn(result.getString("CHIST_UNIQUE_ISN"));
				unique_isn	= result.getString("CHIST_UNIQUE_ISN");
				chexDetail.setChex_addenda_rec_flag(result.getString("CHIST_ADDENDA_REC_FLAG"));
				chexDetail.setChex_orig_inst_rte(result.getString("CHIST_ORIG_INST_RTE"));
				chexDetail.setChex_isn(result.getString("CHIST_ISN"));
				chexDetail.setChex_budget_id(result.getString("CHIST_BUDGET_ID"));
				chexDetail.setChex_return_date(result.getString("CHIST_RETURN_DATE"));
				chexDetail.setChex_return_reason(result.getString("CHIST_RETURN_REASON"));
				chexDetail.setChex_return_status(result.getString("CHIST_RETURN_STATUS"));
				chexDetail.setChex_BOFD_aba(result.getString("CHIST_BOFD_ABA"));
				chexDetail.setChex_BOFD_date(result.getString("CHIST_BOFD_DATE"));
				if (result.getString("CHIST_EXTRA_1") == null) {
					chexDetail.setChex_extra_1(" ");
				} else {
					chexDetail.setChex_extra_1(result.getString("CHIST_EXTRA_1"));
				}
				if (result.getString("CHIST_EXTRA_2") == null) {
					chexDetail.setChex_extra_2(" ");
				} else {
					chexDetail.setChex_extra_2(result.getString("CHIST_EXTRA_2"));
				}
				chexDetail.setChex_issue_date(result.getString("CHIST_ISSUE_DATE"));
				chexDetail.setChex_payee(result.getString("CHIST_PAYEE"));
				chexDetail.setChex_payee_addr1(result.getString("CHIST_PAYEE_ADDR1"));
				chexDetail.setChex_payee_addr2(result.getString("CHIST_PAYEE_ADDR2"));
				chexDetail.setChex_payee_addr3(result.getString("CHIST_PAYEE_ADDR3"));
				chexDetail.setChex_comments(result.getString("CHIST_COMMENTS"));
				rejDesc	= "";
				rejCode	= result.getString("CHIST_REASON_CODES");
				if (rejCode != null) {
					if (!rejCode.equals("") || !rejCode.equals(" ")) {
						for (int idx = 0; idx < rejCode.length(); idx++) {
							rejDesc	= rejDesc + 
								  chexSelector.getRejReason("C"+rejCode.substring(idx, idx+1)) + ", ";
						}
						if (!rejDesc.equals("")) {
							chexDetail.setChex_reason_desc(rejDesc.substring(0,rejDesc.length()-2));
						}
					}
				}
				//if (!rejCode.equals("") || !rejCode.equals(" ") || rejCode != null) {
				//	chexDetail.setChex_reason_codes(rejCode);
				//	for (int idx = 0; idx < rejCode.length(); idx++) {
				//		rejDesc	= rejDesc + chexSelector.getRejReason(rejCode.substring(idx, idx+1)) + ", ";
				//	}
				//	chexDetail.setChex_reason_desc(rejDesc.substring(0,rejDesc.length()-2));
				//}
				statusCode	= result.getString("CHIST_CHECK_STATUS");
				chexDetail.setChex_check_status(statusCode);
				chexDetail.setChex_status_desc(chexSelector.getChexStatus("C"+statusCode));
				chexDetail.setChex_amount_od(result.getString("CHIST_AMOUNT_OD"));
				chexDetail.setChex_image(result.getString("CHIST_IMAGE"));
				chexDetail.setChex_last_modified(result.getString("CHIST_LAST_MODIFIED"));
				chexDetail.setChex_mod_user(result.getString("CHIST_MOD_USER"));
				chexDetail.setChex_mod_func(result.getString("CHIST_MOD_FUNC"));
				chexDetail.setChex_modparam();
				GetChexImages(chexSelector, chexDetail, proc_date, unique_isn);
				chexBeans.add(row_count, chexDetail);
				row_count++;
			}
			statement.close();
			result.close();
		}
		chexSelector.setCheckrows(chexBeans);
		//PrintLog("Number of ChexBeans " + chexBeans.size());
		amountTotal_s	= df.format(amountTotal);
		chexSelector.setDetailAmount(amountTotal_s);
		chexSelector.setCheckrows(chexBeans);
		chexSelector.setDetail_count(row_count);
		return (row_count);
	}
	//
	// Get History rows for return only
	//
	public int GetRetHistRows(Connection dbConn, ArrayList<String> hist_list,
			ChexSelector chexSelector) throws Exception {
		moduleName				= "GetHistRows: ";
		String checkReturned	= "";
		String unique_isn		= "";
		String proc_date		= "";
		//String imageLookup		= chexSelector.getImageLookup();
		String appl_date		= chexSelector.getAppl_date();
		from_period				= chexSelector.getCh_from_period();
		to_period				= chexSelector.getCh_to_period();
		if (to_period.equals("NONE")) {
			to_period	= "";
		}
		//chex_from_check			= chexSelector.getCh_from_check();
		//chex_to_check			= chexSelector.getCh_to_check();
		chex_from_check			= "1";
		chex_to_check			= "1";
		// DecimalFormat df		= new DecimalFormat("###,###.00");
		chexBeans.clear();
		if (param.equals(""))
			// GetHistParams (chexSelector);
			param	= chexSelector.GetHistParams();
		StringBuffer sql		= new StringBuffer();
		String hist_table;
		ArrayList<String> hist_tables	= new ArrayList<String>();
		int hist_size					= hist_list.size();
		// PrintLog("appl date:>> "+appl_date);
		PrintLog("hist_size:>> "+hist_size);
		// PrintLog("Chex_from_check >"+chex_from_check+"< to check >"+chex_to_check+"<");
		// PrintLog("Chex_from_period >"+from_period+"< to period >"+to_period+"<");
		for (int idx = 0; idx < hist_size; idx++) {
			hist_table	= hist_list.get(idx);
			if (chex_from_check.equals("") && chex_to_check.equals("")) {
				if (hist_table.compareTo(from_period) == 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(to_period) == 0) {
					hist_tables.add(hist_table);
				}
			} else {
				hist_tables.add(hist_table);
			}
		}
		PrintLog("Tables:>> "+hist_tables.size());
		row_count					= 0;
		Iterator<String> h_tables	= hist_tables.iterator();
		while (h_tables.hasNext()) {
			// PrintLog("Getting Rows");
			// ------------------- DO BOT REMOVE -------------------------
			// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
			// ---------------------------------------------------------------
			hist_table	= h_tables.next().toString();
			//PrintLog("Tables:>> "+hist_table);
			sql.setLength(0);
			// PrintLog("SQL: "+sql+" Param: "+param);
			sql.append("SELECT CHIST_PROC_DATE, CHIST_ORIG_ACCOUNT_NUM, ");
			sql.append("CHIST_ORIG_CHECK_NUM, CHIST_ACCOUNT_NUM, ");
			sql.append("CHIST_CHECK_NUM, CHIST_ROUTING_TRANSIT, ");
			sql.append("CHIST_CHECK_CURRENCY, CHIST_CHECK_AMOUNT, ");
			sql.append("CHIST_PROC_CONTROL, CHIST_EXT_PROC_CONTROL, ");
			sql.append("CHIST_IMAGE_LOCATOR, CHIST_UNIQUE_ISN, ");
			sql.append("CHIST_ADDENDA_REC_FLAG, CHIST_ORIG_INST_RTE, ");
			sql.append("CHIST_ISN, CHIST_BUDGET_ID, ");
			sql.append("CHIST_RETURN_DATE, ");
			sql.append("CHIST_RETURN_REASON, ");
			sql.append("CHIST_RETURN_STATUS, ");
			sql.append("CHIST_BOFD_ABA, ");
			sql.append("CHIST_BOFD_DATE, ");
			sql.append("CHIST_EXTRA_1, ");
			sql.append("CHIST_EXTRA_2, ");
			sql.append("CHIST_ISSUE_DATE, ");
			sql.append("CHIST_PAYEE, ");
			sql.append("CHIST_PAYEE_ADDR1, ");
			sql.append("CHIST_PAYEE_ADDR2, ");
			sql.append("CHIST_PAYEE_ADDR3, ");
			sql.append("CHIST_COMMENTS, ");
			sql.append("CHIST_REASON_CODES, ");
			sql.append("CHIST_CHECK_STATUS, CHIST_AMOUNT_OD, CHIST_IMAGE, ");
			sql.append("CHIST_LAST_MODIFIED, ");
			sql.append("CHIST_MOD_USER, CHIST_MOD_FUNC ");
			sql.append("FROM incl_chex_m_" + hist_table.trim() + " ");
			sql.append("WHERE CHIST_RETURN_STATUS='N' ");
			sql.append(param);
			//PrintLog("SQL: "+sql);
			if (dbConn == null)
				PrintLog("------------------ NULL DBCONN -----------------");
			Statement statement	= dbConn.createStatement();
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				ChexDetail chexDetail	= new ChexDetail();
				// Here add the fields tp the CHISTDetail
				chexDetail.setAppl_date(appl_date);
				chexDetail.setDbUsed(chexSelector.getDbUsed());
				chexDetail.setChex_proc_date(result.getString("CHIST_PROC_DATE"));
				proc_date	= result.getString("CHIST_PROC_DATE");
				chexDetail.setChex_orig_account_num(result.getString("CHIST_ORIG_ACCOUNT_NUM"));
				chexDetail.setChex_orig_check_num(result.getString("CHIST_ORIG_CHECK_NUM"));
				chexDetail.setChex_account_num(result.getString("CHIST_ACCOUNT_NUM"));
				chexDetail.setChex_check_num(result.getString("CHIST_CHECK_NUM"));
				chexDetail.setChex_routing_transit(result.getString("CHIST_ROUTING_TRANSIT"));
				chexDetail.setChex_check_amount(result.getString("CHIST_CHECK_AMOUNT"));
				chexDetail.setChex_check_currency(result.getString("CHIST_CHECK_CURRENCY"));
				chexDetail.setChex_proc_control(result.getString("CHIST_PROC_CONTROL"));
				chexDetail.setChex_ext_proc_control(result.getString("CHIST_EXT_PROC_CONTROL"));
				chexDetail.setChex_image_locator(result.getString("CHIST_IMAGE_LOCATOR"));
				chexDetail.setChex_unique_isn(result.getString("CHIST_UNIQUE_ISN"));
				unique_isn	= result.getString("CHIST_UNIQUE_ISN");
				chexDetail.setChex_addenda_rec_flag(result.getString("CHIST_ADDENDA_REC_FLAG"));
				chexDetail.setChex_orig_inst_rte(result.getString("CHIST_ORIG_INST_RTE"));
				chexDetail.setChex_isn(result.getString("CHIST_ISN"));
				chexDetail.setChex_budget_id(result.getString("CHIST_BUDGET_ID"));
				chexDetail.setChex_return_date(result.getString("CHIST_RETURN_DATE"));
				chexDetail.setChex_return_reason(result.getString("CHIST_RETURN_REASON"));
				chexDetail.setChex_return_status(result.getString("CHIST_RETURN_STATUS"));
				chexDetail.setChex_BOFD_aba(result.getString("CHIST_BOFD_ABA"));
				chexDetail.setChex_BOFD_date(result.getString("CHIST_BOFD_DATE"));
				if (result.getString("CHIST_EXTRA_1") == null) {
					chexDetail.setChex_extra_1(" ");
				} else {
					chexDetail.setChex_extra_1(result.getString("CHIST_EXTRA_1"));
				}
				if (result.getString("CHIST_EXTRA_2") == null) {
					chexDetail.setChex_extra_2(" ");
				} else {
					chexDetail.setChex_extra_2(result.getString("CHIST_EXTRA_2"));
				}
				chexDetail.setChex_issue_date(result.getString("CHIST_ISSUE_DATE"));
				chexDetail.setChex_payee(result.getString("CHIST_PAYEE"));
				chexDetail.setChex_payee_addr1(result.getString("CHIST_PAYEE_ADDR1"));
				chexDetail.setChex_payee_addr2(result.getString("CHIST_PAYEE_ADDR2"));
				chexDetail.setChex_payee_addr3(result.getString("CHIST_PAYEE_ADDR3"));
				chexDetail.setChex_comments(result.getString("CHIST_COMMENTS"));
				chexDetail.setChex_reason_codes(result.getString("CHIST_REASON_CODES"));
				chexDetail.setChex_check_status(result.getString("CHIST_CHECK_STATUS"));
				chexDetail.setChex_amount_od(result.getString("CHIST_AMOUNT_OD"));
				chexDetail.setChex_image(result.getString("CHIST_IMAGE"));
				chexDetail.setChex_last_modified(result.getString("CHIST_LAST_MODIFIED"));
				chexDetail.setChex_mod_user(result.getString("CHIST_MOD_USER"));
				chexDetail.setChex_mod_func(result.getString("CHIST_MOD_FUNC"));
				chexDetail.setChex_modparam();
				//if (imageLookup.equals("Y")) {
					GetChexImages(chexSelector, chexDetail, proc_date, unique_isn);
				//}
				checkReturned = chexDetail.getChex_return_status();
				//PrintLog("checkReturned:> " + checkReturned);
				if (!checkReturned.equals("Y")) {
					chexBeans.add(chexDetail);
					row_count++;
				}
			}
			statement.close();
			result.close();
		}
		chexSelector.setCheckrows(chexBeans);
		return (row_count);
	}
	//
	// Generic Get Log History rows based on different select clauses
	//
	public int GetLogHistRows(Connection dbConn, ArrayList<String> hist_list,
			ChexSelector chexSelector) throws Exception {
		moduleName	= "GetLogHistRows: ";
		chexSelector.clearNulls();
		String appl_date		= chexSelector.getAppl_date();
		String chex_from_check	= chexSelector.getCh_from_check();
		String chex_to_check	= chexSelector.getCh_to_check();
		String chex_from_period	= chexSelector.getCh_from_period();
		String chex_to_period	= chexSelector.getCh_to_period();
		if (chex_to_period.equals("NONE")) {
			chex_to_period	= "";
		}
		if (param.equals(""))
			// GetLogHistParams (chexSelector);
			param = chexSelector.GetLogHistParams();
		StringBuffer sql	= new StringBuffer();
		String hist_table;
		ArrayList<String> hist_tables	= new ArrayList<String>();
		int hist_size	= hist_list.size();
		//PrintLog("hist_size:>> " + hist_size);
		//PrintLog("Chex_from_check >" + chex_from_check + "< to check >"
		//		+ chex_to_check + "<");
		//PrintLog("Chex_from_period >" + chex_from_period + "< to period >"
		//		+ chex_to_period + "<");
		for (int idx = 0; idx < hist_size; idx++) {
			hist_table	= hist_list.get(idx);
			if (chex_from_check.equals("") && chex_to_check.equals("")) {
				if (hist_table.compareTo(chex_from_period) == 0) {
					//PrintLog("Tables1:>> " + hist_table);
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(chex_from_period) > 0
						&& hist_table.compareTo(chex_to_period) < 0) {
					//PrintLog("Tables2:>> " + hist_table);
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(chex_to_period) == 0) {
					//PrintLog("Tables3:>> " + hist_table);
					hist_tables.add(hist_table);
				}
			} else {
				//PrintLog("Tables:>> " + hist_table);
				hist_tables.add(hist_table);
			}
		}
		// PrintLog("Tables:>> "+hist_tables.size());
		row_count	= 0;
		Iterator<String> h_tables	= hist_tables.iterator();
		while (h_tables.hasNext()) {
			// PrintLog("Getting Rows");
			// ------------------- DO BOT REMOVE -------------------------
			// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
			// ---------------------------------------------------------------
			hist_table	= h_tables.next().toString();
			// PrintLog("Tables:>> "+hist_table);
			sql.setLength(0);
			// PrintLog("SQL: "+sql+" Param: "+param);
			sql.append("SELECT CHLOG_PROC_DATE, CHLOG_ORIG_ACCOUNT_NUM, ");
			sql.append("CHLOG_ORIG_CHECK_NUM, CHLOG_ACCOUNT_NUM, ");
			sql.append("CHLOG_CHECK_NUM, CHLOG_ROUTING_TRANSIT, ");
			sql.append("CHLOG_CHECK_CURRENCY, CHLOG_CHECK_AMOUNT, ");
			sql.append("CHLOG_PROC_CONTROL, CHLOG_EXT_PROC_CONTROL, ");
			sql.append("CHLOG_IMAGE_LOCATOR, CHLOG_UNIQUE_ISN, ");
			sql.append("CHLOG_ADDENDA_REC_FLAG, CHLOG_ORIG_INST_RTE, ");
			sql.append("CHLOG_ISN, CHLOG_BUDGET_ID, ");
			sql.append("CHLOG_RETURN_DATE, ");
			sql.append("CHLOG_RETURN_REASON, ");
			sql.append("CHLOG_RETURN_STATUS, ");
			sql.append("CHLOG_BOFD_ABA, ");
			sql.append("CHLOG_BOFD_DATE, ");
			sql.append("CHLOG_EXTRA_1, ");
			sql.append("CHLOG_EXTRA_2, ");
			sql.append("CHLOG_ISSUE_DATE, ");
			sql.append("CHLOG_PAYEE, ");
			sql.append("CHLOG_PAYEE_ADDR1, ");
			sql.append("CHLOG_PAYEE_ADDR2, ");
			sql.append("CHLOG_PAYEE_ADDR3, ");
			sql.append("CHLOG_COMMENTS, ");
			sql.append("CHLOG_REASON_CODES, ");
			sql.append("CHLOG_CHECK_STATUS, CHLOG_AMOUNT_OD, CHLOG_IMAGE, ");
			sql.append("CHLOG_LAST_MODIFIED, ");
			sql.append("CHLOG_MOD_USER, CHLOG_MOD_FUNC ");
			sql.append("FROM incl_chex_log_" + hist_table.trim() + " ");
			sql.append(param);
			//PrintLog("SQL: " + sql);
			if (dbConn == null)
				PrintLog("------------------ NULL DBCONN -----------------");
			Statement statement	= dbConn.createStatement();
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				ChexDetail chexDetail	= new ChexDetail();
				// Here add the fields tp the CHISTDetail
				chexDetail.setAppl_date(appl_date);
				chexDetail.setDbUsed(chexSelector.getDbUsed());
				chexDetail.setChex_proc_date(result.getString("CHLOG_PROC_DATE"));
				chexDetail.setChex_orig_account_num(result.getString("CHLOG_ORIG_ACCOUNT_NUM"));
				chexDetail.setChex_orig_check_num(result.getString("CHLOG_ORIG_CHECK_NUM"));
				chexDetail.setChex_account_num(result.getString("CHLOG_ACCOUNT_NUM"));
				chexDetail.setChex_check_num(result.getString("CHLOG_CHECK_NUM"));
				chexDetail.setChex_routing_transit(result.getString("CHLOG_ROUTING_TRANSIT"));
				chexDetail.setChex_check_amount(result.getString("CHLOG_CHECK_AMOUNT"));
				chexDetail.setChex_check_currency(result.getString("CHLOG_CHECK_CURRENCY"));
				chexDetail.setChex_proc_control(result.getString("CHLOG_PROC_CONTROL"));
				chexDetail.setChex_ext_proc_control(result.getString("CHLOG_EXT_PROC_CONTROL"));
				chexDetail.setChex_image_locator(result.getString("CHLOG_IMAGE_LOCATOR"));
				chexDetail.setChex_unique_isn(result.getString("CHLOG_UNIQUE_ISN"));
				chexDetail.setChex_addenda_rec_flag(result.getString("CHLOG_ADDENDA_REC_FLAG"));
				chexDetail.setChex_orig_inst_rte(result.getString("CHLOG_ORIG_INST_RTE"));
				chexDetail.setChex_isn(result.getString("CHLOG_ISN"));
				chexDetail.setChex_budget_id(result.getString("CHLOG_BUDGET_ID"));
				chexDetail.setChex_return_date(result.getString("CHLOG_RETURN_DATE"));
				chexDetail.setChex_return_reason(result.getString("CHLOG_RETURN_REASON"));
				chexDetail.setChex_return_status(result.getString("CHLOG_RETURN_STATUS"));
				chexDetail.setChex_BOFD_aba(result.getString("CHLOG_BOFD_ABA"));
				chexDetail.setChex_BOFD_date(result.getString("CHLOG_BOFD_DATE"));
				if (result.getString("CHLOG_EXTRA_1") == null) {
					chexDetail.setChex_extra_1(" ");
				} else {
					chexDetail.setChex_extra_1(result.getString("CHLOG_EXTRA_1"));
				}
				if (result.getString("CHLOG_EXTRA_2") == null) {
					chexDetail.setChex_extra_2(" ");
				} else {
					chexDetail.setChex_extra_2(result.getString("CHLOG_EXTRA_2"));
				}
				chexDetail.setChex_issue_date(result.getString("CHLOG_ISSUE_DATE"));
				chexDetail.setChex_payee(result.getString("CHLOG_PAYEE"));
				chexDetail.setChex_payee_addr1(result.getString("CHLOG_PAYEE_ADDR1"));
				chexDetail.setChex_payee_addr2(result.getString("CHLOG_PAYEE_ADDR2"));
				chexDetail.setChex_payee_addr3(result.getString("CHLOG_PAYEE_ADDR3"));
				chexDetail.setChex_comments(result.getString("CHLOG_COMMENTS"));
				chexDetail.setChex_reason_codes(result.getString("CHLOG_REASON_CODES"));
				chexDetail.setChex_check_status(result.getString("CHLOG_CHECK_STATUS"));
				chexDetail.setChex_amount_od(result.getString("CHLOG_AMOUNT_OD"));
				chexDetail.setChex_image(result.getString("CHLOG_IMAGE"));
				chexDetail.setChex_last_modified(result.getString("CHLOG_LAST_MODIFIED"));
				chexDetail.setChex_mod_user(result.getString("CHLOG_MOD_USER"));
				chexDetail.setChex_mod_func(result.getString("CHLOG_MOD_FUNC"));
				chexDetail.setChex_modparam();
				chexBeans.add(chexDetail);
				row_count++;
			}
			statement.close();
			result.close();
		}
		chexSelector.setCheckrows(chexBeans);
		return (row_count);
	}
	//
	// Generic Get Log Rows
	//
	public int GetChexLogRows(Connection dbConn, ChexSelector chexSelector)
			throws Exception {
		moduleName		= "GetChexLogRows: ";
		String dbUsed	= chexSelector.getDbUsed();
		// PrintLog("DBUsed "+dbUsed);
		if (param.equals("")) {
			// GetLogParams (chexSelector);
			param	= chexSelector.GetLogParams();
		}
		StringBuffer sql	= new StringBuffer();
		row_count			= 0;
		// ------------------- DO BOT REMOVE -------------------------
		// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
		// ---------------------------------------------------------------
		sql.setLength(0);
		sql.append("SELECT CHLOG_PROC_DATE, CHLOG_ORIG_ACCOUNT_NUM, ");
		sql.append("CHLOG_ORIG_CHECK_NUM, CHLOG_ACCOUNT_NUM, ");
		sql.append("CHLOG_CHECK_NUM, CHLOG_ROUTING_TRANSIT, ");
		sql.append("CHLOG_CHECK_CURRENCY, CHLOG_CHECK_AMOUNT, ");
		sql.append("CHLOG_PROC_CONTROL, CHLOG_EXT_PROC_CONTROL, ");
		sql.append("CHLOG_IMAGE_LOCATOR, CHLOG_UNIQUE_ISN, ");
		sql.append("CHLOG_ADDENDA_REC_FLAG, CHLOG_ORIG_INST_RTE, ");
		sql.append("CHLOG_ISN, CHLOG_BUDGET_ID, ");
		sql.append("CHLOG_RETURN_DATE, ");
		sql.append("CHLOG_RETURN_REASON, ");
		sql.append("CHLOG_RETURN_STATUS, ");
		sql.append("CHLOG_BOFD_ABA, ");
		sql.append("CHLOG_BOFD_DATE, ");
		sql.append("CHLOG_EXTRA_1, ");
		sql.append("CHLOG_EXTRA_2, ");
		sql.append("CHLOG_ISSUE_DATE, ");
		sql.append("CHLOG_PAYEE, ");
		sql.append("CHLOG_PAYEE_ADDR1, ");
		sql.append("CHLOG_PAYEE_ADDR2, ");
		sql.append("CHLOG_PAYEE_ADDR3, ");
		sql.append("CHLOG_COMMENTS, ");
		sql.append("CHLOG_REASON_CODES, ");
		sql.append("CHLOG_CHECK_STATUS, CHLOG_AMOUNT_OD, CHLOG_IMAGE, ");
		sql.append("CHLOG_LAST_MODIFIED, ");
		sql.append("CHLOG_MOD_USER, CHLOG_MOD_FUNC ");
		sql.append("FROM incl_chex_log ");
		sql.append(param);
		// PrintLog(" InclChexUtil: SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		//PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			ChexDetail chexDetail	= new ChexDetail();
			// Here add the fields tp the ChexDetail bean
			chexDetail.setDbUsed(dbUsed);
			chexDetail.setChex_proc_date(result.getString("CHLOG_PROC_DATE"));
			chexDetail.setChex_orig_account_num(result.getString("CHLOG_ORIG_ACCOUNT_NUM"));
			chexDetail.setChex_orig_check_num(result.getString("CHLOG_ORIG_CHECK_NUM"));
			chexDetail.setChex_account_num(result.getString("CHLOG_ACCOUNT_NUM"));
			chexDetail.setChex_check_num(result.getString("CHLOG_CHECK_NUM"));
			chexDetail.setChex_routing_transit(result.getString("CHLOG_ROUTING_TRANSIT"));
			chexDetail.setChex_check_amount(result.getString("CHLOG_CHECK_AMOUNT"));
			chexDetail.setChex_check_currency(result.getString("CHLOG_CHECK_CURRENCY"));
			chexDetail.setChex_proc_control(result.getString("CHLOG_PROC_CONTROL"));
			chexDetail.setChex_ext_proc_control(result.getString("CHLOG_EXT_PROC_CONTROL"));
			chexDetail.setChex_image_locator(result.getString("CHLOG_IMAGE_LOCATOR"));
			chexDetail.setChex_unique_isn(result.getString("CHLOG_UNIQUE_ISN"));
			chexDetail.setChex_addenda_rec_flag(result.getString("CHLOG_ADDENDA_REC_FLAG"));
			chexDetail.setChex_orig_inst_rte(result.getString("CHLOG_ORIG_INST_RTE"));
			chexDetail.setChex_isn(result.getString("CHLOG_ISN"));
			chexDetail.setChex_budget_id(result.getString("CHLOG_BUDGET_ID"));
			chexDetail.setChex_return_date(result.getString("CHLOG_RETURN_DATE"));
			chexDetail.setChex_return_reason(result.getString("CHLOG_RETURN_REASON"));
			chexDetail.setChex_return_status(result.getString("CHLOG_RETURN_STATUS"));
			chexDetail.setChex_BOFD_aba(result.getString("CHLOG_BOFD_ABA"));
			chexDetail.setChex_BOFD_date(result.getString("CHLOG_BOFD_DATE"));
			if (result.getString("CHLOG_EXTRA_1") == null) {
				chexDetail.setChex_extra_1(" ");
			} else {
				chexDetail.setChex_extra_1(result.getString("CHLOG_EXTRA_1"));
			}
			if (result.getString("CHLOG_EXTRA_2") == null) {
				chexDetail.setChex_extra_2(" ");
			} else {
				chexDetail.setChex_extra_2(result.getString("CHLOG_EXTRA_2"));
			}
			chexDetail.setChex_issue_date(result.getString("CHLOG_ISSUE_DATE"));
			chexDetail.setChex_payee(result.getString("CHLOG_PAYEE"));
			chexDetail.setChex_payee_addr1(result.getString("CHLOG_PAYEE_ADDR1"));
			chexDetail.setChex_payee_addr2(result.getString("CHLOG_PAYEE_ADDR2"));
			chexDetail.setChex_payee_addr3(result.getString("CHLOG_PAYEE_ADDR3"));
			chexDetail.setChex_comments(result.getString("CHLOG_COMMENTS"));
			chexDetail.setChex_reason_codes(result.getString("CHLOG_REASON_CODES"));
			chexDetail.setChex_check_status(result.getString("CHLOG_CHECK_STATUS"));
			chexDetail.setChex_amount_od(result.getString("CHLOG_AMOUNT_OD"));
			chexDetail.setChex_image(result.getString("CHLOG_IMAGE"));
			chexDetail.setChex_last_modified(result.getString("CHLOG_LAST_MODIFIED"));
			chexDetail.setChex_mod_user(result.getString("CHLOG_MOD_USER"));
			chexDetail.setChex_mod_func(result.getString("CHLOG_MOD_FUNC"));
			chexDetail.setChex_modparam();
			chexBeans.add(chexDetail);
			row_count++;
		}
		//PrintLog("Rows Read " + row_count);
		chexSelector.setCheckrows(chexBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	// This is called by Posipay/Stoppay maint function for updating the checks
	// after a Posipay/Stoppay has been added or modified.
	//
	public int InsertUpdateChex(Connection dbConn, String dbUsed,
					String acct_num, String check_num, String chex_status,
					String userId, String appl_date, String imageDir, String imageURL,
					int ins_or_upd, // 1 for insert or 2 for update
					String mod_func) throws Exception {
		moduleName	= "InsertUpdateChex 1: ";
		// PrintLog("DBUsed "+dbUsed+"  Appl_DATE: "+appl_date);
		ChexSelector chexSelector	= new ChexSelector();
		ChexDetail check_detail		= new ChexDetail();
		int ret_val = 0;
		chexSelector.setDbUsed(dbUsed);
		chexSelector.setAppl_date(appl_date);
		chexSelector.setImageURL(imageURL);
		chexSelector.setImageDir(imageDir);
		param	= " WHERE CHEX_ACCOUNT_NUM='" + acct_num.trim() + "' ";
		param	+= " AND CHEX_CHECK_NUM='" + check_num + "' ";
		if (chex_status.equals("E")) {
			param	+= " AND CHEX_CHECK_STATUS='" + chex_status + "'";
		}
		row_count	= GetChexRows(dbConn, chexSelector);
		//PrintLog("Check Rows -->> "+row_count+
		//				" Acct # "+acct_num+" Check #: "+check_num);
		ChexDetail chexDetail[]	= new ChexDetail[row_count];
		chexDetail				= chexSelector.getCheckrows();
		for (int idx = 0; idx <= chexDetail.length - 1; idx++) {
			// PrintLog("In ProcessLoop");
			check_detail	= chexDetail[idx];
			check_detail.setChex_mod_func(mod_func);
			check_detail.setChex_mod_user(userId);
			check_detail.setAppl_date(appl_date);
			ret_val			= InsertUpdateChex(dbConn, check_detail, ins_or_upd);
			if (ret_val == 0) {
				check_detail.setErrorVec("Inclearing Checks", "error.updating");
			}
		}
		return (1);
	}
	//
	// This is called by Account maint function for updating the checks
	// after an account has been added or modified.
	//
	public int InsertUpdateChex(Connection dbConn, String dbUsed,
								String acct_num, String bank_acct_num, String currCode,
								String appl_date, String userId, String mod_func)
			throws Exception {
		moduleName					= "InsertUpdateChex 2: ";
		// PrintLog(" Detail: DBUsed "+dbUsed);
		ChexSelector chexSelector	= new ChexSelector();
		ChexDetail check_detail		= new ChexDetail();
		;
		int ret_val					= 0;
		int ins_or_upd				= 2;
		chexSelector.setDbUsed(dbUsed);
		chexSelector.setAppl_date(appl_date);
		param		= " WHERE CHEX_ORIG_ACCOUNT_NUM='" + acct_num + "'";
		// if (chex_status.equals("E")) {
		param		+= " AND CHEX_CHECK_CURRENCY='" + currCode + "' ";
		param		+= "AND CHEX_CHECK_STATUS in ('R', 'E')";
		// }
		row_count = GetChexRows(dbConn, chexSelector);
		// PrintLog("Check Rows -->> "+row_count+"  Appl_DATE: "+appl_date);
		ChexDetail chexDetail[]	= new ChexDetail[row_count];
		chexDetail				= chexSelector.getCheckrows();
		for (int idx = 0; idx <= chexDetail.length - 1; idx++) {
			check_detail	= chexDetail[idx];
			check_detail.setChex_account_num(bank_acct_num);
			check_detail.setChex_mod_func(mod_func);
			check_detail.setChex_mod_user(userId);
			check_detail.setAppl_date(appl_date);
			ret_val			= InsertUpdateChex(dbConn, check_detail, ins_or_upd);
			if (ret_val == 0) {
				chexSelector.setErrorVec("Inclearing Checks", "error.updating");
			}
		}
		return (1);
	}
	//
	public int InsertUpdateChex(Connection dbConn, ChexDetail chexDetail,
			int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		// GenUtil gUtil = new GenUtil();
		moduleName = "InsertUpdateChex 3: ";
		// Need the acct, posi and stop beans to validate for limit
		// stop and posi pay
		//PrintLog("In Update Chex");
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		int ret_val			= 1; // Success
		int today_yyyy		= 0;
		int today_mm		= 0;
		int today_dd		= 0;
		int months			= 6;
		int months7			= 7;
		int daysCheckValid	= 0;
		int averageChecks	= 0;
		Long highChNum		= 0L;
		Long checkNumLong	= 0L;
		Long FraudLimit		= 0L;
		String dbTable		= "incl_chex";
		String logHist		= "";
		String six_months	= "";
		String s7Months		= "";
		String threeMonths	= "";
		String pospay_status= "U"; // Unmatched
		boolean pospay_update_flag		= false; // Initial false
		boolean	checkForDuplicates		= false;
		AccountSelector acctSelector	= new AccountSelector();
		PosipaySelector posiSelector	= new PosipaySelector();
		StoppaySelector stopSelector	= new StoppaySelector();
		AccountDetail acctDetail		= new AccountDetail();
		PosipayDetail posiDetail		= new PosipayDetail();
		StoppayDetail stopDetail		= new StoppayDetail();
		InclAcctUtil acUtil				= new InclAcctUtil();
		InclPosiUtil ppUtil				= new InclPosiUtil();
		InclStopUtil spUtil				= new InclStopUtil();
		chexDetail.clearNulls();
		// Here get all the fields and validate each aspect of the check.
		// 1. Check for the Limit
		// 2. Check for Stop payment
		// 3. If posipay required then check for existence of Posipay.
		//
		String dbUsed		= chexDetail.getDbUsed();
		String appl_date	= chexDetail.getAppl_date();
		//
		threeMonths		= ValiDate.subtractDays(appl_date, 124);
		try {
			highCheckNum.clear();
			highCheckNum		= HighCheckNum(dbConn, threeMonths.substring(0, 6));
		} catch (Throwable t) {
			PrintLog("Error Getting High Check Numbers ->" + t.toString());
			t.printStackTrace();
		}
		try {
			monthAverage.clear();
			monthAverage		= acUtil.GetMonthlyCount(dbConn);
		} catch (Throwable t) {
			PrintLog("Error Getting Monthly Average Check Numbers ->" + t.toString());
			t.printStackTrace();
		}
		// PrintLog("DBUsed "+dbUsed+"  Appl_DATE: >"+appl_date+"<");
		// PrintLog("PosiPay Appl_date "+appl_date);
		today_yyyy		= Integer.parseInt(appl_date.substring(0, 4));
		today_mm		= Integer.parseInt(appl_date.substring(4, 6));
		today_dd		= Integer.parseInt(appl_date.substring(6));
		six_months		= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months);
		s7Months		= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months7);
		logHist			= appl_date.substring(0, 6);
		// PrintLog(" Log Hist "+logHist);
		// PrintLog(" PosiPay Six Months "+six_months);
		String bankId					= chexDetail.getBankId();
		String chex_proc_date			= chexDetail.getChex_proc_date();
		String chex_orig_account_num	= chexDetail.getChex_orig_account_num().trim();
		String chex_orig_check_num		= chexDetail.getChex_orig_check_num();
		String chex_account_num			= chexDetail.getChex_account_num().trim();
		String chex_check_num			= chexDetail.getChex_check_num();
		String chex_routing_transit		= chexDetail.getChex_routing_transit();
		String chex_check_amount		= chexDetail.getChex_check_amount();
		double chex_check_amount_d		= Double.parseDouble(chex_check_amount);
		String chex_check_currency		= chexDetail.getChex_check_currency();
		String chex_proc_control		= chexDetail.getChex_proc_control();
		String chex_ext_proc_control	= chexDetail.getChex_ext_proc_control();
		String chex_image_locator		= chexDetail.getChex_image_locator();
		String chex_unique_isn			= chexDetail.getChex_unique_isn();
		String chex_addenda_rec_flag	= chexDetail.getChex_addenda_rec_flag();
		String chex_orig_inst_rte		= chexDetail.getChex_orig_inst_rte();
		String chex_isn					= chexDetail.getChex_isn();
		String chex_budget_id			= chexDetail.getChex_budget_id();
		String chex_return_date			= chexDetail.getChex_return_date();
		String chex_return_reason		= chexDetail.getChex_return_reason();
		String chex_return_status		= chexDetail.getChex_return_status();
		String chex_BOFD_aba			= chexDetail.getChex_BOFD_aba();
		String chex_BOFD_date			= chexDetail.getChex_BOFD_date();
		String chex_extra_1				= chexDetail.getChex_extra_1();
		String chex_extra_2				= chexDetail.getChex_extra_2();
		String chex_issue_date			= chexDetail.getChex_issue_date();
		String chex_payee				= chexDetail.getChex_payee();
		chex_payee						= chex_payee.replaceAll("'", "''");
		String chex_payee_addr1			= chexDetail.getChex_payee_addr1();
		chex_payee_addr1				= chex_payee_addr1.replaceAll("'", "''");
		String chex_payee_addr2			= chexDetail.getChex_payee_addr2();
		chex_payee_addr2				= chex_payee_addr2.replaceAll("'", "''");
		String chex_payee_addr3			= chexDetail.getChex_payee_addr3();
		chex_payee_addr3				= chex_payee_addr3.replaceAll("'", "''");
		String chex_comments			= chexDetail.getChex_comments();
		chex_comments					= chex_comments.replaceAll("'", "''");
		String chex_reason_codes		= "";
		String chex_check_status		= "";
		String chex_amount_od			= chexDetail.getChex_amount_od();
		if (chex_amount_od.equals("")) {
			chex_amount_od				= "0";
		}
		double chex_amount_od_d			= Double.parseDouble(chex_amount_od);
		String chexImage				= chexDetail.getChex_image();
		String chex_mod_user			= chexDetail.getChex_mod_user();
		String chex_mod_func			= chexDetail.getChex_mod_func();
		checkNumLong					= Long.parseLong(chex_check_num);
		//
		checkForDuplicates				= chexDetail.getCheckForDuplicates();
		//
		acctSelector.setDbUsed(dbUsed);
		//ret_val = acUtil.GetAccountRows(dbConn,	acctSelector, chex_account_num);
		//PrintLog("Acct_num "+chex_account_num);
		acctDetail	= acUtil.GetAccountRows(dbConn, chex_account_num);
		if (acctDetail == null) {
			chexDetail.setErrorVec("Inclearing Checks", "error.updating");
			//PrintLog("Acct_num "+chex_account_num);
			return (ret_val);
		} else {
			/*
			PrintLog("CheckAccountExists: "+chex_account_num);
			// Here check for Fraudulant checks based on the Monthly Average Checks the client issues.
			highChNum	= 0L;
			//Set<String> keys = chexCount.keySet();
			//for (String key: keys) {
			//    System.out.println("Check Count for "+key+" is: "+chexCount.get(key));
			//}
			averageChecks	= 0;
			if (monthAverage.containsKey(chex_account_num)) {
				averageChecks	= monthAverage.get(chex_account_num);
			}
			if (averageChecks > 0) {
				if (highCheckNum.containsKey(chex_account_num)) {
					highChNum	= highCheckNum.get(chex_account_num);
					PrintLog ("Bank Acct: " + chex_account_num + "\tHighest Check Number " + highChNum);
					FraudLimit	= highChNum + averageChecks;
				}
				PrintLog ("Bank Acct: "+chex_account_num+" Check Number: " + checkNumLong +
						  " averageChecks "+ averageChecks + 
						  " Fraud Limit: " + FraudLimit); 
				if (checkNumLong > FraudLimit) {
					chex_reason_codes	= " ";
					chex_check_status	= "F";
				}
			}
			*/
		}
		//PrintLog("Acct_num "+chex_account_num);
		// now that we have the account extract the fields.
		//acctDetail						= acctSelector.getArow();
		daysCheckValid					= Integer.parseInt(acctDetail.getAccount_dayscheckvalid());
		six_months						= ValiDate.subtractDays(appl_date, daysCheckValid);
		String acct_max_check_amt		= acctDetail.getAccount_max_check_amount();
		String acct_posi_pay_flag		= acctDetail.getAccount_posi_pay_flag();
		String acct_posi_pay_amt_min	= acctDetail.getAccount_posi_pay_amt_min();
		double acct_max_check_amt_d		= Double.parseDouble(acct_max_check_amt);
		double acct_posi_pay_amt_min_d	= Double.parseDouble(acct_posi_pay_amt_min);
		String acct_internal_external	= acctDetail.getAccount_int_ext();
		//
		// Here Check and see if the Check was cleared in the past by trying to
		// get it from the history tables.
		//
		retBool = ChexExistsInHistory (dbConn, chex_account_num, chex_check_num, chexDetail,
										s7Months);
		if (retBool == true) { // Check exists in history
			chex_reason_codes	+= "H";
			chex_check_status	= "R";
		} else {
			stopSelector.setDbUsed(dbUsed);
			ret_val		= spUtil.GetStoppayRows(dbConn, stopSelector,
												chex_account_num, chex_check_num);
			if (ret_val > 0) { // found Stop Payment request
				stopDetail						= stopSelector.getArow();
				String stopay_check_amount		= stopDetail.getStopay_check_amount();
				double stopay_check_amount_d	= Double.parseDouble(stopay_check_amount);
				// determine if it indeed a good stoppay (OK if stoppay amount is 0)
				// this is possible in the case of lost checks. The check owner does
				// not know what amount the lost checks will be made out for
				if (chex_check_amount_d == stopay_check_amount_d) {
					chex_reason_codes		+= "S";
					if (!chex_check_status.equals("E")) {
						chex_check_status	= "S";
					}
				} else {
					chex_reason_codes		+= "T";
					if (!chex_check_status.equals("E")) {
						chex_check_status	= "R";
					}
				}
			} else {
				// Check for Posi Pay
				// " "+PrintLog(" PosiPay Check "+acct_posi_pay_flag);
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// Posi pay flag = Y
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				if (acct_posi_pay_flag.equals("Y")) {
					//PrintLog(bankId + " PosiPay Yes -- Check ");
					posiSelector.setDbUsed(dbUsed);
					ret_val	= ppUtil.GetPosipayRows(dbConn, posiSelector,
													chex_account_num, chex_check_num, 
													pospay_status);
					// System.out.println(java.util.Calendar.getInstance().getTime()+PrintLog(
					// "PosiPay Payee "+ret_val);
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					// Found Posi pay
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					if (ret_val > 0) { // found Posi Payment Advice
						//PrintLog("PosiPay Yes -- Found");
						posiDetail						= posiSelector.getArow();
						String pospay_check_amount		= posiDetail.getPospay_check_amount();
						double pospay_check_amount_d	= Double.parseDouble(pospay_check_amount);
						// determine if it indeed a good Posipay
						if (chex_check_amount_d != pospay_check_amount_d) {
							chex_reason_codes += "Q";
							if (!chex_check_status.equals("E")) {
								chex_check_status	= "R";
							}
						} else if (chex_check_amount_d == pospay_check_amount_d) {
							chexDetail.setChex_payee(posiDetail.getPospay_payee());
							chexDetail.setChex_payee_addr1(posiDetail.getPospay_payee_addr1());
							chexDetail.setChex_payee_addr2(posiDetail.getPospay_payee_addr2());
							chexDetail.setChex_payee_addr3(posiDetail.getPospay_payee_addr3());
							chexDetail.setChex_issue_date(posiDetail.getPospay_issue_date());
							// chexDetail.setChex_extra_1(posiDetail.getPospay_swift_ref());
							chex_payee	= chexDetail.getChex_payee();
							// if (chex_payee == null) {
							// chex_payee = "";
							// }
							if (!chex_payee.equals("")) {
								chex_payee	= chex_payee.replaceAll("'", "''");
							}
							chex_payee_addr1	= chexDetail.getChex_payee_addr1();
							if (!chex_payee_addr1.equals("")) {
								chex_payee_addr1	= chex_payee_addr1.replaceAll("'","''");
							}
							chex_payee_addr2	= chexDetail.getChex_payee_addr2();
							if (!chex_payee_addr2.equals("")) {
								chex_payee_addr2	= chex_payee_addr2.replaceAll("'","''");
							}
							chex_payee_addr3	= chexDetail.getChex_payee_addr3();
							if (!chex_payee_addr3.equals("")) {
								chex_payee_addr3	= chex_payee_addr3.replaceAll("'","''");
							}
							chex_issue_date	= chexDetail.getChex_issue_date();
							chex_extra_1	= chexDetail.getChex_extra_1();
							// PrintLog("Posipay SW_REF "+ chex_extra_1);
							// PrintLog("Posipay SW_REF "+
							// PrintLog(posiDetail.getPospay_swift_ref());
							// PrintLog("==================================================");
							chex_reason_codes += " ";
							if (!chex_check_status.equals("E")) {
								/*
								if (bankId.equals("CBKNY")) {
									//PrintLog(" PosiPay Yes -- CBKNY Account type: " +
									//		 acct_internal_external);
									//
									// Commerzbank Custom Do no change -- UNCOMMENT
									// 7 LINES starting with //if FOR COMMERZBANK
									//
									//if (acct_internal_external.equals("I")) {
									//chex_check_status	= "Z";
									// } else if (chex_check_amount_d >=
									// acct_posi_pay_amt_min_d) {
									// chex_check_status = "A";
									//} else {
									//	chex_check_status = "Z";
									//}
									chex_check_status	= "Z";
								} else {
									//PrintLog(" PosiPay Yes -- Check Amount: " +
									//		 chex_check_amount_d + " Min PosiPay amoutn: " +
									//		 acct_posi_pay_amt_min_d);
								*/
								if ((acct_posi_pay_amt_min_d > 0) && 
									(chex_check_amount_d >= acct_posi_pay_amt_min_d)) {
								    chex_check_status   = "A";
								} else {
									pospay_update_flag	= true;
									chex_check_status   = "Z";
								}
								//
								if (!chex_issue_date.equals("0")) {
								    if (chex_issue_date.compareTo(six_months) < 0) {	// older than six months
								    	//PrintLog("PosiPay Six Months "+six_months+
								    	//		       " Issue date: "+chex_issue_date);
								    	chex_reason_codes	+= "D";
								    	chex_check_status	= "R";
								    }
								}
								if ((acct_max_check_amt_d > 0.00) &&
								    (chex_check_amount_d > acct_max_check_amt_d)) {
								    if (!chex_check_status.equals("P")) {
									chex_check_status	= "A";
									chex_reason_codes	+= "L";
									PrintLog("PosiPay Y Max Amt "+ acct_max_check_amt_d +
											   " Check Amt "+ chex_check_amount_d +
											   " Status "+ chex_check_status);
								    }
								}								
							}
							//PrintLog("PosiPay Yes -- Done STATUS " + chex_check_status);
						}
						// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
						// Did Not Find Posi pay
						// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					} else {
						// Posipay Flag=Y and Posipay not found
						//PrintLog("Posi Pay Flag Y and No PosiPay Found");
						//PrintLog("Found No Posi "+chex_check_num+" "+
						//		   chex_payee+" I/E: "+acct_internal_external);
						if (bankId.equals("CBKNY")) {
							if (acct_internal_external.equals("I")) {
								chex_reason_codes += " ";
								if (!chex_check_status.equals("E")) {
									chex_check_status = "Z";
								}
							} else {
								if (!chex_issue_date.equals("0") &&
									(!chex_payee.equals("") || !chex_payee.equals(" "))) {
									if (chex_check_amount_d >= acct_posi_pay_amt_min_d) {
										chex_reason_codes	+= "P";
										chex_check_status	= "R";
									} else {
										chex_reason_codes += " ";
										chex_check_status = "Z";
									}
								} else {
									chex_reason_codes += "P";
									chex_check_status = "R";
								}
							}
						} else if (!chex_issue_date.equals("0") &&
								  (!chex_payee.equals("") || !chex_payee.equals(" "))) {
							//chex_reason_codes	+= "P";
							//chex_check_status	= "R";
							if (chex_check_amount_d >= acct_posi_pay_amt_min_d) {
								chex_check_status = "A";
							} else {
								chex_check_status = "Z";
							}
						} else { // This can be turned off if the client does not
							// want to fully qualify the check
							//
							// Next three Instructions are from BNP Montreal --
							// COMMENT them for others
							//
							if (bankId.equals("BNPMO")) {
								chex_payee = "POSIPAY NOT PROVIDED";
								chex_issue_date = "XXXXXXXX";
								chex_reason_codes += "";
								if (!chex_check_status.equals("E")) {
									// chex_check_status = "Z";
									if (chex_check_amount_d >= acct_posi_pay_amt_min_d) {
										chex_check_status = "A";
									} else {
										// Added on 02/08/2020
										if (acct_max_check_amt_d > 0.00) {
											if (chex_check_amount_d > acct_max_check_amt_d) {
												chex_check_status = "A";
												chex_reason_codes += "L";
											}
										} else {
											chex_check_status = "Z";
										}
									}
								}
							} else {
								//
								// Next 6 lines of Instructions are others --
								// COMMENT them for BNP Montreal
								//
								chex_payee = " ";
								chex_issue_date = "0";
								chex_reason_codes += "P";
								if (!chex_check_status.equals("E")) {
									chex_check_status = "R";
								}
							}
						}
						if (!chex_check_status.equals("E") && !chex_check_status.equals("R")) {
							if (acct_max_check_amt_d > 0.00) {
								if (chex_check_amount_d > acct_max_check_amt_d) {
									chex_check_status = "A";
									chex_reason_codes += "L";
								}
							}
						}
					}
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					// Posi pay flag = N
					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				} else { // Posipay Flag = N
					PrintLog("Posi Flag N: "+chex_check_num+" "+chex_payee);
					if (acct_internal_external.equals("I")) {
						chex_reason_codes += " ";
						if (!chex_check_status.equals("E")) {
							if (bankId.equals("BNPMO")) {
								chex_payee = "POSIPAY NOT PROVIDED";
								chex_issue_date = "XXXXXXXX";
							}
							chex_check_status = "Z";
						}
					} else if (!chex_issue_date.equals("0")
							&& !chex_payee.equals("") && !chex_payee.equals(" ")) {
						chex_reason_codes += " ";
						if (!chex_check_status.equals("E")) {
							if (bankId.equals("BNPMO")) {
								chex_payee = "POSIPAY NOT PROVIDED";
								chex_issue_date = "XXXXXXXX";
							}
							chex_check_status = "Z";
						}
						// PrintLog("==================================================");
					} else { 
						// This can be turned if the client does not want to
						// fully qualify the check
						//chex_reason_codes += "P";
						//if (!chex_check_status.equals("E")) {
						//	chex_check_status = "R";
						//}
					}
					if (acct_max_check_amt_d > 0.00) {
						if (chex_check_amount_d > acct_max_check_amt_d) {
							chex_check_status = "A";
							chex_reason_codes = "L";
						} else {
							if (chex_check_status.equals("")) {
								chex_check_status = "Z";
								chex_reason_codes = " ";
							}
						}
					}
					PrintLog("Posi Flag N: chex_check_status: "+chex_check_status);
				}
			}
		}
		// PrintLog("Check Number: "+chex_check_num);
		if (chex_check_status.equals("")) {
			// set the status to Authorize -- all changes need to be Authorized
			if (ins_or_upd == 1) {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_check_status = "R";
					chex_reason_codes += "C";
					//PrintLog("Check Rejected -- Check Number is blank or zero");
				} else {
					chex_check_status = "Z";
				}
			} else {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_check_status = "R";
					chex_reason_codes += "C";
					//PrintLog("Check Rejected -- Check Number is blank or zero");
				} else {
					//PrintLog("Check To be Authorized -- Check Status was Blank");
					chex_check_status = "A";
				}
			}
		} else {
			if (chex_check_status.equals("E")) {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_reason_codes += "C";
				}
			} else {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_check_status = "R";
					chex_reason_codes += "C";
					//PrintLog("Check Rejected -- Check Number is blank or zero");
				}
			}
		}
		PrintLog("CheckAccountExists: "+chex_account_num);
		// Here check for Fraudulant checks based on the Monthly Average Checks the client issues.
		highChNum	= 0L;
		//Set<String> keys = chexCount.keySet();
		//for (String key: keys) {
		//    System.out.println("Check Count for "+key+" is: "+chexCount.get(key));
		//}
		if (!chex_check_status.equals("E") && !chex_check_status.equals("Z")) { //Mod on 5/25/2022
			highChNum		= 0L;
			averageChecks	= 0;
			if (monthAverage.containsKey(chex_account_num)) {
				averageChecks	= monthAverage.get(chex_account_num);
			}
			if (averageChecks > 0) {
				if (highCheckNum.containsKey(chex_account_num)) {
					highChNum	= highCheckNum.get(chex_account_num);
					PrintLog ("Bank Acct: " + chex_account_num + "\tHighest Check Number " + highChNum);
					FraudLimit	= highChNum + averageChecks;
				}
				PrintLog ("Bank Acct: "+chex_account_num+" Check Number: " + checkNumLong +
						" averageChecks "+ averageChecks + 
						" Fraud Limit: " + FraudLimit); 
				if (checkNumLong > FraudLimit) {
					chex_check_status	= "F";
				}
			}
		}
		if (checkForDuplicates == true) {
				PrintLog ("Check for Duplicates: "+ chex_account_num + " Check Number: " + checkNumLong);
				if (ChexExists(dbConn, dbTable, chex_account_num, chex_check_num, chexDetail) == true) {
				//chexDetail.setErrorVec("Inclearing Check", "error.field.duplicate");
				//if (!chex_check_status.equals("Z")) {
					chex_check_status	= "E";
				//}
				chex_reason_codes		+= "K";
			}
		}
		// Here determine if the Account Number was Modiifed
		// here we Insert or Update the Check row and insert a log row
		// PrintLog("Posi Flag N Check Num >"+chex_check_num+"< Payee >"+chex_payee+"<");
		dbConn.setAutoCommit(false);
		PrintLog("chex_check_status: "+chex_reason_codes + 
				 " chex_check_status: "+chex_check_status);
		if (ins_or_upd == 1) {
			// mod_func = "Load file";
			sql.setLength(0);
			sql.append("INSERT INTO incl_chex VALUES (");
			sql.append("'" + chex_proc_date + "', ");
			sql.append("'" + chex_orig_account_num + "', ");
			sql.append("'" + chex_orig_check_num + "', ");
			sql.append("'" + chex_account_num + "', ");
			sql.append("'" + chex_check_num + "', ");
			sql.append("'" + chex_routing_transit + "', ");
			sql.append("'" + chex_check_currency + "', ");
			sql.append("'" + chex_check_amount_d + "', ");
			sql.append("'" + chex_proc_control + "', ");
			sql.append("'" + chex_ext_proc_control + "', ");
			sql.append("'" + chex_image_locator + "', ");
			sql.append("'" + chex_unique_isn + "', ");
			sql.append("'" + chex_addenda_rec_flag + "', ");
			sql.append("'" + chex_orig_inst_rte + "', ");
			sql.append("'" + chex_isn + "', ");
			sql.append("'" + chex_budget_id + "', ");
			sql.append("'" + chex_return_date + "', ");
			sql.append("'" + chex_return_reason + "', ");
			sql.append("'" + chex_return_status + "', ");
			sql.append("'" + chex_BOFD_aba + "', ");
			sql.append("'" + chex_BOFD_date + "', ");
			sql.append("'" + chex_extra_1 + "', ");
			sql.append("'" + chex_extra_2 + "', ");
			sql.append("'" + chex_issue_date + "', ");
			sql.append("'" + chex_payee + "', ");
			sql.append("'" + chex_payee_addr1 + "', ");
			sql.append("'" + chex_payee_addr2 + "', ");
			sql.append("'" + chex_payee_addr3 + "', ");
			sql.append("'" + chex_comments + "', ");
			sql.append("'" + chex_reason_codes + "', ");
			sql.append("'" + chex_check_status + "', ");
			sql.append("'" + chex_amount_od_d + "', ");
			sql.append("'" + chexImage + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL,");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate,");
			}
			sql.append("'" + chex_mod_user + "', ");
			sql.append("'" + chex_mod_func + "')");
			// PrintLog("Chex Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Chex ->" + e.toString());
				PrintLog("Chex Insert SQL ---> " + sql);
				dbConn.rollback();
				chexDetail.setErrorVec("Inclearing Checks", "error.inserting");
				statement.close();
				return (0);
			}
		} else {
			// mod_func = "Modify Chex";
			sql.setLength(0);
			sql.append("update incl_chex ");
			sql.append("set CHEX_ACCOUNT_NUM='" + chex_account_num + "', ");
			sql.append("    CHEX_CHECK_NUM=" + chex_check_num + ", ");
			sql.append("    CHEX_CHECK_AMOUNT=" + chex_check_amount_d + ", ");
			sql.append("    CHEX_REASON_CODES='" + chex_reason_codes + "', ");
			sql.append("    CHEX_CHECK_STATUS='" + chex_check_status + "', ");
			sql.append("    CHEX_AMOUNT_OD=" + chex_amount_od_d + ", ");
			sql.append("    CHEX_ISSUE_DATE='" + chex_issue_date + "', ");
			sql.append("    CHEX_EXTRA_1='" + chex_extra_1 + "', ");
			sql.append("    CHEX_PAYEE='" + chex_payee + "', ");
			sql.append("    CHEX_PAYEE_ADDR1='" + chex_payee_addr1 + "', ");
			sql.append("    CHEX_PAYEE_ADDR2='" + chex_payee_addr2 + "', ");
			sql.append("    CHEX_PAYEE_ADDR3='" + chex_payee_addr3 + "', ");
			sql.append("    CHEX_COMMENTS='" + chex_comments + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHEX_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHEX_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHEX_MOD_USER='" + chex_mod_user + "', ");
			sql.append("    CHEX_MOD_FUNC='" + chex_mod_func + "' ");
			sql.append("    WHERE CHEX_ORIG_ACCOUNT_NUM='"
					+ chex_orig_account_num + "' AND ");
			sql.append("          CHEX_ORIG_CHECK_NUM='" + chex_orig_check_num
					+ "' AND ");
			sql.append("          CHEX_UNIQUE_ISN='" + chex_unique_isn + "'");
			//PrintLog("Chex Update SQL ---> " + sql);
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Chex ->" + e.toString());
				PrintLog("Chex Update SQL ---> " + sql);
				dbConn.rollback();
				chexDetail.setErrorVec("Inclearing Checks", "error.updating");
				statement.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO incl_chex_log_" + logHist + " VALUES (");
		sql.append("'" + chex_proc_date + "', ");
		sql.append("'" + chex_orig_account_num + "', ");
		sql.append("'" + chex_orig_check_num + "', ");
		sql.append("'" + chex_account_num + "', ");
		sql.append("'" + chex_check_num + "', ");
		sql.append("'" + chex_routing_transit + "', ");
		sql.append("'" + chex_check_currency + "', ");
		sql.append("'" + chex_check_amount_d + "', ");
		sql.append("'" + chex_proc_control + "', ");
		sql.append("'" + chex_ext_proc_control + "', ");
		sql.append("'" + chex_image_locator + "', ");
		sql.append("'" + chex_unique_isn + "', ");
		sql.append("'" + chex_addenda_rec_flag + "', ");
		sql.append("'" + chex_orig_inst_rte + "', ");
		sql.append("'" + chex_isn + "', ");
		sql.append("'" + chex_budget_id + "', ");
		sql.append("'" + chex_return_date + "', ");
		sql.append("'" + chex_return_reason + "', ");
		sql.append("'" + chex_return_status + "', ");
		sql.append("'" + chex_BOFD_aba + "', ");
		sql.append("'" + chex_BOFD_date + "', ");
		sql.append("'" + chex_extra_1 + "', ");
		sql.append("'" + chex_extra_2 + "', ");
		sql.append("'" + chex_issue_date + "', ");
		sql.append("'" + chex_payee + "', ");
		sql.append("'" + chex_payee_addr1 + "', ");
		sql.append("'" + chex_payee_addr2 + "', ");
		sql.append("'" + chex_payee_addr3 + "', ");
		sql.append("'" + chex_comments + "', ");
		sql.append("'" + chex_reason_codes + "', ");
		sql.append("'" + chex_check_status + "', ");
		sql.append("'" + chex_amount_od_d + "', ");
		sql.append("'" + chexImage + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + chex_mod_user + "', ");
		sql.append("'" + chex_mod_func + "')");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Chex Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
			dbConn.rollback();
			chexDetail.setErrorVec("Inclearing Checks Log", "error.inserting");
			statement.close();
			return (0);
		}
		if (pospay_update_flag == true) {
			int update_flag = 2;
			posiDetail.setPospay_status("M");
			ppUtil.InsertUpdatePosi(dbConn, posiDetail, chex_mod_user,
					update_flag); // 1 for insert or 2 for update
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		// result.close();
		// PrintLog("Reason Codes: "+chex_reason_codes+
		// "  Check Status: "+chex_check_status);
		chexDetail.setChex_reason_codes(chex_reason_codes);
		chexDetail.setChex_check_status(chex_check_status);
		return (1);
	}
	//
	public int AuthRejAllChex(Connection dbConn, String dbUsed,
			String applDate,
			// ActionErrors errors,
			String userId, int auth_rej_flag, String mod_func) throws Exception {
		moduleName = "AuthRejAllChex: ";
		ChexSelector chexSelector = new ChexSelector();
		// acctSelector.setDbUsed(dbUsed);
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT into incl_chex_log_" + applDate.substring(0, 6)
				+ " ");
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		if (auth_rej_flag == 1) {
			sql.append("'Z', ");
		} else {
			sql.append("'R', ");
		}
		sql.append("CHEX_AMOUNT_OD, ");
		sql.append("CHEX_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate,");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM incl_chex ");
		sql.append("    WHERE CHEX_CHECK_STATUS='A' AND ");
		sql.append("          CHEX_MOD_USER!='" + userId + "'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update incl_chex set ");
			if (auth_rej_flag == 1) {
				sql.append("CHEX_CHECK_STATUS='Z', ");
			} else {
				sql.append("CHEX_CHECK_STATUS='R', ");
			}
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHEX_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHEX_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHEX_MOD_USER='" + userId + "', ");
			sql.append("    CHEX_MOD_FUNC='" + mod_func + "'");
			sql.append("    WHERE CHEX_CHECK_STATUS='A' AND ");
			sql.append("          CHEX_MOD_USER!='" + userId + "'");
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				return (1);
			} catch (SQLException e) {
				PrintLog("Error Updating Chex ->" + e.toString());
				//
				// Remember to add chexSelector as a parameter to be passed
				// to trap the error messaeges
				//
				chexSelector.setErrorVec("Inclearing Checks", "error.updating");
				dbConn.rollback();
			}
		} catch (SQLException e) {
			PrintLog("Error Inserting Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			chexSelector.setErrorVec("Inclearing Checks Log", "error.inserting");
			dbConn.rollback();
		}
		statement.close();
		return (0);
	}
	//
	public int AuthRejectChex(Connection dbConn, ChexDetail chexDetail,
	// ActionErrors errors,
			String userId, int auth_rej_flag, String mod_func) throws Exception {
		moduleName = "AuthRejectChex: ";
		ChexSelector chexSelector = new ChexSelector();
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		chexDetail.clearNulls();
		String dbUsed = chexDetail.getDbUsed();
		String acct_num = chexDetail.getChex_account_num();
		String check_num = chexDetail.getChex_check_num();
		String check_unique_isn = chexDetail.getChex_unique_isn();
		String comments = chexDetail.getChex_comments();
		comments = comments.replaceAll("'", "''");
		String applDate = chexDetail.getChex_proc_date();
		// acctSelector.setDbUsed(dbUsed);
		sql.setLength(0);
		sql.append("INSERT into incl_chex_log_" + applDate.substring(0, 6)
				+ " ");
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		if (auth_rej_flag == 1) {
			sql.append("'Z', ");
		} else {
			sql.append("'R', ");
		}
		sql.append("CHEX_AMOUNT_OD, ");
		sql.append("CHEX_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM incl_chex ");
		sql.append("    WHERE CHEX_ACCOUNT_NUM='" + acct_num + "' AND ");
		sql.append("          CHEX_CHECK_NUM='" + check_num + "' AND ");
		sql.append("          CHEX_UNIQUE_ISN='" + check_unique_isn + "'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update incl_chex set ");
			if (auth_rej_flag == 1) {
				sql.append("CHEX_CHECK_STATUS='Z', ");
			} else {
				sql.append("CHEX_CHECK_STATUS='R', ");
			}
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHEX_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHEX_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHEX_COMMENTS='" + comments + "', ");
			sql.append("    CHEX_MOD_USER='" + userId + "', ");
			sql.append("    CHEX_MOD_FUNC='" + mod_func + "'");
			sql.append("    WHERE CHEX_ACCOUNT_NUM='" + acct_num + "' AND ");
			sql.append("          CHEX_CHECK_NUM='" + check_num + "' AND ");
			sql.append("          CHEX_UNIQUE_ISN='" + check_unique_isn + "'");
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				statement.close();
				return (1);
			} catch (SQLException e) {
				PrintLog("Error Updating  ->" + e.toString());
				//
				// Remember to add chexSelector as a parameter to be passed
				// to trap the error messaeges
				//
				chexSelector.setErrorVec("Inclearing Checks", "error.updating");
			}
		} catch (SQLException e) {
			PrintLog("Error Updating Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			chexSelector.setErrorVec("Inclearing Checks Log", "error.inserting");
		}
		statement.close();
		return (0);
	}
	//
	// Update Fraud/Genuine checks methods
	//
	public int GenuineFraudAllChex(Connection dbConn, String dbUsed,
			String applDate,
			String userId, int genFraudFlag, String mod_func) throws Exception {
		moduleName = "GenuineFraudAllChex: ";
		ChexSelector chexSelector = new ChexSelector();
		// acctSelector.setDbUsed(dbUsed);
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT into incl_chex_log_" + applDate.substring(0, 6) + " ");
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, ");
		sql.append("CHEX_RETURN_DATE, ");
		//sql.append("CHEX_RETURN_REASON, ");
		if (genFraudFlag == 1) {
			sql.append("' ', ");
		} else {
			sql.append("'N', ");
		}
		//sql.append("CHEX_RETURN_STATUS, ");
		if (genFraudFlag == 1) {
			sql.append("' ', ");
		} else {
			sql.append("'N', ");
		}
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		sql.append("'Z', ");
		sql.append("CHEX_AMOUNT_OD, ");
		sql.append("CHEX_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate,");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM incl_chex ");
		sql.append("    WHERE CHEX_CHECK_STATUS='F'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update incl_chex set ");
			if (genFraudFlag == 1) {
				sql.append("CHEX_RETURN_REASON=' ', ");
			} else {
				sql.append("CHEX_RETURN_REASON='N', ");
			}
			if (genFraudFlag == 1) {
				sql.append("CHEX_RETURN_STATUS=' ', ");
			} else {
				sql.append("CHEX_RETURN_STATUS='N', ");
			}
			sql.append("CHEX_CHECK_STATUS='Z', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHEX_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHEX_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHEX_MOD_USER='" + userId + "', ");
			sql.append("    CHEX_MOD_FUNC='" + mod_func + "'");
			sql.append("    WHERE CHEX_CHECK_STATUS='F'");
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				return (1);
			} catch (SQLException e) {
				PrintLog("Error Updating Chex ->" + e.toString());
				//
				// Remember to add chexSelector as a parameter to be passed
				// to trap the error messaeges
				//
				chexSelector.setErrorVec("Inclearing Checks", "error.updating");
				dbConn.rollback();
			}
		} catch (SQLException e) {
			PrintLog("Error Inserting Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			chexSelector.setErrorVec("Inclearing Checks Log", "error.inserting");
			dbConn.rollback();
		}
		statement.close();
		return (0);
	}
	//
	public int GenuineFraudChex(Connection dbConn, ChexDetail chexDetail,
			String userId, int genFraudFlag, String mod_func) throws Exception {
		moduleName	= "GenuineFraudChex: ";
		ChexSelector chexSelector	= new ChexSelector();
		StringBuffer sql			= new StringBuffer();
		Statement statement			= null;
		chexDetail.clearNulls();
		String dbUsed				= chexDetail.getDbUsed();
		String acct_num				= chexDetail.getChex_account_num();
		String check_num			= chexDetail.getChex_check_num();
		String check_unique_isn		= chexDetail.getChex_unique_isn();
		String comments				= chexDetail.getChex_comments();
		comments					= comments.replaceAll("'", "''");
		String applDate				= chexDetail.getChex_proc_date();
		// acctSelector.setDbUsed(dbUsed);
		sql.setLength(0);
		sql.append("INSERT into incl_chex_log_" + applDate.substring(0, 6) + " ");
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, ");
		sql.append("CHEX_RETURN_DATE, ");
		//sql.append("CHEX_RETURN_REASON, ");
		if (genFraudFlag == 1) {
			sql.append("' ', ");
		} else {
			sql.append("'N', ");
		}
		//sql.append("CHEX_RETURN_STATUS, ");
		if (genFraudFlag == 1) {
			sql.append("' ', ");
		} else {
			sql.append("'N', ");
		}
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		sql.append("'Z', ");
		//if (genFraudFlag == 1) {
		//	sql.append("'Z', ");
		//} else {
		//	sql.append("'R', ");
		//}
		sql.append("CHEX_AMOUNT_OD, ");
		sql.append("CHEX_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM incl_chex ");
		sql.append("    WHERE CHEX_ACCOUNT_NUM='" + acct_num + "' AND ");
		sql.append("          CHEX_CHECK_NUM='" + check_num + "' AND ");
		sql.append("          CHEX_UNIQUE_ISN='" + check_unique_isn + "'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update incl_chex set ");
			if (genFraudFlag == 1) {
				sql.append("CHEX_RETURN_REASON=' ', ");
			} else {
				sql.append("CHEX_RETURN_REASON='N', ");
			}
			if (genFraudFlag == 1) {
				sql.append("CHEX_RETURN_STATUS=' ', ");
			} else {
				sql.append("CHEX_RETURN_STATUS='N', ");
			}
			sql.append("CHEX_CHECK_STATUS='Z', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHEX_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHEX_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHEX_COMMENTS='" + comments + "', ");
			sql.append("    CHEX_MOD_USER='" + userId + "', ");
			sql.append("    CHEX_MOD_FUNC='" + mod_func + "'");
			sql.append("    WHERE CHEX_ACCOUNT_NUM='" + acct_num + "' AND ");
			sql.append("          CHEX_CHECK_NUM='" + check_num + "' AND ");
			sql.append("          CHEX_UNIQUE_ISN='" + check_unique_isn + "'");
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				statement.close();
				return (1);
			} catch (SQLException e) {
				PrintLog("Error Updating  ->" + e.toString());
				//
				// Remember to add chexSelector as a parameter to be passed
				// to trap the error messaeges
				//
				chexSelector.setErrorVec("Inclearing Checks", "error.updating");
			}
		} catch (SQLException e) {
			PrintLog("Error Updating Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			chexSelector.setErrorVec("Inclearing Checks Log", "error.inserting");
		}
		statement.close();
		return (0);
	}
	//
	// Here update the History record for return/Undo-reurn
	//
	public int ReturnUndoChex(Connection dbConn, ChexDetail chexDetail,
			String appl_date, String userId, String return_undo_flag,
			String mod_func) throws Exception {
		moduleName = "ReturnUndoChex: ";
		chexDetail.clearNulls();
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String dbUsed = chexDetail.getDbUsed();
		String check_proc_date = chexDetail.getChex_proc_date();
		// PrintLog("Proc Date>> "+check_proc_date);
		String acct_num = chexDetail.getChex_account_num();
		String check_num = chexDetail.getChex_check_num();
		String check_unique_isn = chexDetail.getChex_unique_isn();
		String comments = chexDetail.getChex_comments();
		String chex_return_date = chexDetail.getChex_return_date();
		String chex_return_reason = chexDetail.getChex_return_reason();
		String chex_return_status = chexDetail.getChex_return_status();
		String chex_bofd_aba = chexDetail.getChex_BOFD_aba();
		String chex_bofd_date = chexDetail.getChex_BOFD_date();
		if (return_undo_flag.equals("U")) {
			chex_return_date = " ";
			chex_return_reason = " ";
			chex_return_status = " ";
		} else {
			chex_return_date = appl_date;
			chex_return_status = "N";
		}
		//PrintLog("chex_BOFD_date >> " + chex_bofd_date);
		/*
		 * PrintLog("chex_ret_reason >> "+chex_ret_reason);
		 * PrintLog("chex_ret_reason1 >> "+chex_ret_reason1); if
		 * (chex_extra_1.length() > 1) { chex_extra_1 = chex_extra_1 +
		 * chex_ret_reason + chex_bofd; } if (return_undo_flag.equals("U")) {
		 * chex_return_status = " "; } else { chex_return_status = "N"; }
		 */
		comments = comments.replaceAll("'", "''");
		// acctSelector.setDbUsed(dbUsed);
		sql.setLength(0);
		sql.append("INSERT into incl_chex_log_"	+ check_proc_date.substring(0, 6) + " ");
		sql.append("SELECT CHIST_PROC_DATE, CHIST_ORIG_ACCOUNT_NUM, ");
		sql.append("CHIST_ORIG_CHECK_NUM, CHIST_ACCOUNT_NUM, ");
		sql.append("CHIST_CHECK_NUM, CHIST_ROUTING_TRANSIT, ");
		sql.append("CHIST_CHECK_CURRENCY, CHIST_CHECK_AMOUNT, ");
		sql.append("CHIST_PROC_CONTROL, CHIST_EXT_PROC_CONTROL, ");
		sql.append("CHIST_IMAGE_LOCATOR, CHIST_UNIQUE_ISN, ");
		sql.append("CHIST_ADDENDA_REC_FLAG, CHIST_ORIG_INST_RTE, ");
		sql.append("CHIST_ISN, CHIST_BUDGET_ID, ");
		sql.append("CHIST_RETURN_DATE, ");
		sql.append("CHIST_RETURN_REASON, ");
		sql.append("CHIST_RETURN_STATUS, ");
		sql.append("CHIST_BOFD_ABA, ");
		sql.append("CHIST_BOFD_DATE, ");
		sql.append("CHIST_EXTRA_1, ");
		sql.append("CHIST_EXTRA_2, CHIST_ISSUE_DATE, ");
		sql.append("CHIST_PAYEE, ");
		sql.append("CHIST_PAYEE_ADDR1, ");
		sql.append("CHIST_PAYEE_ADDR2, ");
		sql.append("CHIST_PAYEE_ADDR3, ");
		sql.append("CHIST_COMMENTS, ");
		sql.append("CHIST_REASON_CODES, ");
		sql.append("CHIST_CHECK_STATUS, ");
		sql.append("CHIST_AMOUNT_OD, ");
		sql.append("CHIST_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM incl_chex_m_" + check_proc_date.substring(0, 6));
		sql.append("    WHERE CHIST_ACCOUNT_NUM='" + acct_num + "' AND ");
		sql.append("          CHIST_CHECK_NUM='" + check_num + "' AND ");
		sql.append("          CHIST_UNIQUE_ISN='" + check_unique_isn + "'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update incl_chex_m_" + check_proc_date.substring(0, 6)
					+ " set ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHIST_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHIST_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHIST_BOFD_ABA='" + chex_bofd_aba + "', ");
			sql.append("    CHIST_BOFD_DATE='" + chex_bofd_date + "', ");
			sql.append("    CHIST_RETURN_DATE='" + chex_return_date + "', ");
			sql.append("    CHIST_RETURN_REASON='" + chex_return_reason + "', ");
			sql.append("    CHIST_RETURN_STATUS='" + chex_return_status + "', ");
			sql.append("    CHIST_COMMENTS='" + comments + "', ");
			sql.append("    CHIST_MOD_USER='" + userId + "', ");
			sql.append("    CHIST_MOD_FUNC='" + mod_func + "'");
			sql.append("    WHERE CHIST_ACCOUNT_NUM='" + acct_num + "' AND ");
			sql.append("          CHIST_CHECK_NUM='" + check_num + "' AND ");
			sql.append("          CHIST_UNIQUE_ISN='" + check_unique_isn + "'");
			//PrintLog("SQL >> "+sql);
			try {
				dbConn.setAutoCommit(false);
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				statement.close();
				return (1);
			} catch (SQLException e) {
				PrintLog("SQL >> " + sql);
				PrintLog("Error Updating  ->" + e.toString());
				chexDetail.setErrorVec("Inclearing Checks History", "error.updating");
			}
		} catch (SQLException e) {
			PrintLog("Error Updating Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			chexDetail.setErrorVec("Inclearing Checks Log", "error.inserting");
		}
		statement.close();
		return (0);
	}
	//
	// Here update the History record for returned check
	//
	public int ReturnChex(Connection dbConn, ChexDetail chexDetail,
			String userId, String mod_func) throws Exception {
		moduleName					= "ReturnChex: ";
		StringBuffer sql			= new StringBuffer();
		Statement statement			= null;
		String dbUsed				= chexDetail.getDbUsed();
		String check_proc_date		= chexDetail.getChex_proc_date();
		// PrintLog("Proc Date>> "+check_proc_date);
		String acct_num				= chexDetail.getChex_account_num();
		String check_num			= chexDetail.getChex_check_num();
		String check_unique_isn		= chexDetail.getChex_unique_isn();
		String comments				= chexDetail.getChex_comments();
		String chex_return_status	= chexDetail.getChex_return_status();
		// PrintLog("chex_ret_reason >> "+chex_ret_reason);
		// PrintLog("chex_ret_reason1 >> "+chex_ret_reason1);
		// if (chex_extra_1.length() > 1) {
		// chex_extra_1 = chex_extra_1 + "Y";
		// }
		chex_return_status			= "Y";
		comments					= comments.replaceAll("'", "''");
		// acctSelector.setDbUsed(dbUsed);
		sql.setLength(0);
		sql.append("INSERT into incl_chex_log_" + check_proc_date.substring(0, 6) + " ");
		sql.append("SELECT CHIST_PROC_DATE, CHIST_ORIG_ACCOUNT_NUM, ");
		sql.append("CHIST_ORIG_CHECK_NUM, CHIST_ACCOUNT_NUM, ");
		sql.append("CHIST_CHECK_NUM, CHIST_ROUTING_TRANSIT, ");
		sql.append("CHIST_CHECK_CURRENCY, CHIST_CHECK_AMOUNT, ");
		sql.append("CHIST_PROC_CONTROL, CHIST_EXT_PROC_CONTROL, ");
		sql.append("CHIST_IMAGE_LOCATOR, CHIST_UNIQUE_ISN, ");
		sql.append("CHIST_ADDENDA_REC_FLAG, CHIST_ORIG_INST_RTE, ");
		sql.append("CHIST_ISN, CHIST_BUDGET_ID, ");
		sql.append("CHIST_RETURN_DATE, ");
		sql.append("CHIST_RETURN_REASON, ");
		sql.append("CHIST_RETURN_STATUS, ");
		sql.append("CHIST_BOFD_ABA, ");
		sql.append("CHIST_BOFD_DATE, ");
		sql.append("CHIST_EXTRA_1, ");
		sql.append("CHIST_EXTRA_2, CHIST_ISSUE_DATE, ");
		sql.append("CHIST_PAYEE, ");
		sql.append("CHIST_PAYEE_ADDR1, ");
		sql.append("CHIST_PAYEE_ADDR2, ");
		sql.append("CHIST_PAYEE_ADDR3, ");
		sql.append("CHIST_COMMENTS, ");
		sql.append("CHIST_REASON_CODES, ");
		sql.append("CHIST_CHECK_STATUS, ");
		sql.append("CHIST_AMOUNT_OD, ");
		sql.append("CHIST_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM incl_chex_m_" + check_proc_date.substring(0, 6));
		sql.append("    WHERE CHIST_ACCOUNT_NUM='" + acct_num + "' AND ");
		sql.append("          CHIST_CHECK_NUM='" + check_num + "' AND ");
		sql.append("          CHIST_UNIQUE_ISN='" + check_unique_isn + "'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update incl_chex_m_" + check_proc_date.substring(0, 6) 	+ " set ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHIST_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHIST_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHIST_RETURN_STATUS='" + chex_return_status + "', ");
			sql.append("    CHIST_MOD_USER='" + userId + "', ");
			sql.append("    CHIST_MOD_FUNC='" + mod_func + "'");
			sql.append("    WHERE CHIST_ACCOUNT_NUM='" + acct_num + "' AND ");
			sql.append("          CHIST_CHECK_NUM='" + check_num + "' AND ");
			sql.append("          CHIST_UNIQUE_ISN='" + check_unique_isn + "'");
			//PrintLog("SQL >> "+sql);
			try {
				dbConn.setAutoCommit(false);
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				statement.close();
				return (1);
			} catch (SQLException e) {
				PrintLog("SQL >> " + sql);
				PrintLog("Error Updating  ->" + e.toString());
				chexDetail.setErrorVec("Inclearing Checks History", "error.updating");
			}
		} catch (SQLException e) {
			PrintLog("Error Updating Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			chexDetail.setErrorVec("Inclearing Checks Log", "error.inserting");
		}
		statement.close();
		return (0);
	}
	//
	
	public int ExtractChexRows(Connection dbConn, ChexSelector chexSelector)
			throws Exception {
		moduleName = "ExtractChexRows: ";
		SysadControlUtil ctlUtil = new SysadControlUtil();
		ControlSelector ctlSelector = new ControlSelector();
		ControlDetail ctlDetail = new ControlDetail();
		int rowCount = chexSelector.getDetail_count();
		ChexDetail chexDetail[] = new ChexDetail[rowCount];
		ChexDetail cD = new ChexDetail();
		String extractFileName = "";
		String fileTime = "";
		DateFormat newFmt2 = null;
		String outputPath = "";
		String outputChex = "";
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
		extractFileName = outputPath + "ExtractChecks_" + fileTime;
		//PrintLog("Extract File Name -> " + extractFileName);
		outExtract = new FileOutputStream(extractFileName + "_temp");
		psExtract = new PrintStream(outExtract);
		//
		chexDetail = chexSelector.getCheckrows();
		outputChex = ("Account Number" + "\t" + 
						"Check Number" + "\t" +
						"Amount" + "\t" + 
						"Date Processed" + "\t" + 
						"Issue Date" + "\t" +
						"Payee" + "\t" + 
						"Payee Address Line 1" + "\t" +
						"Payee Address Line 2" + "\t" + 
						"Payee Address Line 3" + "\t" +
						"Status");
		newText = outputChex.getBytes();
		outExtract.write(newText, 0, outputChex.length());
		psExtract.println();
		outputChex = "";
		for (int idx = 0; idx <= chexDetail.length - 1; idx++) {
			cD = chexDetail[idx];
			// Here get the fields from the ChexDetail bean
			String chist_account_num = cD.getChex_account_num();
			String chist_check_num = cD.getChex_check_num();
			// String chist_currency = cD.getChex_currency();
			String chist_check_amount = cD.getChex_check_amount();
			String chist_issue_date = cD.getChex_issue_date_disp();
			String chist_payee = cD.getChex_payee();
			String chist_payee_addr1 = cD.getChex_payee_addr1();
			String chist_payee_addr2 = cD.getChex_payee_addr2();
			String chist_payee_addr3 = cD.getChex_payee_addr3();
			String chist_proc_date = cD.getChex_proc_date_disp();
			String chist_check_status = cD.getChex_check_status();
			outputChex = (chist_account_num + "\t" +
						  chist_check_num + "\t" +
						  chist_check_amount + "\t" + 
						  chist_proc_date + "\t" +
						  chist_issue_date + "\t" + 
						  chist_payee + "\t" +
						  chist_payee_addr1 + "\t" + 
						  chist_payee_addr2 + "\t" +
						  chist_payee_addr3 + "\t" + 
						  chist_check_status);
			newText = outputChex.getBytes();
			outExtract.write(newText, 0, outputChex.length());
			psExtract.println();
			outputChex = "";
		}
		outExtract.close();
		//PrintLog("Will Rename " + extractFileName + "_temp to "
		//		+ extractFileName + ".xls");
		File extractFile = new File(extractFileName + "_temp");
		extractFile.renameTo(new File(extractFileName + ".xls"));
		return (row_count);
	}
	//
	public int InsertUpdateDeps(Connection dbConn, ChexDetail chexDetail,
			int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		// GenUtil gUtil = new GenUtil();
		moduleName = "InsertUpdateDeps: ";
		// Need the acct, posi and stop beans to validate for limit
		// stop and posi pay
		// PrintLog("In Update Chex");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		int ret_val = 1; // Success
		String logHist = "";
		AccountSelector acctSelector = new AccountSelector();
		InclAcctUtil acUtil = new InclAcctUtil();
		//
		String dbUsed = chexDetail.getDbUsed();
		String appl_date = chexDetail.getAppl_date();
		// six_months = ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,
		// months);
		logHist = appl_date.substring(0, 6);
		String chex_proc_date = chexDetail.getChex_proc_date();
		String chex_orig_account_num = chexDetail.getChex_orig_account_num()
				.trim();
		String chex_orig_check_num = chexDetail.getChex_orig_check_num();
		String chex_account_num = chexDetail.getChex_account_num().trim();
		String chex_check_num = chexDetail.getChex_check_num();
		String chex_routing_transit = chexDetail.getChex_routing_transit();
		String chex_check_amount = chexDetail.getChex_check_amount();
		;
		double chex_check_amount_d = Double.parseDouble(chex_check_amount);
		String chex_check_currency = chexDetail.getChex_check_currency();
		String chex_proc_control = " ";
		String chex_ext_proc_control = " ";
		String chex_image_locator = " ";
		String chex_unique_isn = chexDetail.getChex_unique_isn();
		String chex_addenda_rec_flag = " ";
		String chex_orig_inst_rte = " ";
		String chex_isn = " ";
		String chex_budget_id = " ";
		String chex_return_date = chexDetail.getChex_return_date();
		String chex_return_reason = chexDetail.getChex_return_reason();
		String chex_return_status = chexDetail.getChex_return_status();
		String chex_BOFD_aba = chexDetail.getChex_BOFD_aba();
		String chex_BOFD_date = chexDetail.getChex_BOFD_date();
		String chex_extra_1 = chexDetail.getChex_extra_1();
		String chex_extra_2 = " ";
		String chex_issue_date = chexDetail.getChex_issue_date();
		String chex_payee = chexDetail.getChex_payee();
		chex_payee = chex_payee.replaceAll("'", "''");
		String chex_payee_addr1 = " ";
		String chex_payee_addr2 = " ";
		String chex_payee_addr3 = " ";
		String chex_comments = chexDetail.getChex_comments();
		chex_comments = chex_comments.replaceAll("'", "''");
		String chex_reason_codes = " ";
		String chex_check_status = " ";
		String chex_amount_od = chexDetail.getChex_amount_od();
		if (chex_amount_od.equals("")) {
			chex_amount_od = "0";
		}
		double chex_amount_od_d = Double.parseDouble(chex_amount_od);
		String chexImage = chexDetail.getChex_image();
		String chex_mod_user = chexDetail.getChex_mod_user();
		String chex_mod_func = chexDetail.getChex_mod_func();
		//
		acctSelector.setDbUsed(dbUsed);
		ret_val = acUtil.GetAccountRows(dbConn,
		// dummy_list,
				acctSelector, chex_account_num);
		if (ret_val == 0) {
			chexDetail.setErrorVec("Deposit Checks", "error.updating");
			// PrintLog("Acct_num "+chex_account_num);
			return (ret_val);
		}
		// now that we have the account extract the fields.
		// acctDetail = acctSelector.getArow();
		//
		// Here Check and see if the Check was cleared in the past by trying to
		// get it from the history tables.
		//
		// ret_bool = ChexExistsInHistory (dbConn,
		// dbUsed,
		// chex_account_num,
		// chex_check_num,
		// errors);
		// if (ret_bool == true) { // Check exists in history
		// chex_reason_codes += "H";
		// chex_check_status = "R";
		// } else {
		// }
		// PrintLog("Check Number: "+chex_check_num);
		if (chex_check_status.equals("")) {
			// set the status to Authorize -- all changes need to be Authorized
			if (ins_or_upd == 1) {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_check_status = "R";
					chex_reason_codes += "C";
					//PrintLog("Check Rejected -- Check Number is blank or zero");
				} else {
					chex_check_status = "Z";
				}
			} else {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_check_status = "R";
					chex_reason_codes += "C";
					//PrintLog("Check Rejected -- Check Number is blank or zero");
				} else {
					//PrintLog("Check To be Authorized -- Check Status was Blank");
					chex_check_status = "A";
				}
			}
		} else {
			if (chex_check_status.equals("E")) {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_reason_codes += "C";
				}
			} else {
				if (Long.parseLong(chex_check_num) == 0) {
					chex_check_status = "R";
					chex_reason_codes += "C";
					//PrintLog("Check Rejected -- Check Number is blank or zero");
				}
			}
		}
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			sql.setLength(0);
			sql.append("INSERT INTO deps_chex VALUES (");
			sql.append("'" + chex_proc_date + "', ");
			sql.append("'" + chex_orig_account_num + "', ");
			sql.append("'" + chex_orig_check_num + "', ");
			sql.append("'" + chex_account_num + "', ");
			sql.append("'" + chex_check_num + "', ");
			sql.append("'" + chex_routing_transit + "', ");
			sql.append("'" + chex_check_currency + "', ");
			sql.append("'" + chex_check_amount_d + "', ");
			sql.append("'" + chex_proc_control + "', ");
			sql.append("'" + chex_ext_proc_control + "', ");
			sql.append("'" + chex_image_locator + "', ");
			sql.append("'" + chex_unique_isn + "', ");
			sql.append("'" + chex_addenda_rec_flag + "', ");
			sql.append("'" + chex_orig_inst_rte + "', ");
			sql.append("'" + chex_isn + "', ");
			sql.append("'" + chex_budget_id + "', ");
			sql.append("'" + chex_return_date + "', ");
			sql.append("'" + chex_return_reason + "', ");
			sql.append("'" + chex_return_status + "', ");
			sql.append("'" + chex_BOFD_aba + "', ");
			sql.append("'" + chex_BOFD_date + "', ");
			sql.append("'" + chex_extra_1 + "', ");
			sql.append("'" + chex_extra_2 + "', ");
			sql.append("'" + chex_issue_date + "', ");
			sql.append("'" + chex_payee + "', ");
			sql.append("'" + chex_payee_addr1 + "', ");
			sql.append("'" + chex_payee_addr2 + "', ");
			sql.append("'" + chex_payee_addr3 + "', ");
			sql.append("'" + chex_comments + "', ");
			sql.append("'" + chex_reason_codes + "', ");
			sql.append("'" + chex_check_status + "', ");
			sql.append("'" + chex_amount_od_d + "', ");
			sql.append("'" + chexImage + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL,");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate,");
			}
			sql.append("'" + chex_mod_user + "', ");
			sql.append("'" + chex_mod_func + "')");
			// PrintLog("Chex Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Chex ->" + e.toString());
				PrintLog("Chex Insert SQL ---> " + sql);
				dbConn.rollback();
				chexDetail.setErrorVec("Deposit Checks", "error.inserting");
				statement.close();
				return (0);
			}
		} else {
			// mod_func = "Modify Chex";
			sql.setLength(0);
			sql.append("update deps_chex ");
			sql.append("set CHEX_ACCOUNT_NUM='" + chex_account_num + "', ");
			sql.append("    CHEX_CHECK_NUM=" + chex_check_num + ", ");
			sql.append("    CHEX_CHECK_AMOUNT=" + chex_check_amount_d + ", ");
			sql.append("    CHEX_REASON_CODES='" + chex_reason_codes + "', ");
			sql.append("    CHEX_CHECK_STATUS='" + chex_check_status + "', ");
			sql.append("    CHEX_AMOUNT_OD=" + chex_amount_od_d + ", ");
			sql.append("    CHEX_ISSUE_DATE='" + chex_issue_date + "', ");
			sql.append("    CHEX_EXTRA_1='" + chex_extra_1 + "', ");
			sql.append("    CHEX_PAYEE='" + chex_payee + "', ");
			sql.append("    CHEX_PAYEE_ADDR1='" + chex_payee_addr1 + "', ");
			sql.append("    CHEX_PAYEE_ADDR2='" + chex_payee_addr2 + "', ");
			sql.append("    CHEX_PAYEE_ADDR3='" + chex_payee_addr3 + "', ");
			sql.append("    CHEX_COMMENTS='" + chex_comments + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    CHEX_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    CHEX_LAST_MODIFIED=sysdate, ");
			}
			sql.append("    CHEX_MOD_USER='" + chex_mod_user + "', ");
			sql.append("    CHEX_MOD_FUNC='" + chex_mod_func + "' ");
			sql.append("    WHERE CHEX_ORIG_ACCOUNT_NUM='"
					+ chex_orig_account_num + "' AND ");
			sql.append("          CHEX_ORIG_CHECK_NUM='" + chex_orig_check_num
					+ "' AND ");
			sql.append("          CHEX_UNIQUE_ISN='" + chex_unique_isn + "'");
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Chex ->" + e.toString());
				PrintLog("Chex Update SQL ---> " + sql);
				dbConn.rollback();
				chexDetail.setErrorVec("Deposit Checks", "error.updating");
				statement.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO incl_chex_log_" + logHist + " VALUES (");
		sql.append("'" + chex_proc_date + "', ");
		sql.append("'" + chex_orig_account_num + "', ");
		sql.append("'" + chex_orig_check_num + "', ");
		sql.append("'" + chex_account_num + "', ");
		sql.append("'" + chex_check_num + "', ");
		sql.append("'" + chex_routing_transit + "', ");
		sql.append("'" + chex_check_currency + "', ");
		sql.append("'" + chex_check_amount_d + "', ");
		sql.append("'" + chex_proc_control + "', ");
		sql.append("'" + chex_ext_proc_control + "', ");
		sql.append("'" + chex_image_locator + "', ");
		sql.append("'" + chex_unique_isn + "', ");
		sql.append("'" + chex_addenda_rec_flag + "', ");
		sql.append("'" + chex_orig_inst_rte + "', ");
		sql.append("'" + chex_isn + "', ");
		sql.append("'" + chex_budget_id + "', ");
		sql.append("'" + chex_return_date + "', ");
		sql.append("'" + chex_return_reason + "', ");
		sql.append("'" + chex_return_status + "', ");
		sql.append("'" + chex_BOFD_aba + "', ");
		sql.append("'" + chex_BOFD_date + "', ");
		sql.append("'" + chex_extra_1 + "', ");
		sql.append("'" + chex_extra_2 + "', ");
		sql.append("'" + chex_issue_date + "', ");
		sql.append("'" + chex_payee + "', ");
		sql.append("'" + chex_payee_addr1 + "', ");
		sql.append("'" + chex_payee_addr2 + "', ");
		sql.append("'" + chex_payee_addr3 + "', ");
		sql.append("'" + chex_comments + "', ");
		sql.append("'" + chex_reason_codes + "', ");
		sql.append("'" + chex_check_status + "', ");
		sql.append("'" + chex_amount_od_d + "', ");
		sql.append("'" + chexImage + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + chex_mod_user + "', ");
		sql.append("'" + chex_mod_func + "')");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting incl Chex Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
			dbConn.rollback();
			chexDetail.setErrorVec("Deposit Log", "error.inserting");
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		chexDetail.setChex_reason_codes(chex_reason_codes);
		chexDetail.setChex_check_status(chex_check_status);
		return (1);
	}
}
