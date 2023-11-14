package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 数据采集表 isky_collect_data
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class IskyCollectData extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 图片 */
	private String images;
	/** 来源URL */
	private String sourceUrl;
	/** 详细描述 */
	private String detail;
	/**  */
	private String status;
	/** 价格 */
	private String price;
	/**  */
	private Date createDate;
	/**  */
	private Date updateDate;
	/**  */
	private String remarks;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setImages(String images) 
	{
		this.images = images;
	}

	public String getImages() 
	{
		return images;
	}
	public void setSourceUrl(String sourceUrl) 
	{
		this.sourceUrl = sourceUrl;
	}

	public String getSourceUrl() 
	{
		return sourceUrl;
	}
	public void setDetail(String detail) 
	{
		this.detail = detail;
	}

	public String getDetail() 
	{
		return detail;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getPrice() 
	{
		return price;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("images", getImages())
            .append("sourceUrl", getSourceUrl())
            .append("detail", getDetail())
            .append("status", getStatus())
            .append("price", getPrice())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .toString();
    }
}
