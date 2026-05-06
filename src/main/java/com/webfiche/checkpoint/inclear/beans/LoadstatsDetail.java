package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:31:11 $
 */

public final class LoadstatsDetail implements Serializable {
	private static final long	   serialVersionUID		= 1L;
	// --------------------------------------------------- Instance Variables
	private String	actionFlag			= "";
	private String	accessFlag			= "";
	private String	dbUsed				= "";
	private String	appl_date			= "";
	private String	bankId				= "";
	private String	defCurr				= "";
	private String	totalChecks			= "";
	private boolean	newFile				= false; 
	private String	statId				= "";
	private String	statField1			= "";
	private String	statField2			= "";
	private String	statField3			= "";
	private String	statField4			= "";
	private String	statField5			= "";
	private String	statField6			= "";
	private String	statField7			= "";
	private String	statField8			= "";
	private String	statField9			= "";
	private String	statField10			= "";
	//
	private Vector<Vector<String>>  errorVec			= new Vector<Vector<String>>();
	//
	DecimalFormat	df					= new DecimalFormat("##,###,##0.00");
	//
	// private String moduleName = "> ReconDetail.";
	// ----------------------------------------------------------- Properties
	//
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag	= actionFlag;
	}
	//
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String accessFlag) {
		this.accessFlag	= accessFlag;
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
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId	= bankId;
	}
	//
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr	= defCurr;
	}
	//
	public String getTotalChecks() {
		return (this.totalChecks);
	}
	public void setTotalChecks(String totalChecks) {
		this.totalChecks	= totalChecks;
	}
	//
	public boolean getNewFile() {
		return (this.newFile);
	}
	public void setNewFile(boolean newFile) {
		this.newFile	= newFile;
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
		Vector<String> vecPair	= new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId	= statId;
	}
	//
	public String getStatField1() {
		return statField1;
	}
	public void setStatField1(String statField1) {
		this.statField1	= statField1;
	}
	//
	public String getStatField2() {
		return statField2;
	}
	public void setStatField2(String statField2) {
		this.statField2	= statField2;
	}
	//
	public String getStatField3() {
		return statField3;
	}
	public void setStatField3(String statField3) {
		this.statField3	= statField3;
	}
	//
	public String getStatField4() {
		return statField4;
	}
	public void setStatField4(String statField4) {
		this.statField4	= statField4;
	}
	//
	public String getStatField5() {
		return statField5;
	}
	public void setStatField5(String statField5) {
		this.statField5	= statField5;
	}
	//
	public String getStatField6() {
		return statField6;
	}
	public void setStatField6(String statField6) {
		this.statField6	= statField6;
	}
	//
	public String getStatField7() {
		return statField7;
	}
	public void setStatField7(String statField7) {
		this.statField7	= statField7;
	}
	//
	public String getStatField8() {
		return statField8;
	}
	public void setStatField8(String statField8) {
		this.statField8	= statField8;
	}
	//
	public String getStatField9() {
		return statField9;
	}
	public void setStatField9(String statField9) {
		this.statField9	= statField9;
	}
	//
	public String getStatField10() {
		return statField10;
	}
	public void setStatField10(String statField10) {
		this.statField10	= statField10;
		if (this.statField10.equals("SUMMARY")) {
			setNewFile(true);
		}
	}
	//
	public void ShowFields () {
		String tempStr	= this.statId + "\t" + 
							this.statField1 + "\t" + 
							this.statField2 + "\t" + 
							this.statField3 + "\t" + 
							this.statField4 + "\t" + 
							this.statField5 + "\t" + 
							this.statField6 + "\t" + 
							this.statField7 + "\t" + 
							this.statField8 + "\t" + 
							this.statField9 + "\t" + 
							this.statField10; 
		System.out.println(java.util.Calendar.getInstance().getTime() +
						"> viewStatsDb: "+ tempStr);

	}
}
