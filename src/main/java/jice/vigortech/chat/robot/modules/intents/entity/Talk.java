package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;

public class Talk implements Serializable{

	/**
	 * 场景中的客户提问与robot的回答
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer intentId;//场景id
	private String ask;
	private String answer;
	private String delFlag;
	
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntentsId() {
		return intentId;
	}
	public void setIntentsId(Integer intentId) {
		this.intentId = intentId;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
