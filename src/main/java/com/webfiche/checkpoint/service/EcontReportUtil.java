package com.webfiche.checkpoint.service;
/*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */
import java.io.*;
import java.util.*;
import com.webfiche.checkpoint.util.*;
//
public class EcontReportUtil {
	int line_ctr = 0;
	int page_ctr = 0;
	int page_size = 55; // including the headings
	int page_num_line = 1; // Line where the page Number gets printed
	int page_date_line = 3; // line where the report date is printed
	//
	String appl_date = "";
	private String moduleName;
	ArrayList<String> headings = new ArrayList<String>();
	GenUtil gUtil = new GenUtil();
	//
	protected FileWriter reportFile;
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()
				+ moduleName + logMsg);
	}
	public EcontReportUtil(String fileName) {
		try {
			reportFile = new FileWriter(fileName);
		} catch (IOException e) {
			PrintLog("EcontReportUtil: " + fileName + e.getMessage());
			// PrintLog(e.getMessage());
		}
	}
	//
	public void setHeadings(String heading) {
		headings.add(heading);
	}
	public ArrayList<String> getHeadings() {
		return (headings);
	}
	//
	public void setAppl_date(String appl_date) {
		this.appl_date = (appl_date.substring(4, 6) + "/" +
							appl_date.substring(6, 8) + "/" + appl_date.substring(0, 4));
	}
	public String getAppl_date() {
		return (appl_date);
	}
	//
	public void setPage_num_line(int page_num_line) {
		this.page_num_line = page_num_line;
	}
	public int getPage_num_line() {
		return (page_num_line);
	}
	//
	public void setPage_date_line(int page_date_line) {
		this.page_date_line = page_date_line;
	}
	public int getPage_date_line() {
		return (page_date_line);
	}
	//
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getPage_size() {
		return (page_size);
	}
	//
	protected void WriteLine(String lineIn) {
		try {
			reportFile.write(lineIn + "\n");
		} catch (IOException e) {
			PrintLog("WriteLine: " + e.getMessage());
		}
	}
	//
	public void Close() {
		try {
			reportFile.close();
		} catch (IOException e) {
			PrintLog("close: " + e.getMessage());
		}
	}
	//
	public void WriteHeadings() {
		String temp = "";
		if (page_ctr != 0) {
			temp = "\f\n";
			WriteLine(temp);
		}
		line_ctr = 0;
		page_ctr++;
		String page_ctr_s = page_ctr + "";
		int max_len = 4;
		temp = GenUtil.pad(page_ctr_s, max_len, " ");
		page_ctr_s = temp;
		Iterator<String> hdgs = headings.iterator();
		while (hdgs.hasNext()) {
			temp = (String) hdgs.next();
			if (line_ctr == page_num_line - 1) {
				WriteLine(temp + page_ctr_s);
			} else if (line_ctr == page_date_line - 1) {
				WriteLine(temp + " " + appl_date);
			} else {
				WriteLine(temp);
			}
			line_ctr++;
		}
	}
	//
	public void WriteDetail(String detail) {
		if (line_ctr >= page_size) {
			WriteHeadings();
		}
		WriteLine(detail);
		line_ctr++;
		if (line_ctr >= page_size) {
			WriteHeadings();
		}
	}
}
