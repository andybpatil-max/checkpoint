package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.text.*;
import java.util.*;

//
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.service.*;

@Service
public final class InclReturnChexUtil {
	// Process Statistics Counters
	String			className		= "> InclReturnChexUtil.";
	String			moduleName;
	String			calledFrom;
	static TreeMap<String, String>retReasons	= new TreeMap<String, String>();
	static String	dbUsed			= "";
	static String	dbTable			= "";
	static String	procDate		= "";
	static String	retTime			= "";
	static String	fileTime		= "";
	static String	appl_date		= "";
	static String	temp			= "";
	static String	tempNum			= "";
	static String	numStr			= "";
	static String	outDir			= "";
	static String	ourABA			= "";
	static String	retReason		= "";
	static String	prevType		= "";
	static String	chexBOFD		= "";
	static String	chexBOFDDate	= "";
	static int		outRecs			= 0;
	static int		addendumCount	= 0;
	static int		addendumSeq		= 0;
	static String	addendumCount_s	= "";
	static double	tot_amt_25		= 0.0;
	static int		tot_chex_25		= 0;
	static int		row_id			= 1;
	static int		rowCount		= 0;
	static int		retCount		= 0;
	static int		startImage		= 0;
	static int		startJpeg		= 0;
	static int		fileNum			= 0;
	static int		readBytes		= 0;
	static int		imageLen		= 0;
	static int		retResult		= 0;
	static String	file_to_load	= "";
	static String	recType			= "";
	static String	modFunc			= "GenReturn";
	static boolean	retCheck		= false;
	static String	reportHdg		= null;
	static String	repPad			= "";
	static int		repPadSize		= 0;
	static String	imageDir		= "";
	String			prodId			= "";
	//
	// File Header Record 01 Fields
	//
	static String	rec_type_01;							// rec_type_code_101;
	static String	std_level_01;							// prio_code_101;
	static String	file_type_01;							// P=prod
															// T=test
	static String	dest_aba_01;							// receiving_aba_101;
	static String	orig_aba_01;							// origination_aba_101;
	static String	process_date_01;						// process_date_101;
	static String	dest_inst_name_01;						// dest_inst_name_101;
	static String	orig_inst_name_01;						//
	static String	file_ID_modifier_01;					// 0 thru 9
	//
	// Cash Letter (cl) Header Record 1001 Fields
	//
	static String	rec_type_10;							// rec_type_code_5290;
	static String	dest_aba_10;							// orig_inst_name_5290;
	static String	orig_aba_10;							// origination_aba_5290;
	static String	cl_biz_date_10;							// process_date_5290;
	static String	cl_cre_date_10;							//
	static String	cl_cre_time_10;							//
	//
	// Bundle Header Record 20 fields
	//
	static String	rec_type_20;							// record_type_693;
	static String	dest_aba_20;							// bundle_num_693;
	static String	orig_aba_20;							// item_count_693;
	static String	biz_date_20;							// bundle_amount_693;
	static String	cre_date_20;
	static String	bundle_id_20;
	static String	bundle_seq_20;
	static String	cycleNumber_20;
	//
	// Check Detail Record 25 Fields
	//
	static String	rec_type_25;
	static String	check_num_25;
	static String	ext_proc_control_25;
	static String	routing_transit_25;
	// static String check_digit_25;
	static String	account_num_25;
	static String	check_amount_25;
	static String	unique_isn_25;
	static String	ret_accept_ind_25;
	// Next field stored in chex_ext_proc_control
	static String	micr_valid_25;
	// following made up of
	// 1 Document type Indicator
	// 2 Return Acceptance Indicator
	// 3 MICR Valid code
	// 4 Bank of First Deposit (BFD) indicator
	// 5 Check Detail Record Addendum Count
	// 6 On Us Format
	// Keep in Image locator column
	//
	static String	misc_codes_25;
	static String	process_date_25;
	//
	// Check Detail Addendum C Record type 28
	//
	static String	rec_type_28;
	static String	bofd_aba_28;
	static String	bofd_date_28;
	static String	truncInd_28;
	//
	// Image Records
	//
	static int		imageRec_seq;
	static String	imageRec;
	static String	jpegFile;
	static boolean	jpegCreated;
	//
	// Bundle Control Record 70 Fields
	//
	static String	rec_type_70;							// record_type_8290;
	static String	bundle_count_70;						// srv_class_code_8290;
	static String	bundle_tot_amt_70;						// det_item_count_8290;
	static String	micr_val_totamt_70;						// tot_amount_8290
	//
	// Cash Letter Control Record 90 Fields
	//
	static String	rec_type_90;							// record_type_8290;
	static String	bundle_count_90;						// srv_class_code_8290;
	static String	cl_count_90;							// srv_class_code_8290;
	static String	cl_tot_amt_90;							// det_item_count_8290;
	static String	dest_name_90;
	static String	orig_name_90;
	static String	settle_date_90;
	//
	// Record 99 Fields
	//
	static String	rec_type_99;							// record_type_9;
	static String	cl_count_99;							// batch_count_9;
	static String	tot_recs_99;							// detail_count_9;
	static String	tot_items_99;
	static String	file_tot_amt_99;						// total_amount_9;
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
	static String	iclFileDir;
	static String	frbFileDir;
	static String	fileToProcess;
	static String	file2Process;
	static String	reportDir;
	static String	sn_mm			= "";
	static String	sn_dd			= "";
	static String	sn_yy			= "";
	static String	sn_date			= "";
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
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ControlSelector	ctlSelector		= new ControlSelector();
	ControlDetail	ctlDetail		= new ControlDetail();
	InclChexUtil	chUtil			= new InclChexUtil();
	GenUtil			gUtil			= new GenUtil();
	FileOutputStream	outWrite	= null;
	ByteArrayOutputStream baos		= new ByteArrayOutputStream();
	String			returnOutputFile= "";
	// declare a print stream object
	PrintStream		ps				 = null;

	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ className + moduleName + logMsg);
	}
	//
	public int ChexReturn(Connection dbConn, String dbUsed_1,
			ChexSelector chexSelector, String userId) throws SQLException,
			Exception {
		EcontServletContextListener escl	= new EcontServletContextListener();
		reportHdg			= escl.getReportHdg();
		moduleName			= "ChexReturn: ";
		String temp			= "";
		String reportName	= "";
		tot_amt_25			= 0.0;
		tot_chex_25			= 0;
		outRecs				= 0;
		// row_id = cycleNum*10;
		dbUsed				= dbUsed_1;
		// appl_date = appl_date_1;
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
		dbTable				= "returnreasons";
		retReasons			= chUtil.GetReturnReasons(dbConn, dbTable);
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
		reportName	= "ReturnChex_" + fileTime + ".rpt";
		try {
			ctlDetail.setDbUsed(dbUsed);
			//
			prodId			= "A";
			rowCount		= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail		= ctlSelector.getARow();
			appl_date		= ctlDetail.getApplDate();
			procDate		= ctlDetail.getApplDate();
			frbFileDir 		= ctlDetail.getImgBaseDir() + "incl/";
			frbFileName 	= ctlDetail.getInclImgFile();
			frbFileNamePre	= frbFileName.substring(0, frbFileName.indexOf("*"));
			//frbFileNameSuf	= frbFileName.substring(frbFileName.indexOf("*") + 1);
			frbFileNameSuf	= frbFileName.substring(frbFileName.indexOf("*") + 1);
			imageDir		= (ctlDetail.getImgBaseDir() + procDate.substring(0, 4) +
								"/" + procDate.substring(4, 6) + "/" +
								procDate.substring(6, 8) + "/");
			reportDir		= ctlDetail.getLocOutputDir();
			reportDir		+= "incl/";
			//
			//PrintLog("Starting FRB Check Return process on " + appl_date + " " +
			//		 frbFileNamePre + "" + frbFileNameSuf);
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
		eRep.setAppl_date(appl_date);
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
		rowCount				= chexSelector.getDetail_count();
		ChexDetail chexDetail[]	= new ChexDetail[rowCount];
		ChexDetail chex1Detail	= new ChexDetail();
		chexDetail				= chexSelector.getCheckrows();
		PrintLog("CheckDetail size " + chexDetail.length + " rowCount "	+ rowCount);
		retCount				= chexDetail.length;
		returnOutputFile		= (reportDir + "ReturnChex_" + fileTime + ".dat");
		PrintLog("ReturnOutputFile " + returnOutputFile);
		outWrite				= new FileOutputStream(returnOutputFile);
		try {
			ps = new PrintStream(outWrite);
		} catch (Exception e) {
			System.err.println("Error in writing to file");
		}
		//
		// Loop through for each check to be returned and determine the number
		// of Addendum Records
		//
		String[] fileList;
		for (int idx = 0; idx < chexDetail.length; idx++) {
			PrintLog("In ProcessLoop1 pass " + idx);
			chex1Detail		= chexDetail[idx];
			chex1Detail.setChex_mod_func(modFunc);
			chex1Detail.setChex_mod_user(userId);
			chex_unique_isn	= chex1Detail.getChex_unique_isn();
			retReason		= chex1Detail.getChex_return_reason();
			PrintLog("Return Reason1 " + retReason);
			chexBOFD		= chex1Detail.getChex_BOFD_aba();
			chexBOFDDate	= chex1Detail.getChex_BOFD_date();
			retCheckDate	= chex1Detail.getChex_proc_date();
			iclFileDir		= frbFileDir + retCheckDate.substring(0, 4) +
								"/" + retCheckDate.substring(4, 6) + "/" +
								retCheckDate.substring(6, 8) + "/";
			fileToProcess	= (iclFileDir + frbFileNamePre + "D" + 
								retCheckDate.substring(2, 8) + ".T*.dat");
			fileList		= FileUtil.GetFNamesWC(iclFileDir, 
													frbFileNamePre +
													retCheckDate.substring(2,8)+".T*",".dat");
			for (fileNum = 0; fileNum < fileList.length; fileNum++) {
				file2Process	= iclFileDir+fileList[fileNum];
				PrintLog("File to Process " + fileNum + " > " + file2Process);
				//findFile		= new File(fileList[fileNum]);
				//if (!findFile.exists()) {
				//	continue;
				//}
				String inRec	= "";
				String one_rec	= "";
				PrintLog("Will Process File > " + file2Process);
				readBytes		= 80;
				try {
					BufferedReader inRead	= new BufferedReader(new FileReader(file2Process));
					inRec			= "";
					one_rec			= "";
					addendumCount	= 0;
					while ((inRec = inRead.readLine()) != null) {
						while (inRec.length() > 0) {
							one_rec	= inRec;
							if (one_rec.substring(0, 2).equals("II")) {
								prevType		= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 4).equals("0103")) {
								prevType		= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 4).equals("1001")) {
								prevType		= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 4).equals("2001")) {
								prevType		= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 2).equals("25")) {
								retCheck		= false;
								unique_isn_25	= one_rec.substring(57, 72).trim();
								if (unique_isn_25.compareTo(chex_unique_isn) == 0) {
									retCheck	= true;
									check_num_25= one_rec.substring(2, 17).trim();
								}
							} else if (one_rec.substring(0, 2).equals("26")) {
								if (retCheck) {
									// count one 26
									addendumCount++;
									PrintLog("Record Type (1): " + one_rec.substring(0, 2) +
											 " Addendum Count: " + addendumCount);
									prevType	= one_rec.substring(0, 2);
								}
							} else if (one_rec.substring(0, 2).equals("28")) {
								// PrintLog("Prev TYpe: "+prevType);
								if (retCheck) {
									PrintLog("Prev TYpe: " + prevType + " check_num_25 " + check_num_25);
									if (prevType.equals("26")) {
										if (check_num_25.equals("")) {
										} else {
											// Need to add a Record Type 33
											addendumCount++;
											PrintLog("Record Type (2): " + one_rec.substring(0, 2) +
													 " Addendum Count: " + addendumCount);
											prevType	= one_rec.substring(0, 2);
										}
									} else if (prevType.equals("28")) {
										//
									} else {
										// count one each 26
										// addendumCount++;
										// PrintLog("Record Type (3): "+one_rec.substring(0,2)+
										// " Addendum Count: "+addendumCount);
										if (check_num_25.equals("")) {
										} else {
											// count one each 26
											addendumCount++;
											PrintLog("Record Type (3): " + one_rec.substring(0, 2) +
													 " Addendum Count: " + addendumCount);
											// add a Record Type 33
											addendumCount++;
											PrintLog("Record Type (4): " + one_rec.substring(0, 2) +
													 " Addendum Count: " + addendumCount);
										}
									}
									// count one 35
									addendumCount++;
									PrintLog("Record Type (5): " + one_rec.substring(0, 2) +
											 " Addendum Count: " + addendumCount);
									prevType	= one_rec.substring(0, 2);
								}
							} else if (one_rec.substring(0, 2).equals("50")) {
								// imageLen =
								// Integer.parseInt(one_rec.substring(24,31));
								if (retCheck) {
									if (prevType.equals("54")) {
										//
									} else {
										// count one 35
										addendumCount++;
										PrintLog("Record Type (6): " + one_rec.substring(0, 2) +
												 " Addendum Count: " + addendumCount);
									}
									// chexRecType_50(one_rec);
									prevType	= one_rec.substring(0, 2);
									PrintLog("Pass " + idx + " Addendum count : " + addendumCount);
									PrintLog("Pass " + idx + " File2Process : " + file2Process);
									chexDetail[idx].setAddendumCount(addendumCount);
									chexDetail[idx].setChex_fileName(file2Process);
								}
							} else if (one_rec.substring(0, 2).equals("52")) {
								prevType	= one_rec.substring(0, 2);
								imageLen	= Integer.parseInt(one_rec.substring(130, 137));
								inRead.skip(imageLen);
							} else if (one_rec.substring(0, 2).equals("54")) {
								prevType	= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 2).equals("70")) {
								prevType	= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 2).equals("90")) {
								prevType	= one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 2).equals("99")) {
								prevType	= one_rec.substring(0, 2);
							}
							inRec = "";
							// baos.reset();
						}
					}
					inRead.close();
				} finally {
					try {
						//
					} catch (Exception e) {
						PrintLog("Exception in runtime : " + e);
					}
				}
			}
		}
		//
		// Loop through for each check to be returned generating the required
		// output
		//
		for (int idx = 0; idx < chexDetail.length; idx++) {
			PrintLog("In ProcessLoop2 pass " + idx);
			chex1Detail		= chexDetail[idx];
			chex1Detail.setChex_mod_func(modFunc);
			chex1Detail.setChex_mod_user(userId);
			addendumCount	= chex1Detail.getAddendumCount();
			chex_unique_isn	= chex1Detail.getChex_unique_isn();
			retReason		= chex1Detail.getChex_return_reason();
			PrintLog("Return Reason2 " + retReason);
			chexBOFD		= chex1Detail.getChex_BOFD_aba();
			retCheckDate	= chex1Detail.getChex_proc_date();
			file2Process	= chex1Detail.getChex_fileName();
			int start		= 0;
			String inRec	= "";
			String one_rec	= "";
			PrintLog("File to Process > " + file2Process);
			readBytes		= 80;
			try {
				BufferedReader inRead	= new BufferedReader(new FileReader(file2Process));
				start					= 0;
				inRec					= "";
				one_rec					= "";
				while ((inRec = inRead.readLine()) != null) {
					// PrintLog("inRec 1  "+inRec.length());
					while (inRec.length() > 0) {
						// PrintLog("Record Type:2 "+inRec.substring(0,2));
						one_rec	= inRec;
						// inRec = inRec.substring(80,inRec.length());
						//PrintLog("One Rec: "+one_rec);
						if ((one_rec.length() > 2) && one_rec.substring(0, 2).equals("II")) {
							//
						} else if ((one_rec.length() > 4) && (one_rec.substring(0, 4).equals("0103"))) {
							if (idx == 0) {
								chexRecType_0101(one_rec);
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 4) && (one_rec.substring(0, 4).equals("1001"))) {
							if (idx == 0) {
								if (written10 == false) {
									chexRecType_1001(one_rec);
									written10	= true;
								}
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 4) && (one_rec.substring(0, 4).equals("2001"))) {
							if (idx == 0) {
								if (written20 == false) {
									cycleNumber_20	= one_rec.substring(52, 54);
									chexRecType_2001(one_rec);
									written20		= true;
								}
								prevType = one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("25"))) {
							retCheck		= false;
							imageRec_seq	= 1;
							chexRecType_25(eRep, one_rec, chex1Detail);
							prevType		= one_rec.substring(0, 2);
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("26"))) {
							if (retCheck) {
								PrintLog("Record Type (1): " + one_rec.substring(0, 2) +
										 " Will Add Addendum Record: ");
								chexRecType_2601(one_rec);
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("28"))) {
							// PrintLog("Prev TYpe: "+prevType);
							if (retCheck) {
								PrintLog("Prev TYpe: " + prevType + " check_num_25 " + check_num_25);
								if (prevType.equals("26")) {
									if (check_num_25.equals("0")) {
									} else {
										chexRecType_33();
										PrintLog("Record Type (2): " + one_rec.substring(0, 2) +
												 " Will Add Addendum Record: ");
									}
								} else if (prevType.equals("28")) {
									//
								} else {
									// PrintLog("Record Type (3): "+one_rec.substring(0,2)+
									// " Will Add Addendum Record: ");
									// chexRecType_2601(one_rec);
									if (check_num_25.equals("")) {
									} else {
										PrintLog("Record Type (3): " + one_rec.substring(0, 2) +
												 " Will Add Addendum Record: ");
										chexRecType_2601(one_rec);
										// chexRecType_2601(one_rec);
										PrintLog("Record Type (4): " + one_rec.substring(0, 2) +
												 " Will Add Addendum Record: ");
										chexRecType_33();
									}
								}
								PrintLog("Record Type (5): " + one_rec.substring(0, 2) +
										 " Will Add Addendum Record: ");
								chexRecType_2801(one_rec);
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("50"))) {
							//PrintLog(one_rec.substring(0,one_rec.length()));
							//imageLen	= Integer.parseInt(one_rec.substring(24,31));
							if (retCheck) {
								//
								// Here create the last Rec Type 35 record
								//
								if (prevType.equals("54")) {
									//
								} else {
									PrintLog("Record Type (6): " + one_rec.substring(0, 2) +
											 " Will Add Addendum Record: ");
									chexRecType_2801_1();
								}
								//
								chexRecType_50(one_rec);
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("52"))) {
							imageLen	= Integer.parseInt(one_rec.substring(130,137));
							if (retCheck) {
								if (idx == retCount - 1) {
									written52	= true;
								}
								imageRec	= "";
								for (int charCount = 0; charCount < imageLen; charCount++) {
									imageRec	= imageRec + (char) inRead.read();
								}
								chexRecType_52(one_rec);
								prevType	= one_rec.substring(0, 2);
							} else {
								inRead.skip(imageLen);
							}
							prevType	= one_rec.substring(0, 2);
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("54"))) {
							if (retCheck) {
								// chexRecType_54(one_rec);
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("70"))) {
							// PrintLog("IDX : "+idx+"  retCount: "+retCount);
							if (idx == retCount - 1) {
								if (written52 == true) {
									if (written70 == false) {
										chexRecType_70();
										written70	= true;
									}
								}
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("90"))) {
							if (idx == retCount - 1) {
								if (written52 == true) {
									if (written90 == false) {
										chexRecType_90();
										written90	= true;
									}
								}
								prevType	= one_rec.substring(0, 2);
							}
						} else if ((one_rec.length() > 2) && (one_rec.substring(0, 2).equals("99"))) {
							if (written52 == true) {
								if (idx == retCount - 1) {
									chexRecType_99();
									prevType	= one_rec.substring(0, 2);
								}
							}
						}
						inRec	= "";
						start++;
						// baos.reset();
					}
				}
				PrintLog("Records read  " + start);
				inRead.close();
				retResult	= chUtil.ReturnChex(dbConn, chex1Detail, userId, modFunc);
			} finally {
				try {
					// bis.close();
				} catch (Exception e) {
					PrintLog("Exception in runtime : " + e);
				}
			}
			// }
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
	// Method to process Record Type 01
	//
	public void chexRecType_0101(String one_rec) throws IOException {
		calledFrom = moduleName;
		moduleName = "chexRecType0101: ";
		//
		// PrintLog("in chexRecType_0101="+one_rec);
		//
		dest_inst_name_01 = one_rec.substring(36, 54);
		temp = (one_rec.substring(0, 4)	+ "P" +
				one_rec.substring(14, 23) + one_rec.substring(5, 14) + sn_date +
				retTime + "N" + one_rec.substring(54, 72) +
				one_rec.substring(36, 54) + "        ");
		PrintLog(temp);
		newText = temp.getBytes("Cp037");
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
								ChexDetail chex1Detail) throws IOException {
		calledFrom			= moduleName;
		moduleName			= "chexRecType_25: ";
		String temp			= "";
		String bank_acct	= "";
		String zerosStr		= "000000000000000";
		// AccountSelector acctSelector = new AccountSelector();
		// ChexDetail chexDetail = new ChexDetail();
		//
		addendumCount_s		= addendumCount + "";
		addendumCount_s		= zerosStr.substring(0, 2 - addendumCount_s.length()) + addendumCount_s;
		try {
			//PrintLog("Unique_ISN_25: "+unique_isn_25+" chex_unique_isn: " + chex_unique_isn);
			unique_isn_25	= one_rec.substring(57, 72).trim();
			if (unique_isn_25.compareTo(chex_unique_isn) == 0) {
				retCheck	= true;
				//
				// Here write the Record Type 31 to the return file
				//
				ourABA		= "";
				ourABA		= one_rec.substring(18, 27);
				temp		= ("31" + one_rec.substring(18, 57) +
								retReason.substring(0, 1) + addendumCount_s +
								one_rec.substring(73, 73) + "                                    ");
				PrintLog(temp);
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
			bank_acct		= chex1Detail.getChex_account_num();
			tempNum			= "";
			check_num_25	= "";
			check_num_25	= one_rec.substring(2, 17).trim();
			PrintLog("in chexRecType_25=" + check_num_25);
			if (check_num_25.equals("")) {
				check_num_25	= "0";
			} else {
				for (int n = 0; n < check_num_25.length(); n++) {
					if (gUtil.isNumeric(check_num_25.substring(n, n + 1)) == true) {
						tempNum	+= check_num_25.substring(n, n + 1);
					}
				}
				if (temp.equals("")) {
					check_num_25	= "0";
				} else {
					check_num_25	= tempNum;
				}
			}
			temp			= "";
			account_num_25	= "";
			// check_num_690 = Integer.parseInt(one_rec.substring(39,
			// 54).trim())+"";
			ext_proc_control_25		= one_rec.substring(17, 18);
			routing_transit_25		= one_rec.substring(18, 27).trim();
			// check_digit_25 = one_rec.substring(26, 27).trim();
			temp					= one_rec.substring(27, 47).trim();
			if (temp.length() == 0) {
				account_num_25		= "0000000000000000";
			} else {
				for (int indx = 0; indx < temp.length(); indx++) {
					if (temp.substring(indx, indx + 1).equals("/")) {
						//
					} else {
						account_num_25	= account_num_25 + temp.substring(indx, indx + 1);
					}
				}
			}
			PrintLog("in chexRecType_25 account_num_25="+account_num_25);
			check_amount_25	= (Double.parseDouble(one_rec.substring(47, 57)) / 100)	+ "";
			// PrintLog("in chexRecType_25 UniqueISN="+unique_isn_25);
			micr_valid_25	= one_rec.substring(74, 75).trim();
			misc_codes_25	= one_rec.substring(72, 78).trim();
			tot_amt_25		= tot_amt_25 + (Double.parseDouble(check_amount_25));
			tot_chex_25++;
			payee			= chex1Detail.getChex_payee();
			iss_date		= chex1Detail.getChex_issue_date_disp();
			sw_ref			= chex1Detail.getChex_extra_1();
			String temp_amt	= chex1Detail.getChex_check_amount_disp();
			String temp_date	= chex1Detail.getChex_proc_date_disp();
			temp			= ((bank_acct + "                   ").substring(0, 19) +
								(account_num_25 + "                 ").substring(0, 16) +
								(check_num_25 + "               ").substring(0, 15) +
								("             ").substring(0, 13 - temp_amt.length()) +
								temp_amt + "  " + temp_date + "  " +
								unique_isn_25.substring(0) + "    " +
								routing_transit_25 + "    " + payee);
			eRep.WriteDetail(temp);
			temp			= "";
			temp			= "Return Reason: "+retReasons.get(retReason);
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
			temp	= ("321" + chexBOFD + chexBOFDDate +
						"                                                     " + "N" + "      ");
		}
		PrintLog("Record Type (1): " + one_rec.substring(0, 2));
		PrintLog("" + temp);
		newText	= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
		addendumSeq	= Integer.parseInt(one_rec.substring(2, 4));
		temp		= ("35" + one_rec.substring(2, 80));
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
		String zerosStr		= "00";
		String spaceFill	= "               ";
		String tempISN		= "";
		addendumSeq++;
		addendumCount_s		= addendumSeq + "";
		addendumCount_s		= zerosStr.substring(0, 2 - addendumCount_s.length()) + addendumCount_s;
		tempISN				= chex_unique_isn + spaceFill.substring(0, 15 - chex_unique_isn.length());
		temp				= ("35" + addendumCount_s + ourABA + retCheckDate + tempISN + "N" + 
								"                                           ");
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
		String zerosStr		= "00";
		String spaceFill	= "               ";
		String tempISN		= "";
		String tempCkn		= "";
		addendumCount_s		= addendumSeq + "";
		addendumCount_s		= zerosStr.substring(0, 2 - addendumCount_s.length()) + addendumCount_s;
		tempISN				= chex_unique_isn + spaceFill.substring(0, 15 - chex_unique_isn.length());
		tempCkn				= check_num_25 + spaceFill.substring(0, 15 - check_num_25.length());
		temp				= ("33" + dest_inst_name_01 + tempCkn + tempISN + appl_date +
								"                      ");
		PrintLog(temp);
		newText				= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
			// PrintLog((char)(((int)newText[i])&0xFF));
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		outRecs++;
		baos.reset();
		moduleName	= calledFrom;
	}
	//
	// Bundle Control record type 52
	//
	public void chexRecType_52(String one_rec) throws IOException {
		calledFrom		= moduleName;
		moduleName		= "chexRecType_52: ";
		String sFill	= "               ";
		baos.write(recSizePrefixOther);
		recSizeOthers	= baos.toString();
		baos.reset();
		temp			 = (one_rec.substring(0, 2) + ourABA + one_rec.substring(11, 19) +
							cycleNumber_20 + unique_isn_25 +
							sFill.substring(0, 15 - unique_isn_25.length()) +
							"                                                " +
							one_rec.substring(84, 85) + "                " +
							one_rec.substring(101));
		PrintLog(temp);
		imageLen		= imageLen + temp.length();
		recSizePrefixOther[0]	= (byte) ((imageLen >> 24) & 0xFF);
		recSizePrefixOther[1]	= (byte) ((imageLen >> 16) & 0xFF);
		recSizePrefixOther[2]	= (byte) ((imageLen >> 8) & 0xFF);
		recSizePrefixOther[3]	= (byte) (imageLen & 0xFF);
		newText					= temp.getBytes("Cp037");
		outWrite.write(recSizePrefixOther, 0, recSizePrefixOther.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
		String zFill	= "000000000000";
		// Check Count
		numStr			= retCount + "";
		temp			= ("70" + zFill.substring(0, (4 - numStr.length())) + numStr);
		// Check Amount
		// numStr = tot_amt_25 + "";
		numStr			= df.format(tot_amt_25);
		if (numStr.length() - numStr.indexOf(".") == 2) {
			numStr		= numStr + "0";
		}
		numStr		= numStr.substring(0, numStr.indexOf(".")) +
					  numStr.substring(numStr.indexOf(".") + 1);
		temp		= (temp + zFill.substring(0, (12 - numStr.length())) + numStr + "            ");
		// Check Count
		numStr		= (retCount * 2) + "";
		temp		= (temp + zFill.substring(0, (5 - numStr.length())) + numStr + 
					   "                                             ");
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
		String zFill	= "00000000000000";
		// Debit/Credit Item Count
		numStr			= retCount + "";
		temp			= ("90000001" + zFill.substring(0, (8 - numStr.length())) + numStr);
		// Check Amount
		// PrintLog("Rec Len: T90 >"+temp+"<");
		numStr			= "";
		// numStr = tot_amt_25 + "";
		numStr			= df.format(tot_amt_25);
		if (numStr.length() - numStr.indexOf(".") == 2) {
			numStr		= numStr + "0";
		}
		numStr		= numStr.substring(0, numStr.indexOf(".")) + numStr.substring(numStr.indexOf(".") + 1);
		temp		= temp + zFill.substring(0, (14 - numStr.length())) + numStr;
		// Image view record Count
		// PrintLog("Rec Len: T90 >"+temp+"<");
		numStr		= (retCount * 2) + "";
		temp		= (temp + zFill.substring(0, (9 - numStr.length())) + numStr +
					   dest_inst_name_01.substring(0, 18) + "                       ");
		// PrintLog("Rec Len: T90 "+temp.length());
		PrintLog(temp);
		newText		= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
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
		String zFill	= "0000000000000000";
		// Total Record Count
		outRecs++;
		numStr			= outRecs + "";
		temp			= ("99000001" + zFill.substring(0, (8 - numStr.length())) + numStr);
		// Total Item Count
		numStr			= retCount + "";
		temp			= temp + zFill.substring(0, (8 - numStr.length())) + numStr;
		// File Total Amount
		// numStr = tot_amt_25 + "";
		numStr			= df.format(tot_amt_25);
		if (numStr.length() - numStr.indexOf(".") == 2) {
			numStr		= numStr + "0";
		}
		numStr			= numStr.substring(0, numStr.indexOf(".")) +
						  numStr.substring(numStr.indexOf(".") + 1);
		temp			= (temp + zFill.substring(0, (16 - numStr.length())) + numStr + 
						   "P&R           2018506503                ");
		PrintLog(temp);
		newText			= temp.getBytes("Cp037");
		outWrite.write(recSizePrefix80, 0, recSizePrefix80.length);
		for (int i = 0; i < newText.length; i++) {
			// PrintLog((char)(((int)newText[i])&0xFF));
			ps.print((char) (((int) newText[i]) & 0xFF));
		}
		baos.reset();
		moduleName	= calledFrom;
	}
}
