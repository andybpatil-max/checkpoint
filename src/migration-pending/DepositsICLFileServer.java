package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.io.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.deposits.service.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
//
public class DepositsICLFileServer extends Thread {
	String	className		= "";
	String	moduleName;
	private String	depoIclSource	= "";
	boolean	depoCodPerformed	= false;
	boolean	depoFileExists		= true;
	boolean	iclDepoProcessed	= false;
	boolean mkDepoDir			= false;
	private int depoCycleNum	= 1;
	private String fS			= System.getProperty("file.separator");
	private String depoFile2Process	= "";
	//
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ExtractICLImages	extractDep	= new ExtractICLImages();
	FileUtil			fUtil		= new FileUtil();
	ProcessUtil 		pUtil		= new ProcessUtil();
	File	findFile				= null;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + logMsg);
	}
	//
	public DepositsICLFileServer(String ICLSource) {
		super(ICLSource);
		//iclSource	= ICLSource;
		className	= "> " + ICLSource + " ICLServer.";
	}
	//
	public void stopIclSrv() {
		depoCodPerformed = true;
	}
	//
	public void wakeIclSrv() {
		//
		// Here to just wake up the thread so that it will initiate
		// the harvest process.
		//
	}
	public boolean CreateImageDir (String dirPath) {
		//PrintLog("Date Dir: "+dirPath);
		File daysDir	= new File(dirPath);
		if (!daysDir.exists()) {
			PrintLog("Specified directory does not exist - Creating > "	+ dirPath);
			File fileOutdir	= new File(dirPath);
			mkDepoDir	= fileOutdir.mkdirs();
			if (mkDepoDir == false) {
				PrintLog("Error creating Directory " + mkDepoDir);
			}
		}
		return mkDepoDir;
	}
	//
	public void CheckForICLFile(String applDateAtStart, String depoIclSource, String localDir, String locOutputDir,
								String todaysDir, String localFile) {
		moduleName		= "CheckForICLFile: ";
		iclDepoProcessed	= false;
		String[] fileList;
		PrintLog("Looking for :" + localDir + localFile);
		//PrintLog("In Directory :" + localDir);
		if (localFile.indexOf("*") > 0) {
			//PrintLog("Will run fUtil.GetFNamesWC");
			fileList	= FileUtil.GetFNamesWC(localDir,
												localFile.substring(0, localFile.indexOf("*")),
												localFile.substring(localFile.indexOf("*") + 1));
		} else {
			//PrintLog("Will run fUtil.GetFileNames");
			fileList = FileUtil.GetFileNames(localDir, localFile);
		}
		if (fileList == null) {
			PrintLog("File " + localFile + " or Directory " + localDir + " does not exist");
			return;
		} else {
			if (fileList.length == 0) {
				PrintLog("No New " + localFile + " file to process at this time");
				return;
			} else {
				PrintLog("Will check File List " + fileList[0]);
			}
		}
		try {
			for (int idx = 0; idx < fileList.length; idx++) {
				depoFile2Process	= fileList[idx];
				PrintLog("File to process: >" + depoFile2Process + "<");
				findFile		= new File(localDir + depoFile2Process + "_" + applDateAtStart);
				PrintLog("Find Processed File: >" + findFile + "<");
				depoFileExists		= findFile.exists();
				if (depoFileExists == false) {
					findFile	= new File(localDir + depoFile2Process);
					depoFileExists	= findFile.exists();
					if (depoFileExists == false) {
						PrintLog("No " + findFile + " File to process at this time");
					} else {
						PrintLog(depoFile2Process + " Exists -- Processing");
						extractDep.runImageloader(localDir, todaysDir, 
												  locOutputDir + depoIclSource.toLowerCase() + fS,
												  depoFile2Process);
						File inputFile	= new File(localDir+depoFile2Process);
						inputFile.renameTo(new File(localDir+depoFile2Process + "_" + applDateAtStart));
						iclDepoProcessed	= true;
						break;
					}
				} else {
					PrintLog("File " + findFile + " has already been processed ");
					File inputFile	= new File(localDir+depoFile2Process);
					inputFile.renameTo(new File(localDir+depoFile2Process + "_" + applDateAtStart));
					depoCycleNum	= 2;
				}
			} // end for
			Runtime.getRuntime().gc();
		} catch (Throwable t) {
			PrintLog("EXEC " + t);
		}
	}
	//
	public void GetCycleNumber (String applDateAtStart, String localDir, 
								String localFile) {
		moduleName		= "GetCycleNumber: ";
		String[] fileList;
		PrintLog("Looking for :" + localDir + localFile);
		PrintLog("In Directory :" + localDir);
		if (localFile.indexOf("*") > 0) {
			PrintLog("Will run fUtil.GetFNamesWC");
			fileList	= FileUtil.GetFNamesWC(localDir,
												localFile.substring(0, localFile.indexOf("*")),
												localFile.substring(localFile.indexOf("*") + 1)
												+"_" + applDateAtStart);
		} else {
			PrintLog("Will run fUtil.GetFileNames");
			fileList = FileUtil.GetFileNames(localDir, localFile);
		}
		if (fileList == null) {
			PrintLog("File " + localFile + " or Directory " + localDir + " does not exist");
			return;
		} else if (fileList.length == 0) {
			PrintLog("No New " + localFile + " file to process at this time");
			return;
		} else {
			PrintLog("Will check File List " + fileList[0]);
			PrintLog("File " + fileList[0] + " has already been processed ");
			depoCycleNum	= 2;
			//actionFlag	= "";
		}
	}
	//
	public void run() {
		moduleName			= "run: ";
		String todays_date	= ValiDate.getTodays_date();
		ControlSelector		ctlSelector	= new ControlSelector();
		ControlDetail		ctlDetailA	= new ControlDetail();
		ControlDetail		ctlDetailD	= new ControlDetail();
		String actionFlag		= "LoadFile";
		String applDate			= "";
		String applDateAtStart	= "";
		String dbUsed			= "";
		String db_driver		= "";
		String db_pass			= "";
		String db_url			= "";
		String db_user			= "";
		String iclFile			= "";
		String imgBaseDir		= "";
		String locOutputDir		= "";
		String localDir			= "";
		String localPath		= "";
		String localFile		= "";
		String prodId			= "";
		String prodIdA			= "A";
		String prodIdD			= "D";
		String todaysDir		= "";
		String userName			= "DepositLoader";
		//
		Connection	dbConn		= null;
		UploadNYDeps	uploadDeps	= new UploadNYDeps();
		PrintLog ("Running "+ depoIclSource + " ICL Server");
		EcontServletContextListener escl	= new EcontServletContextListener();
		try {
			Thread.sleep(1000 * 60 * 1); // Sleep for 1 minute
			/*
			for (int idx = 0; idx < 13; idx++) {
				Thread.sleep(1000 * 5); // Sleep for 5 seconds
				if (Thread.currentThread().isInterrupted()) {
					returnBack(dbConn, applDateAtStart);
					return;
				}
				if (depoCodPerformed == true) {
					returnBack(dbConn, applDateAtStart);
					return;
				}
			}
			*/
			PrintLog("Starting CollectionsFileServer");
			fS				= System.getProperty("file.separator");
			db_url			= escl.getDbUrl();
			db_driver		= escl.getDbDriver();
			db_user			= escl.getDbUser();
			db_pass			= escl.getDbPass();
			dbUsed			= escl.getDbUsed();
			Class.forName(db_driver);
			dbConn			= DriverManager.getConnection(db_url, db_user, db_pass);
			ctlSelector.setDbUsed(dbUsed);
			ctlDetailA.setDbUsed(dbUsed);
			ctlDetailD.setDbUsed(dbUsed);
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodIdA);
			ctlDetailA		= ctlSelector.getARow();
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodIdD);
			ctlDetailD		= ctlSelector.getARow();
			localPath		= ctlDetailA.getLocInputDir();
			locOutputDir	= ctlDetailA.getLocOutputDir();
			imgBaseDir		= ctlDetailA.getImgBaseDir();
			applDateAtStart	= ctlDetailD.getApplDate();
			//
			prodId			= "D";
			iclFile			= ctlDetailA.getRdcIcl();
			localFile		= iclFile.replaceAll("\\*", applDateAtStart+"*");;
			applDate		= applDateAtStart;
			todaysDir		= (imgBaseDir + "outcl"+ fS + 
							   applDateAtStart.substring(0, 4) + fS +
							   applDateAtStart.substring(4, 6) + fS +
							   applDateAtStart.substring(6, 8) + fS);
			CreateImageDir(todaysDir);
			depoIclSource		= "rdc";
			iclFile			= ctlDetailA.getRdcIcl();
			localFile		= iclFile.replaceAll("\\*", applDateAtStart+"*");;
			localDir		= localPath + "rdc" + fS;
			GetCycleNumber(applDateAtStart, localDir, localFile);
			PrintLog("First Pass: "+ depoCycleNum);
			depoIclSource	= "lockbox";
			iclFile			= ctlDetailA.getLboxIcl();
			localFile		= iclFile.replaceAll("\\*", applDateAtStart+"*");;
			localDir		= localPath + "lockbox" + fS;
			GetCycleNumber(applDateAtStart, localDir, localFile);
			PrintLog("Starting Cycle "+ depoCycleNum +" ICL file Server for application date " + applDateAtStart);
		} catch (Throwable t) {
			PrintLog("Exception >" + t + "< Terminating Process");
			t.printStackTrace();
			depoCodPerformed	= true;
		}
		prodId			= "D";
		while (depoCodPerformed == false) {
			try {
				PrintLog("Starting New Cycle " + depoCycleNum + " on " + applDateAtStart);
				iclFile			= ctlDetailA.getRdcIcl();
				localFile		= iclFile.replaceAll("\\*", applDateAtStart+"*");
				localDir		= localPath + "rdc" + fS;
				depoIclSource	= "rdc";
				CheckForICLFile(applDateAtStart, depoIclSource, localDir, locOutputDir,
								todaysDir, localFile);
				if (iclDepoProcessed == true) {
					Thread.sleep(1000 * 60 * 1); // Sleep for 1 minute
					/*
					for (int idx = 0; idx < 3; idx++) {
						Thread.sleep(1000 * 5); // Sleep for 5 seconds
						if (Thread.currentThread().isInterrupted()) {
							returnBack(dbConn, applDateAtStart);
							return;
						}
						if (depoCodPerformed == true) {
							returnBack(dbConn, applDateAtStart);
							return;
						}
					}
					*/
					uploadDeps.UpLoadDeps(dbConn, dbUsed, depoIclSource,
											applDate, todaysDir, 
											locOutputDir+depoIclSource.toLowerCase() + fS,
											depoFile2Process, depoCycleNum, actionFlag,
											userName);
					depoCycleNum	= 2;
					actionFlag		= "";
				}
				depoIclSource	= "lockbox";
				iclFile			= ctlDetailA.getLboxIcl();
				localFile		= iclFile.replaceAll("\\*", applDateAtStart+"*");
				localDir		= localPath + "lockbox" + fS;
				CheckForICLFile(applDateAtStart, depoIclSource, localDir, locOutputDir,
								todaysDir, localFile);
				if (iclDepoProcessed == true) {
					Thread.sleep(1000 * 60 * 1); // Sleep for 1 minute
					/*
					for (int idx = 0; idx < 3; idx++) {
						Thread.sleep(1000 * 5); // Sleep for 5 seconds
						if (Thread.currentThread().isInterrupted()) {
							returnBack(dbConn, applDateAtStart);
							return;
						}
						if (depoCodPerformed == true) {
							returnBack(dbConn, applDateAtStart);
							return;
						}
					}
					*/
					try {
						uploadDeps.UpLoadDeps(dbConn, dbUsed, depoIclSource,
											applDate, todaysDir, 
											locOutputDir+depoIclSource.toLowerCase() + fS,
											depoFile2Process, depoCycleNum, actionFlag,
											userName);
					} catch (FileNotFoundException e){
						PrintLog ("No File Found to Upload -- Continuing Processing");
					} catch (IOException e) {
						PrintLog ("Errors encountered processing "+todaysDir+depoFile2Process+
								" -- Continuing Processing");
					}
					depoCycleNum	= 2;
					actionFlag		= "";
				}
				if (depoCodPerformed == true) {
					break;
				}
				try {
					Thread.sleep(1000 * 60 * 10); // Sleep for 10 minutes
					/*
					for (int idx = 0; idx < 61; idx++) {
						Thread.sleep(5000); // Sleep for 5 seconds
						if (Thread.currentThread().isInterrupted()) {
							returnBack(dbConn, applDateAtStart);
							return;
						}
						if (depoCodPerformed == true) {
							returnBack(dbConn, applDateAtStart);
							return;
						}
					}
					*/
				} catch (InterruptedException e) {
					returnBack(dbConn, applDateAtStart);
					return;
				}
				ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
				ctlDetailD	= ctlSelector.getARow();
				if (todays_date.compareTo(applDate) < 0) {
					PrintLog("Shutting down Server -- Application date " + applDateAtStart + 
							 " is in the future.");
					PrintLog("Today is :" + todays_date);
					depoCodPerformed = true;
					break;
				}
			} catch (InterruptedException e) {
				PrintLog("Received Termination signal -- Ending Process");
				depoCodPerformed = true;
				returnBack(dbConn, applDateAtStart);
				return;
			} catch (FileNotFoundException e){
				PrintLog ("No File Found to Upload -- Continuing Processing");
			} catch (Throwable t) {
				PrintLog(t + "");
				PrintLog("Exception >"+t+"< Terminating Process");
				t.printStackTrace();
				depoCodPerformed = true;
				break;
			}
		}
	}
	public void returnBack (Connection dbConn, String applDateAtStart) {
		try {
			PrintLog ("Closing db Connection");
			dbConn.close();
		} catch (Exception e) {
			PrintLog ("FAILED Closing db Connection");
			/* Ignored */
		}
		PrintLog("Shutting down for the DAY " + applDateAtStart);
	}
}
