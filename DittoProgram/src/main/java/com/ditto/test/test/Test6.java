package com.ditto.test.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test6 {
	public static void main(String[] args) {
		String js = "\"orderNo\":\"253\",\"modelId\":\"\",\"channelType\":\"0\",\"cityId\":\"512000\",\"materialId\":\"986\",\"mac\":,\"type\":\"1\",\"version\":\"10000\",\"spaceCode\":\"appScreenSaver\",\"regionId\":\"510000\",\"time\":1535153735510,\"scheduleId\":\"1049\",\"channelId\":\"beevideo\",ip:117.176.5.141";
//		String js = "\"orderNo\":\"253\",modelId:,\"channelType\":\"0\",\"cityId\":\"512000\",\"materialId\":\"986\",\"mac\":,\"type\":\"1\",\"version\":\"10000\",\"spaceCode\":\"appScreenSaver\",\"regionId\":\"510000\",\"time\":1535153735510,\"scheduleId\":\"1049\",\"channelId\":\"beevideo\",ip:117.176.5.141";
		js ="{" + js + "}";
//		js = "{cityId:0,orderNo:283,spaceCode:ottBoot,regionId:0,scheduleId:1199,materialId:759,mac:4c:0f:c7:a6:e5:c2,type:1,channelType:OTT,version:2.01.70,modelId:p201_iptv,channelId:fangzheng,time:1535551510311,ip:124.166.233.37}";
		js = js.replace(":,", ":\"\",");
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(js, JsonObject.class);
		JsonArray array = new JsonArray();
		array.add(json);
		for (JsonElement je : array) {
			JsonObject object = je.getAsJsonObject();
			System.out.println(object.get("mac").toString());
		}
	}
}
