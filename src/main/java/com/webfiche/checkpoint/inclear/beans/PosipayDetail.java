package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;
import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.2 $ $Date: 2016/04/09 14:33:06 $
 */

public final class PosipayDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String className = "> AccountDetail.";
	private String moduleName = "";
	private String actionFlag = "";
	private String accessFlag = "";
	private String dbUsed = "";
	private String appl_date = "";
	private String bankId = "";
	private String userId;
	private String defCurr = "USD";
	private String pospay_account_num = "";
	private String pospay_check_num = "";
	private String pospay_value_date = "";
	private String pospay_currency = "";
	private String pospay_check_amount_disp = "";
	private String pospay_check_amount = "";
	private String pospay_swift_ref = "";
	private String pospay_issue_date = "";
	private String pospay_payee = "";
	private String pospay_payee_addr1 = "";
	private String pospay_payee_addr2 = "";
	private String pospay_payee_addr3 = "";
	private String pospay_proc_date = "";
	private String pospay_expiry_date = "";
	private String pospay_status = "";
	private String pospay_last_modified = "";
	private String pospay_mod_user = "";
	private String pospay_mod_func = "";
	private HashMap<String, String> pospay_modparam = new HashMap<String, String>();
	private String pospay_account_num_o = "";
	private String pospay_check_num_o = "";
	private String pospay_value_date_o = "";
	private String pospay_currency_o = "";
	private String pospay_check_amount_o = "";
	private String pospay_swift_ref_o = "";
	private String pospay_issue_date_o = "";
	private String pospay_payee_o = "";
	private String pospay_payee_addr1_o = "";
	private String pospay_payee_addr2_o = "";
	private String pospay_payee_addr3_o = "";
	private String pospay_proc_date_o = "";
	private String pospay_expiry_date_o = "";
	private String pospay_status_o = "";
	private String pospay_last_modified_o = "";
	private String pospay_mod_user_o = "";
	private String pospay_mod_func_o = "";
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
	//
	DecimalFormat df = new DecimalFormat("###,##0.00");

	// ----------------------------------------------------------- Properties
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + userId + " - " + logMsg);
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
	public String getPospay_account_num() {
		return (this.pospay_account_num);
	}

	public void setPospay_account_num(String pospay_account_num) {
		this.pospay_account_num = pospay_account_num;
	}

	//
	public String getPospay_check_num() {
		return (this.pospay_check_num);
	}

	public void setPospay_check_num(String pospay_check_num) {
		this.pospay_check_num = pospay_check_num;
	}

	//
	public String getPospay_value_date() {
		return (this.pospay_value_date);
	}

	public void setPospay_value_date(String pospay_value_date) {
		this.pospay_value_date = pospay_value_date;
	}

	//
	public String getPospay_currency() {
		return (this.pospay_currency);
	}

	public void setPospay_currency(String pospay_currency) {
		this.pospay_currency = pospay_currency.toUpperCase();
	}

	//
	public String getPospay_check_amount() {
		return (this.pospay_check_amount);
	}

	public String getPospay_check_amount_disp() {
		return (this.pospay_check_amount_disp);
	}

	public void setPospay_check_amount(String pospay_check_amount) {
		String temp	= pospay_check_amount;
		if (temp.equals("")) {
			temp = "0";
		}
		this.pospay_check_amount = temp;
		this.pospay_check_amount_disp	= df.format(Double.parseDouble(temp));
	}

	//
	public String getPospay_swift_ref() {
		return (this.pospay_swift_ref);
	}

	public void setPospay_swift_ref(String pospay_swift_ref) {
		this.pospay_swift_ref = pospay_swift_ref;
	}

	//
	public String getPospay_issue_date() {
		return (this.pospay_issue_date);
	}

	public void setPospay_issue_date(String pospay_issue_date) {
		this.pospay_issue_date = pospay_issue_date;
	}

	//
	public String getPospay_payee() {
		return (this.pospay_payee);
	}

	public void setPospay_payee(String pospay_payee) {
		this.pospay_payee = pospay_payee;
	}

	//
	public String getPospay_payee_addr1() {
		return (this.pospay_payee_addr1);
	}

	public void setPospay_payee_addr1(String pospay_payee_addr1) {
		this.pospay_payee_addr1 = pospay_payee_addr1;
	}

	//
	public String getPospay_payee_addr2() {
		return (this.pospay_payee_addr2);
	}

	public void setPospay_payee_addr2(String pospay_payee_addr2) {
		this.pospay_payee_addr2 = pospay_payee_addr2;
	}

	//
	public String getPospay_payee_addr3() {
		return (this.pospay_payee_addr3);
	}

	public void setPospay_payee_addr3(String pospay_payee_addr3) {
		this.pospay_payee_addr3 = pospay_payee_addr3;
	}

	//
	public String getPospay_proc_date() {
		return (this.pospay_proc_date);
	}

	public void setPospay_proc_date(String pospay_proc_date) {
		this.pospay_proc_date = pospay_proc_date;
	}

	//
	public String getPospay_expiry_date() {
		return (this.pospay_expiry_date);
	}

	public void setPospay_expiry_date(String pospay_expiry_date) {
		this.pospay_expiry_date = pospay_expiry_date;
	}

	//
	public String getPospay_status() {
		return (this.pospay_status);
	}

	public void setPospay_status(String pospay_status) {
		this.pospay_status = pospay_status;
	}

	//
	public String getPospay_last_modified() {
		return (this.pospay_last_modified);
	}

	public void setPospay_last_modified(String pospay_last_modified) {
		int space_at = pospay_last_modified.indexOf(" ");
		if (!pospay_last_modified.equals("")
				&& pospay_last_modified.length() > 15) {
			this.pospay_last_modified = (pospay_last_modified.substring(0,
					space_at) + " @ " + pospay_last_modified.substring(
					space_at + 1, pospay_last_modified.length()));
		} else if (!pospay_last_modified.equals("")
				&& pospay_last_modified.length() > 13) {
			this.pospay_last_modified = pospay_last_modified.substring(0, 4)
					+ "/";
			this.pospay_last_modified += pospay_last_modified.substring(4, 6)
					+ "/";
			this.pospay_last_modified += pospay_last_modified.substring(6, 8)
					+ " @ ";
			this.pospay_last_modified += pospay_last_modified.substring(8, 10)
					+ ":";
			this.pospay_last_modified += pospay_last_modified.substring(10, 12)
					+ ":";
			this.pospay_last_modified += pospay_last_modified.substring(12, 14);
		} else {
			this.pospay_last_modified = pospay_last_modified;
		}
	}

	//
	public String getPospay_mod_user() {
		return (this.pospay_mod_user);
	}

	public void setPospay_mod_user(String pospay_mod_user) {
		this.pospay_mod_user = pospay_mod_user;
	}

	//
	public String getPospay_mod_func() {
		return (this.pospay_mod_func);
	}

	public void setPospay_mod_func(String pospay_mod_func) {
		this.pospay_mod_func = pospay_mod_func;
	}

	//
	// Saved fields
	//
	public String getPospay_account_num_o() {
		return (this.pospay_account_num_o);
	}

	public void setPospay_account_num_o(String pospay_account_num_o) {
		this.pospay_account_num_o = pospay_account_num_o;
	}

	//
	public String getPospay_check_num_o() {
		return (this.pospay_check_num_o);
	}

	public void setPospay_check_num_o(String pospay_check_num_o) {
		this.pospay_check_num_o = pospay_check_num_o;
	}

	//
	public String getPospay_value_date_o() {
		return (this.pospay_value_date_o);
	}

	public void setPospay_value_date_o(String pospay_value_date_o) {
		this.pospay_value_date_o = pospay_value_date_o;
	}

	//
	public String getPospay_currency_o() {
		return (this.pospay_currency_o);
	}

	public void setPospay_currency_o(String pospay_currency_o) {
		this.pospay_currency_o = pospay_currency_o;
	}

	//
	public String getPospay_check_amount_o() {
		return (this.pospay_check_amount_o);
	}

	public void setPospay_check_amount_o(String pospay_check_amount_o) {
		this.pospay_check_amount_o = pospay_check_amount_o;
	}

	//
	public String getPospay_swift_ref_o() {
		return (this.pospay_swift_ref_o);
	}

	public void setPospay_swift_ref_o(String pospay_swift_ref_o) {
		this.pospay_swift_ref_o = pospay_swift_ref_o;
	}

	//
	public String getPospay_issue_date_o() {
		return (this.pospay_issue_date_o);
	}

	public void setPospay_issue_date_o(String pospay_issue_date_o) {
		this.pospay_issue_date_o = pospay_issue_date_o;
	}

	//
	public String getPospay_payee_o() {
		return (this.pospay_payee_o);
	}

	public void setPospay_payee_o(String pospay_payee_o) {
		this.pospay_payee_o = pospay_payee_o;
	}

	//
	public String getPospay_payee_addr1_o() {
		return (this.pospay_payee_addr1_o);
	}

	public void setPospay_payee_addr1_o(String pospay_payee_addr1_o) {
		this.pospay_payee_addr1_o = pospay_payee_addr1_o;
	}

	//
	public String getPospay_payee_addr2_o() {
		return (this.pospay_payee_addr2_o);
	}

	public void setPospay_payee_addr2_o(String pospay_payee_addr2_o) {
		this.pospay_payee_addr2_o = pospay_payee_addr2_o;
	}

	//
	public String getPospay_payee_addr3_o() {
		return (this.pospay_payee_addr3_o);
	}

	public void setPospay_payee_addr3_o(String pospay_payee_addr3_o) {
		this.pospay_payee_addr3_o = pospay_payee_addr3_o;
	}

	//
	public String getPospay_proc_date_o() {
		return (this.pospay_proc_date_o);
	}

	public void setPospay_proc_date_o(String pospay_proc_date_o) {
		this.pospay_proc_date_o = pospay_proc_date_o;
	}

	//
	public String getPospay_expiry_date_o() {
		return (this.pospay_expiry_date_o);
	}

	public void setPospay_expiry_date_o(String pospay_expiry_date_o) {
		this.pospay_expiry_date_o = pospay_expiry_date_o;
	}

	//
	public String getPospay_status_o() {
		return (this.pospay_status_o);
	}

	public void setPospay_status_o(String pospay_status_o) {
		this.pospay_status_o = pospay_status_o;
	}

	//
	public String getPospay_last_modified_o() {
		return (this.pospay_last_modified_o);
	}

	public void setPospay_last_modified_o(String pospay_last_modified_o) {
		this.pospay_last_modified_o = pospay_last_modified_o;
	}

	//
	public String getPospay_mod_user_o() {
		return (this.pospay_mod_user_o);
	}

	public void setPospay_mod_user_o(String pospay_mod_user_o) {
		this.pospay_mod_user_o = pospay_mod_user_o;
	}

	//
	public String getPospay_mod_func_o() {
		return (this.pospay_mod_func_o);
	}

	public void setPospay_mod_func_o(String pospay_mod_func_o) {
		this.pospay_mod_func_o = pospay_mod_func_o;
	}

	//
	public HashMap<String, String> getPospay_modparam() {
		return (this.pospay_modparam);
	}

	public void setPospay_modparam() {
		this.pospay_modparam.put("account_number", pospay_account_num);
		this.pospay_modparam.put("check_number", pospay_check_num);
	}

	//
	// Other methods
	//
	public void clearNulls() {
		pospay_account_num = (pospay_account_num == null) ? " "	: pospay_account_num;
		pospay_check_num = (pospay_check_num == null) ? " " : pospay_check_num;
		pospay_value_date = (pospay_value_date == null) ? " " : pospay_value_date;
		pospay_currency = (pospay_currency == null) ? " " : pospay_currency;
		pospay_check_amount = (pospay_check_amount == null) ? " " : pospay_check_amount;
		pospay_swift_ref = (pospay_swift_ref == null) ? " " : pospay_swift_ref;
		pospay_issue_date = (pospay_issue_date == null) ? " " : pospay_issue_date;
		pospay_payee = (pospay_payee == null) ? " " : pospay_payee;
		pospay_payee_addr1 = (pospay_payee_addr1 == null) ? " " : pospay_payee_addr1;
		pospay_payee_addr2 = (pospay_payee_addr2 == null) ? " " : pospay_payee_addr2;
		pospay_payee_addr3 = (pospay_payee_addr3 == null) ? " " : pospay_payee_addr3;
		pospay_proc_date = (pospay_proc_date == null) ? " " : pospay_proc_date;
		pospay_expiry_date = (pospay_expiry_date == null) ? " " : pospay_expiry_date;
		pospay_status = (pospay_status == null) ? " " : pospay_status;
		pospay_last_modified = (pospay_last_modified == null) ? " " : pospay_last_modified;
		pospay_mod_user = (pospay_mod_user == null) ? " " : pospay_mod_user;
		pospay_mod_func = (pospay_mod_func == null) ? " " : pospay_mod_func;
	}

	//
	public void clearDetails() {
		pospay_account_num = " ";
		pospay_check_num = " ";
		pospay_value_date = " ";
		pospay_currency = " ";
		pospay_check_amount = " ";
		pospay_swift_ref = " ";
		pospay_issue_date = " ";
		pospay_payee = " ";
		pospay_payee_addr1 = " ";
		pospay_payee_addr2 = " ";
		pospay_payee_addr3 = " ";
		pospay_proc_date = " ";
		pospay_expiry_date = " ";
		pospay_status = " ";
		pospay_last_modified = " ";
		pospay_mod_user = " ";
		pospay_mod_func = " ";
		setPospay_make_copy();
	}

	//
	public void setPospay_make_copy() {
		clearNulls();
		pospay_account_num_o	= pospay_account_num;
		pospay_check_num_o		= pospay_check_num;
		pospay_value_date_o		= pospay_value_date;
		pospay_currency_o		= pospay_currency;
		pospay_check_amount_o	= pospay_check_amount;
		pospay_swift_ref_o		= pospay_swift_ref;
		pospay_issue_date_o		= pospay_issue_date;
		pospay_payee_o			= pospay_payee;
		pospay_payee_addr1_o	= pospay_payee_addr1;
		pospay_payee_addr2_o	= pospay_payee_addr2;
		pospay_payee_addr3_o	= pospay_payee_addr3;
		pospay_proc_date_o		= pospay_proc_date;
		pospay_expiry_date_o	= pospay_expiry_date;
		pospay_status_o			= pospay_status;
		pospay_last_modified_o	= pospay_last_modified;
		pospay_mod_user_o		= pospay_mod_user;
		pospay_mod_func_o		= pospay_mod_func;
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
		int date_yyyy = 0;
		int date_mm = 0;
		int date_dd = 0;
		clearNulls();
		//
		// Just check if any of the following fields was changed
		if (pospay_payee_o.compareTo(pospay_payee) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (pospay_payee_addr1_o.compareTo(pospay_payee_addr1) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (pospay_payee_addr2_o.compareTo(pospay_payee_addr2) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (pospay_payee_addr3_o.compareTo(pospay_payee_addr3) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (pospay_swift_ref_o.compareTo(pospay_swift_ref) != 0) {
			data_changed = 1;
			// PrintLog("sw addr changed ");
		}
		if (pospay_check_amount_o.compareTo(pospay_check_amount) != 0) {
			data_changed = 1;
			// PrintLog("name changed ");
		}
		if (pospay_issue_date_o.compareTo(pospay_issue_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("eff date changed "+pospay_effective_date.trim().length());
		}
		if (pospay_value_date_o.compareTo(pospay_value_date) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("clo date changed "+pospay_closed_date.trim().length());
		}
		if (pospay_currency_o.compareTo(pospay_currency) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("eff date changed "+pospay_effective_date.trim().length());
		}
		if (pospay_status_o.compareTo(pospay_status) != 0) {
			if (data_changed == 0)
				data_changed = 1;
			// PrintLog("ppay flag changed ");
		}
		//
		// Validate the Check Number
		//
		try {
			if (Double.valueOf(pospay_check_num).toString().equals("0.0")) {
				data_changed = 2;
				setErrorVec("Check Number", "error.field.required");
			}
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Check Number", "error.field.required");
		}
		//
		// Validate Currency
		//
		if (this.bankId.substring(0, 3).equals("BNPMO")) {
			if (this.pospay_currency.equals("")) {
				if (this.pospay_account_num.length() > 17) {
					this.pospay_currency = this.pospay_account_num.substring(
							10, 13);
				} else {
					this.pospay_currency = this.defCurr;
				}
			} else {
				if (this.pospay_account_num.indexOf(this.pospay_currency) > 0) {
					// It is ok
				} else if (this.pospay_account_num.indexOf(this.defCurr) > 0) {
					this.pospay_currency = this.defCurr;
				} else {
					data_changed = 2;
					// ret_bool = false;
					setErrorVec("Currency", "error.field.invalid");
				}
			}
		} else {
			this.pospay_currency = this.defCurr;
		}
		//
		// Validate Check Amount
		//
		try {
			if (Double.valueOf(pospay_check_amount).toString().equals("0.0")) {
				// if (Double.compare(Double.valueOf(chex_check_num) ==
				// Double.valueOf(cnum_zero))) {
				data_changed = 2;
				setErrorVec("Amount", "error.field.required");
			}
		} catch (NumberFormatException nfe) {
			data_changed = 2;
			setErrorVec("Amount", "error.field.required");
		}
		//
		try {
			if (pospay_issue_date.trim().length() > 7) {
				date_yyyy = Integer.parseInt(pospay_issue_date.substring(0, 4));
				date_mm = Integer.parseInt(pospay_issue_date.substring(4, 6));
				date_dd = Integer.parseInt(pospay_issue_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid Effective
					data_changed = 2;
					setErrorVec("Issue Date", "error.field.invalid");
				}
				if (pospay_issue_date.compareTo(appl_date) > 0) { // Issued in the Future
					data_changed = 2;
					setErrorVec("Issue Date", "error.date.infuture");
				}
			} else {
				data_changed = 2;
				setErrorVec("Issue Date", "error.field.invalid");
			}
		} catch (Exception e) {
			data_changed = 2;
			setErrorVec("Issue Date", "error.field.required");
		}
		try {
			if (pospay_value_date.trim().length() > 7) {
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// "> PosipayDetail.CheckForChanges: Value Date " +
				// pospay_value_date);
				date_yyyy = Integer.parseInt(pospay_value_date.substring(0, 4));
				date_mm = Integer.parseInt(pospay_value_date.substring(4, 6));
				date_dd = Integer.parseInt(pospay_value_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid value
					data_changed = 2;
					setErrorVec("Value Date", "error.field.invalid");
				}
			} else {
				// data_changed = 2;
				pospay_value_date = pospay_issue_date;
			}
		} catch (Exception e) {
			data_changed = 2;
			setErrorVec("Value Date", "error.field.required");
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> PosipayDetail.CheckForChanges: Value Date " + pospay_value_date);
		if (pospay_issue_date.trim().compareTo(pospay_value_date.trim()) > 0) {
			data_changed = 2;
			setErrorVec("valissdates", "error.date.valultissu");
		}
		pospay_status = pospay_status.toUpperCase();
		// pospay_status_o = pospay_status;
		if (pospay_status.equals("M") || pospay_status.equals("U")) {
			// OK
		} else {
			// error
			data_changed = 2;
			setErrorVec("Status", "error.field.invalid"); // set up the error
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// PrintLog("Returning : " + data_changed);
		if (data_changed == 0) {
			setErrorVec("nomods", "info.nomods");
		}
		return (data_changed);
	}
	//
	public boolean NewPosiFieldsValid() {
		boolean ret_bool = true;
		int date_yyyy = 0;
		int date_mm = 0;
		int date_dd = 0;
		// Acccount num exists and the posipay does not (acct bnum + check #)
		//
		clearNulls();
		if (pospay_payee.trim().equals("")) {
			ret_bool = false;
			PrintLog("NULL ");
			setErrorVec("Payee", "error.field.required");
		}
		//
		// Validate the Check Number
		//
		try {
			if (Double.valueOf(pospay_check_num).toString().equals("0.0")) {
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
		if (this.bankId.substring(0, 3).equals("BNP")) {
			if (this.pospay_currency.equals("")) {
				if (this.pospay_account_num.length() > 17) {
					this.pospay_currency = this.pospay_account_num.substring(
							10, 13);
				} else {
					this.pospay_currency = this.defCurr;
				}
			} else {
				if (this.pospay_account_num.indexOf(this.defCurr) > 0) {
					this.pospay_currency = this.defCurr;
				} else {
					// data_changed= 2;
					ret_bool = false;
					setErrorVec("Currency", "error.field.invalid");
				}
			}
		} else {
			this.pospay_currency = this.defCurr;
		}
		//
		// Validate the check amount
		//
		try {
			if (Double.valueOf(pospay_check_amount).toString().equals("0.0")) {
				// if (Double.compare(Double.valueOf(chex_check_num) ==
				// Double.valueOf(cnum_zero))) {
				ret_bool = false;
				setErrorVec("Amount", "error.field.required");
			}
		} catch (NumberFormatException nfe) {
			ret_bool = false;
			setErrorVec("Amount", "error.field.required");
		}
		try {
			if ((pospay_issue_date.trim().length() > 0)
					&& (pospay_issue_date.trim().length() < 8)) {
				ret_bool = false;
				setErrorVec("Issue Date", "error.field.invalid");
			} else if (pospay_issue_date.trim().length() > 7) {
				date_yyyy = Integer.parseInt(pospay_issue_date.substring(0, 4));
				date_mm = Integer.parseInt(pospay_issue_date.substring(4, 6));
				date_dd = Integer.parseInt(pospay_issue_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid Effective
					ret_bool = false;
					setErrorVec("Issue Date", "error.field.invalid");
				}
				PrintLog("ApplDate: "+appl_date+" issue_date: "+pospay_issue_date);
				if (pospay_issue_date.compareTo(appl_date) > 0) { // Issued in the Future
					ret_bool = false;
					setErrorVec("Issue Date", "error.date.infuture");
				}
			} else {
				ret_bool = false;
				setErrorVec("Issue Date", "error.field.invalid");
			}
		} catch (Exception nfe) {
			ret_bool = false;
			setErrorVec("Issue Date", "error.field.required");
		}
		try {
			if ((pospay_value_date.trim().length() > 0)
					&& (pospay_value_date.trim().length() < 8)) {
				ret_bool = false;
				setErrorVec("Value Date", "error.field.invalid");
			} else if (pospay_value_date.trim().length() > 7) {
				date_yyyy = Integer.parseInt(pospay_value_date.substring(0, 4));
				date_mm = Integer.parseInt(pospay_value_date.substring(4, 6));
				date_dd = Integer.parseInt(pospay_value_date.substring(6));
				if (ValiDate.CheckDate(date_mm, date_dd, date_yyyy) == false) {
					// Invalid value
					ret_bool = false;
					setErrorVec("Value Date", "error.field.invalid");
				}
			} else {
				// ret_bool = false;
				pospay_value_date = pospay_issue_date;
			}
		} catch (NumberFormatException nfe) {
			ret_bool = false;
			setErrorVec("Value Date", "error.field.invalid");
		}
		if (pospay_issue_date.trim().compareTo(pospay_value_date.trim()) > 0) {
			ret_bool = false;
			setErrorVec("vaissdates", "error.date.valultissu");
		}
		pospay_status = pospay_status.toUpperCase();
		// pospay_status_o = pospay_status;
		if (pospay_status.equals("M") || pospay_status.equals("U")) {
			// OK
		} else {
			// error
			ret_bool = false;
			setErrorVec("Status", "error.field.invalid"); // set up the error
		}
		return (ret_bool);
	}
}
