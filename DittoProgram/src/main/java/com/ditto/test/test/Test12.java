package com.ditto.test.test;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Test12 {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("");
		ClassPathResource resource = new ClassPathResource("");
		InputStream resourceAsStream = resource.getClassLoader().getResourceAsStream("");
		InputStream openStream = resource.getClass().getResource("").openStream();
	}
	
	public void test() {
		String resource = this.getClass().getResource("").getPath();
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("");
		InputStream inputStream = this.getClass().getResourceAsStream("");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document parse = documentBuilder.parse(inputStream);	//同saxReader 一样将流转换成document对象
			
			/*SAXReader reader = new SAXReader();
			org.dom4j.Document document = reader.read(inputStream);*/
			
			
			Element element = parse.getDocumentElement();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	}
}
