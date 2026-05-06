package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;

//public class InclICLFileServer implements Runnable {
public class InclICLFileServer extends Thread {
	String	className		= "> InclICLFileServer.";
	String	moduleName;
	boolean	inclIclCodPerformed	= false;
	boolean	fileExists		= true;
	boolean	file_copied		= false;
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
	int		inclWaitResult		= 0;
	int inclCtlCount	= 0;
	int inclXmlCount	= 0;
	int inclImgCount	= 0;
	String ctlFileName	= "";
	String xmlFileName	= "";
	String imgFileName	= "";
	//
	String	inclBankId			= null;
	String	inclCycleNum		= "";
	String[] inclCpCmd			= { "/bin/sh", "-c", "/bin/cp " };
	String[] inclGzCmd			= { "/bin/sh", "-c", "/bin/gzip -df " };
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public InclICLFileServer(String FRBFiles_str) {
		super(FRBFiles_str);
	}
	//
	public void stopInclFRBF() {
		inclIclCodPerformed	= true;
		//Thread.currentThread().interrupt();
	}
	//
	public void wakeInclFRBF() {
		//
		// Here to just wake up the thread so that it will initiate
		// the harvest process.
		//
	}
	//
	public void ConstructFileName (String applDateAtStart, String localFile) {
		String lFile	= "";
		String[] fileParts		= new String[10];
		fileParts	= localFile.split("\\.");
		for (int i=0; i<fileParts.length; i++) {
		    //System.out.println(fParts[i]);
		    if (fileParts[i].equals("D*")) {
		    	lFile	+= "D" + applDateAtStart.substring(2,8) + ".T*.";
		    } else {
		    	if (i == (fileParts.length-1)) {
		    		lFile	+= fileParts[i];
		    	} else {
		    		lFile	+= fileParts[i] + ".";
		    	}
		    }
		}
		localFile	= lFile;
		PrintLog("File to Process: "+lFile);
	}
	public boolean CheckIfileProcessed(String fileName) {
		boolean fileProcessed	= false;
		findFile = new File(fileName);
		///home/source/input/frb/micrimage_1_fedchk_ny.img_20080114");
		fileProcessed	= findFile.exists();
		return fileProcessed;
	}
	//
	public void GetFiles(String applDateAtStart, String todaysDir, 
						 String locOutputDir, String localDir, String localFile) {
		moduleName		= "GetFiles: ";
		file_copied		= false;
		String[] fileList;
		String fileExt	= "";
		PrintLog("Looking in :" + localDir);
		PrintLog("for :" + localFile);
		if (localFile.indexOf("*") > 0) {
			ConstructFileName(applDateAtStart, localFile);
			fileExt		= localFile.substring(localFile.lastIndexOf("."),localFile.length());
			PrintLog("File Extension: " + fileExt);
			fileList	= FileUtil.GetFNamesWC(localDir, 
												localFile.substring(0, localFile.indexOf("*")),
												fileExt);
		} else {
			fileList	= FileUtil.GetFileNames(localDir, localFile);
		}
		if (fileList	== null) {
			PrintLog("File " + localFile + " does not exist");
			return;
		} else {
			if (fileList.length	== 0) {
				PrintLog("No New " + localFile + " available for processing");
				return;
			} else {
				PrintLog("Will check File List " + fileList[0]);
			}
		}
		try {
			for (int idx = 0; idx < fileList.length; idx++) {
				PrintLog("Remote File : >" + fileList[idx] + "<");
				if (inclBankId.equals("BNPMO")) {
					//
					// Symcor file then unzip, extract and process the XML file
					//
					if (fileList[idx].indexOf(".zip") > 0) {
						inclCtlCount	= 1;
						inclXmlCount	= 1;
						inclImgCount	= 1;
						PrintLog("Will Unzip and Extract File: " + localDir + fileList[idx]);
						UnzipMontrealFiles(applDateAtStart, fileList[idx], localDir, todaysDir);
								//localDir + remoteFile);
						PrintLog("Number of XML files extracted: " + (inclXmlCount - 1));
						for (int idx2 = 1; idx2 <= inclXmlCount - 1; idx2++) {
							PrintLog("Starting Image Loader for " + todaysDir + imgFileName);
							extractMo.runMontrealLoader(todaysDir, todaysDir + xmlFileName);
						}
					}
					File daysDir	= new File(todaysDir);
					if (!daysDir.exists()) {
						PrintLog("Specified directory does not exist - Creating > " + todaysDir);
						// create the directory needed for the images
						File fileOutdir	= new File(todaysDir);
						boolean mk_dir	= fileOutdir.mkdirs();
						if (mk_dir	== false) {
							PrintLog("Error creating Directory " + mk_dir);
							return;
						}
					}
					if (fileList[idx].substring(0, 4).equals("facs")) {
						PrintLog("Copying file from Montreal Input as ");
						inclCpCmd[2]	= "/bin/cp " + localDir + fileList[idx] + " " + 
											todaysDir +	fileList[idx];
						PrintLog(" inclCpCmd[2]: " + inclCpCmd[2]);
						copyFileProc	= Runtime.getRuntime().exec(inclCpCmd);
						inclWaitResult		= copyFileProc.waitFor();
						File facsFile	= new File(localDir + fileList[idx]);
						facsFile.renameTo(new File(localDir + fileList[idx] + "_" +
										  applDateAtStart));
					}
				} else {
					if (fileList[idx].indexOf("D" + applDateAtStart.substring(2,8),0) < 0) {
						PrintLog(fileList[idx]+" -- Not todays file -- IGNORED");
						if (fileList[idx].indexOf(applDateAtStart,0) > 0) {
							PrintLog("File has already been processed " + fileList[idx]);
						} else {
							File processedIclFile = new File(localDir+fileList[idx]);
							processedIclFile.renameTo(new File(localDir+fileList[idx]+"_"+applDateAtStart));
						}
						continue;
					} else if (fileList[idx].indexOf(applDateAtStart,0) > 0) {
						PrintLog("File has already been processed " + fileList[idx]);
						continue;
					} else if (CheckIfileProcessed(localDir+fileList[idx]+"_"+applDateAtStart)) {
						File processedIclFile = new File(localDir+fileList[idx]);
						processedIclFile.renameTo(new File(localDir+fileList[idx]+"_"+applDateAtStart));
						PrintLog("File has already been processed " + fileList[idx]);
						continue;
					} else { // Found file to process
						File daysDir	= new File(todaysDir);
						if (!daysDir.exists()) {
							PrintLog("Specified directory does not exist - Creating > " + todaysDir);
							// create the directory needed for the images
							File fileOutdir	= new File(todaysDir);
							boolean mk_dir	= fileOutdir.mkdirs();
							if (mk_dir	== false) {
								PrintLog("Error creating Directory " + mk_dir);
								return;
							}
						}
						PrintLog("Starting Image Loader for " + localDir + fileList[idx]);
						extractNy.runExtractImages(localDir, todaysDir, locOutputDir + "gps/",
													fileList[idx]);
					}
				} // end for (idx	= 0; idx < fileList.length; idx++) {
				PrintLog("Directory scan 4 Complete");
				Runtime.getRuntime().gc();
			}
		} catch (Throwable t) {
			PrintLog("EXEC " + t);
		}
	}
	//
	public void UnzipMontrealFiles(String applDateAtStart, String remoteFile, String localDir,
					String todaysDir) {
		moduleName			= "UnzipMontrealFiles: ";
		Enumeration<?> entries;
		ZipFile zipFile;
		//
		int entriesInZip	= 0;
		String unzippedFileName	= "";
		String extractedFile	= "";
		try {
			zipFile			= new ZipFile(localDir+remoteFile);
			entries			= zipFile.entries();
			PrintLog("Will Unzip file :" + localDir+remoteFile);
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
						extractedFile	= unzippedFileName.replaceAll(".CNTL", "");
						File ctlFile	= new File(localDir + unzippedFileName);
						//ctlFile.renameTo(new File(localDir + extractedFile + ".ctl"));
						ctlFile.renameTo(new File(todaysDir + extractedFile + ".ctl"));
						PrintLog("Renamed " + localDir + unzippedFileName +
								" to " + todaysDir + extractedFile + ".ctl");
						ctlFileName	= extractedFile + ".ctl";
						PrintLog("ctlFileName " + ctlFileName);
						inclCtlCount++;
					} else if (unzippedFileName.indexOf("INDX") > 0) {
						extractedFile	= unzippedFileName.replaceAll(".INDX", "");
						File xmlFile	= new File(localDir + unzippedFileName);
						//xmlFile.renameTo(new File(localDir + extractedFile + ".xml"));
						xmlFile.renameTo(new File(todaysDir + extractedFile + ".xml"));
						PrintLog("Renamed " + localDir + unzippedFileName +
								" to " + todaysDir + extractedFile + ".xml");
						xmlFileName	= extractedFile + ".xml";
						PrintLog("xmlFileName " + xmlFileName);
						inclXmlCount++;
					}
					if (unzippedFileName.indexOf("DATA") > 0) {
						extractedFile	= unzippedFileName.replaceAll(".DATA", "");
						File imgFile	= new File(localDir + unzippedFileName);
						//imgFile.renameTo(new File(localDir + extractedFile + ".img"));
						imgFile.renameTo(new File(todaysDir + extractedFile + ".img"));
						PrintLog("Renamed " + localDir + unzippedFileName +
								" to " + todaysDir + extractedFile + ".img");
						imgFileName	= extractedFile + ".img";
						PrintLog("imgFileName " + imgFileName);
						inclImgCount++;
					}
				}
			}
			PrintLog("Extracted " + entriesInZip + " files from " + remoteFile);
			PrintLog("Will rename File : " + localDir + remoteFile);
			File xmlFile	= new File(localDir+remoteFile);
			xmlFile.renameTo(new File(localDir+remoteFile + "_" + applDateAtStart));
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
		//
		ControlSelector  ctlSelector		= new ControlSelector();
		ControlDetail	 ctlDetail			= new ControlDetail();
		String	db_url			= null;
		String	db_driver		= null;
		String	db_user			= null;
		String	db_pass			= null;
		String	dbUsed			= null;
		String	facsFileName	= "";
		String	locInputDir		= "";
		String	locOutputDir	= "";
		String	imgBaseDir		= "";
		String	inclImgFile		= "";
		String	inclMicrFile	= "";
		String	prodId			= "C";
		String	localDir		= "";
		String	localFile		= "";
		String	outputPath		= "";
		String	todaysDir		= "";
		String	appl_date		= "";
		String	applDateAtStart	= "";
		String todays_date		= ValiDate.getTodays_date();
		Connection	   dbConn	= null;
		EcontServletContextListener escl	= new EcontServletContextListener();
		try {
			Thread.sleep(1000 * 60 * 1); // Sleep for 1 minute
			/*
			for (int idx = 0; idx < 61; idx++) {
				Thread.sleep(1000 * 1); // Sleep for 1 seconds
				if (Thread.currentThread().isInterrupted()) {
					returnBack (dbConn, applDateAtStart);
					return;
				}
				if (inclIclCodPerformed == true) {
					returnBack (dbConn, applDateAtStart);
					return;
				}
			}
			*/
			PrintLog("Starting ICLFileServer");
			db_url				= escl.getDbUrl();
			db_driver			= escl.getDbDriver();
			db_user				= escl.getDbUser();
			db_pass				= escl.getDbPass();
			dbUsed				= escl.getDbUsed();
			inclBankId			= escl.getBankId();
			Class.forName(db_driver);
			dbConn				= DriverManager.getConnection(db_url, db_user, db_pass);
			ctlDetail.setDbUsed(dbUsed);
			prodId				= "A";
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail			= ctlSelector.getARow();
			applDateAtStart	= ctlDetail.getApplDate();
			appl_date			= applDateAtStart;
			// PrintLog("Appl Date "+appl_date);
			locInputDir			= ctlDetail.getLocInputDir();	// /home/source/input/
			locOutputDir		= ctlDetail.getLocOutputDir();	// /home/source/output/
			imgBaseDir			= ctlDetail.getImgBaseDir();	// /home/andy/eController/images/
			inclImgFile			= ctlDetail.getInclImgFile();	// CEPF0.DAT2.FWD.R0260.N0804.DYYMMDD.T04401.dat
			inclMicrFile		= ctlDetail.getInclMicrFile();	// facs*.zip
			outputPath			= imgBaseDir;
			/*
			*/
			if (inclBankId.equals("BNPMO")) {
				facsFileName	= inclMicrFile;
			}
			todaysDir	= (outputPath + "incl/" + applDateAtStart.substring(0, 4) + "/" +
							applDateAtStart.substring(4, 6) + "/" +
							applDateAtStart.substring(6, 8) + "/");
			PrintLog("Starting InclICLFileServer for application date " + applDateAtStart);
		} catch (InterruptedException e) {
			returnBack (dbConn, applDateAtStart);
			return;
		} catch (Throwable t) {
			PrintLog("Exception >" + t + "< Terminating Process");
			inclIclCodPerformed	= true;
		}
		if (inclIclCodPerformed	== false) {
			prodId				= "C";
			ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail			= ctlSelector.getARow();
			//bod_flag			= ctlDetail.getBodFlag();
			applDateAtStart	= ctlDetail.getApplDate();
			appl_date			= applDateAtStart;
		}
		while (!Thread.currentThread().isInterrupted()) {
			try {
				PrintLog("Starting New Cycle on " + applDateAtStart);
				localDir	= locInputDir + "frb/";
				localFile	= inclImgFile;
				GetFiles(applDateAtStart, todaysDir, 
						 locOutputDir, localDir, localFile);
				if (inclBankId.equals("BNPMO")) {
					localFile	= facsFileName;
					GetFiles(applDateAtStart, todaysDir, locOutputDir, localDir, localFile);
				}
				if (file_copied	== true) {
					Thread.sleep(1000 * 60 * 1); // Sleep for 1 minute
					/*
					for (int idx = 0; idx < 31; idx++) {
						Thread.sleep(1000 * 1); // Sleep for 1 second
						if (Thread.currentThread().isInterrupted()) {
							returnBack (dbConn, applDateAtStart);
							return;
						}
						if (inclIclCodPerformed == true) {
							returnBack (dbConn, applDateAtStart);
							return;
						}
					}
					*/
				}
				if (inclIclCodPerformed	== true) {
					break;
				}
				try {
					Thread.sleep(1000 * 60 * 5); // Sleep for 5 minutes
					/*
					for (int idx = 0; idx < 301; idx++) { // 300 seconds = 5 minutes
						Thread.sleep(1000 * 1); // Sleep for 1 second
						if (Thread.currentThread().isInterrupted()) {
							returnBack (dbConn, applDateAtStart);
							return;
						}
						if (inclIclCodPerformed == true) {
							returnBack (dbConn, applDateAtStart);
							return;
						}
					}
					*/
				} catch (InterruptedException e) {
					returnBack (dbConn, applDateAtStart);
					return;
				}
				ctlDetail.setDbUsed(dbUsed);
				ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
				ctlDetail	= ctlSelector.getARow();
				appl_date	= ctlDetail.getApplDate();
				//
				if (todays_date.compareTo(appl_date) < 0) {
					PrintLog("Shutting down -- Application date " +
							 applDateAtStart + " is in the future.");
					PrintLog("Today is :" + todays_date);
					inclIclCodPerformed	= true;
					break;
				}
			} catch (InterruptedException e) {
				returnBack (dbConn, applDateAtStart);
				return;
			} catch (Throwable t) {
				PrintLog(t + "");
				inclIclCodPerformed	= true;
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
