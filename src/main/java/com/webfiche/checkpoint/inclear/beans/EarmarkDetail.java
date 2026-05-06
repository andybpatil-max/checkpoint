package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
//import java.util.*;

public final class EarmarkDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String processDate		= "";
	private String actionFlag		= "";
	private String file2Release		= "";
	//
	public String getProcessDate() {
		return processDate;
	}
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
	//
	public String getActionFlag() {
		return actionFlag;
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	//
	public void setFile2Release (String f2Rel) {
		this.file2Release	= f2Rel;
	}
	public String getFile2Release() {
		return (this.file2Release);
	}
}
