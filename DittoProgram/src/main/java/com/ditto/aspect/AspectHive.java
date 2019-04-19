package com.ditto.aspect;

import com.ditto.service.ActionRecordService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@Component
@Aspect
@Order(1)
public class AspectHive {
	
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	@Autowired
	private ActionRecordService actionService;
	
	@Pointcut("execution(* com.ditto.controller.InfobrightController.loadData(..))")
	public void loadDataIntoIB() {}
	
	@Before(value = "loadDataIntoIB()")
	public void beforeLoadingData() throws SQLException {
		System.out.println("11111111");
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		Connection con = DriverManager.getConnection("jdbc:hive2://192.168.52.19:10000/hive", "hadoop", "hadoop");
		Statement stmt = con.createStatement();
		String sql = "insert overwrite local directory '' select * ";
		
	}
	
	@After(value = "loadDataIntoIB()")
	public void afteLoadingData() {
		System.out.println("222222222");
	}
	
}
