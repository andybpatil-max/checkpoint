package com.webfiche.checkpoint.deposits.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 */

public final class DepsDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID		= 1L;
	// --------------------------------------------------- Instance Variables
	private String	className				= "> DepsDetail.";
	private String	moduleName				= "";
	private boolean	createPayer				= false;
	private String	userId					= "";
	private int		addendumCount			= 0;
	private int		daysCheckValid			= 180;
	private String	actionFlag				= "";
	private String	accessFlag				= "";
	private String	dbUsed					= "";
	private String	dbTable					= "";
	private String	appl_date				= "";
	private String	bankId					= "";
	private String	defCurr					= "";
	//
	private String	chex_proc_date			= " ";
	private String	chex_proc_date_disp		= " ";
	private String	chex_orig_account_num	= " ";
	private String	chex_orig_check_num		= " ";
	private String	chex_account_num		= " ";
	private String	chex_check_num			= " ";
	private String	chex_routing_transit	= " ";
	private String	chex_check_digit		= " ";
	private String	chex_check_currency		= " ";
	private String	chex_check_amount_disp	= " ";
	private String	chex_check_amount		= " ";
	private String	chex_proc_control		= " ";
	private String	chex_ext_proc_control	= " ";
	private String	chex_image_locator		= " ";
	private String	chex_unique_isn			= " ";	// OSN for out clearing
	private String	chex_addenda_rec_flag	= " ";
	private String	chex_orig_inst_rte		= " ";
	private String	chex_isn				= " ";
	private String	chex_budget_id			= " ";
	private String	chex_bundleid			= " ";
	private String	chex_return_date		= " ";
	private String	chex_return_reason		= " ";
	private String	chex_return_status		= " ";
	private String	chex_BOFD_aba			= " ";
	private String	chex_BOFD_date			= " ";
	private String	chex_extra_1			= " ";	// payees A/C # from rec type 26
	private String	chex_extra_2			= " ";
	private String	chex_issue_date			= " ";
	private String	chex_issue_date_disp	= " ";
	private String	chex_payeeaba			= " ";
	private String	chex_payeeacct			= " ";
	private String	chex_payee				= " ";	// Payees Name From rec type 26
	private String	chex_payee_addr1		= " ";
	private String	chex_payee_addr2		= " ";
	private String	chex_payee_addr3		= " ";
	private String	chex_comments			= " ";
	private String	chex_reason_codes		= " ";
	private String	chex_check_status		= " ";
	private String	chex_amount_od_disp		= " ";
	private String	chex_amount_od			= " ";
	private String	chex_source				= " ";
	private String	modtime					= " ";
	private String	moduser					= " ";
	private String	modfunc					= " ";
	private String	chex_image				= " ";
	private String	chex_image_f			= " ";
	private String	chex_image_b			= " ";
	private String	chex_fileName			= " ";
	private String	chex_reason_desc		= " ";
	private String	chex_status_desc		= " ";
	private String	chexPayerName			= " ";
	private String	chexPayerCountry		= " ";
	//
	private String	chex_proc_date_o		= "";
	private String	chex_orig_account_num_o	= "";
	private String	chex_orig_check_num_o	= "";
	private String	chex_account_num_o		= "";
	private String	chex_check_num_o		= "";
	private String	chex_routing_transit_o	= "";
	private String	chex_check_digit_o		= "";
	private String	chex_check_currency_o	= "";
	private String	chex_check_amount_o		= "";
	private String	chex_proc_control_o		= "";
	private String	chex_ext_proc_control_o	= "";
	private String	chex_image_locator_o	= "";
	private String	chex_unique_isn_o		= "";
	private String	chex_addenda_rec_flag_o	= "";
	private String	chex_orig_inst_rte_o	= "";
	private String	chex_isn_o				= "";
	private String	chex_budget_id_o		= "";
	private String	chex_bundleid_o			= "";
	private String	chex_return_date_o		= "";
	private String	chex_return_reason_o	= "";
	private String	chex_return_status_o	= "";
	private String	chex_BOFD_aba_o			= "";
	private String	chex_BOFD_date_o		= "";
	private String	chex_extra_1_o			= "";
	private String	chex_extra_2_o			= "";
	private String	chex_issue_date_o		= "";
	private String	chex_payeeaba_o			= "";
	private String	chex_payeeacct_o		= "";
	private String	chex_payee_o			= "";
	private String	chex_payee_addr1_o		= "";
	private String	chex_payee_addr2_o		= "";
	private String	chex_payee_addr3_o		= "";
	private String	chex_comments_o			= "";
	private String	chex_reason_codes_o		= "";
	private String	chex_check_status_o		= "";
	private String	chex_amount_od_o		= "";
	private String	modtime_o				= "";
	private String	moduser_o				= "";
	private String	modfunc_o				= "";
	private String	chexPayerName_o			= "";
	private String	chexPayerCountry_o		= " ";
	//
	private Vector<Vector<String>>  errorVec	= new Vector<Vector<String>>();
	//
	private HashMap<String, String> chex_modparam	= new HashMap<String, String>();
	private String	chex_auth_rej		= "";
	//
	DecimalFormat	df					= new DecimalFormat("###,##0.00");
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + userId + " - " + logMsg);
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
	public boolean getCreatePayer() {
		return (this.createPayer);
	}
	public void setCreatePayer(boolean createPayer) {
		this.createPayer	= createPayer;
	}
	//
	public int getAddendumCount() {
		return (this.addendumCount);
	}
	public void setAddendumCount(int addendumCount) {
		this.addendumCount	= addendumCount;
	}
	//
	public int getDaysCheckValid() {
		return (this.daysCheckValid);
	}
	public void setDaysCheckValid(int daysCheckValid) {
		this.daysCheckValid	= daysCheckValid;
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
	//
	public String getChex_proc_date() {
		return (this.chex_proc_date);
	}
	public String getChex_proc_date_disp() {
		return (this.chex_proc_date_disp);
	}
	public void setChex_proc_date(String chex_proc_date) {
		// PrintLog("setChex_proc_date: "+chex_proc_date);
		this.chex_proc_date			= chex_proc_date;
		this.chex_proc_date_disp	= (chex_proc_date.substring(4, 6) + 
										"/"	+ chex_proc_date.substring(6) + 
										"/" + chex_proc_date.substring(0, 4));
	}
	//
	public String getChex_orig_account_num() {
		return (this.chex_orig_account_num);
	}
	public void setChex_orig_account_num(String chex_orig_account_num) {
		this.chex_orig_account_num = chex_orig_account_num;
	}
	//
	public String getChex_orig_check_num() {
		return (this.chex_orig_check_num);
	}
	public void setChex_orig_check_num(String chex_orig_check_num) {
		this.chex_orig_check_num = chex_orig_check_num;
	}
	//
	public String getChex_account_num() {
		return (this.chex_account_num);
	}
	public void setChex_account_num(String chex_account_num) {
		this.chex_account_num = chex_account_num;
	}
	//
	public String getChex_check_num() {
		return (this.chex_check_num);
	}
	public void setChex_check_num(String chex_check_num) {
		this.chex_check_num = chex_check_num;
	}
	//
	public String getChex_routing_transit() {
		return (this.chex_routing_transit);
	}
	public void setChex_routing_transit(String chex_routing_transit) {
		if (chex_routing_transit!=null) {
			if (chex_routing_transit.equals(" ") ||
				!chex_routing_transit.equals(" ")) { 
				this.chex_routing_transit = String.format("%09d", Long.parseLong(chex_routing_transit));
			} else {
				this.chex_routing_transit = chex_routing_transit;
			}
		} else {
			this.chex_routing_transit = chex_routing_transit;
		}
	}
	//
	public String getChex_check_digit() {
		return (this.chex_check_digit);
	}
	public void setChex_check_digit(String chex_check_digit) {
		this.chex_check_digit = chex_check_digit;
	}
	//
	public String getChex_check_currency() {
		return (this.chex_check_currency);
	}
	public void setChex_check_currency(String chex_check_currency) {
		this.chex_check_currency = chex_check_currency;
	}
	//
	public String getChex_check_amount() {
		return (this.chex_check_amount);
	}
	public String getChex_check_amount_disp() {
		return (this.chex_check_amount_disp);
	}
	public void setChex_check_amount(String chex_check_amount) {
		moduleName = "setChex_check_amount: ";
		this.chex_check_amount = (chex_check_amount == null) ? "0.00" : chex_check_amount;
		// PrintLog(chex_check_amount);
		if (chex_check_amount.equals("") || chex_check_amount.equals(" ")) {
			this.chex_check_amount	= "0";
		} else {
			this.chex_check_amount	= chex_check_amount;
		}
		String temp = df.format(Double.parseDouble(chex_check_amount));
		this.chex_check_amount_disp	= temp;
	}
	//
	public String getChex_proc_control() {
		return (this.chex_proc_control);
	}
	public void setChex_proc_control(String chex_proc_control) {
		this.chex_proc_control = chex_proc_control;
	}
	public String getChex_ext_proc_control() {
		return (this.chex_ext_proc_control);
	}
	public void setChex_ext_proc_control(String chex_ext_proc_control) {
		this.chex_ext_proc_control = chex_ext_proc_control;
	}
	public String getChex_image_locator() {
		return (this.chex_image_locator);
	}
	public void setChex_image_locator(String chex_image_locator) {
		this.chex_image_locator = chex_image_locator;
	}
	public String getChex_unique_isn() {
		return (this.chex_unique_isn);
	}
	public void setChex_unique_isn(String chex_unique_isn) {
		this.chex_unique_isn = chex_unique_isn;
	}
	public String getChex_addenda_rec_flag() {
		return (this.chex_addenda_rec_flag);
	}
	public void setChex_addenda_rec_flag(String chex_addenda_rec_flag) {
		this.chex_addenda_rec_flag = chex_addenda_rec_flag;
	}
	public String getChex_orig_inst_rte() {
		return (this.chex_orig_inst_rte);
	}
	public void setChex_orig_inst_rte(String chex_orig_inst_rte) {
		this.chex_orig_inst_rte = chex_orig_inst_rte;
	}
	public String getChex_isn() {
		return (this.chex_isn);
	}
	public void setChex_isn(String chex_isn) {
		this.chex_isn = chex_isn;
	}
	//
	public String getChex_budget_id() {
		return (this.chex_budget_id);
	}
	public void setChex_budget_id(String chex_budget_id) {
		this.chex_budget_id = chex_budget_id;
	}
	//
	public String getChex_bundleid() {
		return (this.chex_bundleid);
	}
	public void setChex_bundleid(String chex_bundleid) {
		this.chex_bundleid = chex_bundleid;
	}
	//
	public String getChex_return_date() {
		return (this.chex_return_date);
	}
	public void setChex_return_date(String chex_return_date) {
		this.chex_return_date = chex_return_date;
	}
	//
	public String getChex_return_reason() {
		return (this.chex_return_reason);
	}
	public void setChex_return_reason(String chex_return_reason) {
		this.chex_return_reason = chex_return_reason;
	}
	//
	public String getChex_return_status() {
		return (this.chex_return_status);
	}
	public void setChex_return_status(String chex_return_status) {
		this.chex_return_status = chex_return_status;
	}
	//
	public String getChex_BOFD_aba() {
		return (this.chex_BOFD_aba);
	}
	public void setChex_BOFD_aba(String chex_BOFD_aba) {
		this.chex_BOFD_aba = chex_BOFD_aba;
	}
	//
	public String getChex_BOFD_date() {
		return (this.chex_BOFD_date);
	}
	public void setChex_BOFD_date(String chex_BOFD_date) {
		this.chex_BOFD_date = chex_BOFD_date;
	}
	//
	// field extra_1 used for Days Checks are valid 180 days (about 6 mos)
	//
	public String getChex_extra_1() {
		return (this.chex_extra_1);
	}
	public void setChex_extra_1(String chex_extra_1) {
		this.chex_extra_1 = chex_extra_1;
	}
	public String getChex_extra_2() {
		return (this.chex_extra_2);
	}
	public void setChex_extra_2(String chex_extra_2) {
		this.chex_extra_2 = chex_extra_2;
	}
	//
	public String getChex_issue_date() {
		return (this.chex_issue_date);
	}
	public String getChex_issue_date_disp() {
		return (this.chex_issue_date_disp);
	}
	public void setChex_issue_date(String chex_issue_date) {
		this.chex_issue_date = chex_issue_date;
		if (this.chex_issue_date.equals("XXXXXXXX")) {
			this.chex_issue_date_disp = chex_issue_date;
		} else if (chex_issue_date.length() > 7) {
			this.chex_issue_date_disp = (chex_issue_date.substring(4, 6) + 
										"/"	+ chex_issue_date.substring(6) + 
										"/" + chex_issue_date.substring(0, 4));
		} else {
			this.chex_issue_date_disp = chex_issue_date;
		}
	}
	//
	public String getChex_payeeaba() {
		return (this.chex_payeeaba);
	}
	public void setChex_payeeaba(String chex_payeeaba) {
		this.chex_payeeaba = chex_payeeaba;
	}
	//
	public String getChex_payeeacct() {
		return (this.chex_payeeacct);
	}
	public void setChex_payeeacct(String chex_payeeacct) {
		this.chex_payeeacct = chex_payeeacct;
	}
	//
	public String getChex_payee() {
		return (this.chex_payee);
	}
	public void setChex_payee(String chex_payee) {
		this.chex_payee = chex_payee;
	}
	//
	public String getChex_payee_addr1() {
		return (this.chex_payee_addr1);
	}
	public void setChex_payee_addr1(String chex_payee_addr1) {
		this.chex_payee_addr1 = chex_payee_addr1;
	}
	//
	public String getChex_payee_addr2() {
		return (this.chex_payee_addr2);
	}
	public void setChex_payee_addr2(String chex_payee_addr2) {
		this.chex_payee_addr2 = chex_payee_addr2;
	}
	//
	public String getChex_payee_addr3() {
		return (this.chex_payee_addr3);
	}
	public void setChex_payee_addr3(String chex_payee_addr3) {
		this.chex_payee_addr3 = chex_payee_addr3;
	}
	//
	public String getChex_comments() {
		return (this.chex_comments);
	}
	public void setChex_comments(String chex_comments) {
		this.chex_comments = chex_comments;
	}
	//
	public String getChex_reason_codes() {
		return (this.chex_reason_codes);
	}
	public void setChex_reason_codes(String chex_reason_codes) {
		this.chex_reason_codes = chex_reason_codes;
	}
	//
	public String getChex_check_status() {
		return (this.chex_check_status);
	}
	public void setChex_check_status(String chex_check_status) {
		this.chex_check_status = chex_check_status;
	}
	//
	public String getChex_amount_od() {
		return (this.chex_amount_od);
	}
	public String getChex_amount_od_disp() {
		return (this.chex_amount_od_disp);
	}
	public void setChex_amount_od(String chex_amount_od) {
		this.chex_amount_od = chex_amount_od;
	}
	//
	public String getChex_source() {
		return (this.chex_source);
	}
	public void setChex_source(String chex_source) {
		this.chex_source	= chex_source;
	}
	//
	public String getModtime() {
		return (this.modtime);
	}
	public void setModtime(String modtime) {
		int space_at = modtime.indexOf(" ");
		if (!modtime.equals("") && modtime.length() > 15) {
			this.modtime = (modtime.substring(0, space_at) + " @ " + 
					modtime.substring(space_at + 1, modtime.length()));
		} else if (!modtime.equals("")
				&& modtime.length() > 13) {
			this.modtime	= modtime.substring(0, 4) + "/";
			this.modtime	+= modtime.substring(4, 6) + "/";
			this.modtime += modtime.substring(6, 8) + " @ ";
			this.modtime += modtime.substring(8, 10) + ":";
			this.modtime += modtime.substring(10, 12) + ":";
			this.modtime += modtime.substring(12, 14);
		} else {
			this.modtime	= modtime;
		}
		// PrintLog("setModtime Formatted:"+this.modtime);
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
	public String getChex_fileName() {
		return (this.chex_fileName);
	}
	public void setChex_fileName(String chex_fileName) {
		this.chex_fileName = chex_fileName;
	}
	//
	public String getChex_image() {
		return (this.chex_image);
	}
	public void setChex_image(String chex_image) {
		// PrintLog("setChex_image: "+chex_image_f);
		this.chex_image = chex_image;
	}
	//
	public String getChex_image_f() {
		return (this.chex_image_f);
	}
	public void setChex_image_f(String chex_image_f) {
		// PrintLog("setChex_image_f: "+chex_image_f);
		this.chex_image_f = chex_image_f;
	}
	public String getChex_image_b() {
		return (this.chex_image_b);
	}
	public void setChex_image_b(String chex_image_b) {
		// PrintLog("setChex_image_b: "+chex_image_b);
		this.chex_image_b = chex_image_b;
	}
	//
	public String getChex_reason_desc() {
		return (this.chex_reason_desc);
	}
	public void setChex_reason_desc(String chex_reason_desc) {
		this.chex_reason_desc	= chex_reason_desc;
	}
	//
	public String getChex_status_desc() {
		return (this.chex_status_desc);
	}
	public void setChex_status_desc(String chex_status_desc) {
		this.chex_status_desc	= chex_status_desc;
	}
	//
	public String getChexPayerName() {
		return (this.chexPayerName);
	}
	public void setChexPayerName(String chexPayerName) {
		this.chexPayerName	= chexPayerName;
	}
	//
	public String getChexPayerCountry() {
		return (this.chexPayerCountry);
	}
	public void setChexPayerCountry(String chexPayerCountry) {
		this.chexPayerCountry	= chexPayerCountry;
	}
	//
	// Hash map for passing parameters for modify
	public HashMap<String, String> getChex_modparam() {
		return (this.chex_modparam);
	}
	public void setChex_modparam() {
		this.chex_modparam.put("proc_date", chex_proc_date);
		this.chex_modparam.put("account_number", chex_account_num);
		this.chex_modparam.put("check_number", chex_check_num);
		this.chex_modparam.put("check_unique_isn", chex_unique_isn);
	}
	public String getChex_auth_rej() {
		return (this.chex_auth_rej);
	}
	public void setChex_auth_rej(String chex_auth_rej) {
		this.chex_auth_rej = chex_auth_rej;
	}
	//
	// Saved fields
	//
	public String getChex_proc_date_o() {
		return (this.chex_proc_date_o);
	}
	public void setChex_proc_date_o(String chex_proc_date_o) {
		this.chex_proc_date_o = chex_proc_date_o;
	}
	public String getChex_account_num_o() {
		return (this.chex_account_num_o);
	}
	public void setChex_account_num_o(String chex_account_num_o) {
		this.chex_account_num_o = chex_account_num_o;
	}
	public String getChex_check_num_o() {
		return (this.chex_check_num_o);
	}
	public void setChex_check_num_o(String chex_check_num_o) {
		this.chex_check_num_o = chex_check_num_o;
	}
	public String getChex_routing_transit_o() {
		return (this.chex_routing_transit_o);
	}
	public void setChex_routing_transit_o(String chex_routing_transit_o) {
		this.chex_routing_transit_o = chex_routing_transit_o;
	}
	public String getChex_check_digit_o() {
		return (this.chex_check_digit_o);
	}
	public void setChex_check_digit_o(String chex_check_digit_o) {
		this.chex_check_digit_o = chex_check_digit_o;
	}
	public String getChex_check_currency_o() {
		return (this.chex_check_currency_o);
	}
	public void setChex_check_currency_o(String chex_check_currency_o) {
		this.chex_check_currency_o = chex_check_currency_o;
	}
	public String getChex_check_amount_o() {
		return (this.chex_check_amount_o);
	}
	public void setChex_check_amount_o(String chex_check_amount_o) {
		this.chex_check_amount_o = chex_check_amount_o;
	}
	public String getChex_proc_control_o() {
		return (this.chex_proc_control_o);
	}
	public void setChex_proc_control_o(String chex_proc_control_o) {
		this.chex_proc_control_o = chex_proc_control_o;
	}
	public String getChex_ext_proc_control_o() {
		return (this.chex_ext_proc_control_o);
	}
	public void setChex_ext_proc_control_o(String chex_ext_proc_control_o) {
		this.chex_ext_proc_control_o = chex_ext_proc_control_o;
	}
	public String getChex_image_locator_o() {
		return (this.chex_image_locator_o);
	}
	public void setChex_image_locator_o(String chex_image_locator_o) {
		this.chex_image_locator_o = chex_image_locator_o;
	}
	public String getChex_unique_isn_o() {
		return (this.chex_unique_isn_o);
	}
	public void setChex_unique_isn_o(String chex_unique_isn_o) {
		this.chex_unique_isn_o = chex_unique_isn_o;
	}
	public String getChex_addenda_rec_flag_o() {
		return (this.chex_addenda_rec_flag_o);
	}
	public void setChex_addenda_rec_flag_o(String chex_addenda_rec_flag_o) {
		this.chex_addenda_rec_flag_o = chex_addenda_rec_flag_o;
	}
	public String getChex_orig_inst_rte_o() {
		return (this.chex_orig_inst_rte_o);
	}
	public void setChex_orig_inst_rte_o(String chex_orig_inst_rte_o) {
		this.chex_orig_inst_rte_o = chex_orig_inst_rte_o;
	}
	public String getChex_isn_o() {
		return (this.chex_isn_o);
	}
	public void setChex_isn_o(String chex_isn_o) {
		this.chex_isn_o = chex_isn_o;
	}
	public String getChex_budget_id_o() {
		return (this.chex_budget_id_o);
	}
	public void setChex_budget_id_o(String chex_budget_id_o) {
		this.chex_budget_id_o = chex_budget_id_o;
	}
	//
	public String getChex_bundleid_o() {
		return (this.chex_bundleid_o);
	}
	public void setChex_bundleid_o(String chex_bundleid_o) {
		this.chex_bundleid_o = chex_bundleid_o;
	}
	//
	public String getChex_return_date_o() {
		return (this.chex_return_date_o);
	}
	public void setChex_return_date_o(String chex_return_date_o) {
		this.chex_return_date_o = chex_return_date_o;
	}
	//
	public String getChex_return_reason_o() {
		return (this.chex_return_reason_o);
	}
	public void setChex_return_reason_o(String chex_return_reason_o) {
		this.chex_return_reason_o = chex_return_reason_o;
	}
	//
	public String getChex_return_status_o() {
		return (this.chex_return_status_o);
	}
	public void setChex_return_status_o(String chex_return_status_o) {
		this.chex_return_status_o = chex_return_status_o;
	}
	//
	public String getChex_BOFD_aba_o() {
		return (this.chex_BOFD_aba_o);
	}
	public void setChex_BOFD_aba_o(String chex_BOFD_aba_o) {
		this.chex_BOFD_aba_o = chex_BOFD_aba_o;
	}
	//
	public String getChex_BOFD_date_o() {
		return (this.chex_BOFD_date_o);
	}
	public void cetChex_BOFD_date_o(String chex_BOFD_date_o) {
		this.chex_BOFD_date_o = chex_BOFD_date_o;
	}
	//
	public String getChex_extra_1_o() {
		return (this.chex_extra_1_o);
	}
	public void setChex_extra_1_o(String chex_extra_1_o) {
		this.chex_extra_1_o = chex_extra_1_o;
	}
	public String getChex_extra_2_o() {
		return (this.chex_extra_2_o);
	}
	public void setChex_extra_2_o(String chex_extra_2_o) {
		this.chex_extra_2_o = chex_extra_2_o;
	}
	public String getChex_issue_date_o() {
		return (this.chex_issue_date_o);
	}
	public void setChex_issue_date_o(String chex_issue_date_o) {
		this.chex_issue_date_o = chex_issue_date_o;
	}
	//
	public String getChex_payeeaba_o() {
		return (this.chex_payeeaba_o);
	}
	public void setChex_payeeaba_o(String chex_payeeaba_o) {
		this.chex_payeeaba_o = chex_payeeaba_o;
	}
	//
	public String getChex_payeeacct_o() {
		return (this.chex_payeeacct_o);
	}
	public void setChex_payeeacct_o(String chex_payeeacct_o) {
		this.chex_payeeacct_o	= chex_payeeacct_o;
	}
	//
	public String getChex_payee_o() {
		return (this.chex_payee_o);
	}
	public void setChex_payee_o(String chex_payee_o) {
		this.chex_payee_o = chex_payee_o;
	}
	public String getChex_payee_addr1_o() {
		return (this.chex_payee_addr1_o);
	}
	public void setChex_payee_addr1_o(String chex_payee_addr1_o) {
		this.chex_payee_addr1_o = chex_payee_addr1_o;
	}
	public String getChex_payee_addr2_o() {
		return (this.chex_payee_addr2_o);
	}
	public void setChex_payee_addr2_o(String chex_payee_addr2_o) {
		this.chex_payee_addr2_o = chex_payee_addr2_o;
	}
	public String getChex_payee_addr3_o() {
		return (this.chex_payee_addr3_o);
	}
	public void setChex_payee_addr3_o(String chex_payee_addr3_o) {
		this.chex_payee_addr3_o = chex_payee_addr3_o;
	}
	public String getChex_comments_o() {
		return (this.chex_comments_o);
	}
	public void setChex_comments_o(String chex_comments_o) {
		this.chex_comments_o = chex_comments_o;
	}
	public String getChex_reason_codes_o() {
		return (this.chex_reason_codes_o);
	}
	public void setChex_reason_codes_o(String chex_reason_codes_o) {
		this.chex_reason_codes_o = chex_reason_codes_o;
	}
	public String getChex_check_status_o() {
		return (this.chex_check_status_o);
	}
	public void setChex_check_status_o(String chex_check_status_o) {
		this.chex_check_status_o = chex_check_status_o;
	}
	public String getChex_amount_od_o() {
		return (this.chex_amount_od_o);
	}
	public void setChex_amount_od_o(String chex_amount_od_o) {
		this.chex_amount_od_o = chex_amount_od_o;
	}
	//
	public String getChex_orig_account_num_o() {
		return (this.chex_orig_account_num_o);
	}
	public void setChex_orig_account_num_o(String chex_orig_account_num_o) {
		this.chex_orig_account_num_o = chex_orig_account_num_o;
	}
	//
	public String getChex_orig_check_num_o() {
		return (this.chex_orig_check_num_o);
	}
	public void setChex_orig_check_num_o(String chex_orig_check_num_o) {
		this.chex_orig_check_num_o = chex_orig_check_num_o;
	}
	//
	public String getModtime_o() {
		return (this.modtime_o);
	}
	public void setModtime_o(String modtime_o) {
		this.modtime_o = modtime_o;
	}
	public String getModuser_o() {
		return (this.moduser_o);
	}
	public void setModuser_o(String moduser_o) {
		this.moduser_o = moduser_o;
	}
	public String getModfunc_o() {
		return (this.modfunc_o);
	}
	public void setModfunc_o(String modfunc_o) {
		this.modfunc_o = modfunc_o;
	}
	//
	public String getChexPayerName_o() {
		return (this.chexPayerName_o);
	}
	public void setChexPayerName_o(String chexPayerName_o) {
		this.chexPayerName_o	= chexPayerName_o;
	}
	//
	public String getChexPayerCountry_o() {
		return (this.chexPayerCountry_o);
	}
	public void setChexPayerCountry_o(String chexPayerCountry_o) {
		this.chexPayerCountry_o	= chexPayerCountry_o;
	}
	//
	public void ShowDetails() {
		moduleName = "ShowDetails: ";
		PrintLog("chex_proc_date\t\t" + chex_proc_date);
		PrintLog("chex_orig_account_num\t\t" + chex_orig_account_num);
		PrintLog("chex_orig_check_num\t\t" + chex_orig_check_num);
		PrintLog("chex_account_num\t\t" + chex_account_num);
		PrintLog("chex_check_num\t\t" + chex_check_num);
		PrintLog("chex_routing_transit\t\t" + chex_routing_transit);
		PrintLog("chex_check_digit\t\t" + chex_check_digit);
		PrintLog("chex_check_currency\t\t" + chex_check_currency);
		PrintLog("chex_check_amount\t\t" + chex_check_amount);
		PrintLog("chex_proc_control\t\t" + chex_proc_control);
		PrintLog("chex_ext_proc_control\t\t" + chex_ext_proc_control);
		PrintLog("chex_image_locator\t\t" + chex_image_locator);
		PrintLog("chex_unique_isn\t\t" + chex_unique_isn);
		PrintLog("chex_addenda_rec_flag\t\t" + chex_addenda_rec_flag);
		PrintLog("chex_orig_inst_rte\t\t" + chex_orig_inst_rte);
		PrintLog("chex_isn\t\t" + chex_isn);
		PrintLog("chex_budget_id\t\t" + chex_budget_id);
		PrintLog("chex_bundleid\t\t" + chex_bundleid);
		PrintLog("chex_extra_1\t\t" + chex_extra_1);
		PrintLog("chex_extra_2\t\t" + chex_extra_2);
		PrintLog("chex_issue_date\t\t" + chex_issue_date);
		PrintLog("chex_payeeaba\t\t" + chex_payeeaba);
		PrintLog("chex_payeeacct\t\t" + chex_payeeacct);
		PrintLog("chex_payee\t\t" + chex_payee);
		PrintLog("chex_payee_addr1\t\t" + chex_payee_addr1);
		PrintLog("chex_payee_addr2\t\t" + chex_payee_addr2);
		PrintLog("chex_payee_addr3\t\t" + chex_payee_addr3);
		PrintLog("chex_comments\t\t" + chex_comments);
		PrintLog("chex_reason_codes\t\t" + chex_reason_codes);
		PrintLog("chex_check_status\t\t" + chex_check_status);
		PrintLog("chex_amount_od\t\t" + chex_amount_od);
		PrintLog("chexPayerName\t\t" + chexPayerName);
		PrintLog("chexPayerCountry\t\t" + chexPayerCountry);
		PrintLog("chexSource\t\t" + chex_source);
		PrintLog("modtime\t\t" + modtime);
		PrintLog("moduser\t\t" + moduser);
		PrintLog("modfunc\t\t" + modfunc);
	}
	//
	public void clearNulls() {
		// moduleName = "clearNulls: ";
		chex_proc_date			= (chex_proc_date == null) ? " " : chex_proc_date;
		chex_orig_account_num	= (chex_orig_account_num == null) ? " "	: chex_orig_account_num;
		chex_orig_check_num		= (chex_orig_check_num == null) ? " " : chex_orig_check_num;
		chex_account_num		= (chex_account_num == null) ? " " : chex_account_num;
		chex_check_num			= (chex_check_num == null) ? " " : chex_check_num;
		chex_routing_transit	= (chex_routing_transit == null) ? " "	: chex_routing_transit;
		chex_check_digit		= (chex_check_digit == null) ? " " : chex_check_digit;
		chex_check_currency		= (chex_check_currency == null) ? " " : chex_check_currency;
		chex_check_amount		= (chex_check_amount == null) ? " " : chex_check_amount;
		chex_proc_control		= (chex_proc_control == null) ? " " : chex_proc_control;
		chex_ext_proc_control	= (chex_ext_proc_control == null) ? " " : chex_ext_proc_control;
		chex_image_locator		= (chex_image_locator == null) ? " " : chex_image_locator;
		chex_unique_isn			= (chex_unique_isn == null) ? " " : chex_unique_isn;
		chex_addenda_rec_flag	= (chex_addenda_rec_flag == null) ? " " : chex_addenda_rec_flag;
		chex_orig_inst_rte		= (chex_orig_inst_rte == null) ? " " : chex_orig_inst_rte;
		chex_isn				= (chex_isn == null) ? " " : chex_isn;
		chex_budget_id			= (chex_budget_id == null) ? " " : chex_budget_id;
		chex_bundleid			= (chex_bundleid == null) ? " " : chex_bundleid;
		chex_extra_1			= (chex_extra_1 == null) ? " " : chex_extra_1;
		chex_extra_2			= (chex_extra_2 == null) ? " " : chex_extra_2;
		chex_issue_date			= (chex_issue_date == null) ? " " : chex_issue_date;
		chex_payeeaba			= (chex_payeeaba == null) ? " " : chex_payeeaba;
		chex_payeeacct			= (chex_payeeacct == null) ? " " : chex_payeeacct;
		chex_payee				= (chex_payee == null) ? " " : chex_payee;
		chex_payee_addr1		= (chex_payee_addr1 == null) ? " " : chex_payee_addr1;
		chex_payee_addr2		= (chex_payee_addr2 == null) ? " " : chex_payee_addr2;
		chex_payee_addr3		= (chex_payee_addr3 == null) ? " " : chex_payee_addr3;
		chex_comments			= (chex_comments == null) ? " " : chex_comments;
		chex_reason_codes		= (chex_reason_codes == null) ? " " : chex_reason_codes;
		chex_check_status		= (chex_check_status == null) ? " " : chex_check_status;
		chex_amount_od			= (chex_amount_od == null) ? " " : chex_amount_od;
		modtime					= (modtime == null) ? " " : modtime;
		moduser					= (moduser == null) ? " " : moduser;
		modfunc					= (modfunc == null) ? " " : modfunc;
		chexPayerName			= (chexPayerName == null) ? " " : chexPayerName;
		chexPayerCountry		= (chexPayerCountry == null) ? " " : chexPayerCountry;
		//chex_source				= (chex_source == null) ? " " : chex_source;
	}
	public void clearDetails() {
		chex_proc_date			= "";
		chex_orig_account_num	= "";
		chex_orig_check_num		= "";
		chex_account_num		= "";
		chex_check_num			= "";
		chex_routing_transit	= "";
		chex_check_digit		= "";
		chex_check_currency		= "";
		chex_check_amount		= "";
		chex_proc_control		= "";
		chex_ext_proc_control	= "";
		chex_image_locator		= "";
		chex_unique_isn			= "";
		chex_addenda_rec_flag	= "";
		chex_orig_inst_rte		= "";
		chex_isn				= "";
		chex_budget_id			= "";
		chex_extra_1			= "";
		chex_extra_2			= "";
		chex_issue_date			= "";
		chex_payeeaba			= "";
		chex_payeeacct			= "";
		chex_payee				= "";
		chex_payee_addr1		= "";
		chex_payee_addr2		= "";
		chex_payee_addr3		= "";
		chex_comments			= "";
		chex_reason_codes		= "";
		chex_check_status		= "";
		chex_amount_od			= "";
		modtime					= "";
		moduser					= "";
		modfunc					= "";
		chexPayerName			= "";
		chexPayerCountry		= "";
		//chex_source				= "";
		setChex_make_copy();
	}
	//
	public void setChex_make_copy() {
		clearNulls();
		chex_proc_date_o		= chex_proc_date;
		chex_orig_account_num_o	= chex_orig_account_num;
		chex_orig_check_num_o	= chex_orig_check_num;
		chex_account_num_o		= chex_account_num;
		chex_check_num_o		= chex_check_num;
		chex_routing_transit_o	= chex_routing_transit;
		chex_check_digit_o		= chex_check_digit;
		chex_check_currency_o	= chex_check_currency;
		chex_check_amount_o		= chex_check_amount;
		chex_proc_control_o		= chex_proc_control;
		chex_ext_proc_control_o	= chex_ext_proc_control;
		chex_image_locator_o	= chex_image_locator;
		chex_unique_isn_o		= chex_unique_isn;
		chex_addenda_rec_flag_o	= chex_addenda_rec_flag;
		chex_orig_inst_rte_o	= chex_orig_inst_rte;
		chex_isn_o				= chex_isn;
		chex_budget_id_o		= chex_budget_id;
		chex_bundleid_o			= chex_bundleid;
		chex_extra_1_o			= chex_extra_1;
		chex_extra_2_o			= chex_extra_2;
		chex_issue_date_o		= chex_issue_date;
		chex_payeeaba_o			= chex_payeeaba;
		chex_payeeacct_o		= chex_payeeacct;
		chex_payee_o			= chex_payee;
		chex_payee_addr1_o		= chex_payee_addr1;
		chex_payee_addr2_o		= chex_payee_addr2;
		chex_payee_addr3_o		= chex_payee_addr3;
		chex_comments_o			= chex_comments;
		chex_reason_codes_o		= chex_reason_codes;
		chex_check_status_o		= chex_check_status;
		chex_amount_od_o		= chex_amount_od;
		modtime_o				= modtime;
		moduser_o				= moduser;
		modfunc_o				= modfunc;
		chexPayerName_o			= chexPayerName;
		chexPayerCountry_o		= chexPayerCountry;
	}
	public int ChexAccountChanged() {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed = 0;
		if (chex_account_num_o.compareTo(chex_account_num) != 0) {
			data_changed = 1;
			if (chex_account_num.equals("")) {
				setErrorVec("Account Number", "error.field.required");
				data_changed = 2;
			}
		}
		return (data_changed);
	}
	//
	public int CheckForChanges() {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		moduleName = "CheckForChanges: ";
		// ShowDetails();
		int data_changed	= 0;
		//int date_yyyy		= 0;
		//int date_mm			= 0;
		//int date_dd			= 0;
		//boolean check_issue_date	= false;
		boolean payee		= false;
		//String six_months	= "";
		clearNulls();
		//
		PrintLog("CheckForChanges: Appl_date " + appl_date);
		//
		// Get the account Detail
		//
		/*
		 * InclAcctUtil acUtil = new InclAcctUtil(); AccountDetail acctDetail =
		 * new AccountDetail();
		 */
		if ((chex_account_num == null) || (chex_account_num.equals(" "))) {
			chex_account_num = chex_account_num_o;
		}
		/*
		 * else { if (!chex_account_num.equals(chex_unique_isn)) { try {
		 * acctDetail = acUtil.GetAccountRows(dbConn, chex_account_num);
		 * //daysCheckValid =
		 * Integer.parseInt(acctDetail.getAccount_extra1())*(-1); } catch
		 * (Throwable t) { PrintLog("CheckForChanges: "+t); t.printStackTrace();
		 * } } }
		 */
		//
		// Just check if any of the following 5 fields was changed
		// -------------------------------------------------------
		if (chex_orig_account_num_o.compareTo(chex_orig_account_num) != 0) {
			PrintLog("CheckForChanges: orig Account >" +
					 chex_orig_account_num_o + "< new>" + chex_orig_account_num + "<");
			data_changed	= 1;
		}
		if (chex_orig_check_num_o.compareTo(chex_orig_check_num) != 0) {
			PrintLog("CheckForChanges:orig Check num >" + chex_orig_check_num_o +
					 "< new >" + chex_orig_check_num + "<");
			data_changed	= 1;
		}
		if (chex_account_num_o.compareTo(chex_account_num) != 0) {
			PrintLog("CheckForChanges: Account >" + chex_account_num_o +
					 "< new>" + chex_account_num + "<");
			data_changed	= 1;
			// this.createMap = true;
		}
		if (chex_check_num_o.compareTo(chex_check_num) != 0) {
			PrintLog("CheckForChanges: Check num >" + chex_check_num_o +
					 "< new >" + chex_check_num + "<");
			data_changed	= 1;
		}
		if (chex_check_currency_o.compareTo(chex_check_currency) != 0) {
			PrintLog("CheckForChanges: Check currency >" +
					 chex_check_currency_o + "< new >" + chex_check_currency + "<");
			data_changed	= 1;
		}
		PrintLog("CheckForChanges: Amount >" + chex_check_amount_o + "< new >" +
				 chex_check_amount + "<");
		if (chex_check_amount_o.compareTo(chex_check_amount) != 0) {
			PrintLog("CheckForChanges: Amount >" + chex_check_amount_o +
					 "< new >" + chex_check_amount + "<");
			data_changed	= 1;
		}
		if (chex_routing_transit_o.compareTo(chex_routing_transit) != 0) {
			PrintLog("CheckForChanges: routing_transit >" +
					 chex_routing_transit_o + "< new >" + chex_routing_transit + "<");
			data_changed	= 1;
		}
		if (chex_issue_date_o.compareTo(chex_issue_date) != 0
				&& !(chex_issue_date.equals("0"))) {
			PrintLog("CheckForChanges: issue-o >" + chex_issue_date_o +
					 "< new >" + chex_issue_date + "<");
			//check_issue_date = true;
			data_changed	= 1;
		}
		if (chex_payeeaba_o.compareTo(chex_payeeaba) != 0) {
			PrintLog("CheckForChanges: payeeaba-o >" + chex_payeeaba_o + "< new >" +
					 chex_payeeaba + "<");
			//payeeaba		= true;
			data_changed	= 1;
		}
		if (chex_payeeacct_o.compareTo(chex_payeeacct) != 0) {
			PrintLog("CheckForChanges: payeeacct-o >" + chex_payeeacct_o + "< new >" +
					 chex_payeeacct + "<");
			//payee = true;
			data_changed	= 1;
		}
		//PrintLog("CheckForChanges: payee-o >" + chex_payee_o + "< new >" + chex_payee + "<");
		if (chex_payee_o.compareTo(chex_payee) != 0 && !chex_payee.equals(" ")) {
			PrintLog("CheckForChanges: payee-o >" + chex_payee_o + "< new >" + chex_payee + "<");
			payee			= true;
			data_changed	= 1;
		}
		if (chex_payee_addr1_o.compareTo(chex_payee_addr1) != 0) {
			PrintLog("CheckForChanges: payee_addr1-o >" + chex_payee_addr1_o +
					 "< new >" + chex_payee_addr1 + "<");
			data_changed	= 1;
		}
		if (chex_payee_addr2_o.compareTo(chex_payee_addr2) != 0) {
			PrintLog("CheckForChanges: payee_addr2-o >" + chex_payee_addr2_o +
					 "< new >" + chex_payee_addr2 + "<");
			data_changed	= 1;
		}
		if (chex_payee_addr3_o.compareTo(chex_payee_addr3) != 0) {
			PrintLog("CheckForChanges: payee_addr3-o >" + chex_payee_addr3_o +
					 "< new >" + chex_payee_addr3 + "<");
			data_changed	= 1;
		}
		if (chex_comments_o.compareTo(chex_comments) != 0) {
			PrintLog("CheckForChanges: Comments-o >" + chex_comments_o +
					 "< new >" + chex_comments + "<");
			data_changed	= 1;
		}
		//if (chex_return_reason_o.compareTo(chex_return_reason) != 0) {
		//	PrintLog("CheckForChanges: return reason-o >" +
		//			 chex_return_reason_o + "< new >" + chex_return_reason + "<");
		//	data_changed	= 1;
		//}
		if (this.chexPayerName_o==null) {
			this.chexPayerName_o	= " ";
		}
		if (this.chexPayerName_o.compareTo(chexPayerName) != 0) {
			PrintLog("CheckForChanges: payer name>" +
					 chexPayerName_o + "< new >" + chexPayerName + "<");
			this.createPayer	= true;
			data_changed		= 1;
		}
		if (this.chexPayerCountry_o==null) {
			this.chexPayerCountry_o	= " ";
		}
		if (this.chexPayerCountry_o.compareTo(chexPayerCountry) != 0) {
			PrintLog("CheckForChanges: payer Country>" +
					 chexPayerCountry_o + "< new >" + chexPayerCountry + "<");
			this.createPayer	= true;
			data_changed		= 1;
		}
		//
		// Validate here
		// Check Num
		//
		try {
			if (Double.valueOf(chex_check_num).toString().equals("0.0")) {
				data_changed = 2;
				setErrorVec("Check Number", "error.field.missingnum");
			}
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("checknum", "error.field.nonnum");
		}
		//
		// Check Currency
		//
		// int space_at = modtime.indexOf(" ");
		/*
		 * if (chex_account_num.indexOf(chex_check_currency) > 0) { // OK } else
		 * { data_changed = 2; setErrorVec("Currency","error.field.invalid"); }
		 */
		//
		// Check Amount
		//
		try {
			if (Double.valueOf(chex_check_amount).toString().equals("0.0")) {
				// if (Double.compare(Double.valueOf(chex_check_num) ==
				// Double.valueOf(cnum_zero))) {
				PrintLog("Invalid amount " + chex_check_amount);
				data_changed = 2;
				setErrorVec("Amount", "error.field.zero");
			}
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Amount", "error.field.nonnum");
		}
		//
		// Issue Date
		//
		if (this.chex_issue_date.equals("XXXXXXXX")) {
			//
		} else {
			//if ((check_issue_date == true) || (payee == true)) {
			if (payee == true) {
				/* Commented out for Commerz
				if ((chex_issue_date.trim().length() > 0)
						&& (chex_issue_date.trim().length() < 8)) {
					data_changed = 2;
					// ret_bool = false;
					setErrorVec("Issue Date", "error.field.invalid");
				} else if (chex_issue_date.trim().length() > 7) {
					date_yyyy = Integer.parseInt(chex_issue_date.substring(0, 4));
					date_mm = Integer.parseInt(chex_issue_date.substring(4, 6));
					date_dd = Integer.parseInt(chex_issue_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid Effective
						data_changed = 2;
						// ret_bool = false;
						setErrorVec("Issue Date", "error.field.invalid");
					} else {
						six_months = ValiDate.subtractDays(appl_date, daysCheckValid);
						// six_months = vDate.getPriorDates(today_mm, today_dd,
						// today_yyyy, months);
						PrintLog("appl Date: "+appl_date+" days check valid: "+daysCheckValid+
								" six Months: "+six_months);
						if (chex_issue_date.compareTo(six_months) < 0) { // older than six months
							PrintLog("CheckForChanges: Issue Date STALE CHECK " + six_months);
							setErrorVec("Issue Date", "warning.field.staledate");
						}
						if (chex_issue_date.compareTo(appl_date) > 0) {
							// } else { // Issue date greater than or equal to
							// processing date
							data_changed = 2;
							setErrorVec("Issue Date", "error.date.infuture");
						}
					}
				} else {
					data_changed	= 2;
					// ret_bool		= false;
					setErrorVec("Issue Date", "error.field.invalid");
				}
				 */
				if (chex_payee.trim().equals("")) {
					data_changed	= 2;
					// ret_bool		= false;
					setErrorVec("Payee", "error.field.required");
				}
			}
		}
		if (data_changed == 0) {
			setErrorVec("nochange", "info.nomods");
		}
		return (data_changed);
	}
	//
	public int CheckForBOFD() {
		int data_changed = 1;
		int date_yyyy = 0;
		int date_mm = 0;
		int date_dd = 0;
		//
		// PrintLog("CheckForBOFD: "+this.chex_bofd);
		if (actionFlag.equals("return_confirm")) {
			if (this.chex_BOFD_aba.equals("")) {
				PrintLog("CheckForBOFD: " + this.chex_BOFD_aba);
				data_changed = 2;
				setErrorVec("BoFD", "error.field.required");
			}
			if (this.chex_BOFD_aba.length() != 9) {
				PrintLog("setCheckForBOFD: " + this.chex_BOFD_aba);
				data_changed = 2;
				setErrorVec("BoFD", "error.field.required");
			}
			//
			// BOFD Business (Endorsement) Date
			//
			if ((this.chex_BOFD_date.trim().length() > 0)
					&& (this.chex_BOFD_date.trim().length() < 8)) {
				PrintLog("CheckForBOFD: Date1 " + this.chex_BOFD_date);
				data_changed = 2;
				// ret_bool = false;
				setErrorVec("BOFD Date", "error.field.invalid");
			} else if (this.chex_BOFD_date.trim().length() > 7) {
				try {
					date_yyyy = Integer.parseInt(this.chex_BOFD_date.substring(0, 4));
					date_mm = Integer.parseInt(this.chex_BOFD_date.substring(4,6));
					date_dd = Integer.parseInt(this.chex_BOFD_date.substring(6));
					PrintLog("CheckForBOFD: Date2 " + this.chex_BOFD_date +
							 " Appl_date: " + this.appl_date);
					if (this.chex_BOFD_date.compareTo(this.appl_date) > 0) {
						data_changed = 2;
						setErrorVec("BOFD Date", "error.date.infuture");
					}
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						data_changed = 2;
						setErrorVec("BOFD Date", "error.field.invalid");
					}
				} catch (Exception e) {
					data_changed = 2;
					setErrorVec("BOFD Date", "error.field.invalid");
				}
			} else if (this.chex_BOFD_date.equals("")) {
				PrintLog("CheckForBOFD: Date3 " + this.chex_BOFD_date);
				if (data_changed == 1) {
					data_changed = 2;
					setErrorVec("BOFD Date", "error.field.required");
				}
			}
		}
		/*
		 * if (data_changed == 0) { setErrorVec("nomods","info.nomods"); }
		 */
		return (data_changed);
	}
}
