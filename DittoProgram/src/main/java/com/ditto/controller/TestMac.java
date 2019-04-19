package com.ditto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ditto.service.ActionRecordService;

@Controller
@RequestMapping("/checkData")
public class TestMac {
	@Autowired
	private ActionRecordService actionService;
	@RequestMapping("/s")
	@ResponseBody
	public String compareMac() {
		Map<String, Object> mapParam = new HashMap<>();
		List<Object> list = new ArrayList<>();
		mapParam.put("tbName", "new25");
		List<Map<String, Object>> listNow = actionService.queryData(mapParam);
		for (Map<String, Object> map2 : listNow) {
			mapParam.put("tbName", "new24");
			String mac_ip = map2.get("mac_ip").toString();
			mapParam.put("version", map2.get("version").toString());
		/*	mapParam.put("mac", mac_ip.split("_")[0]);
			mapParam.put("ip", mac_ip.split("_")[1]);*/
			mapParam.put("mac_ip", mac_ip);
			Map<String, Object> result = actionService.compareData(mapParam);
			if (result == null) {
				list.add(result);
			}
		}
		System.out.println("=====" + list);
		return "ok";
	}
}
