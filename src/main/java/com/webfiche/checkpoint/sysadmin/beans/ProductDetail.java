package com.webfiche.checkpoint.sysadmin.beans;

import java.io.Serializable;
import java.util.*;

/**
 * @auhor Andy Patil
 * @version $Revision: 1.1 $ $Date: 2016/01/24 01:30:29 $
 */

public final class ProductDetail implements Serializable {
	/**
	 * 
	 */
	private static final long	   serialVersionUID		= 1L;
	// --------------------------------------------------- Instance Variables
	private String	actionFlag				= "";
	private String	accessFlag				= "";
	private String	dbUsed					= "";
	private String	nodeName				= "";
	private int		menuRank				= 0;
	private String	product_type			= " ";
	private String	product_key				= " ";
	private String	product_pmf_entitled	= " ";
	private String	product_id				= " ";
	private String	product_menu_id			= " ";
	private String	product_menu_func_id	= " ";
	private String	product_pnemonic		= " ";
	private String	product_pmf_desc		= " ";
	private String	product_pmf_link		= " ";
	private String	product_pmf_param		= " ";
	private String	product_last_modified	= " ";
	private String	product_mod_user		= " ";
	private String	product_mod_func		= " ";
	//private HashMap<String, String> product_modparam		= new HashMap<String, String>();

