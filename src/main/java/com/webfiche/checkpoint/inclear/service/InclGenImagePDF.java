package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;

//import org.apache.struts.action.*;
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.actions.*;

@Service
public final class InclGenImagePDF {
	private static String	className		= "> InclGenImagePDF.";
	private static String	moduleName		= "";
	String	preAcct			= "";
	String	curAcct			= "";
	String	procDateF		= "";
	String	procDate		= "";
	String	chexAmt			= "";
	String	chexNum			= "";
	String	image_f			= "";
	String	image_b			= "";
	String	message			= "";
	String	bankId			= "";
	String	stmtDlvry		= "";
	boolean	pdfGenerated	= false;
	String	lstmtFreq		= "";
	int		acctCount		= 0;
	int		acctTotal		= 0;
	int		fileCount		= 0;
	//
	// Define the 12 fields for statements
	//
	String	to				= "";
	String	inRec			= "";
	String	messageFile		= "";
	String[] filenames		= { "" };
	//
	String	temp1			= "";	
	// imagedir.substring(imagedir.indexOf("/",1),imagedir.length());
	//
	ArrayList<String> acctPlist		= new ArrayList<String>();
	BufferedWriter	outPutWriter	= null;
	BufferedReader	br				= null;
	String	clientName		= "";
	String	stmt_email		= "";
	String	stmt_fax		= "";
	String	stmt_mail1		= "";
	String	stmt_mail2		= "";
	String	stmt_mail3		= "";
	String	stmt_mail4		= "";
	String	stmt_emailfreqD	= "";
	String	stmt_emailfreqW	= "";
	String	stmt_emailfreqM	= "";
	String	stmt_faxfreqD 	= "";
	String	stmt_faxfreqW 	= "";
	String	stmt_faxfreqM 	= "";
	String	stmt_mailfreqD	= "";
	String	stmt_mailfreqW	= "";
	String	stmt_mailfreqM	= "";
	String	br_str			= "";
	//
	String	server			= "";
	String	from			= "";
	String	subject			= "";
	String	msgFileDir		= "";
	String	stmtPrinter		= "";
	String	imagesPerPDF	= "";
	String	adHocStmt		= "";
	int		imgsPerPDF		= 0;
	//
	boolean	monthlyOn		= false;
	boolean	weeklyOn		= false;
	boolean	dailyOn			= false;
	boolean	prWait			= false;
	boolean	procRec			= false;
	String	lprCmd[]		= { "/bin/sh", "-c", "" };
	int		startPNum		= 1;
	GenPDFUtil pdfUtil		= new GenPDFUtil();
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public void WriteHeader(BufferedWriter outPutWriter) throws Exception {
		// module_name = "> InclGenImagePDF.WriteHeader> ";
		outPutWriter.write("<html>\n");
		outPutWriter.write("\n");
		outPutWriter.write("<head>\n");
		// outPutWriter.write("<base href='http://esl:12000/com.webfiche.checkpoint-v2/index.jsp'>\n");
		outPutWriter.write("<meta http-equiv='Content-Language' content='en-us'>\n");
		outPutWriter.write("<meta http-equiv='Content-Type' content='text/html'>\n");
		outPutWriter.write("\n");
		outPutWriter.write("</head>\n");
		outPutWriter.write("\n");
		outPutWriter.write("<body>\n");
		outPutWriter.write("<center>\n");
	}
	//
	public void WriteHtml(BufferedWriter outPutWriter) throws Exception {
		String outRec	= "";
		// module_name = "> InclGenImagePDF.WriteHtml> ";
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// module_name+"lstmtFreq "+lstmtFreq+ " Account "+curAcct);
		outRec	= "<table border='0' width='100%' colspan='2'>";
		outPutWriter.write(outRec + "\n");
		outRec	= "<tr>";
		outPutWriter.write(outRec + "\n");
		outRec	= "<td colspan='2'><font size='4'><center>Account: <b>" + curAcct + "</b>";
		outPutWriter.write(outRec);
		outRec	= " Check Number: <b>" + chexNum + "</b>";
		outPutWriter.write(outRec);
		outRec	= " Amount: <b>" + chexAmt + "</b>";
		outPutWriter.write(outRec);
		outRec	= " Cleared: <b>" + procDateF + "</b></font></center></td>";
		outPutWriter.write(outRec + "\n");
		outRec	= "</tr>";
		outPutWriter.write(outRec + "\n");
		//
		// Create the Front of check Image line
		//
		outRec	= "<tr>";
		outPutWriter.write(outRec + "\n");
		outRec	= "<td width='50%' align='center'>";
		outPutWriter.write(outRec + "\n");
		outRec	= ("<img border='0' src='" + image_f + "' width='100%'>");
		outPutWriter.write(outRec + "\n");
		outRec	= "</td>";
		outPutWriter.write(outRec + "\n");
		//
		// Create the Back of check Image line
		//
		outRec	= "<td width='50%' align='center'>";
		outPutWriter.write(outRec + "\n");
		outRec	= ("<img border='0' src='" + image_b + "' width='100%'>");
		outPutWriter.write(outRec + "\n");
		outRec	= "</td>";
		outPutWriter.write(outRec + "\n");
		outRec	= "</tr>";
		outPutWriter.write(outRec + "\n");
		outRec	= "</table>";
		outPutWriter.write(outRec + "\n");
	}
	//
	public void WriteFaxTextFile(BufferedWriter outPutWriter, String faxNumber,
			String clientName, String todaysDate) throws Exception {
		// module_name	= "> InclGenImagePDF.WriteFaxTextFile> ";
		outPutWriter.write(faxNumber + "\n");
		outPutWriter.write(clientName + "\n");
		outPutWriter.write(todaysDate + "\n");
		outPutWriter.write("\n");
		outPutWriter.write("\n");
		outPutWriter.write(message);
		outPutWriter.close();
	}
	//
	public void WriteMailTextFile(BufferedWriter outPutWriter,
			String stmt_mail1, String stmt_mail2, String stmt_mail3,
			String stmt_mail4) throws Exception {
		// module_name	= "> InclGenImagePDF.WriteMailTextFile> ";
		outPutWriter.write("\n");
		outPutWriter.write("\n");
		outPutWriter.write("\n");
		outPutWriter.write(stmt_mail1 + "\n");
		outPutWriter.write(stmt_mail2 + "\n");
		outPutWriter.write(stmt_mail3 + "\n");
		outPutWriter.write(stmt_mail4 + "\n");
		outPutWriter.write("\n");
		outPutWriter.write("\n");
		outPutWriter.write(message);
		outPutWriter.close();
	}
	//
	public void WriteAndExecCmd(BufferedWriter outPutWriter, String outputDir,
			String outputFile) throws Exception {
			//String outputFile, String stmtFreq) throws Exception {
		moduleName	= "WriteAndExecCmd> ";
		//
		outPutWriter.write("</center>\n");
		outPutWriter.write("</body>\n");
		outPutWriter.write("\n");
		outPutWriter.write("</html>\n");
		//
		// CLOSE the file
		//
		PrintLog("Will CLOSE " + outputFile + ".html");
		outPutWriter.close();
		pdfUtil.GenPDF(outputDir, outputFile, startPNum);
		PrintLog("Generated PDF for " + preAcct + " record count " + acctCount);
	}
	//
	public void ProcessAccount(String outputDir, String stmtFreq, 
			BufferedWriter outPutWriter, String outputFile, String appl_date_f,
			String deliverStmts) {
		moduleName			= "ProcessAccount> ";
		Process proc;
		String logFile		= outputDir + "html-2-ps-2-pdf.log";
		MailClient mClient	= new MailClient();
		try {
			//
			// 1. eMailed - call sendMail
			// 2. Printed - call print command
			// 3. Faxed - Create the txt file for FAX server
			//
			// PrintLog("Statement Frequency "+stmtFreq);
			// PrintLog("Will Generate Statements for "+curAcct+" record count "+acctCount);
			pdfGenerated	= false;
			if (fileCount == 1) {
				startPNum	= 1;
			} else {
				startPNum	= (fileCount - 1) * 20 + 1;
			}
			if (stmtFreq.equals("adhoc")) {
				WriteAndExecCmd(outPutWriter, outputDir, outputFile); //, stmtFreq);
				pdfGenerated	= true;
				if ((stmt_emailfreqM.equals("Y")) ||
					(stmt_mailfreqM.equals("Y")) ||
					(stmt_faxfreqM.equals("Y"))) {
					pdfGenerated = true;
					if (deliverStmts.equals("Y")) {
						try {
							to	= stmt_email;
							//to	= "andy.patil@webfiche.com";
							filenames[0]	= outputFile + ".pdf";
							// client.sendMail(server,from,to,subject,message,filenames);
							if (!to.trim().equals("")) {
								PrintLog("Will eMail " + outputFile + " to " + to);
								mClient.sendMail(server, from, to, subject,	message, filenames);
							}
						} catch (Exception e) {
							e.printStackTrace(System.out);
						}
					}
				}
				if ((stmt_mailfreqM.equals("Y"))) {
					if (!stmtPrinter.equals("")) {
						BufferedWriter outPutWriter1	= new BufferedWriter(new FileWriter(
																			outputFile + ".mail"));
						WriteMailTextFile(outPutWriter1, stmt_mail1, stmt_mail2,
											stmt_mail3, stmt_mail4);
						// lpr -PMONPEXTRAIT01 -o document-format=text/plain -o
						// charset=utf-8 test.print.txt
						lprCmd[2] = ("lpr -P " + stmtPrinter + " -o document-format=text/plain -o charset=utf-8 "
									+ outputFile + ".mail >> " + logFile);
						PrintLog("Will Print Mail Address page");
						proc	= Runtime.getRuntime().exec(lprCmd);
						try {
							br		= new BufferedReader(new InputStreamReader(proc.getInputStream()));
						} catch (Exception e) {
							PrintLog("Failed BufferedReader ");
							e.printStackTrace(System.out);
						}
						prWait	= true;
						while (prWait) {
							br_str	= br.readLine();
							if (br_str == null) {
								prWait	= false;
							}
							PrintLog("BR Str " + br_str);
						}
						proc.waitFor();
						lprCmd[2]	= ("lpr -P " + stmtPrinter + " " + outputFile	+ ".pdf >> " + logFile);
						PrintLog("Will Print " + outputFile + ".pdf");
						proc	= Runtime.getRuntime().exec(lprCmd);
						prWait	= true;
						while (prWait) {
							br_str	= br.readLine();
							if (br_str == null) {
								prWait	= false;
							}
							PrintLog("BR Str " + br_str);
						}
						proc.waitFor();
						br.close();
					}
				}
				if ((stmt_faxfreqM.equals("Y"))) {
					if (deliverStmts.equals("Y")) {
						if (deliverStmts.equals("Y")) {
							BufferedWriter outPutWriter1	= new BufferedWriter(new FileWriter(
																				outputFile + ".txt"));
							WriteFaxTextFile(outPutWriter1, stmt_fax, clientName, appl_date_f);
							outPutWriter1	= new BufferedWriter(new FileWriter(
																outputFile + ".ack"));
							outPutWriter1.close();
						}
					}
				}
			}
			if ((stmtFreq.equals("Daily") && (stmt_emailfreqD.equals("Y"))) ||
				(stmtFreq.equals("Weekly") && (stmt_emailfreqW.equals("Y"))) ||
					// (stmtFreq.equals("Monthly") && (stmt_emailfreqM.equals("Y")))) {
					(stmtFreq.equals("Monthly"))) {
				WriteAndExecCmd(outPutWriter, outputDir, outputFile);	//, stmtFreq);
				pdfGenerated = true;
				if (stmtFreq.equals("Monthly") && !stmt_emailfreqM.equals("Y")) {
				} else {
					if (deliverStmts.equals("Y")) {
						try {
							to		= stmt_email;
							// to	= "andy.patil@webfiche.com";
							filenames[0]	= outputFile + ".pdf";
							// client.sendMail(server,from,to,subject,message,filenames);
							if (!to.trim().equals("")) {
								PrintLog("Will eMail " + outputFile + " to " + to);
								mClient.sendMail(server, from, to, subject,	message, filenames);
							}
						} catch (Exception e) {
							PrintLog("Failed to eMail " + outputFile + " to " + to);
							e.printStackTrace(System.out);
						}
					}
				}
			}
			//
			// 2. Printed - call print command
			//
			if ((stmtFreq.equals("Daily") && (stmt_mailfreqD.equals("Y"))) ||
				(stmtFreq.equals("Weekly") && (stmt_mailfreqW.equals("Y"))) ||
				(stmtFreq.equals("Monthly") && (stmt_mailfreqM.equals("Y")))) {
				if (pdfGenerated == false) {
					WriteAndExecCmd(outPutWriter, outputDir, outputFile);	//,stmtFreq);
					pdfGenerated	= true;
				}
				// Print the file
				if (deliverStmts.equals("Y")) {
					if (!stmtPrinter.equals("")) {
						BufferedWriter outPutWriter1	= new BufferedWriter(new FileWriter(outputFile +
																			".mail"));
						WriteMailTextFile(outPutWriter1, stmt_mail1, stmt_mail2,	stmt_mail3, stmt_mail4);
						lprCmd[2]	= ("lpr -P " + stmtPrinter +
										" -o document-format=text/plain -o charset=utf-8 " +
										outputFile + ".mail >> " + logFile);
						PrintLog("Will Print Mail Address page");
						proc	= Runtime.getRuntime().exec(lprCmd);
						//br		= new BufferedReader(new InputStreamReader(proc.getInputStream()));
						try {
							br		= new BufferedReader(new InputStreamReader(proc.getInputStream()));
						} catch (Exception e) {
							PrintLog("Failed BufferedReader ");
							e.printStackTrace(System.out);
						}
						prWait	= true;
						while (prWait) {
							br_str	= br.readLine();
							if (br_str == null) {
								prWait	= false;
							}
							PrintLog("BR Str " + br_str);
						}
						proc.waitFor();
						lprCmd[2]	= ("lpr -P " + stmtPrinter + " " + outputFile + ".pdf >> " + 
										logFile);
						PrintLog("Will Print " + outputFile + ".pdf");
						proc	= Runtime.getRuntime().exec(lprCmd);
						prWait	= true;
						while (prWait) {
							br_str	= br.readLine();
							if (br_str == null) {
								prWait	= false;
							}
							PrintLog("BR Str " + br_str);
						}
						proc.waitFor();
						br.close();
					}
				}
			}
			//
			// 3. Faxed - Create the txt file for FAX server
			//
			if ((stmtFreq.equals("Daily") && (stmt_faxfreqD.equals("Y"))) ||
				(stmtFreq.equals("Weekly") && (stmt_faxfreqW.equals("Y"))) ||
				(stmtFreq.equals("Monthly") && (stmt_faxfreqM.equals("Y")))) {
				// Print the file
				if (pdfGenerated == false) {
					WriteAndExecCmd(outPutWriter, outputDir, outputFile);	//,stmtFreq);
					pdfGenerated	= true;
				}
				if (deliverStmts.equals("Y")) {
					BufferedWriter outPutWriter1	= new BufferedWriter(new FileWriter(outputFile	+ ".txt"));
					WriteFaxTextFile(outPutWriter1, stmt_fax, clientName, appl_date_f);
					outPutWriter1	= new BufferedWriter(new FileWriter(outputFile + ".ack"));
					outPutWriter1.close();
				}
			}
		} catch (Throwable t) {
			PrintLog("WriteAndExecCmd 1: " + t);
			t.printStackTrace(System.out);
		}
	}
	//
	public void GenImageHtml(ChexSelector chexSelector, String imageDir,
			String outputDir, String stmtFreq, Connection dbConn,
			String deliverStmts) throws Exception {
		//
		moduleName	= "GenImageHtml> ";
		//
		InclAcctUtil acUtil				= new InclAcctUtil();
		AccountDetail acctDetail		= new AccountDetail();
		EcontServletContextListener escl= new EcontServletContextListener();
		String statementFrq				= stmtFreq;
		//
		server			= escl.getSmtpDomain();
		from			= escl.getFromEmailAddress();
		// from			= "andy.patil@webfiche.com";
		subject			= escl.getStmtSubject();
		msgFileDir		= escl.getStmtMsgDir();
		stmtPrinter		= escl.getStmtPrinter();
		imagesPerPDF	= escl.getImagesPerPDF();
		imgsPerPDF		= Integer.parseInt(imagesPerPDF);
		//
		String appl_date_f	= chexSelector.getAppl_date_f();
		String appl_date	= chexSelector.getAppl_date();
		int rowCount		= chexSelector.getDetail_count();
		//
		String monthStart	= chexSelector.getMonthStart();
		String weekEnd		= chexSelector.getWeekEnd();
		String dailyDate	= chexSelector.getDailyDate();
		String fromPeriod	= chexSelector.getCh_from_period();
		String toPeriod		= chexSelector.getCh_to_period();
		String fromDate		= chexSelector.getCh_from_date();
		String toDate		= chexSelector.getCh_to_date();
		bankId				= chexSelector.getBankId();
		int extractMonth	= 0;
		//
		ArrayList<?> chexRow= new ArrayList<Object>();
		ChexDetail aRow		= new ChexDetail();
		//
		String outputFile	= "";
		//
		// here determine what goes in the Subject and Mail/Fax body if
		// Statement frequecy id "adhoc".
		//
		if (toPeriod.equals("NONE")) {
			statementFrq	= "Monthly";
			extractMonth	= Integer.parseInt(fromPeriod.substring(4, 6));
			monthStart		= ValiDate.getMonth(extractMonth);
		}
		if (toDate.equals("")) {
			if (fromDate.equals("")) {
			} else {
				statementFrq	= "adhoc";
				dailyDate		= (fromDate.substring(6) + "/" +
									fromDate.substring(4, 6) + "/" + 
									fromDate.substring(0, 4));
			}
		} else if (fromDate.equals("")) {
			statementFrq	= "adhoc";
			dailyDate		= (toDate.substring(6) + "/" + 
								toDate.substring(4, 6) + "/" +
								toDate.substring(0, 4));
		} else {
			statementFrq	= "adhoc";
			adHocStmt		= ("from " + fromDate.substring(6) + "/" +
						 		fromDate.substring(4, 6) + "/" + 
						 		fromDate.substring(0, 4) + " to " + 
						 		toDate.substring(6) + "/" + 
						 		toDate.substring(4, 6) + "/" + 
						 		toDate.substring(0, 4));
		}
		PrintLog("From Period " + fromPeriod + " to Period " +
				 toPeriod + " from Date >" + fromDate + "< to Date >" + toDate + "<");
		BufferedReader inRead	= new BufferedReader(new FileReader(msgFileDir + "message.txt"));
		message	= "";
		while ((inRec = inRead.readLine()) != null) {
			if (inRec.length() < 2) {
				message	+= "\n";
			} else if (inRec.indexOf("payment") > 0) {
				if (statementFrq.equals("Monthly")) {
					message	+= inRec + " for the Month of " + monthStart + "\n";
					message	+= "\n";
					subject	+= " for the Month of " + monthStart;
				} else if (statementFrq.equals("Weekly")) {
					message	+= inRec + " for the week ending " + weekEnd + "\n";
					message	+= "\n";
					subject	+= " for the Week ending " + weekEnd;
				} else if (statementFrq.equals("Daily")) {
					message	+= (inRec + " on " + dailyDate);
					message	+= "\n";
					subject	+= (" as of " + dailyDate);
				} else {
					message	+= (inRec + adHocStmt);
					message	+= "\n";
					subject	+= (adHocStmt);
				}
			} else if (inRec.indexOf("paid ") > 0) {
				if (statementFrq.equals("Monthly")) {
					message	+= inRec + " for the Month of " + monthStart + "\n";
					message	+= "\n";
					subject	+= " for the Month of " + monthStart;
				} else if (statementFrq.equals("Weekly")) {
					message	+= inRec + " for the week ending " + weekEnd + "\n";
					message	+= "\n";
					subject	+= " for the Week ending " + weekEnd;
				} else if (statementFrq.equals("Daily")) {
					message	+= (inRec + " on " + dailyDate);
					message	+= "\n";
					subject	+= (" as of " + dailyDate);
				} else {
					message	+= (inRec + adHocStmt);
					message	+= "\n";
					subject	+= (adHocStmt);
				}
			} else {
				message	+= inRec + '\n';
			}
		}
		inRead.close();
		PrintLog("Generating PDFs on Appl_date " + appl_date +
				 " for a total " + rowCount + " for " + statementFrq + " frequency");
		//
		chexRow = chexSelector.getChexRowsArray();
		Iterator<?> getCheck	= chexRow.iterator();
		int n	= 0;
		try {
			while (getCheck.hasNext()) {
				aRow		= (ChexDetail) getCheck.next();
				n++;
				curAcct		= aRow.getChex_account_num();
				chexNum		= aRow.getChex_check_num();
				chexAmt		= aRow.getChex_check_amount_disp();
				procDateF	= aRow.getChex_proc_date_disp();
				procDate	= aRow.getChex_proc_date();
				if (bankId.equals("BNPMO")) {
					procDateF	= (procDate.substring(6) + "/" +
									procDate.substring(4, 6) + "/" + 
									procDate.substring(0, 4));
				}
				temp1	= aRow.getChex_image_f();
				image_f	= imageDir + temp1.substring(temp1.indexOf("/", 1) + 1,	temp1.length());
				//
				// PrintLog("Check Account: "+curAcct+" Check Count: "+n);
				// PrintLog("Image Front: "+image_f);
				// PrintLog("Image Front: "+temp1);
				// image_f	= imageDir + temp1.substring(temp1.indexOf("/",1)+1,temp1.length());
				// image_f	= temp1.substring(temp1.indexOf("/",1),temp1.length());
				temp1	= aRow.getChex_image_b();
				image_b	= imageDir + temp1.substring(temp1.indexOf("/", 1) + 1,	temp1.length());
				// image_b	= imageDir + temp1.substring(temp1.indexOf("/",1)+1,temp1.length());
				// image_b	= temp1.substring(temp1.indexOf("/",1),temp1.length());
				// PrintLog("Image Back: "+image_b);
				//
				if (n == 1) {
					//
					// Get the row for the current check account
					//
					fileCount	= 1;
					try {
						acctDetail	= acUtil.GetAccountRows(dbConn, curAcct);
					} catch (Throwable t) {
						t.printStackTrace(System.out);
					}
					clientName		= acctDetail.getAccount_client_name();
					stmt_email		= acctDetail.getAccount_stmt_email();
					stmt_fax		= acctDetail.getAccount_stmt_fax();
					stmt_mail1		= acctDetail.getAccount_stmt_mail1();
					stmt_mail2		= acctDetail.getAccount_stmt_mail2();
					stmt_mail3		= acctDetail.getAccount_stmt_mail3();
					stmt_mail4		= acctDetail.getAccount_stmt_mail4();
					stmt_emailfreqD	= acctDetail.getAccount_stmt_emailfreqD();
					stmt_emailfreqW	= acctDetail.getAccount_stmt_emailfreqW();
					stmt_emailfreqM	= acctDetail.getAccount_stmt_emailfreqM();
					stmt_faxfreqD	= acctDetail.getAccount_stmt_faxfreqD();
					stmt_faxfreqW	= acctDetail.getAccount_stmt_faxfreqW();
					stmt_faxfreqM	= acctDetail.getAccount_stmt_faxfreqM();
					stmt_mailfreqD	= acctDetail.getAccount_stmt_mailfreqD();
					stmt_mailfreqW	= acctDetail.getAccount_stmt_mailfreqW();
					stmt_mailfreqM	= acctDetail.getAccount_stmt_mailfreqM();
					//
					procRec	= false;
					if (statementFrq.equals("Monthly")) {
						procRec	= true;
					}
					if ((statementFrq.equals("Weekly") && 
						(stmt_emailfreqW.equals("Y") || 
						(stmt_mailfreqW.equals("Y")) || 
						(stmt_faxfreqW.equals("Y"))))) {
						procRec	= true;
					}
					if ((statementFrq.equals("Daily") && 
						(stmt_emailfreqD.equals("Y") || 
						(stmt_mailfreqD.equals("Y")) || 
						(stmt_faxfreqD.equals("Y"))))) {
						procRec	= true;
					}
					if (statementFrq.equals("adhoc")) {
						procRec	= true;
					}
					if (procRec == true) {
						//
						// First create the output html files for M, W, D
						//
						outputFile	= (outputDir + "ChexImages_" + appl_date.substring(0, 6) + 
										"_" + curAcct +"_" + statementFrq + "_" + fileCount);
						//
						// The first time only open a new file
						//
						PrintLog("Will create " + outputFile + ".html");
						outPutWriter	= new BufferedWriter(new FileWriter(outputFile + ".html"));
						//
						// Write the Header
						//
						try {
							WriteHeader(outPutWriter);
							WriteHtml(outPutWriter);
						} catch (Throwable t) {
							PrintLog("WriteHeader 1: " + t);
							t.printStackTrace(System.out);
						}
						procRec	= false;
					}
					// n++;
					preAcct	= curAcct;
					acctTotal++;
					acctCount++;
					// PrintLog("n = 1  "+preAcct+ " record count "+acctCount+
					//			" record Total "+acctTotal);
				}
				if (!curAcct.equals(preAcct)) {
					// n++;
					// PrintLog("!curAcct.equals(preAcct) "+preAcct+
					//			" record count "+acctCount+
					//			" record Total "+acctTotal);
					PrintLog("Will PROCESS Account " + curAcct);
					ProcessAccount(outputDir, statementFrq, outPutWriter, outputFile,
									appl_date_f, deliverStmts);
					// PrintLog("Processed "+preAcct + " record count Total "+acctTotal);
					fileCount	= 1;
					try {
						acctDetail	= acUtil.GetAccountRows(dbConn, curAcct);
					} catch (Throwable t) {
						t.printStackTrace(System.out);
					}
					clientName		= acctDetail.getAccount_client_name();
					stmt_email		= acctDetail.getAccount_stmt_email();
					stmt_fax		= acctDetail.getAccount_stmt_fax();
					stmt_mail1		= acctDetail.getAccount_stmt_mail1();
					stmt_mail2		= acctDetail.getAccount_stmt_mail2();
					stmt_mail3		= acctDetail.getAccount_stmt_mail3();
					stmt_mail4		= acctDetail.getAccount_stmt_mail4();
					stmt_emailfreqD	= acctDetail.getAccount_stmt_emailfreqD();
					stmt_emailfreqW	= acctDetail.getAccount_stmt_emailfreqW();
					stmt_emailfreqM	= acctDetail.getAccount_stmt_emailfreqM();
					stmt_faxfreqD	= acctDetail.getAccount_stmt_faxfreqD();
					stmt_faxfreqW	= acctDetail.getAccount_stmt_faxfreqW();
					stmt_faxfreqM	= acctDetail.getAccount_stmt_faxfreqM();
					stmt_mailfreqD	= acctDetail.getAccount_stmt_mailfreqD();
					stmt_mailfreqW	= acctDetail.getAccount_stmt_mailfreqW();
					stmt_mailfreqM	= acctDetail.getAccount_stmt_mailfreqM();
					//
					procRec	= false;
					if (statementFrq.equals("Monthly")) {
						procRec	= true;
					}
					if ((statementFrq.equals("Weekly") && 
						(stmt_emailfreqW.equals("Y") || 
						(stmt_mailfreqW.equals("Y")) ||
						(stmt_faxfreqW.equals("Y"))))) {
						procRec	= true;
					}
					if ((statementFrq.equals("Daily") && 
						(stmt_emailfreqD.equals("Y") || 
						(stmt_mailfreqD.equals("Y")) || 
						(stmt_faxfreqD.equals("Y"))))) {
						procRec	= true;
					}
					if (statementFrq.equals("adhoc")) {
						procRec	= true;
					}
					if (procRec == true) {
						//
						// First create the output html files for M, W, D
						//
						outputFile	= (outputDir + "ChexImages_" +
										appl_date.substring(0, 6) + "_" + curAcct +
										"_" + statementFrq + "_" + fileCount);
						//
						// The first time only open a new file
						//
						PrintLog("Will create " + outputFile + ".html");
						outPutWriter	= new BufferedWriter(new FileWriter(outputFile + ".html"));
						//
						// Write the Header
						//
						try {
							WriteHeader(outPutWriter);
							WriteHtml(outPutWriter);
						} catch (Throwable t) {
							PrintLog("WriteHeader 2: " + t);
							t.printStackTrace(System.out);
						}
						procRec	= false;
					}
					//
					preAcct		= curAcct;
					acctTotal	= 1;
					acctCount	= 1;
					// PrintLog("end !curAcct.equals(preAcct) "+preAcct +
					//			" record count " + acctCount + " record Total " + acctTotal);
				} else {
					if (acctCount == imgsPerPDF) {
						// PrintLog("acctCount > 199 " + preAcct +
						// 			" record count " + acctCount + 
						//			" record Total " + acctTotal);
						PrintLog("Will PROCESS Account " + curAcct);
						ProcessAccount(outputDir, statementFrq, outPutWriter, outputFile,
										appl_date_f, deliverStmts);
						fileCount++;
						//
						procRec	= false;
						if (statementFrq.equals("Monthly")) {
							procRec	= true;
						}
						if ((statementFrq.equals("Weekly") && 
							(stmt_emailfreqW.equals("Y") || 
							(stmt_mailfreqW.equals("Y")) || 
							(stmt_faxfreqW.equals("Y"))))) {
							procRec	= true;
						}
						if ((statementFrq.equals("Daily") && 
							(stmt_emailfreqD.equals("Y") || 
							(stmt_mailfreqD.equals("Y")) || 
							(stmt_faxfreqD.equals("Y"))))) {
							procRec	= true;
						}
						if (statementFrq.equals("adhoc")) {
							procRec	= true;
						}
						if (procRec == true) {
							//
							// First create the output html files for M, W, D
							//
							PrintLog("Will create "+ outputFile + ".html");
							outputFile	= (outputDir + "ChexImages_" +
										  appl_date.substring(0, 6) + "_" + curAcct +
										  "_" + statementFrq + "_" + fileCount);
							//
							// The first time only open a new file
							//
							outPutWriter	= new BufferedWriter(new FileWriter(outputFile + ".html"));
							//
							// Write the Header
							//
							try {
								WriteHeader(outPutWriter);
								WriteHtml(outPutWriter);
							} catch (Throwable t) {
								PrintLog("WriteHeader 3: " + t);
								t.printStackTrace(System.out);
							}
							procRec	= false;
						}
						acctCount = 1;
						// PrintLog("end acctCount > 199 "+preAcct+
						// 			" record count "+acctCount+" record Total "+acctTotal);
					} else {
						if (n > 1) {
							if (statementFrq.equals("Monthly")) {
								try {
									WriteHtml(outPutWriter);
									preAcct	= curAcct;
								} catch (Throwable t) {
									PrintLog("WriteHtml 1: " + t);
									t.printStackTrace(System.out);
								}
							}
							if (statementFrq.equals("adhoc")) {
								try {
									WriteHtml(outPutWriter);
									preAcct	= curAcct;
								} catch (Throwable t) {
									PrintLog("WriteHtml 2: " + t);
									t.printStackTrace(System.out);
								}
							}
							if ((statementFrq.equals("Weekly") && 
								(stmt_emailfreqW.equals("Y") ||
								(stmt_mailfreqW.equals("Y")) || 
								(stmt_faxfreqW.equals("Y"))))) {
								try {
									WriteHtml(outPutWriter);
									preAcct	= curAcct;
								} catch (Throwable t) {
									PrintLog("WriteHtml 3: "+ t);
									t.printStackTrace(System.out);
								}
							}
							if ((statementFrq.equals("Daily") && 
								(stmt_emailfreqD.equals("Y") ||
								(stmt_mailfreqD.equals("Y")) || 
								(stmt_faxfreqD.equals("Y"))))) {
								try {
									WriteHtml(outPutWriter);
									preAcct	= curAcct;
								} catch (Throwable t) {
									PrintLog("WriteHtml 4: " + t);
									t.printStackTrace(System.out);
								}
							}
							acctTotal++;
							acctCount++;
							// PrintLog("past acctCount > 199 "+preAcct+
							// 			" record count "+acctCount+
							// 			" record Total "+acctTotal);
						}
					}
				}
			}
			//
			// Process the last Record here
			//
			pdfGenerated	= false;
			PrintLog("Process Last Record #n" + outputFile);
			PrintLog("Will PROCESS Account " + curAcct);
			ProcessAccount(outputDir, statementFrq, outPutWriter, outputFile, 
							appl_date_f,deliverStmts);
		} catch (IOException e) {
			PrintLog("IO-Ecxception " + e);
			e.printStackTrace(System.out);
		}
	}
}
