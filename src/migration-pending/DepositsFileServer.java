package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.io.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.deposits.service.UploadDepMicr;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
//
public class DepositsFileServer extends Thread {
	String	className		= "> DepositsFileServer.";
	String	moduleName;
	boolean	cod_performed	= false;
	boolean	fileExists		= true;
	boolean	file_copied		= false;
	//
	String	db_url			= "";
	String	db_driver		= "";
	String	db_user			= "";
	String	db_pass			= "";
	String	dbUsed			= "";
	String	bankId			= "";
	String	oS				= "";
	String	depositTiffs	= "";
	//
	String cpCmd			= "";
	String[] cp_cmd			= { "/bin/sh ", " -c ", " /bin/cp " };
	String[] gz_cmd			= { "/bin/sh ", " -c ", " /bin/gzip -df " };
	String[] gzdWin_cmd		= { "gzip -df " };
	String[] gzWin_cmd		= { "gzip " };
	//
	char	 space			= ' ';
	//
	int		 idx			= 0;
	int		 idx1			= 0;
	int		 idx2			= 0;
	int		 row_count		= 0;
	int		 waitResult		= 0;
	int		 procResult		= 0;
	int		 dotPos			= 0;
	int		 hyPos			= 0;
	long	 freememory		= 0;
	//
	String	remBaseDir		= "";
	String	locOutputDir	= "";
	String	imgBaseDir		= "";
	String	depsMicrFile	= "";
	//
	String	prodId			= "D";
	// String prodId = "A";
	String	local_dir		= "";
	String	localFile		= "";
	String	copy_from		= "";
	String	rm_file			= "";
	String	todaysDir		= "";
	//
	String	appl_date		= "";
	String	applDateAtStart = "";
	String	bod_flag		= "";
	String	dir_spec		= "";
	String	ec_mcr_dir		= "";
	String	ec_mcr_file		= "";
	String	ec_imageDir		= "";
	String	ec_image_file	= "";
	String[]	file_info	= { "", "", "", "", "", "", "", "","", ""};
	String	file_info_prev	= "";
	String	file_info_curr	= "";
	String	REGEX			= "\\s";
	String	temp			= "";
	String	file_name		= "";
	String	file_name_uz	= "";
	String	wf_dirspec		= "";
	String	wf_datadir		= "";
	String	wf_data_file	= "";
	String	wf_image_file	= "";
	String	wf_image_file_gz= "";
	String	remoteFile		= "";
	//
	String	sBuff			= "";
	String	s1Buff			= "";
	String	extractedFile	= "";
	String	cycleNum		= "";
	int		ctlCount		= 1;
	int		xmlCount		= 1;
	int		imgCount		= 1;
	//
	Connection	dbConn;
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ControlSelector		ctlSelector	= new ControlSelector();
	ControlDetail		ctlDetail	= new ControlDetail();
	ControlDetail		ctlDetailD	= new ControlDetail();
	ExtractDepsImages	extractDep	= new ExtractDepsImages();
	ZipFilesUtil		zipFiles	= new ZipFilesUtil();
	FileUtil			fUtil		= new FileUtil();
	//GetCAR				exCar		= new GetCAR();
	ProcessUtil 		pUtil		= new ProcessUtil();
	UploadDepMicr		upLoadMicr	= new UploadDepMicr();
	BufferedReader		frb_br1		= null;
	BufferedReader		fileTest	= null;
	Process	copyFileProc;
	Process	proc_ls;
	Process	remoteDirProc;
	Process	unzipFileProc;
	Process	uZproc;
	Process	mvProc;
	File	findFile				= null;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + logMsg);
	}
	//
	public DepositsFileServer(String DepsFiles_str) {
		super(DepsFiles_str);
	}
	//
	public void stopDepsSrv() {
		cod_performed = true;
	}
	//
	public void wakeDepsSrv() {
		//
		// Here to just wake up the thread so that it will initiate
		// the harvest process.
		//
	}
	//
	public void GetFiles() {
		moduleName		= "GetFiles: ";
		file_copied		= false;
		// file_found	= false;
		cp_cmd[2]		= "/bin/cp " + copy_from + " " + local_dir;
		gz_cmd[2]		= "/bin/gzip -df " + local_dir + localFile + ".gz";
		String[] fileList;
		// PrintLog("Dir Str 1 "+copy_from);
		PrintLog("Looking for :" + localFile);
		PrintLog("In Directory :" + wf_datadir);
		if (localFile.indexOf("*") > 0) {
			// PrintLog("Will run fUtil.GetFNamesWC");
			fileList	= FileUtil.GetFNamesWC(wf_datadir,
												localFile.substring(0, localFile.indexOf("*")),
												localFile.substring(localFile.indexOf("*") + 1) + ".gz");
		} else {
			// PrintLog("Will run fUtil.GetFileNames");
			fileList = FileUtil.GetFileNames(wf_datadir, localFile + ".gz");
		}
		if (fileList == null) {
			// if (fileList.length == 0) {
			PrintLog("File " + localFile + " o/r Directory " + wf_datadir + " does not exist");
			return;
		} else if (fileList.length == 0) {
			PrintLog("No New " + localFile + " files to process at this time");
			return;
		} else {
			PrintLog("Will check File List " + fileList[0]);
		}
		try {
			for (idx = 0; idx < fileList.length; idx++) {
				remoteFile	= fileList[idx].substring(0, fileList[idx].indexOf(".gz"));
				PrintLog("Remote File : >" + remoteFile + "<");
				findFile	= new File(local_dir + remoteFile + "_" + applDateAtStart);
				PrintLog("Processed File : >" + findFile + "<");
				fileExists	= findFile.exists();
				if (fileExists == false) {
					findFile	= new File(local_dir + remoteFile);
					fileExists	= findFile.exists();
					if (fileExists == false) {
						//cp_cmd[2]	= "/bin/cp " + wf_datadir + remoteFile + ".gz" + " " + local_dir;
						cpCmd		= cp_cmd[0] + " " + cp_cmd[1] + " /bin/cp " + wf_datadir + 
										remoteFile + ".gz" + " " + local_dir;
						PrintLog("Copying file from WF ");
						//PrintLog(" cp_cmd[2]: " + cp_cmd[2]);
						PrintLog(" cpCmd: " + cpCmd);
						pUtil.ProcessCommand(cpCmd);
						Runtime.getRuntime().gc();
						cpCmd		= gz_cmd[0] + gz_cmd[1] + gz_cmd[2] + wf_datadir + 
										remoteFile + ".gz" + " " + local_dir;
						//gz_cmd[2]		= "/bin/gzip -df " + local_dir + remoteFile + ".gz";
						PrintLog("copyFileProc completion Status : " + waitResult);
						PrintLog(" cpCmd: " + cpCmd);
						if (pUtil.ProcessCommand(cpCmd)) {
							file_copied	= true;
							PrintLog("Remote file type " + remoteFile.substring(0, 4));
							PrintLog("Starting Image Loader for " + local_dir + remoteFile);
							extractDep.RunDepsLoader(local_dir, todaysDir, locOutputDir + 
													 "gps/", remoteFile);
						} else {
							PrintLog("Problem in Copy/Unzip file please check Log: Code " + waitResult);
						}
						Runtime.getRuntime().gc();
					} else {
						PrintLog(remoteFile + "Exists Skipping Copy -- Processing");
						extractDep.RunDepsLoader(local_dir, todaysDir, locOutputDir + 
												 "gps/", remoteFile);
					}
				} else {
					PrintLog("File has already been processed " + remoteFile + "_" + applDateAtStart);
				}
			} // end for (idx = 0; idx < fileList.length; idx++) {
			PrintLog("Directory scan 4 Complete");
			Runtime.getRuntime().gc();
			// PrintLog("Free Memory 5: "+Runtime.getRuntime().freeMemory());
		} catch (Throwable t) {
			PrintLog("EXEC " + t);
		}
		// }
	}
	//
	public void ProcessDepositTiffs() {
		moduleName	= "ProcessDepositTiffs: ";
		// First extract JPEGs from scanned checks
		try {
			/*
			if (JPEGfromTIFF.DoJpegFromTiff (depositTiffs, todaysDir)) {
				exCar.ExtractCAR();
				//sleep(1000 * 15 * 1); // Sleep for 15 seconbds
				// Here zip the tiff files
				zipFiles.ZipFiles(depositTiffs, depositTiffs+"\\tiffZips\\", "CheckImages");
			}
			*/
			//exData.Extract();
		} catch (Throwable t) {
			PrintLog("EXEC " + t);
		}
	}
	//
	public void run() {
		moduleName			= "run: ";
		String todays_date	= ValiDate.getTodays_date();
		EcontServletContextListener escl	= new EcontServletContextListener();
		try {
			sleep(1000 * 60 * 3); // Sleep for 3 minutes
			//PrintLog("Starting CollectionsFileServer");
			String fS		= System.getProperty("file.separator");
			oS				= System.getProperty("os.name");
			db_url			= escl.getDbUrl();
			db_driver		= escl.getDbDriver();
			db_user			= escl.getDbUser();
			db_pass			= escl.getDbPass();
			dbUsed			= escl.getDbUsed();
			bankId			= escl.getBankId();
			Class.forName(db_driver);
			dbConn			= DriverManager.getConnection(db_url, db_user, db_pass);
			ctlDetail.setDbUsed(dbUsed);
			prodId			= "A";
			row_count		= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail		= ctlSelector.getARow();
			bod_flag		= ctlDetail.getBodFlag();
			applDateAtStart	= ctlDetail.getApplDate();
			appl_date		= applDateAtStart;
			remBaseDir		= ctlDetail.getRemBaseDir();	// /usr/local/sav/nohtdocs/sav/rpt/ny/
			local_dir		= ctlDetail.getLocInputDir() + "frb" + fS;	// /home/source/input/frb
			locOutputDir	= ctlDetail.getLocOutputDir();	// /home/source/output/
			imgBaseDir		= ctlDetail.getImgBaseDir();	// /home/andy/eController/images/
			depsMicrFile	= ctlDetail.getDepsMicrFile();	// depsmicr_*.zip
			localFile		= depsMicrFile;
			wf_datadir		= (remBaseDir + appl_date.substring(0, 4) + fS + 
								appl_date.substring(0, 6) + fS + appl_date.substring(0, 8) + fS + 
								"fgaaaa01" + fS);
			todaysDir		= (imgBaseDir + "outcl"+ fS + 
							   applDateAtStart.substring(0, 4) + fS +
							   applDateAtStart.substring(4, 6) + fS +
							   applDateAtStart.substring(6, 8) + fS);
			File daysDir	= new File(todaysDir);
			if (!daysDir.exists()) {
				PrintLog("Specified directory does not exist - Creating > "	+ todaysDir);
				File fileOutdir	= new File(todaysDir);
				boolean mk_dir	= fileOutdir.mkdirs();
				if (mk_dir == false) {
					PrintLog("Error creating Directory " + mk_dir);
				}
			}
			prodId			= escl.getProdId("Collection");
			PrintLog("Product Id: "+prodId);
			row_count		= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetailD		= ctlSelector.getARow();
			bod_flag		= ctlDetailD.getBodFlag();
			applDateAtStart	= ctlDetailD.getApplDate();
			appl_date		= applDateAtStart;
			PrintLog("Starting CollectionsFileServer for application date " + applDateAtStart);
		} catch (Throwable t) {
			PrintLog("Exception >" + t + "< Terminating Process");
			cod_performed	= true;
		}
		while (cod_performed == false) {
			try {
				PrintLog("Starting New Cycle on " + applDateAtStart);
				copy_from	= wf_datadir + depsMicrFile + ".gz";
				//ProcessDepositTiffs();
				GetFiles();
				if (file_copied == true) {
					sleep(1000 * 30 * 1); // Sleep for 1/2 minute
					// Setup the control detail and call upload data
					upLoadMicr.LoadDepositMicr(dbConn, ctlDetail, ctlDetailD);
				}
				if (cod_performed == true) {
					break;
				}
				//
				sleep(1000 * 60 * 5); // Sleep for 5 minutes
				ctlDetail.setDbUsed(dbUsed);
				PrintLog("Get Row for Product :" + prodId);
				row_count	= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
				ctlDetail	= ctlSelector.getARow();
				bod_flag	= ctlDetail.getBodFlag();
				appl_date	= ctlDetail.getApplDate();
				//
				if (todays_date.compareTo(appl_date) < 0) {
					PrintLog("Shutting down -- Application date " + applDateAtStart + 
							 " is in the future.");
					PrintLog("Today is :" + todays_date);
					cod_performed = true;
				}
			} catch (InterruptedException e) {
				PrintLog("Received Termination signal -- Ending Process");
				cod_performed = true;
			} catch (Throwable t) {
				PrintLog(t + "");
				//PrintLog("Exception >"+t+"< Terminating Process");
				//t.printStackTrace();
				// this will terminate the process
				cod_performed = true;
			}
		}
		PrintLog("Shutting down for the DAY " + applDateAtStart);
	}
}
