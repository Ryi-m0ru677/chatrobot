package jice.vigortech.chat.robot.common.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jice.vigortech.chat.robot.common.util.DateUtils;
import jice.vigortech.chat.robot.modules.sys.user.entity.User;

public class BaseEntity implements Serializable {

	/**
	 * 基础实体类
	 */
	private static final long serialVersionUID = 1L;

	protected User createBy; // 创建者

	protected Date createDate; // 创建日期

	protected Date updateDate; // 更新日期

	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	protected String createDateString;

	protected String updateDateString;
	
	
	

	

	/**
	 * 获取创建时间字符串
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public String getCreateDateString() {
		if (createDate == null) {
			createDate = new Date();
		}
		return DateUtils.formatDateTime(createDate);
	}

	/**
	 * 获取修改时间字符串
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public String getUpdateDateString() {
		if (updateDate == null) {
			updateDate = new Date();
		}
		return DateUtils.formatDateTime(updateDate);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		//TODO user = securityUtil.getUSer
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min = 1, max = 1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	/*public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = new HashMap<String,String>();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}*/
}
