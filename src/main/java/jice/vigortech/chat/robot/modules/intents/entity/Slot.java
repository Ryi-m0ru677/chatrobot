package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;

public class Slot implements Serializable{

	/**
	 * 动作
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer intentId;
	private Integer flag;//是否必须
	private String typeName;//参数名称
	private String type;//参数类型--词库中取
	private String message;//提示语
	private String defValue;//默认值
	private Integer delFlag;
	
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIntentId() {
		return intentId;
	}
	public void setIntentId(Integer intentId) {
		this.intentId = intentId;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	
	
	
}
