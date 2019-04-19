package com.ditto.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ditto.service.ActionRecordService;


@Component
@Aspect
@Order(5)
public class AspectIB {
	@Autowired
	private ActionRecordService actionService;
	
	@Pointcut("execution(* com.ditto.controller.InfobrightController.loadData(..))")
	public void loadDataIntoIB() {}
	
	@Before(value = "loadDataIntoIB()")
	public void beforeLoadingData() {
		System.out.println("11111111");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String tableName = "t_sta_day_ib_" + sdf.format(new Date());
		
		actionService.createIBTable(tableName);
	}
	
	@After(value = "loadDataIntoIB()")
	public void afteLoadingData() {
		System.out.println("222222222");
	}
	
}
