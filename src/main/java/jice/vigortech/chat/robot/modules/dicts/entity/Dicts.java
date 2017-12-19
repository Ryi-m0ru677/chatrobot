package jice.vigortech.chat.robot.modules.dicts.entity;

import java.io.Serializable;
import java.util.List;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class Dicts extends BaseEntity implements Serializable {

	/**
	 * 词库
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer appId;//应用的id
	private String name;
	private List<Synonym> word;// 同义词

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Synonym> getWord() {
		return word;
	}

	public void setWord(List<Synonym> word) {
		this.word = word;
	}

	

}
