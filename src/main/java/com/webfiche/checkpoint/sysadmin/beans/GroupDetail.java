/*
 *
 * ====================================================================
 *
 */

package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;

public final class GroupDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID   = 1L;
	// Instance Variables
	private String	actionFlag;
	private String	accessFlag;
	private String	dbUsed;
	private String	gdProd;
	private String	gdProdpnem;
	private String	gdGroup_id;
	//private String	prodArray[]		= { "", "", "", "", "",
	//									"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
	//									"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
	//									"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
	//									"", "", "", ""};
	private String	pmfArray[]		 = { "", "", "", "", "",
										 "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										 "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										 "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										 "", "", "", ""};
	private String	pmfArray_o[]	   = { "", "", "", "", "",
										   "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										   "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										   "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										   "", "", "", ""};
	private String[]	group_pmf_entitled = new String[0];
	private String	gd_last_modified;
	private String	gd_mod_user;
	private String	gd_mod_func;
	private HashMap<String, String> gd_modparam		= new HashMap<String, String>();
	Vector<String>	prodVec			= new Vector<String>();
	Vector<String>	pmfVec			 = new Vector<String>();
	Vector<String>	pmfVec_o		   = new Vector<String>();
	Vector<Vector<String>>		  errorVec		   = new Vector<Vector<String>>();
	ArrayList<String>	groupList		  = new ArrayList<String>();
	//
	// Get and set actionFlag
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
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String action_flag) {
		this.actionFlag = action_flag;
	}
	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
	}
	// Get and Set Database used
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getGdProd() {
		return (this.gdProd);
	}
	public void setGdProd(String gdProd) {
		this.gdProd = gdProd;
	}
	// Get and set Prod Pnem
	public String getGdProdpnem() {
		return (this.gdProdpnem);
	}
	public void setGdProdpnem(String gdProdpnem) {
		this.gdProdpnem = gdProdpnem;
	}
	// Get and set the Group
	public String getGdGroup_id() {
		return (this.gdGroup_id);
	}
	public void setGdGroup_id(String gdGroup_id) {
		this.gdGroup_id = gdGroup_id;
		if (gdGroup_id != null) {
			this.gdGroup_id = gdGroup_id.toUpperCase();
		}
	}
	/*
	// --------------------------------------
	public String getPmfArray(int index) {
		if (index < 60) {
			return (this.pmfArray[index]);
		} else {
			return (null);
		}
	}
	public void setPmfArray(int index, String pmf_code) {
		if (index < 60) {
			this.pmfArray[index] = pmf_code;
		}
	}
	public String[] getPmfArray() {
		return (this.pmfArray);
	}
	public void setPmfArray(String[] pmf_array) {
		this.pmfArray = pmf_array;
	}
	// --------------------------------------
	public String getProdArray(int index) {
		if (index < 60) {
			return (this.prodArray[index]);
		} else {
			return (null);
		}
	}
	public void setProdArray(int index, String prod_code) {
		if (index < 60) {
			this.prodArray[index] = prod_code;
		}
	}
	public String[] getProdArray() {
		return (this.prodArray);
	}
	public void setProdArray(String[] prod_array) {
		this.prodArray = prod_array;
	}
	// -----------------------------------------------
	public String getPmfArray_o(int index) {
		if (index < 60) {
			return (this.pmfArray_o[index]);
		} else {
			return (null);
		}
	}
	public void setPmfArray_o(int index, String pmf_code) {
		if (index < 60) {
			this.pmfArray_o[index] = pmf_code;
		}
	}
	public String[] getPmfArray_o() {
		return (this.pmfArray_o);
	}
	public void setPmfArray_o(String[] pmf_array) {
		this.pmfArray_o = pmf_array;
	}
	*/
	// -------------------------------------------------
	public String getGd_last_modified() {
		return (this.gd_last_modified);
	}
	public void setGd_last_modified(String last_modified) {
		this.gd_last_modified = last_modified;
	}
	//
	public String getGd_mod_user() {
		return (this.gd_mod_user);
	}
	public void setGd_mod_user(String mod_user) {
		this.gd_mod_user = mod_user;
	}
	//
	public String getGd_mod_func() {
		return (this.gd_mod_func);
	}
	public void setGd_mod_func(String mod_func) {
		this.gd_mod_func = mod_func;
	}
	//
	// Used to populate the pmf array from the DB row of 1 to 60 codes
	//
	public void setGroupArray(String gd_codes) {
		int ctr = 0;
		String gdCodes = gd_codes;
		while (gdCodes.length() > 0) {
			pmfArray[ctr] = gd_codes.substring(0, 2);
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": HOLIUTIL Dates "+dateArray[ctr]);
			gdCodes = gdCodes.substring(3, gdCodes.length());
			ctr++;
		}
	}
	public String getGroupArray() {
		int ctr = 0;
		String temp_pmf = "";
		for (ctr = 0; ctr < 60; ctr++) {
			if (!pmfArray[ctr].equals("")) {
				temp_pmf += pmfArray[ctr];
			}
		}
		return (temp_pmf);
	}
	//
	// ----------------------------------------------------------------------
	public String getGroup_pmf_entitled(int index) {
		if (index < 60) {
			// return ((String) this.group_pmf_entitled.get(index));
			return (this.group_pmf_entitled[index]);
		} else {
			return (null);
		}
	}
	//
	/*
	public void setGroup_pmf_entitled(int index) {
		if (index < 60) {
			// if (this.group_pmf_entitled.size() > index) {
			// this.group_pmf_entitled.set(index,group_pmf_entitled);
			// } else {
			// this.group_pmf_entitled.add(group_pmf_entitled);
			// }
			// this.group_pmf_entitled[index] = group_pmf_entitled;
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": GroupSel Entitle:1 >"+this.group_pmf_entitled.get(index)+"<");
	}
	//
	 */
	public String[] getGroup_pmf_entitled() {
		return (this.group_pmf_entitled);
	}
	public void setGroup_pmf_entitled(String[] group_pmf_entitled) {
		this.group_pmf_entitled = group_pmf_entitled;
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": GroupSel Entitle:1 >"+java.lang.reflect.Array.getLength(this.group_pmf_entitled)+"<");
	}
	//
	//
	// public String getGd_modparam() {
	// return (this.gd_modparam);
	// }
	// public void setGd_modparam () {
	// this.gd_modparam = gdProdGroup;
	// }
	public void setPmf_make_copy() {
		for (int idx = 0; idx < 60; idx++) {
			this.pmfArray_o[idx] = this.pmfArray[idx];
		}
	}
	public int CheckForChanges() {
		int data_changed = 0;
		for (int idx = 0; idx < 60; idx++) {
			if (!this.pmfArray[idx].equals(this.pmfArray_o[idx])) {
				data_changed = 1;
			}
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": HOLIUTIL Dates Old >"+dateArray_o[idx]+"<  NEW >"+dateArray[idx]+"<");
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": HOLIUTIL Dates "+dateArray_o[idx]);
		}
		if (data_changed == 0) {
			setErrorVec("group_id", "info.nomods");
		}
		return (data_changed);
	}
	public HashMap<String, String> getGd_modparam() {
		return (this.gd_modparam);
	}
	public void setGd_modparam() {
		this.gd_modparam.put("product_id", gdProd);
		this.gd_modparam.put("product_pnem", gdProdpnem);
		this.gd_modparam.put("group_id", gdGroup_id);
	}
}
