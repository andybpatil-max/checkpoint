package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class DbSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Instance Variables
	//private String className = "> RhostSelector.";
	private String moduleName;
	private String calledFrom;
	private String actionFlag	= "";
	private String accessFlag	= "";
	private String userId		= "";
	private String param		= "";
	private String applDate		= "";
	private String dbUsed		= "";
	private String dbAppl		= "";
	private String dbTable		= "";
	private String dbSql		= "";
	private String dbAppl_o		= "";
	private String dbTable_o	= "";
	//private String dbSql_o	= "";
	private ArrayList<DbDetail> dbRows		= new ArrayList<DbDetail>();
	private ArrayList<String> applList		= new ArrayList<String>();
	private ArrayList<String> dbList		= new ArrayList<String>();
	private Vector<Vector<String>> errorVec	= new Vector<Vector<String>>();

	/*
	 * // Properties private void PrintLog (String logMsg) {
	 * System.out.println(java.util.Calendar.getInstance().getTime()+
	 * moduleName+logMsg); }
	 */
	public void clearRows() {
		dbRows.clear();
	}

	//
	public void clearErrors() {
		errorVec.clear();
	}

	//
	public Vector<Vector<String>> getErrorVec() {
		return this.errorVec;
	}

	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}

	//
	public String getActionFlag() {
		return (this.actionFlag);
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}

	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
	}

	//
	public String getUserId() {
		return (this.userId);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//
	public String getDbUsed() {
		return (this.dbUsed);
	}

	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}

	//
	public String getApplDate() {
		return (this.applDate);
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	//
	public String getDbAppl() {
		return (this.dbAppl);
	}

	public void setDbAppl(String dbAppl) {
		this.dbAppl = dbAppl;
	}

	//
	public String getDbTable() {
		return (this.dbTable);
	}

	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}

	//
	public String getDbSql() {
		return (this.dbSql);
	}

	public void setDbSql(String dbSql) {
		this.dbSql = dbSql;
	}

	//
	public void saveParams() {
		dbAppl_o = dbAppl;
		dbTable_o = dbTable;
	}

	//
	public void restoreParams() {
		dbAppl = dbAppl_o;
		dbTable = dbTable_o;
	}

	//
	public ArrayList<String> getDbList() {
		return (this.dbList);
	}

	public void setDbList(ArrayList<?> dbList) {
		this.dbList.clear();
		for (int i = 0; i < dbList.size(); i++) {
			this.dbList.add(i, (String) dbList.get(i));
		}
		/*
		 * for (int i =0; i<this.dbList.size(); i++) { System.out.println
		 * (java.util.Calendar.getInstance().getTime()+
		 * "> RdbSelector.setDbList: "+ dbList.get(i));
		 */
	}

	//
	public ArrayList<String> getApplList() {
		return (this.applList);
	}

	public void setApplList(ArrayList<?> applList) {
		this.applList.clear();
		for (int i = 0; i < applList.size(); i++) {
			this.applList.add(i, (String) applList.get(i));
		}
		/*
		 * for (int i =0; i<this.dbList.size(); i++) { System.out.println
		 * (java.util.Calendar.getInstance().getTime()+
		 * "> RdbSelector.setDbList: "+ dbList.get(i));
		 */
	}

	//
	//
	public DbDetail[] getDbRows() {
		DbDetail results[] = new DbDetail[dbRows.size()];
		Iterator<DbDetail> dbs = dbRows.iterator();
		int n = 0;
		while (dbs.hasNext()) {
			results[n++] = (DbDetail) dbs.next();
		}
		return (results);
	}

	public DbDetail getARow() {
		DbDetail results = new DbDetail();
		Iterator<DbDetail> dbs = dbRows.iterator();
		results = (DbDetail) dbs.next();
		return (results);
	}

	public void setDbRows(ArrayList<?> db_row) {
		Iterator<?> rows = db_row.iterator();
		while (rows.hasNext()) {
			this.dbRows.add((DbDetail) rows.next());
		}
	}

	//
	//
	public String GetParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		calledFrom = moduleName;
		moduleName = "GetParams: ";
		param = "";
		// String dbTable = dbSelector.getDbTable();
		// String dbSql = dbSelector.getDbSql();
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		// String appParam = "";
		// String tblParam = "";
		// String sqlParam = "";
		String applParam = "";
		String tableParam = "";
		// String sqlParam = "";
		//
		if (!dbAppl.equals("ALL")) {
			applParam = dbAppl.trim();
		}
		if (!dbTable.equals("ALL")) {
			tableParam = dbTable.trim();
		}
		if (!applParam.equals("")) {
			param = "dbappl='" + applParam + "' ";
		}
		if (param.equals("")) {
			if (!tableParam.equals("")) {
				param = "dbtable='" + tableParam + "' ";
			}
		} else {
			if (!tableParam.equals("")) {
				param = param + " AND dbtable='" + tableParam + "' ";
			}
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY DBAPPL, DBTABLE";
		moduleName = calledFrom;
		// PrintLog("GetParams Param->" + param);
		return (param);
	}
	/*
	 * public String GetLogParams() { calledFrom = moduleName; moduleName =
	 * "GetLogParams: "; //String dbTable = dbSelector.getDbTable(); //String
	 * dbSql = dbSelector.getDbSql(); // Set up the user selection criteria
	 * based on the user // selection to get the rows. //
	 * ---------------------------------------------------- String tableParam =
	 * ""; //String sqlParam = "";
	 * 
	 * if (!dbTable.equals("ALL")) { tableParam = dbTable.trim(); //param =
	 * host_sel; } if (!tableParam.equals("")) { //host_param =
	 * "RHOSTS_NODE_ID='" + host_from + "' "; param = "dbtable='" + tableParam +
	 * "' "; } if (!param.equals("")) { param = " WHERE " + param; } param +=
	 * " ORDER BY DBLOGTABLE"; moduleName = calledFrom;
	 * //PrintLog("GetParams Param->" + param); param +=
	 * " ORDER BY DBLOGTABLE, DBLOGMODTIME DESC"; return(param); }
	 */
}
