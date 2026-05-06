package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;
import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.2 $ $Date: 2016/04/09 14:33:06 $
 */

public final class AccountDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID			= 1L;
	// --------------------------------------------------- Instance Variables
	private String	className					= "> AccountDetail.";
	private String	moduleName					= "";
	private String	actionFlag					= "";
	private String	accessFlag					= "";
	private String	dbUsed						= "";
	private String	appl_date					= "";
	private String	bankId						= "";
	private String	userId						= "";
	private String	prodId						= "";
	private String	defCurr						= "";
	private String	account_num					= "";
	private String	account_currency			= "";
	private String	account_acc_rep				= "";
	private String	account_alt_acc_rep			= "";
	private String	account_sw_addr				= "";
	private String	account_client_name			= "";
	private String	account_client_addr1		= "";
	private String	account_client_addr2		= "";
	private String	account_client_addr3		= "";
	private String	account_client_addr4		= "";
	private String	account_effective_date		= "";
	private String	account_closed_date			= "";
	private String	account_blocked_date		= "";
	private String	account_max_checkamt_disp	= "0";
	private String	account_max_check_amount	= "0";
	private String	account_posi_pay_flag		= "N";
	private String	account_posi_payamtmin_disp = "0";
	private String	account_posi_pay_amt_min	= "0";
	private String	account_int_ext			 	= "";	// C=Creditor D=Debtor E=External I=Internal
	private String	account_gen_suspense		= "";	// Account num on check
	private String	account_stop_suspense		= "";	// Used for Creditors Account Number
	private String	account_exce_suspense		= "";
	private String	account_stmt_email			= "";
	private String	account_stmt_fax			= "";
	private String	account_stmt_mail1			= "";
	private String	account_stmt_mail2			= "";
	private String	account_stmt_mail3			= "";
	private String	account_stmt_mail4			= "";
	private String	account_stmt_emailfreqD		= "";
	private String	account_stmt_emailfreqW		= "";
	private String	account_stmt_emailfreqM		= "";
	private String	account_stmt_faxfreqD		= "";
	private String	account_stmt_faxfreqW		= "";
	private String	account_stmt_faxfreqM		= "";
	private String	account_stmt_mailfreqD		= "";
	private String	account_stmt_mailfreqW		= "";
	private String	account_stmt_mailfreqM		= "";
	private String	account_stmt_extra			= "";
	private String	account_user_comments		= "";
	private String	account_dayscheckvalid		= "";
	private String	account_extra1				= "";
	private String	account_extra2				= "";
	private String	account_extra3				= "";
	private String	account_extra4				= "";
	private String	account_extra5				= "";
	private String	account_extra6				= "";
	private String	account_availday1			= "0";
	private String	account_availlow1			= "0.00";
	private String	account_availhigh1			= "0.00";
	private String	account_availday2			= "0";
	private String	account_availlow2			= "0.00";
	private String	account_availhigh2			= "0.00";
	private String	account_availday3			= "0";
	private String	account_availlow3			= "0.00";
	private String	account_availhigh3			= "0.00";
	private String	account_availday4			= "0";
	private String	account_availlow4			= "0.00";
	private String	account_availhigh4			= "0.00";
	private String	account_availday5			= "0";
	private String	account_availlow5			= "0.00";
	private String	account_availhigh5			= "0.00";
	private String	account_last_modified		= "";
	private String	account_mod_user			= "";
	private String	account_mod_func			= "";
	//
	private String	account_currency_o			= "";
	private String	account_acc_rep_o			= "";
	private String	account_alt_acc_rep_o		= "";
	private String	account_sw_addr_o			= "";
	private String	account_client_name_o		= "";
	private String	account_client_addr1_o		= "";
	private String	account_client_addr2_o		= "";
	private String	account_client_addr3_o		= "";
	private String	account_client_addr4_o		= "";
	private String	account_effective_date_o	= "";
	private String	account_closed_date_o		= "";
	private String	account_blocked_date_o		= "";
	private String	account_max_check_amount_o	= "";
	private String	account_posi_pay_flag_o		= "";
	private String	account_posi_pay_amt_min_o	= "";
	private String	account_int_ext_o			= "";
	private String	account_gen_suspense_o		= "";
	private String	account_stop_suspense_o		= "";
	private String	account_exce_suspense_o		= "";
	private String	account_stmt_email_o		= "";
	private String	account_stmt_fax_o			= "";
	private String	account_stmt_mail1_o		= "";
	private String	account_stmt_mail2_o		= "";
	private String	account_stmt_mail3_o		= "";
	private String	account_stmt_mail4_o		= "";
	private String	account_stmt_emailfreqD_o	= "";
	private String	account_stmt_emailfreqW_o	= "";
	private String	account_stmt_emailfreqM_o	= "";
	private String	account_stmt_faxfreqD_o		= "";
	private String	account_stmt_faxfreqW_o		= "";
	private String	account_stmt_faxfreqM_o		= "";
	private String	account_stmt_mailfreqD_o	= "";
	private String	account_stmt_mailfreqW_o	= "";
	private String	account_stmt_mailfreqM_o	= "";
	private String	account_stmt_extra_o		= "";
	private String	account_user_comments_o		= "";
	private String	account_dayscheckvalid_o	= "";
	private String	account_extra1_o			= "";
	private String	account_extra2_o			= "";
	private String	account_extra3_o			= "";
	private String	account_extra4_o			= "";
	private String	account_extra5_o			= "";
	private String	account_extra6_o			= "";
	private String	account_availday1_o			= "";
	private String	account_availlow1_o			= "";
	private String	account_availhigh1_o		= "";
	private String	account_availday2_o			= "";
	private String	account_availlow2_o			= "";
	private String	account_availhigh2_o		= "";
	private String	account_availday3_o			= "";
	private String	account_availlow3_o			= "";
	private String	account_availhigh3_o		= "";
	private String	account_availday4_o			= "";
	private String	account_availlow4_o			= "";
	private String	account_availhigh4_o		= "";
	private String	account_availday5_o			= "";
	private String	account_availlow5_o			= "";
	private String	account_availhigh5_o		= "";
	private String	account_last_modified_o		= "";
	private String	account_mod_user_o			= "";
	private String	account_mod_func_o			= "";
	//
	//
	private Vector<Vector<String>> errorVec					= new Vector<Vector<String>>();
	//
	DecimalFormat	 df		= new DecimalFormat(														  "###,##0.00");
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
		Vector<String> vecPair = new Vector<String>();
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
	// Account Row fields
	//
	public String getAccount_num() {
		return (this.account_num);
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num.trim();
	}
	//
	public String getAccount_currency() {
		return (this.account_currency);
	}
	public void setAccount_currency(String account_currency) {
		// (account_currency == null) ? " " : account_currency.trim().toUpperCase();
		this.account_currency = account_currency;
	}
	//
	public String getAccount_acc_rep() {
		return (this.account_acc_rep);
	}
	public void setAccount_acc_rep(String account_acc_rep) {
		this.account_acc_rep = account_acc_rep;
	}
	//
	public String getAccount_alt_acc_rep() {
		return (this.account_alt_acc_rep);
	}
	public void setAccount_alt_acc_rep(String account_alt_acc_rep) {
		this.account_alt_acc_rep = account_alt_acc_rep;
	}
	//
	public String getAccount_sw_addr() {
		return (this.account_sw_addr);
	}
	public void setAccount_sw_addr(String account_sw_addr) {
		moduleName = "setAccount_sw_addr: ";
		this.account_sw_addr = account_sw_addr;
	}
	//
	public String getAccount_client_name() {
		return (this.account_client_name);
	}
	public void setAccount_client_name(String account_client_name) {
		if (account_client_name.length() > 35) {
			this.account_client_name = account_client_name.substring(0, 35);
		} else {
			this.account_client_name = account_client_name;
		}
	}
	//
	public String getAccount_client_addr1() {
		return (this.account_client_addr1);
	}
	public void setAccount_client_addr1(String account_client_addr1) {
		this.account_client_addr1 = account_client_addr1;
	}
	//
	public String getAccount_client_addr2() {
		return (this.account_client_addr2);
	}
	public void setAccount_client_addr2(String account_client_addr2) {
		this.account_client_addr2 = account_client_addr2;
	}
	//
	public String getAccount_client_addr3() {
		return (this.account_client_addr3);
	}
	public void setAccount_client_addr3(String account_client_addr3) {
		this.account_client_addr3 = account_client_addr3;
	}
	//
	public String getAccount_client_addr4() {
		return (this.account_client_addr4);
	}
	public void setAccount_client_addr4(String account_client_addr4) {
		this.account_client_addr4 = account_client_addr4;
	}
	//
	public String getAccount_effective_date() {
		return (this.account_effective_date);
	}
	public void setAccount_effective_date(String account_effective_date) {
		this.account_effective_date = account_effective_date;
	}
	//
	public String getAccount_closed_date() {
		return (this.account_closed_date);
	}
	public void setAccount_closed_date(String account_closed_date) {
		this.account_closed_date = account_closed_date;
	}
	//
	public String getAccount_blocked_date() {
		return (this.account_blocked_date);
	}
	public void setAccount_blocked_date(String account_blocked_date) {
		this.account_blocked_date = account_blocked_date;
	}
	//
	public String getAccount_max_check_amount() {
		return (this.account_max_check_amount);
	}
	public void setAccount_max_check_amount(String account_max_check_amount) {
		moduleName = "setAccount_max_check_amount: ";
		this.account_max_check_amount = account_max_check_amount;
		String temp = df.format(Double.parseDouble(account_max_check_amount));
		// PrintLog(temp);
		this.account_max_checkamt_disp = temp;
	}
	//
	public String getAccount_max_checkamt_disp() {
		return (this.account_max_checkamt_disp);
	}
	//
	public String getAccount_posi_pay_flag() {
		moduleName = "getAccount_posi_pay_flag: ";
		// PrintLog(this.account_posi_pay_flag);
		return (this.account_posi_pay_flag);
	}
	public void setAccount_posi_pay_flag(String account_posi_pay_flag) {
		this.account_posi_pay_flag = account_posi_pay_flag;
	}
	//
	public String getAccount_posi_pay_amt_min() {
		return (this.account_posi_pay_amt_min);
	}
	public void setAccount_posi_pay_amt_min(String account_posi_pay_amt_min) {
		this.account_posi_pay_amt_min = account_posi_pay_amt_min;
		String temp = df.format(Double.parseDouble(account_posi_pay_amt_min));
		this.account_posi_payamtmin_disp = temp;
	}
	public String getAccount_posi_payamtmin_disp() {
		return (this.account_posi_payamtmin_disp);
	}
	//
	public String getAccount_int_ext() {
		return (this.account_int_ext);
	}
	public void setAccount_int_ext(String account_int_ext) {
		this.account_int_ext = account_int_ext;
	}
	//
	public String getAccount_gen_suspense() {
		return (this.account_gen_suspense);
	}
	public void setAccount_gen_suspense(String account_gen_suspense) {
		this.account_gen_suspense = account_gen_suspense;
	}
	//
	public String getAccount_stop_suspense() {
		return (this.account_stop_suspense);
	}
	public void setAccount_stop_suspense(String account_stop_suspense) {
		this.account_stop_suspense = account_stop_suspense;
	}
	//
	public String getAccount_exce_suspense() {
		return (this.account_exce_suspense);
	}
	public void setAccount_exce_suspense(String account_exce_suspense) {
		this.account_exce_suspense = account_exce_suspense;
	}
	//
	public String getAccount_stmt_email() {
		return (this.account_stmt_email);
	}
	public void setAccount_stmt_email(String account_stmt_email) {
		this.account_stmt_email = account_stmt_email;
	}
	//
	public String getAccount_stmt_fax() {
		return (this.account_stmt_fax);
	}
	public void setAccount_stmt_fax(String account_stmt_fax) {
		this.account_stmt_fax = account_stmt_fax;
	}
	//
	public String getAccount_stmt_mail1() {
		return (this.account_stmt_mail1);
	}
	public void setAccount_stmt_mail1(String account_stmt_mail1) {
		this.account_stmt_mail1 = account_stmt_mail1;
	}
	//
	public String getAccount_stmt_mail2() {
		return (this.account_stmt_mail2);
	}
	public void setAccount_stmt_mail2(String account_stmt_mail2) {
		this.account_stmt_mail2 = account_stmt_mail2;
	}
	//
	public String getAccount_stmt_mail3() {
		return (this.account_stmt_mail3);
	}
	public void setAccount_stmt_mail3(String account_stmt_mail3) {
		this.account_stmt_mail3 = account_stmt_mail3;
	}
	//
	public String getAccount_stmt_mail4() {
		return (this.account_stmt_mail4);
	}
	public void setAccount_stmt_mail4(String account_stmt_mail4) {
		this.account_stmt_mail4 = account_stmt_mail4;
	}
	//
	public String getAccount_stmt_emailfreqD() {
		return (this.account_stmt_emailfreqD);
	}
	public void setAccount_stmt_emailfreqD(String account_stmt_emailfreqD) {
		this.account_stmt_emailfreqD = account_stmt_emailfreqD;
	}
	//
	public String getAccount_stmt_emailfreqW() {
		return (this.account_stmt_emailfreqW);
	}
	public void setAccount_stmt_emailfreqW(String account_stmt_emailfreqW) {
		this.account_stmt_emailfreqW = account_stmt_emailfreqW;
	}
	//
	public String getAccount_stmt_emailfreqM() {
		return (this.account_stmt_emailfreqM);
	}
	public void setAccount_stmt_emailfreqM(String account_stmt_emailfreqM) {
		this.account_stmt_emailfreqM = account_stmt_emailfreqM;
	}
	//
	public String getAccount_stmt_faxfreqD() {
		return (this.account_stmt_faxfreqD);
	}
	public void setAccount_stmt_faxfreqD(String account_stmt_faxfreqD) {
		this.account_stmt_faxfreqD = account_stmt_faxfreqD;
	}
	//
	public String getAccount_stmt_faxfreqW() {
		return (this.account_stmt_faxfreqW);
	}
	public void setAccount_stmt_faxfreqW(String account_stmt_faxfreqW) {
		this.account_stmt_faxfreqW = account_stmt_faxfreqW;
	}
	//
	public String getAccount_stmt_faxfreqM() {
		return (this.account_stmt_faxfreqM);
	}
	public void setAccount_stmt_faxfreqM(String account_stmt_faxfreqM) {
		this.account_stmt_faxfreqM = account_stmt_faxfreqM;
	}
	//
	public String getAccount_stmt_mailfreqD() {
		return (this.account_stmt_mailfreqD);
	}
	public void setAccount_stmt_mailfreqD(String account_stmt_mailfreqD) {
		this.account_stmt_mailfreqD = account_stmt_mailfreqD;
	}
	//
	public String getAccount_stmt_mailfreqW() {
		return (this.account_stmt_mailfreqW);
	}
	public void setAccount_stmt_mailfreqW(String account_stmt_mailfreqW) {
		this.account_stmt_mailfreqW = account_stmt_mailfreqW;
	}
	//
	public String getAccount_stmt_mailfreqM() {
		return (this.account_stmt_mailfreqM);
	}
	public void setAccount_stmt_mailfreqM(String account_stmt_mailfreqM) {
		this.account_stmt_mailfreqM = account_stmt_mailfreqM;
	}
	//
	public String getAccount_stmt_extra() {
		return (this.account_stmt_extra);
	}
	public void setAccount_stmt_extra(String account_stmt_extra) {
		this.account_stmt_extra = account_stmt_extra;
	}
	//
	public String getAccount_user_comments() {
		return (this.account_user_comments);
	}
	//
	public void setAccount_user_comments(String account_user_comments) {
		// if (account_user_comments.length() > 80) {
		// account_user_comments = account_user_comments.substring(0,80);
		// }
		this.account_user_comments = account_user_comments;
	}
	//
	public String getAccount_dayscheckvalid() {
		return (this.account_dayscheckvalid);
	}
	public void setAccount_dayscheckvalid(String account_dayscheckvalid) {
		this.account_dayscheckvalid = account_dayscheckvalid;
	}
	//
	public String getAccount_extra1() {
		return (this.account_extra1);
	}
	public void setAccount_extra1(String account_extra1) {
		this.account_extra1 = account_extra1;
	}
	//
	public String getAccount_extra2() {
		return (this.account_extra2);
	}
	public void setAccount_extra2(String account_extra2) {
		this.account_extra2 = account_extra2;
	}
	//
	public String getAccount_extra3() {
		return (this.account_extra3);
	}
	public void setAccount_extra3(String account_extra3) {
		this.account_extra3 = account_extra3;
	}
	//
	public String getAccount_extra4() {
		return (this.account_extra4);
	}
	public void setAccount_extra4(String account_extra4) {
		this.account_extra4 = account_extra4;
	}
	//
	public String getAccount_extra5() {
		return (this.account_extra5);
	}
	public void setAccount_extra5(String account_extra5) {
		this.account_extra5 = account_extra5;
	}
	//
	public String getAccount_extra6() {
		return (this.account_extra6);
	}
	public void setAccount_extra6(String account_extra6) {
		this.account_extra6 = account_extra6;
	}
	//
	public String getAccount_availday1() {
		return account_availday1;
	}
	public void setAccount_availday1(String account_availday1) {
		if (account_availday1.equals(" ")) {
			this.account_availday1	= "0";
		} else {
			this.account_availday1 = account_availday1;
		}
	}
	//
	public String getAccount_availlow1() {
		return account_availlow1;
	}
	public void setAccount_availlow1(String account_availlow1) {
		//if (account_availlow1.equals("0.00")) {
		//	this.account_availlow1	= "0.01";
		//} else {
			this.account_availlow1 = account_availlow1;
		//}
	}
	//
	public String getAccount_availhigh1() {
		return account_availhigh1;
	}
	public void setAccount_availhigh1(String account_availhigh1) {
		this.account_availhigh1 = account_availhigh1;
	}
	//
	public String getAccount_availday2() {
		return account_availday2;
	}
	public void setAccount_availday2(String account_availday2) {
		if (account_availday2.equals(" ")) {
			this.account_availday2	= "0";
		} else {
			this.account_availday2 = account_availday2;
		}
	}
	//
	public String getAccount_availlow2() {
		return account_availlow2;
	}
	public void setAccount_availlow2(String account_availlow2) {
		this.account_availlow2 = account_availlow2;
	}
	//
	public String getAccount_availhigh2() {
		return account_availhigh2;
	}
	public void setAccount_availhigh2(String account_availhigh2) {
		this.account_availhigh2 = account_availhigh2;
	}
	//
	public String getAccount_availday3() {
		return account_availday3;
	}
	public void setAccount_availday3(String account_availday3) {
		if (account_availday3.equals(" ")) {
			this.account_availday3	= "0";
		} else {
			this.account_availday3 = account_availday3;
		}
	}
	//
	public String getAccount_availlow3() {
		return account_availlow3;
	}
	public void setAccount_availlow3(String account_availlow3) {
		this.account_availlow3 = account_availlow3;
	}
	//
	public String getAccount_availhigh3() {
		return account_availhigh3;
	}
	public void setAccount_availhigh3(String account_availhigh3) {
		this.account_availhigh3 = account_availhigh3;
	}
	//
	public String getAccount_availday4() {
		return account_availday4;
	}
	public void setAccount_availday4(String account_availday4) {
		if (account_availday4.equals(" ")) {
			this.account_availday4	= "0";
		} else {
			this.account_availday4 = account_availday4;
		}
	}
	//
	public String getAccount_availlow4() {
		return account_availlow4;
	}
	public void setAccount_availlow4(String account_availlow4) {
		this.account_availlow4 = account_availlow4;
	}
	//
	public String getAccount_availhigh4() {
		return account_availhigh4;
	}
	public void setAccount_availhigh4(String account_availhigh4) {
		this.account_availhigh4 = account_availhigh4;
	}
	//
	public String getAccount_availday5() {
		return account_availday5;
	}
	public void setAccount_availday5(String account_availday5) {
		if (account_availday5.equals(" ")) {
			this.account_availday5	= "0";
		} else {
			this.account_availday5 = account_availday5;
		}
	}
	//
	public String getAccount_availlow5() {
		return account_availlow5;
	}
	public void setAccount_availlow5(String account_availlow5) {
		this.account_availlow5 = account_availlow5;
	}
	//
	public String getAccount_availhigh5() {
		return account_availhigh5;
	}
	public void setAccount_availhigh5(String account_availhigh5) {
		this.account_availhigh5 = account_availhigh5;
	}
	//
	public String getAccount_last_modified() {
		return (this.account_last_modified);
	}
	public void setAccount_last_modified(String account_last_modified) {
		moduleName = "setAccount_last_modified: ";
		// PrintLog(account_last_modified);
		int space_at = account_last_modified.indexOf(" ");
		if (!account_last_modified.equals("") && account_last_modified.length() > 15) {
			this.account_last_modified = (account_last_modified.substring(0,
					space_at) + " @ " + account_last_modified.substring(
					space_at + 1, account_last_modified.length()));
		} else if (!account_last_modified.equals("") && account_last_modified.length() > 13) {
			this.account_last_modified = account_last_modified.substring(0, 4) + "/";
			this.account_last_modified += account_last_modified.substring(4, 6) + "/";
			this.account_last_modified += account_last_modified.substring(6, 8) + " @ ";
			this.account_last_modified += account_last_modified.substring(8, 10) + ":";
			this.account_last_modified += account_last_modified.substring(10,12) + ":";
			this.account_last_modified += account_last_modified.substring(12,14);
		} else {
			this.account_last_modified = account_last_modified;
		}
	}
	//
	//
	public String getAccount_mod_user() {
		return (this.account_mod_user);
	}
	public void setAccount_mod_user(String account_mod_user) {
		// if (account_mod_user.length() > 15) {
		// account_mod_user = account_mod_user.substring(0,15);
		// }
		this.account_mod_user = account_mod_user;
	}
	//
	public String getAccount_mod_func() {
		return (this.account_mod_func);
	}
	public void setAccount_mod_func(String account_mod_func) {
		// if (account_mod_func.length() > 15) {
		// account_mod_func = account_mod_func.substring(0,15);
		// }
		this.account_mod_func = account_mod_func;
	}
	//
	// Saved fields
	//
	public String getAccount_currency_o() {
		return (this.account_currency_o);
	}
	public void setAccount_currency_o(String account_currency_o) {
		this.account_currency_o = account_currency_o;
	}
	//
	public String getAccount_acc_rep_o() {
		return (this.account_acc_rep_o);
	}
	public void setAccount_acc_rep_o(String account_acc_rep_o) {
		this.account_acc_rep_o = account_acc_rep_o;
	}
	//
	public String getAccount_alt_acc_rep_o() {
		return (this.account_alt_acc_rep_o);
	}
	public void setAccount_alt_acc_rep_o(String account_alt_acc_rep_o) {
		this.account_alt_acc_rep_o = account_alt_acc_rep_o;
	}
	//
	public String getAccount_sw_addr_o() {
		return (this.account_sw_addr_o);
	}
	public void setAccount_sw_addr_o(String account_sw_addr_o) {
		this.account_sw_addr_o = account_sw_addr_o;
	}
	//
	public String getAccount_client_name_o() {
		return (this.account_client_name_o);
	}
	public void setAccount_client_name_o(String account_client_name_o) {
		this.account_client_name_o = account_client_name_o;
	}
	//
	public String getAccount_client_addr1_o() {
		return (this.account_client_addr1_o);
	}
	public void setAccount_client_addr1_o(String account_client_addr1_o) {
		this.account_client_addr1_o = account_client_addr1_o;
	}
	//
	public String getAccount_client_addr2_o() {
		return (this.account_client_addr2_o);
	}
	public void setAccount_client_addr2_o(String account_client_addr2_o) {
		this.account_client_addr2_o = account_client_addr2_o;
	}
	//
	public String getAccount_client_addr3_o() {
		return (this.account_client_addr3_o);
	}
	public void setAccount_client_addr3_o(String account_client_addr3_o) {
		this.account_client_addr3_o = account_client_addr3_o;
	}
	//
	public String getAccount_client_addr4_o() {
		return (this.account_client_addr4_o);
	}
	public void setAccount_client_addr4_o(String account_client_addr4_o) {
		this.account_client_addr4_o = account_client_addr4_o;
	}
	//
	public String getAccount_effective_date_o() {
		return (this.account_effective_date_o);
	}
	public void setAccount_effective_date_o(String account_effective_date_o) {
		this.account_effective_date_o = account_effective_date_o;
	}
	//
	public String getAccount_closed_date_o() {
		return (this.account_closed_date_o);
	}
	public void setAccount_closed_date_o(String account_closed_date_o) {
		this.account_closed_date_o = account_closed_date_o;
	}
	//
	public String getAccount_blocked_date_o() {
		return (this.account_blocked_date_o);
	}
	public void setAccount_blocked_date_o(String account_blocked_date_o) {
		this.account_blocked_date_o = account_blocked_date_o;
	}
	//
	public String getAccount_max_check_amount_o() {
		return (this.account_max_check_amount_o);
	}
	public void setAccount_max_check_amount_o(String account_max_check_amount_o) {
		this.account_max_check_amount_o = account_max_check_amount_o;
	}
	//
	public String getAccount_posi_pay_flag_o() {
		return (this.account_posi_pay_flag_o);
	}
	public void setAccount_posi_pay_flag_o(String account_posi_pay_flag_o) {
		this.account_posi_pay_flag_o = account_posi_pay_flag_o;
	}
	//
	public String getAccount_posi_pay_amt_min_o() {
		return (this.account_posi_pay_amt_min_o);
	}
	public void setAccount_posi_pay_amt_min_o(String account_posi_pay_amt_min_o) {
		this.account_posi_pay_amt_min_o = account_posi_pay_amt_min_o;
	}
	//
	public String getAccount_int_ext_o() {
		return (this.account_int_ext_o);
	}
	public void setAccount_int_ext_o(String account_int_ext_o) {
		this.account_int_ext_o = account_int_ext_o;
	}
	//
	public String getAccount_stmt_email_o() {
		return (this.account_stmt_email_o);
	}
	public void setAccount_stmt_email_o(String account_stmt_email_o) {
		this.account_stmt_email_o = account_stmt_email_o;
	}
	//
	public String getAccount_stmt_fax_o() {
		return (this.account_stmt_fax_o);
	}
	public void setAccount_stmt_fax_o(String account_stmt_fax_o) {
		this.account_stmt_fax_o = account_stmt_fax_o;
	}
	//
	public String getAccount_stmt_mail1_o() {
		return (this.account_stmt_mail1_o);
	}
	public void setAccount_stmt_mail1_o(String account_stmt_mail1_o) {
		this.account_stmt_mail1_o = account_stmt_mail1_o;
	}
	//
	public String getAccount_stmt_mail2_o() {
		return (this.account_stmt_mail2_o);
	}
	public void setAccount_stmt_mail2_o(String account_stmt_mail2_o) {
		this.account_stmt_mail2_o = account_stmt_mail2_o;
	}
	//
	public String getAccount_stmt_mail3_o() {
		return (this.account_stmt_mail3_o);
	}
	public void setAccount_stmt_mail3_o(String account_stmt_mail3_o) {
		this.account_stmt_mail3_o = account_stmt_mail3_o;
	}
	//
	public String getAccount_stmt_mail4_o() {
		return (this.account_stmt_mail4_o);
	}
	public void setAccount_stmt_mail4_o(String account_stmt_mail4_o) {
		this.account_stmt_mail4_o = account_stmt_mail4_o;
	}
	//
	public String getAccount_stmt_emailfreqD_o() {
		return (this.account_stmt_emailfreqD_o);
	}
	public void setAccount_stmt_emailfreqD_o(String account_stmt_emailfreqD_o) {
		this.account_stmt_emailfreqD_o = account_stmt_emailfreqD_o;
	}
	//
	public String getAccount_stmt_emailfreqW_o() {
		return (this.account_stmt_emailfreqW_o);
	}
	public void setAccount_stmt_emailfreqW_o(String account_stmt_emailfreqW_o) {
		this.account_stmt_emailfreqW_o = account_stmt_emailfreqW_o;
	}
	//
	public String getAccount_stmt_emailfreqM_o() {
		return (this.account_stmt_emailfreqM_o);
	}
	public void setAccount_stmt_emailfreqM_o(String account_stmt_emailfreqM_o) {
		this.account_stmt_emailfreqM_o = account_stmt_emailfreqM_o;
	}
	//
	public String getAccount_stmt_faxfreqD_o() {
		return (this.account_stmt_faxfreqD_o);
	}
	public void setAccount_stmt_faxfreqD_o(String account_stmt_faxfreqD_o) {
		this.account_stmt_faxfreqD_o = account_stmt_faxfreqD_o;
	}
	//
	public String getAccount_stmt_faxfreqW_o() {
		return (this.account_stmt_faxfreqW_o);
	}
	public void setAccount_stmt_faxfreqW_o(String account_stmt_faxfreqW_o) {
		this.account_stmt_faxfreqW_o = account_stmt_faxfreqW_o;
	}
	//
	public String getAccount_stmt_faxfreqM_o() {
		return (this.account_stmt_faxfreqM_o);
	}
	public void setAccount_stmt_faxfreqM_o(String account_stmt_faxfreqM_o) {
		this.account_stmt_faxfreqM_o = account_stmt_faxfreqM_o;
	}
	//
	public String getAccount_stmt_mailfreqD_o() {
		return (this.account_stmt_mailfreqD_o);
	}
	public void setAccount_stmt_mailfreqD_o(String account_stmt_mailfreqD_o) {
		this.account_stmt_mailfreqD_o = account_stmt_mailfreqD_o;
	}
	//
	public String getAccount_stmt_mailfreqW_o() {
		return (this.account_stmt_mailfreqW_o);
	}
	public void setAccount_stmt_mailfreqW_o(String account_stmt_mailfreqW_o) {
		this.account_stmt_mailfreqW_o = account_stmt_mailfreqW_o;
	}
	//
	public String getAccount_stmt_mailfreqM_o() {
		return (this.account_stmt_mailfreqM_o);
	}
	public void setAccount_stmt_mailfreqM_o(String account_stmt_mailfreqM_o) {
		this.account_stmt_mailfreqM_o = account_stmt_mailfreqM_o;
	}
	//
	public String getAccount_stmt_extra_o() {
		return (this.account_stmt_extra_o);
	}
	public void setAccount_stmt_extra_o(String account_stmt_extra_o) {
		this.account_stmt_extra_o = account_stmt_extra_o;
	}
	//
	public String getAccount_gen_suspense_o() {
		return (this.account_gen_suspense_o);
	}
	public void setAccount_gen_suspense_o(String account_gen_suspense_o) {
		this.account_gen_suspense_o = account_gen_suspense_o;
	}
	//
	public String getAccount_stop_suspense_o() {
		return (this.account_stop_suspense_o);
	}
	public void setAccount_stop_suspense_o(String account_stop_suspense_o) {
		this.account_stop_suspense_o = account_stop_suspense_o;
	}
	//
	public String getAccount_exce_suspense_o() {
		return (this.account_exce_suspense_o);
	}
	public void setAccount_exce_suspense_o(String account_exce_suspense_o) {
		this.account_exce_suspense_o = account_exce_suspense_o;
	}
	//
	public String getAccount_user_comments_o() {
		return (this.account_user_comments_o);
	}
	public void setAccount_user_comments_o(String account_user_comments_o) {
		this.account_user_comments_o = account_user_comments_o;
	}
	//
	public String getAccount_dayscheckvalid_o() {
		return (this.account_dayscheckvalid_o);
	}
	public void setAccount_dayscheckvalid_o(String account_dayscheckvalid_o) {
		this.account_dayscheckvalid_o = account_dayscheckvalid_o;
	}
	//
	public String getAccount_extra1_o() {
		return (this.account_extra1_o);
	}
	public void setAccount_extra1_o(String account_extra1_o) {
		this.account_extra1_o = account_extra1_o;
	}
	//
	public String getAccount_extra2_o() {
		return (this.account_extra2_o);
	}
	public void setAccount_extra2_o(String account_extra2_o) {
		this.account_extra2_o = account_extra2_o;
	}
	//
	public String getAccount_extra3_o() {
		return (this.account_extra3_o);
	}
	public void setAccount_extra3_o(String account_extra3_o) {
		this.account_extra3_o = account_extra3_o;
	}
	//
	public String getAccount_extra4_o() {
		return (this.account_extra4_o);
	}
	public void setAccount_extra4_o(String account_extra4_o) {
		this.account_extra4_o = account_extra4_o;
	}
	//
	public String getAccount_extra5_o() {
		return (this.account_extra5_o);
	}
	public void setAccount_extra5_o(String account_extra5_o) {
		this.account_extra5_o = account_extra5_o;
	}
	//
	public String getAccount_extra6_o() {
		return (this.account_extra6_o);
	}
	public void setAccount_extra6_o(String account_extra6_o) {
		this.account_extra6_o = account_extra6_o;
	}
	//
	public String getAccount_availday1_o() {
		return account_availday1_o;
	}
	public void setAccount_availday1_o(String account_availday1_o) {
		this.account_availday1_o = account_availday1_o;
	}
	//
	public String getAccount_availlow1_o() {
		return account_availlow1_o;
	}
	public void setAccount_availlow1_o(String account_availlow1_o) {
		this.account_availlow1_o = account_availlow1_o;
	}
	//
	public String getAccount_availhigh1_o() {
		return account_availhigh1_o;
	}
	public void setAccount_availhigh1_o(String account_availhigh1_o) {
		this.account_availhigh1_o = account_availhigh1_o;
	}
	//
	public String getAccount_availday2_o() {
		return account_availday2_o;
	}
	public void setAccount_availday2_o(String account_availday2_o) {
		this.account_availday2_o = account_availday2_o;
	}
	//
	public String getAccount_availlow2_o() {
		return account_availlow2_o;
	}
	public void setAccount_availlow2_o(String account_availlow2_o) {
		this.account_availlow2_o = account_availlow2_o;
	}
	//
	public String getAccount_availhigh2_o() {
		return account_availhigh2_o;
	}
	public void setAccount_availhigh2_o(String account_availhigh2_o) {
		this.account_availhigh2_o = account_availhigh2_o;
	}
	//
	public String getAccount_availday3_o() {
		return account_availday3_o;
	}
	public void setAccount_availday3_o(String account_availday3_o) {
		this.account_availday3_o = account_availday3_o;
	}
	//
	public String getAccount_availlow3_o() {
		return account_availlow3_o;
	}
	public void setAccount_availlow3_o(String account_availlow3_o) {
		this.account_availlow3_o = account_availlow3_o;
	}
	//
	public String getAccount_availhigh3_o() {
		return account_availhigh3_o;
	}
	public void setAccount_availhigh3_o(String account_availhigh3_o) {
		this.account_availhigh3_o = account_availhigh3_o;
	}
	//
	public String getAccount_availday4_o() {
		return account_availday4_o;
	}
	public void setAccount_availday4_o(String account_availday4_o) {
		this.account_availday4_o = account_availday4_o;
	}
	//
	public String getAccount_availlow4_o() {
		return account_availlow4_o;
	}
	public void setAccount_availlow4_o(String account_availlow4_o) {
		this.account_availlow4_o = account_availlow4_o;
	}
	//
	public String getAccount_availhigh4_o() {
		return account_availhigh4_o;
	}
	public void setAccount_availhigh4_o(String account_availhigh4_o) {
		this.account_availhigh4_o = account_availhigh4_o;
	}
	//
	public String getAccount_availday5_o() {
		return account_availday5_o;
	}
	public void setAccount_availday5_o(String account_availday5_o) {
		this.account_availday5_o = account_availday5_o;
	}
	//
	public String getAccount_availlow5_o() {
		return account_availlow5_o;
	}
	public void setAccount_availlow5_o(String account_availlow5_o) {
		this.account_availlow5_o = account_availlow5_o;
	}
	//
	public String getAccount_availhigh5_o() {
		return account_availhigh5_o;
	}
	public void setAccount_availhigh5_o(String account_availhigh5_o) {
		this.account_availhigh5_o = account_availhigh5_o;
	}
	//
	public String getAccount_last_modified_o() {
		return (this.account_last_modified_o);
	}
	public void setAccount_last_modified_o(String account_last_modified_o) {
		this.account_last_modified_o = account_last_modified_o;
	}
	//
	public String getAccount_mod_user_o() {
		return (this.account_mod_user_o);
	}
	public void setAccount_mod_user_o(String account_mod_user_o) {
		this.account_mod_user_o = account_mod_user_o;
	}
	//
	public String getAccount_mod_func_o() {
		return (this.account_mod_func_o);
	}
	public void setAccount_mod_func_o(String account_mod_func_o) {
		this.account_mod_func_o = account_mod_func_o;
	}
	//
	public void ShowDetails() {
		moduleName = "ShowDetails: ";
		PrintLog("account_num\t\t'" + account_num + "'");
		PrintLog("account_currency\t\t'" + account_currency + "'");
		PrintLog("account_acc_rep\t\t'" + account_acc_rep + "'");
		PrintLog("account_alt_acc_rep\t\t'" + account_alt_acc_rep + "'");
		PrintLog("account_sw_addr\t\t'" + account_sw_addr + "'");
		PrintLog("account_client_name\t\t'" + account_client_name + "'");
		PrintLog("account_client_addr1\t\t'" + account_client_addr1 + "'");
		PrintLog("account_client_addr2\t\t'" + account_client_addr2 + "'");
		PrintLog("account_client_addr3\t\t'" + account_client_addr3 + "'");
		PrintLog("account_client_addr4\t\t'" + account_client_addr4 + "'");
		PrintLog("account_effective_date\t\t'" + account_effective_date + "'");
		PrintLog("account_closed_date\t\t'" + account_closed_date + "'");
		PrintLog("account_blocked_date\t\t'" + account_blocked_date + "'");
		PrintLog("account_max_checkamt_disp\t\t'" + account_max_checkamt_disp + "'");
		PrintLog("account_max_check_amount\t\t'" + account_max_check_amount + "'");
		PrintLog("account_posi_pay_flag\t\t'" + account_posi_pay_flag + "'");
		PrintLog("account_posi_payamtmin_disp\t\t'" + account_posi_payamtmin_disp + "'");
		PrintLog("account_posi_pay_amt_min\t\t'" + account_posi_pay_amt_min + "'");
		PrintLog("account_int_ext\t\t'" + account_int_ext + "'");
		PrintLog("account_gen_suspense\t\t'" + account_gen_suspense + "'");
		PrintLog("account_stop_suspense\t\t'" + account_stop_suspense + "'");
		PrintLog("account_exce_suspense\t\t'" + account_exce_suspense + "'");
		PrintLog("account_stmt_email\t\t'" + account_stmt_email + "'");
		PrintLog("account_stmt_fax\t\t'" + account_stmt_fax + "'");
		PrintLog("account_stmt_mail1\t\t'" + account_stmt_mail1 + "'");
		PrintLog("account_stmt_mail2\t\t'" + account_stmt_mail2 + "'");
		PrintLog("account_stmt_mail3\t\t'" + account_stmt_mail3 + "'");
		PrintLog("account_stmt_mail4\t\t'" + account_stmt_mail4 + "'");
		PrintLog("account_stmt_emailfreqD\t\t'" + account_stmt_emailfreqD + "'");
		PrintLog("account_stmt_emailfreqW\t\t'" + account_stmt_emailfreqW + "'");
		PrintLog("account_stmt_emailfreqM\t\t'" + account_stmt_emailfreqM + "'");
		PrintLog("account_stmt_faxfreqD\t\t'" + account_stmt_faxfreqD + "'");
		PrintLog("account_stmt_faxfreqW\t\t'" + account_stmt_faxfreqW + "'");
		PrintLog("account_stmt_faxfreqM\t\t'" + account_stmt_faxfreqM + "'");
		PrintLog("account_stmt_mailfreqD\t\t'" + account_stmt_mailfreqD + "'");
		PrintLog("account_stmt_mailfreqW\t\t'" + account_stmt_mailfreqW + "'");
		PrintLog("account_stmt_mailfreqM\t\t'" + account_stmt_mailfreqM + "'");
		PrintLog("account_stmt_extra\t\t'" + account_stmt_extra + "'");
		PrintLog("account_user_comments\t\t'" + account_user_comments + "'");
		PrintLog("account_dayscheckvalid\t\t'" + account_dayscheckvalid + "'");
		PrintLog("account_extra1\t\t'" + account_extra1 + "'");
		PrintLog("account_extra2\t\t'" + account_extra2 + "'");
		PrintLog("account_extra3\t\t'" + account_extra3 + "'");
		PrintLog("account_extra4\t\t'" + account_extra4 + "'");
		PrintLog("account_extra5\t\t'" + account_extra5 + "'");
		PrintLog("account_extra6\t\t'" + account_extra6 + "'");
		PrintLog("account_availday1\t\t'" + account_availday1 + "'");
		PrintLog("account_availlow1\t\t'" + account_availlow1 + "'");
		PrintLog("account_availhigh1\t\t'" + account_availhigh1 + "'");
		PrintLog("account_availday2\t\t'" + account_availday2 + "'");
		PrintLog("account_availlow2\t\t'" + account_availlow2 + "'");
		PrintLog("account_availhigh2\t\t'" + account_availhigh2 + "'");
		PrintLog("account_availday3\t\t'" + account_availday3 + "'");
		PrintLog("account_availlow3\t\t'" + account_availlow3 + "'");
		PrintLog("account_availhigh3\t\t'" + account_availhigh3 + "'");
		PrintLog("account_availday4\t\t'" + account_availday4 + "'");
		PrintLog("account_availlow4\t\t'" + account_availlow4 + "'");
		PrintLog("account_availhigh4\t\t'" + account_availhigh4 + "'");
		PrintLog("account_availday5\t\t'" + account_availday5 + "'");
		PrintLog("account_availlow5\t\t'" + account_availlow5 + "'");
		PrintLog("account_availhigh5\t\t'" + account_availhigh5 + "'");
	}
	//
	public void clearNulls() {
		this.account_currency			= (account_currency == null) ? " " : account_currency;
		this.account_acc_rep			= (account_acc_rep == null) ? " " : account_acc_rep;
		this.account_alt_acc_rep		= (account_alt_acc_rep == null) ? " " : account_alt_acc_rep;
		this.account_sw_addr			= (account_sw_addr == null) ? " " : account_sw_addr;
		this.account_client_name		= (account_client_name == null) ? " " : account_client_name;
		this.account_client_addr1		= (account_client_addr1 == null) ? " " : account_client_addr1;
		this.account_client_addr2		= (account_client_addr2 == null) ? " " : account_client_addr2;
		this.account_client_addr3		= (account_client_addr3 == null) ? " " : account_client_addr3;
		this.account_client_addr4		= (account_client_addr4 == null) ? " " : account_client_addr4;
		this.account_effective_date		= (account_effective_date == null) ? " " : account_effective_date;
		this.account_closed_date		= (account_closed_date == null) ? " " : account_closed_date;
		this.account_blocked_date		= (account_blocked_date == null) ? " " : account_blocked_date;
		this.account_max_check_amount	= (account_max_check_amount == null) ? " " : account_max_check_amount;
		this.account_posi_pay_flag		= (account_posi_pay_flag == null) ? " " : account_posi_pay_flag;
		this.account_posi_pay_amt_min	= (account_posi_pay_amt_min == null) ? " " : account_posi_pay_amt_min;
		this.account_int_ext			= (account_int_ext == null) ? " " : account_int_ext;
		this.account_gen_suspense		= (account_gen_suspense == null) ? " " : account_gen_suspense;
		this.account_stop_suspense		= (account_stop_suspense == null) ? " " : account_stop_suspense;
		this.account_exce_suspense		= (account_exce_suspense == null) ? " " : account_exce_suspense;
		this.account_stmt_email			= (account_stmt_email == null) ? " " : account_stmt_email;
		this.account_stmt_fax			= (account_stmt_fax == null) ? " " : account_stmt_fax;
		this.account_stmt_mail1			= (account_stmt_mail1 == null) ? " " : account_stmt_mail1;
		this.account_stmt_mail2			= (account_stmt_mail2 == null) ? " " : account_stmt_mail2;
		this.account_stmt_mail3			= (account_stmt_mail3 == null) ? " " : account_stmt_mail3;
		this.account_stmt_mail4			= (account_stmt_mail4 == null) ? " " : account_stmt_mail4;
		this.account_stmt_emailfreqD	= (account_stmt_emailfreqD == null) ? " " : account_stmt_emailfreqD;
		this.account_stmt_emailfreqW	= (account_stmt_emailfreqW == null) ? " " : account_stmt_emailfreqW;
		this.account_stmt_emailfreqM	= (account_stmt_emailfreqM == null) ? " " : account_stmt_emailfreqM;
		this.account_stmt_faxfreqD		= (account_stmt_faxfreqD == null) ? " " : account_stmt_faxfreqD;
		this.account_stmt_faxfreqW		= (account_stmt_faxfreqW == null) ? " " : account_stmt_faxfreqW;
		this.account_stmt_faxfreqM		= (account_stmt_faxfreqM == null) ? " " : account_stmt_faxfreqM;
		this.account_stmt_mailfreqD		= (account_stmt_mailfreqD == null) ? " " : account_stmt_mailfreqD;
		this.account_stmt_mailfreqW		= (account_stmt_mailfreqW == null) ? " " : account_stmt_mailfreqW;
		this.account_stmt_mailfreqM		= (account_stmt_mailfreqM == null) ? " " : account_stmt_mailfreqM;
		this.account_stmt_extra			= (account_stmt_extra == null) ? " " : account_stmt_extra;
		this.account_user_comments		= (account_user_comments == null) ? " " : account_user_comments;
		this.account_dayscheckvalid		= (account_dayscheckvalid == null) ? "0" : account_dayscheckvalid;
		this.account_extra1				= (account_extra1 == null) ? " " : account_extra1; 
		this.account_extra2				= (account_extra2 == null) ? " " : account_extra2;
		this.account_extra3				= (account_extra3 == null) ? " " : account_extra3;
		this.account_extra4				= (account_extra4 == null) ? " " : account_extra4;
		this.account_extra5				= (account_extra5 == null) ? " " : account_extra5;
		this.account_extra6				= (account_extra6 == null) ? " " : account_extra6;
		this.account_availday1			= (account_availday1 == null) ? "0" : account_availday1;
		this.account_availlow1			= (account_availlow1 == null) ? "0.00" : account_availlow1;
		this.account_availhigh1			= (account_availhigh1 == null) ? "0.00" : account_availhigh1;
		this.account_availday2			= (account_availday2 == null) ? "0" : account_availday2;
		this.account_availlow2			= (account_availlow2 == null) ? "0.00" : account_availlow2;
		this.account_availhigh2			= (account_availhigh2 == null) ? "0.00" : account_availhigh2;
		this.account_availday3			= (account_availday3 == null) ? "0" : account_availday3;
		this.account_availlow3			= (account_availlow3 == null) ? "0.00" : account_availlow3;
		this.account_availhigh3			= (account_availhigh3 == null) ? "0.00" : account_availhigh3;
		this.account_availday4			= (account_availday4 == null) ? "0" : account_availday4;
		this.account_availlow4			= (account_availlow4 == null) ? "0.00" : account_availlow4;
		this.account_availhigh4			= (account_availhigh4 == null) ? "0.00" : account_availhigh4;
		this.account_availday5			= (account_availday5 == null) ? "0" : account_availday5;
		this.account_availlow5			= (account_availlow5 == null) ? "0.00" : account_availlow5;
		this.account_availhigh5			= (account_availhigh5 == null) ? "0.00" : account_availhigh5;
		this.account_last_modified		= (account_last_modified == null) ? " " : account_last_modified;
		this.account_mod_user			= (account_mod_user == null) ? " " : account_mod_user;
		this.account_mod_func			= (account_mod_func == null) ? " " : account_mod_func;
	}
	//
	public void clearDetails() {
		account_num					= "";
		account_currency			= "";
		account_acc_rep				= "";
		account_alt_acc_rep			= "";
		account_sw_addr				= "";
		account_client_name			= "";
		account_client_addr1		= "";
		account_client_addr2		= "";
		account_client_addr3		= "";
		account_client_addr4		= "";
		account_effective_date		= "";
		account_closed_date			= "";
		account_blocked_date		= "";
		account_max_check_amount	= "";
		account_posi_pay_flag		= "";
		account_posi_pay_amt_min	= "";
		account_int_ext				= "";
		account_gen_suspense		= "";
		account_stop_suspense		= "";
		account_exce_suspense		= "";
		account_stmt_email			= "";
		account_stmt_fax			= "";
		account_stmt_mail1			= "";
		account_stmt_mail2			= "";
		account_stmt_mail3			= "";
		account_stmt_mail4			= "";
		account_stmt_emailfreqD		= "";
		account_stmt_emailfreqW		= "";
		account_stmt_emailfreqM		= "";
		account_stmt_faxfreqD		= "";
		account_stmt_faxfreqW		= "";
		account_stmt_faxfreqM		= "";
		account_stmt_mailfreqD		= "";
		account_stmt_mailfreqW		= "";
		account_stmt_mailfreqM		= "";
		account_stmt_extra			= "";
		account_user_comments		= "";
		account_dayscheckvalid		= "";
		account_extra1				= "";
		account_extra2				= "";
		account_extra3				= "";
		account_extra4				= "";
		account_extra5				= "";
		account_extra6				= "";
		account_availday1			= "";
		account_availlow1			= "";
		account_availhigh1			= "";
		account_availday2			= "";
		account_availlow2			= "";
		account_availhigh2			= "";
		account_availday3			= "";
		account_availlow3			= "";
		account_availhigh3			= "";
		account_availday4			= "";
		account_availlow4			= "";
		account_availhigh4			= "";
		account_availday5			= "";
		account_availlow5			= "";
		account_availhigh5			= "";
		account_last_modified		= "";
		account_mod_user			= "";
		account_mod_func			= "";
		setAccount_make_copy();
	}
	//
	public void setAccount_make_copy() {
		clearNulls();
		account_currency_o			= account_currency;
		account_acc_rep_o			= account_acc_rep;
		account_alt_acc_rep_o		= account_alt_acc_rep;
		account_sw_addr_o			= account_sw_addr;
		account_client_name_o		= account_client_name;
		account_client_addr1_o		= account_client_addr1;
		account_client_addr2_o		= account_client_addr2;
		account_client_addr3_o		= account_client_addr3;
		account_client_addr4_o		= account_client_addr4;
		account_effective_date_o	= account_effective_date;
		account_closed_date_o		= account_closed_date;
		account_blocked_date_o		= account_blocked_date;
		account_max_check_amount_o	= account_max_check_amount;
		account_posi_pay_flag_o		= account_posi_pay_flag;
		account_posi_pay_amt_min_o	= account_posi_pay_amt_min;
		account_int_ext_o			= account_int_ext;
		account_gen_suspense_o		= account_gen_suspense;
		account_stop_suspense_o		= account_stop_suspense;
		account_exce_suspense_o		= account_exce_suspense;
		account_stmt_email_o		= account_stmt_email;
		account_stmt_fax_o			= account_stmt_fax;
		account_stmt_mail1_o		= account_stmt_mail1;
		account_stmt_mail2_o		= account_stmt_mail2;
		account_stmt_mail3_o		= account_stmt_mail3;
		account_stmt_mail4_o		= account_stmt_mail4;
		account_stmt_emailfreqD_o	= account_stmt_emailfreqD;
		account_stmt_emailfreqW_o	= account_stmt_emailfreqW;
		account_stmt_emailfreqM_o	= account_stmt_emailfreqM;
		account_stmt_faxfreqD_o		= account_stmt_faxfreqD;
		account_stmt_faxfreqW_o		= account_stmt_faxfreqW;
		account_stmt_faxfreqM_o		= account_stmt_faxfreqM;
		account_stmt_mailfreqD_o	= account_stmt_mailfreqD;
		account_stmt_mailfreqW_o	= account_stmt_mailfreqW;
		account_stmt_mailfreqM_o	= account_stmt_mailfreqM;
		account_stmt_extra_o		= account_stmt_extra;
		account_user_comments_o		= account_user_comments;
		account_dayscheckvalid_o	= account_dayscheckvalid;
		account_extra1_o			= account_extra1;
		account_extra2_o			= account_extra2;
		account_extra3_o			= account_extra3;
		account_extra4_o			= account_extra4;
		account_extra5_o			= account_extra5;
		account_extra6_o			= account_extra6;
		account_availday1_o			= account_availday1;
		account_availlow1_o			= account_availlow1;
		account_availhigh1_o		= account_availhigh1;
		account_availday2_o			= account_availday2;
		account_availlow2_o			= account_availlow2;
		account_availhigh2_o		= account_availhigh2;
		account_availday3_o			= account_availday3;
		account_availlow3_o			= account_availlow3;
		account_availhigh3_o		= account_availhigh3;
		account_availday4_o			= account_availday4;
		account_availlow4_o			= account_availlow4;
		account_availhigh4_o		= account_availhigh4;
		account_availday5_o			= account_availday5;
		account_availlow5_o			= account_availlow5;
		account_availhigh5_o		= account_availhigh5;
		account_last_modified_o		= account_last_modified;
		account_mod_user_o			= account_mod_user;
		account_mod_func_o			= account_mod_func;
	}
	//
	public int CheckForChanges() {
		moduleName = "CheckForChanges: ";
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		boolean	availChanged	= false;
		int data_changed	= 0;
		// int found_at		= 0;
		// int found_dot	= 0;
		int date_yyyy		= 0;
		int date_mm			= 0;
		int date_dd 		= 0;
		clearNulls();
		// PrintLog("IN CheckForChanges ");
		//
		// Just check if any of the fields was changed
		if (account_currency_o.compareTo(account_currency) != 0) {
			data_changed = 1;
			PrintLog("currency changed "+account_currency);
		}
		if (account_client_name_o.compareTo(account_client_name) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (account_sw_addr_o.compareTo(account_sw_addr) != 0) {
			account_sw_addr = account_sw_addr.toUpperCase();
			data_changed = 1;
			// PrintLog("sw addr changed >'"+account_sw_addr_o+"'<>'"+account_sw_addr+"'<");
		}
		if (account_max_check_amount_o.compareTo(account_max_check_amount) != 0) {
			data_changed = 1;
			// PrintLog("max amt changed ");
		}
		if (account_posi_pay_amt_min_o.compareTo(account_posi_pay_amt_min) != 0) {
			data_changed = 1;
			// PrintLog("min amt changed ");
		}
		if (account_user_comments_o.compareTo(account_user_comments) != 0) {
			data_changed = 1;
			// PrintLog("comments changed ");
		}
		if (account_stmt_email_o.compareTo(account_stmt_email) != 0) {
			data_changed = 1;
			// PrintLog("stmt email");
		}
		if (account_stmt_fax_o.compareTo(account_stmt_fax) != 0) {
			data_changed = 1;
			// PrintLog("stmt fax changed ");
		}
		if (account_stmt_mail1_o.compareTo(account_stmt_mail1) != 0) {
			data_changed = 1;
			// PrintLog("stmt mail 1 changed ");
		}
		if (account_stmt_mail2_o.compareTo(account_stmt_mail2) != 0) {
			data_changed = 1;
			// PrintLog("stmt mail 2 changed ");
		}
		if (account_stmt_mail3_o.compareTo(account_stmt_mail3) != 0) {
			data_changed = 1;
			// PrintLog("stmt mail 3 changed ");
		}
		if (account_stmt_mail4_o.compareTo(account_stmt_mail4) != 0) {
			data_changed = 1;
			// PrintLog("stmt mail 4 changed ");
		}
		if (account_stmt_emailfreqD_o.compareTo(account_stmt_emailfreqD) != 0) {
			data_changed = 1;
			// PrintLog("email freq d changed ");
		}
		if (account_stmt_emailfreqW_o.compareTo(account_stmt_emailfreqW) != 0) {
			data_changed = 1;
			// PrintLog("email freq w changed ");
		}
		if (account_stmt_emailfreqM_o.compareTo(account_stmt_emailfreqM) != 0) {
			data_changed = 1;
			// PrintLog("email freq m changed ");
		}
		if (account_stmt_faxfreqD_o.compareTo(account_stmt_faxfreqD) != 0) {
			data_changed = 1;
			// PrintLog("fax freq d changed ");
		}
		if (account_stmt_faxfreqW_o.compareTo(account_stmt_faxfreqW) != 0) {
			data_changed = 1;
			// PrintLog("fax freq w changed ");
		}
		if (account_stmt_faxfreqM_o.compareTo(account_stmt_faxfreqM) != 0) {
			data_changed = 1;
			// PrintLog("fax freq m changed ");
		}
		if (account_stmt_mailfreqD_o.compareTo(account_stmt_mailfreqD) != 0) {
			data_changed = 1;
			// PrintLog("mail freq d changed ");
		}
		if (account_stmt_mailfreqW_o.compareTo(account_stmt_mailfreqW) != 0) {
			data_changed = 1;
			// PrintLog("mail freq w changed ");
		}
		if (account_stmt_mailfreqM_o.compareTo(account_stmt_mailfreqM) != 0) {
			data_changed = 1;
			// PrintLog("mail freq m changed ");
		}
		PrintLog(account_stmt_extra_o+"< new >"+account_stmt_extra+"<");
		if (account_stmt_extra_o.compareTo(account_stmt_extra) != 0) {
			data_changed = 1;
			// PrintLog("stmt extra changed>");
			//PrintLog(account_stmt_extra_o+"< new >"+account_stmt_extra+"<");
		}
		if (account_dayscheckvalid_o.compareTo(account_dayscheckvalid) != 0) {
			data_changed = 1;
			// PrintLog("dayscheckvalid changed ");
		}
		if (account_extra1_o.compareTo(account_extra1) != 0) {
			data_changed = 1;
			// PrintLog("extra1 changed ");
		}
		if (account_extra2_o.compareTo(account_extra2) != 0) {
			data_changed = 1;
			// PrintLog("extra2 changed ");
		}
		if (account_extra3_o.compareTo(account_extra3) != 0) {
			data_changed = 1;
			// PrintLog("extraq3 changed ");
		}
		if (account_extra4_o.compareTo(account_extra4) != 0) {
			data_changed = 1;
			// PrintLog("extr4 changed ");
		}
		if (account_extra5_o.compareTo(account_extra5) != 0) {
			data_changed = 1;
			// PrintLog("extra5 changed ");
		}
		if (account_extra6_o.compareTo(account_extra6) != 0) {
			data_changed = 1;
			// PrintLog("extra6 changed ");
		}
		if (account_acc_rep_o.compareTo(account_acc_rep) != 0) {
			data_changed = 1;
			// PrintLog("acc rep changed "+account_acc_rep.trim().length());
		}
		if (account_alt_acc_rep_o.compareTo(account_alt_acc_rep) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("altacc rep changed "+account_alt_acc_rep.trim().length());
		}
		if (account_effective_date_o.compareTo(account_effective_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("eff date changed "+account_effective_date.trim().length());
		}
		if (account_closed_date_o.compareTo(account_closed_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("clo date changed "+account_closed_date.trim().length());
		}
		if (account_blocked_date_o.compareTo(account_blocked_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("clo date changed "+account_closed_date.trim().length());
		}
		if (account_posi_pay_flag_o.compareTo(account_posi_pay_flag) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("ppay flag changed ");
		}
		if (account_gen_suspense_o.compareTo(account_gen_suspense) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("Check Acct Num changed ");
			// PrintLog(": Old: >"+account_gen_suspense_o+"< New>"+account_gen_suspense+"<");
		}
		if (account_stop_suspense_o.compareTo(account_stop_suspense) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("Check Acct Num changed ");
			//PrintLog(": Old: >" + account_stop_suspense_o + "< New>"
			//		+ account_stop_suspense + "<");
		}
		if (account_int_ext_o.compareTo(account_int_ext) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_dayscheckvalid_o.compareTo(account_dayscheckvalid) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("dayscheckvalid-o: "+account_dayscheckvalid_o+"  int ext :"+
			//			account_dayscheckvalid);
			// PrintLog("dayscheckvalid changed ");
		}
		//
		if (account_extra1_o.compareTo(account_extra1) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		if (account_extra2_o.compareTo(account_extra2) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		if (account_availday1_o.compareTo(account_availday1) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availlow1_o.compareTo(account_availlow1) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availhigh1_o.compareTo(account_availhigh1) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		if (account_availday2_o.compareTo(account_availday2) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availlow2_o.compareTo(account_availlow2) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availhigh2_o.compareTo(account_availhigh2) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		if (account_availday3_o.compareTo(account_availday3) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availlow3_o.compareTo(account_availlow3) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availhigh3_o.compareTo(account_availhigh3) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		if (account_availday4_o.compareTo(account_availday4) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availlow4_o.compareTo(account_availlow4) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availhigh4_o.compareTo(account_availhigh4) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		if (account_availday5_o.compareTo(account_availday5) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availlow5_o.compareTo(account_availlow5) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		if (account_availhigh5_o.compareTo(account_availhigh5) != 0) {
			availChanged	= true;
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("int ext-o: "+account_int_ext_o+"  int ext :"+account_int_ext);
			// PrintLog("int ext changed ");
		}
		//
		// Validate the currency, amounts, e-Mail address, dates, int_ext and
		// Posi pay flag
		//
		if (availChanged) {
			//
		}
		//PrintLog("def Currency: "+defCurr);
		if (this.bankId.substring(0, 5).equals("BNPMO")) {
			PrintLog("Currency " + account_currency);
			if (this.account_currency.equals("")) {
				if (this.account_num.length() > 16) {
					this.account_currency = this.account_num.substring(10, 13);
				} else {
					this.account_currency = this.defCurr;
				}
			} else {
				if (this.account_num.indexOf(this.account_currency) > 0) {
					// It is ok
					// } else if (this.account_num.indexOf(this.defCurr) > 0) {
					// this.account_currency = this.defCurr;
				} else {
					data_changed = 2;
					setErrorVec("Currency", "error.field.invalid");
				}
			}
		} else {
			if (account_currency.equals(" ") || account_currency.equals("")) {
				this.account_currency = this.defCurr;
			}
		}
		try {
			Double.valueOf(account_max_check_amount);
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Max Check Amount", "error.field.nonnum");
		}
		try {
			Double.valueOf(account_posi_pay_amt_min);
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Min Positive Payment Amount", "error.field.nonnum");
		}
		// if (!account_effective_date.trim().equals("") &
		// !account_effective_date.trim().equals("0")) {
		// if (account_effective_date.length() > 7) {
		if (!account_effective_date.equals(" ") && !account_effective_date.equals("0")) {
			if (ValiDate.DateAllNumeric(account_effective_date) == true) {
				if (account_effective_date.length() > 7) {
					date_yyyy	= Integer.parseInt(account_effective_date.substring(0, 4));
					date_mm		= Integer.parseInt(account_effective_date.substring(4,6));
					date_dd		= Integer.parseInt(account_effective_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid Effective
						data_changed	= 2;
						setErrorVec("Account Effective Date", "error.field.invalid");
					}
				} else {
					data_changed	= 2;
					setErrorVec("Account Effective Date", "error.field.invalid");
				}
			} else {
				data_changed	= 2;
				setErrorVec("Account Effective Date", "error.field.invalid");
			}
		} else {
			account_effective_date = "0";
		}
		// if (!account_closed_date.trim().equals("") &
		// !account_closed_date.trim().equals("0")) {
		// if (account_closed_date.trim().length() > 7) {
		if (!account_closed_date.equals(" ") && !account_closed_date.equals("0")) {
			if (ValiDate.DateAllNumeric(account_closed_date) == true) {
				if (account_closed_date.length() > 7) {
					date_yyyy = Integer.parseInt(account_closed_date.substring(0, 4));
					date_mm = Integer.parseInt(account_closed_date.substring(4, 6));
					date_dd = Integer.parseInt(account_closed_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid closed
						data_changed = 2;
						setErrorVec("Account Closed Date", "error.field.invalid");
					}
				} else {
					data_changed = 2;
					setErrorVec("Account Closed Date", "error.field.invalid");
				}
			} else {
				data_changed = 2;
				setErrorVec("Account Closed Date", "error.field.invalid");
			}
		} else {
			account_closed_date = "0";
		}
		// if (!account_blocked_date.trim().equals("") &
		// !account_blocked_date.trim().equals("0")) {
		// if (account_blocked_date.trim().length() > 7) {
		if (!account_blocked_date.equals(" ") && !account_blocked_date.equals("0")) {
			if (ValiDate.DateAllNumeric(account_blocked_date) == true) {
				if (account_blocked_date.length() > 7) {
					date_yyyy = Integer.parseInt(account_blocked_date.substring(0,4));
					date_mm = Integer.parseInt(account_blocked_date.substring(4, 6));
					date_dd = Integer.parseInt(account_blocked_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid blocked
						data_changed = 2;
						setErrorVec("Account Blocked Date", "error.field.invalid");
					}
				} else {
					data_changed = 2;
					setErrorVec("Account Blocked Date", "error.field.invalid");
				}
			} else {
				data_changed = 2;
				setErrorVec("Account Blocked Date", "error.field.invalid");
			}
		} else {
			account_blocked_date = "0";
		}
		account_posi_pay_flag = account_posi_pay_flag.toUpperCase();
		account_posi_pay_flag_o = account_posi_pay_flag;
		// PrintLog("PosiPayFlag " + account_posi_pay_flag);
		if (account_posi_pay_flag.equals("Y")
				|| account_posi_pay_flag.equals("N")) {
			// OK
		} else {
			// error
			data_changed = 2;
			setErrorVec("Positive Payment Flag", "error.field.invalid"); // set up the error
		}
		account_int_ext = account_int_ext.toUpperCase();
		account_int_ext_o = account_int_ext;
		if (bankId.equals("FNGNY")) { // C=Creditor D=Debtor
			if (account_int_ext.equals("D") || account_int_ext.equals("C")) {
				// OK
			} else {
				// error
				data_changed = 2;
				// set up the error
				setErrorVec("Internal/External Flag", "error.field.invalid");
			}
		} else {
			if (account_int_ext.equals("I") || account_int_ext.equals("E")) {
				// OK
			} else {
				// error
				data_changed = 2;
				//set up the error
				setErrorVec("Internal/External Flag", "error.field.invalid"); 
			}
		}
		//
		// Check Valid days must lie in the 30-999 days range
		//
		if (gUtil.isNumeric(this.account_dayscheckvalid) == true) {
			int i = Integer.parseInt(this.account_dayscheckvalid);
			// PrintLog("Days Check Valid: " + i);
			if ((i > 999) || (i < 30)) {
				data_changed = 2;
				// set up the error
				setErrorVec("Days Check Valid", "error.field.invalid");
			} else {
				// OK
			}
		}
		//
		// Check if any of the FREQ fields is set to Y. If yes make sure the
		// corresponding
		// address field is entered.
		//
		if (account_stmt_emailfreqD.equals("Y")
				|| account_stmt_emailfreqW.equals("Y")
				|| account_stmt_emailfreqM.equals("Y")) {
			if (account_stmt_email.equals(" ") || account_stmt_email.equals("")) {
				data_changed = 2;
				setErrorVec("Email Address", "error.field.required");
			}
		}
		if (account_stmt_faxfreqD.equals("Y")
				|| account_stmt_faxfreqW.equals("Y")
				|| account_stmt_faxfreqM.equals("Y")) {
			if (account_stmt_fax.equals(" ") || account_stmt_fax.equals("")) {
				data_changed = 2;
				setErrorVec("Fax Number", "error.field.required");
			}
		}
		if (account_stmt_mailfreqD.equals("Y")
				|| account_stmt_mailfreqW.equals("Y")
				|| account_stmt_mailfreqM.equals("Y")) {
			if (account_stmt_mail1.equals(" ") || account_stmt_mail1.equals("")) {
				data_changed = 2;
				setErrorVec("Mail Address", "error.field.required");
			}
		}
		//
		if (AvailabilityFieldsValid()==false) {
			data_changed = 2;
		}
		//
		/*
		if (this.bankId.equals("FNGNY")) {
			if (this.account_int_ext.equals("C")) {
				// ABA is equired field
				if (account_extra2.equals("")) {
					setErrorVec("Aba", "error.field.required");
				}
			} else if (this.account_int_ext.equals("D")) {
				// Creditor is required field
				if (account_stop_suspense.equals("")) {
					setErrorVec("Creditor Account", "error.field.required");
				}
			}
		}
		*/
		// PrintLog("Returning : " + data_changed);
		//ShowDetails();
		if (data_changed == 0) {
			setErrorVec("field", "info.nomods");
		}
		return (data_changed);
	}
	//
	public boolean AvailabilityFieldsValid () {
		moduleName	= "AvailabilityFieldsValid: ";
		PrintLog("Validating vaiability Fields");
		clearErrors();
		boolean availValid	= true;
		int account_availday1			= Integer.parseInt(this.account_availday1);
		double account_availlow1		= Double.parseDouble(this.account_availlow1);
		double account_availhigh1		= Double.parseDouble(this.account_availhigh1);
		int account_availday2			= Integer.parseInt(this.account_availday2);
		double account_availlow2		= Double.parseDouble(this.account_availlow2);
		double account_availhigh2		= Double.parseDouble(this.account_availhigh2);
		int account_availday3			= Integer.parseInt(this.account_availday3);
		double account_availlow3		= Double.parseDouble(this.account_availlow3);
		double account_availhigh3		= Double.parseDouble(this.account_availhigh3);
		int account_availday4			= Integer.parseInt(this.account_availday4);
		double account_availlow4		= Double.parseDouble(this.account_availlow4);
		double account_availhigh4		= Double.parseDouble(this.account_availhigh4);
		int account_availday5			= Integer.parseInt(this.account_availday5);
		double account_availlow5		= Double.parseDouble(this.account_availlow5);
		double account_availhigh5		= Double.parseDouble(this.account_availhigh5);
		if ((account_availday1>0) && 
			(account_availlow1>0) && (account_availhigh1>0)) {
			if ((account_availday2>0) && 
				(account_availlow2>0) && (account_availhigh2>0)) {
				if (account_availhigh2<account_availhigh1) {
					availValid	= false;
					setErrorVec("Tier 2 upper range ", "error.avail.range");
				}
				if ((account_availday3>0) && 
					(account_availlow3>0) && (account_availhigh3>0)) {
					if (account_availhigh3<account_availhigh2) {
						availValid	= false;
						setErrorVec("Tier 3 upper range ", "error.avail.range");
					}
					if ((account_availday4>0) && 
						(account_availlow4>0) && (account_availhigh4>0)) {
						if (account_availhigh4<account_availhigh3) {
							availValid	= false;
							setErrorVec("Tier 4 upper range ", "error.avail.range");
						}
						if ((account_availday5>0) && 
							(account_availlow5>0) && (account_availhigh5>0)) {
						} else {
							if (account_availhigh5!=0 && account_availhigh5<account_availhigh4) {
								availValid	= false;
								setErrorVec("Tier 5 upper range ", "error.avail.range");
							}
							if (account_availhigh4!=99999999.99) {
								availValid	= false;
								setErrorVec("Tier 5 availabaility days &/or amount range", "error.field.zero");
							}
						}
					} else {
						if (account_availhigh3!=99999999.99) {
							availValid	= false;
							setErrorVec("Tier 4 availabaility days &/or amount range", "error.field.zero");
						}
					}
				} else {
					if (account_availhigh2!=99999999.99) {
						availValid	= false;
						setErrorVec("Tier 3 availabaility days &/or amount range", "error.field.zero");
					}
				}
			} else {
				if (account_availhigh1!=99999999.99) {
					availValid	= false;
					setErrorVec("Tier 2 availabaility days &/or amount range", "error.field.zero");
				}
			}
		} else {
			availValid	= false;
			setErrorVec("Tier 1 avaiabaility days &/or amount range", "error.field.zero");
		}
		return availValid;
	}
	//
	public boolean NewAcctFieldsValid() {
		moduleName			= "NewAcctFieldsValid: ";
		boolean ret_bool	= true;
		//int found_at		= 0;
		//int found_dot		= 0;
		int date_yyyy		= 0;
		int date_mm			= 0;
		int date_dd			= 0;
		//ValiDate vDate	= new ValiDate();
		//GenUtil gUtil		= new GenUtil();
		clearNulls();
		//ShowDetails();
		// if (account_client_name.trim().equals("")) {
		if (account_num.equals("") || account_num.equals("")) {
			ret_bool = false;
			PrintLog("Account Num blank");
			setErrorVec("Account Number", "error.field.required");
			return false;
		}
		if (account_client_name.equals("")) {
			ret_bool = false;
			PrintLog("Client name NULL ");
			setErrorVec("Client Name", "error.field.required");
		}
		PrintLog("bankId " + bankId);
		if (this.bankId.substring(0, 5).equals("BNPMO")) {
			PrintLog("Currency " + account_currency);
			if (this.account_currency.equals("")) {
				if (this.account_num.length() > 16) {
					this.account_currency = this.account_num.substring(10, 13);
				} else {
					this.account_currency = this.defCurr;
				}
			} else {
				if (this.account_num.length() > 16) {
					if (this.account_num.indexOf(this.account_currency) > 0) {
						// OK
					} else {
						// data_changed= 2;
						ret_bool = false;
						setErrorVec("Currency", "error.field.invalid");
					}
				} else {
					// data_changed= 2;
					// ret_bool = false;
					// setErrorVec("Currency","error.field.invalid");
				}
			}
		} else {
			//this.account_currency = this.defCurr;
		}
		if (!account_max_check_amount.equals(" ")) {
			try {
				Double.valueOf(account_max_check_amount);
			} catch (NumberFormatException nfe) {
				ret_bool = false;
				setErrorVec("Max Check Amount", "error.field.nonnum");
			}
		}
		if (!account_posi_pay_amt_min.equals(" ")) {
			try {
				Double.valueOf(account_posi_pay_amt_min);
			} catch (NumberFormatException nfe) {
				ret_bool = false;
				setErrorVec("Min Positive Payment Amount", "error.field.nonnum");
			}
		}
		if (!account_effective_date.equals(" ")
				& !account_effective_date.equals("0")) {
			if (ValiDate.DateAllNumeric(account_effective_date) == true) {
				if ((account_effective_date.length() > 0) && 
						(account_effective_date.length() < 8)) {
					ret_bool = false;
					setErrorVec("Account Effective Date", "error.field.invalid");
				} else if (account_effective_date.length() > 7) {
					date_yyyy = Integer.parseInt(account_effective_date.substring(0, 4));
					date_mm = Integer.parseInt(account_effective_date.substring(4,6));
					date_dd = Integer.parseInt(account_effective_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid Effective
						ret_bool = false;
						setErrorVec("Account Effective Date", "error.field.invalid");
					}
				}
			} else {
				ret_bool = false;
				setErrorVec("Account Effective Date", "error.field.invalid");
			}
		}
		if (!account_closed_date.equals(" ") && !account_closed_date.equals("0")) {
			if (ValiDate.DateAllNumeric(account_closed_date) == true) {
				if ((account_closed_date.length() > 0) && (account_closed_date.length() < 8)) {
					ret_bool = false;
					setErrorVec("Account Closed Date", "error.field.invalid");
				} else if (account_closed_date.length() > 7) {
					date_yyyy = Integer.parseInt(account_closed_date.substring(0, 4));
					date_mm = Integer.parseInt(account_closed_date.substring(4, 6));
					date_dd = Integer.parseInt(account_closed_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid closed
						ret_bool = false;
						setErrorVec("Account Closed Date", "error.field.invalid");
					}
				}
			} else {
				ret_bool = false;
				setErrorVec("Account Closed Date", "error.field.invalid");
			}
		}
		if (!account_blocked_date.equals(" ") && !account_blocked_date.equals("0")) {
			if (ValiDate.DateAllNumeric(account_blocked_date) == true) {
				if ((account_blocked_date.length() > 0)
						&& (account_blocked_date.length() < 8)) {
					ret_bool = false;
					setErrorVec("Account Blocked Date", "error.field.invalid");
				} else if (account_blocked_date.length() > 7) {
					date_yyyy = Integer.parseInt(account_blocked_date.substring(0,4));
					date_mm = Integer.parseInt(account_blocked_date.substring(4, 6));
					date_dd = Integer.parseInt(account_blocked_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid blocked
						ret_bool = false;
						setErrorVec("Account Blocked Date", "error.field.invalid");
					}
				}
			} else {
				ret_bool = false;
				setErrorVec("Account Blocked Date", "error.field.invalid");
			}
		}
		account_int_ext = account_int_ext.toUpperCase();
		account_int_ext_o = account_int_ext;
		if (account_int_ext.equals("I") || account_int_ext.equals("E")) {
			// OK
		} else {
			// error
			ret_bool = false;
			// set up the error
			setErrorVec("Internal/External Flag", "error.field.invalid");
		}
		account_posi_pay_flag = account_posi_pay_flag.toUpperCase();
		account_posi_pay_flag_o = account_posi_pay_flag;
		if (account_posi_pay_flag.equals("Y")
				|| account_posi_pay_flag.equals("N")) {
			// OK
		} else {
			// error
			ret_bool = false;
			// set up the error
			setErrorVec("Positive Payment Flag", "error.field.invalid");
		}
		if (this.account_gen_suspense.equals("")) {
			this.account_gen_suspense = (this.account_num.substring(1, 3) +
										 this.account_num.substring(4, 10) + 
										 this.account_num.substring(13, 18));
		}
		//
		// Check Valid days must lie in the 30-999 days range
		//
		if (gUtil.isNumeric(this.account_dayscheckvalid) == true) {
			int i = Integer.parseInt(this.account_dayscheckvalid);
			if ((i > 999) || (i < 30)) {
				ret_bool = false;
				// set up the error
				setErrorVec("Days Check Valid", "error.field.invalid");
			}
		} else {
			ret_bool = false;
			// set up the error
			setErrorVec("Days Check Valid", "error.field.invalid");
		}
		ret_bool	= AvailabilityFieldsValid();
		return (ret_bool);
	}
	//
}
