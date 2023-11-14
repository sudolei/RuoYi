package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 手机壳产品表 isky_phone_work
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public class IskyPhoneWork extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer mbId;
	/**  */
	private String workName;
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
	public void setMbId(Integer mbId) 
	{
		this.mbId = mbId;
	}

	public Integer getMbId() 
	{
		return mbId;
	}
	public void setWorkName(String workName) 
	{
		this.workName = workName;
	}

	public String getWorkName() 
	{
		return workName;
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
            .append("mbId", getMbId())
            .append("workName", getWorkName())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .toString();
    }
}
