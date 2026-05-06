package com.webfiche.checkpoint.util;
//
import java.io.*;
import java.util.StringTokenizer;
//
public class BatchUtil {
	// private static int procResult = 0;
	private static Process proc;
	private static String  runCmd	= "";

	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> BatchUtil.ProcessBatch: " + logMsg);
	}
	//
	public void ProcessBatch(String execPgm, String formName,
								String imageDir, String resultDir) {
		String s;
		runCmd				= execPgm + " " + formName + " " + imageDir + " " + resultDir;
		StringTokenizer st	= new StringTokenizer(runCmd);
		String[] cmd		= new String[st.countTokens()];
		int total			= st.countTokens();
		for (int i = 0; i < total; i++) {
			cmd[i]	= st.nextToken(" ");
		}
		try {
			//PrintLog("Will execute: " + cmd);
			//pUtil.ProcessCommand(cmd);
			ProcessBuilder processBuilder	= new ProcessBuilder(cmd);
			proc	= processBuilder.start();
			BufferedReader br1	= new BufferedReader(new InputStreamReader(proc.getInputStream()));
			PrintLog("Started Processing Batch");
			proc.waitFor();
			while (true) {
				s	= br1.readLine();
				if (s == null)
					break;
				if (s.length() > 16) {
					if (s.indexOf("Processing file:", 0) > -1) {
						PrintLog(s);
					}
				}
			}
			br1.close();
		} catch (Throwable t) {
			PrintLog(" EXEC " + t);
			t.printStackTrace();
		}
		PrintLog("Completed Processing Batch");
	}
	//
	// For use from Command Line
	//
	public static void main(String[] args) throws Exception {
		BatchUtil ProcBatch	= new BatchUtil();
		String execPgm		= "";
		String formName		= "";
		String imageDir		= "";
		String resultDir	= "";
		//
		if (args.length == 0) { // Execute Batch if no arguments are provided
			PrintLog("Argumment needed -- using default");
			execPgm		= "h:\\BatchTest\\JustTest.bat";
		} else if (args.length == 1) { // Should be a full specification of a Batch file
			execPgm		= args[0];
		} else if (args.length == 4) {
			execPgm		= args[0];
			formName	= args[1];
			imageDir	= args[2];
			resultDir	= args[3];
		} else {
			PrintLog("Insufficient arguments -- Please provide Program, Form file, Image Dir and Result dir");
			System.exit(0);
		}
		ProcBatch.ProcessBatch(execPgm, formName, imageDir, resultDir);
	}
}
