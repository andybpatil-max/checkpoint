/*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */

package com.webfiche.checkpoint.sysadmin.service;

import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.util.*;

@Service
public class SysadControlUtil {
	private Class<?>	thisClass;
	private String	moduleName;
	private String	calledFrom;
	private String	className	= "> SysadControlUtil.";
	private String	unknownFields;
	private String	param		= "";
	ArrayList<ControlDetail> ctlBeans	= new ArrayList<ControlDetail>();
	private Vector<?>	xVec			= new Vector<Object>();
	XMLUtil				xUtil			= new XMLUtil();

	public SysadControlUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public int GetControlRow(Connection dbConn,	ControlSelector controlSelector, String prodId) {
		int rowCount = 0;
		className	= "> SysadControlUtil.";
		moduleName	= "GetControlRow: ";
		//PrintLog("ProductId: "+prodId);
		ctlBeans.clear();
		param = " where PRODUCTID='" + prodId + "' ";
		try {
			rowCount = GetControlRow(dbConn, controlSelector);
		} catch (Throwable t) {
			t.printStackTrace(System.out);
		}
		param = "";
		return (rowCount);
	}
	//
	public int GetControlRow(Connection dbConn, ControlSelector controlSelector)
			throws Exception {
		className			= "> SysadControlUtil.";
		moduleName			= "GetControlRows: ";
		String dbUsed		= controlSelector.getDbUsed();
		TreeMap<String, String> prodNames	= new TreeMap<String, String>();
		TreeMap<String, ControlDetail> ctlRows = new TreeMap <String, ControlDetail>();
		prodNames			= controlSelector.getProdNames();
		String prodId		= "";
		String varData		= "";
		StringBuffer sql	= new StringBuffer();
		int row_count		= 0;
		sql.append("SELECT PRODUCTID, BANKID, OURABA, DEFCURR, APPLDATE, PREVBIZDATE," +
					"NEXTBIZDATE, EODFLAG, BODFLAG, CODFLAG, EODOPER," +
					"BODOPER, CODOPER, EODTIME, BODTIME, CODTIME," +
					"VARDATA, MODTIME,MODUSER,MODFUNC " + "FROM system_control ");
		if (!param.equals("")) {
			sql.append(param);
		}
		sql.append(" ORDER BY PRODUCTID");
		//
		//PrintLog("GetControl SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		// PrintLog("Dbused >"+dbUsed+" <> "+sql);
		// TreeMap controlRows	= new TreeMap();
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			ControlDetail controlDetail = new ControlDetail();
			// PrintLog("Getting Rows");
			// Here add the fields to the ControlDetail bean
			/*
			 * "PRODUCTID, BANKID, OURABA, DEFCURR, APPLDATE, PREVBIZDATE,"+
			 * "NEXTBIZDATE, EODFLAG, BODFLAG, CODFLAG, EODOPER,"+
			 * "BODOPER, CODOPER, EODTIME, BODTIME, CODTIME,"+
			 * "VARDATA, MODTIME,MODUSER,MODFUNC")
			 */
			prodId	= result.getString("PRODUCTID").trim();
			PrintLog("Product Id: "+prodId);
			controlDetail.setDbUsed(dbUsed);
			prodId	= result.getString("PRODUCTID").trim();
			controlDetail.setProductId(prodId);
			controlDetail.setProdDesc(prodNames.get(prodId));
			controlDetail.setBankId(result.getString("BANKID").trim());
			controlDetail.setOurAba(result.getString("OURABA"));
			controlDetail.setDefCurr(result.getString("DEFCURR"));
			controlDetail.setApplDate(result.getString("APPLDATE"));
			controlDetail.setPrevBizDate(result.getString("PREVBIZDATE"));
			controlDetail.setNextBizDate(result.getString("NEXTBIZDATE"));
			controlDetail.setEodFlag(result.getString("EODFLAG"));
			controlDetail.setBodFlag(result.getString("BODFLAG"));
			controlDetail.setCodFlag(result.getString("CODFLAG"));
			controlDetail.setEodOper(result.getString("EODOPER"));
			controlDetail.setBodOper(result.getString("BODOPER"));
			controlDetail.setCodOper(result.getString("CODOPER"));
			controlDetail.setEodTime(result.getString("EODTIME"));
			controlDetail.setBodTime(result.getString("BODTIME"));
			controlDetail.setCodTime(result.getString("CODTIME"));
			varData = result.getString("VARDATA");
			//PrintLog(prodId+" VarData --> "+varData);
			controlDetail.setVarData(varData);
			ExplodeXML(varData, controlDetail);
			controlDetail.setUnknownFields(this.unknownFields);
			// PrintLog(prodId+" UnknowFields --> "+unknownFields);
			controlDetail.setModTime(result.getString("MODTIME"));
			controlDetail.setModUser(result.getString("MODUSER"));
			controlDetail.setModFunc(result.getString("MODFUNC"));
			// controlRows.put(prodId,controlDetail);
			ctlBeans.add(row_count, controlDetail);
			ctlRows.put(prodId, controlDetail);
			controlSelector.setCtlProds(prodId);
			row_count++;
			varData = "";
		}
		statement.close();
		result.close();
		// controlSelector.setControlRows(controlRows);
		controlSelector.setControlRows(ctlBeans);
		controlSelector.setControlRows(ctlRows);
		// PrintLog("Control Returned: Row Count "+row_count);
		return (row_count);
	}
	//
	public void ExplodeXML(String varData, ControlDetail methObj) {
		calledFrom	= moduleName;
		moduleName	= "ExplodeXML: ";
		if ((varData == null) || (varData.equals(" "))) {
			PrintLog("NO VARIABLE DATA PRESENT");
		} else {
			String fldName = "";
			// PrintLog("Var Data: "+varData);
			// this.varData = varData;
			this.unknownFields = "";
			// Vector xVec = new Vector();
			xVec.clear();
			xVec = xUtil.GetXMLFields(varData);
			// PrintLog("XML Elements: "+xVec.size());
			Object argList[] = new Object[1];
			Class<?> parTypes[] = new Class[1];
			String s = "";
			// ControlDetail methObj;// = new ControlDetail();
			try {
				thisClass = Class.forName("com.webfiche.checkpoint.sysadmin.beans.ControlDetail");
			} catch (Throwable e) {
				e.printStackTrace(System.out);
				PrintLog(varData+" "+argList[0]);
			}
			for (int j = 0; j < xVec.size(); j++) {
				s			= "";
				s			= (String) ((Vector<?>) xVec.get(j)).get(0);
				fldName		= s;
				s			= "set" + s.substring(0, 1).toUpperCase() + s.substring(1);
				argList[0]	= (String) ((Vector<?>) xVec.get(j)).get(1);
				//PrintLog(s+" "+argList[0]);
				try {
					parTypes[0]	= String.class;
					Method m	= thisClass.getMethod(s, parTypes);
					m.invoke(methObj, argList);
				} catch (Throwable e) {
					PrintLog(" Unknown Field: "+argList[0]);
					this.unknownFields	+= "<" + fldName + ">" + argList[0] +
											"</" + fldName + ">";
					e.printStackTrace(System.out);
				}
			}
		}
		moduleName	= calledFrom;
	}
	//
	public int InsertUpdateControl(Connection dbConn,
									ControlDetail controlDetail, String userId, int insOrUpd)
	throws Exception {
		moduleName			= "UpdateControl: ";
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		//
		String varData		= "";
		String dbUsed		= controlDetail.getDbUsed();
		String productId	= controlDetail.getProductId();
		String bankId		= controlDetail.getBankId();
		String ourAba		= controlDetail.getOurAba();
		String defCurr		= controlDetail.getDefCurr();
		String applDate		= controlDetail.getApplDate();
		String prevBizDate	= controlDetail.getPrevBizDate();
		String nextBizDate	= controlDetail.getNextBizDate();
		String eodFlag		= controlDetail.getEodFlag();
		String bodFlag		= controlDetail.getBodFlag();
		String codFlag		= controlDetail.getCodFlag();
		String eodOper		= controlDetail.getEodOper();
		String bodOper		= controlDetail.getBodOper();
		String codOper		= controlDetail.getCodOper();
		controlDetail.clearNulls();
		if (productId.equals("A")) {
			varData			= controlDetail.getVarDataA();
		//} else if (productId.equals("B")) {
		//	varData			= controlDetail.getVarDataB();
		} else if (productId.equals("C")) {
			varData			= controlDetail.getVarDataC();
		} else if (productId.equals("D")) {
			varData			= controlDetail.getVarDataD();
		//} else if (productId.equals("E")) {
		//	varData			= controlDetail.getVarDataE();
		}
		// PrintLog("db Used: "+dbUsed);
		//PrintLog("Variable Data: " + varData + " ProductId: " + productId);
		dbConn.setAutoCommit(false);
		sql.setLength(0);
		if (insOrUpd == 1) { // Add
			sql.append("INSERT into system_control VALUES ('" + productId +
						"', '" + bankId + "', '" + ourAba + "', '" + defCurr +
						"', '" + applDate + "', '" + prevBizDate + "','" +
						nextBizDate + "', '" + eodFlag + "', '" + bodFlag +
						"', '" + codFlag + "', '" + eodOper + "', '" + bodOper +
						"', '" + codOper + "', ");
			if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, sysdate, sysdate, '" + varData	+ "', sysdate, '");
			} else {
				sql.append("null, null, null, '" + varData + "', null, '");
			}
			sql.append(userId + "', 'Add Control')");
		} else {
			sql.append("UPDATE system_control  SET " + 
						"PRODUCTID='" + productId + "', " + 
						"BANKID='" + bankId + "', " +
						"APPLDATE='" + applDate + "', " + 
						"PREVBIZDATE='" + prevBizDate + "', " + 
						"NEXTBIZDATE='" + nextBizDate + "', " + 
						"DEFCURR='" + defCurr + "', " + 
						"OURABA='" + ourAba + "', " + 
						"EODFLAG='" + eodFlag + "', " +
						"BODFLAG='" + bodFlag + "', " + 
						"CODFLAG='" + codFlag + "', " + 
						"EODOPER='" + eodOper + "', " + 
						"BODOPER='" + bodOper + "', " + 
						"CODOPER='" + codOper + "', " +
						"VARDATA='" + varData + "', ");
			if (dbUsed.equals("ORACLE")) {
				sql.append("MODTIME=sysdate, ");
			} else {
				sql.append("MODTIME=null, ");
			}
			sql.append("MODUSER='" + userId + "', ");
			sql.append("MODFUNC='Modify Control' where PRODUCTID='" + productId	+ "'");
		}
		//PrintLog("Control row Update SQL ---> "+sql);
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Updating -->" + e.toString());
			PrintLog("Control row Update SQL ---> " + sql);
			try {
				dbConn.rollback();
			} catch (Exception ex) {
				PrintLog("Error Rolling Back Control: " + ex);
			}
			statement.close();
			return (0);
		}
		//
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		// result.close();
		return (1);
	}
}