package com.webfiche.checkpoint.beans;

import java.io.Serializable;
//
public final class SetDisplay implements Serializable {
	private static final long serialVersionUID 	= 1L;
	private int rowStart			= 0;
	private int rowEnd				= 20;
	private int rowsDisplayed		= 20;
	private int detail_count		= 0;
	private String rowsDispStr		= "";
	//
	public int getRowStart() {
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	public int getRowEnd() {
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	public int getRowsDisplayed() {
		return rowsDisplayed;
	}
	public void setRowsDisplayed(int rowsDisplayed) {
		this.rowsDisplayed = rowsDisplayed;
	}
	public int getDetail_count() {
		return detail_count;
	}
	public void setDetail_count(int detail_count) {
		this.detail_count = detail_count;
	}
	public String getRowsDispStr() {
		return rowsDispStr;
	}
	public void setRowsDispStr(String rowsDispStr) {
		this.rowsDispStr = rowsDispStr;
	}
}
