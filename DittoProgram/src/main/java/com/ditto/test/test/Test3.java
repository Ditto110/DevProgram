package com.ditto.test.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Test3 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws MalformedURLException, DocumentException {
		URL url = null;
		url = new URL("http://wthrcdn.etouch.cn/WeatherApi?citykey=101020100");
//		url = new URL("http://115.28.209.21:8888/sms.aspx");
		SAXReader saxReader = new SAXReader();
		
		Document document = saxReader.read(url);
		Element messageElement = document.getRootElement();

		
		
		
		System.out.println(messageElement.getName() + ":" + messageElement.getTextTrim());

		Iterator<Element> oneLevelElementItor = messageElement.elementIterator();
		while (oneLevelElementItor.hasNext()) {
			Element e1 = oneLevelElementItor.next();
			System.out.println(e1.getName() + " :" + e1.getText());
		}
	}

}
