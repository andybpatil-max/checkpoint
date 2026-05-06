package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class RhostSelector implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID	  = 1L;
	// Instance Variables
	// private String className = "> RhostSelector.";
	// private String moduleName = "";
	private String				 param				 = "";
	private String				 userId				= "";
	private String				 actionFlag			= "";
	private String				 accessFlag			= "";
	private String				 applDate			  = "";
	private String				 dbUsed				= "";
	private String				 rhosts_rem_node_sel   = "";
	private String				 rhosts_rem_node_sel_o = "";
	private ArrayList<RhostDetail> rhostrows			 = new ArrayList<RhostDetail>();
	private ArrayList<String>	  hostList			  = new ArrayList<String>();
	private Vector<Vector<String>> errorVec			  = new Vector<Vector<String>>();

	/*
	 * private void PrintLog(String logMsg) { System.out.println
	 * (java.util.Calendar.getInstance().getTime()+
	 * className+moduleName+logMsg); }
	 */
	// Properties
	public void clearRows() {
		rhostrows.clear();
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
	public String getRhosts_rem_node_sel() {
		return (this.rhosts_rem_node_sel);
	}
	public void setRhosts_rem_node_sel(String rhosts_rem_node_sel) {
		this.rhosts_rem_node_sel = rhosts_rem_node_sel;
	}
	//
	public void saveParams() {
		rhosts_rem_node_sel_o = rhosts_rem_node_sel;
	}
	//
	public void restoreParams() {
		rhosts_rem_node_sel = rhosts_rem_node_sel_o;
	}
	//
	public ArrayList<String> getHostList() {
		return (this.hostList);
	}
	/*
	 * public void setHostList(ArrayList<String> hostList) { Iterator hosts =
	 * hostList.iterator(); int n = 0; while (hosts.hasNext()) {
	 * this.hostList.add(n, (String) hosts.next()); n++; System.out.println
	 * (java.util.Calendar.getInstance().getTime()+
	 * "> RhostSelector.setHostList: "+(String) hosts.next()); } if (n == 0) {
	 * this.hostList.add(" "); } }
	 */
	public void setHostList(ArrayList<?> hostList) {
		this.hostList.clear();
		Iterator<?> hL = hostList.iterator();
		while (hL.hasNext()) {
			this.hostList.add((String) hL.next());
		}
		/*
		 * this.hostList = hostList; for (int i =0; i<this.hostList.size(); i++)
		 * { System.out.println (java.util.Calendar.getInstance().getTime()+
		 * "> RhostSelector.setHostList: "+ hostList.get(i)); }
		 */
	}
	//
	//
	public RhostDetail[] getRhostrows() {
		RhostDetail results[] = new RhostDetail[rhostrows.size()];
		Iterator<RhostDetail> rhosts = rhostrows.iterator();
		int n = 0;
		while (rhosts.hasNext()) {
			results[n++] = (RhostDetail) rhosts.next();
		}
		return (results);
	}

	public RhostDetail getArow() {
		RhostDetail results = new RhostDetail();
		Iterator<RhostDetail> rhosts = rhostrows.iterator();
		results = (RhostDetail) rhosts.next();
		return (results);
	}

	public void setRhostrows(ArrayList<?> rhost_row) {
		Iterator<?> rows = rhost_row.iterator();
		while (rows.hasNext()) {
			this.rhostrows.add((RhostDetail) rows.next());
		}
	}
	//
	public String GetParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		// calledFrom = moduleName;
		// moduleName = "GetParams: ";
		param = "";
		// String host_sel = rhostSelector.getRhosts_rem_node_sel();
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String host_from = "";
		//
		if (!rhosts_rem_node_sel.equals("ALL")) {
			host_from = rhosts_rem_node_sel.trim();
			// param = host_sel;
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!host_from.equals("")) {
			// host_param = "RHOSTS_NODE_ID='" + host_from + "' ";
			param = "RHOSTS_NODE_ID='" + host_from + "' ";
		}
		/*
		 * if (!param.equals("")) { if (!host_param.equals("")) param += " AND "
		 * + host_param; } else { param = host_param; }
		 */
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY RHOSTS_REM_NODE";
		// PrintLog("GetParams Param->" + param);
		return (param);
	}
	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		// moduleName = "GetLogParams: ";
		param = "";
		// String host_sel = rhostSelector.getRhosts_rem_node_sel();
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String host_from = "";
		String host_param = "";
		// String curr_param = "";
		//
		if (!rhosts_rem_node_sel.equals("ALL")) {
			host_from = rhosts_rem_node_sel.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!host_from.equals("")) {
			host_param = "RHLOG_NODE_ID='" + host_from + "' ";
		}
		if (!param.equals("")) {
			if (!host_param.equals(""))
				param += " AND " + host_param;
		} else {
			param = host_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY RHLOG_REM_NODE, RHLOG_LAST_MODIFIED DESC";
		return (param);
	}
}
