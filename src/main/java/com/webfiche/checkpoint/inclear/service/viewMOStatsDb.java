package com.webfiche.checkpoint.inclear.service;

import java.sql.*;
import org.springframework.stereotype.Service;
import java.util.*;
import com.webfiche.checkpoint.inclear.beans.*;

// This class gets View statistics data from database

@Service
public class viewMOStatsDb {
	Connection conn1;
	ArrayList<LoadstatsDetail>	statBeans		= new ArrayList<LoadstatsDetail>();
	StringBuffer	statsSql					= new StringBuffer();
	String totalChecks							= "";
	int rowCount								= 0;
	String statId								= "";
	//
	public viewMOStatsDb() {
	}
	//
	public int getLoadStats(Connection connection, LoadstatsSelector statSelector) {
		this.conn1 = connection;
		statsSql.setLength(0);
		statsSql.append("SELECT STAT_ID, STAT_FIELD1, STAT_FIELD2, STAT_FIELD3, ");
		statsSql.append("STAT_FIELD4, STAT_FIELD5, STAT_FIELD6, STAT_FIELD7, ");
		statsSql.append("STAT_FIELD8, STAT_FIELD9, STAT_FIELD10 ");
		statsSql.append("FROM incl_load_stats ORDER BY STAT_ID");
		try {
			statSelector.clearStatRows();
			statBeans.clear();
			System.out.println(java.util.Calendar.getInstance().getTime() +
								"> viewStatsDb: statId " + statsSql);
			Statement stmt = conn1.createStatement();
			ResultSet rset = stmt.executeQuery(statsSql.toString());
			while (rset.next()) {
				LoadstatsDetail statDetail	= new LoadstatsDetail();
				rowCount++;
				statId = Integer.toString(rset.getInt("STAT_ID"));
				//System.out.println(java.util.Calendar.getInstance().getTime() +
				//					"> viewStatsDb: statId " + statId);
				if (rowCount > 1 && statId.substring(1, 4).equals("000")) {
					totalChecks = (Integer.parseInt(rset.getString("STAT_FIELD2").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD3").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD4").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD5").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD6").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD7").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD8").trim()) +
							Integer.parseInt(rset.getString("STAT_FIELD9").trim()) + 
							Integer.parseInt(rset.getString("STAT_FIELD10").trim()))	+ "";
					//System.out.println(java.util.Calendar.getInstance().getTime() +
					//					"> viewStatsDb: Total Checks " + totalChecks);
					statDetail.setTotalChecks(totalChecks);
					statDetail.setNewFile(true);
				} else if (rowCount == 1 && statId.substring(1, 4).equals("000")) {
					totalChecks = (Integer.parseInt(rset.getString("STAT_FIELD2").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD3").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD4").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD5").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD6").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD7").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD8").trim()) +
									Integer.parseInt(rset.getString("STAT_FIELD9").trim()) + 
									Integer.parseInt(rset.getString("STAT_FIELD10").trim()))	+ "";
					//System.out.println(java.util.Calendar.getInstance().getTime() +
					//					"> viewStatsDb: Total Checks " + totalChecks);
					statDetail.setTotalChecks(totalChecks);
					statDetail.setNewFile(true);
				}
				statDetail.setStatId(Integer.toString(rset.getInt("STAT_ID")).trim());
				statDetail.setStatField1(rset.getString("STAT_FIELD1").trim());
				statDetail.setStatField2(rset.getString("STAT_FIELD2").trim());
				statDetail.setStatField3(rset.getString("STAT_FIELD3").trim());
				statDetail.setStatField4(rset.getString("STAT_FIELD4").trim());
				statDetail.setStatField5(rset.getString("STAT_FIELD5").trim());
				statDetail.setStatField6(rset.getString("STAT_FIELD6").trim());
				statDetail.setStatField7(rset.getString("STAT_FIELD7").trim());
				statDetail.setStatField8(rset.getString("STAT_FIELD8"));
				statDetail.setStatField9(rset.getString("STAT_FIELD9"));
				statDetail.setStatField10(rset.getString("STAT_FIELD10"));
				//statDetail.ShowFields();
				statBeans.add(statDetail);
			}
			stmt.close();
			rset.close();
			statSelector.setStatRows(statBeans);
			System.out.println(java.util.Calendar.getInstance().getTime() +
					"> viewStatsDb: Total Beans " + statBeans.size());
		} catch (SQLException e) {
			System.out.println(java.util.Calendar.getInstance().getTime() + 
								"> viewStatsDb: " + e.toString());
			e.printStackTrace();
		}
		System.out.println(java.util.Calendar.getInstance().getTime() +
							"> viewStatsDb: Total Checks " + totalChecks);
		return rowCount;
	}
}
