package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 手机壳型号表 isky_phone_type
 * 
 * @author sunlei
 * @date 2019-08-21
 */
public class IskyPhoneType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String typeName;
	/**  */
	private String typeCover;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}

	public String getTypeName() 
	{
		return typeName;
	}
	public void setTypeCover(String typeCover) 
	{
		this.typeCover = typeCover;
	}

	public String getTypeCover() 
	{
		return typeCover;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeName", getTypeName())
            .append("typeCover", getTypeCover())
            .append("remark", getRemark())
            .toString();
    }
}
