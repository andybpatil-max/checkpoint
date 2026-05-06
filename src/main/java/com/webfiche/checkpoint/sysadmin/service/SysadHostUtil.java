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
public class SysadHostUtil {
	private String		 className  = "> SysadHostUtil.";
	private String		 moduleName;
	// private String calledFrom;
	ArrayList<String>	  paramArray;
	ArrayList<RhostDetail> rhostBeans = new ArrayList<RhostDetail>();
	String	param		= "";
	int		row_count	= 0;

	public SysadHostUtil() {
	}
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public ArrayList<String> GetHostsList(Connection dbConn) throws Exception {
		moduleName			= "GetHostsList: ";
		String temp_host	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> host_list		= new ArrayList<String>();
		host_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT RHOSTS_NODE_ID from rhosts"	+ " ORDER by RHOSTS_NODE_ID");
		// Setup the SELECT statement.
		//PrintLog("Execute " + sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp_host	= result.getString("RHOSTS_NODE_ID");
			//PrintLog("temp_host: " + temp_host);
			host_list.add(temp_host);
		}
		statement.close();
		result.close();
		return (host_list);
	}
	//
	public ArrayList<String> GetHostsLogList(Connection dbConn)
			throws Exception {
		moduleName			= "GetHostsLogList: ";
		String temp_host	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> host_list	= new ArrayList<String>();
		host_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT RHLOG_NODE_ID from rhosts_log "	+ "ORDER by RHLOG_NODE_ID");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			//PrintLog("Execute In While");
			temp_host	= result.getString("RHLOG_NODE_ID");
			host_list.add(temp_host);
		}
		statement.close();
		result.close();
		return (host_list);
	}
	//
	public boolean HostExists(Connection dbConn, String host) throws Exception {
		//
		moduleName			= "HostExists: ";
		StringBuffer sql	= new StringBuffer();
		// String temp = "";
		sql.append("SELECT RHOSTS_NODE_ID from rhosts ");
		sql.append("WHERE RHOSTS_NODE_ID='" + host + "' ");
		// PrintLog("Host Exist SQL "+sql);
		// Setup the SELECT statement.
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// temp = result.getString("RHOSTS_NODE_ID");
			statement.close();
			result.close();
			//PrintLog("HOST Exist returning true ");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public int GetHostRows(Connection dbConn, RhostSelector rhostSelector,
			String host) throws Exception {
		// Called by the modify module with an account number
		// --------------------------------------------------
		moduleName	= "GetHostRows: ";
		param		= "WHERE RHOSTS_NODE_ID='" + host + "'";
		row_count	= GetHostRows(dbConn, rhostSelector);
		return (row_count);
	}
	//
	public int GetHostRows(Connection dbConn, RhostSelector rhostSelector)
			throws Exception {
		moduleName	= "GetHostRows: ";
		if (param.equals(""))
			param	= rhostSelector.GetParams();
		StringBuffer sql	= new StringBuffer();
		row_count			= 0;
		sql.append("SELECT RHOSTS_NODE_ID, RHOSTS_REM_NODE, RHOSTS_USER_NAME, ");
		sql.append("RHOSTS_USER_PASS, RHOSTS_XFER_MODE, RHOSTS_EXTRA, RHOSTS_LAST_MODIFIED, ");
		sql.append("RHOSTS_MOD_USER, RHOSTS_MOD_FUNC ");
		sql.append("FROM rhosts ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		// int idx = 0;
		rhostBeans.clear();
		while (result.next()) {
			RhostDetail rhostDetail	= new RhostDetail();
			rhostDetail.setRhosts_node_id(result.getString("RHOSTS_NODE_ID"));
			rhostDetail.setRhosts_rem_node(result.getString("RHOSTS_REM_NODE"));
			rhostDetail.setRhosts_user_name(result.getString("RHOSTS_USER_NAME"));
			rhostDetail.setRhosts_user_pass(result.getString("RHOSTS_USER_PASS"));
			rhostDetail.setRhostsXferMode(result.getString("RHOSTS_XFER_MODE"));
			rhostDetail.setRhostsXtra(result.getString("RHOSTS_EXTRA"));
			rhostDetail.setRhosts_last_modified(result.getString("RHOSTS_LAST_MODIFIED"));
			rhostDetail.setRhosts_mod_user(result.getString("RHOSTS_MOD_USER"));
			rhostDetail.setRhosts_mod_func(result.getString("RHOSTS_MOD_FUNC"));
			rhostDetail.setRhosts_modparam();
			rhostBeans.add(rhostDetail);
			row_count++;
		}
		rhostSelector.setRhostrows(rhostBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetHostLogRows(Connection dbConn, RhostSelector rhostSelector)
			throws Exception {
		moduleName = "GetHostLogRows: ";
		if (param.equals(""))
			// GetLogParams (rhostSelector);
			param = rhostSelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT RHLOG_NODE_ID, RHLOG_REM_NODE, RHLOG_USER_NAME, ");
		sql.append("RHLOG_USER_PASS, RHLOG_XFER_MODE, RHLOG_EXTRA, ");
		sql.append("RHLOG_LAST_MODIFIED, RHLOG_MOD_USER, RHLOG_MOD_FUNC ");
		sql.append("FROM rhosts_log ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		rhostBeans.clear();
		while (result.next()) {
			RhostDetail rhostDetail = new RhostDetail();
			rhostDetail.setRhosts_node_id(result.getString("RHLOG_NODE_ID"));
			rhostDetail.setRhosts_rem_node(result.getString("RHLOG_REM_NODE"));
			rhostDetail
					.setRhosts_user_name(result.getString("RHLOG_USER_NAME"));
			rhostDetail
					.setRhosts_user_pass(result.getString("RHLOG_USER_PASS"));
			rhostDetail.setRhostsXferMode(result.getString("RHLOG_XFER_MODE"));
			rhostDetail.setRhostsXtra(result.getString("RHLOG_EXTRA"));
			rhostDetail.setRhosts_last_modified(result
					.getString("RHLOG_LAST_MODIFIED"));
			rhostDetail.setRhosts_mod_user(result.getString("RHLOG_MOD_USER"));
			rhostDetail.setRhosts_mod_func(result.getString("RHLOG_MOD_FUNC"));
			rhostDetail.setRhosts_modparam();
			rhostBeans.add(rhostDetail);
			row_count++;
		}
		rhostSelector.setRhostrows(rhostBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	public int InsertUpdateHost(Connection dbConn, RhostDetail rhostDetail,
			String dbUsed, String user_name, int ins_or_upd) // 1 for insert or
															 // 2 for update
			throws Exception {
		moduleName = "InsertUpdateHost: ";
		PrintLog("In Insert/Update Remote Host Data");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "";
		//
		String rhosts_node_id = rhostDetail.getRhosts_node_id();
		String rhosts_rem_node = rhostDetail.getRhosts_rem_node();
		String rhosts_user_name = rhostDetail.getRhosts_user_name();
		String rhosts_user_pass = rhostDetail.getRhosts_user_pass();
		String rhostsXferMode = rhostDetail.getRhostsXferMode();
		String rhostsXtra = " ";
		if (ins_or_upd == 1) {
			mod_func = "Add Host";
			sql.setLength(0);
			sql.append("INSERT INTO rhosts VALUES (");
			sql.append("'" + rhosts_node_id + "', ");
			sql.append("'" + rhosts_rem_node + "', ");
			sql.append("'" + rhosts_user_name + "', ");
			sql.append("'" + rhosts_user_pass + "', ");
			sql.append("'" + rhostsXferMode + "', ");
			sql.append("'" + rhostsXtra + "', ");
			// sql.append("NULL,");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sys-date, ");
			}
			// sql.append("sysdate,");
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog("Rhosts Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Hosts ->" + e.toString());
				PrintLog("Rhosts Insert SQL ---> " + sql);
			}
		} else {
			mod_func = "Modify Rhosts";
			sql.setLength(0);
			sql.append("UPDATE rhosts SET ");
			sql.append("RHOSTS_NODE_ID='" + rhosts_node_id + "', ");
			sql.append("RHOSTS_REM_NODE='" + rhosts_rem_node + "', ");
			sql.append("RHOSTS_USER_NAME='" + rhosts_user_name + "', ");
			sql.append("RHOSTS_USER_PASS='" + rhosts_user_pass + "', ");
			sql.append("RHOSTS_XFER_MODE='" + rhostsXferMode + "', ");
			sql.append("RHOSTS_EXTRA='" + rhostsXtra + "', ");
			// sql.append("RHOSTS_LAST_MODIFIED=NULL, ");
			if (dbUsed.equals("MySQL")) {
				sql.append("RHOSTS_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("RHOSTS_LAST_MODIFIED=sysdate, ");
			}
			sql.append("RHOSTS_MOD_USER='" + user_name + "', ");
			sql.append("RHOSTS_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE RHOSTS_NODE_ID='" + rhosts_node_id + "' ");
			// PrintLog("Rhosts Update SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Hosts ->" + e.toString());
				PrintLog("Rhosts Update SQL ---> " + sql);
			}
		}
		statement.close();
		sql.setLength(0);
		sql.append("INSERT INTO rhosts_log VALUES (");
		sql.append("'" + rhosts_node_id + "', ");
		sql.append("'" + rhosts_rem_node + "', ");
		sql.append("'" + rhosts_user_name + "', ");
		sql.append("'" + rhosts_user_pass + "', ");
		sql.append("'" + rhostsXferMode + "', ");
		sql.append("'" + rhostsXtra + "', ");
		// sql.append("NULL,");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("sysdate,");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		// PrintLog("Rhost Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Hosts Log ->" + e.toString());
			PrintLog("Rhost Log Insert SQL ---> " + sql);
		}
		statement.close();
		return (1);
	}
	public int DeleteHost(Connection dbConn, RhostDetail rhostDetail,
			String dbUsed, String user_name) // ,
			// int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "DeleteHost: ";
		// PrintLog("In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "Delete host";
		//
		String rhosts_node_id = rhostDetail.getRhosts_node_id();

		sql.setLength(0);
		sql.append("INSERT INTO rhosts_log ");
		sql.append("SELECT RHOSTS_NODE_ID, RHOSTS_REM_NODE, RHOSTS_USER_NAME, ");
		sql.append("RHOSTS_USER_PASS, RHOSTS_XFER_MODE, RHOSTS_EXTRA");
		// sql.append("NULL, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("sysdate, ");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM rhosts ");
		sql.append("WHERE RHOSTS_NODE_ID='" + rhosts_node_id + "' ");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting host Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
		}
		statement.close();
		sql.setLength(0);
		sql.append("DELETE FROM rhosts ");
		sql.append("WHERE RHOSTS_NODE_ID='" + rhosts_node_id + "' ");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting Host-> " + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
		}
		statement.close();
		return (1);
	}
}
