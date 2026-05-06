package com.webfiche.checkpoint.util;

import java.io.*;

/*import java.util.*;
 import java.text.*;
 import java.util.zip.*;
 */

public final class ZipUnzipUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	static String className  = "> ZipUnzipUtil.";
	static String moduleName = "";
	/**/
	// String imageDir;
	// String zipDir;
	// String holdDir;
	String		procDate;
	ProcessUtil   pUtil	= new ProcessUtil();

	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	public boolean CopyUnZip(String fileDir, String fileName) {
		moduleName		= "CopyUnZip: ";
		String[] gzCmd	= { "/bin/sh ", "-c ", " " };
		String[] cpCmd	= { "/bin/sh ", "-c ", " " };
		String shCmd	= "";
		boolean fileCopiedUnzipped = false;
		try {
			cpCmd[2]	= "/bin/cp  " + fileDir + fileName + ".gz " + fileDir + fileName + ".gz_copy";
			shCmd		= cpCmd[2];
			// shCmd = cpCmd[0] + cpCmd[1] + cpCmd[2];
			// PrintLog(" shCmd: " + shCmd);
			if (pUtil.ProcessCommand(shCmd)) {
				fileCopiedUnzipped = true;
			} else {
				PrintLog("Problem in Copy file please check Log: Code ");
			}
			gzCmd[2]	= "/bin/gzip -df " + fileDir + fileName + ".gz";
			// + " > " + fileDir + fileName;
			// shCmd = gzCmd[0] + gzCmd[1] + gzCmd[2];
			shCmd		= gzCmd[2];
			/**/
			PrintLog(" shCmd: " + shCmd);
			if (pUtil.ProcessCommand(shCmd)) {
				fileCopiedUnzipped = true;
			} else {
				PrintLog("Problem in Copy/Unzip file please check Log: Code ");
			}
			File gzCopyFile	= new File(fileDir + fileName + ".gz_copy");
			gzCopyFile.renameTo(new File(fileDir + fileName + ".gz"));
			/**/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (fileCopiedUnzipped);
	}
	//
	public boolean GzipAndCopy (String fileDir, String fileName, String destDir) {
		/*
		 * gzip the file in the fileDir and copy to the destination directory (destDir)
		 */
		moduleName		= "GzipAndCopy: ";
		String[] gzCmd	= { "/bin/sh ", "-c ", " " };
		String[] cpCmd	= { "/bin/sh ", "-c ", " " };
		String shCmd	= "";
		boolean fileGzipped	= false;
		try {
			gzCmd[2]	= "/bin/gzip -c " + fileDir + fileName;
			shCmd		= gzCmd[2];
			PrintLog(" shCmd: " + shCmd);
			if (pUtil.ProcessCommand(shCmd)) {
				fileGzipped	= true;
			} else {
				PrintLog("Problem in Copy/Unzip file please check Log: Code ");
			}
			cpCmd[2]	= "/bin/cp  " + fileDir + fileName + ".gz " + destDir + fileName + ".gz";
			shCmd		= cpCmd[2];
			// PrintLog(" shCmd: " + shCmd);
			if (pUtil.ProcessCommand(shCmd)) {
				fileGzipped = true;
			} else {
				PrintLog("Problem Copying file please check Log: Code ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (fileGzipped);
	}
	//
	public boolean CopyFile (String fileDir, String fileName, String destDir,
							String destFileName) {
		/*
		 * Copy the source dir (fileDir) a file named fileName
		 * to destination dir (destDir) as destFileName
		 */
		moduleName		= "CopyFile: ";
		String[] cpCmd	= { "/bin/sh ", "-c ", " " };
		String shCmd	= "";
		boolean fileCopied	= false;
		try {
			cpCmd[2]	= "/bin/cp  " + fileDir + fileName + ".gz " + destDir + destFileName + ".gz";
			shCmd		= cpCmd[2];
			// PrintLog(" shCmd: " + shCmd);
			if (pUtil.ProcessCommand(shCmd)) {
				fileCopied = true;
			} else {
				PrintLog("Problem Copying file please check Log: Code ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (fileCopied);
	}
	//
	public boolean GzipFile (String fileName, String destDir) {
		/*
		 * Gzip the file named fileName in the destDir
		 */
		moduleName		= "GzipFile: ";
		String[] gzCmd	= { "/bin/sh ", "-c ", " " };
		String shCmd	= "";
		boolean fileGzipped	= false;
		try {
			gzCmd[2]	= "/bin/gzip -c " + destDir + fileName;
			shCmd		= gzCmd[2];
			PrintLog(" shCmd: " + shCmd);
			if (pUtil.ProcessCommand(shCmd)) {
				fileGzipped	= true;
			} else {
				PrintLog("Problem in Copy/Unzip file please check Log: Code ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (fileGzipped);
	}
	//
	public boolean ConcatenateFiles (String destDir, String fileName, String cattedFile) {
		/*
		 * Concatenate file named fileName in the destDir to 
		 * file named cattedFile in destDir
		 */
		moduleName			= "ConcatenateFiles: ";
		boolean fileCatted	= false;
		File file			= new File(destDir+cattedFile);
		try {
			FileWriter output		= new FileWriter(file, true);
			BufferedReader bReader	= new BufferedReader(new FileReader(destDir+fileName));
			String line;
			while ((line = bReader.readLine())!=null ) {
				//PrintLog(line);
				output.write(line+"\n");
			}
			bReader.close();
			output.close();
		} catch (Throwable t) {
			PrintLog("error "+t);
		}
		return (fileCatted);
	}
	//
	public static final void main(String[] args) {
		ZipUnzipUtil cUnz	= new ZipUnzipUtil();
		cUnz.CopyUnZip("/opt/reports/ny/2003/200308/20030804/nyaaaa01/",
				"benerpt_kapiti_ny.dat");
		// cUnz.CopyUnZip("/usr/local/econtr/misc/unzip/", "test.dat");
	}
}
