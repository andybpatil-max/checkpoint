package com.webfiche.checkpoint.util;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.zip.*;
//import java.util.Map;

public final class ZipFilesUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	boolean		  delFlag;
	static final int BUFFER		= 2048;
	static String	className	= "> ZipFiles.";
	static String	moduleName	= "main: ";
	/**/
	//String		   imageDir;
	//String		   zipDir;
	//String		   holdDir;
	String		   procDate;

	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public void ZipFiles(String imageDir, String zipDir, String zipFileName) {
		/* imageDir	= (String) ((Map<String, String>) (System.getenv())).get("imagedir");
		 * zipDir		= (String) ((Map<String, String>) (System.getenv())).get("zipdir");
		 * holdDir		= (String) ((Map<String, String>) (System.getenv())).get("holddir");
		 * DelFiles delFiles = new DelFiles();
		 *
		 * String imageDir = (String)((Map)(System.getenv())).get("imagedir");
		 * String zipDir = (String)((Map)(System.getenv())).get("zipdir");
		 * String holdDir = (String)((Map)(System.getenv())).get("holddir");
		 *
		 *
		 * PrintLog((String)((Map)(System.getenv())).get("imagedir"));
		 * PrintLog((String)((Map)(System.getenv())).get("zipdir"));
		 * PrintLog((String)((Map)(System.getenv())).get("holddir"));
		 */
		GregorianCalendar calendar	= new GregorianCalendar();
		DateFormat format			= DateFormat.getInstance();
		format						= new SimpleDateFormat("yyyyMMdd_HHmmss");
		String procDate				= format.format(calendar.getTime());
		try {
			BufferedInputStream origin	= null;
			FileOutputStream dest	= new FileOutputStream(zipDir + zipFileName+ "_" +
														   procDate + ".zip");
			ZipOutputStream out		= new ZipOutputStream(new BufferedOutputStream(dest));
			byte data[]				= new byte[BUFFER];
			String[] files	= FileUtil.GetTiffImageFiles(imageDir);
			if ((files != null) && (files.length > 0)) {
				for (int i = 0; i < files.length; i++) {
					PrintLog("Adding to zip: " + imageDir + files[i]);
					/*
					File fileToRename = new File(imageDir + files[i]);
					fileToRename.renameTo(new File(zipDir + files[i] + "_processed"));
					*/
					FileInputStream fi	= new FileInputStream(imageDir + files[i]);
					origin				= new BufferedInputStream(fi, BUFFER);
					ZipEntry entry		= new ZipEntry(files[i]);
					// PrintLog("ZipEntry: "+files[i]);
					out.putNextEntry(entry);
					int count;
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					origin.close();
					// fi.close();
					File f2Del = new File(imageDir + files[i]);
					// PrintLog("WILL DELETE: "+imageDir+files[i]);
					try {
						delFlag = f2Del.delete();
						while (delFlag == false) {
							if (i == files.length-1) {
								PrintLog("Closing zip file ");
								out.close();
							}
							origin.close();
							//PrintLog("Removed " + imageDir + files[i]);
							Thread.sleep(1000 * 60 * 1); // Sleep for 1 MINUTE
							PrintLog("Not Removed will RETRY");
							delFlag = f2Del.delete();
						}
						PrintLog("Removed " + imageDir + files[i]);
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
				out.close();
			} else {
				if (files.length == 0) {
					PrintLog("No Image Files to Process at this time");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//PrintLog("WILL EXECUTE DELETE: ");
		//delFiles.DeleteFiles();
	}
}
