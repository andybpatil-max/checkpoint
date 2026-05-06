package com.webfiche.checkpoint.deposits.service;

import java.io.*;
import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.deposits.beans.*;
//import com.webfiche.checkpoint.deposits.service.*;
//import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.util.*;

@Service
public class DepsChexUtil {
	String moduleName;
	String className		= "> DepsChexUtil.";
	boolean modifyRow		= false;
	String from_period		= "";
	String to_period		= "";
	String chex_from_period	= "";
	String chex_to_period	= "";
	String chex_from_date	= "";
	String chex_to_date		= "";
	String chex_from_acct	= "";
	String chex_to_acct		= "";
	String chex_from_check	= "";
	String chex_to_check	= "";
	String chex_currency	= "";
	String chex_creditor	= "";
	String chex_from_amount	= "";
	String chex_to_amount	= "";
	String chex_stmt_freq	= "";
	String chex_status_sel	= "";
	String chex_log_date	= "";
	String chex_log_user	= "";
	String userId			= "";
	ArrayList<DepsDetail> chexBeans			= new ArrayList<DepsDetail>();
	ArrayList<AcctentryDetail> postingBeans	= new ArrayList<AcctentryDetail>();
	ArrayList<DepsSummary> chexSBeans		= new ArrayList<DepsSummary>();
	ArrayList<String> hist_range			= new ArrayList<String>();
	DepsPayerUtil	dpUtil					= new DepsPayerUtil();
	String param	= "";
	int row_count	= 0;
	//
	public DepsChexUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + logMsg);
	}
	//
	public void GetDepsAccountList(Connection dbConn, DepsSelector depsSelector)
			throws Exception {
		moduleName	= "GetDepsAccountList: ";
		// PrintLog("Detail: DBUsed "+dbUsed);
		String dbTable		= depsSelector.getDbTable().toLowerCase();
		String chexSource	= depsSelector.getChexSource();
		String temp		= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> tempList	= new ArrayList<String>();
		tempList.clear();
		StringBuffer sql	= new StringBuffer();
		String param		= depsSelector.getParam();
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHEX_PAYEEACCT from " + dbTable);
		if (!param.equals("")) {
			sql.append(" "+param+" ");
		} else {
			sql.append(" WHERE CHEX_SOURCE='"+ chexSource + "' ");
		}
		sql.append(" ORDER by CHEX_PAYEEACCT");
		//PrintLog("SQL: "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("CHEX_PAYEEACCT");
			// PrintLog("Account List: "+temp);
			tempList.add(temp);
		}
		depsSelector.setPayeeAcctList(tempList);
		tempList.clear();
		statement.close();
		result.close();
		//
		// Payer List
		//
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHEX_ACCOUNT_NUM from " + dbTable);
		sql.append(" WHERE CHEX_SOURCE='"+ chexSource + "' ");
		sql.append(" ORDER by CHEX_ACCOUNT_NUM ");
		//PrintLog("SQL: "+sql);
		statement	= dbConn.createStatement();
		result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("CHEX_ACCOUNT_NUM");
			// PrintLog("Account List: "+temp);
			tempList.add(temp);
		}
		depsSelector.setPayerAcctList(tempList);
		tempList.clear();
		statement.close();
		result.close();
		//
		// Payee List
		//
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHEX_PAYEE from " + dbTable.toLowerCase());
		sql.append(" WHERE CHEX_SOURCE='"+ chexSource + "' ");
		sql.append(" ORDER by CHEX_PAYEE");
		statement	= dbConn.createStatement();
		result		= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("CHEX_PAYEE");
			// PrintLog("Creditor List: "+temp);
			tempList.add(temp);
		}
		depsSelector.setPayeeList(tempList);
		statement.close();
		result.close();
	}
	//
	public ArrayList<String> GetDepsLogAccountList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetDepsLogAccountList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> chex_list	= new ArrayList<String>();
		chex_list.clear();
		StringBuffer sql			= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT CHEX_ACCOUNT_NUM from " + dbTable.toLowerCase() +
					" ORDER by CHEX_ACCOUNT_NUM");
		// Setup the SELECT statement.
		// PrintLog(" InclChexUtil: Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog(" InclChexUtil: Execute In While");
			temp	= result.getString("CHEX_ACCOUNT_NUM");
			chex_list.add(temp);
		}
		statement.close();
		result.close();
		return (chex_list);
	}
	//
	public ArrayList<String> GetDepsLogDateList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetDepsLogDateList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> cdate_list	= new ArrayList<String>();
		cdate_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT SUBSTR(to_char(MODTIME,'yyyymmdd hh24:mi:ss'),1,8) as CLM1, ");
		sql.append("SUBSTR(MODTIME,1,9) as CLM from " + dbTable.toLowerCase() + " ORDER by CLM1 DESC");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("CLM");
			cdate_list.add(temp);
		}
		statement.close();
		result.close();
		return (cdate_list);
	}
	//
	public ArrayList<String> GetDepsLogUserList(Connection dbConn,
			String dbTable) throws Exception {
		moduleName	= "GetDepsLogUserList: ";
		// PrintLog(" DBUsed "+dbUsed);
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> cuser_list	= new ArrayList<String>();
		cuser_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		sql.append("SELECT DISTINCT MODUSER from " + dbTable.toLowerCase() + " ORDER by MODUSER");
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("MODUSER");
			cuser_list.add(temp);
		}
		statement.close();
		result.close();
		return (cuser_list);
	}
	//
	public boolean ChexExists(Connection dbConn, String dbTable,
			String acct_num, String check_num, DepsDetail depsDetail)
			throws Exception {
		moduleName = "DepsExists: ";
		// PrintLog(" DBUsed "+dbUsed);
		// Called by the Add module with an account number
		// --------------------------------------------------
		if (acct_num.length() == 0) {
			depsDetail.setErrorVec("Account Number", "error.field.required");
			//PrintLog("Chex Exists  " + acct_num);
			return (true);
		}
		if (check_num.length() == 0) {
			depsDetail.setErrorVec("Check Number", "error.field.required");
			//PrintLog("Chex Exists " + check_num);
			return (true);
		}
		String checkISN	= depsDetail.getChex_unique_isn();
		String procDate	= depsDetail.getChex_proc_date();
		param	= "WHERE CHEX_PROC_DATE='" + procDate + "' ";
		param	+= "AND CHEX_ACCOUNT_NUM='" + acct_num + "' ";
		param 	+= "AND CHEX_CHECK_NUM='" + check_num + "' ";
		param 	+= "AND CHEX_UNIQUE_ISN='" + checkISN + "' ";
		StringBuffer sql	= new StringBuffer();
		sql.setLength(0);
		// sql.append("SELECT CHEX_ACCOUNT_NUM from deps_chex ");
		sql.append("SELECT CHEX_ACCOUNT_NUM from " + dbTable.toLowerCase() + " ");
		sql.append(param);
		// PrintLog("Chex Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public int GetDepsStatusSummaryRows(Connection dbConn, DepsSelector depsSelector) 
		throws Exception {
		moduleName					= "GetDepsStatusSummaryRows: ";
		String check_status_desc	= "";
		String check_status			= "";
		String check_amount			= "";
		String check_count			= "";
		String completed_checks		= "0";
		String dbTable				= depsSelector.getDbTable().toLowerCase();
		//String chexSource			= depsSelector.getChexSource().substring(0,1);
		String chexSource			= depsSelector.getChexSource();
		String prodId				= "D";
		// double d	= 0.45666;
		DecimalFormat df			= new DecimalFormat("###,###.00");
		// String s			= df.format(d);
		// PrintLog(Double.parseDouble(s));
		double amount_total			= 0;
		String amount_total_s		= "";
		int check_total				= 0;
		StringBuffer sql			= new StringBuffer();
		int row_count				= 0;
		//
		sql.setLength(0);
		sql.append("SELECT SUM(CHEX_CHECK_AMOUNT), ");
		sql.append("CHEX_CHECK_STATUS, COUNT(*) FROM "+dbTable);
		sql.append(" WHERE CHEX_SOURCE='"+ chexSource + "' ");
		sql.append(" GROUP BY CHEX_CHECK_STATUS ");
		sql.append("ORDER BY CHEX_CHECK_STATUS");
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		//PrintLog("Summary Sql"+sql);
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		//depsSelector.ShowStatus();
		while (result.next()) {
			DepsSummary depsSummary	= new DepsSummary();
			// Here add the fields tp the DepsDetail bean
			check_status	= result.getString("CHEX_CHECK_STATUS").trim();
			check_amount	= result.getString("SUM(CHEX_CHECK_AMOUNT)");
			check_count		= result.getString("COUNT(*)");
			if (check_status.equals("Z")) {
				completed_checks	= check_count;
			}
			//PrintLog("Check Status: " + check_status + " Check Count: " + check_count);
			check_status_desc= depsSelector.getChexStatus(prodId+check_status);
			depsSummary.setChex_check_status(check_status);
			depsSummary.setChex_status_description(check_status_desc);
			depsSummary.setChex_summ_amount(df.format(Double.parseDouble(check_amount)));
			depsSummary.setChex_check_count(check_count);
			amount_total	+= Double.parseDouble(check_amount);
			check_total		+= Integer.parseInt(check_count);
			chexSBeans.add(depsSummary);
			row_count++;
		}
		// double d = 0.45666;
		// d = Math.round(d * 100.0) / 100.0; // round to 2 decimal places
		// PrintLog(d); // print it.
		amount_total_s = df.format(amount_total);
		// PrintLog(amount_total_s); // print it.
		if (check_total != 0) {
			if (Integer.parseInt(completed_checks) == check_total) {
				depsSelector.setAllow_eod("Y");
			}
		} else {
			depsSelector.setAllow_eod("N");
		}
		//PrintLog("Allow EOD: "+depsSelector.getAllow_eod());
		// }
		depsSelector.setDetail_count(row_count);
		depsSelector.setRow_Count(row_count+"");
		depsSelector.setSumm_total_amount(amount_total_s);
		new String();
		depsSelector.setSumm_total_checks(String.valueOf(check_total));
		depsSelector.setSummaryrows(chexSBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetDepsEodStatusSummary(Connection dbConn, DepsSelector depsSelector) 
		throws Exception {
		moduleName					= "GetDepsStatusSummaryRows: ";
		String check_status_desc	= "";
		String check_status			= "";
		String check_amount			= "";
		String check_count			= "";
		String dbTable				= "deps_chex";
		DecimalFormat df			= new DecimalFormat("###,###.00");
		double amount_total			= 0;
		String amount_total_s		= "";
		int check_total				= 0;
		StringBuffer sql			= new StringBuffer();
		int row_count				= 0;
		//
		sql.setLength(0);
		sql.append("SELECT SUM(CHEX_CHECK_AMOUNT), ");
		sql.append("CHEX_CHECK_STATUS, COUNT(*) FROM "+ dbTable + " ");
		sql.append("GROUP BY CHEX_CHECK_STATUS ");
		sql.append("ORDER BY CHEX_CHECK_STATUS");
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		//Prepare the SELECT statement.
		//PrintLog("Summary Sql"+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		//depsSelector.ShowStatus();
		depsSelector.setAllow_eod("Y");
		while (result.next()) {
			DepsSummary depsSummary	= new DepsSummary();
			check_status	= result.getString("CHEX_CHECK_STATUS").trim();
			check_amount	= result.getString("SUM(CHEX_CHECK_AMOUNT)");
			check_count		= result.getString("COUNT(*)");
			//PrintLog("Check Status: " + check_status + " Check Count: " + check_count);
			check_status_desc= depsSelector.getChexStatus("D"+check_status);
			//PrintLog("Check Status: " + check_status + " " + check_status_desc + 
			//		 " Check Count: " + check_count);
			if (!check_status.equals("Z") && !check_status.equals("C")) {
				depsSelector.setAllow_eod("N");
			}
			depsSummary.setChex_check_status(check_status);
			depsSummary.setChex_status_description(check_status_desc);
			depsSummary.setChex_summ_amount(df.format(Double.parseDouble(check_amount)));
			depsSummary.setChex_check_count(check_count);
			amount_total	+= Double.parseDouble(check_amount);
			check_total		+= Integer.parseInt(check_count);
			chexSBeans.add(depsSummary);
			row_count++;
		}
		//if (row_count==0) {
		//	depsSelector.setAllow_eod("N");
		//}
		//PrintLog("Allow EOD: "+depsSelector.getAllow_eod());
		amount_total_s	= df.format(amount_total);
		depsSelector.setSumm_total_amount(amount_total_s);
		//new String();
		depsSelector.clearRows();
		depsSelector.setSumm_total_checks(String.valueOf(check_total));
		depsSelector.setSummaryrows(chexSBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public void GetDepsImages(DepsSelector depsSelector, DepsDetail depsDetail,
			String proc_date, String unique_isn) throws Exception {
		moduleName				= "GetDepsImages: ";
		String imageFieldDate	= "";
		String imageField		= "";
		String bankId			= depsDetail.getBankId();
		bankId					= depsSelector.getBankId();
		String[] fileNames;
		//
		String imageURL			= depsSelector.getImageURL();
		String imageDir			= depsSelector.getImageDir();
		//
		imageField				= depsDetail.getChex_image();
		imageFieldDate			= (proc_date.substring(0, 4) + "/" +
									proc_date.substring(4, 6) + "/" +
									proc_date.substring(6, 8) + "/");
		//cp PrintLog("Finding "+imageDir+"outcl/"+imageFieldDate+imageField);
		// PrintLog("BankId "+bankId);
		//
		// GetDepsImages(depsSelector, depsDetail, proc_date, unique_isn);

		//if (imageField.equals(" ")) {
		//PrintLog("File to lookup " + imageDir + "outcl/" + imageFieldDate +" Unique ISN: " + unique_isn);
			if (bankId.equals("BNPMO")) {
				//
				// Here use account # and Check # to get image
				// 00000000011111111112222222222333333333344444444445555555555666666
				// 12345678901234567890123456789012345678901234567890123456789012345
				// Check_00000003064505140_000000000001147_000002004110000_back.jpg
				// Check_00000003064505140_000000000001147_000002004110000_front.jpg
				//
				// PrintLog("Finding "+imageDir+imageFieldDate+imageField);
				fileNames	= FileUtil.GetFileNames(imageDir + "outcl/" + imageFieldDate, unique_isn);
				// PrintLog("Filenames size "+fileNames.length);
				// if (fileNames.length > 0) {
				// //
				// } else {
				// PrintLog("BNPMO Finding "+imageDir+imageFieldDate+imageField.substring(6,39));
				// fileNames = fUtil.GetFileNames(imageDir+imageFieldDate,
				// imageField.substring(6,39));
				// }
			} else {
				/*
				 * 	File imgDir		= new File(imageDir + imageFieldDate);
					//PrintLog("Finding "+imageDir+imageFieldDate);
					if (imgDir.exists()) {
						fileNames	= FileUtil.GetFileNames(imageDir + imageFieldDate, unique_isn);
					}

				 */
				//fileNames	= FileUtil.GetFileNames(imageDir + "outcl/" +
				//			  imageFieldDate, imageField.substring(6) +
				//			  "_front.jpg");
				fileNames	= FileUtil.GetFileNames(imageDir + "outcl/" + imageFieldDate, unique_isn);
				//PrintLog("Filenames size " + fileNames.length);
			}
			if (fileNames.length > 0) {
				imageField	= fileNames[0].substring(0,fileNames[0].lastIndexOf("_") - 1);
				//PrintLog("Found File file: " + imageField);
				depsDetail.setChex_image(imageField);
				//depsDetail.setChex_image_b(imageURL + imageFieldDate + imageField + "_back.jpg");
				//depsDetail.setChex_image_f(imageURL + imageFieldDate + imageField + "_front.jpg");
				depsDetail.setChex_image_b(imageURL  + "outcl/" + imageFieldDate + fileNames[0]);
				depsDetail.setChex_image_f(imageURL  + "outcl/" + imageFieldDate + fileNames[1]);
			} else {
				PrintLog("Images do not exist " + imageDir + imageFieldDate	+ imageField);
				imageField	= "Chex_imageUnavailable.jpg";
				depsDetail.setChex_image(imageField);
				depsDetail.setChex_image_b(imageURL + imageField);
				depsDetail.setChex_image_f(imageURL + imageField);
			}
		/*
			} else {
			//depsDetail.setChex_image_b(imageURL + "outcl/" + imageFieldDate +
			//							imageField + "_back.jpg");
			//depsDetail.setChex_image_f(imageURL + "outcl/" + imageFieldDate +
			//							imageField + "_front.jpg");
			fileNames	= FileUtil.GetFileNames(imageURL + "outcl" + imageFieldDate, unique_isn);
			if (fileNames.length > 0) {
				PrintLog("Check Image " + fileNames[0]);				
				imageField	= fileNames[0].substring(0,fileNames[0].lastIndexOf("_") - 1);
				depsDetail.setChex_image_b(imageURL + imageFieldDate + fileNames[0]);
				depsDetail.setChex_image_f(imageURL + imageFieldDate + fileNames[1]);
			} else {
				PrintLog("Images do not exist " + imageDir + imageFieldDate	+ imageField);
				imageField	= "Chex_imageUnavailable.jpg";
				depsDetail.setChex_image(imageField);
				depsDetail.setChex_image_b(imageURL + imageField);
				depsDetail.setChex_image_f(imageURL + imageField);
			}
		}
		*/
	}
	//
	public int GetDepsRowsByUser(Connection dbConn, DepsSelector depsSelector,
			String user_name) throws Exception {
		// Called by Authorize
		moduleName			= "GetDepsRows: ";
		String proc_date	= "";
		String unique_isn	= "";
		String chexSource	= depsSelector.getChexSource();
		param				= "WHERE (CHEX_CHECK_STATUS='A' AND ";
		param 				+= " MODUSER!='" + user_name + "' ";
		param 				+= " CHEX_SOURCE='" + chexSource + "') ";
		param				+= " ORDER BY CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, ";
		param				+= " CHEX_UNIQUE_ISN";
		row_count			= GetDepsRows(dbConn, depsSelector);
		if (row_count > 0) {
			DepsDetail depsDetail	= new DepsDetail();
			depsDetail				= depsSelector.getArow();
			proc_date				= depsDetail.getChex_proc_date();
			depsSelector.setApplDate(proc_date);
			unique_isn				= depsDetail.getChex_unique_isn();
			// if (chexImage.equals(" ")) {
			GetDepsImages(depsSelector, depsDetail, proc_date, unique_isn);
			// }
		}
		return (row_count);
	}
	//
	public int GetDepsRows(Connection dbConn, DepsSelector depsSelector,
			String check_status) throws Exception {
		moduleName	= "GetDepsRows: ";
		String chexSource	= depsSelector.getChexSource();
		param		= "WHERE (CHEX_CHECK_STATUS='" + check_status + "'";
		param		+= "AND CHEX_SOURCE='" + chexSource + "') ";
		param		+= " ORDER BY CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, ";
		param		+= " CHEX_UNIQUE_ISN";
		row_count	= GetDepsRows(dbConn, depsSelector);
		return (row_count);
	}
	//
	public int GetDepsRows(Connection dbConn, DepsSelector depsSelector,
			String check_status, String chexSource) throws Exception {
		moduleName	= "GetDepsRows: ";
		//String chexSource	= depsSelector.getChexSource();
		param		= "WHERE (CHEX_CHECK_STATUS='" + check_status + "'";
		if (chexSource.equals("ALL")) {
			param		+= ") ";
		} else {
			param		+= "AND CHEX_SOURCE='" + chexSource + "') ";
		}
		param		+= " ORDER BY CHEX_PAYEEACCT, CHEX_EXT_PROC_CONTROL, CHEX_ROUTING_TRANSIT ";
		row_count	= GetDepsRows(dbConn, depsSelector);
		return (row_count);
	}
	//
	public int GetDepsRows(Connection dbConn, DepsSelector depsSelector,
			String acct_num, String check_num, String check_unique_isn)
			throws Exception {
		moduleName			= "GetDepsRows: ";
		String proc_date	= "";
		String unique_isn	= "";
		String chexSource	= depsSelector.getChexSource();
		param		= " WHERE (CHEX_ACCOUNT_NUM='" + acct_num + "'";
		param		+= " AND CHEX_CHECK_NUM='" + check_num + "'";
		param		+= " AND CHEX_UNIQUE_ISN='" + check_unique_isn + "'";
		param		+= " AND CHEX_SOURCE='" + chexSource + "') ";
		String accessFlag	= depsSelector.getAccessFlag();
		String dbUsed		= depsSelector.getDbUsed();
		if (dbUsed.equals("ORACLE")) {
			if (accessFlag != null) {
				if (accessFlag.equals("inq")) {
					;
				} else {
					param	+= " for UPDATE nowait ";
					dbConn.setAutoCommit(false);
				}
			} else {
				param	+= " for UPDATE nowait ";
				dbConn.setAutoCommit(false);
			}
		}
		modifyRow	= true;
		row_count	= GetDepsRows(dbConn, depsSelector);
		modifyRow	= false;
		if (row_count > 0) {
			DepsDetail depsDetail	= new DepsDetail();
			// depsDetail	= depsSelector.getArow();
			depsDetail	= chexBeans.get(0);
			depsSelector.setModifyRow(depsDetail);
			proc_date	= depsDetail.getChex_proc_date();
			unique_isn	= depsDetail.getChex_unique_isn();
			GetDepsImages(depsSelector, depsDetail, proc_date, unique_isn);
			/*
			 * if (chexImage.equals(" ")) { GetDepsImages(depsSelector,
			 * depsDetail, proc_date, unique_isn); } else {
			 * depsDetail.setChex_image_b ("/imagedir/"+chexImage+"_back.jpg");
			 * depsDetail.setChex_image_f ("/imagedir/"+chexImage+"_front.jpg");
			 * }
			 */
		}
		return (row_count);
	}
	//
	public int GetDepsRows(Connection dbConn, String acctNum, DepsSelector depsSelector)
			throws Exception {
		// Called for getting batch for cancel
		moduleName			= "GetDepsRows: ";
		String chexSource	= depsSelector.getChexSource();
		param				= " WHERE (CHEX_PAYEEACCT='" + acctNum + "' AND ";
		param				+= " CHEX_CHECK_STATUS!='C' AND CHEX_SOURCE='"+ 
								chexSource +"')";
		row_count	= GetDepsRows(dbConn, depsSelector);
		return (row_count);
	}
	//
	public int GetDepsRows(Connection dbConn, DepsSelector depsSelector)
			throws Exception {
		//
		moduleName				= "GetDepsRows: ";
		double amountTotal		= 0;
		String amountTotal_s	= "";
		String unique_isn		= "";
		String proc_date		= "";
		String chexAcctNum		= "";
		String chexAba			= "";
		String payerName		= "";
		String payerCountry		= "";
		String appl_date		= depsSelector.getApplDate();
		String dbUsed			= depsSelector.getDbUsed();
		String dbTable			= depsSelector.getDbTable().toLowerCase();
		String rejCode			= "";
		//String rejDesc			= "";
		String statusCode		= "";
		String chexSource		= depsSelector.getChexSource();
		String prodId			= "D";
		//PrintLog("Action Flag: " + actionFlag + " Access Flag: " + accessFlag);
		String bankId			= depsSelector.getBankId();
		//
		DecimalFormat df		= new DecimalFormat("###,###,###.00");
		if (param.equals("")) {
			param	= depsSelector.GetParams();
			//PrintLog("Param: "+param);
			if (param.equals("")) {
				param	= " WHERE CHEX_SOURCE='" + chexSource + "' ";
			}
		}
		StringBuffer sql	= new StringBuffer();
		row_count			= 0;
		sql.setLength(0);
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, CHEX_BUNDLEID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, ");
		sql.append("CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEEABA, ");
		sql.append("CHEX_PAYEEACCT, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, CHEX_CHECK_STATUS, ");
		sql.append("CHEX_AMOUNT_OD, CHEX_IMAGE, CHEX_SOURCE, ");
		sql.append("MODTIME, MODUSER, MODFUNC ");
		sql.append("FROM " + dbTable + " ");
		sql.append(param);
		//PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		try {
			Statement statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				DepsDetail depsDetail	= new DepsDetail();
				// Here add the fields tp the DepsDetail bean
				depsDetail.setDbUsed(dbUsed);
				depsDetail.setBankId(bankId);
				depsDetail.setAppl_date(appl_date);
				depsDetail.setChex_proc_date(result.getString("CHEX_PROC_DATE"));
				proc_date	= depsDetail.getChex_proc_date();
				depsDetail.setChex_orig_account_num(result.getString("CHEX_ORIG_ACCOUNT_NUM"));
				depsDetail.setChex_orig_check_num(result.getString("CHEX_ORIG_CHECK_NUM"));
				depsDetail.setChex_account_num(result.getString("CHEX_ACCOUNT_NUM"));
				depsDetail.setChex_check_num(result.getString("CHEX_CHECK_NUM"));
				depsDetail.setChex_routing_transit(result.getString("CHEX_ROUTING_TRANSIT"));
				//chexAcctNum	= Long.parseLong(result.getString("CHEX_ACCOUNT_NUM"))+"";
				chexAcctNum	= result.getString("CHEX_ACCOUNT_NUM");
				chexAba		= result.getString("CHEX_ROUTING_TRANSIT");
				//PrintLog("Get PayerName: "+payerName);
				depsDetail.setChex_check_amount(result.getString("CHEX_CHECK_AMOUNT"));
				//
				amountTotal	= amountTotal + Double.parseDouble(result.getString("CHEX_CHECK_AMOUNT"));
				//
				depsDetail.setChex_check_currency(result.getString("CHEX_CHECK_CURRENCY"));
				depsDetail.setChex_proc_control(result.getString("CHEX_PROC_CONTROL"));
				depsDetail.setChex_ext_proc_control(result.getString("CHEX_EXT_PROC_CONTROL"));
				depsDetail.setChex_image_locator(result.getString("CHEX_IMAGE_LOCATOR"));
				depsDetail.setChex_unique_isn(result.getString("CHEX_UNIQUE_ISN"));
				unique_isn	= depsDetail.getChex_unique_isn();
				depsDetail.setChex_addenda_rec_flag(result.getString("CHEX_ADDENDA_REC_FLAG"));
				depsDetail.setChex_orig_inst_rte(result.getString("CHEX_ORIG_INST_RTE"));
				depsDetail.setChex_isn(result.getString("CHEX_ISN"));
				depsDetail.setChex_budget_id(result.getString("CHEX_BUDGET_ID"));
				depsDetail.setChex_bundleid(result.getString("CHEX_BUNDLEID"));
				depsDetail.setChex_return_date(result.getString("CHEX_RETURN_DATE"));
				depsDetail.setChex_return_reason(result.getString("CHEX_RETURN_REASON"));
				depsDetail.setChex_return_status(result.getString("CHEX_RETURN_STATUS"));
				depsDetail.setChex_BOFD_aba(result.getString("CHEX_BOFD_ABA"));
				depsDetail.setChex_BOFD_date(result.getString("CHEX_BOFD_DATE"));
				depsDetail.setChex_extra_1(result.getString("CHEX_EXTRA_1"));
				depsDetail.setChex_extra_2(result.getString("CHEX_EXTRA_2"));
				depsDetail.setChex_issue_date(result.getString("CHEX_ISSUE_DATE"));
				depsDetail.setChex_payeeaba(result.getString("CHEX_PAYEEABA"));
				depsDetail.setChex_payeeacct(result.getString("CHEX_PAYEEACCT"));
				depsDetail.setChex_payee(result.getString("CHEX_PAYEE"));
				depsDetail.setChex_payee_addr1(result.getString("CHEX_PAYEE_ADDR1"));
				depsDetail.setChex_payee_addr2(result.getString("CHEX_PAYEE_ADDR2"));
				depsDetail.setChex_payee_addr3(result.getString("CHEX_PAYEE_ADDR3"));
				payerName	= result.getString("CHEX_PAYEE_ADDR3");
				if (payerName.equals("") || payerName.equals(" ")) {
					try {
						payerName	= dpUtil.GetPayerName(dbConn,chexAba,chexAcctNum);
						if (payerName.length() < 1) {
							payerName	= " ";
						} else {
							if (payerName.length() > 35) {
								payerName	= payerName.substring(0,35);
							}
						}
					} catch (Exception e) {
						payerName	= " ";
						PrintLog("Exception getting payer name for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
					}
				}
				try {
					payerCountry= dpUtil.GetPayerCountry(dbConn, chexAba, chexAcctNum);
					if (payerCountry == null) {
						payerCountry	= "US";
					} else {
						if (payerCountry.equals("") || payerCountry.equals(" ")) {
							payerCountry	= "US";
						}
					}
				} catch (Exception e) {
					payerCountry	= "  ";
					PrintLog("Exception getting payer country for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
				}
				depsDetail.setChexPayerName(payerName);
				depsDetail.setChexPayerCountry(payerCountry);
				depsDetail.setChex_comments(result.getString("CHEX_COMMENTS"));
				/*
				rejDesc	= "";
				*/
				rejCode	= result.getString("CHEX_REASON_CODES");
				depsDetail.setChex_reason_codes(rejCode);
				/*
				for (int idx = 0; idx < rejCode.length(); idx++) {
					rejDesc	= rejDesc + depsSelector.getRejReason(prodId+rejCode.substring(idx, idx+1)) + ", ";
				}
				depsDetail.setChex_reason_desc(rejDesc.substring(0,rejDesc.length()-2));
				*/
				depsDetail.setChex_reason_desc(" ");
				statusCode	= result.getString("CHEX_CHECK_STATUS");
				depsDetail.setChex_check_status(statusCode);
				depsDetail.setChex_status_desc(depsSelector.getChexStatus(prodId+statusCode));
				depsDetail.setChex_amount_od(result.getString("CHEX_AMOUNT_OD"));
				depsDetail.setChex_image(result.getString("CHEX_IMAGE"));
				depsDetail.setChex_source(result.getString("CHEX_SOURCE"));
				depsDetail.setModtime(result.getString("MODTIME"));
				depsDetail.setModuser(result.getString("MODUSER"));
				depsDetail.setModfunc(result.getString("MODFUNC"));
				depsDetail.setChex_modparam();
				// PrintLog("Acct Num:>"+acct_num+"< Check Num > "+check_num+
				// "< Unique ISN >"+unique_isn);
				GetDepsImages(depsSelector, depsDetail, proc_date, unique_isn);
				chexBeans.add(depsDetail);
				row_count++;
			}
			amountTotal_s	= df.format(amountTotal);
			depsSelector.setDetailAmount(amountTotal_s);
			if (modifyRow) {
			} else {
				depsSelector.setCheckrows(chexBeans);
				depsSelector.setDetail_count(row_count);
			}
			statement.close();
			result.close();
			return (row_count);
		} catch (SQLException e) {
			PrintLog("Error Getting Authorize" + e.toString());
			PrintLog("Get AuthorizeChex " + sql);
			return (0);
		}
	}
	//
	public ArrayList<String> GetDepsHistList(Connection dbConn, String dbUsed, String appSchema)
			throws Exception {
		moduleName	= "GetDepsHistList: ";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> hist_list	= new ArrayList<String>();
		hist_list.clear();
		DatabaseMetaData mD	= dbConn.getMetaData();
		String tableName	= null;
		ResultSet rset		= null;
		//PrintLog("dbUsed: " + dbUsed);
		if (dbUsed.equals("ORACLE")) {
			rset	= mD.getTables(null, appSchema, "DEPS_CHEX_2%", new String[] { "TABLE" });
		} else {
			rset	= mD.getTables(null, appSchema, "deps_chex_2%", new String[] { "TABLE" });
		}
		while (rset.next()) {
			tableName	= rset.getString("TABLE_NAME");
			//PrintLog("History TABLE_NAME "+tableName);
			hist_list.add(tableName.substring(tableName.lastIndexOf("_") + 1));
		}
		rset.close();
		// PrintLog("GetChexHistList size -->"+ hist_list.size());
		return (hist_list);
	}
	//
	public ArrayList<String> GetDepsLogHistList(Connection dbConn, String dbUsed, String appSchema)
			throws Exception {
		moduleName	= "GetDepsLogHistList: ";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> hist_list		= new ArrayList<String>();
		hist_list.clear();
		DatabaseMetaData mD	= dbConn.getMetaData();
		String tableName	= null;
		ResultSet rset		= null;
		//PrintLog("dbUsed: " + dbUsed);
		if (dbUsed.equals("ORACLE")) {
			rset	= mD.getTables(null, appSchema, "DEPS_CHEX_2%", new String[] { "TABLE" });
		} else {
			rset	= mD.getTables(null, appSchema, "deps_chex_2%", new String[] { "TABLE" });
		}
		while (rset.next()) {
			tableName	= rset.getString("TABLE_NAME");
			// PrintLog("log TABLE_NAME "+tableName);
			hist_list.add(tableName.substring(tableName.lastIndexOf("_") + 1));
		}
		rset.close();
		// PrintLog("GetChexHistList size -->"+ hist_list.size());
		return (hist_list);
	}
	//
	// Called by ChexHistDetailAction for Inquiry of a specific check
	//
	public int GetHistRows(Connection dbConn, String proc_date,
			String acct_num, String check_num, String check_unique_isn,
			DepsSelector depsSelector) throws Exception {
		moduleName		= "GetHistRows: ";
		int hist_rows	= 0;
		ArrayList<String> hist_list = new ArrayList<String>();
		from_period		= proc_date.substring(0, 6);
		param			= " WHERE CHEX_ACCOUNT_NUM='" + acct_num + "'";
		param			+= " AND CHEX_CHECK_NUM='" + check_num + "'";
		param			+= " AND CHEX_UNIQUE_ISN='" + check_unique_isn + "'";
		hist_list.add(proc_date.substring(0, 6));
		hist_rows		= GetHistRows(dbConn, hist_list, depsSelector);
		if (hist_rows > 0) {
			DepsDetail depsDetail	= new DepsDetail();
			depsDetail				= depsSelector.getArow();
			GetDepsImages(depsSelector, depsDetail, proc_date, check_unique_isn);
		}
		return (hist_rows);
	}
	//
	// Called by ChexGenPDF to generate PDFs.
	//
	public int GetHistRows(Connection dbConn, ArrayList<String> hist_list,
			DepsSelector depsSelector, String dummy) throws Exception {
		moduleName	= "GetHistRows: for PDFs: ";
		//PrintLog(" In Get Hist Rows Proc date "+dummy);
		int hist_rows	= 0;
		param			= "";
		hist_rows		= GetHistRows(dbConn, hist_list, depsSelector);
		return (hist_rows);
	}
	//
	// Generic Get History rows based on different select clauses
	//
	public int GetHistRows(Connection dbConn, ArrayList<String> hist_list,
			DepsSelector depsSelector) throws Exception {
		moduleName				= "GetHistRows: ";
		double amountTotal		= 0;
		String amountTotal_s	= "";
		String unique_isn		= "";
		String proc_date		= "";
		String rejCode			= "";
		String rejDesc			= " ";
		String statusCode		= "";
		String chexAcctNum		= "";
		String chexAba			= "";
		String payerName		= "";
		String payerCountry		= "";
		//String chexSource		= depsSelector.getChexSource();
		String prodId			= "D";
		String applDate		= depsSelector.getApplDate();
		DecimalFormat df		= new DecimalFormat("###,###,###.00");
		//param	= depsSelector.getParam();
		if (param.equals("")) {
			param	= depsSelector.GetHistParams();
			//PrintLog("HistParams: "+param);
			if (param.equals("")) {
				//param	= " WHERE CHEX_SOURCE='" + chexSource + "' ";
			}
		}
		from_period			= depsSelector.getCh_from_period();
		to_period			= depsSelector.getCh_to_period();
		chex_from_check		= depsSelector.getCh_from_check();
		chex_to_check		= depsSelector.getCh_to_check();
		StringBuffer sql	= new StringBuffer();
		String hist_table;
		ArrayList<String> hist_tables	= new ArrayList<String>();
		int hist_size		= hist_list.size();
		PrintLog("hist_size:>> " + hist_size);
		// PrintLog("Chex_from_check >"+chex_from_check+"< to check >"+chex_to_check+"<");
		// PrintLog("Chex_from_period >"+from_period+"< to period >"+to_period+"<");
		for (int idx = 0; idx < hist_size; idx++) {
			hist_table = hist_list.get(idx);
			if (chex_from_check.equals("") && chex_to_check.equals("")) {
				if (hist_table.compareTo(from_period) == 0) {
					//PrintLog("1.1 Hist Table: " + hist_table + " From Period: "	+ from_period);
					hist_tables.add(hist_table);
				} else if (!to_period.equals("NONE")
						&& hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					//PrintLog("1.2 Hist Table: " + hist_table + " to Period: " +
					//		to_period + " From Period: " + from_period);
					hist_tables.add(hist_table);
				} else if (!to_period.equals("NONE")
						&& hist_table.compareTo(to_period) == 0) {
					//PrintLog("1.3 Hist Table: " + hist_table + " From Period: " +
					//		 from_period);
					hist_tables.add(hist_table);
				}
			} else {
				//PrintLog("2. Hist Table: " + hist_table + " From Period: " + from_period);
				if (hist_table.compareTo(from_period) == 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(to_period) == 0) {
					hist_tables.add(hist_table);
				}
				// hist_tables.add(hist_table);
			}
		}
		//PrintLog("Tables:>> "+hist_tables.size());
		chexBeans.clear();
		row_count	= 0;
		depsSelector.clearRows();
		Iterator<String> h_tables	= hist_tables.iterator();
		while (h_tables.hasNext()) {
			// PrintLog("Getting Rows");
			// ------------------- DO BOT REMOVE -------------------------
			// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
			// ---------------------------------------------------------------
			hist_table	= h_tables.next().toString();
			PrintLog("Tables:>> "+hist_table);
			sql.setLength(0);
			// PrintLog("SQL: "+sql+" Param: "+param);
			sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
			sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
			sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
			sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
			sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
			sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
			sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
			sql.append("CHEX_ISN, CHEX_BUDGET_ID, CHEX_BUNDLEID, ");
			sql.append("CHEX_RETURN_DATE, ");
			sql.append("CHEX_RETURN_REASON, ");
			sql.append("CHEX_RETURN_STATUS, ");
			sql.append("CHEX_BOFD_ABA, ");
			sql.append("CHEX_BOFD_DATE, ");
			sql.append("CHEX_EXTRA_1, ");
			sql.append("CHEX_EXTRA_2, ");
			sql.append("CHEX_ISSUE_DATE, ");
			sql.append("CHEX_PAYEEABA, ");
			sql.append("CHEX_PAYEEACCT, ");
			sql.append("CHEX_PAYEE, ");
			sql.append("CHEX_PAYEE_ADDR1, ");
			sql.append("CHEX_PAYEE_ADDR2, ");
			sql.append("CHEX_PAYEE_ADDR3, ");
			sql.append("CHEX_COMMENTS, ");
			sql.append("CHEX_REASON_CODES, ");
			sql.append("CHEX_CHECK_STATUS, CHEX_AMOUNT_OD, CHEX_IMAGE, ");
			sql.append("CHEX_SOURCE, MODTIME, ");
			sql.append("MODUSER, MODFUNC ");
			sql.append("FROM deps_chex_" + hist_table.trim() + " ");
			sql.append(param);
			//PrintLog("SQL: "+sql);
			if (dbConn == null)
				PrintLog("------------------ NULL DBCONN -----------------");
			Statement statement	= dbConn.createStatement();
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				DepsDetail depsDetail	= new DepsDetail();
				// Here add the fields tp the CHEXDetail
				depsDetail.setAppl_date(applDate);
				depsDetail.setDbUsed(depsSelector.getDbUsed());
				depsDetail.setChex_proc_date(result.getString("CHEX_PROC_DATE"));
				proc_date	= result.getString("CHEX_PROC_DATE");
				depsDetail.setChex_orig_account_num(result.getString("CHEX_ORIG_ACCOUNT_NUM"));
				depsDetail.setChex_orig_check_num(result.getString("CHEX_ORIG_CHECK_NUM"));
				depsDetail.setChex_account_num(result.getString("CHEX_ACCOUNT_NUM"));
				depsDetail.setChex_check_num(result.getString("CHEX_CHECK_NUM"));
				depsDetail.setChex_routing_transit(result.getString("CHEX_ROUTING_TRANSIT"));
				chexAcctNum	= result.getString("CHEX_ACCOUNT_NUM");
				chexAba		= result.getString("CHEX_ROUTING_TRANSIT");
				payerName	= depsSelector.getPayerName(chexAba+chexAcctNum);
				payerCountry= depsSelector.getPayerCountry(chexAba+chexAcctNum);
				if (payerCountry == null) {
					payerCountry	= "US";
				} else {
					if (payerCountry.equals("") || payerCountry.equals(" ")) {
						payerCountry	= "US";
					}
				}
				//PrintLog ("Payer Name: "+payerName+" Country: "+payerCountry);
				depsDetail.setChexPayerName(payerName);
				depsDetail.setChexPayerCountry(payerCountry);
				depsDetail.setChex_check_amount(result.getString("CHEX_CHECK_AMOUNT"));
				//
				amountTotal = amountTotal + Double.parseDouble(result.getString("CHEX_CHECK_AMOUNT"));
				//
				depsDetail.setChex_check_currency(result.getString("CHEX_CHECK_CURRENCY"));
				depsDetail.setChex_proc_control(result.getString("CHEX_PROC_CONTROL"));
				depsDetail.setChex_ext_proc_control(result.getString("CHEX_EXT_PROC_CONTROL"));
				depsDetail.setChex_image_locator(result.getString("CHEX_IMAGE_LOCATOR"));
				depsDetail.setChex_unique_isn(result.getString("CHEX_UNIQUE_ISN"));
				unique_isn	= result.getString("CHEX_UNIQUE_ISN");
				depsDetail.setChex_addenda_rec_flag(result.getString("CHEX_ADDENDA_REC_FLAG"));
				depsDetail.setChex_orig_inst_rte(result.getString("CHEX_ORIG_INST_RTE"));
				depsDetail.setChex_isn(result.getString("CHEX_ISN"));
				depsDetail.setChex_budget_id(result.getString("CHEX_BUDGET_ID"));
				depsDetail.setChex_bundleid(result.getString("CHEX_BUNDLEID"));
				depsDetail.setChex_return_date(result.getString("CHEX_RETURN_DATE"));
				depsDetail.setChex_return_reason(result.getString("CHEX_RETURN_REASON"));
				depsDetail.setChex_return_status(result.getString("CHEX_RETURN_STATUS"));
				depsDetail.setChex_BOFD_aba(result.getString("CHEX_BOFD_ABA"));
				depsDetail.setChex_BOFD_date(result.getString("CHEX_BOFD_DATE"));
				if (result.getString("CHEX_EXTRA_1") == null) {
					depsDetail.setChex_extra_1(" ");
				} else {
					depsDetail.setChex_extra_1(result.getString("CHEX_EXTRA_1"));
				}
				if (result.getString("CHEX_EXTRA_2") == null) {
					depsDetail.setChex_extra_2(" ");
				} else {
					depsDetail.setChex_extra_2(result.getString("CHEX_EXTRA_2"));
				}
				depsDetail.setChex_issue_date(result.getString("CHEX_ISSUE_DATE"));
				depsDetail.setChex_payeeaba(result.getString("CHEX_PAYEEABA"));
				depsDetail.setChex_payeeacct(result.getString("CHEX_PAYEEACCT"));
				depsDetail.setChex_payee(result.getString("CHEX_PAYEE"));
				depsDetail.setChex_payee_addr1(result.getString("CHEX_PAYEE_ADDR1"));
				depsDetail.setChex_payee_addr2(result.getString("CHEX_PAYEE_ADDR2"));
				depsDetail.setChex_payee_addr3(result.getString("CHEX_PAYEE_ADDR3"));
				depsDetail.setChex_comments(result.getString("CHEX_COMMENTS"));
				payerName	= result.getString("CHEX_PAYEE_ADDR3");
				if (payerName.equals("") || payerName.equals(" ")) {
					try {
						payerName	= dpUtil.GetPayerName(dbConn,chexAba,chexAcctNum);
						if (payerName.length() < 1) {
							payerName	= " ";
						} else {
							if (payerName.length() > 35) {
								payerName	= payerName.substring(0,35);
							}
						}
					} catch (Exception e) {
						payerName	= " ";
						PrintLog("Exception getting payer name for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
					}
				}
				try {
					payerCountry= dpUtil.GetPayerCountry(dbConn, chexAba, chexAcctNum);
					if (payerCountry == null) {
						payerCountry	= "US";
					} else {
						if (payerCountry.equals("") || payerCountry.equals(" ")) {
							payerCountry	= "US";
						}
					}
				} catch (Exception e) {
					payerCountry	= "  ";
					PrintLog("Exception getting payer country for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
				}
				depsDetail.setChexPayerName(payerName);
				depsDetail.setChexPayerCountry(payerCountry);
				//
				rejDesc	= "";
				rejCode	= result.getString("CHEX_REASON_CODES");
				depsDetail.setChex_reason_codes(rejCode);
				/*
				for (int idx = 0; idx < rejCode.length(); idx++) {
					rejDesc	= rejDesc + depsSelector.getRejReason(prodId+rejCode.substring(idx, idx+1)) + ", ";
				}
				*/
				//chexSource	= result.getString("CHEX_SOURCE");
				prodId	= "D";
				//depsDetail.setChex_reason_desc(rejDesc.substring(0,rejDesc.length()-2));
				depsDetail.setChex_reason_desc(rejDesc);
				statusCode	= result.getString("CHEX_CHECK_STATUS");
				depsDetail.setChex_check_status(statusCode);
				depsDetail.setChex_amount_od(result.getString("CHEX_AMOUNT_OD"));
				depsDetail.setChex_image(result.getString("CHEX_IMAGE"));
				depsDetail.setChex_source(result.getString("CHEX_SOURCE"));
				depsDetail.setChex_status_desc(depsSelector.getChexStatus(prodId+statusCode));
				depsDetail.setModtime(result.getString("MODTIME"));
				depsDetail.setModuser(result.getString("MODUSER"));
				depsDetail.setModfunc(result.getString("MODFUNC"));
				depsDetail.setChex_modparam();
				GetDepsImages(depsSelector, depsDetail, proc_date, unique_isn);
				moduleName				= "GetHistRows: ";
				chexBeans.add(row_count, depsDetail);
				row_count++;
			}
			statement.close();
			result.close();
		}
		depsSelector.setCheckrows(chexBeans);
		//PrintLog("Number of ChexBeans " + chexBeans.size());
		amountTotal_s	= df.format(amountTotal);
		depsSelector.setDetailAmount(amountTotal_s);
		return (row_count);
	}
	//
	// Generic Get Log History rows based on different select clauses
	//
	public int GetLogHistRows(Connection dbConn, ArrayList<String> hist_list,
			DepsSelector depsSelector) throws Exception {
		moduleName	= "GetLogHistRows: ";
		String appl_date	= depsSelector.getApplDate();
		String dbTable		= depsSelector.getDbTable().toLowerCase();
		String chexSource	= depsSelector.getChexSource();
		String payerName	= "";
		String payerCountry	= "";
		String chexAba		= "";
		String chexAcctNum	= "";
		if (param.equals("")) {
			param	= depsSelector.GetLogHistParams();
			if (param.equals("")) {				
				param	= " WHERE CHEX_SOURCE='" + chexSource + "' ";
			}
		}
		from_period	= depsSelector.getCh_from_period();
		to_period	= depsSelector.getCh_to_period();
		if (to_period.equals("NONE")) {
			to_period	= "";
		}
		chex_from_check		= depsSelector.getCh_from_check();
		chex_to_check		= depsSelector.getCh_to_check();
		StringBuffer sql	= new StringBuffer();
		String hist_table;
		ArrayList<String> hist_tables	= new ArrayList<String>();
		int hist_size		= hist_list.size();
		// PrintLog("hist_size:>> "+hist_size);
		// PrintLog("Chex_from_check >"+chex_from_check+"< to check >"+chex_to_check+"<");
		// PrintLog("Chex_from_period >"+from_period+"< to period >"+to_period+"<");
		for (int idx = 0; idx < hist_size; idx++) {
			hist_table	= hist_list.get(idx);
			if (chex_from_check.equals("") && chex_to_check.equals("")) {
				if (hist_table.compareTo(from_period) == 0) {
					//PrintLog("1.1 Hist Table: " + hist_table + " From Period: " + from_period);
					hist_tables.add(hist_table);
				} else if (!to_period.equals("NONE")
						&& hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					//PrintLog("1.2 Hist Table: " + hist_table + " to Period: " +
					//		 to_period + " From Period: " + from_period);
					hist_tables.add(hist_table);
				} else if (!to_period.equals("NONE")
						&& hist_table.compareTo(to_period) == 0) {
					//PrintLog("1.3 Hist Table: " + hist_table + " From Period: " + from_period);
					hist_tables.add(hist_table);
				}
			} else {
				//PrintLog("2. Hist Table: " + hist_table + " From Period: " + from_period);
				if (hist_table.compareTo(from_period) == 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(from_period) > 0
						&& hist_table.compareTo(to_period) < 0) {
					hist_tables.add(hist_table);
				} else if (hist_table.compareTo(to_period) == 0) {
					hist_tables.add(hist_table);
				}
				// hist_tables.add(hist_table);
			}
		}
		// PrintLog("Tables:>> "+hist_tables.size());
		row_count	= 0;
		Iterator<String> h_tables	= hist_tables.iterator();
		while (h_tables.hasNext()) {
			// PrintLog("Getting Rows");
			// ------------------- DO BOT REMOVE -------------------------
			// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
			// ---------------------------------------------------------------
			hist_table	= h_tables.next().toString();
			// PrintLog("Tables:>> "+hist_table);
			sql.setLength(0);
			// PrintLog("SQL: "+sql+" Param: "+param);
			sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
			sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
			sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
			sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
			sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
			sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
			sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
			sql.append("CHEX_ISN, CHEX_BUDGET_ID, CHEX_BUNDLEID, ");
			sql.append("CHEX_RETURN_DATE, ");
			sql.append("CHEX_RETURN_REASON, ");
			sql.append("CHEX_RETURN_STATUS, ");
			sql.append("CHEX_BOFD_ABA, ");
			sql.append("CHEX_BOFD_DATE, ");
			sql.append("CHEX_EXTRA_1, ");
			sql.append("CHEX_EXTRA_2, ");
			sql.append("CHEX_ISSUE_DATE, ");
			sql.append("CHEX_PAYEEABA, ");
			sql.append("CHEX_PAYEEACCT, ");
			sql.append("CHEX_PAYEE, ");
			sql.append("CHEX_PAYEE_ADDR1, ");
			sql.append("CHEX_PAYEE_ADDR2, ");
			sql.append("CHEX_PAYEE_ADDR3, ");
			sql.append("CHEX_COMMENTS, ");
			sql.append("CHEX_REASON_CODES, ");
			sql.append("CHEX_CHECK_STATUS, CHEX_AMOUNT_OD, CHEX_IMAGE, ");
			sql.append("CHEX_SOURCE, MODTIME, ");
			sql.append("MODUSER, MODFUNC ");
			sql.append("FROM " + dbTable + "_" + hist_table.trim() + " ");
			sql.append(param);
			// PrintLog("SQL: "+sql);
			if (dbConn == null)
				PrintLog("------------------ NULL DBCONN -----------------");
			Statement statement	= dbConn.createStatement();
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				DepsDetail depsDetail	= new DepsDetail();
				// Here add the fields tp the CHEXDetail
				depsDetail.setAppl_date(appl_date);
				depsDetail.setDbUsed(depsSelector.getDbUsed());
				depsDetail.setChex_proc_date(result.getString("CHEX_PROC_DATE"));
				depsDetail.setChex_orig_account_num(result.getString("CHEX_ORIG_ACCOUNT_NUM"));
				depsDetail.setChex_orig_check_num(result.getString("CHEX_ORIG_CHECK_NUM"));
				depsDetail.setChex_account_num(result.getString("CHEX_ACCOUNT_NUM"));
				chexAcctNum	= result.getString("CHEX_ACCOUNT_NUM");
				depsDetail.setChex_check_num(result.getString("CHEX_CHECK_NUM"));
				depsDetail.setChex_routing_transit(result.getString("CHEX_ROUTING_TRANSIT"));
				chexAba	= result.getString("CHEX_ROUTING_TRANSIT");
				depsDetail.setChex_check_amount(result.getString("CHEX_CHECK_AMOUNT"));
				depsDetail.setChex_check_currency(result.getString("CHEX_CHECK_CURRENCY"));
				depsDetail.setChex_proc_control(result.getString("CHEX_PROC_CONTROL"));
				depsDetail.setChex_ext_proc_control(result.getString("CHEX_EXT_PROC_CONTROL"));
				depsDetail.setChex_image_locator(result.getString("CHEX_IMAGE_LOCATOR"));
				depsDetail.setChex_unique_isn(result.getString("CHEX_UNIQUE_ISN"));
				depsDetail.setChex_addenda_rec_flag(result.getString("CHEX_ADDENDA_REC_FLAG"));
				depsDetail.setChex_orig_inst_rte(result.getString("CHEX_ORIG_INST_RTE"));
				depsDetail.setChex_isn(result.getString("CHEX_ISN"));
				depsDetail.setChex_budget_id(result.getString("CHEX_BUDGET_ID"));
				depsDetail.setChex_bundleid(result.getString("CHEX_BUNDLEID"));
				depsDetail.setChex_return_date(result.getString("CHEX_RETURN_DATE"));
				depsDetail.setChex_return_reason(result.getString("CHEX_RETURN_REASON"));
				depsDetail.setChex_return_status(result.getString("CHEX_RETURN_STATUS"));
				depsDetail.setChex_BOFD_aba(result.getString("CHEX_BOFD_ABA"));
				depsDetail.setChex_BOFD_date(result.getString("CHEX_BOFD_DATE"));
				if (result.getString("CHEX_EXTRA_1") == null) {
					depsDetail.setChex_extra_1(" ");
				} else {
					depsDetail.setChex_extra_1(result.getString("CHEX_EXTRA_1"));
				}
				if (result.getString("CHEX_EXTRA_2") == null) {
					depsDetail.setChex_extra_2(" ");
				} else {
					depsDetail.setChex_extra_2(result.getString("CHEX_EXTRA_2"));
				}
				depsDetail.setChex_issue_date(result.getString("CHEX_ISSUE_DATE"));
				depsDetail.setChex_payeeaba(result.getString("CHEX_PAYEEABA"));
				depsDetail.setChex_payeeacct(result.getString("CHEX_PAYEEACCT"));
				depsDetail.setChex_payee(result.getString("CHEX_PAYEE"));
				depsDetail.setChex_payee_addr1(result.getString("CHEX_PAYEE_ADDR1"));
				depsDetail.setChex_payee_addr2(result.getString("CHEX_PAYEE_ADDR2"));
				depsDetail.setChex_payee_addr3(result.getString("CHEX_PAYEE_ADDR3"));
				if (payerName.equals("") || payerName.equals(" ")) {
					try {
						payerName	= dpUtil.GetPayerName(dbConn,chexAba,chexAcctNum);
						if (payerName.length() < 1) {
							payerName	= " ";
						} else {
							if (payerName.length() > 35) {
								payerName	= payerName.substring(0,35);
							}
						}
					} catch (Exception e) {
						payerName	= " ";
						PrintLog("Exception getting payer name for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
					}
				}
				try {
					payerCountry= dpUtil.GetPayerCountry(dbConn, chexAba, chexAcctNum);
					if (payerCountry == null) {
						payerCountry	= "US";
					} else {
						if (payerCountry.equals("") || payerCountry.equals(" ")) {
							payerCountry	= "US";
						}
					}
				} catch (Exception e) {
					payerCountry	= "  ";
					PrintLog("Exception getting payer country for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
				}
				depsDetail.setChexPayerName(payerName);
				depsDetail.setChexPayerCountry(payerCountry);
				depsDetail.setChex_comments(result.getString("CHEX_COMMENTS"));
				depsDetail.setChex_reason_codes(result.getString("CHEX_REASON_CODES"));
				depsDetail.setChex_check_status(result.getString("CHEX_CHECK_STATUS"));
				depsDetail.setChex_amount_od(result.getString("CHEX_AMOUNT_OD"));
				depsDetail.setChex_image(result.getString("CHEX_IMAGE"));
				depsDetail.setChex_source(result.getString("CHEX_SOURCE"));
				depsDetail.setModtime(result.getString("MODTIME"));
				depsDetail.setModuser(result.getString("MODUSER"));
				depsDetail.setModfunc(result.getString("MODFUNC"));
				depsDetail.setChex_modparam();
				chexBeans.add(depsDetail);
				row_count++;
			}
			statement.close();
			result.close();
		}
		depsSelector.setCheckrows(chexBeans);
		return (row_count);
	}
	//
	// Generic Get Log Rows
	//
	public int GetDepsLogRows(Connection dbConn, DepsSelector depsSelector)
			throws Exception {
		moduleName		= "GetDepsLogRows: ";
		String dbTable	= depsSelector.getDbTable().toLowerCase();
		String dbUsed	= depsSelector.getDbUsed();
		String payerName	= "";
		String payerCountry	= "";
		String chexAba		= "";
		String chexAcctNum	= "";
		// PrintLog("DBUsed "+dbUsed);
		//PrintLog("param: " + param);
		if (param.equals("")) {
			param = depsSelector.GetLogHistParams();
			//PrintLog("param: " + param);
		}
		StringBuffer sql	= new StringBuffer();
		row_count = 0;
		// ------------------- DO BOT REMOVE -------------------------
		// FOR ORACLE TO SUPRESS THE null STRING RETURNED FOR NULL COLUMNS
		// ---------------------------------------------------------------
		sql.setLength(0);
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, CHEX_BUNDLEID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, ");
		sql.append("CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEEABA, ");
		sql.append("CHEX_PAYEEACCT, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		sql.append("CHEX_CHECK_STATUS, CHEX_AMOUNT_OD, CHEX_IMAGE, ");
		sql.append("CHEX_SOURCE, MODTIME, ");
		sql.append("MODUSER, MODFUNC ");
		sql.append("FROM " + dbTable + " ");
		sql.append(param);
		//PrintLog(" DepsChexUtil: SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		//PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			DepsDetail depsDetail	= new DepsDetail();
			// Here add the fields tp the DepsDetail bean
			depsDetail.setDbUsed(dbUsed);
			depsDetail.setChex_proc_date(result.getString("CHEX_PROC_DATE"));
			depsDetail.setChex_orig_account_num(result.getString("CHEX_ORIG_ACCOUNT_NUM"));
			depsDetail.setChex_orig_check_num(result.getString("CHEX_ORIG_CHECK_NUM"));
			depsDetail.setChex_account_num(result.getString("CHEX_ACCOUNT_NUM"));
			chexAcctNum	= result.getString("CHEX_ACCOUNT_NUM");
			depsDetail.setChex_check_num(result.getString("CHEX_CHECK_NUM"));
			depsDetail.setChex_routing_transit(result.getString("CHEX_ROUTING_TRANSIT"));
			chexAba	= result.getString("CHEX_ROUTING_TRANSIT");
			depsDetail.setChex_check_amount(result.getString("CHEX_CHECK_AMOUNT"));
			depsDetail.setChex_check_currency(result.getString("CHEX_CHECK_CURRENCY"));
			depsDetail.setChex_proc_control(result.getString("CHEX_PROC_CONTROL"));
			depsDetail.setChex_ext_proc_control(result.getString("CHEX_EXT_PROC_CONTROL"));
			depsDetail.setChex_image_locator(result.getString("CHEX_IMAGE_LOCATOR"));
			depsDetail.setChex_unique_isn(result.getString("CHEX_UNIQUE_ISN"));
			depsDetail.setChex_addenda_rec_flag(result.getString("CHEX_ADDENDA_REC_FLAG"));
			depsDetail.setChex_orig_inst_rte(result.getString("CHEX_ORIG_INST_RTE"));
			depsDetail.setChex_isn(result.getString("CHEX_ISN"));
			depsDetail.setChex_budget_id(result.getString("CHEX_BUDGET_ID"));
			depsDetail.setChex_bundleid(result.getString("CHEX_BUNDLEID"));
			depsDetail.setChex_return_date(result.getString("CHEX_RETURN_DATE"));
			depsDetail.setChex_return_reason(result.getString("CHEX_RETURN_REASON"));
			depsDetail.setChex_return_status(result.getString("CHEX_RETURN_STATUS"));
			depsDetail.setChex_BOFD_aba(result.getString("CHEX_BOFD_ABA"));
			depsDetail.setChex_BOFD_date(result.getString("CHEX_BOFD_DATE"));
			if (result.getString("CHEX_EXTRA_1") == null) {
				depsDetail.setChex_extra_1(" ");
			} else {
				depsDetail.setChex_extra_1(result.getString("CHEX_EXTRA_1"));
			}
			if (result.getString("CHEX_EXTRA_2") == null) {
				depsDetail.setChex_extra_2(" ");
			} else {
				depsDetail.setChex_extra_2(result.getString("CHEX_EXTRA_2"));
			}
			depsDetail.setChex_issue_date(result.getString("CHEX_ISSUE_DATE"));
			depsDetail.setChex_payeeaba(result.getString("CHEX_PAYEEABA"));
			depsDetail.setChex_payeeacct(result.getString("CHEX_PAYEEACCT"));
			depsDetail.setChex_payee(result.getString("CHEX_PAYEE"));
			depsDetail.setChex_payee_addr1(result.getString("CHEX_PAYEE_ADDR1"));
			depsDetail.setChex_payee_addr2(result.getString("CHEX_PAYEE_ADDR2"));
			depsDetail.setChex_payee_addr3(result.getString("CHEX_PAYEE_ADDR3"));
			if (payerName.equals("") || payerName.equals(" ")) {
				try {
					payerName	= dpUtil.GetPayerName(dbConn,chexAba,chexAcctNum);
					if (payerName.length() < 1) {
						payerName	= " ";
					} else {
						if (payerName.length() > 35) {
							payerName	= payerName.substring(0,35);
						}
					}
				} catch (Exception e) {
					payerName	= " ";
					PrintLog("Exception getting payer name for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
				}
			}
			try {
				payerCountry= dpUtil.GetPayerCountry(dbConn, chexAba, chexAcctNum);
				if (payerCountry == null) {
					payerCountry	= "US";
				} else {
					if (payerCountry.equals("") || payerCountry.equals(" ")) {
						payerCountry	= "US";
					}
				}
			} catch (Exception e) {
				payerCountry	= "  ";
				PrintLog("Exception getting payer country for : Payer ABA: "+ chexAba + " Payer Account: " + chexAcctNum + e);
			}
			depsDetail.setChexPayerName(payerName);
			depsDetail.setChexPayerCountry(payerCountry);
			depsDetail.setChex_comments(result.getString("CHEX_COMMENTS"));
			depsDetail.setChex_reason_codes(result.getString("CHEX_REASON_CODES"));
			depsDetail.setChex_check_status(result.getString("CHEX_CHECK_STATUS"));
			depsDetail.setChex_amount_od(result.getString("CHEX_AMOUNT_OD"));
			depsDetail.setChex_image(result.getString("CHEX_IMAGE"));
			depsDetail.setChex_source(result.getString("CHEX_SOURCE"));
			depsDetail.setModtime(result.getString("MODTIME"));
			depsDetail.setModuser(result.getString("MODUSER"));
			depsDetail.setModfunc(result.getString("MODFUNC"));
			depsDetail.setChex_modparam();
			chexBeans.add(depsDetail);
			row_count++;
		}
		//PrintLog("Rows Read " + row_count);
		depsSelector.setCheckrows(chexBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int CancelDeposits (Connection dbConn, String dbUsed, DepsSelector depsSelector)
			throws Exception {
		moduleName				= "CancelDeposits: ";
		String dbTable			= depsSelector.getDbTable();
		//String applDate			= depsSelector.getApplDate();
		String userId			= depsSelector.getUserId();
		String modFunc			= depsSelector.getModFunc();
		String chexAcctNum		= depsSelector.getCh_from_account();
		StringBuffer sql		= new StringBuffer();
		Statement statement		= null;
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT into " + dbTable + "LOG ");
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, CHEX_BUNDLEID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEEABA, ");
		sql.append("CHEX_PAYEEACCT, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		sql.append("'C', ");
		sql.append("CHEX_AMOUNT_OD, ");
		sql.append("CHEX_IMAGE, ");
		sql.append("CHEX_SOURCE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate,");
		}
		sql.append("'" + userId + "', ");
		sql.append("'" + modFunc + "' ");
		sql.append("FROM " + dbTable);
		sql.append("    WHERE CHEX_PAYEEACCT='"+ chexAcctNum+"' ");
		try {
			statement	= dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update " + dbTable + " set ");
			sql.append("CHEX_CHECK_STATUS='C', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    MODTIME=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    MODTIME=sysdate, ");
			}
			sql.append("    MODUSER='" + userId + "', ");
			sql.append("    MODFUNC='" + modFunc + "'");
			sql.append("    WHERE CHEX_PAYEEACCT='"+ chexAcctNum+"' ");
			try {
				statement	= dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				return (1);
			} catch (SQLException e) {
				PrintLog("Error Updating Chex ->" + e.toString());
				depsSelector.setErrorVec("Deposit Table", "error.updating");
				dbConn.rollback();
			}
		} catch (SQLException e) {
			PrintLog("Error Updating Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			depsSelector.setErrorVec("Deposit Table", "error.updating");
			dbConn.rollback();
		}
		statement.close();
		return (0);
	}
	//
	//
	public int AuthRejectDeps(Connection dbConn, DepsDetail depsDetail,
	// ActionErrors errors,
			String user_name, int auth_rej_flag, String mod_func)
			throws Exception {
		moduleName	= "AuthRejectChex: ";
		DepsSelector depsSelector	= new DepsSelector();
		StringBuffer sql			= new StringBuffer();
		Statement statement			= null;
		depsDetail.clearNulls();
		String dbUsed				= depsDetail.getDbUsed();
		String acct_num				= depsDetail.getChex_account_num();
		String check_num			= depsDetail.getChex_check_num();
		String check_unique_isn		= depsDetail.getChex_unique_isn();
		String comments				= depsDetail.getChex_comments();
		comments					= comments.replaceAll("'", "''");
		String applDate				= depsDetail.getChex_proc_date();
		// acctSelector.setDbUsed(dbUsed);
		sql.setLength(0);
		sql.append("INSERT into deps_chex_log_" + applDate.substring(0, 6)+ " ");
		sql.append("SELECT CHEX_PROC_DATE, CHEX_ORIG_ACCOUNT_NUM, ");
		sql.append("CHEX_ORIG_CHECK_NUM, CHEX_ACCOUNT_NUM, ");
		sql.append("CHEX_CHECK_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("CHEX_CHECK_CURRENCY, CHEX_CHECK_AMOUNT, ");
		sql.append("CHEX_PROC_CONTROL, CHEX_EXT_PROC_CONTROL, ");
		sql.append("CHEX_IMAGE_LOCATOR, CHEX_UNIQUE_ISN, ");
		sql.append("CHEX_ADDENDA_REC_FLAG, CHEX_ORIG_INST_RTE, ");
		sql.append("CHEX_ISN, CHEX_BUDGET_ID, CHEX_BUNDLEID, ");
		sql.append("CHEX_RETURN_DATE, ");
		sql.append("CHEX_RETURN_REASON, ");
		sql.append("CHEX_RETURN_STATUS, ");
		sql.append("CHEX_BOFD_ABA, ");
		sql.append("CHEX_BOFD_DATE, ");
		sql.append("CHEX_EXTRA_1, ");
		sql.append("CHEX_EXTRA_2, CHEX_ISSUE_DATE, ");
		sql.append("CHEX_PAYEEABA, ");
		sql.append("CHEX_PAYEEACCT, ");
		sql.append("CHEX_PAYEE, ");
		sql.append("CHEX_PAYEE_ADDR1, ");
		sql.append("CHEX_PAYEE_ADDR2, ");
		sql.append("CHEX_PAYEE_ADDR3, ");
		sql.append("CHEX_COMMENTS, ");
		sql.append("CHEX_REASON_CODES, ");
		if (auth_rej_flag == 1) {
			sql.append("'Z', ");
		} else {
			sql.append("'R', ");
		}
		sql.append("CHEX_AMOUNT_OD, ");
		sql.append("CHEX_IMAGE, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM deps_chex ");
		sql.append("    WHERE CHEX_ACCOUNT_NUM='" + acct_num + "' AND ");
		sql.append("          CHEX_CHECK_NUM='" + check_num + "' AND ");
		sql.append("          CHEX_UNIQUE_ISN='" + check_unique_isn + "'");
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
			sql.setLength(0);
			sql.append("update deps_chex set ");
			if (auth_rej_flag == 1) {
				sql.append("CHEX_CHECK_STATUS='Z', ");
			} else {
				sql.append("CHEX_CHECK_STATUS='R', ");
			}
			if (dbUsed.equals("MySQL")) {
				sql.append("    MODTIME=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    MODTIME=sysdate, ");
			}
			sql.append("    CHEX_COMMENTS='" + comments + "', ");
			sql.append("    MODUSER='" + user_name + "', ");
			sql.append("    MODFUNC='" + mod_func + "'");
			sql.append("    WHERE CHEX_ACCOUNT_NUM='" + acct_num + "' AND ");
			sql.append("          CHEX_CHECK_NUM='" + check_num + "' AND ");
			sql.append("          CHEX_UNIQUE_ISN='" + check_unique_isn + "'");
			try {
				statement = dbConn.createStatement();
				statement.executeUpdate(sql.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				statement.close();
				return (1);
			} catch (SQLException e) {
				PrintLog("Error Updating  ->" + e.toString());
				//
				// Remember to add depsSelector as a parameter to be passed
				// to trap the error messaeges
				//
				depsSelector.setErrorVec("Deposit Table", "error.updating");
			}
		} catch (SQLException e) {
			PrintLog("Error Updating Chex ->" + e.toString());
			PrintLog("SQL >> " + sql);
			depsSelector.setErrorVec("Deposit Log", "error.updating");
		}
		statement.close();
		return (0);
	}
	//
	// Here update the History record for return/Undo-reurn
	//
	public int ExtractDepsRows(Connection dbConn, DepsSelector depsSelector)
			throws Exception {
		//
		moduleName					= "ExtractChexRows: ";
		SysadControlUtil ctlUtil	= new SysadControlUtil();
		ControlSelector ctlSelector	= new ControlSelector();
		ControlDetail ctlDetail		= new ControlDetail();
		int rowCount				= depsSelector.getDetail_count();
		DepsDetail depsDetail[]		= new DepsDetail[rowCount];
		DepsDetail cD				= new DepsDetail();
		String prodId				= "A";
		String extractFileName		= "";
		String fileTime				= "";
		DateFormat newFmt2			= null;
		String outputPath			= "";
		String outputChex			= "";
		//
		byte[] newText;
		FileOutputStream outExtract	= null;
		PrintStream psExtract		= null;
		//
		try {
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail	= ctlSelector.getARow();
		} catch (Throwable t) {
			PrintLog("Error Getting Control ->" + t.toString());
		}
		outputPath		= ctlDetail.getLocOutputDir() + "deposits/";
		//
		newFmt2			= new SimpleDateFormat("yyyyMMddHHmmss");
		fileTime		= newFmt2.format(java.util.Calendar.getInstance().getTime());
		extractFileName	= outputPath + "ExtractDeposits_" + fileTime;
		//PrintLog("Extract File Name -> " + extractFileName);
		outExtract		= new FileOutputStream(extractFileName + "_temp");
		psExtract		= new PrintStream(outExtract);
		//
		depsDetail		= depsSelector.getCheckrows();
		outputChex		= ("Check Routing Number" + "\t" + "Check Account Number" + "\t" +
							"Check Number" + "\t" +
							"Amount" + "\t" + "Date Processed" + "\t" + "Payee Name" + "\t" +
							"Payee Account");
		newText			= outputChex.getBytes();
		outExtract.write(newText, 0, outputChex.length());
		psExtract.println();
		outputChex = "";
		for (int idx = 0; idx <= depsDetail.length - 1; idx++) {
			cD = depsDetail[idx];
			// Here get the fields from the DepsDetail bean
			//String chex_account_num			= cD.getChex_account_num();
			String chex_routing_transit		= cD.getChex_routing_transit();
			String chex_orig_account_num	= cD.getChex_orig_account_num();
			String chex_orig_check_num		= cD.getChex_orig_check_num();
			// String chex_currency		= cD.getChex_currency();
			String chex_check_amount		= cD.getChex_check_amount();
			// String chex_issue_date		= cD.getChex_issue_date_disp();
			String chex_payee				= cD.getChex_payee();
			String chex_payee_account		= cD.getChex_extra_1();
			// String chex_payee_addr1		= cD.getChex_payee_addr1();
			// String chex_payee_addr2		= cD.getChex_payee_addr2();
			String chex_payee_addr3		= cD.getChex_payee_addr3();
			String chex_proc_date			= cD.getChex_proc_date_disp();
			outputChex = (chex_routing_transit + "\t" + chex_orig_account_num + "\t" +
						chex_orig_check_num + "\t" +
						// chex_currency + "\t" +
						chex_check_amount + "\t" + chex_proc_date + "\t" +
						// chex_issue_date + "\t" +
						chex_payee + "\t" +
						chex_payee_account + "\t" +
						// chex_payee_addr2 + "\t" +
						chex_payee_addr3 + "\t"
						// chex_check_status
						);
			newText		= outputChex.getBytes();
			outExtract.write(newText, 0, outputChex.length());
			psExtract.println();
			outputChex	= "";
		}
		outExtract.close();
		PrintLog("Will Rename " + extractFileName + "_temp to " + extractFileName + ".xls");
		File extractFile	= new File(extractFileName + "_temp");
		extractFile.renameTo(new File(extractFileName + ".xls"));
		return (row_count);
	}
	//
	// This is called by Payer Server/Maint function for updating the checks
	// after a payer has been added or modified.
	//
	public int InsertUpdateDeps(Connection dbConn, String dbTable, String dbLogTable,
								DepsSelector depsSelector) throws Exception {
		// Called by DepsDDAUtil to update the status from Posting Ready to Warehouse Ready
		moduleName				= "InsertUpdateDeps: ";
		ArrayList<DepsDetail> depsRows	= new ArrayList<DepsDetail>();
		DepsDetail detailRow	= new DepsDetail();
		userId					= depsSelector.getUserId();
		depsRows				= depsSelector.getChexRowsArray();
		//checkCount++;
		for (int i=0; i<depsRows.size(); i++) {
			detailRow		= depsRows.get(i);
			detailRow.setChex_check_status("Z");
			detailRow.setModuser(userId);
			detailRow.setModfunc("DDAPost");
			InsertUpdateDeps(dbConn, dbTable, dbLogTable, detailRow, 2);
		}
		return (1);
	}
	//
	public int InsertUpdateDeps(Connection dbConn, String dbTable, String dbLogTable,
			DepsDetail depsDetail, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		// GenUtil gUtil	= new GenUtil();
		moduleName				= "InsertUpdateDeps: ";
		double chex_amount_od_d	= 0.0;
		// Need the acct, posi and stop beans to validate for limit
		// stop and posi pay
		// PrintLog("In Update Chex");
		depsDetail.clearNulls();
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		//int ret_val			= 1; // Success
		String chex_cr_aba	= "";
		//String logHist		= "";
		//
		//depsDetail.ShowDetails();
		String dbUsed					= depsDetail.getDbUsed();
		//String appl_date				= depsDetail.getAppl_date();
		String chex_proc_date			= depsDetail.getChex_proc_date();
		String chex_orig_account_num	= depsDetail.getChex_orig_account_num().trim();
		String chex_orig_check_num		= depsDetail.getChex_orig_check_num();
		String chex_account_num			= depsDetail.getChex_account_num().trim();
		String chex_check_num			= depsDetail.getChex_check_num();
		String chex_routing_transit		= depsDetail.getChex_routing_transit();
		String chex_check_amount		= depsDetail.getChex_check_amount();
		//
		double chex_check_amount_d		= Double.parseDouble(chex_check_amount);
		String chex_check_currency		= depsDetail.getChex_check_currency();
		String chex_proc_control		= " ";
		String chex_ext_proc_control	= depsDetail.getChex_ext_proc_control();
		String chex_image_locator		= " ";
		String chex_unique_isn			= depsDetail.getChex_unique_isn();
		String chex_addenda_rec_flag	= " ";
		String chex_orig_inst_rte		= depsDetail.getChex_orig_inst_rte();
		String chex_isn					= " ";
		String chex_budget_id			= " ";
		String chex_bundleid			= depsDetail.getChex_bundleid();;
		String chex_return_date			= depsDetail.getChex_return_date();
		String chex_return_reason		= depsDetail.getChex_return_reason();
		String chex_return_status		= depsDetail.getChex_return_status();
		String chex_BOFD_aba			= depsDetail.getChex_BOFD_aba();
		String chex_BOFD_date			= depsDetail.getChex_BOFD_date();
		String chex_extra_1				= depsDetail.getChexPayerCountry();
		String chex_extra_2				= depsDetail.getChex_extra_2();
		String chex_issue_date			= depsDetail.getChex_issue_date();
		String chex_payeeAba			= depsDetail.getChex_payeeaba();
		String chex_payeeAcct			= depsDetail.getChex_payeeacct();
		String chex_payee				= depsDetail.getChex_payee();
		chex_payee						= chex_payee.replaceAll("'", "''");
		String chex_payee_addr1			= " ";
		String chex_payee_addr2			= " ";
		String chex_payee_addr3			= depsDetail.getChexPayerName();
		chex_payee_addr3				= chex_payee_addr3.replaceAll("'", "''");
		String chex_comments			= depsDetail.getChex_comments();
		chex_comments					= chex_comments.replaceAll("'", "''");
		String chex_reason_codes		= depsDetail.getChex_reason_codes();
		String chex_check_status		= depsDetail.getChex_check_status();
		String chex_amount_od			= depsDetail.getChex_amount_od();
		//PrintLog("chex_amount_od: '"+chex_amount_od+"'");
		if (chex_amount_od.equals("") || chex_amount_od.equals(" ")) {
			chex_amount_od		= "0";
			chex_amount_od_d	= Double.parseDouble(chex_amount_od);
		}
		if (!chex_amount_od.equals("") || chex_amount_od.equals(" ")) {
			chex_amount_od_d	= Double.parseDouble(chex_amount_od);
		}
		String chexImage				= depsDetail.getChex_image();
		String chexSource				= depsDetail.getChex_source();
		/*
		if (chexSource.equals("RDC") || chexSource.equals("LOCKBOX")) {
			logHist							= "";
		} else {
			logHist							= "_" + appl_date.substring(0, 6);
		}
		*/
		String chex_mod_user			= depsDetail.getModuser();
		String chex_mod_func			= depsDetail.getModfunc();
		//
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			sql.setLength(0);
			PrintLog("chex_account_num: "+chex_account_num+" chex_orig_account_num: "+chex_orig_account_num);
			PrintLog("chex_check_num: "+chex_check_num);
			sql.append("INSERT INTO " + dbTable.toLowerCase() + " VALUES (");
			sql.append("'" + chex_proc_date + "', ");
			sql.append("'" + chex_orig_account_num + "', ");
			sql.append("'" + chex_orig_check_num + "', ");
			sql.append("'" + chex_account_num + "', ");
			sql.append("'" + chex_check_num + "', ");
			sql.append("'" + chex_routing_transit + "', ");
			sql.append("'" + chex_check_currency + "', ");
			sql.append("'" + chex_check_amount_d + "', ");
			sql.append("'" + chex_proc_control + "', ");
			sql.append("'" + chex_ext_proc_control + "', ");
			sql.append("'" + chex_image_locator + "', ");
			sql.append("'" + chex_unique_isn + "', ");
			sql.append("'" + chex_addenda_rec_flag + "', ");
			sql.append("'" + chex_orig_inst_rte + "', ");
			sql.append("'" + chex_isn + "', ");
			sql.append("'" + chex_budget_id + "', ");
			sql.append("'" + chex_bundleid + "', ");
			sql.append("'" + chex_return_date + "', ");
			sql.append("'" + chex_return_reason + "', ");
			sql.append("'" + chex_return_status + "', ");
			sql.append("'" + chex_BOFD_aba + "', ");
			sql.append("'" + chex_BOFD_date + "', ");
			sql.append("'" + chex_extra_1 + "', ");
			sql.append("'" + chex_extra_2 + "', ");
			sql.append("'" + chex_issue_date + "', ");
			sql.append("'" + chex_payeeAba + "', ");
			sql.append("'" + chex_payeeAcct + "', ");
			sql.append("'" + chex_payee + "', ");
			sql.append("'" + chex_payee_addr1 + "', ");
			sql.append("'" + chex_payee_addr2 + "', ");
			sql.append("'" + chex_payee_addr3 + "', ");
			sql.append("'" + chex_comments + "', ");
			sql.append("'" + chex_reason_codes + "', ");
			sql.append("'" + chex_check_status + "', ");
			sql.append("'" + chex_amount_od_d + "', ");
			sql.append("'" + chexImage + "', ");
			sql.append("'" + chexSource.toUpperCase() + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL,");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate,");
			}
			sql.append("'" + chex_mod_user + "', ");
			sql.append("'" + chex_mod_func + "')");
			PrintLog("Deps Insert SQL ---> "+sql);
			//
			try {
				statement	= dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Deposit ->" + e.toString());
				PrintLog("Deposit Insert > Ins/Upd flag: " + ins_or_upd);
				PrintLog("Deposit Insert SQL ---> " + sql);
				dbConn.rollback();
				depsDetail.setErrorVec("Deposit Table", "error.inserting");
				statement.close();
				return (0);
			}
		} else {
			// mod_func = "Modify Chex";
			sql.setLength(0);
			sql.append("update " + dbTable.toLowerCase() + " ");
			sql.append("set CHEX_ACCOUNT_NUM='" + chex_account_num + "', ");
			sql.append("    CHEX_CHECK_NUM=" + chex_check_num + ", ");
			sql.append("    CHEX_ORIG_ACCOUNT_NUM='" + chex_orig_account_num + "', ");
			sql.append("    CHEX_ORIG_CHECK_NUM=" + chex_orig_check_num + ", ");
			sql.append("    CHEX_CHECK_AMOUNT=" + chex_check_amount_d + ", ");
			sql.append("    CHEX_REASON_CODES='" + chex_reason_codes + "', ");
			sql.append("    CHEX_CHECK_STATUS='" + chex_check_status + "', ");
			sql.append("    CHEX_ROUTING_TRANSIT='" + chex_routing_transit + "', ");
			/*
			 * sql.append("    CHEX_AMOUNT_OD="+chex_amount_od_d+", ");
			 */
			sql.append("    CHEX_ISSUE_DATE='"+chex_issue_date+"', ");
			sql.append("    CHEX_ORIG_INST_RTE='" + chex_cr_aba + "', ");
			sql.append("    CHEX_PAYEEACCT='" + chex_payeeAcct + "', ");
			sql.append("    CHEX_PAYEE='" + chex_payee + "', ");
			sql.append("    CHEX_EXTRA_1='" + chex_extra_1 + "', ");
			sql.append("    CHEX_PAYEE_ADDR1='" + chex_payee_addr1 + "', ");
			sql.append("    CHEX_PAYEE_ADDR2='" + chex_payee_addr2 + "', ");
			sql.append("    CHEX_PAYEE_ADDR3='" + chex_payee_addr3 + "', ");
			sql.append("    CHEX_COMMENTS='" + chex_comments + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("    MODTIME=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("    MODTIME=sysdate, ");
			}
			sql.append("    MODUSER='" + chex_mod_user + "', ");
			sql.append("    MODFUNC='" + chex_mod_func + "' ");
			sql.append("    WHERE CHEX_ORIG_ACCOUNT_NUM='" + chex_orig_account_num + "'");
			sql.append("    AND CHEX_ORIG_CHECK_NUM='" + chex_orig_check_num + "'");
			sql.append("    AND CHEX_UNIQUE_ISN='" + chex_unique_isn + "'");
			/*
			 * sql.append("    WHERE CHEX_ORIG_ACCOUNT_NUM='"+chex_orig_account_num
			 * +"' AND ");
			 * sql.append("          CHEX_ORIG_CHECK_NUM='"+chex_orig_check_num
			 * +"' AND ");
			 * sql.append("          CHEX_UNIQUE_ISN='"+chex_unique_isn+"'");
			 */
			//PrintLog("Deposits Update SQL ---> "+sql);
			try {
				statement	= dbConn.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Deposit ->" + e.toString());
				PrintLog("Deposit Update > Ins/Upd flag: " + ins_or_upd);
				PrintLog("Deposit Update SQL ---> " + sql);
				dbConn.rollback();
				depsDetail.setErrorVec("Deposit Table", "error.updating");
				statement.close();
				return (0);
			}
		}
		statement.close();
		// result.close();
		sql.setLength(0);
		sql.append("INSERT INTO " + dbLogTable.toLowerCase() + " VALUES (");
		sql.append("'" + chex_proc_date + "', ");
		sql.append("'" + chex_orig_account_num + "', ");
		sql.append("'" + chex_orig_check_num + "', ");
		sql.append("'" + chex_account_num + "', ");
		sql.append("'" + chex_check_num + "', ");
		sql.append("'" + chex_routing_transit + "', ");
		sql.append("'" + chex_check_currency + "', ");
		sql.append("'" + chex_check_amount_d + "', ");
		sql.append("'" + chex_proc_control + "', ");
		sql.append("'" + chex_ext_proc_control + "', ");
		sql.append("'" + chex_image_locator + "', ");
		sql.append("'" + chex_unique_isn + "', ");
		sql.append("'" + chex_addenda_rec_flag + "', ");
		sql.append("'" + chex_orig_inst_rte + "', ");
		sql.append("'" + chex_isn + "', ");
		sql.append("'" + chex_budget_id + "', ");
		sql.append("'" + chex_bundleid + "', ");
		sql.append("'" + chex_return_date + "', ");
		sql.append("'" + chex_return_reason + "', ");
		sql.append("'" + chex_return_status + "', ");
		sql.append("'" + chex_BOFD_aba + "', ");
		sql.append("'" + chex_BOFD_date + "', ");
		sql.append("'" + chex_extra_1 + "', ");
		sql.append("'" + chex_extra_2 + "', ");
		sql.append("'" + chex_issue_date + "', ");
		sql.append("'" + chex_payeeAba + "', ");
		sql.append("'" + chex_payeeAcct + "', ");
		sql.append("'" + chex_payee + "', ");
		sql.append("'" + chex_payee_addr1 + "', ");
		sql.append("'" + chex_payee_addr2 + "', ");
		sql.append("'" + chex_payee_addr3 + "', ");
		sql.append("'" + chex_comments + "', ");
		sql.append("'" + chex_reason_codes + "', ");
		sql.append("'" + chex_check_status + "', ");
		sql.append("'" + chex_amount_od_d + "', ");
		sql.append("'" + chexImage + "', ");
		sql.append("'" + chexSource + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL,");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + chex_mod_user + "', ");
		sql.append("'" + chex_mod_func + "')");
		//PrintLog("Log Insert SQL  "+sql);
		try {
			statement	= dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Deps Chex Log ->" + e.toString());
			PrintLog("Log Insert SQL ---> " + sql);
			dbConn.rollback();
			depsDetail.setErrorVec("Deposit Log", "error.updating");
			statement.close();
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		depsDetail.setChex_reason_codes(chex_reason_codes);
		depsDetail.setChex_check_status(chex_check_status);
		return (1);
	}
	//
	public int GetDepsPostingRows(Connection dbConn, AcctentrySelector acctentrySelector)
			throws Exception {
		moduleName				= "GetDepsRows: ";
		double amountTotal		= 0;
		int		rowCount		= 0;
		String amountTotal_s	= "";
		String timeStamp		= "";
		String dbUsed			= acctentrySelector.getDbUsed();
		//String accessFlag		= acctentrySelector.getAccessFlag();
		//String actionFlag		= acctentrySelector.getActionFlag();
		String chexSource		= "RP";
		String chexSourceSel	= acctentrySelector.getChexSource();
		if (chexSourceSel.equals("ALL")) {
			chexSourceSel	= "";
		}
		//
		java.util.Date today	= new java.util.Date();
		SimpleDateFormat sdf	= new SimpleDateFormat("yyMMdd-HHmmssSS");
		
		//timeStamp				= sdf.format(today.getTime());
		//
		StringBuffer sql		= new StringBuffer();
		DecimalFormat df		= new DecimalFormat("###,###.00");
		row_count				= 0;
		sql.setLength(0);
		/*
		SELECT CHEX_PAYEEACCT, CHEX_PAYEEABA, SUM(CHEX_CHECK_AMOUNT) 
		FROM deps_chex where (CHEX_SOURCE='RDC' AND CHEX_CHECK_STATUS='P') 
		GROUP BY (CHEX_PAYEEABA, CHEX_PAYEEACCT, CHEX_CHECK_STATUS) 
 		ORDER BY CHEX_PAYEEABA, CHEX_PAYEEACCT, CHEX_CHECK_STATUS
		*/
		sql.append("SELECT CHEX_SOURCE, CHEX_PROC_DATE, CHEX_PAYEEACCT, CHEX_PAYEEABA, ");
		sql.append("CHEX_ACCOUNT_NUM, CHEX_ROUTING_TRANSIT, ");
		sql.append("SUM(CHEX_CHECK_AMOUNT) AS pAmt,COUNT(*) ");
		sql.append("FROM deps_chex ");
		if (chexSourceSel.equals("")) {
			sql.append("WHERE CHEX_CHECK_STATUS='P' ");
		} else {
			sql.append("WHERE (CHEX_CHECK_STATUS='P'  AND CHEX_SOURCE='"+chexSourceSel+"') ");
		}
		sql.append("GROUP BY (CHEX_PAYEEABA, CHEX_PAYEEACCT, CHEX_CHECK_STATUS, CHEX_PROC_DATE, ");
		sql.append("CHEX_SOURCE, CHEX_ACCOUNT_NUM, CHEX_ROUTING_TRANSIT) ");
		sql.append("ORDER BY CHEX_PAYEEABA, CHEX_PAYEEACCT");
		//PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		try {
			Statement statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			ResultSet result	= statement.executeQuery(sql.toString());
			while (result.next()) {
				AcctentryDetail postingDetail	= new AcctentryDetail();
				postingDetail.setDbUsed(dbUsed);
				postingDetail.setPostingBusinessdate(result.getString("CHEX_PROC_DATE"));
				postingDetail.setPostingApplPrefix(chexSource);
				postingDetail.setPostingBranch("150");
				postingDetail.setPostingSeqNum("");
				postingDetail.setPostingRecStatus("2");
				postingDetail.setPostingAmount(result.getString("SUM(CHEX_CHECK_AMOUNT)"));
				postingDetail.setPostingCurrency("USD");
				postingDetail.setPostingDebitAcct("150930161500USD");
				postingDetail.setPostingCreditAcct(result.getString("CHEX_PAYEEACCT"));
				postingDetail.setPostingDebitValueDate(result.getString("CHEX_PROC_DATE"));
				postingDetail.setPostingCreditValueDate("");
				postingDetail.setPostingReasonCode("");
				postingDetail.setPostingReqExeDate("");
				postingDetail.setPostingTranRefNum("");
				postingDetail.setPostingDetailsOfPayment("TOTAL CHECK DEPOSITS");
				//postingDetail.setPostingBBI("");
				postingDetail.setPostingTotalDebits(result.getString("COUNT(*)"));
				postingDetail.setPostingTotalCredits(result.getString("COUNT(*)"));
				timeStamp				= sdf.format(today.getTime());
				postingDetail.setPostingTimeStamp(timeStamp);
				//postingDetail.setPostingBookingText("");
				postingDetail.setPostingOriginalCurrency("USD");
				postingDetail.setPostingOriginalAmount(result.getString("SUM(CHEX_CHECK_AMOUNT)"));
				//postingDetail.setPostingCheckNum("");
				//postingDetail.setPostingBeneficiary("");
				//postingDetail.setPostingOBKName("");
				//postingDetail.setPostingOBKAddress("");
				//postingDetail.setPostingLocalKey("");
				//postingDetail.setPostingFiller("");
				postingBeans.add(postingDetail);
				rowCount++;
			}
			amountTotal_s	= df.format(amountTotal);
			acctentrySelector.setDetailAmount(amountTotal_s);
			acctentrySelector.setPostingRows(postingBeans);
			acctentrySelector.setDetail_count(row_count);
			statement.close();
			result.close();
			return (rowCount);
		} catch (SQLException e) {
			PrintLog("Error Getting Authorize" + e.toString());
			PrintLog("Get AuthorizeChex " + sql);
			return (0);
		}
	}
}
