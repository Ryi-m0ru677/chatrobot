package jice.vigortech.chat.robot.modules.mservices.entity;

import java.io.Serializable;
import java.util.List;

public class Minterface implements Serializable {

	/**
	 * 微服务接口
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer mid;// 微服务id
	private String name;
	private String describe;
	private String micUrl;// 接口URL
	private Integer delFlag;
	private List<Iattribute> attrList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	

	public String getMicUrl() {
		return micUrl;
	}

	public void setMicUrl(String micUrl) {
		this.micUrl = micUrl;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<Iattribute> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<Iattribute> attrList) {
		this.attrList = attrList;
	}

}
