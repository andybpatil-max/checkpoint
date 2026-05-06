/*
 * ====================================================================
 */

package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

public final class ReconSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID	= 1L;
	// -- Instance Variables
	private int detail_count	= 0;
	private String row_Count	= "0";
	private String actionFlag	= "";
	private String accessFlag	= "";
	private String imageLookup	= "";
	private String dbUsed		= "";
	private String appl_date	= "";
	private String appl_date_f	= "";
	private String bankId		= "";
	private String defCurr		= "";
	private ArrayList<ReconDetail> reconrows	= new ArrayList<ReconDetail>();
	private String imageURL		= "";
	private String imageDir		= "";
	private String pdfDir		= "";
	private String monthStart	= "";
	private String monthEnd		= "";
	private String weekStart	= "";
	private String weekEnd		= "";
	private String dailyDate	= "";
	private Vector<Vector<String>> errorVec	= new Vector<Vector<String>>();
	// ----------------------------------------------------------- Properties
	//
	public void clearReconrows() {
		reconrows.clear();
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
	public String getImageLookup() {
		return (this.imageLookup);
	}
	public void setImageLookup(String imageLookup) {
		this.imageLookup	= imageLookup;
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
	// date="112304";
	// new_date=
	// "20"+date.substring(4,6)+date.substring(2,4)+date.substring(0,2);
	public void setAppl_date(String appl_date) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ "> ChexSelector: Appl_date " + appl_date);
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
	// For use with the JSP
	public String getImageURL() {
		return (this.imageURL);
	}
	public void setImageURL(String imageURL) {
		this.imageURL	= imageURL;
	}
	// For use to lookup image files on the disk
	public String getImageDir() {
		return (this.imageDir);
	}
	public void setImageDir(String imageDir) {
		this.imageDir	= imageDir;
	}
	// Directory where PDFs will be generated
	public String getPdfDir() {
		return (this.pdfDir);
	}
	public void setPdfDir(String pdfDir) {
		this.pdfDir	= pdfDir;
	}
	// Start of month for eMail, Fax and Mail
	public String getMonthStart() {
		return (this.monthStart);
	}
	public void setMonthStart(String monthStart) {
		this.monthStart	= monthStart;
	}
	// End of month for eMail, Fax and Mail
	public String getMonthEnd() {
		return (this.monthEnd);
	}
	public void setMonthEnd(String monthEnd) {
		this.monthEnd	= monthEnd;
	}
	// Start of Week for eMail, Fax and Mail
	public String getWeekStart() {
		return (this.weekStart);
	}
	public void setWeekStart(String weekStart) {
		this.weekStart	= weekStart;
	}
	// End of month for eMail, Fax and Mail
	public String getWeekEnd() {
		return (this.weekEnd);
	}
	public void setWeekEnd(String weekEnd) {
		this.weekEnd	= weekEnd;
	}
	// Daily Date for eMail, Fax and Mail
	public String getDailyDate() {
		return (this.dailyDate);
	}
	public void setDailyDate(String dailyDate) {
		this.dailyDate	= dailyDate;
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
	public ReconDetail[] getReconrows() {
		ReconDetail results[]		= new ReconDetail[reconrows.size()];
		Iterator<ReconDetail> recon	= reconrows.iterator();
		int n						= 0;
		while (recon.hasNext()) {
			results[n++]	= (ReconDetail) recon.next();
		}
		System.out.println(java.util.Calendar.getInstance().getTime() + 
							"> ReconSelector: size " + n);
		return (results);
	}
	//
	public ArrayList<ReconDetail> getReconRowsArray() {
		return (this.reconrows);
	}
	//
	public ReconDetail getArow() {
		ReconDetail results			= new ReconDetail();
		Iterator<ReconDetail> recon	= reconrows.iterator();
		results						= (ReconDetail) recon.next();
		return (results);
	}
	//
	public void setReconrows(ArrayList<?> recon_row) {
		Iterator<?> rows	= recon_row.iterator();
		int n	= 0;
		while (rows.hasNext()) {
			this.reconrows.add(n, (ReconDetail) rows.next());
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
