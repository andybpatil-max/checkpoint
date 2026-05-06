package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2009/05/15 15:16:22
 */
public final class CurrencyDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	private String bankId = "";
	//
	private String currCode = "";
	private String currCountry = "";
	private String currName = "";
	private String currNumber = "";
	private String currMinorUnit = "";
	private String currModTime = "";
	private String currModUser = "";
	private String currModFunc = "";
	//
	private String currCode_o = "";
	private String currCountry_o = "";
	private String currName_o = "";
	private String currNumber_o = "";
	private String currMinorUnit_o = "";
	//
	private HashMap<String, String> currModParam = new HashMap<String, String>();
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
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
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	//
	public String getCurrCode() {
		return (this.currCode);
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	//
	public String getCurrCountry() {
		return (this.currCountry);
	}
	public void setCurrCountry(String currCountry) {
		this.currCountry = currCountry;
	}
	//
	public String getCurrName() {
		return (this.currName);
	}
	public void setCurrName(String currName) {
		this.currName = currName;
	}
	//
	public String getCurrNumber() {
		return (this.currNumber);
	}
	public void setCurrNumber(String currNumber) {
		this.currNumber = currNumber;
	}
	//
	public String getCurrMinorUnit() {
		return (this.currMinorUnit);
	}
	public void setCurrMinorUnit(String currMinorUnit) {
		this.currMinorUnit = currMinorUnit;
	}
	//
	public String getCurrModTime() {
		return (this.currModTime);
	}
	public void setCurrModTime(String currModTime) {
		if (!currModTime.equals("") && currModTime.length() > 13) {
			this.currModTime = currModTime.substring(0, 4) + "/";
			this.currModTime += currModTime.substring(4, 6) + "/";
			this.currModTime += currModTime.substring(6, 8) + " @ ";
			this.currModTime += currModTime.substring(8, 10) + ":";
			this.currModTime += currModTime.substring(10, 12) + ":";
			this.currModTime += currModTime.substring(12, 14);
		} else {
			this.currModTime = currModTime;
		}
	}
	//
	public String getCurrModUser() {
		return (this.currModUser);
	}
	public void setCurrModUser(String currModUser) {
		this.currModUser = currModUser;
	}
	//
	public String getCurrModFunc() {
		return (this.currModFunc);
	}
	public void setCurrModFunc(String currModFunc) {
		this.currModFunc = currModFunc;
	}
	//
	// Saved fields
	//
	//
	public String getCurrCode_o() {
		return (this.currCode_o);
	}
	public void setCurrCode_o(String currCode_o) {
		this.currCode_o = currCode_o;
	}
	//
	public String getCurrCountry_o() {
		return (this.currCountry_o);
	}
	public void setCurrCountry_o(String currCountry_o) {
		this.currCountry_o = currCountry_o;
	}
	//
	public String getCurrName_o() {
		return (this.currName_o);
	}
	public void setCurrName_o(String currName_o) {
		this.currName_o = currName_o;
	}
	//
	public String getCurrNumber_o() {
		return (this.currNumber_o);
	}
	public void setCurrNumber_o(String currNumber_o) {
		this.currNumber_o = currNumber_o;
	}
	//
	public String getCurrMinorUnit_o() {
		return (this.currMinorUnit_o);
	}
	public void setCurrMinorUnit_o(String currMinorUnit_o) {
		this.currMinorUnit_o = currMinorUnit_o;
	}
	//
	public HashMap<String, String> getCurrModParam() {
		return (this.currModParam);
	}
	public void setCurrModParam() {
		this.currModParam.put("currCode", currCode);
	}
	//
	public void clearNulls() {
		this.currCode		= (currCode == null) ? " " : currCode;
		this.currCountry	= (currCountry == null) ? " " : currCountry;
		this.currName		= (currName == null) ? " " : currName;
		this.currNumber		= (currNumber == null) ? " " : currNumber;
		this.currMinorUnit	= (currMinorUnit == null) ? " " : currMinorUnit;
	}
	//
	public void clearDetails() {
		currCode		= "";
		currCountry		= "";
		currName		= "";
		currNumber		= "";
		currMinorUnit	= "";
		setCurrMakeCopy();
	}
	//
	public void setCurrMakeCopy() {
		clearNulls();
		currCode_o		= currCode;
		currCountry_o	= currCountry;
		currName_o		= currName;
		currNumber_o	= currNumber;
		currMinorUnit_o	= currMinorUnit;
	}
	//
	public int CheckForChanges() {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		clearNulls();
		int data_changed = 0;
		//
		// Just check if any of the fields was changed
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": CheckForChanges: sw addr changed ");
		if (currCountry_o.compareTo(currCountry) != 0) {
			data_changed = 1;
		}
		if (currName_o.compareTo(currName) != 0) {
			data_changed = 1;
		}
		if (currNumber_o.compareTo(currNumber) != 0) {
			data_changed = 1;
		}
		if (currMinorUnit_o.compareTo(currMinorUnit) != 0) {
			data_changed = 1;
		}
		if (data_changed == 0) {
			// errors.add("currdata_notchanged", new
			// ActionError("error.curr.nomods"));
		}
		return (data_changed);
	}
	//
}
