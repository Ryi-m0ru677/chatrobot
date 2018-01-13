package jice.vigortech.chat.robot.modules.process.entiry;

import java.io.Serializable;

public class ProcessBlock implements Serializable {

	/**
	 * 流程方块
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer aid;// app id
	private String itName;// 意图名称
	private Integer sort;
	private String lat;// 纬度--x
	private String lng;// 经度--y
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getItName() {
		return itName;
	}
	public void setItName(String itName) {
		this.itName = itName;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
}
