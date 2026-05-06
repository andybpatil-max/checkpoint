package com.webfiche.checkpoint.util;

import java.io.*;
import java.util.Map;

public class DelFiles {
	boolean		  delFlag;
	static final int BUFFER	 = 2048;
	static String	className  = "> DelFiles.";
	static String	moduleName = "main: ";
	/**/
	String		   imageDir;
	String		   zipDir;
	String		   holdDir;
	String		   procDate;

	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	public void DeleteFiles() {
		imageDir	= (String) ((Map<String, String>) (System.getenv())).get("imagedir");
		try {
			File f = new File(imageDir);
			String files[] = f.list();
			if (files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f2Del	= new File(imageDir + files[i]);
					try {
						PrintLog("WILL DELETE: " + imageDir + files[i]);
						delFlag	= false;
						if (!f2Del.delete()) {
							String message	= f2Del.exists() ? "is in use by another app" : "does not exist";
							throw new IOException("Cannot delete file, because file " + message + ".");
						}
						// while (delFlag == false) {
						// delFlag = f2Del.delete();
						// }
						if (delFlag == true) {
							PrintLog("Removed " + imageDir + files[i]);
						} else {
							PrintLog("Not Removed " + " " + delFlag);
						}
						// } catch (Throwable t) {
					} catch (SecurityException t) {
						t.printStackTrace();
					}
				}
			} else {
				PrintLog("No Image Files to Process at this time");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DelFiles delFiles	= new DelFiles();
		delFiles.DeleteFiles();
	}
}
