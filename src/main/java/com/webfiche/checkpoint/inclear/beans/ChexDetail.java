package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.inclear.service.InclAcctUtil;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.2 $ $Date: 2016/04/09 14:33:06 $
 */

public final class ChexDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID		= 1L;
	// --------------------------------------------------- Instance Variables
	private String	className				= "> ChexDetail.";
	private String	moduleName				= "";
	private String	userId					= "";
	private int		addendumCount			= 0;
	private String	actionFlag				= "";
	private String	accessFlag				= "";
	private String	dbUsed					= "";
	private String	dbTable					= "incl_chex";
	private String	appl_date				= "";
	private String	bankId					= "";
	private String	defCurr					= "";
	private String	chex_proc_date			= "";
	private String	chex_proc_date_disp		= "";
	private String	chex_orig_account_num   = "";
	private String	chex_orig_check_num		= "";
	private String	chex_account_num		= "";
	private String	chex_check_num			= "";
	private String	chex_routing_transit	= "";
	private String	chex_check_digit		= "";
	private String	chex_check_currency		= "";
	private String	chex_check_amount_disp	= "";
	private String	chex_check_amount		= "";
	private String	chex_proc_control		= "";
	private String	chex_ext_proc_control	= "";
	private String	chex_image_locator		= "";
	private String	chex_unique_isn			= "";
	private String	chex_addenda_rec_flag   = "";
	private String	chex_orig_inst_rte		= "";
	private String	chex_isn				= "";
	private String	chex_budget_id			= "";
	private String	chex_return_date		= "";
	private String	chex_return_reason		= "";
	private String	chex_return_status		= "";
	private String	chex_BOFD_aba			= "";
	private String	chex_BOFD_date			= "";
	private String	chex_extra_1			= "";
	private String	chex_extra_2			= "";
	private String	chex_issue_date			= "";
	private String	chex_issue_date_disp	= "";
	private String	chex_payee				= "";
	private String	chex_payee_addr1		= "";
	private String	chex_payee_addr2		= "";
	private String	chex_payee_addr3		= "";
	private String	chex_comments			= "";
	private String	chex_reason_codes		= "";
	private String	chex_check_status		= "";
	private String	chex_amount_od_disp		= "";
	private String	chex_amount_od			= "";
	private String	chex_last_modified		= "";
	private String	chex_mod_user			= "";
	private String	chex_mod_func			= "";
	private String	chex_image				= "";
	private String	chex_image_f			= "";
	private String	chex_image_b			= "";
	private String	chex_reason_desc		= "";
	private String	chex_status_desc		= "";
	// private String chex_ret_reason = "";
	// private String chex_bofd = "";
	// private String chex_bofdDate = "";
	private String	chex_fileName			= "";
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
	private String	chex_return_date_o		= "";
	private String	chex_return_reason_o	= "";
	private String	chex_return_status_o	= "";
	private String	chex_BOFD_aba_o			= "";
	private String	chex_BOFD_date_o		= "";
	private String	chex_extra_1_o			= "";
	private String	chex_extra_2_o			= "";
	private String	chex_issue_date_o		= "";
	private String	chex_payee_o			= "";
	private String	chex_payee_addr1_o		= "";
	private String	chex_payee_addr2_o		= "";
	private String	chex_payee_addr3_o		= "";
	private String	chex_comments_o			= "";
	private String	chex_reason_codes_o		= "";
	private String	chex_check_status_o		= "";
	private String	chex_amount_od_o		= "";
	private String	chex_last_modified_o	= "";
	private String	chex_mod_user_o			= "";
	private String	chex_mod_func_o			= "";
	private String	temp					= "";
	private boolean checkForDuplicates		= false;
	// private String chex_ret_reason_o = "";
	// private String chex_bofd_o = "";
	// private String chex_bofdDate_o = "";
	//
	private Vector<Vector<String>>  errorVec				= new Vector<Vector<String>>();
	//
	private HashMap<String, String> chex_modparam		   = new HashMap<String, String>();
	private String				  chex_auth_rej		   = "";
	//
	DecimalFormat				   df					  = new DecimalFormat(
																	"###,##0.00");

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
	public int getAddendumCount() {
		return (this.addendumCount);
	}
	public void setAddendumCount(int addendumCount) {
		this.addendumCount = addendumCount;
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
		this.chex_proc_date = chex_proc_date;
		this.chex_proc_date_disp = (chex_proc_date.substring(4, 6) + "/"
				+ chex_proc_date.substring(6) + "/" + chex_proc_date.substring(
				0, 4));
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
	public String getChex_check_num() {
		return (this.chex_check_num);
	}
	public void setChex_check_num(String chex_check_num) {
		this.chex_check_num = chex_check_num;
	}
	public String getChex_routing_transit() {
		return (this.chex_routing_transit);
	}
	public void setChex_routing_transit(String chex_routing_transit) {
		this.chex_routing_transit = chex_routing_transit;
	}
	public String getChex_check_digit() {
		return (this.chex_check_digit);
	}
	public void setChex_check_digit(String chex_check_digit) {
		this.chex_check_digit = chex_check_digit;
	}
	public String getChex_check_currency() {
		return (this.chex_check_currency);
	}
	public void setChex_check_currency(String chex_check_currency) {
		this.chex_check_currency = chex_check_currency;
	}
	public String getChex_check_amount() {
		return (this.chex_check_amount);
	}
	public String getChex_check_amount_disp() {
		return (this.chex_check_amount_disp);
	}
	public void setChex_check_amount(String chex_check_amount) {
		temp	= chex_check_amount;
		if (temp.equals("")) {
			temp	= "0";
		}
		this.chex_check_amount = temp;
		this.chex_check_amount_disp = df.format(Double.parseDouble(this.chex_check_amount));
	}
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
	public String getChex_budget_id() {
		return (this.chex_budget_id);
	}
	public void setChex_budget_id(String chex_budget_id) {
		this.chex_budget_id = chex_budget_id;
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
			this.chex_issue_date_disp = (chex_issue_date.substring(4, 6) + "/" +
										 chex_issue_date.substring(6) + "/" + 
										 chex_issue_date.substring(0, 4));
		} else {
			this.chex_issue_date_disp = chex_issue_date;
		}
	}
	public String getChex_payee() {
		return (this.chex_payee);
	}
	public void setChex_payee(String chex_payee) {
		this.chex_payee = chex_payee;
	}
	public String getChex_payee_addr1() {
		return (this.chex_payee_addr1);
	}
	public void setChex_payee_addr1(String chex_payee_addr1) {
		this.chex_payee_addr1 = chex_payee_addr1;
	}
	public String getChex_payee_addr2() {
		return (this.chex_payee_addr2);
	}
	public void setChex_payee_addr2(String chex_payee_addr2) {
		this.chex_payee_addr2 = chex_payee_addr2;
	}
	public String getChex_payee_addr3() {
		return (this.chex_payee_addr3);
	}
	public void setChex_payee_addr3(String chex_payee_addr3) {
		this.chex_payee_addr3 = chex_payee_addr3;
	}
	public String getChex_comments() {
		return (this.chex_comments);
	}
	public void setChex_comments(String chex_comments) {
		this.chex_comments = chex_comments;
	}
	public String getChex_reason_codes() {
		return (this.chex_reason_codes);
	}
	public void setChex_reason_codes(String chex_reason_codes) {
		this.chex_reason_codes = chex_reason_codes;
	}
	public String getChex_check_status() {
		return (this.chex_check_status);
	}
	public void setChex_check_status(String chex_check_status) {
		this.chex_check_status = chex_check_status;
	}
	public String getChex_amount_od() {
		return (this.chex_amount_od);
	}
	public String getChex_amount_od_disp() {
		return (this.chex_amount_od_disp);
	}
	public void setChex_amount_od(String chex_amount_od) {
		this.chex_amount_od = chex_amount_od;
		if (this.chex_amount_od.equals("")) {
			this.chex_amount_od = "0.00";
		}
		String temp = df.format(Double.parseDouble(this.chex_amount_od));
		this.chex_amount_od_disp = temp;
	}
	//
	public String getChex_last_modified() {
		return (this.chex_last_modified);
	}
	public void setChex_last_modified(String chex_last_modified) {
		// PrintLog(".setChex_last_modified: "+chex_last_modified);
		// 2012-02-09 15:28:34
		int space_at = chex_last_modified.indexOf(" ");
		if (!chex_last_modified.equals("") && chex_last_modified.length() > 15) {
			this.chex_last_modified = (chex_last_modified
					.substring(0, space_at) + " @ " + chex_last_modified
					.substring(space_at + 1, chex_last_modified.length()));
		} else if (!chex_last_modified.equals("")
				&& chex_last_modified.length() > 13) {
			this.chex_last_modified = chex_last_modified.substring(0, 4) + "/";
			this.chex_last_modified += chex_last_modified.substring(4, 6) + "/";
			this.chex_last_modified += chex_last_modified.substring(6, 8)
					+ " @ ";
			this.chex_last_modified += chex_last_modified.substring(8, 10)
					+ ":";
			this.chex_last_modified += chex_last_modified.substring(10, 12)
					+ ":";
			this.chex_last_modified += chex_last_modified.substring(12, 14);
		} else {
			this.chex_last_modified = chex_last_modified;
		}
		// PrintLog("setChex_last_modified Formatted:"+this.chex_last_modified);
	}
	//
	public String getChex_mod_user() {
		return (this.chex_mod_user);
	}
	public void setChex_mod_user(String chex_mod_user) {
		this.chex_mod_user = chex_mod_user;
	}
	//
	public String getChex_mod_func() {
		return (this.chex_mod_func);
	}
	public void setChex_mod_func(String chex_mod_func) {
		this.chex_mod_func = chex_mod_func;
	}
	//
	public String getChex_reason_desc() {
		return (this.chex_reason_desc);
	}
	public void setChex_reason_desc(String chex_reason_desc) {
		this.chex_reason_desc = chex_reason_desc;
	}
	//
	public String getChex_status_desc() {
		return (this.chex_status_desc);
	}
	public void setChex_status_desc(String chex_status_desc) {
		this.chex_status_desc = chex_status_desc;
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
	public void setCheckForDuplicates (boolean checkForDuplicates) {
		this.checkForDuplicates	= checkForDuplicates;
	}
	public boolean getCheckForDuplicates () {
		return checkForDuplicates;
	}
	//
	// Hash map for passing parameters for modify
	//
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
	public String getChex_last_modified_o() {
		return (this.chex_last_modified_o);
	}
	public void setChex_last_modified_o(String chex_last_modified_o) {
		this.chex_last_modified_o = chex_last_modified_o;
	}
	public String getChex_mod_user_o() {
		return (this.chex_mod_user_o);
	}
	public void setChex_mod_user_o(String chex_mod_user_o) {
		this.chex_mod_user_o = chex_mod_user_o;
	}
	public String getChex_mod_func_o() {
		return (this.chex_mod_func_o);
	}
	public void setChex_mod_func_o(String chex_mod_func_o) {
		this.chex_mod_func_o = chex_mod_func_o;
	}
	//
	public void ShowDetails() {
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
		PrintLog("chex_extra_1\t\t" + chex_extra_1);
		PrintLog("chex_extra_2\t\t" + chex_extra_2);
		PrintLog("chex_issue_date\t\t" + chex_issue_date);
		PrintLog("chex_payee\t\t" + chex_payee);
		PrintLog("chex_payee_addr1\t\t" + chex_payee_addr1);
		PrintLog("chex_payee_addr2\t\t" + chex_payee_addr2);
		PrintLog("chex_payee_addr3\t\t" + chex_payee_addr3);
		PrintLog("chex_comments\t\t" + chex_comments);
		PrintLog("chex_reason_codes\t\t" + chex_reason_codes);
		PrintLog("chex_check_status\t\t" + chex_check_status);
		PrintLog("chex_amount_od\t\t" + chex_amount_od);
		PrintLog("chex_last_modified\t\t" + chex_last_modified);
		PrintLog("chex_mod_user\t\t" + chex_mod_user);
		PrintLog("chex_mod_func\t\t" + chex_mod_func);
	}
	//
	public void clearNulls() {
		chex_proc_date = (chex_proc_date == null) ? " " : chex_proc_date;
		chex_orig_account_num = (chex_orig_account_num == null) ? " " : chex_orig_account_num;
		chex_orig_check_num = (chex_orig_check_num == null) ? " " : chex_orig_check_num;
		chex_account_num = (chex_account_num == null) ? " " : chex_account_num;
		chex_check_num = (chex_check_num == null) ? " " : chex_check_num;
		chex_routing_transit = (chex_routing_transit == null) ? " " : chex_routing_transit;
		chex_check_digit = (chex_check_digit == null) ? " " : chex_check_digit;
		chex_check_currency = (chex_check_currency == null) ? " " : chex_check_currency;
		chex_check_amount = (chex_check_amount == null) ? " " : chex_check_amount;
		chex_proc_control = (chex_proc_control == null) ? " " : chex_proc_control;
		chex_ext_proc_control = (chex_ext_proc_control == null) ? " " : chex_ext_proc_control;
		chex_image_locator = (chex_image_locator == null) ? " " : chex_image_locator;
		chex_unique_isn = (chex_unique_isn == null) ? " " : chex_unique_isn;
		chex_addenda_rec_flag = (chex_addenda_rec_flag == null) ? " " : chex_addenda_rec_flag;
		chex_orig_inst_rte = (chex_orig_inst_rte == null) ? " " : chex_orig_inst_rte;
		chex_isn = (chex_isn == null) ? " " : chex_isn;
		chex_budget_id = (chex_budget_id == null) ? " " : chex_budget_id;
		chex_extra_1 = (chex_extra_1 == null) ? " " : chex_extra_1;
		chex_extra_2 = (chex_extra_2 == null) ? " " : chex_extra_2;
		chex_issue_date = (chex_issue_date == null) ? " " : chex_issue_date;
		chex_payee = (chex_payee == null) ? " " : chex_payee;
		chex_payee_addr1 = (chex_payee_addr1 == null) ? " " : chex_payee_addr1;
		chex_payee_addr2 = (chex_payee_addr2 == null) ? " " : chex_payee_addr2;
		chex_payee_addr3 = (chex_payee_addr3 == null) ? " " : chex_payee_addr3;
		chex_comments = (chex_comments == null) ? " " : chex_comments;
		chex_reason_codes = (chex_reason_codes == null) ? " " : chex_reason_codes;
		chex_check_status = (chex_check_status == null) ? " " : chex_check_status;
		chex_amount_od = (chex_amount_od == null) ? " " : chex_amount_od;
		chex_last_modified = (chex_last_modified == null) ? " " : chex_last_modified;
		chex_mod_user = (chex_mod_user == null) ? " " : chex_mod_user;
		chex_mod_func = (chex_mod_func == null) ? " " : chex_mod_func;
	}
	public void clearDetails() {
		chex_proc_date = "";
		chex_orig_account_num = "";
		chex_orig_check_num = "";
		chex_account_num = "";
		chex_check_num = "";
		chex_routing_transit = "";
		chex_check_digit = "";
		chex_check_currency = "";
		chex_check_amount = "";
		chex_proc_control = "";
		chex_ext_proc_control = "";
		chex_image_locator = "";
		chex_unique_isn = "";
		chex_addenda_rec_flag = "";
		chex_orig_inst_rte = "";
		chex_isn = "";
		chex_budget_id = "";
		chex_extra_1 = "";
		chex_extra_2 = "";
		chex_issue_date = "";
		chex_payee = "";
		chex_payee_addr1 = "";
		chex_payee_addr2 = "";
		chex_payee_addr3 = "";
		chex_comments = "";
		chex_reason_codes = "";
		chex_check_status = "";
		chex_amount_od = "";
		chex_last_modified = "";
		chex_mod_user = "";
		chex_mod_func = "";
		setChex_make_copy();
	}
	//
	public void setChex_make_copy() {
		clearNulls();
		chex_proc_date_o = chex_proc_date;
		chex_orig_account_num_o = chex_orig_account_num;
		chex_orig_check_num_o = chex_orig_check_num;
		chex_account_num_o = chex_account_num;
		chex_check_num_o = chex_check_num;
		chex_routing_transit_o = chex_routing_transit;
		chex_check_digit_o = chex_check_digit;
		chex_check_currency_o = chex_check_currency;
		chex_check_amount_o = chex_check_amount;
		chex_proc_control_o = chex_proc_control;
		chex_ext_proc_control_o = chex_ext_proc_control;
		chex_image_locator_o = chex_image_locator;
		chex_unique_isn_o = chex_unique_isn;
		chex_addenda_rec_flag_o = chex_addenda_rec_flag;
		chex_orig_inst_rte_o = chex_orig_inst_rte;
		chex_isn_o = chex_isn;
		chex_budget_id_o = chex_budget_id;
		chex_extra_1_o = chex_extra_1;
		chex_extra_2_o = chex_extra_2;
		chex_issue_date_o = chex_issue_date;
		chex_payee_o = chex_payee;
		chex_payee_addr1_o = chex_payee_addr1;
		chex_payee_addr2_o = chex_payee_addr2;
		chex_payee_addr3_o = chex_payee_addr3;
		chex_comments_o = chex_comments;
		chex_reason_codes_o = chex_reason_codes;
		chex_check_status_o = chex_check_status;
		chex_amount_od_o = chex_amount_od;
		chex_last_modified_o = chex_last_modified;
		chex_mod_user_o = chex_mod_user;
		chex_mod_func_o = chex_mod_func;
	}
	public int ChexAccountChanged() {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed = 0;
		checkForDuplicates	= false;
		if (chex_account_num_o.compareTo(chex_account_num) != 0) {
			data_changed = 1;
			if (chex_account_num.equals("")) {
				setErrorVec("Account Number", "error.field.required");
				data_changed = 2;
			}
		}
		if (data_changed == 1) {
			checkForDuplicates	= true;
		}
		return (data_changed);
	}
	//
	public int CheckForChanges(Connection dbConn) {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed	= 0;
		int date_yyyy		= 0;
		int date_mm			= 0;
		int date_dd			= 0;
		// int months = 6;
		// ValiDate vDate = new ValiDate();
		boolean check_issue_date	= false;
		boolean payee		= false;
		String six_months	= "";
		// String twelve_months = "";
		int daysCheckValid	= 180;
		checkForDuplicates	= false;
		clearNulls();
		InclAcctUtil acUtil = new InclAcctUtil();
		AccountDetail acctDetail = new AccountDetail();
		//
		PrintLog("CheckForChanges: Appl_date: " + appl_date);
		//
		// Just check if any of the following 5 fields was changed
		// -------------------------------------------------------
		if (chex_account_num_o.compareTo(chex_account_num) != 0) {
			PrintLog("CheckForChanges: Account>" + chex_account_num_o
					+ "< new>" + chex_account_num + "<");
			data_changed = 1;
			checkForDuplicates	= true;
		}
		if (chex_check_num_o.compareTo(chex_check_num) != 0) {
			PrintLog("CheckForChanges: Check num" + chex_check_num_o + "< new>"
					+ chex_check_num + "<");
			data_changed = 1;
			checkForDuplicates	= true;
		}
		if (chex_check_currency_o.compareTo(chex_check_currency) != 0) {
			PrintLog("CheckForChanges: Check currency" + chex_check_currency_o
					+ "< new>" + chex_check_currency + "<");
			data_changed = 1;
		}
		if (chex_check_amount_o.compareTo(chex_check_amount) != 0) {
			PrintLog("CheckForChanges: Amount" + chex_check_amount_o + "< new>"
					+ chex_check_amount + "<");
			data_changed = 1;
		}
		if (chex_issue_date_o.compareTo(chex_issue_date) != 0
				&& !(chex_issue_date.equals("0"))) {
			PrintLog("CheckForChanges: issue-o>" + chex_issue_date_o + "< new>"
					+ chex_issue_date + "<");
			check_issue_date = true;
			data_changed = 1;
		}
		if (chex_payee_o.compareTo(chex_payee) != 0) {
			PrintLog("CheckForChanges: payee-o>" + chex_payee_o + "< new>"
					+ chex_payee + "<");
			payee 			= true;
			data_changed	= 1;
		}
		if (chex_payee_addr1_o.compareTo(chex_payee_addr1) != 0) {
			PrintLog("CheckForChanges: payee_addr1-o>" + chex_payee_addr1_o
					+ "< new>" + chex_payee_addr1 + "<");
			data_changed	= 1;
		}
		if (chex_payee_addr2_o.compareTo(chex_payee_addr2) != 0) {
			PrintLog("CheckForChanges: payee_addr2-o>" + chex_payee_addr2_o
					+ "< new>" + chex_payee_addr2 + "<");
			data_changed	= 1;
		}
		if (chex_payee_addr3_o.compareTo(chex_payee_addr3) != 0) {
			PrintLog("CheckForChanges: payee_addr3-o>" + chex_payee_addr3_o
					+ "< new>" + chex_payee_addr3 + "<");
			data_changed	= 1;
		}
		if (chex_comments_o.compareTo(chex_comments) != 0) {
			PrintLog("CheckForChanges: Comments-o>" + chex_comments_o
					+ "< new>" + chex_comments + "<");
			data_changed	= 1;
		}
		if (chex_return_reason_o.compareTo(chex_return_reason.trim()) != 0) {
			PrintLog("CheckForChanges: return reason-o>" + chex_return_reason_o
					+ "< new>" + chex_return_reason + "<");
			data_changed	= 1;
		}
		//
		// Validate here
		//
		// Get the account Detail
		//
		try {
			if (acUtil.AccountExists(dbConn, appl_date, chex_account_num)) {
				PrintLog("CheckForChanges: " + chex_account_num);
				acctDetail		= acUtil.GetAccountRows(dbConn, chex_account_num);
				daysCheckValid	= Integer.parseInt(acctDetail.getAccount_dayscheckvalid());
			} else {
				setErrorVec("Check Account", "error.field.invalid");
				data_changed	= 2;
			}
		} catch (Throwable t) {
			PrintLog("CheckForChanges: " + t);
			t.printStackTrace(System.out);
		}
		if (daysCheckValid==0) {
			daysCheckValid	= 180;
		}
		//
		// Check Num
		//
		try {
			if (Double.valueOf(chex_check_num).toString().equals("0.0")) {
				data_changed = 2;
				setErrorVec("Check Number", "error.field.zero");
			}
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Check Number", "error.field.nonnum");
		}
		//
		// Check Currency
		//
		// int space_at = chex_last_modified.indexOf(" ");
		/*
		if (chex_account_num.indexOf(chex_check_currency) > 0) {
			// OK
		} else {
			data_changed = 2;
			setErrorVec("Currency", "error.field.invalid");
		}
		*/
		//
		// Check Amount
		//
		try {
			if (Double.valueOf(chex_check_amount).toString().equals("0.0")) {
				// if (Double.compare(Double.valueOf(chex_check_num) ==
				// Double.valueOf(cnum_zero))) {
				data_changed = 2;
				setErrorVec("Check Amount", "error.field.zero");
			}
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Check Amount", "error.field.nonnum");
		}
		//
		// Issue Date
		//
		if (this.chex_issue_date.equals("XXXXXXXX")) {
			//
		} else {
			// today_yyyy = Integer.parseInt(appl_date.substring(0,4));
			// today_mm = Integer.parseInt(appl_date.substring(4,6));
			// today_dd = Integer.parseInt(appl_date.substring(6));
			if ((check_issue_date == true) || (payee == true)) {
				if (ValiDate.DateAllNumeric(chex_issue_date) == true) {
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
							PrintLog("days Check Valid "+ daysCheckValid + " applDate: "+appl_date);
							PrintLog("CheckForChanges: Issue Date "+ chex_issue_date +
									 " Six Months back is " + six_months);
							// six_months = vDate.getPriorDates(today_mm, today_dd,
							// today_yyyy, months);
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
						data_changed = 2;
						// ret_bool = false;
						setErrorVec("Issue Date", "error.field.invalid");
					}
				} else {
					data_changed = 2;
					// ret_bool = false;
					setErrorVec("Issue Date", "error.field.invalid");
				}
				if (chex_payee.trim().equals("")) {
					data_changed = 2;
					// ret_bool = false;
					setErrorVec("Payee", "error.field.required");
				}
			}
		}
		if (data_changed == 0) {
			setErrorVec("checkdata", "info.nomods");
		}
		return (data_changed);
	}
	//
	public int CheckForBOFD() {
		int data_changed = 1;
		int date_yyyy = 0;
		int date_mm = 0;
		int date_dd = 0;
		// int today_yyyy = 0;
		// int today_mm = 0;
		// int today_dd = 0;
		// int months = 6;
		// ValiDate vDate = new ValiDate();
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
				setErrorVec("BoFD", "error.field.abasize");
			}
			//
			// BOFD Business (Endorsement) Date
			//
			if ((this.chex_BOFD_date.trim().length() > 0)
					&& (this.chex_BOFD_date.trim().length() < 8)) {
				PrintLog("CheckForBOFD: Date1 " + this.chex_BOFD_date);
				data_changed = 2;
				// ret_bool = false;
				setErrorVec("BoFD", "error.field.invalid");
			} else if (this.chex_BOFD_date.trim().length() > 7) {
				try {
					date_yyyy = Integer.parseInt(this.chex_BOFD_date.substring(0, 4));
					date_mm = Integer.parseInt(this.chex_BOFD_date.substring(4,6));
					date_dd = Integer.parseInt(this.chex_BOFD_date.substring(6));
					PrintLog("CheckForBOFD: Date2 " + this.chex_BOFD_date +
							 " Appl_date: " + this.appl_date);
					if (this.chex_BOFD_date.compareTo(this.appl_date) > 0) {
						data_changed = 2;
						setErrorVec("BoFD Date", "error.date.infuture");
					}
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						data_changed = 2;
						setErrorVec("BoFD Date", "error.field.invalid");
					}
				} catch (Exception e) {
					data_changed = 2;
					setErrorVec("BoFD Date", "error.field.invalid");
				}
			} else if (this.chex_BOFD_date.equals("")) {
				PrintLog("CheckForBOFD: Date3 " + this.chex_BOFD_date);
				if (data_changed == 1) {
					data_changed = 2;
					setErrorVec("BoFD Date", "error.field.required");
				}
			}
		}
		/*
		 * if (data_changed == 0) { setErrorVec("nomods","info.nomods"); }
		 */
		return (data_changed);
	}
}
