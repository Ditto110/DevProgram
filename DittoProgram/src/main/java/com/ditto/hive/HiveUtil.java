package com.ditto.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 获取hive 远程连接
 * 用户是指对hdfs 有操作权限的账户
 * @author SDT14325
 *
 */
public class HiveUtil {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static Connection conn = null;

	public static Connection getHiveConnection() {
		try {
			Class.forName(driverName);
			if (conn == null) {
				conn = DriverManager.getConnection("jdbc:hive2://192.168.52.19:10000/hive", "hadoop", "hadoop");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return conn;
	}
}
