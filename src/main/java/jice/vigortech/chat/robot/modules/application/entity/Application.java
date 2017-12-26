package jice.vigortech.chat.robot.modules.application.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class Application extends BaseEntity implements Serializable {

	/**
	 * 应用
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotBlank(message="应用名称不能为空!")
	private String name;
	private Integer isPrivate;
	private String link;
	private String language;
	private String zone;// 时区
	private String clientToken;
	private String devToken;
	private String describe;
	private String defReply;// 默认回复
	private String storage;//存储方式
	
	
	

	public Application getApp(String name, Integer isPrivate, String defaultReply) {
		Application app = new Application();
		app.setName(name);
		app.setId(isPrivate);
		app.setDefReply(defaultReply);
		return app;
	}

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	

	public String getDefReply() {
		return defReply;
	}

	public void setDefReply(String defReply) {
		this.defReply = defReply;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}


	
}
