package jice.vigortech.chat.robot.modules.mservices.entity;

import java.io.Serializable;
import java.util.List;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class MicService extends BaseEntity implements Serializable {

	/**
	 * 微服务
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String ip;
	private String ename;
	private String describe;
	private List<Minterface> interList;
	
	public List<Minterface> getInterList() {
		return interList;
	}
	public void setInterList(List<Minterface> interList) {
		this.interList = interList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
