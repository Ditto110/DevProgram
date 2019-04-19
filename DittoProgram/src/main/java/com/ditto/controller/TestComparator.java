package com.ditto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestComparator {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		map.put("k1", "v1");
		map2.put("k1", "vf");
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		list.add(map2);
		Collections.sort(list,new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> m1, Map<String, Object> m2) {
				String v1 = m1.get("k1").toString();
				String v2 = m1.get("k1").toString();
				return v1.compareTo(v2);
			}
		});
		System.out.println(list);
	}
}
