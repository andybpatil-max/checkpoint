package com.webfiche.checkpoint.sysadmin.beans;
//
import java.io.*;
import java.util.*;
//
public final class ProductSelector implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance Variables
	// private String className = "> ProductSelector.";
	// private String moduleName = "";
	private String param		= "";
	private String userId		= "";
	private int linksPerCol		= 0;
	private int linksPerCol1	= 0;
	private int linksInCol1		= 0;
	private int linksInCol2		= 0;
	private int linksInCol3		= 0;
	private int linksInCol4		= 0;
	private int linksInCol5		= 0;
	private int linksInCol6		= 0;
	private int linksInCol7		= 0;
	private int linksInCol8		= 0;
	private int linksInCol9		= 0;
	private int linksInCol10	= 0;
	private int offSet0			= 0;
	private int offSet1			= 0;
	private int offSet2			= 0;
	private int offSet3			= 0;
	private int offSet4			= 0;
	private int offSet5			= 0;
	private int offSet6			= 0;
	private int offSet7			= 0;
	private int offSet8			= 0;
	private int offSet9			= 0;
	private int lastFunc		= 0;
	private int numMenus		= 0;
	private Vector<Integer> menuCount	= new Vector<Integer>();
	private Vector<Integer> menuPos		= new Vector<Integer>();
	private String param_prod	= "";
	private String actionFlag	= "";
	private String accessFlag	= "";
	private String dbUsed		= "";
	private String nodeName		= "";
	private String user_name	= "";
	private String group_name	= "";
	private String appl_date	= "";
	private String product_id_sel				= "";
	private String product_menu_id_sel			= "";
	private String product_menu_func_id_sel		= "";
	private String product_id_sel_o				= "";
	private String product_menu_id_sel_o		= "";
	private String product_menu_func_id_sel_o	= "";
	private TreeMap<String, String> prodNames	= new TreeMap<String, String>();
	private TreeMap<String, HashMap<String, ArrayList<String>>> groupList	= 
							new TreeMap<String, HashMap<String, ArrayList<String>>>();
	private ArrayList<String> group_list		= new ArrayList<String>();
	private String goHome						= "com.webfiche.checkpoint.actions.MenuAction";
	private String logOff						= "com.webfiche.checkpoint.actions.LogoutAction";
	private String[] prod_pmf_entitled			= new String[0];
	// private ArrayList prod_pmf_desc = new ArrayList(60);
	// Vector prodType = new Vector<String> ();
	// Vector prodKey = new Vector<String> ();
	// Vector pmfDesc = new Vector<String> ();
	//
	private ArrayList<ProductDetail> prodrows = new ArrayList<ProductDetail>();
	private Vector<Vector<String>> errorVec = new Vector<Vector<String>>();
	/*
	 * private void PrintLog (String logMsg) { System.out.println
	 * (java.util.Calendar.getInstance().getTime()+
	 * className+moduleName+logMsg); }
	 */
	public void clear_arrays() {
		this.prod_pmf_entitled = new String[0];
		this.prodrows.clear();
	}
	public void clearRows() {
		this.prodrows.clear();
	}
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
	public String getUserId() {
		return (this.userId);
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//
	public void saveParams() {
		product_id_sel_o = product_id_sel;
		product_menu_id_sel_o = product_menu_id_sel;
		product_menu_func_id_sel_o = product_menu_func_id_sel;
	}
	//
	public void restoreParams() {
		product_id_sel = product_id_sel_o;
		product_menu_id_sel = product_menu_id_sel_o;
		product_menu_func_id_sel = product_menu_func_id_sel_o;
	}
	// Properties
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
	public String getUser_name() {
		return (this.user_name);
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	//
	public String getAppl_date() {
		return (this.appl_date);
	}
	public void setAppl_date(String appl_date) {
		this.appl_date = appl_date;
	}
	//
	public String getProduct_id_sel() {
		return (this.product_id_sel);
	}
	public void setProduct_id_sel(String product_id_sel) {
		this.product_id_sel = product_id_sel;
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": ProdSel P-ID:1 >"+this.product_id_sel+"<");
	}
	//
	public String getProduct_menu_id_sel() {
		return (this.product_menu_id_sel);
	}
	public void setProduct_menu_id_sel(String product_menu_id_sel) {
		this.product_menu_id_sel = product_menu_id_sel;
	}
	//
	public String getProduct_menu_func_id_sel() {
		return (this.product_menu_func_id_sel);
	}
	public void setProduct_menu_func_id_sel(String product_menu_func_id_sel) {
		this.product_menu_func_id_sel = product_menu_func_id_sel;
	}
	//
	public TreeMap<String, String> getProdNames() {
		return (this.prodNames);
	}
	public void setProdNames(TreeMap<String, String> prodNames) {
		this.prodNames = prodNames;
	}
	//
	public TreeMap<?, ?> getGroupList() {
		return (this.groupList);
	}
	public void setGroupList(TreeMap<String, HashMap<String, ArrayList<String>>> groupList) {
		this.groupList = groupList;
	}
	//
	//
	public ArrayList<String> getGroup_list() {
		return (this.group_list);
	}
	public void setGroup_list(ArrayList<String> group_list) {
		group_list.clear();
		Iterator<String> gL = group_list.iterator();
		while (gL.hasNext()) {
			this.group_list.add((String) gL.next());
		}
	}
	//
	public String getGroup_name() {
		return (this.group_name);
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name.toUpperCase();
	}
	//
	public String getGoHome() {
		return (this.goHome);
	}
	public void setGoHome(String goHome) {
		this.goHome = goHome;
	}
	//
	public String getLogOff() {
		return (this.logOff);
	}
	public void setLogOff(String logOff) {
		this.logOff = logOff;
	}
	public String getProd_pmf_entitled(int index) {
		if (index < 60) {
			// return ((String) this.prod_pmf_entitled.get(index));
			return (this.prod_pmf_entitled[index]);
		} else {
			return (null);
		}
	}
	//
	public void setProd_pmf_entitled(int index) {
		if (index < 60) {
			// if (this.prod_pmf_entitled.size() > index) {
			// this.prod_pmf_entitled.set(index,prod_pmf_entitled);
			// } else {
			// this.prod_pmf_entitled.add(prod_pmf_entitled);
			// }
			// this.prod_pmf_entitled[index] = prod_pmf_entitled;
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": ProdSel Entitle:1 >"+this.prod_pmf_entitled.get(index)+"<");
	}
	//
	public String[] getProd_pmf_entitled() {
		return (this.prod_pmf_entitled);
	}
	public void setProd_pmf_entitled(String[] prod_pmf_entitled) {
		this.prod_pmf_entitled = prod_pmf_entitled;
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// ": ProdSel Entitle:1 >"+java.lang.reflect.Array.getLength(this.prod_pmf_entitled)+"<");
	}
	public void setLinksPerCol(int linksPerCol) {
		this.linksPerCol = linksPerCol;
	}
	//
	public int getLinksPerCol() {
		return this.linksPerCol;
	}
	//
	public void setLinksPerCol1(int linksPerCol1) {
		this.linksPerCol1 = linksPerCol1;
	}
	//
	public int getLinksPerCol1() {
		return this.linksPerCol1;
	}
	//
	public void setLinksInCol1(int linksInCol1) {
		this.linksInCol1 = linksInCol1;
	}
	//
	public int getLinksInCol1() {
		return this.linksInCol1;
	}
	//
	public void setLinksInCol2(int linksInCol2) {
		this.linksInCol2 = linksInCol2;
	}
	//
	public int getLinksInCol2() {
		return this.linksInCol2;
	}
	//
	public void setLinksInCol3(int linksInCol3) {
		this.linksInCol3 = linksInCol3;
	}
	//
	public int getLinksInCol3() {
		return this.linksInCol3;
	}
	//
	public void setLinksInCol4(int linksInCol4) {
		this.linksInCol4 = linksInCol4;
	}
	//
	public int getLinksInCol4() {
		return this.linksInCol4;
	}
	//
	public void setLinksInCol5(int linksInCol5) {
		this.linksInCol5 = linksInCol5;
	}
	//
	public int getLinksInCol5() {
		return this.linksInCol5;
	}
	//
	public void setLinksInCol6(int linksInCol6) {
		this.linksInCol6 = linksInCol6;
	}
	//
	public int getLinksInCol6() {
		return this.linksInCol6;
	}
	//
	public void setLinksInCol7(int linksInCol7) {
		this.linksInCol7 = linksInCol7;
	}
	//
	public int getLinksInCol7() {
		return this.linksInCol7;
	}
	//
	public void setLinksInCol8(int linksInCol8) {
		this.linksInCol8 = linksInCol8;
	}
	//
	public int getLinksInCol8() {
		return this.linksInCol8;
	}
	//
	public void setLinksInCol9(int linksInCol9) {
		this.linksInCol9 = linksInCol9;
	}
	//
	public int getLinksInCol9() {
		return this.linksInCol9;
	}
	//
	public void setLinksInCol10(int linksInCol10) {
		this.linksInCol10 = linksInCol10;
	}
	//
	public int getLinksInCol10() {
		return this.linksInCol10;
	}
	//
	public void setOffSet0(int offSet0) {
		this.offSet0 = offSet0;
	}
	//
	public int getOffSet0() {
		return this.offSet0;
	}
	//
	public void setOffSet1(int offSet1) {
		this.offSet1 = offSet1;
	}
	//
	public int getOffSet1() {
		return this.offSet1;
	}
	//
	public void setOffSet2(int offSet2) {
		this.offSet2 = offSet2;
	}
	//
	public int getOffSet2() {
		return this.offSet2;
	}
	//
	public void setOffSet3(int offSet3) {
		this.offSet3 = offSet3;
	}
	//
	public int getOffSet3() {
		return this.offSet3;
	}
	//
	public void setOffSet4(int offSet4) {
		this.offSet4 = offSet4;
	}
	//
	public int getOffSet4() {
		return this.offSet4;
	}
	//
	public void setOffSet5(int offSet5) {
		this.offSet5 = offSet5;
	}
	//
	public int getOffSet5() {
		return this.offSet5;
	}
	//
	public void setOffSet6(int offSet6) {
		this.offSet6 = offSet6;
	}
	//
	public int getOffSet6() {
		return this.offSet6;
	}
	//
	public void setOffSet7(int offSet7) {
		this.offSet7 = offSet7;
	}
	//
	public int getOffSet7() {
		return this.offSet7;
	}
	//
	public void setOffSet8(int offSet8) {
		this.offSet8 = offSet8;
	}
	//
	public int getOffSet8() {
		return this.offSet8;
	}
	//
	public void setOffSet9(int offSet9) {
		this.offSet9 = offSet9;
	}
	//
	public int getOffSet9() {
		return this.offSet9;
	}
	//
	public void setLastFunc(int lastFunc) {
		this.lastFunc = lastFunc;
	}
	public int getLastFunc() {
		return this.lastFunc;
	}
	//
	public void setNumMenus(int numMenus) {
		this.numMenus = numMenus;
	}
	public int getNumMenus() {
		return this.numMenus;
	}
	//
	public void setParam_prod(String param_prod) {
		this.param_prod = param_prod;
	}
	//
	public String getParam_prod() {
		return this.param_prod;
	}
	//
	public void setMenuPos(Vector<?> menuPos) {
		Iterator<?> menus = menuPos.iterator();
		while (menus.hasNext()) {
			this.menuPos.add((Integer) menus.next());
		}
		// this.menuPos = menuPos;
	}
	//
	public Vector<Integer> getMenuPos() {
		return this.menuPos;
	}
	//
	public int getMenuPos(int i) {
		return this.menuPos.get(i);
	}
	//
	public void clearMenuPos() {
		this.menuPos.clear();
	}
	//
	public void setMenuCount(Vector<?> menuCount) {
		Iterator<?> menuCounts = menuCount.iterator();
		while (menuCounts.hasNext()) {
			this.menuCount.add((Integer) menuCounts.next());
		}
		// this.menuCount = menuCount;
	}
	//
	public Vector<Integer> getMenuCount() {
		return this.menuCount;
	}
	//
	//
	// ----------------------------------------------------------------------
	public ProductDetail[] getProductrows() {
		ProductDetail results[] = new ProductDetail[prodrows.size()];
		Iterator<ProductDetail> prods = prodrows.iterator();
		int n = 0;
		while (prods.hasNext()) {
			results[n++] = (ProductDetail) prods.next();
		}
		// System.out.println(java.util.Calendar.getInstance().getTime()+
		// "> getProductrows: Prod Rows >"+n+"<");
		return (results);
	}
	//
	public int getNumProds() {
		return (this.prodrows.size());
	}
	//
	public ProductDetail getArow() {
		ProductDetail results = new ProductDetail();
		Iterator<ProductDetail> prods = prodrows.iterator();
		results = (ProductDetail) prods.next();
		return (results);
	}
	//
	public void setProductrows(ArrayList<?> prod_row) {
		Iterator<?> rows = prod_row.iterator();
		this.prodrows.clear();
		// int n = 0;
		int i = 0;
		while (rows.hasNext()) {
			// n++;
			this.prodrows.add((ProductDetail) rows.next());
		}
		// this.linksPerCol = ((n+4)/4);
		this.linksPerCol = (this.lastFunc + 4) / 4;
		if (linksPerCol * 4 < this.lastFunc) {
			this.linksPerCol++;
		}
		// this.linksPerCol1 = this.linksPerCol - 2;
		this.offSet0 = 0;
		this.offSet1 = 0;
		this.offSet2 = 0;
		this.offSet3 = 0;
		this.offSet4 = 0;
		this.offSet5 = 0;
		this.offSet6 = 0;
		this.offSet7 = 0;
		this.offSet8 = 0;
		this.offSet9 = 0;
		this.linksInCol1 = 0;
		this.linksInCol2 = 0;
		this.linksInCol3 = 0;
		this.linksInCol4 = 0;
		this.linksInCol5 = 0;
		this.linksInCol6 = 0;
		this.linksInCol7 = 0;
		this.linksInCol8 = 0;
		this.linksInCol9 = 0;
		this.linksInCol10 = 0;
		/*
		 * System.out.println(java.util.Calendar.getInstance().getTime()+
		 * "> setProductrows: # of Menu Positions "+ this.menuPos.size());
		 */
		this.menuCount.clear();
		for (i = 0; i < this.menuPos.size(); i++) {
			/*
			 * System.out.println(java.util.Calendar.getInstance().getTime()+
			 * "> setProductrows: Menu Pos "+i+" "+ this.menuPos.get(i));
			 */
			if (i == 0) {
				this.offSet0 = this.menuPos.get(i);
			} else if (i == 1) {
				this.offSet1 = this.menuPos.get(i);
				linksInCol1 = this.offSet1 - this.offSet0 + 1;
			} else if (i == 2) {
				this.offSet2 = this.menuPos.get(i);
				linksInCol2 = this.offSet2 - this.offSet1;
			} else if (i == 3) {
				this.offSet3 = this.menuPos.get(i);
				linksInCol3 = this.offSet3 - this.offSet2;
			} else if (i == 4) {
				this.offSet4 = this.menuPos.get(i);
				linksInCol4 = this.offSet4 - this.offSet3;
			} else if (i == 5) {
				this.offSet5 = this.menuPos.get(i);
				linksInCol5 = this.offSet5 - this.offSet4;
			} else if (i == 6) {
				this.offSet6 = this.menuPos.get(i);
				linksInCol6 = this.offSet6 - this.offSet5;
			} else if (i == 7) {
				this.offSet7 = this.menuPos.get(i);
				linksInCol7 = this.offSet7 - this.offSet6;
			} else if (i == 8) {
				this.offSet8 = this.menuPos.get(i);
				linksInCol8 = this.offSet8 - this.offSet7;
			} else if (i == 9) {
				this.offSet9 = this.menuPos.get(i);
				linksInCol9 = this.offSet9 - this.offSet8;
			}
			this.menuCount.add(i, i);
		}
		/*
		 * System.out.println(java.util.Calendar.getInstance().getTime()+
		 * "> setProductrows: i value: "+i);
		 */
		if (i == 2) { // 2 menus
			this.linksInCol2 = lastFunc - this.offSet1;
		} else if (i == 3) { // 3 menus
			this.linksInCol3 = lastFunc - this.offSet2;
		} else if (i == 4) { // 4 menus
			this.linksInCol4 = lastFunc - this.offSet3;
		} else if (i == 5) { // 5 menus
			this.linksInCol5 = lastFunc - this.offSet4;
		} else if (i == 6) { // 6 menus
			this.linksInCol6 = lastFunc - this.offSet5;
		} else if (i == 7) { // 7 menus
			this.linksInCol7 = lastFunc - this.offSet6;
		} else if (i == 8) { // 8 menus
			this.linksInCol8 = lastFunc - this.offSet7;
		} else if (i == 9) { // 9 menus
			this.linksInCol9 = lastFunc - this.offSet8;
		} else if (i == 10) { // 10 menus
			this.linksInCol10 = lastFunc - this.offSet9;
		}
	}
	//
	public String GetParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		// moduleName = "> SysadProdUtil.GetParams: ";
		param = "";
		// prod_id_sel = prodSelector.getProduct_id_sel();
		// prod_menu_id_sel = prodSelector.getProduct_menu_id_sel();
		// prod_menu_func_id_sel = prodSelector.getProduct_menu_func_id_sel();
		//
		// selection to get the rows.
		// ----------------------------------------------------
		String prod_id = "";
		String prod_menu_id = "";
		String prod_menu_func_id = "";

		String prod_param = "";
		String menu_param = "";
		String func_param = "";
		//
		// PrintLog("In get params4 : ");
		if (product_id_sel.equals("ALL")) {
			prod_id = "";
		} else {
			prod_id = product_id_sel.trim();
		}
		//
		// PrintLog("In get params5 : ");
		if (product_menu_id_sel != null) {
			if (product_menu_id_sel.equals("ALL")) {
				prod_menu_id = "";
			} else {
				prod_menu_id = product_menu_id_sel.trim();
			}
		}
		//
		// PrintLog("In get params6: ");
		if (product_menu_func_id_sel != null) {
			if (product_menu_func_id_sel.equals("ALL")) {
				prod_menu_func_id = "";
			} else {
				prod_menu_func_id = product_menu_func_id_sel.trim();
			}
		}
		//
		// PrintLog("In get params7: ");
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!prod_id.equals("")) {
			prod_param = "product_id='" + prod_id + "' ";
		}
		if (!prod_menu_id.equals("")) {
			menu_param = "product_menu_id ='" + prod_menu_id + "' ";
		}
		if (!prod_menu_func_id.equals("")) {
			func_param = "product_func_id ='" + prod_menu_func_id + "' ";
		}
		//
		//
		if (!prod_param.equals("")) {
			param = prod_param;
		}
		if (!param.equals("")) {
			if (!menu_param.equals(""))
				param += " AND " + menu_param;
		} else {
			param = menu_param;
		}
		if (!param.equals("")) {
			if (!func_param.equals(""))
				param += " AND " + func_param;
		} else {
			param = func_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		// PrintLog("In get params7: ");
		param += " ORDER BY product_id, product_menu_id, product_func_id";
		// PrintLog("Param8: "+param);
		return (param);
	}
	//
	public String GetLogParams() {
		//
		// Variables needed to set the parameters to fetch the data
		// --------------------------------------------------------
		// moduleName = "GetLogParams: ";
		param = "";
		// prod_id_sel = prodSelector.getProduct_id_sel();
		// prod_menu_id_sel = prodSelector.getProduct_menu_id_sel();
		// prod_menu_func_id_sel = prodSelector.getProduct_menu_func_id_sel();
		//
		// Set up the user selection criteria based on the user
		// selection to get the rows.
		// ----------------------------------------------------
		String prod_id = "";
		String prod_menu_id = "";
		String prod_menu_func_id = "";

		String prod_param = "";
		String menu_param = "";
		String func_param = "";

		if (!product_id_sel.equals("ALL")) {
			prod_id = product_id_sel.trim();
		}
		if (!product_menu_id_sel.equals("")) {
			prod_menu_id = product_menu_id_sel.trim();
		}
		if (!product_menu_func_id_sel.equals("")) {
			prod_menu_func_id = product_menu_func_id_sel.trim();
		}
		//
		// Here build the SQL for fetching the rows
		// ----------------------------------------
		if (!prod_id.equals("")) {
			prod_param = "prolog_id='" + prod_id + "' ";
		}
		if (!prod_menu_id.equals("")) {
			menu_param = "prolog_menu_id='" + prod_menu_id + "' ";
		}
		if (!prod_menu_func_id.equals("")) {
			func_param = "prolog_func_id='" + prod_menu_func_id + "' ";
		}
		//
		//
		if (!prod_param.equals("")) {
			param = prod_param;
		}
		if (!param.equals("")) {
			if (!menu_param.equals(""))
				param += " AND " + menu_param;
		} else {
			param = menu_param;
		}
		if (!param.equals("")) {
			if (!func_param.equals(""))
				param += " AND " + func_param;
		} else {
			param = func_param;
		}
		if (!param.equals("")) {
			param = " WHERE " + param;
		}
		param += " ORDER BY prolog_id, prolog_menu_id, prolog_func_id";
		return (param);
	}
}
