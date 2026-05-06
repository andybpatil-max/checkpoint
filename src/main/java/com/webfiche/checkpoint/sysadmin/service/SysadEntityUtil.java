package com.webfiche.checkpoint.sysadmin.service;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.*;

/**
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
@Service
public class SysadEntityUtil {
	private String		 className  = "> SysadEntityUtil.";
	private String		 moduleName;
	// private String calledFrom;
	ArrayList<String>	  paramArray;
	ArrayList<EntityDetail> entityBeans = new ArrayList<EntityDetail>();
	String		param		= "";
	int			row_count	= 0;

	public SysadEntityUtil() {
	}
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							className + moduleName + logMsg);
	}
	//
	public ArrayList<String> getEntityList(Connection dbConn)
			throws Exception {
		moduleName			= "getEntityList: ";
		String tempEntity	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> entityList	= new ArrayList<String>();
		entityList.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT ENTITY_ID from entity" + " ORDER by ENTITY_ID");
		//PrintLog("Execute " + sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			tempEntity	= result.getString("ENTITY_ID");
			//PrintLog("tempEntity: " + tempEntity);
			entityList.add(tempEntity);
		}
		statement.close();
		result.close();
		return (entityList);
	}
	//
	public TreeMap <String,String> getEntityMap (Connection dbConn)
			throws Exception {
		moduleName			= "getEntityMap: ";
		String tempKey		= "";
		String tempValue	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> entityMap	= new TreeMap <String, String>();
		entityMap.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT ENTITY_ID, ENTITY_NAME, ENTITY_TYPE from entity" +
					" ORDER by ENTITY_ID");
		//PrintLog("Execute " + sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			tempKey		= result.getString("ENTITY_ID");
			tempValue	= result.getString("ENTITY_NAME");
			tempValue	+= " " + result.getString("ENTITY_TYPE");
			//PrintLog("Key: " + tempKey + " Value: " + tempValue);
			entityMap.put(tempKey, tempValue);
		}
		statement.close();
		result.close();
		return (entityMap);
	}
	//
	public ArrayList<String> getEntityLogList(Connection dbConn)
			throws Exception {
		moduleName			= "getEntityLogList: ";
		String tempEntity	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> entityList = new ArrayList<String>();
		entityList.clear();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ENTITY_ID from entitylog "
				+ "ORDER by ENTITY_ID");
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			//PrintLog("Execute In While");
			tempEntity = result.getString("ENTITY_ID");
			entityList.add(tempEntity);
		}
		statement.close();
		result.close();
		return (entityList);
	}
	//
	public boolean EntityExists(Connection dbConn, String entity) throws Exception {
		moduleName			= "EntityExists: ";
		StringBuffer sql	= new StringBuffer();
		// String temp = "";
		sql.append("SELECT ENTITY_ID from entity ");
		sql.append("WHERE ENTITY_ID ='" + entity + "' ");
		// PrintLog("Entity Exist SQL "+sql);
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			// temp = result.getString("ENTITY_ID");
			statement.close();
			result.close();
			//PrintLog("ENTITY Exists returning true ");
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	public int GetEntityRows(Connection dbConn, EntitySelector entitySelector,
			String entity) throws Exception {
		// Called by the modify module with an account number
		// --------------------------------------------------
		moduleName = "GetEntityRows: ";
		param = "WHERE ENTITY_ID='" + entity + "'";
		row_count = GetEntityRows(dbConn, entitySelector);
		return (row_count);
	}
	//
	public int GetEntityRows(Connection dbConn, EntitySelector entitySelector)
			throws Exception {
		moduleName		= "GetEntityRows: ";
		if (param.equals(""))
			// GetParams (entitySelector);
			param = entitySelector.GetParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT ENTITY_ID, ENTITY_NAME, ");
		sql.append("ENTITY_TYPE, ENTITY_MODTIME, ");
		sql.append("ENTITY_MODUSER, ENTITY_MODFUNC ");
		sql.append("FROM entity ");
		sql.append(param);
		PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		entityBeans.clear();
		while (result.next()) {
			EntityDetail entityDetail = new EntityDetail();
			entityDetail.setEntityId(result.getString("ENTITY_ID"));
			entityDetail.setEntityName(result.getString("ENTITY_NAME"));
			entityDetail.setEntityType(result.getString("ENTITY_TYPE"));
			entityDetail.setEntityModtime(result.getString("ENTITY_MODTIME"));
			entityDetail.setEntityModuser(result.getString("ENTITY_MODUSER"));
			entityDetail.setEntityModfunc(result.getString("ENTITY_MODFUNC"));
			entityBeans.add(entityDetail);
			row_count++;
		}
		entitySelector.setEntityRows(entityBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetEntityLogRows(Connection dbConn, EntitySelector entitySelector)
			throws Exception {
		moduleName = "GetEntityLogRows: ";
		if (param.equals(""))
			// GetLogParams (entitySelector);
			param = entitySelector.GetLogParams();
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT ENTITY_ID, ENTITY_NAME, ENTITY_TYPE, ");
		sql.append("ENTITY_MODTIME, ENTITY_MODUSER, ENTITY_MODFUNC ");
		sql.append("FROM entitylog ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog(" ------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		entityBeans.clear();
		while (result.next()) {
			EntityDetail entityDetail = new EntityDetail();
			entityDetail.setEntityId(result.getString("ENTITY_ID"));
			entityDetail.setEntityName(result.getString("ENTITY_NAME"));
			entityDetail.setEntityType(result.getString("ENTITY_TYPE"));
			entityDetail.setEntityModtime(result.getString("ENTITY_MODTIME"));
			entityDetail.setEntityModuser(result.getString("ENTITY_MODUSER"));
			entityDetail.setEntityModfunc(result.getString("ENTITY_MODFUNC"));
			entityBeans.add(entityDetail);
			row_count++;
		}
		entitySelector.setEntityRows(entityBeans);
		statement.close();
		result.close();
		return (row_count);
	}

	public int InsertUpdateEntity(Connection dbConn, EntityDetail entityDetail,
			String dbUsed, String user_name, int ins_or_upd)
	throws Exception {
		// 1 for insert or 2 for update
		moduleName = "InsertUpdateEntity: ";
		PrintLog("In Insert/Update Entity");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "";
		//
		String entityId = entityDetail.getEntityId();
		String entityName = entityDetail.getEntityName();
		String entityType = entityDetail.getEntityType();
		if (ins_or_upd == 1) {
			mod_func = "Add Entity";
			sql.setLength(0);
			sql.append("INSERT INTO entity VALUES (");
			sql.append("'" + entityId + "', ");
			sql.append("'" + entityName + "', ");
			sql.append("'" + entityType + "', ");
			// sql.append("NULL,");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			// sql.append("sysdate,");
			sql.append("'" + user_name + "', ");
			sql.append("'" + mod_func + "')");
			// PrintLog("Entity Insert SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Inserting Entity ->" + e.toString());
				PrintLog("Entity Insert SQL ---> " + sql);
			}
		} else {
			mod_func = "Modify Entity";
			sql.setLength(0);
			sql.append("UPDATE entity SET ");
			sql.append("ENTITY_ID='" + entityId + "', ");
			sql.append("ENTITY_NAME='" + entityName + "', ");
			sql.append("ENTITY_TYPE='" + entityType + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("ENTITY_MODTIME=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("ENTITY_MODTIME=sysdate, ");
			}
			sql.append("ENTITY_MODUSER='" + user_name + "', ");
			sql.append("ENTITY_MODFUNC='" + mod_func + "' ");
			sql.append("WHERE ENTITY_ID='" + entityId + "' ");
			// PrintLog("Entity Update SQL ---> "+sql);
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Error Updating Entity ->" + e.toString());
				PrintLog("Entity Update SQL ---> " + sql);
			}
		}
		statement.close();
		sql.setLength(0);
		sql.append("INSERT INTO entitylog VALUES (");
		sql.append("'" + entityId + "', ");
		sql.append("'" + entityName + "', ");
		sql.append("'" + entityType + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		// PrintLog("Entity Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Entity Log ->" + e.toString());
			PrintLog("Entity Log Insert SQL ---> " + sql);
		}
		statement.close();
		return (1);
	}
	public int DeleteEntity(Connection dbConn, EntityDetail entityDetail,
			String dbUsed, String user_name) // ,
			// int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "DeleteEntity: ";
		// PrintLog("In Insert/Update Entity");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		String mod_func = "Delete entity";
		//
		String entityId = entityDetail.getEntityId();

		sql.setLength(0);
		sql.append("INSERT INTO entitylog ");
		sql.append("SELECT ENTITY_ID, ENTITY_NAME, ENTITY_TYPE, ");
		// sql.append("NULL, ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("sysdate, ");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM entity ");
		sql.append("WHERE ENTITY_ID='" + entityId + "' ");
		// PrintLog("Entity Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error inserting Entity Log ->" + e.toString());
			PrintLog("Entity Log Insert SQL ---> " + sql);
		}
		statement.close();
		sql.setLength(0);
		sql.append("DELETE FROM entity ");
		sql.append("WHERE ENTITY_ID='" + entityId + "' ");
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Error Deleting Entity-> " + e.toString());
			PrintLog("Entity Delete SQL ---> " + sql);
		}
		statement.close();
		return (1);
	}
}
