package com.ditto.entity;

import com.google.gson.annotations.SerializedName;

public class AppReportInfo {
    @SerializedName("appid")
    private String appId;
    @SerializedName("appname")
    private String appName;
    @SerializedName("packageName")
    private String packageName;
    @SerializedName("versionName")
    private String versionName;
    @SerializedName("versionCode")
    private int versionCode;
    @SerializedName("source")
    private String source;
    @SerializedName("flag")
    private int flag;
    @SerializedName("uses")
    private String uses;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getUses() {
		return uses;
	}
	public void setUses(String uses) {
		this.uses = uses;
	}
    
}
