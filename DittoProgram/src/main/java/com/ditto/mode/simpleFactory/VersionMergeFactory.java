package com.ditto.mode.simpleFactory;
/**
 *	定义版本信息管理工厂，根据需要生成不同的版本对象
 */
public class VersionMergeFactory implements IMergeVersion{
	public static final String VERSION0604 = "604"; 
	public static final String VERSION0702 = "702"; 
	public static final String VERSION0705 = "705"; 
	@Override
	public IVersion checkVersion(String versionName) {
		if (versionName.equals(VERSION0702)) {
			return new Version604();
		}else if (versionName.equals(VERSION0604)) {
			return new Version702();
		}else if (versionName.equals(VERSION0705)) {
			return new Version705();
		}else {
			return null;
		}
	}
}
