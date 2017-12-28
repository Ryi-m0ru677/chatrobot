package jice.vigortech.chat.robot.modules.dicts.entity;

import java.io.Serializable;
import java.util.List;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;
import jice.vigortech.chat.robot.common.util.PinyinUtil;

public class Dicts extends BaseEntity implements Serializable {

	/**
	 * 词库
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer appId;// 应用的id
	private String name;
	protected String pinyin;
	private Integer synonymyFlag;// 是否有同义词
	private List<Synonymy> wordList;// 同义词

	public Integer getSynonymyFlag() {
		return synonymyFlag;
	}

	public void setSynonymyFlag(Integer synonymyFlag) {
		this.synonymyFlag = synonymyFlag;
	}

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

	public List<Synonymy> getWordList() {
		return wordList;
	}

	public void setWordList(List<Synonymy> wordList) {
		this.wordList = wordList;
	}

	public String getPinyin() {
		return PinyinUtil.getPingYin(name);
	}
}
