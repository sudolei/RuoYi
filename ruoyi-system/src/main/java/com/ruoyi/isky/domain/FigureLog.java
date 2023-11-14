package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 操作日志表 seg_figure_log
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 批次号 */
	private String bno;
	/** 用户编号 */
	private Integer userId;
	/** 模板编号 */
	private Integer typeId;
	/** 图片编号 */
	private Integer imageId;
	/** 属性编号 */
	private Integer propertyId;
	/** 状态(0:添加，1删除) */
	private String state;
	/** 排序 */
	private Integer orders;
	/** 生成的文件编号 */
	private String newFileName;
	/** 原始文件 */
	private String fileName;
	/** 高度 */
	private String height;
	/** 宽度 */
	private String width;
	/** X坐标 */
	private String xsite;
	/** Y坐标 */
	private String ysite;
	/** 日期 */
	private Date createDate;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setBno(String bno) 
	{
		this.bno = bno;
	}

	public String getBno() 
	{
		return bno;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setTypeId(Integer typeId) 
	{
		this.typeId = typeId;
	}

	public Integer getTypeId() 
	{
		return typeId;
	}
	public void setImageId(Integer imageId) 
	{
		this.imageId = imageId;
	}

	public Integer getImageId() 
	{
		return imageId;
	}
	public void setPropertyId(Integer propertyId) 
	{
		this.propertyId = propertyId;
	}

	public Integer getPropertyId() 
	{
		return propertyId;
	}
	public void setState(String state) 
	{
		this.state = state;
	}

	public String getState() 
	{
		return state;
	}
	public void setOrders(Integer orders) 
	{
		this.orders = orders;
	}

	public Integer getOrders() 
	{
		return orders;
	}
	public void setNewFileName(String newFileName) 
	{
		this.newFileName = newFileName;
	}

	public String getNewFileName() 
	{
		return newFileName;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setHeight(String height) 
	{
		this.height = height;
	}

	public String getHeight() 
	{
		return height;
	}
	public void setWidth(String width) 
	{
		this.width = width;
	}

	public String getWidth() 
	{
		return width;
	}
	public void setXsite(String xsite) 
	{
		this.xsite = xsite;
	}

	public String getXsite() 
	{
		return xsite;
	}
	public void setYsite(String ysite) 
	{
		this.ysite = ysite;
	}

	public String getYsite() 
	{
		return ysite;
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
            .append("bno", getBno())
            .append("userId", getUserId())
            .append("typeId", getTypeId())
            .append("imageId", getImageId())
            .append("propertyId", getPropertyId())
            .append("state", getState())
            .append("orders", getOrders())
            .append("newFileName", getNewFileName())
            .append("fileName", getFileName())
            .append("height", getHeight())
            .append("width", getWidth())
            .append("xsite", getXsite())
            .append("ysite", getYsite())
            .append("createDate", getCreateDate())
            .append("remark", getRemark())
            .toString();
    }
}
