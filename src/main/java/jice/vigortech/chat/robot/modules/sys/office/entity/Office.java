package jice.vigortech.chat.robot.modules.sys.office.entity;

import java.io.Serializable;
import java.util.List;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;

public class Office extends BaseEntity implements Serializable {

	/**
	 * 机构
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String type;
	/*private String grade;*/
	private Office parent; // 父级编号
	private String parentIds; // 所有父级编号
	private List<String> childDeptList;// 快速添加子部门

	public Integer getParent() {
		return parent.getId()==null ? 0 : parent.getId();
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

/*	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}*/

	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

}
