package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码内容表 seg_figure_qrcode_work
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureQrcodeWork extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String codeStr;
	/**  */
	private String workUrl;
	/** 0 图片  1视频 */
	private String workType;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCodeStr(String codeStr) 
	{
		this.codeStr = codeStr;
	}

	public String getCodeStr() 
	{
		return codeStr;
	}
	public void setWorkUrl(String workUrl) 
	{
		this.workUrl = workUrl;
	}

	public String getWorkUrl() 
	{
		return workUrl;
	}
	public void setWorkType(String workType) 
	{
		this.workType = workType;
	}

	public String getWorkType() 
	{
		return workType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("codeStr", getCodeStr())
            .append("workUrl", getWorkUrl())
            .append("createTime", getCreateTime())
            .append("workType", getWorkType())
            .toString();
    }
}
