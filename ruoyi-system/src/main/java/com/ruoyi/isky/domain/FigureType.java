package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 模板分类表 seg_figure_type
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 类型编号 */
	private String typeId;
	/** 名称 */
	private String name;
	/** 图片路径 */
	private String imgUrl;
	/** 前端访问路径 */
	private String accessUrl;
	/** 缩小路径 */
	private String imgSamllUrl;

	private String isFull;

	/** 排序 */
	private Integer myorder;
	/** 创建日期 */
	private Date createDate;
	/** 删除标志 */
	private String delFlag;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTypeId(String typeId) 
	{
		this.typeId = typeId;
	}

	public String getTypeId() 
	{
		return typeId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}
	public void setAccessUrl(String accessUrl) 
	{
		this.accessUrl = accessUrl;
	}

	public String getAccessUrl() 
	{
		return accessUrl;
	}
	public void setImgSamllUrl(String imgSamllUrl) 
	{
		this.imgSamllUrl = imgSamllUrl;
	}

	public String getImgSamllUrl() 
	{
		return imgSamllUrl;
	}
	public void setMyorder(Integer myorder) 
	{
		this.myorder = myorder;
	}

	public Integer getMyorder() 
	{
		return myorder;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

	public String getIsFull() {
		return isFull;
	}

	public void setIsFull(String isFull) {
		this.isFull = isFull;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeId", getTypeId())
            .append("name", getName())
            .append("imgUrl", getImgUrl())
            .append("accessUrl", getAccessUrl())
            .append("imgSamllUrl", getImgSamllUrl())
            .append("myorder", getMyorder())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
