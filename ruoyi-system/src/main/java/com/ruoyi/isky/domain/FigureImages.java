package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 图片集合表 seg_figure_images
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureImages extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 模板编号 */
	private String tpId;
	/** 模板地址 */
	private String imageUrl;
	/**  */
	private String imageZoomUrl;
	/** 图片宽度 */
	private String imageWidth;
	/** 缩放后图片宽度 */
	private String imageZoomWidth;
	/** 缩放比例 */
	private String zooms;
	/** 透明区域数 */
	private Integer areaNum;
	/** 是否圆角(0正角1圆角) */
	private String isround;
	/** 排序 */
	private Integer sort;
	/** 文本X坐标 */
	private String textXsite;
	/** 文本Y坐标 */
	private String textYsite;
	/** 文本宽 */
	private String textWidth;
	/** 字体大小 */
	private String textSize;
	/** 是否封面 */
	private String isCover;
	/** 文本长度 */
	private Integer textMaxlength;
	/** 创建时间 */
	private Date createDate;
	/** 更新时间 */
	private Date updateDate;
	/** 备注信息 */
	private String remarks;
	/** 删除标记（0：正常；1：删除） */
	private String delFlag;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTpId(String tpId) 
	{
		this.tpId = tpId;
	}

	public String getTpId() 
	{
		return tpId;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
	}
	public void setImageZoomUrl(String imageZoomUrl) 
	{
		this.imageZoomUrl = imageZoomUrl;
	}

	public String getImageZoomUrl() 
	{
		return imageZoomUrl;
	}
	public void setImageWidth(String imageWidth) 
	{
		this.imageWidth = imageWidth;
	}

	public String getImageWidth() 
	{
		return imageWidth;
	}
	public void setImageZoomWidth(String imageZoomWidth) 
	{
		this.imageZoomWidth = imageZoomWidth;
	}

	public String getImageZoomWidth() 
	{
		return imageZoomWidth;
	}
	public void setZooms(String zooms) 
	{
		this.zooms = zooms;
	}

	public String getZooms() 
	{
		return zooms;
	}
	public void setAreaNum(Integer areaNum) 
	{
		this.areaNum = areaNum;
	}

	public Integer getAreaNum() 
	{
		return areaNum;
	}
	public void setIsround(String isround) 
	{
		this.isround = isround;
	}

	public String getIsround() 
	{
		return isround;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}

	public Integer getSort() 
	{
		return sort;
	}
	public void setTextXsite(String textXsite) 
	{
		this.textXsite = textXsite;
	}

	public String getTextXsite() 
	{
		return textXsite;
	}
	public void setTextYsite(String textYsite) 
	{
		this.textYsite = textYsite;
	}

	public String getTextYsite() 
	{
		return textYsite;
	}
	public void setTextWidth(String textWidth) 
	{
		this.textWidth = textWidth;
	}

	public String getTextWidth() 
	{
		return textWidth;
	}
	public void setTextSize(String textSize) 
	{
		this.textSize = textSize;
	}

	public String getTextSize() 
	{
		return textSize;
	}
	public void setIsCover(String isCover) 
	{
		this.isCover = isCover;
	}

	public String getIsCover() 
	{
		return isCover;
	}
	public void setTextMaxlength(Integer textMaxlength) 
	{
		this.textMaxlength = textMaxlength;
	}

	public Integer getTextMaxlength() 
	{
		return textMaxlength;
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
            .append("tpId", getTpId())
            .append("imageUrl", getImageUrl())
            .append("imageZoomUrl", getImageZoomUrl())
            .append("imageWidth", getImageWidth())
            .append("imageZoomWidth", getImageZoomWidth())
            .append("zooms", getZooms())
            .append("areaNum", getAreaNum())
            .append("isround", getIsround())
            .append("sort", getSort())
            .append("textXsite", getTextXsite())
            .append("textYsite", getTextYsite())
            .append("textWidth", getTextWidth())
            .append("textSize", getTextSize())
            .append("isCover", getIsCover())
            .append("textMaxlength", getTextMaxlength())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
