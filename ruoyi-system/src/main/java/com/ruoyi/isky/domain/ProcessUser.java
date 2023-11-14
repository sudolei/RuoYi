package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 公司流程用户表 seg_process_user
 * 
 * @author sunlei
 * @date 2019-10-12
 */
public class ProcessUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String userName;
	/** 0所有权限 1 销售 2生产 */
	private String group;
	/**  */
	private String userKey;
	/**  */
	private Date createDate;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setGroup(String group) 
	{
		this.group = group;
	}

	public String getGroup() 
	{
		return group;
	}
	public void setUserKey(String userKey) 
	{
		this.userKey = userKey;
	}

	public String getUserKey() 
	{
		return userKey;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("group", getGroup())
            .append("userKey", getUserKey())
            .append("createDate", getCreateDate())
            .toString();
    }
}
