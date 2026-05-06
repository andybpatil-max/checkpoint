package com.webfiche.checkpoint.util;

import java.io.*;
import java.sql.*;
import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;

public class DbCheckTable {
	 private String className = "> DbCheckTable.";
	 private String moduleName;
	// private String calledFrom;
	private StringBuffer	sql		= new StringBuffer();
	Statement				stmt	= null;
	//EcontServletContextListener	escl	= new EcontServletContextListener();
	//
	private void PrintLog (String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime()+
				className+moduleName+logMsg); }
	//
	public void CheckIfTableExists(Connection dbConn, String inclSchema, String tableName,
			String sqlScript, String initSql) throws Exception {
		moduleName		= "CheckIfTableExists: ";
		//
		//Statement stmt = null;
		//String dbUsed	= escl.getDbUsed();
		String tbName	= "";
		String inRec	= "";
		//int updResult	= 0;
		BufferedReader inRead	= null;
		DatabaseMetaData mD		= dbConn.getMetaData();
		// ResultSet rset = mD.getTables(null, null, tableName.toLowerCase(),
		// new String[]{"TABLE"});
		/**/
		ResultSet rset	= mD.getTables(null, inclSchema, tableName, new String[] { "TABLE" });
		//PrintLog("Init SQL : '"+initSql+"'");
		//PrintLog("TABLE_NAME 1: '"+tableName+"'");
		while (rset.next()) {
			tbName	= rset.getString("TABLE_NAME");
			//PrintLog("TABLE_NAME 1: '"+tbName+"'");
			if (tbName.equals(tableName)) {
				//PrintLog("TABLE_NAME 1: '"+tbName+"'");
				break;
			}
		}
		while (rset.next()) {
			tbName = rset.getString("TABLE_NAME");
			//System.out.println(rset.getString(1));
		}
		//PrintLog("Passed TABLE_NAME 2: '"+tableName+"' Found TABLE_NAME 3: '"+tbName+"'");
		if (tbName.equals("")) {
			// create the table
			if (!sqlScript.equals("")) {
				sql.setLength(0);
				inRec = "";
				sql.setLength(0);
				inRead = new BufferedReader(new FileReader(sqlScript));
				while ((inRec = inRead.readLine()) != null) {
					if (inRec.indexOf("&1") > 0) {
						inRec = inRec.replaceAll("&1", tableName.toLowerCase());
						//PrintLog(inRec);
					}
					if (inRec.length() > 3) {
						if (inRec.substring(0,3).equals("REM")) {
						} else {
							inRec	= inRec.replaceAll("\\);", "\\)");
							sql.append(inRec + '\n');
						}
					}
				}
				inRead.close();
				//PrintLog(sql+"");
				stmt = dbConn.createStatement();
				stmt.executeUpdate(sql.toString());
				PrintLog("Created "+tableName);
				rset.close();
				stmt.close();
				//PrintLog("Init SQL : '"+initSql+"'");
				if (initSql.equals(" ") || initSql.equals("")) {
				} else {
					sql.setLength(0);
					inRec = "";
					sql.setLength(0);
					inRead = new BufferedReader(new FileReader(initSql));
					while ((inRec = inRead.readLine()) != null) {
						if (inRec.indexOf(");")>0) {
							inRec	= inRec.substring(0,inRec.indexOf(");")+1);								
						}
						if (inRec.length() < 10) {
							//
						} else {
							sql.append(inRec + '\n');
							PrintLog(sql+"");
							stmt = dbConn.createStatement();
							stmt.executeUpdate(sql.toString());
							//dbConn.commit();
							rset.close();
							stmt.close();
							sql.setLength(0);
						}
					}
					inRead.close();
					// PrintLog("Populated "+tableName);
					rset.close();
					stmt.close();
				}
			}
		}
	}
	//
	public void ExtractData(Connection dbConn, DbSelector dbSelector)
			throws Exception {
		moduleName			= "ExtractData: ";
		EcontServletContextListener escl	= new EcontServletContextListener();
		String appCtx		= escl.getCtx();
		String dbUsed		= escl.getDbUsed().toUpperCase();
		PrintLog("DbUsed: "+dbUsed);
		DbDetail[] dbRows	= null;
		SysadDbUtil dbU		= new SysadDbUtil();
		Statement stmt		= dbConn.createStatement();
		String tableName	= "";
		String dbAppl		= "";
		String temp			= "";
		String colVal		= "";
		String colTypeName	= "";
		String outRecOracle	= "";
		String outRecMySQL	= "";
		FileWriter writeArecOracle;
		FileWriter writeArecMySQL;
		try {
			dbSelector.setDbAppl("ALL");
			dbSelector.setDbTable("ALL");
			dbSelector.setDbSql("");
			dbU.GetTableRows(dbConn, dbSelector, "");
			dbRows = dbSelector.getDbRows();
			for (int j = 0; j < dbRows.length; j++) {
				tableName	= dbRows[j].getDbTable().toLowerCase();
				dbAppl = dbRows[j].getDbAppl().toLowerCase();
				writeArecOracle	= new FileWriter(appCtx + "WEB-INF/db/oracle/init/"+ dbAppl+ "/" + 
											tableName + "_init.sql");
				writeArecMySQL	= new FileWriter(appCtx + "WEB-INF/db/mysql/init/"+ dbAppl+ "/" + 
											tableName + "_init.sql");
				PrintLog(appCtx + "WEB-INF/db/oracle/init/"+ dbAppl+ "/" + tableName + "_init.sql");
				PrintLog(appCtx + "WEB-INF/db/mysql/init/"+ dbAppl+ "/" + tableName + "_init.sql");
				temp					= ("SELECT * from " + tableName);
				ResultSet rset			= stmt.executeQuery(temp.toString());
				ResultSetMetaData md	= rset.getMetaData();
				int colCount			= md.getColumnCount();
				// PrintLog("Number of columns: "+colCount);
				while (rset.next()) {
					outRecOracle= ("INSERT INTO " + tableName + " values (");
					outRecMySQL	= ("INSERT INTO " + tableName + " values (");
					// " values (");
					for (int i = 1; i <= colCount; i++) {
						//PrintLog(md.getColumnTypeName(i)+"\t\t\t"+md.getColumnName(i));
						colTypeName	= md.getColumnTypeName(i);
						colVal = rset.getString(i);
						//PrintLog("Col Val " + colVal);
						if (colTypeName.equals("TIMESTAMP")) {
							//if (dbUsed.equals("ORACLE")) {
								colVal	= "SYSDATE";
							//} else {
							//	colVal	= "NULL";
							//}
						} else {
							if (colVal == null) {
								colVal = " ";
							}
							if (colVal.equals("null")) {
								colVal = " ";
							}
							if (!colVal.equals(" ")) {
								colVal	= colVal.trim().replaceAll("'","''");
							}
						}
						if (i < colCount) {
							outRecOracle += ("'" + colVal + "', ");
							outRecMySQL += ("'" + colVal + "', ");
							// PrintLog("'"+colVal+"', ");
						} else {
							outRecOracle += ("'" + colVal + "');\n");
							outRecMySQL += ("'" + colVal + "');\n");
							// PrintLog("'"+colVal+"');");
						}
					}
					outRecOracle= outRecOracle.replaceAll("'SYSDATE'","SYSDATE");
					writeArecOracle.write(outRecOracle);
					outRecMySQL	= outRecOracle.replaceAll("SYSDATE","NULL");
					writeArecMySQL.write(outRecMySQL);
					// PrintLog("");
				}
				writeArecOracle.close();
				writeArecMySQL.close();
			}
			PrintLog("Extract data successfully completed");
		} catch (Throwable t) {
			PrintLog("Ended " + t);
			t.printStackTrace(System.out);
		}
	}
}
