package com.ditto.test.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.ditto.utils.DocUtil;
@Controller
public class TestDoc {
	public static void main(String[] args) {
		DocUtil docUtil = new DocUtil();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("time", "Joanna");
		dataMap.put("subject", "111111111111");
		dataMap.put("type", "222222222222222222");
		dataMap.put("area", "C1");
		dataMap.put("cpm", "测试驾校");
		dataMap.put("total", "初次申领");
		dataMap.put("date", "2016-03-10");
//		dataMap.put("orderCount", "第1次");
//		dataMap.put("userImg1", docUtil.getImageStr("D:\\Img\\userImg1.png"));
//		dataMap.put("userImg2", docUtil.getImageStr("D:\\Img\\userImg2.png"));
//		dataMap.put("firstExamTime", "12:41:17-12:44:38");
//		dataMap.put("firstExamScores", "0分，不及格");
//		dataMap.put("firstDeductItem", "12:44:15 20102 1号倒车入库，车身出线 扣100分");
//		dataMap.put("firstPic1", docUtil.getImageStr("D:\\Img\\firstPic1.png"));
//		dataMap.put("firstPic2", docUtil.getImageStr("D:\\Img\\firstPic2.png"));
//		dataMap.put("firstPic3", docUtil.getImageStr("D:\\Img\\firstPic3.png"));
//		dataMap.put("secondExamTime", "12:46:50-13:05:37");
//		dataMap.put("secondExamScores", "90分，通过");
//		dataMap.put("secondDeductItem", "");
//		dataMap.put("secondPic1", docUtil.getImageStr("D:\\Img\\secondPic1.png"));
//		dataMap.put("secondPic2", docUtil.getImageStr("D:\\Img\\secondPic2.png"));
//		dataMap.put("secondPic3", docUtil.getImageStr("D:\\Img\\secondPic3.png"));
		docUtil.createDoc(dataMap, "wtp", "D:\\yanqiong.doc");
	}
}
