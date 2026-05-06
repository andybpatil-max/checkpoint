package com.webfiche.checkpoint.sysadmin.beans;

//
import java.sql.*;
import java.io.Serializable;
import java.util.*;

import com.webfiche.checkpoint.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:30:29 $
 */
public final class ControlDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	// private Class thisClass;
	private String className			= "> ControlDetail.";
	private String moduleName;
	private String actionFlag			= "";
	private String accessFlag			= "";
	private String dbUsed				= "";
	private Connection dbConn;
	private String user_name			= "";
	private long depOSN					= 0;
	private long depOSN_o				= 0;
	private long sodDepOSN				= 0;
	private long sodDepOSN_o			= 0;
	private int newFCount				= 0;
	private int fileSeq					= 0;
	private String newFName				= " ";
	private String newFValue			= " ";
	private ArrayList<String> holidays	= new ArrayList<String>();
	//
	private String appl_date			= "";
	private String file_loaded			= "";
	private String fileToLoad			= "";
	private String file_loaded_time 	= "";
	private String file_loaded_1		= "";
	private String file_loaded_2		= "";
	private String file_loaded_3		= "";
	private String file_loaded_4		= "";
	private String file_loaded_5		= "";
	//
	private String file_loaded_o		= "";
	private String file_loaded_time_o	= "";
	private String file_loaded_1_o		= "";
	private String file_loaded_2_o		= "";
	private String file_loaded_3_o		= "";
	private String file_loaded_4_o		= "";
	private String file_loaded_5_o		= "";
	//
	// System Control Fields
	//
	private String productId			= "";
	private String prodDesc				= "";
	private String bankId				= "";
	private String ourAba				= "";
	private String defCurr				= "";
	private String applDate				= "";
	private String prevBizDate			= "";
	private String nextBizDate			= "";
	private String eodFlag				= "";
	private String bodFlag				= "";
	private String codFlag				= "";
	private String eodOper				= "";
	private String bodOper				= "";
	private String codOper				= "";
	private String eodTime				= "";
	private String bodTime				= "";
	private String codTime				= "";
	private String varData				= "";
	private String modTime				= "";
	private String modUser				= "";
	private String modFunc				= "";
	//
	private String bankId_o				= "";
	private String applDate_o			= "";
	//
	private String unknownFields		= "";
	//
	// System Variable fields for Product A
	//
	private String remBaseDir			= "";
	private String locInputDir			= "";
	private String locOutputDir			= "";
	private String imgBaseDir			= "";
	private String inclImgFile			= "";
	private String inclMicrFile			= "";
	private String depsMicrFile			= "";
	private String lboxMicrFile			= "";
	private String rdcIcl				= "";
	private String lboxIcl				= "";
	private String cifFile				= "";
	private String posiSwfFile			= "";
	private String posiCsvFile			= "";
	private String stopCsvFile			= "";
	private String sysRepHeader			= "";
	private String sysStmtPrinter		= "";
	private String mandateXML			= "";
	private String payerData			= "";
	//
	private String remBaseDir_o			= "";
	private String locInputDir_o		= "";
	private String locOutputDir_o		= "";
	private String imgBaseDir_o			= "";
	private String inclImgFile_o		= "";
	private String inclMicrFile_o		= "";
	private String depsMicrFile_o		= "";
	private String lboxMicrFile_o		= "";
	private String rdcIcl_o				= "";
	private String lboxIcl_o			= "";
	private String cifFile_o			= "";
	private String posiSwfFile_o		= "";
	private String posiCsvFile_o		= "";
	private String stopCsvFile_o		= "";
	private String sysRepHeader_o		= "";
	private String sysStmtPrinter_o		= "";
	private String mandateXML_o			= "";
	private String payerData_o			= "";
	//
	// System Variable fields for Product B
	//
	//
	// System Variable fields for Product C
	//
	private boolean firstFile = true;
	// private String cycleNum	= "0";
	//
	// System Variable fields for Product D
	//
	//
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
	XMLUtil xUtil	= new XMLUtil();
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	// ----------------------------------------------------------- Properties
	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return this.errorVec;
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair = new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public void setFirstFile(boolean firstFile) {
		this.firstFile = firstFile;
	}
	public boolean getFirstFile() {
		return (this.firstFile);
	}
	//
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	public String getActionFlag() {
		return (this.actionFlag);
	}
	//
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String accessFlag) {
		this.accessFlag = accessFlag;
	}
	//
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	public String getDbUsed() {
		return (this.dbUsed);
	}
	//
	public void setDbConn(Connection dbConn) {
		this.dbConn = dbConn;
	}
	public Connection getDbConn() {
		return (this.dbConn);
	}
	//
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_name() {
		return (this.user_name);
	}
	//
	public void setDepOSN(String depOSN) {
		this.depOSN = Long.parseLong(depOSN.trim());
	}
	public void setDepOSN(Long depOSN) {
		this.depOSN = depOSN;
	}
	public Long getDepOSN() {
		return (this.depOSN);
	}
	//
	public void setSodDepOSN(String sodDepOSN) {
		this.sodDepOSN = Long.parseLong(sodDepOSN.trim());
	}
	public void setSodDepOSN(Long sodDepOSN) {
		this.sodDepOSN = sodDepOSN;
	}
	public Long getSodDepOSN() {
		return (this.sodDepOSN);
	}
	//
	public void setHolidays(String a_holiday) {
		moduleName = "setHolidays: ";
		//PrintLog("setHolidays: " + a_holiday);
		this.holidays.add(a_holiday);
		// PrintLog("setHolidays: Holidays has "+this.holidays.size()+" elements");
	}
	public ArrayList<String> getHolidays() {
		return (this.holidays);
	}
	//
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankId() {
		return (this.bankId);
	}
	//
	public void setAppl_date(String appl_date) {
		this.appl_date = appl_date;
	}
	public String getAppl_date() {
		return (this.appl_date);
	}
	public void setFile_loaded(String file_loaded) {
		moduleName = "setFile_loaded: ";
		this.file_loaded = file_loaded;
		// PrintLog(this.file_loaded+" & "+file_loaded);
	}
	public String getFile_loaded() {
		return (this.file_loaded);
	}
	//
	public void setFileToLoad(String fileToLoad) {
		this.fileToLoad = fileToLoad;
	}
	public String getFileToLoad() {
		return (this.fileToLoad);
	}
	//
	public void setFile_loaded_time(String file_loaded_time) {
		this.file_loaded_time = file_loaded_time;
	}
	public String getFile_loaded_time() {
		return (this.file_loaded_time);
	}
	//
	public void setFile_loaded_1(String file_loaded_1) {
		this.file_loaded_1 = file_loaded_1;
		//PrintLog("this.file_loaded_1 = " + file_loaded_1);
	}
	public String getFile_loaded_1() {
		return (this.file_loaded_1);
	}
	//
	public void setFile_loaded_2(String file_loaded_2) {
		this.file_loaded_2 = file_loaded_2;
		//PrintLog("this.file_loaded_2 = " + file_loaded_2);
	}
	public String getFile_loaded_2() {
		return (this.file_loaded_2);
	}
	//
	public void setFile_loaded_3(String file_loaded_3) {
		this.file_loaded_3 = file_loaded_3;
		//PrintLog("this.file_loaded_3 = " + file_loaded_3);
	}
	public String getFile_loaded_3() {
		return (this.file_loaded_3);
	}
	//
	public void setFile_loaded_4(String file_loaded_4) {
		this.file_loaded_4 = file_loaded_4;
		//PrintLog("this.file_loaded_4 = " + file_loaded_4);
	}
	public String getFile_loaded_4() {
		return (this.file_loaded_4);
	}
	//
	public void setFile_loaded_5(String file_loaded_5) {
		this.file_loaded_5 = file_loaded_5;
		//PrintLog("this.file_loaded_5 = " + file_loaded_5);
	}
	public String getFile_loaded_5() {
		return (this.file_loaded_5);
	}
	//
	// Start SAVED fields here
	//
	public void setBankId_o(String bankId_o) {
		this.bankId_o = bankId_o;
	}
	public String getBankId_o() {
		return (this.bankId_o);
	}
	//
	public void setFile_loaded_o(String file_loaded_o) {
		this.file_loaded_o = file_loaded_o;
	}
	public String getFile_loaded_o() {
		return (this.file_loaded_o);
	}
	//
	public void setFile_loaded_time_o(String file_loaded_time_o) {
		this.file_loaded_time_o = file_loaded_time_o;
	}
	public String getFile_loaded_time_o() {
		return (this.file_loaded_time_o);
	}
	//
	public void setFile_loaded_1_o(String file_loaded_1_o) {
		this.file_loaded_1_o = file_loaded_1_o;
	}
	public String getFile_loaded_1_o() {
		return (this.file_loaded_1_o);
	}
	//
	public void setFile_loaded_2_o(String file_loaded_2_o) {
		this.file_loaded_2_o = file_loaded_2_o;
	}
	public String getFile_loaded_2_o() {
		return (this.file_loaded_2_o);
	}
	//
	public void setFile_loaded_3_o(String file_loaded_3_o) {
		this.file_loaded_3_o = file_loaded_3_o;
	}
	public String getFile_loaded_3_o() {
		return (this.file_loaded_3_o);
	}
	//
	public void setFile_loaded_4_o(String file_loaded_4_o) {
		this.file_loaded_4_o = file_loaded_4_o;
	}
	public String getFile_loaded_4_o() {
		return (this.file_loaded_4_o);
	}
	//
	public void setFile_loaded_5_o(String file_loaded_5_o) {
		this.file_loaded_5_o = file_loaded_5_o;
	}
	public String getFile_loaded_5_o() {
		return (this.file_loaded_5_o);
	}
	//
	// Other Methods
	//
	public void setControl_make_copy() {
		/*
		 * product_id_o = product_id; bankId_o = bankId; appl_date_o =
		 * appl_date; def_currency_o = def_currency; our_aba_o = our_aba;
		 * eod_flag_o = eod_flag; bod_flag_o = bod_flag;
		 */
		clearNulls();
		file_loaded_o		= file_loaded;
		file_loaded_time_o	= file_loaded_time;
		file_loaded_1_o		= file_loaded_1;
		file_loaded_2_o		= file_loaded_2;
		file_loaded_3_o		= file_loaded_3;
		file_loaded_4_o		= file_loaded_4;
		file_loaded_5_o		= file_loaded_5;
		/*
		 * eod_oper_o = eod_oper; bod_oper_o = bod_oper; prev_biz_date_o =
		 * prev_biz_date; next_biz_date_o = next_biz_date; eod_time_o =
		 * eod_time; bod_time_o = bod_time; rollover_time_o = rollover_time;
		 * image_file_o = image_file; micr_file_o = micr_file; cif_file_o =
		 * cif_file; posi_file_sw_o = posi_file_sw; posi_file_csv_o =
		 * posi_file_csv; stop_file_sw_o = stop_file_sw; stop_file_csv_o =
		 * stop_file_csv; interface_f8_o = interface_f8; interface_f9_o =
		 * interface_f9; interface_f10_o = interface_f10; system_mode_o =
		 * system_mode;
		 */
	}
	//
	public int CheckForChanges() {
		moduleName			= "CheckForChanges: ";
		int data_changed	= 0;
		int todayYYYY		= 0;
		int todayMM			= 0;
		int todayDD			= 0;
		String temp			= "";
		// if the Appl date is changed get the next and Prev business date
		// and set the fields values
		// If any of the file locations fields have changed then update.
		if (applDate_o.compareTo(applDate) != 0) {
			PrintLog("CheckForChanges: applDate " + applDate_o + " _ " + applDate);
			data_changed	= 1;
			//
			temp			= this.applDate;
			todayYYYY		= Integer.parseInt(temp.substring(0, 4));
			todayMM			= Integer.parseInt(temp.substring(4, 6));
			todayDD			= Integer.parseInt(temp.substring(6));
			PrintLog("Holidays has " + this.holidays.size() + " elements");
			this.nextBizDate = ValiDate.getNextBusinessDay(todayMM, todayDD,
															todayYYYY, this.holidays);
			this.prevBizDate = ValiDate.getPrevBusinessDay(todayMM, todayDD,
															todayYYYY, this.holidays);
			PrintLog("appl_date     " + this.applDate);
			PrintLog("next_biz_date " + this.nextBizDate);
			PrintLog("prev_biz_date " + this.prevBizDate);
		}
		if (depOSN_o != depOSN) {
			PrintLog("CheckForChanges: depOSN");
			data_changed	= 1;
		}
		if (sodDepOSN_o != sodDepOSN) {
			PrintLog("CheckForChanges: sodDepOSN");
			data_changed	= 1;
		}
		if (remBaseDir_o.compareTo(remBaseDir) != 0) {
			PrintLog("CheckForChanges: remBaseDir");
			data_changed	= 1;
		}
		if (locInputDir_o.compareTo(locInputDir) != 0) {
			PrintLog("CheckForChanges: locInputDir");
			data_changed	= 1;
		}
		if (locOutputDir_o.compareTo(locOutputDir) != 0) {
			PrintLog("CheckForChanges: locOutputDir");
			data_changed	= 1;
		}
		if (imgBaseDir_o.compareTo(imgBaseDir) != 0) {
			PrintLog("CheckForChanges: imgBaseDir");
			data_changed	= 1;
		}
		if (inclImgFile_o.compareTo(inclImgFile) != 0) {
			PrintLog("CheckForChanges: inclImgFile");
			data_changed	= 1;
		}
		if (inclMicrFile_o.compareTo(inclMicrFile) != 0) {
			PrintLog("CheckForChanges: inclMicrFile");
			data_changed	= 1;
		}
		if (depsMicrFile_o.compareTo(depsMicrFile) != 0) {
			PrintLog("CheckForChanges: depsMicrFile");
			data_changed	= 1;
		}
		if (lboxMicrFile_o.compareTo(lboxMicrFile) != 0) {
			PrintLog("CheckForChanges: lboxMicrFile");
			data_changed	= 1;
		}
		if (rdcIcl_o.compareTo(rdcIcl) != 0) {
			PrintLog("CheckForChanges: rdcIcl");
			data_changed	= 1;
		}
		if (lboxIcl_o.compareTo(lboxIcl) != 0) {
			PrintLog("CheckForChanges: lboxIcl");
			data_changed	= 1;
		}
		if (cifFile_o.compareTo(cifFile) != 0) {
			PrintLog("CheckForChanges: cifFile");
			data_changed	= 1;
		}
		if (posiSwfFile_o.compareTo(posiSwfFile) != 0) {
			PrintLog("CheckForChanges: posiSwfFile");
			data_changed	= 1;
		}
		if (posiCsvFile_o.compareTo(posiCsvFile) != 0) {
			PrintLog("CheckForChanges: posiCsvFile");
			data_changed	= 1;
		}
		if (stopCsvFile_o.compareTo(stopCsvFile) != 0) {
			PrintLog("CheckForChanges: stopCsvFile");
			data_changed	= 1;
		}
		if (sysRepHeader_o.compareTo(sysRepHeader) != 0) {
			PrintLog("CheckForChanges: sysRepHeader");
			data_changed	= 1;
		}
		if (sysStmtPrinter_o.compareTo(sysStmtPrinter) != 0) {
			PrintLog("CheckForChanges: sysStmtPrinter");
			data_changed	= 1;
		}
		if (mandateXML_o.compareTo(mandateXML) != 0) {
			PrintLog("CheckForChanges: mandateXML");
			data_changed	= 1;
		}
		if (payerData_o.compareTo(payerData) != 0) {
			PrintLog("CheckForChanges: payerData");
			data_changed	= 1;
		}
		return (data_changed);
	}
	//
	public boolean NewControlFieldsValid() {
		moduleName	= "NewControlFieldsValid: ";
		// if the Appl date is changed get the next and Prev business date
		// and set the fields values
		// If any of the file locations fields have changed then update.
		int todayYYYY	= 0;
		int todayMM		= 0;
		int todayDD		= 0;
		String temp		= "";
		PrintLog("appl_date " + applDate);
		temp = this.applDate;
		todayYYYY		= Integer.parseInt(temp.substring(0, 4));
		todayMM			= Integer.parseInt(temp.substring(4, 6));
		todayDD			= Integer.parseInt(temp.substring(6));
		PrintLog("Holidays has " + this.holidays.size() + " elements");
		this.nextBizDate	= ValiDate.getNextBusinessDay(todayMM, todayDD,
															todayYYYY, this.holidays);
		this.prevBizDate	= ValiDate.getPrevBusinessDay(todayMM, todayDD,
															todayYYYY, this.holidays);
		PrintLog("appl_date     " + this.applDate);
		PrintLog("nextBizDate " + this.nextBizDate);
		PrintLog("prevBizDate " + this.prevBizDate);
		return (true);
	}
	//
	// Setters and Getters for System Control
	//
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductId() {
		return (this.productId);
	}
	//
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getProdDesc() {
		return (this.prodDesc);
	}
	//
	public void setOurAba(String ourAba) {
		this.ourAba = ourAba;
	}
	public String getOurAba() {
		return (this.ourAba);
	}
	//
	public void setDefCurr(String defCurr) {
		this.defCurr = defCurr;
	}
	public String getDefCurr() {
		return (this.defCurr);
	}
	//
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getApplDate() {
		return (this.applDate);
	}
	//
	public void setPrevBizDate(String prevBizDate) {
		this.prevBizDate = prevBizDate;
	}
	public String getPrevBizDate() {
		return (this.prevBizDate);
	}
	//
	public void setNextBizDate(String nextBizDate) {
		this.nextBizDate = nextBizDate;
	}
	public String getNextBizDate() {
		return (this.nextBizDate);
	}
	//
	public void setEodFlag(String eodFlag) {
		this.eodFlag = eodFlag;
	}
	public String getEodFlag() {
		return (this.eodFlag);
	}
	//
	public void setBodFlag(String bodFlag) {
		this.bodFlag = bodFlag;
	}
	public String getBodFlag() {
		return (this.bodFlag);
	}
	//
	public void setCodFlag(String codFlag) {
		this.codFlag = codFlag;
	}
	public String getCodFlag() {
		return (this.codFlag);
	}
	//
	public void setEodOper(String eodOper) {
		this.eodOper = eodOper;
	}
	public String getEodOper() {
		return (this.eodOper);
	}
	//
	public void setBodOper(String bodOper) {
		this.bodOper = bodOper;
	}
	public String getBodOper() {
		return (this.bodOper);
	}
	//
	public void setCodOper(String codOper) {
		this.codOper = codOper;
	}
	public String getCodOper() {
		return (this.codOper);
	}
	//
	public void setEodTime(String eodTime) {
		this.eodTime = eodTime;
	}
	public String getEodTime() {
		return (this.eodTime);
	}
	//
	public void setBodTime(String bodTime) {
		this.bodTime = bodTime;
	}
	public String getBodTime() {
		return (this.bodTime);
	}
	//
	public void setCodTime(String codTime) {
		this.codTime = codTime;
	}
	public String getCodTime() {
		return (this.codTime);
	}
	//
	// Here Set should parse the XML and populate the fileds and
	// get should build the XML from the fields and return
	//
	public void setRemBaseDir(String remBaseDir) {
		this.remBaseDir = remBaseDir;
	}
	public String getRemBaseDir() {
		return (this.remBaseDir);
	}
	//
	public void setLocInputDir(String locInputDir) {
		this.locInputDir = locInputDir;
	}
	public String getLocInputDir() {
		return (this.locInputDir);
	}
	//
	public void setLocOutputDir(String locOutputDir) {
		this.locOutputDir = locOutputDir;
	}
	public String getLocOutputDir() {
		return (this.locOutputDir);
	}
	//
	public void setImgBaseDir(String imgBaseDir) {
		this.imgBaseDir = imgBaseDir;
	}
	public String getImgBaseDir() {
		return (this.imgBaseDir);
	}
	//
	public void setInclImgFile(String inclImgFile) {
		this.inclImgFile = inclImgFile;
	}
	public String getInclImgFile() {
		return (this.inclImgFile);
	}
	//
	public void setInclMicrFile(String inclMicrFile) {
		this.inclMicrFile = inclMicrFile;
	}
	public String getInclMicrFile() {
		return (this.inclMicrFile);
	}
	//
	public void setDepsMicrFile(String depsMicrFile1) {
		moduleName = "setDepsMicrFile: ";
		// PrintLog("DepsMicrFile: "+depsMicrFile1);
		this.depsMicrFile = depsMicrFile1;
	}
	public String getDepsMicrFile() {
		return (this.depsMicrFile);
	}
	//
	public void setLboxMicrFile(String lboxMicrFile1) {
		moduleName = "setLboxMicrFile: ";
		// PrintLog("DepsMicrFile: "+lboxMicrFile1);
		this.lboxMicrFile = lboxMicrFile1;
	}
	public String getLboxMicrFile() {
		return (this.lboxMicrFile);
	}
	//
	public void setRdcIcl(String rdcIcl) {
		moduleName = "setRdcIcl: ";
		// PrintLog("rdcIcl: "+rdcIcl);
		this.rdcIcl	= rdcIcl;
	}
	public String getRdcIcl() {
		return (this.rdcIcl);
	}
	//
	public void setLboxIcl(String lboxIcl) {
		moduleName = "setLboxIcl: ";
		// PrintLog("DepsMicrFile: "+lboxMicrFile1);
		this.lboxIcl = lboxIcl;
	}
	public String getLboxIcl() {
		return (this.lboxIcl);
	}
	//
	public void setCifFile(String cifFile) {
		this.cifFile = cifFile;
	}
	public String getCifFile() {
		return (this.cifFile);
	}
	//
	public void setPosiSwfFile(String posiSwfFile) {
		this.posiSwfFile = posiSwfFile;
	}
	public String getPosiSwfFile() {
		return (this.posiSwfFile);
	}
	//
	public void setPosiCsvFile(String posiCsvFile) {
		this.posiCsvFile = posiCsvFile;
	}
	public String getPosiCsvFile() {
		return (this.posiCsvFile);
	}
	//
	public void setStopCsvFile(String stopCsvFile) {
		this.stopCsvFile = stopCsvFile;
	}
	public String getStopCsvFile() {
		return (this.stopCsvFile);
	}
	//
	public void setSysRepHeader(String sysRepHeader) {
		this.sysRepHeader = sysRepHeader;
	}
	public String getSysRepHeader() {
		return (this.sysRepHeader);
	}
	//
	public void setSysStmtPrinter(String sysStmtPrinter) {
		this.sysStmtPrinter = sysStmtPrinter;
	}
	public String getSysStmtPrinter() {
		return (this.sysStmtPrinter);
	}
	// mandateXML
	public void setMandateXML(String mandateXML) {
		this.mandateXML = mandateXML;
	}
	public String getMandateXML() {
		return (this.mandateXML);
	}
	// Payerdata
	public void setPayerData(String payerData) {
		this.payerData	= payerData;
	}
	public String getPayerData() {
		return (this.payerData);
	}
	//
	public void setVarData(String varData) {// , ControlDetail methObj) {
		moduleName = "setVarData: ";
		// PrintLog("Var Data: "+varData);
		this.varData = varData;
		this.unknownFields = "";
	}
	//
	public void setUnknownFields(String unknownFields) {
		this.unknownFields = unknownFields;
	}
	public String getUnknownFields() {
		return this.unknownFields;
	}
	//
	public String getVarData() {
		moduleName			= "getVarData: ";
		String newFields	= getNewFields();
		if (unknownFields == null) {
			unknownFields	= "";
		}
		String varData	= ("<variable>" + unknownFields);
		if (newFields.length() > 0) {
			varData		= varData + getNewFields();
		}
		varData	= varData + "</variable>";
		return (varData);
	}
	//
	public void setNewFCount(int newFCount) {
		this.newFCount	= newFCount;
	}
	public int getNewFCount() {
		return this.newFCount;
	}
	//
	public void setFileSeq(int fileSeq) {
		this.fileSeq	= fileSeq;
	}
	public int getFileSeq() {
		return this.fileSeq;
	}
	//
	public String getNewFName() {
		return this.newFName;
	}
	public void setNewFName(String newFName) {
		this.newFName	= newFName;
	}
	//
	public String getNewFValue() {
		return this.newFValue;
	}
	public void setNewFValue(String fieldValue) {
		this.newFValue	= fieldValue;
	}
	//
	public String getNewFields() {
		moduleName			= "getNewFields: ";
		String newFieldsXml	= "";
		if (newFCount > 0) {
			newFieldsXml	= ("<" + newFName + ">" + newFValue + "</" + newFName + ">");
		}
		PrintLog("New Fields: " + newFieldsXml);
		return (newFieldsXml);
	}
	//
	public void clearNulls() {
		file_loaded		= (file_loaded == null) ? " " : file_loaded;
		fileToLoad		= (fileToLoad == null) ? " " : fileToLoad;
		file_loaded_1	= (file_loaded_1 == null) ? " " : file_loaded_1;
		file_loaded_2	= (file_loaded_2 == null) ? " " : file_loaded_2;
		file_loaded_3	= (file_loaded_3 == null) ? " " : file_loaded_3;
		file_loaded_4	= (file_loaded_4 == null) ? " " : file_loaded_4;
		file_loaded_5	= (file_loaded_5 == null) ? " " : file_loaded_5;
	}
	//
	public void clearDetails() {
		productId	= "";
		// bankId	= "";
		// ourAba	= "";
		// defCurr	= "";
		applDate	= " ";
		prevBizDate	= " ";
		nextBizDate	= " ";
		eodFlag		= " ";
		bodFlag		= " ";
		codFlag		= " ";
		eodOper		= " ";
		bodOper		= " ";
		codOper		= " ";
		eodTime		= " ";
		bodTime		= " ";
		codTime		= " ";
		varData		= " ";
		modTime		= " ";
		modUser		= " ";
		modFunc		= " ";
	}
	//
	public void SaveVarFields() {
		applDate_o			= applDate;
		depOSN_o			= depOSN;
		remBaseDir_o		= remBaseDir;
		locInputDir_o		= locInputDir;
		locOutputDir_o		= locOutputDir;
		imgBaseDir_o		= imgBaseDir;
		inclImgFile_o		= inclImgFile;
		inclMicrFile_o		= inclMicrFile;
		depsMicrFile_o		= depsMicrFile;
		lboxMicrFile_o		= lboxMicrFile;
		rdcIcl_o			= rdcIcl;
		lboxIcl_o			= lboxIcl;
		cifFile_o			= cifFile;
		posiSwfFile_o		= posiSwfFile;
		posiCsvFile_o		= posiCsvFile;
		stopCsvFile_o		= stopCsvFile;
		sysRepHeader_o		= sysRepHeader;
		sysStmtPrinter_o	= sysStmtPrinter;
		mandateXML_o		= mandateXML;
		payerData_o			= payerData;
	}
	//
	public void ShowVarFields() {
		moduleName = "ShowVarFields: ";
		PrintLog("depOSN\t\t" + depOSN);
		PrintLog("remBaseDir\t\t" + remBaseDir);
		PrintLog("locInputDir\t\t" + locInputDir);
		PrintLog("locOutputDir\t\t" + locOutputDir);
		PrintLog("imgBaseDir\t\t" + imgBaseDir);
		PrintLog("inclImgFile\t\t" + inclImgFile);
		PrintLog("inclMicrFile\t\t" + inclMicrFile);
		PrintLog("depsMicrFile\t\t" + depsMicrFile);
		PrintLog("lboxMicrFile\t\t" + lboxMicrFile);
		PrintLog("rdcIcl\t\t" + rdcIcl);
		PrintLog("lboxIcl\t\t" + lboxIcl);
		PrintLog("cifFile\t\t" + cifFile);
		PrintLog("posiSwfFile\t\t" + posiSwfFile);
		PrintLog("posiCsvFile\t\t" + posiCsvFile);
		PrintLog("stopCsvFile\t\t" + stopCsvFile);
		PrintLog("sysRepHeader\t\t" + sysRepHeader);
		PrintLog("sysStmtPrinter\t\t" + sysStmtPrinter);
		PrintLog("mandateXML\t\t" + mandateXML);
		PrintLog("payerData\t\t" + payerData);
	}
	//
	public String getVarDataA() {
		//
		// Here rebuild the XML for Update
		//
		String newFields = getNewFields();
		PrintLog("unknownFields: " + unknownFields);
		if (unknownFields == null) {
			unknownFields = "";
		}
		// PrintLog("remBaseDir\t\t"+ remBaseDir);
		varData = ("<variable>" + 
					"<remBaseDir>"     + remBaseDir     + "</remBaseDir>" +
					"<locInputDir>"    + locInputDir    + "</locInputDir>" +
					"<locOutputDir>"   + locOutputDir   + "</locOutputDir>" +
					"<imgBaseDir>"     + imgBaseDir     + "</imgBaseDir>" +
					"<inclImgFile>"    + inclImgFile    + "</inclImgFile>" +
					"<inclMicrFile>"   + inclMicrFile   + "</inclMicrFile>" +
					"<depsMicrFile>"   + depsMicrFile   + "</depsMicrFile>" +
					"<lboxMicrFile>"   + lboxMicrFile   + "</lboxMicrFile>" +
					"<rdcIcl>"         + rdcIcl         + "</rdcIcl>" +
					"<lboxIcl>"        + lboxIcl        + "</lboxIcl>" +
					"<cifFile>"        + cifFile        + "</cifFile>" + 
					"<posiSwfFile>"    + posiSwfFile    + "</posiSwfFile>" + 
					"<posiCsvFile>"    + posiCsvFile    + "</posiCsvFile>" + 
					"<stopCsvFile>"    + stopCsvFile    + "</stopCsvFile>" + 
					"<sysRepHeader>"   + sysRepHeader   + "</sysRepHeader>" + 
					"<sysStmtPrinter>" + sysStmtPrinter + "</sysStmtPrinter>" + 
					"<mandateXML>"     + mandateXML     + "</mandateXML>" + 
					"<payerData>"      + payerData      + "</payerData>" + 
					unknownFields);
		if (newFields.length() > 0) {
			varData = varData + getNewFields();
		}
		varData = varData + "</variable>";
		return (varData);
	}
	//
	public String getVarDataB() {
		/*
		 * no fields at this time
		 */
		varData = varData + "</variable>";
		return (this.varData);
	}
	//
	public String getVarDataC() {
		String newFields	= getNewFields();
		if (unknownFields == null) {
			unknownFields	= "";
		}
		varData	= ("<variable>" + 
					"<file_loaded>" + file_loaded + "</file_loaded>" + 
					"<file_loaded_1>" + file_loaded_1 + "</file_loaded_1>" + 
					"<file_loaded_2>" + file_loaded_2 + "</file_loaded_2>" + 
					"<file_loaded_3>" + file_loaded_3 + "</file_loaded_3>" + 
					"<file_loaded_4>" + file_loaded_4 + "</file_loaded_4>" + 
					"<file_loaded_5>" + file_loaded_5 + "</file_loaded_5>" + 
					unknownFields);
		if (newFields.length() > 0) {
			varData	= varData + getNewFields();
		}
		if (varData.equals("")) {
			varData	= "<variable> </variable>";
		} else {
			varData	= varData + "</variable>";
		}
		return (this.varData);
	}
	//
	public String getVarDataD() {
		String newFields	= getNewFields();
		if (unknownFields == null) {
			unknownFields	= "";
		}
		varData	= ("<variable>"       + 
					"<depOSN>"        + depOSN        + "</depOSN>"        +
					"<sodDepOSN>"     + sodDepOSN     + "</sodDepOSN>"     + 
					"<file_loaded>"   + file_loaded   + "</file_loaded>"   + 
					"<file_loaded_1>" + file_loaded_1 + "</file_loaded_1>" + 
					"<file_loaded_2>" + file_loaded_2 + "</file_loaded_2>" + 
					"<file_loaded_3>" + file_loaded_3 + "</file_loaded_3>" + 
					"<file_loaded_4>" + file_loaded_4 + "</file_loaded_4>" + 
					"<file_loaded_5>" + file_loaded_5 + "</file_loaded_5>" + 
					unknownFields);
		if (newFields.length() > 0) {
			varData	= varData + getNewFields();
		}
		if (varData.equals("")) {
			varData	= "<variable> </variable>";
		} else {
			varData	= varData + "</variable>";
		}
		return (this.varData);
	}
	//
	public String getVarDataE() {
		String newFields	= getNewFields();
		if (unknownFields == null) {
			unknownFields	= "";
		}
		varData	= ("<variable>"       + 
					"<depOSN>"        + depOSN        + "</depOSN>"        +
					"<sodDepOSN>"     + sodDepOSN     + "</sodDepOSN>"     + 
					"<file_loaded>"   + file_loaded   + "</file_loaded>"   + 
					"<file_loaded_1>" + file_loaded_1 + "</file_loaded_1>" + 
					"<file_loaded_2>" + file_loaded_2 + "</file_loaded_2>" + 
					"<file_loaded_3>" + file_loaded_3 + "</file_loaded_3>" + 
					"<file_loaded_4>" + file_loaded_4 + "</file_loaded_4>" + 
					"<file_loaded_5>" + file_loaded_5 + "</file_loaded_5>" + 
					unknownFields);
		if (newFields.length() > 0) {
			varData	= varData + getNewFields();
		}
		if (varData.equals("")) {
			varData	= "<variable> </variable>";
		} else {
			varData	= varData + "</variable>";
		}
		return (this.varData);
	}
	//
	public void setModTime(String modTime) {
		this.modTime = modTime;
	}
	public String getModTime() {
		return (this.modTime);
	}
	//
	public void setModUser(String modUser) {
		this.modUser = modUser;
	}
	public String getModUser() {
		return (this.modUser);
	}
	//
	public void setModFunc(String modFunc) {
		this.modFunc = modFunc;
	}
	public String getModFunc() {
		return (this.modFunc);
	}
	//
	protected void finalize() {
		// "> ControlDetail: MemoryBlock finalized: "+this );
	}
}
