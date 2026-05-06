package com.webfiche.checkpoint.deposits.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.text.*;
import java.util.*;

//import org.apache.struts.action.*;
//import com.webfiche.checkpoint.inclear.beans.*;
//import com.webfiche.checkpoint.sysadmin.beans.*;
//import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.deposits.beans.DepsDetail;
import com.webfiche.checkpoint.inclear.service.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;
//
/*
import com.sun.media.jai.codec.*;
import javax.media.jai.*;
//import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;

*/
//
@Service
public final class UploadNYDeps {
	// Process Statistics Counters
	String appSchema	= "";
	String ctxPath		= "";
	String dbUsed		= "";
	String dbTable		= "";
	String dbLogTable	= "";
	String dbTblCreSql	= "";
	String dbTblInitSql	= "";
	String applDate		= "";
	String ourAba		= "";
	String payerAba		= "";
	String payerName	= "";
	String payerCountry	= "";
	String temp			= "";
	String outDir		= "";
	String prodId		= " ";
	String prodIdA		= "A";
	String prodIdD		= "D";
	//String prodIdE		= "E";
	String myActionFlag	= "";
	int invalid_accts	= 0;
	int missing_chknum	= 0;
	int pospay_missing	= 0;
	int stale_check		= 0;
	int cleared_check	= 0;
	int stopay			= 0;
	int exceed_limit	= 0;
	int authorize		= 0;
	int postingReady	= 0;
	int completed		= 0;
	int ret_val			= 0;			// Success
	double tot_amt_25	= 0.0;
	double tot_amt_file	= 0.0;
	int tot_chex_25		= 0;
	int cycleId			= 0;
	int row_id			= 0;
	int startImage		= 0;
	int startJpeg		= 0;
	long	skipLen		= 0;
	String checksProcTemp	= "";
	String recType		= "";
	String reportHdg	= null;
	String repPad		= "";
	String iclPrefix	= "";
	String spaces55		= "                                                       ";
	int repPadSize		= 0;
	//
	// File Header Record 01 Fields
	//
	String  rec_type_01;				// rec_type_code_101;
	String  std_level_01;			// prio_code_101;
	String  file_type_01;			// P=prod T=test
	String  dest_aba_01;				// receiving_aba_101;
	String  orig_aba_01;				// origination_aba_101;
	String  process_date_01;			// process_date_101;
	String  dest_inst_name_01;		// dest_inst_name_101;
	String  orig_inst_name_01;		//
	String  file_ID_modifier_01;		// 0 thru 9
	//
	// Cash Letter (cl) Header Record 1001 Fields
	//
	String  rec_type_10;				// rec_type_code_5290;
	String  dest_aba_10;				// orig_inst_name_5290;
	String  orig_aba_10;				// origination_aba_5290;
	String  cl_biz_date_10;			// process_date_5290;
	String  cl_cre_date_10;			//
	String  cl_cre_time_10;			//
	// String cl_empty_ind_10; //check_srv_trncd_5290;
	// String truncation_ind_10; //batch_seq_5290;
	// String cl_id_10; //batch_seq_5290;
	// String orig_contact_name_10; //batch_seq_5290;
	// String orig_contact_num_10; //batch_seq_5290;
	//
	// Bundle Header Record 20 fields
	//
	String  rec_type_20;				   // record_type_693;
	String  dest_aba_20;				   // bundle_num_693;
	String  orig_aba_20;				   // item_count_693;
	String  biz_date_20;				   // bundle_amount_693;
	String  cre_date_20;
	String  bundle_id_20;
	String  bundle_seq_20;
	String  cycleNumber_20;
	//
	// Check Detail Record 25 Fields
	//
	String  rec_type_25;
	String  check_num_25;
	String  ext_proc_control_25;
	String  routing_transit_25;
	// String check_digit_25;
	String  account_num_25;
	String  check_amount_25;
	String  unique_isn_25;
	String  ret_accept_ind_25;
	// Next field stored in chex_ext_proc_control
	String  micr_valid_25;
	// following made up of
	// 1 Document type Indicator
	// 2 Return Acceptance Indicator
	// 3 MICR Valid code
	// 4 Bank of First Deposit (BFD) indicator
	// 5 Check Detail Record Addendum Count
	// 6 On Us Format
	// Keep in Image locator column
	//
	String  misc_codes_25;
	String  process_date_25;
	//
	// Check Detail Record 26 Fields
	//
	String	bofd			= " ";
	String	bofdDate		= " ";
	String	payeeAcct		= " ";
	String	payeeName		= " ";
	String	payeeStatus		= " ";
	//
	// Check Detail Addendum C Record type 28
	//
	String  rec_type_28;
	String  dest_aba_28;
	String  orig_aba_28;
	String  biz_date_28;
	//
	// Image Records
	//
	int	 imageRec_seq;
	String  imageRec;
	String  jpegFile;
	boolean jpegCreated;
	boolean acctExists;
	//
	// Bundle Control Record 70 Fields
	//
	String  rec_type_70;				// record_type_8290;
	String  bundle_count_70;			// srv_class_code_8290;
	String  bundle_tot_amt_70;		// det_item_count_8290;
	String  micr_val_totamt_70;		// tot_amount_8290
	// Cash Letter Control Record 90 Fields
	String  rec_type_90;				// record_type_8290;
	String  bundle_count_90;			// srv_class_code_8290;
	String  cl_count_90;				// srv_class_code_8290;
	String  cl_tot_amt_90;			// det_item_count_8290;
	String  dest_name_90;
	String  orig_name_90;
	String  settle_date_90;
	// Record 99 Fields
	String  rec_type_99;				// record_type_9;
	String  cl_count_99;				// batch_count_9;
	String  tot_recs_99;				// detail_count_9;
	String  tot_items_99;
	String  file_tot_amt_99;			// total_amount_9;
	// Incl Checx Table fields
	String  issue_date	= "0";
	String  iss_date	= " ";
	String  sw_ref		= " ";
	String  payee		= " ";
	String  payee_addr1	= " ";
	String  payee_addr2	= " ";
	String  payee_addr3	= " ";
	String  comments	= " ";
	String  reason_codes;
	String  check_status;
	String  check_amount_od;
	String  last_modified;
	String  mod_user;
	String  mod_func;
	String	acctAndName	= "";
	//
	String  defCurr		= "";
	String  bankId		= "";
	String  checkImage	= "";
	String	userId		= "";
	String	moduleName	= "";
	String	calledFrom	= "";
	//
	InclAcctUtil acUtil		= new InclAcctUtil();
	DepsChexUtil chUtil		= new DepsChexUtil();
	DbCheckTable dbCheck	= new DbCheckTable();
	DepsPayerUtil dpUtil	= new DepsPayerUtil();
	Connection dbConn;
	GenUtil		gUtil	= new GenUtil();

	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> UploadNYDeps." + moduleName + logMsg);
	}
	//
	public int UpLoadDeps(Connection argDbConn, String dbUsed_1, String iclSource,
							String applDate_1, String dataDir, String reportDir,
							String file2Load, int cycleNum, String actionFlag,
							String argUserName) throws SQLException, Exception, IOException {
		EcontServletContextListener escl	= new EcontServletContextListener();
		moduleName			= "UpLoadDeps: ";
		PrintLog("File to Load: "+file2Load);
		dbTable				= "deps_chex";
		dbLogTable			= "deps_chexlog";
		myActionFlag		= actionFlag;
		dbConn				= argDbConn;
		userId				= argUserName;
		String temp			= "";
		//appSchema			= escl.getAppSchema();
		appSchema			= escl.getDbUser().toUpperCase();
		ctxPath				= escl.getCtx();
		ourAba				= escl.getOurAba();
		reportHdg			= escl.getReportHdg();
		defCurr				= escl.getDefCurr();
		bankId				= escl.getBankId();
		tot_amt_25			= 0.0;
		tot_chex_25			= 0;
		cycleId				= cycleNum * 1000;
		dbUsed				= dbUsed_1;
		//String reportName	= iclSource.toLowerCase() + "-iclload_" + cycleNum + ".rpt";
		String reportName	= file2Load.substring(0, file2Load.lastIndexOf(".")).toLowerCase() + 
								"_iclload.rpt";
		applDate			= applDate_1;
		//dbTable			= "deposithist_"  + applDate.substring(0,6);
		//PrintLog("ICLSource: "+iclSource);
		//PrintLog("ICLPrefix: "+iclPrefix);
		iclPrefix		= iclSource.toUpperCase();
		if (actionFlag.equals("LoadHistory")) {
			dbTable			= "deps_chex_"  + applDate.substring(0,6);
			dbLogTable		= "deps_chexlog_"  + applDate.substring(0,6);
		}
		PrintLog("Appl Date: " + applDate + " DB Used: " + dbUsed + " Cycle Num: " + cycleNum);
		PrintLog("For: " + iclPrefix);
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
		PrintLog("output_path "+outDir);
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
			repPadSize		= 92 - reportHdg.length();
			if (repPadSize > 2) {
				repPadSize	= repPadSize / 2;
			}
		}
		repPad	= GenUtil.pad(repPad, repPadSize, " ");
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(applDate);
		temp	= ("REPORT NAME:" + reportName + repPad + reportHdg + repPad + "PAGE:");
		eRep.setHeadings(temp);
		temp	= ("                                                     " +
					"eController OUTCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp	= ("                                         " +
					"  DEPOSITS ICL DATA LOAD REPORT AS OF ");
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
		if (dbUsed.equals("ORACLE")) {
			dbTable		= dbTable.toUpperCase();
			dbLogTable	= dbLogTable.toUpperCase();
		}
		PrintLog("ActionFlag : " + actionFlag + " & DbTable : " + dbTable);
		if (actionFlag.equals("LoadHistory")) {
			String sqlRoot	= ctxPath + "WEB-INF/db/" + dbUsed.toLowerCase();
			dbTblCreSql		= sqlRoot + "/sql/checkpointout/deps_chex_hist_stock.sql";
			dbTblInitSql	= "";
			PrintLog("Will initialise table: " + dbTable);
			dbCheck.CheckIfTableExists(dbConn, appSchema, dbTable, dbTblCreSql, dbTblInitSql);
			dbTblCreSql		= sqlRoot + "/sql/checkpointout/deps_chexlog_stock.sql";
			dbTblInitSql	= "";
			PrintLog("Will initialise table: " + dbLogTable);
			dbCheck.CheckIfTableExists(dbConn, appSchema, dbLogTable, dbTblCreSql, dbTblInitSql);
		}
		if (actionFlag.equals("LoadFile")) {
			try {
				pstmt1.executeUpdate("truncate table " + dbTable);
			} catch (SQLException e) {
				PrintLog("SQLException : " + e);
				PrintLog("Terminating the truncate process of " + dbTable);
				e.printStackTrace();
				return (-1);
			}
			try {
				//pstmt1.executeUpdate("truncate table " + 
				//						iclPrefix.toLowerCase() + "_load_stats ");
				pstmt1.executeUpdate("truncate table " + "deps_load_stats ");
			} catch (SQLException e) {
				PrintLog("" + e);
				//PrintLog("Terminating the truncate process of " + 
				//						iclPrefix.toLowerCase() + "_load_stats ");
				PrintLog("Terminating the truncate process of " + "deps_load_stats ");
				e.printStackTrace();
				return (-1);
			}
		}
		if (actionFlag.equals("ReLoadFiles") && cycleNum == 1) {
			try {
				pstmt1.executeUpdate("truncate table " + dbTable +" ");
			} catch (SQLException e) {
				PrintLog("SQLException : " + e);
				PrintLog("Terminating the delete process of "+dbTable);
				return (-1);
			}
			try {
				//pstmt1.executeUpdate("truncate table " + 
				//						iclPrefix.toLowerCase() + "_load_stats ");
				pstmt1.executeUpdate("truncate table " + "deps_load_stats ");
			} catch (SQLException e) {
				PrintLog("" + e);
				//PrintLog("Terminating the delete process of " + 
				//						iclPrefix.toLowerCase() + "_load_stats ");
				PrintLog("Terminating the delete process of " + "deps_load_stats ");
				return (-1);
			}
			//
			// Delete the chex mods logged today
			//
			try {
				if (dbUsed.equals("ORACLE")) {
					pstmt1.executeUpdate("delete from  + " + dbTable + "log_" +
							applDate.substring(0, 6) + " where " +
							"SUBSTR(to_char(chlog_last_modified,'yyyymmdd hh24:mi:ss'),1,8)='" +
							sn_date + "'");
				} else {
					pstmt1.executeUpdate("delete from " + dbTable + "log_" +
							applDate.substring(0, 6) + " where " +
							"SUBSTR(chlog_last_modified,1,8)='" + sn_date + "'");
				}
			} catch (SQLException e) {
				PrintLog("" + e);
				PrintLog("Terminating the delete process of " + dbTable + "log_" +
						 applDate.substring(0, 6));
				return (-1);
			}
		}
		pstmt1.close();
		PrintLog("Will read" + "  Cycle ID: " + cycleId);
		int start		= 0;
		String inRec	= "";
		String one_rec	= "";
		//System.setProperty("com.sun.media.jai.disableMediaLib", "true");
		// PrintLog("Rec Type > "+one_rec.substring(0,4));
		try {
			PrintLog("Input File " + outDir + file2Load);
			BufferedReader inRead	= new BufferedReader(new FileReader(outDir + file2Load));
			start		= 0;
			inRec		= "";
			one_rec		= "";
			while ((inRec = inRead.readLine()) != null) {
				// PrintLog("inRec 1  "+inRec.length());
				while (inRec.length() > 0) {
					// PrintLog("Record Len: "+start+" "+inRec.length());
					one_rec	= inRec;
					//PrintLog("One Rec: "+one_rec);
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
						if (recType.equals("")) {
						} else {
							PrintLog(recType);
						}
						recType			= "Rec Type: 25";
						imageRec_seq	= 1;
						PrintLog("Rec Type  > "+one_rec);
						chexRecType_25(dbConn, eRep, one_rec, userId);
					} else if (one_rec.substring(0, 2).equals("26")) {
						recType	= recType + ", 26";
						// System.out.print(", 26");
						chexRecType_26(one_rec, eRep);
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
						//skipLen = Integer.parseInt(one_rec.substring(130, 137));
						skipLen	= Integer.parseInt(one_rec.substring(110, 117));
						inRead.skip(skipLen);
					} else if (one_rec.substring(0, 2).equals("54")) {
						recType	= recType + ", 54";
						// System.out.print(", 54");
					} else if (one_rec.substring(0, 2).equals("70")) {
						// PrintLog("Rec Type: 70");
					} else if (one_rec.substring(0, 2).equals("90")) {
						// PrintLog("Rec Type: 90");
						chexRecType_90(dbConn, one_rec);
					} else if (one_rec.substring(0, 2).equals("99")) {
						// PrintLog("Rec Type: 99");
						chexRecType_99(dbConn, one_rec, file2Load);
					} else {
						PrintLog("Rec Type: " + one_rec.substring(0, 2));
					}
					inRec	= "";
					start++;
				}
			}
			PrintLog("Total records processed: " + start);
			inRead.close();
		//} catch (FileNotFoundException e) {
			//e.printStackTrace();
		//	System.out.println(e);
		} finally {
			try {
				// bis.close();
			} catch (Exception e) {
				PrintLog("Exception in runtime : " + e);
			}
		}
		temp	= invalid_accts + "";
		String invalid_accts_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= missing_chknum + "";
		String missing_chknum_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		//temp	= pospay_missing + "";
		//String pospay_missing_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= stale_check + "";
		String stale_check_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		//temp	= cleared_check + "";
		//String cleared_check_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		//temp	= stopay + "";
		//String stopay_s			= ("      ").substring(0, 6 - temp.length()) + temp;
		//temp	= exceed_limit + "";
		//String exceed_limit_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		//temp	= authorize + "";
		//String authorize_s		= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= postingReady + "";
		String postingReady_s		= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= completed + "";
		String completed_s		= ("      ").substring(0, 6 - temp.length()) + temp;
		temp	= tot_chex_25 + "";
		String tot_chex_25_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		eRep.WriteHeadings();
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                           "
				+ "* * *  C h e c k  L o a d  S t a t i s t i c s  * * *");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(spaces55 +	"      Invalid Accounts: " + invalid_accts_s);
		eRep.WriteDetail(spaces55 + " Missing Check Numbers: " + missing_chknum_s);
		//eRep.WriteDetail(spaces55 + "       Posipay Missing: " + pospay_missing_s);
		eRep.WriteDetail(spaces55 + "    Stale Dated Checks: " + stale_check_s);
		//eRep.WriteDetail(spaces55 + "        Cleared Checks: " + cleared_check_s);
		//eRep.WriteDetail(spaces55 + "               Stopped: " + stopay_s);
		//eRep.WriteDetail(spaces55 + " Exceeded Limit Amount: " + exceed_limit_s);
		//eRep.WriteDetail(spaces55 + "   Await Authorization: " + authorize_s);
		eRep.WriteDetail(spaces55 + "         Posting Ready: " + postingReady_s);
		eRep.WriteDetail(spaces55 + "       Warehouse Ready: " + completed_s);
		eRep.WriteDetail(spaces55 + "         TOTAL CHECKS : " + tot_chex_25_s);
		eRep.WriteDetail(spaces55 + "    FILE TOTAL AMOUNT : " + file_tot_amt_99);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		//
		// 2011/06/10 Add a check for total amount from checks and the file. If
		// not equal then create an error message
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
	public void CreateDepRow () {
		int insertChex			= 1; // 1=insert 2=update
		int flag				= 0;
		String[] acctInfo		= {"","",""};
		String nSep				= "\\|";
		DepsDetail chexDetail	= new DepsDetail();
		//StringBuffer sql		= new StringBuffer();
		//Statement statement	= null;
		check_status			= "P";
		//payeeAcct				= " ";
		//payeeName				= " ";
		payeeStatus				= " ";
		//
		try {
			acctExists	= acUtil.DepAccountExists(dbConn, applDate, payeeAcct);
			if (acctExists==false) {
				PrintLog("Back from NO AccountExists: "+payeeAcct);
				reason_codes	+= "A";
				check_status	= "E";
				// PrintLog("Acct_num"+chex_account_num);
			} else {
				acctAndName	= acUtil.CheckAccountExists(dbConn, applDate, payeeAcct);
				acctInfo	= acctAndName.split(nSep);
				payeeAcct	= acctInfo[0];
				if (payeeName.equals("")) {
					payeeName	= acctInfo[1];
				}
				PrintLog("PayeeAcct: "+payeeAcct+" PayeeName: "+payeeName);
				if (acctInfo.length > 2) {
					payeeStatus	= acctInfo[2];
					PrintLog("PayeeStatus: "+payeeStatus);
					if (payeeStatus.equals("CLOSED")) {
						reason_codes	+= "I";
						check_status	= "I";
					} else {						
						reason_codes	+= " ";
						check_status	= "P";
					}
				}
				PrintLog("Back from CheckAccountExists: "+acctAndName);
			}
		} catch (Throwable t) {
			PrintLog("Error Validating Account ->" + t.toString());
			t.printStackTrace();
		}
		//
		if (check_num_25.length()==0) {
			check_num_25	= "000000000000000";
		}
		//PrintLog("Check Number: " + check_num_25 + " Check Number length: "+check_num_25.length());
		chexDetail.setDbUsed(dbUsed);
		chexDetail.setBankId(bankId);
		chexDetail.setAppl_date(applDate);
		chexDetail.setChex_proc_date(process_date_25);
		chexDetail.setChex_orig_account_num(account_num_25);
		chexDetail.setChex_orig_check_num(check_num_25);
		//chexDetail.setChex_account_num(Long.parseLong(account_num_25)+"");
		chexDetail.setChex_account_num(account_num_25);
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
		chexDetail.setChex_budget_id(" ");
		chexDetail.setChex_bundleid(bundle_id_20);
		chexDetail.setChex_return_date(" ");
		chexDetail.setChex_return_reason(" ");
		chexDetail.setChex_return_status(" ");
		chexDetail.setChex_BOFD_aba(bofd);
		chexDetail.setChex_BOFD_date(bofdDate);
		chexDetail.setChex_extra_1(payerCountry);
		chexDetail.setChex_extra_2(" ");
		chexDetail.setChex_issue_date(issue_date);
		chexDetail.setChex_payeeaba(ourAba);
		chexDetail.setChex_payeeacct(payeeAcct);
		chexDetail.setChex_payee(payeeName);
		chexDetail.setChex_payee_addr1(payee_addr1);
		chexDetail.setChex_payee_addr2(payee_addr2);
		chexDetail.setChex_payee_addr3(payee_addr3);
		chexDetail.setChex_comments(" ");
		chexDetail.setChex_reason_codes(reason_codes);
		chexDetail.setChex_check_status(check_status);
		chexDetail.setChex_amount_od(check_amount_od);
		chexDetail.setChex_image(checkImage);
		chexDetail.setChex_source(iclPrefix);
		chexDetail.setModtime(last_modified);
		chexDetail.setModuser(iclPrefix+"Load");
		chexDetail.setModfunc("LoadFile");
		insertChex	= 1; // do not change
		//chexDetail.ShowDetails();
		chexDetail.setChexPayerName(payerName);
		chexDetail.setChexPayerCountry(payerCountry);
		PrintLog("Payer Name > "+payerName+" Payer Country > "+payerCountry);
		try {
			if (chUtil.ChexExists(dbConn, dbTable, account_num_25, 
					check_num_25, chexDetail)==false) {
				flag	= chUtil.InsertUpdateDeps(dbConn, dbTable, dbLogTable, 
													chexDetail, insertChex);
				if (flag == 0) {
					;
				}
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
		check_status	= "";
		payee			= " ";
		sw_ref			= " ";
		iss_date		= "0";
	}
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
											"deps_load_stats(stat_id, stat_field1," +
											"stat_field2 ,stat_field3, stat_field4, stat_field5," +
											"stat_field6) values (?, ?, ?, ?, ? , ?, ?)");
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
		cl_cre_time_10	= one_rec.substring(38, 40) + ":" +
							one_rec.substring(40, 42);
		process_date_01	= one_rec.substring(22, 30);
		// Information only fields we will ignore for now
		// cl_empty_ind_10		= one_rec.substring(43, 44).trim();
		// truncation_ind_10	= one_rec.substring(44, 45).trim();
		// cl_id_10				= one_rec.substring(45, 53).trim();
		// orig_contact_name_01	= one_rec.substring(53, 67).trim();
		// orig_contact_num_10	= one_rec.substring(67, 77).trim();
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into deps_load_stats(stat_id,"+
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
		moduleName	= calledFrom;
	}

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
			bundle_id_20	= "0000000000";
		}
		bundle_id_20	= String.format("%1$010d", Long.parseLong(bundle_id_20));
		bundle_seq_20	= one_rec.substring(48, 52).trim();
		PrintLog("Bundle Id: "+bundle_id_20);
		if (bundle_seq_20==null) {
			bundle_seq_20	= " ";
		} else if (bundle_seq_20.length() == 0) {
			bundle_seq_20	= "0";
		}
		cycleNumber_20	= "Cycle Number: " + one_rec.substring(52, 54).trim();
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into " + 
									"deps_load_stats(stat_id, stat_field1," +
									"stat_field2 ,stat_field3, stat_field4, stat_field5," +
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
		moduleName	= calledFrom;
	}

	public void chexRecType_25(Connection dbConn, EcontReportUtil eRep,
			String one_rec, String user_name) throws SQLException {
		//
		calledFrom				= moduleName;
		moduleName				= "chexRecType_25: ";
		String temp				= "";
		//ArrayList <String> bank_acct	= new ArrayList<String>();
		String zerosStr			= "00000000000000000000";
		DepsDetail chexDetail	= new DepsDetail();
		//
		//check_num_25	= one_rec.substring(2, 17).trim();
		//int    slashPos	= 0;
		//int    startAt	= 0;
		check_amount_25	= "";
		check_num_25	= one_rec.substring(2,17).trim();
		check_num_25	= check_num_25.replaceAll(" ", "");
		unique_isn_25	= one_rec.substring(57,72).trim();
		try {
			if (check_num_25.equals("")) {
				temp	= one_rec.substring(27,47).trim();
				//System.out.println("Temp:\t'"+temp+"'");
				if (temp.equals("")) {
					account_num_25	= zerosStr;
					check_num_25	= zerosStr.substring(0,15);
				} else {
					if (one_rec.substring(46,47).equals("/")) {
						account_num_25	= one_rec.substring(27,47).trim();
					check_num_25	= zerosStr.substring(0,15);
					} else {	
						account_num_25	= one_rec.substring(27,42).trim();
						check_num_25	= one_rec.substring(42,47).trim();
					}
				}
			} else {
				account_num_25	= one_rec.substring(27,47).trim();
				if (account_num_25.equals("")) {
					account_num_25	= zerosStr;
				}
			}
		} catch (Exception e) {
			PrintLog("Exception in runtime : " + e);
			checksProcTemp	= tot_chex_25 + 1 + "";
			account_num_25	= checksProcTemp;
			account_num_25	= zerosStr.substring(0,20 - account_num_25.length())	+ account_num_25;
			check_num_25	= checksProcTemp;
			check_num_25	= zerosStr.substring(0, 15 - check_num_25.length()) + check_num_25;
		}
		check_amount_25	= (Double.parseDouble(one_rec.substring(47,57)) / 100)	+ "";
		check_num_25	= check_num_25.replaceAll(" ", "");
		check_num_25	= check_num_25.replaceAll("/", "");
		check_num_25	= check_num_25.replaceAll("-", "");
		account_num_25	= account_num_25.replaceAll(" ", "");
		account_num_25	= account_num_25.replaceAll("/", "");
		account_num_25	= account_num_25.replaceAll("-", "");
		temp			= "";
		routing_transit_25	= one_rec.substring(18, 27).trim();
		payerAba		= Long.parseLong(routing_transit_25)+"";
		if (routing_transit_25.equals(ourAba) || payerAba.length() < 6) {
			ext_proc_control_25	= "1";
		} else {
			ext_proc_control_25	= " ";
		}
		try {
			payerName	= dpUtil.GetPayerName(dbConn, routing_transit_25, account_num_25);
		} catch (Exception e) {
			PrintLog("Exception in getting payer name for : Payer ABA: "+ payerAba + " Payer Account: " + account_num_25 + e);
		}
		if (payerName.length() < 1) {
			payerName	= " ";
		} else {
			if (payerName.length() > 35) {
				payerName	= payerName.substring(0,35);
				PrintLog("PayerABA >"+payerAba+" Acct Num >" + account_num_25+" Payer Name >"+payerName);
			}
		}
		try {
			payerCountry	= dpUtil.GetPayerCountry(dbConn, routing_transit_25, account_num_25);
		} catch (Exception e) {
			PrintLog("Exception in getting payer Country for : Payer ABA: "+ payerAba + " Payer Account: " + account_num_25 + e);
		}
		if (payerCountry.length() < 1) {
			payerCountry	= " ";
		} else {
			if (payerCountry.length() > 2) {
				payerCountry	= payerCountry.substring(0,2);
				PrintLog("PayerABA >"+payerAba+" Acct Num >" + account_num_25+" Payer Name >"+payerCountry);
			}
		}
		PrintLog("Acct Num >" + account_num_25);
		if (check_amount_25.equals("")) {
			check_amount_25	= (Double.parseDouble(one_rec.substring(47, 57)) / 100)	+ "";
		}
		//unique_isn_25	= one_rec.substring(57, 72).trim();
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
		tot_chex_25++;
		// Used for the Report if there is an error
		chexDetail.setChex_check_amount(check_amount_25);
		chexDetail.setChex_proc_date(process_date_25);
		reason_codes	= " ";
		check_status	= "P";
		PrintLog("Account Num: " + zerosStr.substring(0, 20 - account_num_25.length()) + account_num_25 +
				 " Check Num: " + check_num_25 +
				 " Check Amount: " + check_amount_25 +
				 " Unique ISN: "+unique_isn_25);
		checkImage	= ("Check_" +
						zerosStr.substring(0, 20 - account_num_25.length()) +
						account_num_25 + "_" +
						zerosStr.substring(0, 15 - check_num_25.length()) +
						check_num_25 + "_" +
						zerosStr.substring(0, 15 - unique_isn_25.length()) + unique_isn_25);
		PrintLog("Check Image FIle Name: "+checkImage);
		// Here Insert the row
		//
		// Here Print the report line
		//
		payee		= chexDetail.getChex_payee();
		iss_date	= chexDetail.getChex_issue_date_disp();
		sw_ref		= " ";
		//if (check_status.equals("A")) {
		//	check_status	= "AUTHORIZE";
		//} else if (check_status.equals("S")) {
		//	check_status	= "STOPPED";
		//} else if (check_status.equals("Z")) {
		//	check_status	= "COMPLETE";
		//} else 
		if (check_status.equals("E")) {
			check_status	= "ERROR";
		} else if (check_status.equals("P")) {
			check_status	= "POSTING READY";
		}
		check_status		= "COMPLETE";
		String temp_amt		= chexDetail.getChex_check_amount_disp();
		String temp_date	= chexDetail.getChex_proc_date_disp();
		//PrintLog("Account Num: "+account_num_25);
		temp	= (//(bank_acct + "                   ").substring(0, 19) +
					(account_num_25 + "                    ").substring(0, 19) +
					(check_num_25 + "               ").substring(0, 15) +
					("                ").substring(0, 16 - temp_amt.length()) +
					temp_amt + "  " + temp_date + "  "  +
					unique_isn_25.substring(0) + "     " + routing_transit_25 +
					"    " + (reason_codes + "               ").substring(0, 15) + check_status);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(temp);
		if (check_status.equals("POSTING READY")) {
			temp	= ("                                                        " +
						(iss_date + "  ") +
						(sw_ref + "                           ").substring(0, 27) + 
						(payee + "                                   ").substring(0, 35));
			eRep.WriteDetail(temp);
		}
		moduleName	= calledFrom;
	}
	//
	// Check Detail Addendum C Record type 26
	//
	public void chexRecType_26(String one_rec, EcontReportUtil eRep)
			throws IOException {
		//26102600804420190913192569483000002150111327300                          Y2     
		calledFrom	= moduleName;
		moduleName	= "chexRecType_26: ";
		bofd		= (one_rec.substring(3, 12));
		bofdDate	= (one_rec.substring(12, 20));
		payeeName	= one_rec.substring(58, 72).trim();
		temp		= one_rec.substring(35, 47).trim();
		payeeAcct	= "";
		PrintLog("chexRecType_26 Payee Account Num ="+temp);
		if ((temp.length() == 0) || (temp.equals("/"))) {
			payeeAcct	= "0000000000000000000";
		} else {
			for (int indx = 0; indx < temp.length(); indx++) {
				if (gUtil.isNumeric(temp.substring(indx, indx + 1)) == true) {
					payeeAcct += temp.substring(indx, indx + 1);
				}
			}
			if (payeeAcct.equals("")) {
				payeeAcct	= "0000000000000000000";
			}
		}
		//PrintLog("in chexRecType_25 Account Num ="+account_num_25);
		if (payeeAcct.length() > 17) {
			payeeAcct	= payeeAcct.substring(0, 17);
			PrintLog("chexRecType_26 Payee Account Num ="+payeeAcct);
		}
		temp	= "";
		temp	= ("BOFD: " + one_rec.substring(3, 12) + 
					"    BOFD Date: " + one_rec.substring(12, 20));
		eRep.WriteDetail(temp);
		moduleName = calledFrom;
		CreateDepRow();
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
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into deps_load_stats"+
															"(stat_id, stat_field1," +
															"stat_field2 ,stat_field3," + 
															" stat_field4, stat_field5," +
															"stat_field6) values (?, ?, " +
															"?, ?, ?, ?, ?)");
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
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 50
	//
	public void chexRecType_50()
			throws SQLException {
		//calledFrom	= moduleName;
		//moduleName	= "chexRecType_50: ";
		//moduleName	= calledFrom;
		//
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
			BufferedWriter outWrite1	= new BufferedWriter(new FileWriter(outDir + jpegFile_o));
			outWrite1.write(imageRec);
			outWrite1.close();
			jpegCreated	= doTiff2JPEG(jpegFile_o);
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
		//calledFrom	= moduleName;
		//moduleName	= "chexRecType_54: ";
		//
		//moduleName	= calledFrom;
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
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into " + "" +
										"deps_load_stats(stat_id, stat_field1," +
										"stat_field2 ,stat_field3, stat_field4, stat_field5," +
										"stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
		row_id++;
		// PrintLog(" Row id "+row_id+" CycleId: "+cycleId);
		pstmt.setInt(1, (cycleId + row_id));
		pstmt.setString(2, "Check Detail Stats");
		pstmt.setString(3, " ");
		pstmt.setString(4, " ");
		pstmt.setString(5, " ");
		pstmt.setString(6, tot_chex_25 + "");
		temp	= tot_amt_25 + "";
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
		//
		pstmt	= dbConn.prepareStatement("insert into deps_load_stats(stat_id, stat_field1," +
										  "stat_field2 ,stat_field3, stat_field4, stat_field5," +
										  "stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
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
	// ActionErrors errors,
			String one_rec) throws SQLException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_90: ";
		DecimalFormat df	= new DecimalFormat("#,###,###,##0.00");
		// StringBuffer sql	= new StringBuffer();
		// Statement statement = null;
		// int result	= 0;
		String temp		= "";
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
		PreparedStatement pstmt = dbConn.prepareStatement("insert into " +
											" deps_load_stats(stat_id, stat_field1," +
											"stat_field2 ,stat_field3, stat_field4, stat_field5," +
											"stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
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
		moduleName	= calledFrom;
	}
	//
	// Check record type 9 - File Control Record
	//
	public void chexRecType_99(Connection dbConn,
			String one_rec, String file2Load)
			throws SQLException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType99: ";
		DecimalFormat df	= new DecimalFormat("#,###,###,##0.00");
		invalid_accts		= 0;
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
		//int insOrUpd		= 2;
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
		PreparedStatement pstmt	= dbConn.prepareStatement("insert into " +
									"deps_load_stats(stat_id, stat_field1," +
									"stat_field2 ,stat_field3, stat_field4, stat_field5," +
									"stat_field6) values (?, ?, ?, ?, ?, ?, ?)");
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
		sql.append("CHEX_CHECK_STATUS, COUNT(*) FROM "+dbTable);
		sql.append(" GROUP BY CHEX_REASON_CODES, CHEX_CHECK_STATUS ");
		sql.append("ORDER BY CHEX_CHECK_STATUS");

		// Prepare the SELECT statement.*/
		statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet q_result	= statement.executeQuery(sql.toString());
		while (q_result.next()) {
			// ChexSummary chexSummary	= new ChexSummary();
			// Here add the fields tp the ChexDetail bean
			check_status		= q_result.getString("CHEX_CHECK_STATUS").trim();
			check_reason_code	= q_result.getString("CHEX_REASON_CODES");
			check_count			= q_result.getString("COUNT(*)").trim();
			PrintLog("Check Status: " + check_status + " Reason Codes: " +
					 check_reason_code + " Check Count:  " + check_count);
			if (check_status.equals("E") && check_reason_code.equals("A")) {
				invalid_accts	+= Integer.parseInt(check_count);
			} else if (check_status.equals("E") && check_reason_code.equals("CA")) {
				missing_chknum	+= Integer.parseInt(check_count);
			} else if (check_status.equals("E") && check_reason_code.equals("MA")) {
				missing_chknum	+= Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("C")) {
				missing_chknum	+= Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals("M")) {
				missing_chknum	+= Integer.parseInt(check_count);
			} else if (check_status.equals("R") && check_reason_code.equals(" D")) {
				stale_check		+= Integer.parseInt(check_count);
			//} else if (check_status.equals("R") && check_reason_code.equals("H")) {
			//	cleared_check	+= Integer.parseInt(check_count);
			//} else if (check_status.equals("R") && check_reason_code.equals("L")) {
			//	exceed_limit	+= Integer.parseInt(check_count);
			//} else if (check_status.equals("R") && check_reason_code.equals("P")) {
			//	pospay_missing	+= Integer.parseInt(check_count);
			//} else if (check_status.equals("R") && check_reason_code.equals("Q")) {
			//	pospay_missing	+= Integer.parseInt(check_count);
			//} else if (check_status.equals("S") && check_reason_code.equals("S")) {
			//	stopay			+= Integer.parseInt(check_count);
			//} else if (check_status.equals("R") && check_reason_code.equals("T")) {
			//	stopay			+= Integer.parseInt(check_count);
			} else if (check_status.equals("P")) {
				postingReady		+= Integer.parseInt(check_count);
			} else if (check_status.equals("Z")) {
				completed		+= Integer.parseInt(check_count);
			//} else if (check_status.equals("A")) {
			//	authorize		+= Integer.parseInt(check_count);
			}
		}
		statement.close();
		//
		// Insert a row for status counts
		//
		// row_id = cycleNum*10;
		row_id	= cycleId;
		sql.setLength(0);
		sql.append("INSERT INTO deps_load_stats VALUES (");
		sql.append("'" + row_id + "', ");
		sql.append("'" + invalid_accts + "', ");
		sql.append("'" + missing_chknum + "', ");
		sql.append("'" + pospay_missing + "', ");
		sql.append("'" + stale_check + "', ");
		sql.append("'" + cleared_check + "', ");
		sql.append("'" + stopay + "', ");
		sql.append("'" + exceed_limit + "', ");
		sql.append("'" + authorize + "', ");
		sql.append("'" + completed + "', ");
		sql.append("'0')");
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
		/*
		if (!myActionFlag.equals("LoadHistory")) {
			SysadControlUtil icUtil		= new SysadControlUtil();
			ControlDetail ctlDetail		= new ControlDetail();
			//ControlDetail ctlDetailA	= new ControlDetail();
			//ControlDetail ctlDetailD	= new ControlDetail();
			//ControlDetail ctlDetailE	= new ControlDetail();
			ControlSelector ctlSelector	= new ControlSelector();
			//prodId	= "E";
			//if (iclPrefix.equals("RDC")) {
				prodId	= "D";
			//}
			try {
				int row_count	= icUtil.GetControlRow(dbConn, ctlSelector, prodId);
				if (row_count == 0) {
					;
				}
				ctlDetail	= ctlSelector.getARow();
				ctlDetail.setDbUsed(dbUsed);
				ctlDetail.setFile_loaded(" ");
				if (dbUsed.equals("MySQL")) {
					ctlDetail.setFile_loaded_time("NULL");
				} else if (dbUsed.equals("ORACLE")) {
					ctlDetail.setFile_loaded_time("sysdate");
				}
				try {
					int retVal	= icUtil.InsertUpdateControl(dbConn, ctlDetail,	userId, insOrUpd);
					// int ret_val	= icUtil.UpdateControl(dbConn, ctlDetail);
					if (retVal == 0) {
						;
					}
				} catch (Throwable t) {
					PrintLog("Error Updating Control ->" + t.toString());
				}
			} catch (Throwable t) {
				PrintLog("Error Getting Control ->" + t.toString());
			}
			statement.close();
			row_id		= 0;
			moduleName	= calledFrom;
		}
		*/
	} // END public void chexRecType_99
}
