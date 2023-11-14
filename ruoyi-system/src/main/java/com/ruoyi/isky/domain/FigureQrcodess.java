package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码表 seg_figure_qrcodess
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureQrcodess extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 二维码编号 */
	private String codeStr;
	/** 二维码路径 */
	private String codeUrl;
	/** 二维码状态(0未使用1已使用) */
	private String codeType;
	/** 二维码图片 */
	private String codeImg;
	/** 文本信息 */
	private String textInfo;
	/**  */
	private String delFlag;

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
	public void setCodeUrl(String codeUrl) 
	{
		this.codeUrl = codeUrl;
	}

	public String getCodeUrl() 
	{
		return codeUrl;
	}
	public void setCodeType(String codeType) 
	{
		this.codeType = codeType;
	}

	public String getCodeType() 
	{
		return codeType;
	}
	public void setCodeImg(String codeImg) 
	{
		this.codeImg = codeImg;
	}

	public String getCodeImg() 
	{
		return codeImg;
	}
	public void setTextInfo(String textInfo) 
	{
		this.textInfo = textInfo;
	}

	public String getTextInfo() 
	{
		return textInfo;
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
            .append("codeStr", getCodeStr())
            .append("codeUrl", getCodeUrl())
            .append("codeType", getCodeType())
            .append("codeImg", getCodeImg())
            .append("textInfo", getTextInfo())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
