package jice.vigortech.chat.robot.modules.application.entity;

import java.io.Serializable;

public class DefaultReply implements Serializable {

	/**
	 * 应用默认回复
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer application;
	private String reply;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplication() {
		return application;
	}

	public void setApplication(Integer application) {
		this.application = application;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

}
