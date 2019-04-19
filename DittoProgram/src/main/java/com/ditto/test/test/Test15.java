package com.ditto.test.test;

import java.util.ArrayList;
import java.util.Iterator;

public class Test15 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.add("333");
		list.add("666");
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String st = (String) iterator.next();
			if (st.equals("444")) {
				iterator.remove();
			}
		}
	/*	for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			if (string.equals("333")) {
				list.remove(i);
			}
		}*/
	/*	for (String string : list) {
			if (string.equals("333")) {
				list.remove(string);
			}
		}*/
		System.out.println(list);
	}
}
