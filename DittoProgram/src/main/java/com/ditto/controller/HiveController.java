package com.ditto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller()
@RequestMapping("/hive")
public class HiveController {
	@RequestMapping(value = "/conn")
	@ResponseBody
	public String hiveConn() {
		return "";
	}
}
