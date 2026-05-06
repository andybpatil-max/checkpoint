package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.*;

//import org.apache.struts.action.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
//import com.webfiche.checkpoint.inclear.beans.*;

@Service
public class InclEodUtil {
	private String moduleName	= "> InclEodUtil.EODSetEodParams: ";
	SysadControlUtil icUtil		= new SysadControlUtil();
	private ControlSelector ctlSelector;
	private ControlDetail ctlDetailA;
	private ControlDetail ctlDetailC;
	private ControlDetail ctlDetailD;
	private String prodIdA			= "A";
	private String prodIdC			= "C";
	private String prodIdD			= "D";
	//
	public InclEodUtil() {
	}
	//
	private void PrintLog (String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() + moduleName + logMsg); 
		//"Error Getting Control ->" + t.toString());

	}
	//
	public void EODSetEodParams(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName		= "> InclEodUtil.EODSetEodParams: ";
		PrintLog(" Entering EODSetEodParams");
		StringBuffer sql		= new StringBuffer();
		String temp				= "";
		//String prodIdA			= "A";
		//String prodIdC			= "C";
		int today_yyyy			= 0;
		int today_mm			= 0;
		int today_dd			= 0;
		int months_back			= 0;
		int next_biz_mm			= 0;
		String holi_year_curr	= "";
		String one_holiday		= "";
		String dbUsed			= "";
		String prior_date		= "";
		String next_biz_date	= "";
		ResultSet result;
		Statement statement;
		//
		//
		SysadControlUtil icUtil		= new SysadControlUtil();
		ctlSelector		= new ControlSelector();
		ctlDetailA		= new ControlDetail();
		ctlDetailC		= new ControlDetail();
		//InclEODParams inclEODParams	= new InclEODParams();
		//
		try {
			icUtil.GetControlRow(dbConn, ctlSelector,prodIdA);
			ctlDetailA	= ctlSelector.getARow();
			icUtil.GetControlRow(dbConn, ctlSelector, prodIdC);
			ctlDetailC	= ctlSelector.getARow();
		} catch (Throwable t) {
			PrintLog("Error Getting Control ->" + t.toString());
		}
		eodParams.setProduct_id(ctlDetailC.getProductId());
		eodParams.setOur_aba(ctlDetailC.getOurAba());
		eodParams.setBankId(ctlDetailC.getBankId());
		eodParams.setEod_flag(ctlDetailC.getEodFlag());
		eodParams.setBod_flag(ctlDetailC.getBodFlag());
		eodParams.setFile_loaded(ctlDetailC.getFile_loaded());
		eodParams.setFileLoaded1(ctlDetailC.getFile_loaded_1());
		eodParams.setFileLoaded2(ctlDetailC.getFile_loaded_2());
		eodParams.setFileLoaded3(ctlDetailC.getFile_loaded_3());
		eodParams.setFileLoaded4(ctlDetailC.getFile_loaded_4());
		eodParams.setFileLoaded5(ctlDetailC.getFile_loaded_5());
		eodParams.setToday(ctlDetailC.getApplDate());
		eodParams.setPrev_biz_date(ctlDetailC.getPrevBizDate());
		eodParams.setNext_biz_date(ctlDetailC.getNextBizDate());
		// From Control row A
		eodParams.setCurrency(ctlDetailA.getDefCurr());
		eodParams.setReportdir(ctlDetailA.getLocOutputDir() + "incl/");
		eodParams.setDatadir(ctlDetailA.getLocOutputDir() + "gps/");
		eodParams.setImagedir(ctlDetailA.getImgBaseDir());
		eodParams.setRepHeader(ctlDetailA.getSysRepHeader());
		//
		next_biz_date	= eodParams.getNext_biz_date();
		temp			= eodParams.getToday();
		today_yyyy		= Integer.parseInt(temp.substring(0, 4));
		today_mm		= Integer.parseInt(temp.substring(4, 6));
		today_dd		= Integer.parseInt(temp.substring(6));
		next_biz_mm		= Integer.parseInt(next_biz_date.substring(4, 6));

		PrintLog(" Today_mm: " + today_mm + " Next MM: " + next_biz_mm);
		if (today_mm != next_biz_mm) {
			eodParams.setCreateNewHistory("N");
		} else {
			// here check if
			temp = ("select count(*) from incl_chex_M_" + temp.substring(0, 6));
			try {
				statement	= dbConn.createStatement();
				result		= statement.executeQuery(temp.toString());
			} catch (Throwable t) {
				PrintLog(": Setting History " + temp);
				PrintLog(" " + t);
				eodParams.setCreateNewHistory("Y");
			}
		}
		eodParams.setCreateNewHistory("N");
		months_back	= 1;
		prior_date	= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months_back);
		eodParams.setOne_m_back(prior_date);
		//
		// Here set the One month back date as a purge paramater for the various
		// tables
		//
		eodParams.setPosi_pay_purge(prior_date);
		eodParams.setChex_log_purge(prior_date);
		eodParams.setAcct_log_purge(prior_date);
		eodParams.setPosi_log_purge(prior_date);
		eodParams.setStop_log_purge(prior_date);
		months_back		= 3;
		prior_date		= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months_back);
		eodParams.setThree_m_back(prior_date);
		months_back		= 6;
		prior_date		= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months_back);
		eodParams.setSix_m_back(prior_date);
		months_back		= 12;
		prior_date		= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months_back);
		eodParams.setYear_back(prior_date);
		// Get holidays
		temp			= eodParams.getNext_biz_date();
		holi_year_curr	= temp.substring(0, 4) + eodParams.getCurrency();
		sql.setLength(0);
		sql.append("SELECT HOLIDAYS_YEAR_CURR, HOLIDAYS_DATES, ");
		sql.append("HOLIDAYS_LAST_MODIFIED, ");
		sql.append("HOLIDAYS_MOD_USER, HOLIDAYS_MOD_FUNC ");
		sql.append("FROM holidays ");
		sql.append("WHERE HOLIDAYS_YEAR_CURR='" + holi_year_curr + "' ");
		// Prepare the SELECT statement.
		statement		= dbConn.createStatement();
		// PrintLog(": Getting Holidays");
		result			= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("HOLIDAYS_DATES");
		}
		statement.close();
		result.close();
		while (temp.length() > 0) {
			one_holiday	= holi_year_curr.substring(0, 4) + temp.substring(0, 4);
			PrintLog(" Holidays: " + one_holiday);
			eodParams.setHolidays(one_holiday);
			temp		= temp.substring(4, temp.length());
		}
		//
		dbUsed	= eodParams.getDbUsed();
		//eodParams.ShowDetails();
		PrintLog("Exiting EODSETPARAMS dbUsed " + dbUsed);
		PrintLog("Create History " + eodParams.getCreateNewHistory());
	}

	public int EODPerform_EOD(Connection dbConn, InclEODParams eodParams) {
		moduleName			= "> InclEodUtil.EODPerform_EOD: ";
		ArrayList<String> histList	= new ArrayList<String>();
		ArrayList<String> histPList	= new ArrayList<String>();
		String create_history		= eodParams.getCreateNewHistory();
		String dateToday			= eodParams.getToday();
		//String bankId				= eodParams.getBankId();
		try {
			// dbConn.setAutoCommit(false);
			if (create_history.equals("Y")) {
				//
				// Set up the params for generating the Monthly image PDF files.
				//
				histList.add(dateToday.substring(0, 6));
				histPList.add("");
				histPList.add("");
				histPList.add("");
				histPList.add("");
				histPList.add("");
				histPList.add("");
				histPList.add("ALL");
				histPList.add(dateToday.substring(0, 6));
				histPList.add("NONE");
				histPList.add("");
				histPList.add("");
				try {
					// System.out.println(java.util.Calendar.getInstance().getTime()+
					// moduleName+"EODCreateHistoryTable ");
					EODCreateHistoryTable(dbConn, eodParams);
				} catch (Throwable t) {
					PrintLog("EODCreateHistoryTable: " + t);
					t.printStackTrace();
					try {
						dbConn.rollback();
					} catch (Exception ex) {
						PrintLog("EODCreateHistoryTable: " + ex);
						return (0);
					}
				}
			}
			// First Backup the tables Pre EOD
			try {
				PrintLog("Performing EODBackupTables ");
				EODBackupTables(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("EODBackupTables " + t);
				t.printStackTrace();
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODBackupTables " + ex);
					return (0);
				}
			}
			// Then generate Extract file for Funds Transfer
			try {
				EODExtractData(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("Extract file: " + t);
				t.printStackTrace();
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Extract file: " + ex);
					return (0);
				}
			}
			// If next Busibness day is in the next month then create the
			// next month History file.
			// Move the Rows from incl_chex to incl_chex_MM_yyyymm
			// and Update the incl_chex_history
			try {
				PrintLog("Performing EODMovetoHistory ");
				EODMovetoHistory(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("Error in EODMovetoHistory: " + t);
				t.printStackTrace();
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODMovetoHistory: " + ex);
					return (0);
				}
			}
			try {
				PrintLog("Performing EODPurgeLogs ");
				EODPurgeLogs(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("EODPurgeLogs: " + t);
				t.printStackTrace();
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODPurgeLogs: " + ex);
					return (0);
				}
			}
/*
			if (bankId.equals("BNPMO")) {
				// Then generate Reconciliation Report
				try {
					PrintLog("Performing EODGenReconReport ");
					EODGenReconReport(dbConn, eodParams);
				} catch (Throwable t) {
					PrintLog("EODGenReconReport: " + t);
					t.printStackTrace();
				}
			}
*/
			// Update incl_control
			try {
				PrintLog("Performing EODUpdateControl ");
				EODUpdateControl(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("EODUpdateControl: " + t);
				t.printStackTrace();
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODUpdateControl: " + ex);
					return (0);
				}
			}
			PrintLog("INCLEARING EOD process successfully completed");
			// dbConn.setAutoCommit(false);
			// dbConn.commit();
			// dbConn.setAutoCommit(true);
		} catch (Exception ex) {
			PrintLog("Exception: " + ex);
			ex.printStackTrace();
			return (0);
		}
		return (1);
	}
	//
	public void EODBackupTables(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName	= "> InclEodUtil.EODBackupTables: ";
		String hist_yyyymm	= "";
		String temp			= "";
		temp				= eodParams.getToday();
		hist_yyyymm			= temp.substring(0, 6);
		PrintLog("Hist YYYYMM " + hist_yyyymm);
		try {
			dbConn.setAutoCommit(false);
			Statement statement = dbConn.createStatement();
			statement.addBatch("TRUNCATE table incl_chex_beod_bck");
			// statement.addBatch("TRUNCATE table incl_chex_log_beod_bck");
			statement.addBatch("TRUNCATE table incl_chex_hist_beod_bck");
			statement.addBatch("TRUNCATE table incl_chex_log_hist_beod_bck");
			statement.addBatch("TRUNCATE table system_control_beod_bck");
			//
			statement.addBatch("INSERT into incl_chex_beod_bck SELECT * from incl_chex");
			// statement.addBatch("INSERT into incl_chex_log_beod_bck "+
			// "SELECT * from incl_chex_log");
			statement.addBatch("INSERT INTO incl_chex_hist_beod_bck " +
								"SELECT * from incl_chex_M_" + hist_yyyymm);
			statement.addBatch("INSERT INTO incl_chex_log_hist_beod_bck " +
								"SELECT * from incl_chex_log_" + hist_yyyymm);
			statement.addBatch("INSERT INTO system_control_beod_bck " +
								"SELECT * from system_control");
			//
			statement.executeBatch();
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (BatchUpdateException b) {
			System.err.println("------BatchUpdateException------");
			System.err.println("SQLState:  " + b.getSQLState());
			System.err.println("Message:   " + b.getMessage());
			System.err.println("ErrorCode: " + b.getErrorCode());
			System.err.print("Update counts:  ");
			int[] updateCounts = b.getUpdateCounts();
			for (int i = 0; i < updateCounts.length; i++) {
				System.err.print(updateCounts[i] + "   ");
			}
			System.err.println("");

		} catch (SQLException ex) {
			System.err.println("------SQLException------");
			System.err.println("SQLState:  " + ex.getSQLState());
			System.err.println("Message:   " + ex.getMessage());
			System.err.println("ErrorCode: " + ex.getErrorCode());
		}
	}
	//
	public void EODRestoreTables(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName			= "> InclEodUtil.EODRestoreTables: ";
		String hist_yyyymm	= "";
		String temp			= "";
		temp				= eodParams.getToday();
		hist_yyyymm			= temp.substring(0, 6);
		PrintLog("Hist YYYYMM " + hist_yyyymm);
		try {
			dbConn.setAutoCommit(false);
			Statement statement = dbConn.createStatement();
			statement.addBatch("TRUNCATE table incl_chex");
			// statement.addBatch("TRUNCATE table incl_chex_log");
			statement.addBatch("TRUNCATE table incl_chex_M_" + hist_yyyymm);
			statement.addBatch("TRUNCATE table incl_chex_log_" + hist_yyyymm);
			statement.addBatch("TRUNCATE table system_control");
			//
			statement.addBatch("INSERT into incl_chex SELECT * from incl_chex_beod_bck");
			// statement.addBatch("INSERT into incl_chex_log "+
			// "SELECT * from incl_chex_log_beod_bck");
			statement.addBatch("INSERT INTO incl_chex_M_" + hist_yyyymm + 
								" SELECT * from incl_chex_hist_beod_bck");
			statement.addBatch("INSERT INTO incl_chex_log_" + hist_yyyymm +
								" SELECT * from incl_chex_log_hist_beod_bck");
			statement.addBatch("INSERT INTO system_control SELECT * from system_control_beod_bck");
			//
			statement.executeBatch();
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (BatchUpdateException b) {
			System.err.println("----BatchUpdateException----");
			System.err.println("SQLState:" + b.getSQLState());
			System.err.println("Message: " + b.getMessage());
			System.err.println("Vendor:  " + b.getErrorCode());
			System.err.print("Update counts:  ");
			int[] updateCounts = b.getUpdateCounts();
			for (int i = 0; i < updateCounts.length; i++) {
				System.err.print(updateCounts[i] + "   ");
			}
			System.err.println("");

		} catch (SQLException ex) {
			System.err.println("----SQLException----");
			System.err.println("SQLState:" + ex.getSQLState());
			System.err.println("Message: " + ex.getMessage());
			System.err.println("Vendor:  " + ex.getErrorCode());
		}
	}
	//
	public void EODCreateHistoryTable(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName			= "> InclEodUtil.EODCreateHistoryTable: ";
		StringBuffer sql	= new StringBuffer();
		String hist_yyyymm	= "";
		String temp			= "";
		// temp = eodParams.getToday();
		temp				= eodParams.getNext_biz_date();
		String bankId		= eodParams.getBankId();
		hist_yyyymm			= temp.substring(0, 6);
		PrintLog("EODCreateHistoryTable " + hist_yyyymm);
		//
		// Create Monthly History table.
		//
		sql.setLength(0);
		sql.append("CREATE TABLE incl_chex_M_" + hist_yyyymm);
		sql.append(" AS SELECT * FROM INCL_CHEX_M_STOCK");
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		statement.close();
		result.close();
		PrintLog("Created Hist " + hist_yyyymm);
		//
		// Add the Unique Constraint
		//
		sql.setLength(0);
		sql.append("ALTER TABLE incl_chex_M_" + hist_yyyymm);
		if (bankId.equals("BNPMO")) {
			sql.append(" ADD CONSTRAINT C" + hist_yyyymm +
						" UNIQUE (chist_proc_date, chist_orig_account_num, chist_orig_check_num," +
						" chist_unique_isn)");
		} else {
			sql.append(" ADD CONSTRAINT C" + hist_yyyymm +
						" UNIQUE (chist_orig_account_num, chist_orig_check_num, chist_unique_isn)");
		}
		statement = dbConn.createStatement();
		result = statement.executeQuery(sql.toString());
		statement.close();
		result.close();
		PrintLog("Set Constraint Uniquechex on incl_chex_M_" + hist_yyyymm);
		//
		// Create Monthly Audit History table. Does not need constraint.
		//
		sql.setLength(0);
		sql.append("CREATE TABLE incl_chex_log_" + hist_yyyymm);
		sql.append(" AS SELECT * FROM INCL_CHEX_LOG_STOCK");
		statement	= dbConn.createStatement();
		result		= statement.executeQuery(sql.toString());
		statement.close();
		result.close();
		PrintLog("Created Log Hist " + hist_yyyymm);
	}
	//
	public void EODGenReconReport(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName			= "> InclEodUtil.EODGenReport: ";
		String temp			= "";
		temp				= eodParams.getToday();
		String reportDir	= eodParams.getReportdir();
		String dateToday	= eodParams.getToday();
		String getParams	= "";
		String padChar		= "                      ";
		//
		// Recon Fields
		//
		String recon_proc_date_f		= "";
		String recon_account_num		= "";
		String recon_check_num			= "";
		String recon_check_amount_f		= "";
		String recon_check_currency		= "";
		String recon_routing_transit	= "";
		String recon_unique_isn			= "";
		String recon_source_micr		= "";
		String recon_source_xml			= "";
		String repHeader				= "";
		String rightPad					= "";
		String leftPad					= "";
		//
		int rowCount	= 0;
		int maxLen		= 0;
		int padCount	= 0;
		//
		ArrayList<?> reconRow		= new ArrayList<Object>();
		ReconDetail aRow			= new ReconDetail();
		String reconReport			= reportDir + "reconreport_" + dateToday + ".rpt";
		EcontReportUtil eRep		= new EcontReportUtil(reconReport);
		InclReconUtil recUtil		= new InclReconUtil();
		ReconSelector reconSelector	= new ReconSelector();
		//
		Statement stmt	= dbConn.createStatement();
		temp			= "SELECT REPHEADER FROM incl_control ";
		ResultSet rset	= stmt.executeQuery(temp.toString());
		while (rset.next()) {
			repHeader	= rset.getString("REPHEADER");
		}
		maxLen		= repHeader.length() / 2;
		padCount	= 66 - (maxLen + 27);
		leftPad		= GenUtil.pad(leftPad, padCount, " ");
		padCount	= 66 - (maxLen + 11);
		rightPad	= GenUtil.pad(rightPad, padCount, " ");
		//
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(eodParams.getToday());
		temp	= ("REPORT NAME:RECONREPORT.RPT" + leftPad + repHeader + rightPad + "PAGE: ");
		eRep.setHeadings(temp);
		temp	= ("                                                         " + "INCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp	= ("                                     " +
					"MICR AND IMAGE FILE RECONCILIATION REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("PROCESSING DATE   ACCOUNT NUMBER       CHECK NUMBER CUR " +
					"    CHECK AMOUNT  ROUTING TRANSIT UNIQUE ISN  MICR IMAGE" +
					"           STATUS");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		// Get the Check Count for Header
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"Will run query -- 1");
		//
		// Start the Cheque Processing
		temp	= "";
		getParams	= (" WHERE RECON_STATUS='U' ORDER BY " + 
						"RECON_PROC_DATE, RECON_ACCOUNT_NUM, " +
						"RECON_CHECK_NUM, RECON_CHECK_CURRENCY, " +
						"RECON_UNIQUE_ISN ");
		rowCount = recUtil.GetReconRows(dbConn, reconSelector, getParams);
		if (rowCount > 0) {
			PrintLog("Will Process Recon Query Results");
			reconRow	= reconSelector.getReconRowsArray();
			Iterator<?> getRecon	= reconRow.iterator();
			try {
				while (getRecon.hasNext()) {
					aRow				= (ReconDetail) getRecon.next();
					recon_proc_date_f	= aRow.getRecon_proc_date_disp();
					recon_account_num	= aRow.getRecon_account_num();
					recon_check_num		= aRow.getRecon_check_num();
					;
					recon_check_amount_f	= aRow.getRecon_check_amount_disp();
					recon_check_currency	= aRow.getRecon_check_currency();
					recon_routing_transit	= aRow.getRecon_routing_transit();
					recon_unique_isn		= aRow.getRecon_unique_isn();
					recon_source_micr		= aRow.getRecon_source_micr();
					recon_source_xml		= aRow.getRecon_source_xml();
					temp	= (recon_proc_date_f +
								padChar.substring(0,18 - recon_proc_date_f.length()) +
								recon_account_num +
								padChar.substring(0,21 - recon_account_num.length()) +
								recon_check_num +
								padChar.substring(0,13 - recon_check_num.length()) +
								recon_check_currency +
								padChar.substring(0,5 - recon_check_currency.length()) +
								padChar.substring(0,15 - recon_check_amount_f.length()) +
								recon_check_amount_f + "  " + recon_routing_transit +
								padChar.substring(0,16 - recon_routing_transit.length()) +
								recon_unique_isn +
								padChar.substring(0,13 - recon_unique_isn.length()) +
								recon_source_micr +
								padChar.substring(0,6 - recon_source_micr.length()) +
								recon_source_xml +
								padChar.substring(0,14 - recon_source_xml.length()) + "UNMATCHED");
					eRep.WriteDetail(temp);
					// eRep.WriteDetail(" ");
				}
			} catch (Throwable t) {
				PrintLog("Error while Generating Reconciliation Report" + t);
			}
		} else {
			temp	= "NO UNMATCHED RECORDS WERE FOUND";
			eRep.WriteDetail(" ");
			eRep.WriteDetail(" ");
			eRep.WriteDetail(" ");
			eRep.WriteDetail(" ");
			eRep.WriteDetail(temp);
			eRep.WriteDetail(" ");
			eRep.WriteDetail(" ");
			eRep.WriteDetail(" ");
		}
		eRep.WriteHeadings();
		eRep.WriteDetail("");
		eRep.WriteDetail("");
		eRep.WriteDetail("                                                       "
				+ "     *** END OF REPORT ***");
		eRep.Close();
	}
	//
	public void EODExtractData(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfL = new DecimalFormat("###,###,##0.00");
		moduleName 				= "> InclEodUtil.EODExtractData: ";
		PrintLog("Performing EODExtractData ");
		//
		String[] cp_cmd			= { "/bin/sh", "-c", " " };
		String out_rec			= "";
		String rec_ctr_s		= "";
		String process_date		= "";
		String bankId			= "";
		String our_aba			= "";
		String check_count		= "";
		String acctAmount_s		= "";
		double acctAmount		= 0;
		String check_amount		= "";
		String check_payee		= "";
		String check_num		= "";
		String check_date		= "";
		String check_status		= "";
		String acct_num			= "";
		String check_unique_isn	= "";
		String check_acct_num	= "";
		//
		String sql_str		= "";
		String temp			= "";
		String temp1		= "";
		String datadir		= eodParams.getDatadir();
		String reportDir	= eodParams.getReportdir();
		String datafileName	= "inclmtext.dat";
		String reportName	= "inclmtext.rpt";
		String repHeader	= "";
		String leftPad		= "";
		String rightPad		= "";
		String zeroFill		= "000000000000000";

		// String this_node = "esl";
		bankId				= eodParams.getBankId();
		int rec_ctr			= 0;
		int max_len			= 0;
		int found_dot		= 0;
		int padCount		= 0;
		ResultSet rset		= null;
		//
		datadir					= eodParams.getDatadir();
		FileOutput writeArec	= new FileOutput(datadir + "inclmtext.dat");
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		Statement stmt			= dbConn.createStatement();
		repHeader				= eodParams.getRepHeader();
		/*
		temp					= "SELECT REPHEADER FROM system_control WHERE PRODUCTID='A'";
		ResultSet rset			= stmt.executeQuery(temp.toString());
		while (rset.next()) {
			repHeader			= rset.getString("REPHEADER");
		}
		*/
		max_len					= repHeader.length() / 2;
		padCount				= 66 - (max_len + 25);
		leftPad					= GenUtil.pad(leftPad, padCount, " ");
		padCount				= 66 - (max_len + 11);
		rightPad				= GenUtil.pad(rightPad, padCount, " ");
		//
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(eodParams.getToday());
		temp	= ("REPORT NAME:INCLMTEXT.RPT" + leftPad + repHeader + rightPad + "PAGE: ");
		eRep.setHeadings(temp);
		temp	= ("                                                          INCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp 	= ("                                   " +
					"INCLEARING TO MONTRAN POSTING EXTRACT REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("SEQ #   CHECK AMOUNT   PAYEE                            " +
					"  UNIQUE ISN       CHECK NUMBER      ABA          ACCOUN" +
					"T NUMBER  ISSUE DATE");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		// Get the Check Count for Header
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"Will run query -- 1");
		temp	= ("SELECT COUNT(*), CHEX_PROC_DATE FROM incl_chex GROUP BY CHEX_PROC_DATE");
		PrintLog(temp);
		rset = stmt.executeQuery(temp.toString());
		// First write out the header
		PrintLog("Ran query");
		while (rset.next()) {
			check_count		= rset.getString("COUNT(*)");
			process_date	= rset.getString("CHEX_PROC_DATE");
		}
		temp	= "";
		out_rec	= ("H" + process_date + "0000" + GenUtil.pad(check_count, 4, "0") + "990001" +
					GenUtil.pad(temp, 113, " ") + "A7");
		writeArec.writeLine(out_rec);
		//
		// Get our_aba (Bank Number) from incl_control
		//
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+"Will run query -- 2");
		//temp	= "SELECT OURABA FROM system_control where PRODUCTID='A'";
		//rset	= stmt.executeQuery(temp.toString());
		//while (rset.next()) {
		//	our_aba = "0" + rset.getString("OURABA");
		//}
		our_aba = eodParams.getOur_aba();
		// Start the Cheque Processing
		temp	= "";
		sql_str	= ("SELECT CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, " +
					"CHEX_ROUTING_TRANSIT, " +
					"CHEX_CHECK_AMOUNT, CHEX_UNIQUE_ISN, CHEX_ISSUE_DATE, CHEX_PAYEE, " +
					"CHEX_CHECK_STATUS FROM incl_chex " +
					"ORDER BY CHEX_ACCOUNT_NUM ");
		PrintLog("Will run query -- 4"+sql_str);
		rset	= stmt.executeQuery(sql_str);
		PrintLog("Will Process Query Results");
		while (rset.next()) {
			// PrintLog("Processing Query Results");
			out_rec	= "";
			// Start building the record
			// "D" + Seq num
			rec_ctr++;
			rec_ctr_s			= rec_ctr + "";
			max_len				= 3;
			rec_ctr_s			= GenUtil.pad(rec_ctr_s, max_len, "0");
			// Fetch the Columns here
			check_amount		= rset.getString("CHEX_CHECK_AMOUNT").trim();
			check_acct_num		= rset.getString("CHEX_ACCOUNT_NUM").trim();
			check_unique_isn	= rset.getString("CHEX_UNIQUE_ISN").trim();
			check_unique_isn	= zeroFill.substring(0,15 - check_unique_isn.length()) +
									check_unique_isn;
			check_date			= rset.getString("CHEX_ISSUE_DATE");
			check_payee			= rset.getString("CHEX_PAYEE");
			if (check_payee.equals("POSIPAY NOT PROVIDED")) {
				check_payee	= "NO POSIPAY PROVIDED";
			}
			check_num			= rset.getString("CHEX_CHECK_NUM");
			check_status		= rset.getString("CHEX_CHECK_STATUS").trim();
			//
			// Next commented if statement line is custom for BNP Montreal -
			// UNCOMMENT for others and comment next if statement
			//
			// if (check_date.equals("") || (check_date.length() < 7)) {
			if (bankId.equals("BNPMO")) {
				if (check_date.equals("") || check_date.equals("XXXXXXXX")
						|| (check_date.length() < 7)) {
					check_date	= "XXXX/XX/XX";
				} else {
					check_date	= (check_date.substring(0, 4) + "/" +
									check_date.substring(4, 6) + "/" + 
									check_date.substring(6, 8));
				}
			} else {
				if (check_date.equals("") || check_date.equals("XXXXXXXX")
						|| (check_date.length() < 7)) {
					check_date	= " ";
				} else {
					check_date = (check_date.substring(4, 6) + "/" +
									check_date.substring(6, 8) + "/" + 
									check_date.substring(0, 4));
				}
			}
			// PrintLog("Ran query -- 4 and now in WHILE "+rec_ctr+
			//			"<  CheckAccount>"+check_acct_num+"<"+
			// 			" CheckNum >"+check_num+"<  CheckAmount>"+check_amount+"<");
			// Strip amount of the . (dot)
			found_dot	= check_amount.indexOf(".", 0);
			// PrintLog("Before >"+check_amount+"< Found dot >"+
			// 			found_dot+"< Amount Length >"+check_amount.length()+"<");
			if (found_dot > 0) {
				if ((check_amount.length() - found_dot) == 2) {
					check_amount	= check_amount + "0";
					found_dot		= check_amount.indexOf(".", 0);
				}
				check_amount	= (check_amount.substring(0, found_dot) + 
									check_amount.substring(found_dot + 1));
			} else if ((found_dot == 0)) {
				if ((check_amount.length() - found_dot) == 3) {
					//
					// Amount less than a dollar
					//
					//PrintLog("Before >" + check_amount + "< Found dot >" + found_dot +
					//		 "< Amount Length >" + check_amount.length() + "<");
					check_amount	= check_amount.substring(found_dot + 1);
				} else if ((check_amount.length() - found_dot) == 2) {
					//
					// Amount less than a dollar but 10s multiple
					//
					//PrintLog("Before >" + check_amount + "< Found dot >" + found_dot +
					//		 "< Amount Length >" + check_amount.length() + "<");
					check_amount	= check_amount.substring(found_dot + 1) + "0";
				}
			} else {
				check_amount	= check_amount + "00";
			}
			max_len = 13;
			check_amount	= GenUtil.pad(check_amount, max_len, "0");
			// PrintLog("After  >"+check_amount+"<");
			// Payee (padded with trailing spaces uto 34 bytes)
			// PrintLog(">"+check_payee+"<");
			// if (check_payee == null) {
			// check_payee = "";
			// }
			// The pad utility pads on the left. If you need to pad on the right
			// obtain the padding string and append to the string to be padded
			if (check_payee.length() >= 34) {
				check_payee	= check_payee.substring(0, 34);
			} else {
				// We need 34 less the payee string size (eg 34-23=11)
				max_len		= 34 - check_payee.length();
				// send temp1 with length=0 return temp1 with 11 max_len spaces
				temp1		= "";
				temp1		= GenUtil.pad(temp1, max_len, " ");
				// add 11 spaces to payee
				check_payee	+= temp1;
			}
			// Check Num
			if (check_num == null) {
				check_num	= "0";
			}
			if (check_num.length() > 10) {
				check_num	= check_num.substring(check_num.length() - 11,check_num.length());
			} else {
				max_len		= 10 - check_num.length();
				temp1		= "";
				temp1		= GenUtil.pad(temp1, max_len, " ");
				check_num	+= temp1;
			}
			// Account Num
			// if (check_acct_num.length() > 7) {
			// acct_num =
			// ("0"+check_acct_num.substring(0,2)+"0"+check_acct_num.substring(2,8)+"USD"+
			// check_acct_num.substring(8));
			// }
			// PrintLog("Ran query -- 4 and now in WHILE "+rec_ctr+"<  CheckAccount>"+acct_num+"<");
			max_len		= 20 - check_acct_num.length();
			temp1		= "";
			temp1		= GenUtil.pad(temp1, max_len, " ");
			if (check_status.equals("Z")) {
				check_acct_num	+= temp1;
				// acct_num	= gUtil.pad (acct_num, max_len, " ");
			} else if (check_status.equals("S")) {
				check_acct_num	+= temp1;
				// acct_num	= gUtil.pad (acct_num, max_len, " ");
			} else {
				check_acct_num	+= temp1;
				// acct_num	= gUtil.pad (acct_num, max_len, " ");
			}
			out_rec	= ("D" + rec_ctr_s + "100" + check_amount + check_payee +
						check_unique_isn.substring(5) + check_num + our_aba + check_acct_num +
						"CK NUMBER " + check_num + " DD " + check_date);
			writeArec.writeLine(out_rec);
			if (rec_ctr > 1) {
				if (!acct_num.equals(check_acct_num)) {
					eRep.WriteDetail(" ");
					acctAmount_s	= dfL.format(acctAmount / 100);
					temp			= ("  " + "Account Total " + acctAmount_s);
					eRep.WriteDetail(temp);
					acctAmount		= 0;
					eRep.WriteHeadings();
				}
			}
			temp1	= df.format(Double.parseDouble(check_amount) / 100);
			temp1	= GenUtil.pad(temp1, 15, " ");
			temp	= (rec_ctr_s + temp1 + "   " + check_payee + " " + 
						check_unique_isn.substring(5) + "       " +
						(check_num + "                 ").substring(0, 17) +
						our_aba + "   " + check_acct_num + "  " + check_date);
			eRep.WriteDetail(temp);
			temp	= "";
			temp1	= "";
			acctAmount	= acctAmount + Double.parseDouble(check_amount);
			acct_num	= check_acct_num;
		}
		temp	= "";
		out_rec	= "TEOD0" + rec_ctr_s + GenUtil.pad(temp, 130, " ");
		writeArec.writeLine(out_rec);
		writeArec.close();
		// Write the stats Here and Close
		eRep.WriteDetail(" ");
		acctAmount_s	= dfL.format(acctAmount / 100);
		temp			= ("  " + "Account Total " + acctAmount_s);
		eRep.WriteDetail(temp);
		acctAmount	= 0;
		// eRep.WriteHeadings();
		eRep.WriteDetail("");
		eRep.WriteDetail("");
		eRep.WriteDetail("                                                       " +
						 "     *** END OF REPORT ***");
		eRep.Close();
		try {
			cp_cmd[2] = "/bin/cp " + datadir + datafileName + " " + reportDir;
			PrintLog("Will Copy Data" + cp_cmd[0] + " "	+ cp_cmd[1] + " " + cp_cmd[2]);
			Runtime.getRuntime().exec(cp_cmd);
		} catch (Throwable t) {
			t.printStackTrace();
			PrintLog(" " + t);
		}
		rset.close();
		stmt.close();
	}
	//
	public void EODMovetoHistory(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName			= "> InclEodUtil.EODMovetoHistory: ";
		boolean inclChexTruncated	= false;
		int	cycleCount				= 0;
		String temp			= "";
		String hist_yyyymm	= "";
		String dbUsed		= eodParams.getDbUsed();
		temp				= eodParams.getToday();
		hist_yyyymm			= temp.substring(0, 6);
		Statement statement	= dbConn.createStatement();
		dbConn.setAutoCommit(false);
		temp				= ("DELETE from incl_chex_history where hist_proc_month='" +
								temp.substring(0, 6) + "'");
		try {
			statement.executeUpdate(temp.toString());
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (Throwable t) {
			PrintLog("Error Deleting history CHEXNUM LO/HI RANGE " + t);
			dbConn.setAutoCommit(true);
		}
		//
		// Here Move the Check data to history
		//
		dbConn.setAutoCommit(false);
		temp	= ("insert into incl_chex_M_" + hist_yyyymm + " select * from incl_chex");
		try {
			statement.executeUpdate(temp.toString());
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (Throwable t) {
			PrintLog("ERROR: Inserting into incl_chex_M_" + hist_yyyymm + " " + t);
			dbConn.setAutoCommit(true);
		}
		//
		// Here set up the InclHistory - a row for each account with Min and Max
		// Chex from the Months History table
		//
		dbConn.setAutoCommit(false);
		temp	= ("insert into incl_chex_history " +
					"select substr(chist_proc_date,1,6) as hd, chist_account_num, ");
		if (dbUsed.equals("MySQL")) {
			temp	+= "min(chist_check_num), max(chist_check_num), ";
		} else if (dbUsed.equals("ORACLE")) {
			temp	+= "nvl(min(chist_check_num),0), nvl(max(chist_check_num),0), " +
						"count(chist_check_num), ";
		}
		temp		+= "'incl_chex_M_" + hist_yyyymm + "', ";
		if (dbUsed.equals("MySQL")) {
			temp	+= "NULL, ";
		}
		if (dbUsed.equals("ORACLE")) {
			temp	+= "sysdate, ";
		}
		temp		+= (" 'EOD', 'EOD' " + "from incl_chex_M_" + hist_yyyymm + 
						" WHERE chist_return_status=' ' group by chist_account_num, substr(chist_proc_date,1,6)");
		try {
			statement.executeUpdate(temp.toString());
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (Throwable t) {
			PrintLog("ERROR: Inserting into incl_chex_history " + t + temp.toString());
			dbConn.setAutoCommit(true);
		}
		//
		/*
		try {
		    // to sleep 5 seconds
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		}
		*/
		moduleName			= "> InclEodUtil.EODMovetoHistory.ClearChexTable: ";
		dbConn.setAutoCommit(false);
		temp	= ("truncate table incl_chex ");
		while (inclChexTruncated == false) {
			try {
				statement.executeUpdate(temp.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				inclChexTruncated	= true;
			} catch (Throwable t) {
				PrintLog("ERROR: Truncating incl_chex " + t);
				PrintLog("Truncating incl_chex cycle " + cycleCount);
				cycleCount++;
				if (cycleCount > 5) {
					dbConn.setAutoCommit(true);
					inclChexTruncated	= true;
					PrintLog("ERROR: Truncating incl_chex unsuccessful after " + cycleCount + " tries");
				} else {
					try {
					    // to sleep 5 seconds
					    Thread.sleep(5000);
					} catch (InterruptedException e) {
					    Thread.currentThread().interrupt();
					}
				}
			}
		}
	}
	//
	public void EODPurgeLogs(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName	= "> InclEodUtil.EODPurgeLogs: ";
		//
		// Here add code to purge CIF, Posipay, Stoppay and their log tables
		//
		String next_biz_date	= eodParams.getNext_biz_date();
		String oneMonthOld		= eodParams.getOne_m_back();
		PrintLog("Month Old Date " + oneMonthOld);
		String bankId			= eodParams.getBankId();
		String temp				= eodParams.getToday();
		int todayYYYY			= Integer.parseInt(temp.substring(0, 4));
		String todayMM			= temp.substring(4, 6);
		String todayDD			= temp.substring(6, 8);
		int next_biz_yy			= Integer.parseInt(next_biz_date.substring(0, 4));
		String purgeYear		= todayYYYY - 1 + "";
		String purgeLogYear		= todayYYYY - 10 + "";
		String purgeDate		= "01-JAN-" + purgeYear.substring(2, 3);
		String posiPurgedate	= purgeYear + todayMM + todayDD;
		Statement statement		= dbConn.createStatement();
		if (todayYYYY != next_biz_yy) {
			//
			// Keep logs of the entire previous year for a year
			// Here Purge the incl_posi_pay_log table
			//
			dbConn.setAutoCommit(false);
			temp	= ("delete * from incl_posi_pay_log where pplog_proc_date < '" +
						purgeLogYear + "0101'");
			try {
				statement.executeUpdate(temp.toString());
				dbConn.commit();
			} catch (Throwable t) {
				PrintLog("ERROR: Purging Incl_Posi_Pay_Log " + t);
				dbConn.setAutoCommit(true);
			}
			//
			// Here Purge the incl_stop_pay_log table
			//
			temp	= ("delete * from incl_stop_pay_log where splog_proc_date < '"	+ 
						purgeYear + "%'");
			try {
				statement.executeUpdate(temp.toString());
				dbConn.commit();
			} catch (Throwable t) {
				PrintLog("ERROR: Purging Incl_Stop_Pay_Log " + t);
				dbConn.setAutoCommit(true);
			}
			//
			// Here Purge the incl_accounts_log (CIF) table
			//
			temp	= ("delete * from incl_accounts_log where acctlog_last_modified < '" +
						purgeDate + "%'");
			try {
				statement.executeUpdate(temp.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
			} catch (Throwable t) {
				PrintLog("ERROR: Purging Incl_Accounts_Log " + t);
				dbConn.setAutoCommit(true);
			}
		}
		//
		// Here purge the Posipay, Stoppay and Accounts (CIF) tables
		//
		PrintLog("Purge Posipays Matched before " + posiPurgedate);
		dbConn.setAutoCommit(false);
		temp	= ("delete from incl_posi_pay where " + 
					"pospay_proc_date < '" +
					posiPurgedate + 
					"' AND pospay_proc_date <> ' ' " +
					"AND pospay_status='M'");
		try {
			statement.executeUpdate(temp.toString());
			dbConn.commit();
		} catch (Throwable t) {
			PrintLog("ERROR: Purging Incl_Posi_Pay " + t);
			dbConn.setAutoCommit(true);
		}
		//
		// Here purge the Incl Recon Table
		//
		if (bankId.equals("BNPMO")) {
			dbConn.setAutoCommit(false);
			temp = ("delete from incl_recon where RECON_STATUS='M' OR RECON_PROC_DATE > '" +
					oneMonthOld + "'");
			PrintLog("Recon purge SQL >" + temp + "<");
			try {
				dbConn.setAutoCommit(false);
				statement.executeUpdate(temp.toString());
				dbConn.commit();
			} catch (Throwable t) {
				PrintLog("ERROR: Purging Reconciliation Table " + t);
				dbConn.setAutoCommit(true);
			}
		}
		//
		// Here Purge the incl_stop_pay_log table
		//
		// temp =
		// ("delete * from incl_stop_pay where stopay_proc_date < '"+purgeYear+"%'");
		// try {
		// result = statement.executeUpdate (temp.toString());
		// dbConn.commit();
		// } catch (Throwable t) {
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// moduleName+": Purging Incl_Stop_Pay_Log "+t);
		// dbConn.setAutoCommit(true);
		// }
	}
	//
	public void EODUpdateControl(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName				= "> InclEodUtil.EODUpdateControl: ";
		String sql				= "";
		String dbUsed			= "";
		int today_yyyy			= 0;
		int today_mm 			= 0;
		int today_dd			= 0;
		dbUsed					= eodParams.getDbUsed();
		ArrayList<String> holidays	= new ArrayList<String>();
		holidays				= eodParams.getHolidays();
		//
		String appl_date		= eodParams.getNext_biz_date();
		String prev_biz_date	= eodParams.getToday();
		String eod_oper			= eodParams.getEod_oper();
		today_yyyy				= Integer.parseInt(appl_date.substring(0, 4));
		today_mm				= Integer.parseInt(appl_date.substring(4, 6));
		today_dd				= Integer.parseInt(appl_date.substring(6));
		String next_biz_date	= ValiDate.getNextBusinessDay(today_mm, today_dd,
																today_yyyy, holidays);
		sql = ("UPDATE system_control set APPLDATE='" + appl_date + "', " +
				"EODFLAG='Y', BODFLAG='N', CODFLAG='N', " +
				"EODOPER='" + eod_oper + "', PREVBIZDATE='" +
				prev_biz_date + "', NEXTBIZDATE='" + next_biz_date + "', ");
		if (dbUsed.equals("MySQL")) {
			sql	+= "EODTIME=NULL";
		}
		if (dbUsed.equals("ORACLE")) {
			sql	+= "EODTIME=sysdate";
		}
		sql	= sql + " WHERE PRODUCTID='C' ";
		PrintLog(" Update System Control " + sql);
		Statement statement = dbConn.createStatement();
		dbConn.setAutoCommit(false);
		statement.executeUpdate(sql.toString());
		dbConn.commit();
		dbConn.setAutoCommit(true);
	}
	//
	public void EODCloseTheDay(Connection dbConn, InclEODParams eodParams)
			throws Exception {
		moduleName			= "> InclEodUtil.EODUpdateControl: ";
		int insOrUpd		= 2; //Update
		SysadProdUtil	pUtil	= new SysadProdUtil();
		//String sql			= "";
		String datadir		= eodParams.getDatadir();
		String reportDir	= eodParams.getReportdir();
		String dbUsed		= eodParams.getDbUsed();
		String eod_oper		= eodParams.getEod_oper();
		String userId		= eodParams.getUserId();
		// 
		String eodFlagD		= "";
		String bodFlagD		= "";
		String codFlagD		= "";
		String applDate		= "";
		String nextBizDate	= "";
		String prevBizDate	= "";
		//
		ctlSelector		= new ControlSelector();
		ctlSelector.setDbUsed(dbUsed);
		ctlSelector.setProdNames(pUtil.GetProductsNameList(dbConn));
		icUtil.GetControlRow(dbConn, ctlSelector);
		ctlDetailA	= ctlSelector.getControlRow(prodIdA);
		ctlDetailC	= ctlSelector.getControlRow(prodIdC);
		ctlDetailD	= ctlSelector.getControlRow(prodIdD);
		//
		ctlDetailC.setEodFlag("N");
		ctlDetailC.setBodFlag("Y");
		ctlDetailC.setCodFlag("Y");
		ctlDetailC.setFile_loaded(" ");
		ctlDetailC.setFile_loaded_1(" ");
		ctlDetailC.setFile_loaded_2(" ");
		ctlDetailC.setFile_loaded_3(" ");
		ctlDetailC.setFile_loaded_4(" ");
		ctlDetailC.setFile_loaded_5(" ");
		ctlDetailC.setBodOper(eod_oper);
		ctlDetailC.setDbUsed(dbUsed);
		icUtil.InsertUpdateControl(dbConn,	ctlDetailC, userId, insOrUpd);
		FileOutput flagFile		= new FileOutput(datadir + "inclmtext.ack");
		flagFile.close();
		// Here update the System record (productId="A") as COD
		eodFlagD	= ctlDetailD.getEodFlag();
		bodFlagD	= ctlDetailD.getBodFlag();
		codFlagD	= ctlDetailD.getCodFlag();
		applDate	= ctlDetailD.getApplDate();
		nextBizDate	= ctlDetailD.getNextBizDate();
		prevBizDate	= ctlDetailD.getPrevBizDate();
		if (eodFlagD.equals("N") && 
			bodFlagD.equals("Y") && 
			codFlagD.equals("Y")) {
			ctlDetailA.setApplDate(applDate);
			ctlDetailA.setPrevBizDate(prevBizDate);
			ctlDetailA.setNextBizDate(nextBizDate);
			ctlDetailA.setEodOper(userId);
			ctlDetailA.setBodOper(userId);
			ctlDetailA.setCodOper(userId);
			ctlDetailA.setEodFlag("N");
			ctlDetailA.setBodFlag("Y");
			ctlDetailA.setCodFlag("Y");
			icUtil.InsertUpdateControl(dbConn,	ctlDetailA, userId, insOrUpd);
		}
		FileOutput dayClosed	= new FileOutput(reportDir + "dayClosed");
		dayClosed.close();
		PrintLog("eControler Inclearing/Outclearing Day Closed successfully");
	}
}
