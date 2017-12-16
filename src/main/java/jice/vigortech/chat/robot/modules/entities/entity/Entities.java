package jice.vigortech.chat.robot.modules.entities.entity;

import java.io.Serializable;
import java.util.List;

public class Entities implements Serializable {

	/**
	 * 词库
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer agent;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgent() {
		return agent;
	}

	public void setAgent(Integer agent) {
		this.agent = agent;
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

	private List<Synonym> word;

}
