package com.webfiche.checkpoint;

import java.sql.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.webfiche.checkpoint.actions.*;
import com.webfiche.checkpoint.sysadmin.beans.*;
import com.webfiche.checkpoint.sysadmin.service.*;
import com.webfiche.checkpoint.util.*;

public final class DatabaseServlet extends HttpServlet {
	private static final long       serialVersionUID        = 1L;
	Connection	dbConn;
	int		rowCount			= 0;
	int		tableRows			= 0;
	int		i					= 0;
	int		yearYYYY			= 0;
	int		monthMM				= 0;
	int		dayDD				= 0;
	String	preDate				= "";
	private TreeMap<String, String>	prodNames		 = new TreeMap<String, String>();
	String[]	appLicensed		= {"checkpointin", "checkpointout"};
	String[]	histPrefix		= {"incl_chex_m_", "deps_chex_"};
	String[]	logPrefix		= {"incl_chex_log_", "deps_chexlog_"};
	//
	String	extDelimiter		= "";		// Extract file field separator
	String	fileSeparator		= System.getProperty("file.separator");	// windows "\", Unix "/" etc.
	String	osName				= System.getProperty("os.name");	// Windows, Unix, Mac etc
	//
	String	moduleName;
	// =
	// "> DatabaseServlet: ";
	String	PRODUCT_ID;
	String	applDate;
	String	DEF_CURRENCY;
	String	OUR_ABA;
	String	EOD_FLAG;
	String	BOD_FLAG;
	String	EOD_OPER;
	String	BOD_OPER;
	String	PREV_BIZ_DATE;
	String	NEXT_BIZ_DATE;
	String	stmtPrinter;
	String	fourIsVerify;
	String	prev_year;
	String	year;
	String	nextYear;
	String	prevYearholidays	= null;
	String	currYearholidays	= null;
	String	nextYearholidays	= null;
	String	remBaseDir;
	String	locInputDir;
	String	locOutputDir;
	String	imgBaseDir;
	//
	String	server				= "";
	String	from				= "";
	String	subject				= "";
	String	prodId				= "A";
	//
	String	dbTable				= "";
	String	dbTblCreSql			= "";
	String	dbTblInitSql		= "";
	String	histYM				= "";
	//
	String	dataDir				= "";
	//
	Timestamp	FILE_LOADED_TIME;
	Timestamp	EOD_TIME;
	Timestamp	BOD_TIME;
	Timestamp	ROLLOVER_TIME;
	long		n					= 60;
	long		idx					= 0;
	//
	ArrayList<String>	prod_plist	= new ArrayList<String>();
	ArrayList<String>	hd_plist	= new ArrayList<String>();
	//
	EcontServletContextListener escl = new EcontServletContextListener();
	SysadControlUtil	ctlUtil				= new SysadControlUtil();
	ControlSelector		ctlSelector			= new ControlSelector();
	ControlDetail		ctlDetail			= new ControlDetail();
	DbSelector			dbSelector			= new DbSelector();
	DbDetail			dbDetail			= new DbDetail();
	DbDetail[]			dbRows				= null;
	//
	SysadCalendarUtil	calUtil				= new SysadCalendarUtil();
	SysadProdUtil		prUtil				= new SysadProdUtil();
	SysadDbUtil			dbUtil				= new SysadDbUtil();
	DbCheckTable		dbCheck				= new DbCheckTable();
	//ValiDate			vDate				= new ValiDate();
	//
	CalendarSelector	calSelector_pre		= new CalendarSelector();
	CalendarSelector	calSelector_cur		= new CalendarSelector();
	CalendarSelector	calSelector_next	= new CalendarSelector();
	CalendarDetail		calDetail_pre		= new CalendarDetail();
	CalendarDetail		calDetail_cur		= new CalendarDetail();
	CalendarDetail		calDetail_next		= new CalendarDetail();
	ProductSelector		prodSelector		= new ProductSelector();

	MailClient			mClient				= new MailClient();
	//
	//private BufferedReader inRead;
	//File	findFile						= null;
	//File	strPassFile						= null;

