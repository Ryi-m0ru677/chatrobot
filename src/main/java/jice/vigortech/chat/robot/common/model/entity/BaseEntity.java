package jice.vigortech.chat.robot.common.model.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jice.vigortech.chat.robot.common.util.DateUtils;
import jice.vigortech.chat.robot.modules.user.entity.User;


public class BaseEntity implements Serializable{

	/**
	 * 基础实体类
	 */
	private static final long serialVersionUID = 1L;

	protected String createBy; // 创建者
    
    protected Date createDate; // 创建日期
    
    protected String updateBy; // 更新者
    
    protected Date updateDate; // 更新日期
    
    protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）
    
    protected String createDateString;
    
    protected String updateDateString;
    
    protected User loginUser;
    
    protected int isAdmin = 0;
    
   /* @JsonIgnore
    public int getIsAdmin()
    {
        int flag = 0;
        List<Role> roleList = UserUtils.getRoleListByCreuntUser();
        for (Role role : roleList)
        {
            if ("system".equals(role.getEnname()))
            {
                flag = 1;
                break;
            }
        }
        isAdmin = flag;
        return isAdmin;
    }*/
    
   /* public void setIsAdmin(int isAdmin)
    {
        this.isAdmin = isAdmin;
    }*/
    
   /* @JsonIgnore
    public User getLoginUser()
    {
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId()))
        {
            this.loginUser = user;
        }
        return loginUser;
    }*/
    /**
     * 获取创建时间字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getCreateDateString()
    {
        if (createDate == null)
        {
            createDate = new Date();
        }
        return DateUtils.formatDateTime(createDate);
    }
    
    /**
     * 获取修改时间字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getUpdateDateString()
    {
        if (updateDate == null)
        {
            updateDate = new Date();
        }
        return DateUtils.formatDateTime(updateDate);
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    
   
    
    public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate()
    {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
    
    @JsonIgnore
    @Length(min = 1, max = 1)
    public String getDelFlag()
    {
        return delFlag;
    }
    
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }
}
