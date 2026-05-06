package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:31:11 $
 */

public final class ReconDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID		= 1L;
	// --------------------------------------------------- Instance Variables
	private String  moduleName				= "";
	private String	actionFlag				= "";
	private String	accessFlag				= "";
	private String	dbUsed					= "";
	private String	appl_date				= "";
	private String	bankId					= "";
	private String	defCurr					= "";
	private String	recon_proc_date			= "";
	private String	recon_proc_date_disp	= "";
	private String	recon_account_num		= "";
	private String	recon_check_num			= "";
	private String	recon_routing_transit	= "";
	private String	recon_check_currency	= "";
	private String	recon_check_amount_disp	= "";
	private String	recon_check_amount		= "";
	private String	recon_unique_isn		= "";
	private String	recon_source_micr		= "";
	private String	recon_source_xml		= "";
	private String	recon_status			= "";
	private String	recon_last_modified		= "";
	private String	recon_mod_user			= "";
	private String	recon_mod_func			= "";
	// private String chex_ret_reason		= "";
	// private String chex_bofd				= "";
	// private String chex_bofdDate			= "";
	private String	recon_fileName			= "";

	private String	recon_proc_date_o		= "";
	private String	recon_account_num_o		= "";
	private String	recon_check_num_o		= "";
	private String	recon_routing_transit_o	= "";
	private String	recon_check_currency_o	= "";
	//private String recon_check_amount_disp_o = "";
	private String	recon_check_amount_o	= "";
	private String	recon_source_micr_o		= "";
	private String	recon_source_xml_o		= "";
	private String	recon_status_o			= "";
	private String	recon_last_modified_o	= "";
	private String	recon_mod_user_o		= "";
	private String	recon_mod_func_o		= "";
	//
	private HashMap<String, String> recon_modparam		= new HashMap<String, String>();
	private String	recon_auth_rej		= "";
	//
	private Vector<Vector<String>>  errorVec			= new Vector<Vector<String>>();
	//
	DecimalFormat	df					= new DecimalFormat("##,###,##0.00");
	//
	// private String moduleName = "> ReconDetail.";
	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> ReconDetail." + moduleName + logMsg);
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
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public String getRecon_proc_date() {
		return (this.recon_proc_date);
	}
	public String getRecon_proc_date_disp() {
		return (this.recon_proc_date_disp);
	}
	public void setRecon_proc_date(String recon_proc_date) {
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"setRecon_proc_date: "+recon_proc_date);
		this.recon_proc_date = recon_proc_date;
		this.recon_proc_date_disp = (recon_proc_date.substring(4, 6) + "/" +
									 recon_proc_date.substring(6) + "/" + 
									 recon_proc_date.substring(0, 4));
	}
	//
	public String getRecon_account_num() {
		return (this.recon_account_num);
	}
	public void setRecon_account_num(String recon_account_num) {
		this.recon_account_num = recon_account_num;
	}
	public String getRecon_check_num() {
		return (this.recon_check_num);
	}
	public void setRecon_check_num(String recon_check_num) {
		this.recon_check_num = recon_check_num;
	}
	public String getRecon_routing_transit() {
		return (this.recon_routing_transit);
	}
	public void setRecon_routing_transit(String recon_routing_transit) {
		this.recon_routing_transit = recon_routing_transit;
	}
	public String getRecon_check_currency() {
		return (this.recon_check_currency);
	}
	public void setRecon_check_currency(String recon_check_currency) {
		this.recon_check_currency = recon_check_currency;
	}
	public String getRecon_check_amount() {
		return (this.recon_check_amount);
	}
	public String getRecon_check_amount_disp() {
		return (this.recon_check_amount_disp);
	}
	public void setRecon_check_amount(String recon_check_amount) {
		String temp	= recon_check_amount;
		if (temp.equals("")) {
			temp	= "0";
		}
		this.recon_check_amount			= temp;
		this.recon_check_amount_disp	= df.format(Double.parseDouble(this.recon_check_amount));
	}
	//
	public String getRecon_unique_isn() {
		return (this.recon_unique_isn);
	}
	public void setRecon_unique_isn(String recon_unique_isn) {
		this.recon_unique_isn = recon_unique_isn;
	}
	//
	public String getRecon_source_micr() {
		return (this.recon_source_micr);
	}
	public void setRecon_source_micr(String recon_source_micr) {
		this.recon_source_micr = recon_source_micr;
	}
	//
	public String getRecon_source_xml() {
		return (this.recon_source_xml);
	}
	public void setRecon_source_xml(String recon_source_xml) {
		this.recon_source_xml = recon_source_xml;
	}
	//
	public String getRecon_status() {
		return (this.recon_status);
	}
	public void setRecon_status(String recon_status) {
		this.recon_status = recon_status;
	}
	//
	public String getRecon_last_modified() {
		return (this.recon_last_modified);
	}
	public void setRecon_last_modified(String recon_last_modified) {
		int space_at = recon_last_modified.indexOf(" ");
		if (!recon_last_modified.equals("")
				&& recon_last_modified.length() > 15) {
			this.recon_last_modified = (recon_last_modified.substring(0,
					space_at) + " @ " + recon_last_modified.substring(
					space_at + 1, recon_last_modified.length()));
		} else if (!recon_last_modified.equals("")
				&& recon_last_modified.length() > 13) {
			this.recon_last_modified = recon_last_modified.substring(0, 4) + "/";
			this.recon_last_modified += recon_last_modified.substring(4, 6) + "/";
			this.recon_last_modified += recon_last_modified.substring(6, 8) + " @ ";
			this.recon_last_modified += recon_last_modified.substring(8, 10) + ":";
			this.recon_last_modified += recon_last_modified.substring(10, 12) + ":";
			this.recon_last_modified += recon_last_modified.substring(12, 14);
		} else {
			this.recon_last_modified = recon_last_modified;
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"setRecon_last_modified Formatted:"+this.recon_last_modified);
	}
	//
	public String getRecon_mod_user() {
		return (this.recon_mod_user);
	}
	public void setRecon_mod_user(String recon_mod_user) {
		this.recon_mod_user = recon_mod_user;
	}
	//
	public String getRecon_mod_func() {
		return (this.recon_mod_func);
	}
	public void setRecon_mod_func(String recon_mod_func) {
		this.recon_mod_func = recon_mod_func;
	}
	//
	public String getRecon_fileName() {
		return (this.recon_fileName);
	}
	public void setRecon_fileName(String recon_fileName) {
		this.recon_fileName = recon_fileName;
	}
	//
	//
	// Hash map for passing parameters for modify
	public HashMap<String, String> getRecon_modparam() {
		return (this.recon_modparam);
	}
	public void setRecon_modparam() {
		this.recon_modparam.put("proc_date", recon_proc_date);
		this.recon_modparam.put("account_number", recon_account_num);
		this.recon_modparam.put("check_number", recon_check_num);
		this.recon_modparam.put("check_unique_isn", recon_unique_isn);
	}
	public String getRecon_auth_rej() {
		return (this.recon_auth_rej);
	}
	public void setRecon_auth_rej(String recon_auth_rej) {
		this.recon_auth_rej = recon_auth_rej;
	}
	//
	// Saved fields
	//
	public String getRecon_proc_date_o() {
		return (this.recon_proc_date_o);
	}
	public void setRecon_proc_date_o(String recon_proc_date_o) {
		this.recon_proc_date_o = recon_proc_date_o;
	}
	public String getRecon_account_num_o() {
		return (this.recon_account_num_o);
	}
	public void setRecon_account_num_o(String recon_account_num_o) {
		this.recon_account_num_o = recon_account_num_o;
	}
	public String getRecon_check_num_o() {
		return (this.recon_check_num_o);
	}
	public void setRecon_check_num_o(String recon_check_num_o) {
		this.recon_check_num_o = recon_check_num_o;
	}
	public String getRecon_routing_transit_o() {
		return (this.recon_routing_transit_o);
	}
	public void setRecon_routing_transit_o(String recon_routing_transit_o) {
		this.recon_routing_transit_o = recon_routing_transit_o;
	}
	public String getRecon_check_currency_o() {
		return (this.recon_check_currency_o);
	}
	public void setRecon_check_currency_o(String recon_check_currency_o) {
		this.recon_check_currency_o = recon_check_currency_o;
	}
	public String getRecon_check_amount_o() {
		return (this.recon_check_amount_o);
	}
	public void setRecon_check_amount_o(String recon_check_amount_o) {
		this.recon_check_amount_o = recon_check_amount_o;
	}
	//
	public String getRecon_source_micr_o() {
		return (this.recon_source_micr_o);
	}
	public void setRecon_source_micr_o(String recon_source_micr_o) {
		this.recon_source_micr_o = recon_source_micr_o;
	}
	//
	public String getRecon_source_xml_o() {
		return (this.recon_source_xml_o);
	}
	public void setRecon_source_xml_o(String recon_source_xml_o) {
		this.recon_source_xml_o = recon_source_xml_o;
	}
	//
	public String getRecon_status_o() {
		return (this.recon_status_o);
	}
	public void setRecon_status_o(String recon_status_o) {
		this.recon_status_o = recon_status_o;
	}
	//
	public String getRecon_last_modified_o() {
		return (this.recon_last_modified_o);
	}
	public void setRecon_last_modified_o(String recon_last_modified_o) {
		this.recon_last_modified_o = recon_last_modified_o;
	}
	public String getRecon_mod_user_o() {
		return (this.recon_mod_user_o);
	}
	public void setRecon_mod_user_o(String recon_mod_user_o) {
		this.recon_mod_user_o = recon_mod_user_o;
	}
	public String getRecon_mod_func_o() {
		return (this.recon_mod_func_o);
	}
	public void setRecon_mod_func_o(String recon_mod_func_o) {
		this.recon_mod_func_o = recon_mod_func_o;
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
		chex_proc_date = (chex_proc_date == null) ? " " : chex_proc_date;
	 */
	//
	public void ClearNulls() {
		recon_proc_date			= (recon_proc_date == null) ? " " : recon_proc_date;
		recon_account_num		= (recon_account_num == null) ? " " : recon_account_num;
		recon_check_num			= (recon_check_num == null) ? " " : recon_check_num;
		recon_routing_transit	= (recon_routing_transit == null) ? " " : recon_routing_transit;
		recon_check_currency	= (recon_check_currency == null) ? " " : recon_check_currency;
		recon_check_amount		= (recon_check_amount == null) ? " " : recon_check_amount;
		recon_source_micr		= (recon_source_micr == null) ? " " : recon_source_micr;
		recon_source_xml		= (recon_source_xml == null) ? " " : recon_source_xml;
		recon_status			= (recon_status == null) ? " " : recon_status;
	}
	//
	public void setRecon_make_copy() {
		ClearNulls();
		recon_proc_date_o		= recon_proc_date;
		recon_account_num_o		= recon_account_num;
		recon_check_num_o		= recon_check_num;
		recon_routing_transit_o = recon_routing_transit;
		recon_check_currency_o	= recon_check_currency;
		recon_check_amount_o	= recon_check_amount;
		recon_source_micr_o		= recon_source_micr;
		recon_source_xml_o		= recon_source_xml;
		recon_status_o			= recon_status;
		recon_last_modified_o	= recon_last_modified;
		recon_mod_user_o		= recon_mod_user;
		recon_mod_func_o		= recon_mod_func;
	}
	//
	public void ShowDetails() {
		//clearNulls();
		moduleName	= "ShowDetails: ";
		PrintLog("recon_proc_date: '" + recon_proc_date + "'");
		PrintLog("recon_account_num: '" + recon_account_num + "'");
		PrintLog("recon_check_num: '" + recon_check_num + "'");
		PrintLog("recon_routing_transit: '" + recon_routing_transit + "'");
		PrintLog("recon_check_currency: '" + recon_check_currency + "'");
		PrintLog("recon_check_amount: '" + recon_check_amount + "'");
		PrintLog("recon_source_micr: '" + recon_source_micr + "'");
		PrintLog("recon_source_xml: '" + recon_source_xml + "'");
		PrintLog("recon_status: '" + recon_status + "'");
		PrintLog("recon_last_modified: '" + recon_last_modified + "'");
		PrintLog("recon_mod_user: '" + recon_mod_user + "'");
		PrintLog("recon_mod_func: '" + recon_mod_func + "'");
	}
}
