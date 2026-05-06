package com.webfiche.checkpoint.deposits.beans;

import java.io.*;
import java.util.*;

public final class DepsEodParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -- Instance Variables
	private String actionFlag		= "";
	private String accessFlag		= "";
	private String dbUsed			= "";
	private String datadir			= "";
	private String reportdir		= "";
	private String imagedir			= "";
	private String product_id		= "";
	private String our_aba			= "";
	private String bankId			= "";
	private String eod_status		= "";
	private String eod_flag			= "";
	private String bod_flag			= "";
	private String file_loaded		= "";
	private String fileLoaded1		= "";
	private String fileLoaded2		= "";
	private String fileLoaded3		= "";
	private String fileLoaded4		= "";
	private String fileLoaded5		= "";
	private String currency			= "";
	private String createNewHistory = "N";
	private String today			= "";
	private String prev_biz_date	= "";
	private String next_biz_date	= "";
	private String one_m_back		= "";
	private String three_m_back		= "";
	private String six_m_back		= "";
	private String year_back		= "";
	private String posi_pay_purge	= "";
	private String chex_log_purge	= "";
	private String acct_log_purge	= "";
	private String posi_log_purge	= "";
	private String stop_log_purge	= "";
	private String holidays_purge	= "";
	private String eod_oper			= "";
	private String depsOsn			= "";
	private String sodOsn			= "";
	private String repHeader		= "";
	private ArrayList<String> holidays = new ArrayList<String>();

	// ----------------------------------------------------------- Properties
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	//
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String accessFlag) {
		this.accessFlag = accessFlag;
	}
	//
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getDatadir() {
		return (this.datadir);
	}
	public void setDatadir(String datadir) {
		this.datadir = datadir;
	}
	//
	public String getReportdir() {
		return (this.reportdir);
	}

	public void setReportdir(String reportdir) {
		this.reportdir = reportdir;
	}
	//
	public String getImagedir() {
		return (this.imagedir);
	}
	public void setImagedir(String imagedir) {
		this.imagedir = imagedir;
	}
	//
	public String getProduct_id() {
		return (this.product_id);
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	//
	public String getOur_aba() {
		return (this.our_aba);
	}
	public void setOur_aba(String our_aba) {
		this.our_aba = our_aba;
	}
	//
	public String getBankId() {
		return (this.bankId);
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	//
	public String getEod_status() {
		return (this.eod_status);
	}
	public void setEod_status(String eod_status) {
		this.eod_status = eod_status;
	}
	//
	public String getEod_flag() {
		return (this.eod_flag);
	}
	public void setEod_flag(String eod_flag) {
		this.eod_flag = eod_flag;
	}
	//
	public String getBod_flag() {
		return (this.bod_flag);
	}
	public void setBod_flag(String bod_flag) {
		this.bod_flag = bod_flag;
	}
	//
	public String getFile_loaded() {
		return (this.file_loaded);
	}
	public void setFile_loaded(String file_loaded) {
		this.file_loaded = file_loaded;
	}
	//
	public String getFileLoaded1() {
		return (this.fileLoaded1);
	}
	public void setFileLoaded1(String fileLoaded1) {
		this.fileLoaded1 = fileLoaded1;
	}
	//
	public String getFileLoaded2() {
		return (this.fileLoaded2);
	}
	public void setFileLoaded2(String fileLoaded2) {
		this.fileLoaded2 = fileLoaded2;
	}
	//
	public String getFileLoaded3() {
		return (this.fileLoaded3);
	}
	public void setFileLoaded3(String fileLoaded3) {
		this.fileLoaded3 = fileLoaded3;
	}
	//
	public String getFileLoaded4() {
		return (this.fileLoaded4);
	}
	public void setFileLoaded4(String fileLoaded4) {
		this.fileLoaded4 = fileLoaded4;
	}
	//
	public String getFileLoaded5() {
		return (this.fileLoaded5);
	}
	public void setFileLoaded5(String fileLoaded5) {
		this.fileLoaded5 = fileLoaded5;
	}
	//
	public String getCurrency() {
		return (this.currency);
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	//
	public String getCreateNewHistory() {
		return (this.createNewHistory);
	}
	public void setCreateNewHistory(String cnh_flag) {
		this.createNewHistory = cnh_flag;
	}
	//
	public String getToday() {
		return (this.today);
	}
	public void setToday(String today) {
		this.today = today;
	}
	//
	public String getPrev_biz_date() {
		return (this.prev_biz_date);
	}
	public void setPrev_biz_date(String prev_biz_date) {
		this.prev_biz_date = prev_biz_date;
	}
	//
	public String getNext_biz_date() {
		return (this.next_biz_date);
	}
	public void setNext_biz_date(String next_biz_date) {
		this.next_biz_date = next_biz_date;
	}
	//
	public String getOne_m_back() {
		return (this.one_m_back);
	}
	public void setOne_m_back(String one_m_back) {
		this.one_m_back = one_m_back;
	}
	//
	public String getThree_m_back() {
		return (this.three_m_back);
	}
	public void setThree_m_back(String three_m_back) {
		this.three_m_back = three_m_back;
	}
	//
	public String getSix_m_back() {
		return (this.six_m_back);
	}
	public void setSix_m_back(String six_m_back) {
		this.six_m_back = six_m_back;
	}
	//
	public String getYear_back() {
		return (this.year_back);
	}
	public void setYear_back(String year_back) {
		this.year_back = year_back;
	}
	//
	public String getPosi_pay_purge() {
		return (this.posi_pay_purge);
	}
	public void setPosi_pay_purge(String posi_pay_purge) {
		this.posi_pay_purge = posi_pay_purge;
	}
	//
	public String getChex_log_purge() {
		return (this.chex_log_purge);
	}
	public void setChex_log_purge(String chex_log_purge) {
		this.chex_log_purge = chex_log_purge;
	}
	//
	public String getAcct_log_purge() {
		return (this.acct_log_purge);
	}
	public void setAcct_log_purge(String acct_log_purge) {
		this.acct_log_purge = acct_log_purge;
	}
	//
	public String getPosi_log_purge() {
		return (this.posi_log_purge);
	}
	public void setPosi_log_purge(String posi_log_purge) {
		this.posi_log_purge = posi_log_purge;
	}
	//
	public String getStop_log_purge() {
		return (this.stop_log_purge);
	}
	public void setStop_log_purge(String stop_log_purge) {
		this.stop_log_purge = stop_log_purge;
	}
	//
	public String getHolidays_purge() {
		return (this.holidays_purge);
	}
	public void setHolidays_purge(String holidays_purge) {
		this.holidays_purge = holidays_purge;
	}
	//
	public String getEod_oper() {
		return (this.eod_oper);
	}
	public void setEod_oper(String eod_oper) {
		this.eod_oper = eod_oper.trim();
	}
	//
	public String getDepsOsn() {
		return (this.depsOsn);
	}
	public void setDepsOsn(String depsOsn) {
		this.depsOsn = depsOsn.trim();
	}
	//
	public String getSodOsn() {
		return (this.sodOsn);
	}
	public void setSodOsn(String sodOsn) {
		this.sodOsn = sodOsn.trim();
	}
	//
	public String getRepHeader() {
		return (this.repHeader);
	}
	public void setRepHeader(String repHeader) {
		this.repHeader = repHeader.trim();
	}
	//
	public ArrayList<String> getHolidays() {
		return (this.holidays);
	}
	//
	public void setHolidays(String a_holiday) {
		this.holidays.add(a_holiday);
	}
	//
	protected void finalize() {
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> ChexDetail: MemoryBlock finalized: "+this );
	}
}
