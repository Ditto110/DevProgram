package com.ditto.mode.simpleFactory;
/**
 * 版本兼容的接口
 */
public interface IMergeVersion {
	IVersion checkVersion (String versionName);	//需要兼容的字段
}
