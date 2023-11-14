package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板表 isky_phone_mb
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public class IskyPhoneMb extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer modelId;
	/** 模板 */
	private String mbUrl;
	/** 模板宽度 */
	private Integer mbWidth;
	/** 模板高度 */
	private Integer mbHeight;
	/** X坐标 */
	private Integer mbXsite;
	/** Y坐标 */
	private Integer mbYsite;
	/** 文本 */
	private String mbText;
	/** 排序 */
	private Integer mbOrder;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setModelId(Integer modelId) 
	{
		this.modelId = modelId;
	}

	public Integer getModelId() 
	{
		return modelId;
	}
	public void setMbUrl(String mbUrl) 
	{
		this.mbUrl = mbUrl;
	}

	public String getMbUrl() 
	{
		return mbUrl;
	}
	public void setMbWidth(Integer mbWidth) 
	{
		this.mbWidth = mbWidth;
	}

	public Integer getMbWidth() 
	{
		return mbWidth;
	}
	public void setMbHeight(Integer mbHeight) 
	{
		this.mbHeight = mbHeight;
	}

	public Integer getMbHeight() 
	{
		return mbHeight;
	}
	public void setMbXsite(Integer mbXsite) 
	{
		this.mbXsite = mbXsite;
	}

	public Integer getMbXsite() 
	{
		return mbXsite;
	}
	public void setMbYsite(Integer mbYsite) 
	{
		this.mbYsite = mbYsite;
	}

	public Integer getMbYsite() 
	{
		return mbYsite;
	}
	public void setMbText(String mbText) 
	{
		this.mbText = mbText;
	}

	public String getMbText() 
	{
		return mbText;
	}
	public void setMbOrder(Integer mbOrder) 
	{
		this.mbOrder = mbOrder;
	}

	public Integer getMbOrder() 
	{
		return mbOrder;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("modelId", getModelId())
            .append("mbUrl", getMbUrl())
            .append("mbWidth", getMbWidth())
            .append("mbHeight", getMbHeight())
            .append("mbXsite", getMbXsite())
            .append("mbYsite", getMbYsite())
            .append("mbText", getMbText())
            .append("mbOrder", getMbOrder())
            .toString();
    }
}
