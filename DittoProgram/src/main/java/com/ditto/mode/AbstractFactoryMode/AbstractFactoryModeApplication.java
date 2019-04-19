package com.ditto.mode.AbstractFactoryMode;

/**
 * 
 * 客户端调用 抽象工厂模式可以定义实现多个接口，一个工厂可以生成多个对象（产品），能够遵循“开放-封闭原则”,具备通用性
 */
public class AbstractFactoryModeApplication {
	
	public static void main(String[] args) {
		
		Factory604 factory604 = new Factory604();
		
		ISource source = factory604.checkSource();
		IVersion version = factory604.checkVersion();
		
		System.out.println(source.getSource());
		System.out.println(version.getFlag());
	}
}
