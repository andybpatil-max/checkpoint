package com.webfiche.checkpoint.util;

import java.io.Serializable;

//
public final class GenPDFUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	ProcessUtil	pUtil	= new ProcessUtil();
	//
	public GenPDFUtil() {
	}
	//
	public void GenPDF(String outputDir, String outputFile, int startPNum) throws Exception {
		String logFile		= outputDir + "html-2-ps-2-pdf.log";
		String html2ps[]	= { "/bin/sh", "-c", "" };
		String ps2pdf13[]	= { "/bin/sh", "-c", "" };
		String ps_file		= outputDir + "CheckImage.ps";
		String cmdStr		= "";
		// execute the command html2ps
		//
		// html2ps[2]	= ("html2ps -r "+ imageDir + " -i .23 -o " +
		// html2ps[2]	= ("html2ps -g -s .60 -n -i .35 -o " + // old good 7
		// checks
		//PrintLog("Will Generate PDF for " + preAcct + " record count " + acctCount);
		// html2ps[2]	= ("html2ps -g -s .70 -n -i .45 -o " + // old good 5or6
		// checks giving 4 checks
		// html2ps[2]	= ("html2ps -g -s .65 -n -N "+ startPNum +" -i .45 -o " +
		// // old good 5or6 checks see if it give 5 2010/04/29
		html2ps[2]	= ("html2ps -g -s .65 -n -N " + startPNum + " -i .45 -o " + 
						// old good 5or6 checks see if it give 5 2010/04/29
						// html2ps[2]	= ("html2ps -g -s .75 -n -i .50 -o " + // new good
						// 4 checks
						// outputFile + ".ps " +
						ps_file + " " + outputFile + ".html >> " + logFile);
		cmdStr		= html2ps[0] + html2ps[1] + html2ps[2];
		pUtil.ProcessCommand(cmdStr);
		//
		// execute the command ps2pdf
		//
		ps2pdf13[2]	= "ps2pdf13 " + ps_file + " " + outputFile + ".pdf >> " + logFile;
		// ps2pdf13[2]	= "ps2pdf13 " +
		// outputFile+".ps "+outputFile+".pdf >> "+logFile;
		cmdStr		= ps2pdf13[0] + ps2pdf13[1] + ps2pdf13[2];
		pUtil.ProcessCommand(cmdStr);
	}
}
