package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
//
import com.webfiche.checkpoint.inclear.beans.*;
import com.webfiche.checkpoint.util.*;
import com.webfiche.checkpoint.actions.*;
//import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileNotFoundException;
import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.PDPageContentStream.*;
//import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.common.*;
//import org.apache.pdfbox.pdmodel.graphics.xobject.*;
//import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.util.Matrix;
//import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.graphics.image.*;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

@Service
public final class InclGenStatementPDF {
	private static String	className		= "> InclGenStatementPDF.";
	private static String	moduleName		= "";
	//
	String monthStart		= "";
	String weekEnd			= "";
	String dailyDate		= "";
	String fromPeriod		= "";
	String toPeriod			= "";
	String fromDate			= "";
	String toDate			= "";
	//
	private int startX		= 80;
    // A4
    private int startY		= 720;	// for A4
    private int imgGapV		= 40;	// for A4
    private int startTextY	= 815;	// for A4
    // LETTER
    //private int startY		= 680;	// for LETTER
    //private int imgGapV		= 30;	// for LETTER
    //private int startTextY	= 770;	// for LETTER
    //
    private int imgGapH				= 20;
    //private int startTextX			= 50;
    private float offSet			= 0;
    private String appl_date_f		= "";
    private String appl_date		= "";
    private String outputDir		= "";
    private String preAcct			= "";
    private String curAcct			= "";
    private String procDateF		= "";
    private String procDate			= "";
    private String chexAmt			= "";
    private String chexNum			= "";
    private String imageFront		= "";
    private String imageBack		= "";
    private String message			= "";
    private String bankId			= "";
    //private String	stmtDlvry	= "";
    private String  outputFile		= "";
    private boolean	pdfGenerated	= false;
    //private String	lstmtFreq	= "";
    private int		chexCount		= 0;
    //private int		acctTotal	= 0;
    private int		fileCount		= 0;
	//
	// Define the fields for statements
	//
    private String	to				= "";
    private String	inRec			= "";
	private String outRec			= "";
    //private String	messageFile	= "";
    private String[] filenames		= { "" };
	//
    private String	temp1			= "";	
	// imagedir.substring(imagedir.indexOf("/",1),imagedir.length());
	//
    //private ArrayList<String> acctPlist		= new ArrayList<String>();
    private BufferedWriter	outPutWriter	= null;
    private BufferedReader	br				= null;
    private String	clientName		= "";
    private String	stmt_email		= "";
    private String	stmt_fax		= "";
    private String	stmt_mail1		= "";
    private String	stmt_mail2		= "";
    private String	stmt_mail3		= "";
    private String	stmt_mail4		= "";
    private String	stmt_emailfreqD	= "";
    private String	stmt_emailfreqW	= "";
	private String	stmt_emailfreqM	= "";
	private String	stmt_faxfreqD 	= "";
	private String	stmt_faxfreqW 	= "";
	private String	stmt_faxfreqM 	= "";
	private String	stmt_mailfreqD	= "";
	private String	stmt_mailfreqW	= "";
	private String	stmt_mailfreqM	= "";
	private String	br_str			= "";
	//
	private String	server			= "";
	private String	from			= "";
	private String	subject			= "";
	private String	msgFileDir		= "";
	private String	stmtPrinter		= "";
	private String	imagesPerPDF	= "";
	private String	adHocStmt		= "";
	private int		imgsPerPDF		= 0;
	private int		imgsPerPage		= 6;
	private int		imgsAddedToPage	= 0;
	private int		totalChecks		= 0;
    private float 	scale			= 0.21f; // alter this value to set the image size
	//
	//private boolean	monthlyOn		= false;
	//private boolean	weeklyOn		= false;
	//private boolean	dailyOn			= false;
    //
	private boolean	prWait			= false;
	private boolean	procRec			= false;
	private String	lprCmd[]		= { "/bin/sh", "-c", "" };
	//private int		startPageNum		= 1;
	//GenPDFUtil pdfUtil			= new GenPDFUtil();
	InclAcctUtil acUtil				= new InclAcctUtil();
	AccountDetail acctDetail		= new AccountDetail();
	EcontServletContextListener escl= new EcontServletContextListener();
	String statementFrq				= "";
	Connection dbConn				= null;
	//private PDFont fontPlain		= PDType1Font.HELVETICA;
	//private PDFont fontBold		= PDType1Font.HELVETICA_BOLD;
	//private PDFont fontItalic		= PDType1Font.HELVETICA_OBLIQUE;
	//private PDFont fontMono		= PDType1Font.COURIER;
	private PDType1Font fontMonoBold = new PDType1Font(Standard14Fonts.FontName.COURIER_BOLD);
	//private PDFont fontMonoBold		= PDType1Font.COURIER_BOLD;
	//private PDFont fontMonoBold		= new PDType1Font(FontName.COURIER), 12);
	//private PDPage stmtPage 		= new PDPage(PDPage.PAGE_SIZE_LETTER);
		//
    private float image1X			= 0;
    private float image1Y			= 0;
    private float image2X			= 0;
    private float height			= 0;
    private float width				= 0;
    //private float textX				= 0;
    private float textY				= 0;
    static PDDocument document		= new PDDocument();
    static PDPageContentStream cos	= null;
    //static PDPage stmtPage			= new PDPage(PDPage.PAGE_SIZE_A4);
    static PDPage stmtPage			= new PDPage(PDRectangle.A4);
    static Date date = new Date();
    //
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public void WriteToPDF() throws Exception {
		moduleName = "WriteToPDF: ";
		outRec	= "Account: " + curAcct + " Check Number: " + chexNum + 
					" Amount: " + chexAmt + " Cleared: " + procDateF;
		try {
			//PrintLog("Images to add to Page:"+imgsAddedToPage+" of "+chexCount);
			if (imgsAddedToPage == imgsPerPage) {
				cos.close();
				/*
				PDPage page = createPage(PDRectangle.A4);
			    pdfDoc.addPage(page);
			    */
				stmtPage 		= new PDPage(PDRectangle.A4);
				
				document.addPage(stmtPage);
				cos				= new PDPageContentStream(document, stmtPage);				
				imgsAddedToPage	= 0;
			    //textX			= startTextX;
			    textY			= startTextY;
			    image1X			= startX;
			    image1Y			= startY;
			}
		    //PrintLog("imgOffset: "+imgOffset);
		    cos.beginText();
		    cos.setFont(fontMonoBold, 10);
		    //cos.moveTextPositionByAmount(textX, textY);
		    //PDPageContentStream contentStream = new PDPageContentStream(document, page);
		    Matrix translationMatrix = new Matrix(1, 0, 0, 1, startX, startY);
		    //cos.setTextMatrix(Matrix.getTranslatingInstance(startX, startY));
		    cos.setTextMatrix(translationMatrix);
		    //PrintLog("imgOffset+95: "+(imgOffset+95));
		    //cos.drawString(outRec);
		    cos.showText(outRec);
		    cos.endText();
		    // Add Image 1
		    //PrintLog("Image File: "+imageFront);
		    BufferedImage awtImage	= ImageIO.read(new File(imageFront));
		    height	= awtImage.getHeight();
		    width	= awtImage.getWidth();
		    
		    PDImageXObject newImage = LosslessFactory.createFromImage(document, awtImage); // Or JPEGFactory.createFromImage(...)
		    cos.drawImage(newImage, image1X, image1Y, width, height); // Use drawImage instead of drawXObject
		    //contentStream.drawImage(newImage, image1X, image1Y, width, height); 
		    // Use drawImage instead of drawXObject
		    //PDXObjectImage ximage	= new PDPixelMap(document, awtImage);
		    PDImageXObject ximage = JPEGFactory.createFromImage(document, awtImage);
		    offSet		= (newImage.getHeight()*scale+10)*(-1);
		    // image 1: x fixed y changes
		    cos.drawImage(ximage, image1X, image1Y, ximage.getWidth()*scale, ximage.getHeight()*scale);
		    // Add Image 2
		    awtImage	= ImageIO.read(new File(imageBack));
		    //ximage		= new PDPixelMap(document, awtImage);
		    ximage = JPEGFactory.createFromImage(document, awtImage);
		    image2X		= startX + imgGapH+ximage.getWidth()*scale;
		    // image 2: x changes y as above
		    cos.drawImage(ximage, image2X, image1Y, ximage.getWidth()*scale, 
							ximage.getHeight()*scale);
			imgsAddedToPage++;
		} catch (Throwable t) {
			PrintLog("Failed Adding check image");
			t.printStackTrace(System.out);
		}
	}
	//
	public void WriteFaxTextFile(String faxNumber,
			String clientName, String todaysDate) throws Exception {
		// module_name	= "> InclGenStatementPDF.WriteFaxTextFile> ";
		outPutWriter.write(faxNumber + "\n");
		outPutWriter.write(clientName + "\n");
		outPutWriter.write(todaysDate + "\n");
		outPutWriter.write("\n");
		outPutWriter.write("\n");
		outPutWriter.write(message);
		outPutWriter.close();
	}
	//
	public void WriteMailTextFile(String stmt_mail1, String stmt_mail2, String stmt_mail3,
			String stmt_mail4) throws Exception {
		// module_name	= "> InclGenStatementPDF.WriteMailTextFile> ";
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
	public void WritePDFFile(String outputFile) throws Exception {
			//String outputFile, String stmtFreq) throws Exception {
		moduleName	= "WritePDFFile> ";
	    cos.close();
	    document.save(outputFile+".pdf");
	    document.close();
	    imgsAddedToPage	= 0;
		PrintLog("Generated "+ outputFile + ".pdf for " + preAcct + " record count " + chexCount);
	}
	//
	public void ProcessAccount(String outputDir, String stmtFreq, 
								String outputFile, String appl_date_f, String deliverStmts) {
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
			pdfGenerated	= false;
			/*
			if (fileCount == 1) {
				startPageNum	= 1;
			} else {
				startPageNum	= (fileCount - 1) * 20 + 1;
			}
			*/
			if (stmtFreq.equals("adhoc")) {
				WritePDFFile(outputFile); //, stmtFreq);
				pdfGenerated	= true;
				if ((stmt_emailfreqD.equals("Y") || 
					stmt_emailfreqW.equals("Y") || 
					stmt_emailfreqM.equals("Y") ||
					stmt_mailfreqM.equals("Y") ||
					stmt_faxfreqM.equals("Y"))) {
					pdfGenerated = true;
					if (deliverStmts.equals("Y")) {
						to	= stmt_email;
						//to	= "andy.patil@webfiche.com";
						filenames[0]	= outputFile + ".pdf";
						// client.sendMail(server,from,to,subject,message,filenames);
						if (!to.trim().equals("")) {
							PrintLog("Will eMail " + outputFile + " to " + to);
							mClient.sendMail(server, from, to, subject,	message, filenames);
						}
					}
				}
				if (stmt_mailfreqM.equals("Y")) {
					if (!stmtPrinter.equals("")) {
						WriteMailTextFile(stmt_mail1, stmt_mail2, stmt_mail3, stmt_mail4);
						// lpr -PMONPEXTRAIT01 -o document-format=text/plain -o
						// charset=utf-8 test.print.txt
						lprCmd[2] = ("lpr -P " + stmtPrinter + " -o document-format=text/plain -o charset=utf-8 "
									+ outputFile + ".mail >> " + logFile);
						PrintLog("Will Print Mail Address page");
						proc	= Runtime.getRuntime().exec(lprCmd);
						br		= new BufferedReader(new InputStreamReader(proc.getInputStream()));
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
							WriteFaxTextFile(stmt_fax, clientName, appl_date_f);
							BufferedWriter outPutWriter1	= new BufferedWriter(new FileWriter(outputFile + ".ack"));
							outPutWriter1.close();
						}
					}
				}
			}
			if ((stmtFreq.equals("Daily") && (stmt_emailfreqD.equals("Y"))) ||
				(stmtFreq.equals("Weekly") && (stmt_emailfreqW.equals("Y"))) ||
				(stmtFreq.equals("Monthly"))) {
				WritePDFFile(outputFile);	//, stmtFreq);
				pdfGenerated = true;
				if (stmtFreq.equals("Monthly") && !stmt_emailfreqM.equals("Y")) {
				} else {
					if (deliverStmts.equals("Y")) {
						to		= stmt_email;
						// to	= "andy.patil@webfiche.com";
						filenames[0]	= outputFile + ".pdf";
						// client.sendMail(server,from,to,subject,message,filenames);
						if (!to.trim().equals("")) {
							PrintLog("Will eMail " + outputFile + " to " + to);
							mClient.sendMail(server, from, to, subject,	message, filenames);
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
					WritePDFFile(outputFile);	//,stmtFreq);
					pdfGenerated	= true;
				}
				// Print the file
				if (deliverStmts.equals("Y")) {
					if (!stmtPrinter.equals("")) {
						WriteMailTextFile(stmt_mail1, stmt_mail2,	stmt_mail3, stmt_mail4);
						lprCmd[2]	= ("lpr -P " + stmtPrinter +
										" -o document-format=text/plain -o charset=utf-8 " +
										outputFile + ".mail >> " + logFile);
						PrintLog("Will Print Mail Address page");
						proc	= Runtime.getRuntime().exec(lprCmd);
						br		= new BufferedReader(new InputStreamReader(proc.getInputStream()));
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
					WritePDFFile(outputFile);	//,stmtFreq);
					pdfGenerated	= true;
				}
				if (deliverStmts.equals("Y")) {
					WriteFaxTextFile(stmt_fax, clientName, appl_date_f);
					BufferedWriter outPutWriter1	= new BufferedWriter(new FileWriter(outputFile + ".ack"));
					outPutWriter1.close();
				}
			}
		} catch (Throwable t) {
			PrintLog("WritePDFFile 1: " + t);
			t.printStackTrace(System.out);
		}
	}
	//
	public void GetAccount () {
		try {
			acctDetail	= acUtil.GetAccountRows(dbConn, curAcct);
			acctDetail.clearNulls();
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
		} catch (Throwable t) {
			PrintLog("Error getting Account row: "+t);
			//t.printStackTrace(System.out);
			return;
		}
	}
	//
	public void CheckAccount () {
	    String  timeStamp 		= "";
	    date			= new Date();
		DateFormat sdf	= new SimpleDateFormat("yyyyMMdd-HHmmss");
		procRec	= false;
		if (statementFrq.equals("Monthly")) {
			procRec	= true;
		}
		if (statementFrq.equals("Weekly") && 
			(stmt_emailfreqW.equals("Y") || 
			stmt_mailfreqW.equals("Y") || 
			stmt_faxfreqW.equals("Y"))) {
			procRec	= true;
		}
		if (statementFrq.equals("Daily") && 
			(stmt_emailfreqD.equals("Y") || 
			stmt_mailfreqD.equals("Y") || 
			stmt_faxfreqD.equals("Y"))) {
			procRec	= true;
		}
		if (statementFrq.equals("adhoc")) {
			procRec	= true;
		}
		if (procRec == true) {
			timeStamp	= sdf.format(date);
		    System.out.println(sdf.format(date));
			outputFile	= (outputDir + "ChexImages_" + appl_date.substring(0, 6) +
							"_" + curAcct +	"_" + statementFrq + "_" + 
							fileCount + "_" + timeStamp);
			PrintLog("Will create " + outputFile + ".pdf");
			try {
				//document		= new PDDocument();
				//outPutWriter	= new BufferedWriter(new FileWriter(outputFile + ".html"));
				WriteToPDF();
			} catch (Throwable t) {
				PrintLog("WriteHeader 2: " + t);
				t.printStackTrace(System.out);
			}
			procRec	= false;
		}
	}
	//Main
	public void GenerateImagePDF(ChexSelector chexSelector, String imageDir,
							String argOutputDir, String stmtFreq, Connection argdbConn,
							String deliverStmts) throws Exception {
		moduleName	= "GenerateImagePDF> ";
		//
		this.dbConn		= argdbConn;
		this.outputDir	= argOutputDir;
		statementFrq	= stmtFreq;
		server			= escl.getSmtpDomain();
		from			= escl.getFromEmailAddress();
		// from			= "andy.patil@webfiche.com";
		subject			= escl.getStmtSubject();
		msgFileDir		= escl.getStmtMsgDir();
		stmtPrinter		= escl.getStmtPrinter();
		imagesPerPDF	= escl.getImagesPerPDF();
		imgsPerPDF		= Integer.parseInt(imagesPerPDF);
		//
		appl_date_f		= chexSelector.getAppl_date_f();
		appl_date		= chexSelector.getAppl_date();
		int rowCount	= chexSelector.getDetail_count();
		//
		//chexSelector.clerNulls();
		monthStart		= chexSelector.getMonthStart();
		weekEnd			= chexSelector.getWeekEnd();
		dailyDate		= chexSelector.getDailyDate();
		fromPeriod		= chexSelector.getCh_from_period();
		toPeriod		= chexSelector.getCh_to_period();
		fromDate		= chexSelector.getCh_from_date();
		toDate			= chexSelector.getCh_to_date();
		bankId			= chexSelector.getBankId();
		int extractMonth= 0;
		//
		ArrayList<?> chexRow= new ArrayList<Object>();
		ChexDetail aRow		= new ChexDetail();
		//
		if (toPeriod.equals("NONE")) {
			statementFrq	= "Monthly";
			extractMonth	= Integer.parseInt(fromPeriod.substring(4, 6));
			monthStart		= ValiDate.getMonth(extractMonth);
		}
		if (toDate==null) {
			toDate	= "";
		}
		if (fromDate==null) {
			fromDate	= "";
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
		totalChecks	= chexRow.size();
		PrintLog ("Total Checks to process: "+totalChecks);
		Iterator<?> getCheck	= chexRow.iterator();
		int n	= 0;
		//cos		= new PDPageContentStream(document, stmtPage);
		//document.addPage(stmtPage);
		try {
			while (getCheck.hasNext()) {
				aRow		= (ChexDetail) getCheck.next();
				n++;
				curAcct		= aRow.getChex_account_num();
				chexNum		= aRow.getChex_check_num();
				chexAmt		= aRow.getChex_check_amount_disp();
				//PrintLog(n+":\tCheck Amount: "+chexAmt);
				procDateF	= aRow.getChex_proc_date_disp();
				procDate	= aRow.getChex_proc_date();
				if (bankId.equals("BNPMO")) {
					procDateF	= (procDate.substring(6) + "/" +
									procDate.substring(4, 6) + "/" + 
									procDate.substring(0, 4));
				}
				temp1		= aRow.getChex_image_f();
				imageFront	= imageDir + temp1.substring(temp1.indexOf("/", 1) + 1,	temp1.length());
				temp1		= aRow.getChex_image_b();
				imageBack	= imageDir + temp1.substring(temp1.indexOf("/", 1) + 1,	temp1.length());
				if (n == 1) {
					document	= new PDDocument();
					stmtPage 	= new PDPage(PDRectangle.A4);
					document.addPage(stmtPage);
					cos			= new PDPageContentStream(document, stmtPage);				
				    //textX		= startTextX;
				    textY		= startTextY;
				    image1X		= startX;
				    image1Y		= startY;
					fileCount	= 1;
					GetAccount();
					CheckAccount();
					preAcct		= curAcct;
					chexCount++;
				} 
				if (!curAcct.equals(preAcct)) {
					PrintLog("Will PROCESS Account(1) " + curAcct);
					ProcessAccount(outputDir, statementFrq, outputFile,	appl_date_f, deliverStmts);
					document	= new PDDocument();
					stmtPage 	= new PDPage(PDRectangle.A4);
					document.addPage(stmtPage);
					cos			= new PDPageContentStream(document, stmtPage);				
				    //textX		= startTextX;
				    textY		= startTextY;
				    image1X		= startX;
				    image1Y		= startY;
					fileCount	= 1;
					GetAccount();
					CheckAccount();
					preAcct		= curAcct;
					//acctTotal	= 1;
					chexCount	= 1;
				} else {
					if (chexCount == imgsPerPDF) {
						PrintLog("Will PROCESS Account(2) " + curAcct);
						WriteToPDF();
						ProcessAccount(outputDir, statementFrq, outputFile,	appl_date_f, deliverStmts);
						document	= new PDDocument();
						stmtPage 	= new PDPage(PDRectangle.A4);
						document.addPage(stmtPage);
						cos			= new PDPageContentStream(document, stmtPage);				
					    //textX	= startTextX;
					    textY	= startTextY;
					    image1X	= startX;
					    image1Y	= startY;
						fileCount++;
						//
						imgsAddedToPage	= 0;
						CheckAccount();
						PrintLog("Check Count " + chexCount);
						chexCount	= 1;
						n++;
					} else {
						if (n > 1) {
						    textY	= textY + offSet - 40;
						    image1Y	= image1Y + offSet - imgGapV;
						    //PrintLog("TextY: "+textY+" Image1Y: "+image1Y);
							if (statementFrq.equals("Monthly")) {
								WriteToPDF();
								preAcct	= curAcct;
							}
							if (statementFrq.equals("adhoc")) {
								WriteToPDF();
								preAcct	= curAcct;
							}
							if (statementFrq.equals("Weekly") && 
								(stmt_emailfreqW.equals("Y") ||
								stmt_mailfreqW.equals("Y") || 
								stmt_faxfreqW.equals("Y"))) {
								WriteToPDF();
								preAcct	= curAcct;
							}
							if (statementFrq.equals("Daily") && 
								(stmt_emailfreqD.equals("Y") ||
								stmt_mailfreqD.equals("Y") || 
								stmt_faxfreqD.equals("Y"))) {
								WriteToPDF();
								preAcct	= curAcct;
							}
							//acctTotal++;
							chexCount++;
							//imgsAddedToPage++;
						}
					}
				}
			}
			// Process the last Record here
			pdfGenerated	= false;
			PrintLog("Process Last Record # " + outputFile);
			if (imgsAddedToPage != imgsPerPage) {
				cos.close();
			}
			PrintLog("Will PROCESS Account(3) " + curAcct);
			ProcessAccount(outputDir, statementFrq, outputFile,	appl_date_f, deliverStmts);
		} catch (Throwable t) {
			PrintLog("IO-Ecxception " + t);
			t.printStackTrace(System.out);
		}
	}
}
