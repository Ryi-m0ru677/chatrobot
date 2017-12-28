package jice.vigortech.chat.robot.modules.apptrain.entity;

import java.io.Serializable;

public class Train  implements Serializable{

	/**
	 * app训练
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer appId;
	private String trainDate;
	private String name;
	private String local;
	private Integer delFlag;
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
	public String getTrainDate() {
		return trainDate;
	}
	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
