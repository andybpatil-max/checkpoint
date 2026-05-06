 /*
 * Copyright (c) 2003 eSoftLabs, LLC
 * All rights reserved. 
 */
package com.webfiche.checkpoint.service;

import java.sql.*;

/**
 * <strong>DbUtil</strong> is a utility class to create
 * a connection to our sample database.
 * @author <a href="mailto:feedback@esoftlabs.com">Andy Patil</a>
 * @version 1.0
 */
public class DbUtil
{
    static Connection dbConn;
    private static String className	= "> DbUtil.";
    private static String moduleName	= "connectToDb: ";
    public DbUtil() {
    }
    //
    private static void PrintLog (String logMsg) {
	System.out.println (java.util.Calendar.getInstance().getTime()+
			    className+moduleName+logMsg);
    }
    //
    public static java.sql.Connection connectToDb(String db_drname,
						  String db_url,
						  String dbUser,
						  String dbPass) {
	try {
	    //PrintLog("ConnectToDb Driver Name>"+driverName+"< >"+db_drname+"<");
	    //PrintLog("connectToDb Connection name = >"+dbUrl+"< >"+db_url+"<");
	    Class.forName(db_drname);
	    dbConn	= DriverManager.getConnection(db_url, dbUser, dbPass);
	} catch(Exception e) {
	    PrintLog("DbUtil: Unable to load -->" + db_drname);
	    PrintLog("DbUtil: Connection name = " + db_url);
	    e.printStackTrace(System.out);
	}
	return dbConn;
    }
}