	private String	product_pmf_entitled_o  = " ";
	private String	product_id_o			= " ";
	private String	product_menu_id_o		= " ";
	private String	product_menu_func_id_o	= " ";
	private String	product_pnemonic_o		= " ";
	private String	product_pmf_desc_o		= " ";
	private String	product_pmf_link_o		= " ";
	private String	product_last_modified_o	= " ";
	private String	product_mod_user_o		= " ";
	private String	product_mod_func_o		= " ";
	//
	private Vector<Vector<String>>  errorVec				= new Vector<Vector<String>>();

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
	/*
	 * public void setErrorVec (String error) { this.errorVec.add(error); }
	 */
	public String getActionFlag() {
		return (this.actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	// Get and set accessFlag
	public String getAccessFlag() {
		return (this.accessFlag);
	}
	public void setAccessFlag(String access_flag) {
		this.accessFlag = access_flag;
	}
	//
	public String getDbUsed() {
		return (this.dbUsed);
	}
	public void setDbUsed(String dbUsed) {
		this.dbUsed = dbUsed;
	}
	//
	public String getNodeName() {
		return (this.nodeName);
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	//
	public int getMenuRank() {
		return (this.menuRank);
	}
	public void setMenuRank(int menuRank) {
		this.menuRank = menuRank;
	}
	//
	public String getProduct_type() {
		return (this.product_type);
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	//
	public String getProduct_key() {
		return (this.product_key);
	}
	public void setProduct_key(String product_key) {
		this.product_key = product_key;
	}
	//
	public String getProduct_pmf_entitled() {
		return (this.product_pmf_entitled);
	}
	public void setProduct_pmf_entitled(String product_pmf_entitled) {
		this.product_pmf_entitled = product_pmf_entitled;
	}
	//
	public String getProduct_pmf_entitled_o() {
		return (this.product_pmf_entitled_o);
	}
	public void setProduct_pmf_entitled_o(String product_pmf_entitled_o) {
		this.product_pmf_entitled_o = product_pmf_entitled_o;
	}
	//
	public String getProduct_id() {
		return (this.product_id);
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	//
	public String getProduct_menu_id() {
		return (this.product_menu_id);
	}
	public void setProduct_menu_id(String product_menu_id) {
		if (product_menu_id.equals("") || product_menu_id.equals(" ")) {
			this.product_key = this.product_id + "  ";
			this.product_type = "P";
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			//					  ": Product Key:1 >"+this.product_key+"<");
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// 					  ": PMF ENTITLED:1 >"+this.product_pmf_entitled+"<");
		}
		this.product_menu_id = product_menu_id;
	}
	//
	public String getProduct_menu_func_id() {
		return (this.product_menu_func_id);
	}
	public void setProduct_menu_func_id(String product_menu_func_id) {
		if (product_menu_func_id.equals("") || product_menu_func_id.equals(" ")) {
			if ((!product_menu_id.equals("")) && (!product_menu_id.equals(" "))) {
				this.product_key = this.product_id + this.product_menu_id + " ";
				this.product_type = "M";
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// ": Product Key:2 >"+this.product_key+"<");
				// System.out.println(java.util.Calendar.getInstance().getTime()+
				// ": PMF ENTITLED:2 >"+this.product_pmf_entitled+"<");
			}
		} else {
			this.product_key = (this.product_id + this.product_menu_id + product_menu_func_id);
			this.product_type = "F";
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": Product Key:3 >"+this.product_key+"<");
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": PMF ENTITLED:3 >"+this.product_pmf_entitled+"<");
		}
		this.product_menu_func_id = product_menu_func_id;
	}
	//
	public String getProduct_pnemonic() {
		return (this.product_pnemonic);
	}
	public void setProduct_pnemonic(String product_pnemonic) {
		this.product_pnemonic = product_pnemonic;
	}
	//
	public String getProduct_pmf_desc() {
		return (this.product_pmf_desc);
	}
	public void setProduct_pmf_desc(String product_pmf_desc) {
		this.product_pmf_desc = product_pmf_desc;
	}
	//
	public String getProduct_pmf_link() {
		return (this.product_pmf_link);
	}
	public void setProduct_pmf_link(String product_pmf_link) {
		this.product_pmf_link = product_pmf_link;
	}
	//
	public String getProduct_pmf_param() {
		return (this.product_pmf_param);
	}
	public void setProduct_pmf_param(String product_pmf_param) {
		this.product_pmf_param = product_pmf_param;
	}
	//
	public String getProduct_last_modified() {
		return (this.product_last_modified);
	}
	public void setProduct_last_modified(String product_last_modified) {
		if (!product_last_modified.equals("")
			&& product_last_modified.length() > 25) {
			this.product_last_modified = (product_last_modified.substring(0, 8) +
					" @" + product_last_modified.substring(10, 18) +
					product_last_modified.substring(26, 28));
		} else if (!product_last_modified.equals("")
				&& product_last_modified.length() > 13) {
			this.product_last_modified = product_last_modified.substring(0, 4) + "/";
			this.product_last_modified += product_last_modified.substring(4, 6) + "/";
			this.product_last_modified += product_last_modified.substring(6, 8) + " @ ";
			this.product_last_modified += product_last_modified.substring(8, 10) + ":";
			this.product_last_modified += product_last_modified.substring(10,12) + ":";
			this.product_last_modified += product_last_modified.substring(12,14);
		} else {
			this.product_last_modified = product_last_modified;
		}
	}
	//
	public String getProduct_mod_user() {
		return (this.product_mod_user);
	}
	public void setProduct_mod_user(String product_mod_user) {
		this.product_mod_user = product_mod_user;
	}
	//
	public String getProduct_mod_func() {
		return (this.product_mod_func);
	}
	public void setProduct_mod_func(String product_mod_func) {
		this.product_mod_func = product_mod_func;
	}
	public void setPMFValues() {
		// this.product_pmf_desc = " ";
		// if (!this.product_menu_func_id.equals(" ")) {
		this.product_pmf_link = "NA";
		// }
	}
	//
	// Saved fields
	//
	public String getProduct_id_o() {
		return (this.product_id_o);
	}
	public void setProduct_id_o(String product_id_o) {
		this.product_id_o = product_id_o;
	}
	//
	public String getProduct_menu_id_o() {
		return (this.product_menu_id_o);
	}
	public void setProduct_menu_id_o(String product_menu_id_o) {
		this.product_menu_id_o = product_menu_id_o;
	}
	//
	public String getProduct_menu_func_id_o() {
		return (this.product_menu_func_id_o);
	}
	public void setProduct_menu_func_id_o(String product_menu_func_id_o) {
		this.product_menu_func_id_o = product_menu_func_id_o;
	}
	//
	public String getProduct_pnemonic_o() {
		return (this.product_pnemonic_o);
	}
	public void setProduct_pnemonic_o(String product_pnemonic_o) {
		this.product_pnemonic_o = product_pnemonic_o;
	}
	//
	public String getProduct_pmf_desc_o() {
		return (this.product_pmf_desc_o);
	}
	public void setProduct_pmf_desc_o(String product_pmf_desc_o) {
		this.product_pmf_desc_o = product_pmf_desc_o;
	}
	//
	public String getProduct_pmf_link_o() {
		return (this.product_pmf_link_o);
	}
	public void setProduct_pmf_link_o(String product_pmf_link_o) {
		this.product_pmf_link_o = product_pmf_link_o;
	}
	//
	public String getProduct_last_modified_o() {
		return (this.product_last_modified_o);
	}
	public void setProduct_last_modified_o(String product_last_modified_o) {
		this.product_last_modified_o = product_last_modified_o;
	}
	//
	public String getProduct_mod_user_o() {
		return (this.product_mod_user_o);
	}
	public void setProduct_mod_user_o(String product_mod_user_o) {
		this.product_mod_user_o = product_mod_user_o;
	}
	//
	public String getProduct_mod_func_o() {
		return (this.product_mod_func_o);
	}
	public void setProduct_mod_func_o(String product_mod_func_o) {
		this.product_mod_func_o = product_mod_func_o;
	}
	/*
	public HashMap<String, String> getProduct_modparam() {
		return (this.product_modparam);
	}
	public void setProduct_modparam() {
		this.product_modparam.put("product_id", product_id);
		this.product_modparam.put("product_menu_id", product_menu_id);
		this.product_modparam.put("product_menu_func_id", product_menu_func_id);
	}
	*/
	public void clearDetails() {
		this.product_pmf_entitled	= "";
		this.product_id				= "";
		this.product_menu_id		= "";
		this.product_menu_func_id	= "";
		this.product_pnemonic		= "";
		this.product_pmf_desc		= "";
		this.product_pmf_link		= "";
	}
	//
	public void clearNulls() {
		this.product_pmf_entitled	= (product_pmf_entitled == null) ? " " : product_pmf_entitled;
		this.product_id				= (product_id == null) ? " " : product_id;
		this.product_menu_id		= (product_menu_id == null) ? " " : product_menu_id;
		this.product_menu_func_id	= (product_menu_func_id == null) ? " " : product_menu_func_id;
		this.product_pnemonic		= (product_pnemonic == null) ? " " : product_pnemonic;
		this.product_pmf_desc		= (product_pmf_desc == null) ? " " : product_pmf_desc;
		this.product_pmf_link		= (product_pmf_link == null) ? " " : product_pmf_link;
	}
	//
	public void setProduct_make_copy() {
		clearNulls();
		this.product_pmf_entitled_o		= this.product_pmf_entitled;
		this.product_id_o				= this.product_id;
		this.product_menu_id_o			= this.product_menu_id;
		this.product_menu_func_id_o		= this.product_menu_func_id;
		this.product_pnemonic_o			= this.product_pnemonic;
		this.product_pmf_desc_o			= this.product_pmf_desc;
		this.product_pmf_link_o			= this.product_pmf_link;
		this.product_last_modified_o	= this.product_last_modified;
		this.product_mod_user_o			= this.product_mod_user;
		this.product_mod_func_o			= this.product_mod_func;
	}

	public void restoreParams() {
		this.product_pmf_entitled	= this.product_pmf_entitled_o;
		this.product_id				= this.product_id_o;
		this.product_menu_id		= this.product_menu_id_o;
		this.product_menu_func_id	= this.product_menu_func_id_o;
		this.product_pnemonic		= this.product_pnemonic_o;
		this.product_pmf_desc		= this.product_pmf_desc_o;
		this.product_pmf_link		= this.product_pmf_link_o;
		this.product_last_modified	= this.product_last_modified_o;
		this.product_mod_user		= this.product_mod_user_o;
		this.product_mod_func		= this.product_mod_func_o;
	}

	public int CheckForChanges() {
		// Returns
		// 0 - if nothing was changed
		// 1 - if something was changed and every thing is valid
		// 2 - if the changes result in invalid data
		//
		int data_changed = 0;
		clearErrors();
		//
		// Just check if any of the fields was changed
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": CheckForChanges: sw addr changed ");
		product_pnemonic = product_pnemonic.toUpperCase();
		if (product_pnemonic_o.compareTo(product_pnemonic) != 0) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> CheckForChanges: product_pnemonic OLD "+product_pnemonic_o+
			// " NEW "+product_pnemonic);
			data_changed = 1;
		}
		if (product_pmf_desc_o.compareTo(product_pmf_desc) != 0) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> CheckForChanges: product_desc OLD "+product_pmf_desc_o+
			// " NEW "+product_pmf_desc);
			data_changed = 1;
		}
		if (product_pmf_link_o.compareTo(product_pmf_link) != 0) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// "> CheckForChanges: product_link OLD "+product_pmf_link_o+
			// " NEW "+product_pmf_link);
			data_changed = 1;
		}
		if (data_changed == 0) {
			setErrorVec("Product ID", "info.nomods");
		}
		return (data_changed);
	}

	public boolean NewProdFieldsValid() {
		boolean ret_bool = true;
		clearErrors();
		// The Product. Menu and Function rows are organised in the PMF-ID
		// order.
		if (product_pmf_desc.trim().equals("")) {
			ret_bool = false;
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": CheckForChanges: NULL ");
			setErrorVec("Product/Menu/Function Description", "error.field.required");
		}
		if (product_pmf_link.trim().equals("")) {
			ret_bool = false;
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": CheckForChanges: NULL ");
			setErrorVec("Product/Menu/Function Link", "error.field.required");
		}
		if (product_pnemonic.equals("")) {
			ret_bool = false;
			// System.out.println(java.util.Calendar.getInstance().getTime()+
			// ": CheckForChanges: NULL ");
			setErrorVec("Product Pnemonic", "error.field.required");
		}
		return (ret_bool);
	}
}
