package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码表 seg_figure_qrcode
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureQrcode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 二维码内容 */
	private String codeStr;
	/** URL */
	private String codeUrl;
	/**  */
	private String codeType;

	private String codeUser;

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

	/** 二维码图片路径 */
	private String codeImg;
	/** 文本信息 */
	private String textInfo;
	/** 是否已经使用 */
	private String isUse;
	/** 0 img  1 video */
	private String codeGenre;
	/** 是否已经下载 */
	private String isPrint;
	/**  */
	private String delFlag;


	private Integer num ;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

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
	public void setIsUse(String isUse) 
	{
		this.isUse = isUse;
	}

	public String getIsUse() 
	{
		return isUse;
	}
	public void setCodeGenre(String codeGenre) 
	{
		this.codeGenre = codeGenre;
	}

	public String getCodeGenre() 
	{
		return codeGenre;
	}
	public void setIsPrint(String isPrint) 
	{
		this.isPrint = isPrint;
	}

	public String getIsPrint() 
	{
		return isPrint;
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
            .append("isUse", getIsUse())
            .append("codeGenre", getCodeGenre())
            .append("isPrint", getIsPrint())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .toString();
    }
}
