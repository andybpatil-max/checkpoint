package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.text.*;
import java.util.*;

//import org.apache.struts.action.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.service.*;

//
@Service
public final class UploadMOChex {
	// Process Statistics Counters
	static int	  invalid_accts		= 0;
	static int	  missing_chknum	= 0;
	static int	  pospay_missing	= 0;
	static int	  stale_check		= 0;
	static int	  cleared_check		= 0;
	static int	  stopay			= 0;
	static int	  exceed_limit		= 0;
	static int	  authorize			= 0;
	static int	  completed			= 0;
	static int	  maxLen			= 0;
	static int	  recCtr			= 0;
	static int	  checkCtr			= 0;
	static int	  tot_chex_25		= 0;
	static int	  tot_invalid_acct	= 0;
	static int	  tot_invalid_cnum  = 0;
	static int	  tot_no_posipay	= 0;
	static int	  tot_stale_chex	= 0;
	static int	  tot_cleared		= 0;
	static int	  tot_stopay		= 0;
	static int	  tot_exceed_lim	= 0;
	static int	  tot_auth			= 0;
	static int	  tot_completed		= 0;
	static int	  cycleId			= 0;
	static int	  row_id			= 0;
	static int	  rowCount			= 0;
	static int	  startImage		= 0;
	static int	  startJpeg			= 0;
	static int	  dataOffValue;
	static int	  dataOffValue_o;
	static int	  dataLenValue;
	static long	 skipLen			= 0;
	static double   tot_amt_25		= 0.0;
	static String   bank_acct		= "";
	static String   dbUsed			= "";
	static String   appl_date		= "";
	static String   temp			= "";
	static String   outDir			= "";
	static String   retTime			= "";
	static String   fileTime		= "";
	static String   outRec			= "";
	static String   ourAba			= "";
	static String   tot_amt_25_s	= "";
	static String   recType			= "";
	static String   jpegFile		= "";
	static String   bankId			= "";
	static String   reportHdg		= "";
	static String   repPad			= "";
	static String   procNum			= "1";
	static String   reportName		= "";
	static int	  repPadSize		= 0;
	//
	// Check Detail Record 25 Fields
	//
	static String   check_num_25;
	static String   ext_proc_control_25;
	static String   routing_transit_25;
	static String   account_num_25;
	static String   check_amount_25;
	static String   unique_isn_25;
	static String   ret_accept_ind_25;
	//
	// Next field stored in chex_ext_proc_control
	//
	static String   micr_valid_25;
	static String   misc_codes_25;
	static String   process_date_25;
	//
	// Check Detail Record 26 Fields
	//
	String		  bofd				= " ";
	String		  bofdDate			= " ";
	// Incl Checx Table fields
	static String   issue_date		= "0";
	static String   iss_date		= " ";
	static String   sw_ref			= " ";
	static String   payee			= " ";
	static String   payee_addr1		= " ";
	static String   payee_addr2		= " ";
	static String   payee_addr3		= " ";
	static String   comments		= " ";
	static String   reason_codes;
	static String   check_status;
	static String   check_amount_od;
	static String   last_modified;
	static String   mod_user;
	static String   mod_func;
	static String   recCtr_s;
	//
	// static String temp;
	static String   procDate;
	static String   checkNum;
	static String   checkAmount;
	static String   dbCr;
	static String   checkCurr;
	static String   checkCurrL;
	static String   tranType;
	static String   checkIsn;
	static String   rTransit;
	static String   checkAccount;
	static String   checkOrigin;
	static String   itemCountVal;
	static String   fileTotalAmount;
	static String   earmarkFile;
	//
	String[]	cp_cmd			= { "/bin/sh", "-c", " " };
	String		fieldDataValue	= "";
	String		ldbUsed_1;
	String		lappl_date_1;
	String		loutDir_1;
	String		lreportDir;
	String		lfileToLoad;
	String		lactionFlag;
	String		luserId;
	String		fileLoaded	= "";
	String		filesLoaded	= "";
	String		fileToLoad	= "";
	String[]	filesArray	= {"","","","",""};
	String		userId		= "";
	String		actionType	= "";
	//
	static boolean  id;
	boolean		 fieldName			= false;
	boolean		 fieldValue			= false;
	boolean		 processingDate		= false;
	boolean		 chequeSerialNumber = false;
	boolean		 amount				= false;
	boolean		 dRCRIndicator		= false;
	boolean		 currency			= false;
	boolean		 transactionType	= false;
	boolean		 itemSequenceNumber = false;
	boolean		 routingTransit		= false;
	boolean		 account			= false;
	boolean		 captureSite		= false;
	boolean		 dataOffset			= false;
	boolean		 dataLength			= false;
	boolean		 itemCount			= false;
	boolean		 checksumFieldValue = false;
	//
	int			 lcycleNum;
	//
	Connection	  ldbConn;
	EcontReportUtil leRep;
	ReconSelector   	reconSelector	= new ReconSelector();
	ReconDetail	 		reconDetail		= new ReconDetail();
	InclReconUtil   	recUtil			= new InclReconUtil();
	GenUtil		 		gUtil			= new GenUtil();
	SysadControlUtil	icUtil			= new SysadControlUtil();
	ControlDetail		ctlDetail		= new ControlDetail();
	ControlSelector		ctlSelector		= new ControlSelector();
	//
	DateFormat	  new_fmt1			= null;
	DateFormat	  newFmt2			= null;
	//
	String		  prodId			= "C";
	String		  moduleName		= "";
	String		  calledFrom		= "";
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> UploadMOChex." + moduleName + " - " + luserId + " " +
							logMsg);
	}
	//
	public int MontChexLoad(Connection dbConn, String dbUsed_1,
			String ourAba_1, String appl_date_1, String dataDir,
			String outDir_1, String reportDir, String fileToLoad, int cycleNum,
			String actionFlag, String userId) throws SQLException, Exception {
		//
		EcontServletContextListener escl = new EcontServletContextListener();
		moduleName = "MontChexLoad: ";
		String temp;
		// Process proc;
		//
		ldbConn			= dbConn;
		ldbUsed_1		= dbUsed_1;
		lappl_date_1	= appl_date_1;
		lreportDir		= reportDir;
		lfileToLoad		= fileToLoad;
		lcycleNum		= cycleNum;
		lactionFlag		= actionFlag;
		luserId			= userId;
		reportHdg		= escl.getReportHdg();
		bankId			= escl.getBankId();
		ourAba			= ourAba_1;
		loutDir_1		= outDir_1;
		tot_amt_25		= 0.0;
		tot_chex_25		= 0;
		recCtr			= 0;
		cycleId			= cycleNum * 1000;
		dbUsed			= dbUsed_1;
		reportName		= "montchexload-" + cycleNum + ".rpt";
		appl_date		= appl_date_1;
		PrintLog("Appl Date: " + appl_date + " User id: " + luserId);
		int insOrUpd		= 2;		//update
		//
		// Get todays calendar date
		//
		String sn_mm	= "";
		String sn_dd	= "";
		String sn_yy	= "";
		String sn_date	= "";
		java.util.GregorianCalendar now = new java.util.GregorianCalendar();
		sn_yy			= now.get(Calendar.YEAR) + "";
		sn_mm			= (now.get(Calendar.MONTH) + 1) + "";
		if (sn_mm.length() == 1) {
			sn_mm	= '0' + sn_mm;
		}
		sn_dd = now.get(Calendar.DATE) + "";
		if (sn_dd.length() == 1) {
			sn_dd	= '0' + sn_dd;
		}
		sn_date		= sn_yy + sn_mm + sn_dd;
		//
		// Get Time stamp for the earmark file name
		//
		new_fmt1	= DateFormat.getInstance();
		new_fmt1	= new SimpleDateFormat("HHmm");
		newFmt2		= new SimpleDateFormat("yyyyMMdd");
		retTime		= new_fmt1.format(java.util.Calendar.getInstance().getTime());
		fileTime	= newFmt2.format(java.util.Calendar.getInstance().getTime());
		//
		// Image directory Path for the application date
		//
		outDir		= dataDir;
		// PrintLog("output_path "+outDir);
		File outputDir	= new File(outDir);
		if (!outputDir.exists()) {
			PrintLog("Specified directory does not exist - Creating > " +
					 outDir);
			File fileOutdir	= new File(outDir);
			boolean mk_dir	= fileOutdir.mkdirs();
			if (mk_dir == false) {
				PrintLog("Error creating Directory " + mk_dir);
				return (0);
			}
		}
		reconDetail.setDbUsed(ldbUsed_1);
		//
		// Set up the headings etc for the report
		//
		// repPadSize = (92 - reportHdg.length())/2;
		// repPad = gUtil.pad(repPad, repPadSize, " ");
		//
		if (reportHdg.length() < 92) {
			repPadSize	= 92 - reportHdg.length();
			if (repPadSize > 2) {
				repPadSize	= repPadSize / 2;
			}
		}
		repPad	= GenUtil.pad(repPad, repPadSize, " ");
		//
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		leRep	= eRep;
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(appl_date);
		temp	= ("REPORT NAME:MONTCHEXLOAD-" + cycleNum + ".RPT" + repPad +
					reportHdg + repPad + "PAGE:");
		eRep.setHeadings(temp);
		temp	= ("                                                     " +
					"WEBFICHE INCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp	= ("                                             " +
					" CHECK DATA LOAD REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("ACCOUNT NUMBER     CHECK ACCT NUM  CHECK NUM          CH" +
					"ECK AMOUNT  PROC DATE   UNIQUE ISN CUR ABA          REAS" +
					"ON CODE    STATUS");
		eRep.setHeadings(temp);
		temp	= ("                                                        " +
					"ISSUE DATE  SWIFT REF                  PAYEE");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		//
		// Load or Reload Clear out the Chex, Chex_log and the Stats tables
		//
		Statement pstmt1	= dbConn.createStatement();
		PrintLog("ActionFlag : " + actionFlag);
		if (actionFlag.equals("LoadFile")) {
			try {
				pstmt1.executeUpdate("truncate table incl_chex ");
			} catch (SQLException e) {
				PrintLog("SQLException : " + e);
				PrintLog("Terminating the delete process of incl_chex");
				return (-1);
			}
			try {
				pstmt1.executeUpdate("truncate table incl_load_stats ");
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the delete process of incl_load_stats");
				return (-1);
			}
			//
			// Backup Incl_posi_Pay to BOD.
			//
			try {
				pstmt1.executeUpdate("truncate table incl_posi_pay_bod ");
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Truncate process of incl_posi_pay_bod");
				return (-1);
			}
			try {
				dbConn.setAutoCommit(false);
				pstmt1.executeUpdate("INSERT INTO incl_posi_pay_bod select * from incl_posi_pay");
				dbConn.commit();
				dbConn.setAutoCommit(true);
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Backup process of incl_posi_pay");
				return (-1);
			}
			//
			// Backup Incl_posi_Pay_log to BOD
			//
			try {
				pstmt1.executeUpdate("truncate table incl_posi_pay_log_bod ");
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Truncate process of incl_posi_pay_log_bod");
				return (-1);
			}
			try {
				dbConn.setAutoCommit(false);
				pstmt1.executeUpdate("INSERT INTO incl_posi_pay_log_bod select * from incl_posi_pay_log");
				dbConn.commit();
				dbConn.setAutoCommit(true);
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Backup process of incl_posi_pay_log");
				return (-1);
			}
		}
		if (actionFlag.equals("ReLoadFiles") && cycleNum == 1) {
			try {
				pstmt1.executeUpdate("truncate table incl_chex ");
			} catch (SQLException e) {
				PrintLog("SQLException : " + e);
				PrintLog("Terminating the delete process of incl_chex");
				return (-1);
			}
			try {
				pstmt1.executeUpdate("truncate table incl_load_stats ");
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the delete process of incl_load_stats");
				return (-1);
			}
			//
			// Delete the chex mods logged today
			//
			try {
				if (dbUsed.equals("ORACLE")) {
					pstmt1.executeUpdate("delete from  incl_chex_log_" +
										 appl_date.substring(0, 6) + " where " +
							"SUBSTR(to_char(chlog_last_modified,'yyyymmdd hh24:mi:ss'),1,8)='" +
										sn_date + "'");
				} else {
					pstmt1.executeUpdate("delete from  incl_chex_log_" +
										appl_date.substring(0, 6) + " where " +
										"SUBSTR(chlog_last_modified,1,8)='" + sn_date + "'");
				}
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the delete process of incl_chex_log_"
						+ appl_date.substring(0, 6));
				return (-1);
			}
			//
			// Delete Incl_posi_Pay and Restore from the BOD.
			//
			try {
				pstmt1.executeUpdate("truncate table incl_posi_pay ");
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Truncate process of incl_posi_pay");
				return (-1);
			}
			try {
				dbConn.setAutoCommit(false);
				pstmt1.executeUpdate("INSERT INTO incl_posi_pay select * from incl_posi_pay_bod");
				dbConn.commit();
				dbConn.setAutoCommit(true);
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Restore process of incl_posi_pay");
				return (-1);
			}
			//
			// Delete Incl_posi_Pay_log and Restore from the BOD.
			//
			try {
				pstmt1.executeUpdate("truncate table incl_posi_pay_log ");
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Truncate process of incl_posi_pay_log");
				return (-1);
			}
			try {
				dbConn.setAutoCommit(false);
				pstmt1.executeUpdate("INSERT INTO incl_posi_pay_log select * from incl_posi_pay_log_bod");
				dbConn.commit();
				dbConn.setAutoCommit(true);
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the Restore process of incl_posi_pay_log");
				return (-1);
			}
			// Here clear out the files loaded so that they are populated properly if
			// a previously unprocessed file has become available.
			try {
				int row_count	= icUtil.GetControlRow(dbConn, ctlSelector, prodId);
				if (row_count == 0) {
					;
				}
				ctlDetail	= ctlSelector.getARow();
				ctlDetail.setDbUsed(dbUsed);
				//
				// Here set file_loaded 1...5
				//
				ctlDetail.setFile_loaded(" ");
				ctlDetail.setFile_loaded(" ");
				ctlDetail.setFile_loaded_1(" ");
				ctlDetail.setFile_loaded_2(" ");
				ctlDetail.setFile_loaded_3(" ");
				ctlDetail.setFile_loaded_4(" ");
				ctlDetail.setFile_loaded_5(" ");
				if (dbUsed.equals("MySQL")) {
					ctlDetail.setFile_loaded_time("NULL");
				} else if (dbUsed.equals("ORACLE")) {
					ctlDetail.setFile_loaded_time("sysdate");
				}
				try {
					int retVal	= icUtil.InsertUpdateControl(dbConn, ctlDetail,	userId, insOrUpd);
					// int ret_val = icUtil.UpdateControl(dbConn, ctlDetail);
					if (retVal == 0) {
						;
					}
				} catch (Throwable t) {
					PrintLog("Error Updating Control ->" + t.toString());
				}
			} catch (Throwable t) {
				PrintLog("Error Getting Control ->" + t.toString());
				t.printStackTrace();
			}
		}
		pstmt1.close();
		// facsfileyyyymmdd_
		PrintLog("Will process " + dataDir + lfileToLoad + " Cycle ID: " + cycleId);
		procDate				= lappl_date_1;
		checkCurr				= lfileToLoad.substring(17, 20).toUpperCase();
		checkCurrL				= lfileToLoad.substring(17, 20);
		earmarkFile				= "inclearmark" + checkCurrL + "_" + procDate + ".dat";
		PrintLog("Will create " + loutDir_1 + earmarkFile);
		FileOutput writeARec	= new FileOutput(loutDir_1 + earmarkFile);
		//
		// Write the header record
		//
		temp = "";
		if (checkCurr.equals("CAD")) {
			procNum	= "1";
		} else {
			procNum	= "2";
		}
		outRec = ("H" + procDate + "00000000" + "99000" + procNum + "EARMARK" +
					GenUtil.pad(temp, 106, " ") + "A7");
		writeARec.writeLine(outRec);
		try {
			BufferedReader inRead = new BufferedReader(new FileReader(dataDir + fileToLoad));
			String inRec = "";
			// String one_rec = "";
			// First record is a Header so let us process it and get it out of
			// the way
			while ((inRec = inRead.readLine()) != null) {
				//
				// Record Type A Length = 35
				// record Type B Length = 24 + x * 120 where x=1 to 12 is the
				// number checks in the record
				//
				// PrintLog("record type "+inRec.substring(0,1) + " record size "+inRec.length());
				//
				if (inRec.substring(0, 1).equals("A")) {
					// PrintLog(">"+inRec);
				} else if (inRec.substring(0, 1).equals("B")) {
					// PrintLog(">"+inRec.substring(0,24));
					for (int idx = 24; idx <= inRec.length() - 120; idx += 120) {
						if (inRec.substring(idx, idx + 120).trim().length() == 0) {
							// Skip
						} else {
							// PrintLog(inRec.substring(idx,idx+120)); //
							// Transaction Type 3
							// Amount 10
							checkAmount = inRec.substring(idx + 3, idx + 13);
							// UNUSED 7
							// inRec.substring(idx+13,idx+20)+"\t"+
							// RT 8
							rTransit = inRec.substring(idx + 20, idx + 28);
							// Acount Number 12
							checkAccount = inRec.substring(idx + 28, idx + 40);
							// UNUSED 4
							// inRec.substring(idx+40,idx+44)+"\t"+
							// Origin
							// inRec.substring(idx+44,idx+49)+"\t"+
							// Data Center 5
							// File
							// inRec.substring(idx+49,idx+53)+"\t"+
							// Creation # 4
							// UNUSED 2
							// inRec.substring(idx+53,idx+55)+"\t"+
							// Trace Number 10
							checkIsn = inRec.substring(idx + 55, idx + 65);
							// MICR SN (Check #) 12
							checkNum = inRec.substring(idx + 65, idx + 77); 
							// inRec.substring(idx+77,idx+81)+"\t"); // UNUSED
							recCtr++;
							// PrintLog(checkAccount+" "+checkNum+" "+checkIsn+"  "+recCtr);
							// // Transaction Type 3
							ProcessMICR(dbConn, eRep, userId);
							UpdateRecon(dbConn);
							WriteEarmark(writeARec);
						}
					}
				} else if (inRec.substring(0, 1).equals("T")) {
					// FIELD POSITION SIZE CONTENTS AND FORMAT FIELD
					// NAME/DESCRIPTION
					// 01 1 1 "T" - Character Record Type "T"
					// 02 2-10 9 "XXXXXXXXX" - Numeric Record Count in file
					// Increments by one (1)
					// for each 80 record.
					// 03 11-15 5 "001BB" - Numeric Origin Data Centre (Same as
					// "A" record - field 03).
					// 04 16-19 4 "0001"-"9999" Numeric File Creation Number
					// (Same as "A" Record - Field 04).
					// 05 20-33 14 "XXXXXXXXXXXXXX" Batch Total Numeric (Transit
					// or account level)
					// 2 decimal places understood
					// 06 34-41 8 "XXXXXXXX" Batch Item Count # of items in the
					// batch (transit
					// or account)
					// 07 42-80 39 Bank reserved for future use.
					//
				} else if (inRec.substring(0, 1).equals("Z")) {
					// 01 1 1 "Z" - Character Record Type "Z"
					// 02 2-10 9 "XXXXXXXXX" - Numeric Record Count Last record
					// number which
					// represents total number of
					// records incl. A, B, T and Z records.
					// 03 11-15 5 "001BB" - Numeric Origin Data Centre (Same as
					// "A" record - field 03).
					// 04 16-19 4 "0001" - "9999"-Numeric File Creation Number
					// Same as "A" Record - Field 04)
					// 05 20-33 14 "XXXXXXXXXXXXXX" Batch Total
					//
					fileTotalAmount = inRec.substring(24, 38);
					// Numeric (Transit or account level) 2 decimal
					// places understood.
					// 06 34-41 8 "XXXXXXXX" Total # of items in the file.
					//
					itemCountVal = inRec.substring(38, 46);
					// 07 42-80 39 Bank reserved for future use.
					recCtr_s = recCtr + "";
					maxLen = 3;
					recCtr_s = GenUtil.pad(recCtr_s, maxLen, "0");
					outRec = "TEOD0" + recCtr_s + GenUtil.pad(temp, 130, " ");
					writeARec.writeLine(outRec);
					writeARec.close();
					//
					try {
						cp_cmd[2] = "/bin/cp " + loutDir_1 + earmarkFile + " " + reportDir;
						PrintLog("Will Copy Earmark file " + loutDir_1 +
								 earmarkFile + " to " + reportDir);
						// proc = Runtime.getRuntime().exec(cp_cmd);
						Runtime.getRuntime().exec(cp_cmd);
					} catch (Throwable t) {
						t.printStackTrace();
						PrintLog("" + t);
					}
					//
					ProcessTrailer(dbConn);
				} else {
					// PrintLog(">"+inRec);
				}
				// recCtr++;
			}
			PrintLog("txt file :recs " + recCtr);
			PrintLog("Check Records " + recCtr);
			inRead.close();
		} catch (IOException t) {
			System.out.println("FIle problem  = " + t);
		}
		temp = invalid_accts + "";
		String invalid_accts_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		temp = missing_chknum + "";
		String missing_chknum_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		temp = pospay_missing + "";
		String pospay_missing_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		temp = stale_check + "";
		String stale_check_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		temp = cleared_check + "";
		String cleared_check_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		temp = stopay + "";
		String stopay_s = ("      ").substring(0, 6 - temp.length()) + temp;
		temp = exceed_limit + "";
		String exceed_limit_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		temp = authorize + "";
		String authorize_s = ("      ").substring(0, 6 - temp.length()) + temp;
		temp = completed + "";
		String completed_s = ("      ").substring(0, 6 - temp.length()) + temp;
		temp = tot_chex_25 + "";
		String tot_chex_25_s = ("      ").substring(0, 6 - temp.length())
				+ temp;
		eRep.WriteHeadings();
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                           "
				+ "* * *  C h e c k  L o a d  S t a t i s t i c s (CUMULATIVE) * * *");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       "
				+ "     Invalid Accounts:  " + invalid_accts_s);
		eRep.WriteDetail("                                                       "
				+ "Missing Check Numbers:  " + missing_chknum_s);
		eRep.WriteDetail("                                                       "
				+ "      Posipay Missing:  " + pospay_missing_s);
		eRep.WriteDetail("                                                       "
				+ "   Stale Dated Checks:  " + stale_check_s);
		eRep.WriteDetail("                                                       "
				+ "       Cleared Checks:  " + cleared_check_s);
		eRep.WriteDetail("                                                       "
				+ "              Stopped:  " + stopay_s);
		eRep.WriteDetail("                                                       "
				+ "Exceeded Limit Amount:  " + exceed_limit_s);
		eRep.WriteDetail("                                                       "
				+ "  Await Authorization:  " + authorize_s);
		eRep.WriteDetail("                                                       "
				+ "            Completed:  " + completed_s);
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       " +
						 "     FILE TOTALS");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       "
				+ "   FILE TOTAL CHECKS :  " + tot_chex_25_s);
		eRep.WriteDetail("                                                       "
				+ "   FILE TOTAL AMOUNT :  " + tot_amt_25_s);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       "
				+ "     *** END OF REPORT ***");
		eRep.Close();
		return (1);
	}
	//
	public void ProcessMICR(Connection dbConn,
	// ActionErrors errors,
			EcontReportUtil eRep, String userId) throws SQLException {
		//
		//
		moduleName = "ProcessMICR: ";
		int insert_chex = 1; // 1=insert 2=update
		int flag = 0;
		// int slashPos = 0;
		// int ret_val = 0; // Success
		// boolean acct_exists = false;
		String temp = "";
		String zerosStr = "00000000000000000000";
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// ArrayList<String> dummy_list = new ArrayList(); // Dummy array
		// AccountSelector acctSelector = new AccountSelector();
		ChexDetail chexDetail = new ChexDetail();
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		InclAcctUtil acUtil = new InclAcctUtil();
		InclChexUtil chUtil = new InclChexUtil();
		// int result = 0;
		//
		process_date_25 = procDate;
		check_num_25 = checkNum;
		if (check_num_25.equals("")) {
			// was done to strip leading zeroes
			// check_num_25 = Integer.parseInt(check_num_25)+"";
			// } else {
			check_num_25 = "0";
			tot_invalid_cnum++;
		} else {
			for (int n = 0; n < check_num_25.length(); n++) {
				if (gUtil.isNumeric(check_num_25.substring(n, n + 1)) == true) {
					temp += check_num_25.substring(n, n + 1);
				}
			}
			if (temp.equals("")) {
				check_num_25 = "0";
				tot_invalid_cnum++;
			} else {
				check_num_25 = temp;
			}
		}
		temp = "";
		account_num_25 = "";
		// check_num_690 = Integer.parseInt(one_rec.substring(39,
		// 54).trim())+"";
		ext_proc_control_25 = " ";
		routing_transit_25 = rTransit;
		// check_digit_25 = one_rec.substring(26, 27).trim();
		if (checkAccount.trim().length() == 0) {
			checkAccount = "0";
		} else {
			checkAccount = Long.parseLong(checkAccount) + "";
		}
		temp = checkAccount;
		if ((temp.length() == 0) || (temp.equals("/"))) {
			account_num_25 = "0000000000000000000";
		} else {
			for (int indx = 0; indx < temp.length(); indx++) {
				if (gUtil.isNumeric(temp.substring(indx, indx + 1)) == true) {
					account_num_25 += temp.substring(indx, indx + 1);
				}
			}
			if (account_num_25.equals("")) {
				account_num_25 = "0000000000000000000";
			}
		}
		if (account_num_25.length() > 17) {
			account_num_25 = account_num_25.substring(0, 17);
		}
		if (checkAmount.trim().length() > 0) {
			check_amount_25 = (Double.parseDouble(checkAmount) / 100) + "";
			// PrintLog("check_amount_25 "+check_amount_25);

		} else {
			check_amount_25 = "0.00";
		}
		unique_isn_25	= checkIsn;
		// PrintLog("in chexRecType_25 UniqueISN="+unique_isn_25);
		micr_valid_25	= " ";
		misc_codes_25	= " ";
		reason_codes	= "";
		check_status	= "";
		check_amount_od	= "0.0";
		last_modified	= "NULL";
		mod_user		= userId.trim();
		mod_func		= "Load File";
		if (check_amount_25.trim().length() == 0) {
			check_amount_25	= "0";
		}
		tot_amt_25	= tot_amt_25 + (Double.parseDouble(check_amount_25));
		tot_chex_25++;
		//
		jpegFile = ("Check_" + zerosStr.substring(0, 17 - account_num_25.length()) +
					 account_num_25 + "_" +
					 zerosStr.substring(0, 15 - check_num_25.length()) +
					 check_num_25 + "_" +
					 zerosStr.substring(0, 15 - unique_isn_25.length()) + unique_isn_25);
		//
		// Used for the Report if there is an error
		//
		chexDetail.setBankId(bankId);
		chexDetail.setChex_check_amount(check_amount_25);
		chexDetail.setChex_proc_date(process_date_25);
		// PrintLog(" Process_date_25 >"+process_date_25);
		//
		// Do some Basic Validations Here and Insert the Rows if Errors
		// Detected.
		// Else send the ChexDetail record to the Main InsertUpdate routine to
		// Validate for Account, Limits, StopPay and PosiPay
		if (check_num_25.equals("0")) {
			reason_codes = "C";
			check_status = "R";
		}
		if (account_num_25.equals("")) {
			if (reason_codes.equals("")) {
				reason_codes	= "A";
			} else {
				reason_codes	+= "A";
			}
			check_status	= "E";
		}
		try {
			bank_acct	= acUtil.CheckAccountExists(dbConn, appl_date, account_num_25);
			if (bank_acct.equals("")) {
				bank_acct		= account_num_25;
				// PrintLog("Back from CheckAccountExists"+bank_acct);
				reason_codes	+= "A";
				check_status	= "E";
				tot_invalid_acct++;
				// errors.add ("chex_upderror1", new ActionError
				// ("error.updatingchex"));
				// PrintLog("Acct_num"+chex_account_num);
				// return (ret_val);
			} else {
				if (bank_acct.indexOf(checkCurr) > 0) {
				} else {
					if (reason_codes.equals("")) {
						//
						reason_codes	= "B";
					} else {
						reason_codes	+= "B";
					}
					check_status	= "E";
				}
			}
		} catch (Throwable t) {
			PrintLog("Error Validating Account ->" + t.toString());
		}
		//
		// Here Insert the row
		if (!check_status.equals("")) {
			sql.setLength(0);
			sql.append("INSERT INTO incl_chex VALUES (");
			sql.append("'" + process_date_25 + "', ");
			sql.append("'" + account_num_25 + "', ");
			sql.append("'" + check_num_25 + "', ");
			sql.append("'" + bank_acct + "', ");
			sql.append("'" + check_num_25 + "', ");
			sql.append("'" + routing_transit_25 + "', ");
			sql.append("'" + checkCurr + "', ");
			sql.append("'" + check_amount_25 + "', ");
			sql.append("' ', "); // proc_control
			sql.append("'" + ext_proc_control_25 + "', "); // ext proc control
			sql.append("'" + micr_valid_25 + "', "); // image locator
			sql.append("'" + unique_isn_25 + "', "); // unique ISN
			sql.append("' ', "); // addenda rec flag
			sql.append("' ', "); // orig routing trnasit
			sql.append("'" + misc_codes_25 + "', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("'" + issue_date + "', ");
			sql.append("'" + payee + "', ");
			sql.append("'" + payee_addr1 + "', ");
			sql.append("'" + payee_addr2 + "', ");
			sql.append("'" + payee_addr3 + "', ");
			sql.append("'" + comments + "', ");
			sql.append("'" + reason_codes + "', ");
			sql.append("'" + check_status + "', ");
			sql.append("'" + check_amount_od + "', ");
			sql.append("'" + jpegFile + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + mod_user + "', ");
			sql.append("'" + mod_func + "')");
			dbConn.setAutoCommit(false);
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				// result = statement.executeUpdate(sql.toString());
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Chex ->" + e.toString());
			}
			statement.close();
			sql.setLength(0);
			sql.append("INSERT INTO incl_chex_log_"
					+ process_date_25.substring(0, 6) + " VALUES (");
			sql.append("'" + process_date_25 + "', ");
			sql.append("'" + account_num_25 + "', ");
			sql.append("'" + check_num_25 + "', ");
			sql.append("'" + bank_acct + "', ");
			sql.append("'" + check_num_25 + "', ");
			sql.append("'" + routing_transit_25 + "', ");
			sql.append("'" + checkCurr + "', ");
			sql.append("'" + check_amount_25 + "', ");
			sql.append("' ', "); // proc_control
			sql.append("'" + ext_proc_control_25 + "', "); // ext proc control
			sql.append("'" + micr_valid_25 + "', "); // image locator
			sql.append("'" + unique_isn_25 + "', "); // unique ISN
			sql.append("' ', "); // addenda rec flag
			sql.append("' ', "); // orig routing trnasit
			sql.append("'" + misc_codes_25 + "', "); // ISN
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("' ', ");
			sql.append("'" + issue_date + "', ");
			sql.append("'" + payee + "', ");
			sql.append("'" + payee_addr1 + "', ");
			sql.append("'" + payee_addr2 + "', ");
			sql.append("'" + payee_addr3 + "', ");
			sql.append("'" + comments + "', ");
			sql.append("'" + reason_codes + "', ");
			sql.append("'" + check_status + "', ");
			sql.append("'" + check_amount_od + "', ");
			sql.append("'" + jpegFile + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + mod_user + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog("Log Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				// result = statement.executeUpdate(sql.toString());
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error inserting Chex Log ->" + e.toString());
				// PrintLog(": Log Insert SQL ---> "+sql);
			}
			dbConn.commit();
			dbConn.setAutoCommit(true);
			statement.close();
		} else { // do the other validations and insert the Checks
			chexDetail.setDbUsed(dbUsed);
			chexDetail.setAppl_date(appl_date);
			chexDetail.setChex_proc_date(process_date_25);
			chexDetail.setChex_orig_account_num(account_num_25);
			chexDetail.setChex_orig_check_num(check_num_25);
			chexDetail.setChex_account_num(bank_acct);
			chexDetail.setChex_check_num(check_num_25);
			chexDetail.setChex_routing_transit(routing_transit_25);
			chexDetail.setChex_check_currency(checkCurr);
			chexDetail.setChex_check_amount(check_amount_25);
			chexDetail.setChex_proc_control(" ");
			chexDetail.setChex_ext_proc_control(ext_proc_control_25);
			chexDetail.setChex_image_locator(micr_valid_25);
			chexDetail.setChex_unique_isn(unique_isn_25);
			chexDetail.setChex_addenda_rec_flag(" ");
			chexDetail.setChex_orig_inst_rte(" ");
			chexDetail.setChex_isn(misc_codes_25);
			chexDetail.setChex_budget_id("");
			chexDetail.setChex_return_date(" ");
			chexDetail.setChex_return_reason(" ");
			chexDetail.setChex_return_status(" ");
			chexDetail.setChex_BOFD_aba(" ");
			chexDetail.setChex_BOFD_date(" ");
			chexDetail.setChex_extra_1(" ");
			chexDetail.setChex_extra_2(" ");
			chexDetail.setChex_issue_date(issue_date);
			chexDetail.setChex_payee(payee);
			chexDetail.setChex_payee_addr1(payee_addr1);
			chexDetail.setChex_payee_addr2(payee_addr2);
			chexDetail.setChex_payee_addr3(payee_addr3);
			chexDetail.setChex_comments(comments);
			chexDetail.setChex_reason_codes(reason_codes);
			chexDetail.setChex_check_status(check_status);
			chexDetail.setChex_amount_od(check_amount_od);
			chexDetail.setChex_image(jpegFile);
			chexDetail.setChex_last_modified(last_modified);
			chexDetail.setChex_mod_user(userId);
			chexDetail.setChex_mod_func("Load File");
			insert_chex	= 1; // do not change
			try {
				flag	= chUtil.InsertUpdateChex(dbConn, chexDetail, insert_chex);
				if (flag == 0) {
					;
				}
			} catch (Throwable t) {
				PrintLog("Error inserting Chex ->>>" + t.toString());
				t.printStackTrace();
			}
			reason_codes	= chexDetail.getChex_reason_codes();
			check_status	= chexDetail.getChex_check_status();
			// PrintLog("Reason Codes: "+reason_codes+
			// "  Check Status: "+check_status);
			bofd			= " ";
			bofdDate		= " ";
		}
		//
		// Here Print the report line
		//
		payee		= chexDetail.getChex_payee();
		iss_date	= chexDetail.getChex_issue_date_disp();
		sw_ref		= chexDetail.getChex_extra_1();
		if (check_status.equals("A")) {
			check_status	= "AUTHORIZE";
		} else if (check_status.equals("E")) {
			check_status	= "ERROR";
		} else if (check_status.equals("R")) {
			check_status	= "REJECTED";
		} else if (check_status.equals("S")) {
			check_status	= "STOPPED";
		} else if (check_status.equals("Z")) {
			check_status	= "COMPLETE";
		}
		String temp_amt		= chexDetail.getChex_check_amount_disp();
		String temp_date	= chexDetail.getChex_proc_date_disp();
		temp	= ((bank_acct + "                   ").substring(0, 19) +
				  (account_num_25 + "                 ").substring(0, 16) +
				  (check_num_25 + "               ").substring(0, 15) +
				  ("                ").substring(0, 16 - temp_amt.length()) +
				  temp_amt + "  " + temp_date + "  " +
				  unique_isn_25.substring(0) + " " + checkCurr + " " +
				  routing_transit_25 + "     " +
				  (reason_codes + "               ").substring(0, 15) + check_status);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(temp);
		if (check_status.equals("COMPLETE") || check_status.equals("AUTHORIZE")) {
			temp = ("                                                        " +
					// "      " +
					(iss_date + "  ") +
					(sw_ref + "                           ").substring(0, 27) + 
					(payee + "                                   ").substring(0, 35));
			eRep.WriteDetail(temp);
		}
		check_status	= "";
		payee			= " ";
		sw_ref			= " ";
		iss_date		= "0";
	}
	//
	public void UpdateRecon(Connection dbConn) {
		//
		// Check if a recon records exists. If it exists and if it is of a
		// different processing date
		// then move the Images to that date.
		// Update the record as matched.
		//
		moduleName	= "UpdateRecon: ";
		// String reconProcDate	= "";
		// String oldDir		= "";
		String curSrc			= "";
		String accountPar		= Long.parseLong(checkAccount) + "";
		String chknumPar		= Long.parseLong(checkNum) + "";
		String amountPar		= Double.parseDouble(checkAmount) / 100 + "";
		PrintLog("User Id: "+ luserId + " Check Amount par " + amountPar);
		int insUpdFlag			= 0;
		int ins_or_upd			= 0;
		reconSelector.clearReconrows();
		try {
			rowCount	= recUtil.GetReconRows(dbConn, reconSelector, accountPar,
												chknumPar, amountPar);
			PrintLog("Got " + rowCount + " Recon Row/s ");
			if (rowCount > 0) {
				//
				// If it exists then update it
				//
				reconDetail			= reconSelector.getArow();
				// reconProcDate	= reconDetail.getRecon_proc_date();
				curSrc				= reconDetail.getRecon_source_micr();
				if (curSrc.equals(" ")) {
					reconDetail.setRecon_source_micr("Y");
					reconDetail.setRecon_status("M");
					reconDetail.setDbUsed(dbUsed);
					ins_or_upd		= 2;
					insUpdFlag		= recUtil.InsertUpdateRecon(dbConn, reconDetail,
																luserId, ins_or_upd);
					if (insUpdFlag == 0) {
						;
					}
				}
			} else {
				//
				// else insert one with XML source Y
				//
				reconDetail.setDbUsed(dbUsed);
				reconDetail.setRecon_proc_date(procDate);
				reconDetail.setRecon_account_num(account_num_25);
				reconDetail.setRecon_check_num(check_num_25);
				reconDetail.setRecon_routing_transit(routing_transit_25);
				reconDetail.setRecon_check_currency(checkCurr);
				reconDetail.setRecon_check_amount(check_amount_25);
				reconDetail.setRecon_unique_isn(checkIsn);
				reconDetail.setRecon_source_micr("Y");
				reconDetail.setRecon_source_xml(" ");
				reconDetail.setRecon_status("U");
				if (dbUsed.equals("ORACLE")) {
					reconDetail.setRecon_last_modified("sysdate");
				} else {
					reconDetail.setRecon_last_modified("NULL");
				}
				reconDetail.setRecon_mod_user(luserId);
				reconDetail.setRecon_mod_func("Add Recon");
				 // 1 for insert or 2 for update
				ins_or_upd = 1;
				insUpdFlag = recUtil.InsertUpdateRecon(dbConn, reconDetail,
														luserId, ins_or_upd);
			}
		} catch (Throwable t) {
			//String accountPar		= Long.parseLong(checkAccount) + "";
			//String chknumPar		= Long.parseLong(checkNum) + "";
			PrintLog("Exception getting Recon Row " + t);
			PrintLog("check Account par: " + accountPar + 
						" Check Number Par: " + chknumPar +
						" Check Amount par " + amountPar);
			//t.printStackTrace();
			// this will terminate the process
			// cod_performed = true;
		}
	}
	//
	public void WriteEarmark(FileOutput writeARec) {
		//
		moduleName	= "WriteEarmark: ";
		String checkNum_tr;
		temp		= "";
		maxLen		= 13;
		checkAmount	= GenUtil.pad(checkAmount, maxLen, "0");
		maxLen		= 3;
		recCtr_s	= recCtr + "";
		recCtr_s	= GenUtil.pad(recCtr_s, maxLen, "0");
		// PrintLog("recCtr_s "+recCtr_s);
		if (checkNum.trim().length() == 0) {
			checkNum	= "0";
		}
		checkNum_tr	= Long.parseLong(checkNum) + "";
		temp		= "";
		maxLen		= 10;
		temp		= GenUtil.pad(temp, maxLen, " ");
		checkNum_tr	= checkNum_tr + temp;
		checkIsn	= checkIsn + temp;
		checkAccount	= bank_acct + "                    ";
		// PrintLog("Account Num "+checkAccount);
		outRec	= ("D" + recCtr_s + "100" + checkAmount +
					"NO POSIPAY PROVIDED               " +
					checkIsn.substring(0, 10) + checkNum_tr.substring(0, 10) +
					"0" + ourAba + checkAccount.substring(0, 20) + "CK NUMBER " +
					checkNum_tr.substring(0, 10) + " DD " + "XXXX/XX/XX");
		writeARec.writeLine(outRec);
	}
	//
	// Check record type Z - File Control Record
	//
	public void ProcessTrailer(Connection dbConn)
	// ActionErrors errors,
	// int cycleNum,
	// String fileToLoad)
			throws SQLException {
		moduleName			= "ProcessTrailer ";
		DecimalFormat df	= new DecimalFormat("#,###,###,##0.00");
		invalid_accts		= 0;
		missing_chknum		= 0;
		pospay_missing		= 0;
		stopay				= 0;
		stale_check			= 0;
		exceed_limit		= 0;
		authorize			= 0;
		completed			= 0;
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		int result	= 0;
		String temp					= "";
		String check_status			= "";
		String check_reason_code	= "";
		String check_count			= "";
		if (dbConn == null)
			PrintLog("----------- NULL DBCONN ------------");
		//
		// Here write the Status counts and then the final stat record.
		// Insert a row for stats
		//
		/*
		PreparedStatement pstmt = dbConn.prepareStatement("insert into incl_load_stats(" +
														"stat_id, stat_field1, " +
														"stat_field2 ,stat_field3, " +
														"stat_field4, stat_field5, " +
														"stat_field6) values " + 
														"(?, ?, ?, ?, ?, ?, ?)");
		 */
		PreparedStatement pstmt = dbConn.prepareStatement("insert " +
									"into incl_load_stats(stat_id, stat_field1," +
											"stat_field2, stat_field3, stat_field4, " +
											"stat_field5, stat_field6, stat_field7, " +
											"stat_field8, stat_field9, stat_field10) " +
											"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		PrintLog(" Row id " + row_id + " CycleId: " + cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Detail Records");
		pstmt.setString(3, " ");
		pstmt.setString(4, " ");
		pstmt.setString(5, tot_chex_25 + "");
		temp = tot_amt_25 + "";
		if (temp.trim().length() == 0) {
			temp = "0";
		}
		tot_amt_25_s = df.format(Double.parseDouble(temp));
		pstmt.setString(6, tot_amt_25_s);
		pstmt.setString(7, " ");
		pstmt.setString(8, " ");
		pstmt.setString(9, " ");
		pstmt.setString(10, " ");
		pstmt.setString(11, "1");
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		//
		row_id++;
		PrintLog(" Row id " + row_id + " CycleId: " + cycleId);
		temp = fileTotalAmount;
		if (temp.trim().length() == 0) {
			temp = "0";
		}
		fileTotalAmount = df.format(Double.parseDouble(temp) / 100) + "";
		PrintLog(" File Total Amount " + fileTotalAmount);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Trailer Record");
		pstmt.setString(3, " ");
		pstmt.setString(4, " ");
		if (itemCountVal.trim().length() == 0) {
			itemCountVal = "0";
		}
		pstmt.setString(5, Integer.parseInt(itemCountVal) + "");
		pstmt.setString(6, fileTotalAmount);
		pstmt.setString(7, " ");
		pstmt.setString(8, " ");
		pstmt.setString(9, " ");
		pstmt.setString(10, " ");
		pstmt.setString(11, " ");
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		//
		// Here get the Stats we need
		//
		sql.setLength(0);
		sql.append("SELECT CHEX_REASON_CODES, ");
		sql.append("CHEX_CHECK_STATUS, COUNT(*) FROM ");
		sql.append("incl_chex GROUP BY CHEX_REASON_CODES, CHEX_CHECK_STATUS ");
		sql.append("ORDER BY CHEX_CHECK_STATUS");

		// Prepare the SELECT statement.*/
		statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet q_result = statement.executeQuery(sql.toString());
		while (q_result.next()) {
			// ChexSummary chexSummary = new ChexSummary();
			// Here add the fields tp the ChexDetail bean
			check_status = q_result.getString("CHEX_CHECK_STATUS").trim();
			check_reason_code = q_result.getString("CHEX_REASON_CODES");
			check_count = q_result.getString("COUNT(*)").trim();
			PrintLog("Check Status: " + check_status + " Reason Codes: "
					+ check_reason_code + " Check Count:  " + check_count);
			if (check_status.equals("E") && check_reason_code.equals("A")) {
				invalid_accts += Integer.parseInt(check_count);
			} else if (check_status.equals("E")
					&& check_reason_code.equals("B")) {
				invalid_accts += Integer.parseInt(check_count);
			} else if (check_status.equals("E")
					&& check_reason_code.equals("CA")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("E")
					&& check_reason_code.equals("MA")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("C")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("M")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals(" D")) {
				stale_check += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("H")) {
				cleared_check += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("L")) {
				exceed_limit += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("P")) {
				pospay_missing += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("Q")) {
				pospay_missing += Integer.parseInt(check_count);
			} else if (check_status.equals("S")
					&& check_reason_code.equals("S")) {
				stopay += Integer.parseInt(check_count);
			} else if (check_status.equals("R")
					&& check_reason_code.equals("T")) {
				stopay += Integer.parseInt(check_count);
			} else if (check_status.equals("Z")) {
				completed += Integer.parseInt(check_count);
			} else if (check_status.equals("A")) {
				authorize += Integer.parseInt(check_count);
			}
		}
		statement.close();
		//
		// Insert a row for status counts
		//
		// row_id = cycleNum*10;
		row_id = cycleId;
		sql.setLength(0);
		sql.append("INSERT INTO incl_load_stats VALUES (");
		sql.append("'" + row_id + "', ");
		sql.append("'" + lfileToLoad + "', ");
		sql.append("'" + invalid_accts + "', ");
		sql.append("'" + missing_chknum + "', ");
		sql.append("'" + pospay_missing + "', ");
		sql.append("'" + stale_check + "', ");
		sql.append("'" + cleared_check + "', ");
		sql.append("'" + stopay + "', ");
		sql.append("'" + exceed_limit + "', ");
		sql.append("'" + authorize + "', ");
		sql.append("'" + completed +"') ");
		// PrintLog("SQL ->"+sql);
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			dbConn.setAutoCommit(false);
			// result = statement.executeUpdate(sql.toString());
			statement.executeUpdate(sql.toString());
			dbConn.commit();
			dbConn.setAutoCommit(true);
		} catch (SQLException e) {
			PrintLog("Error inserting log ->" + e.toString());
			PrintLog("Error inserting log SQL ->" + sql);
		}
		statement.close();
		//
		// Update control row with file name and time stamp
		//
		SysadControlUtil icUtil = new SysadControlUtil();
		ControlDetail ctlDetail = new ControlDetail();
		ControlSelector ctlSelector = new ControlSelector();
		int insOrUpd = 2; // Update
		try {
			int row_count	= icUtil.GetControlRow(dbConn, ctlSelector, prodId);
			if (row_count == 0) {
				;
			}
			ctlDetail	= ctlSelector.getARow();
			ctlDetail.setDbUsed(dbUsed);
			//
			// Here set file_loaded 1...5
			//
			ctlDetail.setFile_loaded(lfileToLoad);
			filesLoaded	= ctlDetail.getFile_loaded_1() + "@" + 
							ctlDetail.getFile_loaded_2() + "@" + 
							ctlDetail.getFile_loaded_3() + "@" + 
							ctlDetail.getFile_loaded_4() + "@" + 
							ctlDetail.getFile_loaded_5();
			filesArray	= filesLoaded.split("@");
			result	= filesArray.length;
			PrintLog("File to Load: " + lfileToLoad);
			PrintLog("Array Size " + result + " Files Loaded: " + filesLoaded);
			if (result == 0) {
				ctlDetail.setFile_loaded_1(lfileToLoad);
			} else if (result == 1) {
				ctlDetail.setFile_loaded_2(lfileToLoad);
			} else if (result == 2) {
				ctlDetail.setFile_loaded_3(lfileToLoad);
			} else if (result == 3) {
				ctlDetail.setFile_loaded_4(lfileToLoad);
			} else {
				ctlDetail.setFile_loaded_5(lfileToLoad);
			}
			/*
			for (int idx=0; idx < filesArray.length; idx++) {
				PrintLog("File Loaded "+idx+" "+filesArray[idx]);
				if (filesArray[idx].equals("")) {
					if (idx == 0) {
						ctlDetail.setFile_loaded_1(fileLoaded);
					} else if (idx == 1) {
						ctlDetail.setFile_loaded_2(fileLoaded);
					} else if (idx == 2) {
						ctlDetail.setFile_loaded_3(fileLoaded);
					} else if (idx == 3) {
						ctlDetail.setFile_loaded_4(fileLoaded);
					} else {
						ctlDetail.setFile_loaded_5(fileLoaded);
					}
				}
			}
			*/
			ctlDetail.setFile_loaded(lfileToLoad);
			if (dbUsed.equals("MySQL")) {
				ctlDetail.setFile_loaded_time("NULL");
			} else if (dbUsed.equals("ORACLE")) {
				ctlDetail.setFile_loaded_time("sysdate");
			}
			try {
				int retVal	= icUtil.InsertUpdateControl(dbConn, ctlDetail,	userId, insOrUpd);
				// int ret_val = icUtil.UpdateControl(dbConn, ctlDetail);
				if (retVal == 0) {
					;
				}
			} catch (Throwable t) {
				PrintLog("Error Updating Control ->" + t.toString());
			}
		} catch (Throwable t) {
			PrintLog("Error Getting Control ->" + t.toString());
			t.printStackTrace();
		}
		statement.close();
		row_id		= 0;
		moduleName	= calledFrom;
	}
}
