package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class UserSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID	= 1L;
	// -- Instance Variables
	//private String className				= "> UserSelector.";
	private String moduleName				= "";
	private String param					= "";
	private String actionFlag				= "";
	private String accessFlag				= "";
	private String dbUsed					= "";
	private String appl_date				= "";
	private String user_id_from				= "";
	private String user_id_to				= "";
	private String rowCount					= "";
	private ArrayList<?> userList			= new ArrayList<Object>();
	private ArrayList<UserDetail> userrows	= new ArrayList<UserDetail>();
	private Vector<Vector<String>> errorVec	= new Vector<Vector<String>>();
	private TreeMap<String, TreeMap<String, ArrayList<String>>> prodGroupUsers	= new TreeMap<String, TreeMap<String,ArrayList<String>>>();
	//
	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> UserSelector." + moduleName + logMsg);
	}
	//
	public void clearRows() {
		userrows.clear();
	}
	//
	public void clearErrors() {
		this.errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return this.errorVec;
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair	= new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag	= actionFlag;
	}
	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String access_flag) {
		this.accessFlag	= access_flag;
	}
	//
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed	= dbUsed;
	}
	//
	public String getAppl_date() {
		return (this.appl_date);
	}
	public void setAppl_date(String appl_date) {
		this.appl_date	= appl_date;
	}
	//
	public String getUser_id_from() {
		return (this.user_id_from);
	}
	public void setUser_id_from(String user_id_from) {
		this.user_id_from	= user_id_from;
	}
	//
	public String getUser_id_to() {
		return (this.user_id_to);
	}
	public void setUser_id_to(String user_id_to) {
		this.user_id_to	= user_id_to;
	}
	//
	public String getRowCount() {
		return (this.rowCount);
	}
	public void setRowCount(String rowCount) {
		// System.out.println (java.util.Calendar.getInstance().getTime()+
		// ">UserSelector.setRowCount: "+rowCount );
		this.rowCount	= rowCount;
	}
	//
	public UserDetail[] getUserrows() {
		// return (this.userrows);
		// (users) {
		UserDetail results[]		= new UserDetail[userrows.size()];
		Iterator<UserDetail> accts	= userrows.iterator();
		int n	= 0;
		while (accts.hasNext()) {
			results[n++]	= (UserDetail) accts.next();
		}
		return (results);
		// }
	}
	//
	public void setUserList(ArrayList<?> userList) {
		this.userList	= userList;
	}
	public ArrayList<?> getUserList() {
		return (this.userList);
	}
	//
	public UserDetail getArow() {
		//PrintLog("In userSelector.getArow");
		UserDetail results			= new UserDetail();
		Iterator<UserDetail> users	= userrows.iterator();
		results	= (UserDetail) users.next();
		return (results);
	}
	public void setUserrows(ArrayList<?> user_row) {
		Iterator<?> rows	= user_row.iterator();
		while (rows.hasNext()) {
			this.userrows.add((UserDetail) rows.next());
		}
	}
	//
	public TreeMap<String, TreeMap<String, ArrayList<String>>> getProdGroupUsers() {
		return prodGroupUsers;
	}
	public void setProdGroupUsers(TreeMap<String, TreeMap<String, ArrayList<String>>> prodGroupUsers) {
		this.prodGroupUsers	= prodGroupUsers;
	}
	//
	public String GetParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName	= "> SysadUserUtil.GetParams: ";
		param		= "";
		//
		// PrintLog("In get params1 : "+user_id_from);
		// PrintLog("In get params2 : "+user_id_to);
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String user_id_f	= "";
		String user_id_t	= "";
		String name_param	= "";
		// PrintLog("In get params4 : ");
		user_id_f			= "";
		if (!user_id_from.equals("ALL")) {
			if (!user_id_from.equals("")) {
				user_id_f	= user_id_from.trim();
			}
		}
		// PrintLog("In get params5 : ");
		user_id_t = "";
		if (!user_id_to.equals("NONE")) {
			if (!user_id_to.equals("")) {
				user_id_t	= user_id_to.trim();
			}
		}
		// PrintLog("In get params7: ");
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!user_id_f.equals("")) {
			if (!user_id_t.equals("")) {
				name_param	= ("user_id between'" + user_id_f + "' AND '" +
								user_id_t + "' ");
			} else {
				name_param	= ("user_id='" + user_id_f + "' ");
			}
		} else if (!user_id_t.equals("")) {
			name_param	= "user_id='" + user_id_t + "' ";
		}
		//
		if (!name_param.equals("")) {
			param	= " WHERE " + name_param;
		}
		// PrintLog("In get params7: ");
		param	+= " ORDER BY user_id";
		// PrintLog("Get Param "+param);
		return (param);
	}
	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		moduleName	= "GetLogParams: ";
		param		= "";
		//
		// PrintLog("In get params1 : "+user_id_from);
		// PrintLog("In get params2 : "+user_id_to);
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String user_id_f	= "";
		String user_id_t	= "";
		String name_param	= "";
		// PrintLog("In get params4 : ");
		if (!user_id_from.equals("")) {
			if (!user_id_from.equals("ALL")) {
				user_id_f	= user_id_from.trim();
			} else {
				user_id_f	= "";
			}
		}
		// PrintLog("In get params5 : ");
		if (!user_id_to.equals("")) {
			if (!user_id_to.equals("NONE")) {
				user_id_t	= user_id_to.trim();
			} else {
				user_id_t	= "";
			}
		}
		// PrintLog("In get params7: ");
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!user_id_f.equals("")) {
			if (!user_id_t.equals("")) {
				name_param	= ("user_id between'" + user_id_f + "' AND '" +
								user_id_t + "' ");
			} else {
				name_param	= ("user_id='" + user_id_f + "' ");
			}
		} else if (!user_id_t.equals("")) {
			name_param		= "user_id='" + user_id_t + "' ";
		}
		//
		//
		if (!name_param.equals("")) {
			param	= " WHERE " + name_param;
		}
		// PrintLog("In get params7: ");
		param	+= " ORDER BY user_id, ulog_last_modified DESC ";
		PrintLog("Log Param: " + param);
		return (param);
	}
}
