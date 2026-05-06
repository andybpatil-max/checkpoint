package com.webfiche.checkpoint.util;

import java.util.*;

public class MicrDecode {
	String className = "> MicrDecode.";
	String moduleName;
	String calledFrom;
	String abaDelim = "\\:";
	String numActDelim = "\\<";
	String abaStr = "";
	String numStr = "";
	String actStr = "";
	String tempStr = "";
	String amtStr = "";
	ArrayList<String> micrFields = new ArrayList<String>();
	GenUtil	gUtil	= new GenUtil();
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public ArrayList<String> GetMicr(String mStr) {
		moduleName	= "GetMicr: ";
		PrintLog("Micr: " + mStr);
		abaStr	= "0";
		numStr	= "0";
		actStr	= "0";
		tempStr	= "";
		amtStr	= "";
		micrFields.clear();
		String[] micrStrs1 = mStr.split("\\:");
		for (int i = 0; i < micrStrs1.length; i++) {
			if (micrStrs1[i].length() == 9) {
				if (gUtil.isNumeric(micrStrs1[i]) == true) {
					abaStr	= micrStrs1[i];
				}
			} else {
				String[] micrStrs11 = micrStrs1[i].split("\\<");
				for (int j = 0; j < micrStrs11.length; j++) {
					if (gUtil.isNumeric(micrStrs11[j]) == true) {
						if (micrStrs11[j].length() > 6) {
							actStr	= micrStrs11[j];
						}
						if (micrStrs11[j].length() <= 6) {
							numStr	= micrStrs11[j];
						}
					} else {
						String[] micrStrs12 = micrStrs11[j].split("\\;");
						for (int k = 0; k < micrStrs12.length; k++) {
							if (gUtil.isNumeric(micrStrs12[k]) == true) {
								amtStr	= micrStrs12[k];
							}
						}
					}
				}
			}
		}
		PrintLog("Aba " + abaStr + " Check Num " + numStr + " Account " +
				 actStr + " Amount " + amtStr);
		// PrintLog("");
		micrFields.add(0, numStr);
		micrFields.add(1, abaStr);
		micrFields.add(2, actStr);
		if (!amtStr.equals("")) {
			micrFields.add(3, amtStr);
		}
		/*
		 * for (int i = 0; i<micrFields.size; i++) {
		 * PrintLog("micrFeilds i="+i+" "+micrFields.get(i)); }
		 */
		return (micrFields);
	}
}
