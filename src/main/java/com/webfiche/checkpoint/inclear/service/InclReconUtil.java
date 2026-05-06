package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
//import org.apache.struts.action.*;
import com.webfiche.checkpoint.inclear.beans.*;

/**
 * <strong>InclReconUtil</strong> is a utility class to provide methods to
 * access .and manipilate the incl_recon database table.
 * 
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
@Service
public class InclReconUtil {
	ArrayList<String>	  paramArray;
	ArrayList<ReconDetail> reconBeans = new ArrayList<ReconDetail>();
	String				 param	  = "";
	int					row_count  = 0;

	public InclReconUtil() {
	}
	//
	// Called for Recon Matching
	//
	public int GetReconRows(Connection dbConn, ReconSelector reconSelector,
			String acct_num, String check_num, String checkAmt)
			throws Exception {
		// moduleName = "> InclReconUtil.GetReconRows : ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param = "WHERE RECON_ACCOUNT_NUM='" + acct_num + "' ";
		param += "AND RECON_CHECK_NUM='" + check_num + "' ";
		// param += "AND RECON_UNIQUE_ISN='"+uniqueIsn+"' ";
		param += "AND RECON_CHECK_AMOUNT='" + checkAmt + "' ";
		row_count = GetReconRows(dbConn, reconSelector);
		return (row_count);
	}
	//
	// Called for Modify and possibly update
	//
	public int GetReconRows(Connection dbConn, ReconSelector reconSelector,
			String acct_num, String check_num, String checkAmt, String reconSrc)
			throws Exception {
		// moduleName = "> InclReconUtil.GetReconRows : ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param	= reconSrc;		// just to overcome warning
		param	= "WHERE RECON_ACCOUNT_NUM='" + acct_num + "' ";
		param	+= "AND RECON_CHECK_NUM='" + check_num + "' ";
		// param += "AND RECON_UNIQUE_ISN='"+uniqueIsn+"' ";
		param += "AND RECON_CHECK_AMOUNT='" + checkAmt + "' ";
		// if (reconSrc.equals("micr")) {
		// param += "AND RECON_SOURCE_MICR='Y' ";
		// } else {
		// param += "AND RECON_SOURCE_XML='Y' ";
		// }
		String accessFlag = reconSelector.getAccessFlag();
		String dbUsed = reconSelector.getDbUsed();
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
		row_count = GetReconRows(dbConn, reconSelector);
		return (row_count);
	}
	//
	public int GetReconRows(Connection dbConn, ReconSelector reconSelector,
			String getParams) throws Exception {
		// moduleName = "> InclReconUtil.GetReconRows : ";
		// Called by the modify module with an account number
		// --------------------------------------------------
		param = getParams;
		row_count = GetReconRows(dbConn, reconSelector);
		return (row_count);
	}
	//
	public int GetReconRows(Connection dbConn, ReconSelector reconSelector)
			throws Exception {
		String module_name = "> InclReconUtil.GetReconRows : ";
		// DecimalFormat df = new DecimalFormat("###,###.00");
		String dbUsed = reconSelector.getDbUsed();
		String appl_date = reconSelector.getAppl_date();
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// module_name+"DBUSED "+dbUsed+" ApplDate: "+appl_date);
		// if (param.equals(""))
		// GetParams (stop_plist);
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT RECON_PROC_DATE, RECON_ACCOUNT_NUM, ");
		sql.append("RECON_CHECK_NUM, RECON_ROUTING_TRANSIT, RECON_CHECK_CURRENCY, ");
		sql.append("RECON_CHECK_AMOUNT, RECON_UNIQUE_ISN, ");
		sql.append("RECON_SOURCE_MICR, RECON_SOURCE_XML, ");
		sql.append("RECON_STATUS, RECON_LAST_MODIFIED, ");
		sql.append("RECON_MOD_USER, RECON_MOD_FUNC ");
		sql.append("FROM incl_recon ");
		sql.append(param);
		//
		//System.out.println(java.util.Calendar.getInstance().getTime()+
		//					module_name+"SQL: "+dbUsed+" <> "+sql);
		if (dbConn == null)
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ module_name
					+ "------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		reconBeans.clear();
		try {
			Statement statement = dbConn.createStatement();
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// module_name+"Executing result set");
			ResultSet result = statement.executeQuery(sql.toString());
			// int idx = 0;
			while (result.next()) {
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// module_name+"Getting Rows");
				ReconDetail recon_detail = new ReconDetail();
				// Here add the fields tp the ReconDetail bean
				recon_detail.setDbUsed(dbUsed);
				recon_detail.setAppl_date(appl_date);
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// module_name+"get col 0");
				recon_detail.setRecon_proc_date(result.getString("RECON_PROC_DATE").trim());
				recon_detail.setRecon_account_num(result.getString("RECON_ACCOUNT_NUM").trim());
				recon_detail.setRecon_check_num(result.getString("RECON_CHECK_NUM").trim());
				recon_detail.setRecon_routing_transit(result.getString("RECON_ROUTING_TRANSIT").trim());
				recon_detail.setRecon_check_currency(result.getString("RECON_CHECK_CURRENCY").trim());
				recon_detail.setRecon_check_amount(result.getString("RECON_CHECK_AMOUNT"));
				recon_detail.setRecon_unique_isn(result.getString("RECON_UNIQUE_ISN"));
				recon_detail.setRecon_source_micr(result.getString("RECON_SOURCE_MICR"));
				recon_detail.setRecon_source_xml(result.getString("RECON_SOURCE_XML"));
				recon_detail.setRecon_status(result.getString("RECON_STATUS"));
				recon_detail.setRecon_last_modified(result.getString("RECON_LAST_MODIFIED"));
				recon_detail.setRecon_mod_user(result.getString("RECON_MOD_USER").trim());
				recon_detail.setRecon_mod_func(result.getString("RECON_MOD_FUNC").trim());
				recon_detail.setRecon_modparam();
				reconBeans.add(recon_detail);
				row_count++;
			}
			reconSelector.setReconrows(reconBeans);
			statement.close();
			result.close();
			return (row_count);
		} catch (SQLException e) {
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ module_name + "Error Getting Recon " + e.toString());
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ module_name + "Get Recon " + sql);
			return (0);
		}
	}
	//
	public int InsertUpdateRecon(Connection dbConn, ReconDetail reconDetail,
			String user_name, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		String module_name = "> InclReconUtil.InsertUpdateRecon: ";
		String dbUsed	= reconDetail.getDbUsed();
		System.out.println(java.util.Calendar.getInstance().getTime()+
							module_name+"In Insert/Update Recon Invoked by: "+ user_name +
							" Using DB: "+dbUsed);
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "";
		//
		String recon_account_num = reconDetail.getRecon_account_num().trim();
		String recon_check_num;
		try {
			recon_check_num = Integer.parseInt(reconDetail.getRecon_check_num()) + "";
		} catch (Exception e) {
			recon_check_num = reconDetail.getRecon_check_num();
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// module_name+"Recon Insert/Update check num --->"+
		// reconDetail.getRecon_check_num()+"<");
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// module_name+"Recon Insert/Update check num ---> "+recon_check_num);
		//
		// temp = reconDetail.getRecon_check_amount();
		// String recon_check_amount = gUtil.strip_commas(temp);
		reconDetail.ClearNulls();
		String recon_proc_date = reconDetail.getRecon_proc_date();
		String recon_check_amount = reconDetail.getRecon_check_amount();
		String recon_check_currency = reconDetail.getRecon_check_currency();
		String recon_routing_transit = reconDetail.getRecon_routing_transit();
		String recon_unique_isn = reconDetail.getRecon_unique_isn();
		String recon_source_micr = reconDetail.getRecon_source_micr();
		String recon_source_xml = reconDetail.getRecon_source_xml();
		String recon_status = reconDetail.getRecon_status();
		mod_func = reconDetail.getRecon_mod_func();
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			mod_func = "Add Recon";
			sql.setLength(0);
			sql.append("INSERT INTO incl_recon VALUES (");
			sql.append("'" + recon_proc_date + "', ");
			sql.append("'" + recon_account_num + "', ");
			sql.append("'" + recon_check_num + "', ");
			sql.append("'" + recon_routing_transit + "', ");
			sql.append("'" + recon_check_currency + "', ");
			sql.append("'" + recon_check_amount + "', ");
			sql.append("'" + recon_unique_isn + "', ");
			sql.append("'" + recon_source_micr + "', ");
			sql.append("'" + recon_source_xml + "', ");
			sql.append("'" + recon_status + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL,");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate,");
			}
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			//System.out.println(java.util.Calendar.getInstance().getTime()+
			//					module_name+"Recon Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// module_name+"Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				System.out.println(java.util.Calendar.getInstance().getTime() +
								module_name + "Error Inserting Recon pay ->" + e.toString());
				System.out.println(java.util.Calendar.getInstance().getTime() +
								module_name + "Recon Insert SQL ---> " + sql);
				//reconDetail.ShowDetails();
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					System.out.println(java.util.Calendar.getInstance().getTime() +
										module_name + "Error Rolling back 1: " + ex);
				}
				statement.close();
				// result.close();
				return (0);
			}
		} else {
			mod_func = "Modify Recon";
			sql.setLength(0);
			sql.append("UPDATE incl_recon SET ");
			sql.append("RECON_PROC_DATE='" + recon_proc_date + "', ");
			sql.append("RECON_SOURCE_MICR='" + recon_source_micr + "', ");
			sql.append("RECON_SOURCE_XML='" + recon_source_xml + "', ");
			sql.append("RECON_STATUS='" + recon_status + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("RECON_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("RECON_LAST_MODIFIED=sysdate, ");
			}
			sql.append("RECON_MOD_USER='" + user_name + "', ");
			sql.append("RECON_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE RECON_ACCOUNT_NUM='" + recon_account_num + "' ");
			sql.append("AND RECON_CHECK_NUM='" + recon_check_num + "' ");
			sql.append("AND RECON_UNIQUE_ISN='" + recon_unique_isn + "'");
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// module_name+"Recon Pay Update SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// module_name+"Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				System.out.println(java.util.Calendar.getInstance().getTime() +
						module_name + "Error Updating -->" + e.toString());
				System.out.println(java.util.Calendar.getInstance().getTime() +
									module_name + "Recon Update SQL ---> " + sql);

				try {
					dbConn.rollback();
				} catch (Exception ex) {
					System.out.println(java.util.Calendar.getInstance().getTime() +
										module_name + "Error Rolling back 2: " + ex);
				}
				statement.close();
				// result.close();
				return (0);
			}
		}
		statement.close();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
	//
	public int DeleteRecon(Connection dbConn, ReconDetail reconDetail,
							String user_name) throws Exception {
		String module_name		= "> DeleteRecon: ";
		StringBuffer sql		= new StringBuffer();
		Statement statement		= null;
		//
		String recon_account_num	= reconDetail.getRecon_account_num().trim();
		String recon_check_num		= Integer.parseInt(reconDetail.getRecon_check_num()) + "";
		dbConn.setAutoCommit(false);
		sql.append(user_name);
		sql.setLength(0);
		sql.append("DELETE from incl_recon ");
		sql.append("WHERE RECON_ACCOUNT_NUM='" + recon_account_num + "' ");
		sql.append("AND RECON_CHECK_NUM='" + recon_check_num + "'");
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// module_name+"Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// module_name+"Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			System.out.println(java.util.Calendar.getInstance().getTime() +
								module_name + ": Error Deleting Recon ->" + e.toString());
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				System.out.println(java.util.Calendar.getInstance().getTime() +
									module_name + "Error Rolling Back Recon Delete: " + ex);
			}
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
}
