package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 传图分类表 seg_figure
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class Figure extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 名称 */
	private String figureName;
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
	public void setFigureName(String figureName) 
	{
		this.figureName = figureName;
	}

	public String getFigureName() 
	{
		return figureName;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("figureName", getFigureName())
            .append("createDate", getCreateDate())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
