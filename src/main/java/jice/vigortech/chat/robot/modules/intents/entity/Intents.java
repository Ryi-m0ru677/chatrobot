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
	private String actionName;
	private List<RobotAction> action;// 动作
	private List<Talk> talk;// 场景中的客户提问与robot的回答

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

	

	public List<RobotAction> getAction() {
		return action;
	}

	public void setAction(List<RobotAction> action) {
		this.action = action;
	}

	public List<Talk> getTalk() {
		return talk;
	}

	public void setTalk(List<Talk> talk) {
		this.talk = talk;
	}

}
