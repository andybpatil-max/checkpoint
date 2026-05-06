package com.webfiche.checkpoint.beans;

import java.io.Serializable;
import java.util.Vector;
//import java.security.Principal;

public final class User implements Serializable {
	private static final long serialVersionUID 	= 1L;
	private String	actionFlag			= "";
	private String	applDate			= "";
	private String	dbUsed				= "";
	private String	userId				= "";
	//private String	userName			= "";
	private String	userFName			= "";
	private String	userLName			= "";
	private String	userPass			= "";
	private String	user_pass_expiry_f	= "";
	private String	user_pass_expiry  	= "";
	private String	newPass				= "";
	private String	newVerify			= "";
	private String	nodeName			= " ";
	private String	currentVersion 		= " ";
	private String	userProd			= " ";
	private String	userMenu			= " ";
	private String	currentJSP			= " ";
	private String	currentProd			= " ";
	private String	currentAppl			= " ";
	//
	private int		loginAttempts		= 0;
	Vector<Vector<String>>	errorVec	= new Vector<Vector<String>>();

	//
	public void clearErrors() {
		errorVec.clear();
	}
	//
	public String getApplDate() {
		return (applDate);
	}
	public void setApplDate(String applDate) {
		this.applDate	= applDate;
	}
	//
	public String getActionFlag() {
		return (actionFlag);
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag	= actionFlag;
	}
	//
	public String getdbUsed() {
		return (dbUsed);
	}
	public void setdbUsed(String dbUsed) {
		this.dbUsed		= dbUsed;
	}
	//
	public String getUserId() {
		return (userId);
	}
	public void setUserId(String userId) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
				"> User.setUserId: " + userId + " - " + "User Id Set here");
		this.userId	= userId;
	}
	//
	/*
	public String getUserName() {
		return (userName);
	}
	public void setUserName(String userName) {
		this.userName	= userName;
	}
	*/
	//
	public String getUserFName() {
		return (userFName);
	}
	public void setUserFName(String userFName) {
		this.userFName	= userFName;
	}
	//
	public String getUserLName() {
		return (userLName);
	}
	public void setUserLName(String userLName) {
		this.userLName	= userLName;
	}
	//
	public String getUserPass() {
		return (userPass);
	}
	public void setUserPass(String userPass) {
		this.userPass	= userPass;
	}
	//
	public String getNewPass() {
		return (this.newPass);
	}
	public void setNewPass(String newPass) {
		this.newPass	= newPass;
	}
	//
	public String getNewVerify() {
		return (this.newVerify);
	}
	public void setNewVerify(String newVerify) {
		this.newVerify	= newVerify;
	}
	//
	public String getUser_pass_expiry() {
		return (user_pass_expiry);
	}
	public String getUser_pass_expiry_f() {
		return (user_pass_expiry_f);
	}
	//
	public String getNodeName() {
		return (this.nodeName);
	}
	public void setNodeName(String nodeName) {
		this.nodeName	= nodeName;
	}
	//
	public String getUserProd() {
		return (this.userProd);
	}
	public void setUserProd(String userProd) {
		this.userProd	= userProd;
	}
	//
	public String getUserMenu() {
		return (this.userMenu);
	}
	public void setUserMenu(String userMenu) {
		this.userMenu	= userMenu;
	}
	//
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	//
	public String getCurrentJSP() {
		return (this.currentJSP);
	}
	public void setCurrentJSP(String currentJSP) {
		this.currentJSP	= currentJSP;
	}
	//
	public String getCurrentProd() {
		return (this.currentProd);
	}
	public void setCurrentProd(String currentProd) {
		this.currentProd	= currentProd;
	}
	//
	public String getCurrentAppl() {
		return (this.currentAppl);
	}
	public void setCurrentAppl(String currentAppl) {
		this.currentAppl	= currentAppl;
	}
	//
	public Vector<Vector<String>> getErrorVec() {
		return (this.errorVec);
	}
	public void setErrorVec(String fieldName, String errorString) {
		Vector<String> vecPair	= new Vector<String>();
		vecPair.add(fieldName);
		vecPair.add(errorString);
		this.errorVec.add(vecPair);
	}
	//
	public void setUser_pass_expiry(String user_pass_expiry) {
		if (!user_pass_expiry.equals("") && user_pass_expiry.length() > 7) {
			this.user_pass_expiry_f	= user_pass_expiry.substring(4, 6) + "/";
			this.user_pass_expiry_f += user_pass_expiry.substring(6, 8) + "/";
			this.user_pass_expiry_f += user_pass_expiry.substring(0, 4);
		} else {
			this.user_pass_expiry_f	= user_pass_expiry;
		}
		this.user_pass_expiry	= user_pass_expiry;
	}
	//
	public int getLoginAttempts() {
		return (this.loginAttempts);
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts	= loginAttempts;
	}
	//
	public void reset() {
		this.userId		= null;
		this.userFName	= null;
		this.userLName	= null;
		this.userPass	= null;
		this.newPass	= null;
		this.newVerify	= null;
		if (this.actionFlag == null) {
			;
		} else if (this.actionFlag.equals("Blocked")) {
			this.actionFlag	= "Login";
		}
	}
	//
	public void validate() {
		// String module_name	= "> LoginForm/validate: ";
		errorVec.clear();
		if ((userId == null) || (userId.length() < 1)) {
			// System.out.println(java.util.Calendar.getInstance().getTime()+module_name+"User Name NULL");
			//setErrorVec("userid", "error.field.required");
			this.actionFlag	= "Login";
		}
		if ((userPass == null) || (userPass.length() < 1)) {
			if (this.actionFlag.equals("Logoff")) {
				//
			} else {
				//System.out.println(java.util.Calendar.getInstance().getTime()+module_name+"User UserPass NULL "+actionFlag);
				//setErrorVec("Password", "error.field.required");
				this.actionFlag	= "Login";
			}
		}
	}
}
