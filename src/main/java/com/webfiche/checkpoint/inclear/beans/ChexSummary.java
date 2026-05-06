package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:31:11 $
 */

public final class ChexSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	private String chex_status_description = "";
	private String chex_check_status = "";
	private String chex_summ_amount = "";
	private String chex_check_count = "";
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();

	// ----------------------------------------------------------- Properties
	//
	public String getActionFlag() {
		return (this.actionFlag);
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

	public String getAccessFlag() {
		return (this.accessFlag);
	}

	public void setAccessFlag(String accessFlag) {
		this.accessFlag = accessFlag;
	}

	//
	public String getDbUsed() {
		return (this.dbUsed);
	}

	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}

	public String getChex_status_description() {
		return (this.chex_status_description);
	}

	public void setChex_status_description(String chex_status_description) {
		this.chex_status_description = chex_status_description;
	}

	public String getChex_check_status() {
		return (this.chex_check_status);
	}

	public void setChex_check_status(String chex_check_status) {
		this.chex_check_status = chex_check_status;
	}

	public String getChex_summ_amount() {
		return (this.chex_summ_amount);
	}

	public void setChex_summ_amount(String chex_summ_amount) {
		this.chex_summ_amount = chex_summ_amount;
	}

	public String getChex_check_count() {
		return (this.chex_check_count);
	}

	public void setChex_check_count(String chex_check_count) {
		this.chex_check_count = chex_check_count;
	}

	//
	public void clearErrors() {
		errorVec.clear();
	}

	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}

	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}

	/*
	 * public void setErrorVec (String errorString) {
	 * this.errorVec.add(errorString); }
	 */
	//
	protected void finalize() {
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> ChexDetail: MemoryBlock finalized: "+this );
	}
}
