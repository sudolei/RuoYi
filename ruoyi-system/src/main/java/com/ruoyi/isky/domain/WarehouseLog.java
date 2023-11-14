package com.ruoyi.isky.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存日志表 seg_warehouse_log
 * 
 * @author sunlei
 * @date 2019-11-06
 */
public class WarehouseLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 类型编号 */
	private Long goodId;

	private String goodsName;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/** 用户编号 */
	private Integer userId;
	/** 曹县仓库 */
	private Integer warehouseA;
	/** 邹平仓库 */
	private Integer warehouseB;
	/** 青岛仓库 */
	private Integer warehouseC;
	/** 本地仓库 */
	private Integer warehouseD;
	/** 备用仓库 */
	private Integer warehouseE;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setGoodId(Long goodId)
	{
		this.goodId = goodId;
	}

	public Long getGoodId()
	{
		return goodId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setWarehouseA(Integer warehouseA) 
	{
		this.warehouseA = warehouseA;
	}

	public Integer getWarehouseA() 
	{
		return warehouseA;
	}
	public void setWarehouseB(Integer warehouseB) 
	{
		this.warehouseB = warehouseB;
	}

	public Integer getWarehouseB() 
	{
		return warehouseB;
	}
	public void setWarehouseC(Integer warehouseC) 
	{
		this.warehouseC = warehouseC;
	}

	public Integer getWarehouseC() 
	{
		return warehouseC;
	}
	public void setWarehouseD(Integer warehouseD) 
	{
		this.warehouseD = warehouseD;
	}

	public Integer getWarehouseD() 
	{
		return warehouseD;
	}
	public void setWarehouseE(Integer warehouseE) 
	{
		this.warehouseE = warehouseE;
	}

	public Integer getWarehouseE() 
	{
		return warehouseE;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodId", getGoodId())
            .append("userId", getUserId())
            .append("warehouseA", getWarehouseA())
            .append("warehouseB", getWarehouseB())
            .append("warehouseC", getWarehouseC())
            .append("warehouseD", getWarehouseD())
            .append("warehouseE", getWarehouseE())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
