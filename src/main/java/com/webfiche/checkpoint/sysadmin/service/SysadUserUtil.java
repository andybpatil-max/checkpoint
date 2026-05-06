package com.webfiche.checkpoint.sysadmin.service;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;
//import org.apache.struts.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.config.AppProperties;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.service.*;

@Service
public class SysadUserUtil {
	private String		className  = "> SysadUserUtil.";
	private String		moduleName = "";
	@Autowired AppProperties appProperties;
	// ArrayList<String> paramArray;
	ArrayList<UserDetail> userBeans  = new ArrayList<UserDetail>();
	String				param	  = "";
	int				   row_count  = 0;

	public SysadUserUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public ArrayList<String> GetUsersList(Connection dbConn,
			String current_user) throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "GetUsersList: ";
		String temp = "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> user_list = new ArrayList<String>();
		user_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT USER_ID from econtroller_users ORDER by USER_ID");
		// Setup the SELECT statement.
		// PrintLog(": Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(": Execute In While");
			temp = result.getString("USER_ID");
			if (current_user.equals("webfiche")) {
				user_list.add(temp);
			} else {
				if (!temp.equals("webfiche")) {
					user_list.add(temp);
				}
			}
		}
		statement.close();
		result.close();
		return (user_list);
	}
	//
	public TreeMap<String,String> GetUserIDNameList(Connection dbConn) throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "GetUserIDNameList: ";
		String tempKey		= "";
		String tempValue	= "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		TreeMap<String,String> userList = new TreeMap<String,String>();
		userList.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT USER_ID, USER_FNAME, USER_LNAME from econtroller_users ORDER by USER_ID");
		//PrintLog(": Execute "+sql);
		/*
		sql.append("SELECT DISTINCT FOLDERNAME, FOLDERDESC from folders " + "ORDER by FOLDERNAME ");
		// PrintLog(" "+appl_date+" SQL ---> "+sql);
		while (result.next()) {
			tempKey		= result.getString("FOLDERNAME");
			tempValue	= result.getString("FOLDERDESC");
			folderList.put(tempKey, tempValue);
		}
		 * */
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(": Execute In While");
			tempKey		= result.getString("USER_ID");
			tempValue	= result.getString("USER_FNAME").trim() + " " + 
						  result.getString("USER_LNAME").trim();
			userList.put(tempKey, tempValue);
		}
		statement.close();
		result.close();
		return (userList);
	}
	//
	public ArrayList<String> GetUsersLogList(Connection dbConn,
			String current_user) throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "GetUsersLogList: ";
		String temp = "";
		if (dbConn == null)
			PrintLog(": dbConn is Null");
		ArrayList<String> user_list = new ArrayList<String>();
		user_list.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT USER_ID from econtroller_users_log ORDER by USER_ID");
		// Setup the SELECT statement.
		// PrintLog(": Execute "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(": Execute In While");
			temp = result.getString("USER_ID");
			if (current_user.equals("webfiche")) {
				user_list.add(temp);
			} else {
				if (!temp.equals("webfiche")) {
					user_list.add(temp);
				}
			}
		}
		statement.close();
		result.close();
		return (user_list);
	}
	//
	public boolean UserExists(Connection dbConn, String user_id)
			throws Exception {
		// Called by the Add module with the user key
		// can be used to check the existemce of P, M or F
		// -----------------------------------------------
		className  = "> SysadUserUtil.";
		moduleName = "UserExists: ";
		// String temp_1 = "";
		param = "WHERE USER_ID='" + user_id + "' ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID from econtroller_users ");
		sql.append(param);
		// PrintLog(": User Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// temp_1 = result.getString("USER_ID");
			result.getString("USER_ID");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public boolean UserNameExists(Connection dbConn, String user_id,
			String userFName, String userLName) throws Exception {
		// Called by the Add module with the user key
		// can be used to check the existemce of P, M or F
		// -----------------------------------------------
		className  = "> SysadUserUtil.";
		moduleName = "UserExists: ";
		// String temp_1 = "";
		param = ("WHERE USER_ID<>'" + user_id + "' AND USER_FNAME='"
				+ userFName + "' AND USER_LNAME='" + userLName + "' ");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID from econtroller_users ");
		sql.append(param);
		// PrintLog(": User Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			result.getString("USER_ID");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	// Called by LogonAction
	//
	public int GetUser(Connection dbConn, UserSelector userSelector,
						String user_id, String user_pass) throws Exception {
		className	= "> SysadUserUtil.";
		moduleName	= "GetUser: ";
		param		= "WHERE USER_ID='" + user_id + "' AND USER_PASS='" + user_pass + "' ";
		row_count	= GetUserRows(dbConn, userSelector);
		return (row_count);
	}
	//
	// Called by ModifyUser/Inquiry User Details
	//
	public int GetUserRows(Connection dbConn, UserSelector userSelector,
			String user_id) throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "GetUserRows: ";
		param = "WHERE USER_ID='" + user_id + "'";
		String accessFlag = userSelector.getAccessFlag();
		String dbUsed = userSelector.getDbUsed();
		if (dbUsed.equals("ORACLE")) {
			if (accessFlag != null) {
				if (accessFlag.equals("inq")) {
					;
				} else {
					param += " for UPDATE nowait ";
					dbConn.setAutoCommit(false);
				}
			} else {
				param += " for UPDATE nowait ";
				dbConn.setAutoCommit(false);
			}
		}
		row_count = GetUserRows(dbConn, userSelector);
		userSelector.setRowCount(row_count + "");
		return (row_count);
	}
	//
	public int GetUserRows(Connection dbConn, UserSelector userSelector)
			throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "GetUserRows: ";
		String temp = "";
		if (param.equals(""))
			// GetParams (userSelector);
			param = userSelector.GetParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT USER_ID, ");
		sql.append("USER_FNAME, ");
		sql.append("USER_LNAME, ");
		sql.append("USER_PASS, ");
		sql.append("USER_PASS_1, ");
		sql.append("USER_PASS_2, ");
		sql.append("USER_PASS_DATE, ");
		sql.append("USER_PASS_EXPIRY, ");
		sql.append("USER_PASS_LASTMODIFIED, ");
		sql.append("USER_FIRST_LOGIN, ");
		sql.append("USER_LAST_SUCCESS_LOGIN, ");
		sql.append("USER_LAST_FAILED_LOGIN, ");
		sql.append("USER_FAILED_ATTEMPTS, ");
		sql.append("USER_ID_BLOCKED, ");
		sql.append("USER_MUST_CHANGE, ");
		sql.append("USER_PA_AUTH, ");
		sql.append("USER_PB_AUTH, ");
		sql.append("USER_PC_AUTH, ");
		sql.append("USER_PD_AUTH, ");
		sql.append("USER_PE_AUTH, ");
		sql.append("USER_PF_AUTH, ");
		sql.append("USER_PG_AUTH, ");
		sql.append("USER_PH_AUTH, ");
		sql.append("USER_PI_AUTH, ");
		sql.append("USER_PJ_AUTH, ");
		sql.append("USER_PK_AUTH, ");
		sql.append("USER_PL_AUTH, ");
		sql.append("USER_PM_AUTH, ");
		sql.append("USER_PN_AUTH, ");
		sql.append("USER_PO_AUTH, ");
		sql.append("USER_PP_AUTH, ");
		sql.append("USER_PQ_AUTH, ");
		sql.append("USER_PR_AUTH, ");
		sql.append("USER_PS_AUTH, ");
		sql.append("USER_PT_AUTH, ");
		sql.append("USER_MENU_OPTION, ");
		sql.append("USER_LOCALE, ");
		sql.append("USER_EXTRA_1, ");
		sql.append("USER_EXTRA_2, ");
		sql.append("USER_EXTRA_3, ");
		sql.append("USER_EXTRA_4, ");
		sql.append("USER_EXTRA_5, ");
		sql.append("USER_DISPLAY_THEME, ");
		sql.append("USER_LAST_MODIFIED, ");
		sql.append("USER_MOD_USER, ");
		sql.append("USER_MOD_FUNC ");
		sql.append("FROM econtroller_users ");
		sql.append(param);
		//PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			UserDetail userDetail = new UserDetail();
			// Here add the fields tp the UserDetail bean
			userDetail.setUser_id(result.getString("USER_ID"));
			userDetail.setUser_fname(result.getString("USER_FNAME"));
			userDetail.setUser_lname(result.getString("USER_LNAME"));
			userDetail.setUser_pass(result.getString("USER_PASS"));
			userDetail.setUser_pass_current(result.getString("USER_PASS"));
			userDetail.setUser_pass_1(result.getString("USER_PASS_1"));
			userDetail.setUser_pass_2(result.getString("USER_PASS_2"));
			userDetail.setUser_pass_date(result.getString("USER_PASS_DATE"));
			userDetail.setUser_pass_expiry(result.getString("USER_PASS_EXPIRY"));
			userDetail.setUser_pass_lastmodified(result.getString("USER_PASS_LASTMODIFIED"));
			userDetail.setUser_first_login(result.getString("USER_FIRST_LOGIN"));
			userDetail.setUser_last_success_login(result.getString("USER_LAST_SUCCESS_LOGIN"));
			userDetail.setUser_last_failed_login(result.getString("USER_LAST_FAILED_LOGIN"));
			userDetail.setUser_failed_attempts(result.getString("USER_FAILED_ATTEMPTS"));
			userDetail.setUser_id_blocked(result.getString("USER_ID_BLOCKED"));
			userDetail.setUser_must_change(result.getString("USER_MUST_CHANGE"));
			userDetail.setUser_pa_auth(result.getString("USER_PA_AUTH"));
			userDetail.setUser_pb_auth(result.getString("USER_PB_AUTH"));
			userDetail.setUser_pc_auth(result.getString("USER_PC_AUTH"));
			userDetail.setUser_pd_auth(result.getString("USER_PD_AUTH"));
			userDetail.setUser_pe_auth(result.getString("USER_PE_AUTH"));
			userDetail.setUser_pf_auth(result.getString("USER_PF_AUTH"));
			userDetail.setUser_pg_auth(result.getString("USER_PG_AUTH"));
			userDetail.setUser_ph_auth(result.getString("USER_PH_AUTH"));
			userDetail.setUser_pi_auth(result.getString("USER_PI_AUTH"));
			userDetail.setUser_pj_auth(result.getString("USER_PJ_AUTH"));
			userDetail.setUser_pk_auth(result.getString("USER_PK_AUTH"));
			userDetail.setUser_pl_auth(result.getString("USER_PL_AUTH"));
			userDetail.setUser_pm_auth(result.getString("USER_PM_AUTH"));
			userDetail.setUser_pn_auth(result.getString("USER_PN_AUTH"));
			userDetail.setUser_po_auth(result.getString("USER_PO_AUTH"));
			userDetail.setUser_pp_auth(result.getString("USER_PP_AUTH"));
			userDetail.setUser_pq_auth(result.getString("USER_PQ_AUTH"));
			userDetail.setUser_pr_auth(result.getString("USER_PR_AUTH"));
			userDetail.setUser_ps_auth(result.getString("USER_PS_AUTH"));
			userDetail.setUser_pt_auth(result.getString("USER_PT_AUTH"));
			userDetail.setUser_menu_option(result.getString("USER_MENU_OPTION"));
			userDetail.setUser_locale(result.getString("USER_LOCALE"));
			userDetail.setUser_extra_1(result.getString("USER_EXTRA_1"));
			userDetail.setUser_extra_2(result.getString("USER_EXTRA_2"));
			userDetail.setUser_extra_3(result.getString("USER_EXTRA_3"));
			userDetail.setUser_extra_4(result.getString("USER_EXTRA_4"));
			userDetail.setUser_extra_5(result.getString("USER_EXTRA_5"));
			userDetail.setUser_display_theme(result.getString("USER_DISPLAY_THEME"));
			temp = result.getString("USER_LAST_MODIFIED");
			// PrintLog("TS: "+temp);
			//PrintLog("User Pass date: "+userDetail.getUser_pass_date());
			userDetail.setUser_last_modified(temp);
			userDetail.setUser_mod_user(result.getString("USER_MOD_USER"));
			userDetail.setUser_mod_func(result.getString("USER_MOD_FUNC"));
			userDetail.setUser_modparam(result.getString("USER_ID"));
			userBeans.add(userDetail);
			row_count++;
		}
		userSelector.setUserrows(userBeans);
		statement.close();
		result.close();
		userSelector.setRowCount(row_count + "");
		return (row_count);
	}

	public int GetUserLogRows(Connection dbConn, UserSelector userSelector)
			throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "GetUserLogRows: ";
		if (param.equals(""))
			// GetLogParams (userSelector);
			param = userSelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT USER_ID, ");
		sql.append("USER_FNAME, ");
		sql.append("USER_LNAME, ");
		sql.append("ULOG_PASS, ");
		sql.append("ULOG_PASS_1, ");
		sql.append("ULOG_PASS_2, ");
		sql.append("ULOG_PASS_DATE, ");
		sql.append("ULOG_PASS_EXPIRY, ");
		sql.append("ULOG_PASS_LASTMODIFIED, ");
		sql.append("ULOG_FIRST_LOGIN, ");
		sql.append("ULOG_LAST_SUCCESS_LOGIN, ");
		sql.append("ULOG_LAST_FAILED_LOGIN, ");
		sql.append("ULOG_FAILED_ATTEMPTS, ");
		sql.append("ULOG_ID_BLOCKED, ");
		sql.append("ULOG_MUST_CHANGE, ");
		sql.append("ULOG_PA_AUTH, ");
		sql.append("ULOG_PB_AUTH, ");
		sql.append("ULOG_PC_AUTH, ");
		sql.append("ULOG_PD_AUTH, ");
		sql.append("ULOG_PE_AUTH, ");
		sql.append("ULOG_PF_AUTH, ");
		sql.append("ULOG_PG_AUTH, ");
		sql.append("ULOG_PH_AUTH, ");
		sql.append("ULOG_PI_AUTH, ");
		sql.append("ULOG_PJ_AUTH, ");
		sql.append("ULOG_PK_AUTH, ");
		sql.append("ULOG_PL_AUTH, ");
		sql.append("ULOG_PM_AUTH, ");
		sql.append("ULOG_PN_AUTH, ");
		sql.append("ULOG_PO_AUTH, ");
		sql.append("ULOG_PP_AUTH, ");
		sql.append("ULOG_PQ_AUTH, ");
		sql.append("ULOG_PR_AUTH, ");
		sql.append("ULOG_PS_AUTH, ");
		sql.append("ULOG_PT_AUTH, ");
		sql.append("ULOG_MENU_OPTION, ");
		sql.append("ULOG_LOCALE, ");
		sql.append("ULOG_EXTRA_1, ");
		sql.append("ULOG_EXTRA_2, ");
		sql.append("ULOG_EXTRA_3, ");
		sql.append("ULOG_EXTRA_4, ");
		sql.append("ULOG_EXTRA_5, ");
		sql.append("ULOG_DISPLAY_THEME, ");
		sql.append("ULOG_LAST_MODIFIED, ");
		sql.append("ULOG_MOD_USER, ");
		sql.append("ULOG_MOD_FUNC ");
		sql.append("FROM econtroller_users_log ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			// PrintLog("Row Count .................."+row_count);
			UserDetail userDetail = new UserDetail();
			// Here add the fields tp the UserDetail bean
			userDetail.setUser_id(result.getString("USER_ID"));
			userDetail.setUser_fname(result.getString("USER_FNAME"));
			userDetail.setUser_lname(result.getString("USER_LNAME"));
			userDetail.setUser_pass(result.getString("ULOG_PASS"));
			userDetail.setUser_pass(result.getString("ULOG_PASS_1"));
			userDetail.setUser_pass(result.getString("ULOG_PASS_2"));
			userDetail.setUser_pass(result.getString("ULOG_PASS_DATE"));
			userDetail.setUser_pass_expiry(result.getString("ULOG_PASS_EXPIRY"));
			userDetail.setUser_pass_lastmodified(result.getString("ULOG_PASS_LASTMODIFIED"));
			userDetail.setUser_first_login(result.getString("ULOG_FIRST_LOGIN"));
			userDetail.setUser_last_success_login(result.getString("ULOG_LAST_SUCCESS_LOGIN"));
			userDetail.setUser_last_failed_login(result.getString("ULOG_LAST_FAILED_LOGIN"));
			userDetail.setUser_failed_attempts(result.getString("ULOG_FAILED_ATTEMPTS"));
			userDetail.setUser_id_blocked(result.getString("ULOG_ID_BLOCKED"));
			userDetail.setUser_must_change(result.getString("ULOG_MUST_CHANGE"));
			// PrintLog("ULOG_MUST_CHANGE ..................");
			userDetail.setUser_pa_auth(result.getString("ULOG_PA_AUTH"));
			// PrintLog("ULOG_PA_AUTH ..................");
			userDetail.setUser_pb_auth(result.getString("ULOG_PB_AUTH"));
			userDetail.setUser_pc_auth(result.getString("ULOG_PC_AUTH"));
			userDetail.setUser_pd_auth(result.getString("ULOG_PD_AUTH"));
			userDetail.setUser_pe_auth(result.getString("ULOG_PE_AUTH"));
			userDetail.setUser_pf_auth(result.getString("ULOG_PF_AUTH"));
			userDetail.setUser_pg_auth(result.getString("ULOG_PG_AUTH"));
			userDetail.setUser_ph_auth(result.getString("ULOG_PH_AUTH"));
			userDetail.setUser_pi_auth(result.getString("ULOG_PI_AUTH"));
			userDetail.setUser_pj_auth(result.getString("ULOG_PJ_AUTH"));
			userDetail.setUser_pk_auth(result.getString("ULOG_PK_AUTH"));
			userDetail.setUser_pl_auth(result.getString("ULOG_PL_AUTH"));
			userDetail.setUser_pm_auth(result.getString("ULOG_PM_AUTH"));
			userDetail.setUser_pn_auth(result.getString("ULOG_PN_AUTH"));
			userDetail.setUser_po_auth(result.getString("ULOG_PO_AUTH"));
			userDetail.setUser_pp_auth(result.getString("ULOG_PP_AUTH"));
			userDetail.setUser_pq_auth(result.getString("ULOG_PQ_AUTH"));
			userDetail.setUser_pr_auth(result.getString("ULOG_PR_AUTH"));
			userDetail.setUser_ps_auth(result.getString("ULOG_PS_AUTH"));
			userDetail.setUser_pt_auth(result.getString("ULOG_PT_AUTH"));
			userDetail.setUser_menu_option(result.getString("ULOG_MENU_OPTION"));
			// PrintLog("ULOG_MENU_OPTION ..................");
			userDetail.setUser_locale(result.getString("ULOG_LOCALE"));
			userDetail.setUser_extra_1(result.getString("ULOG_EXTRA_1"));
			userDetail.setUser_extra_2(result.getString("ULOG_EXTRA_2"));
			userDetail.setUser_extra_3(result.getString("ULOG_EXTRA_3"));
			userDetail.setUser_extra_4(result.getString("ULOG_EXTRA_4"));
			userDetail.setUser_extra_5(result.getString("ULOG_EXTRA_5"));
			userDetail.setUser_display_theme(result.getString("ULOG_DISPLAY_THEME"));
			userDetail.setUser_last_modified(result.getString("ULOG_LAST_MODIFIED"));
			userDetail.setUser_mod_user(result.getString("ULOG_MOD_USER"));
			userDetail.setUser_mod_func(result.getString("ULOG_MOD_FUNC"));
			userDetail.setUser_modparam(result.getString("USER_ID"));
			userBeans.add(userDetail);
			// userDetail.PrintallFields();
			row_count++;
		}
		userSelector.setUserrows(userBeans);
		statement.close();
		result.close();
		userSelector.setRowCount(row_count + "");
		return (row_count);
	}

	public int InsertUpdateUser(Connection dbConn, UserDetail userDetail,
			String user_id_arg, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		className		= "> SysadUserUtil.";
		moduleName		= "InsertUpdateUser: ";
		// PrintLog(": In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement	= null;
		int sizePastPw	= 0;
		int numPastPw	= appProperties.getMaxPastPw();
		// int ret_val	= 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// ValiDate vDate				= new ValiDate();
		userDetail.clearNulls();
		String mod_func					= "";
		String user_pass				= " ";
		String user_password			= " ";
		String user_pass_1				= " ";
		String user_pass_2				= " ";
		String user_pass_date			= " ";
		String user_pass_lastmodified	= " ";
		String user_extra_1				= " ";
		String user_must_change			= userDetail.getUser_must_change();
		String password_changed			= userDetail.getPassword_changed();
		// PrintLog(": In Insert/Update Acct Password_changed "+password_changed);
		String dbUsed					= userDetail.getDbUsed();
		String user_id					= userDetail.getUser_id();
		String user_fname				= userDetail.getUser_fname();
		String user_lname				= userDetail.getUser_lname();
		DateFormat new_fmt				= DateFormat.getInstance();
		new_fmt	= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		if (password_changed.equals("Y")) {
			//user_pass_2		= userDetail.getUser_pass_2();
			user_password	= userDetail.getUser_pass_current();
			user_pass		= com.webfiche.checkpoint.sysadmin.service.SysadPasswordUtil.getInstance().encrypt(user_password);
			sizePastPw		= numPastPw * user_pass.length();
			user_pass_1		= userDetail.getUser_pass() + userDetail.getUser_pass_1().trim();
			if (user_pass_1.length()>sizePastPw) {
				user_pass_1		= userDetail.getUser_pass_1().substring(0, sizePastPw);
			}
			user_pass_date	= ValiDate.getTodays_date();
			user_pass_lastmodified = new_fmt.format(java.util.Calendar.getInstance().getTime());
			user_extra_1	= " ";
			//PrintLog("user_id: " + user_id + " User Id Arg: " + user_id_arg);
			if (user_id.equals(user_id_arg)) {
				user_must_change	= " ";
			} else {
				// user_must_change = userDetail.getUser_must_change();
				user_must_change	= "Y";
			}
		} else {
			user_pass		= userDetail.getUser_pass();
			user_pass_1		= userDetail.getUser_pass_1();
			user_pass_2		= userDetail.getUser_pass_2();
			user_pass_date	= userDetail.getUser_pass_date();
			user_pass_lastmodified = userDetail.getUser_pass_lastmodified();
			user_extra_1	= userDetail.getUser_extra_1();
			user_must_change= userDetail.getUser_must_change();
		}
		String user_first_login	= userDetail.getUser_first_login();
		// PrintLog(": User First Login "+user_first_login);
		String user_last_success_login = userDetail
				.getUser_last_success_login();
		// PrintLog(": User First Login "+user_first_login);
		String user_pass_expiry = userDetail.getUser_pass_expiry();
		String user_last_failed_login = userDetail.getUser_last_failed_login();
		String user_failed_attempts = userDetail.getUser_failed_attempts();
		String user_id_blocked = userDetail.getUser_id_blocked();
		if (password_changed.equals("Y")) {
			user_id_blocked = "ACTIVE";
		}
		String user_pa_auth = userDetail.getUser_pa_auth();
		String user_pb_auth = userDetail.getUser_pb_auth();
		String user_pc_auth = userDetail.getUser_pc_auth();
		String user_pd_auth = userDetail.getUser_pd_auth();
		String user_pe_auth = userDetail.getUser_pe_auth();
		String user_pf_auth = userDetail.getUser_pf_auth();
		String user_pg_auth = userDetail.getUser_pg_auth();
		String user_ph_auth = userDetail.getUser_ph_auth();
		String user_pi_auth = userDetail.getUser_pi_auth();
		String user_pj_auth = userDetail.getUser_pj_auth();
		String user_pk_auth = userDetail.getUser_pk_auth();
		String user_pl_auth = userDetail.getUser_pl_auth();
		String user_pm_auth = userDetail.getUser_pm_auth();
		String user_pn_auth = userDetail.getUser_pn_auth();
		String user_po_auth = userDetail.getUser_po_auth();
		String user_pp_auth = userDetail.getUser_pp_auth();
		String user_pq_auth = userDetail.getUser_pq_auth();
		String user_pr_auth = userDetail.getUser_pr_auth();
		String user_ps_auth = userDetail.getUser_ps_auth();
		String user_pt_auth = userDetail.getUser_pt_auth();
		// String user_menu_option = userDetail.getUser_menu_option();
		// String user_locale = userDetail.getUser_locale();
		// String user_extra_2 = userDetail.getUser_extra_2();
		// String user_extra_3 = userDetail.getUser_extra_3();
		// String user_extra_4 = userDetail.getUser_extra_4();
		// String user_extra_5 = userDetail.getUser_extra_5();
		// String user_display_theme = userDetail.getUser_display_theme();
		String user_menu_option = " ";
		String user_locale = " ";
		String user_extra_2 = " ";
		String user_extra_3 = " ";
		String user_extra_4 = " ";
		String user_extra_5 = " ";
		String user_display_theme = " ";
		// String user_last_modified = userDetail.getUser_last_modified();
		// String user_mod_user = userDetail.getUser_mod_user();
		// String user_mod_func = userDetail.getUser_mod_func();
		if (ins_or_upd == 1) {
			mod_func = "Add User";
			user_pass_lastmodified = " ";
			user_first_login = " ";
			user_last_success_login = " ";
			user_last_failed_login = " ";
			user_failed_attempts = "0 ";
			user_id_blocked = "ACTIVE";
			user_must_change = "Y";
			sql.setLength(0);
			sql.append("INSERT INTO econtroller_users VALUES (");
			sql.append("'" + user_id + "', ");
			sql.append("'" + user_fname + "', ");
			sql.append("'" + user_lname + "', ");
			sql.append("'" + user_pass + "', ");
			sql.append("'" + user_pass_1 + "', ");
			sql.append("'" + user_pass_2 + "', ");
			sql.append("'" + user_pass_date + "', ");
			sql.append("'" + user_pass_expiry + "', ");
			sql.append("'" + user_pass_lastmodified + "', ");
			sql.append("'" + user_first_login + "', ");
			sql.append("'" + user_last_success_login + "', ");
			sql.append("'" + user_last_failed_login + "', ");
			sql.append("'" + user_failed_attempts + "', ");
			sql.append("'" + user_id_blocked + "', ");
			sql.append("'" + user_must_change + "', ");
			sql.append("'" + user_pa_auth + "', ");
			sql.append("'" + user_pb_auth + "', ");
			sql.append("'" + user_pc_auth + "', ");
			sql.append("'" + user_pd_auth + "', ");
			sql.append("'" + user_pe_auth + "', ");
			sql.append("'" + user_pf_auth + "', ");
			sql.append("'" + user_pg_auth + "', ");
			sql.append("'" + user_ph_auth + "', ");
			sql.append("'" + user_pi_auth + "', ");
			sql.append("'" + user_pj_auth + "', ");
			sql.append("'" + user_pk_auth + "', ");
			sql.append("'" + user_pl_auth + "', ");
			sql.append("'" + user_pm_auth + "', ");
			sql.append("'" + user_pn_auth + "', ");
			sql.append("'" + user_po_auth + "', ");
			sql.append("'" + user_pp_auth + "', ");
			sql.append("'" + user_pq_auth + "', ");
			sql.append("'" + user_pr_auth + "', ");
			sql.append("'" + user_ps_auth + "', ");
			sql.append("'" + user_pt_auth + "', ");
			sql.append("'" + user_menu_option + "', ");
			sql.append("'" + user_locale + "', ");
			sql.append("'" + user_extra_1 + "', ");
			sql.append("'" + user_extra_2 + "', ");
			sql.append("'" + user_extra_3 + "', ");
			sql.append("'" + user_extra_4 + "', ");
			sql.append("'" + user_extra_5 + "', ");
			sql.append("'" + user_display_theme + "', ");
			if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate,");
			} else if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			}
			sql.append("'" + user_id_arg + "', ");
			sql.append("'" + mod_func + "')");
			//PrintLog(": User Insert SQL ---> "+sql);
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog(": Error Inserting User ->" + e.toString());
				PrintLog(": User Insert SQL ---> " + dbUsed + "  " + sql);
			}
		} else {
			mod_func = "Modify User";
			sql.setLength(0);
			sql.append("UPDATE econtroller_users SET ");
			sql.append("USER_PASS='" + user_pass + "', ");
			sql.append("USER_FNAME='" + user_fname + "', ");
			sql.append("USER_LNAME='" + user_lname + "', ");
			sql.append("USER_PASS_1='" + user_pass_1 + "', ");
			sql.append("USER_PASS_2='" + user_pass_2 + "', ");
			sql.append("USER_PASS_DATE='" + user_pass_date + "', ");
			sql.append("USER_PASS_EXPIRY='" + user_pass_expiry + "', ");
			sql.append("USER_PASS_LASTMODIFIED='" + user_pass_lastmodified + "', ");
			sql.append("USER_FIRST_LOGIN='" + user_first_login + "', ");
			sql.append("USER_LAST_SUCCESS_LOGIN='" + user_last_success_login + "', ");
			sql.append("USER_LAST_FAILED_LOGIN='" + user_last_failed_login + "', ");
			sql.append("USER_FAILED_ATTEMPTS='" + user_failed_attempts + "', ");
			sql.append("USER_ID_BLOCKED='" + user_id_blocked + "', ");
			sql.append("USER_MUST_CHANGE='" + user_must_change + "', ");
			sql.append("USER_PA_AUTH='" + user_pa_auth + "', ");
			sql.append("USER_PB_AUTH='" + user_pb_auth + "', ");
			sql.append("USER_PC_AUTH='" + user_pc_auth + "', ");
			sql.append("USER_PD_AUTH='" + user_pd_auth + "', ");
			sql.append("USER_PE_AUTH='" + user_pe_auth + "', ");
			sql.append("USER_PF_AUTH='" + user_pf_auth + "', ");
			sql.append("USER_PG_AUTH='" + user_pg_auth + "', ");
			sql.append("USER_PH_AUTH='" + user_ph_auth + "', ");
			sql.append("USER_PI_AUTH='" + user_pi_auth + "', ");
			sql.append("USER_PJ_AUTH='" + user_pj_auth + "', ");
			sql.append("USER_PK_AUTH='" + user_pk_auth + "', ");
			sql.append("USER_PL_AUTH='" + user_pl_auth + "', ");
			sql.append("USER_PM_AUTH='" + user_pm_auth + "', ");
			sql.append("USER_PN_AUTH='" + user_pn_auth + "', ");
			sql.append("USER_PO_AUTH='" + user_po_auth + "', ");
			sql.append("USER_PP_AUTH='" + user_pp_auth + "', ");
			sql.append("USER_PQ_AUTH='" + user_pq_auth + "', ");
			sql.append("USER_PR_AUTH='" + user_pr_auth + "', ");
			sql.append("USER_PS_AUTH='" + user_ps_auth + "', ");
			sql.append("USER_PT_AUTH='" + user_pt_auth + "', ");
			sql.append("USER_MENU_OPTION='" + user_menu_option + "', ");
			sql.append("USER_LOCALE='" + user_locale + "', ");
			sql.append("USER_EXTRA_1='" + user_extra_1 + "', ");
			sql.append("USER_EXTRA_2='" + user_extra_2 + "', ");
			sql.append("USER_EXTRA_3='" + user_extra_3 + "', ");
			sql.append("USER_EXTRA_4='" + user_extra_4 + "', ");
			sql.append("USER_EXTRA_5='" + user_extra_5 + "', ");
			sql.append("USER_DISPLAY_THEME='" + user_display_theme + "', ");
			if (dbUsed.equals("ORACLE")) {
				sql.append("USER_LAST_MODIFIED=sysdate, ");
			} else if (dbUsed.equals("MySQL")) {
				sql.append("USER_LAST_MODIFIED=NULL, ");
			}
			sql.append("USER_MOD_USER='" + user_id_arg + "', ");
			sql.append("USER_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE USER_id='" + user_id + "' ");
			// PrintLog("User Update SQL ---> "+sql);
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating User ->" + e.toString());
				PrintLog("User Update SQL ---> " + sql);
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO econtroller_users_log VALUES (");
		sql.append("'" + user_id + "', ");
		sql.append("'" + user_fname + "', ");
		sql.append("'" + user_lname + "', ");
		sql.append("'" + user_pass + "', ");
		sql.append("'" + user_pass_1 + "', ");
		sql.append("'" + user_pass_2 + "', ");
		sql.append("'" + user_pass_date + "', ");
		sql.append("'" + user_pass_expiry + "', ");
		sql.append("'" + user_pass_lastmodified + "', ");
		sql.append("'" + user_first_login + "', ");
		sql.append("'" + user_last_success_login + "', ");
		sql.append("'" + user_last_failed_login + "', ");
		sql.append("'" + user_failed_attempts + "', ");
		sql.append("'" + user_id_blocked + "', ");
		sql.append("'" + user_must_change + "', ");
		sql.append("'" + user_pa_auth + "', ");
		sql.append("'" + user_pb_auth + "', ");
		sql.append("'" + user_pc_auth + "', ");
		sql.append("'" + user_pd_auth + "', ");
		sql.append("'" + user_pe_auth + "', ");
		sql.append("'" + user_pf_auth + "', ");
		sql.append("'" + user_pg_auth + "', ");
		sql.append("'" + user_ph_auth + "', ");
		sql.append("'" + user_pi_auth + "', ");
		sql.append("'" + user_pj_auth + "', ");
		sql.append("'" + user_pk_auth + "', ");
		sql.append("'" + user_pl_auth + "', ");
		sql.append("'" + user_pm_auth + "', ");
		sql.append("'" + user_pn_auth + "', ");
		sql.append("'" + user_po_auth + "', ");
		sql.append("'" + user_pp_auth + "', ");
		sql.append("'" + user_pq_auth + "', ");
		sql.append("'" + user_pr_auth + "', ");
		sql.append("'" + user_ps_auth + "', ");
		sql.append("'" + user_pt_auth + "', ");
		sql.append("'" + user_menu_option + "', ");
		sql.append("'" + user_locale + "', ");
		sql.append("'" + user_extra_1 + "', ");
		sql.append("'" + user_extra_2 + "', ");
		sql.append("'" + user_extra_3 + "', ");
		sql.append("'" + user_extra_4 + "', ");
		sql.append("'" + user_extra_5 + "', ");
		sql.append("'" + user_display_theme + "', ");
		if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate,");
		} else if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		}
		sql.append("'" + user_id_arg + "', ");
		sql.append("'" + mod_func + "')");
		dbConn.setAutoCommit(false);
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error inserting User Log ->" + e.toString());
			PrintLog(": Log Insert SQL ---> " + sql);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		// result.close();
		return (1);
	}
	//
	public int UpdateUserLastSuccessLogin(Connection dbConn, String user_id)
			throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "UpdateUserLastSuccessLogin: ";
		//PrintLog(": In Insert/Update User");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// int result = 0;
		// int ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// String mod_func = "";
		DateFormat new_fmt = DateFormat.getInstance();
		new_fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String user_last_success_login = new_fmt.format(java.util.Calendar.getInstance().getTime());
		// system.out.println(new_fmt.format(java.util.Calendar.getInstance().getTime()));
		sql.append("UPDATE econtroller_users SET ");
		sql.append("USER_LAST_SUCCESS_LOGIN='" + user_last_success_login + "', ");
		sql.append("USER_FAILED_ATTEMPTS=0, ");
		sql.append("USER_MOD_USER='" + user_id + "', ");
		sql.append("USER_MOD_FUNC='User Login' ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error Updating UserLastSucecssLogin ->" + e.toString());
			PrintLog(": User Update SQL ---> " + sql);
		}
		sql.setLength(0);
		sql.append("INSERT INTO econtroller_users_log ");
		sql.append("SELECT * FROM econtroller_users ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error creating log entry for user login->" + e.toString());
			PrintLog(": User Update SQL ---> " + sql);
		}
		return (1);
	}
	//
	public int UpdateUserPassModified(Connection dbConn, UserDetail userDetail, String user_id)
			throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "UpdateUserPassModified: ";
		// PrintLog(": In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		int sizePastPw	= 0;
		int numPastPw	= appProperties.getMaxPastPw();
		// int result = 0;
		// int ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// String mod_func = "";
		String user_password	= "";
		String user_pass		= "";
		String user_pass_1		= "";
		String user_pass_2		= userDetail.getUser_pass_2();
		String user_pass_date	= userDetail.getUser_pass_date();
		String user_pass_expiry	= userDetail.getUser_pass_expiry();
		String dbUsed			= userDetail.getDbUsed();
		user_password	= userDetail.getUser_pass_current();
		user_pass		= com.webfiche.checkpoint.sysadmin.service.SysadPasswordUtil.getInstance().encrypt(user_password);
		sizePastPw		= numPastPw * user_pass.length();
		user_pass_1		= userDetail.getUser_pass() + userDetail.getUser_pass_1().trim();
		if (user_pass_1.length()>sizePastPw) {
			user_pass_1		= userDetail.getUser_pass_1().substring(0, sizePastPw);
		}
		user_pass_date	= ValiDate.getTodays_date();
		DateFormat new_fmt = DateFormat.getInstance();
		new_fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String user_pass_lastmodified = new_fmt.format(java.util.Calendar.getInstance().getTime());
		// system.out.println(new_fmt.format(java.util.Calendar.getInstance().getTime()));
		sql.append("UPDATE econtroller_users SET ");
		sql.append("USER_PASS='" + user_pass + "', ");
		sql.append("USER_PASS_1='" + user_pass_1 + "', ");
		sql.append("USER_PASS_2='" + user_pass_2 + "', ");
		sql.append("USER_PASS_DATE='" + user_pass_date + "', ");
		sql.append("USER_PASS_EXPIRY='"+user_pass_expiry + "', ");
		sql.append("USER_PASS_LASTMODIFIED='" + user_pass_lastmodified + "', ");
		sql.append("USER_MUST_CHANGE=' ', ");
		if (dbUsed.equals("ORACLE")) {
			sql.append("USER_LAST_MODIFIED=sysdate,");
		} else if (dbUsed.equals("MySQL")) {
			sql.append("USER_LAST_MODIFIED=NULL,");
		}
		sql.append("USER_MOD_USER='" + user_id + "', ");
		sql.append("USER_MOD_FUNC='Change PSWD' ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		//PrintLog("Password Modified Update: SQL: "+sql);
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error Updating UserPassModified ->" + e.toString());
			PrintLog(": User Password Modified Update SQL ---> " + sql);
		}
		sql.setLength(0);
		sql.append("INSERT INTO econtroller_users_log ");
		sql.append("SELECT * FROM econtroller_users ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error creating log entry for user login->" + e.toString());
			PrintLog(": Password Modified Update SQL ---> " + sql);
		}
		return (1);
	}
	//
	public int UpdateUserFirstLogin(Connection dbConn, String user_id)
			throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "UpdateUserFirstLogin: ";
		// PrintLog(": In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// int result = 0;
		// int ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// String mod_func = "";
		DateFormat new_fmt = DateFormat.getInstance();
		new_fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String user_first_login = new_fmt.format(java.util.Calendar
				.getInstance().getTime());
		// system.out.println(new_fmt.format(java.util.Calendar.getInstance().getTime()));
		sql.append("UPDATE econtroller_users SET ");
		sql.append("USER_FIRST_LOGIN='" + user_first_login + "', ");
		sql.append("USER_LAST_SUCCESS_LOGIN='" + user_first_login + "', ");
		sql.append("USER_MOD_USER='" + user_id + "', ");
		sql.append("USER_MOD_FUNC='User 1st Login' ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error Updating UserFirstLogin ->" + e.toString());
			PrintLog(": User Update SQL ---> " + sql);
		}
		sql.setLength(0);
		sql.append("INSERT INTO econtroller_users_log ");
		sql.append("SELECT * FROM econtroller_users ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error creating log entry for user login->" + e.toString());
			PrintLog(": User Update SQL ---> " + sql);
		}
		return (1);
	}
	//
	public int UpdateUserFailedLogin(Connection dbConn, int failed_attempts,
			String user_id) throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "UpdateUserFailedLogin: ";
		PrintLog(": In Insert/Update Failed Login for "+user_id);
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// int result = 0;
		// ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// String mod_func = "";
		DateFormat new_fmt = DateFormat.getInstance();
		new_fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String user_last_failed_login = new_fmt.format(java.util.Calendar
				.getInstance().getTime());
		// system.out.println(new_fmt.format(java.util.Calendar.getInstance().getTime()));
		sql.append("UPDATE econtroller_users SET ");
		sql.append("USER_LAST_FAILED_LOGIN='" + user_last_failed_login + "', ");
		sql.append("USER_MOD_FUNC='Failed Login', ");
		sql.append("USER_MOD_USER='" + user_id + "', ");
		sql.append("USER_FAILED_ATTEMPTS=" + failed_attempts + " ");
		if (failed_attempts > 4) {
			sql.append(", USER_ID_BLOCKED='BLOCKED' ");
		}
		sql.append("WHERE USER_ID='" + user_id + "' ");
		//PrintLog(": User Update Failed Login SQL ---> " + sql);
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error Updating UserFailedLogin ->" + e.toString());
			PrintLog(": User Update SQL ---> " + sql);
		}
		sql.setLength(0);
		sql.append("INSERT INTO econtroller_users_log ");
		sql.append("SELECT * FROM econtroller_users ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		//PrintLog(": User Update Log Failed Login SQL ---> " + sql);
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog(": Error creating log entry for user login->" + e.toString());
			PrintLog(": User Update SQL ---> " + sql);
		}
		return (1);
	}
	//
	public int DeleteUser(Connection dbConn, UserDetail userDetail,
			String user_id_arg) // ,
			// int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		className  = "> SysadUserUtil.";
		moduleName = "DeleteUser: ";
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// int result = 0;
		// int ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		String mod_func = "Delete User";
		dbConn.setAutoCommit(false);
		//
		String dbUsed = userDetail.getDbUsed();
		String user_id = userDetail.getUser_id();
		sql.setLength(0);
		sql.append("INSERT into econtroller_users_log ");
		sql.append("SELECT USER_ID, ");
		sql.append("USER_FNAME, ");
		sql.append("USER_LNAME, ");
		sql.append("USER_PASS, ");
		sql.append("USER_PASS_1, ");
		sql.append("USER_PASS_2, ");
		sql.append("USER_PASS_DATE, ");
		sql.append("USER_PASS_EXPIRY, ");
		sql.append("USER_PASS_LASTMODIFIED, ");
		sql.append("USER_FIRST_LOGIN, ");
		sql.append("USER_LAST_SUCCESS_LOGIN, ");
		sql.append("USER_LAST_FAILED_LOGIN, ");
		sql.append("USER_FAILED_ATTEMPTS, ");
		sql.append("USER_ID_BLOCKED, ");
		sql.append("USER_MUST_CHANGE, ");
		sql.append("USER_PA_AUTH, ");
		sql.append("USER_PB_AUTH, ");
		sql.append("USER_PC_AUTH, ");
		sql.append("USER_PD_AUTH, ");
		sql.append("USER_PE_AUTH, ");
		sql.append("USER_PF_AUTH, ");
		sql.append("USER_PG_AUTH, ");
		sql.append("USER_PH_AUTH, ");
		sql.append("USER_PI_AUTH, ");
		sql.append("USER_PJ_AUTH, ");
		sql.append("USER_PK_AUTH, ");
		sql.append("USER_PL_AUTH, ");
		sql.append("USER_PM_AUTH, ");
		sql.append("USER_PN_AUTH, ");
		sql.append("USER_PO_AUTH, ");
		sql.append("USER_PP_AUTH, ");
		sql.append("USER_PQ_AUTH, ");
		sql.append("USER_PR_AUTH, ");
		sql.append("USER_PS_AUTH, ");
		sql.append("USER_PT_AUTH, ");
		sql.append("USER_MENU_OPTION, ");
		sql.append("USER_LOCALE, ");
		sql.append("USER_EXTRA_1, ");
		sql.append("USER_EXTRA_2, ");
		sql.append("USER_EXTRA_3, ");
		sql.append("USER_EXTRA_4, ");
		sql.append("USER_EXTRA_5, ");
		sql.append("USER_DISPLAY_THEME, ");
		if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate,");
		} else if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		}
		sql.append("'" + user_id_arg + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM econtroller_users ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting User Log ->" + e.toString());
			PrintLog("User Log Insert SQL ---> " + sql);

		}
		statement.close();
		sql.setLength(0);
		sql.append("DELETE from econtroller_users ");
		sql.append("WHERE USER_ID='" + user_id + "' ");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting User ->" + e.toString());
			PrintLog("User Delete SQL ---> " + sql);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		userDetail.setErrorVec("User", "result.deleted");
		return (1);
	}
	//
	public void PrintUserLogReport(Connection dbConn, UserSelector userSelector)
			throws Exception {
		//
		className					= "> SysadUserUtil.";
		moduleName					= "PrintUserLogReport: ";
		SysadControlUtil ctlUtil	= new SysadControlUtil();
		ControlSelector ctlSelector	= new ControlSelector();
		ControlDetail ctlDetail		= new ControlDetail();
		//GenUtil gUtil				= new GenUtil();
		String rowCount				= userSelector.getRowCount();
		int iRowCount				= Integer.parseInt(rowCount);
		UserDetail userDetail[]		= new UserDetail[iRowCount];
		UserDetail uD				= new UserDetail();
		String repFileName			= "";
		String reportFileName		= "";
		String fileTime				= "";
		DateFormat newFmt2			= null;
		String outputPath			= "";
		// String outputUser		= "";
		String repHeader			= "";
		String applDate				= "";
		String userIdFrom			= userSelector.getUser_id_from();
		String userIdTo				= userSelector.getUser_id_to();
		//
		String userId				= "";
		// String userFName			= "";
		// String userLName			= "";
		String userPass_expiry		= "";
		String userPass_lastmodified	= "";
		String userFirst_login		= "";
		String userLast_success_login	= "";
		String userLast_failed_login	= "";
		String userFailed_attempts	= "";
		String userId_blocked		= "";
		String userPa_auth			= "";
		String userPc_auth			= "";
		String userLast_modified	= "";
		String userMod_user			= "";
		String userMod_func			= "";
		//
		String leftPad				= "";
		String printData			= "";
		String temp					= "";
		String prodId				= "A";
		String tempHdr				= "";
		int prePad					= 0;
		//
		// Process proc;
		//
		// byte[] newText;
		// FileOutputStream outReport = null;
		// PrintStream psReport = null;
		//
		try {
			// int ctlRow	= ctlUtil.GetControlRow (dbConn, ctlSelector, prodId);
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail		= ctlSelector.getARow();
		} catch (Throwable t) {
			PrintLog("Error Getting Control ->" + t.toString());
			return;
		}
		applDate		= ctlDetail.getApplDate();
		// PrintLog("Appl Date "+applDate);
		outputPath		= ctlDetail.getLocOutputDir() + "incl/";
		repHeader		= ctlDetail.getSysRepHeader();
		//
		newFmt2			= new SimpleDateFormat("yyyyMMddHHmmss");
		fileTime		= newFmt2.format(java.util.Calendar.getInstance().getTime());
		repFileName		= "UserAudit_" + userIdFrom;
		reportFileName	= outputPath + "UserAudit_" + userIdFrom;
		if (!userIdTo.equals("NONE")) {
			repFileName		+= "_" + userIdTo;
			reportFileName	+= "_" + userIdTo;
		}
		repFileName			+= "_" + fileTime;
		reportFileName		+= "_" + fileTime;
		PrintLog("Report File Name -> " + reportFileName);
		//
		//
		EcontReportUtil eRep	= new EcontReportUtil(reportFileName);
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(4);
		eRep.setAppl_date(applDate);
		//
		tempHdr		= "REPORT NAME:" + repFileName + ".rpt";
		if (tempHdr.length() < 120) {
			prePad	= 120 - tempHdr.length();
		}
		leftPad		= GenUtil.pad(leftPad, prePad, " ");
		temp		= (tempHdr + leftPad + "PAGE:");
		eRep.setHeadings(temp);
		//
		if (repHeader.length() < 132) {
			prePad	= 132 - repHeader.length();
			if (prePad > 2) {
				prePad	= prePad / 2;
			}
		}
		leftPad		= GenUtil.pad(leftPad, prePad, " ");
		temp		= (leftPad + repHeader);
		eRep.setHeadings(temp);
		//
		tempHdr		= "com.webfiche.checkpoint INCLEARING SYSTEM";
		if (tempHdr.length() < 132) {
			prePad	= 132 - tempHdr.length();
			if (prePad > 2) {
				prePad	= prePad / 2;
			}
		}
		leftPad	= GenUtil.pad(leftPad, prePad, " ");
		temp	= (leftPad + tempHdr);
		eRep.setHeadings(temp);
		//
		tempHdr	= "USER PROFILE CHANGE AUDIT REPORT as of";
		if (tempHdr.length() < 120) {
			prePad	= 120 - tempHdr.length();
			if (prePad > 2) {
				prePad	= prePad / 2;
			}
		}
		leftPad	= GenUtil.pad(leftPad, prePad, " ");
		temp	= (leftPad + tempHdr);
		eRep.setHeadings(temp);
		//
		temp	= ("----------------------------------------------------------" +
					"----------------------------------------------------------" +
					"----------------");
		eRep.setHeadings(temp);
		//
		temp	= ("USER-ID           PASSWORD-EXPIRES  PASSWORD-MODIFIED     " +
					"FIRST-LOGIN           LAST-LOGIN            FAILED-LOGIN  " +
					" FAILED-ATTEMPTS");
		eRep.setHeadings(temp);
		//
		temp	= ("USER-STATUS   SECURITY-ADMIN-GROUP      INCLEARING-GROUP  " +
					"   USER-LAST-MODIFIED        MODIFIED-BY-USER         MODI" +
					"FIED-BY-FUNCTION");
		eRep.setHeadings(temp);
		//
		temp	= ("----------------------------------------------------------" +
					"----------------------------------------------------------" +
					"----------------");
		eRep.setHeadings(temp);
		//
		eRep.WriteHeadings();
		//
		//
		userDetail	= userSelector.getUserrows();
		for (int idx = 0; idx <= userDetail.length - 1; idx++) {
			uD		= userDetail[idx];
			// Here get the fields from the PosipayDetail bean
			userId	= uD.getUser_id();
			userPass_expiry = uD.getUser_pass_expiry();
			userPass_lastmodified = uD.getUser_pass_lastmodified();
			userPass_lastmodified = userPass_lastmodified.replaceAll("\\. ","\\.");
			userFirst_login = uD.getUser_first_login();
			userFirst_login = userFirst_login.replaceAll("\\. ", "\\.");
			userLast_success_login = uD.getUser_last_success_login();
			userLast_success_login = userLast_success_login.replaceAll("\\. ","\\.");
			userLast_failed_login = uD.getUser_last_failed_login();
			userLast_failed_login = userLast_failed_login.replaceAll("\\. ","\\.");
			userFailed_attempts = uD.getUser_failed_attempts();
			userId_blocked = uD.getUser_id_blocked();
			userPa_auth = uD.getUser_pa_auth();
			userPc_auth = uD.getUser_pc_auth();
			userLast_modified = uD.getUser_last_modified();
			userLast_modified = userLast_modified.replaceAll("\\. ", "\\.");
			userMod_user = uD.getUser_mod_user();
			userMod_func = uD.getUser_mod_func();
			printData	= "";
			printData	= ((userId + "                  ").substring(0, 18) +
							(userPass_expiry + "                  ").substring(0, 18) +
							(userPass_lastmodified + "                      ").substring(0,22) +
							(userFirst_login + "                      ").substring(0,22) +
							(userLast_success_login + "                      ").substring(0, 22) +
							(userLast_failed_login + "                            ").substring(0, 29) +
							(userFailed_attempts));
			eRep.WriteDetail(printData);
			printData	= ((userId_blocked + "              ").substring(0, 14) +
							(userPa_auth + "                          ").substring(0,26) +
							(userPc_auth + "                     ").substring(0, 21) +
							(userLast_modified + "                          ").substring(0, 26) +
							(userMod_user + "                         ").substring(0,25) + 
							(userMod_func));
			eRep.WriteDetail(printData);
		}
		eRep.Close();
		PrintLog("Will Rename " + reportFileName + " to " + reportFileName + ".rpt");
		File reportFile	= new File(reportFileName);
		reportFile.renameTo(new File(reportFileName + ".rpt"));
	}
}
