package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采集数据图片表 isky_collect_img
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class IskyCollectImg extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String imgUrl;
	/**  */
	private Integer status;
	/**  */
	private Integer dataId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setDataId(Integer dataId) 
	{
		this.dataId = dataId;
	}

	public Integer getDataId() 
	{
		return dataId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imgUrl", getImgUrl())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("status", getStatus())
            .append("dataId", getDataId())
            .toString();
    }
}
