package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 传图登录表 seg_figure_login
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureLogin extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 淘宝 */
	private String taobao;
	/** 用户名称 */
	private String username;
	/** 电话 */
	private String telephone;
	/**  */
	private Date createDate;
	/**  */
	private Date updateDate;
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
	public void setTaobao(String taobao) 
	{
		this.taobao = taobao;
	}

	public String getTaobao() 
	{
		return taobao;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getUsername() 
	{
		return username;
	}
	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public String getTelephone() 
	{
		return telephone;
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
            .append("taobao", getTaobao())
            .append("username", getUsername())
            .append("telephone", getTelephone())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
