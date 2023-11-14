package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 品牌型号表 isky_phone_brand_model
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public class IskyPhoneBrandModel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer brandId;
	/**  */
	private String modelName;
	/**  */
	private String modelCover;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setBrandId(Integer brandId) 
	{
		this.brandId = brandId;
	}

	public Integer getBrandId() 
	{
		return brandId;
	}
	public void setModelName(String modelName) 
	{
		this.modelName = modelName;
	}

	public String getModelName() 
	{
		return modelName;
	}
	public void setModelCover(String modelCover) 
	{
		this.modelCover = modelCover;
	}

	public String getModelCover() 
	{
		return modelCover;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandId", getBrandId())
            .append("modelName", getModelName())
            .append("modelCover", getModelCover())
            .append("remark", getRemark())
            .toString();
    }
}
