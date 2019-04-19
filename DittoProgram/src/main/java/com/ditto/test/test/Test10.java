package com.ditto.test.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test10 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DocumentException, IOException {
		HashMap<Object, Object> map = new HashMap<>();
		//dom4j 解析xml
		SAXReader saxReader = new SAXReader();
		saxReader.setIgnoreComments(true);
		saxReader.setEncoding("UTF8");
		//hettp 请求 获取接口数据
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://wthrcdn.etouch.cn/WeatherApi?citykey=101040400");
		CloseableHttpResponse execute = httpClient.execute(get);
		HttpEntity entity = execute.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
		
		Document read = saxReader.read(byteArrayInputStream);
		Element rootElement = read.getRootElement();
		//迭代器获取xml各节点数据
		Iterator<Element> eIterator2 = rootElement.elementIterator();
		while (eIterator2.hasNext()) {
			Element level2_e = (Element) eIterator2.next();
			String level2_eName = level2_e.getName();
			if (level2_eName != null && level2_eName.equals("environment")) {
				Iterator<Element> eIterator3 = level2_e.elementIterator();
				while (eIterator3.hasNext()) {
					Element level3_e = (Element) eIterator3.next();
					String level3_eName = level3_e.getName();
					if (level3_eName != null && level3_eName.equals("aqi")) {
						map.put("aqi", level3_e.getStringValue());
					} else if (level3_eName != null && level3_eName.equals("pm25")) {
						map.put("pm25", level3_e.getStringValue());
					} else if (level3_eName != null && level3_eName.equals("quality")) {
						map.put("quality", level3_e.getStringValue());
					}
				}
				break;
			}
		}
		if (byteArrayInputStream != null) {
			byteArrayInputStream.close();
		}
		System.out.println("===="+map);
	}
}
