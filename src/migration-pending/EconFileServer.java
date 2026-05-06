package com.webfiche.checkpoint.actions;

import java.util.*;
import java.util.zip.*;
import java.sql.*;
import java.io.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;

public class EconFileServer extends Thread {
	String	className			= "> EconFileServer.";
	String	moduleName;
	boolean	inclCodPerformed	= false;
	boolean	inclFileExists		= true;
	boolean	inclFileCopied		= false;
	//
	int		inclWaitResult		= 0;
	int		inclCtlCount		= 1;
	int		inclXmlCount		= 1;
	int		inclImgCount		= 1;
	//
	String[] inclCpCmd			= { "/bin/sh", "-c", "/bin/cp " };
	String[] inclGzCmd			= { "/bin/sh", "-c", "/bin/gzip -df " };
	//
	String	inclBankId			= null;
	String	inclFileName		= "micrimage";
	String	inclCycleNum		= "";
	//
	SysadControlUtil ctlUtil			= new SysadControlUtil();
	ExtractNYImages  extractNy			= new ExtractNYImages();
	ExtractMOImages  extractMo			= new ExtractMOImages();
	FileUtil		 fUtil				= new FileUtil();
	BufferedReader   frb_br1			= null;
	BufferedReader   fileTest			= null;
	Process	copyFileProc;
	Process	proc_ls;
	Process	remoteDirProc;
	Process	unzipFileProc;
	Process	uZproc;
	Process	mvProc;
	File	findFile		  	= null;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public EconFileServer(String FRBFiles_str) {
		super(FRBFiles_str);
	}
	//
	public void stopIFRBF() {
		inclCodPerformed	= true;
	}
	//
	public void wakeIFRBF() {
		//
		// Here to just wake up the thread so that it will initiate
		// the harvest process.
		//
	}
	//
	public void GetFiles(String applDateAtStart, String copyFrom, String localDir, String localFile,
							String locInputDir, String todaysDir) {
		moduleName	= "GetFiles: ";
		inclFileCopied	= false;
		String remoteFile	= "";
		// file_found	= false;
		inclCpCmd[2]	= "/bin/cp " + copyFrom + " " + localDir;
		inclGzCmd[2]	= "/bin/gzip -df " + localDir + localFile + ".gz";
		String[] fileList;
		// PrintLog("Dir Str 1 "+copy_from);
		PrintLog("Looking for :" + localDir + localFile);
		if (localFile.indexOf("*") > 0) {
			// PrintLog("Will run fUtil.GetFNamesWC");
			fileList	= FileUtil.GetFNamesWC(locInputDir,
											localFile.substring(0, localFile.indexOf("*")),
											localFile.substring(localFile.indexOf("*") + 1));
		} else {
			// PrintLog("Will run fUtil.GetFileNames");
			fileList	= FileUtil.GetFileNames(locInputDir, localFile);
		}
		if (fileList	== null) {
			// if (fileList.length	== 0) {
			PrintLog("File " + localFile + " or Directory " + locInputDir + " does not exist");
			return;
		} else if (fileList.length	== 0) {
			PrintLog("No New " + localFile + " available for processing");
			return;
		} else {
			PrintLog("Will check File List " + fileList[0]);
		}
		try {
			for (int idx = 0; idx < fileList.length; idx++) {
				remoteFile	= fileList[idx].substring(0, fileList[idx].indexOf(".gz"));
				PrintLog("Remote File : >" + remoteFile + "<");
				findFile	= new File(localDir + remoteFile + "_" + applDateAtStart);
				inclFileExists	= findFile.exists();
				if (inclFileExists == false) {
					findFile	= new File(localDir + remoteFile);
					inclFileExists	= findFile.exists();
					if (inclFileExists == false) {
						//inclCpCmd[2]	= "/bin/cp " + wf_datadir + remoteFile + ".gz" + " " + localDir;
						PrintLog("Copying file from WF ");
						PrintLog(" inclCpCmd[2]: " + inclCpCmd[2]);
						copyFileProc	= Runtime.getRuntime().exec(inclCpCmd);
						inclWaitResult		= copyFileProc.waitFor();
						Runtime.getRuntime().gc();
						inclGzCmd[2]		= "/bin/gzip -df " + localDir + remoteFile + ".gz";
						PrintLog("copyFileProc completion Status : " + inclWaitResult);
						PrintLog(" inclGzCmd[2]: " + inclGzCmd[2]);
						unzipFileProc	= Runtime.getRuntime().exec(inclGzCmd);
						inclWaitResult		= unzipFileProc.waitFor();
						Runtime.getRuntime().gc();
						PrintLog("gz -df completion Status : " + inclWaitResult);
						if (inclWaitResult	== 0) {
							inclFileCopied	= true;
							if (inclBankId.equals("BNPMO")) {
								//
								// Symcor file then unzip, extract and process the XML file
								//
								if (remoteFile.indexOf(".zip") > 0) {
									inclCtlCount	= 1;
									inclXmlCount	= 1;
									inclImgCount	= 1;
									PrintLog("Will Unzip and Extract File: " + localDir + remoteFile);
									UnzipMontrealFiles(applDateAtStart, remoteFile, localDir);
											//localDir + remoteFile);
									PrintLog("Number of XML files extracted: " + (inclXmlCount - 1));
									for (int idx2 = 1; idx2 <= inclXmlCount - 1; idx2++) {
										PrintLog("Starting Image Loader for " + localDir +
												inclFileName + inclCycleNum.substring(0, 2) +
												idx2 + inclCycleNum.substring(2) + ".xml");
										extractMo.runMontrealLoader(todaysDir,
																	//locOutputDir + "frb/", 
																	todaysDir + inclFileName + 
																	inclCycleNum.substring(0,2)	+ idx2 +
																	inclCycleNum.substring(2));
										// localDir+extractedFile+".xml");
									}
								}
								//
								// If FACS files then copy them to the daily
								// Directory and rename the
								// FACS file in the input directory
								//
								File daysDir	= new File(todaysDir);
								if (!daysDir.exists()) {
									PrintLog("Specified directory does not exist - Creating > " +
											 todaysDir);
									// create the directory needed for the images
									File fileOutdir	= new File(todaysDir);
									boolean mk_dir	= fileOutdir.mkdirs();
									if (mk_dir	== false) {
										PrintLog("Error creating Directory " + mk_dir);
										return;
									}
								}
								if (remoteFile.substring(0, 4).equals("facs")) {
									PrintLog("Copying file from Montreal Input as ");
									inclCpCmd[2]	= "/bin/cp " + localDir + remoteFile + " " + todaysDir +
												remoteFile;
									PrintLog(" inclCpCmd[2]: " + inclCpCmd[2]);
									copyFileProc	= Runtime.getRuntime().exec(inclCpCmd);
									inclWaitResult		= copyFileProc.waitFor();
									File facsFile	= new File(localDir + remoteFile);
									facsFile.renameTo(new File(localDir + remoteFile + "_" +
													  applDateAtStart));
								}
							} else {
								PrintLog("Remote file type " + remoteFile.substring(0, 4));
								File daysDir	= new File(todaysDir);
								if (!daysDir.exists()) {
									PrintLog("Specified directory does not exist - Creating > " +
											 todaysDir);
									// create the directory needed for the images
									File fileOutdir	= new File(todaysDir);
									boolean mk_dir	= fileOutdir.mkdirs();
									if (mk_dir	== false) {
										PrintLog("Error creating Directory " + mk_dir);
										return;
									}
								}
							}
						} else {
							PrintLog("Problem in Copy/Unzip file please check Log: Code " + 
									 inclWaitResult);
						}
					} else {
						PrintLog(remoteFile + " File exists Skipping Copy");
					}
				} else {
					PrintLog("File has already been processed " + remoteFile + "_" + applDateAtStart);
				}
			} // end for (idx	= 0; idx < fileList.length; idx++) {
			PrintLog("Directory scan 4 Complete");
			Runtime.getRuntime().gc();
			// PrintLog("Free Memory 5: "+Runtime.getRuntime().freeMemory());
		} catch (Throwable t) {
			PrintLog("EXEC " + t);
			t.printStackTrace();
		}
	}
	//
	public void UnzipMontrealFiles(String applDateAtStart, String remoteFile, String localDir) {
		moduleName			= "UnzipMontrealFiles: ";
		Enumeration<?> entries;
		ZipFile zipFile;
		//
		int entriesInZip	= 0;
		int		dotPos			= 0;
		int		hyPos			= 0;
		//
		String	unzippedFileName= "";
		//
		dotPos				= remoteFile.indexOf(".");
		hyPos				= remoteFile.indexOf("_");
		inclCycleNum			= remoteFile.substring(hyPos, dotPos);
		//
		try {
			zipFile			= new ZipFile(remoteFile);
			entries			= zipFile.entries();
			PrintLog("Will Unzip file :" + remoteFile);
			while (entries.hasMoreElements()) {
				ZipEntry entry		= (ZipEntry) entries.nextElement();
				unzippedFileName	= entry.getName();
				PrintLog("Extracting file: " + unzippedFileName);
				//
				// Extract the file name and rename
				//
				if (unzippedFileName.substring(0, 4).equals("PSYM")) {
					// PrintLog("unzippedFileName "+unzippedFileName);
					copyInputStream(zipFile.getInputStream(entry),
							new BufferedOutputStream(new FileOutputStream(
									localDir + entry.getName())));
					entriesInZip++;
					if (unzippedFileName.indexOf("CNTL") > 0) {
						File ctlFile	= new File(localDir + unzippedFileName);
						ctlFile.renameTo(new File(localDir + inclFileName +
										inclCycleNum.substring(0, 2) + inclCtlCount +
										inclCycleNum.substring(2) + ".ctl"));
						PrintLog("Renamed " + localDir + unzippedFileName +
								 " to " + localDir + inclFileName +
								 inclCycleNum.substring(0, 2) + inclCtlCount +
								 inclCycleNum.substring(2) + ".ctl");
						inclCtlCount++;
					} else if (unzippedFileName.indexOf("INDX") > 0) {
						File xmlFile	= new File(localDir + unzippedFileName);
						xmlFile.renameTo(new File(localDir + inclFileName +
										 inclCycleNum.substring(0, 2) + inclXmlCount +
										 inclCycleNum.substring(2) + ".xml"));
						//extractedFile	= (inclFileName + inclCycleNum.substring(0, 1) + inclXmlCount + 
						//				inclCycleNum.substring(1));
						PrintLog("Renamed " + localDir + unzippedFileName +
								 " to " + localDir + inclFileName +
								 inclCycleNum.substring(0, 2) + inclXmlCount +
								 inclCycleNum.substring(2) + ".xml");
						inclXmlCount++;
					}
					if (unzippedFileName.indexOf("DATA") > 0) {
						File imgFile	= new File(localDir + unzippedFileName);
						imgFile.renameTo(new File(localDir + inclFileName +
										 inclCycleNum.substring(0, 2) + inclImgCount +
										 inclCycleNum.substring(2) + ".img"));
						PrintLog("Renamed " + localDir + unzippedFileName +
								 " to " + localDir + inclFileName +
								 inclCycleNum.substring(0, 2) + inclImgCount +
								 inclCycleNum.substring(2) + ".img");
						inclImgCount++;
					}
				}
			}
			PrintLog("Extracted " + entriesInZip + " files from " + remoteFile);
			PrintLog("Will rename File : " + remoteFile);
			File xmlFile	= new File(remoteFile);
			xmlFile.renameTo(new File(remoteFile + "_" + applDateAtStart));
		} catch (Throwable t) {
			PrintLog(" EXEC " + t);
		}
	}
	//
	public static final void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer	= new byte[1024];
		int len;
		//
		while ((len	= in.read(buffer)) >= 0)
			out.write(buffer, 0, len);
		//
		in.close();
		out.close();
	}
	//
	public void run() {
		moduleName							= "run: ";
		ControlSelector  ctlSelector		= new ControlSelector();
		ControlDetail	 ctlDetail			= new ControlDetail();
		String	db_url			= null;
		String	db_driver		= null;
		String	db_user			= null;
		String	db_pass			= null;
		String	dbUsed			= null;
		//
		//int		rowCount		= 0;
		//
		String	remBaseDir		= "";
		String	locInputDir		= "";
		//String	locOutputDir	= "";
		String	imgBaseDir		= "";
		//String	cifFile			= "";
		String	posiSwfFile		= "";
		String	posiCsvFile		= "";
		//String	stopCsvFile		= "";
		//
		String	prodId			= "C";
		// String prodId	= "A";
		String	localDir		= "";
		String	localFile		= "";
		String	copyFrom		= "";
		//String	rm_file			= "";
		String	todaysDir		= "";
		//
		String	appl_date		= "";
		String	applDateAtStart	= "";
		//String	bod_flag		= "";
		//String	dir_spec		= "";
		String	ec_cif_dir		= "";
		String	ec_cif_file		= "";
		//String	ec_mcr_dir		= "";
		String	ec_mcr_file		= "";
		String	ec_posi_dir		= "";
		String	ec_posi_file	= "";
		String	ec_posisw_dir	= "";
		String	ec_posisw_file	= "";
		String	facsFile		= "";
		//String	facsCADFile		= "";
		//String	facsUSDFile		= "";
		//String[] file_info		= { "", "", "", "", "", "", "", "", "",""};
		//String	file_info_prev	= "";
		//String	file_info_curr	= "";
		//String	REGEX			= "\\s";
		//String	temp			= "";
		//String	file_name		= "";
		//String	file_name_uz	= "";
		//String	remoteFile		= "";
		//
		//String	sBuff			= "";
		//String	s1Buff			= "";
		//String	extractedFile	= "";
		//
		Connection	   dbConn	= null;
		String todays_date					= ValiDate.getTodays_date();
		EcontServletContextListener escl	= new EcontServletContextListener();
		try {
			sleep(1000 * 60 * 1); // Sleep for 1 minutes
			PrintLog("Starting eFileServer");
			db_url				= escl.getDbUrl();
			db_driver			= escl.getDbDriver();
			db_user				= escl.getDbUser();
			db_pass				= escl.getDbPass();
			dbUsed				= escl.getDbUsed();
			inclBankId			= escl.getBankId();
			// PrintLog("Starting FRB Server for DB "+db_url);
			// PrintLog("DB Driver "+db_driver);
			Class.forName(db_driver);
			dbConn				= DriverManager.getConnection(db_url, db_user, db_pass);
			ctlDetail.setDbUsed(dbUsed);
			prodId				= "A";
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail			= ctlSelector.getARow();
			//bod_flag			= ctlDetail.getBodFlag();
			applDateAtStart		= ctlDetail.getApplDate();
			appl_date			= applDateAtStart;
			remBaseDir			= ctlDetail.getRemBaseDir();	// /usr/local/sav/nohtdocs/sav/rpt/ny/
			locInputDir			= ctlDetail.getLocInputDir();	// /user/local/econtr/ny/input/
			//locOutputDir		= ctlDetail.getLocOutputDir();	// /user/local/econtr/ny/output/
			imgBaseDir			= ctlDetail.getImgBaseDir();	// /user/local/econtr/eController/images/
			//cifFile				= ctlDetail.getCifFile();		// atlas_cif-200.dat
			posiSwfFile			= ctlDetail.getPosiSwfFile();	// swift*
			posiCsvFile			= ctlDetail.getPosiCsvFile();	// posipay*.txt
			//stopCsvFile			= ctlDetail.getStopCsvFile();	// stoppay*.txt
			//
			ec_cif_file			= ctlDetail.getCifFile();
			ec_cif_dir			= locInputDir + "atlas/";
			ec_posisw_file		= posiSwfFile;
			ec_posisw_dir		= locInputDir + "swift/";
			ec_posi_file		= posiCsvFile;
			ec_posi_dir			= locInputDir + "swift/";
			//
			todaysDir	= (imgBaseDir + "incl/" + applDateAtStart.substring(0, 4) + "/" +
							applDateAtStart.substring(4, 6) + "/" +
							applDateAtStart.substring(6, 8) + "/");
			copyFrom	= remBaseDir + applDateAtStart.substring(0, 4) + "/" +
							applDateAtStart.substring(4, 6) + "/" +
							applDateAtStart.substring(6, 8) + "/" + "cbaaaa01/";
			/* } */
			PrintLog("Starting eFileServer for application date " +
					 applDateAtStart);
		} catch (Throwable t) {
			PrintLog("Exception >" + t + "< Terminating Process");
			// t.printStackTrace();
			// this will terminate the process
			inclCodPerformed	= true;
		}
		prodId				= "C";
		ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
		ctlDetail			= ctlSelector.getARow();
		//bod_flag			= ctlDetail.getBodFlag();
		applDateAtStart	= ctlDetail.getApplDate();
		appl_date			= applDateAtStart;
		while (inclCodPerformed	== false) {
			try {
				PrintLog("Starting New Cycle on " + applDateAtStart);
				localDir	= ec_posisw_dir;
				localFile	= ec_posisw_file;
				PrintLog("Will Get: "+ec_posisw_file);
				GetFiles(applDateAtStart, copyFrom, localDir, localFile, locInputDir, todaysDir);
				//
				localDir	= ec_cif_dir;
				localFile	= ec_cif_file;
				PrintLog("Will Get: "+ec_cif_file);
				GetFiles(applDateAtStart, copyFrom, localDir, localFile, locInputDir, todaysDir);
				//
				if (inclBankId.equals("BNPMO")) {
					// facsFile	= "facsfile"+appl_date+"*.mcr";
					localDir	= ec_mcr_file;
					localFile	= facsFile;
					GetFiles(applDateAtStart, copyFrom, localDir, localFile, locInputDir, todaysDir);
				} else {
					// FRB MICR Image file
					/*
					copy_from	= wf_datadir + wf_image_file_gz;
					localDir	= locInputDir + "frb/";
					localFile	= ec_image_file;
					GetFiles();
					*/
				}
				// Manual Posi files
				localDir	= ec_posi_dir;
				localFile	= ec_posi_file;
				PrintLog("Will Get: "+ec_posi_file);
				GetFiles(applDateAtStart, copyFrom, localDir, localFile, locInputDir, todaysDir);
				//
				if (inclFileCopied	== true) {
					sleep(1000 * 30 * 1); // Sleep for 1/2 minute
				}
				if (inclCodPerformed	== true) {
					break;
				}
				sleep(1000 * 60 * 5); // Sleep for 5 minutes
				ctlDetail.setDbUsed(dbUsed);
				ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
				ctlDetail	= ctlSelector.getARow();
				//bod_flag	= ctlDetail.getBodFlag();
				appl_date	= ctlDetail.getApplDate();
				//
				if (todays_date.compareTo(appl_date) < 0) {
					PrintLog("Shutting down -- Application date " +
							 applDateAtStart + " is in the future.");
					PrintLog("Today is :" + todays_date);
					inclCodPerformed	= true;
				}
			} catch (InterruptedException e) {
				PrintLog("Received Termination signal -- Ending Process");
				inclCodPerformed = true;
			} catch (Throwable t) {
				PrintLog(t + "");
				// PrintLog("Exception >"+t+"< Terminating Process");
				// t.printStackTrace();
				// this will terminate the process
				inclCodPerformed	= true;
			}
		}
		PrintLog("Shutting down for the DAY " + applDateAtStart);
	}
}
