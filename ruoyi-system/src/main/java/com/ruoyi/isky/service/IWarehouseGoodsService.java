package com.ruoyi.isky.service;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.isky.domain.WarehouseGoods;

import java.util.List;

/**
 * 库存树 服务层
 * 
 * @author sunlei
 * @date 2019-11-05
 */
public interface IWarehouseGoodsService 
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
	 * 查询部门管理树
	 *
	 * @param warehouseGoods 部门信息
	 * @return 所有部门信息
	 */
	public List<Ztree> selectDeptTree(WarehouseGoods warehouseGoods);


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
     * 删除库存树信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWarehouseGoodsByIds(String ids);



	/**
	 * 删除 信息
	 *
	 * @param id 部门ID
	 * @return 结果
	 */
	public int deleteWarehouseGoodsById(Long id);


	/**
	 * 查询部门人数
	 *
	 * @param parentId 父部门ID
	 * @return 结果
	 */
	public int selectCount(Long parentId);
	
}
