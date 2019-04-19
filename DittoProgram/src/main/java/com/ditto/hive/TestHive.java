package com.ditto.hive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestHive {

	public static void main(String[] args) throws SQLException {
		Connection con = HiveUtil.getHiveConnection();
		Statement smt = con.createStatement();
		String tbname = "test2";
//		operateDDL(smt, tbname);
		/*for (int i = 0; i < 7; i++) {
			loadDateIntoHive(smt,tbname,i);
		}*/
		selectDML(smt, tbname);
	}
	//DDL
	public static void operateDDL(Statement stmt,String name) throws SQLException {
		stmt.execute("drop table if exists " + name);	//删表
		stmt.execute("create table " + name + " ("
				+ "id string, "
				+ "orderNo string, "
				+ "modelId string, "
				+ "channelType string, "
				+ "cityId string, "
				+ "materialId string, "
				+ "type string, "
				+ "version string, "
				+ "spaceCode string, "
				+ "mac string, "
				+ "regionId string, "
				+ "time string, "
				+ "scheduleId string, "
				+ "channelId string, "
				+ "ip string "
				+ ") "
				+ "row format delimited fields terminated by ','" 
				);	//建表
		System.out.println("Create table success!");
	}
	
	//DML
	public static void selectDML(Statement stmt,String name) throws SQLException {
/*		String sql = "select count(1) from " + name + " limit 10";
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1) + "\t");
		}*/
/*		String sql = "select orderNo,modelId,channelType,cityId,materialId,type,version,spaceCode,mac,regionId,scheduleId,channelId,ip"
				+ " from "+ name + " group by orderNo,modelId,channelType,cityId,materialId,type,version,spaceCode,mac,regionId,scheduleId,channelId,ip" 
				+ " limit 10000";*/
/*		String sql = "select channelType,scheduleId,channelId,modelId,version,spaceCode,cityId,regionId,type,count(1) as pv,count(distinct mac,ip) as uv"
				+ " from "+ name + " group by channelType,scheduleId,channelId,modelId,version,spaceCode,cityId,regionId,type" 
				+ " limit 10000";*/
		String sql = "select * from test2 where channelId = 253 limit 10";
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4) + "\t" 
		+ res.getString(5) + "\t" +res.getString(6) + "\t" +res.getString(7) + "\t" +res.getString(8) + "\t" +res.getString(9) + "\t" +res.getString(10) + "\t" +res.getString(11) + "\t");
		}
	}
	
	public static void loadDateIntoHive(Statement stmt,String tbname,int i) {
		String fpath = "'/opt/hadoop/" + "schedule_node172.28.63.119_2018-08-1"+i+".log'";
		String sql = "load data local inpath " + fpath + " into table " + tbname;
		System.out.println(sql);
		try {
			boolean execute = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
