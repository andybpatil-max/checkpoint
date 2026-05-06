package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:30:28 $
 */

public final class EntityDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String className = "> EntityDetail.";
	private String moduleName;
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	//
	private String entityId = "";
	private String entityName = "";
	private String entityType = "";
	private String entityModtime = "";
	private String entityModuser = "";
	private String entityModfunc = "";
	//
	private String entityId_o = "";
	private String entityName_o = "";
	private String entityType_o = "";
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();

	//
	// Methods
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
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

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityModuser() {
		return entityModuser;
	}

	public void setEntityModuser(String entityModuser) {
		this.entityModuser = entityModuser;
	}

	public String getEntityModfunc() {
		return entityModfunc;
	}

	public void setEntityModfunc(String entityModfunc) {
		this.entityModfunc = entityModfunc;
	}

	//
	public String getEntityModtime() {
		return (this.entityModtime);
	}

	public void setEntityModtime(String entityModtime) {
		if (!entityModtime.equals("") && entityModtime.length() > 13) {
			this.entityModtime = entityModtime.substring(0, 4) + "/";
			this.entityModtime += entityModtime.substring(4, 6) + "/";
			this.entityModtime += entityModtime.substring(6, 8) + " @ ";
			this.entityModtime += entityModtime.substring(8, 10) + ":";
			this.entityModtime += entityModtime.substring(10, 12) + ":";
			this.entityModtime += entityModtime.substring(12, 14);
		} else {
			this.entityModtime = entityModtime;
		}
	}

	//
	// Saved fields
	//
	public String getEntityId_o() {
		return entityId_o;
	}

	public void setEntityId_o(String entityId_o) {
		this.entityId_o = entityId_o;
	}

	public String getEntityName_o() {
		return entityName_o;
	}

	public void setEntityName_o(String entityName_o) {
		this.entityName_o = entityName_o;
	}

	public String getEntityType_o() {
		return entityType_o;
	}

	public void setEntityType_o(String entityType_o) {
		this.entityType_o = entityType_o;
	}

	//
	public void clearNulls() {
		this.entityId = (this.entityId == null) ? "" : this.entityId;
		this.entityName = (this.entityName == null) ? "" : this.entityName;
		this.entityType = (this.entityType == null) ? "" : this.entityType;
	}

	public void clearFields() {
		entityId = "";
		entityName = "";
		entityType = "";
		setEntitySave();
	}

	//
	public void setEntitySave() {
		clearNulls();
		entityId_o = entityId;
		entityName_o = entityName;
		entityType_o = entityType;
	}

	//
	public int CheckForChanges() {
		className = "> EntityDetail.";
		moduleName = "CheckForChanges: ";
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
		PrintLog("Entity Type: " + entityType);
		if (entityId_o.compareTo(entityId) != 0) {
			data_changed = 1;
		}
		if (entityName_o.compareTo(entityName) != 0) {
			data_changed = 1;
		}
		if (entityType_o.compareTo(entityType) != 0) {
			data_changed = 1;
		}
		if (data_changed == 0) {
			setErrorVec("entityunchanged", "error.field.required");
		}
		return (data_changed);
	}

	//
	public boolean NewEntityFieldsValid() {
		className = "> EntityDetail.";
		moduleName = "NewEntityFieldsValid: ";
		boolean ret_bool = true;
		// The Entity. Menu and Function rows are organised in the PMF-ID
		// order.
		if (entityName.trim().equals("")) {
			ret_bool = false;
			// PrintLog(": CheckForChanges: NULL ");
			setErrorVec("Entity Name", "error.field.required");
		}
		if (entityType.trim().equals("")) {
			ret_bool = false;
			// PrintLog(": CheckForChanges: NULL ");
			setErrorVec("Entity Type", "error.field.required");
		}
		return (ret_bool);
	}

	public void showFields() {
		PrintLog("Entity Id: " + this.entityId);
		PrintLog("Entity Name: " + this.entityName);
		PrintLog("Entity Type: " + this.entityType);
	}
}