	private void PrintLog(String logMsg) {
		System.out.println(java.util.Calendar.getInstance().getTime() +
							moduleName + logMsg);
	}
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//
		moduleName				= "> DatabaseServlet.init: ";
		String subject			= "eController - Error Initialising System  FAILED TO CONNECT TO DATABASE";
		//
		String driverclassname	= config.getInitParameter("driverclassname");
		String releaseVersion	= config.getInitParameter("releaseVersion");
		String appSchema		= config.getInitParameter("appSchema");
		String dburl			= config.getInitParameter("dburl");
		String uPort			= config.getInitParameter("uPort");
		String user				= config.getInitParameter("user");
		//String password		= config.getInitParameter("password");
		String password			= "";
		String nodename			= config.getInitParameter("nodename");
		String dbused			= config.getInitParameter("dbused");
		String minSizePw		= config.getInitParameter("minSizePw");
		String numPastPw		= config.getInitParameter("numPastPw");
		String numTrysPw		= config.getInitParameter("numTrysPw");
		String bankId			= config.getInitParameter("bankId");
		String fastAxsNum		= config.getInitParameter("fastAxsNum");
		String smtpDomain		= config.getInitParameter("smtpDomain");
		String imagesPerPDF		= config.getInitParameter("imagesPerPDF");
		String checksPerView	= config.getInitParameter("checksPerView");
		String imageURL			= config.getInitParameter("imageURL");
		String fromEmailAddress	= config.getInitParameter("fromEmailAddress");
		String stmtSubject		= config.getInitParameter("stmtSubject");
		String stmtMsgDir		= config.getInitParameter("stmtMsgDir");
		// String fourIsVerify	= config.getInitParameter("fourIsVerify");
		String sysAlertEmail	= config.getInitParameter("sysAlertEmail");
		String extDelimiter		= config.getInitParameter("extDelimiter");
		String batchTiffs		= config.getInitParameter("batchTiffs");
		String batchPgm			= config.getInitParameter("batchPgm");
		String batchForm		= config.getInitParameter("batchForm");
		//String checkTiffs		= config.getInitParameter("checkTiffs");
		//String checkPgm		= config.getInitParameter("checkPgm");
		//String checkForm		= config.getInitParameter("checkForm");
		//String mandateTiffs	= config.getInitParameter("mandateTiffs");
		//String mandatePgm		= config.getInitParameter("mandatePgm");
		//String mandateForm	= config.getInitParameter("mandateForm");
		String contentRoot		= config.getInitParameter("contentRoot");
		String textFiles		= config.getInitParameter("textFiles");
		String cpIn				= config.getInitParameter("checkPointIn");
		String cpOutLbox		= config.getInitParameter("checkPointOutLockBox");
		String cpRemote			= config.getInitParameter("checkPointRemote");
		String flowPoint		= config.getInitParameter("flowPoint");
		String netPoint			= config.getInitParameter("netPoint");
		String infoPoint		= config.getInitParameter("infoPoint");
		String mdPoint			= config.getInitParameter("mdPoint");
		String mandatePoint		= config.getInitParameter("mandatePoint");
		String reportHdg		= config.getInitParameter("reportHdg");
		String debugFlag		= config.getInitParameter("debugFlag");
		String ctx				= System.getProperty("catalina.home") + "/webapps" + 
								  config.getServletContext().getContextPath() + "/";
		String sqlRoot			= ctx + "WEB-INF/db/" + dbused.toLowerCase();
		String webInf			= ctx + "WEB-INF/";
		//String encPassword		= "";
		String daysCheckValid	= config.getInitParameter("daysCheckValid");
		PrintLog("SQL Root " + sqlRoot);
		PrintLog("Starting Database Servlet");
		PrintLog("*****************************************");
		PrintLog("Bank Id          --> " + bankId);
		PrintLog("DRCLASSNAME      --> " + driverclassname);
		PrintLog("DBURL            --> " + dburl);
		PrintLog("User             --> " + user);
		PrintLog("PassWord         --> " + "********");
		// 
		// Here get the password from the File
		//		If file stringPass.cfg is found then read the password from the file & encrypt
		//		and write it back into the file and rename it as encpass.cfg
		//		If file encryptedPass.cfg is found then read the encrypted password, decrypt and use
		//		it to login to database.
		//
		password	= EncryptDecryptString.GetAccess(webInf);
		//PrintLog("Working Directory = " + webInf+" > "+encPassword);
		//
		// Here connect to the database
		//
		try {
			Class.forName(driverclassname);
			dbConn = DriverManager.getConnection(dburl, user, password);
		} catch (Exception c) {
			PrintLog("Exception getting DBCONN");
			PrintLog(" "+c);
		}
		//
		// PrintLog("Next step >>>> 1 ");
		// PrintLog("Got DBCONN MOVING ON");
		//
		// Call CheckIfTableExists. This will check if a table exists, if not it
		// will create it
		// execute an initialisation script if present.
		//
		try {
			rowCount	= dbUtil.GetTableRows(dbConn, dbSelector, dbTable);
			dbRows		= dbSelector.getDbRows();
			if (dbRows == null) {
				// create DbTable and insert table rows
				dbTblCreSql		= sqlRoot + "/sql/sys/DbTables.sql";
				dbTblInitSql	= sqlRoot + "/init/sys/dbtables_init.sql";
				PrintLog("Will initialise table: " + dbTable);
				dbCheck.CheckIfTableExists(dbConn, user.toUpperCase(), dbTable, dbTblCreSql, dbTblInitSql);
				//dbCheck.CheckIfTableExists(dbConn, appSchema, dbTable, dbTblCreSql, dbTblInitSql);
			}
		} catch (Throwable t) {
			dbTable			= "dbtables";
			dbTblCreSql		= sqlRoot + "/sql/sys/DbTables.sql";
			dbTblInitSql	= sqlRoot + "/init/sys/dbtables_init.sql";
			PrintLog("Creating Table: " + dbTable);
			try {
				dbCheck.CheckIfTableExists(dbConn, user.toUpperCase(), dbTable, dbTblCreSql, dbTblInitSql);
				//dbCheck.CheckIfTableExists(dbConn, appSchema, dbTable, dbTblCreSql, dbTblInitSql);
			} catch (Throwable t1) {
				PrintLog("Error Creating Table: " + t1);
				t1.printStackTrace();
			}
		}
		try {
			dbTable		= "";
			rowCount	= dbUtil.GetTableRows(dbConn, dbSelector, dbTable);
			dbRows		= dbSelector.getDbRows();
			// PrintLog("SQL Root: "+sqlRoot);
			for (i = 0; i < dbRows.length; i++) {
				dbTable	= dbRows[i].getDbTable();
				if (dbused.equals("ORACLE")) {
					dbTable	= dbTable.toUpperCase();
				} else {
					dbTable	= dbTable.toLowerCase();
				}
				if (dbTable.toLowerCase().equals("dbtable")) {
					//
				} else {
					dbTblCreSql		= sqlRoot + "/sql/" + dbRows[i].getDbAppl() +
									  "/" + dbRows[i].getDbSql();
					dbTblInitSql	= dbRows[i].getDbInitSql();
					if (!dbTblInitSql.equals(" ")) {
						dbTblInitSql	= sqlRoot + "/init/" +
											dbRows[i].getDbAppl() + "/" +
											dbRows[i].getDbInitSql();
					}
					// PrintLog("Table Name: "+dbTable+" dbTblCreSql: "+dbTblCreSql+
					// " dbTblInitSql: "+dbTblInitSql);
					//PrintLog("Checked for Table Name: " + dbTable);
					//dbCheck.CheckIfTableExists(dbConn, appSchema, dbTable, dbTblCreSql, dbTblInitSql);
					dbCheck.CheckIfTableExists(dbConn, user.toUpperCase(), dbTable, dbTblCreSql, dbTblInitSql);
				}
			}
		} catch (Throwable t) {
			PrintLog("Error Creating Tables2: " + t);
			t.printStackTrace(System.out);
		}

