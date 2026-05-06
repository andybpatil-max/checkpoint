/*
 * ====================================================================
 */

package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class ControlSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance Variables
	// private String className = "> ControlSelector.";
	// private String moduleName;
	private String actionFlag		= "";
	private String accessFlag		= "";
	private String dbUsed			= "";
	private String ctlProdFrom		= "";
	private String ctlProdTo		= "";
	private Vector<String> ctlProds	= new Vector<String>();
	private TreeMap<String, String> prodNames = new TreeMap<String, String>();
	//
	private TreeMap<String, ControlDetail> ctlRows = new TreeMap <String, ControlDetail>();
	//
	private ArrayList<ControlDetail> controlRows = new ArrayList<ControlDetail>();
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
	/*
	 * private void PrintLog(String logMsg) { System.out.println
	 * (java.util.Calendar.getInstance().getTime()+
	 * className+moduleName+logMsg); }
	 */
	public void clearCtlProds() {
		ctlProds.clear();
	}
	//
	public void clearRows() {
		// moduleName = "clearRows: ";
		controlRows.clear();
		// PrintLog("Number Of Rows: "+this.controlRows.size());
	}
	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return this.errorVec;
	}
	//
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public Vector<String> getCtlProds() {
		return this.ctlProds;
	}
	//
	public void setCtlProds(String prod) {
		this.ctlProds.add(prod);
	}
	//
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	//
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
	public String getCtlProdFrom() {
		return (this.ctlProdFrom);
	}
	public void setCtlProdFrom(String ctlProdFrom) {
		this.ctlProdFrom = ctlProdFrom;
	}
	//
	public String getCtlProdTo() {
		return (this.ctlProdTo);
	}
	public void setCtlProdTo(String ctlProdTo) {
		this.ctlProdTo = ctlProdTo;
	}
	//
	public TreeMap<String, String> getProdNames() {
		return (this.prodNames);
	}
	public void setProdNames(TreeMap<String, String> prodNames) {
		this.prodNames = prodNames;
	}
	//
	public ArrayList<ControlDetail> getControlRows() {
		return (controlRows);
	}
	//
	public ControlDetail getARow() {
		ControlDetail results = new ControlDetail();
		Iterator<ControlDetail> users = controlRows.iterator();
		results = (ControlDetail) users.next();
		return (results);
	}
	//
	public void setControlRows (TreeMap<String, ControlDetail> ctlRows) {
		this.ctlRows	= ctlRows;
	}
	public ControlDetail getControlRow(String productId) {
		return (ctlRows.get(productId));
	}
	//
	public void setControlRows(ArrayList<ControlDetail> controlRowsIn) {
		// moduleName = "setControlRows: ";
		int idx = 0;
		Iterator<ControlDetail> rows = controlRowsIn.iterator();
		while (rows.hasNext()) {
			this.controlRows.add(idx, (ControlDetail) rows.next());
			idx++;
		}
		// PrintLog("Number Of Rows: "+idx);
	}
}
