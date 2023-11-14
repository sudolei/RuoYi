package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 图片属性表 seg_figure_image_property
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureImageProperty extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 图片编号 */
	private Integer imageId;
	/** x坐标 */
	private String xSite;

	public String getxSite() {
		return xSite;
	}

	public void setxSite(String xSite) {
		this.xSite = xSite;
	}

	public String getySite() {
		return ySite;
	}

	public void setySite(String ySite) {
		this.ySite = ySite;
	}

	/** y坐标 */

	private String ySite;
	/** 宽度 */
	private String width;
	/** 高度 */
	private String height;
	/**  */
	private String isround;
	/**  */
	private String zoomXsite;
	/**  */
	private String zoomYsite;
	/**  */
	private String zoomWidth;
	/**  */
	private String zoomHeight;
	/** 排序 */
	private Integer orderNum;
	/** 创建时间 */
	private Date createDate;
	/** 更新时间 */
	private Date updateDate;
	/** 备注信息 */
	private String remarks;
	/** 删除标记 */
	private String delFlag;

	private String tpId;

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setImageId(Integer imageId) 
	{
		this.imageId = imageId;
	}

	public Integer getImageId() 
	{
		return imageId;
	}
	public void setXSite(String xSite) 
	{
		this.xSite = xSite;
	}

	public String getXSite() 
	{
		return xSite;
	}
	public void setYSite(String ySite) 
	{
		this.ySite = ySite;
	}

	public String getYSite() 
	{
		return ySite;
	}
	public void setWidth(String width) 
	{
		this.width = width;
	}

	public String getWidth() 
	{
		return width;
	}
	public void setHeight(String height) 
	{
		this.height = height;
	}

	public String getHeight() 
	{
		return height;
	}
	public void setIsround(String isround) 
	{
		this.isround = isround;
	}

	public String getIsround() 
	{
		return isround;
	}
	public void setZoomXsite(String zoomXsite) 
	{
		this.zoomXsite = zoomXsite;
	}

	public String getZoomXsite() 
	{
		return zoomXsite;
	}
	public void setZoomYsite(String zoomYsite) 
	{
		this.zoomYsite = zoomYsite;
	}

	public String getZoomYsite() 
	{
		return zoomYsite;
	}
	public void setZoomWidth(String zoomWidth) 
	{
		this.zoomWidth = zoomWidth;
	}

	public String getZoomWidth() 
	{
		return zoomWidth;
	}
	public void setZoomHeight(String zoomHeight) 
	{
		this.zoomHeight = zoomHeight;
	}

	public String getZoomHeight() 
	{
		return zoomHeight;
	}
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}

	public Integer getOrderNum() 
	{
		return orderNum;
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
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imageId", getImageId())
            .append("xSite", getXSite())
            .append("ySite", getYSite())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("isround", getIsround())
            .append("zoomXsite", getZoomXsite())
            .append("zoomYsite", getZoomYsite())
            .append("zoomWidth", getZoomWidth())
            .append("zoomHeight", getZoomHeight())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
