package com.webfiche.checkpoint.sysadmin.service;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.*;

/**
 */
@Service
public class SysadDbUtil {
	private String	  className = "> SysadDbUtil.";
	private String	  moduleName;
	// private String calledFrom;
	ArrayList<String>   paramArray;
	ArrayList<DbDetail> dbBeans	= new ArrayList<DbDetail>();
	String			  param		= "";
	int				 rowCount	= 0;
	//
	public SysadDbUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public boolean TableExists(Connection dbConn, String dbTable)
		throws Exception {
		//
		moduleName			= "TableExists: ";
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DBTABLE from dbtables ");
		sql.append("WHERE DBTABLE='" + dbTable + "' ");
		//PrintLog("Table Exist SQL "+sql);
		//Setup the SELECT statement.
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("DBTABLE");
			statement.close();
			result.close();
			// PrintLog("Table Exist returning true ");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public int GetTableRows(Connection dbConn, DbSelector dbSelector,
			String dbTable) throws Exception {
		moduleName	= "GetTableRows: ";
		dbSelector.clearRows();
		if (dbTable.equals("")) {
			if (param.equals("")) {
				// GetParams (dbSelector);
				param	= dbSelector.GetParams();
			}
		} else {
			param	= "WHERE DBTABLE='" + dbTable + "' ";
		}
		StringBuffer sql	= new StringBuffer();
		rowCount			= 0;
		sql.append("SELECT DBAPPL, DBTABLE, DBSQL, DBINITSQL, ");
		sql.append("DBMODTIME, DBMODUSER, DBMODFUNC ");
		sql.append("FROM dbtables ");
		sql.append(param);
		//PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		// int idx = 0;
		dbBeans.clear();
		while (result.next()) {
			DbDetail dbDetail	= new DbDetail();
			dbDetail.setDbAppl(result.getString("DBAPPL"));
			dbDetail.setDbTable(result.getString("DBTABLE"));
			dbDetail.setDbSql(result.getString("DBSQL"));
			dbDetail.setDbInitSql(result.getString("DBINITSQL"));
			dbDetail.setDbModTime(result.getString("DBMODTIME"));
			dbDetail.setDbModUser(result.getString("DBMODUSER"));
			dbDetail.setDbModFunc(result.getString("DBMODFUNC"));
			dbBeans.add(dbDetail);
			rowCount++;
		}
		dbSelector.setDbRows(dbBeans);
		statement.close();
		result.close();
		return (rowCount);
	}
	//
	public int GetRowCounts(Connection dbConn, String dbTable) throws Exception {
		moduleName	= "GetRowCounts: ";
		StringBuffer sql	= new StringBuffer();
		rowCount			= 0;
		sql.append("SELECT COUNT(*) as totalRows FROM ");
		sql.append(dbTable);
		//PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		result.next();
		rowCount	=	Integer.parseInt(result.getString("totalRows"));
		statement.close();
		result.close();
		return (rowCount);
	}
	//
	public ArrayList<String> GetTableList(Connection dbConn)
			throws Exception {
		moduleName			= "GetTableList: ";
		String tempTable	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> dbList	= new ArrayList<String>();
		dbList.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT DBTABLE FROM dbtables ORDER BY DBTABLE");
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			tempTable	= result.getString("DBTABLE");
			// PrintLog("temp_db: "+temp_db);
			dbList.add(tempTable);
		}
		statement.close();
		result.close();
		return (dbList);
	}
	//
	public int InsertUpdateTable(Connection dbConn, DbDetail dbDetail,
			String dbUsed, String user_name, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName			= "InsertUpdateTables: ";
		PrintLog("In Insert/Update Table Data");
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		String mod_func		= "";
		dbDetail.clearNulls();
		//
		String dbAppl		= dbDetail.getDbAppl();
		String dbTable		= dbDetail.getDbTable();
		String dbSql		= dbDetail.getDbSql();
		String dbInitSql	= dbDetail.getDbInitSql();
		if (ins_or_upd == 1) {
			mod_func	= "Add";
			sql.setLength(0);
			sql.append("INSERT INTO dbtables VALUES (");
			sql.append("'" + dbAppl + "', ");
			sql.append("'" + dbTable + "', ");
			sql.append("'" + dbSql + "', ");
			sql.append("'" + dbInitSql + "', ");
			// sql.append("NULL,");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			// sql.append("sysdate,");
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog("DbTable Insert SQL ---> "+sql);
			//
			try {
				statement	= dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Tables ->" + e.toString());
				PrintLog("DbTable Insert SQL ---> " + sql);
			}
		} else {
			mod_func	= "Modify";
			sql.setLength(0);
			sql.append("UPDATE dbtables SET ");
			sql.append("DBAPPL='" + dbAppl + "', ");
			sql.append("DBTABLE='" + dbTable + "', ");
			sql.append("DBSQL='" + dbSql + "', ");
			sql.append("DBINITSQL='" + dbInitSql + "', ");
			// sql.append("RHOSTS_LAST_MODIFIED=NULL, ");
			if (dbUsed.equals("MySQL")) {
				sql.append("DBMODTIME=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("DBMODTIME=sysdate, ");
			}
			sql.append("DBMODUSER='" + user_name + "', ");
			sql.append("DBMODFUNC='" + mod_func + "' ");
			sql.append("WHERE DBTABLE='" + dbTable + "' ");
			PrintLog("DbTables Update SQL ---> " + sql);
			//
			try {
				statement	= dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Dbtables ->" + e.toString());
				PrintLog("DbTables Update SQL ---> " + sql);
			}
		}
		statement.close();
		return (1);
	}
	//
	public int DeleteTable(Connection dbConn, DbDetail dbDetail) //, String dbUsed,
			//String user_name) // ,
			// int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "DeleteTable: ";
		// PrintLog("In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		//
		String dbTable = dbDetail.getDbTable();
		/*
		 * sql.setLength(0); sql.append("INSERT INTO dbtables_log ");
		 * sql.append("SELECT DBTABLE, DBSQL, "); if (dbUsed.equals("MySQL")) {
		 * sql.append("NULL, "); } else if (dbUsed.equals("ORACLE")) {
		 * sql.append("sysdate, "); } sql.append("'"+user_name+"', ");
		 * sql.append("'"+mod_func+"' "); sql.append("FROM DBTABLES ");
		 * sql.append("WHERE DBTABLE='"+dbTabel+"' ");
		 * //PrintLog("Delete Table Log Insert SQL ---> "+sql); // try {
		 * statement = dbConn.createStatement();
		 * //PrintLog("Executing result set"); result =
		 * statement.executeUpdate(sql.toString()); } catch (SQLException e) {
		 * PrintLog("Error inserting DB Table Log ->"+ e.toString());
		 * PrintLog("Log Insert SQL ---> "+sql); } statement.close();
		 */
		sql.setLength(0);
		sql.append("DELETE FROM dbtables ");
		sql.append("WHERE DBTABLE='" + dbTable + "' ");
		PrintLog("Delete SQL ---> " + sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting DbTable-> " + e.toString());
			PrintLog("Delete Table Insert SQL ---> " + sql);
		}
		statement.close();
		return (1);
	}
}
