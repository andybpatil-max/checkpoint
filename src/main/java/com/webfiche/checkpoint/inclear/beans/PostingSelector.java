/*
 * ====================================================================
 */

package com.webfiche.checkpoint.inclear.beans;

import java.io.*;
import java.util.*;

public final class PostingSelector implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID  = 1L;
	// -- Instance Variables
	//private String	className		= "> PostingSelector.";
	//private String	moduleName		= "";
	//private String	param			= "";
	private int		detail_count		= 0;
	private int		recIndex			= 0;
	private String	row_Count			= "0";
	private String	detailAmount		= "";
	private String	actionFlag			= "";
	private String	accessFlag			= "";
	private String	imageLookup			= "";
	private String	dbUsed				= "";
	private String	dbTable				= "incl_chex";
	private String	applDate			= "";
	private String	applDate_f			= "";
	private String	bankId				= "";
	private String	defCurr				= "";
	private String	chexSource			= "";
	private String	allow_eod			= "N";
	private String	eod_status			= "";
	private String	bod_status			= "";
	private String	summ_total_amount	= "";
	private String	summ_total_checks	= "";
	private ArrayList<PostingDetail>  postingRows		= new ArrayList<PostingDetail>();
	private ArrayList<PostingDetail>  ddaPostings		= new ArrayList<PostingDetail>();
	private Vector<Vector<String>> errorVec				= new Vector<Vector<String>>();
	// Key Account-Num Value AccountDetail
	private TreeMap<String, AccountDetail>	  acctList	= new TreeMap<String, AccountDetail>();
	// Key PayerAba value RDC, LBOX, NEXTDAY
	private TreeMap<String, String> payerList			= new TreeMap<String, String>();
	//
	// Here deifne variables for controlling the number of checks displayed.
	// Use the checksPerView constant defined in web.xml
	//
	private int		rowStart			= 0;
	private int		rowEnd				= 20;
	private int		rowsDisplayed		= 20;
	private String	rowsDispStr	 		= "";
	// ----------------------------------------------------------- Properties
	/*
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	*/
	public void clearRows() {
		postingRows.clear();
		this.detail_count	= 0;
	}
	public void clearDdas() {
		ddaPostings.clear();
	}
	public void clearAcctList() {
		acctList.clear();
	}
	public void clearPayerList() {
		payerList.clear();
	}
	//
	public int getDetail_count() {
		return (this.detail_count);
	}
	public void setDetail_count(int detail_count) {
		this.detail_count = detail_count;
		//setRowsDisplayed(this.detail_count);
	}
	//
	public int getRecIndex() {
		return (this.recIndex);
	}
	public void setRecIndex(int recIndex) {
		this.recIndex = recIndex;
	}
	//
	public String getRow_Count() {
		return (this.row_Count);
	}
	public void setRow_Count(String row_Count) {
		this.row_Count = row_Count;
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
	public String getImageLookup() {
		return (this.imageLookup);
	}
	public void setImageLookup(String imageLookup) {
		this.imageLookup = imageLookup;
	}
	//
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getDbTable() {
		return (this.dbTable);
	}
	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}
	//
	public String getApplDate_f() {
		return (this.applDate_f);
	}
	//
	public String getApplDate() {
		return (this.applDate);
	}
	// date="112304";
	// new_date=
	// "20"+date.substring(4,6)+date.substring(2,4)+date.substring(0,2);
	public void setApplDate(String applDate) {
		//System.out.println(java.util.Calendar.getInstance().getTime() +
		//					"> ChexSelector: Appl_date " + applDate);
		this.applDate = applDate;
		if (!applDate.equals("")) {
			this.applDate_f = (applDate.substring(4, 6) + "/" +
								applDate.substring(6) + "/" + applDate.substring(0, 4));
		}
	}
	//
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	//
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public String getChexSource() {
		return (this.chexSource);
	}
	public void setChexSource(String chexSource) {
		this.chexSource = chexSource;
	}
	//
	public String getAllow_eod() {
		return (this.allow_eod);
	}
	public void setAllow_eod(String allow_eod) {
		this.allow_eod = allow_eod;
	}
	//
	public String getEod_status() {
		return (this.eod_status);
	}
	public void setEod_status(String eod_status) {
		this.eod_status = eod_status;
	}
	//
	public String getBod_status() {
		return (this.bod_status);
	}
	public void setBod_status(String bod_status) {
		this.bod_status = bod_status;
	}
	//
	public String getSumm_total_amount() {
		return (this.summ_total_amount);
	}
	public void setSumm_total_amount(String summ_total_amount) {
		this.summ_total_amount = summ_total_amount;
	}
	//
	public String getSumm_total_checks() {
		return (this.summ_total_checks);
	}
	public void setSumm_total_checks(String summ_total_checks) {
		this.summ_total_checks = summ_total_checks;
	}
	//
	public String getDetailAmount() {
		return (this.detailAmount);
	}
	public void setDetailAmount(String detailAmount) {
		this.detailAmount = detailAmount;
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
	//
	public void setAcctList(TreeMap<String, AccountDetail> acctList) {
		clearAcctList();
		/*
		for (int i = 0; i < acctList.size(); i++) {
			this.acctList.add((String) acctList.get(i));
		}
		*/
		this.acctList	= acctList;
	}
	public TreeMap<String, AccountDetail> getAcctList() {
		return (this.acctList);
	}
	public AccountDetail getAcctRow (String acctKey) {
		return (this.acctList.get(acctKey));
	}
	//
	public void setPayerList(TreeMap<String, String> payerList) {
		clearPayerList();
		this.payerList	= payerList;
	}
	public TreeMap<String, String> getPayerList() {
		return (this.payerList);
	}
	public String getPayerValue(String payerKey) {
		return (this.payerList.get(payerKey));
	}
	//
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	public int getRowStart() {
		return (this.rowStart);
	}
	//
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	public int getRowEnd() {
		return (this.rowEnd);
	}
	//
	public void setRowsDisplayed(int rowsDisplayed) {
		//moduleName	= "setRowsDisplayed: ";
		//PrintLog("rowsDisplayed: " + rowsDisplayed);
		if (rowsDisplayed <= 500) {
			this.rowsDisplayed	= rowsDisplayed;
		} else {
			this.rowsDisplayed	= 500;
		}
		if (this.rowsDisplayed > this.detail_count) {
			this.rowsDisplayed	= this.detail_count;
		}
		setRowsDispStr(""+this.rowsDisplayed);
	}
	public int getRowsDisplayed() {
		return (this.rowsDisplayed);
	}
	//
	public void setRowsDispStr(String rowsDispStr) {
		//moduleName	= "setRowsDispStr: ";
		//PrintLog("rowsDispStr: " + rowsDispStr);
		if (Integer.parseInt(rowsDispStr) <= 500) {
			this.rowsDispStr	= rowsDispStr;
		} else {
			this.rowsDispStr	= "500";
		}
		this.rowsDisplayed = Integer.parseInt(this.rowsDispStr);
		if (this.rowsDisplayed > this.detail_count) {
			this.rowsDisplayed	= this.detail_count;
			this.rowsDispStr	= this.detail_count + "";
		}
		//PrintLog("rowsDispStr: " + this.rowsDispStr);
	}
	public String getRowsDispStr() {
		return (this.rowsDisplayed + "");
	}
	//
	public void setRowSEC(int rowStart, int rowEnd, int rowCount) {
		this.rowStart		= rowStart;
		this.rowEnd			= rowEnd;
		this.rowsDisplayed	= rowCount;
	}
	//
	public void InitRowSEC() {
		this.rowStart		= 0;
		this.rowEnd			= 20;
		this.rowsDisplayed	= 20;
	}
	//
	public PostingDetail[] getDdaPostings() {
		//moduleName					= "getDdaPostings: ";
		PostingDetail results[]			= new PostingDetail[ddaPostings.size()];
		Iterator<PostingDetail> posting	= ddaPostings.iterator();
		int n	= 0;
		while (posting.hasNext()) {
			results[n++]	= (PostingDetail) posting.next();
		}
		// PrintLog("Detail count "+n);
		return (results);
	}
	//
	public ArrayList<PostingDetail> getDdaPostingsArray() {
		return (this.ddaPostings);
	}
	//
	public PostingDetail getAPosting() {
		PostingDetail results = new PostingDetail();
		Iterator<PostingDetail> chex = ddaPostings.iterator();
		results = (PostingDetail) chex.next();
		return (results);
	}
	//
	public PostingDetail getAPosting(int recIndex) {
		PostingDetail results = new PostingDetail();
		// Iterator chex = postingRows.iterator();
		this.recIndex = recIndex;
		results = (PostingDetail) ddaPostings.get(recIndex);
		return (results);
	}
	//
	public void setDdaPostings(ArrayList<PostingDetail> ddaPosting) {
		ddaPostings.clear();
		Iterator<PostingDetail> rows = ddaPostings.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.ddaPostings.add(n, (PostingDetail) rows.next());
			n++;
		}
		//PrintLog("Checks Count: "+n);
		detail_count = n;
	}
	//
	public PostingDetail[] getPostingRows() {
		//moduleName				= "getPostingRows: ";
		PostingDetail results[]	= new PostingDetail[postingRows.size()];
		Iterator<PostingDetail> posting	= postingRows.iterator();
		int n = 0;
		while (posting.hasNext()) {
			results[n++] = (PostingDetail) posting.next();
		}
		// PrintLog("Detail count "+n);
		return (results);
	}
	//
	public ArrayList<PostingDetail> getPostingRowsArray() {
		return (this.postingRows);
	}
	//
	public PostingDetail getArow() {
		PostingDetail results = new PostingDetail();
		Iterator<PostingDetail> chex = postingRows.iterator();
		results = (PostingDetail) chex.next();
		return (results);
	}
	//
	public PostingDetail getArow(int recIndex) {
		PostingDetail aRow	= new PostingDetail();
		// Iterator chex = postingRows.iterator();
		this.recIndex = recIndex;
		aRow	= (PostingDetail) postingRows.get(recIndex);
		return (aRow);
	}
	//
	public void setPostingRows(ArrayList<PostingDetail> postingRow) {
		postingRows.clear();
		Iterator<PostingDetail> rows = postingRow.iterator();
		int n = 0;
		while (rows.hasNext()) {
			this.postingRows.add(n, (PostingDetail) rows.next());
			n++;
		}
		//PrintLog("Checks Count: "+n);
		detail_count = n;
	}
	//
	public void SetNextRows() {
		if (this.rowEnd < detail_count) {
			this.rowStart	= this.rowEnd;
			this.rowEnd		= this.rowEnd + this.rowsDisplayed;
			if (this.rowEnd > this.detail_count) {
				this.rowEnd	= this.detail_count;
			}
		}
	}
	//
	public void SetPrevRows() {
		if (this.rowStart >= this.rowsDisplayed) {
			this.rowEnd = this.rowStart;
			this.rowStart = this.rowStart - this.rowsDisplayed;
			if (this.rowStart < 0) {
				this.rowStart = 0;
			}
		} else {
			if (this.rowStart < this.rowsDisplayed) {
				this.rowStart = 0;
				this.rowEnd = this.rowsDisplayed;
			}
		}
	}
}
