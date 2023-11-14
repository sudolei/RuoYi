package com.ruoyi.isky.mapper;

import com.ruoyi.isky.domain.WarehouseGoods;

import java.util.List;

/**
 * 库存树 数据层
 * 
 * @author sunlei
 * @date 2019-11-05
 */
public interface WarehouseGoodsMapper 
{
	/**
     * 查询库存树信息
     * 
     * @param id 库存树ID
     * @return 库存树信息
     */
	public WarehouseGoods selectWarehouseGoodsById(Long id);
	
	/**
     * 查询库存树列表
     * 
     * @param warehouseGoods 库存树信息
     * @return 库存树集合
     */
	public List<WarehouseGoods> selectWarehouseGoodsList(WarehouseGoods warehouseGoods);
	
	/**
     * 新增库存树
     * 
     * @param warehouseGoods 库存树信息
     * @return 结果
     */
	public int insertWarehouseGoods(WarehouseGoods warehouseGoods);
	
	/**
     * 修改库存树
     * 
     * @param warehouseGoods 库存树信息
     * @return 结果
     */
	public int updateWarehouseGoods(WarehouseGoods warehouseGoods);
	
	/**
     * 删除库存树
     * 
     * @param id 库存树ID
     * @return 结果
     */
	public int deleteWarehouseGoodsById(Long id);
	
	/**
     * 批量删除库存树
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWarehouseGoodsByIds(String[] ids);



	public int selectCount(WarehouseGoods warehouseGoods);
	
}