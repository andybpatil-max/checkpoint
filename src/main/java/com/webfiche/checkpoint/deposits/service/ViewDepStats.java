package com.webfiche.checkpoint.deposits.service;

import java.sql.* ;
import java.util.*;
// This class gets View statistics data from database

public class ViewDepStats {
    Connection	 conn1;
    ArrayList <ArrayList<String>> inclLoadStats	= new ArrayList<ArrayList<String>>();
    ArrayList <String>l_incl_load_stats	= new ArrayList<String>();
    ArrayList <String>rowArray		= new ArrayList<String>();
    StringBuffer incl_load_statsSql	= new StringBuffer();
    String	 totalChecks		= "";
    int		 rowCount		= 0;
    String 	 statId			= "";
    // cTor 
    public ViewDepStats(Connection connection){
	this.conn1 = connection;
    }

    //public ArrayList<String> getLoadStats() {
    public ArrayList<ArrayList<String>> getLoadStats() {
	incl_load_statsSql.append("SELECT STAT_ID, STAT_FIELD1, STAT_FIELD2, STAT_FIELD3, ");
	incl_load_statsSql.append("STAT_FIELD4, STAT_FIELD5, STAT_FIELD6, STAT_FIELD7, ");
	incl_load_statsSql.append("STAT_FIELD8, STAT_FIELD9, STAT_FIELD10 ");
	incl_load_statsSql.append("FROM deps_load_stats ORDER BY STAT_ID");
	try {
	    Statement stmt = conn1.createStatement() ;
	    ResultSet rset = stmt.executeQuery(incl_load_statsSql.toString());
	    while (rset.next()) {
		rowCount++;
		//
		//System.out.println(java.util.Calendar.getInstance().getTime()+
		//		   "> viewStatsDb row num: "+rowCount);
		//
		statId	= Integer.toString(rset.getInt("STAT_ID"));
		System.out.println(java.util.Calendar.getInstance().getTime()+
				   "> viewStatsDb: statId "+ statId);
		if (rowCount > 1 && statId.substring(1,4).equals("000")) {
		    System.out.println(java.util.Calendar.getInstance().getTime()+
				       "> viewStatsDb: statId "+ statId);
		    totalChecks	= (Integer.parseInt(rset.getString("STAT_FIELD1").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD2").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD3").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD4").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD5").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD6").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD7").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD8").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD9").trim())) + "";
		    System.out.println(java.util.Calendar.getInstance().getTime()+
				       "> viewStatsDb: Total Checks "+ totalChecks);
		    //inclLoadStats.add(l_incl_load_stats.clone());
		    inclLoadStats.add(l_incl_load_stats);
		    l_incl_load_stats	= new ArrayList<String>();
		} else if (rowCount == 1 && statId.substring(1,4).equals("000")) {
		    System.out.println(java.util.Calendar.getInstance().getTime()+
				       "> viewStatsDb: statId "+ statId);
		    totalChecks	= (Integer.parseInt(rset.getString("STAT_FIELD1").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD2").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD3").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD4").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD5").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD6").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD7").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD8").trim()) +
				   Integer.parseInt(rset.getString("STAT_FIELD9").trim())) + "";
		    System.out.println(java.util.Calendar.getInstance().getTime()+
				       "> viewStatsDb: Total Checks "+ totalChecks);
		}
		rowArray.clear();                             // Clear rowArray.
		rowArray.add(Integer.toString(rset.getInt("STAT_ID")).trim());
		rowArray.add(rset.getString("STAT_FIELD1").trim());
		rowArray.add(rset.getString("STAT_FIELD2").trim());
		rowArray.add(rset.getString("STAT_FIELD3").trim());
		rowArray.add(rset.getString("STAT_FIELD4").trim());
		rowArray.add(rset.getString("STAT_FIELD5").trim());
		rowArray.add(rset.getString("STAT_FIELD6").trim());
		rowArray.add(rset.getString("STAT_FIELD7").trim());
		rowArray.add(rset.getString("STAT_FIELD8"));
		rowArray.add(rset.getString("STAT_FIELD9"));
		rowArray.add(totalChecks);
		//rowArray.add(rset.getString("STAT_FIELD10").trim());
		// Add rowArray to l_incl_load_stats.
		//l_incl_load_stats.add(rowArray.clone());  
		//l_incl_load_stats.add(rowArray);
		inclLoadStats.add(rowArray);
	    }
	    //inclLoadStats.add(l_incl_load_stats.clone());
	    //inclLoadStats.add(l_incl_load_stats);
	    stmt.close();
	    rset.close();
	} catch ( SQLException e) {
	    System.out.println(java.util.Calendar.getInstance().getTime()+
			       "> viewStatsDb: "+ e.toString());
        }
	return inclLoadStats;
	//return l_incl_load_stats ;
    }
}
