package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.text.*;
import java.util.*;

import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.actions.*;
//import com.webfiche.checkpoint.deposits.beans.DepsDetail;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;
//import com.sun.media.jai.codec.*;
//import javax.media.jai.*;
//import java.awt.image.RenderedImage;
//import java.awt.image.renderable.ParameterBlock;

@Service
public final class UploadNYChex {
	// Process Statistics Counters
	static String  dbUsed		= "";
	static String  appl_date	= "";
	static String  temp			= "";
	static String  outDir		= "";
	static String  prodId		= "C";
	static int	invalid_accts	= 0;
	static int	fraudAlert		= 0;
	static int	missing_chknum	= 0;
	static int	pospay_missing	= 0;
	static int	stale_check		= 0;
	static int	cleared_check	= 0;
	static int	stopay			= 0;
	static int	exceed_limit	= 0;
	static int	authorize		= 0;
	static int	completed		= 0;
	static int	ret_val			= 0;			// Success
	//static int checksProcessed= 0;
	static Long highChNum		= 0L;
	static Long checkNumLong	= 0L;
	static Long FraudLimit		= 0L;
	static int averageChecks	= 0;
	static double  tot_amt_25	= 0.0;
	static double  tot_amt_file = 0.0;
	static double  bundleAmt	= 0.0;
	static int	tot_chex_25		= 0;
	static int	bundleCount		= 0;
	static int	cycleId			= 0;
	static int	row_id			= 0;
	static int	startImage		= 0;
	static int	startJpeg		= 0;
	static long	skipLen			= 0;
	static String recType		= "";
	static String reportHdg		= null;
	static String repPad		= "";
	static String sevenMonths	= "";
	static String threeMonths	= "";
	static int	repPadSize		= 0;
	//
	// File Header Record 01 Fields
	//
	static String  rec_type_01;				// rec_type_code_101;
	static String  std_level_01;			// prio_code_101;
	static String  file_type_01;			// P=prod T=test
	static String  dest_aba_01;				// receiving_aba_101;
	static String  orig_aba_01;				// origination_aba_101;
	static String  process_date_01;			// process_date_101;
	static String  dest_inst_name_01;		// dest_inst_name_101;
	static String  orig_inst_name_01;		//
	static String  file_ID_modifier_01;		// 0 thru 9
	//
	// Cash Letter (cl) Header Record 1001 Fields
	//
	static String  rec_type_10;				// rec_type_code_5290;
	static String  dest_aba_10;				// orig_inst_name_5290;
	static String  orig_aba_10;				// origination_aba_5290;
	static String  cl_biz_date_10;			// process_date_5290;
	static String  cl_cre_date_10;			//
	static String  cl_cre_time_10;			//
	// static String cl_empty_ind_10; //check_srv_trncd_5290;
	// static String truncation_ind_10; //batch_seq_5290;
	// static String cl_id_10; //batch_seq_5290;
	// static String orig_contact_name_10; //batch_seq_5290;
	// static String orig_contact_num_10; //batch_seq_5290;
	//
	// Bundle Header Record 20 fields
	//
	static String  rec_type_20;				   // record_type_693;
	static String  dest_aba_20;				   // bundle_num_693;
	static String  orig_aba_20;				   // item_count_693;
	static String  biz_date_20;				   // bundle_amount_693;
	static String  cre_date_20;
	static String  bundle_id_20;
	static String  bundle_seq_20;
	static String  cycleNumber_20;
	//
	// Check Detail Record 25 Fields
	//
	static String  rec_type_25;
	static String  check_num_25;
	static String  ext_proc_control_25;
	static String  routing_transit_25;
	//static String check_digit_25;
	static String  account_num_25;
	static String  check_amount_25;
	static String  unique_isn_25;
	static String  ret_accept_ind_25;
	//Next field stored in chex_ext_proc_control
	static String  micr_valid_25;
	static String  payorAcct;
	static String  payorName;
	static String  payorStatus;
	//following made up of
	//1 Document type Indicator
	//2 Return Acceptance Indicator
	//3 MICR Valid code
	//4 Bank of First Deposit (BFD) indicator
	//5 Check Detail Record Addendum Count
	//6 On Us Format
	//Keep in Image locator column
	//
	static String  misc_codes_25;
	static String  process_date_25;
	//
	// Check Detail Record 26 Fields
	//
	String		 bofd		   = " ";
	String		 bofdDate	   = " ";
	//
	// Check Detail Addendum C Record type 28
	//
	static String  rec_type_28;
	static String  dest_aba_28;
	static String  orig_aba_28;
	static String  biz_date_28;
	//
	// Image Records
	//
	static int	 imageRec_seq;
	static String  imageRec;
	static String  jpegFile;
	static boolean jpegCreated;
	static boolean rec25Flag;
	static boolean clearedCheck;
	//
	// Bundle Control Record 70 Fields
	//
	static String  rec_type_70;				// record_type_8290;
	static String  bundle_count_70;			// srv_class_code_8290;
	static String  bundle_tot_amt_70;		// det_item_count_8290;
	static String  micr_val_totamt_70;		// tot_amount_8290
	// Cash Letter Control Record 90 Fields
	static String  rec_type_90;				// record_type_8290;
	static String  bundle_count_90;			// srv_class_code_8290;
	static String  cl_count_90;				// srv_class_code_8290;
	static String  cl_tot_amt_90;			// det_item_count_8290;
	static String  dest_name_90;
	static String  orig_name_90;
	static String  settle_date_90;
	// Record 99 Fields
	static String  rec_type_99;				// record_type_9;
	static String  cl_count_99;				// batch_count_9;
	static String  tot_recs_99;				// detail_count_9;
	static String  tot_items_99;
	static String  file_tot_amt_99;			// total_amount_9;
	// Incl Checx Table fields
	static String  issue_date	= "0";
	static String  iss_date	 	= " ";
	static String  sw_ref		= " ";
	static String  payee		= " ";
	static String  payee_addr1	= " ";
	static String  payee_addr2	= " ";
	static String  payee_addr3	= " ";
	static String  comments	  	= " ";
	static String  reason_codes;
	static String  check_status;
	static String  check_amount_od;
	static String  last_modified;
	static String  mod_user;
	static String  mod_func;
	//
	static String  defCurr		= "";
	static String  bankId		= "";
	static String  checkImage	= "";
	//static TreeMap <String, Integer>	chexCount	= new TreeMap <String, Integer>(); 
	static TreeMap <String, Long>	highCheckNum	= new TreeMap <String, Long>(); 
	static TreeMap <String, Integer>	monthAverage= new TreeMap <String, Integer>(); 
	String			fileLoaded	= "";
	String			filesLoaded	= "";
	String			fileToLoad	= "";
	String[]		filesArray	= {"","","","",""};
	String			userId		= "";
	String			moduleName	= "";
	String			calledFrom	= "";
	String			actionType	= "";
	//
	GenUtil		gUtil			= new GenUtil();
	SysadControlUtil icUtil		= new SysadControlUtil();
	ControlDetail ctlDetail		= new ControlDetail();
	ControlSelector ctlSelector	= new ControlSelector();
	//
	InclAcctUtil acUtil		= new InclAcctUtil();
	InclChexUtil chUtil		= new InclChexUtil();
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> UploadNYChex." + moduleName + " - " + userId + " " + logMsg);
	}
	//
	public int UpLoadMICR(Connection dbConn, String dbUsed_1,
			String appl_date_1, String dataDir, String reportDir,
			String file_to_load, int cycleNum, String actionFlag,
			String user_name) throws SQLException, Exception {
		moduleName			= "UpoadMICR: ";
		String temp			= "";
		actionType			= actionFlag;
		// Process proc;
		userId				= user_name;
		fileToLoad			= file_to_load;
		EcontServletContextListener escl	= new EcontServletContextListener();
		reportHdg			= escl.getReportHdg();
		defCurr				= escl.getDefCurr();
		bankId				= escl.getBankId();
		tot_amt_25			= 0.0;
		tot_chex_25			= 0;
		bundleAmt			= 0.0;
		bundleCount			= 0;
		cycleId				= cycleNum * 1000;
		dbUsed				= dbUsed_1;
		temp				= file_to_load.substring(file_to_load.indexOf("4.D")+2, 
													 file_to_load.indexOf(".dat"));
		temp				= temp.replaceAll("\\.","_");
		String reportName	= "chexload_" + temp + ".rpt";
		appl_date			= appl_date_1;
		int insOrUpd		= 2;		//update
		PrintLog("Appl Date: " + appl_date + " DB Used " + dbUsed + " Cycle Num: " + cycleNum);
		//
		// Get todays calendar date
		//
		String sn_mm		= "";
		String sn_dd		= "";
		String sn_yy		= "";
		String sn_date		= "";
		java.util.GregorianCalendar now	= new java.util.GregorianCalendar();
		sn_yy				= now.get(Calendar.YEAR) + "";
		sn_mm				= (now.get(Calendar.MONTH) + 1) + "";
		if (sn_mm.length() == 1) {
			sn_mm		= '0' + sn_mm;
		}
		sn_dd				= now.get(Calendar.DATE) + "";
		if (sn_dd.length() == 1) {
			sn_dd			= '0' + sn_dd;
		}
		sn_date				= sn_yy + sn_mm + sn_dd;
		//
		// Create Image directory for today
		//
		outDir				= dataDir;
		// PrintLog("output_path "+out_dir);
		File outputDir		= new File(outDir);
		if (!outputDir.exists()) {
			PrintLog("Specified directory does not exist - Creating > "	+ outDir);
			File fileOutdir	= new File(outDir);
			boolean mk_dir	= fileOutdir.mkdirs();
			if (mk_dir == false) {
				PrintLog("Error creating Directory " + mk_dir);
				return (0);
			}
		}
		//
		// Set up the headings etc for the report
		//
		if (reportHdg.length() < 92) {
			repPadSize	= 92 - reportHdg.length();
			if (repPadSize > 2) {
				repPadSize	= repPadSize / 2;
			}
		}
		repPad = GenUtil.pad(repPad, repPadSize, " ");
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(appl_date);
		temp	= ("REPORT NAME:CHEXLOADMICR80.RPT" + repPad + reportHdg + repPad + "PAGE:");
		eRep.setHeadings(temp);
		temp	= ("                                                     " +
					"WEBFICHE INCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp	= ("                                         " +
					"FRB MICR CHECK DATA LOAD REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("ACCOUNT NUMBER     CHECK ACCT NUM  CHECK NUM          CH" +
					"ECK AMOUNT  PROC DATE   UNIQUE ISN     ABA          REAS" +
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
				PrintLog("Terminating the delete process of incl_chex_log_" +
						 appl_date.substring(0, 6));
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
		PrintLog("Will continue with Cycle ID: " + cycleId);
		int start		= 0;
		String inRec	= "";
		String one_rec	= "";
		String one_25rec	= "";
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		//threeMonths		= ValiDate.subtractDays(appl_date, 124);
		sevenMonths		= ValiDate.subtractDays(appl_date, 182);
		// PrintLog("Rec Type > "+one_rec.substring(0,4));
		try {
			highCheckNum.clear();
			//highCheckNum		= chUtil.HighCheckNum(dbConn, threeMonths.substring(0, 6));
			highCheckNum		= chUtil.HighCheckNum(dbConn, sevenMonths.substring(0, 6));
		} catch (Throwable t) {
			PrintLog("Error Getting High Check Numbers ->" + t.toString());
			t.printStackTrace();
		}
		try {
			monthAverage.clear();
			monthAverage		= acUtil.GetMonthlyCount(dbConn);
		} catch (Throwable t) {
			PrintLog("Error Getting Monthly Average Check Numbers ->" + t.toString());
			t.printStackTrace();
		}
		try {
			PrintLog("Input File " + outDir + file_to_load);
			BufferedReader inRead	= new BufferedReader(new FileReader(outDir + file_to_load));
			start	= 0;
			inRec	= "";
			one_rec	= "";
			while ((inRec = inRead.readLine()) != null) {
				// PrintLog("inRec 1  "+inRec.length());
				while (inRec.length() > 0) {
					// PrintLog("Record Len: "+start+" "+inRec.length());
					one_rec	= inRec;
					// PrintLog("One Rec: "+one_rec);
					if (one_rec.substring(0, 2).equals("II")) {
						PrintLog("Rec Type: II");
						//
					} else if (one_rec.substring(0, 4).equals("0103")) {
						PrintLog("Rec Type: 0103");
						chexRecType_0101(dbConn, one_rec);
					} else if (one_rec.substring(0, 4).equals("1001")) {
						PrintLog("Rec Type: 1001");
						chexRecType_1001(dbConn, one_rec);
					} else if (one_rec.substring(0, 4).equals("2001")) {
						PrintLog("Rec Type: 2001");
						chexRecType_2001(dbConn, one_rec);
					} else if (one_rec.substring(0, 2).equals("25")) {
						// In case rec 26 is missing then
						if (rec25Flag == true) {
							chexRecType_25(dbConn, eRep, one_25rec, user_name);
							one_25rec	= "";
							rec25Flag	= false;
						}
						//if (recType.equals("")) {
							PrintLog(recType);
							recType	= "Rec Type: 25";
							//recType	= recType + ", 25";
						//} else {
						//	PrintLog(recType);
						//}
						imageRec_seq	= 1;
						one_25rec		= one_rec;
						rec25Flag		= true;
						//chexRecType_25(dbConn, eRep, one_rec, user_name);
					} else if (one_rec.substring(0, 2).equals("26")) {
						recType	= recType + ", 26";
						// System.out.print(", 26");
						chexRecType_26(one_rec, eRep);
						if (rec25Flag == true) {
							chexRecType_25(dbConn, eRep, one_25rec, user_name);
						}
						rec25Flag	= false;
						one_25rec	= "";
					} else if (one_rec.substring(0, 4).equals("2801")) {
						recType	= recType + ", 28";
						// System.out.print(", 28");
					} else if (one_rec.substring(0, 2).equals("28")) {
						recType	= recType + ", 28";
						// System.out.print(", 28");
					} else if (one_rec.substring(0, 2).equals("50")) {
						recType	= recType + ", 50";
						// System.out.print(", 50");
						// skipLen = Integer.parseInt(one_rec.substring(24,31));
					} else if (one_rec.substring(0, 2).equals("52")) {
						recType	= recType + ", 52";
						// System.out.print(", 52");
						skipLen	= Integer.parseInt(one_rec.substring(130, 137));
						inRead.skip(skipLen);
					} else if (one_rec.substring(0, 2).equals("54")) {
						recType	= recType + ", 54";
						// System.out.print(", 54");
					} else if (one_rec.substring(0, 2).equals("70")) {
						// PrintLog("Rec Type: 70");
						recType	= recType + ", 70";
						chexRecType_70(dbConn, one_rec);
					} else if (one_rec.substring(0, 2).equals("90")) {
						// PrintLog("Rec Type: 90");
						chexRecType_90(dbConn, one_rec);
					} else if (one_rec.substring(0, 2).equals("99")) {
						// PrintLog("Rec Type: 99");
						chexRecType_99(dbConn, one_rec, file_to_load);
					} else {
						PrintLog("Rec Type: " + one_rec.substring(0, 2));
					}
					inRec	= "";
					start++;
				}
			}
			PrintLog("Total records processed: " + start);
			inRead.close();
		} finally {
			try {
				// bis.close();
			} catch (Throwable t) {
				PrintLog("Exception in runtime : " + t);

			}
		}
		temp	= invalid_accts + "";
		String invalid_accts_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= fraudAlert + "";
		String fraudAlert_s		= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= missing_chknum + "";
		String missing_chknum_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= pospay_missing + "";
		String pospay_missing_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= stale_check + "";
		String stale_check_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= cleared_check + "";
		String cleared_check_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= stopay + "";
		String stopay_s			= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= exceed_limit + "";
		String exceed_limit_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= authorize + "";
		String authorize_s		= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= completed + "";
		String completed_s		= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= tot_chex_25 + "";
		String tot_chex_25_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		eRep.WriteHeadings();
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                           " +
						"* * *  C h e c k  L o a d  S t a t i s t i c s  * * *");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       " +
						 "     Invalid Accounts:  " + invalid_accts_s);
		eRep.WriteDetail("                                                       " +
						 "          Fraud Alert:  " + fraudAlert_s);
		eRep.WriteDetail("                                                       " +
						 "Missing Check Numbers:  " + missing_chknum_s);
		eRep.WriteDetail("                                                       " +
						 "      Posipay Missing:  " + pospay_missing_s);
		eRep.WriteDetail("                                                       " +
						 "   Stale Dated Checks:  " + stale_check_s);
		eRep.WriteDetail("                                                       " +
						 "       Cleared Checks:  " + cleared_check_s);
		eRep.WriteDetail("                                                       " +
						 "              Stopped:  " + stopay_s);
		eRep.WriteDetail("                                                       " +
						 "Exceeded Limit Amount:  " + exceed_limit_s);
		eRep.WriteDetail("                                                       " +
						 "  Await Authorization:  " + authorize_s);
		eRep.WriteDetail("                                                       " +
						 "            Completed:  " + completed_s);
		eRep.WriteDetail("                                                       " +
						 "        TOTAL CHECKS :  " + tot_chex_25_s);
		eRep.WriteDetail("                                                       " +
						 "   FILE TOTAL AMOUNT :  " + file_tot_amt_99);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		//
		// 2011/06/10 Add a check for total amount from checks and the file. If
		// not equal thne create an error message
		//
		ret_val	= 1;
		/*
		 * if (tot_chex_25 != tot_amt_file) {
		 * eRep.WriteDetail("                    " +
		 * "MICR AND FRB TRAILER RECORD TOTALS DO NOT MATCH CONTACT SUPPORT IMMEDIATELY"
		 * ); errors.add("chex_upderror1", new ActionError
		 * ("error.totalmismatch")); ret_val = -1; }
		 */
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       " +
						 "     *** END OF REPORT ***");
		eRep.Close();
		return (ret_val);
	}
	//
	// public static void chexRecType_0101 (Connection dbConn,
	public void chexRecType_0101(Connection dbConn, String one_rec)
			throws SQLException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType0101: ";
		// PrintLog("in chexRecType_0101="+one_rec);
		rec_type_01			= one_rec.substring(0, 4);
		file_type_01		= one_rec.substring(4, 5);
		dest_aba_01			= one_rec.substring(5, 14);
		orig_aba_01			= one_rec.substring(14, 23);
		process_date_01		= one_rec.substring(23, 31);
		// PrintLog("in chexRecType_0101="+process_date_01);
		process_date_25		= process_date_01;
		dest_inst_name_01	= one_rec.substring(36, 54).trim();
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into " +
									"incl_load_stats(stat_id, stat_field1," +
									"stat_field2 ,stat_field3, stat_field4, " +
									"stat_field5," + "stat_field6) " + 
									"values (?, ?, ?, ?, ? , ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "File Header");
		pstmt.setString(3, dest_aba_01);
		pstmt.setString(4, orig_aba_01);
		pstmt.setString(5, process_date_01);
		pstmt.setString(6, dest_inst_name_01);
		pstmt.setString(7, " ");
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		moduleName	= calledFrom;
	}
	//
	public void chexRecType_1001(Connection dbConn, String one_rec)
			throws SQLException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType_1001: ";
		rec_type_10		= one_rec.substring(0, 4);
		dest_aba_10		= one_rec.substring(4, 13).trim();
		orig_aba_10		= one_rec.substring(13, 22).trim();
		cl_biz_date_10	= (one_rec.substring(26, 28) + "/" +
						   one_rec.substring(28, 30) + "/" + one_rec.substring(22, 26));
		cl_cre_date_10	= (one_rec.substring(34, 36) + "/" +
						   one_rec.substring(36, 38) + "/" + one_rec.substring(30, 34));
		cl_cre_time_10	= one_rec.substring(38, 40) + ":" + one_rec.substring(40, 42);
		// Information only fields we will ignore for now
		// cl_empty_ind_10		= one_rec.substring(43, 44).trim();
		// truncation_ind_10	= one_rec.substring(44, 45).trim();
		// cl_id_10 = one_rec.substring(45, 53).trim();
		// orig_contact_name_01	= one_rec.substring(53, 67).trim();
		// orig_contact_num_10	= one_rec.substring(67, 77).trim();
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into incl_load_stats(stat_id,"+
															"stat_field1, stat_field2," +
															"stat_field3, stat_field4, " +
															"stat_field5, stat_field6) " +
															"values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Cash Letter Header");
		pstmt.setString(3, dest_aba_10);
		pstmt.setString(4, orig_aba_10);
		pstmt.setString(5, cl_biz_date_10);
		pstmt.setString(6, cl_cre_date_10);
		pstmt.setString(7, cl_cre_time_10);
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		moduleName = calledFrom;
	}
	//
	public void chexRecType_2001(Connection dbConn, String one_rec)
			throws SQLException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType_2001: ";
		rec_type_20		= one_rec.substring(0, 4);
		dest_aba_20		= one_rec.substring(4, 13).trim();
		orig_aba_20		= one_rec.substring(13, 22).trim();
		biz_date_20		= one_rec.substring(22, 30).trim();
		cre_date_20		= one_rec.substring(30, 38).trim();
		bundle_id_20	= one_rec.substring(38, 48).trim();
		if (bundle_id_20.length() == 0) {
			bundle_id_20	= "0";
		}
		bundle_seq_20	= one_rec.substring(48, 52).trim();
		if (bundle_seq_20.length() == 0) {
			bundle_seq_20	= "0";
		}
		cycleNumber_20	= "Cycle Number: " + one_rec.substring(52, 54).trim();
		PreparedStatement pstmt = dbConn.prepareStatement("insert into incl_load_stats(" +
										"stat_id," +
										"stat_field1, " +
										"stat_field2, stat_field3, stat_field4, stat_field5, " +
										"stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		// PrintLog("in chexRecType_2001:  Rowe Id: "+row_id+
		// " bundle_id_20: "+bundle_id_20+
		// " bundle_seq_20: "+bundle_seq_20);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Bundle Header Record");
		pstmt.setString(3, bundle_id_20);
		pstmt.setString(4, bundle_seq_20);
		pstmt.setString(5, " ");
		pstmt.setString(6, " ");
		pstmt.setString(7, cycleNumber_20);
		// pstmt.setString(3, dest_aba_20);
		// pstmt.setString(4, orig_aba_20);
		// pstmt.setString(5, cl_biz_date_20);
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		moduleName = calledFrom;
	}
	//
	public void chexRecType_25(Connection dbConn, EcontReportUtil eRep,
			String one_rec, String user_name) throws SQLException {
		//
		calledFrom			= moduleName;
		moduleName			= "chexRecType_25: ";
		int insert_chex		= 1; // 1=insert 2=update
		int flag			= 0;
		PrintLog(one_rec);
		highChNum			= 0L;
		averageChecks		= 0;
		// boolean acct_exists	= false;
		String temp			= "";
		String bank_acct	= "";
		String zerosStr		= "00000000000000000000";
		String[] acctInfo		= {"","",""};
		String nSep				= "\\|";
		payorAcct				= " ";
		payorName				= " ";
		payorStatus				= " ";
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		// AccountSelector acctSelector = new AccountSelector();
		ChexDetail chexDetail	= new ChexDetail();
		StringBuffer sql		= new StringBuffer();
		Statement statement		= null;
		//InclAcctUtil acUtil		= new InclAcctUtil();
		//InclChexUtil chUtil		= new InclChexUtil();
		// int result			= 0;
		//
/*
		check_num_25	= one_rec.substring(2, 17).trim();
		if (check_num_25.equals("")) {
			check_num_25	= "0";
		} else {
			for (int n = 0; n < check_num_25.length(); n++) {
				if (gUtil.isNumeric(check_num_25.substring(n, n + 1)) == true) {
					temp	+= check_num_25.substring(n, n + 1);
				}
			}
			if (temp.equals("")) {
				check_num_25	= "0";
			} else {
				check_num_25	= temp;
			}
		}
*/
		//int    slashPos	= 0;
		check_num_25	= one_rec.substring(2, 17).trim();
		if (check_num_25.equals("")) {
			temp	= one_rec.substring(27,47).trim();
			account_num_25	= one_rec.substring(27,42).trim();
			check_num_25	= one_rec.substring(42,47).trim();
		} else {
			account_num_25	= one_rec.substring(27,47).trim();
		}
		check_num_25		= check_num_25.replaceAll(" ", "");
		check_num_25		= check_num_25.replaceAll("/", "");
		check_num_25		= check_num_25.replaceAll("-", "");
		account_num_25		= account_num_25.replaceAll(" ", "");
		account_num_25		= account_num_25.replaceAll("/", "");
		account_num_25		= account_num_25.replaceAll("-", "");
		temp				= "";
		checkNumLong		= Long.parseLong(check_num_25);
/*
		temp				= "";
		account_num_25		= "";
		ext_proc_control_25	= one_rec.substring(17, 18);
		routing_transit_25	= one_rec.substring(18, 27).trim();
		temp				= one_rec.substring(27, 47).trim();
		if ((temp.length() == 0) || (temp.equals("/"))) {
			account_num_25	= "0000000000000000000";
		} else {
			for (int indx = 0; indx < temp.length(); indx++) {
				if (gUtil.isNumeric(temp.substring(indx, indx + 1)) == true) {
					account_num_25	+= temp.substring(indx, indx + 1);
				}
			}
			if (account_num_25.equals("")) {
				account_num_25	= "0000000000000000000";
			}
		}
*/
		// PrintLog("in chexRecType_25 Account Num ="+account_num_25);
		ext_proc_control_25	= one_rec.substring(17, 18);
		routing_transit_25	= one_rec.substring(18, 27).trim();
		if (account_num_25.length() < 17) {
			account_num_25	= zerosStr.substring(0,17 - account_num_25.length()) + account_num_25;
		}
		if (account_num_25.length() > 17) {
			account_num_25	= account_num_25.substring(0, 17);
		}
		account_num_25	= Long.parseLong(account_num_25)+"";
		check_amount_25	= (Double.parseDouble(one_rec.substring(47, 57)) / 100)	+ "";
		unique_isn_25	= one_rec.substring(57, 72).trim();
		// PrintLog("in chexRecType_25 UniqueISN="+unique_isn_25);
		micr_valid_25	= one_rec.substring(74, 75).trim();
		misc_codes_25	= one_rec.substring(72, 78).trim();
		reason_codes	= "";
		check_status	= "";
		check_amount_od	= "0.0";
		last_modified	= "NULL";
		mod_user		= user_name.trim();
		mod_func		= "Load File";
		tot_amt_25		= tot_amt_25 + (Double.parseDouble(check_amount_25));
		bundleAmt		= bundleAmt + Double.parseDouble(check_amount_25);
		tot_chex_25++;
		bundleCount++;
		// Used for the Report if there is an error
		chexDetail.setChex_check_amount(check_amount_25);
		chexDetail.setChex_proc_date(process_date_25);
		// PrintLog(" Process_date_25 >"+process_date_25);
		//
		if (check_num_25.equals("0")) {
			reason_codes	= "C";
			check_status	= "R";
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
			PrintLog("Bank Acct: "+bank_acct);
			if (bank_acct.equals("")) {
				bank_acct		= account_num_25;
				// PrintLog("Back from CheckAccountExists"+bank_acct);
				reason_codes	+= "A";
				check_status	= "E";
				// PrintLog("Acct_num"+chex_account_num);
			} else {	// Found Account
				//bank_acct	= bank_acct.substring(0,bank_acct.indexOf("|"));
				//PrintLog("Bank Acct: "+bank_acct);
				acctInfo	= bank_acct.split(nSep);
				payorAcct	= acctInfo[0];
				payorName	= acctInfo[1];
				if (acctInfo.length > 2) {
					payorStatus	= acctInfo[2];
					if (payorStatus.equals("BLOCKED")) {
						reason_codes	+= "I";
						check_status	= "R";
					}
				}
				bank_acct	= payorAcct;
				PrintLog("Back from CheckAccountExists: "+bank_acct);
				// Here check for Fraudulant checks based on the Monthly Average Checks
				// the client issues.
				highChNum	= 0L;
				//Set<String> keys = chexCount.keySet();
				//for (String key: keys) {
				//    System.out.println("Check Count for "+key+" is: "+chexCount.get(key));
				//}
				if (highCheckNum.containsKey(bank_acct)) {
					highChNum	= highCheckNum.get(bank_acct);
					PrintLog ("Bank Acct: " + bank_acct + "\tHighest Check Number " + highCheckNum.get(bank_acct));
				}
				averageChecks	= 0;
				if (monthAverage.containsKey(bank_acct)) {
					averageChecks	= monthAverage.get(bank_acct);
				}
				FraudLimit	= 0L;
				if (averageChecks > 0) {
					if (highCheckNum.containsKey(bank_acct)) {
						highChNum	= highCheckNum.get(bank_acct);
						FraudLimit	= highChNum + averageChecks;
					}
					PrintLog ("Bank Acct: "+bank_acct+" Check Number: " + checkNumLong +
							  " averageChecks "+ averageChecks + 
							  " Fraud Limit: " + FraudLimit); 
					if (checkNumLong > FraudLimit) {
						reason_codes	= " ";
						check_status	= "F";
						fraudAlert++;
					}
				}
			}
			PrintLog("Check for Duplicate " + bank_acct + ", "+ checkNumLong+"");
			if (chUtil.ChexExists(dbConn, "incl_chex", bank_acct, checkNumLong+"", chexDetail) == true) {
				PrintLog("Duplicate Check found " + bank_acct + ", "+ check_num_25);
				//chexDetail.setErrorVec("Inclearing Check", "error.field.duplicate");
				//if (!check_status.equals("Z")) {
					check_status	= "E";
				//}
				reason_codes		+= "K";
			}
			//PrintLog("Bank Acct: "+bank_acct);
			//PrintLog("Reason Codes: '"+reason_codes+"' Check Status: '"+check_status+"'");
		} catch (Throwable t) {
			PrintLog("Error Validating Account ->" + t.toString());
		}
		clearedCheck	= false;
		sevenMonths		= ValiDate.subtractDays(process_date_25, 214);
		PrintLog("Seven Months back->" + sevenMonths);
		try {
			clearedCheck	= chUtil.ChexExistsInHistory(dbConn, bank_acct, check_num_25,
												chexDetail, sevenMonths);
			if (clearedCheck){
				reason_codes	+= "H";
				check_status	= "R";
			}
		}  catch (Throwable t) {
			PrintLog("Error CheckExists in History ->" + t.toString());
		}
		checkImage	= ("Check_" +
						zerosStr.substring(0, 17 - account_num_25.length()) +
						account_num_25 + "_" +
						zerosStr.substring(0, 15 - check_num_25.length()) +
						check_num_25 + "_" +
						zerosStr.substring(0, 15 - unique_isn_25.length()) + unique_isn_25);
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
			sql.append("'" + defCurr + "', ");
			sql.append("'" + check_amount_25 + "', ");
			sql.append("' ', "); // proc_control
			sql.append("'" + ext_proc_control_25 + "', "); // ext proc control
			sql.append("'" + micr_valid_25 + "', "); // image locator
			sql.append("'" + unique_isn_25 + "', "); // unique ISN
			sql.append("' ', "); // addenda rec flag
			sql.append("' ', "); // orig routing trnasit
			sql.append("'" + misc_codes_25 + "', "); // chex_isn
			sql.append("' ', "); // BudgetId
			sql.append("' ', "); // chex_return_date
			sql.append("' ', "); // chex_return_reason
			sql.append("' ', "); // chex_return_status
			sql.append("'" + bofd + "', "); // chex_BOFD_aba
			sql.append("'" + bofdDate + "', "); // chex_BOFD_date
			sql.append("' ', "); // extra_1
			sql.append("' ', "); // extra_2
			sql.append("'" + issue_date + "', ");
			sql.append("'" + payee + "', ");
			sql.append("'" + payee_addr1 + "', ");
			sql.append("'" + payee_addr2 + "', ");
			sql.append("'" + payee_addr3 + "', ");
			sql.append("'" + comments + "', ");
			sql.append("'" + reason_codes + "', ");
			sql.append("'" + check_status + "', ");
			sql.append("'" + check_amount_od + "', ");
			sql.append("'" + checkImage + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + mod_user + "', ");
			sql.append("'" + mod_func + "')");
			PrintLog("SQL: "+sql);
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
			sql.append("'" + defCurr + "', ");
			sql.append("'" + check_amount_25 + "', ");
			sql.append("' ', "); // proc_control
			sql.append("'" + ext_proc_control_25 + "', "); // ext proc control
			sql.append("'" + micr_valid_25 + "', "); // image locator
			sql.append("'" + unique_isn_25 + "', "); // unique ISN
			sql.append("' ', "); // addenda rec flag
			sql.append("' ', "); // orig routing trnasit
			sql.append("'" + misc_codes_25 + "', "); // chex_isn
			sql.append("' ', "); // BudgetId
			sql.append("' ', "); // chex_return_date
			sql.append("' ', "); // chex_return_reason
			sql.append("' ', "); // chex_return_status
			sql.append("'" + bofd + "', "); // chex_BOFD_aba
			sql.append("'" + bofdDate + "', "); // chex_BOFD_date
			sql.append("' ', "); // extra_1
			sql.append("' ', "); // extra_2
			sql.append("'" + issue_date + "', ");
			sql.append("'" + payee + "', ");
			sql.append("'" + payee_addr1 + "', ");
			sql.append("'" + payee_addr2 + "', ");
			sql.append("'" + payee_addr3 + "', ");
			sql.append("'" + comments + "', ");
			sql.append("'" + reason_codes + "', ");
			sql.append("'" + check_status + "', ");
			sql.append("'" + check_amount_od + "', ");
			sql.append("'" + checkImage + "', ");
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
			//PrintLog("Reason Codes: '"+reason_codes+"' Check Status: '"+check_status+"'");
			chexDetail.setDbUsed(dbUsed);
			chexDetail.setBankId(bankId);
			chexDetail.setAppl_date(appl_date);
			chexDetail.setChex_proc_date(process_date_25);
			chexDetail.setChex_orig_account_num(account_num_25);
			chexDetail.setChex_orig_check_num(check_num_25);
			chexDetail.setChex_account_num(bank_acct);
			chexDetail.setChex_check_num(check_num_25);
			chexDetail.setChex_routing_transit(routing_transit_25);
			chexDetail.setChex_check_currency(defCurr);
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
			chexDetail.setChex_BOFD_aba(bofd);
			chexDetail.setChex_BOFD_date(bofdDate);
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
			chexDetail.setChex_image(checkImage);
			chexDetail.setChex_last_modified(last_modified);
			chexDetail.setChex_mod_user(user_name);
			chexDetail.setChex_mod_func("Load File");
			insert_chex = 1; // do not change
			try {
				flag = chUtil.InsertUpdateChex(dbConn, chexDetail, insert_chex);
				if (flag == 0) {
					;
				}
			} catch (Throwable t) {
				PrintLog("Error inserting Chex ->>>" + t.toString());
				t.printStackTrace();
			}
			reason_codes = chexDetail.getChex_reason_codes();
			check_status = chexDetail.getChex_check_status();
			//PrintLog("Reason Codes: "+reason_codes+" Check Status: "+check_status);
			//bofd		= " ";
			//bofdDate	= " ";
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
		} else if (check_status.equals("F")) {
			check_status	= "FRAUD ALERT";
		} else if (check_status.equals("R")) {
			check_status	= "REJECTED";
		} else if (check_status.equals("S")) {
			check_status	= "STOPPED";
		} else if (check_status.equals("Z")) {
			check_status	= "COMPLETE";
		}
		String temp_amt		= chexDetail.getChex_check_amount_disp();
		String temp_date	= chexDetail.getChex_proc_date_disp();
		temp = ((bank_acct + "                   ").substring(0, 19) +
				(account_num_25 + "                 ").substring(0, 16) +
				(check_num_25 + "               ").substring(0, 15) +
				("                ").substring(0, 16 - temp_amt.length()) +
				temp_amt + "  " + temp_date + "  " +
				unique_isn_25.substring(0) + "     " + routing_transit_25 +
				"    " + (reason_codes + "               ").substring(0, 15) + check_status);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(temp);
		//if (check_status.equals("COMPLETE") || check_status.equals("AUTHORIZE")) {
		//	temp = ("                                                        " +
		//			(iss_date + "  ")
		//			+ (sw_ref + "                           ").substring(0, 27) + 
		//			(payee + "                                   ").substring(0, 35));
		//	eRep.WriteDetail(temp);
		//}
		temp		= "";
		temp		= ("BOFD: " + bofd + "    BOFD Date: " + bofdDate);
		if (check_status.equals("COMPLETE") || check_status.equals("AUTHORIZE")) {
			temp = temp + ("                  " + (iss_date + "  ") +
				   (sw_ref + "                           ").substring(0, 27) + 
				   (payee + "                                   ").substring(0, 35));
			//eRep.WriteDetail(temp);
		}
		eRep.WriteDetail(temp);
		bofd		= " ";
		bofdDate	= " ";
		check_status= "";
		payee		= " ";
		sw_ref		= " ";
		iss_date	= "0";
		moduleName	= calledFrom;
	}
	//
	// Check Detail Addendum C Record type 26
	//
	public void chexRecType_26(String one_rec, EcontReportUtil eRep)
			throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_26: ";
		bofd		= (one_rec.substring(3, 12));
		bofdDate	= (one_rec.substring(12, 20));

		//temp		= "";
		//temp		= ("BOFD: " + one_rec.substring(3, 12) + "    BOFD Date: " + 
		//				one_rec.substring(12, 20));
		//eRep.WriteDetail(temp);
		moduleName	= calledFrom;
	}
	//
	// Check Detail Addendum C Record type 28
	//
	public void chexRecType_2801(Connection dbConn, String one_rec)
			throws SQLException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_2801: ";
		rec_type_28	= one_rec.substring(0, 4);
		dest_aba_28	= one_rec.substring(4, 13).trim();
		orig_aba_28	= one_rec.substring(13, 21).trim();
		biz_date_28	= one_rec.substring(21, 36).trim();
		PreparedStatement pstmt = dbConn.prepareStatement("insert into incl_load_stats" + 
										"(stat_id, stat_field1, stat_field2 ," + 
										"stat_field3, stat_field4, stat_field5," +
										"stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Check Detail Addendum C Record");
		pstmt.setString(3, dest_aba_28);
		pstmt.setString(4, orig_aba_28);
		pstmt.setString(5, biz_date_28);
		pstmt.setString(6, " ");
		pstmt.setString(7, " ");
		// pstmt.setString(3, dest_aba_20);
		// pstmt.setString(4, orig_aba_20);
		// pstmt.setString(5, cl_biz_date_20);
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		moduleName = calledFrom;
	}
	//
	// Bundle Control record type 50
	//
	public void chexRecType_50() throws SQLException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_50: ";
		//
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 52
	//
	public void chexRecType_52(String one_rec) throws Exception {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_52: ";
		/*
		try {
			String jpegFile_o	= "";
			imageRec			= one_rec.substring(startImage, one_rec.length());
			// PrintLog("FOUND image @ "+startImage+" & Image size of"+imageRec.length());
			// PrintLog("Image size "+imageRec.length());
			if (imageRec_seq == 1) {
				jpegFile_o	= jpegFile + "_front.tiff";
			} else {
				jpegFile_o	= jpegFile + "_back.tiff";
			}
			BufferedWriter outWrite1	= new BufferedWriter(new FileWriter(outDir + 
																jpegFile_o));
			outWrite1.write(imageRec);
			outWrite1.close();
			//jpegCreated	= doTiff2JPEG(jpegFile_o);
			// outWrite.write(one_rec, 0, startImage);
			// outWrite.newLine();
			// outWrite.write(imageRec,0, imageRec.length());
			// outWrite.newLine();
		} catch (IOException e) {
			PrintLog("" + e);
		}
		*/
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 54
	//
	public void chexRecType_54() throws SQLException {
		calledFrom = moduleName;
		moduleName = "chexRecType_54: ";
		//
		moduleName = calledFrom;
	}
	//
	// Bundle Control record type 70
	//
	public void chexRecType_70(Connection dbConn, String one_rec)
			throws SQLException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_70: ";
		String temp			= "";
		DecimalFormat df	= new DecimalFormat("#,###,###,##0.00");
		rec_type_70			= one_rec.substring(0, 2);
		bundle_count_70		= Integer.parseInt(one_rec.substring(2, 6)) + "";
		temp				= one_rec.substring(6, 18).trim();
		bundle_tot_amt_70	= df.format(Double.parseDouble(temp) / 100); // + "";
		temp				= "0" + one_rec.substring(18, 30).trim();
		micr_val_totamt_70	= df.format(Double.parseDouble(temp) / 100); // + "";
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into incl_load_stats(" +
									"stat_id, stat_field1," +
									"stat_field2 ,stat_field3, stat_field4, stat_field5," +
									"stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Data Load Statistics");
		pstmt.setString(3, " ");
		pstmt.setString(4, " ");
		pstmt.setString(5, " ");
		pstmt.setString(6, bundleCount + "");
		temp	= bundleAmt + "";
		temp	= df.format(Double.parseDouble(temp));
		// pstmt.setString(7, tot_amt_25+"");
		pstmt.setString(7, temp);
		// pstmt.setString(3, dest_aba_20);
		// pstmt.setString(4, orig_aba_20);
		// pstmt.setString(5, cl_biz_date_20);
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		bundleCount	= 0;
		bundleAmt	= 0.00;
		//
		pstmt = dbConn.prepareStatement("insert into incl_load_stats(stat_id, " +
										"stat_field1, stat_field2 ,stat_field3, " +
										"stat_field4, stat_field5, stat_field6) " +
										"values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Bundle Control Record");
		pstmt.setString(3, " ");
		pstmt.setString(4, " ");
		pstmt.setString(5, micr_val_totamt_70);
		pstmt.setString(6, bundle_count_70);
		pstmt.setString(7, bundle_tot_amt_70);
		// pstmt.setString(3, dest_aba_20);
		// pstmt.setString(4, orig_aba_20);
		// pstmt.setString(5, cl_biz_date_20);
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		moduleName	= calledFrom;
	}
	//
	// Record type 90 - Cash Letter Control Record
	//
	public void chexRecType_90(Connection dbConn,
			String one_rec) throws SQLException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_90: ";
		DecimalFormat df	= new DecimalFormat("#,###,###,##0.00");
		// StringBuffer sql	= new StringBuffer();
		// Statement statement	= null;
		// int result		= 0;
		String temp			= "";
		// PrintLog(": one_rec "+one_rec);
		// Here various rows are inserted into the incl_stats table used for
		// displaying the Load Statistics.
		// Insert a row for orig_inst_name
		// These records have a rowe id of 3, 8, 13 ... etc,
		rec_type_90		= one_rec.substring(0, 2);
		bundle_count_90	= Integer.parseInt(one_rec.substring(2, 8)) + "";
		cl_count_90		= Integer.parseInt(one_rec.substring(8, 16)) + "";
		temp			= one_rec.substring(16, 30).trim();
		cl_tot_amt_90	= df.format(Double.parseDouble(temp) / 100) + "";
		//
		PreparedStatement pstmt = dbConn.prepareStatement("insert into incl_load_stats(stat_id, " + 
									"stat_field1, stat_field2, stat_field3, " +
									"stat_field4, stat_field5, stat_field6) " + 
									"values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Cash Letter Control");
		pstmt.setString(3, bundle_count_90);
		pstmt.setString(4, cl_count_90);
		pstmt.setString(5, " ");
		pstmt.setString(6, " ");
		pstmt.setString(7, cl_tot_amt_90);
		// pstmt.setString(3, dest_aba_20);
		// pstmt.setString(4, orig_aba_20);
		// pstmt.setString(5, cl_biz_date_20);
		dbConn.setAutoCommit(false);
		pstmt.execute();
		dbConn.commit();
		dbConn.setAutoCommit(true);
		pstmt.close();
		moduleName = calledFrom;
	}
	//
	// Check record type 9 - File Control Record
	//
	public void chexRecType_99(Connection dbConn,
	// ActionErrors errors,
			String one_rec, String file_to_load)
			throws SQLException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType99: ";
		DecimalFormat df	= new DecimalFormat("#,###,###,##0.00");
		invalid_accts		= 0;
		fraudAlert			= 0;
		missing_chknum		= 0;
		pospay_missing		= 0;
		stopay				= 0;
		stale_check			= 0;
		cleared_check		= 0;
		exceed_limit		= 0;
		authorize			= 0;
		completed			= 0;
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		// int result		= 0;
		int insOrUpd		= 2;
		String temp			= "";
		String check_status	= "";
		String check_reason_code	= "";
		String check_count	= "";
		cl_count_99			= Integer.parseInt(one_rec.substring(2, 8)) + "";
		tot_recs_99			= Integer.parseInt(one_rec.substring(8, 16)) + "";
		tot_items_99		= Integer.parseInt(one_rec.substring(16, 24)) + "";
		temp				= one_rec.substring(24, 40).trim();
		tot_amt_file		= Double.parseDouble(temp) / 100;
		file_tot_amt_99		= df.format(Double.parseDouble(temp) / 100); // + "";
		if (dbConn == null)
			PrintLog("----------- NULL DBCONN ------------");
		//
		// Here write the Status counts and then the final stat record.
		// Insert a row for stats
		//
		PreparedStatement pstmt = dbConn.prepareStatement("insert into incl_load_stats(" +
														"stat_id, stat_field1, " +
														"stat_field2 ,stat_field3, " +
														"stat_field4, stat_field5, " +
														"stat_field6) values " + 
														"(?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "File Control");
		pstmt.setString(3, cl_count_99);
		pstmt.setString(4, " ");
		pstmt.setString(5, tot_recs_99);
		pstmt.setString(6, tot_items_99);
		pstmt.setString(7, file_tot_amt_99);
		// pstmt.setString(3, dest_aba_20);
		// pstmt.setString(4, orig_aba_20);
		// pstmt.setString(5, cl_biz_date_20);
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
			} if (check_status.equals("E") && check_reason_code.equals("AH")) {
				invalid_accts += Integer.parseInt(check_count);
			} else if (check_status.equals("E")	&& check_reason_code.equals("CA")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("E")	&& check_reason_code.equals("CAH")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("F") && check_reason_code.equals(" ")) {
				fraudAlert	   += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("MA")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("C")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("M")) {
				missing_chknum += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals(" D")) {
				stale_check += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("H")) {
				cleared_check += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals(" H")) {
				cleared_check += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals(" K")) {
				cleared_check += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("L")) {
				exceed_limit += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("P")) {
				pospay_missing += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("Q")) {
				pospay_missing += Integer.parseInt(check_count);
			} else if (check_status.equals("S") && check_reason_code.equals("S")) {
				stopay += Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("T")) {
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
		row_id	= cycleId;
		sql.setLength(0);
		sql.append("INSERT INTO incl_load_stats VALUES (");
		sql.append("'" + row_id + "', ");
		sql.append("'" + invalid_accts + "', ");
		sql.append("'" + fraudAlert + "', ");
		sql.append("'" + missing_chknum + "', ");
		sql.append("'" + pospay_missing + "', ");
		sql.append("'" + stale_check + "', ");
		sql.append("'" + cleared_check + "', ");
		sql.append("'" + stopay + "', ");
		sql.append("'" + exceed_limit + "', ");
		sql.append("'" + authorize + "', ");
		sql.append("'" + completed + "', ");
		sql.append("'SUMMARY', ");
		sql.append("' ', ");
		sql.append("' ', ");
		sql.append("' ', ");
		sql.append("' ')");
		// PrintLog("SQL ->"+sql);
		try {
			statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			dbConn.setAutoCommit(false);
			// result	= statement.executeUpdate(sql.toString());
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
		//if (!actionType.equals("ReLoadFiles")) {
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
				ctlDetail.setFile_loaded(fileToLoad);
				filesLoaded	= ctlDetail.getFile_loaded_1() + "@" + 
								ctlDetail.getFile_loaded_2() + "@" + 
								ctlDetail.getFile_loaded_3() + "@" + 
								ctlDetail.getFile_loaded_4() + "@" + 
								ctlDetail.getFile_loaded_5();
				filesArray	= filesLoaded.split("@");
				PrintLog("Array Size "+filesArray.length);
				int filesLoaded	= filesArray.length;
				if (filesLoaded == 0) {
					ctlDetail.setFile_loaded_1(fileToLoad);
				} else if (filesLoaded == 1) {
					ctlDetail.setFile_loaded_2(fileToLoad);
				} else if (filesLoaded == 2) {
					ctlDetail.setFile_loaded_3(fileToLoad);
				} else if (filesLoaded == 3) {
					ctlDetail.setFile_loaded_4(fileToLoad);
				} else {
					ctlDetail.setFile_loaded_5(fileToLoad);
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
				ctlDetail.setFile_loaded(file_to_load);
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
		//}
	} // END public void chexRecType_99
}
