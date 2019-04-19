package com.ditto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ditto.service.ActionRecordService;
import com.ditto.utils.PageUtils;
import com.ditto.utils.Query;
import com.ditto.utils.R;

@Controller
@RequestMapping("/grid")
public class JqGridController {
	@Autowired
	private ActionRecordService actionService;

	@RequestMapping("/list")
	@ResponseBody
	public R grid(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = actionService.listJqTestData(query);
		int size = actionService.listJqTestDataCount(query);
		PageUtils pageUtil = new PageUtils(list, size, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	@RequestMapping("/del")
	@ResponseBody
	public R del(Integer id) {
		actionService.delJqTestData(id);
		return R.ok();
	}
	
}
