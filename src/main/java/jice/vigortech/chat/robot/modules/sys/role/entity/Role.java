package jice.vigortech.chat.robot.modules.sys.role.entity;

import java.awt.Menu;
import java.io.Serializable;
import java.util.List;

import org.assertj.core.util.Lists;

import jice.vigortech.chat.robot.common.model.entity.BaseEntity;
import jice.vigortech.chat.robot.modules.sys.office.entity.Office;

public class Role extends BaseEntity implements Serializable{

	/**
	 * 角色
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String enName;//英文名称
	private String name;
	private String dataScope;//数据范围
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表
	private List<Office> officeList = Lists.newArrayList(); // 按明细设置数据范围
	
	
	
	
	// 数据范围（1：所有数据；2：所在公司数据；3：所在部门数据；5：仅本人数据；9：按明细设置）
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_COMPANY = "2";
	public static final String DATA_SCOPE_OFFICE = "3";
	public static final String DATA_SCOPE_SELF = "5";
	public static final String DATA_SCOPE_CUSTOM = "9";

	
	public Role(Integer id){
		this.id = id;
	}
	
	
	
	public String getEnName() {
		return enName;
	}



	public void setEnName(String enName) {
		this.enName = enName;
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

	

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope==null?Role.DATA_SCOPE_SELF:dataScope;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Office> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}


}
