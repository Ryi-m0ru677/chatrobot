package jice.vigortech.chat.robot.modules.mservices.entity;

import java.io.Serializable;

public class Iattribute implements Serializable {

	/**
	 * 接口属性
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer iid;//微服务接口id
	private String name;
	private String type;//属性类型
	private String describe;
	//private Integer delFlag;
	
	/*public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIid() {
		return iid;
	}
	public void setIid(Integer iid) {
		this.iid = iid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
