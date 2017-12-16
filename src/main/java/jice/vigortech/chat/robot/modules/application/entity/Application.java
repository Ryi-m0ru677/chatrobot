package jice.vigortech.chat.robot.modules.application.entity;

import java.io.Serializable;
import java.util.List;

import jice.vigortech.chat.robot.modules.intents.entity.Intents;

public class Application implements Serializable {

	/**
	 * 应用
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer isPrivate;
	private String link;
	private String language;
	private String zone;// 时区
	private String clientToken;
	private String devToken;
	private List<String> defaultReply;// 默认回复
	private List<Intents> intents;
	private Integer delflag;

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

	public Integer getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Integer isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}

	public String getDevToken() {
		return devToken;
	}

	public void setDevToken(String devToken) {
		this.devToken = devToken;
	}

	public List<String> getDefaultReply() {
		return defaultReply;
	}

	public void setDefaultReply(List<String> defaultReply) {
		this.defaultReply = defaultReply;
	}

	public List<Intents> getIntents() {
		return intents;
	}

	public void setIntents(List<Intents> intents) {
		this.intents = intents;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

}
