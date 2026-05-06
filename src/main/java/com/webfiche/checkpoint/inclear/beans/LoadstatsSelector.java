/*
 * ====================================================================
 */

package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

public final class LoadstatsSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID	= 1L;
	// -- Instance Variables
	private int detail_count	= 0;
	private String row_Count	= "0";
	private String actionFlag	= "";
	private String accessFlag	= "";
	private String dbUsed		= "";
	private String appl_date	= "";
	private String appl_date_f	= "";
	private String bankId		= "";
	private String defCurr		= "";
	private String totalChecks	= "";
	private ArrayList<LoadstatsDetail> statRows	= new ArrayList<LoadstatsDetail>();
	private Vector<Vector<String>> errorVec	= new Vector<Vector<String>>();
	// ----------------------------------------------------------- Properties
	//
	public void clearStatRows() {
		statRows.clear();
	}
	//
	public int getDetail_count() {
		return (this.detail_count);
	}
	public void setDetail_count(int detail_count) {
		this.detail_count	= detail_count;
	}
	//
	public String getRow_Count() {
		return (this.row_Count);
	}
	public void setRow_Count(String row_Count) {
		this.row_Count	= row_Count;
	}
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
	public String getAppl_date_f() {
		return (this.appl_date_f);
	}
	//
	public String getAppl_date() {
		return (this.appl_date);
	}
	// date		="112304";
	// new_date	="20"+date.substring(4,6)+date.substring(2,4)+date.substring(0,2);
	public void setAppl_date(String appl_date) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> ChexSelector: Appl_date " + appl_date);
		this.appl_date	= appl_date;
		if (!appl_date.equals("")) {
			this.appl_date_f	= (appl_date.substring(4, 6) + "/" +
								   appl_date.substring(6) + "/" + 
								   appl_date.substring(0, 4));
		}
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
	/*
	 * public void setErrorVec (String errorString) {
	 * this.errorVec.add(errorString); }
	 */
	//
	public LoadstatsDetail[] getStatRows() {
		LoadstatsDetail results[]		= new LoadstatsDetail[statRows.size()];
		Iterator<LoadstatsDetail> stat	= statRows.iterator();
		int n				= 0;
		while (stat.hasNext()) {
			results[n++]	= (LoadstatsDetail) stat.next();
		}
		System.out.println(java.util.Calendar.getInstance().getTime() + 
							"> LoadstatsSelector: size " + n);
		return (results);
	}
	//
	public ArrayList<LoadstatsDetail> getStatRowsArray() {
		return (this.statRows);
	}
	//
	public LoadstatsDetail getArow() {
		LoadstatsDetail results			= new LoadstatsDetail();
		Iterator<LoadstatsDetail> stat	= statRows.iterator();
		results							= (LoadstatsDetail) stat.next();
		return (results);
	}
	//
	public void setStatRows(ArrayList<LoadstatsDetail> statRow) {
		Iterator<LoadstatsDetail> rows	= statRow.iterator();
		int n	= 0;
		while (rows.hasNext()) {
			this.statRows.add(n, (LoadstatsDetail) rows.next());
			n++;
		}
		detail_count	= n;
	}
	//
	protected void finalize() {
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> ChexSelector: MemoryBlock finalized: "+this );
	}
}
