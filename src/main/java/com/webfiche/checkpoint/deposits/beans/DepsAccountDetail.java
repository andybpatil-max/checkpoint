package com.webfiche.checkpoint.deposits.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;
import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:31:06 $
 */

public final class DepsAccountDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String className			= "> DepsAccountDetail.";	
	private String moduleName			= "";	
	private String userId				= "";
	private String actionFlag			= "";
	private String accessFlag			= "";
	private String dbUsed				= "";
	private String applDate				= "";
	private String bankId				= "";
	private String defCurr				= "";
	private String deps_account			= "";
	private String deps_payee			= "";
	private String deps_checkaba		= "";
	private String deps_checkaccount	= "";
	private String deps_extra1			= "";
	private String deps_comments		= "";
	private String deps_modtime			= "";
	private String deps_moduser			= "";
	private String deps_modfunc			= "";
	private String deps_account_o		= "";
	private String deps_checkaba_o		= "";
	private String deps_checkaccount_o	= "";
	private String deps_comments_o		= "";
	//private String deps_extra1_o		= "";
	//
	// Suspense Accounts
	// cp=check processing
	// sd=sundry debt
	// 
	DecimalFormat df	= new DecimalFormat("###,##0.00");
	GenUtil gUtil		= new GenUtil();

	private Vector <Vector <String>> errorVec	= new Vector<Vector<String>>();

	// ----------------------------------------------------------- Properties
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()+
							className+moduleName+userId+" - "+logMsg);
	}
	//
	public void clearErrors () {
		errorVec.clear();
	}
	//
	public Vector <Vector <String>> getErrorVec() {
		return (this.errorVec);
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector <String>vecPair	= new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public void clearFields () {
		deps_account		= "";
		deps_checkaba		= "";
		deps_checkaccount	= "";
		deps_extra1		= "";
		deps_modtime		= "";
		deps_moduser		= "";
		deps_modfunc		= "";
	}
	//
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag (String actionFlag) {
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
	public void setDbUsed (String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getUserId() {
		return (this.userId);
	}
	public void setUserId (String userId) {
		this.userId = userId;
	}
	//
	public String getApplDate() {
		return (this.applDate);
	}
	public void setApplDate (String applDate) {
		this.applDate	= applDate;
		this.applDate	= GenUtil.stripSlashes(this.applDate);
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
	public String getDeps_account() {
		return (this.deps_account);
	}
	public void setDeps_account(String deps_account) {
		this.deps_account = deps_account.trim();
	}
	//
	public String getDeps_payee() {
		return (this.deps_payee);
	}
	public void setDeps_payee(String deps_payee) {
		moduleName	= "setDeps_payee: ";
		if (deps_payee == null) {
			this.deps_payee = " ";
		} else {
			this.deps_payee = deps_payee.trim();
		}
		PrintLog("Set deps Payee: "+this.deps_payee);
	}
	//
	public String getDeps_checkaba() {
		return (this.deps_checkaba);
	}
	public void setDeps_checkaba(String deps_checkaba) {
		this.deps_checkaba = deps_checkaba.trim();
	}
	//
	public String getDeps_checkaccount() {
		return (this.deps_checkaccount);
	}
	public void setDeps_checkaccount(String deps_checkaccount) {
		this.deps_checkaccount = deps_checkaccount.trim();
	}
	//
	public String getDeps_extra1() {
		return (this.deps_extra1);
	}
	public void setDeps_extra1 (String deps_extra1) {
		this.deps_extra1	= deps_extra1;
	}
	//
	public String getDeps_comments() {
		return (this.deps_comments);
	}
	public void setDeps_comments (String deps_comments) {
		this.deps_comments	= deps_comments;
	}
	//
	public String getDeps_modtime() {
		return (this.deps_modtime);
	}
	public void setDeps_modtime (String deps_modtime) {
		//PrintLog("+account_last_modified);
		int space_at	= deps_modtime.indexOf(" ");
		if (!deps_modtime.equals("") && deps_modtime.length() > 15) {
			this.deps_modtime	= (deps_modtime.substring(0,space_at)+
									" @ "+
									deps_modtime.substring(space_at+1,deps_modtime.length()));
		} else if (!deps_modtime.equals("") && deps_modtime.length() > 13) {
			this.deps_modtime	= deps_modtime.substring(0,4)+"/";
			this.deps_modtime	+= deps_modtime.substring(4,6)+"/";
			this.deps_modtime	+= deps_modtime.substring(6,8)+" @ ";
			this.deps_modtime	+= deps_modtime.substring(8,10)+":";
			this.deps_modtime	+= deps_modtime.substring(10,12)+":";
			this.deps_modtime	+= deps_modtime.substring(12,14);
		} else {
			this.deps_modtime	= deps_modtime;
		}
	}
	//
	public String getDeps_moduser() {
		return (this.deps_moduser);
	}
	public void setDeps_moduser (String deps_moduser) {
		//if (deps_moduser.length() > 15) {
		//	deps_moduser	= deps_moduser.substring(0,15);
		//}
		this.deps_moduser	= deps_moduser;
	}
	//
	public String getDeps_modfunc() {
		return (this.deps_modfunc);
	}
	public void setDeps_modfunc (String deps_modfunc) {
		//if (deps_modfunc.length() > 15) {
		//	deps_modfunc	= deps_modfunc.substring(0,15);
		//}
		this.deps_modfunc	= deps_modfunc;
	}
	//
	public void clearNulls() {
		deps_account		= (deps_account == null) ? " " : deps_account;
		deps_payee		= (deps_payee == null) ? " " : deps_payee;
		deps_checkaba		= (deps_checkaba == null) ? " " : deps_checkaba;
		deps_checkaccount	= (deps_checkaccount == null) ? " " : deps_checkaccount;
		deps_extra1		= (deps_extra1 == null) ? " " : deps_extra1;
		deps_comments		= (deps_comments == null) ? " " : deps_comments;
	}
	public void clearDetails() {
		deps_account		= "";
		deps_payee		= "";
		deps_checkaba		= "";
		deps_checkaccount	= "";
		deps_extra1		= "";
		deps_comments		= "";
		setAccount_make_copy();
	}
	public void setAccount_make_copy () {
		clearNulls();
		deps_account_o		= deps_account;
		deps_checkaba_o		= deps_checkaba;
		deps_checkaccount_o	= deps_checkaccount;
		//deps_extra1_o		= deps_extra1;
		deps_comments_o		= deps_comments;
	}
	//
	public int CheckForChanges () {
		// Returns
		//	 0 - if nothing was changed
		//	 1 - if something was changed and every thing is valid
		//	 2 - if the changes result in invalid data
		//
		int data_changed	= 0;
		clearNulls();
		PrintLog(": IN CheckForChanges ");
		//
		// Just check if any of the fields was changed
		if (deps_account_o.compareTo(deps_account) != 0) {
			data_changed	= 1;
			PrintLog("DEPS Account changed ");
		}
		if (deps_checkaba_o.compareTo(deps_checkaba) != 0) {
			data_changed	= 1;
			PrintLog("DEPS ABA on Check changed ");
		}
		if (deps_checkaccount_o.compareTo(deps_checkaccount) != 0) {
			data_changed	= 1;
			PrintLog("DEPS Account on Check changed ");
		}
		if (deps_comments_o.compareTo(deps_comments) != 0) {
			data_changed	= 1;
			PrintLog("DEPS Account on Check changed ");
		}
		if (data_changed == 0) {
			setErrorVec("depsaccount", "info.nomods");
		}
		return (data_changed);
	}
	//
	public boolean NewAcctFieldsValid () {
		boolean ret_bool = true;
		//
		if (deps_checkaba.length() < 9) {
			ret_bool	= false;
			setErrorVec("Deposit Check ABA", "error.field.required");
		}
		if (deps_checkaccount == null) {
			ret_bool	= false;
			setErrorVec("Deposit Account", "error.field.required");
		} else if (deps_checkaccount.equals("")) {
			ret_bool	= false;
			setErrorVec("Deposit Account", "error.field.required");
		}
		return (ret_bool);
	}
}