		try {
			// PrintLog("Entering Main DB Servlet");
			// Statement stmt = dbConn.createStatement ();
			ctlDetail.setDbUsed(dbused);
			rowCount		= ctlUtil.GetControlRow(dbConn, ctlSelector, prodId);
			ctlDetail		= ctlSelector.getARow();
			PRODUCT_ID		= ctlDetail.getProductId();
			applDate		= ctlDetail.getApplDate();
			DEF_CURRENCY	= ctlDetail.getDefCurr();
			OUR_ABA			= ctlDetail.getOurAba();
			EOD_FLAG		= ctlDetail.getEodFlag();
			BOD_FLAG		= ctlDetail.getBodFlag();
			EOD_OPER		= ctlDetail.getEodOper();
			BOD_OPER		= ctlDetail.getBodOper();
			PREV_BIZ_DATE	= ctlDetail.getPrevBizDate();
			NEXT_BIZ_DATE	= ctlDetail.getNextBizDate();
			remBaseDir		= ctlDetail.getRemBaseDir();
			locInputDir		= ctlDetail.getLocInputDir();
			locOutputDir	= ctlDetail.getLocOutputDir();
			imgBaseDir		= ctlDetail.getImgBaseDir();
			// PrintLog("APPL DATE        --> "+applDate);
			// PrintLog("EOD_FLAG         --> "+EOD_FLAG);
			// PrintLog("EOD_FLAG         --> "+BOD_FLAG);
			config.getServletContext().setAttribute("CTX", ctx);
			config.getServletContext().setAttribute("FILESEPARATOR", fileSeparator);
			config.getServletContext().setAttribute("OSNAME", osName);
			config.getServletContext().setAttribute("PREVBIZDATE", PREV_BIZ_DATE);
			config.getServletContext().setAttribute("APPLDATE", applDate);
			config.getServletContext().setAttribute("NEXTBIZDATE", NEXT_BIZ_DATE);
			config.getServletContext().setAttribute("DAYSCHECKVALID", daysCheckValid);
			config.getServletContext().setAttribute("DBURL", dburl);
			config.getServletContext().setAttribute("DBDRIVER", driverclassname);
			config.getServletContext().setAttribute("RELEASEVERSION", releaseVersion);
			config.getServletContext().setAttribute("APPSCHEMA", appSchema);
			config.getServletContext().setAttribute("UPORT", uPort);
			config.getServletContext().setAttribute("DBUSER", user);
			config.getServletContext().setAttribute("DBPASS", password);
			config.getServletContext().setAttribute("NODENAME", nodename);
			config.getServletContext().setAttribute("DBUSED", dbused);
			config.getServletContext().setAttribute("MINSIZEPW", minSizePw);
			config.getServletContext().setAttribute("NUMPASTPW", numPastPw);
			config.getServletContext().setAttribute("NUMTRYSPW", numTrysPw);
			config.getServletContext().setAttribute("BANKID", bankId);
			config.getServletContext().setAttribute("OURABA", OUR_ABA);
			config.getServletContext().setAttribute("FASTAXSNUM", fastAxsNum);
			config.getServletContext().setAttribute("SMTPDOMAIN", smtpDomain);
			config.getServletContext().setAttribute("IMAGESPERPDF", imagesPerPDF);
			config.getServletContext().setAttribute("CHECKSPERVIEW", checksPerView);
			config.getServletContext().setAttribute("IMAGEURL", imageURL);
			// config.getServletContext().setAttribute("IMAGES", images);
			// config.getServletContext().setAttribute("MONTIMAGES", montimages);
			config.getServletContext().setAttribute("REMBASEDIR", remBaseDir);
			config.getServletContext().setAttribute("LOCINPUTDIR", locInputDir);
			config.getServletContext().setAttribute("LOCOUTPUTDIR",	locOutputDir);
			config.getServletContext().setAttribute("IMGBASEDIR", imgBaseDir);
			config.getServletContext().setAttribute("FROMEMAILADDRESS",	fromEmailAddress);
			config.getServletContext().setAttribute("STMTSUBJECT", stmtSubject);
			config.getServletContext().setAttribute("STMTMSGDIR", stmtMsgDir);
			config.getServletContext().setAttribute("DEFCURR", DEF_CURRENCY);
			config.getServletContext().setAttribute("EOD_FLAG", EOD_FLAG);
			config.getServletContext().setAttribute("BOD_FLAG", BOD_FLAG);
			config.getServletContext().setAttribute("REPORTHDG", reportHdg);
			config.getServletContext().setAttribute("STMTPRINTER", stmtPrinter);
			/*
			 * config.getServletContext().setAttribute("FOURISVERIFY",
			 * fourIsVerify);
			 */
			config.getServletContext().setAttribute("SYSALERTEMAIL",sysAlertEmail);
			/**/
			config.getServletContext().setAttribute("CPIN", cpIn);
			config.getServletContext().setAttribute("CPOUTLBOX", cpOutLbox);
			config.getServletContext().setAttribute("CPREMOTE", cpRemote);
			config.getServletContext().setAttribute("FLOWPOINT", flowPoint);
			config.getServletContext().setAttribute("NETPOINT", netPoint);
			config.getServletContext().setAttribute("INFOPOINT", infoPoint);
			config.getServletContext().setAttribute("MDPOINT", mdPoint);
			config.getServletContext().setAttribute("MANDATEPOINT", mandatePoint);
			 /**/
			config.getServletContext().setAttribute("EXTDELIMITER", extDelimiter);
			config.getServletContext().setAttribute("BATCHPGM", batchPgm);
			config.getServletContext().setAttribute("BATCHFORM", batchForm);
			config.getServletContext().setAttribute("BATCHTIFFS", batchTiffs);
			config.getServletContext().setAttribute("CONTENTROOT", contentRoot);
			config.getServletContext().setAttribute("TEXTFILES", textFiles);
			config.getServletContext().setAttribute("DEBUGFLAG", debugFlag);
			//
			// Here Get the Holidays for the Current and the Previous Years
			//
			year = applDate.substring(0, 4);
			prev_year	= (Integer.parseInt(year) - 1) + "";
			nextYear	= (Integer.parseInt(year) + 1) + "";
			try {
				rowCount	= calUtil.GetHolidayRows(dbConn, calSelector_pre, prev_year + DEF_CURRENCY);
				calDetail_pre		= calSelector_pre.getArow();
				prevYearholidays	= prev_year + DEF_CURRENCY + calDetail_pre.getHoliday_dates();
				// PrintLog("Prior Year Holidays = "+holidaysPre);
				config.getServletContext().setAttribute("PREVYEARHOLIDAYS",	prevYearholidays);
				//
				rowCount		= calUtil.GetHolidayRows(dbConn, calSelector_cur, year + DEF_CURRENCY);
				calDetail_cur	= calSelector_cur.getArow();
				currYearholidays= year + DEF_CURRENCY + calDetail_cur.getHoliday_dates();
				config.getServletContext().setAttribute("CURRYEARHOLIDAYS",	currYearholidays);
				//
				if (applDate.compareTo(year+"1220")>0) {
					rowCount		= calUtil.GetHolidayRows(dbConn, calSelector_next, 
																nextYear + DEF_CURRENCY);
					if (rowCount!=0) {
						calDetail_next	= calSelector_next.getArow();
						nextYearholidays= nextYear + DEF_CURRENCY + calDetail_next.getHoliday_dates();
						// PrintLog("Current Year Holidays = "+holidaysCur);
						config.getServletContext().setAttribute("NEXTYEARHOLIDAYS",	nextYearholidays);
					}
				}
				//
			} catch (Throwable t) {
				PrintLog("Exception Getting Holiday Rows" + t);
			}
			//
			// Here we introduce the Session Attribute that will carry the
			// Product Ids and Details as a an Array of prodcut table rows.
			//
			prodSelector.setProduct_id_sel("ALL");
			prodSelector.setProduct_menu_id_sel("   ");
			prodSelector.setProduct_menu_func_id_sel("   ");
			prodSelector.setNodeName(nodename);
			try {
				rowCount	= prUtil.GetProductRows(dbConn, prodSelector);
				config.getServletContext().setAttribute("PRODUCTS", prodSelector);
				PrintLog("Loaded " + rowCount + " product rows in session");
				prodNames	= prUtil.GetLProductsNameList(dbConn);
				escl.setProdIDesc(prodNames);
			} catch (Throwable t) {
				PrintLog("Load product errored out " + t);
			}
		} catch (Throwable t) {
			PrintLog("DATABASE SERVLET: Error initialising " + t);
			t.printStackTrace(System.out);
			//
			// Send an alert email to Support staff
			//
			String[] fileNames = {};
			String message = ("This message was generated by eController\n" +
							  "eController failed initialisation\n" +
							  "Please check the log file for details\n");
			try {
				mClient.sendMail(smtpDomain, fromEmailAddress, sysAlertEmail,
									subject, message, fileNames);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		dbTblCreSql		= "";
		dbTblInitSql	= "";
		try {
			// Here check for Monthly Check History and Log Tables
			histYM = applDate.substring(0, 4) + applDate.substring(4, 6);
			yearYYYY	= Integer.parseInt(applDate.substring(0, 4));
			monthMM		= Integer.parseInt(applDate.substring(4, 6));
			dayDD		= Integer.parseInt(applDate.substring(6, 8));
			for (int j= 0; j<7; j++) {
			    preDate	= ValiDate.getPriorDates(monthMM, dayDD, yearYYYY, j);
			    histYM	= preDate.substring(0,6);
			    //System.out.println("Hist YYYYMMDD: " + preDate.substring(0,6));
			    for (i = 0; i < appLicensed.length; i++) {
			    	//dbTable	= "incl_chex_m_" + histYM;
			    	dbTable		= histPrefix[i] + histYM;
			    	if (dbused.equals("ORACLE")) {
			    		dbTable = dbTable.toUpperCase();
			    	}
			    	PrintLog("Checking for Table: " + dbTable);
			    	dbTblCreSql	= sqlRoot + "/sql/" + appLicensed[i] + "/" +
			    			histPrefix[i] + "hist_stock.sql";
			    	PrintLog("db Table Create Sql1: " + dbTblCreSql);
			    	//dbCheck.CheckIfTableExists(dbConn, appSchema, dbTable, dbTblCreSql, dbTblInitSql);
			    	dbCheck.CheckIfTableExists(dbConn, user.toUpperCase(), dbTable, dbTblCreSql, dbTblInitSql);
			    	// dbTable = "incl_chex_log_" + histYM;
			    	dbTable = logPrefix[i] + histYM;
			    	//PrintLog("Table Name5: " + dbTable);
			    	if (dbused.equals("ORACLE")) {
			    		dbTable = dbTable.toUpperCase();
			    	}
			    	PrintLog("Checking for Table: " + dbTable);
			    	dbTblCreSql = sqlRoot + "/sql/" + appLicensed[i] + "/" +
			    			logPrefix[i] + "stock.sql";
			    	PrintLog("db Table Create Sql2: " + dbTblCreSql);
			    	//dbCheck.CheckIfTableExists(dbConn, appSchema, dbTable, dbTblCreSql, dbTblInitSql);
			    	dbCheck.CheckIfTableExists(dbConn, user.toUpperCase(), dbTable, dbTblCreSql, dbTblInitSql);
			    }
			}
			PrintLog("incl application ...database servlet loaded...");
			PrintLog("**********************************************");
			escl.StartServers();
		} catch (Throwable t) {
			PrintLog("Error Creating History Tables: " + t);
			t.printStackTrace(System.out);
			PrintLog("Errors starting eController Application CALL SUPPORT...");
			PrintLog("*******************************************************");
		}
		// Push key values into Spring AppProperties so MVC layer stays in sync
		try {
			org.springframework.web.context.WebApplicationContext springCtx =
				org.springframework.web.context.support.WebApplicationContextUtils
					.getWebApplicationContext(config.getServletContext());
			if (springCtx != null) {
				com.webfiche.checkpoint.config.AppProperties appProps =
					springCtx.getBean(com.webfiche.checkpoint.config.AppProperties.class);
				appProps.setApplDate(applDate);
				appProps.setNodeName(nodename);
				appProps.setDbUsed(dbused);
				appProps.setBankId(bankId);
				appProps.setOurAba(OUR_ABA);
				appProps.setDefCurr(DEF_CURRENCY);
				appProps.setImagesPerPdf(Integer.parseInt(imagesPerPDF));
				appProps.setChecksPerView(Integer.parseInt(checksPerView));
				appProps.setImageUrl(imageURL);
				appProps.setFromEmailAddress(fromEmailAddress);
				appProps.setSysAlertEmail(sysAlertEmail);
				appProps.setSmtpHost(smtpDomain);
				appProps.setDebugFlag(debugFlag);
				appProps.setEodFlag(EOD_FLAG);
				appProps.setBodFlag(BOD_FLAG);
				PrintLog("Spring AppProperties updated from DatabaseServlet");
			}
		} catch (Exception e) {
			PrintLog("Could not update Spring AppProperties: " + e.getMessage());
		}
	} // end of init method
}
