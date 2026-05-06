package com.webfiche.checkpoint.sysadmin.service;

import java.sql.*;
import java.util.*;
//import org.apache.struts.action.*;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.*;

/**
 * <strong></strong> is a utility class to provide methods to access .and
 * manipilate the database table.
 *
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
@Service
public class SysadCalendarUtil {
	private String className = "> SysadCalendarUtil.";
	private String moduleName = "";
	private String userId = "";
	String fromYear;
	String toYear;
	String fromCurr;
	String toCurr;
	ArrayList<String> paramArray;
	String param = "";
	int row_count = 0;

	//
	public SysadCalendarUtil() {
	}

	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + userId + " - " + logMsg);
	}

	//
	// The following Methods are specialised for each of the incl tables.
	// The first is a set of methods for specific purposes;
	// 1. Get the accounts number colums of every row in the incl_accounts table
	// for the Accounts Inquiry/Maintenance selection table.
	//
	public void GetCalendarList(Connection dbConn, CalendarSelector calSelector)
			throws Exception {
		moduleName = "GetHolidayYearList: ";
		String tempYear = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		calSelector.clearLists();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT HOLIDAYS_YEAR_CURR from holidays"
				+ " ORDER by HOLIDAYS_YEAR_CURR desc");
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());

		while (result.next()) {
			// PrintLog("Execute In While");
			tempYear = result.getString("HOLIDAYS_YEAR_CURR");
			// PrintLog("Cal Year Curr: "+tempYear);
			calSelector.setYearList(tempYear);
			calSelector.setCurrList(tempYear.substring(4));
		}
		statement.close();
		result.close();
	}

	//
	public java.util.ArrayList<String> GetHolidayYearList(Connection dbConn)
			throws Exception {
		moduleName = "GetHolidayYearList: ";
		String temp_year = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> holi_list = new ArrayList<String>();
		holi_list.clear();
		StringBuffer sql = new StringBuffer();
		// sql.append("SELECT HOLIDAYS_YEAR, HOLIDAYS_CURRENCY from holidays"+
		// " ORDER by HOLIDAYS_YEAR, HOLIDAYS_CURRENCY");
		sql.append("SELECT DISTINCT HOLIDAYS_YEAR_CURR from holidays"
				+ " ORDER by HOLIDAYS_YEAR_CURR");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp_year = result.getString("HOLIDAYS_YEAR_CURR");
			// temp_curr = result.getString("HOLIDAYS_CURR");
			// holi_list.add(temp_year+temp_curr);
			holi_list.add(temp_year);
		}
		statement.close();
		result.close();
		return (holi_list);
	}

	//
	public java.util.ArrayList<String> GetCalendarLogYearList(Connection dbConn)
			throws Exception {
		moduleName = "GetCalendarLogYearList: ";
		String temp_year = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> holi_list = new ArrayList<String>();
		holi_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT HDLOG_YEAR_CURR from holidays_log "
				+ "ORDER by HDLOG_YEAR_CURR");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			PrintLog("Execute In While");
			// temp_year = result.getString("HDLOG_YEAR");
			result.getString("HDLOG_YEAR_CURR");
			// temp_curr = result.getString("HDLOG_CURRENCY");
			// holi_list.add(temp_year+temp_curr);
			holi_list.add(temp_year);
		}
		statement.close();
		result.close();
		return (holi_list);
	}

	//
	public boolean CurrencyExists(Connection dbConn, String holi_year_curr)
	// ,
	// CalendarSelector calSelector)
			throws Exception {
		//
		moduleName = "> CalendarUtil.CurrencyExists: ";
		StringBuffer sql = new StringBuffer();
		boolean curr_exists = false;
		String holi_curr = "";
		//
		// Check if the Currency Exists
		//
		if (holi_year_curr.length() == 7) {
			holi_curr = holi_year_curr.substring(4, 7);
		}
		sql.append("SELECT CURR_CODE from currency ");
		param = "WHERE CURR_CODE='" + holi_curr + "' ";
		sql.append(param);
		// PrintLog("Check for Currency "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("CURR_CODE");
			curr_exists = true;
		}
		if (curr_exists == false) {
			return (false);
		}
		statement.close();
		result.close();
		return (true);
	}

	//
	public boolean HolidayExists(Connection dbConn, String holi_year_curr) // ,
			// CalendarSelector calSelector)
			throws Exception {
		//
		moduleName = "HolidayExists: ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT HOLIDAYS_YEAR_CURR from holidays ");
		param = "WHERE HOLIDAYS_YEAR_CURR='" + holi_year_curr + "' ";
		sql.append(param);
		// PrintLog("Holidays Exist SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("HOLIDAYS_YEAR_CURR");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}

	//
	// Called by Modify needs Lock
	//
	public int GetHolidayRows(Connection dbConn, CalendarSelector calSelector,
			String holi_year_curr)
		// String holi_curr)
			throws Exception {
		// Called by the modify module with Year and Currency
		// --------------------------------------------------
		moduleName = "GetHolidayRows1: ";
		String accessFlag = calSelector.getAccessFlag();
		String dbUsed = calSelector.getDbUsed();
		param = "WHERE HOLIDAYS_YEAR_CURR='" + holi_year_curr + "'";
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
		row_count = GetHolidayRows(dbConn, calSelector);
		return (row_count);
	}

	//
	public int GetHolidayRows(Connection dbConn, CalendarSelector calSelector)
			throws Exception {
		moduleName = "GetHolidayRows2: ";
		String temp;
		if (param.equals(""))
			// GetParams (calSelector);
			param = calSelector.GetParams();
		StringBuffer sql = new StringBuffer();
		ArrayList<CalendarDetail> holiBeans = new ArrayList<CalendarDetail>();
		row_count = 0;
		sql.append("SELECT HOLIDAYS_YEAR_CURR, HOLIDAYS_DATES, ");
		sql.append("HOLIDAYS_LAST_MODIFIED, ");
		sql.append("HOLIDAYS_MOD_USER, HOLIDAYS_MOD_FUNC ");
		sql.append("FROM holidays ");
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
			CalendarDetail calDetail = new CalendarDetail();
			// Here add the fields tp the HolidaysDetail bean
			temp = result.getString("HOLIDAYS_YEAR_CURR");
			calDetail.setHdYearCurr(temp);
			calDetail.setHdYear(temp.substring(0, 4));
			calDetail.setHdCurr(temp.substring(4, 7));
			calDetail.setHoliday_dates(result.getString("HOLIDAYS_DATES"));
			calDetail.setHd_last_modified(result
					.getString("HOLIDAYS_LAST_MODIFIED"));
			calDetail.setHd_mod_user(result.getString("HOLIDAYS_MOD_USER"));
			calDetail.setHd_mod_func(result.getString("HOLIDAYS_MOD_FUNC"));
			calDetail.setHd_modparam();
			holiBeans.add(calDetail);
			row_count++;
		}
		calSelector.setCalendarrows(holiBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	//
	public int GetCalendarLogRows(Connection dbConn,
			CalendarSelector calSelector) throws Exception {
		moduleName = "GetHolidayLogRows: ";
		// ArrayList acct_rows = new ArrayList();
		ArrayList<CalendarDetail> holiBeans = new ArrayList<CalendarDetail>();
		if (param.equals(""))
			// GetLogParams (calSelector);
			param = calSelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT HDLOG_YEAR_CURR, HDLOG_DATE, ");
		sql.append("HDLOG_LAST_MODIFIED, ");
		sql.append("HDLOG_MOD_USER, HDLOG_MOD_FUNC ");
		sql.append("FROM holidays_log ");
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
			CalendarDetail calDetail = new CalendarDetail();
			// Here add the fields tp the HolidaysDetail bean
			calDetail.setHdYear(result.getString("HDLOG_YEAR_CURR"));
			calDetail.setHoliday_dates(result.getString("HDLOG_DATE"));
			calDetail.setHd_last_modified(result
					.getString("HDLOG_LAST_MODIFIED"));
			calDetail.setHd_mod_user(result.getString("HDLOG_MOD_USER"));
			calDetail.setHd_mod_func(result.getString("HDLOG_MOD_FUNC"));
			calDetail.setHd_modparam();
			holiBeans.add(calDetail);
			row_count++;
		}
		calSelector.setCalendarrows(holiBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	//
	public int InsertUpdateHoli(Connection dbConn, CalendarDetail calDetail,
			String dbUsed, String user_name, int ins_or_upd) // 1 for insert or
																// 2 for update
			throws Exception {
		moduleName = "InsertUpdateHoli: ";
		PrintLog("In Insert/Update Holidays " + dbUsed);
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "";
		//
		String holidays_year = calDetail.getHdYear();
		String holidays_curr = calDetail.getHdCurr();
		// String holidays_curr = calDetail.getHdCurr();
		String holidays_date = calDetail.getHoliday_dates();
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			mod_func = "Add Holidays";
			sql.setLength(0);
			sql.append("INSERT INTO holidays VALUES (");
			sql.append("'" + holidays_year + holidays_curr + "', ");
			// sql.append("'"+holidays_curr+"', ");
			sql.append("'" + holidays_date + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			// sql.append("NULL,");
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Holidays Insert SQL ---> " + sql);
				PrintLog("Error Inserting Holidays ->" + e.toString());
			}
		} else {
			mod_func = "Modify Holidays";
			sql.setLength(0);
			sql.append("UPDATE holidays SET ");
			sql.append("HOLIDAYS_YEAR_CURR='" + holidays_year + holidays_curr
					+ "', ");
			// sql.append("HOLIDAYS_CURRENCY='"+holidays_curr+"', ");
			sql.append("HOLIDAYS_DATES='" + holidays_date + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("HOLIDAYS_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("HOLIDAYS_LAST_MODIFIED=sysdate, ");
			}
			// sql.append("HOLIDAYS_LAST_MODIFIED=NULL, ");
			sql.append("HOLIDAYS_MOD_USER='" + user_name + "', ");
			sql.append("HOLIDAYS_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE HOLIDAYS_YEAR_CURR='" + holidays_year
					+ holidays_curr + "' ");
			// sql.append("AND HOLIDAYS_CURRENCY='"+holidays_curr+"'");
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Holidays Update SQL ---> " + sql);
				PrintLog("Error Updating Holidays ->" + e.toString());
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO holidays_log VALUES (");
		sql.append("'" + holidays_year + holidays_curr + "', ");
		// sql.append("'"+holidays_curr+"', ");
		sql.append("'" + holidays_date + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("NULL,");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Holidays Log Insert SQL ---> " + sql);
			PrintLog("Error inserting Holidays Log ->" + e.toString());
		}
		statement.close();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		// result.close();
		if (ins_or_upd == 1) {
			calDetail.setErrorVec("Holidays", "result.added");
		} else {
			calDetail.setErrorVec("Holidays", "result.modified");
		}
		return (1);
	}
}
