package jice.vigortech.chat.robot.modules.entities.entity;

import java.io.Serializable;

public class Synonym implements Serializable {

	/**
	 * 同义词
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer entities;
	private String keyword;// 关键词
	private Integer synonymyFlag;// 是否有同义词
	private String synonymy;// 同义词

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEntities() {
		return entities;
	}

	public void setEntities(Integer entities) {
		this.entities = entities;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSynonymy() {
		return synonymy;
	}

	public void setSynonymy(String synonymy) {
		if (synonymyFlag == 1) {
			this.synonymy = null;
		}
		this.synonymy = synonymy;
	}

	public Integer getSynonymyFlag() {
		return synonymyFlag;
	}

	public void setSynonymyFlag(Integer synonymyFlag) {
		this.synonymyFlag = synonymyFlag;
	}

}
