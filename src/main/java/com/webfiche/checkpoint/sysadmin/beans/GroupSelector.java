package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class GroupSelector implements Serializable {
	/**
	 **/
	private static final long serialVersionUID	= 1L;
	// -- Instance Variables
	// private String className		= "> CurrencySelector.";
	// private String moduleName	= "";
	private String actionFlag		= "";
	private String accessFlag		= "";
	private String dbUsed			= "";
	private String g_prod_group		= "";
	private String g_group			= "";
	private String g_prod_group_o	= "";
	private String g_group_o		= "";
	private ArrayList<GroupDetail> grouprows	= new ArrayList<GroupDetail>();
	private Vector<Vector<String>> errorVec		= new Vector<Vector<String>>();
	/*
	 * private void PrintLog (String logMsg) { System.out.println
	 * (java.util.Calendar.getInstance().getTime()+
	 * className+moduleName+logMsg); }
	 */
	// ----------------------------------------------------------- Properties
	public void clearRows() {
		grouprows.clear();
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
	public String getG_prod_group() {
		return (this.g_prod_group);
	}
	public void setG_prod_group(String g_prod_group) {
		this.g_prod_group	= g_prod_group;
	}
	//
	public String getG_group() {
		return (this.g_group);
	}
	public void setG_group(String g_group) {
		this.g_group	= g_group;
	}
	//
	public String getG_prod_group_o() {
		return (this.g_prod_group_o);
	}
	public void setG_prod_group_o(String g_prod_group_o) {
		this.g_prod_group_o	= g_prod_group_o;
	}
	//
	public String getG_group_o() {
		return (this.g_group_o);
	}
	public void setG_group_o(String g_group_o) {
		this.g_group_o	= g_group_o;
	}
	//
	public void saveParams() {
		g_prod_group_o	= g_prod_group;
		g_group_o		= g_group;
	}
	//
	public void restoreParams() {
		g_prod_group	= g_prod_group_o;
		g_group			= g_group_o;
	}
	//
	public GroupDetail[] getGrouprows() {
		GroupDetail results[]		= new GroupDetail[grouprows.size()];
		Iterator<GroupDetail> grps	= grouprows.iterator();
		int n	= 0;
		while (grps.hasNext()) {
			results[n++]	= (GroupDetail) grps.next();
		}
		return (results);
	}
	public GroupDetail getArow() {
		GroupDetail results			= new GroupDetail();
		Iterator<GroupDetail> grps	= grouprows.iterator();
		results						= (GroupDetail) grps.next();
		return (results);
	}
	public void setGrouprows(ArrayList<?> group_row) {
		Iterator<?> rows	= group_row.iterator();
		while (rows.hasNext()) {
			this.grouprows.add((GroupDetail) rows.next());
		}
	}
}
