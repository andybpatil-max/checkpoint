package com.webfiche.checkpoint.util;

import java.io.*;
import java.util.StringTokenizer;
// private static int procResult = 0;
public class ProcessUtil {
	//
	private static String className		= "> ProcessUtil.";
	private static String moduleName	= "";
	private static Process proc;
	private static String  runCmd		= "";
	//
	private static void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//	
	public boolean ProcessCommand(String execCMD) throws Exception {
		className	= "> ProcessUtil.";
		moduleName	= "ProcessComamnd: ";
		String s;
		runCmd				= execCMD;
		StringTokenizer st	= new StringTokenizer(runCmd);
		String[] cmd		= new String[st.countTokens()];
		int total			= st.countTokens();
		//		
		for (int i = 0; i < total; i++) {
			cmd[i]	= st.nextToken(" ");
		}
		try {
			// PrintLog("Will execute: " + cmd);
			ProcessBuilder processBuilder	= new ProcessBuilder(cmd);
			proc				= processBuilder.start();
			BufferedReader br1	= new BufferedReader(new InputStreamReader(proc.getInputStream()));
			// procResult = proc.waitFor();
			PrintLog("Started Processing Command");
			proc.waitFor();
			// PrintLog("procResult " + procResult);
			while (true) {
				s	= br1.readLine();
				if (s == null)
					break;
				if (s.length() > 0) {
					PrintLog(s);
				}
			}
			br1.close();
			// PrintLog("procResult " + procResult);
		} catch (Throwable t) {
			PrintLog(" EXEC " + t);
			t.printStackTrace();
			return false;
		}
		PrintLog("Completed Processing Command");
		return true;
	}
	//
	// For use from Command Line
	//
	public static void main(String[] args) throws Exception {
		className			= ">ProcessUtil.";
		moduleName			= "Main: ";
		ProcessUtil pUtil	= new ProcessUtil();
		String execCMD		= "";
		//
		PrintLog("Number of Arguments: " + args.length);
		if (args.length == 0) { // Execute Batch if no arguments are provided
			PrintLog("Insufficient arguments -- Please provide Program, Form file, Image Dir and Result dir");
			System.exit(0);
		} else {
			for (int i = 0; i < args.length; i++) {
				execCMD	= execCMD + args[i] + " ";
			}
			PrintLog("Will process " + execCMD);
		}
		try {
			pUtil.ProcessCommand(execCMD);
		} catch (Throwable t) {
			PrintLog("Process Command Threw Exception " + t);
		}
	}
}
