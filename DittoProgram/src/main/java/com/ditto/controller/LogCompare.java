package com.ditto.controller;

import com.ditto.entity.ActionRecordEntity;
import com.ditto.service.ActionRecordService;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/logDeal")
public class LogCompare {
	private static final Logger log = LogManager.getLogger(LogCompare.class);
	private static String prefix = "STATIS count mark start ";
	private static String suffix = " count mark end";

	@Autowired
	private ActionRecordService actionService;
	@RequestMapping("/t")
	@ResponseBody
	public void test() {
		File file = new File("E:\\test");
		File[] files = file.listFiles();
		if (files == null){
			return;
		}
		LineIterator it = null;
		int i = 0;
		for (File file2 : files) {
			if (file2.isDirectory()) {
				continue;
			}
			List<String> list = new ArrayList<>();
			try {
				it = FileUtils.lineIterator(new File(file2.getAbsolutePath()), "UTF-8");
				while (it.hasNext()) {
					String line = it.nextLine();
					if (!line.contains(prefix) || !line.contains(suffix)) { // 对数据过滤
						continue;
					}
					int start = line.indexOf(prefix) + prefix.length();
					int end = line.indexOf(suffix);
					String content ;
					content = line.substring(start, end).replace("\"", ""); // 截取数据内容
					if (!"".equals(content)) {
						i++;
						list.add(content);
						if (list.size() == 10000) {
							getData(list);
							list.clear();
						}
					}
				}
				if (list.size() != 0) {
					getData(list);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		LineIterator.closeQuietly(it);
	}

	private void getData(List<String> list) {
		for (String line : list) {
			try {
				if (!"".equals(line)) {
					String[] keyValue = line.split(","); // 分割
					JsonObject json = new JsonObject();
					for (String str : keyValue) {
						if (str.contains("mac")) { // 由于mac地址中包含有冒号，一次需特殊处理
							String k = str.substring(0, 3);
							String v = str.substring(4);
							json.addProperty(k, v);
						} else {
							String[] kv = str.split(":"); // 分割
							json.addProperty(kv[0], kv[1]); // 拼凑json对象
						}
					}
				}
			} catch (Exception e) {
				log.error("===" + line);
			}
		}

	}

	@RequestMapping("/ipCompare")
	@ResponseBody
	public void ipCompare () {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		int size = 10000;
		Map<String, Object> map = new HashMap<>();
		int total = actionService.queryTotal(map);
		int page = (total % size == 0)?(total/size):total/size + 1;
		
		for (int i = 1; i <=page; i++) {
			int offset = (i-1)* size;
			map.put("tbName", "ip_zj_jk25");
			map.put("offset", offset);
			map.put("limit", size);
			List<ActionRecordEntity> list = actionService.queryList(map);
			synchronized (list) {
				queryIpExist queryIpExist = new queryIpExist(list,map);
				pool.execute(queryIpExist);
			}
		}
	}
	
	class queryIpExist implements Runnable{
		List<ActionRecordEntity> list ;
		Map<String, Object> map ;
		private queryIpExist(List<ActionRecordEntity> list, Map<String, Object> map) {
			this.list = list;
			this.map = map;
		}

		@Override
		public void run() {
			for (ActionRecordEntity action : list) {
				String a = action.getActions().substring(2);
				map.put("action", a);
				log.info(a);
				map.put("tbName", "action_record");
				ActionRecordEntity ar = actionService.queryExist(map);
				if (ar == null) {
					log.error(a);
				}
			}
		}
	}
}
