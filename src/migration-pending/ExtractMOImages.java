package com.webfiche.checkpoint.actions;

import java.sql.*;
import java.util.Vector;
import java.io.*;

//import org.apache.struts.action.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.inclear.service.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.sysadmin.beans.*;

//
import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

//
public class ExtractMOImages {
	private static String	moduleName;
	boolean		cod_performed		= false;
	boolean		file_found			= true;
	//
	int			start				= 0;
	int			checksProc			= 0;
	int			imagesProc			= 0;
	int			rowCount			= 0;
	boolean		jpegCreated			= false;
	// XML field Tags
	boolean		fieldName			= false;
	boolean		fieldValue			= false;
	boolean		processingDate		= false;
	boolean		chequeSerialNumber	= false;
	boolean		amount				= false;
	boolean		dRCRIndicator		= false;
	boolean		currency			= false;
	boolean		transactionType		= false;
	boolean		itemSequenceNumber	= false;
	boolean		routingTransit		= false;
	boolean		account				= false;
	boolean		captureSite			= false;
	boolean		dataOffset			= false;
	boolean		dataLength			= false;
	//
	int			dataOffValueCurrent	= 0;
	int			dataOffValuePrev	= 0;
	int			dataLenValue		= 0;
	//
	String		db_url				= "";
	String		db_driver			= "";
	String		db_user				= "";
	String		db_pass				= "";
	String		fieldData			= "";
	//
	String		acctNum				= "";
	String		uniqueIsn			= "";
	String		appl_date			= "";
	String		appl_date_at_start	= "";
	String		bod_flag			= "";
	String		checkNum			= "";
	String		checkAmt			= "";
	String		checkCur			= "";
	String		checkRT				= "";
	String		procDate			= "";
	String		dbUsed				= "";
	//String		inDir				= "";
	String		outDir				= "";
	String		jpegFile			= "";
	String		fileName			= "";
	String		xmlFile				= "";
	String		fileNameBase		= "";
	String		fileNum				= "";
	String		tiffFile			= "CheckImage.tiff";
	String		zerosStr			= "00000000000000000000";
	String		temp				= "";
	String		imageRec			= "";
	String		imageFileName		= "";
	String		prodId				= "A";
    private Vector <Vector<String>> tagValue	= new Vector <Vector<String>>();
    private Vector <String> tvTriple			= new Vector <String>();
	//
	Connection			dbConn;
	SysadControlUtil	ctlUtil		= new SysadControlUtil();
	ControlDetail		ctlDetail	= new ControlDetail();
	ControlSelector		ctlSelector	= new ControlSelector();
	InclReconUtil		recUtil		= new InclReconUtil();
	ExtractJPEGinTIFF	exTiff		= new ExtractJPEGinTIFF();
	JPEGfromTIFF		jpTiff		= new JPEGfromTIFF();
	ParseMontrealIndex	parseMOxml	= new ParseMontrealIndex();
	ReconSelector		reconSelector	= new ReconSelector();
	ReconDetail			reconDetail	= new ReconDetail();
	String				userId		= "ImageLoader";
	FileOutputStream	outWrite	= null;	// new FileOutputStream("new_format_ascii1.dat");
	PrintStream			ps			= null;	// declare a print stream object
	FileOutputStream	outMCR		= null;
	PrintStream			psMCR		= null;
	Process				proc;
	BufferedInputStream	bis			= null;
	BufferedInputStream	bisImg		= null;
	ByteArrayOutputStream	baos	= null;
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
				"> ExtractMOImages." + moduleName + logMsg);
	}
	//
	public boolean isNumeric(String token) {
		boolean ok = false;
		try {
			Double.valueOf(token).doubleValue();
			ok = true;
		} catch (Exception e) {
			//
		}
		return ok;
	}
	//
	public void ExtractImages() {
		moduleName		= "ExtractImages: ";
		PrintLog("File Name " + fileName);
		imageFileName	= fileName;
		//
		// Here parse the XML file
		//
		try {
			bisImg	= new BufferedInputStream(new FileInputStream(fileName.replaceAll("xml", "img")));
			bis		= new BufferedInputStream(new FileInputStream(fileName));
			SAXParserFactory parserFact	= SAXParserFactory.newInstance();
			SAXParser parser			= parserFact.newSAXParser();
			PrintLog("XML Data: ");
			DefaultHandler dHandler = new DefaultHandler() {
				public void startElement(String uri, String localName,
						String element_name, Attributes attrs)
						throws SAXException {
					if (element_name.equals("field_name")) {
						fieldName	= true;
					}
					if (element_name.equals("field_value")) {
						fieldValue	= true;
					}
					if (element_name.equals("data_offset")) {
						dataOffset	= true;
					}
					if (element_name.equals("data_length")) {
						dataLength	= true;
					}
					//PrintLog("Element Name: "+element_name);
				}
				//
				public void characters(char[] ch, int start, int len)
						throws SAXException {
					fieldData	= new String(ch, start, len);
					if (fieldName) {
						if (fieldData.equals("ProcessingDate")) {
							processingDate		= true;
						}
						if (fieldData.equals("ChequeSerialNumber")) {
							// Check #
							chequeSerialNumber	= true;
						}
						if (fieldData.equals("Amount")) {
							amount				= true;
						}
						// if (fieldData.equals("DRCRIndicator")) {
						// dRCRIndicator		= true;
						// }
						if (fieldData.equals("Currency")) {
							currency			= true;
						}
						// if (fieldData.equals("TransactionType")) {
						// transactionType		= true;
						// }
						if (fieldData.equals("ItemSequenceNumber")) {
							// Unique ISN
							itemSequenceNumber	= true;
						}
						if (fieldData.equals("RoutingTransit")) {
							routingTransit		= true;
						}
						if (fieldData.equals("Account")) {
							account				= true;
						}
						// if (fieldData.equals("CaptureSite")) {
						// captureSite = true;
						// }
						fieldName				= false;
					}
					if (fieldValue) {
						//PrintLog("Field Value: "+fieldData);
						if (processingDate) {
							procDate 			= fieldData;
							processingDate		= false;
						}
						if (chequeSerialNumber) {
							checkNum = fieldData;
							chequeSerialNumber	= false;
						}
						if (amount) {
							checkAmt			= fieldData;
							amount				= false;
						}
						// if (dRCRIndicator) {
						// dRCRIndicator = false;
						// }
						if (currency) {
							checkCur			= fieldData;
							currency			= false;
						}
						// if (transactionType) {
						// uniqueIsn
						// transactionType = false;
						// }
						if (itemSequenceNumber) {
							uniqueIsn = fieldData;
							itemSequenceNumber	= false;
						}
						if (routingTransit) {
							checkRT = fieldData;
							routingTransit		= false;
						}
						if (account) {
							acctNum				= fieldData;
							account				= false;
						}
						// if (captureSite) {
						// captureSite = false;
						// }
						fieldValue				= false;
					}
					if (dataOffset) {
						PrintLog("Data Offset: "+fieldData);
						dataOffValueCurrent	= Integer.parseInt(fieldData);
						dataOffset				= false;
					}
					if (dataLength) {
						//PrintLog("Chars: "+new String(ch));
						PrintLog("dataLength String: "+fieldData);
						jpegFile	= "";
						temp		= "";
						temp		= ("Check_" + zerosStr.substring(0, 17 - acctNum.length()) +
										acctNum + "_" +
										zerosStr.substring(0, 15 - checkNum.length()) +
										checkNum + "_" + 
										zerosStr.substring(0, 15 - uniqueIsn.length()) + uniqueIsn);
						jpegFile	= temp;
						//tiffFile	= temp + ".tiff";
						//PrintLog("data length Value: "+fieldData);
						//if (dataOffValueCurrent==0) {
						dataLenValue	= Integer.parseInt(fieldData);
						//} else {
						PrintLog("derived DataLength: "+ (dataOffValueCurrent - dataOffValuePrev));
						dataOffValuePrev= dataOffValueCurrent;
						//}
						//if (dataLenValue==20) {
						//	dataLenValue	= 20311;
						//}
						dataLength	= false;
						//extractImages();
						//updateRecon();
						moduleName		= "ExtractImages: ";
					}
				}
				public void endElement(String uri, String localName, String qName) {
					//
				}
			};
			parser.parse(fileName, dHandler);
		} catch (Exception e) {
			PrintLog("XML File hasn't any elements");
			e.printStackTrace();
		}
		//
		//PrintLog("Will Rename " + outDir + fileName + ".xml to " + outDir + temp	+ ".xml");
		//File inXMLFile		= new File(inDir + fileName + ".xml");
		//inXMLFile.renameTo(new File(outDir + fileName + ".xml"));
		//
		//PrintLog("Will Rename " + imageFileName + " to " + outDir + temp + ".img");
		//File outImageFile	= new File(imageFileName);
		//outImageFile.renameTo(new File(outDir + fileName + ".img"));
	}
	//
	public void extractImages() {
		moduleName = "extractImages: ";
		try {
			byte[] recText;
			byte[] newText;
			baos = new ByteArrayOutputStream();
			PrintLog("Image Data Length: " + dataLenValue);
			recText = new byte[dataLenValue];
			bisImg.read(recText);
			baos.write(recText);
			imageRec = baos.toString();
			FileOutputStream outWrite1 = new FileOutputStream(outDir + tiffFile);
			newText = imageRec.getBytes();
			outWrite1.write(newText);
			outWrite1.close();
			jpegCreated = jpTiff.doTiff2JPEG(outDir, tiffFile, jpegFile);
			if (jpegCreated == false) {
				PrintLog("doTiff2JPEG failed attempting ExtractJPEGinTIFF");
				jpegCreated = ExtractJPEGinTIFF.GetJPEGFiles(outDir, tiffFile, jpegFile);
			}
		} catch (Exception e) {
			PrintLog("IO-Ecxception " + e);
			e.printStackTrace();
		}
	}
	//
	public void updateRecon() {
		moduleName = "updateRecon: ";
		//
		String reconProcDate	= "";
		String oldDir			= "";
		String recAcct			= Long.parseLong(acctNum) + "";
		String recChk			= Long.parseLong(checkNum) + "";
		String recSrc			= "";
		String recStat			= "";
		int ins_or_upd			= 0;
		// PrintLog("Update Recon "+dbUsed);
		reconSelector.setDbUsed(dbUsed);
		try {
			rowCount = recUtil.GetReconRows(dbConn, reconSelector, recAcct, 
											recChk,	checkAmt);
			PrintLog("Got " + rowCount + " Recon Rows ");
			// PrintLog("Account Num '"+acctNum+"' "+"Check Num '"+checkNum+"' "+
			// "Check Amount '"+checkAmt+"'");
			if (rowCount > 0) {
				//
				// If it exists then update it
				//
				reconDetail		= reconSelector.getArow();
				reconProcDate	= reconDetail.getRecon_proc_date();
				recSrc			= reconDetail.getRecon_source_xml();
				recStat			= reconDetail.getRecon_status();
				PrintLog("Recon Proc Date: " + reconProcDate + " " +
						 "Recon Source   : " + recSrc + " " +
						 "Recon Stat     : " + recStat);
				if (reconProcDate.equals(appl_date)) {
					//
					// Match and Update
					//
					if (!recSrc.equals("Y")) {
						PrintLog("Match & Update Recon " + dbUsed);
						reconDetail.setRecon_source_xml("Y");
						reconDetail.setRecon_status("M");
						ins_or_upd = 2;
						recUtil.InsertUpdateRecon(dbConn, reconDetail,
						// errors,
								userId, ins_or_upd); // 1 for insert or 2 for
														// update
					}
				} else {
					//
					// Rename the images from current procDate to reconProcDate
					//
					PrintLog("Rename, Match & Update Recon " + dbUsed);
					if (recSrc.equals(" ")) {
						oldDir = (outDir + reconProcDate.substring(0, 4) + "/"
								+ reconProcDate.substring(4, 6) + "/"
								+ reconProcDate.substring(6, 8) + "/");
						File imageFile = new File(outDir + jpegFile
								+ "_front.jpg");
						imageFile.renameTo(new File(oldDir + jpegFile
								+ "_front.jpg"));
						imageFile = new File(outDir + jpegFile + "_back.jpg");
						imageFile.renameTo(new File(oldDir + jpegFile
								+ "_back.jpg"));
						// File imageFile =
						reconDetail.setRecon_source_xml("Y");
						reconDetail.setRecon_status("M");
						reconDetail.setDbUsed(dbUsed);
						ins_or_upd = 2;
						recUtil.InsertUpdateRecon(dbConn, reconDetail,	userId, ins_or_upd);
						// 1 for insert or 2 for update
					}
				}
			} else {
				//
				// else insert one with XML source Y
				//
				PrintLog("Create Recon " + dbUsed);
				reconDetail.setDbUsed(dbUsed);
				reconDetail.setRecon_proc_date(procDate);
				reconDetail.setRecon_account_num(acctNum);
				reconDetail.setRecon_check_num(checkNum);
				reconDetail.setRecon_routing_transit(checkRT);
				reconDetail.setRecon_check_currency(checkCur);
				reconDetail.setRecon_check_amount(checkAmt);
				reconDetail.setRecon_unique_isn(uniqueIsn);
				reconDetail.setRecon_source_micr(" ");
				reconDetail.setRecon_source_xml("Y");
				reconDetail.setRecon_status("U");
				reconDetail.setRecon_last_modified(" ");
				reconDetail.setRecon_mod_user(" ");
				reconDetail.setRecon_mod_func(userId);
				ins_or_upd = 1;
				recUtil.InsertUpdateRecon(dbConn, reconDetail,
				// errors,
						userId, ins_or_upd); // 1 for insert or 2 for update
			}
		} catch (Throwable t) {
			PrintLog("Exception getting Recon Row " + t);
			t.printStackTrace();
		}
	}
	//
	public void runMontrealLoader(String outputDir, String imageFile) {
		moduleName	= "runMontrealLoader: ";
		EcontServletContextListener escl	= new EcontServletContextListener();
		String db_url						= escl.getDbUrl();
		String db_driver					= escl.getDbDriver();
		String db_user						= escl.getDbUser();
		String db_pass						= escl.getDbPass();
		dbUsed								= escl.getDbUsed();
		//String temp1						= "";
		String temp2						= "";
		//String temp3						= "";
		//String temp4						= "";
		String fileSizeStr					= "";
		long fileSize						= 0;
		//String[] jpegFields					= new String[4];
		reconSelector.setDbUsed(dbUsed);
		try {
			Class.forName(db_driver);
			dbConn		= DriverManager.getConnection(db_url, db_user, db_pass);
			ctlDetail.setDbUsed(dbUsed);
			rowCount	= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlSelector.getARow();
			bod_flag	= ctlDetail.getBodFlag();
			appl_date	= ctlDetail.getApplDate();
			appl_date	= escl.getApplDate();
			fileName	= imageFile;
			xmlFile		= fileName;
			File file 	= new File(fileName.replaceAll("xml", "img"));
			fileSize	= file.length();
			fileSizeStr	= fileSize+"";
			outDir		= outputDir;
			bisImg		= new BufferedInputStream(new FileInputStream(fileName.replaceAll("xml", "img")));
			//inDir		= inputDir;
			PrintLog("Starting Chex Image Loader for Montreal for " + fileName);
			start		= 0;
			checksProc	= 0;
			imagesProc	= 0;
			tagValue	= parseMOxml.ParseMOChecks(xmlFile);
			tvTriple.add(fileSizeStr);
			tvTriple.add(" ");
			tvTriple.add(" ");
			tagValue.add(tvTriple);
			//ExtractImages();
			for(int i=0;i<tagValue.size()-1;i++) {
				moduleName	= "runMontrealLoader: ";
				//temp1	= (String)((Vector<String>)tagValue.get(i)).get(0);
				/*
				 				dataOffset	= (String)((Vector<String>)tagValue.get(i)).get(0);
				jpegName	= (String)((Vector<String>)tagValue.get(i)).get(2);
				accountNum	= (String)((Vector<String>)tagValue.get(i)).get(3);
				chkNum		= (String)((Vector<String>)tagValue.get(i)).get(4);
				uniqISN		= (String)((Vector<String>)tagValue.get(i)).get(5);
				chkAmount	= (String)((Vector<String>)tagValue.get(i)).get(6);
				processDate	= (String)((Vector<String>)tagValue.get(i)).get(7);
				chkCurr		= (String)((Vector<String>)tagValue.get(i)).get(8);
				checkRouting= (String)((Vector<String>)tagValue.get(i)).get(9);

				 */
				temp2		= (String)((Vector<String>)tagValue.get(i)).get(1);
				jpegFile	= (String)((Vector<String>)tagValue.get(i)).get(2);
				acctNum		= (String)((Vector<String>)tagValue.get(i)).get(3);
				checkNum	= (String)((Vector<String>)tagValue.get(i)).get(4);
				uniqueIsn	= (String)((Vector<String>)tagValue.get(i)).get(5);
				checkAmt	= (String)((Vector<String>)tagValue.get(i)).get(6);
				procDate	= (String)((Vector<String>)tagValue.get(i)).get(7);
				checkCur	= (String)((Vector<String>)tagValue.get(i)).get(8);
				checkRT		= (String)((Vector<String>)tagValue.get(i)).get(9);
				dataLenValue= Integer.parseInt(temp2);
				//PrintLog("Data Offset: " + " : " + temp1 +
				//		 " Data length: "+ temp2 + " jpegFile: " + temp3);
				//jpegFields	= jpegFile.split("_");
				//acctNum		= jpegFields[1];
				//checkNum	= jpegFields[2];
				//uniqueIsn	= jpegFields[3];
				extractImages();
				updateRecon();
			}
			PrintLog("Completed Extracting Images for " + appl_date);
		} catch (Throwable t) {
			PrintLog("Exception getting control Row " + t);
			t.printStackTrace();
		}
	}
}
