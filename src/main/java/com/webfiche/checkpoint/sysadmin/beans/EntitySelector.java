package com.webfiche.checkpoint.sysadmin.beans;

import java.io.*;
import java.util.*;

public final class EntitySelector implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID = 1L;
	// Instance Variables
	private String className = "> EntitySelector.";
	private String moduleName = "";
	private String	param			= "";
	private String	userId			= "";
	private String	actionFlag		= "";
	private String	accessFlag		= "";
	private String	applDate		= "";
	private String	dbUsed			= "";
	private String	entityId		= "";
	private String	entityId_o		= "";
	private ArrayList<EntityDetail>	entityRows	= new ArrayList<EntityDetail>();
	private ArrayList<String>		entityList	= new ArrayList<String>();
	private TreeMap<String,String>	entityMap	= new TreeMap<String,String>();
	private Vector<Vector<String>>  errorVec	= new Vector<Vector<String>>();
	//
	private void PrintLog(String logMsg) { 
			System.out.println (java.util.Calendar.getInstance().getTime()+
								className + moduleName + logMsg); 
	}
	// Properties
	public void clearRows() {
		entityRows.clear();
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
	public String getEntityId() {
		return (this.entityId);
	}
	public void setEntityId(String entityId) {
		this.entityId	= entityId;
	}
	//
	public void saveParams() {
		entityId_o	= entityId;
	}
	//
	public void restoreParams() {
		entityId	= entityId_o;
	}
	//
	public ArrayList<String> getEntityList() {
		return (this.entityList);
	}
	public void setEntityList(ArrayList<String> entityList) {
		this.entityList.clear();
		Iterator<String> eL = entityList.iterator();
		while (eL.hasNext()) {
			//PrintLog("Adding: ");
			this.entityList.add((String) eL.next());
		}
	}
	//
	public TreeMap<String,String> getEntityMap() {
		return (this.entityMap);
	}
	public String getEntityMap(String entityId) {
		return (this.entityMap.get(entityId));
	}
	public void setEntityMap(TreeMap<String,String> entityMap) {
		this.entityMap	= entityMap;
	}
	//
	public EntityDetail[] getEntityRows() {
		EntityDetail results[] = new EntityDetail[entityRows.size()];
		Iterator<EntityDetail> entitys = entityRows.iterator();
		int n = 0;
		while (entitys.hasNext()) {
			results[n++] = (EntityDetail) entitys.next();
		}
		return (results);
	}
	//
	public EntityDetail getArow() {
		EntityDetail results = new EntityDetail();
		Iterator<EntityDetail> entitys = entityRows.iterator();
		results = (EntityDetail) entitys.next();
		return (results);
	}
	//
	public void setEntityRows(ArrayList<EntityDetail> entityRow) {
		Iterator<?> rows = entityRow.iterator();
		while (rows.hasNext()) {
			this.entityRows.add((EntityDetail) rows.next());
		}
	}
	//
	public void clearNulls() {
		this.entityId	= (this.entityId == null) ? "" : this.entityId;
		//this.entityName	= (this.entityName == null) ? "" : this.entityName;
		//this.entityType	= (this.entityType == null) ? "" : this.entityType;
	}
	//
	public void ShowParams() {
		PrintLog("Entity ID: "+this.entityId);
	}
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
		String entityFrom = "";
		clearNulls();
		//
		if (!entityId.equals("ALL")) {
			entityFrom	= entityId.trim();
			// param = host_sel;
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!entityFrom.equals("")) {
			// host_param = "RHOSTS_NODE_ID='" + host_from + "' ";
			param = "ENTITY_ID='" + entityFrom + "' ";
		}
		/*
		 * if (!param.equals("")) { if (!host_param.equals("")) param += " AND "
		 * + host_param; } else { param = host_param; }
		 */
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY ENTITY_ID";
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
		String entityFrom = "";
		String entityParam = "";
		// String curr_param = "";
		//
		if (!entityId.equals("ALL")) {
			entityFrom = entityId.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!entityFrom.equals("")) {
			entityParam = "ENTITY_ID='" + entityParam + "' ";
		}
		if (!param.equals("")) {
			if (!entityParam.equals(""))
				param += " AND " + entityParam;
		} else {
			param = entityParam;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY ENTITY_ID, ENTITY_MODTIME DESC";
		return (param);
	}
}
