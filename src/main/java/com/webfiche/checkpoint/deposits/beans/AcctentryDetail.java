package com.webfiche.checkpoint.deposits.beans;

import java.io.Serializable;
//import java.sql.*;
import java.util.*;
import java.text.*;

//import com.webfiche.checkpoint.util.*;
//import com.webfiche.checkpoint.inclear.service.InclAcctUtil;

/*
	@author Andy Patil
	@version $Revision: 1.2 $ $Date: 2016/04/09 14:33:07 $
*/
public final class AcctentryDetail implements Serializable {
	/**
	 */
	private static final long	   serialVersionUID		= 1L;
	// --------------------------------------------------- Instance Variables
	private String	className				= "> PostingDetail.";
	private String	moduleName				= "";
	private String	userId					= "";
	private String	actionFlag				= "";
	private String	accessFlag				= "";
	private String	dbUsed					= "";
	private String	dbTable					= "deps_chex";
	private String	appl_date				= "";
	private String	bankId					= "";
	private String	defCurr					= "";
	private String	postingBusinessdate		= "";			// 6 Current Business Date Export Date
	private String	postingApplPrefix		= "";			// 2 RP
	private String	postingBranch			= "150";		// 3
	private String	postingSeqNum			= "";			// 5
	private String	postingRecStatus		= "2";			// 1
	private String	postingAmount			= "";			// 15
	private String	postingCurrency			= "USD";		// 3
	private String	postingDebitAcct		= "150930161500USD";	// 15
	private String	postingCreditAcct		= "";			// 15 
	private String	postingDebitValueDate	= "";			// 6  Date When Batch was scanned RUN DATE
	private String	postingCreditValueDate	= "";			// 6  Based on availability Schedule
	private String	postingReasonCode		= "250";		// 3  Check Deposit LBOX=250 RDC=225
	private String	postingReqExeDate		= "";			// 6  postingBusinessdate
	private String	postingTranRefNum		= "";			// 16 "RP"+"YYYYMMDD" + "nnnnnnn"
	private String	postingRefRelTran		= "";			// 16 "BATCH"+"nnnnn" + "nnnnnnn"
	private String	postingDetailsOfPayment	= "";			// LBOX="TOTAL CHECK DEPOSITS" RDC="REMOTE DEPOSIT"
	private String	postingBBI				= "";			// 210 spaces
	private String	postingTotalDebits		= "";			// 4
	private String	postingTotalCredits		= "";			// 4
	private String	postingTimeStamp		= "";			// 15  YYMMDD_HHMMSSCC
	private String	postingBookingText		= "";			// 210 spaces
	private String	postingOriginalCurrency	= "";			// 3   postingCurrency
	private String	postingOriginalAmount	= "";			// 15  postingAmount
	private String	postingCheckNum			= "";			// 16  spaces
	private String	postingBeneficiary		= "";			// 140 spaces
	private String	postingOBKName			= "";			// 35  spaces
	private String	postingOBKAddress		= "";			// 105 spaces
	private String	postingLocalKey			= "";			// 10  spaces
	private String	postingFiller			= "";			// 475 spaces
	private Vector<Vector<String>>  errorVec				= new Vector<Vector<String>>();
	//
	DecimalFormat				   df					  = new DecimalFormat("###,##0.00");
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + userId + logMsg);
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
	// ----------------------------------------------------------- Properties
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
	public String getDbTable() {
		return (this.dbTable);
	}
	public void setDbTable(String dbTable) {
		this.dbTable = dbTable;
	}
	//
	public String getAppl_date() {
		return (this.appl_date);
	}
	public void setAppl_date(String appl_date) {
		this.appl_date = appl_date;
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
	/*
	public void setChex_proc_date(String chex_proc_date) {
		// PrintLog("setChex_proc_date: "+chex_proc_date);
		this.chex_proc_date = chex_proc_date;
		this.chex_proc_date_disp = (chex_proc_date.substring(4, 6) + "/"
				+ chex_proc_date.substring(6) + "/" + chex_proc_date.substring(
				0, 4));
	}
	*/
	public String getPostingBusinessdate() {
		return postingBusinessdate;
	}
	public void setPostingBusinessdate(String postingBusinessdate) {
		this.postingBusinessdate = postingBusinessdate;
	}
	//
	public String getPostingApplPrefix() {
		return postingApplPrefix;
	}
	public void setPostingApplPrefix(String postingApplPrefix) {
		this.postingApplPrefix = postingApplPrefix;
	}
	//
	public String getPostingBranch() {
		return postingBranch;
	}
	public void setPostingBranch(String postingBranch) {
		this.postingBranch = postingBranch;
	}
	//
	public String getPostingSeqNum() {
		return postingSeqNum;
	}
	public void setPostingSeqNum(String postingSeqNum) {
		this.postingSeqNum = postingSeqNum;
	}
	//
	public String getPostingRecStatus() {
		return postingRecStatus;
	}
	public void setPostingRecStatus(String postingRecStatus) {
		this.postingRecStatus = postingRecStatus;
	}
	//
	public String getPostingAmount() {
		return postingAmount;
	}
	public void setPostingAmount(String postingAmount) {
		this.postingAmount = postingAmount;
	}
	//
	public String getPostingCurrency() {
		return postingCurrency;
	}
	public void setPostingCurrency(String postingCurrency) {
		this.postingCurrency = postingCurrency;
	}
	//
	public String getPostingDebitAcct() {
		return postingDebitAcct;
	}
	public void setPostingDebitAcct(String postingDebitAcct) {
		this.postingDebitAcct = postingDebitAcct;
	}
	//
	public String getPostingCreditAcct() {
		return postingCreditAcct;
	}
	public void setPostingCreditAcct(String postingCreditAcct) {
		this.postingCreditAcct = postingCreditAcct;
	}
	//
	public String getPostingDebitValueDate() {
		return postingDebitValueDate;
	}
	public void setPostingDebitValueDate(String postingDebitValueDate) {
		this.postingDebitValueDate = postingDebitValueDate;
	}
	//
	public String getPostingCreditValueDate() {
		return postingCreditValueDate;
	}
	public void setPostingCreditValueDate(String postingCreditValueDate) {
		this.postingCreditValueDate = postingCreditValueDate;
	}
	//
	public String getPostingReasonCode() {
		return postingReasonCode;
	}
	public void setPostingReasonCode(String postingReasonCode) {
		this.postingReasonCode = postingReasonCode;
	}
	//
	public String getPostingReqExeDate() {
		return postingReqExeDate;
	}
	public void setPostingReqExeDate(String postingReqExeDate) {
		this.postingReqExeDate = postingReqExeDate;
	}
	//
	public String getPostingTranRefNum() {
		return postingTranRefNum;
	}
	public void setPostingTranRefNum(String postingTranRefNum) {
		this.postingTranRefNum	= postingTranRefNum;
	}
	//
	public String getPostingRefRelTran() {
		return postingRefRelTran;
	}
	public void setPostingRefRelTran(String postingRefRelTran) {
		this.postingRefRelTran	= postingRefRelTran;
	}
	//
	public String getPostingDetailsOfPayment() {
		return postingDetailsOfPayment;
	}
	public void setPostingDetailsOfPayment(String postingDetailsOfPayment) {
		this.postingDetailsOfPayment = postingDetailsOfPayment;
	}
	//
	public String getPostingBBI() {
		return postingBBI;
	}
	public void setPostingBBI(String postingBBI) {
		this.postingBBI = postingBBI;
	}
	//
	public String getPostingTotalDebits() {
		return postingTotalDebits;
	}
	public void setPostingTotalDebits(String postingTotalDebits) {
		this.postingTotalDebits = postingTotalDebits;
	}
	//
	public String getPostingTotalCredits() {
		return postingTotalCredits;
	}
	public void setPostingTotalCredits(String postingTotalCredits) {
		this.postingTotalCredits = postingTotalCredits;
	}
	//
	public String getPostingTimeStamp() {
		return postingTimeStamp;
	}
	public void setPostingTimeStamp(String postingTimeStamp) {
		this.postingTimeStamp = postingTimeStamp;
	}
	//
	public String getPostingBookingText() {
		return postingBookingText;
	}
	public void setPostingBookingText(String postingBookingText) {
		this.postingBookingText = postingBookingText;
	}
	//
	public String getPostingOriginalCurrency() {
		return postingOriginalCurrency;
	}
	public void setPostingOriginalCurrency(String postingOriginalCurrency) {
		this.postingOriginalCurrency = postingOriginalCurrency;
	}
	//
	public String getPostingOriginalAmount() {
		return postingOriginalAmount;
	}
	public void setPostingOriginalAmount(String postingOriginalAmount) {
		this.postingOriginalAmount = postingOriginalAmount;
	}
	//
	public String getPostingCheckNum() {
		return postingCheckNum;
	}
	public void setPostingCheckNum(String postingCheckNum) {
		this.postingCheckNum = postingCheckNum;
	}
	//
	public String getPostingBeneficiary() {
		return postingBeneficiary;
	}
	public void setPostingBeneficiary(String postingBeneficiary) {
		this.postingBeneficiary = postingBeneficiary;
	}
	//
	public String getPostingOBKName() {
		return postingOBKName;
	}
	public void setPostingOBKName(String postingOBKName) {
		this.postingOBKName = postingOBKName;
	}
	//
	public String getPostingOBKAddress() {
		return postingOBKAddress;
	}
	public void setPostingOBKAddress(String postingOBKAddress) {
		this.postingOBKAddress = postingOBKAddress;
	}
	//
	public String getPostingLocalKey() {
		return postingLocalKey;
	}
	public void setPostingLocalKey(String postingLocalKey) {
		this.postingLocalKey = postingLocalKey;
	}
	//
	public String getPostingFiller() {
		return postingFiller;
	}
	public void setPostingFiller(String postingFiller) {
		this.postingFiller = postingFiller;
	}
	//
	public void ShowDetails() {
		PrintLog("postingBusinessdate\t\t" + postingBusinessdate);
		PrintLog("postingApplPrefix\t\t" + postingApplPrefix);
		PrintLog("postingBranch\t\t" + postingBranch);
		PrintLog("postingSeqNum\t\t" + postingSeqNum);
		PrintLog("postingRecStatus\t\t" + postingRecStatus);
		PrintLog("postingAmount\t\t" + postingAmount);
		PrintLog("postingCurrency\t\t" + postingCurrency);
		PrintLog("postingDebitAcct\t\t" + postingDebitAcct);
		PrintLog("postingCreditAcct\t\t" + postingCreditAcct);
		PrintLog("postingDebitValueDate\t\t" + postingDebitValueDate);
		PrintLog("postingCreditValueDate\t\t" + postingCreditValueDate);
		PrintLog("postingReasonCode\t\t" + postingReasonCode);
		PrintLog("postingReqExeDate\t\t" + postingReqExeDate);
		PrintLog("postingTranRefNum\t\t" + postingTranRefNum);
		PrintLog("postingDetailsOfPayment\t\t" + postingDetailsOfPayment);
		//PrintLog("postingBBI\t\t" + postingBBI);
		PrintLog("postingTotalDebits\t\t" + postingTotalDebits);
		PrintLog("postingTotalCredits\t\t" + postingTotalCredits);
		PrintLog("postingTimeStamp\t\t" + postingTimeStamp);
		//PrintLog("postingBookingText\t\t" + postingBookingText);
		PrintLog("postingOriginalCurrency\t\t" + postingOriginalCurrency);
		PrintLog("postingOriginalAmount\t\t" + postingOriginalAmount);
		//PrintLog("postingCheckNum\t\t" + postingCheckNum);
		//PrintLog("postingBeneficiary\t\t" + postingBeneficiary);
		//PrintLog("postingOBKName\t\t" + postingOBKName);
		//PrintLog("postingOBKAddress\t\t" + postingOBKAddress);
		//PrintLog("postingLocalKey\t\t" + postingLocalKey);
		//PrintLog("postingFiller\t\t" + postingFiller);
	}
	//
	public void PrintDetails() {
		PrintLog(postingBusinessdate + 
				 "\t" + postingApplPrefix + 
				 "\t" + postingBranch + 
				 "\t" + postingSeqNum + 
				 "\t" + postingRecStatus + 
				 "\t" + postingAmount + 
				 "\t" + postingCurrency + 
				 "\t" + postingDebitAcct + 
				 "\t" + postingCreditAcct + 
				 "\t" + postingDebitValueDate + 
				 "\t" + postingCreditValueDate + 
				 "\t" + postingReasonCode + 
				 "\t" + postingReqExeDate + 
				 "\t" + postingTranRefNum + 
				 "\t" + postingDetailsOfPayment + 
				 "\t" + postingTotalDebits + 
				 "\t" + postingTotalCredits + 
				 "\t" + postingTimeStamp + 
				 "\t" + postingOriginalCurrency + 
				 "\t" + postingOriginalAmount);
	}
	//
	public void clearNulls() {
		postingBusinessdate		= (postingBusinessdate == null) ? " " : postingBusinessdate;
		postingApplPrefix		= (postingApplPrefix == null) ? " " : postingApplPrefix;
		postingBranch			= (postingBranch == null) ? " " : postingBranch;
		postingSeqNum			= (postingSeqNum == null) ? " " : postingSeqNum;
		postingRecStatus		= (postingRecStatus == null) ? " " : postingRecStatus;
		postingAmount			= (postingAmount == null) ? " " : postingAmount;
		postingCurrency			= (postingCurrency == null) ? " " : postingCurrency;
		postingDebitAcct		= (postingDebitAcct == null) ? " " : postingDebitAcct;
		postingCreditAcct		= (postingCreditAcct == null) ? " " : postingCreditAcct;
		postingDebitValueDate	= (postingDebitValueDate == null) ? " " : postingDebitValueDate;
		postingCreditValueDate	= (postingCreditValueDate == null) ? " " : postingCreditValueDate;
		postingReasonCode		= (postingReasonCode == null) ? " " : postingReasonCode;
		postingReqExeDate		= (postingReqExeDate == null) ? " " : postingReqExeDate;
		postingTranRefNum		= (postingTranRefNum == null) ? " " : postingTranRefNum;
		postingDetailsOfPayment	= (postingDetailsOfPayment == null) ? " " : postingDetailsOfPayment;
		//postingBBI			= (postingBBI == null) ? " " : postingBBI;
		postingTotalDebits		= (postingTotalDebits == null) ? " " : postingTotalDebits;
		postingTotalCredits		= (postingTotalCredits == null) ? " " : postingTotalCredits;
		postingTimeStamp		= (postingTimeStamp == null) ? " " : postingTimeStamp;
		//postingBookingText	= (postingBookingText == null) ? " " : postingBookingText;
		postingOriginalCurrency	= (postingOriginalCurrency == null) ? " " : postingOriginalCurrency;
		postingOriginalAmount	= (postingOriginalAmount == null) ? " " : postingOriginalAmount;
		//postingCheckNum		= (postingCheckNum == null) ? " " : postingCheckNum;
		//postingBeneficiary	= (postingBeneficiary == null) ? " " : postingBeneficiary;
		//postingOBKName		= (postingOBKName == null) ? " " : postingOBKName;
		//postingOBKAddress		= (postingOBKAddress == null) ? " " : postingOBKAddress;
		//postingLocalKey		= (postingLocalKey == null) ? " " : postingLocalKey);
		//postingFiller			= (postingFiller == null) ? " " : postingFiller);
	}
	public void clearDetails() {
		postingBusinessdate		= "";
		postingApplPrefix		= "";
		postingBranch			= "150";
		postingSeqNum			= "";
		postingRecStatus		= "2";
		postingAmount			= "";
		postingCurrency			= "USD";
		postingDebitAcct		= "150930161500USD";
		postingCreditAcct		= "";
		postingDebitValueDate	= "";
		postingCreditValueDate	= "";
		postingReasonCode		= "250";
		postingReqExeDate		= "";
		postingTranRefNum		= "";
		postingDetailsOfPayment	= "";
		//postingBBI			= "";
		postingTotalDebits		= "";
		postingTotalCredits		= "";
		postingTimeStamp		= "";
		//postingBookingText	= "";
		postingOriginalCurrency	= "";
		postingOriginalAmount	= "";
		//postingCheckNum		= "";
		//postingBeneficiary	= "";
		//postingOBKName		= "";
		//postingOBKAddress		= "";
		//postingLocalKey		= "";
		//postingFiller			= "";
	}
}
