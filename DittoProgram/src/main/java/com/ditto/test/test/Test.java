package com.ditto.test.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {
	static int days;
	static Integer dayIn;
	public static void main(String[] args) {
		int a = 0;
		JSONObject obj = new JSONObject();
		try {
			obj.put("name", "ditto");
			obj.put("mac", 9527);
			String string = obj.toString();
			System.out.println(string);
			String str = "[{name:'a',value:'aa'},{name:'b',value:'bb'},{name:'c',value:'cc'},{name:'d',value:'dd'}]" ; 
			JSONArray array = new JSONArray(str);
			Object object = array.get(0);
			JSONObject object2 = array.getJSONObject(0);
			System.out.println("--------"+object2.toString());
			String name = (String) object2.get("name");
			System.out.println("======="+name);
			System.out.println(array);
			System.out.println("===-----"+days);
			System.out.println("===-----"+dayIn);
			System.out.println("===-----"+a);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
