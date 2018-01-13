package jice.vigortech.chat.robot.modules.process.entiry;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class Processes extends BaseEntity implements Serializable {

	/**
	 * 流程
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String describe;
	private String content;

	private List<ProcessBlock> processBlockList;

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getContent() {
		return content;
	}

	public void setContent(List<ProcessBlock> processBlockList) {
		this.content = JSON.toJSONString(processBlockList);
	}

	public List<ProcessBlock> getProcessBlockList() {
		return processBlockList;
	}

	public void setProcessBlockList(List<ProcessBlock> processBlockList) {
		this.processBlockList = processBlockList;
	}
}
