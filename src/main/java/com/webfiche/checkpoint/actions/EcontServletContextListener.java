package com.webfiche.checkpoint.actions;

import java.util.*;
import java.sql.*;

//
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

//import econtroller.beans.User;
//
public final class EcontServletContextListener implements
		ServletContextListener, ServletContextAttributeListener {

	// private ServletContext context = null;
	private String							   moduleName;
	static private Hashtable<String, String> userSession	= new Hashtable<String, String>();
	static private TreeMap<String, String> prodIDesc		= new TreeMap<String, String>();
	static private Hashtable<String, Connection> dbConns	= new Hashtable<String, Connection>();
	static private String	prevBizDate		= "";
	static private String	applDate		= "";
	static private String	nextBizDate		= "";
	static private String	daysCheckValid	= "";
	static private String	ctx				= "";
	static private String	releaseVersion	= "";
	static private String	appSchema		= "";
	static private String	dbUrl			= "";
	static private String	dbDriver		= "";
	static private String	dbUser			= "";
	static private String	dbPass		 	= "";
	static private String	uPort			= "";
	static private String	nodeName		= "";
	static private String	dbUsed			= "";
	static private String	ftpGateway		= "";
	static private String	wfCgi			= "";
	static private String	minSizePw		= "";
	static private String	numPastPw		= "";
	static private String	numTrysPw		= "";
	static private String	bankId			= "";
	static private String	ourAba			= "";
	static private String	defCurr			= "";
	static private String	fileLoaded		= "";
	static private String	eodFlag			= "";
	static private String	bodFlag			= "";
	static private String	interfaceF8		= "";
	static private String	interfaceF10	= "";
	static private String	fastAxsNum		= "";
	static private String	smtpDomain		= "";
	static private String	imagesPerPDF	= "";
	static private String	checksPerView	= "";
	static private String	imageURL		= "";
	static private String	stmtSubject		= "";
	static private String	stmtMsgDir		= "";
	static private String	reportHdg		= "";
	static private String	stmtPrinter		= "";
	static private String	fourIsVerify	= "";
	static private String	sysAlertEmail	= "";
	// Products turned on/off
	static private String	cpIn			= "Y";
	static private String	cpOutLbox		= "Y";
	//static private String	cpOutRDC		= "Y";
	static private String	cpRemote		= "N";
	static private String	flowPoint		= "";
	static private String	netPoint		= "";
	static private String	infoPoint		= "";
	static private String	mdPoint			= "";
	static private String	mandatePoint	= "N";
	//
	static private String	remBaseDir		= "";
	static private String	locInputDir		= "";
	static private String	locOutputDir	= "";
	static private String	imgBaseDir		= "";
	static private String	fromEmailAddress= "";
	static private String	prevYearholidays= "";
	static private String	currYearholidays= "";
	static private String	nextYearholidays= "";
	static private String	extDelimiter	= "";
	static private String	batchPgm		= "";
	static private String	batchForm		= "";
	static private String	batchTiffs		= "";
	static private String	contentRoot		= "";
	static private String	textFiles		= "";
	static private String	debugFlag		= "";
	static private String	fileSeparator	= "";	// windows
	//
	static private String	osName			= "";	// Windows,
	//
	// static private String	testStr		= "initial value";
	// private Map m = Collections.synchronizedMap(new HashTable());
	//
	Thread inclFileServer		= new Thread();
	Thread rdcIclServer			= new Thread();
	Thread historyICLServer		= new Thread();
	//
	public String				getUserName = null;
	//
	public EcontServletContextListener() {
	}
	//
	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> ServletContextListener."+ moduleName + logMsg);
	}
	//
	// This method is invoked when the Web Application is ready to service
	// requests
	// Context Listener Event Methods
	//
	public void contextInitialized(ServletContextEvent event) {
		moduleName = "contextInitialized: ";
		// this.context = event.getServletContext();
		PrintLog("eController UP and RUNNING ");
		//
		// Here Spawn the Interface Jobs
		//
		//StartServers(event);
		//StartServers();
	}
	//public void StartServers (ServletContextEvent event) {
	public void StartServers () {
		moduleName = "StartServers: ";
		if (cpIn.equals("Y")) {
			//eFileServer.start();
			//PrintLog("Started eFileServer");
			//
			//InclICLFileServer.start();
			inclFileServer.setDaemon(true);
			inclFileServer.start();
			PrintLog("Started FRB Icl File Server");
			//
			PrintLog("Posipay upload and PDF generation handled by Spring services");
		}
		if (cpOutLbox.equals("Y")) {
			rdcIclServer.setDaemon(true);
			rdcIclServer.start();
			PrintLog("Started Deposits ICL File Server");
			//lboxIclServer.start();
			//PrintLog("Started LBOX ICL File Server");
			historyICLServer.setDaemon(true);
			historyICLServer.start();
			PrintLog("Started History ICL File Server");
			//payerFilePrepServer.start();
			//PrintLog("Started Lockbox Payer File Prep Server");
			PrintLog("Lockbox Payer File Server handled by Spring services");
		}
		if (cpRemote.equals("Y")) {
			//capsFileServer.start();
			//PrintLog("Started RDC File Server");
		}
		/*
		if (mandatePoint.equals("Y")) {
			manFileServer.start();
			PrintLog("Started Mandate File Server");
		}
		*/
		if (infoPoint.equals("Y")) {
			// Does not need a stop
			//ipCleanup.start();
			PrintLog("Started InfoPointCleanup");
		}
	}
	// This method is invoked when the Web Application has been removed
	// and is no longer able to accept requests
	public void contextDestroyed(ServletContextEvent event) {
		moduleName = "contextDestroyed: ";
		// Output a simple message to the server's console
		PrintLog("Started eController Server SHUT DOWN Procedure");
		PrintLog("Stopping FRB Icl File Server");
		//InclICLFileServer.stopInclFRBF();
		//InclICLFileServer.interrupt();
		//inclFileServer.stopInclFRBF();
		inclFileServer.interrupt();
		//try {
		//	inclFileServer.join(1000);
		//} catch (InterruptedException e) {
		//	// Do Nothing
		//}
		PrintLog("Posipay upload and PDF generation handled by Spring services");
		if (cpOutLbox.equals("Y")) {
			PrintLog("Stopping Deposits ICL File Server");
			//rdcIclServer.stopIclSrv();
			rdcIclServer.interrupt();
			//try {
			//	rdcIclServer.join(1000);
			//} catch (InterruptedException e) {
			//	// Do Nothing
			//}
			PrintLog("Lockbox Payer File Server handled by Spring services");
			PrintLog("Stopping History ICL File Server");
			//historyICLServer.stopHistoryIclSrv();
			historyICLServer.interrupt();
			//try {
			//	historyICLServer.join(1000);
			//} catch (InterruptedException e) {
			//	// Do Nothing
			//}
		}
		//try {
		//	Thread.sleep(10000);
		//} catch (InterruptedException e) {
		//	// Do Nothing
		//}
		PrintLog("eController SHUT DOWN");
		// this.context = null;
	}
	//
	// Methods that return the Context attributes
	//
	public String getPrevBizDate() {
		return (prevBizDate);
	}
	public String getApplDate() {
		return (applDate);
	}
	public String getNextBizDate() {
		return (nextBizDate);
	}
	//
	public String getDaysCheckValid() {
		return (daysCheckValid);
	}
	public void setDaysCheckValid(String daysChkValid) {
		daysCheckValid	= daysChkValid;
		PrintLog("daysCheckValid: " + daysCheckValid);
	}
	//
	public String getReleaseVersion() {
		return (releaseVersion);
	}
	public String getAppSchema() {
		return (appSchema);
	}
	public String getCtx() {
		return (ctx);
	}
	public String getDbUrl() {
		return (dbUrl);
	}
	public String getDbDriver() {
		return (dbDriver);
	}
	public String getDbUser() {
		return (dbUser);
	}
	public String getDbPass() {
		return (dbPass);
	}
	public String getUPort() {
		return (uPort);
	}
	public String getNodeName() {
		return (nodeName);
	}
	public String getDbUsed() {
		return (dbUsed);
	}
	public String getFtpGateway() {
		return (ftpGateway);
	}
	public String getWfCgi() {
		return (wfCgi);
	}
	public String getMinSizePw() {
		return (minSizePw);
	}
	public String getNumPastPw() {
		return (numPastPw);
	}
	public String getNumTrysPw() {
		return (numTrysPw);
	}
	public String getBankId() {
		return (bankId);
	}
	public String getOurAba() {
		return (ourAba);
	}
	public String getDefCurr() {
		return (defCurr);
	}
	public String getFileLoaded() {
		return (fileLoaded);
	}
	public String getEodFlag() {
		return (eodFlag);
	}
	public String getBodFlag() {
		return (bodFlag);
	}
	public String getInterfaceF8() {
		return (interfaceF8);
	}
	public String getInterfaceF10() {
		return (interfaceF10);
	}
	public String getPrevYearHolidays() {
		return (prevYearholidays);
	}
	public String getCurrYearHolidays() {
		return (currYearholidays);
	}
	public String getNextYearHolidays() {
		return (nextYearholidays);
	}
	public String getFastAxsNum() {
		return (fastAxsNum);
	}
	public String getSmtpDomain() {
		return (smtpDomain);
	}
	public String getImagesPerPDF() {
		return (imagesPerPDF);
	}
	public String getChecksPerView() {
		return (checksPerView);
	}
	public String getImageURL() {
		return (imageURL);
	}
	public String getReportHdg() {
		return (reportHdg);
	}
	/*
	 * public String getImages () { return (images); } public String
	 * getMontimages () { return (montimages); }
	 */
	public String getRemBaseDir() {
		return (remBaseDir);
	}
	public String getLocInputDir() {
		return (locInputDir);
	}
	public String getLocOutputDir() {
		return (locOutputDir);
	}
	public String getImgBaseDir() {
		return (imgBaseDir);
	}
	public String getFromEmailAddress() {
		return (fromEmailAddress);
	}
	public String getStmtSubject() {
		return (stmtSubject);
	}
	public String getStmtMsgDir() {
		return (stmtMsgDir);
	}
	public String getStmtPrinter() {
		return (stmtPrinter);
	}
	public String getFourIsVerify() {
		return (fourIsVerify);
	}
	public String getSysAlertEmail() {
		return (sysAlertEmail);
	}
	public String getExtDelimiter() {
		return (extDelimiter);
	}
	public String getBatchPgm() {
		return (batchPgm);
	}
	public String getBatchForm() {
		return (batchForm);
	}
	public String getBatchTiffs() {
		return (batchTiffs);
	}
	public String getContentRoot() {
		return (contentRoot);
	}
	public String getTextFiles() {
		return (textFiles);
	}
	public String getDebugFlag () {
		return (debugFlag);
	}
	/*
	public String getTestStr() {
		return (testStr);
	}
	public void setTestStr(String testStr) {
		testStr = testStr;
	}
	*/
	//
	// Attribute Listener Event methods
	//
	public void attributeAdded(ServletContextAttributeEvent event) {
		moduleName		= "attributeAdded: ";
		String attrName	= event.getName();
		// String attrValue = (String) event.getValue();
		// PrintLog(attrName +" and Value = "+attrValue);
		if (attrName.equals("RELEASEVERSION")) {
			releaseVersion		= (String) event.getValue();
			PrintLog("RELEASEVERSION\t= " + releaseVersion);
		} else if (attrName.equals("APPSCHEMA")) {
			appSchema			= (String) event.getValue();
			PrintLog("APPSCHEMA\t\t= " + appSchema);
		} else if (attrName.equals("DBURL")) {
			dbUrl				= (String) event.getValue();
			PrintLog("DBURL\t\t= " + dbUrl);
		} else if (attrName.equals("PREVBIZDATE")) {
			prevBizDate			= (String) event.getValue();
			PrintLog("PREVBIZDATE\t= " + prevBizDate);
		} else if (attrName.equals("APPLDATE")) {
			applDate			= (String) event.getValue();
			PrintLog("APPLDATE\t\t= " + applDate);
		} else if (attrName.equals("NEXTBIZDATE")) {
			nextBizDate			= (String) event.getValue();
			PrintLog("NEXTBIZDATE\t= " + nextBizDate);
		} else if (attrName.equals("DAYSCHECKVALID")) {
			daysCheckValid		= (String) event.getValue();
			PrintLog("DAYSCHECKVALID\t= " + daysCheckValid);
		} else if (attrName.equals("CTX")) {
			ctx					= (String) event.getValue();
			PrintLog("CTX\t\t= " + ctx);
		} else if (attrName.equals("DBDRIVER")) {
			dbDriver			= (String) event.getValue();
			PrintLog("DBDRIVER\t\t= " + dbDriver);
		} else if (attrName.equals("DBUSER")) {
			dbUser				= (String) event.getValue();
			PrintLog("DBUSER\t\t= " + dbUser);
		} else if (attrName.equals("DBPASS")) {
			dbPass				= (String) event.getValue();
			PrintLog("DBPASS\t\t= " + "********");
		} else if (attrName.equals("UPORT")) {
			uPort				= (String) event.getValue();
			PrintLog("UPORT\t\t= " + uPort);
		} else if (attrName.equals("NODENAME")) {
			nodeName			= (String) event.getValue();
			PrintLog("NODENAME\t\t= " + nodeName);
		} else if (attrName.equals("DBUSED")) {
			dbUsed				= (String) event.getValue();
			PrintLog("DBUSED\t\t= " + dbUsed);
		} else if (attrName.equals("FTPGATEWAY")) {
			ftpGateway			= (String) event.getValue();
			PrintLog("FTPGATEWAY\t= " + ftpGateway);
		} else if (attrName.equals("REPORTHDG")) {
			reportHdg			= (String) event.getValue();
			PrintLog("REPORTHD\t\t= " + reportHdg);
		} else if (attrName.equals("MINSIZEPW")) {
			minSizePw			= (String) event.getValue();
			PrintLog("MINSIZEPW\t\t= " + minSizePw);
		} else if (attrName.equals("NUMPASTPW")) {
			numPastPw			= (String) event.getValue();
			PrintLog("NUMPASTPW\t\t= " + numPastPw);
		} else if (attrName.equals("NUMTRYSPW")) {
			numTrysPw			= (String) event.getValue();
			PrintLog("NUMTRYSPW\t\t= " + numTrysPw);
		} else if (attrName.equals("BANKID")) {
			bankId				= (String) event.getValue();
			PrintLog("BANKID\t\t= " + bankId);
		} else if (attrName.equals("OURABA")) {
			ourAba				= (String) event.getValue();
			PrintLog("OURABA\t\t= " + ourAba);
		} else if (attrName.equals("DEFCURR")) {
			defCurr				= (String) event.getValue();
			PrintLog("DEFCURR\t\t= " + defCurr);
		} else if (attrName.equals("WFCGI")) {
			wfCgi				= (String) event.getValue();
			PrintLog("WFCGI\t\t= " + wfCgi);
		} else if (attrName.equals("FILE_LOADED")) {
			fileLoaded			= (String) event.getValue();
			PrintLog("FILE_LOADED\t= " + fileLoaded);
		} else if (attrName.equals("EOD_FLAG")) {
			eodFlag				= (String) event.getValue();
			PrintLog("EOD_FLAG\t\t= " + eodFlag);
		} else if (attrName.equals("BOD_FLAG")) {
			bodFlag				= (String) event.getValue();
			PrintLog("BOD_FLAG\t\t= " + bodFlag);
		} else if (attrName.equals("PDF_DIR")) {
			interfaceF8			= (String) event.getValue();
			PrintLog("INTERFACE_F8\t= " + interfaceF8);
		} else if (attrName.equals("IMAGE_DIR")) {
			interfaceF10		= (String) event.getValue();
			PrintLog("INTERFACE_F10\t= " + interfaceF10);
		} else if (attrName.equals("PREVYEARHOLIDAYS")) {
			prevYearholidays	= (String) event.getValue();
			PrintLog("PREHOLIDAYS\t= " + prevYearholidays);
		} else if (attrName.equals("CURRYEARHOLIDAYS")) {
			currYearholidays	= (String) event.getValue();
			PrintLog("CURHOLIDAYS\t= " + currYearholidays);
		} else if (attrName.equals("NEXTYEARHOLIDAYS")) {
			nextYearholidays	= (String) event.getValue();
			PrintLog("NEXTHOLIDAYS\t= " + nextYearholidays);
		} else if (attrName.equals("FASTAXSNUM")) {
			fastAxsNum			= (String) event.getValue();
			PrintLog("FASTAXSNUM\t\t= " + fastAxsNum);
		} else if (attrName.equals("SMTPDOMAIN")) {
			smtpDomain			= (String) event.getValue();
			PrintLog("SMTPDOMAIN\t\t= " + smtpDomain);
		} else if (attrName.equals("IMAGESPERPDF")) {
			imagesPerPDF		= (String) event.getValue();
			PrintLog("IMAGESPERPDF\t= " + imagesPerPDF);
		} else if (attrName.equals("CHECKSPERVIEW")) {
			checksPerView		= (String) event.getValue();
			PrintLog("CHECKSPERVIEW\t= " + checksPerView);
		} else if (attrName.equals("IMAGEURL")) {
			imageURL			= (String) event.getValue();
			PrintLog("IMAGEURL\t\t= " + imageURL);
		} else if (attrName.equals("REMBASEDIR")) {
			remBaseDir			= (String) event.getValue();
			PrintLog("REMBASEDIR\t\t= " + remBaseDir);
		} else if (attrName.equals("LOCINPUTDIR")) {
			locInputDir			= (String) event.getValue();
			PrintLog("LOCINPUTDIR\t= " + locInputDir);
		} else if (attrName.equals("LOCOUTPUTDIR")) {
			locOutputDir		= (String) event.getValue();
			PrintLog("LOCOUTPUTDIR\t= " + locOutputDir);
		} else if (attrName.equals("IMGBASEDIR")) {
			imgBaseDir			= (String) event.getValue();
			PrintLog("IMGBASEDIR\t\t= " + imgBaseDir);
		} else if (attrName.equals("FROMEMAILADDRESS")) {
			fromEmailAddress	= (String) event.getValue();
			PrintLog("FROMEMAILADDRESS\t= " + fromEmailAddress);
		} else if (attrName.equals("STMTSUBJECT")) {
			stmtSubject			= (String) event.getValue();
			PrintLog("STMTSUBJECT\t= " + stmtSubject);
		} else if (attrName.equals("STMTMSGDIR")) {
			stmtMsgDir			= (String) event.getValue();
			PrintLog("STMTMSGDIR\t\t= " + stmtMsgDir);
		} else if (attrName.equals("STMTPRINTER")) {
			stmtPrinter			= (String) event.getValue();
			PrintLog("STMTPRINTER\t= " + stmtPrinter);
		} else if (attrName.equals("FOURISVERIFY")) {
			fourIsVerify		= (String) event.getValue();
			PrintLog("FOURISVERIFY\t= " + fourIsVerify);
		} else if (attrName.equals("SYSALERTEMAIL")) {
			sysAlertEmail		= (String) event.getValue();
			PrintLog("SYSALERTEMAIL\t= " + sysAlertEmail);
		} else if (attrName.equals("CPIN")) {
			cpIn				= (String) event.getValue();
			PrintLog("cpIn\t\t= " + cpIn);
		} else if (attrName.equals("CPOUTLBOX")) {
			cpOutLbox			= (String) event.getValue();
			PrintLog("cpOut\t\t= " + cpOutLbox);
		} else if (attrName.equals("CPREMOTE")) {
			cpRemote			= (String) event.getValue();
			PrintLog("cpRemote\t\t= " + cpRemote);
		} else if (attrName.equals("FLOWPOINT")) {
			flowPoint			= (String) event.getValue();
			PrintLog("flowPoint\t\t= " + flowPoint);
		} else if (attrName.equals("NETPOINT")) {
			netPoint			= (String) event.getValue();
			PrintLog("netPoint\t\t= " + netPoint);
		} else if (attrName.equals("INFOPOINT")) {
			infoPoint			= (String) event.getValue();
			PrintLog("infoPoint\t\t= " + infoPoint);
		} else if (attrName.equals("MDPOINT")) {
			mdPoint				= (String) event.getValue();
			PrintLog("mdPoint\t\t= " + mdPoint);
		} else if (attrName.equals("MANDATEPOINT")) {
			mandatePoint		= (String) event.getValue();
			PrintLog("mandatePoint\t= " + mandatePoint);
		} else if (attrName.equals("EXTDELIMITER")) {
			extDelimiter		= (String) event.getValue();
			PrintLog("extDelimiter\t= " + extDelimiter);
		} else if (attrName.equals("FILESEPARATOR")) {
			fileSeparator		= (String) event.getValue();
			PrintLog("FILESEPARATOR\t= " + fileSeparator);
		} else if (attrName.equals("OSNAME")) {
			osName				= (String) event.getValue();
			PrintLog("OSNAME\t\t= " + osName);
		} else if (attrName.equals("BATCHPGM")) {
			batchPgm			= (String) event.getValue();
			PrintLog("BATCHPGM\t\t= " + batchPgm);
		} else if (attrName.equals("BATCHFORM")) {
			batchForm			= (String) event.getValue();
			PrintLog("BATCHFORM\t\t= " + batchForm);
		} else if (attrName.equals("BATCHTIFFS")) {
			batchTiffs			= (String) event.getValue();
			PrintLog("BATCHTIFFS\t\t= " + batchTiffs);
		} else if (attrName.equals("CONTENTROOT")) {
			contentRoot			= (String) event.getValue();
			PrintLog("CONTENTROOT\t= " + contentRoot);
		} else if (attrName.equals("TEXTFILES")) {
			textFiles			= (String) event.getValue();
			PrintLog("TEXTFILES\t= " + textFiles);
		} else if (attrName.equals("DEBUGFLAG")) {
			debugFlag			= (String) event.getValue();
			PrintLog("DEBUGFLAG\t\t= " + debugFlag);
		} else if (attrName.equals("PRODUCTS")) {
			//textFiles	= (String) event.getValue();
			PrintLog("PRODUCTS\t\t= ProductSelector");
		} else {
			PrintLog("Attribute Name\t= " + attrName);
			//PrintLog("event.getValue: " + event.getValue());
		}
	}
	//
	public void attributeRemoved(ServletContextAttributeEvent event) {
		moduleName		= "attributeRemoved: ";
		String attrName	= event.getName();
		if (attrName.equals("DBURL")) {
			String attrValue	= (String) event.getValue();
			PrintLog("DBURL	= " + attrValue);
		}
	}
	//
	public void attributeReplaced(ServletContextAttributeEvent event) {
		moduleName	= "attributeReplaced. ";
	}
	//
	// Product Id/Description Methods
	//
	public void setProdIDesc(TreeMap<String, String> p_prodIDesc) {
		moduleName = "setProdIDesc: ";
		String temp	= "";
		prodIDesc	= p_prodIDesc;
        Set<String> prods = prodIDesc.keySet();
        Iterator<String> i = prods.iterator();
		PrintLog("Licensed Products:");
		while(i.hasNext()) {
		    temp = (String) i.next();
			//System.out.println(temp + ": " + prodIDesc.get(temp));
			PrintLog(temp + "\t" + prodIDesc.get(temp));
		}
	}
	//
	public String getProdId(String prodDesc) {
		moduleName	= "getProdId: ";
		String temp	= "";
		String desc	= "";
		PrintLog("Get Product Id for Product: " + prodDesc);
        Set<String> prods = prodIDesc.keySet();
        Iterator<String> i = prods.iterator();
		while(i.hasNext()) {
		    temp	= (String) i.next();
		    desc	= prodIDesc.get(temp);
		    if (desc.indexOf(prodDesc,0) > -1) {
			    System.out.println(temp + ": " + prodIDesc.get(temp));
				return (temp);
		    } else {
		    	temp	= "";
		    }
		}
		return (temp);
	}
	//
	public String getProdDesc(String prodId) {
		moduleName	= "getProdDesc: ";
		String temp	= "";
		PrintLog("Get Product Description for Product: " + prodId);
		if (prodIDesc.containsKey(prodId)) {
			temp	= prodIDesc.get(prodId);
			PrintLog("Found Product Description: " + temp);
		}
		return (temp);
	}
	//
	// Session Handling Methods
	//
	public void setUserSession(String userId, String sessionId) {
		moduleName = "setUserSession: ";
		userSession.put(sessionId, userId);
		PrintLog("Added User: " + userId + " for session " + sessionId);
	}
	//
	// Add the dbConn to the hash table for releasing the locks on
	// timeout/logout
	//
	public void setDbConnection(Connection conn1, String sessionId) {
		moduleName = "setDbConnection: ";
		dbConns.put(sessionId, conn1);
		PrintLog("Added DbConn for session: " + sessionId);
	}
	//
	//
	public boolean userSessionExists(String userId, String sessionId) {
		moduleName = "userSessionExists: ";
		PrintLog("Look For userId: " + userId);
		if (userSession.containsValue(userId)) {
			PrintLog("Found userId: " + userId);
			String temp = "";
			PrintLog("Session Hash Size " + userSession.size());
			for (Enumeration<String> e = userSession.keys(); e.hasMoreElements();) {
			//Enumeration<String> e = userSession.keys();
			//while (e.hasMoreElements()) {
				temp = e.nextElement().toString();
				PrintLog("SessionID: " + temp + " UserId: " + userSession.get(temp));
			}
			//setUserSession(userId, sessionId);
			return (true);
		} else {
			setUserSession(userId, sessionId);
			return (false);
		}
	}
	//
	//
	public String getExistingId(String userId) {
		moduleName = "getExistingId: ";
		String temp = "";
		PrintLog("Get Id for userId: " + userId);
		if (userSession.containsValue(userId)) {
			PrintLog("Found userId: " + userId);
			PrintLog("Session Hash Size " + userSession.size());
			for (Enumeration<String> e = userSession.keys(); e.hasMoreElements();) {
				temp = e.nextElement().toString();
				PrintLog("SessionID: " + temp + " Userid: " + userSession.get(temp));
			}
		}
		return (temp);
	}
	//
	//
	public String getExistingUser(String sessId) {
		moduleName = "getExistingUser: ";
		String temp = "";
		PrintLog("Get userId for Session Id: " + sessId);
		if (userSession.containsKey(sessId)) {
			temp = userSession.get(sessId);
			PrintLog("Found Session: " + sessId + " User name " + temp);
		}
		return (temp);
	}
	//
	//
	public void removeUserSession(String userId, String sessionId) {
		/* New Stuff */
		/*
		moduleName = "removeUserSession: ";
		String userIdArg	= userId;
		String userId1		= userId;
		String temp			= "";
		PrintLog("Removing session1: " + sessionId + " for " + userIdArg);
		userSession.remove(sessionId);
		//String sessId		= "";
		Connection dbConn	= null;
		Enumeration<String> e = userSession.keys();
		//for (Enumeration<String> e = userSession.keys(); e.hasMoreElements();) {
		while (e.hasMoreElements()) {
			temp = e.nextElement().toString();
			userId1 = userSession.get(temp);
			PrintLog("SessionID: " + temp + " UserId: " + userId1 + " User Id Arg: " + userIdArg);
			dbConn = dbConns.get(temp);
			if ((temp != "") && (userId1 != "")) {
				if (dbConn != null) {
					PrintLog("Will Remove Db Connection: " + dbConn);
					try {
						dbConn.commit();
					} catch (Throwable t) {
						PrintLog("Error Rolling Back");
					}
					dbConns.remove(temp);
					PrintLog("Removed Db Connection: " + dbConn);
				}
				PrintLog("Removing session2: " + temp + " for " + userIdArg);
				userSession.remove(temp);
				//PrintLog("Removed userId: " + userIdArg);
			}
		}
		 */
		// Original
		/**/
		moduleName = "removeUserSession: ";
		//String userId1 = userSession.get(sessionId);
		PrintLog("SessionID: " + sessionId + " UserId: " + userId);
		Connection dbConn = dbConns.get(sessionId);
		if (userId != null) {
			try {
				dbConn.commit();
			} catch (Throwable t) {
				PrintLog("Error Rolling Back" + t);
			}
			dbConns.remove(userId);
			PrintLog("Removed Db Connection: " + dbConn);
			userSession.remove(sessionId);
			PrintLog("Removed userId: " + userId);
		}
		/**/
		/*
		 Enumeration<String> enumeration = ht.keys();

        // iterate using enumeration object
        while(enumeration.hasMoreElements()) {

           String key = enumeration.nextElement();
           System.out.println("Country : "  + key 
                   + "\t\t President : "  + ht.get(key));
        }

		 */
		
	}
	//
	public void removeDbConnection(Connection conn, String sessionId) {
		moduleName = "removeDbConnection: ";
		PrintLog("SessId = " + sessionId);
		// if (sessId != null) {
		Connection dbConn = dbConns.get(sessionId);
		PrintLog("dbConn = " + dbConn);
		if (dbConn != null) {
			PrintLog("Will Commit");
			try {
				dbConn.commit();
			} catch (Throwable t) {
				// PrintLog("Error Rolling Back");
			}
		}
		dbConns.remove(sessionId);
		PrintLog("Removed DbConnection: " + dbConn);
		// }
	}
}
