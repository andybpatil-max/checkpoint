package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:30:30 $
 */

public final class DbDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String	className		= "> DbDetail.";
	private String	moduleName;
	// private String calledFrom;
	private String	actionFlag	= "";
	private String	accessFlag	= "";
	private String	dbUsed		= "";
	private String	userId		= "";
	//
	private String	dbAppl		= "";
	private String	dbTable		= "";
	private String	dbSql		= "";
	private String	dbInitSql	= "";
	private String	dbModUser	= "";
	private String	dbModFunc	= "";
	private String	dbModTime	= "";
	//
	private String	dbAppl_o	= " ";
	private String	dbTable_o	= " ";
	private String	dbSql_o		= " ";
	private String	dbInitSql_o	= " ";
	private Vector<Vector<String>> errorVec		 = new Vector<Vector<String>>();
	//
	// Methods
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	public void clearErrors() {
		errorVec.clear();
	}
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
	// ----------------------------------------------------------- Properties
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
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getUserId() {
		return (this.userId);
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getDbInitSql() {
		return (this.dbInitSql);
	}
	public void setDbInitSql(String dbInitSql) {
		this.dbInitSql = dbInitSql;
	}
	//
	//
	public String getDbModTime() {
		return (this.dbModTime);
	}
	public void setDbModTime(String dbModTime) {
		if (!dbModTime.equals("") && dbModTime.length() > 13) {
			this.dbModTime = dbModTime.substring(0, 4) + "/";
			this.dbModTime += dbModTime.substring(4, 6) + "/";
			this.dbModTime += dbModTime.substring(6, 8) + " @ ";
			this.dbModTime += dbModTime.substring(8, 10) + ":";
			this.dbModTime += dbModTime.substring(10, 12) + ":";
			this.dbModTime += dbModTime.substring(12, 14);
		} else {
			this.dbModTime = dbModTime;
		}
	}
	//
	public String getDbModUser() {
		return (this.dbModUser);
	}
	public void setDbModUser(String dbModUser) {
		this.dbModUser = dbModUser;
	}
	//
	public String getDbModFunc() {
		return (this.dbModFunc);
	}
	public void setDbModFunc(String dbModFunc) {
		this.dbModFunc = dbModFunc;
	}
	//
	// Saved fields
	//
	public void clearNulls() {
		this.dbAppl		= (dbAppl == null) ? " " : dbAppl;
		this.dbTable	= (dbTable == null) ? " " : dbTable;
		this.dbSql		= (dbSql == null) ? " " : dbSql;
		this.dbInitSql	= (dbInitSql == null) ? " " : dbInitSql;
	}
	public void clearDetails() {
		dbAppl		= " ";
		dbTable		= " ";
		dbSql		= " ";
		dbInitSql	= " ";
		dbModTime	= " ";
		dbModUser	= " ";
		dbModFunc	= " ";
		saveDetails();
	}
	//
	public void saveDetails() {
		clearNulls();
		dbAppl_o	= dbAppl;
		dbTable_o	= dbTable;
		dbSql_o		= dbSql;
		dbInitSql_o	= dbInitSql;
	}
	//
	public int CheckForChanges() {
		moduleName	= "CheckForChanges: ";
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed = 0;
		//
		// Just check if any of the fields was changed
		// PrintLog(": CheckForChanges: sw addr changed ");
		clearNulls();
		PrintLog("dbAppl_o " + dbAppl_o + " dbAppl " + dbAppl);
		if (dbAppl_o.compareTo(dbAppl) != 0) {
			data_changed = 1;
			PrintLog("dbAppl_o " + dbAppl_o + " dbAppl " + dbAppl);
		}
		if (dbTable_o.compareTo(dbTable) != 0) {
			data_changed = 1;
			// PrintLog("dbTable_o "+ dbTable_o + " dbTable " + dbTable);
		}
		if (dbSql_o.compareTo(dbSql) != 0) {
			data_changed = 1;
			// PrintLog("dbSql_o " + dbSql_o + " dbSql " + dbSql);
		}
		// PrintLog("dbInitSql_o " + dbInitSql_o + " dbInitSql " + dbInitSql);
		if (dbInitSql_o.compareTo(dbInitSql) != 0) {
			data_changed = 1;
			// PrintLog("dbInitSql_o " + dbInitSql_o + " dbInitSql " +
			// dbInitSql);
		}
		if (data_changed == 0) {
			setErrorVec("dbData_notchanged", "info.nomods");
		}
		return (data_changed);
	}
	//
	public boolean ValidateNewData() {
		moduleName = "ValidateNewData: ";
		boolean ret_bool = true;
		clearNulls();
		if (dbAppl.trim().equals("")) {
			ret_bool = false;
			// PrintLog("dbAppl field empty ");
			setErrorVec("DbAppl", "error.field.required");
		}
		if (dbTable.trim().equals("")) {
			ret_bool = false;
			// PrintLog("dbTable field empty ");
			setErrorVec("DbTable", "error.field.required");
		}
		if (dbSql.trim().equals("")) {
			ret_bool = false;
			// PrintLog("dbSql field empty");
			setErrorVec("DbSQL", "error.field.required");
		}
		return (ret_bool);
	}
}
