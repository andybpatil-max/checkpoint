package com.webfiche.checkpoint.deposits.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import com.webfiche.checkpoint.deposits.beans.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.inclear.service.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;
//
@Service
public final class UploadDepMicr {
	// Process Statistics Counters
	private String	moduleName;
	private String	calledFrom;
	private String	className		= "> UploadDepMicr.";
	// private boolean micrNotOk = false;
	static String	actionFlag		= "";
	static String	dbUsed			= "";
	static String	dbTable			= "rdc_chex";
	static String	dbLogTable		= "rdc_chexlog";
	static String	applDate		= "";
	static String	temp			= "";
	static String	outDir			= "";
	static String	imageBaseDir	= "";
	static String	reportDir		= "";
	static String	fileToLoad		= "";
	static boolean	firstFile		= false;
	//
	static int		invalid_accts	= 0;
	static int		missing_chknum	= 0;
	static int		missingAmount	= 0;
	static int		multiMaps		= 0;
	static int		stale_check		= 0;
	static int		cleared_check	= 0;
	static int		stopay			= 0;
	static int		exceed_limit	= 0;
	static int		authorize		= 0;
	static int		completed		= 0;
	static int		ret_val			= 0;					 // Success
	static double	totAmt			= 0;
	static int		totChex			= 0;
	static int		cycleId			= 0;
	static int		row_id			= 0;
	static int		osnIndex		= 0;
	static int		numMicrFields	= 0;
	long			maxOSN			= 999999999999L;
	long			depsOSN			= 0;
	static String	recType			= "";
	static String	reportHdg		= null;
	static String	repPad			= "";
	static int		repPadSize		= 0;
	String			inRec			= "";
	//
	static String	micrCheckNum;		// 15 bytes
	static String	micrABA;			// 9 bytes
	static String	micrAcctNum;		// 12 bytes
	static String	micrAmount;		// Max 10 bytes
	static String	micrScore;			// 3 bytes max
	static int		iMicrScore;			// 3 bytes max
	static String	micrOSN;			// 12 bytes
	static String	micrFileName;
	static String	mapAcctNum;
	static String	micrComments;
	static String	creditorAcct;
	static String	acctPayee;
	static String	checkImage;
	// Incl Checx Table fields
	static String	issue_date		= "0";
	static String	iss_date		= " ";
	static String	sw_ref			= " ";
	static String	payee			= " ";
	static String	payee_addr1	= " ";
	static String	payee_addr2	= " ";
	static String	payee_addr3	= " ";
	static String	comments		= " ";
	static String	reason_codes	= " ";
	static String	check_status	= " ";
	static String	check_amt_od	= " ";
	static String	modTime		= " ";
	static String	modUser		= " ";
	static String	modFunc		= " ";
	//
	static String	defCurr		= "";
	static String	bankId			= "";
	static String[] micrFields	= { "", "", "", "", "", "", "", "", "", "" };
	static String	fsTab			= "\\t";
	//
	int				insert_chex		= 1;	// 1=insert 2=update
	int				flag				= 0;
	int				slashPos			= 0;
	boolean			acct_exists		= false;
	String			bankAcct		= "";
	String			zerosStr		= "00000000000000000000";
	ArrayList<String> bankAcctList	= new ArrayList<String>();
	ArrayList<String> dummy_list	= new ArrayList<String>();		// Dummy array
	PayerSelector	payerSelector	= new PayerSelector();
	PayerDetail		payerDetail		= new PayerDetail();
	AccountSelector	acctSelector	= new AccountSelector();
	AccountDetail	acctDetail		= new AccountDetail();
	DepsDetail		depsDetail		= new DepsDetail();
	StringBuffer	sql			= new StringBuffer();
	Statement		statement		= null;
	InclAcctUtil	acUtil		= new InclAcctUtil();
	DepsPayerUtil	daUtil		= new DepsPayerUtil();
	DepsChexUtil	deUtil		= new DepsChexUtil();
	int				result			= 0;
	GenUtil			gUtil			= new GenUtil();
	Connection		dbConn;
	EcontReportUtil	eRep;
	String			user_name;
	//
	public void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
	}
	public int LoadDepositMicr(Connection dbConn1, ControlDetail controlDetail,
			ControlDetail controlDetailD) throws SQLException, Exception {
		moduleName	= "LoadDepositMicr: ";
		String temp;
		EcontServletContextListener escl	= new EcontServletContextListener();
		// GenUtil gUtil	= new GenUtil();
		dbConn			= dbConn1;
		// dbConn		= controlDetail.getDbConn();
		reportHdg		= escl.getReportHdg();
		defCurr			= escl.getDefCurr();
		bankId			= escl.getBankId();
		dbUsed			= escl.getDbUsed();
		actionFlag		= controlDetail.getActionFlag();
		imageBaseDir	= controlDetail.getImgBaseDir();
		reportDir		= controlDetail.getLocOutputDir() + "deposits/";
		applDate		= controlDetail.getApplDate();
		fileToLoad		= controlDetailD.getFileToLoad();
		firstFile		= controlDetail.getFirstFile();
		user_name		= controlDetail.getUser_name();
		depsOSN			= controlDetailD.getDepOSN();
		if (depsOSN > maxOSN) {
			depsOSN	= 0;
		}
		totAmt	= 0.0;
		totChex	= 0;
		PrintLog("Appl Date: " + applDate + " Cycle Num: " + cycleId);
		String reportName	= "UploadDeposit_" + cycleId + ".rpt";
		//
		// Get date and create a directory for todays files
		//
		String sn_mm	= "";
		String sn_dd	= "";
		String sn_yy	= "";
		String sn_date	= "";
		java.util.GregorianCalendar now = new java.util.GregorianCalendar();
		sn_yy	= now.get(Calendar.YEAR) + "";
		sn_mm	= (now.get(Calendar.MONTH) + 1) + "";
		if (sn_mm.length() == 1) {
			sn_mm	= '0' + sn_mm;
		}
		sn_dd	= now.get(Calendar.DATE) + "";
		if (sn_dd.length() == 1) {
			sn_dd	= '0' + sn_dd;
		}
		sn_date	= sn_yy + sn_mm + sn_dd;
		//
		imageBaseDir	= (imageBaseDir + "outcl/" + applDate.substring(0, 4) + 
							"/" + applDate.substring(4, 6) + 
							"/" + applDate.substring(6, 8) + "/");
		PrintLog("Output Dir " + imageBaseDir);
		// From the appl date find if the directory exists for writing out the
		// image files
		File outputDir = new File(imageBaseDir);
		if (!outputDir.exists()) {
			PrintLog("Specified directory does not exist - Creating > "
					+ outDir);
			File fileOutdir = new File(imageBaseDir);
			boolean mk_dir = fileOutdir.mkdirs();
			if (mk_dir == false) {
				PrintLog("Error creating Directory " + mk_dir);
				return (0);
			}
		}
		outDir = imageBaseDir;
		//
		// Set up the headings etc for the report
		//
		// repPadSize = (92 - reportHdg.length())/2;
		if (reportHdg.length() < 92) {
			repPadSize = 92 - reportHdg.length();
			if (repPadSize > 2) {
				repPadSize = repPadSize / 2;
			}
		}
		repPad = GenUtil.pad(repPad, repPadSize, " ");
		// PrintLog("ReportDir: "+reportDir);
		// PrintLog("ReportName: "+reportName);
		eRep = new EcontReportUtil(reportDir + reportName);
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(applDate);
		temp = ("REPORT NAME:UPLOADMICR.RPT" + repPad + reportHdg + repPad + "PAGE:");
		eRep.setHeadings(temp);
		temp = ("                                                     "
				+ "PureDataware checkPoint SYSTEM");
		eRep.setHeadings(temp);
		temp = ("                                           "
				+ "    MICR DATA UPLOAD REPORT AS OF ");
		eRep.setHeadings(temp);
		temp = ("--------------------------------------------------------"
				+ "--------------------------------------------------------"
				+ "--------------------");
		eRep.setHeadings(temp);
		temp = ("ACCOUNT NUMBER     CHECK ACCT NUM  CHECK NUM          CH"
				+ "ECK AMOUNT  PROC DATE     UNIQUE ISN   ABA          REAS"
				+ "ON CODE    STATUS");
		eRep.setHeadings(temp);
		temp = ("--------------------------------------------------------"
				+ "--------------------------------------------------------"
				+ "--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		//
		// Load or Reload Clear out the Chex, Chex_log and the Stats tables
		//
		Statement pstmt1 = dbConn.createStatement();
		PrintLog("ActionFlag : " + actionFlag);
		if (actionFlag.equals("LoadFile")) {
			try {
				pstmt1.executeUpdate("truncate table deps_chex ");
			} catch (SQLException e) {
				PrintLog("SQLException : " + e);
				PrintLog("Terminating the delete process of deps_chex");
				return (-1);
			}
		}
		//
		// If reload then reste the tables for the first file only
		//
		if (actionFlag.equals("ReLoadFiles") && firstFile) {
			//
			// Restore the OSN from control table
			//
			try {
				pstmt1.executeUpdate("truncate table deps_chex ");
				if (dbUsed.equals("ORACLE")) {
					pstmt1.executeUpdate("delete from  deps_chex_log_" +
										 applDate.substring(0, 6) + " where " +
										 "SUBSTR(to_char(chlog_last_modified,'yyyymmdd hh24:mi:ss'),1,8)='" +
										 sn_date + "'");
				} else {
					pstmt1.executeUpdate("delete from  deps_chex_log_" +
										 applDate.substring(0, 6) + " where " +
										 "SUBSTR(chlog_last_modified,1,8)='" + sn_date + "'");
				}
			} catch (SQLException e) {
				PrintLog("SQLException : " + e);
				PrintLog("Terminating the delete process of deps_chex/deps_chex_log_" +
						 applDate.substring(0, 6));
				return (-1);
			}
		}
		pstmt1.close();
		PrintLog("Will read Cycle ID: " + cycleId);
		// int start = 0;
		// String one_rec = "";
		try {
			//
			// The input file has
			// CheckNumber/ABA/Account/Amount/Score/CheckImageFilename
			//
			PrintLog("Input File " + outDir + fileToLoad);
			BufferedReader inRead	= new BufferedReader(new FileReader(outDir + fileToLoad));
			// start = 0;
			inRec	= "";
			while ((inRec = inRead.readLine()) != null) {
				totChex++;
				PrintLog("Input: " + inRec.substring(0, inRec.indexOf("Check") - 1));
				if (inRec.length() > 0) {
					micrFields = inRec.split(fsTab);
					//
					// Fields are Check Number=6 chars
					// ABA=9 chars,
					// Account=12 chars,
					// and if present Amount=12 chars
					// Score upto 3 chars
					// and Image FileName
					//
					micrCheckNum	= micrFields[0];
					micrABA			= micrFields[1];
					micrAcctNum		= micrFields[2];
					micrAmount		= micrFields[3];
					micrScore		= micrFields[4];
					iMicrScore		= Integer.parseInt(micrFields[4]);
					checkImage		= micrFields[5];
					numMicrFields	= micrFields.length;
					if (micrCheckNum.equals("0") && micrABA.equals("0")
							&& micrAcctNum.equals("0")) {
						micrNotOk();
						continue;
					}
					// checkImage = ("CheckImage_"+);
					depsOSN++;
					micrOSN	= "" + depsOSN;
					micrOk();
				}
			}
			PrintLog("Total records processed: " + totChex);
			inRead.close();
		} catch (Exception e) {
			PrintLog("Exception in runtime : " + e);
			e.printStackTrace(System.out);
		}
		processEOF();
		temp		= invalid_accts + "";
		String invalid_accts_s = ("      ").substring(0, 6 - temp.length()) + temp;
		temp = missing_chknum + "";
		// String missing_chknum_s = ("      ").substring(0,6 - temp.length()) +
		// temp;
		temp				= completed + "";
		String completed_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		temp				= totChex + "";
		String totChexs		= ("      ").substring(0, 6 - temp.length()) + temp;
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
						 "            Completed:  " + completed_s);
		eRep.WriteDetail("                                                       " +
						 "        TOTAL CHECKS :  " + totChexs);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		ret_val	= 1;
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       " +
						 "     *** END OF REPORT ***");
		eRep.Close();
		controlDetail.setErrorVec("Deposit MICR", "result.data.loaded");
		return (ret_val);
	}
	//
	public void micrNotOk() {
		calledFrom		= moduleName;
		moduleName		= "micrNotOk: ";
		// PrintLog("In micrNotOk "+totChex);
		depsOSN++;
		micrOSN			= "" + depsOSN;
		micrAcctNum		= micrOSN;
		;
		micrCheckNum	= micrOSN;
		micrABA			= "0";
		bankAcct		= micrOSN;
		// micrAmount	= "0.0";
		acctPayee		= " ";
		/*
		 * depsDetail.setChex_check_amount("0.0");
		 * depsDetail.setChex_account_num(micrOSN);
		 * depsDetail.setChex_check_num(micrOSN);
		 * depsDetail.setChex_orig_account_num(micrOSN);
		 * depsDetail.setChex_orig_check_num(micrOSN);
		 */
		temp			= inRec.replaceAll("\\t", "_");
		micrComments	= temp.substring(0, temp.lastIndexOf("_") - 1);
		if (!micrAmount.equals("")) {
			totAmt		= totAmt + (Double.parseDouble(micrAmount));
		}
		// totChex++;
		// Used for the Report if there is an error
		if (gUtil.isNumeric(micrAmount) == true) {
			micrAmount	= "" + (Double.parseDouble(micrAmount) / 100);
		}
		depsDetail.setChex_check_amount(micrAmount);

		// PrintLog("micrComments: "+micrComments);
		reason_codes	= "S";
		check_status	= "S";
		creditorAcct	= " ";
		try {
			processMicr();
		} catch (Exception e) {
			PrintLog("Exception in runtime : " + e);
			e.printStackTrace();
		}
		reason_codes	= "";
		check_status	= "";
		moduleName		= "LoadDepositMicr: ";
	}
	//
	public void micrOk() {
		calledFrom	= moduleName;
		moduleName	= "micrOk: ";
		// PrintLog("In micrOk "+totChex);
		// PrintLog("micrCheckNum: "+micrCheckNum);
		// PrintLog("micrAcctNum: "+micrAcctNum);
		// PrintLog("micrAmount: "+micrAmount);
		// PrintLog("micrOSN: "+micrOSN);
		// PrintLog("checkImage: "+checkImage);
		// PrintLog("User Name: "+user_name);
		//
		if (micrCheckNum.equals("")) {
			micrCheckNum	= "0";
		}
		if (micrAcctNum.length() == 0) {
			micrAcctNum		= "0000000000000000000";
		}
		if (micrAcctNum.length() > 17) {
			micrAcctNum		= micrAcctNum.substring(0, 17);
		}
		acctPayee			= " ";
		reason_codes		= "";
		check_status		= "";
		modTime				= "NULL";
		modUser				= user_name.trim();
		modFunc				= "Load File";
		if (!micrAmount.equals("")) {
			totAmt			= totAmt + (Double.parseDouble(micrAmount));
		}
		// totChex++;
		// Used for the Report if there is an error
		if (gUtil.isNumeric(micrAmount) == true) {
			micrAmount		= "" + (Double.parseDouble(micrAmount) / 100);
		}
		depsDetail.setChex_check_amount(micrAmount);
		depsDetail.setChex_proc_date(applDate);
		if (micrCheckNum.equals("00000000")) {
			reason_codes	= "C";
			check_status	= "R";
		}
		if (micrAcctNum.equals("000000000000")) {
			if (reason_codes.equals("")) {
				reason_codes	= "A";
			} else {
				reason_codes	+= "A";
			}
			check_status	= "E";
		}

		creditorAcct	= " ";
		try {
			mapAcctNum		= micrABA + micrAcctNum;
			payerSelector.setPayerAba(micrABA);
			payerSelector.setPayerAcct(micrAcctNum);
			//bankAcctList	= daUtil.PayerAcctExists(dbConn, payerSelector);
			if (bankAcctList.size() == 0) {
				bankAcct	= micrAcctNum;
				// PrintLog("Back from CheckAccountExists"+bankAcct);
				if (!reason_codes.startsWith("A")) {
					reason_codes	+= "A";
				}
				check_status	= "E";
				invalid_accts++;
				// PrintLog("Acct_num"+chex_account_num);
			} else if (bankAcctList.size() == 1) {
				bankAcct		= bankAcctList.get(0);
				acctDetail		= acUtil.GetAccountRows(dbConn, bankAcct);
				creditorAcct	= acctDetail.getAccount_stop_suspense();
				acctPayee		= acctDetail.getAccount_client_name();
				reason_codes	+= " ";
				check_status	= "Z";
				completed++;
			} else if (bankAcctList.size() > 1) {
				bankAcct	= micrAcctNum;
				if (!reason_codes.startsWith("A")) {
					reason_codes	+= "A";
				}
				check_status	= "M";
				multiMaps++;
			}
		} catch (Throwable t) {
			PrintLog("Error Validating Account ->" + t.toString());
		}
		//
		// Determine the status based on the Amount Score acceptable above 800
		//
		if (iMicrScore < 800) {
			if (check_status.equals("")) {
				check_status	= "R";
			}
			reason_codes	+= "R";
		}
		/*
		 * depsDetail.setChex_account_num(micrAcctNum);
		 * depsDetail.setChex_check_num(micrCheckNum);
		 * depsDetail.setChex_orig_account_num(micrAcctNum);
		 * depsDetail.setChex_orig_check_num(micrCheckNum);
		 * depsDetail.setChex_extra_1(" ");
		 */
		micrComments	= " ";
		try {
			// PrintLog("Will process MICR");
			processMicr();
		} catch (Exception e) {
			PrintLog("Exception in runtime : " + e);
			e.printStackTrace();
		}
		moduleName	= "LoadDepositMicr: ";
	}
	//
	public void processMicr() throws SQLException {
		calledFrom	= moduleName;
		moduleName	= "processMicr: ";
		PrintLog("CreditorAccount beforeUpdate->" + creditorAcct);
		//
		// Here Insert the row
		// PrintLog("In process MICR");
		depsDetail.setDbUsed(dbUsed);
		depsDetail.setBankId(bankId);
		depsDetail.setAppl_date(applDate);
		depsDetail.setChex_proc_date(applDate);
		depsDetail.setChex_orig_account_num(micrAcctNum);
		depsDetail.setChex_orig_check_num(micrCheckNum);
		depsDetail.setChex_account_num(bankAcct);
		depsDetail.setChex_check_num(micrCheckNum);
		depsDetail.setChex_routing_transit(micrABA);
		depsDetail.setChex_check_currency(defCurr);
		depsDetail.setChex_check_amount(micrAmount);
		depsDetail.setChex_proc_control(" ");
		depsDetail.setChex_ext_proc_control(" ");
		depsDetail.setChex_image_locator(" ");
		depsDetail.setChex_unique_isn(micrOSN);
		depsDetail.setChex_addenda_rec_flag(" ");
		depsDetail.setChex_orig_inst_rte(" ");
		depsDetail.setChex_isn(" ");
		depsDetail.setChex_budget_id(" ");
		depsDetail.setChex_return_date(" ");
		depsDetail.setChex_return_reason(" ");
		depsDetail.setChex_return_status(" ");
		depsDetail.setChex_BOFD_aba(" ");
		depsDetail.setChex_BOFD_date(" ");
		depsDetail.setChex_extra_1(creditorAcct);
		depsDetail.setChex_extra_2(micrScore);
		depsDetail.setChex_issue_date(" ");
		depsDetail.setChex_payee(acctPayee);
		depsDetail.setChex_payee_addr1(" ");
		depsDetail.setChex_payee_addr2(" ");
		depsDetail.setChex_payee_addr3(" ");
		depsDetail.setChex_comments(micrComments);
		depsDetail.setChex_reason_codes(reason_codes);
		depsDetail.setChex_check_status(check_status);
		depsDetail.setChex_amount_od(" ");
		depsDetail.setChex_image(checkImage);
		depsDetail.setModtime(modTime);
		depsDetail.setModuser(user_name);
		depsDetail.setModfunc("Load File");
		insert_chex = 1; // do not change
		try {
			// PrintLog("Calling InsertUpdateDeps from  process MICR");
			flag = deUtil.InsertUpdateDeps(dbConn, dbTable, dbLogTable, depsDetail, insert_chex);
		} catch (Throwable t) {
			PrintLog("Error inserting Chex ->>>" + t.toString());
			t.printStackTrace();
		}
		reason_codes	= depsDetail.getChex_reason_codes();
		check_status	= depsDetail.getChex_check_status();
		// PrintLog("Reason Codes: "+reason_codes+"  Check Status: "+check_status);
		// bofd = " ";
		// bofdDate = " ";
		//
		// Here Print the report line
		//
		payee		= depsDetail.getChex_payee();
		iss_date	= depsDetail.getChex_issue_date_disp();
		sw_ref		= depsDetail.getChex_extra_1();
		if (check_status.equals("A")) {
			check_status	= "AUTHORIZE";
		} else if (check_status.equals("E")) {
			check_status	= "ERROR";
		} else if (check_status.equals("M")) {
			check_status	= "MULTIPLE REF";
		} else if (check_status.equals("R")) {
			check_status	= "REJECTED";
		} else if (check_status.equals("S")) {
			check_status	= "SCAN ERRORS";
		} else if (check_status.equals("Z")) {
			check_status	= "COMPLETE";
		}
		String temp_amt		= depsDetail.getChex_check_amount_disp();
		String temp_date	= depsDetail.getChex_proc_date_disp();
		temp	= ((bankAcct + "                   ").substring(0, 19) +
					(micrAcctNum + "                 ").substring(0, 16) +
					(micrCheckNum + "               ").substring(0, 15) +
					("                ").substring(0, 16 - temp_amt.length()) +
					temp_amt + "  " + temp_date + "  " +
					("            ").substring(0, 12 - micrOSN.length()) +
					micrOSN.substring(0) + "   " + micrABA + "    " +
					(reason_codes + "               ").substring(0, 15) + check_status);
		eRep.WriteDetail(" ");
		eRep.WriteDetail(temp);
		check_status = "";
		moduleName = calledFrom;
	}
	//
	public void processEOF() {
		// throws SQLException {
		calledFrom		= moduleName;
		moduleName		= "processEOF: ";
		String prodId	= "D";
		int insOrUpd	= 2;
		//
		// Update control row with file name
		//
		SysadControlUtil icUtil		= new SysadControlUtil();
		ControlDetail ctlDetail		= new ControlDetail();
		ControlSelector ctlSelector	= new ControlSelector();
		ctlDetail.setDbUsed(dbUsed);
		try {
			PrintLog("Will Update Control Row......................");
			result		= icUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail	= ctlSelector.getARow();
			ctlDetail.setFile_loaded(fileToLoad);
			try {
				int retVal	= icUtil.InsertUpdateControl(dbConn, ctlDetail,	user_name, insOrUpd);
				if (retVal == 0) {
					;
				}
				// statement.close();
			} catch (Throwable t) {
				PrintLog("Error Updating Control ->" + t.toString());
			}
		} catch (Throwable t) {
			PrintLog("Error Getting Control ->" + t.toString());
		}
		row_id		= 0;
		moduleName	= calledFrom;
	}
}
