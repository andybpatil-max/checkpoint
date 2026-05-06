package com.webfiche.checkpoint.sysadmin.service;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Service;
import com.webfiche.checkpoint.sysadmin.beans.*;

/**
 * Copyright (c) 2003 eSoftLabs, LLC All rights reserved.
 *
 * <strong>SysadProdUtil</strong> is a utility class to provide methods to
 * access .and manipulate the products and groups database tables.
 *
 * @author <a href="mailto:adnybpatil@gmail.com">Andy Patil</a>
 */
@Service
public class SysadProdUtil {
	private String		   moduleName;
	// String prod_id_sel;
	// String prod_menu_id_sel;
	// String prod_menu_func_id_sel;
	ArrayList<String>		paramArray;
	ArrayList<ProductDetail> prodBeans	= new ArrayList<ProductDetail>();
	ArrayList<GroupDetail>   groupBeans	= new ArrayList<GroupDetail>();
	String	param		= "";
	int		row_count	= 0;

	public SysadProdUtil() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> SysadProdUtil." + moduleName + logMsg);
	}
	// ----------------------------------------------------------------
	// Product Methods
	// ----------------------------------------------------------------
	// The following Methods are specialised for each of the incl tables.
	// The first is a set of methods for specific purposes;
	// 1. Get the accounts number columns of every row in the incl_accounts table
	// for the Accounts Inquiry/Maintenance selection table.
	//
	public ArrayList<String> GetProductsList(Connection dbConn)
			throws Exception {
		moduleName		= "GetProductsList: ";
		String temp		= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> prod_list	= new ArrayList<String>();
		prod_list.clear();
		StringBuffer sql			= new StringBuffer();
		sql.append("SELECT DISTINCT PRODUCT_ID from products ORDER by PRODUCT_ID");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp	= result.getString("PRODUCT_ID");
			prod_list.add(temp);
		}
		statement.close();
		result.close();
		return (prod_list);
	}
	//
	public TreeMap<String, String> GetProductsNameList(Connection dbConn)
		throws Exception {
		moduleName		= "GetProductsNameList: ";
		String p_id		= "";
		String p_desc	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> prod_names	= new TreeMap<String, String>();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT PRODUCT_ID, PRODUCT_PMF_DESC from products ");
		sql.append("WHERE PRODUCT_MENU_ID=' ' and PRODUCT_FUNC_ID=' ' ");
		sql.append("ORDER by PRODUCT_ID ");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			p_id	= result.getString("PRODUCT_ID");
			p_desc	= result.getString("PRODUCT_PMF_DESC");
			prod_names.put(p_id, p_desc);
			// PrintLog(":Product Id :"+p_id+" Product Desc :"+p_desc);
		}
		statement.close();
		result.close();
		return (prod_names);
	}
	//
	public TreeMap<String, String> GetLProductsNameList(
			Connection dbConn) throws Exception {
		moduleName		= "GetLProductsNameList: ";
		String p_id		= "";
		String p_desc	= "";
		// String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, String> prod_names = new TreeMap<String, String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT PRODUCT_ID, PRODUCT_PNEMONIC, PRODUCT_PMF_DESC from products ");
		sql.append("WHERE PRODUCT_MENU_ID=' ' and PRODUCT_FUNC_ID=' ' ");
		sql.append("AND SUBSTR(PRODUCT_PNEMONIC,5,1)='Y' ORDER by PRODUCT_ID ");
		/*
		 * if (dbUsed.equals("ORACLE")) {
		 * sql.append("AND SUBSTR(PRODUCT_PNEMONIC,5,1)='Y' ORDER by PRODUCT_ID "
		 * ); } else {
		 * sql.append("AND SUBSTRING(PRODUCT_PNEMONIC,5,1)='Y' ORDER by PRODUCT_ID "
		 * ); }
		 */
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			p_id	= result.getString("PRODUCT_ID");
			p_desc	= result.getString("PRODUCT_PMF_DESC");
			// temp	= result.getString("PRODUCT_PNEMONIC");
			// PrintLog(">"+temp+"<>"+temp.substring(4,5)+"<");
			prod_names.put(p_id, p_desc);
			// PrintLog("Product Id :"+p_id+" Product Desc :"+p_desc);
		}
		statement.close();
		result.close();
		return (prod_names);
	}
	//
	public ArrayList<String> GetProductsLogList(Connection dbConn)
			throws Exception {
		moduleName	= "GetProductsLogList: ";
		String temp	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> prolog_list	= new ArrayList<String>();
		prolog_list.clear();
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT DISTINCT PROLOG_ID from prodiucts_log ORDER by PROLOG_ID");
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			temp	= result.getString("PROLOG_ID");
			prolog_list.add(temp);
		}
		statement.close();
		result.close();
		return (prolog_list);
	}
	//
	public boolean ProductExists(Connection dbConn, String product_id,
			String product_menu_id, String product_menu_func_id)
			throws Exception {
		// Called by the Add module with the product key
		// can be used to check the existemce of P, M or F
		// -----------------------------------------------
		moduleName = "ProductExists: ";
		//String temp_1 = "";
		//String temp_2 = "";
		//String temp_3 = "";
		param = "WHERE PRODUCT_ID='" + product_id + "' ";
		param += " AND PRODUCT_MENU_ID='" + product_menu_id + "'";
		param += " AND PRODUCT_FUNC_ID='" + product_menu_func_id + "'";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PRODUCT_ID, PRODUCT_MENU_ID, PRODUCT_FUNC_ID from products ");
		sql.append(param);
		// PrintLog("Product Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			//temp_1 = result.getString("PRODUCT_ID");
			//temp_2 = result.getString("PRODUCT_MENU_ID");
			//temp_3 = result.getString("PRODUCT_FUNC_ID");
			//PrintLog("Found product for " + temp_1 + temp_2 + temp_3);
			return (true);
		}
		statement.close();
		result.close();
		return (false);
	}
	//
	// Called by Modify -- needs lock
	//
	public int GetProductRows(Connection dbConn, ProductSelector prodSelector,
			String prod_id, String prod_menu_id, String prod_menu_func_id)
			throws Exception {
		moduleName = "GetProductRows: ";
		String accessFlag	= prodSelector.getAccessFlag();
		String dbUsed		= prodSelector.getDbUsed();
		if (prod_menu_id.equals("")) {
			prod_menu_id	= " ";
		}
		if (prod_menu_func_id.equals("")) {
			prod_menu_func_id	= " ";
		}
		param	 = "WHERE PRODUCT_ID='" + prod_id + "' ";
		param	+= "AND PRODUCT_MENU_ID='" + prod_menu_id + "' ";
		param	+= "AND PRODUCT_FUNC_ID='" + prod_menu_func_id + "' ";
		if (dbUsed.equals("ORACLE")) {
			if (accessFlag != null) {
				if (accessFlag.equals("inq")) {
					;
				} else {
					param += " for UPDATE nowait ";
					dbConn.setAutoCommit(false);
				}
			} else {
				param += " for UPDATE nowait ";
				dbConn.setAutoCommit(false);
			}
		}
		row_count = GetProductRows(dbConn, prodSelector);
		return (row_count);
	}
	//
	public int GetProductRows(Connection dbConn, ProductSelector prodSelector)
			throws Exception {
		moduleName = "GetProductRows: ";
		// PrintLog("Prod_Plist[0]: "+prod_plist.get(0));
		if (param.equals(""))
			// GetParams (prodSelector);
			param = prodSelector.GetParams();
		String nodeName = prodSelector.getNodeName();
		//PrintLog("Node Name: " + nodeName);
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT PRODUCT_ID, PRODUCT_MENU_ID, PRODUCT_FUNC_ID, ");
		sql.append("PRODUCT_PNEMONIC, PRODUCT_PMF_DESC, PRODUCT_PMF_LINK, ");
		sql.append("PRODUCT_LAST_MODIFIED, ");
		sql.append("PRODUCT_MOD_USER, PRODUCT_MOD_FUNC ");
		sql.append("FROM products ");
		sql.append(param);
		//PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			ProductDetail prodDetail = new ProductDetail();
			// Here add the fields tp the ProductDetail bean
			prodDetail.setProduct_id(result.getString("PRODUCT_ID"));
			if (result.getString("PRODUCT_MENU_ID").equals(" ")) {
				prodDetail.setProduct_menu_id("");
			} else {
				prodDetail.setProduct_menu_id(result
						.getString("PRODUCT_MENU_ID"));
			}
			if (result.getString("PRODUCT_FUNC_ID").equals(" ")) {
				prodDetail.setProduct_menu_func_id("");
			} else {
				prodDetail.setProduct_menu_func_id(result
						.getString("PRODUCT_FUNC_ID"));
			}
			prodDetail.setProduct_pnemonic(result.getString("PRODUCT_PNEMONIC"));
			prodDetail.setProduct_pmf_desc(result.getString("PRODUCT_PMF_DESC"));
			prodDetail.setProduct_pmf_link(result.getString("PRODUCT_PMF_LINK"));
			prodDetail.setProduct_last_modified(result.getString("PRODUCT_LAST_MODIFIED"));
			prodDetail.setProduct_mod_user(result.getString("PRODUCT_MOD_USER"));
			prodDetail.setProduct_mod_func(result.getString("PRODUCT_MOD_FUNC"));
			prodDetail.setNodeName(nodeName);
			//prodDetail.setProduct_modparam();
			prodDetail.setProduct_pmf_entitled("   ");
			prodDetail.setProduct_make_copy();
			// PrintLog("Prod_id: "+result.getString("PRODUCT_ID")+
			// result.getString("PRODUCT_MENU_ID") +
			// result.getString("PRODUCT_FUNC_ID") +
			// " "+ result.getString("PRODUCT_PMF_DESC"));
			// prodSelector.setProdKey(row_count, prodDetail.getProduct_key());
			// prodSelector.setProdType(row_count,
			// prodDetail.getProduct_type());
			// prodSelector.setPmfDesc(row_count,
			// prodDetail.getProduct_pmf_desc());
			prodBeans.add(prodDetail);
			row_count++;
		}
		prodSelector.setProductrows(prodBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetProductLogRows(Connection dbConn, ProductSelector prodSelector)
			throws Exception {
		moduleName = "GetProductLogRows: ";
		if (param.equals("")) {
			// GetLogParams (prodSelector);
			param = prodSelector.GetLogParams();
		}
		StringBuffer sql = new StringBuffer();
		row_count = 0;
		sql.append("SELECT PROLOG_ID, PROLOG_MENU_ID, PROLOG_FUNC_ID, ");
		sql.append("PRLOG_PNEMONIC, PROLOG_PMF_DESC, PROLOG_PMF_LINK, ");
		sql.append("PROLOG_LAST_MODIFIED, ");
		sql.append("PROLOG_MOD_USER, PROLOG_MOD_FUNC ");
		sql.append("FROM products ");
		sql.append(param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			ProductDetail prodDetail = new ProductDetail();
			// Here add the fields tp the ProductDetail bean
			prodDetail.setProduct_id(result.getString("PROLOG_ID"));
			prodDetail.setProduct_menu_id(result.getString("PROLOG_MENU_ID"));
			prodDetail.setProduct_menu_func_id(result.getString("PROLOG_FUNC_ID"));
			prodDetail.setProduct_menu_func_id(result.getString("PROLOG_PNEMONIC"));
			prodDetail.setProduct_pmf_desc(result.getString("PROLOG_PMF_DESC"));
			prodDetail.setProduct_pmf_link(result.getString("PROLOG_PMF_LINK"));
			prodDetail.setProduct_last_modified(result.getString("PROLOG_LAST_MODIFIED"));
			prodDetail.setProduct_mod_user(result.getString("PROLOG_MOD_USER"));
			prodDetail.setProduct_mod_func(result.getString("PROLOG_MOD_FUNC"));
			//prodDetail.setProduct_modparam();
			prodDetail.setProduct_pmf_entitled("   ");
			prodBeans.add(prodDetail);
			row_count++;
		}
		prodSelector.setProductrows(prodBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int InsertUpdateProduct(Connection dbConn, ProductDetail prodDetail,
			String dbUsed, String user_name, int ins_or_upd) // 1 for insert or
															 // 2 for update
			throws Exception {
		moduleName = "InsertUpdateProduct: ";
		// PrintLog("In Insert/Update Acct");
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// int result = 0;
		// int ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		String mod_func = "";
		dbConn.setAutoCommit(false);
		String userName = user_name.trim();
		//
		String product_id = prodDetail.getProduct_id();
		String product_menu_id = prodDetail.getProduct_menu_id();
		if (product_menu_id.equals("")) {
			product_menu_id = " ";
		}
		String product_menu_func_id = prodDetail.getProduct_menu_func_id();
		if (product_menu_func_id.equals("")) {
			product_menu_func_id = " ";
		}
		String product_pnemonic = prodDetail.getProduct_pnemonic();
		String product_pmf_desc = prodDetail.getProduct_pmf_desc();
		String product_pmf_link = prodDetail.getProduct_pmf_link();
		// String product_last_modified = prodDetail.getProduct_last_modified();
		// String product_mod_user = prodDetail.getProduct_mod_user();
		// String product_mod_func = prodDetail.getProduct_mod_func();
		if (ins_or_upd == 1) {
			mod_func = "Add Prod";
			sql.setLength(0);
			sql.append("INSERT INTO products VALUES (");
			sql.append("'" + product_id + "', ");
			sql.append("'" + product_menu_id + "', ");
			sql.append("'" + product_menu_func_id + "', ");
			sql.append("'" + product_pnemonic + "', ");
			sql.append("'" + product_pmf_desc + "', ");
			sql.append("'" + product_pmf_link + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + userName + "', ");
			sql.append("'" + mod_func + "')");
			//
			try {
				statement = dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Product Insert SQL ---> " + sql);
				PrintLog("Error Inserting Product ->" + e.toString());
			}
		} else {
			mod_func = "Modify Prod";
			sql.setLength(0);
			sql.append("UPDATE products SET ");
			sql.append("PRODUCT_PNEMONIC='" + product_pnemonic + "', ");
			sql.append("PRODUCT_PMF_DESC='" + product_pmf_desc + "', ");
			sql.append("PRODUCT_PMF_LINK='" + product_pmf_link + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("PRODUCT_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("PRODUCT_LAST_MODIFIED=sysdate, ");
			}
			sql.append("PRODUCT_MOD_USER='" + userName + "', ");
			sql.append("PRODUCT_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE PRODUCT_ID='" + product_id + "' ");
			sql.append("AND PRODUCT_MENU_ID='" + product_menu_id + "'");
			sql.append("AND PRODUCT_FUNC_ID='" + product_menu_func_id + "'");
			//
			try {
				statement = dbConn.createStatement();
				PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Product Update SQL ---> " + sql);
				PrintLog("Error Updating Product ->" + e.toString());
			}
		}
		statement.close();
		sql.setLength(0);
		sql.append("INSERT INTO products_log VALUES (");
		sql.append("'" + product_id + "', ");
		sql.append("'" + product_menu_id + "', ");
		sql.append("'" + product_menu_func_id + "', ");
		sql.append("'" + product_pnemonic + "', ");
		sql.append("'" + product_pmf_desc + "', ");
		sql.append("'" + product_pmf_link + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userName + "', ");
		sql.append("'" + mod_func + "')");
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Log Insert SQL ---> " + sql);
			PrintLog("Error inserting Product Log ->" + e.toString());
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
	//
	public int DeleteProduct(Connection dbConn, ProductDetail prodDetail,
			String dbUsed, String user_name) // ,
			// int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName = "DeleteProduct: ";
		String userName = user_name.trim();
		// Deleting a product must be clearly understood because of the
		// Heirarchical organisation of Product with Menus and Functions
		// within Menus.
		// If a product is deleted all the Menus and Functions are deleted
		// If a Menu is deleted then the Menu and its Functions are deleted
		// If a function is deleted only that function is deleted.
		//
		// Since the rows will be deleted in this Method we have to first
		// log them and then effect the delete. We will therefore log
		// what we will be deleting.
		//
		StringBuffer sql = new StringBuffer();
		Statement statement = null;
		// int result = 0;
		// int ret_val = 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		String mod_func = "Delete prod";
		//
		String product_id = prodDetail.getProduct_id();
		String product_menu_id = prodDetail.getProduct_menu_id();
		String product_menu_func_id = prodDetail.getProduct_menu_func_id();

		dbConn.setAutoCommit(false);
		sql.setLength(0);
		sql.append("INSERT INTO products_log ");
		sql.append("SELECT PRODUCT_ID, ");
		sql.append("PRODUCT_MENU_ID, ");
		sql.append("PRODUCT_FUNC_ID, ");
		sql.append("PRODUCT_PNEMONIC, ");
		sql.append("PRODUCT_PMF_DESC, ");
		sql.append("PRODUCT_PMF_LINK,");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userName + "', ");
		sql.append("'" + mod_func + "' ");
		sql.append("FROM products ");
		sql.append("WHERE PRODUCT_ID='" + product_id + "' ");
		if (!product_menu_id.equals("")) {
			sql.append("AND PRODUCT_MENU_ID='" + product_menu_id + "' ");
		}
		if (!product_menu_func_id.equals("")) {
			sql.append("AND PRODUCT_FUNC_ID='" + product_menu_func_id + "'");
		}
		// PrintLog("Log Insert SQL ---> "+sql);
		//
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Log Insert SQL ---> " + sql);
			PrintLog("Error inserting Prod Log ->" + e.toString());
		}
		statement.close();
		sql.setLength(0);
		sql.append("DELETE from products ");
		sql.append("WHERE PRODUCT_ID='" + product_id + "' ");
		if (!product_menu_id.equals("")) {
			sql.append("AND PRODUCT_MENU_ID='" + product_menu_id + "' ");
		}
		if (!product_menu_func_id.equals("")) {
			sql.append("AND PRODUCT_FUNC_ID='" + product_menu_func_id + "'");
		}
		// PrintLog("Log Insert SQL ---> "+sql);
		try {
			statement = dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Log Insert SQL ---> " + sql);
			PrintLog("Error Deleting Log ->" + e.toString());
			return (0);
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
	// ------------------------------------------------------------------------------
	// Group methods
	// ------------------------------------------------------------------------------
	public TreeMap<String, HashMap<String, ArrayList<String>>> GetLProdGroupsList (
			Connection dbConn, String dbUsed) throws Exception {
		moduleName					= "GetLProdGroupsList: ";
		String p_id					= "";
		String p_id_o				= "";
		String p_desc				= "";
		String p_desc_o				= "";
		String p_grp				= "";
		ArrayList<String> grp_arr	= new ArrayList<String>();
		HashMap<String, ArrayList<String>> grp_hash	= new HashMap<String, ArrayList<String>>();
		// String temp = "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		TreeMap<String, HashMap<String, ArrayList<String>>> prod_groups	= new TreeMap<String, HashMap<String, ArrayList<String>>>();
		StringBuffer sql			= new StringBuffer();
		//
		sql.append("SELECT PRODUCT_ID, PRODUCT_PNEMONIC, PRODUCT_PMF_DESC, GROUP_ID ");
		sql.append("FROM products, user_groups WHERE (PRODUCT_ID=GROUP_PROD_CODE AND ");
		sql.append("PRODUCT_MENU_ID=' ' AND PRODUCT_FUNC_ID=' ' AND ");
		if (dbUsed.equals("MySQL")) {
			sql.append("SUBSTRING(PRODUCT_PNEMONIC,5,1)='Y') ORDER by PRODUCT_ID");
		} else {
			sql.append("SUBSTR(PRODUCT_PNEMONIC,5,1)='Y') ORDER by PRODUCT_ID");
		}
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			p_id	= result.getString("PRODUCT_ID");
			p_desc	= result.getString("PRODUCT_PMF_DESC");
			p_grp	= result.getString("GROUP_ID");
			if (p_id_o.equals("")) {
				p_id_o		= p_id;
				p_desc_o	= p_desc;
			}
			if (!p_id.equals(p_id_o)) {
				grp_hash.put(p_desc_o, grp_arr);
				prod_groups.put(p_id_o, grp_hash);
				grp_hash	= new HashMap<String, ArrayList<String>>();
				grp_arr		= new ArrayList<String>();
				p_id_o		= p_id;
				p_desc_o	= p_desc;
			}
			grp_arr.add(p_grp);
			// PrintLog(">"+temp+"<>"+temp.substring(4,5)+"<");
			//PrintLog("Product Id :" + p_id + " Product Desc :" + p_desc + " Group :" + p_grp);
		}
		grp_hash.put(p_desc_o, grp_arr);
		prod_groups.put(p_id_o, grp_hash);
		statement.close();
		result.close();
		return (prod_groups);
	}
	//
	public ArrayList<String> GetGroupList(Connection dbConn) throws Exception {
		moduleName				= "GetGroupList: ";
		String temp_prod_code	= "";
		String temp_group_id	= "";
		if (dbConn == null)
			PrintLog("dbConn is Null");
		ArrayList<String> group_list	= new ArrayList<String>();
		group_list.clear();
		StringBuffer sql		= new StringBuffer();
		sql.append("SELECT GROUP_PROD_CODE, " + 
					"GROUP_ID from user_groups " +
					"ORDER BY GROUP_PROD_CODE, GROUP_ID");
		// Setup the SELECT statement.
		// PrintLog("Execute "+sql);
		Statement statement	= dbConn.createStatement();
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			// PrintLog("Execute In While");
			temp_prod_code	= result.getString("GROUP_PROD_CODE");
			temp_group_id	= result.getString("GROUP_ID");
			group_list.add(temp_prod_code + temp_group_id);
			// PrintLog("Prod: "+temp_prod_code+
			// " Pnem: "+temp_prod_pnem+"GR id: "+temp_group_id);
		}
		statement.close();
		result.close();
		return (group_list);
	}
	//
	public int GetGroupRows(Connection dbConn, String prod_id,
			GroupSelector groupSelector) throws Exception {
		moduleName			= "GetGroupRows: ";
		StringBuffer sql	= new StringBuffer();
		row_count			= 0;
		sql.append("SELECT GROUP_PROD_CODE, " +
					"GROUP_ID, GROUP_ENTITLEMENTS FROM user_groups WHERE " +
					"GROUP_PROD_CODE='" + prod_id + "' order by GROUP_ID");
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			GroupDetail groupDetail	= new GroupDetail();
			// PrintLog("In While Get");
			groupDetail.setGdProd(result.getString("GROUP_PROD_CODE"));
			groupDetail.setGdGroup_id(result.getString("GROUP_ID"));
			groupDetail.setGd_modparam();
			groupBeans.add(groupDetail);
			row_count++;
		}
		//groupSelector.setGroupList(groupList);
		groupSelector.setGrouprows(groupBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	public int GetGroupLogRows(Connection dbConn, String prod_id,
			GroupSelector groupSelector) throws Exception {
		moduleName			= "GetGroupRows: ";
		StringBuffer sql	= new StringBuffer();
		row_count			= 0;
		sql.append("SELECT DISTINCT UGLOG_PROD_CODE, " +
					"UGLOG_ID FROM user_groups_log WHERE " + "UGLOG_PROD_CODE='" +
					prod_id + "' order by UGLOG_ID");
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		// int idx = 0;
		while (result.next()) {
			GroupDetail groupDetail	= new GroupDetail();
			// PrintLog("In While Get");
			groupDetail.setGdProd(result.getString("UGLOG_PROD_CODE"));
			groupDetail.setGdGroup_id(result.getString("UGLOG_ID"));
			groupDetail.setGd_modparam();
			groupBeans.add(groupDetail);
			row_count++;
		}
		groupSelector.setGrouprows(groupBeans);
		statement.close();
		result.close();
		return (row_count);
	}
	//
	// Called by Mod/view/Delete group
	//
	public void GetAGroupLogRows(Connection dbConn, String group_id,
			GroupSelector groupSelector) throws Exception {
		moduleName	= "GetAGroupRow: ";
		// PrintLog("Group_id : >"+group_id+"<");
		String temp_prod_code	= "";
		String temp_group_id	= "";
		String temp_group_en	= "";
		// String temp_param	= "";
		String[] nents			= { "", "", "", "", "", "", "", "", "", "", "", "", "",
									"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
									"", "", "", "", "", "", "", "", "", "", "" };
		int j, k			= 0;
		StringBuffer sql	= new StringBuffer();
		row_count			= 0;
		// PrintLog("group_id");
		sql.append("SELECT UGLOG_PROD_CODE, " +
					"UGLOG_ID, UGLOG_ENTITLEMENTS FROM user_groups_log WHERE " +
					"UGLOG_PROD_CODE='" + group_id.substring(0, 1) + "' and " +
					"UGLOG_ID='" + group_id.substring(1).trim() + "'");
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			GroupDetail groupDetail	= new GroupDetail();
			// PrintLog("In While Get");
			temp_prod_code			= result.getString("UGLOG_PROD_CODE");
			temp_group_id			= result.getString("UGLOG_ID");
			temp_group_en			= result.getString("UGLOG_ENTITLEMENTS");
			// PrintLog("Entitlements: "+temp_group_en);
			for (j = 0, k = 0; j < temp_group_en.length(); j++, k++) {
				nents[k]	= temp_prod_code.substring(0, 1) + temp_group_en.substring(j, j + 2);
				j++;
				// PrintLog("New Ents: "+nents[k]);
			}
			groupDetail.setGroup_pmf_entitled(nents);
			groupDetail.setGdProd(temp_prod_code);
			groupDetail.setGdGroup_id(temp_group_id);
			groupBeans.add(groupDetail);
		}
		groupSelector.setGrouprows(groupBeans);
		groupSelector.setG_prod_group(temp_prod_code);
		groupSelector.setG_group(temp_group_id);
		statement.close();
		result.close();
	}
	//
	// Called by Mod/view/Delete group
	//
	public ArrayList<String> GetAGroupRow(Connection dbConn, String group_id,
			GroupSelector groupSelector) throws Exception {
		moduleName					= "GetAGroupRow: ";
		// PrintLog("Group_id : >"+group_id+"<");
		ArrayList<String> groupRow	= new ArrayList<String>();
		String temp_prod_code		= "";
		String temp_group_id		= "";
		String temp_group_en		= "";
		String temp_param			= "";
		StringBuffer sql			= new StringBuffer();
		row_count					= 0;
		// sql.append("SELECT GROUP_PROD_CODE, GROUP_PROD_PNEM, "+
		sql.append("SELECT GROUP_PROD_CODE, " +
				   "GROUP_ID, GROUP_ENTITLEMENTS FROM user_groups WHERE " +
				   "GROUP_PROD_CODE='" + group_id.substring(0, 1) + "' and " +
				   "GROUP_ID='" + group_id.substring(1).trim() + "'");
		String accessFlag	= groupSelector.getAccessFlag();
		String dbUsed		= groupSelector.getDbUsed();
		// PrintLog("DB USed : "+dbUsed+" accessFlag: >"+accessFlag+"<");
		if (dbUsed.equals("ORACLE")) {
			if (accessFlag != null) {
				if (accessFlag.equals("inq")) {
					//;
				} else {
					temp_param	= " for UPDATE nowait ";
					dbConn.setAutoCommit(false);
				}
			} else {
				temp_param	= " for UPDATE nowait ";
				dbConn.setAutoCommit(false);
			}
		}
		sql.append(temp_param);
		// PrintLog("SQL: "+sql);
		if (dbConn == null)
			PrintLog("------------------ NULL DBCONN -----------------");
		// Prepare the SELECT statement.*/
		Statement statement	= dbConn.createStatement();
		// PrintLog("Executing result set");
		ResultSet result	= statement.executeQuery(sql.toString());
		while (result.next()) {
			GroupDetail groupDetail	= new GroupDetail();
			temp_prod_code			= result.getString("GROUP_PROD_CODE");
			temp_group_id			= result.getString("GROUP_ID");
			temp_group_en			= result.getString("GROUP_ENTITLEMENTS");
			groupRow.add(temp_prod_code);
			groupRow.add(temp_group_id);
			groupRow.add(temp_group_en);
			groupDetail.setGdProd(temp_prod_code);
			groupDetail.setGdGroup_id(temp_group_id);
			groupBeans.add(groupDetail);
		}
		groupSelector.setGrouprows(groupBeans);
		groupSelector.setG_prod_group(temp_prod_code);
		groupSelector.setG_group(temp_group_id);
		statement.close();
		result.close();
		return (groupRow);
	}
	//
	public String GroupExists(Connection dbConn, String product_id,
								String group_id, String entitlements) throws Exception {
		// Called by the Add module with the product key
		// can be used to check the existemce of P, M or F
		// -----------------------------------------------
		boolean gr_exists	= false;
		moduleName			= "GroupExists: ";
		// String temp_1	= "";
		String temp_2		= "";
		// String temp_3	= "";
		param	= "WHERE GROUP_PROD_CODE='" + product_id + "' " +
					"AND GROUP_ID='" + group_id + "'";
		StringBuffer sql	= new StringBuffer();
		sql.append("SELECT GROUP_PROD_CODE, " +
					"GROUP_ID, GROUP_ENTITLEMENTS FROM user_groups ");
		sql.append(param);
		// PrintLog("1. Group Exists SQL "+sql);
		// Setup the SELECT statement.
		Statement statement = dbConn.createStatement();
		ResultSet result = statement.executeQuery(sql.toString());
		while (result.next()) {
			temp_2		= result.getString("GROUP_ID");
			gr_exists	= true;
		}
		if (gr_exists == true) {
			gr_exists	= false;
			sql.setLength(0);
			param = ("WHERE GROUP_PROD_CODE='" + product_id + "' " +
					 "AND GROUP_ENTITLEMENTS='" + entitlements + "'");
			sql.append("SELECT GROUP_PROD_CODE, " +
						"GROUP_ID, GROUP_ENTITLEMENTS FROM user_groups ");
			sql.append(param);
			// PrintLog("2. Group Exists SQL "+sql);
			statement	= dbConn.createStatement();
			result		= statement.executeQuery(sql.toString());
			// temp_1 = "";
			temp_2		= "";
			// temp_3 = "";
			while (result.next()) {
				// temp_1	= result.getString("GROUP_PROD_CODE");
				temp_2	= result.getString("GROUP_ID");
				// temp_3	= result.getString("GROUP_ENTITLEMENTS");
				gr_exists	= true;
				// PrintLog("2 Group Exists TRUE");
			}
		}
		statement.close();
		result.close();
		return (temp_2);
	}
	//
	public int CreateMasterGroups(Connection dbConn, String dbUsed,
									ProductSelector prodSelector) throws Exception {
		moduleName			= "CreateMasterGroups: ";
		String user_name	= "GroupMaint";
		//
		ArrayList<String> param_list	= new ArrayList<String>();
		param_list.add("");
		param_list.add("");
		param_list.add("");
		// int row_count	= GetProductRows (dbConn, param_list,prodSelector);
		ProductDetail prod_rows[]	= prodSelector.getProductrows();
		// PrintLog("NUMBER OF PRODUCT ROWS: "+prod_rows.length);
		StringBuffer sql			= new StringBuffer();
		Statement statement			= null;
		String mod_func				= "";
		String product_id			= "";
		String product_menu_id		= "";
		String product_menu_func_id	= "";
		String group_prod_code		= "";
		String group_id				= "";
		String group_entitlements	= "";
		sql.setLength(0);
		/**/
		sql.append("DELETE FROM user_groups where GROUP_ID='MASTER'");
		try {
			statement	= dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Group table empty ->" + e.toString());
			statement.close();
		}
		/**/
		product_id				= prod_rows[0].getProduct_id();
		product_menu_id			= prod_rows[0].getProduct_menu_id();
		product_menu_func_id	= prod_rows[0].getProduct_menu_func_id();
		// product_pnemonic		= prod_rows[0].getProduct_pnemonic().substring(0,4);
		// product_pmf_desc		= prod_rows[0].getProduct_pmf_desc();
		group_prod_code			= product_id;
		// group_prod_pnem		= product_pnemonic;
		group_id				= "MASTER";
		// group_entitlements	+= product_menu_id + product_menu_func_id;
		for (int i = 0; i < prod_rows.length; i++) {
			product_id				= prod_rows[i].getProduct_id();
			product_menu_id			= prod_rows[i].getProduct_menu_id();
			product_menu_func_id	= prod_rows[i].getProduct_menu_func_id();
			if (!product_id.equals(group_prod_code)) {
				if (group_entitlements.equals("")) {
					group_entitlements	= "  ";
				}
				if (group_entitlements.length() > 0) {
					group_entitlements	= group_entitlements.trim();
				}
				mod_func	= "Add Master";
				sql.setLength(0);
				sql.append("INSERT INTO user_groups VALUES (");
				sql.append("'" + group_prod_code + "', ");
				// sql.append("'"+group_prod_pnem+"', ");
				sql.append("'" + group_id + "', ");
				sql.append("'" + group_entitlements + "', ");
				if (dbUsed.equals("MySQL")) {
					sql.append("NULL, ");
				} else if (dbUsed.equals("ORACLE")) {
					sql.append("sysdate, ");
				}
				// sql.append("sysdate,");
				sql.append("'" + user_name + "', ");
				sql.append("'" + mod_func + "')");
				PrintLog("SQL: "+sql);
				try {
					statement	= dbConn.createStatement();
					// PrintLog("Executing result set");
					statement.executeUpdate(sql.toString());
					statement.close();
				} catch (SQLException e) {
					PrintLog("Master Group Insert SQL ---> " + sql);
					PrintLog("Error Inserting Master Group ->" + e.toString());
					statement.close();
					return (0);
				}
				/**/
				group_entitlements	= "";
			}
			group_prod_code			= product_id;
			// group_prod_pnem		= product_pnemonic;
			if ((!product_menu_id.equals("")) || (!product_menu_id.equals(" "))) {
				if ((product_menu_func_id.equals("")) || (product_menu_func_id.equals(""))) {
					product_menu_func_id	= " ";
				}
				group_entitlements	+= product_menu_id + product_menu_func_id;
			}
		}
		if (group_entitlements.equals("")) {
			group_entitlements	= "  ";
		}
		if (group_entitlements.length() > 0) {
			group_entitlements	= group_entitlements.trim();
		}
		mod_func	= "Add Master";
		sql.setLength(0);
		sql.append("INSERT INTO user_groups VALUES (");
		sql.append("'" + group_prod_code + "', ");
		// sql.append("'"+group_prod_pnem+"', ");
		sql.append("'" + group_id + "', ");
		sql.append("'" + group_entitlements + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		// sql.append("sysdate,");
		sql.append("'" + user_name + "', ");
		sql.append("'" + mod_func + "')");
		PrintLog("SQL: "+sql);
		try {
			statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
			statement.close();
		} catch (SQLException e) {
			PrintLog("Master Group Insert SQL ---> " + sql);
			PrintLog("Error Inserting Master Group ->" + e.toString());
			statement.close();
			return (0);
		}
		return (1);
	}
	//
	public int InsertUpdateGroup(Connection dbConn, String prod_id,
			String group_id, String entitlements,
			// GroupDetail groupDetail,
			String user_name, String dbUsed, int ins_or_upd) // 1 for insert or 2 for update
			throws Exception {
		moduleName			= "InsertUpdateGroup: ";
		String userName		= user_name.trim();
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		String mod_func		= "";
		//
		dbConn.setAutoCommit(false);
		if (ins_or_upd == 1) {
			mod_func	= "Add Group";
			sql.setLength(0);
			sql.append("INSERT INTO user_groups VALUES (");
			sql.append("'" + prod_id + "', ");
			sql.append("'" + group_id + "', ");
			sql.append("'" + entitlements + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("sysdate, ");
			}
			sql.append("'" + userName + "', ");
			sql.append("'" + mod_func + "')");
			//
			try {
				statement	= dbConn.createStatement();
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Group Insert SQL ---> " + sql);
				PrintLog("Error Inserting Group ->" + e.toString());
			}
		} else {
			mod_func	= "Modify Group";
			sql.setLength(0);
			sql.append("UPDATE user_groups SET ");
			sql.append("GROUP_PROD_CODE='" + prod_id + "', ");
			sql.append("GROUP_ID='" + group_id + "', ");
			sql.append("GROUP_ENTITLEMENTS='" + entitlements + "', ");
			if (dbUsed.equals("MySQL")) {
				sql.append("GROUP_LAST_MODIFIED=NULL, ");
			} else if (dbUsed.equals("ORACLE")) {
				sql.append("GROUP_LAST_MODIFIED=sysdate, ");
			}
			sql.append("GROUP_MOD_USER='" + userName + "', ");
			sql.append("GROUP_MOD_FUNC='" + mod_func + "' ");
			sql.append("WHERE GROUP_PROD_CODE='" + prod_id + "' ");
			sql.append("AND GROUP_ID='" + group_id + "'");
			//
			try {
				statement	= dbConn.createStatement();
				// PrintLog("Executing result set");
				statement.executeUpdate(sql.toString());
			} catch (SQLException e) {
				PrintLog("Group Update SQL ---> " + sql);
				PrintLog("Error Updating Group ->" + e.toString());
			}
		}
		statement.close();
		sql.setLength(0);
		sql.append("INSERT INTO user_groups_log VALUES (");
		sql.append("'" + prod_id + "', ");
		sql.append("'" + group_id + "', ");
		sql.append("'" + entitlements + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userName + "', ");
		sql.append("'" + mod_func + "')");
		//
		try {
			statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Group Log Insert SQL ---> " + sql);
			PrintLog("Error inserting Group Log ->" + e.toString());
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (1);
	}
	//
	public int DeleteGroup(Connection dbConn, String prod_id, String group_id,
			String entitlements, String user_name, String dbUsed)
			throws Exception {
		moduleName			= "InsertUpdateGroup: ";
		String userName		= user_name.trim();
		// PrintLog("In Delete Group "+prod_id);
		StringBuffer sql	= new StringBuffer();
		Statement statement	= null;
		// int result		= 0;
		int retVal			= 1; // Success
		// ArrayList<String> dummy_list = new ArrayList<String>(); // Dummy
		// array
		String mod_func		= "";
		//
		dbConn.setAutoCommit(false);
		mod_func			= "Delete Group";
		sql.setLength(0);
		sql.append("INSERT INTO user_groups_log VALUES (");
		sql.append("'" + prod_id + "', ");
		sql.append("'" + group_id + "', ");
		sql.append("'" + entitlements + "', ");
		if (dbUsed.equals("MySQL")) {
			sql.append("NULL, ");
		} else if (dbUsed.equals("ORACLE")) {
			sql.append("sysdate, ");
		}
		sql.append("'" + userName + "', ");
		sql.append("'" + mod_func + "')");
		//
		try {
			statement	= dbConn.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Group Log Insert SQL ---> " + sql);
			PrintLog("rror Inserting Group Log->" + e.toString());
		}
		statement.close();
		sql.setLength(0);
		sql.append("DELETE FROM user_groups ");
		sql.append("WHERE GROUP_PROD_CODE='" + prod_id + "' AND ");
		sql.append("GROUP_ID='" + group_id + "' ");
		//
		try {
			statement	= dbConn.createStatement();
			// PrintLog("Executing result set");
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			PrintLog("Group Delete SQL ---> " + sql);
			PrintLog("Error Deleting Group ->" + e.toString());
			retVal = 0;
		}
		dbConn.commit();
		dbConn.setAutoCommit(true);
		statement.close();
		return (retVal);
	}
}
