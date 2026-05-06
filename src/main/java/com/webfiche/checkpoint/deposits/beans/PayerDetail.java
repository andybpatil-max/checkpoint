package com.webfiche.checkpoint.deposits.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.2 $ $Date: 2017/01/22 00:19:45 $
 */

public final class PayerDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID			= 1L;
	// --------------------------------------------------- Instance Variables
	private String	className			= "> AccountDetail.";
	private String	moduleName			= "";
	private String	actionFlag			= "";
	private String	accessFlag			= "";
	private String	dbUsed				= "";
	private String	appl_date			= "";
	private String	bankId				= "";
	private String	userId				= "";
	private String	prodId				= "";
	private String	defCurr				= "";
	//
	private String	payersource			= "";
	private String	payeraba			= "";
	private String	payeracct			= "";
	private String	payername			= "";
	private String	payercountry		= "";
	private String	payernextday		= "";
	private String	payerbank			= "";
	private String	modtime				= "";
	private String	moduser				= "";
	private String	modfunc				= "";
	//
	private String	payersource_o		= "";
	private String	payeraba_o			= "";
	private String	payeracct_o			= "";
	private String	payername_o			= "";
	private String	payercountry_o		= "";
	private String	payernextday_o		= "";
	private String	payerbank_o			= "";
	//
	//
	private Vector<Vector<String>> errorVec					= new Vector<Vector<String>>();
	//
	DecimalFormat	 df			 = new DecimalFormat("###,##0.00");
	GenUtil gUtil			= new GenUtil();
	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + userId + " - " + logMsg);
	}
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
	public String getUserId() {
		return (this.userId);
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//
	public String getProdId() {
		return (this.prodId);
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	//
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	// Payer Row fields
	//
	//
	public String getPayersource() {
		return payersource;
	}
	public void setPayersource(String payersource) {
		this.payersource = payersource;
	}
	//
	public String getPayeraba() {
		return payeraba;
	}
	public void setPayeraba(String payeraba) {
		if (payeraba!=null) {
			if (payeraba.equals(" ") ||
				!payeraba.equals(" ")) { 
				this.payeraba = String.format("%09d", Long.parseLong(payeraba));
			} else {
				this.payeraba = payeraba;
			}
		} else {
			this.payeraba = payeraba;
		}
		//this.payeraba = payeraba;
	}
	//
	public String getPayeracct() {
		return payeracct;
	}
	public void setPayeracct(String payeracct) {
		this.payeracct = payeracct;
	}
	//
	public String getPayername() {
		return payername;
	}
	public void setPayername(String payername) {
		this.payername = payername;
	}
	//
	public String getPayercountry() {
		return this.payercountry;
	}
	public void setPayercountry(String payercountry) {
		this.payercountry = payercountry;
	}
	//
	public String getPayernextday() {
		return this.payernextday;
	}
	public void setPayernextday(String payernextday) {
		this.payernextday = payernextday;
	}
	//
	public String getPayerbank() {
		return payerbank;
	}
	public void setPayerbank(String payerbank) {
		this.payerbank = payerbank;
	}
	//
	// Saved fields
	//
	public String getPayersource_o() {
		return payersource_o;
	}
	public void setPayersource_o(String payersource_o) {
		this.payersource_o = payersource_o;
	}
	//
	public String getPayeraba_o() {
		return payeraba_o;
	}
	public void setPayeraba_o(String payeraba_o) {
		this.payeraba_o = payeraba_o;
	}
	//
	public String getPayeracct_o() {
		return payeracct_o;
	}
	public void setPayeracct_o(String payeracct_o) {
		this.payeracct_o = payeracct_o;
	}
	//
	public String getPayername_o() {
		return payername_o;
	}
	public void setPayername_o(String payername_o) {
		this.payername_o = payername_o;
	}
	//
	public String getPayercountry_o() {
		return payercountry_o;
	}
	public void setPayercountry_o(String payercountry_o) {
		this.payercountry_o = payercountry_o;
	}
	//
	public String getPayernextday_o() {
		return payernextday_o;
	}
	public void setPayernextday_o(String payernextday_o) {
		this.payernextday_o = payernextday_o;
	}
	//
	public String getPayerbank_o() {
		return payerbank_o;
	}
	public void setPayerbank_o(String payerbank_o) {
		this.payerbank_o = payerbank_o;
	}
	//
	//
	public String getModtime() {
		return (this.modtime);
	}
	public void setModtime(String modtime) {
		moduleName = "setModtime: ";
		// PrintLog(account_last_modified);
		int space_at = modtime.indexOf(" ");
		if (!modtime.equals("") && modtime.length() > 15) {
			this.modtime = (modtime.substring(0,
					space_at) + " @ " + modtime.substring(
					space_at + 1, modtime.length()));
		} else if (!modtime.equals("") && modtime.length() > 13) {
			this.modtime = modtime.substring(0, 4)
					+ "/";
			this.modtime += modtime.substring(4, 6)
					+ "/";
			this.modtime += modtime.substring(6, 8)
					+ " @ ";
			this.modtime += modtime
					.substring(8, 10) + ":";
			this.modtime += modtime.substring(10,
					12) + ":";
			this.modtime += modtime.substring(12,
					14);
		} else {
			this.modtime = modtime;
		}
	}
	//
	public String getModuser() {
		return (this.moduser);
	}
	public void setModuser(String moduser) {
		this.moduser = moduser;
	}
	//
	public String getModfunc() {
		return (this.modfunc);
	}
	public void setModfunc(String modfunc) {
		this.modfunc = modfunc;
	}
	//
	public void ShowDetails() {
		moduleName = "ShowDetails: ";
		PrintLog("payersource\t\t'" + payersource + "'");
		PrintLog("payeraba\t\t'" + payeraba + "'");
		PrintLog("payeracct\t\t'" + payeracct + "'");
		PrintLog("payername\t\t'" + payername + "'");
		PrintLog("payercountry\t\t'" + payercountry + "'");
		PrintLog("payerbank_o\t\t'" + payerbank + "'");
	}
	//
	public void ClearNulls() {
		this.payersource	= (payersource == null) ? "" : this.payersource;
		this.payeraba		= (payeraba == null) ? "" : this.payeraba;
		this.payeracct		= (payeracct == null) ? "" : this.payeracct;
		this.payername		= (payername == null) ? "" : this.payername;
		this.payercountry	= (payercountry == null) ? "" : this.payercountry;
		this.payernextday	= (payernextday == null) ? "" : this.payernextday;
		this.payerbank		= (payerbank == null) ? "" : this.payerbank;
	}
	//
	public void clearDetails() {
		this.payersource	= "";
		this.payeraba		= "";
		this.payeracct		= "";
		this.payername		= "";
		this.payercountry	= "";
		this.payernextday	= "";
		this.payerbank		= "";
		setAccount_make_copy();
	}
	//
	public void setAccount_make_copy() {
		ClearNulls();
		this.payersource_o		= this.payersource;
		this.payeraba_o			= this.payeraba;
		this.payeracct_o		= this.payeracct;
		this.payername_o		= this.payername;
		this.payercountry_o		= this.payercountry;
		this.payernextday_o		= this.payernextday;
		this.payerbank_o		= this.payerbank;
	}
	//
	public int CheckForChanges() {
		moduleName = "CheckForChanges: ";
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed = 0;
		ClearNulls();
		// PrintLog("IN CheckForChanges ");
		//
		// Just check if any of the fields was changed
		if (this.payersource_o.compareTo(this.payersource) != 0) {
			data_changed = 1;
			//PrintLog("currency changed "+account_currency);
		}
		if (this.payeraba_o.compareTo(this.payeraba) != 0) {
			data_changed = 1;
			//PrintLog("currency changed "+account_currency);
		}
		if (this.payeracct_o.compareTo(this.payeracct) != 0) {
			data_changed = 1;
			//PrintLog("currency changed "+account_currency);
		}
		if (this.payername_o.compareTo(this.payername) != 0) {
			data_changed = 1;
			//PrintLog("currency changed "+account_currency);
		}
		if (this.payercountry_o.compareTo(this.payercountry) != 0) {
			data_changed = 1;
			//PrintLog("currency changed "+account_currency);
		}
		if (this.payerbank_o.compareTo(this.payerbank) != 0) {
			data_changed = 1;
			//PrintLog("currency changed "+account_currency);
		}
		// PrintLog("Returning : " + data_changed);
		ShowDetails();
		if (data_changed == 0) {
			setErrorVec("field", "info.nomods");
		}
		return (data_changed);
	}
	//
	public boolean NewAcctFieldsValid() {
		moduleName = "NewAcctFieldsValid: ";
		boolean ret_bool = true;
		ClearNulls();
		//ShowDetails();
		if (payeraba.equals("") || payeraba.equals(" ")) {
			ret_bool = false;
			PrintLog("Aba blank");
			setErrorVec("Payer ABA ", "error.field.required");
			return false;
		}
		if (payeracct.equals("") || payeracct.equals(" ")) {
			ret_bool = false;
			PrintLog("PayerAccount blank");
			setErrorVec("Payer ACCOUNT ", "error.field.required");
			return false;
		}
		if (payername.equals("")) {
			ret_bool = false;
			PrintLog("Payer name NULL ");
			setErrorVec("Payer Name", "error.field.required");
		}
		return (ret_bool);
	}
	//
}
