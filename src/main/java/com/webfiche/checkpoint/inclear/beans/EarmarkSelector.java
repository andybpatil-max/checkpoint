package com.webfiche.checkpoint.inclear.beans;

import java.io.Serializable;
import java.util.*;

public final class EarmarkSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String processDate		= "";
	private String actionFlag		= "";
	//
	private Vector<Vector<String>>  errorVec	= new Vector<Vector<String>>();
	//
	//private String[] filesToRelease	= { "", "", "", "", "", "" };
	private ArrayList<EarmarkDetail> relFile	= new ArrayList <EarmarkDetail>();
	private int	fileCount	= 0;
	//private int	recIndex	= 0;
	//
	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public String getProcessDate() {
		return processDate;
	}
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
	//
	public String getActionFlag() {
		return actionFlag;
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	//
	public void clearRelFile() {
		//file2Release.clear();
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	//
	public EarmarkDetail[] getFilerows() {
		//moduleName = "getCheckrows: ";
		EarmarkDetail results[] = new EarmarkDetail[relFile.size()];
		Iterator<EarmarkDetail> chex = relFile.iterator();
		int n = 0;
		while (chex.hasNext()) {
			results[n++] = (EarmarkDetail) chex.next();
		}
		// PrintLog("Detail count "+n);
		return (results);
	}
	//
	public ArrayList<EarmarkDetail> getRelFileArray() {
		return (this.relFile);
	}
	//
	public ArrayList<EarmarkDetail> getRelFile() {
		return (this.relFile);
	}
	//
	public EarmarkDetail getArow() {
		EarmarkDetail results = new EarmarkDetail();
		Iterator<EarmarkDetail> chex = relFile.iterator();
		results = (EarmarkDetail) chex.next();
		return (results);
	}
	//
	public EarmarkDetail getArow(int recIndex) {
		EarmarkDetail results = new EarmarkDetail();
		// Iterator chex = checkrows.iterator();
		//this.recIndex = recIndex;
		results = (EarmarkDetail) relFile.get(recIndex);
		return (results);
	}
	//
	// Populate the files name into the ArrayList
	//
	public void setRelFile(ArrayList<String> relFileArg) {
		relFile.clear();
		Iterator<String> files = relFileArg.iterator();
		int n = 0;
		while (files.hasNext()) {
			EarmarkDetail	emDetail	= new EarmarkDetail();
			emDetail.setProcessDate(this.processDate);
			emDetail.setActionFlag(this.actionFlag);
			emDetail.setProcessDate(this.processDate);
			emDetail.setFile2Release((String)files.next());
			//System.out.println (java.util.Calendar.getInstance().getTime()+
			//	    "> EarmarkSelector.setRelFile: "+emDetail.getFile2Release());
			this.relFile.add(n, emDetail);
			n++;
		}
		//PrintLog("File Count: "+n);
		this.fileCount = n;
	}
}
