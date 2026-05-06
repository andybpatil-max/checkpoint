package com.webfiche.checkpoint.util;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.Map;

public class GetDataFromTiff {
	private String className	= "> GetDataFromTiff.";
	private String moduleName	= "";
	//
	//private String tag270	= "";
	//
	public GetDataFromTiff() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public void ExtractData () throws IOException {
		moduleName		= "ExtractData: ";
		ZipFilesUtil zF	= new ZipFilesUtil();
		String imageDir	= (String) ((Map<String, String>) (System.getenv())).get("imagedir");
		String zipDir	= (String) ((Map<String, String>) (System.getenv())).get("zipdir");
		GregorianCalendar calendar	= new GregorianCalendar();
		DateFormat format			= DateFormat.getInstance();
		//DataInTiff tiffData			= new DataInTiff();
		format						= new SimpleDateFormat("yyyyMMdd_HHmmss");
		String procDate				= format.format(calendar.getTime());
		//byte[] newText;
		/*
		 * Variable to hold path of image that needs to be processed
		 */
		// Timeout variable (in milliseconds) to be used in the GetResult and
		// ScrOpenChannelExt functions
		FileOutputStream writeCARrep	= new FileOutputStream(imageDir + "CARReport_" + procDate + ".txt");
		PrintLog("CAR report :" + imageDir + "CARReport_" + procDate + ".txt");
		PrintWriter Error	= new PrintWriter(new FileWriter("Error.txt"));
		try {
			File f = new File(imageDir);
			String files[] = f.list();
			if (files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].indexOf("A.t") > 0) {
						//tag270	= tiffData.GetTiffField(files[i], 270);
					}
				}
				// Close results and error output files
				writeCARrep.close();
				// Output.close();
				Error.close();
				zF.ZipFiles(imageDir, zipDir, "CheckImages");
				PrintLog("Application Completed.");
			} else {
				PrintLog("No files to Process at this time");
			}
		} catch (Exception e) {
			PrintLog("Application error: " + e);
			e.printStackTrace(System.out);
		}
	}
	public static void main(String[] args) throws IOException {
		// Variables to hold results for amount, amount score, payee name, and payee name score
		GetDataFromTiff exCar	= new GetDataFromTiff();
		exCar.ExtractData();
	}
}
