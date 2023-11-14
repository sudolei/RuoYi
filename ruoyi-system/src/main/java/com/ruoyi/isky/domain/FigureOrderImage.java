package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 订单图片表 seg_figure_order_image
 * 
 * @author sunlei
 * @date 2019-06-21
 */
public class FigureOrderImage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer orderId;
	/**  */
	private String imageUrl;
	/**  */
	private String imageNum;
	/**  */
	private String delFlag;
	/**  */
	private Date createDate;
	/**  */
	private Date updateDate;
	/**  */
	private Integer tagint;


	private Integer orders;

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOrderId(Integer orderId) 
	{
		this.orderId = orderId;
	}

	public Integer getOrderId() 
	{
		return orderId;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
	}
	public void setImageNum(String imageNum) 
	{
		this.imageNum = imageNum;
	}

	public String getImageNum() 
	{
		return imageNum;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
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
	public void setTagint(Integer tagint) 
	{
		this.tagint = tagint;
	}

	public Integer getTagint() 
	{
		return tagint;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("imageUrl", getImageUrl())
            .append("imageNum", getImageNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remark", getRemark())
            .append("tagint", getTagint())
            .toString();
    }
}
