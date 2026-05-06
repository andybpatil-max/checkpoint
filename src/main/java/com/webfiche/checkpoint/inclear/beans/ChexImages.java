package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:31:11 $
 */

public final class ChexImages implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String applDate = "";
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	private String imageDate = "";
	private int imageCount = 0;
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();

	// ----------------------------------------------------------- Properties
	//
	public String getApplDate() {
		return (this.applDate);
	}

	public void setApplDate(String applDate) {
		this.applDate = applDate;
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

	//
	public String getImageDate() {
		return (this.imageDate);
	}

	public void setImageDate(String imageDate) {
		this.imageDate = imageDate;
	}

	//
	public int getImageCount() {
		return (this.imageCount);
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public void clearErrors() {
		errorVec.clear();
	}

	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}

	/*
	 * public void setErrorVec (String errorString) {
	 * this.errorVec.add(errorString); }
	 */
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}

	//
	//
	protected void finalize() {
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> ChexDetail: MemoryBlock finalized: "+this );
	}
}
