package com.ditto.dto;

import java.util.Date;

public class MediaEntity {
	private Date expoDate;
	private Integer channelId;
	private Integer modelId;
	private Integer type;	//表示点击还是曝光 1表示曝光，0表示点击
	private Date endExpoDate;
	private Integer spaceId;
	private String spaceCode;
	private String orderNo;
	private String orderName;
	private Integer materialId; 
	private String materialName;
	private String version;
	private Integer expoType;	//1,2,3,4  分别表示曝光次数，曝光人数，点击次数，点击人数
	private Integer queryUnit;  //1,2,3,4 分别表示byHour,byDay,byWeek,byMonth
	private Integer provinceId;
	private Integer cityId;
	private Integer length;
	private Integer start;
	private Integer draw;
	private Integer sEcho;
	private Integer scheduleId;
	private Object[][] order;
	public Date getExpoDate() {
		return expoDate;
	}
	public void setExpoDate(Date expoDate) {
		this.expoDate = expoDate;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer queryType) {
		this.type = queryType;
	}
	
	public Date getEndExpoDate() {
		return endExpoDate;
	}
	public void setEndExpoDate(Date endExpoDate) {
		this.endExpoDate = endExpoDate;
	}
	
	public Integer getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(Integer spaceId) {
		this.spaceId = spaceId;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialNo(Integer materialId) {
		this.materialId = materialId;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Integer getExpoType() {
		return expoType;
	}
	public void setExpoType(Integer expoType) {
		this.expoType = expoType;
	}
	public Integer getQueryUnit() {
		return queryUnit;
	}
	public void setQueryUnit(Integer queryUnit) {
		this.queryUnit = queryUnit;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getsEcho() {
		return sEcho;
	}
	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}
	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	public String getSpaceCode() {
		return spaceCode;
	}
	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public Object[][] getOrder() {
		return order;
	}
	public void setOrder(Object[][] order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "MediaEntity [expoDate=" + expoDate + ", channelId=" + channelId + ", modelId=" + modelId + ", type="
				+ type + ", endExpoDate=" + endExpoDate + ", spaceId=" + spaceId + ", spaceCode=" + spaceCode
				+ ", orderNo=" + orderNo + ", orderName=" + orderName + ", materialId=" + materialId + ", materialName="
				+ materialName + ", version=" + version + ", expoType=" + expoType + ", queryUnit=" + queryUnit
				+ ", provinceId=" + provinceId + ", cityId=" + cityId + ", length=" + length + ", start=" + start
				+ ", draw=" + draw + ", sEcho=" + sEcho + ", scheduleId=" + scheduleId + ", order=" + order + "]";
	}
	
}
