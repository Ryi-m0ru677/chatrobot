package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;

public class Entity implements Serializable {

	/**
	 * 参数实体
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer askId;
	private Integer start;//截取字符串开始位置;
	private Integer end;//截取字符串结束位置;
	private String value;//截取内容
	private String entity;//词库名称
	private Integer delFlag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAskId() {
		return askId;
	}
	public void setAskId(Integer askId) {
		this.askId = askId;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
