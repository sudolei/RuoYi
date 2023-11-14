package com.ruoyi.isky.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存树表 seg_warehouse_goods
 * 
 * @author sunlei
 * @date 2019-11-05
 */
public class WarehouseGoods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 用户编号 */
	private Integer userId;
	/** 父部门id */
	private Long parentId;
	/** 祖级列表 */
	private String ancestors;
	/** 名称 */
	private String goodsName;
	/** 排序号 */
	private Integer orderNum;
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
	/** 仓库（对应字典） */
	private String warehouseAll;
	/** 状态 */
	private String status;
	/** 库存数量 */
	private Long stockQuantity;
	/** 预警最小值 */
	private Long warningMinQuantity;
	/** 预警最大值 */
	private Long warningMaxQuantity;

	private String mobileHref;

	private String mobileHrefCl;


	private List<WarehouseGoods> goodsList = new ArrayList<WarehouseGoods>();

	public List<WarehouseGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<WarehouseGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getMobileHrefCl() {
		return mobileHrefCl;
	}

	public void setMobileHrefCl(String mobileHrefCl) {
		this.mobileHrefCl = mobileHrefCl;
	}

	private String ckIds;

	private String ckNames;

	public String getCkIds() {
		return ckIds;
	}

	public void setCkIds(String ckIds) {
		this.ckIds = ckIds;
	}

	public String getCkNames() {
		return ckNames;
	}

	public void setCkNames(String ckNames) {
		this.ckNames = ckNames;
	}

	public String getMobileHref() {
		return mobileHref;
	}

	public void setMobileHref(String mobileHref) {
		this.mobileHref = mobileHref;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setParentId(Long parentId) 
	{
		this.parentId = parentId;
	}

	public Long getParentId() 
	{
		return parentId;
	}
	public void setAncestors(String ancestors) 
	{
		this.ancestors = ancestors;
	}

	public String getAncestors() 
	{
		return ancestors;
	}
	public void setGoodsName(String goodsName) 
	{
		this.goodsName = goodsName;
	}

	public String getGoodsName() 
	{
		return goodsName;
	}
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}

	public Integer getOrderNum() 
	{
		return orderNum;
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
	public void setWarehouseAll(String warehouseAll) 
	{
		this.warehouseAll = warehouseAll;
	}

	public String getWarehouseAll() 
	{
		return warehouseAll;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setStockQuantity(Long stockQuantity) 
	{
		this.stockQuantity = stockQuantity;
	}

	public Long getStockQuantity() 
	{
		return stockQuantity;
	}
	public void setWarningMinQuantity(Long warningMinQuantity) 
	{
		this.warningMinQuantity = warningMinQuantity;
	}

	public Long getWarningMinQuantity() 
	{
		return warningMinQuantity;
	}
	public void setWarningMaxQuantity(Long warningMaxQuantity) 
	{
		this.warningMaxQuantity = warningMaxQuantity;
	}

	public Long getWarningMaxQuantity() 
	{
		return warningMaxQuantity;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("goodsName", getGoodsName())
            .append("orderNum", getOrderNum())
            .append("warehouseA", getWarehouseA())
            .append("warehouseB", getWarehouseB())
            .append("warehouseC", getWarehouseC())
            .append("warehouseD", getWarehouseD())
            .append("warehouseE", getWarehouseE())
            .append("warehouseAll", getWarehouseAll())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("stockQuantity", getStockQuantity())
            .append("warningMinQuantity", getWarningMinQuantity())
            .append("warningMaxQuantity", getWarningMaxQuantity())
            .toString();
    }
}
