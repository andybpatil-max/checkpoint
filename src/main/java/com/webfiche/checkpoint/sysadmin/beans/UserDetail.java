package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;
import java.text.*;
import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:30:29 $
 */

public final class UserDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	  serialVersionUID		  = 1L;
	// --------------------------------------------------- Instance Variables
	private String	actionFlag					= " ";
	private String	accessFlag					= " ";
	private String	dbUsed						= " ";
	private String	appl_date					= " ";
	private String	password_changed			= " ";
	private String	user_id						= " ";
	private String	user_fname					= " ";
	private String	user_lname					= " ";
	private String	user_pass					= " ";
	private String	user_pass_current			= "";
	private String	user_pass_1					= " ";
	private String	user_pass_2					= " ";
	private String	user_pass_date				= " ";
	private String	user_pass_date_f			= " ";
	private String	user_verify_pass			= "";
	private String	user_pass_expiry			= " ";
	private String	user_pass_expiry_f			= " ";
	private String	user_pass_lastmodified		= " ";
	private String	user_pass_lastmodified_f	= " ";
	private String	user_first_login			= " ";
	private String	user_first_login_f			= " ";
	private String	user_last_success_login		= " ";
	private String	user_last_success_login_f	= " ";
	private String	user_last_failed_login		= " ";
	private String	user_last_failed_login_f	= " ";
	private String	user_failed_attempts		= " ";
	private String	user_id_blocked				= " ";
	private String	user_must_change			= " ";
	private String	user_pa_auth				= " ";
	private String	user_pb_auth				= " ";
	private String	user_pc_auth				= " ";
	private String	user_pd_auth				= " ";
	private String	user_pe_auth				= " ";
	private String	user_pf_auth				= " ";
	private String	user_pg_auth				= " ";
	private String	user_ph_auth				= " ";
	private String	user_pi_auth				= " ";
	private String	user_pj_auth				= " ";
	private String	user_pk_auth				= " ";
	private String	user_pl_auth				= " ";
	private String	user_pm_auth				= " ";
	private String	user_pn_auth				= " ";
	private String	user_po_auth				= " ";
	private String	user_pp_auth				= " ";
	private String	user_pq_auth				= " ";
	private String	user_pr_auth				= " ";
	private String	user_ps_auth				= " ";
	private String	user_pt_auth				= " ";
	private String	user_menu_option			= "A";
	private String	user_locale					= " ";
	private String	user_extra_1				= " ";
	private String	user_extra_2				= " ";
	private String	user_extra_3				= " ";
	private String	user_extra_4				= " ";
	private String	user_extra_5				= " ";
	private String	user_display_theme			= " ";
	private String	user_last_modified			= " ";
	private String	user_mod_user				= " ";
	private String	user_mod_func				= " ";
	private String	user_modparam				= " ";
	//
	// Saved Fields
	//
	private String	user_id_o					= " ";
	private String	user_fname_o				= " ";
	private String	user_lname_o				= " ";
	private String	user_pass_o					= " ";
	private String	user_verify_pass_o			= " ";
	private String	user_pass_expiry_o			= " ";
	private String	user_pass_lastmodified_o	= " ";
	private String	user_first_login_o			= " ";
	private String	user_last_success_login_o	= " ";
	private String	user_last_failed_login_o	= " ";
	private String	user_failed_attempts_o		= " ";
	private String	user_id_blocked_o			= " ";
	private String	user_must_change_o			= " ";
	private String	user_pa_auth_o				= " ";
	private String	user_pb_auth_o				= " ";
	private String	user_pc_auth_o				= " ";
	private String	user_pd_auth_o				= " ";
	private String	user_pe_auth_o				= " ";
	private String	user_pf_auth_o				= " ";
	private String	user_pg_auth_o				= " ";
	private String	user_ph_auth_o				= " ";
	private String	user_pi_auth_o				= " ";
	private String	user_pj_auth_o				= " ";
	private String	user_pk_auth_o				= " ";
	private String	user_pl_auth_o				= " ";
	private String	user_pm_auth_o				= " ";
	private String	user_pn_auth_o				= " ";
	private String	user_po_auth_o				= " ";
	private String	user_pp_auth_o				= " ";
	private String	user_pq_auth_o				= " ";
	private String	user_pr_auth_o				= " ";
	private String	user_ps_auth_o				= " ";
	private String	user_pt_auth_o				= " ";
	private String	user_menu_option_o			= " ";
	private String	user_locale_o				= " ";
	private String	user_extra_1_o				= " ";
	private String	user_extra_2_o				= " ";
	private String	user_extra_3_o				= " ";
	private String	user_extra_4_o				= " ";
	private String	user_extra_5_o				= " ";
	private String	user_display_theme_o		= " ";
	private String	user_last_modified_o		= " ";
	private String	user_mod_user_o				= " ";
	private String	user_mod_func_o				= " ";
	//
	ArrayList<String> userGroups				= new ArrayList<String>();
	DecimalFormat	 df							= new DecimalFormat("###,##0.00");
	String			moduleName;
	private Vector<Vector<String>> errorVec		= new Vector<Vector<String>>();
	//
	PasswordValidator passwordValidator			= new PasswordValidator();
	private void PrintLog(String logMsg) {
		System.out.println (java.util.Calendar.getInstance().getTime()+
							"> UserDetail." + moduleName + logMsg);
	}
	// ----------------------------------------------------------- Properties
	//
	public void clearErrors() {
		this.errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return this.errorVec;
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	// ----------------------------------------------------------- Properties
	//
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
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
	public String getPassword_changed() {
		return (this.password_changed);
	}
	public void setPassword_changed(String password_changed) {
		this.password_changed = password_changed;
	}
	//
	public String getUser_id() {
		return (this.user_id);
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	//
	public String getUser_fname() {
		return (this.user_fname);
	}
	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}
	//
	public String getUser_lname() {
		return (this.user_lname);
	}
	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}
	//
	public String getUser_pass() {
		return (this.user_pass);
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	//
	public String getUser_pass_current() {
		return (this.user_pass_current);
	}
	public void setUser_pass_current(String user_pass_current) {
		this.user_pass_current = user_pass_current;
	}
	//
	public String getUser_pass_1() {
		return (this.user_pass_1);
	}
	public void setUser_pass_1(String user_pass_1) {
		this.user_pass_1 = user_pass_1;
	}
	//
	public String getUser_pass_2() {
		return (this.user_pass_2);
	}
	public void setUser_pass_2(String user_pass_2) {
		this.user_pass_2 = user_pass_2;
	}
	//
	public String getUser_pass_date() {
		return (this.user_pass_date);
	}
	public String getUser_pass_date_f() {
		return (this.user_pass_date_f);
	}
	public void setUser_pass_date(String user_pass_date) {
		moduleName = "> UserDetail.setUser_pass_date: ";
		if (!user_pass_date.equals("") && user_pass_date.length() > 7) {
			this.user_pass_date_f = user_pass_date.substring(4, 6) + "/";
			this.user_pass_date_f += user_pass_date.substring(6, 8) + "/";
			this.user_pass_date_f += user_pass_date.substring(0, 4);
		} else {
			this.user_pass_date_f = user_pass_date;
		}
		this.user_pass_date = user_pass_date;
		// PrintLog("User Pass Date: "+this.user_pass_date);
	}
	//
	public String getUser_verify_pass() {
		return (this.user_verify_pass);
	}
	public void setUser_verify_pass(String user_verify_pass) {
		this.user_verify_pass = user_verify_pass;
	}
	//
	public String getUser_pass_expiry() {
		return (this.user_pass_expiry);
	}
	public String getUser_pass_expiry_f() {
		return (this.user_pass_expiry_f);
	}
	public void setUser_pass_expiry(String user_pass_expiry) {
		moduleName = "> UserDetail.setUser_pass_expiry: ";
		if (!user_pass_expiry.equals("") && user_pass_expiry.length() > 7) {
			this.user_pass_expiry_f = user_pass_expiry.substring(4, 6) + "/";
			this.user_pass_expiry_f += user_pass_expiry.substring(6, 8) + "/";
			this.user_pass_expiry_f += user_pass_expiry.substring(0, 4);
		} else {
			this.user_pass_expiry_f = user_pass_expiry;
		}
		this.user_pass_expiry = user_pass_expiry;
		//PrintLog("User Pass Expiry Date: "+this.user_pass_expiry);
	}
	//
	public String getUser_pass_lastmodified() {
		return (this.user_pass_lastmodified);
	}
	public String getUser_pass_lastmodified_f() {
		return (this.user_pass_lastmodified_f);
	}
	public void setUser_pass_lastmodified(String user_pass_lastmodified) {
		int space_at = user_pass_lastmodified.indexOf(" ");
		if (!user_pass_lastmodified.equals("")
				&& user_pass_lastmodified.length() > 15) {
			this.user_pass_lastmodified_f = (user_pass_lastmodified.substring(
					0, space_at) + " @ " + user_pass_lastmodified.substring(
					space_at + 1, user_pass_lastmodified.length()));
		} else if (!user_pass_lastmodified.equals("")
				&& user_pass_lastmodified.length() > 13) {
			this.user_pass_lastmodified_f = user_pass_lastmodified.substring(0,4) + "/";
			this.user_pass_lastmodified_f += user_pass_lastmodified.substring(4, 6) + "/";
			this.user_pass_lastmodified_f += user_pass_lastmodified.substring(6, 8) + " @ ";
			this.user_pass_lastmodified_f += user_pass_lastmodified.substring(8, 10) + ":";
			this.user_pass_lastmodified_f += user_pass_lastmodified.substring(10, 12) + ":";
			this.user_pass_lastmodified_f += user_pass_lastmodified.substring(12, 14);
		} else {
			this.user_pass_lastmodified_f = user_pass_lastmodified;
		}
		this.user_pass_lastmodified = user_pass_lastmodified;
	}
	//
	public String getUser_first_login() {
		return (this.user_first_login);
	}
	public String getUser_first_login_f() {
		return (this.user_first_login_f);
	}
	public void setUser_first_login(String user_first_login) {
		int space_at = user_first_login.indexOf(" ");
		if (!user_first_login.equals("") && user_first_login.length() > 15) {
			this.user_first_login_f = (user_first_login.substring(0, space_at)
					+ " @ " + user_first_login.substring(space_at + 1,
					user_first_login.length()));
		} else if (!user_first_login.equals("")
				&& user_first_login.length() > 13) {
			this.user_first_login_f = user_first_login.substring(0, 4) + "/";
			this.user_first_login_f += user_first_login.substring(4, 6) + "/";
			this.user_first_login_f += user_first_login.substring(6, 8) + " @ ";
			this.user_first_login_f += user_first_login.substring(8, 10) + ":";
			this.user_first_login_f += user_first_login.substring(10, 12) + ":";
			this.user_first_login_f += user_first_login.substring(12, 14);
		} else {
			this.user_first_login_f = user_first_login;
		}
		this.user_first_login = user_first_login;
	}
	//
	public String getUser_last_success_login() {
		return (this.user_last_success_login);
	}
	public String getUser_last_success_login_f() {
		return (this.user_last_success_login_f);
	}
	public void setUser_last_success_login(String user_last_success_login) {
		moduleName = "> UserDetail.setUser_last_success_login: ";
		// PrintLog("Last User Success Login: "+this.user_last_success_login);
		int space_at = user_last_success_login.indexOf(" ");
		// PrintLog("Space at : "+space_at+" "+this.user_last_success_login);
		if (!user_last_success_login.equals("")
				&& user_last_success_login.length() > 15) {
			this.user_last_success_login_f = (user_last_success_login
					.substring(0, space_at) + " @ " + user_last_success_login
					.substring(space_at + 1, user_last_success_login.length()));
		} else if (!user_last_success_login.equals("")
				&& user_last_success_login.length() > 13) {
			this.user_last_success_login_f = user_last_success_login.substring(0, 4) + "/";
			this.user_last_success_login_f += user_last_success_login.substring(4, 6) + "/";
			this.user_last_success_login_f += user_last_success_login.substring(6, 8) + " @ ";
			this.user_last_success_login_f += user_last_success_login.substring(8, 10) + ":";
			this.user_last_success_login_f += user_last_success_login.substring(10, 12) + ":";
			this.user_last_success_login_f += user_last_success_login.substring(12, 14);
		} else {
			this.user_last_success_login_f = user_last_success_login;
		}
		this.user_last_success_login = user_last_success_login;
	}
	//
	public String getUser_last_failed_login() {
		return (this.user_last_failed_login);
	}
	public String getUser_last_failed_login_f() {
		return (this.user_last_failed_login_f);
	}
	public void setUser_last_failed_login(String user_last_failed_login) {
		int space_at = user_last_failed_login.indexOf(" ");
		if (!user_last_failed_login.equals("")
				&& user_last_failed_login.length() > 15) {
			this.user_last_failed_login_f = (user_last_failed_login.substring(
					0, space_at) + " @ " + user_last_failed_login.substring(
					space_at + 1, user_last_failed_login.length()));
		} else if (!user_last_failed_login.equals("")
				&& user_last_failed_login.length() > 13) {
			this.user_last_failed_login_f = user_last_failed_login.substring(0, 4) + "/";
			this.user_last_failed_login_f += user_last_failed_login.substring(4, 6) + "/";
			this.user_last_failed_login_f += user_last_failed_login.substring(6, 8) + " @ ";
			this.user_last_failed_login_f += user_last_failed_login.substring(8, 10) + ":";
			this.user_last_failed_login_f += user_last_failed_login.substring(10, 12) + ":";
			this.user_last_failed_login_f += user_last_failed_login.substring(12, 14);
		} else {
			this.user_last_failed_login_f = user_last_failed_login;
		}
		this.user_last_failed_login = user_last_failed_login;
	}
	//
	public String getUser_failed_attempts() {
		return (this.user_failed_attempts);
	}
	public void setUser_failed_attempts(String user_failed_attempts) {
		this.user_failed_attempts = user_failed_attempts;
	}
	//
	public String getUser_id_blocked() {
		return (this.user_id_blocked);
	}
	public void setUser_id_blocked(String user_id_blocked) {
		this.user_id_blocked = user_id_blocked;
	}
	//
	public String getUser_must_change() {
		return (this.user_must_change);
	}
	public void setUser_must_change(String user_must_change) {
		this.user_must_change = user_must_change;
	}
	//
	public String getUser_pa_auth() {
		return (this.user_pa_auth);
	}
	public void setUser_pa_auth(String user_pa_auth) {
		if (user_pa_auth.equals("NONE") || user_pa_auth.equals("")) {
			this.user_pa_auth = " ";
		} else {
			this.user_pa_auth = user_pa_auth;
		}
	}
	//
	public String getUser_pb_auth() {
		return (this.user_pb_auth);
	}
	public void setUser_pb_auth(String user_pb_auth) {
		if (user_pb_auth.equals("NONE") || user_pb_auth.equals("")) {
			this.user_pb_auth = " ";
		} else {
			this.user_pb_auth = user_pb_auth;
		}
	}
	//
	public String getUser_pc_auth() {
		return (this.user_pc_auth);
	}
	public void setUser_pc_auth(String user_pc_auth) {
		if (user_pc_auth.equals("NONE") || user_pc_auth.equals("")) {
			this.user_pc_auth = " ";
		} else {
			this.user_pc_auth = user_pc_auth;
		}
	}
	//
	public String getUser_pd_auth() {
		return (this.user_pd_auth);
	}
	public void setUser_pd_auth(String user_pd_auth) {
		moduleName = "> UserDetail.setUser_pd_auth: ";
		if (user_pd_auth.equals("NONE") || user_pd_auth.equals("")) {
			// PrintLog("setting to BLANK");
			this.user_pd_auth = " ";
		} else {
			this.user_pd_auth = user_pd_auth;
		}
	}
	//
	public String getUser_pe_auth() {
		return (this.user_pe_auth);
	}
	public void setUser_pe_auth(String user_pe_auth) {
		if (user_pe_auth.equals("NONE") || user_pe_auth.equals("")) {
			this.user_pe_auth = " ";
		} else {
			this.user_pe_auth = user_pe_auth;
		}
	}
	//
	public String getUser_pf_auth() {
		return (this.user_pf_auth);
	}
	public void setUser_pf_auth(String user_pf_auth) {
		if (user_pf_auth.equals("NONE") || user_pf_auth.equals("")) {
			this.user_pf_auth = " ";
		} else {
			this.user_pf_auth = user_pf_auth;
		}
	}
	//
	public String getUser_pg_auth() {
		return (this.user_pg_auth);
	}
	public void setUser_pg_auth(String user_pg_auth) {
		if (user_pg_auth.equals("NONE") || user_pg_auth.equals("")) {
			this.user_pg_auth = " ";
		} else {
			this.user_pg_auth = user_pg_auth;
		}
	}
	//
	public String getUser_ph_auth() {
		return (this.user_ph_auth);
	}
	public void setUser_ph_auth(String user_ph_auth) {
		if (user_ph_auth.equals("NONE") || user_ph_auth.equals("")) {
			this.user_ph_auth = " ";
		} else {
			this.user_ph_auth = user_ph_auth;
		}
	}
	//
	public String getUser_pi_auth() {
		return (this.user_pi_auth);
	}
	public void setUser_pi_auth(String user_pi_auth) {
		if (user_pi_auth.equals("NONE") || user_pi_auth.equals("")) {
			this.user_pi_auth = " ";
		} else {
			this.user_pi_auth = user_pi_auth;
		}
	}
	//
	public String getUser_pj_auth() {
		return (this.user_pj_auth);
	}
	public void setUser_pj_auth(String user_pj_auth) {
		if (user_pj_auth.equals("NONE") || user_pj_auth.equals("")) {
			this.user_pj_auth = " ";
		} else {
			this.user_pj_auth = user_pj_auth;
		}
	}
	//
	public String getUser_pk_auth() {
		return (this.user_pk_auth);
	}
	public void setUser_pk_auth(String user_pk_auth) {
		if (user_pk_auth.equals("NONE") || user_pk_auth.equals("")) {
			this.user_pk_auth = " ";
		} else {
			this.user_pk_auth = user_pk_auth;
		}
	}
	//
	public String getUser_pl_auth() {
		return (this.user_pl_auth);
	}
	public void setUser_pl_auth(String user_pl_auth) {
		if (user_pl_auth.equals("NONE") || user_pl_auth.equals("")) {
			this.user_pl_auth = " ";
		} else {
			this.user_pl_auth = user_pl_auth;
		}
	}
	//
	public String getUser_pm_auth() {
		return (this.user_pm_auth);
	}
	public void setUser_pm_auth(String user_pm_auth) {
		if (user_pm_auth.equals("NONE") || user_pm_auth.equals("")) {
			this.user_pm_auth = " ";
		} else {
			this.user_pm_auth = user_pm_auth;
		}
	}
	//
	public String getUser_pn_auth() {
		return (this.user_pn_auth);
	}
	public void setUser_pn_auth(String user_pn_auth) {
		if (user_pn_auth.equals("NONE") || user_pn_auth.equals("")) {
			this.user_pn_auth = " ";
		} else {
			this.user_pn_auth = user_pn_auth;
		}
	}
	//
	public String getUser_po_auth() {
		return (this.user_po_auth);
	}
	public void setUser_po_auth(String user_po_auth) {
		if (user_po_auth.equals("NONE") || user_po_auth.equals("")) {
			this.user_po_auth = " ";
		} else {
			this.user_po_auth = user_po_auth;
		}
	}
	//
	public String getUser_pp_auth() {
		return (this.user_pp_auth);
	}
	public void setUser_pp_auth(String user_pp_auth) {
		if (user_pp_auth.equals("NONE") || user_pp_auth.equals("")) {
			this.user_pp_auth = " ";
		} else {
			this.user_pp_auth = user_pp_auth;
		}
	}
	//
	public String getUser_pq_auth() {
		return (this.user_pq_auth);
	}
	public void setUser_pq_auth(String user_pq_auth) {
		if (user_pq_auth.equals("NONE") || user_pq_auth.equals("")) {
			this.user_pq_auth = " ";
		} else {
			this.user_pq_auth = user_pq_auth;
		}
	}
	//
	public String getUser_pr_auth() {
		return (this.user_pr_auth);
	}
	public void setUser_pr_auth(String user_pr_auth) {
		if (user_pr_auth.equals("NONE") || user_pr_auth.equals("")) {
			this.user_pr_auth = " ";
		} else {
			this.user_pr_auth = user_pr_auth;
		}
	}
	//
	public String getUser_ps_auth() {
		return (this.user_ps_auth);
	}
	public void setUser_ps_auth(String user_ps_auth) {
		if (user_ps_auth.equals("NONE") || user_ps_auth.equals("")) {
			this.user_ps_auth = " ";
		} else {
			this.user_ps_auth = user_ps_auth;
		}
	}
	//
	public String getUser_pt_auth() {
		return (this.user_pt_auth);
	}
	public void setUser_pt_auth(String user_pt_auth) {
		if (user_pt_auth.equals("NONE") || user_pt_auth.equals("")) {
			this.user_pt_auth = " ";
		} else {
			this.user_pt_auth = user_pt_auth;
		}
	}
	//
	public ArrayList<String> getUserGroups() {
		setUserGroups();
		return (this.userGroups);
	}
	public void setUserGroups() {
		moduleName = "> UserDetail.setUserGroups: ";
		userGroups.clear();
		userGroups.add(this.user_pa_auth);
		userGroups.add(this.user_pb_auth);
		userGroups.add(this.user_pc_auth);
		userGroups.add(this.user_pd_auth);
		userGroups.add(this.user_pe_auth);
		userGroups.add(this.user_pf_auth);
		userGroups.add(this.user_pg_auth);
		userGroups.add(this.user_ph_auth);
		userGroups.add(this.user_pi_auth);
		userGroups.add(this.user_pj_auth);
		userGroups.add(this.user_pk_auth);
		userGroups.add(this.user_pl_auth);
		userGroups.add(this.user_pm_auth);
		userGroups.add(this.user_pn_auth);
		userGroups.add(this.user_po_auth);
		userGroups.add(this.user_pp_auth);
		userGroups.add(this.user_pq_auth);
		userGroups.add(this.user_pr_auth);
		userGroups.add(this.user_ps_auth);
		userGroups.add(this.user_pt_auth);
		// PrintLog("User Auth a: "+this.user_pt_auth);
	}
	//
	public String getUser_menu_option() {
		return (this.user_menu_option);
	}
	public void setUser_menu_option(String user_menu_option) {
		this.user_menu_option = user_menu_option;
	}
	//
	public String getUser_locale() {
		return (this.user_locale);
	}
	public void setUser_locale(String user_locale) {
		this.user_locale = user_locale;
	}
	//
	public String getUser_extra_1() {
		return (this.user_extra_1);
	}
	public void setUser_extra_1(String user_extra_1) {
		this.user_extra_1 = user_extra_1;
	}
	//
	public String getUser_extra_2() {
		return (this.user_extra_2);
	}
	public void setUser_extra_2(String user_extra_2) {
		this.user_extra_2 = user_extra_2;
	}
	//
	public String getUser_extra_3() {
		return (this.user_extra_3);
	}
	public void setUser_extra_3(String user_extra_3) {
		this.user_extra_3 = user_extra_3;
	}
	//
	public String getUser_extra_4() {
		return (this.user_extra_4);
	}
	public void setUser_extra_4(String user_extra_4) {
		this.user_extra_4 = user_extra_4;
	}
	//
	public String getUser_extra_5() {
		return (this.user_extra_5);
	}
	public void setUser_extra_5(String user_extra_5) {
		this.user_extra_5 = user_extra_5;
	}
	//
	public String getUser_display_theme() {
		return (this.user_display_theme);
	}
	public void setUser_display_theme(String user_display_theme) {
		this.user_display_theme = user_display_theme;
	}
	//
	public String getUser_last_modified() {
		return (this.user_last_modified);
	}
	public void setUser_last_modified(String user_last_modified) {
		int space_at = user_last_modified.indexOf(" ");
		moduleName = "> UserDetail.setUser_last_modified: ";
		// PrintLog("Last Modified: "+user_last_modified);
		if (!user_last_modified.equals("") && user_last_modified.length() > 15) {
			this.user_last_modified = (user_last_modified.substring(0, space_at) + 
							" @ " + 
							user_last_modified.substring(space_at + 1, user_last_modified.length()));
		} else if (!user_last_modified.equals("")
				&& user_last_modified.length() > 13) {
			this.user_last_modified = user_last_modified.substring(0, 4) + "/";
			this.user_last_modified += user_last_modified.substring(4, 6) + "/";
			this.user_last_modified += user_last_modified.substring(6, 8) + " @ ";
			this.user_last_modified += user_last_modified.substring(8, 10) + ":";
			this.user_last_modified += user_last_modified.substring(10, 12) + ":";
			this.user_last_modified += user_last_modified.substring(12, 14);
		} else {
			this.user_last_modified = user_last_modified;
		}
		this.user_last_modified = user_last_modified;
		// PrintLog("Last Modified: "+this.user_last_modified);
	}

	public String getUser_mod_user() {
		return (this.user_mod_user);
	}
	public void setUser_mod_user(String user_mod_user) {
		this.user_mod_user = user_mod_user;
	}

	public String getUser_mod_func() {
		return (this.user_mod_func);
	}
	public void setUser_mod_func(String user_mod_func) {
		this.user_mod_func = user_mod_func;
	}
	//
	// Saved fields
	//
	public String getUser_id_o() {
		return (this.user_id_o);
	}
	public void setUser_id_o(String user_id_o) {
		this.user_id_o = user_id_o;
	}
	//
	public String getUser_fname_o() {
		return (this.user_fname_o);
	}
	public void setUser_fname_o(String user_fname_o) {
		this.user_fname_o = user_fname_o;
	}
	//
	public String getUser_lname_o() {
		return (this.user_lname_o);
	}
	public void setUser_lname_o(String user_lname_o) {
		this.user_lname_o = user_lname_o;
	}
	//
	public String getUser_pass_o() {
		return (this.user_pass_o);
	}
	public void setUser_pass_o(String user_pass_o) {
		this.user_pass_o = user_pass_o;
	}
	//
	public String getUser_verify_pass_o() {
		return (this.user_verify_pass_o);
	}
	public void setUser_verify_pass_o(String user_verify_pass_o) {
		this.user_verify_pass_o = user_verify_pass_o;
	}
	//
	public String getUser_pass_expiry_o() {
		return (this.user_pass_expiry_o);
	}
	public void setUser_pass_expiry_o(String user_pass_expiry_o) {
		this.user_pass_expiry_o = user_pass_expiry_o;
	}
	//
	public String getUser_pass_lastmodified_o() {
		return (this.user_pass_lastmodified_o);
	}
	public void setUser_pass_lastmodified_o(String user_pass_lastmodified_o) {
		this.user_pass_lastmodified_o = user_pass_lastmodified_o;
	}
	//
	public String getUser_first_login_o() {
		return (this.user_first_login_o);
	}
	public void setUser_first_login_o(String user_first_login_o) {
		this.user_first_login_o = user_first_login_o;
	}
	//
	public String getUser_last_success_login_o() {
		return (this.user_last_success_login_o);
	}
	public void setUser_last_success_login_o(String user_last_success_login_o) {
		this.user_last_success_login_o = user_last_success_login_o;
	}
	//
	public String getUser_last_failed_login_o() {
		return (this.user_last_failed_login_o);
	}
	public void setUser_last_failed_login_o(String user_last_failed_login_o) {
		this.user_last_failed_login_o = user_last_failed_login_o;
	}
	//
	public String getUser_failed_attempts_o() {
		return (this.user_failed_attempts_o);
	}
	public void setUser_failed_attempts_o(String user_failed_attempts_o) {
		this.user_failed_attempts_o = user_failed_attempts_o;
	}
	//
	public String getUser_id_blocked_o() {
		return (this.user_id_blocked_o);
	}
	public void setUser_id_blocked_o(String user_id_blocked_o) {
		this.user_id_blocked_o = user_id_blocked_o;
	}
	//
	public String getUser_must_change_o() {
		return (this.user_must_change_o);
	}
	public void setUser_must_change_o(String user_must_change_o) {
		this.user_must_change_o = user_must_change_o;
	}
	//
	public String getUser_pa_auth_o() {
		return (this.user_pa_auth_o);
	}
	public void setUser_pa_auth_o(String user_pa_auth_o) {
		this.user_pa_auth_o = user_pa_auth_o;
	}
	//
	public String getUser_pb_auth_o() {
		return (this.user_pb_auth_o);
	}
	public void setUser_pb_auth_o(String user_pb_auth_o) {
		this.user_pb_auth_o = user_pb_auth_o;
	}
	//
	public String getUser_pc_auth_o() {
		return (this.user_pc_auth_o);
	}
	public void setUser_pc_auth_o(String user_pc_auth_o) {
		this.user_pc_auth_o = user_pc_auth_o;
	}
	//
	public String getUser_pd_auth_o() {
		return (this.user_pd_auth_o);
	}
	public void setUser_pd_auth_o(String user_pd_auth_o) {
		this.user_pd_auth_o = user_pd_auth_o;
	}
	//
	public String getUser_pe_auth_o() {
		return (this.user_pe_auth_o);
	}
	public void setUser_pe_auth_o(String user_pe_auth_o) {
		this.user_pe_auth_o = user_pe_auth_o;
	}
	//
	public String getUser_pf_auth_o() {
		return (this.user_pf_auth_o);
	}
	public void setUser_pf_auth_o(String user_pf_auth_o) {
		this.user_pf_auth_o = user_pf_auth_o;
	}
	//
	public String getUser_pg_auth_o() {
		return (this.user_pg_auth_o);
	}
	public void setUser_pg_auth_o(String user_pg_auth_o) {
		this.user_pg_auth_o = user_pg_auth_o;
	}
	//
	public String getUser_ph_auth_o() {
		return (this.user_ph_auth_o);
	}
	public void setUser_ph_auth_o(String user_ph_auth_o) {
		this.user_ph_auth_o = user_ph_auth_o;
	}
	//
	public String getUser_pi_auth_o() {
		return (this.user_pi_auth_o);
	}
	public void setUser_pi_auth_o(String user_pi_auth_o) {
		this.user_pi_auth_o = user_pi_auth_o;
	}
	//
	public String getUser_pj_auth_o() {
		return (this.user_pj_auth_o);
	}
	public void setUser_pj_auth_o(String user_pj_auth_o) {
		this.user_pj_auth_o = user_pj_auth_o;
	}
	//
	public String getUser_pk_auth_o() {
		return (this.user_pk_auth_o);
	}
	public void setUser_pk_auth_o(String user_pk_auth_o) {
		this.user_pk_auth_o = user_pk_auth_o;
	}
	//
	public String getUser_pl_auth_o() {
		return (this.user_pl_auth_o);
	}
	public void setUser_pl_auth_o(String user_pl_auth_o) {
		this.user_pl_auth_o = user_pl_auth_o;
	}
	//
	public String getUser_pm_auth_o() {
		return (this.user_pm_auth_o);
	}
	public void setUser_pm_auth_o(String user_pm_auth_o) {
		this.user_pm_auth_o = user_pm_auth_o;
	}
	//
	public String getUser_pn_auth_o() {
		return (this.user_pn_auth_o);
	}
	public void setUser_pn_auth_o(String user_pn_auth_o) {
		this.user_pn_auth_o = user_pn_auth_o;
	}
	//
	public String getUser_po_auth_o() {
		return (this.user_po_auth_o);
	}
	public void setUser_po_auth_o(String user_po_auth_o) {
		this.user_po_auth_o = user_po_auth_o;
	}
	//
	public String getUser_pp_auth_o() {
		return (this.user_pp_auth_o);
	}
	public void setUser_pp_auth_o(String user_pp_auth_o) {
		this.user_pp_auth_o = user_pp_auth_o;
	}
	//
	public String getUser_pq_auth_o() {
		return (this.user_pq_auth_o);
	}
	public void setUser_pq_auth_o(String user_pq_auth_o) {
		this.user_pq_auth_o = user_pq_auth_o;
	}
	//
	public String getUser_pr_auth_o() {
		return (this.user_pr_auth_o);
	}
	public void setUser_pr_auth_o(String user_pr_auth_o) {
		this.user_pr_auth_o = user_pr_auth_o;
	}
	//
	public String getUser_ps_auth_o() {
		return (this.user_ps_auth_o);
	}
	public void setUser_ps_auth_o(String user_ps_auth_o) {
		this.user_ps_auth_o = user_ps_auth_o;
	}
	//
	public String getUser_pt_auth_o() {
		return (this.user_pt_auth_o);
	}
	public void setUser_pt_auth_o(String user_pt_auth_o) {
		this.user_pt_auth_o = user_pt_auth_o;
	}
	//
	public String getUser_menu_option_o() {
		return (this.user_menu_option_o);
	}
	public void setUser_menu_option_o(String user_menu_option_o) {
		this.user_menu_option_o = user_menu_option_o;
	}
	//
	public String getUser_locale_o() {
		return (this.user_locale_o);
	}
	public void setUser_locale_o(String user_locale_o) {
		this.user_locale_o = user_locale_o;
	}
	//
	public String getUser_extra_1_o() {
		return (this.user_extra_1_o);
	}
	public void setUser_extra_1_o(String user_extra_1_o) {
		this.user_extra_1_o = user_extra_1_o;
	}
	//
	public String getUser_extra_2_o() {
		return (this.user_extra_2_o);
	}
	public void setUser_extra_2_o(String user_extra_2_o) {
		this.user_extra_2_o = user_extra_2_o;
	}
	//
	public String getUser_extra_3_o() {
		return (this.user_extra_3_o);
	}
	public void setUser_extra_3_o(String user_extra_3_o) {
		this.user_extra_3_o = user_extra_3_o;
	}
	//
	public String getUser_extra_4_o() {
		return (this.user_extra_4_o);
	}
	public void setUser_extra_4_o(String user_extra_4_o) {
		this.user_extra_4_o = user_extra_4_o;
	}
	//
	public String getUser_extra_5_o() {
		return (this.user_extra_5_o);
	}
	public void setUser_extra_5_o(String user_extra_5_o) {
		this.user_extra_5_o = user_extra_5_o;
	}
	//
	public String getUser_display_theme_o() {
		return (this.user_display_theme_o);
	}
	public void setUser_display_theme_o(String user_display_theme_o) {
		this.user_display_theme_o = user_display_theme_o;
	}
	//
	public String getUser_last_modified_o() {
		return (this.user_last_modified_o);
	}
	public void setUser_last_modified_o(String user_last_modified_o) {
		this.user_last_modified_o = user_last_modified_o;
	}
	//
	public String getUser_mod_user_o() {
		return (this.user_mod_user_o);
	}
	public void setUser_mod_user_o(String user_mod_user_o) {
		this.user_mod_user_o = user_mod_user_o;
	}
	//
	public String getUser_mod_func_o() {
		return (this.user_mod_func_o);
	}
	public void setUser_mod_func_o(String user_mod_func_o) {
		this.user_mod_func_o = user_mod_func_o;
	}
	//
	public String getUser_modparam() {
		return (this.user_modparam);
	}
	public void setUser_modparam(String user_id) {
		this.user_modparam = user_id;
		// this.user_modparam.put("",user_check_num);
	}
	//
	// Other methods
	//
	public void setUser_make_copy() {
		clearNulls();
		user_id_o					= user_id;
		user_fname_o				= user_fname;
		user_lname_o				= user_lname;
		user_pass_o					= user_pass;
		// user_pass_1_o			= user_pass_1;
		// user_pass_2_o			= user_pass_2;
		// user_pass_date_o			= user_pass_date;
		user_pass_expiry_o			= user_pass_expiry;
		user_pass_lastmodified_o	= user_pass_lastmodified;
		user_first_login_o			= user_first_login;
		user_last_success_login_o	= user_last_success_login;
		user_last_failed_login_o	= user_last_failed_login;
		user_failed_attempts_o		= user_failed_attempts;
		user_id_blocked_o			= user_id_blocked;
		user_must_change_o			= user_must_change;
		user_pa_auth_o				= user_pa_auth;
		user_pb_auth_o				= user_pb_auth;
		user_pc_auth_o				= user_pc_auth;
		user_pd_auth_o				= user_pd_auth;
		user_pe_auth_o				= user_pe_auth;
		user_pf_auth_o				= user_pf_auth;
		user_pg_auth_o				= user_pg_auth;
		user_ph_auth_o				= user_ph_auth;
		user_pi_auth_o				= user_pi_auth;
		user_pj_auth_o				= user_pj_auth;
		user_pk_auth_o				= user_pk_auth;
		user_pl_auth_o				= user_pl_auth;
		user_pm_auth_o				= user_pm_auth;
		user_pn_auth_o				= user_pn_auth;
		user_po_auth_o				= user_po_auth;
		user_pp_auth_o				= user_pp_auth;
		user_pq_auth_o				= user_pq_auth;
		user_pr_auth_o				= user_pr_auth;
		user_ps_auth_o				= user_ps_auth;
		user_pt_auth_o				= user_pt_auth;
		user_menu_option_o			= user_menu_option;
		user_locale_o				= user_locale;
		user_extra_1_o				= user_extra_1;
		user_extra_2_o				= user_extra_2;
		user_extra_3_o				= user_extra_3;
		user_extra_4_o				= user_extra_4;
		user_extra_5_o				= user_extra_5;
		user_display_theme_o		= user_display_theme;
		user_last_modified_o		= user_last_modified;
		user_mod_user_o				= user_mod_user;
		user_mod_func_o				= user_mod_func;
	}
	//
	public void clearNulls() {
		user_id					= (user_id == "") ? " " : user_id;
		user_fname				= (user_fname == "") ? " " : user_fname;
		user_lname				= (user_lname == "") ? " " : user_lname;
		user_pass				= (user_pass == "") ? " " : user_pass;
		user_pass_o				= (user_pass_o == "") ? " " : user_pass_o;
		user_pass_current		= (user_pass_current == "") ? " " : user_pass_current;
		user_pass_expiry		= (user_pass_expiry == "") ? " " : user_pass_expiry;
		user_pass_lastmodified	= (user_pass_lastmodified == "") ? " " : user_pass_lastmodified;
		user_first_login		= (user_first_login == "") ? " " : user_first_login;
		user_last_success_login	= (user_last_success_login == "") ? " " : user_last_success_login;
		user_last_failed_login	= (user_last_failed_login == "") ? " " : user_last_failed_login;
		user_failed_attempts	= (user_failed_attempts == "") ? " " : user_failed_attempts;
		user_id_blocked			= (user_id_blocked == "") ? " " : user_id_blocked;
		user_must_change		= (user_must_change == "") ? " " : user_must_change;
		user_pa_auth			= (user_pa_auth == "") ? " " : user_pa_auth;
		user_pb_auth			= (user_pb_auth == "") ? " " : user_pb_auth;
		user_pc_auth			= (user_pc_auth == "") ? " " : user_pc_auth;
		user_pd_auth			= (user_pd_auth == "") ? " " : user_pd_auth;
		user_pe_auth			= (user_pe_auth == "") ? " " : user_pe_auth;
		user_pf_auth			= (user_pf_auth == "") ? " " : user_pf_auth;
		user_pg_auth			= (user_pg_auth == "") ? " " : user_pg_auth;
		user_ph_auth			= (user_ph_auth == "") ? " " : user_ph_auth;
		user_pi_auth			= (user_pi_auth == "") ? " " : user_pi_auth;
		user_pj_auth			= (user_pj_auth == "") ? " " : user_pj_auth;
		user_pk_auth			= (user_pk_auth == "") ? " " : user_pk_auth;
		user_pl_auth			= (user_pl_auth == "") ? " " : user_pl_auth;
		user_pm_auth			= (user_pm_auth == "") ? " " : user_pm_auth;
		user_pn_auth			= (user_pn_auth == "") ? " " : user_pn_auth;
		user_po_auth			= (user_po_auth == "") ? " " : user_po_auth;
		user_pp_auth			= (user_pp_auth == "") ? " " : user_pp_auth;
		user_pq_auth			= (user_pq_auth == "") ? " " : user_pq_auth;
		user_pr_auth			= (user_pr_auth == "") ? " " : user_pr_auth;
		user_ps_auth			= (user_ps_auth == "") ? " " : user_ps_auth;
		user_pt_auth			= (user_pt_auth == "") ? " " : user_pt_auth;
		user_menu_option		= (user_menu_option == "") ? " " : user_menu_option;
		user_locale				= (user_locale == "") ? " " : user_locale;
		user_extra_1			= (user_extra_1 == "") ? " " : user_extra_1;
		user_extra_2			= (user_extra_2 == "") ? " " : user_extra_2;
		user_extra_3			= (user_extra_3 == "") ? " " : user_extra_3;
		user_extra_4			= (user_extra_4 == "") ? " " : user_extra_4;
		user_extra_5			= (user_extra_5 == "") ? " " : user_extra_5;
		user_display_theme		= (user_display_theme == "") ? " " : user_display_theme;
	}
	//
	public void clearDetails() {
		user_id					= "";
		user_fname				= "";
		user_lname				= "";
		user_pass				= "";
		user_pass_1				= "";
		user_pass_2				= "";
		user_pass_date			= "";
		user_pass_expiry		= "";
		user_pass_lastmodified	= "";
		user_first_login		= "";
		user_last_success_login	= "";
		user_last_failed_login	= "";
		user_failed_attempts	= "";
		user_id_blocked			= "";
		user_must_change		= "";
		user_pa_auth			= "";
		user_pb_auth			= "";
		user_pc_auth			= "";
		user_pd_auth			= "";
		user_pe_auth			= "";
		user_pf_auth			= "";
		user_pg_auth			= "";
		user_ph_auth			= "";
		user_pi_auth			= "";
		user_pj_auth			= "";
		user_pk_auth			= "";
		user_pl_auth			= "";
		user_pm_auth			= "";
		user_pn_auth			= "";
		user_po_auth			= "";
		user_pp_auth			= "";
		user_pq_auth			= "";
		user_pr_auth			= "";
		user_ps_auth			= "";
		user_pt_auth			= "";
		user_menu_option		= "";
		user_locale				= "";
		user_extra_1			= "";
		user_extra_2			= "";
		user_extra_3			= "";
		user_extra_4			= "";
		user_extra_5			= "";
		user_display_theme		= "";
		user_last_modified		= "";
		user_mod_user			= "";
		user_mod_func			= "";
		setUser_make_copy();
	}
	//
	public int CheckForChanges() {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		moduleName	= "CheckForChanges: ";
		int data_changed		= 0;
		// int date_yyyy		= 0;
		// int date_mm			= 0;
		// int date_dd			= 0;
		String temp_pass		= null;
		// ValiDate vDate		= new ValiDate();
		boolean errors_found	= false;
		//boolean lower_alpha	= false;
		//boolean upper_alpha	= false;
		//boolean number_digit	= false;
		// boolean other_char	= false;
		//
		// Just check if any of the following fields was changed
		clearNulls();
		if (user_pass_current == null) {
			user_pass_current	= " ";
		}
		if (user_pass_o == null) {
			user_pass_o	= " ";
		}
		//PrintLog("Old Password : "+this.user_pass_o+" New Password : "+this.user_pass_current);
		//PrintLog("New Password : "+this.user_pass_current+" Verify Password : "+this.user_verify_pass);
		clearErrors();
		this.password_changed	= "N";
		if (!user_pass_current.equals(user_pass_o)) {
			//PrintLog("Password has Changed ");
			if (user_pass_current.indexOf(this.user_id)>0) {
				setErrorVec("oldpadd", "error.user.nouserid");
				//PrintLog("Username in Password ");
				errors_found = true;
			} else {
				try {
					temp_pass = com.webfiche.checkpoint.sysadmin.service.SysadPasswordUtil.getInstance().encrypt(user_pass_current);
					//PrintLog("New Password encrypted: "+temp_pass);
					//PrintLog("Old PWs : "+this.user_pass_1+" New Password : "+temp_pass);
				} catch (Throwable t) {
					PrintLog("Password failed :" + t);
				}
				if ((this.user_pass_current.equals(this.user_pass))
//					|| (temp_pass.equals(this.user_pass_1))
//					|| (temp_pass.equals(this.user_pass_2))) {
					|| (user_pass_1.indexOf(temp_pass)>=0)) {
					setErrorVec("oldpadd", "error.user.oldpass");
					errors_found = true;
				} else {
					// if (!user_pass_current.equals("")) {
					if (user_pass_current != null) {
						data_changed = 1;
						this.password_changed = "Y";
						//PrintLog("Set Password Changed to Y: ");
						if (passwordValidator.validate(this.user_pass_current)) {
							//if ((lower_alpha == true) && (upper_alpha == true)
							//		&& (number_digit == true)) {
							if (!user_pass_current.equals(user_verify_pass)) {
								setErrorVec("passVerify", "error.password.mismatch");
								errors_found = true;
							} else {
								setUser_pass_date(ValiDate.getTodays_date());
								setUser_pass_expiry(ValiDate.addDays(90));
								// this.user_extra_1 = this.user_pass_current;
								this.password_changed = "Y";
								//PrintLog("Password Expires: "+this.user_pass_expiry);
							}
						} else {
							data_changed = 2;
							setErrorVec("password", "error.user.passnotaA0");
							errors_found = true;
						}
					}
				}
			}
		}
		if (!user_fname.equals(user_fname_o)) {
			data_changed = 1;
		}
		if (!user_lname.equals(user_lname_o)) {
			data_changed = 1;
		}
		if (!user_pa_auth.equals(user_pa_auth_o)) {
			data_changed = 1;
		}
		if (!user_pb_auth.equals(user_pb_auth_o)) {
			data_changed = 1;
		}
		if (!user_pc_auth.equals(user_pc_auth_o)) {
			data_changed = 1;
		}
		if (!user_pd_auth.equals(user_pd_auth_o)) {
			data_changed = 1;
		}
		if (!user_pe_auth.equals(user_pe_auth_o)) {
			data_changed = 1;
		}
		if (!user_pf_auth.equals(user_pf_auth_o)) {
			data_changed = 1;
		}
		if (!user_pg_auth.equals(user_pg_auth_o)) {
			data_changed = 1;
		}
		if (!user_ph_auth.equals(user_ph_auth_o)) {
			data_changed = 1;
		}
		if (!user_pi_auth.equals(user_pi_auth_o)) {
			data_changed = 1;
		}
		if (!user_pj_auth.equals(user_pj_auth_o)) {
			data_changed = 1;
		}
		if (!user_pk_auth.equals(user_pk_auth_o)) {
			data_changed = 1;
		}
		if (!user_pl_auth.equals(user_pl_auth_o)) {
			data_changed = 1;
		}
		if (!user_pm_auth.equals(user_pm_auth_o)) {
			data_changed = 1;
		}
		if (!user_pn_auth.equals(user_pn_auth_o)) {
			data_changed = 1;
		}
		if (!user_po_auth.equals(user_po_auth_o)) {
			data_changed = 1;
		}
		if (!user_pp_auth.equals(user_pp_auth_o)) {
			data_changed = 1;
		}
		if (!user_pq_auth.equals(user_pq_auth_o)) {
			data_changed = 1;
		}
		if (!user_pr_auth.equals(user_pr_auth_o)) {
			data_changed = 1;
		}
		if (!user_ps_auth.equals(user_ps_auth_o)) {
			data_changed = 1;
		}
		if (!user_pt_auth.equals(user_pt_auth_o)) {
			data_changed = 1;
		}
		if (!user_menu_option.equals(user_menu_option_o)) {
			data_changed = 1;
		}
		if (!user_locale.equals(user_locale_o)) {
			data_changed = 1;
		}
		if (!user_display_theme.equals(user_display_theme_o)) {
			data_changed = 1;
		}
		if (data_changed == 0) {
			setErrorVec("userid", "info.nomods");
		}
		if (errors_found == true) {
			data_changed = 2;
		}
		return (data_changed);
	}
	//
	public void PrintallFields() {
		moduleName = "PrintallFields: ";
		PrintLog("user_id\t" + user_id);
		PrintLog("user_fname\t" + user_fname);
		PrintLog("user_lname\t" + user_lname);
		PrintLog("user_pass_date\t" + user_pass_date);
		PrintLog("user_pass_date_f\t" + user_pass_date_f);
		PrintLog("user_verify_pass\t" + user_verify_pass);
		PrintLog("user_pass_expiry\t" + user_pass_expiry);
		PrintLog("user_pass_expiry_f\t" + user_pass_expiry_f);
		PrintLog("user_pass_lastmodified\t" + user_pass_lastmodified);
		PrintLog("user_pass_lastmodified_f\t" + user_pass_lastmodified_f);
		PrintLog("user_first_login\t" + user_first_login);
		PrintLog("user_first_login_f\t" + user_first_login_f);
		PrintLog("user_last_success_login\t" + user_last_success_login);
		PrintLog("user_last_success_login_f\t" + user_last_success_login_f);
		PrintLog("user_last_failed_login\t" + user_last_failed_login);
		PrintLog("user_last_failed_login_f\t" + user_last_failed_login_f);
		PrintLog("user_failed_attempts\t" + user_failed_attempts);
		PrintLog("user_id_blocked\t" + user_id_blocked);
		PrintLog("user_must_change\t" + user_must_change);
		PrintLog("user_pa_auth\t" + user_pa_auth);
		PrintLog("user_pc_auth\t" + user_pc_auth);
		PrintLog("user_menu_option\t" + user_menu_option);
		PrintLog("user_last_modified\t" + user_last_modified);
		PrintLog("user_mod_user\t" + user_mod_user);
		PrintLog("user_mod_func\t" + user_mod_func);
		PrintLog("user_modparam\t" + user_modparam);
	}
	//
	public boolean NewUserFieldsValid() {
		moduleName = "NewUserFieldsValid: ";
		boolean ret_bool = true;
		//PrintLog("New Password : " + this.user_pass_current +
		//		" Verify Password : " + this.user_verify_pass);
		if (user_pass_current.indexOf(this.user_id)>0) {
			setErrorVec("oldpadd", "error.user.nouserid");
			ret_bool = false;
		} else {
			if (passwordValidator.validate(this.user_pass_current)) {
				if (!user_pass_current.equals(user_verify_pass)) {
					setErrorVec("passVerify", "error.password.mismatch");
					ret_bool = false;
				} else {
					setUser_pass_date(ValiDate.getTodays_date());
					setUser_pass_expiry(ValiDate.addDays(90));
					// this.user_extra_1 = this.user_pass_current;
					this.password_changed = "Y";
					// PrintLog("Password Expires: "+this.user_pass_expiry);
				}
			} else {
				setErrorVec("invpword", "error.user.passnotaA0");
				ret_bool = false;
			}
		}
		return (ret_bool);
	}
}
