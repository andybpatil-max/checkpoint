package com.webfiche.checkpoint.util;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.io.*;
import java.util.Vector;

public class ParseMontrealIndex {
    private Vector <Vector<String>> tagValue	= new Vector <Vector<String>>();
    private Vector <String> tvTriple			= new Vector <String>();
    private String className			= "> ParseMontrealIndex.";
    private String moduleName			= "ParseMOChecks: ";
    //
    String temp;
    String dataOffset	= "";
    String dataLen		= "";
    String jpegName		= "";
    String accountNum	= "";
    String chkNum		= "";
    String uniqISN		= "";
    String chkAmount	= "";
    String processDate	= "";
    String chkCurr		= "";
    String checkRouting	= "";
	boolean id;
	int dataOffValue;
	int dataOffValue_o;
	int dataOffValueCurrent	= 0;
	int	dataOffValueNext	= 0;
	int	dataLenValue		= 0;
	int	dataLenCalc			= 0;
	String procDate			= "";
	String checkNum			= "";
	String checkAmount		= "";
	String dataLenStr		= "";
	String dataOffStr		= "";
	String dbCr				= "";
	String checkCurr		= "";
	String tranType			= "";
	String uniqueIsn		= "";
	String checkRT			= "";
	String acctNum			= "";
	String checkOrigin		= "";
	String fieldData		= "";
	String tiffFile			= "CheckImage.tiff";
	String zerosStr			= "00000000000000000000";
	String	jpegFile		= "";
	BufferedInputStream	bis	= null;
	BufferedReader bf = null;
	//
	public void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public Vector <Vector <String>> ParseMOChecks(String xmlFile) {
		PrintLog("Will Parse: "+xmlFile);
		try {
			File file = new File(xmlFile);
			if (file.exists()) {
				bis	= new BufferedInputStream(new FileInputStream(xmlFile));
				SAXParserFactory parserFact = SAXParserFactory.newInstance();
				SAXParser parser = parserFact.newSAXParser();
				//System.out.println("XML Data: ");
				DefaultHandler dHandler = new DefaultHandler() {
					boolean fieldName			= false;
					boolean fieldValue			= false;
					boolean processingDate		= false;
					boolean chequeSerialNumber	= false;
					boolean amount				= false;
					// boolean dRCRIndicator	= false;
					boolean currency			= false;
					// boolean transactionType	= false;
					boolean itemSequenceNumber	= false;
					boolean routingTransit		= false;
					boolean account				= false;
					// boolean captureSite		= false;
					boolean dataOffset			= false;
					boolean dataLength			= false;
					// boolean nameImg			= false;
					//String str					= "";
					public void startElement(String uri, String localName,
							String element_name, Attributes attrs)
							throws SAXException {
						if (element_name.equals("field_name")) {
							fieldName = true;
						}
						if (element_name.equals("field_value")) {
							fieldValue = true;
						}
						if (element_name.equals("data_offset")) {
							dataOffset = true;
						}
						if (element_name.equals("data_length")) {
							dataLength = true;
						}
					}
					//
					public void characters(char[] ch, int start, int len)
							throws SAXException {
						fieldData = new String(ch, start, len);
						if (fieldName) {
							//PrintLog("FieldName: "+fieldData);
							if (fieldData.equals("ProcessingDate")) {
								processingDate = true;
							}
							if (fieldData.equals("ChequeSerialNumber")) {
								chequeSerialNumber = true;
							}
							if (fieldData.equals("Amount")) {
								amount = true;
							}
							if (fieldData.equals("Currency")) {
								currency = true;
							}
							if (fieldData.equals("ItemSequenceNumber")) {
								itemSequenceNumber = true;
							}
							if (fieldData.equals("RoutingTransit")) {
								routingTransit = true;
							}
							if (fieldData.equals("Account")) {
								account = true;
							}
							fieldName = false;
						}
						if (fieldValue) {
							/*
							PrintLog("FieldData: "+fieldData);
							 */
							if (processingDate) {
								procDate = fieldData;
								processingDate = false;
							}
							if (chequeSerialNumber) {
								checkNum = fieldData;
								chequeSerialNumber = false;
							}
							if (amount) {
								checkAmount = fieldData;
								amount = false;
							}
							if (currency) {
								checkCurr = fieldData;
								currency = false;
							}
							if (itemSequenceNumber) {
								uniqueIsn = fieldData;
								itemSequenceNumber = false;
							}
							if (routingTransit) {
								checkRT = fieldData;
								routingTransit = false;
							}
							if (account) {
								acctNum = fieldData;
								account = false;
							}
							fieldValue = false;
						}
						if (dataOffset) {
							//PrintLog("Dataoffset: "+fieldData);
							dataOffStr	= fieldData;
							dataOffValue = Integer.parseInt(fieldData);
							dataOffset = false;
							//dataOffValueCurrent	= Integer.parseInt(fieldData);
							//dataOffset				= false;
						}
						if (dataLength) {
							//PrintLog("dataLength String: "+fieldData);
							dataLenStr	= fieldData;
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
							//dataLenValue	= Integer.parseInt(fieldData);
							//} else {
							//PrintLog("derived DataLength: "+ (dataOffValueCurrent - dataOffValuePrev));
							//dataOffValuePrev= dataOffValueCurrent;
							//}
							//if (dataLenValue==20) {
							//	dataLenValue	= 20311;
							//}
							dataLength	= false;
							//extractImages();
							//updateRecon();
							tvTriple.add(dataOffStr);	//0
							tvTriple.add(dataLenStr);	//1
							tvTriple.add(jpegFile);		//2
							tvTriple.add(acctNum);		//3
							tvTriple.add(checkNum);		//4
							tvTriple.add(uniqueIsn);	//5
							tvTriple.add(checkAmount);	//6
							tvTriple.add(procDate);		//7
							tvTriple.add(checkCurr);	//8
							tvTriple.add(checkRT);		//9
							tagValue.add(tvTriple);
							tvTriple	= new Vector <String>();
							//moduleName		= "ExtractImages: ";
						}
					}
					//
					public void endElement(String uri, String localName, String qName) {
						//Print
						//tvTriple.add(dataOffStr);
						//tvTriple.add(dataLenStr);
						//tvTriple.add(jpegFile);
						//tagValue.add(tvTriple);
						//tvTriple	= new Vector <String>();
					}
				};
				parser.parse(xmlFile, dHandler);
			} else {
				System.out.println("File not found!");
			}
		} catch (Exception e) {
			System.out.println("Error Processing "+xmlFile);
			e.printStackTrace();
		}
		PrintLog("Number of Vector elements:"+tagValue.size());
		for(int i=0;i<tagValue.size();i++) {
			// Get the data length and if less than 15000 the 
			dataLen	= (String)((Vector<String>)tagValue.get(i)).get(1);
			dataLenValue	= Integer.parseInt(dataLen);
			if (dataLenValue<10000) {
				dataOffset	= (String)((Vector<String>)tagValue.get(i)).get(0);
				jpegName	= (String)((Vector<String>)tagValue.get(i)).get(2);
				accountNum	= (String)((Vector<String>)tagValue.get(i)).get(3);
				chkNum		= (String)((Vector<String>)tagValue.get(i)).get(4);
				uniqISN		= (String)((Vector<String>)tagValue.get(i)).get(5);
				chkAmount	= (String)((Vector<String>)tagValue.get(i)).get(6);
				processDate	= (String)((Vector<String>)tagValue.get(i)).get(7);
				chkCurr		= (String)((Vector<String>)tagValue.get(i)).get(8);
				checkRouting= (String)((Vector<String>)tagValue.get(i)).get(9);
				temp	= dataOffset;
				dataOffValueCurrent	= Integer.parseInt(temp);
				temp	= (String)((Vector<String>)tagValue.get(i+1)).get(0);
				dataOffValueNext	= Integer.parseInt(temp);
				dataLenCalc			= dataOffValueNext - dataOffValueCurrent;
				tvTriple.add(dataOffset);		//0
				tvTriple.add(dataLenCalc+"");	//1
				tvTriple.add(jpegName);			//2
				tvTriple.add(accountNum);		//3
				tvTriple.add(chkNum);			//4
				tvTriple.add(uniqISN);			//5
				tvTriple.add(checkAmount);		//6
				tvTriple.add(processDate);		//7
				tvTriple.add(chkCurr);			//8
				tvTriple.add(checkRouting);		//9
				tagValue.set(i, tvTriple);
			}
			/*
			temp	= (String)((Vector<String>)tagValue.get(i)).get(0);
			System.out.println("Data Offset "+i+" : "+temp +
					" Data length: "+ (String)((Vector<String>)tagValue.get(i)).get(1) +
					" jpegFile: "+ (String)((Vector<String>)tagValue.get(i)).get(2));
			 */
		}
		return (tagValue);
	}
	// <data_offset>444129</data_offset>
	// <data_length>23446</data_length>

	public static void main(String[] args) throws IOException {
		//ParseMontrealChecks parseMOChecks	= new ParseMontrealChecks();
		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter XML file name:");
		//String xmlFile = bf.readLine();
		//ParseMontrealChecks detail = new ParseMontrealChecks (xmlFile);
		// ParseMChecks ("mo1.xml");
		//parseMOChecks(xmlFile);
		//return (tagValue);
	}
}
