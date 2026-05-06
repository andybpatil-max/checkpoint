package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.io.*;
//import java.text.*;
import java.util.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.deposits.service.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
//
public class DepohistICLFileServer extends Thread {
	String	className			= "";
	String	moduleName;
	int	histCycleNum			= 0;	
	String	iclSource0			= "";
	String	histIclSource		= "";
	Connection	histDbConn		= null;
	boolean	histCodPerformed	= false;
	boolean	histFileExists		= true;
	boolean	iclHistoryProcessed	= false;
	boolean mkHistDir			= false;
	ArrayList<String> retVals0	= new ArrayList <String> ();
	String fS					= System.getProperty("file.separator");
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ExtractICLImages	extractDep	= new ExtractICLImages();
	FileUtil			fUtil		= new FileUtil();
	ProcessUtil 		pUtil		= new ProcessUtil();
	UploadNYDeps		uploadDeps	= new UploadNYDeps();
	File	findFile				= null;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
						   className + moduleName + logMsg);
	}
	//
	public DepohistICLFileServer(String ICLSource) {
		super(ICLSource);
		iclSource0	= ICLSource;
		className	= "> History IclServer.";
	}
	//
	public void stopHistoryIclSrv() {
		histCodPerformed = true;
		Thread.currentThread().interrupt();
	}
	//
	public void wakeIclSrv() {
		//
		// Here to just wake up the thread so that it will initiate
		// the harvest process.
		//
	}
	public boolean CreateImageDir (String dateForDir, String imgBaseDir) {
		/*
		String dateDir	= "";
		dateDir		= (imgBaseDir + "outcl"+ fS + 
						dateForDir.substring(0, 4) + fS +
						dateForDir.substring(4, 6) + fS +
						dateForDir.substring(6, 8) + fS);
		*/
		File daysDir	= new File(dateForDir);
		if (!daysDir.exists()) {
			PrintLog("Specified directory does not exist - Creating > "	+ dateForDir);
			File fileOutdir	= new File(dateForDir);
			mkHistDir	= fileOutdir.mkdirs();
			if (mkHistDir == false) {
				PrintLog("Error creating Directory " + mkHistDir);
			}
		}
		return mkHistDir;
	}
	//
	public ArrayList<String> CheckForICLFile(String dbUsed, 
											 String applDateAtStart, String localDir,
											 String localFile, String locOutputDir, 
											 String imgBaseDir) {
		moduleName		= "CheckForICLFile: ";
		PrintLog("File Separator: "+fS);
		String actionFlag	= "LoadHistory";
		String userName		= "DepositLoader";
		iclHistoryProcessed	= false;
		String file2Process	= "";
		String[] fileList;
		String fileDate		= "";
		String dateDir		= "";
		PrintLog("Looking for :" + localDir + localFile);
		//PrintLog("In Directory :" + localDir);
		if (localFile.indexOf("*") >= 0) {
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
		} else if (fileList.length == 0) {
			PrintLog("No New " + localFile + " file to process at this time");
		} else {
			PrintLog("Will check File List " + fileList[0]);
			try {
				for (int idx = 0; idx < fileList.length; idx++) {
					file2Process	= fileList[idx];
					//file2Process	= fileList[0];
					PrintLog("File to process: >" + file2Process + "<");
					findFile		= new File(localDir + file2Process + "_" + applDateAtStart);
					PrintLog("Find Processed File: >" + findFile + "<");
					histFileExists		= findFile.exists();
					if (histFileExists == false) {
						findFile	= new File(localDir + file2Process);
						histFileExists	= findFile.exists();
						if (histFileExists == false) {
							PrintLog("No " + findFile + " File to process at this time");
							continue;
							//return retVals0;
						} else {
							fileDate	= file2Process.substring(file2Process.indexOf("_")+1,
															 file2Process.indexOf("_")+9);
							histIclSource	= file2Process.substring(0,file2Process.indexOf("_")).toUpperCase();
							PrintLog("IclSource: "+histIclSource);
							//if (histIclSource.equals("LOCKBOX")) {
							//	histIclSource	= "LBOX";
							//}
							//depsSelector.setChexSource(iclSource);
							dateDir		= (imgBaseDir + "outcl"+ "/" + 
											fileDate.substring(0, 4) + "/" +
											fileDate.substring(4, 6) + "/" +
											fileDate.substring(6, 8) + "/");
							//if (!fileDate.equals(applDateAtStart)) {
								CreateImageDir(dateDir, imgBaseDir);
							//}
							PrintLog(file2Process + " Exists -- Processing");
							extractDep.runImageloader(localDir, dateDir, 
												  		locOutputDir + histIclSource.toLowerCase() + "/",
												  		file2Process);
							File inputFile	= new File(localDir+file2Process);
							inputFile.renameTo(new File(localDir+file2Process + "_" + applDateAtStart));
							retVals0.clear();
							retVals0.add(0,dateDir);
							retVals0.add(1,fileDate);
							retVals0.add(2,file2Process);
							iclHistoryProcessed	= true;
							uploadDeps.UpLoadDeps(histDbConn, dbUsed, histIclSource,
									fileDate, dateDir, 
									//locOutputDir+histIclSource.toLowerCase() + "/",
									locOutputDir+"deposits/",
									file2Process, histCycleNum, actionFlag,
									userName);
							histCycleNum	= 2;
							Thread.sleep(1000 * 5 * 1); // Sleep for 5 seconds
							/*
							for (int idx1 = 0; idx1 < 3; idx1++) {
								Thread.sleep(1000 * 5); // Sleep for 5 seconds
								if (Thread.currentThread().isInterrupted()) {
									break;
								}
							}
							*/
						}
					} else {
						PrintLog("File " + findFile + " has already been processed ");
						histCycleNum	= 2;
					}
				} // end for
				Runtime.getRuntime().gc();
				//retVals0.clear();
			} catch (InterruptedException e) {
				returnBack (applDateAtStart);
			} catch (Throwable t) {
				PrintLog("EXEC " + t);
			}
		}
		return retVals0;
	}
	//
	public Connection GethistDbConn (String db_driver, String db_url, String db_user, String db_pass) {
		try {
			Class.forName(db_driver);
			histDbConn			= DriverManager.getConnection(db_url, db_user, db_pass);
		} catch (Throwable t) {
			PrintLog("Exception >" + t + "< Terminating Process");
			t.printStackTrace();
			histCodPerformed	= true;
		}
		return histDbConn;
	}
	public void run() {
		moduleName			= "run: ";
		String todays_date	= ValiDate.getTodays_date();
		PrintLog ("Running "+ iclSource0 + " Server");
		EcontServletContextListener escl	= new EcontServletContextListener();
		ControlSelector		ctlSelector	= new ControlSelector();
		ControlDetail		ctlDetailA	= new ControlDetail();
		ControlDetail		ctlDetailD	= new ControlDetail();
		fS						= System.getProperty("file.separator");
		String db_url			= escl.getDbUrl();
		String db_driver		= escl.getDbDriver();
		String db_user			= escl.getDbUser();
		String db_pass			= escl.getDbPass();
		String dbUsed			= escl.getDbUsed();
		//String bankId			= escl.getBankId();
		String prodIdA			= "A";
		String prodIdD			= "D";
		//String prodIdE			= "E";
		String imgBaseDir		= "";
		String locOutputDir		= "";
		String localDir			= "";
		String localFile		= "";
		String prodId			= "";
		String applDate			= "";
		String applDateAtStart	= "";
		try {
			Thread.sleep(1000 * 1); // Sleep for 1 second
			/*
			for (int idx0 = 0; idx0 < 61; idx0++) {
				Thread.sleep(1000 * 1); // Sleep for 1 second
				if (Thread.currentThread().isInterrupted()) {
					returnBack (applDateAtStart);
					return;
				}
				if (histCodPerformed == true) {
					returnBack (applDateAtStart);
					return;
				}
			}
			*/
			db_url			= escl.getDbUrl();
			db_driver		= escl.getDbDriver();
			db_user			= escl.getDbUser();
			db_pass			= escl.getDbPass();
			dbUsed			= escl.getDbUsed();
			Class.forName(db_driver);
			histDbConn			= DriverManager.getConnection(db_url, db_user, db_pass);
			ctlSelector.setDbUsed(dbUsed);
			ctlDetailA.setDbUsed(dbUsed);
			ctlDetailD.setDbUsed(dbUsed);
			ctlUtil.GetControlRow(histDbConn, ctlSelector, prodIdA);
			ctlDetailA		= ctlSelector.getARow();
			ctlUtil.GetControlRow(histDbConn, ctlSelector, prodIdD);
			ctlDetailD		= ctlSelector.getARow();
			localDir		= ctlDetailA.getLocInputDir() + "history" + "/";
			locOutputDir	= ctlDetailA.getLocOutputDir();
			imgBaseDir		= ctlDetailA.getImgBaseDir();
			prodId			= "D";
			applDateAtStart	= ctlDetailD.getApplDate();
			localFile		= "*.icl";
			applDate		= applDateAtStart;
			PrintLog("Starting "+ iclSource0 +" ICL file Server for application date " + applDateAtStart);
		} catch (InterruptedException e) {
			returnBack (applDateAtStart);
			histCodPerformed	= true;
		} catch (Throwable t) {
			PrintLog("Exception >" + t + "< Terminating Process");
			t.printStackTrace();
			histCodPerformed	= true;
		}
		while (histCodPerformed == false) {
			try {
				PrintLog("Starting New Cycle on " + applDateAtStart);
				retVals0	= CheckForICLFile(dbUsed, applDateAtStart, localDir, 
											  localFile, locOutputDir, imgBaseDir);
				if (iclHistoryProcessed == true) {
					Thread.sleep(1000 * 60 * 1); // Sleep for 1 minutes
					/*
					for (int idx = 0; idx < 7; idx++) {
						Thread.sleep(1000 * 1); // Sleep for 5 seconds
						if (Thread.currentThread().isInterrupted()) {
							returnBack (applDateAtStart);
							return;
						}
						if (histCodPerformed == true) {
							returnBack (applDateAtStart);
							return;
						}
					}
					*/
					histCycleNum	= 2;
				}
				if (histCodPerformed == true) {
					break;
				}
				//
				try {
					Thread.sleep(1000 * 60 * 10); // Sleep for 10 minutes
					/*
					for (int idx = 0; idx < 601; idx++) { //600 seconds = 10 minutes
						Thread.sleep(1000 * 1); // Sleep for 1 second
						if (Thread.currentThread().isInterrupted()) {
							returnBack (applDateAtStart);
							return;
						}
						if (histCodPerformed == true) {
							returnBack (applDateAtStart);
							return;
						}
					}
					*/
				} catch (InterruptedException e) {
					returnBack (applDateAtStart);
					return;
				}
				ctlUtil.GetControlRow(histDbConn, ctlSelector, prodId);
				ctlDetailD	= ctlSelector.getARow();
				applDate	= ctlDetailD.getApplDate();
				if (todays_date.compareTo(applDate) < 0) {
					PrintLog("Shutting down Server -- Application date " + applDateAtStart + 
							 " is in the future.");
					PrintLog("Today is :" + todays_date);
					histCodPerformed = true;
					break;
				}
			} catch (InterruptedException e) {
				returnBack (applDateAtStart);
				return;
			} catch (Throwable t) {
				PrintLog(t + "");
				PrintLog("Exception >"+t+"< Terminating Process");
				t.printStackTrace();
				histCodPerformed = true;
				break;
			}
		}
	}
	public void returnBack (String applDateAtStart) {
		try {
			PrintLog ("Closing db Connection");
			histDbConn.close();
		} catch (Exception e) {
			PrintLog ("FAILED Closing db Connection");
			/* Ignored */
		}
		PrintLog("Shutting down for the DAY " + applDateAtStart);
	}
}
