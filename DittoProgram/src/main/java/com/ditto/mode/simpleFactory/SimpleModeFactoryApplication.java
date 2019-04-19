package com.ditto.mode.simpleFactory;
/**
 *	客户端调用
 *	 	优点：实现了客户端（调用方）和服务端（工厂对象）的代码逻辑分离，客户端只管调用，服务端只负责返回结果(产品)
 *		缺点：  不便于扩展，如果客户端需要另一个对象（比如version800），那就只有去扩展工厂类的实现方法，
	如果同一份代码是由两个人开发维护，那就可能造成新的问题，违背了“开放-封闭”的原则
 */
public class SimpleModeFactoryApplication {
	public static void main(String[] args) {
		VersionMergeFactory versionMergeFactory = new VersionMergeFactory();
		//传入指定的版本信息即可得到对应的对象（产品）
		IVersion version604 = versionMergeFactory.checkVersion(VersionMergeFactory.VERSION0604);
		IVersion version702 = versionMergeFactory.checkVersion(VersionMergeFactory.VERSION0702);
		IVersion version705 = versionMergeFactory.checkVersion(VersionMergeFactory.VERSION0705);
		System.out.println(version604.getFlag());
		System.out.println(version702.getFlag());
		System.out.println(version705.getFlag());
	}
}
