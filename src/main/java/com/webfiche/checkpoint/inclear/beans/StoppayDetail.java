package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;
import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.2 $ $Date: 2016/04/09 14:33:07 $
 */

public final class StoppayDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID		 = 1L;
	// --------------------------------------------------- Instance Variables
	private String className				= "> StoppayDetail.";
	private String moduleName				= "";
	private String	actionFlag				= "";
	private String	accessFlag				= "";
	private String	dbUsed					= "";
	private String	appl_date				= "";
	private String	bankId					= "";
	private String	userId;
	private String	defCurr					= "";
	private String	stopay_account_num		= "";
	private String	stopay_check_num		= "";
	private String	stopay_check_nume		= "";
	private String	stopay_value_date		= "";
	private String	stopay_currency			= "";
	private String	stopay_check_amount_disp= "";
	private String	stopay_check_amount		= "";
	private String	stopay_swift_ref		= "";
	private String	stopay_issue_date		= "";
	private String	stopay_payee			= "";
	private String	stopay_payee_addr1		= "";
	private String	stopay_payee_addr2		= "";
	private String	stopay_payee_addr3		= "";
	private String	stopay_proc_date		= "";
	private String	stopay_expiry_date		= "";
	private String	stopay_status			= "";
	private String	stopay_last_modified	= "";
	private String	stopay_mod_user			= "";
	private String	stopay_mod_func			= "";
	//
	private String	stopay_account_num_o	= "";
	private String	stopay_check_num_o		= "";
	private String	stopay_check_nume_o		= "";
	private String	stopay_value_date_o		= "";
	private String	stopay_currency_o		= "";
	private String	stopay_check_amount_o	= "";
	private String	stopay_swift_ref_o		= "";
	private String	stopay_issue_date_o		= "";
	private String	stopay_payee_o			= "";
	private String	stopay_payee_addr1_o	= "";
	private String	stopay_payee_addr2_o	= "";
	private String	stopay_payee_addr3_o	= "";
	private String	stopay_proc_date_o		= "";
	private String	stopay_expiry_date_o	= "";
	private String	stopay_status_o			= "";
	private String	stopay_last_modified_o  = "";
	private String	stopay_mod_user_o		= "";
	private String	stopay_mod_func_o		= "";
	//
	private HashMap<String, String> stopay_modparam		  = new HashMap<String, String>();
	//
	private Vector<Vector<String>>  errorVec	= new Vector<Vector<String>>();
	//
	DecimalFormat	df		= new DecimalFormat("###,##0.00");

	// ----------------------------------------------------------- Properties
	//
	/**/
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()+
							className+moduleName+userId+" - "+logMsg); }
	 /**/
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
	public String getDefCurr() {
		return (this.defCurr);
	}
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	//
	public String getStopay_account_num() {
		return (this.stopay_account_num);
	}
	public void setStopay_account_num(String stopay_account_num) {
		this.stopay_account_num = stopay_account_num;
	}
	//
	public String getStopay_check_num() {
		return (this.stopay_check_num);
	}
	public void setStopay_check_num(String stopay_check_num) {
		this.stopay_check_num = stopay_check_num;
	}
	//
	public String getStopay_check_nume() {
		return (this.stopay_check_nume);
	}
	public void setStopay_check_nume(String stopay_check_nume) {
		this.stopay_check_nume = stopay_check_nume;
	}
	//
	public String getStopay_value_date() {
		return (this.stopay_value_date);
	}
	public void setStopay_value_date(String stopay_value_date) {
		this.stopay_value_date = stopay_value_date;
	}
	//
	public String getStopay_currency() {
		return (this.stopay_currency);
	}
	public void setStopay_currency(String stopay_currency) {
		this.stopay_currency = stopay_currency.toUpperCase();
	}
	//
	public String getStopay_check_amount() {
		return (this.stopay_check_amount);
	}
	public String getStopay_check_amount_disp() {
		return (this.stopay_check_amount_disp);
	}
	public void setStopay_check_amount(String stopay_check_amount) {
		String temp	= stopay_check_amount;
		if (temp.equals("")) {
			temp	= "0";
		}
		this.stopay_check_amount = temp;
		this.stopay_check_amount_disp = df.format(Double.parseDouble(temp));
	}
	//
	public String getStopay_swift_ref() {
		return (this.stopay_swift_ref);
	}
	public void setStopay_swift_ref(String stopay_swift_ref) {
		this.stopay_swift_ref = stopay_swift_ref;
	}
	//
	public String getStopay_issue_date() {
		return (this.stopay_issue_date);
	}
	public void setStopay_issue_date(String stopay_issue_date) {
		if (stopay_issue_date == null) {
			this.stopay_issue_date = appl_date;
		} else {
			this.stopay_issue_date = stopay_issue_date;
		}
	}
	//
	public String getStopay_payee() {
		return (this.stopay_payee);
	}
	public void setStopay_payee(String stopay_payee) {
		this.stopay_payee = stopay_payee;
	}
	//
	public String getStopay_payee_addr1() {
		return (this.stopay_payee_addr1);
	}
	public void setStopay_payee_addr1(String stopay_payee_addr1) {
		this.stopay_payee_addr1 = stopay_payee_addr1;
	}
	//
	public String getStopay_payee_addr2() {
		return (this.stopay_payee_addr2);
	}
	public void setStopay_payee_addr2(String stopay_payee_addr2) {
		this.stopay_payee_addr2 = stopay_payee_addr2;
	}
	//
	public String getStopay_payee_addr3() {
		return (this.stopay_payee_addr3);
	}
	public void setStopay_payee_addr3(String stopay_payee_addr3) {
		this.stopay_payee_addr3 = stopay_payee_addr3;
	}
	//
	public String getStopay_proc_date() {
		return (this.stopay_proc_date);
	}
	public void setStopay_proc_date(String stopay_proc_date) {
		this.stopay_proc_date = stopay_proc_date;
	}
	//
	public String getStopay_expiry_date() {
		return (this.stopay_expiry_date);
	}
	public void setStopay_expiry_date(String stopay_expiry_date) {
		this.stopay_expiry_date = stopay_expiry_date;
	}
	//
	public String getStopay_status() {
		return (this.stopay_status);
	}
	public void setStopay_status(String stopay_status) {
		this.stopay_status = stopay_status;
	}
	//
	public String getStopay_last_modified() {
		return (this.stopay_last_modified);
	}
	public void setStopay_last_modified(String stopay_last_modified) {
		int space_at = stopay_last_modified.indexOf(" ");
		if (!stopay_last_modified.equals("")
				&& stopay_last_modified.length() > 15) {
			this.stopay_last_modified = (stopay_last_modified.substring(0,
					space_at) + " @ " + stopay_last_modified.substring(
					space_at + 1, stopay_last_modified.length()));
		} else if (!stopay_last_modified.equals("")
				&& stopay_last_modified.length() > 13) {
			this.stopay_last_modified = stopay_last_modified.substring(0, 4) + "/";
			this.stopay_last_modified += stopay_last_modified.substring(4, 6) + "/";
			this.stopay_last_modified += stopay_last_modified.substring(6, 8) + " @ ";
			this.stopay_last_modified += stopay_last_modified.substring(8, 10) + ":";
			this.stopay_last_modified += stopay_last_modified.substring(10, 12) + ":";
			this.stopay_last_modified += stopay_last_modified.substring(12, 14);
		} else {
			this.stopay_last_modified = stopay_last_modified;
		}
	}
	//
	public String getStopay_mod_user() {
		return (this.stopay_mod_user);
	}
	public void setStopay_mod_user(String stopay_mod_user) {
		this.stopay_mod_user = stopay_mod_user;
	}
	//
	public String getStopay_mod_func() {
		return (this.stopay_mod_func);
	}
	public void setStopay_mod_func(String stopay_mod_func) {
		this.stopay_mod_func = stopay_mod_func;
	}
	//
	// Saved fields
	//
	public String getStopay_account_num_o() {
		return (this.stopay_account_num_o);
	}
	public void setStopay_account_num_o(String stopay_account_num_o) {
		this.stopay_account_num_o = stopay_account_num_o;
	}
	//
	public String getStopay_check_num_o() {
		return (this.stopay_check_num_o);
	}
	public void setStopay_check_num_o(String stopay_check_num_o) {
		this.stopay_check_num_o = stopay_check_num_o;
	}
	//
	public String getStopay_check_nume_o() {
		return (this.stopay_check_nume_o);
	}
	public void setStopay_check_nume_o(String stopay_check_nume_o) {
		this.stopay_check_nume_o = stopay_check_nume_o;
	}
	//
	public String getStopay_value_date_o() {
		return (this.stopay_value_date_o);
	}
	public void setStopay_value_date_o(String stopay_value_date_o) {
		this.stopay_value_date_o = stopay_value_date_o;
	}
	//
	public String getStopay_currency_o() {
		return (this.stopay_currency_o);
	}
	public void setStopay_currency_o(String stopay_currency_o) {
		this.stopay_currency_o = stopay_currency_o;
	}
	//
	public String getStopay_check_amount_o() {
		return (this.stopay_check_amount_o);
	}
	public void setStopay_check_amount_o(String stopay_check_amount_o) {
		this.stopay_check_amount_o = stopay_check_amount_o;
	}
	//
	public String getStopay_swift_ref_o() {
		return (this.stopay_swift_ref_o);
	}
	public void setStopay_swift_ref_o(String stopay_swift_ref_o) {
		this.stopay_swift_ref_o = stopay_swift_ref_o;
	}
	//
	public String getStopay_issue_date_o() {
		return (this.stopay_issue_date_o);
	}
	public void setStopay_issue_date_o(String stopay_issue_date_o) {
		this.stopay_issue_date_o = stopay_issue_date_o;
	}
	//
	public String getStopay_payee_o() {
		return (this.stopay_payee_o);
	}
	public void setStopay_payee_o(String stopay_payee_o) {
		this.stopay_payee_o = stopay_payee_o;
	}
	//
	public String getStopay_payee_addr1_o() {
		return (this.stopay_payee_addr1_o);
	}
	public void setStopay_payee_addr1_o(String stopay_payee_addr1_o) {
		this.stopay_payee_addr1_o = stopay_payee_addr1_o;
	}
	//
	public String getStopay_payee_addr2_o() {
		return (this.stopay_payee_addr2_o);
	}
	public void setStopay_payee_addr2_o(String stopay_payee_addr2_o) {
		this.stopay_payee_addr2_o = stopay_payee_addr2_o;
	}
	//
	public String getStopay_payee_addr3_o() {
		return (this.stopay_payee_addr3_o);
	}
	public void setStopay_payee_addr3_o(String stopay_payee_addr3_o) {
		this.stopay_payee_addr3_o = stopay_payee_addr3_o;
	}
	//
	public String getStopay_proc_date_o() {
		return (this.stopay_proc_date_o);
	}
	public void setStopay_proc_date_o(String stopay_proc_date_o) {
		this.stopay_proc_date_o = stopay_proc_date_o;
	}
	//
	public String getStopay_expiry_date_o() {
		return (this.stopay_expiry_date_o);
	}
	public void setStopay_expiry_date_o(String stopay_expiry_date_o) {
		this.stopay_expiry_date_o = stopay_expiry_date_o;
	}
	//
	public String getStopay_status_o() {
		return (this.stopay_status_o);
	}
	public void setStopay_status_o(String stopay_status_o) {
		this.stopay_status_o = stopay_status_o;
	}
	//
	public String getStopay_last_modified_o() {
		return (this.stopay_last_modified_o);
	}
	public void setStopay_last_modified_o(String stopay_last_modified_o) {
		this.stopay_last_modified_o = stopay_last_modified_o;
	}
	//
	public String getStopay_mod_user_o() {
		return (this.stopay_mod_user_o);
	}
	public void setStopay_mod_user_o(String stopay_mod_user_o) {
		this.stopay_mod_user_o = stopay_mod_user_o;
	}
	//
	public String getStopay_mod_func_o() {
		return (this.stopay_mod_func_o);
	}
	public void setStopay_mod_func_o(String stopay_mod_func_o) {
		this.stopay_mod_func_o = stopay_mod_func_o;
	}
	//
	public HashMap<String, String> getStopay_modparam() {
		return (this.stopay_modparam);
	}
	public void setStopay_modparam() {
		this.stopay_modparam.put("account_number", stopay_account_num);
		this.stopay_modparam.put("check_number", stopay_check_num);
	}
	//
	// Other methods
	//
	public void clearNulls() {
		stopay_account_num	= (stopay_account_num == null) ? " " : stopay_account_num;
		stopay_check_num	= (stopay_check_num == null) ? " " : stopay_check_num;
		stopay_check_nume	= (stopay_check_nume == null) ? " " : stopay_check_nume;
		stopay_value_date	= (stopay_value_date == null) ? " " : stopay_value_date;
		stopay_currency		= (stopay_currency == null) ? " " : stopay_currency;
		stopay_check_amount	= (stopay_check_amount == null) ? " " : stopay_check_amount;
		stopay_swift_ref	= (stopay_swift_ref == null) ? " " : stopay_swift_ref;
		stopay_issue_date	= (stopay_issue_date == null) ? " " : stopay_issue_date;
		stopay_payee		= (stopay_payee == null) ? " " : stopay_payee;
		stopay_payee_addr1	= (stopay_payee_addr1 == null) ? " "	: stopay_payee_addr1;
		stopay_payee_addr2	= (stopay_payee_addr2 == null) ? " "	: stopay_payee_addr2;
		stopay_payee_addr3	= (stopay_payee_addr3 == null) ? " "	: stopay_payee_addr3;
		stopay_proc_date	= (stopay_proc_date == null) ? " " : stopay_proc_date;
		stopay_expiry_date	= (stopay_expiry_date == null) ? " "	: stopay_expiry_date;
		stopay_status		= (stopay_status == null) ? " " : stopay_status;
		stopay_last_modified= (stopay_last_modified == null) ? " "	: stopay_last_modified;
		stopay_mod_user		= (stopay_mod_user == null) ? " " : stopay_mod_user;
		stopay_mod_func		= (stopay_mod_func == null) ? " " : stopay_mod_func;
	}
	//
	public void clearDetails() {
		stopay_account_num		= " ";
		stopay_check_num		= " ";
		stopay_check_nume		= " ";
		stopay_value_date		= " ";
		stopay_currency			= " ";
		stopay_check_amount		= " ";
		stopay_swift_ref		= " ";
		stopay_issue_date		= " ";
		stopay_payee			= " ";
		stopay_payee_addr1		= " ";
		stopay_payee_addr2		= " ";
		stopay_payee_addr3		= " ";
		stopay_proc_date		= " ";
		stopay_expiry_date		= " ";
		stopay_status			= " ";
		stopay_last_modified	= " ";
		stopay_mod_user			= " ";
		stopay_mod_func			= " ";
		setStopay_make_copy();
	}
	//
	public void setStopay_make_copy() {
		clearNulls();
		stopay_account_num_o	= stopay_account_num;
		stopay_check_num_o		= stopay_check_num;
		stopay_check_nume_o		= stopay_check_nume;
		stopay_value_date_o		= stopay_value_date;
		stopay_currency_o		= stopay_currency;
		stopay_check_amount_o	= stopay_check_amount;
		stopay_swift_ref_o		= stopay_swift_ref;
		stopay_issue_date_o		= stopay_issue_date;
		stopay_payee_o			= stopay_payee;
		stopay_payee_addr1_o	= stopay_payee_addr1;
		stopay_payee_addr2_o	= stopay_payee_addr2;
		stopay_payee_addr3_o	= stopay_payee_addr3;
		stopay_proc_date_o		= stopay_proc_date;
		stopay_expiry_date_o	= stopay_expiry_date;
		stopay_status_o			= stopay_status;
		stopay_last_modified_o	= stopay_last_modified;
		stopay_mod_user_o		= stopay_mod_user;
		stopay_mod_func_o		= stopay_mod_func;
	}
	public int CheckForChanges() {
		// moduleName = "CheckForChanges: ";
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed= 0;
		int date_yyyy	= 0;
		int date_mm		= 0;
		int date_dd		= 0;
		//int a_year		= 12; // 12 months
		int sixMonths	= 6; // 12 months
		clearNulls();
		//
		// Just check if any of the following fields was changed
		if (stopay_payee_o.compareTo(stopay_payee) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (stopay_payee_addr1_o.compareTo(stopay_payee_addr1) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (stopay_payee_addr2_o.compareTo(stopay_payee_addr2) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (stopay_payee_addr3_o.compareTo(stopay_payee_addr3) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (stopay_swift_ref_o.compareTo(stopay_swift_ref) != 0) {
			data_changed = 1;
			// PrintLog("sw addr changed ");
		}
		if (stopay_check_amount_o.compareTo(stopay_check_amount) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (stopay_issue_date_o.compareTo(stopay_issue_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("eff date changed "+stopay_effective_date.trim().length());
		}
		if (stopay_value_date_o.compareTo(stopay_value_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("clo date changed "+stopay_closed_date.trim().length());
		}
		if (stopay_currency_o.compareTo(stopay_currency) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("clo date changed "+stopay_closed_date.trim().length());
		}
		if (stopay_status_o.compareTo(stopay_status) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("ppay flag changed ");
		}
		//
		// Validate Currency
		//
		// PrintLog(" Stoppaydetail> CheckForChanges: Validate Currency "+
		// " Stoppay Account "+this.stopay_account_num+
		// " Stoppay Currency "+this.stopay_currency);
		if (this.bankId.substring(0, 3).equals("BNP")) {
			if (this.stopay_currency.equals("")) {
				if (this.stopay_account_num.length() > 17) {
					this.stopay_currency = this.stopay_account_num.substring(
							10, 13);
				} else {
					this.stopay_currency = this.defCurr;
				}
			} else {
				// PrintLog(" Position of currency: "+this.stopay_account_num.indexOf(this.stopay_currency));
				if (this.stopay_account_num.indexOf(this.stopay_currency) > 0) {
					// It is ok
					// PrintLog("Stoppaydetail> CheckForChanges: Validate Currency "+
					// " Stoppay Account "+this.stopay_account_num+
					// " Stoppay Currency "+this.stopay_currency);
				} else if (this.stopay_account_num.indexOf(this.defCurr) > 0) {
					this.stopay_currency = this.defCurr;
				} else {
					data_changed = 2;
					// ret_bool = false;
					setErrorVec("Currency", "error.field.invalid");
				}
			}
		} else {
			this.stopay_currency = this.defCurr;
		}
		/*
		 * // // Validate the check amount // if
		 * (stopay_check_amount.trim().equals("") ||
		 * stopay_check_amount.trim().equals("0")) { data_changed = 2;
		 * setErrorVec("Amount","error.field.required"); }
		 */
		try {
			if (stopay_issue_date.trim().length() > 7) {
				// date_yyyy = Integer.parseInt(stopay_issue_date.substring(0,4));
				// date_mm = Integer.parseInt(stopay_issue_date.substring(4,6));
				// date_dd = Integer.parseInt(stopay_issue_date.substring(6));
				date_yyyy = Integer.parseInt(appl_date.substring(0, 4));
				date_mm = Integer.parseInt(appl_date.substring(4, 6));
				date_dd = Integer.parseInt(appl_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid Effective
					data_changed = 2;
					setErrorVec("Issue Date", "error.field.invalid");
				} else {
					this.stopay_expiry_date = ValiDate.getFutureDates(date_mm, date_dd, date_yyyy, sixMonths);
					PrintLog("New fields: expirydate " + this.stopay_expiry_date);
				}
			} else {
				data_changed = 2;
				setErrorVec("Issue Date", "error.field.invalid");
			}
		} catch (Exception e) {
			data_changed = 2;
			setErrorVec("Issue Date", "error.field.invalid");
		}
		try {
			if (stopay_value_date.trim().length() > 7) {
				date_yyyy = Integer.parseInt(stopay_value_date.substring(0, 4));
				date_mm = Integer.parseInt(stopay_value_date.substring(4, 6));
				date_dd = Integer.parseInt(stopay_value_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid value
					data_changed = 2;
					setErrorVec("Value Date", "error.field.invalid");
				}
			} else {
				// data_changed = 2;
				// setErrorVec("Value Date","error.field.invalid");
				stopay_value_date = stopay_issue_date;
			}
		} catch (Exception e) {
			data_changed = 2;
			setErrorVec("Value Date", "error.field.invalid");
		}
		if (stopay_issue_date.trim().compareTo(stopay_value_date.trim()) > 0) {
			data_changed = 2;
			setErrorVec("valueissdates", "error.date.valultissu");
		}
		stopay_status = stopay_status.toUpperCase();
		// stopay_status_o = stopay_status;
		if (stopay_status.equals("M") || stopay_status.equals("U")) {
			// OK
		} else {
			// error
			data_changed = 2;
			setErrorVec("Status", "error.field.invalid"); // set up the error
		}
		// PrintLog(": CheckForChanges: Returning : " + data_changed);
		if (data_changed == 0) {
			setErrorVec("nomods", "info.nomods");
		}
		return (data_changed);
	}

	public boolean NewStopFieldsValid() {
		boolean ret_bool = true;
		int date_yyyy = 0;
		int date_mm = 0;
		int date_dd = 0;
		//int a_year = 12; // 12 months
		int sixMonths	= 6; // 12 months
		clearNulls();
		// Acccount num exists and the stoppay does not (acct bnum + check #)
		//
		// Validate the Check Number
		//
		try {
			if (Double.valueOf(stopay_check_num).toString().equals("0.0")) {
				ret_bool = false;
				setErrorVec("Check Number", "error.field.required");
			}
		} catch (NumberFormatException nfe) {
			ret_bool = false;
			setErrorVec("Check Number", "error.field.required");
		}
		//
		// Validate Currency
		//
		// PrintLog("Stoppaydetail> CheckForChanges: Validate Currency "+
		// "Stoppay Account "+this.stopay_account_num+
		// " Stoppay Currency "+this.stopay_currency);
		if (this.bankId.substring(0, 3).equals("BNP")) {
			if (this.stopay_currency.equals("")) {
				if (this.stopay_account_num.length() > 17) {
					this.stopay_currency = this.stopay_account_num.substring(
							10, 13);
				} else {
					this.stopay_currency = this.defCurr;
				}
			} else {
				// PrintLog("Position of currency: "+this.stopay_account_num.indexOf(this.stopay_currency));
				if (this.stopay_account_num.indexOf(this.stopay_currency) > 0) {
					// It is ok
				} else if (this.stopay_account_num.indexOf(this.defCurr) > 0) {
					this.stopay_currency = this.defCurr;
				} else {
					// data_changed= 2;
					ret_bool = false;
					setErrorVec("Currency", "error.field.invalid");
				}
			}
		} else {
			this.stopay_currency = this.defCurr;
		}
		//
		// Validate the check amount
		//
		/*
		 * try { if (Double.parseDouble(stopay_check_amount) == true) { //if
		 * (Double.valueOf(stopay_check_amount).toString().equals("0.0")) {
		 * //ret_bool = false; //setErrorVec("Amount","error.field.required"); }
		 * } catch (NumberFormatException nfe) { ret_bool = false;
		 * setErrorVec("Amount","error.field.required"); }
		 */
		//
		try {
			if (!stopay_issue_date.equals("")) {
				if ((stopay_issue_date.trim().length() > 0)
						&& (stopay_issue_date.trim().length() < 8)) {
					ret_bool = false;
					setErrorVec("Issue Date", "error.field.invalid");
				} else if (stopay_issue_date.trim().length() > 7) {
					// date_yyyy =
					// Integer.parseInt(stopay_issue_date.substring(0,4));
					// date_mm = Integer.parseInt(stopay_issue_date.substring(4,6));
					// date_dd = Integer.parseInt(stopay_issue_date.substring(6));
					date_yyyy = Integer.parseInt(appl_date.substring(0, 4));
					date_mm = Integer.parseInt(appl_date.substring(4, 6));
					date_dd = Integer.parseInt(appl_date.substring(6));
					if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
						// Invalid Effective
						ret_bool = false;
						setErrorVec("Issue Date", "error.field.invalid");
					} else {
						this.stopay_expiry_date = ValiDate.getFutureDates(date_mm,
								date_dd, date_yyyy, sixMonths);
						PrintLog(" New fields: expirydate " + this.stopay_expiry_date);
					}
				} else {
					ret_bool = false;
					setErrorVec("Issue Date", "error.field.invalid");
				}
			}
		} catch (Exception e) {
			ret_bool = false;
			setErrorVec("Issue Date", "error.field.invalid");
		}
		//
		if (stopay_payee.trim().equals("")) {
			ret_bool = false;
			PrintLog("CheckForChanges: NULL ");
			setErrorVec("Payee", "error.field.required");
		}
		//
		try {
			if ((stopay_value_date.trim().length() > 0)
					&& (stopay_value_date.trim().length() < 8)) {
				ret_bool = false;
				setErrorVec("Value Date", "error.field.invalid");
			} else if (stopay_value_date.trim().length() > 7) {
				date_yyyy = Integer.parseInt(stopay_value_date.substring(0, 4));
				date_mm = Integer.parseInt(stopay_value_date.substring(4, 6));
				date_dd = Integer.parseInt(stopay_value_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid value
					ret_bool = false;
					setErrorVec("Value Date", "error.field.invalid");
				}
			} else {
				stopay_value_date = stopay_issue_date;
			}
		} catch (Exception e) {
			ret_bool = false;
			setErrorVec("Value Date", "error.field.invalid");
		}
		stopay_status = stopay_status.toUpperCase();
		if (stopay_status.equals("M") || stopay_status.equals("U")) {
			// OK
		} else {
			// error
			ret_bool = false;
			setErrorVec("Status", "error.field.invalid"); // set up the error
		}
		return (ret_bool);
	}
}
