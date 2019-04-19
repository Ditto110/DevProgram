package com.ditto.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ditto.dto.TableHandlerEntity;
import com.ditto.entity.ActionRecordEntity;
import com.ditto.service.ActionRecordService;
import com.ditto.utils.DocUtil;
import com.ditto.utils.R;
import com.mipt.util.fmCommonStat.StatLogUtils;
import com.mipt.util.fmCommonStat.StatQueryUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 */
@Controller
@RequestMapping("/api")
public class DevController {
	private static final Logger LOGGER = LogManager.getLogger(DevController.class);
	@Autowired
	private DocUtil docUtil;
	@Autowired
	private ActionRecordService actionRecord;
	@Autowired
	private ActionRecordService actionService;

	/**
	 * 创建doc文档
	 * @return
	 */
	@RequestMapping("/")
	@ResponseBody
	public R testCreateDoc() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("time", "Joanna");
		dataMap.put("subject", "广告数据结算统计");
		dataMap.put("type", "客户版");
		dataMap.put("area", "全国");
		dataMap.put("totalCpm", "100CPM");
		dataMap.put("castCpm", "90CPM");
		dataMap.put("scDate", "2018-03-10");
		ArrayList<Object> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			HashMap<Object,Object> map = new HashMap<>();
			map.put("date1", i);
			map.put("date2", i);
			map.put("date3", i);
			map.put("date4", i);
			list.add(map);
		}
		dataMap.put("dataList", list);
		docUtil.createDoc(dataMap, "doc", "D:\\yanqiong.doc");
	return R.ok();
	}

	/**
	 *  锁
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/testLockOperate")
	@ResponseBody
	public R lockOperate() throws IOException {
		TableHandlerEntity handlerEntity = new TableHandlerEntity();
		handlerEntity.setTableName("action_record");
		handlerEntity.setAction("write");
		actionRecord.lockOperation(handlerEntity);
		LineIterator it = FileUtils.lineIterator(new File(""),"UTF-8");
		try {
			while (it.hasNext()) {
				String string = it.nextLine();
				LOGGER.info(string);
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
		return R.ok();
	}

	/**
	 * 解锁
	 * @return
	 */
	@RequestMapping("/testUnlockOperate")
	@ResponseBody
	public R unlockOperate() {
		actionRecord.unlockOperation();
		return R.ok();
	}

	/**
	 * read
	 * @return
	 */
	@RequestMapping("/testReadOperate")
	@ResponseBody
	public R readOperate() {
		List<ActionRecordEntity> list = actionRecord.queryList(null);
		LOGGER.info(list);
		return R.ok();
	}

	/**
	 *  探针
	 * @param arrStr
	 * @param name
	 * @param addr
	 * @return
	 */
	@RequestMapping("/conn/{name}/{addr}")
//	@ResponseBody
	public String testConn(String arrStr,@PathVariable("name")String name,@PathVariable("addr") String addr) {
		JSONArray array = JSON.parseArray(arrStr);
		if (array != null) {
			Object object = array.get(0);
			LOGGER.info("==="+object.toString());
		}
		return "index";
	}

	/**
	 * transaction
	 * @return
	 */
	@RequestMapping("/testTrasaction")
	@ResponseBody
	public R testTransaction() {
		actionService.testTrasaction();
		return R.ok();
	}

	/**
	 * dataTable test
	 * @param ma
	 * @return
	 */
	@RequestMapping(value="/testDataTable",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String testDataTable(@RequestParam Map<String, Object> ma) {
		return new JSONObject(ma).toString();
	}

	/**
	 * asyn log
	 * @return
	 */
	@RequestMapping("/testLog")
	@ResponseBody
	public R test(){
		LOGGER.info("testLog");
		StatLogUtils.stat("aaaaaaaaaaaa");
		int count = StatQueryUtils.count(1, new HashMap<>(), new HashMap<>(), new HashMap<>());
		System.out.println(count);
		return R.ok();
	}

}
