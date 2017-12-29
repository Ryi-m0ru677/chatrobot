package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;
import java.util.List;

public class Ask implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String text;
	private String intent;//场景名称
	private List<Entity> entitys;
	private Integer delFlag;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	public List<Entity> getEntitys() {
		return entitys;
	}
	public void setEntitys(List<Entity> entitys) {
		this.entitys = entitys;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
