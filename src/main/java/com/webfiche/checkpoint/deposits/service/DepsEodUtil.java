package com.webfiche.checkpoint.deposits.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.*;

import com.webfiche.checkpoint.actions.*;
//import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.deposits.beans.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;

@Service
public class DepsEodUtil {
	String moduleName;
	String className = "> DepsEodUtil.";
	//
	private ControlDetail ctlDetailA;
	private ControlDetail ctlDetailC;
	private ControlDetail ctlDetailD;
	private String prodIdA			= "A";
	private String prodIdC			= "C";
	private String prodIdD			= "D";
	//
	MailClient			mClient		= new MailClient();
	private String fromEmailAddress	= "";
	private String smtpDomain		= "";
	private String sysAlertEmail	= "";
	private String alertSubject		= "Error during Deposit EOD processing";
	private String alertMessage		= "";
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public DepsEodUtil() {
	}
	//
	public void EODSetEodParams(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className			= "> DepsEodUtil.";
		moduleName			= "EODSetEodParams: ";
		PrintLog(" Entering EODSetEodParams");
		StringBuffer sql	= new StringBuffer();
		String temp			= "";
		String prodId		= "A";
		int today_yyyy		= 0;
		int today_mm		= 0;
		int today_dd		= 0;
		int months_back		= 0;
		int next_biz_mm		= 0;
		String holiYearCurr	= "";
		String one_holiday	= "";
		String dbUsed		= "";
		String prior_date	= "";
		String nextBizDate	= "";
		ResultSet result;
		Statement statement;
		//
		SysadControlUtil icUtil		= new SysadControlUtil();
		ControlSelector ctlSelector = new ControlSelector();
		ControlDetail ctlDetailA	= new ControlDetail();
		ControlDetail ctlDetailD	= new ControlDetail();
		try {
		    icUtil.GetControlRow (dbConn, ctlSelector, prodId);
			ctlDetailA = ctlSelector.getARow();
		} catch (Throwable t) {
			PrintLog("Error Getting Control 1 ->" + t.toString());
		}
		eodParams.setDatadir(ctlDetailA.getLocOutputDir() + "deposits/");
		eodParams.setReportdir(ctlDetailA.getLocOutputDir() + "deposits/");
		eodParams.setImagedir(ctlDetailA.getImgBaseDir() + "outcl/");
		eodParams.setCurrency(ctlDetailA.getDefCurr());
		prodId = "D";
		try {
			icUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetailD	= ctlSelector.getARow();
		} catch (Throwable t) {
			PrintLog("Error Getting Control 2 ->" + t.toString());
		}
		eodParams.setProduct_id(ctlDetailD.getProductId());
		eodParams.setOur_aba(ctlDetailD.getOurAba());
		eodParams.setBankId(ctlDetailD.getBankId());
		eodParams.setEod_flag(ctlDetailD.getEodFlag());
		eodParams.setBod_flag(ctlDetailD.getBodFlag());
		eodParams.setFile_loaded(ctlDetailD.getFile_loaded());
		eodParams.setFileLoaded1(ctlDetailD.getFile_loaded_1());
		eodParams.setFileLoaded2(ctlDetailD.getFile_loaded_2());
		eodParams.setFileLoaded3(ctlDetailD.getFile_loaded_3());
		eodParams.setFileLoaded4(ctlDetailD.getFile_loaded_4());
		eodParams.setFileLoaded5(ctlDetailD.getFile_loaded_5());
		eodParams.setToday(ctlDetailD.getApplDate());
		eodParams.setPrev_biz_date(ctlDetailD.getPrevBizDate());
		eodParams.setNext_biz_date(ctlDetailD.getNextBizDate());
		eodParams.setRepHeader(ctlDetailD.getSysRepHeader());
		//
		nextBizDate		= eodParams.getNext_biz_date();
		temp			= eodParams.getToday();
		today_yyyy		= Integer.parseInt(temp.substring(0, 4));
		today_mm		= Integer.parseInt(temp.substring(4, 6));
		today_dd		= Integer.parseInt(temp.substring(6));
		next_biz_mm		= Integer.parseInt(nextBizDate.substring(4, 6));

		PrintLog(" Today_mm: " + today_mm + " Next MM: " + next_biz_mm);
		months_back	= 1;
		prior_date	= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy, months_back);
		eodParams.setOne_m_back(prior_date);
		//
		// Here set the One month back date as a purge paramater for the various tables
		//
		eodParams.setPosi_pay_purge(prior_date);
		eodParams.setChex_log_purge(prior_date);
		eodParams.setAcct_log_purge(prior_date);
		eodParams.setPosi_log_purge(prior_date);
		eodParams.setStop_log_purge(prior_date);
		months_back = 3;
		prior_date	= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy, months_back);
		eodParams.setThree_m_back(prior_date);
		months_back	= 6;
		prior_date	= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months_back);
		eodParams.setSix_m_back(prior_date);
		months_back	= 12;
		prior_date	= ValiDate.getPriorDates(today_mm, today_dd, today_yyyy,months_back);
		eodParams.setYear_back(prior_date);
		// Get holidays
		temp		= eodParams.getNext_biz_date();
		holiYearCurr	= temp.substring(0, 4) + eodParams.getCurrency();
		sql.setLength(0);
		sql.append("SELECT HOLIDAYS_YEAR_CURR, HOLIDAYS_DATES, ");
		sql.append("HOLIDAYS_LAST_MODIFIED, ");
		sql.append("HOLIDAYS_MOD_USER, HOLIDAYS_MOD_FUNC ");
		sql.append("FROM holidays ");
		sql.append("WHERE HOLIDAYS_YEAR_CURR='" + holiYearCurr + "' ");
		// Prepare the SELECT statement.
		statement	= dbConn.createStatement();
		// PrintLog(": Getting Holidays");
		result		= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("HOLIDAYS_DATES");
		}
		statement.close();
		result.close();
		while (temp.length() > 0) {
			one_holiday		= holiYearCurr.substring(0, 4) + temp.substring(0, 4);
			PrintLog(" Holidays: " + one_holiday);
			eodParams.setHolidays(one_holiday);
			temp	= temp.substring(4, temp.length());
		}
		//
		dbUsed		= eodParams.getDbUsed();
		PrintLog("Exiting EODSETPARAMS dbUsed " + dbUsed);
		PrintLog("Create History " + eodParams.getCreateNewHistory());
	}
	//
	public int EODPerform_EOD(Connection dbConn, DepsEodParams eodParams) {
		className = "> DepsEodUtil.";
		moduleName = "EODPerform_EOD: ";
		try {
			// First Backup the tables Pre EOD
			try {
				PrintLog("Performing EODBackupTables ");
				EODBackupTables(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("EODBackupTables " + t);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODBackupTables " + ex);
					return (0);
				}
			}
			// Then generate Extract file for Funds Transfer/Back Office
			/*
			try {
				PrintLog("Performing EODExtractData ");
				EODExtractData(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("Extract file: " + t);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("Extract file: " + ex);
					return (0);
				}
			}
			*/
			// Move the Rows from incl_chex to incl_chex_MM_yyyymm
			// and Update the incl_chex_history
			try {
				PrintLog("Performing EODMovetoHistory ");
				EODMovetoHistory(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("Error in EODMovetoHistory: " + t);
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
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODPurgeLogs: " + ex);
					return (0);
				}
			}
			// Update control
			try {
				PrintLog("Performing EODUpdateControl ");
				EODUpdateControl(dbConn, eodParams);
			} catch (Throwable t) {
				PrintLog("EODUpdateControl: " + t);
				try {
					dbConn.rollback();
				} catch (Exception ex) {
					PrintLog("EODUpdateControl: " + ex);
					return (0);
				}
			}
			PrintLog("OUTCLEARING EOD process successfully completed");
		} catch (Exception ex) {
			PrintLog("Exception: " + ex);
			return (0);
		}
		return (1);
	}
	//
	public void EODBackupTables(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className = "> DepsEodUtil.";
		moduleName = "EODBackupTables: ";
		String hist_yyyymm = "";
		String temp = "";
		temp = eodParams.getToday();
		hist_yyyymm = temp.substring(0, 6);
		PrintLog("Hist YYYYMM " + hist_yyyymm);
		try {
			/*
			DEPS_CHEX
			DEPS_CHEXLOG
			DEPS_CHEXLOG_201510
			DEPS_CHEXLOG_201512
			DEPS_CHEXLOG_201601
			*DEPS_CHEXLOG_HIST_BEOD_BCK
			DEPS_CHEX_201510
			DEPS_CHEX_201512
			DEPS_CHEX_20151204
			DEPS_CHEX_201601
			*DEPS_CHEX_BEOD_BCK
			*DEPS_CHEX_HIST_BEOD_BCK
			DEPS_CHEX_HIST_STOCK
			DEPS_CHEX_LOG_201512
			DEPS_CHEX_LOG_STOCK
			*DEPS_CONTROL_BEOD_BCK
			 */
			dbConn.setAutoCommit(false);
			Statement statement = dbConn.createStatement();
			statement.addBatch("TRUNCATE table deps_chex_datawarehouse");
			statement.addBatch("TRUNCATE table deps_chex_beod_bck");
			statement.addBatch("TRUNCATE table deps_chex_hist_beod_bck");
			statement.addBatch("TRUNCATE table deps_chexlog_hist_beod_bck");
			// Create a class to backup a control row into a separate table
			statement.addBatch("DELETE from deps_control_beod_bck  where PRODUCTID='D'");
			//
			statement.addBatch("INSERT into deps_chex_beod_bck " +
							   "SELECT * from deps_chex");
			statement.addBatch("INSERT INTO deps_chex_hist_beod_bck " +
							   "SELECT * from deps_chex_" + hist_yyyymm);
			statement.addBatch("INSERT INTO deps_chexlog_hist_beod_bck " +
							   "SELECT * from deps_chexlog_" + hist_yyyymm);
			statement.addBatch("INSERT INTO deps_control_beod_bck " +
							   "SELECT * from system_control where PRODUCTID='D'");
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
	public void EODRestoreTables(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className	= "> DepsEodUtil.";
		moduleName	= "EODRestoreTables: ";
		String hist_yyyymm = "";
		String temp	= "";
		temp		= eodParams.getToday();
		hist_yyyymm	= temp.substring(0, 6);
		PrintLog("Hist YYYYMM " + hist_yyyymm);
		try {
			dbConn.setAutoCommit(false);
			Statement statement = dbConn.createStatement();
			statement.addBatch("TRUNCATE table deps_chex");
			// statement.addBatch("TRUNCATE table deps_chex_log");
			statement.addBatch("TRUNCATE table deps_chex_" + hist_yyyymm);
			statement.addBatch("TRUNCATE table deps_chexlog_" + hist_yyyymm);
			statement.addBatch("DELETE from system_control where PRODUCTID='D'");
			//
			statement.addBatch("INSERT into deps_chex " +
							   "SELECT * from deps_chex_beod_bck");
			// statement.addBatch("INSERT into deps_chex_log "+
			// "SELECT * from deps_chex_log_beod_bck");
			statement.addBatch("INSERT INTO deps_chex_" + hist_yyyymm + " " +
							   "SELECT * from deps_chex_hist_beod_bck");
			statement.addBatch("INSERT INTO deps_chexlog_" + hist_yyyymm + " " +
					 		   "SELECT * from deps_chexlog_hist_beod_bck");
			statement.addBatch("INSERT INTO system_control " +
							   "SELECT * from deps_control_beod_bck where PRODUCTID='D'");
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
	public void EODExtractData(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		DecimalFormat df	= new DecimalFormat("###,##0.00");
		DecimalFormat dfL	= new DecimalFormat("###,###,##0.00");
		className			= "> DepsEodUtil.";
		moduleName			= "EODExtractData: ";
		EcontServletContextListener escl	= new EcontServletContextListener();
		//
		String[] cp_cmd			= { "/bin/sh", "-c", " " };
		String out_rec			= "";
		String rec_ctr_s		= "";
		String process_date		= "";
		String bankId			= "";
		String our_aba			= "";
		String acctAmount_s		= "";
		double acctAmount		= 0;
		String checkProcDate	= "";
		String check_amount		= "";
		String check_creditor	= "";
		String check_payee		= "";
		String check_num		= "";
		String check_date		= "";
		String acct_num			= "";
		String check_unique_isn	= "";
		String check_acct_num	= "";
		//
		String sql_str			= "";
		String temp				= "";
		String temp1			= "";
		String datadir			= eodParams.getDatadir();
		String reportDir		= eodParams.getReportdir();
		String datafileName		= "depsext.dat";
		String reportName		= "depsext.rpt";
		String repHeader		= "";
		String leftPad			= "";
		String rightPad			= "";
		String extDelim			= escl.getExtDelimiter();
		fromEmailAddress		= escl.getFromEmailAddress();
		smtpDomain				= escl.getSmtpDomain();
		sysAlertEmail			= escl.getSysAlertEmail();
		//
		// String this_node = "esl";
		bankId					= eodParams.getBankId();
		int rec_ctr				= 0;
		int max_len				= 0;
		int padCount			= 0;
		//
		datadir					= eodParams.getDatadir();
		FileOutput writeArec	= new FileOutput(datadir + "depsext.dat");
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		Statement stmt			= dbConn.createStatement();
		repHeader	= eodParams.getRepHeader();
		max_len		= repHeader.length() / 2;
		padCount	= 66 - (max_len + 25);
		leftPad		= GenUtil.pad(leftPad, padCount, " ");
		padCount	= 66 - (max_len + 11);
		rightPad	= GenUtil.pad(rightPad, padCount, " ");
		//
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(eodParams.getToday());
		temp = ("REPORT NAME:DEPSEXT.RPT" + leftPad + repHeader + rightPad + "PAGE: ");
		eRep.setHeadings(temp);
		temp	= ("                                               " + 
					"WEBFICHE CHECKPOINT SYSTEM FOR COMMERZBANK");
		eRep.setHeadings(temp);
		temp	= ("                                               " +
					"POSTING EXTRACT REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("SEQ    CHECK AMOUNT    CREDITOR A/C  PAYEE                        " +
					"       UNIQUE ISN         CHECK # ABA        ACCCOUNT # ISSUE DATE");
		// SEQ CHECK AMOUNT CREDITOR A/C PAYEE UNIQUE ISN CHECK # ABA ACCOUNT #
		// ISSUE DATE
		// 12345 000000.00 12344567890 1234567890123456789012345678901234 123456
		// 123456 123456789 1234567890 12/34/5678
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		// Get the Check Count for Header
		// PrintLog("Will run query -- 1");
		//
		//select chex_payeeacct, sum(chex_check_amount), count(*) 
		//from rdc_chex group by chex_payeeacct order by chex_payeeacct;
		//
		temp	= ("SELECT COUNT(*), CHEX_PROC_DATE FROM deps_chex " +
					"GROUP BY CHEX_PROC_DATE");
		PrintLog(temp);
		ResultSet rset = stmt.executeQuery(temp.toString());
		// First write out the header
		PrintLog("Ran query");
		while (rset.next()) {
			process_date	= rset.getString("CHEX_PROC_DATE");
		}
		temp	= "";
		out_rec	= ("H" + process_date + GenUtil.pad(temp, 113, " ") + "A7");
		writeArec.writeLine(out_rec);
		//
		// Get our_aba (Bank Number) from incl_control
		//
		// PrintLog("Will run query -- 2");
		temp = "SELECT OUR_ABA FROM system_control ";
		rset = stmt.executeQuery(temp.toString());
		while (rset.next()) {
			our_aba	= "0" + rset.getString("OUR_ABA");
		}
		//
		// Start the Cheque Processing
		//select chex_payeeacct, sum(chex_check_amount), count(*) 
		//from rdc_chex group by chex_payeeacct order by chex_payeeacct;
		//
		temp	= "";
		sql_str	= ("SELECT OUR_ABA, CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, " +
					"CHEX_ROUTING_TRANSIT, " +
					"CHEX_CHECK_AMOUNT, CHEX_UNIQUE_ISN, CHEX_ISSUE_DATE, CHEX_PAYEE, " +
					"CHEX_CHECK_STATUS " + "FROM system_control, deps_chex " +
					"ORDER BY CHEX_ACCOUNT_NUM");
		// PrintLog("Will run query -- 4");
		rset	= stmt.executeQuery(sql_str);
		PrintLog("Will Process Query Results");
		while (rset.next()) {
			// PrintLog("Processing Query Results");
			out_rec	= "";
			// Start building the record
			// "D" + Seq num
			rec_ctr++;
			rec_ctr_s	= rec_ctr + "";
			max_len		= 3;
			rec_ctr_s	= GenUtil.pad(rec_ctr_s, max_len, "0");
			// Fetch the Columns here
			checkProcDate		= rset.getString("CHEX_PROC_DATE").trim();
			check_amount		= rset.getString("CHEX_CHECK_AMOUNT").trim();
			check_acct_num		= rset.getString("CHEX_ACCOUNT_NUM").trim();
			check_unique_isn	= rset.getString("CHEX_UNIQUE_ISN").trim();
			check_creditor		= rset.getString("CHEX_EXTRA_1").trim();
			// check_unique_isn =
			// zeroFill.substring(0,15-check_unique_isn.length()) +
			// check_unique_isn;
			check_date = rset.getString("CHEX_ISSUE_DATE");
			if (check_date.equals("")) {
				check_date	= checkProcDate;
			}
			check_payee	= rset.getString("CHEX_PAYEE");
			check_num	= rset.getString("CHEX_CHECK_NUM");
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
				check_date	= (check_date.substring(4, 6) + "/" +
								check_date.substring(6, 8) + "/" +
								check_date.substring(0, 4));
			}
			if (check_payee == null) {
				check_payee	= "";
			}
			// The pad utility pads on the left. If you need to pad on the right
			// obtain the padding string and append to the string to be padded
			if (check_payee.length() >= 34) {
				check_payee	= check_payee.substring(0, 34);
			} else {
				// We need 34 less the payee string size (eg 34-23=11)
				max_len	= 34 - check_payee.length();
				// send temp1 with length=0 return temp1 with 11 max_len spaces
				temp1	= "";
				temp1	= GenUtil.pad(temp1, max_len, " ");
				// add 11 spaces to payee
				check_payee	+= temp1;
			}
			// Check Num
			if (check_num == null) {
				check_num	= "0";
			}
			out_rec	= (rec_ctr_s + extDelim + check_amount + extDelim +
						check_payee + extDelim + check_unique_isn.substring(5) +
						extDelim + check_num + extDelim + our_aba + extDelim + check_acct_num);
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
			temp	= (rec_ctr_s + temp1 + "   " + check_creditor + check_payee +
						"       " + check_unique_isn.substring(5) + "   " +
						(check_num + "                 ").substring(0, 17) +
						our_aba + "   " + check_acct_num + "  " + check_date);
			eRep.WriteDetail(temp);
			temp	= "";
			temp1	= "";
			acctAmount	= acctAmount + Double.parseDouble(check_amount);
			acct_num	= check_acct_num;
		}
		temp	= "";
		// out_rec = "TEOD0" + rec_ctr_s + gUtil.pad(temp, 130, " ");
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
			cp_cmd[2]	= "/bin/cp " + datadir + datafileName + " " + reportDir;
			PrintLog("Will Copy Data " + cp_cmd[0] + " " + cp_cmd[1] + " "
					+ cp_cmd[2]);
			Runtime.getRuntime().exec(cp_cmd);
		} catch (Throwable t) {
			t.printStackTrace();
			PrintLog("Throwable: " + t);
		}
		rset.close();
		stmt.close();
	}
	//
	public void EODMovetoHistory(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className			= "> DepsEodUtil.";
		moduleName			= "EODMovetoHistory: ";
		SysadDbUtil dbUtil		= new SysadDbUtil();
		boolean depsChexTruncated	= false;
		int	cycleCount				= 0;
		int	depsCount				= 0;
		int	warehouseCount			= 0;
		String temp			= "";
		String hist_yyyymm	= "";
		//String dbUsed = eodParams.getDbUsed();
		temp				= eodParams.getToday();
		hist_yyyymm			= temp.substring(0, 6);
		Statement statement	= dbConn.createStatement();
		dbConn.setAutoCommit(false);
		dbConn.setAutoCommit(false);
		depsCount	= dbUtil.GetRowCounts(dbConn, "deps_chex");
		temp = ("insert into deps_chex_" + hist_yyyymm + " select * from deps_chex");
		try {
			statement.executeUpdate(temp.toString());
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (Throwable t) {
			PrintLog(temp.toString());
			PrintLog("Inserting into deps_chex_" + hist_yyyymm + " >" + t);
			dbConn.setAutoCommit(true);
			alertMessage	= className+moduleName+"error Moving Deposit data to History";
			sendAlert();
		}
		//
		PrintLog("Moving " + depsCount + " to Deposit Checks Warehouse table");
		temp = ("insert into deps_chex_datawarehouse select * from deps_chex");
		try {
			statement.executeUpdate(temp.toString());
			dbConn.commit();
			dbConn.setAutoCommit(true);
			warehouseCount	= dbUtil.GetRowCounts(dbConn, "deps_chex_datawarehouse");
			if (depsCount != warehouseCount) {
				PrintLog("Moved " + warehouseCount + " to Deposit Checks Warehouse table");
				alertMessage	= className+moduleName+"error Moving Deposit data to Deposit Checks Warehouse Table";
				sendAlert();
			} else {
				PrintLog("Moved " + warehouseCount + " to Deposit Checks Warehouse table");
			}
		} catch (Throwable t) {
			PrintLog("Inserting into deps_chex_datawarehouse " + t);
			dbConn.setAutoCommit(true);
		}
		//
		moduleName			= "EODMovetoHistory.ClearDepsTable: ";
		dbConn.setAutoCommit(false);
		temp = ("truncate table deps_chex ");
		while (depsChexTruncated == false) {
			try {
				statement.executeUpdate(temp.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
				depsChexTruncated	= true;
			} catch (Throwable t) {
				PrintLog("ERROR: Truncating deps_chex " + t);
				PrintLog("Truncating deps_chex cycle " + cycleCount);
				cycleCount++;
				if (cycleCount > 5) {
					dbConn.setAutoCommit(true);
					depsChexTruncated	= true;
					PrintLog("ERROR: Truncating DEPS_chex unsuccessful after " + cycleCount + " tries");
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
	public void EODPurgeLogs(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className = "> DepsEodUtil.";
		moduleName = "EODPurgeLogs: ";
		//
		// Here add code to purge CIF, Posipay, Stoppay and their
		// log tables
		//
		String nextBizDate	= eodParams.getNext_biz_date();
		String oneMonthOld	= eodParams.getOne_m_back();
		PrintLog(": Month Old Date " + oneMonthOld);
		String temp			= eodParams.getToday();
		int todayYYYY		= Integer.parseInt(temp.substring(0, 4));
		int next_biz_yy		= Integer.parseInt(nextBizDate.substring(0, 4));
		String purgeYear	= todayYYYY - 1 + "";
		String purgeDate	= "01-JAN-" + purgeYear.substring(2, 3);
		Statement statement	= dbConn.createStatement();
		if (todayYYYY != next_biz_yy) {
			//
			// Here Purge the incl_accounts_log (CIF) table
			//
			temp = ("delete from deps_chexlog where modtim < '" + purgeDate + "%'");
			try {
				statement.executeUpdate(temp.toString());
				dbConn.commit();
				dbConn.setAutoCommit(true);
			} catch (Throwable t) {
				PrintLog(": Purging deps_chexLog " + t);
				dbConn.setAutoCommit(true);
			}
		}
	}
	//
	public void EODUpdateControl(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className		= "> DepsEodUtil.";
		moduleName		= "EODUpdateControl: ";
		String sql		= "";
		String dbUsed	= "";
		int today_yyyy	= 0;
		int today_mm	= 0;
		int today_dd	= 0;
		dbUsed			= eodParams.getDbUsed();
		ArrayList<String> holidays = new ArrayList<String>();
		holidays		= eodParams.getHolidays();
		//
		String appl_date	= eodParams.getNext_biz_date();
		String prev_biz_date= eodParams.getToday();
		String eod_oper		= eodParams.getEod_oper();
		String productId	= "D";
		today_yyyy			= Integer.parseInt(appl_date.substring(0, 4));
		today_mm			= Integer.parseInt(appl_date.substring(4, 6));
		today_dd			= Integer.parseInt(appl_date.substring(6));
		String nextBizDate	= ValiDate.getNextBusinessDay(today_mm, today_dd, today_yyyy, holidays);
		sql	= ("UPDATE system_control " + "set APPLDATE='" + appl_date + "', " +
				"    EODFLAG='Y', " + "    BODFLAG='N', " + "    EODOPER='" +
				eod_oper + "', " + "    PREVBIZDATE='" + prev_biz_date +
				"', " + "    NEXTBIZDATE='" + nextBizDate + "', ");
		if (dbUsed.equals("MySQL")) {
			sql += "    EODTIME=NULL";
		}
		if (dbUsed.equals("ORACLE")) {
			sql += "    EODTIME=sysdate";
		}
		eodParams.setEod_flag("Y");
		eodParams.setBod_flag("N");
		eodParams.setToday(appl_date);
		sql	+= " where productid='" + productId + "'";
		PrintLog(" Update system Control " + sql);
		Statement statement	= dbConn.createStatement();
		dbConn.setAutoCommit(false);
		statement.executeUpdate(sql.toString());
		dbConn.commit();
		dbConn.setAutoCommit(true);
	}
	//
	public void EODCloseTheDay(Connection dbConn, DepsEodParams eodParams)
			throws Exception {
		className		= "> DepsEodUtil.";
		moduleName		= "EODUpdateControl: ";
		//String sql		= "";
		int insUpd		= 2;
		//
		String reportDir	= eodParams.getReportdir();
		String dbUsed		= eodParams.getDbUsed();
		String eodOper		= eodParams.getEod_oper();
		//
		String eodFlagC		= "";
		String bodFlagC		= "";
		String codFlagC		= "";
		String applDate		= "";
		String nextBizDate	= "";
		String prevBizDate	= "";
		//
		ControlSelector ctlSelector	= new ControlSelector();
		SysadControlUtil icUtil		= new SysadControlUtil();
		icUtil.GetControlRow(dbConn, ctlSelector);
		ctlDetailA	= ctlSelector.getControlRow(prodIdA);
		ctlDetailC	= ctlSelector.getControlRow(prodIdC);
		ctlDetailD	= ctlSelector.getControlRow(prodIdD);
		//
		ctlDetailD.setEodFlag("N");
		ctlDetailD.setBodFlag("Y");
		ctlDetailD.setCodFlag("Y");
		ctlDetailD.setBodOper(eodOper);
		ctlDetailD.setDbUsed(dbUsed);
		icUtil.InsertUpdateControl(dbConn,	ctlDetailD, eodOper, insUpd);
		// Here update the System record (productId="A") as COD
		eodFlagC	= ctlDetailC.getEodFlag();
		bodFlagC	= ctlDetailC.getBodFlag();
		codFlagC	= ctlDetailC.getCodFlag();
		applDate	= ctlDetailC.getApplDate();
		nextBizDate	= ctlDetailC.getNextBizDate();
		prevBizDate	= ctlDetailC.getPrevBizDate();
		if (eodFlagC.equals("N") && 
			bodFlagC.equals("Y") && 
			codFlagC.equals("Y")) {
			ctlDetailA.setApplDate(applDate);
			ctlDetailA.setPrevBizDate(prevBizDate);
			ctlDetailA.setNextBizDate(nextBizDate);
			ctlDetailA.setEodOper(eodOper);
			ctlDetailA.setBodOper(eodOper);
			ctlDetailA.setCodOper(eodOper);
			ctlDetailA.setEodFlag("N");
			ctlDetailA.setBodFlag("Y");
			ctlDetailA.setCodFlag("Y");
			icUtil.InsertUpdateControl(dbConn,	ctlDetailA, eodOper, insUpd);
		}
		FileOutput dayClosed = new FileOutput(reportDir + "dayClosed");
		dayClosed.close();
		PrintLog("eControler Inclearing/Outclearing Day Closed successfully");
	}
	//
	public void sendAlert () {
		String[] fileNames = {};
		try {
			mClient.sendMail(smtpDomain, fromEmailAddress, sysAlertEmail,
								alertSubject, alertMessage, fileNames);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
