package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 品牌表 isky_phone_brand
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public class IskyPhoneBrand extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 品牌名称 */
	private String brandName;
	/** 品牌封面 */
	private String brandCover;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setBrandName(String brandName) 
	{
		this.brandName = brandName;
	}

	public String getBrandName() 
	{
		return brandName;
	}
	public void setBrandCover(String brandCover) 
	{
		this.brandCover = brandCover;
	}

	public String getBrandCover() 
	{
		return brandCover;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandName", getBrandName())
            .append("brandCover", getBrandCover())
            .append("remark", getRemark())
            .toString();
    }
}
