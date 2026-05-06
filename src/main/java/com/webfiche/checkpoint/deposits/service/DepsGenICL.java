package com.webfiche.checkpoint.deposits.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.text.*;
import java.util.*;
//import org.apache.struts.action.*;
import com.webfiche.checkpoint.deposits.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
//import org.apache.log4j.Logger;
//import com.webfiche.checkpoint.icl.record.*;
//import com.webfiche.checkpoint.icl.util.*;
//import com.webfiche.checkpoint.jpos.iso.ISOUtil;
//import com.webfiche.checkpoint.icl.record;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;

@Service
public final class DepsGenICL {
	// Process Statistics Counters
	String			className		= "> DepsGenICL.";
	String			moduleName;
	String			calledFrom;
	static String 	checkAmount		= "";
	static String 	checkAcctNum	= "";
	static String 	checkUniqueISN	= "";
	static String 	checkDate		= "";
	static String 	checkPayee		= "";
	static String 	checkNum		= "";
	static String 	checkStatus		= "";
	static String	dbUsed			= "";
	static String	applDate		= "";
	static String	procDate		= "";
	static String 	temp			= "";
	static String	retTime			= "";
	static String	fileTime		= "";
	static int		outRecs			= 0;
	static String	outRecs_s		= "";
	static double	tot_amt_25		= 0.0;
	static int		tot_chex_25		= 0;
	static int		row_id			= 1;
	static int		rowCount		= 0;
	static int		retCount		= 0;
	static int		fileNum			= 0;
	static int		readBytes		= 0;
	static int		imageLen		= 0;
	static int		retResult		= 0;
	static int		maxLen			= 0;
	static String	file_to_load	= "";
	static String	recType			= "";
	static String	modFunc			= "DepsGenICL";
	static String	reportHdg		= null;
	static String	repPad			= "";
	static int		repPadSize		= 0;
	static String	imageDir		= "";
	String			prodId			= "";
	//
	// Incl Checx Table fields
	//
	static String	issue_date		= "0";
	static String	iss_date		= " ";
	static String	sw_ref			= " ";
	static String	payee			= " ";
	static String	payee_addr1		= " ";
	static String	payee_addr2		= " ";
	static String	payee_addr3		= " ";
	static String	comments		= " ";
	static String	chex_unique_isn;
	static String	reason_codes;
	static String	check_status;
	static String	check_amount_od;
	static String	last_modified;
	static String	retCheckDate;
	static String	frbFileName;
	static String	frbFileNamePre;
	static String	frbFileNameSuf;
	static String	frbFileDir;
	static String	fileToProcess;
	static String	reportDir;
	static String	sn_mm			= "";
	static String	sn_dd			= "";
	static String	sn_yy			= "";
	static String	sn_date			= "";
	static String	imageRec		= "";
	//
	String[]		cp_cmd			= { "/bin/sh", "-c", " " };
	//
	boolean			written10		= false;
	boolean			written20		= false;
	boolean			written52		= false;
	boolean			written70		= false;
	boolean			written90		= false;
	//
	byte[]			recText;
	byte[]			recText1;
	byte[]			newText;
	byte[]			recSizePrefix80	= new byte[4];
	byte[]			recSizePrefix85	= new byte[4];
	byte[]			recSizePrefixOther = new byte[4];
	static String	recSize80s		= "";
	static String	recSizeOthers	= "";
	File			findFile		= null;
	DateFormat		new_fmt1		= null;
	DateFormat		newFmt2			= null;
	DecimalFormat	df				= new DecimalFormat("#########0.00");
	DecimalFormat	dfR				= new DecimalFormat("#,###,###,##0.00");
	//
	//RT01FileHeader			fileHeader			= new RT01FileHeader();
	//RT10CashLetterHeader	cashLetterHeader	= new RT10CashLetterHeader();
	//RT20BundleHeader		bundleHeader		= new RT20BundleHeader();
	//RT25CheckDetail			checkDetail			= new RT25CheckDetail();
	//RT26CheckDetailAddendumA addendumA			= new RT26CheckDetailAddendumA();
	//RT50ImageViewDetail		imageDetail			= new RT50ImageViewDetail();
	//RT52ImageViewData		dataRecord			= new RT52ImageViewData();
	//RT54ImageViewAnalysis	analysisRecord		= new RT54ImageViewAnalysis();
	//RT70BundleControl 		bundleControl		= new RT70BundleControl();
	//RT90CashLetterControl	cashLetterControl	= new RT90CashLetterControl();
	//RT99FileControl			fileControl			= new RT99FileControl();
	//
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ControlSelector		ctlSelector	= new ControlSelector();
	ControlDetail		ctlDetail	= new ControlDetail();
	DepsChexUtil		chUtil		= new DepsChexUtil();
	GenUtil				gUtil		= new GenUtil();
	FileOutputStream	outWrite	= null;
	ByteArrayOutputStream baos		= new ByteArrayOutputStream();
	String		returnOutputFile	= "";
	// declare a print stream object
	PrintStream		ps				 = null;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public int GenerateICL (Connection dbConn, String dbUsed_1,
			DepsSelector depsSelector, String userId) 
		throws SQLException, Exception {
		EcontServletContextListener escl	= new EcontServletContextListener();
		reportHdg			= escl.getReportHdg();
		moduleName			= "GenerateICL: ";
		String temp			= "";
		String reportName	= "";
		String ourAba 		= "";
		PrintLog(userId);
		tot_amt_25			= 0.0;
		tot_chex_25			= 0;
		outRecs				= 0;
		String zeroFill		= "000000000000000";
		// row_id = cycleNum*10;
		dbUsed				= dbUsed_1;
		// applDate = applDate_1;
		PrintLog("Appl Date: " + applDate);
		int recSize80 = 80;
		recSizePrefix80[0]	= (byte) ((recSize80 >> 24) & 0xFF);
		recSizePrefix80[1]	= (byte) ((recSize80 >> 16) & 0xFF);
		recSizePrefix80[2]	= (byte) ((recSize80 >> 8) & 0xFF);
		recSizePrefix80[3]	= (byte) (recSize80 & 0xFF);
		recSize80			= 85;
		recSizePrefix85[0]	= (byte) ((recSize80 >> 24) & 0xFF);
		recSizePrefix85[1]	= (byte) ((recSize80 >> 16) & 0xFF);
		recSizePrefix85[2]	= (byte) ((recSize80 >> 8) & 0xFF);
		recSizePrefix85[3]	= (byte) (recSize80 & 0xFF);
		//
		// Get todays calendar date
		//
		java.util.GregorianCalendar now	= new java.util.GregorianCalendar();
		sn_yy	= now.get(Calendar.YEAR) + "";
		sn_mm	= (now.get(Calendar.MONTH) + 1) + "";
		if (sn_mm.length() == 1) {
			sn_mm	= '0' + sn_mm;
		}
		sn_dd		= now.get(Calendar.DATE) + "";
		if (sn_dd.length() == 1) {
			sn_dd	= '0' + sn_dd;
		}
		sn_date		= sn_yy + sn_mm + sn_dd;
		new_fmt1	= DateFormat.getInstance();
		new_fmt1	= new SimpleDateFormat("HHmm");
		newFmt2		= new SimpleDateFormat("yyyyMMddHHmmss");
		retTime		= new_fmt1.format(java.util.Calendar.getInstance().getTime());
		fileTime	= newFmt2.format(java.util.Calendar.getInstance().getTime());
		reportName	= "DepositICL_" + fileTime + ".rpt";
		try {
			ctlDetail.setDbUsed(dbUsed);
			//
			prodId			= "A";
			rowCount		= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail		= ctlSelector.getARow();
			applDate		= ctlDetail.getApplDate();
			procDate		= ctlDetail.getApplDate();
			frbFileName 	= ctlDetail.getInclImgFile();
			frbFileNamePre	= frbFileName.substring(0, frbFileName.indexOf("*"));
			frbFileNameSuf	= frbFileName.substring(frbFileName.indexOf("*") + 1);
			frbFileDir		= ctlDetail.getLocInputDir() + "frb/";
			imageDir		= (ctlDetail.getImgBaseDir() + procDate.substring(0, 4) +
								"/" + procDate.substring(4, 6) + "/" +
								procDate.substring(6, 8) + "/");
			reportDir		= ctlDetail.getLocOutputDir();
			reportDir		+= "incl/";
			//
			PrintLog("Starting FRB Check Return process on " + applDate + " " +
					 frbFileNamePre + "1" + frbFileNameSuf);
		} catch (Throwable t) {
			PrintLog("Exception getting control Row " + t);
		}
		if (reportHdg.length() < 94) {
			repPadSize	= 94 - reportHdg.length();
			if (repPadSize > 2) {
				repPadSize	= repPadSize / 2;
			}
		}
		repPad					= GenUtil.pad(repPad, repPadSize, " ");
		EcontReportUtil eRep	= new EcontReportUtil(reportDir + reportName);
		eRep.setPage_num_line(1);
		eRep.setPage_date_line(3);
		eRep.setAppl_date(applDate);
		temp	= ("REPORT NAME:RETURNCHEX.RPT" + repPad + reportHdg + repPad + "PAGE:");
		eRep.setHeadings(temp);
		temp	= ("                                                     " +
					"WEBFICHE INCLEARING SYSTEM");
		eRep.setHeadings(temp);
		temp	= ("                                         " +
					"FRB RETURN CHECK DATA REPORT AS OF ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" +
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		temp	= ("ACCOUNT NUMBER     CHECK ACCT NUM  CHECK NUM       CHECK" +
					" AMOUNT  PROC DATE   UNIQUE ISN    ABA          PAYE" +
					"E                   ");
		eRep.setHeadings(temp);
		temp	= ("--------------------------------------------------------" + 
					"--------------------------------------------------------" +
					"--------------------");
		eRep.setHeadings(temp);
		eRep.WriteHeadings();
		//
		PrintLog("Will read");
		//
		rowCount				= depsSelector.getDetail_count();
		DepsDetail depsDetail[]	= new DepsDetail[rowCount];
		//DepsDetail deps1Detail	= new DepsDetail();
		depsDetail				= depsSelector.getCheckrows();
		PrintLog("CheckDetail size " + depsDetail.length + " rowCount "	+ rowCount);
		retCount				= depsDetail.length;
		returnOutputFile		= (reportDir + "ReturnChex_" + fileTime + ".dat");
		PrintLog("ReturnOutputFile " + returnOutputFile);
		outWrite				= new FileOutputStream(returnOutputFile);
		try {
			ps = new PrintStream(outWrite);
		} catch (Exception e) {
			System.err.println("Error in writing to file");
		}
		Statement stmt			= dbConn.createStatement();
		temp					= "SELECT OUR_ABA FROM incl_control ";
		ResultSet rset			= stmt.executeQuery(temp.toString());
		while (rset.next()) {
			ourAba = "0" + rset.getString("OUR_ABA");
		}
		PrintLog("Will run query -- for "+ourAba);
		// Start the Cheque Processing
		temp	= "";
		String sql_str	= ("SELECT OUR_ABA, CHEX_ACCOUNT_NUM, CHEX_CHECK_NUM, " +
							"CHEX_ROUTING_TRANSIT, " +
							"CHEX_CHECK_AMOUNT, CHEX_UNIQUE_ISN, CHEX_ISSUE_DATE, CHEX_PAYEE, " +
							"CHEX_CHECK_STATUS " + "FROM incl_control, deps_chex " +
							"ORDER BY CHEX_ACCOUNT_NUM");
		// PrintLog("Will run query -- 4");
		rset	= stmt.executeQuery(sql_str);
		PrintLog("Will Process Deposits");
		while (rset.next()) {
			outRecs++;
			outRecs_s			= outRecs + "";
			maxLen				= 3;
			outRecs_s			= GenUtil.pad(outRecs_s, maxLen, "0");
			// Fetch the Columns here
			checkAmount		= rset.getString("CHEX_CHECK_AMOUNT").trim();
			checkAcctNum	= rset.getString("CHEX_ACCOUNT_NUM").trim();
			checkUniqueISN	= rset.getString("CHEX_UNIQUE_ISN").trim();
			checkUniqueISN	= zeroFill.substring(0,15 - checkUniqueISN.length()) +
													checkUniqueISN;
			checkDate		= rset.getString("CHEX_ISSUE_DATE");
			checkPayee		= rset.getString("CHEX_PAYEE");
			if (checkPayee.equals("POSIPAY NOT PROVIDED")) {
				checkPayee	= "NO POSIPAY PROVIDED";
			}
			checkNum			= rset.getString("CHEX_CHECK_NUM");
			checkStatus		= rset.getString("CHEX_CHECK_STATUS").trim();
		}

		temp					= tot_chex_25 + "";
		String tot_chex_25_s	= ("      ").substring(0, 6 - temp.length()) + temp;
		eRep.WriteHeadings();
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                         " +
						 "* * *  C h e c k  R e t u r n  S t a t i s t i c s  * * *");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       " +
						 "        TOTAL RETURNS      :  " + tot_chex_25_s);
		eRep.WriteDetail("                                                       " +
						 "        TOTAL OUTPUT RCORDS:  " + outRecs + "");
		eRep.WriteDetail("                                                       " +
						 "        TOTAL AMOUNT       :  " + tot_amt_25 + "");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail(" ");
		eRep.WriteDetail("                                                       " +
						 "     *** END OF REPORT ***");
		eRep.Close();
		outWrite.close();
		try {
			cp_cmd[2]	= "/bin/cp " + returnOutputFile + " " + imageDir;
			PrintLog("Will Copy Return Data" + cp_cmd[0] + " " + cp_cmd[1] + " " + cp_cmd[2]);
			Runtime.getRuntime().exec(cp_cmd);
		} catch (Throwable t) {
			t.printStackTrace();
			// PrintLogt(" ");
		}
		FileOutput flagFile	= new FileOutput(reportDir + "ReturnChex_" + fileTime + ".ack");
		flagFile.close();
		return (1);
	}
	//
	// Method to create Record Type 01
	//
	public void chexRecType_0101() throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType0101: ";
		//
		// PrintLog("in chexRecType_0101="+one_rec);
		//
		PrintLog(temp);
		newText				= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName = calledFrom;
	}
	//
	// Method to process Record Type 10
	//
	public void chexRecType_1001(String one_rec) throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_1001: ";
		temp		= (one_rec.substring(0, 2) + "03" + one_rec.substring(13, 22) +
						one_rec.substring(4, 13) + one_rec.substring(22, 30) +
						sn_date + retTime + "I" + one_rec.substring(43, 52) + "P&R" + 
						"                     C   ");
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName = calledFrom;
	}
	//
	// Method to process Record Type 20
	//
	public void chexRecType_2001(String one_rec) throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_2001: ";
		temp		= (one_rec.substring(0, 2) + "03" + one_rec.substring(13, 22) +
						one_rec.substring(4, 13) + one_rec.substring(22, 30) +
						sn_date + one_rec.substring(38, 54) + "                          ");
		PrintLog(temp);
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		newText	= temp.getBytes("Cp037");
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName = calledFrom;
	}
	//
	// Method to process Record Type 25
	//
	public void chexRecType_25(EcontReportUtil eRep, String one_rec,
								DepsDetail deps1Detail) throws IOException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_25: ";
		String unique_isn_25= "";
		String ourABA		= "";
		String temp			= "";
		try {
			unique_isn_25	= one_rec.substring(57, 72).trim();
			if (unique_isn_25.compareTo(chex_unique_isn) == 0) {
				//
				// Here write the Record Type 31 to the return file
				//
				ourABA		= "";
				ourABA		= one_rec.substring(18, 27);
				PrintLog(temp+ourABA);
				newText		= temp.getBytes("Cp037");
				outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
				for (int i = 0; i < newText.length; i++) {
					// PrintLog((char)(((int)newText[i])&0xFF));
					ps.print((char) (((int) newText[i]) & 0xFF));
				}
				outRecs++;
				baos.reset();
			} else {
				return;
			}
			temp	= "";
			temp	= one_rec.substring(27, 47).trim();
			if (temp.length() == 0) {
				//account_num_25		= "0000000000000000";
			} else {
				for (int indx = 0; indx < temp.length(); indx++) {
					if (temp.substring(indx, indx + 1).equals("/")) {
						//
					} else {
						//account_num_25	= account_num_25 + temp.substring(indx, indx + 1);
					}
				}
			}
			tot_chex_25++;
			payee			= deps1Detail.getChex_payee();
			iss_date		= deps1Detail.getChex_issue_date_disp();
			sw_ref			= deps1Detail.getChex_extra_1();
			eRep.WriteDetail(temp);
			eRep.WriteDetail(" ");
		} catch (Exception e) {
			PrintLog("IO-Ecxception " + e);
		}
		moduleName = calledFrom;
	}
	//
	// Check Detail Addendum A Record type 26
	//
	public void chexRecType_2601(String one_rec) throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_2601: ";
		if (one_rec.substring(0, 2).equals("26")) {
			temp	= ("32" + one_rec.substring(2, 80));
		} else {
			//temp	= ("321" + chexBOFD + chexBOFDDate +
			//			"                                                     " + "N" + "      ");
		}
		PrintLog("" + temp);
		newText	= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Check Detail Addendum C Record type 28
	//
	public void chexRecType_2801(String one_rec) throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_2801: ";
		temp		= ("35" + one_rec.substring(2, 80));
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Check Detail Addendum C Record type 28 (Creates Rec Type 35)
	//
	public void chexRecType_2801_1() throws IOException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_2801: ";
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Check Detail Addendum C Record type 28 (Creates Rec Type 35)
	//
	public void chexRecType_33() throws IOException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_2801: ";
		newText				= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName			= calledFrom;
	}
	//
	// Bundle Control record type 50
	//
	public void chexRecType_50(String one_rec) throws IOException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_50: ";
		temp				= (one_rec.substring(0, 34) + "0                                             ");
		PrintLog(temp);
		newText				= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 52
	//
	public void chexRecType_52() throws IOException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType_52: ";
		newText			= temp.getBytes("Cp037");
		outWrite.write(recSizePrefixOther, 0, recSizePrefixOther.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		newText		= imageRec.getBytes();
		outWrite.write(newText, 0, newText.length);
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 54
	//
	public void chexRecType_54() throws IOException {
		calledFrom	= moduleName;
		moduleName	= "chexRecType_54: ";
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 70
	//
	public void chexRecType_70() throws IOException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType_70: ";
		// Check Count
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Record type 90 - Cash Letter Control Record
	//
	public void chexRecType_90() throws IOException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType_90: ";
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Check record type 99 - File Control Record
	//
	public void chexRecType_99() throws IOException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType99: ";
		temp			= ("P&R           2018506503                ");
		PrintLog(temp);
		newText			= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		baos.reset();
		moduleName	= calledFrom;
	}
}
