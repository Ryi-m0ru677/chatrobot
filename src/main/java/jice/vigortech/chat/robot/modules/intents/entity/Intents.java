package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;
import java.util.List;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class Intents extends BaseEntity implements Serializable {

	/**
	 * 场景
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer appId;//应用id
	private String name;
	private Integer rank;// 等级
	private String answer;//回复
	private String actionName;
	private List<Slot> action;// 动作
	private List<Ask> ask;// 场景中的客户提问与robot的回答

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	

	public List<Slot> getAction() {
		return action;
	}

	public void setAction(List<Slot> action) {
		this.action = action;
	}

	public List<Ask> getAsk() {
		return ask;
	}

	public void setAsk(List<Ask> ask) {
		this.ask = ask;
	}

	

}
