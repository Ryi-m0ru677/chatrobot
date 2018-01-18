package jice.vigortech.chat.robot.modules.secret.entity;

import java.io.Serializable;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class Secret extends BaseEntity implements Serializable {

	/**
	 * 密级管理
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String describe;
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
