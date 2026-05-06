package com.webfiche.checkpoint.sysadmin.beans;


import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:30:29 $
 */

public final class RhostDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------------- Instance Variables
	private String moduleName;
	private String actionFlag			= "";
	private String accessFlag			= "";
	private String dbUsed				= "";
	//
	private String rhosts_node_id		= "";
	private String rhosts_rem_node		= "";
	private String rhosts_rem_os		= "";
	private String rhosts_user_name		= "";
	private String rhosts_user_pass		= "";
	private String rhosts_user_passh	= "";
	private String rhosts_user_passv	= "";
	private String rhostsXferMode		= "";
	private String rhostsXtra			= "";
	private String rhosts_last_modified	= "";
	private String rhosts_mod_user		= "";
	private String rhosts_mod_func		= "";
	//
	private HashMap <String,String>rhosts_modparam	= new HashMap<String,String>();
	//
	private String rhosts_node_id_o			= "";
	private String rhosts_rem_node_o		= "";
	private String rhosts_rem_os_o			= "";
	private String rhosts_user_name_o		= "";
	private String rhosts_user_pass_o		= "";
	private String rhosts_user_passv_o		= "";
	private String rhostsXferMode_o			= "";
	private String rhosts_last_modified_o	= "";
	private String rhosts_mod_user_o		= "";
	private String rhosts_mod_func_o		= "";
	private Vector <Vector<String>>errorVec	= new Vector<Vector<String>>();
	//
	//	Methods
	//
	private void PrintLog (String logMsg) {
    	System.out.println(java.util.Calendar.getInstance().getTime()+moduleName+logMsg);
    }
	public void clearErrors () {
    	errorVec.clear();
    }
	public Vector<Vector<String>> getErrorVec () {
    	return this.errorVec;
    }
	public void setErrorVec(String fieldName, String errorString) {
    	Vector <String>vecPair	= new Vector<String>();
    	vecPair.add(fieldName);
    	vecPair.add(errorString);
    	this.errorVec.add(vecPair);
    }
	//
	// ----------------------------------------------------------- Properties
    //
	public String getActionFlag() {
    	return (this.actionFlag);
    }
	public void setActionFlag (String actionFlag) {
    	this.actionFlag = actionFlag;
    }
	// Get and set accessFlag
	public String getAccessFlag() { 
    	return (this.accessFlag);
    }
	public void setAccessFlag(String access_flag) { 
    	this.accessFlag	= access_flag; 
    }
	//
	public String getDbUsed() {
    	return (this.dbUsed);
    }
	public void setDbUsed(String dbUsed) {
    	this.dbUsed = dbUsed;
    }
	//
	public String getRhosts_node_id() {
    	return (this.rhosts_node_id);
    }
	public void setRhosts_node_id(String rhosts_node_id) {
    	this.rhosts_node_id = rhosts_node_id;
    }
	//
	public String getRhosts_rem_node() {
    	return (this.rhosts_rem_node);
    }
	public void setRhosts_rem_node(String rhosts_rem_node) {
    	this.rhosts_rem_node = rhosts_rem_node;
    }
	//
	public String getRhosts_rem_os() {
    	return (this.rhosts_rem_os);
    }
	public void setRhosts_rem_os(String rhosts_rem_os) {
    	this.rhosts_rem_os = rhosts_rem_os;
    }
	//
	public String getRhosts_user_name() {
    	return (this.rhosts_user_name);
    }
	public void setRhosts_user_name(String rhosts_user_name) {
    	this.rhosts_user_name = rhosts_user_name;
    }
	//
	public String getRhosts_user_pass() {
    	return (this.rhosts_user_pass);
    }
	public String getRhosts_user_passh() {
    	return (this.rhosts_user_passh);
    }
	public void setRhosts_user_pass(String rhosts_user_pass) {
    	this.rhosts_user_pass	= rhosts_user_pass;
    	this.rhosts_user_passh	= "";
    	for (int idx=0; idx<=rhosts_user_pass.length(); idx++) {
    		this.rhosts_user_passh	+= "*";
    	}
    	this.rhosts_user_passh	+= "**";
    	//PrintLog("Rhost Pass "+rhosts_user_passh);
    }
	//
	public String getRhosts_user_passv() {
    	return (this.rhosts_user_passv);
    }
	public void setRhosts_user_passv(String rhosts_user_passv) {
    	this.rhosts_user_passv = rhosts_user_passv;
    }
	//
	public String getRhostsXferMode() {
    	return (this.rhostsXferMode);
    }
	public void setRhostsXferMode(String rhostsXferMode) {
    	this.rhostsXferMode = rhostsXferMode;
    }
	//
	public String getRhostsXtra() {
    	return (this.rhostsXtra);
    }
	public void setRhostsXtra(String rhostsXtra) {
    	this.rhostsXtra = rhostsXtra;
    }
	//
	public String getRhosts_last_modified() {
    	return (this.rhosts_last_modified);
    }
	public void setRhosts_last_modified(String rhosts_last_modified) {
    	if (!rhosts_last_modified.equals("") && rhosts_last_modified.length() > 13) {
    		this.rhosts_last_modified	= rhosts_last_modified.substring(0,4)+"/";
    		this.rhosts_last_modified	+= rhosts_last_modified.substring(4,6)+"/";
    		this.rhosts_last_modified	+= rhosts_last_modified.substring(6,8)+" @ ";
    		this.rhosts_last_modified	+= rhosts_last_modified.substring(8,10)+":";
    		this.rhosts_last_modified	+= rhosts_last_modified.substring(10,12)+":";
    		this.rhosts_last_modified	+= rhosts_last_modified.substring(12,14);
    	} else {
    		this.rhosts_last_modified	= rhosts_last_modified;
    	}
    }
	//
	public String getRhosts_mod_user() {
    	return (this.rhosts_mod_user);
    }
	public void setRhosts_mod_user(String rhosts_mod_user) {
    	this.rhosts_mod_user = rhosts_mod_user;
    }
	//
	public String getRhosts_mod_func() {
    	return (this.rhosts_mod_func);
    }
	public void setRhosts_mod_func(String rhosts_mod_func) {
    	this.rhosts_mod_func = rhosts_mod_func;
    }
	//
	// Saved fields
	//
	public String getRhosts_node_id_o() {
    	return (this.rhosts_node_id_o);
    }
	public void setRhosts_node_id_o(String rhosts_node_id_o) {
    	this.rhosts_node_id_o	= rhosts_node_id_o;
    }
	//
	public String getRhosts_rem_node_o() {
    	return (this.rhosts_rem_node_o);
    }
	public void setRhosts_rem_node_o(String rhosts_rem_node_o) {
    	this.rhosts_rem_node_o	= rhosts_rem_node_o;
    }
	//
	public String getRhosts_rem_os_o() {
    	return (this.rhosts_rem_os_o);
    }
	public void setRhosts_rem_os_o(String rhosts_rem_os_o) {
    	this.rhosts_rem_os_o	= rhosts_rem_os_o;
    }
	//
	public String getRhosts_user_name_o() {
    	return (this.rhosts_user_name_o);
    }
	public void setRhosts_user_name_o(String rhosts_user_name_o) {
    	this.rhosts_user_name_o	= rhosts_user_name_o;
    }
	//
	public String getRhosts_user_pass_o() {
    	return (this.rhosts_user_pass_o);
    }
	public void setRhosts_user_pass_o(String rhosts_user_pass_o) {
    	this.rhosts_user_pass_o	= rhosts_user_pass_o;
    }
	//
	public String getRhosts_user_passv_o() {
    	return (this.rhosts_user_passv_o);
    }
	public void setRhosts_user_passv_o(String rhosts_user_passv_o) {
    	this.rhosts_user_passv_o = rhosts_user_passv_o;
    }
	//
	public String getRhostsXferMode_o() {
    	return (this.rhostsXferMode_o);
    }
	public void setRhostsXferMode_o(String rhostsXferMode_o) {
    	this.rhostsXferMode_o = rhostsXferMode_o;
    }
	//
	public String getRhosts_last_modified_o() {
    	return (this.rhosts_last_modified_o);
    }
	public void setRhosts_last_modified_o(String rhosts_last_modified_o) {
    	this.rhosts_last_modified_o = rhosts_last_modified_o;
    }
	//
	public String getRhosts_mod_user_o() {
    	return (this.rhosts_mod_user_o);
    }
	public void setRhosts_mod_user_o(String rhosts_mod_user_o) {
    	this.rhosts_mod_user_o = rhosts_mod_user_o;
    }
	//
	public String getRhosts_mod_func_o() {
    	return (this.rhosts_mod_func_o);
    }
	public void setRhosts_mod_func_o(String rhosts_mod_func_o) {
    	this.rhosts_mod_func_o = rhosts_mod_func_o;
    }
	//
	public HashMap<String, String> getRhosts_modparam() {
    	return (this.rhosts_modparam);
    }
	public void setRhosts_modparam () {
    	this.rhosts_modparam.put("rhosts_node_id", rhosts_node_id);
    	this.rhosts_modparam.put("rhosts_rem_node", rhosts_rem_node);
    	this.rhosts_modparam.put("rhosts_user_name",rhosts_user_name);
    	this.rhosts_modparam.put("rhosts_user_pass",rhosts_user_pass);
    }
	public void clearNulls(){
    	this.rhosts_node_id		= (rhosts_node_id == null) ? " " :  rhosts_node_id;
    	this.rhosts_rem_node	= (rhosts_rem_node == null) ? " " :  rhosts_rem_node;
    	this.rhosts_rem_os		= (rhosts_rem_os == null) ? " " :  rhosts_rem_os;
    	this.rhosts_user_name	= (rhosts_user_name == null) ? " " :  rhosts_user_name;
    	this.rhosts_user_pass	= (rhosts_user_pass == null) ? " " :  rhosts_user_pass;
    	this.rhosts_user_passv	= (rhosts_user_passv == null) ? " " :  rhosts_user_passv;
    	this.rhostsXferMode		= (rhostsXferMode == null) ? " " :  rhostsXferMode;
    	this.rhostsXtra			= (rhostsXtra == null) ? " " :  rhostsXtra;
    }
	public void clearDetails(){
    	rhosts_node_id		= "";
    	rhosts_rem_node		= "";
    	rhosts_rem_os		= "";
    	rhosts_user_name	= "";
    	rhosts_user_pass	= "";
    	rhosts_user_passv	= "";
    	rhostsXferMode		= "";
    	rhostsXtra		= "";
    	rhosts_last_modified	= "";
    	rhosts_mod_user		= "";
    	rhosts_mod_func		= "";
    	setRhosts_make_copy ();
    }
	//
	public void setRhosts_make_copy () {
		rhosts_node_id_o		= rhosts_node_id;
		rhosts_rem_node_o		= rhosts_rem_node;
		rhosts_rem_os_o			= rhosts_rem_os;
		rhosts_user_name_o		= rhosts_user_name;
		rhosts_user_pass_o		= rhosts_user_pass;
		rhosts_user_passv_o		= rhosts_user_passv;
		rhostsXferMode_o		= rhostsXferMode;
		rhosts_last_modified_o	= rhosts_last_modified;
		rhosts_mod_user_o		= rhosts_mod_user;
		rhosts_mod_func_o		= rhosts_mod_func;
	}
	//
	public int CheckForChanges () {
    	moduleName	= "RhostDetail.CheckForChanges: ";
    	// Returns
    	//     0 - if nothing was changed
    	//     1 - if something was changed and every thing is valid
    	//     2 - if the changes result in invalid data
    	//
    	int data_changed	= 0;
    	//
    	// Just check if any of the fields was changed
    	//PrintLog(": CheckForChanges: sw addr changed ");
    	clearNulls();
    	if (rhosts_user_pass.equals("")) {
    		data_changed	= 2;
    		setErrorVec("Password", "error.field.required");
    		return (data_changed);
    	}
    	if (rhosts_user_pass.compareTo(rhosts_user_passv) != 0) {
    		data_changed	= 2;
    		setErrorVec("rhostspass_mismatch", "error.password.mismatch");
    		return (data_changed);
    	}
    	if (rhosts_rem_os_o.compareTo(rhosts_rem_os) != 0) {
    		data_changed	= 1;
    	}
    	if (rhosts_user_name_o.compareTo(rhosts_user_name) != 0) {
    		data_changed	= 1;
    	}
    	if (rhosts_user_pass_o.compareTo(rhosts_user_pass) != 0) {
    		data_changed	= 1;
    	}
    	if (rhosts_user_passv_o.compareTo(rhosts_user_passv) != 0) {
    		data_changed	= 1;
    	}
    	if (rhostsXferMode_o.compareTo(rhostsXferMode) != 0) {
    		data_changed	= 1;
    	}
    	if (data_changed == 0) {
    		setErrorVec("rhostsdata_notchanged", "info.nomods");
    	}
    	return (data_changed);
    }
	//
	public boolean NewHostFieldsValid () {
		moduleName	= "RhostDetail.NewHostFieldsValid: ";
		boolean ret_bool = true;
		// The Rhosts. Menu and Function rows are organised in the PMF-ID
		// order.
		if (rhosts_user_name.trim().equals("")) {
			ret_bool = false;
			PrintLog(": CheckForChanges: NULL ");
			setErrorVec("Remote Host User Name", "error.field.required");
		}
		if (rhosts_user_pass.trim().equals("")) {
			ret_bool = false;
			PrintLog(": CheckForChanges: NULL ");
			setErrorVec("Remote Host User Password", "error.field.required");
		}
		return (ret_bool);
	}
}

