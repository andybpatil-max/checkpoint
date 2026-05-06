package com.webfiche.checkpoint.inclear.service;

import java.io.*;
//import org.apache.struts.action.*;
import com.webfiche.checkpoint.inclear.beans.*;
import org.springframework.stereotype.Service;

@Service
public final class InclChexFRBDetails {
	// Process Statistics Counters
	static String procDate  = "";
	static String appl_date = "";
	static String temp	  = "";	;
	static String prevType  = "";	;
	static int	imageLen  = 0;
	boolean	   retCheck  = false;
	File		  findFile  = null;
	//
	// Check Detail Record 25 Fields
	//
	static String unique_isn_25;
	//
	// Image Records
	//
	static int	imageRec_seq;
	static String imageRec;
	//
	// Incl Checx Table fields
	//
	static String chex_unique_isn;
	static String fileToProcess;
	static String frbFileNamePre;
	static String frbFileNameSuf;

	//
	public void GetChexFRBDetail(ChexDetail chexDetail, String frbFileName)
			throws Exception {
		String moduleName = "> InclChexFRBDetails.getChexFRBDetail: ";
		appl_date = chexDetail.getChex_proc_date();
		chex_unique_isn = chexDetail.getChex_unique_isn();
		System.out.println(java.util.Calendar.getInstance().getTime() +
							moduleName + "Appl Date: " + appl_date + " Chex Unique ISN " +
							chex_unique_isn);
		//
		try {
			frbFileNamePre = frbFileName.substring(0, frbFileName.indexOf("*"));
			frbFileNameSuf = frbFileName
					.substring(frbFileName.indexOf("*") + 1);
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + "Starting Get FRB Check Details "
					+ appl_date + " " + frbFileNamePre + "1" + frbFileNameSuf);
		} catch (Throwable t) {
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + "Exception getting control Row " + t);
		}
		//
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ moduleName + "Will read");
		//
		//
		for (int idx = 1; idx <= 9; idx++) {
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + "In ProcessLoop pass " + idx);
			fileToProcess = frbFileNamePre + idx + frbFileNameSuf;
			findFile = new File(fileToProcess);
			if (!findFile.exists()) {
				break;
			}
			int start = 0;
			String inRec = "";
			String one_rec = "";
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + "File to Process > " + fileToProcess);
			try {
				BufferedReader inRead = new BufferedReader(new FileReader(
						fileToProcess));
				start = 0;
				inRec = "";
				one_rec = "";
				while ((inRec = inRead.readLine()) != null) {
					// System.out.println(java.util.Calendar.getInstance().getTime()+
					// moduleName+"inRec 1  "+inRec.length());
					while (inRec.length() > 0) {
						// System.out.println(java.util.Calendar.getInstance().getTime()+
						// moduleName+"Record Type:2 "+inRec.substring(0,2));
						one_rec = inRec;
						// inRec = inRec.substring(80,inRec.length());
						// System.out.println(java.util.Calendar.getInstance().getTime()+
						// moduleName+"One Rec: "+one_rec);
						if (one_rec.length() > 2) {
							// System.out.println(java.util.Calendar.getInstance().getTime()+
							// moduleName+"Record Type:2 "+inRec.substring(0,2));
							if (one_rec.substring(0, 2).equals("II")) {
								//
							} else if (one_rec.substring(0, 4).equals("0103")) {
								//
							} else if (one_rec.substring(0, 4).equals("1001")) {
								//
							} else if (one_rec.substring(0, 4).equals("2001")) {
								//
							} else if (one_rec.substring(0, 2).equals("25")) {
								retCheck = false;
								imageRec_seq = 1;
								chexRecType_25(one_rec);
								prevType = one_rec.substring(0, 2);
							} else if (one_rec.substring(0, 2).equals("26")) {
								if (retCheck) {
									System.out.println(java.util.Calendar.getInstance().getTime() +
														moduleName + "Record Type:2 " + inRec.substring(0, 2));
									chexRecType_26(one_rec, chexDetail);
									prevType = one_rec.substring(0, 2);
									retCheck = false;
									// idx = 10;
									// break;
									return;
								}
							} else if (one_rec.substring(0, 2).equals("28")) {
								//
							} else if (one_rec.substring(0, 2).equals("50")) {
								//imageLen = Integer.parseInt(one_rec.substring(24, 31));
								//
							} else if (one_rec.substring(0, 2).equals("52")) {
								imageLen = Integer.parseInt(one_rec.substring(130,137));
								inRead.skip(imageLen);
							} else if (one_rec.substring(0, 2).equals("54")) {
								//
							} else if (one_rec.substring(0, 2).equals("70")) {
								//
							} else if (one_rec.substring(0, 2).equals("90")) {
								//
							} else if (one_rec.substring(0, 2).equals("99")) {
								//
							} else {
								//
							}
							inRec = "";
							start++;
							// baos.reset();
						}
					}
				}
				System.out.println(java.util.Calendar.getInstance().getTime()
						+ moduleName + "Records read  " + start);
				inRead.close();
			} finally {
				try {
					// bis.close();
				} catch (Exception e) {
					System.out.println(java.util.Calendar.getInstance()
							.getTime()
							+ moduleName
							+ "Exception in runtime : "
							+ e);
				}
			}
		}
	}
	//
	// Method to process Record Type 25
	//
	public void chexRecType_25(String one_rec) {
		String moduleName = "> InclChexFRBDetails.chexRecType_25: ";
		//
		// System.out.println(java.util.Calendar.getInstance().getTime()+moduleName+
		// " unique_isn_25 " + unique_isn_25
		// +" chex_unique_isn "+chex_unique_isn);
		unique_isn_25 = one_rec.substring(57, 72).trim();
		if (unique_isn_25.compareTo(chex_unique_isn) == 0) {
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + " unique_isn_25 " + unique_isn_25
					+ " chex_unique_isn " + chex_unique_isn);
			retCheck = true;
		}
	}
	//
	// Check Detail Addendum A Record type 26
	//
	public void chexRecType_26(String one_rec, ChexDetail chexDetail)
			throws IOException {
		String moduleName = "> InclChexFRBDetails.chexRecType_26: ";
		temp = "";
		// Here set the fields
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ moduleName + " BOFD " + one_rec.substring(3, 12)
				+ " BOFD DATE " + one_rec.substring(12, 20));
		chexDetail.setChex_BOFD_aba(one_rec.substring(3, 12));
		chexDetail.setChex_BOFD_date(one_rec.substring(12, 20));
	}
}
