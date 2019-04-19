package com.ditto.controller;

import com.ditto.service.ActionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/ib")
public class InfobrightController {
	@Autowired
	private ActionRecordService actionService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String  testconn() {
		return "hello";
	}
	
	@RequestMapping("/createIBTable")
	@ResponseBody
	public void  createIBTable() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String tableName = "t_sta_day_ib_" + sdf.format(new Date());
		
		actionService.createIBTable(tableName);
	} 
	@SuppressWarnings("static-access")
	@RequestMapping("/loadData")
	@ResponseBody
	public void loadData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String tableName = "t_sta_day_ib_" + sdf.format(new Date());
		ResourceBundle bundle = ResourceBundle.getBundle("init");
		ResourceBundle.clearCache();
		String filePath = bundle.getString("filePath");
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("filePath", filePath);
		actionService.loadData(map);
	}
	
	@RequestMapping("/queryTotal")
	@ResponseBody
	public int queryTotal() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String tableName = "t_sta_day_ib_" + sdf.format(new Date());
		int total = actionService.queryIBTable(tableName);
		return total;
	}
}
