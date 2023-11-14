package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 图片地址表 isky_phone_work_image
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public class IskyPhoneWorkImage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer workId;
	/**  */
	private String imageUrl;
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
	public void setWorkId(Integer workId) 
	{
		this.workId = workId;
	}

	public Integer getWorkId() 
	{
		return workId;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
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
            .append("workId", getWorkId())
            .append("imageUrl", getImageUrl())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .toString();
    }
}
