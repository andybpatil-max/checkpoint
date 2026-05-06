package com.webfiche.checkpoint.actions;

import java.util.*;
import java.text.*;
import java.util.zip.*;
import java.sql.*;
import java.io.*;

import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.deposits.beans.*;
import com.webfiche.checkpoint.deposits.service.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;

//
public class ExtractDepsImages {
	String	calledFrom;
	String	moduleName;
	boolean	codPerformed	= false;
	//
	String	db_url			= "";
	String	db_driver		= "";
	String	db_user			= "";
	String	db_pass			= "";
	String	chexSource		= "";
	String	dbTable			= "";
	String	dbLogTable			= "";
	//
	int		rowCount		= 0;
	int		errCode			= 0;
	int		retCode			= 0;
	int		locDot			= 0;
	int		checkSide		= 0;
	int		firstUnderscore	= 0;
	int		lastUnderscore	= 0;
	int		len				= 0;
	long	maxOSN			= 999999999999L;
	long	depOSN			= 0;
	//
	byte[]	buffer			= new byte[1024];
	byte[]	buffer1			= null;
	String	applDate		= "";
	String	applDateAtStart	= "";
	String	ourAba			= "";
	String	bodFlag			= "";
	String[]checkFields;
	String	checkCur		= "";
	// MICR Split
	String	checkMICRAba	= "";
	String	checkMICRAct	= "";
	String	checkMICRNum	= "";
	//
	String	checkBatch		= "";
	String	checkProcDate	= "";
	String	checkNum		= "";
	String	checkDate		= "";
	String	checkPayeeAcct	= "";
	String	checkPayee		= "";
	String	checkAmt		= "";
	String	checkMICR		= "";
	//String	checkAmtSc		= "";
	//String	checkAbaSc		= "";
	//String	checkDateSc		= "";
	//String	checkMICRSc		= "";
	//String	checkNumSc		= "";
	//String	checkPayeeSc	= "";
	String	procDate		= "";
	String	inputDir		= "";
	String	imgDir			= "";
	String	outputDir		= "";
	String	micrStr			= "";
	String	amtStr			= "";
	String	scoreStr		= "";
	String	tag270Str		= "";
	String	dbUsed			= "";
	String	ec_imageDir		= "";
	String	ec_image_file	= "";
	String[]file_info		= { "", "", "", "", "", "", "", "", "",	""};
	String	nodeName		= "";
	String	outputPath		= "";
	String	outputMICRPath	= "";
	String	REGEX			= "\\s";
	String	outDir			= "";
	String	jpegFile		= "";
	String	fileName		= "";
	String	checkFileName	= "";
	//String	micrFileName	= "";
	String	newFileName		= "";
	String	fileNum			= "";
	String	zerosStr		= "00000000000000000000";
	String	temp			= "";
	String	imageFileName	= "";
	//String	homeDir			= "";
	//String	convFilesList	= "";
	ArrayList<String>	micrFields		= new ArrayList<String>();
	DecimalFormat		df				= new DecimalFormat("000000000000");
	//
	Connection			dbConn;
	DepsChexUtil		depsUtil		= new DepsChexUtil();
	SysadControlUtil	ctlUtil			= new SysadControlUtil();
	DepsDetail			depsDetail		= new DepsDetail();
	ControlDetail		ctlDetail		= new ControlDetail();
	ControlSelector		ctlSelector		= new ControlSelector();
	JPEGfromTIFF		jpTiff			= new JPEGfromTIFF();
	DataInTiff			tiffData		= new DataInTiff();
	MicrDecode			micrDecode		= new MicrDecode();
	// ActionErrors errors = new ActionErrors();
	String				userId			= "ImageLoader";
	FileOutputStream	outWrite		= null;
	OutputStream		out				= null;
	//FileWriter			micrFile;
	FileWriter			convFile;
	BufferedReader		br1;
	String[]			tag270;
	String[]			amtSplit;
	String[]			fieldSplit;
	String[]			scoreSplit;

	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
				"> ExtractDepsImages." + moduleName + logMsg);
	}
	//
	public void ProcessFiles() {
		moduleName		= "ProcessFiles: ";
		//micrFileName	= imageFileName.substring(0, imageFileName.indexOf(".")) + ".mcr";
		//homeDir			= (String) ((Map<?, ?>) (System.getenv())).get("HOME");
		//convFilesList	= homeDir + "/scripts/ConvertImages.lis";
		Enumeration<?> entries;
		ZipFile zipFile;
		int entriesInZip	= 0;
		//
		outDir			= imgDir;
		File output_dir	= new File(outDir);
		if (!output_dir.exists()) {
			PrintLog("Directory " + outDir + " does not exist.");
			File file_outdir	= new File(outDir);
			boolean mk_dir		= file_outdir.mkdirs();
			if (mk_dir == false) {
				PrintLog("Error creating Directory " + mk_dir);
				return;
			}
		}
		//
		try {
			PrintLog("Will Unzip file :" + inputDir + imageFileName);
			zipFile	= new ZipFile(inputDir + imageFileName);
			entries	= zipFile.entries();
			if (depOSN > maxOSN) {
				depOSN	= 0;
			}
			while (entries.hasMoreElements()) {
				ZipEntry entry	= (ZipEntry) entries.nextElement();
				InputStream in	= zipFile.getInputStream(entry);
				int dataLength	= in.available();
				checkFileName	= entry.getName();
				locDot			= checkFileName.indexOf(".");
				// PrintLog("x "+x+"   formatted "+df.format(x));
				//newFileName	= ("Check_" + fileName.substring(firstUnderscore, lastUnderscore));
				// PrintLog("File Name "+checkFileName);
				// Check if the image file is of front of the check
				if (checkFileName.substring(locDot - 1, locDot).equals("A")) {
					//
					buffer1	= new byte[dataLength];
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					// PrintLog("In Stream Length "+in.available());
					while ((len = in.read(buffer1)) >= 0) {
						baos.write(buffer1, 0, len);
					}
					tag270Str	= tiffData.GetTiffField(baos.toByteArray(), 270);
					tag270		= tag270Str.split("\\ ");
					// 1.	Batch
					// 2.	Proc Date
					// 3.	Check Number
					// 4.	Issue Date
					// 5.	Payee Account
					// 6.	Payee Name
					// 7.	Check MICR
					checkBatch	= tag270[0];
					fieldSplit	= checkBatch.split("\\=");
					if (fieldSplit.length > 0) {
						checkBatch	= fieldSplit[1];
					}
					checkProcDate	= tag270[1];
					fieldSplit	= checkProcDate.split("\\=");
					if (fieldSplit.length > 0) {
						checkProcDate	= fieldSplit[1];
					}
					checkNum		= tag270[2];
					fieldSplit	= checkNum.split("\\=");
					if (fieldSplit.length > 0) {
						checkNum	= fieldSplit[1];
					}
					checkDate		= tag270[3];
					fieldSplit	= checkDate.split("\\=");
					if (fieldSplit.length > 0) {
						checkDate	= fieldSplit[1];
					}
					checkPayeeAcct	= tag270[4];
					fieldSplit	= checkPayeeAcct.split("\\=");
					if (fieldSplit.length > 0) {
						checkPayeeAcct	= fieldSplit[1];
					}
					checkPayee		= tag270[5];
					fieldSplit	= checkPayee.split("\\=");
					if (fieldSplit.length > 0) {
						checkPayee	= fieldSplit[1];
					}
					checkAmt		= tag270[6];
					fieldSplit	= checkAmt.split("\\=");
					if (fieldSplit.length > 0) {
						checkAmt	= fieldSplit[1];
					}
					checkMICR	= tag270[7];
					micrStr		= checkMICR;
					micrStr		= micrStr.replaceAll("\\@_", "_");
					micrStr		= micrStr.replaceAll("\\@_", "_");
					micrStr		= micrStr.replaceAll("_\\@", "_");
					micrStr		= micrStr.replaceAll("\\@", "_");
					micrStr		= micrStr.replaceAll("\\$", "");
					micrStr		= micrStr.replaceAll("__", "_");
					micrStr		= micrStr.replaceAll("_", "");
					PrintLog("MicrStr: " + micrStr);
					micrFields		= micrDecode.GetMicr(micrStr);
					checkMICRNum	= micrFields.get(0);
					checkMICRAba	= micrFields.get(1);
					checkMICRAct	= micrFields.get(2);
					// checkAct = zerosStr.substring(0,12-checkAct.length()) + checkAct;
					if (checkMICRNum.equals("0") && checkMICRAba.equals("0") && checkMICRAct.equals("0")) {
						fileName	= micrStr.replaceAll("MICR=", "");
						fileName	= fileName.replaceAll("<", "_");
						fileName	= fileName.replaceAll(";", "_");
						fileName	= fileName.replaceAll("=", "_");
						fileName	= fileName.replaceAll("__", "_");
						fileName	= fileName.replaceAll("__", "_");
						PrintLog("fileName: " + fileName);
					} else {
						// fileName = micrFields.get(0)+"_"+micrFields.get(1)+"_"+micrFields.get(2);
						fileName	= checkMICRNum + "_" + checkMICRAba + "_" + checkMICRAct;
					}
					// micrStr	= micrFields.get(0) + "\t" + micrFields.get(1) + "\t" + micrFields.get(2);
					micrStr	= checkMICRNum + "\t" + checkMICRAba + "\t" + checkMICRAct;
					if (micrFields.size() == 4) {
						micrStr	= micrStr + "\t" + micrFields.get(3) + "\t"	+ "999";
					} else {
						if (!amtStr.equals("")) {
							micrStr	= micrStr + "\t" + amtStr + "\t" + scoreStr;
						}
					}
					//micrStr	= micrStr.replaceAll("_","\t");
					//micrStr	= micrStr.trim() + "\t" + "Check_" + fileName + "\n";
					//micrFile.write(micrStr);
					//checkSide++;
					newFileName	= "Check_" + fileName + "_front";
					//convFile.write(outDir + newFileName + ".tif" + '\n');
					CreateCheckRow();
				} else {
					newFileName	= "Check_" + fileName + "_back";
					//convFile.write(outDir + newFileName + ".tif" + '\n');
					// checkSide++;
				}
				PrintLog("Extracting file: " + checkFileName + " to " + newFileName);
				// PrintLog("Will Create:     " + newFileName+".tif");
				//newFileName		= outDir + newFileName;
				copyInputStream(zipFile.getInputStream(entry),
								new BufferedOutputStream(new FileOutputStream(newFileName + ".tif")));
				jpTiff.doTiff2JPEG(outDir, newFileName, newFileName + ".tif");
				entriesInZip++;
			}
			//
			// Here run convert
			//
			/*
			 * PrintLog("OutDir: "+outDir);
			 * ProcessBuilder pb	= new
			 * ProcessBuilder("bash", "-c", "$HOME/scripts/tifcon "+outDir);
			 * Process p	= pb.start();
			 * br1			= new BufferedReader(new
			 * InputStreamReader(p.getInputStream()));
			 * errCode		= p.waitFor();
			 * while (true) {
			 * 		String s = br1.readLine();
			 * 		if (s==null) break;
			 * 		System.out.println ("String s "+s);
			 * }
			 * br1.close();
			 * PrintLog("ErrCode: "+errCode);
			 */
			/*
			 * This works too!!!!
			 * convFile.close();
			 * String[] cmd	= { "bash", "-c", "$HOME/scripts/tifcon " + convFilesList };
			 * // String[] cmd= {"bash", "-c", "$HOME/scripts/tifcon "+outDir};
			 * Process proc	= Runtime.getRuntime().exec(cmd);
			 * br1				= new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 * errCode			= proc.waitFor();
			 * PrintLog("ErrCode: " + errCode);
			 * while (true) {
			 * 	String s	= br1.readLine();
			 * 	if (s == null)
			 * 		break;
			 * 	System.out.println("String s " + s);
			 * }
			 * br1.close();
			 */
			//micrFile.close();
			File imageFileRename	= new File(inputDir + imageFileName);
			imageFileRename.renameTo(new File(inputDir + imageFileName + "_" + applDate));
			//
			//File micrFileRename		= new File(imgDir + micrFileName + "_temp");
			//micrFileRename.renameTo(new File(imgDir + micrFileName));
			PrintLog("Extracted " + entriesInZip + " files from " + imageFileName);
		} catch (Throwable t) {
			PrintLog(" EXEC " + t);
			t.printStackTrace();
		}
	}
	//
	public void CreateCheckRow() throws Exception {
		calledFrom		= moduleName;
		moduleName		= "CreateCheckRow: ";
		depOSN++;
		if (depOSN > maxOSN) {
			depOSN	= 0;
		}
		depsDetail.clearDetails();
		depsDetail.setChex_proc_date(checkProcDate);
		depsDetail.setChex_orig_account_num(checkMICRAct);
		depsDetail.setChex_orig_check_num(checkMICRNum);
		depsDetail.setChex_account_num(checkMICRAct);
		depsDetail.setChex_check_num(checkMICRNum);
		depsDetail.setChex_routing_transit(checkMICRAba);
		depsDetail.setChex_check_currency("USD");
		depsDetail.setChex_check_amount(checkAmt);
		depsDetail.setChex_unique_isn(depOSN+"");
		depsDetail.setChex_orig_inst_rte(ourAba);
		depsDetail.setChex_isn(checkBatch);
		depsDetail.setChex_issue_date(checkDate);
		depsDetail.setChex_extra_1(checkPayeeAcct);
		depsDetail.setChex_payee(checkPayee);
		depsDetail.setChex_check_status("Z");
		depsDetail.setChex_amount_od("0.00");
		depsDetail.setChex_image(newFileName);
		try {
			retCode	= depsUtil.InsertUpdateDeps(dbConn, dbTable, dbLogTable, depsDetail, 1);
		} catch (Throwable t) {
			PrintLog("Error Updating to Control table" + t);
			t.printStackTrace();
		}
		moduleName		= calledFrom;
	}
	public final void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		moduleName		= "copyInputStream.";
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}
		//
		in.close();
		out.close();
	}
	//
	public void RunDepsLoader(String inDir, String imageDir, String outDir, String imageFile) {
		moduleName			= "RunDepsLoader: ";
		codPerformed		= false;
		String todays_date	= ValiDate.getTodays_date();
		String prodId		= "D";
		int insOrUpd		= 1;
		String userId		= "FileServer";
		inputDir			= inDir;
		outputDir			= outDir;
		imgDir				= imageDir;
		imageFileName		= imageFile;
		chexSource			= imageFileName.substring(0,1);
		if (chexSource.equals("R")) {
			chexSource	= "RDC";
		} else {
			chexSource	= "LBOX";
		}
		dbTable		= "DEPS_CHEX";
		dbTable		= "DEPS_CHEXLOG";
		// PrintLog("Starting Deposit/Collection Chex Image Loader");
		EcontServletContextListener escl	= new EcontServletContextListener();
		if (codPerformed == false) {
			db_url		= escl.getDbUrl();
			db_driver	= escl.getDbDriver();
			db_user		= escl.getDbUser();
			db_pass		= escl.getDbPass();
			nodeName	= escl.getNodeName();
			dbUsed		= escl.getDbUsed();
			PrintLog("Starting Deposits Image Loader for DB " + dbUsed +
					 " Input file: " + imageFile);
			try {
				Class.forName(db_driver);
				dbConn	= DriverManager.getConnection(db_url, db_user, db_pass);
			} catch (Throwable t) {
				PrintLog("Error Loading Driver or Connecting to DB" + t);
			}
			try {
				ctlDetail.setDbUsed(dbUsed);
				rowCount		= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
				ctlDetail		= ctlSelector.getARow();
				bodFlag			= ctlDetail.getBodFlag();
				depOSN			= ctlDetail.getDepOSN();
				applDateAtStart	= ctlDetail.getApplDate();
				ourAba			= ctlDetail.getOurAba();
				// Save the cycle number
				//
				applDate		= applDateAtStart;
				PrintLog("Starting Deposits Image Loader on " + applDate);
			} catch (Throwable t) {
				PrintLog("Exception getting control Row " + t);
				// this will terminate the process
				codPerformed	= true;
			}
			if (todays_date.compareTo(applDate) < 0) {
				PrintLog("Shutting down -- Application date " + applDateAtStart +
						 " is in the future.");
				PrintLog("Today is :" + todays_date);
				codPerformed	= true;
			}
		}
		if (codPerformed == false) {
			ProcessFiles();
			ctlDetail.setDepOSN(depOSN);
			try {
				rowCount	= ctlUtil.InsertUpdateControl(dbConn, ctlDetail,
														  userId, insOrUpd);
			} catch (Throwable t) {
				PrintLog("Error Updateing to Control table" + t);
				t.printStackTrace();
			}
		}
		PrintLog("Completed Processing Deposits on " + applDateAtStart);
	}
}
