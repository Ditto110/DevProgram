package com.ditto.mode.factoryMethod;
/**
 * 
 *	客户端调用
 *	 	优点：每个产品一个工厂，通过工厂生成对应的对象。实现了客户端（调用方）和服务端（工厂对象）的代码逻辑分离，客户端只管调用，服务端只负责返回结果(产品)
 *	如果有需要其他的对象，只需要实现工厂接口，生成对应的对象工厂即可。	
 *	缺点：  需求的对象种类越多，那么工厂类就越多，同时存在产品交叉的问题。比如，需要同时获取版本（Version）、和来源（source）的问题
 */
public class FactoryMethodModeApplication {
	public static void main(String[] args) {
		//根据各自的版本工厂
		VersionMergeFactory604 factory604 = new VersionMergeFactory604();
		VersionMergeFactory702 factory702 = new VersionMergeFactory702();
		VersionMergeFactory705 factory705 = new VersionMergeFactory705();
		//获取对应的对象（产品）
		IVersion version604 = factory604.checkVersion();
		IVersion version702 = factory702.checkVersion();
		IVersion version705 = factory705.checkVersion();
		System.out.println(version604.getFlag());
		System.out.println(version702.getFlag());
		System.out.println(version705.getFlag());
	}
}
